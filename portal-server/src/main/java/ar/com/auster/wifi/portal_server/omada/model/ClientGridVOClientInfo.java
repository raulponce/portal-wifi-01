package ar.com.auster.wifi.portal_server.omada.model;

public class ClientGridVOClientInfo extends PageOutput<ClientInfo> {

    protected ClientStatVO clientStat;

    public ClientStatVO getClientStat() {
        return clientStat;
    }

    public void setClientStat(ClientStatVO clientStat) {
        this.clientStat = clientStat;
    }
}
