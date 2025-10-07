package ar.com.auster.wifi.portal_server.meraki.services;

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

    public void setToken(String newToken) {
        if (authorizationValue != newToken) {
            authorizationValue = newToken != null? newToken.trim():null;
            if (authorizationValue != null && !authorizationValue.isEmpty()) {
                log.info("New authorization token: 'token.getAccessToken()'");
            } else {
                clearToken();
            }
        }
    }

    public void clearToken() {
        authorizationValue = null;
        log.warn("New authorization token: null");
    }

    public String getToken() {
        return authorizationValue;
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
