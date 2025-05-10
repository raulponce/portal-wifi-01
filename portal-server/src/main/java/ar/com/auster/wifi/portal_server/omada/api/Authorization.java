package ar.com.auster.wifi.portal_server.omada.api;

import ar.com.auster.wifi.portal_server.omada.model.CommonResponse;
import ar.com.auster.wifi.portal_server.omada.model.TokenInput;
import ar.com.auster.wifi.portal_server.omada.model.TokenOuput;
import retrofit2.Call;
import retrofit2.http.*;

public interface Authorization {

    @POST("/openapi/authorize/token")
    @Headers({"Content-Type: appllication/json"})
    Call<CommonResponse<TokenOuput>> getToken(@Query("grant_type") String grantType, @Query("refresh_token") String refreshToken, @Body TokenInput input);

}
