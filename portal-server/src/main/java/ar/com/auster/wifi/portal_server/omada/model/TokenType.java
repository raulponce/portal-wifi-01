package ar.com.auster.wifi.portal_server.omada.model;

public enum TokenType {
    BEARER("bearer");

    private String code;

    TokenType(String c) {
        this.code = c;
    }

    public String getCode() {
        return code;
    }
}
