package ar.com.auster.wifi.portal_server.mapper;

import ar.com.auster.wifi.portal_server.model.Client;
import ar.com.auster.wifi.portal_server.model.ClientVoucher;

import java.util.Map;

public class ClientMapper {

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_STATUS = "STATUS";
    public static final String COL_CREATION_TIME = "CREATION_TIME";
    public static final String COL_LAST_OPERATION_TIME = "LAST_OPERATION_TIME";
    public static final String COL_LAST_VOUCHER_ID = "LAST_VOUCHER_ID";

    public static Client buildObject(Map<String, Object> mapItem) {
        Client result = new Client();
        result.setId((Long)mapItem.get(COL_ID));
        result.setName((String)mapItem.get(COL_NAME));
        result.setEmail((String)mapItem.get(COL_EMAIL));
        //TODO
        return result;
    }
}
