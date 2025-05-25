package ar.com.auster.wifi.portal_server.mapper;

import ar.com.auster.wifi.portal_server.model.Client;
import ar.com.auster.wifi.portal_server.model.Device;
import ar.com.auster.wifi.portal_server.model.DeviceStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public class DeviceMapper {

    public static final String COL_CLIENT_ID = "CLIENT_ID";
    public static final String COL_MAC = "MAC";
    public static final String COL_IP = "IP";

    public static final String COL_STATUS = "STATUS";
    public static final String COL_LAST_OPERATION = "LAST_OPERATION";

    public static Device buildObject(Map<String, Object> mapItem) {
        Device result = new Device();
        Client client = new Client();
        client.setId((Long)mapItem.get(COL_CLIENT_ID));
        result.setClient(client);

        result.setMac((String)mapItem.get(COL_MAC));
        result.setIp((String)mapItem.get(COL_IP));

        String strStatus = (String)mapItem.get(COL_STATUS);
        DeviceStatus status = DeviceStatus.PENDING;
        try {
            status = DeviceStatus.valueOf(strStatus);
        } catch (Throwable t) {}
        result.setStatus(status);

        //FIXME: revisar
        Timestamp sqlLastOperation = (Timestamp)mapItem.get(COL_LAST_OPERATION);
        if (sqlLastOperation != null) {
            LocalDateTime localDate = sqlLastOperation.toLocalDateTime();
            result.setLastOperation(localDate.atOffset(ZoneOffset.UTC));
        }
        return result;
    }
}
