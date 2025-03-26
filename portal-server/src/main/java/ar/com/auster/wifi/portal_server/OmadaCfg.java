package ar.com.auster.wifi.portal_server;

import ar.com.auster.wifi.portal_server.omada.api.Authorization;
import ar.com.auster.wifi.portal_server.omada.api.Client;
import ar.com.auster.wifi.portal_server.omada.services.TokenHeaderInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class OmadaCfg {

    @Bean
    public Authorization buildOmadaAPIAuthorization(@Value("${omada.api.urlBase}") String urlBase) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Authorization api = retrofit.create(Authorization.class);
        return api;
    }

    @Bean
    public Client buildOmadaAPIClient(@Value("${omada.api.urlBase}") String urlBase, TokenHeaderInterceptor authInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(authInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Client api = retrofit.create(Client.class);
        return api;
    }

    @Bean
    public TokenHeaderInterceptor authInterceptor() {
        return new TokenHeaderInterceptor();
    }
}
