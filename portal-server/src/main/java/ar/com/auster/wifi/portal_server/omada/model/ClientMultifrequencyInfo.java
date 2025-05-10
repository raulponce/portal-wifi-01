package ar.com.auster.wifi.portal_server.omada.model;

public class ClientMultifrequencyInfo {
    private int radioId;	    //Radio ID should be a value as follows: 0: 2.4GHz; 1: 5GHz-1; 2:5GHz-2; 3: 6GHz.
    private int wifiMode;	    //Wi-Fi mode should be a value as follows: 0: 11a; 1: 11b; 2: 11g; 3: 11na; 4: 11ng; 5: 11ac; 6: 11axa; 7: 11axg.	integer(int32)
    private int channel;	    //(Wireless) Actual channel.	integer(int32)
    private int rxRate;	        //(Wireless) Uplink negotiation rate (Kbit/s).	integer(int64)
    private int txRate;	        //(Wireless) Downlink negotiation rate (Kbit/s).	integer(int64)
    private boolean powerSave;	//(Wireless) true: Power save mode enabled.	boolean
    private int rssi;	        //(Wireless) Signal strength, unit: dBm.	integer(int32)
    private int snr;	        //(Wireless) Signal Noise Ratio.	integer(int32)
    private int signalLevel;	//(Wireless) Signal strength percentage should be within the range of 0-100.	integer(int32)
    private int signalRank;	    //(Wireless) Signal strength level should be within the range of 0-5.	integer(int32)
    private int upPacket;	    //Number of upstream packets.	integer(int64)
    private int downPacket;	    //Number of downstream packets.	integer(int64)
    private int trafficDown;	//Downstream traffic (Byte).	integer(int64)
    private int trafficUp;      //Upstream traffic (Byte).	integer(int64)
    private int activity;       //Real-time downlink rate (Byte/s).	integer(int64)
    private int signalLevelAndRank;

    public int getRadioId() {
        return radioId;
    }

    public void setRadioId(int radioId) {
        this.radioId = radioId;
    }

    public int getWifiMode() {
        return wifiMode;
    }

    public void setWifiMode(int wifiMode) {
        this.wifiMode = wifiMode;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getRxRate() {
        return rxRate;
    }

    public void setRxRate(int rxRate) {
        this.rxRate = rxRate;
    }

    public int getTxRate() {
        return txRate;
    }

    public void setTxRate(int txRate) {
        this.txRate = txRate;
    }

    public boolean isPowerSave() {
        return powerSave;
    }

    public void setPowerSave(boolean powerSave) {
        this.powerSave = powerSave;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getSnr() {
        return snr;
    }

    public void setSnr(int snr) {
        this.snr = snr;
    }

    public int getSignalLevel() {
        return signalLevel;
    }

    public void setSignalLevel(int signalLevel) {
        this.signalLevel = signalLevel;
    }

    public int getSignalRank() {
        return signalRank;
    }

    public void setSignalRank(int signalRank) {
        this.signalRank = signalRank;
    }

    public int getUpPacket() {
        return upPacket;
    }

    public void setUpPacket(int upPacket) {
        this.upPacket = upPacket;
    }

    public int getDownPacket() {
        return downPacket;
    }

    public void setDownPacket(int downPacket) {
        this.downPacket = downPacket;
    }

    public int getTrafficDown() {
        return trafficDown;
    }

    public void setTrafficDown(int trafficDown) {
        this.trafficDown = trafficDown;
    }

    public int getTrafficUp() {
        return trafficUp;
    }

    public void setTrafficUp(int trafficUp) {
        this.trafficUp = trafficUp;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getSignalLevelAndRank() {
        return signalLevelAndRank;
    }

    public void setSignalLevelAndRank(int signalLevelAndRank) {
        this.signalLevelAndRank = signalLevelAndRank;
    }
}
