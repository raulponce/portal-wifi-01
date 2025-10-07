package ar.com.auster.wifi.portal_server.meraki.model;

import java.time.OffsetDateTime;

public class Ssids {

    public static class Ssid {
        private boolean isAuthorized;
        private OffsetDateTime authorizedAt;
        private OffsetDateTime expiresAt;

        public boolean isAuthorized() {
            return isAuthorized;
        }

        public void setAuthorized(boolean authorized) {
            isAuthorized = authorized;
        }

        public OffsetDateTime getAuthorizedAt() {
            return authorizedAt;
        }

        public void setAuthorizedAt(OffsetDateTime authorizedAt) {
            this.authorizedAt = authorizedAt;
        }

        public OffsetDateTime getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(OffsetDateTime expiresAt) {
            this.expiresAt = expiresAt;
        }
    }

    private Ssid _0;
    private Ssid _1;
    private Ssid _2;
    private Ssid _3;
    private Ssid _4;
    private Ssid _5;
    private Ssid _6;
    private Ssid _7;
    private Ssid _8;
    private Ssid _9;
    private Ssid _10;
    private Ssid _11;
    private Ssid _12;
    private Ssid _13;
    private Ssid _14;
}
