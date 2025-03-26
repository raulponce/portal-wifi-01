package ar.com.auster.wifi.portal_server.omada.api;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Client {

    @GET("/openapi/v1/{omadac_id}/sites/{site_id}/hotspot/clients/{client_mac}/auth")
    @Headers({"Content-Type: appllication/json"})
    void authorize(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);

    @GET("/openapi/v1/{omadac_id}/sites/{site_id}/hotspot/clients/{client_mac}/unauth")
    @Headers({"Content-Type: appllication/json"})
    void unAuthorize(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);

    @GET("/openapi/v1/{omadac_id}/sites/{site_id}/clients/{client_mac}/reconnect")
    @Headers({"Content-Type: appllication/json"})
    void reConnect(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);
    // https://localhost:8043/openapi/v1/{{omadac_id}}/sites/{{site_id}}/hotspot/authed-records/{{id}}/disconnect

    @GET("/openapi/v1/{omadac_id}/sites/{site_id}/clients/{client_mac}")
    @Headers({"Content-Type: appllication/json"})
    void delete(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);

    @GET("/openapi/v1/{omadac_id}/sites/{site_id}/clients/{client_mac}/block")
    @Headers({"Content-Type: appllication/json"})
    void block(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);

    @GET("/openapi/v1/{omadac_id}/sites/{site_id}/clients/{client_mac}/unblock")
    @Headers({"Content-Type: appllication/json"})
    void unBlock(@Path("omadac_id")String omadacId, @Path("site_id")String siteId, @Path("client_mac")String clientMac);
}
