package ar.com.auster.wifi.portal_server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PortalInfo {
    @Getter @Setter
    public String clientMac;
    @Getter @Setter
    public String clientIp;
    @Getter @Setter
    public long t;
    @Getter @Setter
    public String site; //validar
    @Getter @Setter
    public String redirectUrl;
    @Getter @Setter
    public String apMac; //validar
    @Getter @Setter
    public String ssidName; //Validar
    @Getter @Setter
    public int radioId;
}
