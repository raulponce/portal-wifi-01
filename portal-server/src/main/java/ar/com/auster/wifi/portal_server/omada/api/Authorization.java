package ar.com.auster.wifi.portal_server.omada.api;

import ar.com.auster.wifi.portal_server.omada.model.CommonResponse;
import ar.com.auster.wifi.portal_server.omada.model.TokenInput;
import ar.com.auster.wifi.portal_server.omada.model.TokenOuput;
import retrofit2.http.*;

public interface Authorization {

    @POST("/openapi/authorize/token")
    @Headers({"Content-Type: appllication/json"})
    CommonResponse<TokenOuput> getToken(@Query("grant_type") String grantType, @Body TokenInput input);

}
