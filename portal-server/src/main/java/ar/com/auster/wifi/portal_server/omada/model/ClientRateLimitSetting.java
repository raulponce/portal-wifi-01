package ar.com.auster.wifi.portal_server.omada.model;

public class ClientRateLimitSetting {

    private int mode;                       //Rate limit mode should be a value as follows:
                                            // 0: Custom mode. Apply the given rate limit value to the client;
                                            // 1: Rate limit profile mode. Find the corresponding rate limit file with rate limit ID and apply it to the client.
    private String rateLimitProfileId;      // Rate limit profile ID. Required when ratelimit mode is 1
    private CustomRateLimitEntity customRateLimit;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getRateLimitProfileId() {
        return rateLimitProfileId;
    }

    public void setRateLimitProfileId(String rateLimitProfileId) {
        this.rateLimitProfileId = rateLimitProfileId;
    }

    public CustomRateLimitEntity getCustomRateLimit() {
        return customRateLimit;
    }

    public void setCustomRateLimit(CustomRateLimitEntity customRateLimit) {
        this.customRateLimit = customRateLimit;
    }
}
