package ar.com.auster.wifi.portal_server.omada.mock;

import ar.com.auster.wifi.portal_server.omada.api.Client;
import ar.com.auster.wifi.portal_server.omada.model.ClientGridVOClientInfo;
import ar.com.auster.wifi.portal_server.omada.model.ClientInfo;
import ar.com.auster.wifi.portal_server.omada.model.CommonResponse;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class ClientMock implements Client {

//    private final BehaviorDelegate<CommonResponse<Void>> commonVoidDelegate;
//    private final BehaviorDelegate<CommonResponse<ClientGridVOClientInfo>> commonClientGridVOClientInfo;
//    private final BehaviorDelegate<CommonResponse<ClientInfo>> commonClientInfo;
//
//    public ClientMock(BehaviorDelegate<CommonResponse<Void>> commonVoidDelegate,
//                      BehaviorDelegate<CommonResponse<ClientGridVOClientInfo>> commonClientGridVOClientInfo,
//                      BehaviorDelegate<CommonResponse<ClientInfo>> commonClientInfo) {
//        this.commonVoidDelegate = commonVoidDelegate;
//        this.commonClientGridVOClientInfo = commonClientGridVOClientInfo;
//        this.commonClientInfo = commonClientInfo;
//    }

    @Override
    public Call<CommonResponse<Void>> authorize(String omadacId, String siteId, String clientMac) {
        CommonResponse<Void> result = new CommonResponse<Void>();
        result.setErrorCode(0);
        result.setMsg(null);
        return Calls.response(result);
    }

    @Override
    public Call<CommonResponse<Void>> unAuthorize(String omadacId, String siteId, String clientMac) {
        CommonResponse<Void> result = new CommonResponse<Void>();
        result.setErrorCode(0);
        result.setMsg(null);
        return Calls.response(result);
    }

    @Override
    public Call<CommonResponse<ClientGridVOClientInfo>> getClients(int page, int pageSize) {
        return Calls.failure(new RuntimeException("NOT IMPLEMENTED"));
    }

    @Override
    public Call<CommonResponse<ClientInfo>> getClientInfo(String omadacId, String siteId, String clientMac) {
        return Calls.failure(new RuntimeException("NOT IMPLEMENTED"));
    }
}
