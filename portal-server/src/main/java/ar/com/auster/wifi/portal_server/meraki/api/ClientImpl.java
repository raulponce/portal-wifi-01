package ar.com.auster.wifi.portal_server.meraki.api;

import ar.com.auster.meraki.v1.ApiClient;
import ar.com.auster.meraki.v1.ApiException;
import ar.com.auster.meraki.v1.api.OrganizationsApi;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Response;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientImpl /*implements Client*/ {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(ClientImpl.class);

    private ApiClient apiClient;
    private OrganizationsApi organizationsApi;

    @PostConstruct
    private void onPost() {
        String merakiApiKey = "";
        String merakyBaseURL = "";

        apiClient = new ApiClient();
//        apiClient.setApiKey(merakiApiKey);
        apiClient.setAccessToken(merakiApiKey);
        apiClient.setBasePath(merakyBaseURL);

        organizationsApi = new OrganizationsApi(apiClient);
    }

    public String[] orgenizationsId() {
        try {
            Call call = organizationsApi.getOrganizationsCall(10, null, null, null, null);
            Response resp =  call.execute();
            if (resp != null && resp.isSuccessful()) {
            } else {

            }
        } catch (ApiException | IOException e) {
            log.error("ERROR", e);
        }
        return null;
    }
}
