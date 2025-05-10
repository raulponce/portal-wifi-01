package ar.com.auster.wifi.portal_server.omada.model;

public enum ClientAuthStatus {
    PENDING(1), AUTHORIZED(2), AUTH_FREE(3);

    private int code;

    ClientAuthStatus(int c) {
        this.code = c;
    }

    public int getCode() {
        return code;
    }

    public ClientAuthStatus fromCode(int c) {
        for (ClientAuthStatus item : values()) {
            if (c == item.code)
                return item;
        }
        return null;
    }
}
