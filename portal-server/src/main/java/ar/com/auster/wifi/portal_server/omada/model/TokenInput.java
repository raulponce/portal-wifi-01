package ar.com.auster.wifi.portal_server.omada.model;

public class TokenInput {

    private String client_id;
    private String client_secret;
    private String omadacId;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getOmadacId() {
        return omadacId;
    }

    public void setOmadacId(String omadacId) {
        this.omadacId = omadacId;
    }
}
