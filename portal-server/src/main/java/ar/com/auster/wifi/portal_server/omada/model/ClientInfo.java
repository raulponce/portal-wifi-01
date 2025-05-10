package ar.com.auster.wifi.portal_server.omada.model;

import java.util.List;

public class ClientInfo {
    
    private String id;              //Client ID
    private String mac;             //Client MAC Address
    private String name;            //Client name / alias
    private String hostName;        //Host / Device name
    private String vendor;          //Vendor
    private String deviceType;      //Device Type: iphone, ipod, android, pc, printer, tv...
    private String deviceCategory;  //Device Category: loT, TV, computer, phone...
    private String osName;          //Device system version.
    private String ip;              //IP Address.
    private List<String> ipv6List;  //IPv6 Address.;

    private int connectType;                        //Connect type should be a value as follows: 0: wireless guest; 1: wireless user; 2: wired user.
    private String connectDevType;                  //connect device type should be a value as follows: ap, switch, gateway.
    private boolean connectedToWirelessRouter;      //true: Client is connecting to a wireless router.
    private boolean wireless;                       //true: Wireless device (connectDevType=ap); false: Not wireless device(connectDevType=switch or gateway).
    private String ssid;                            //(Wireless) SSID name.
    private int signalLevel;                        //(Wireless) Signal strength percentage should be within the range of 0-100.
    private int healthScore;                        //1~3: poor; 4~7: fair; 0: no data; 8~10 good.
    private int signalRank;                         //(Wireless) Signal strength level should be within the range of 0-5.
    private int wifiMode;                           //(Wireless) Wifi mode should be a value as follows: 0: 11a; 1: 11b; 2: 11g; 3: 11na; 4: 11ng; 5: 11ac; 6: 11axa; 7: 11axg; 8: 11beg; 9: 11bea.
    private String apName;                          //(Wireless) AP Name.
    private String apMac;                           //(Wireless) AP MAC Address.
    private int radioId;                            //(Wireless) Radio ID should be a value as follows: 0: 2.4GHz; 1: 5GHz-1; 2:5GHz-2; 3: 6GHz.
    private int channel;                            //(Wireless) Actual channel.
    private int rxRate;                             //(Wireless) Uplink negotiation rate (Kbit/s).
    private int txRate;                             //(Wireless) Downlink negotiation rate (Kbit/s).
    private boolean powerSave;                      //(Wireless) true: Power save mode enabled.
    private int rssi;                               //(Wireless) Signal strength, unit: dBm.
    private int snr;                                //(Wireless) Signal Noise Ratio.
    private String switchMac;                       //(Wired, connectDevType=switch) Switch MAC address.
    private String switchName;                      //(Wired, connectDevType=switch) Switch name.
    private String gatewayMac;                      //(Wired, connectDevType=gateway) Gateway MAC Address.
    private String gatewayName;                     //(Wired, connectDevType=gateway) Gateway name.
    private int vid;                                //(Wired) vlan.
    private String networkName;                     //(Wired) Network name.
    private String dot1xIdentity;                   //(Wired) 802.1x authentication identity.
    private int dot1xVlan;                          //(Wired) Network name corresponding to the VLAN obtained by 802.1x D-VLAN.
    private int port;                               //(Wired) Port ID.
    private int lagId;                              //(Wired) LAG ID. Exists only when the client is connected to the LAG.
    private int activity;                           //Real-time downlink rate (Byte/s).
    private int trafficDown;                        //Downstream traffic (Byte).
    private int trafficUp;                          //Upstream traffic (Byte).
    private int uptime;                             //Up time (unit: s).
    private int lastSeen;                           //Last found time, timestamp (ms).

    private int authStatus;                         //Authentication status should be a value as follows (@see ClientAuthStatus):
                                                    // 0: CONNECTED // Access without any authentication method;
                                                    // 1: PENDING // Access to Portal, but authentication failed;
                                                    // 2: AUTHORIZED // Pass through portal, pass other authentication without portal;
                                                    // 3: AUTH-FREE // No portal authentication required.
    private boolean blocked;                        //Whether the client is blocked.
    private boolean guest;                          //(Wireless) Whether it is Guest (used to display the wireless Guest client icon).
    private boolean active;                         //Whether the client is online.
    private boolean manager;                        //Whether it is the client currently being managed.

    private ClientIPSetting ipSetting;

    private int downPacket;                         //Number of downstream packets.
    private int upPacket;                           //Number of upstream packets.
    private ClientRateLimitSetting rateLimit;
    private ClientLockToApSetting clientLockToApSetting;
    private ClientMultifrequencyInfo multiLink;     //(Wireless) Client multifrequency info list.
    private int unit;                               //Unit ID.
    private String standardPort;                    //Standard port.
    private String systemName;	                    //Device system name.
    private String description;	                    //Device description.
    private List<String> capabilities;	            //One or more of the following values:
                                                    //  Station
                                                    //  DOCSIS cable device
                                                    //  Telephone
                                                    //  Router
                                                    //  WLAN access point
                                                    //  Bridge
                                                    //  Repeater
                                                    //  other
    private boolean blockDisable;	                //Block client disabled, default value: false.
    private int dhcpLeaseTime;	                    //DHCP lease time, unit seconds

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getIpv6List() {
        return ipv6List;
    }

    public void setIpv6List(List<String> ipv6List) {
        this.ipv6List = ipv6List;
    }

    public int getConnectType() {
        return connectType;
    }

    public void setConnectType(int connectType) {
        this.connectType = connectType;
    }

    public String getConnectDevType() {
        return connectDevType;
    }

    public void setConnectDevType(String connectDevType) {
        this.connectDevType = connectDevType;
    }

    public boolean isConnectedToWirelessRouter() {
        return connectedToWirelessRouter;
    }

    public void setConnectedToWirelessRouter(boolean connectedToWirelessRouter) {
        this.connectedToWirelessRouter = connectedToWirelessRouter;
    }

    public boolean isWireless() {
        return wireless;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getSignalLevel() {
        return signalLevel;
    }

    public void setSignalLevel(int signalLevel) {
        this.signalLevel = signalLevel;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public int getSignalRank() {
        return signalRank;
    }

    public void setSignalRank(int signalRank) {
        this.signalRank = signalRank;
    }

    public int getWifiMode() {
        return wifiMode;
    }

    public void setWifiMode(int wifiMode) {
        this.wifiMode = wifiMode;
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName;
    }

    public String getApMac() {
        return apMac;
    }

    public void setApMac(String apMac) {
        this.apMac = apMac;
    }

    public int getRadioId() {
        return radioId;
    }

    public void setRadioId(int radioId) {
        this.radioId = radioId;
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

    public String getSwitchMac() {
        return switchMac;
    }

    public void setSwitchMac(String switchMac) {
        this.switchMac = switchMac;
    }

    public String getSwitchName() {
        return switchName;
    }

    public void setSwitchName(String switchName) {
        this.switchName = switchName;
    }

    public String getGatewayMac() {
        return gatewayMac;
    }

    public void setGatewayMac(String gatewayMac) {
        this.gatewayMac = gatewayMac;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getDot1xIdentity() {
        return dot1xIdentity;
    }

    public void setDot1xIdentity(String dot1xIdentity) {
        this.dot1xIdentity = dot1xIdentity;
    }

    public int getDot1xVlan() {
        return dot1xVlan;
    }

    public void setDot1xVlan(int dot1xVlan) {
        this.dot1xVlan = dot1xVlan;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getLagId() {
        return lagId;
    }

    public void setLagId(int lagId) {
        this.lagId = lagId;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
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

    public int getUptime() {
        return uptime;
    }

    public void setUptime(int uptime) {
        this.uptime = uptime;
    }

    public int getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(int lastSeen) {
        this.lastSeen = lastSeen;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isGuest() {
        return guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public ClientIPSetting getIpSetting() {
        return ipSetting;
    }

    public void setIpSetting(ClientIPSetting ipSetting) {
        this.ipSetting = ipSetting;
    }

    public int getDownPacket() {
        return downPacket;
    }

    public void setDownPacket(int downPacket) {
        this.downPacket = downPacket;
    }

    public int getUpPacket() {
        return upPacket;
    }

    public void setUpPacket(int upPacket) {
        this.upPacket = upPacket;
    }

    public ClientRateLimitSetting getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(ClientRateLimitSetting rateLimit) {
        this.rateLimit = rateLimit;
    }

    public ClientLockToApSetting getClientLockToApSetting() {
        return clientLockToApSetting;
    }

    public void setClientLockToApSetting(ClientLockToApSetting clientLockToApSetting) {
        this.clientLockToApSetting = clientLockToApSetting;
    }

    public ClientMultifrequencyInfo getMultiLink() {
        return multiLink;
    }

    public void setMultiLink(ClientMultifrequencyInfo multiLink) {
        this.multiLink = multiLink;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getStandardPort() {
        return standardPort;
    }

    public void setStandardPort(String standardPort) {
        this.standardPort = standardPort;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public boolean isBlockDisable() {
        return blockDisable;
    }

    public void setBlockDisable(boolean blockDisable) {
        this.blockDisable = blockDisable;
    }

    public int getDhcpLeaseTime() {
        return dhcpLeaseTime;
    }

    public void setDhcpLeaseTime(int dhcpLeaseTime) {
        this.dhcpLeaseTime = dhcpLeaseTime;
    }
}
