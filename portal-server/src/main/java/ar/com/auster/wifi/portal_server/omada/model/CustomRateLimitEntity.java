package ar.com.auster.wifi.portal_server.omada.model;

public class CustomRateLimitEntity {
    private boolean upEnable;       //Up limit enable
    private int upUnit;             //Up limit unit should be a value as follows: 1: Kbps; 2: Mbps
    private int upLimit;            //Up limit should be within the range of 1–1024.
    private boolean downEnable;	    //Down limit enable
    private int downUnit;	        //Down limit unit should be a value as follows: 1: Kbps; 2: Mbps
    private int downLimit;	        //Down limit should be within the range of 1–1024.

    public boolean isUpEnable() {
        return upEnable;
    }

    public void setUpEnable(boolean upEnable) {
        this.upEnable = upEnable;
    }

    public int getUpUnit() {
        return upUnit;
    }

    public void setUpUnit(int upUnit) {
        this.upUnit = upUnit;
    }

    public int getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(int upLimit) {
        this.upLimit = upLimit;
    }

    public boolean isDownEnable() {
        return downEnable;
    }

    public void setDownEnable(boolean downEnable) {
        this.downEnable = downEnable;
    }

    public int getDownUnit() {
        return downUnit;
    }

    public void setDownUnit(int downUnit) {
        this.downUnit = downUnit;
    }

    public int getDownLimit() {
        return downLimit;
    }

    public void setDownLimit(int downLimit) {
        this.downLimit = downLimit;
    }
}
