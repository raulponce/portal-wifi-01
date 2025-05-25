package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.model.Device;
import ar.com.auster.wifi.portal_server.model.OmadaControllerException;
import ar.com.auster.wifi.portal_server.model.PortalInfo;

public interface ISessionService {

    boolean refreshToken();

    Device getClientStatus(String mac, String siteId) throws OmadaControllerException;

    boolean unAuth(String siteId, String clientMac);

    boolean isValid(PortalInfo portalInfo);
}
