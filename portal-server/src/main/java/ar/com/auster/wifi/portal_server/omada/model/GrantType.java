package ar.com.auster.wifi.portal_server.omada.model;

public enum GrantType {
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");

    private String valor;

    GrantType(String v) {
        this.valor = v;
    }

    public String getValor() {
        return this.valor;
    }

    public GrantType fromValor(String v) {
        for (GrantType item : GrantType.values()) {
            if (v.equals(item.valor)) {
                return item;
            }
        }
        return null;
    }
}
