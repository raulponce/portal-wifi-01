package ar.com.auster.wifi.portal_server.omada.api;

import ar.com.auster.wifi.portal_server.omada.model.ClientGridVOClientInfo;
import ar.com.auster.wifi.portal_server.omada.model.ClientInfo;
import ar.com.auster.wifi.portal_server.omada.model.CommonResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface Client {

    @POST("/openapi/v1/{omadac_id}/sites/{site_id}/hotspot/clients/{client_mac}/auth")
    @Headers({"Content-Type: appllication/json"})
    Call<CommonResponse<Void>> authorize(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);

    @POST("/openapi/v1/{omadac_id}/sites/{site_id}/hotspot/clients/{client_mac}/unauth")
    @Headers({"Content-Type: appllication/json"})
    Call<CommonResponse<Void>> unAuthorize(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);

    @GET("/openapi/v1/{omadac_id}/sites/{site_id}/clients")
    @Headers({"Content-Type: appllication/json"})
    Call<CommonResponse<ClientGridVOClientInfo>> getClients(@Query("page")int page, @Query("pageSize")int pageSize);

    @GET("/openapi/v1/{omadac_id}/sites/{site_id}/clients/{client_mac}")
    @Headers({"Content-Type: appllication/json"})
    Call<CommonResponse<ClientInfo>> getClientInfo(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);

}
