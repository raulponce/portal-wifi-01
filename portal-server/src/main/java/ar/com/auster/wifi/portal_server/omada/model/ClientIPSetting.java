package ar.com.auster.wifi.portal_server.omada.model;

public class ClientIPSetting {

    private boolean useFixedAddr;   //Whether to use the specified IP.
    private String netId;           //LAN network ID.
    private String ip;              //Client IP

    public boolean isUseFixedAddr() {
        return useFixedAddr;
    }

    public void setUseFixedAddr(boolean useFixedAddr) {
        this.useFixedAddr = useFixedAddr;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
