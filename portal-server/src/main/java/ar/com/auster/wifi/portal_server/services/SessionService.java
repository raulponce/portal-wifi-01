package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.api.v1.Vouchers;
import ar.com.auster.wifi.portal_server.model.*;
import ar.com.auster.wifi.portal_server.omada.api.Authorization;
import ar.com.auster.wifi.portal_server.omada.api.Client;
import ar.com.auster.wifi.portal_server.omada.model.*;
import ar.com.auster.wifi.portal_server.omada.services.TokenHeaderInterceptor;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SessionService implements ISessionService, IVoucherService {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(SessionService.class);

    @Autowired
    private TokenHeaderInterceptor tokenIntereceptor;

    @Autowired
    private Authorization omadaAuthApi;

    @Autowired
    private IDAOData daoData;

    @Autowired
    private Client omadaClientApi;

    @Value("${omada.client.id}")
    private String clientId;
    @Value("${omada.client.secret}")
    private String clientSecret;
    @Value("${omada.omadac.id}")
    private String omadacId;

    @Autowired
    private IVoucherByTimeTask voucherByTimeTask;

    @PostConstruct
    private void onInit() {
        voucherByTimeTask.setSessionService(this);
        voucherByTimeTask.startTask();
    }

    @PreDestroy
    private void onDestroy() {
        voucherByTimeTask.stopTask();;
    }

    private boolean processTokenResponse(CommonResponse<TokenOuput> newTokenResponse, OffsetDateTime tokenRequestTime) {
        if (newTokenResponse != null && newTokenResponse.getErrorCode() == 0 && newTokenResponse.getResult() != null) {
            //validar token
            TokenOuput token = newTokenResponse.getResult();
            if (token.getAccessToken() != null && !token.getAccessToken().isEmpty() &&
                    token.getRefreshToken() != null && !token.getRefreshToken().isEmpty() &&
                    TokenType.BEARER.getCode().equals(token.getTokenType())) {
                OffsetDateTime tokenExpiredTime = tokenRequestTime.plusSeconds(token.getExpiresIn());
                tokenIntereceptor.setToken(token, tokenRequestTime, tokenExpiredTime);
                return true;
            } else {
                tokenIntereceptor.clearToken();
                log.warn("Token obtenido no valido");
                return false;
            }
        } else {
            tokenIntereceptor.clearToken();
            log.warn("Token obtenido vacio");
            return false;
        }
    }

    public boolean refreshToken() {
        try {
            log.info("RefreshToken Begin");
            // 1.- Hay token en tokenInterceptor?
            TokenOuput currentToken = tokenIntereceptor.getToken();
            if (currentToken != null) { // 1.1.- Si. Hay token
                OffsetDateTime currentExpiredTime = tokenIntereceptor.getExpiredTime();
                if (OffsetDateTime.now().isAfter(currentExpiredTime)) { // Expiró?
                    // 1.1.1.- Si. renovar
                    TokenInput tokenInput = new TokenInput();
                    tokenInput.setClient_id(clientId);
                    tokenInput.setClient_secret(clientSecret);
                    tokenInput.setOmadacId(null);
                    Call<CommonResponse<TokenOuput>> newTokenCall = omadaAuthApi.getToken(GrantType.REFRESH_TOKEN.getValor(), currentToken.getRefreshToken(), tokenInput);
                    Response<CommonResponse<TokenOuput>> newTokenResponse = newTokenCall.execute();
                    CommonResponse<TokenOuput> newTokenResult = newTokenResponse.body();
                    if (newTokenResponse.isSuccessful() && newTokenResult != null) {
                        OffsetDateTime tokenRequestTime = OffsetDateTime.now();
                        boolean tokenRefreshSuccess = processTokenResponse(newTokenResult, tokenRequestTime);
                        if (!tokenRefreshSuccess) { // 1.1.2.- No. Solicitar uno nuevo
                            tokenInput = new TokenInput();
                            tokenInput.setClient_id(clientId);
                            tokenInput.setClient_secret(clientSecret);
                            tokenInput.setOmadacId(omadacId);
                            newTokenCall = omadaAuthApi.getToken(GrantType.CLIENT_CREDENTIALS.getValor(), null, tokenInput);
                            newTokenResponse = newTokenCall.execute();
                            newTokenResult = newTokenResponse.body();
                            if (newTokenResponse.isSuccessful() && newTokenResult != null) {
                                tokenRequestTime = OffsetDateTime.now();
                                return processTokenResponse(newTokenResult, tokenRequestTime);
                            } else {
                                log.error("Fallo el intento de solicitar un nuevo el token");
                                return false;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        log.error("Fallo el intento de renovar el token");
                        return false;
                    }
                } else { // 1.2.- No. nada
                    return true;
                }

            } else { // 2.- No. Solicitar uno nuevo
                TokenInput tokenInput = new TokenInput();
                tokenInput.setClient_id(clientId);
                tokenInput.setClient_secret(clientSecret);
                tokenInput.setOmadacId(omadacId);
                Call<CommonResponse<TokenOuput>> newTokenCall = omadaAuthApi.getToken(GrantType.CLIENT_CREDENTIALS.getValor(), null, tokenInput);
                Response<CommonResponse<TokenOuput>> newTokenResponse = newTokenCall.execute();
                CommonResponse<TokenOuput> newTokenResult = newTokenResponse.body();
                if (newTokenResponse.isSuccessful() && newTokenResult != null) {
                    OffsetDateTime tokenRequestTime = OffsetDateTime.now();
                    return processTokenResponse(newTokenResult, tokenRequestTime);
                } else {
                    log.error("Fallo el intento de solicitar un nuevo el token");
                    return false;
                }
            }
        } catch (IOException e) {
            log.error("ERROR Refresh Token", e);
            return false;
        } finally {
            log.info("RefreshToken End");
        }
    }

    public Device getClientStatus(String mac, String siteId) throws OmadaControllerException {
        if (!refreshToken()) {
            throw new OmadaControllerException("No se pudo autenticar contra el omada controller");
        }
        try {
            // 1.- consultar estado en el omadacontroller
            final CommonResponse<ClientInfo>[] clientInfo = new CommonResponse[]{null};
            Thread getClientInfoTask = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Call<CommonResponse<ClientInfo>> clientInfoCall = omadaClientApi.getClientInfo(omadacId, siteId, mac);
                        Response<CommonResponse<ClientInfo>> clientInfoResponse = clientInfoCall.execute();
                        if (clientInfoResponse.isSuccessful()) {
                            clientInfo[0] = clientInfoResponse.body();
                        }
                    } catch (IOException e) {
                        clientInfo[0] = null;
                    }
                }
            });

            // 2.- consultar estado en la base de datos


            // 3.- constratar y consolidar datos
            try {
                getClientInfoTask.join();
            } catch (InterruptedException e) {
            }

//        } catch (IOException e) {
//            throw new InternalError();
        } finally {
        }
        return null;
    }

    public boolean unAuth(String siteId, String clientMac) {
        if (!refreshToken()) {
            log.error("No se pudo autenticar contra el omada controller. Client {}",clientMac);
            return false;
        }
        try {
            Call<CommonResponse<Void>> callUnAuthReq = omadaClientApi.unAuthorize(omadacId, siteId, clientMac);
            Response<CommonResponse<Void>> respUnAuthReq = callUnAuthReq.execute();
            if (respUnAuthReq != null && respUnAuthReq.isSuccessful()) {
                CommonResponse<Void> respUnAuth = respUnAuthReq.body();
                if (respUnAuth != null && respUnAuth.getErrorCode() == 0) {
                    log.info("Cliente {} desautorizado del site {} con exito", clientMac, siteId);
                    return true;
                } else {
                    log.warn("No se pudo des-autorizar al cliente {} del site {}", clientMac, siteId);
                }
            } else {
                log.warn("No se pudo des-autorizar al cliente {} del site {}", clientMac, siteId);
            }
        } catch (Throwable t) {
            log.error("ERROR", t);
        }
        return false;

    }

    //IVoucherService
    public List<VoucherType> getVoucherTypes() {
        return Arrays.stream(VoucherType.values()).toList();
    }

    public List<Voucher> getVoucher() {
        List<Voucher> vouchers = new ArrayList<>();
        log.info("Voucher list solicitadas");
        try {
            vouchers.addAll(daoData.getVouchers());
        } catch (Throwable e) {
            log.error("ERROR", e);
        }
        return vouchers;
    }

    public boolean buyVoucher(Voucher voucher, Vouchers.OmadaQParam omadaQParam) {
        if (voucher.getType() == VoucherType.BY_DATA) {
            log.info("Voucher no disponible. Client {} ({}) Voucher {}", omadaQParam.clientMac, omadaQParam.clientIp, voucher.getName());
            return false;
        }

        if (!refreshToken()) {
            log.error("No se pudo autenticar contra el omada controller. Client {} ({}) Voucher {}", omadaQParam.clientMac, omadaQParam.clientIp, voucher.getName());
            return false;
        }
        try {
            Call<CommonResponse<Void>> callAuthReq = omadaClientApi.authorize(this.omadacId, omadaQParam.site, omadaQParam.clientMac);
            Response<CommonResponse<Void>> respAuthReq = callAuthReq.execute();
            if (respAuthReq != null && respAuthReq.isSuccessful()) {
                CommonResponse<Void> respAuth = respAuthReq.body();
                if (respAuth != null && respAuth.getErrorCode() == 0) {
                    log.info("Cliente {} ({}) autorizado Voucher {}", omadaQParam.clientMac, omadaQParam.clientIp, voucher.getName());
                    if (voucher.getType() == VoucherType.BY_TIME) {
                        voucherByTimeTask.add(omadaQParam, voucher);
                    }
                    return true;
                }
            }

        } catch (Throwable t) {
            log.error("ERROR", t);
        }
        log.info("Pago no aceptado: Cliente {} ({}) Voucher {}", omadaQParam.clientMac, omadaQParam.clientIp, voucher.getName());
        return false;
    }
}
