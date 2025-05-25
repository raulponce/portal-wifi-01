package ar.com.auster.wifi.portal_server;

import ar.com.auster.wifi.portal_server.omada.api.Authorization;
import ar.com.auster.wifi.portal_server.omada.api.Client;
import ar.com.auster.wifi.portal_server.omada.mock.AuthorizationMock;
import ar.com.auster.wifi.portal_server.omada.mock.ClientMock;
import ar.com.auster.wifi.portal_server.omada.services.TokenHeaderInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.net.ssl.*;
import java.security.cert.CertificateException;

@Configuration
public class OmadaCfg {

    private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Authorization buildOmadaAPIAuthorization(@Value("${omada.api.urlBase}") String urlBase, @Value("${omada.api.mock.enable:false}")boolean useMock) {
        Authorization api = null;
        if (useMock) {
            api = new AuthorizationMock();
        } else {
            OkHttpClient.Builder httpClient = getUnsafeOkHttpClient();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(urlBase)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            api = retrofit.create(Authorization.class);
        }
        return api;
    }

    @Bean
    public Client buildOmadaAPIClient(@Value("${omada.api.urlBase}") String urlBase, TokenHeaderInterceptor authInterceptor, @Value("${omada.api.mock.enable:false}")boolean useMock) {
        Client api = null;
        if (useMock) {
            api = new ClientMock();
        } else {
            OkHttpClient.Builder httpClient = getUnsafeOkHttpClient();
            httpClient.addInterceptor(authInterceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(urlBase)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            api = retrofit.create(Client.class);
        }
        return api;
    }

    @Bean
    public TokenHeaderInterceptor authInterceptor() {
        return new TokenHeaderInterceptor();
    }
}
