package ar.com.auster.wifi.portal_server.omada.model;

import java.util.List;

public class ClientLockToApSetting {

    private boolean enable;             //Lock to AP enable.
    private List<ApBriefInfoVO> aps;    //AP name and MAC info list.

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<ApBriefInfoVO> getAps() {
        return aps;
    }

    public void setAps(List<ApBriefInfoVO> aps) {
        this.aps = aps;
    }
}
