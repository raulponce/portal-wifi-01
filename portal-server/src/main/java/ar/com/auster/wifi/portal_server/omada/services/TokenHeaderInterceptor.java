package ar.com.auster.wifi.portal_server.omada.services;

import ar.com.auster.wifi.portal_server.omada.model.TokenOuput;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.OffsetDateTime;

public class TokenHeaderInterceptor implements Interceptor {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(TokenHeaderInterceptor.class);

    private String authorizationValue = null;
    private TokenOuput token = null;
    private OffsetDateTime requestedTime = null;
    private OffsetDateTime expiredTime = null;

    public void setToken(TokenOuput newToken, OffsetDateTime requestedTime, OffsetDateTime expiredTime) {
        if (token != newToken) {
            token = newToken;
            if (token != null && token.getAccessToken() != null && !token.getAccessToken().isEmpty()) {
                this.authorizationValue = String.format("AccessToken=%s", token.getAccessToken().trim());
                this.requestedTime = requestedTime;
                this.expiredTime = expiredTime;
                log.info("New authorization token: 'token.getAccessToken()'");
            } else {
                clearToken();
            }
        }
    }

    public void clearToken() {
        authorizationValue = null;
        token = null;
        requestedTime = null;
        expiredTime = null;
        log.warn("New authorization token: null");
    }

    public TokenOuput getToken() {
        return token;
    }

    public OffsetDateTime getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(OffsetDateTime requestedTime) {
        this.requestedTime = requestedTime;
    }

    public OffsetDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(OffsetDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalReq = chain.request();
        if (authorizationValue != null) {
            Request modifiedReq = originalReq.newBuilder()
                    .addHeader("Authorization",  authorizationValue)
                    .build();
            return chain.proceed(modifiedReq);
        } else {
            return chain.proceed(originalReq);
        }
    }
}
