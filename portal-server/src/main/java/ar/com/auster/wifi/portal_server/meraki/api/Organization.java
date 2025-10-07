package ar.com.auster.wifi.portal_server.meraki.api;

import ar.com.auster.wifi.portal_server.meraki.model.Network;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Organization {

    @POST("/api/v1/organizations")
    @Headers({"Accept: appllication/json",
            "'Authorization: Bearer dd05e1617bd8a0b7cfebddd296facc719395507e"})
    Call<Organization> getOrganizations(@Query(value = "perPage", encoded = true)Integer perPage); //, String startingAfter, String endingBefore

    //GET /organizations/{organizationId}/networks
    @GET("/api/v1/organizations/{organizationId}/networks")
    @Headers({"Accept: appllication/json"})
    Call<List<Network>> getNetworks(@Path(value = "organizationId", encoded = true) String organizationId);
}
