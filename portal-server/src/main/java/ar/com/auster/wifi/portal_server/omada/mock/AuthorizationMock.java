package ar.com.auster.wifi.portal_server.omada.mock;

import ar.com.auster.wifi.portal_server.omada.api.Authorization;
import ar.com.auster.wifi.portal_server.omada.model.CommonResponse;
import ar.com.auster.wifi.portal_server.omada.model.TokenInput;
import ar.com.auster.wifi.portal_server.omada.model.TokenOuput;
import ar.com.auster.wifi.portal_server.omada.model.TokenType;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.Calls;

import java.time.OffsetDateTime;

public class AuthorizationMock implements Authorization {

    @Override
    public Call<CommonResponse<TokenOuput>> getToken(String grantType, String refreshToken, TokenInput input) {
        CommonResponse<TokenOuput> result = new CommonResponse<TokenOuput>();
        result.setErrorCode(0);
        TokenOuput token = new TokenOuput();
        token.setTokenType(TokenType.BEARER.getCode());
        token.setAccessToken("valid_access_token");
        token.setRefreshToken("valid_refresh_token");
        token.setExpiresIn(OffsetDateTime.now().plusDays(1).toEpochSecond());
        result.setResult(token);
        return Calls.response(Response.success(result));
    }
}
