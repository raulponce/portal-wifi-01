package ar.com.auster.wifi.portal_server.meraki.api;

import ar.com.auster.wifi.portal_server.meraki.model.ClientStatus;
import ar.com.auster.wifi.portal_server.meraki.model.Ssids;
import retrofit2.Call;
import retrofit2.http.*;

public interface Client {

    //GET /networks/{networkId}/clients/{clientId}/splashAuthorizationStatus
    @GET("/api/v1/networks/{networkId}/clients/{clientId}/splashAuthorizationStatus")
    @Headers({"Accept: appllication/json"})
    Call<ClientStatus> getAutorizationStatus(@Path(value = "networkId", encoded = true)String networkId, @Path(value = "clientId", encoded = true) String clientId);

    //PUT /networks/{networkId}/clients/{clientId}/splashAuthorizationStatus
    @PUT("/api/v1/networks/{networkId}/clients/{clientId}/splashAuthorizationStatus")
    @Headers({"Content-Type: appllication/json", "Accept: appllication/json"})
    Call<ClientStatus> putAuthorizationStatus(@Path(value = "networkId", encoded = true)String networkId, @Path(value = "clientId", encoded = true) String clientId, @Body() Ssids ssids);

    //el reino de los cielos
}
