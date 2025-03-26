package ar.com.auster.wifi.portal_server.omada.services;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TokenHeaderInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalReq = chain.request();
        Request modifiedReq = originalReq.newBuilder()
                .addHeader("Authorization", "AccessToken={{access_token}}")
                .build();
        return chain.proceed(modifiedReq);
    }
}
