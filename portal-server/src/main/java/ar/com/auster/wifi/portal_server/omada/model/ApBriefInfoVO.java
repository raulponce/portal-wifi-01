package ar.com.auster.wifi.portal_server.omada.model;

public class ApBriefInfoVO {
    private String name;    //AP name.
    private String mac;	    //AP MAC, for example: AA-AA-AA-AA-AA-AA.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
