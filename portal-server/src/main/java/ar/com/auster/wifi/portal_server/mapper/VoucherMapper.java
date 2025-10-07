package ar.com.auster.wifi.portal_server.mapper;

import ar.com.auster.wifi.portal_server.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

public class VoucherMapper implements RowMapper<Voucher> {

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_TYPE = "TYPE";
    public static final String COL_STATUS = "STATUS";
    public static final String COL_CURRENCY = "CURRENCY";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_UNIT = "UNIT";
    public static final String COL_DATA_INT = "DATA_INT";

    public static final String COL_CREATION_TIME = "CREATION_TIME";
    public static final String COL_MODIFICATION_TIME = "MODIFICATION_TIME";
    public static final String COL_DELETION_TIME = "DELETION_TIME";

    public static Voucher buildObject(Map<String, Object> mapItem) {
        if (mapItem.isEmpty() || !mapItem.containsKey(COL_ID)) {
            return null;
        }
        Voucher result = new Voucher();

        result.setId((Long)mapItem.get(COL_ID));
        result.setName((String)mapItem.get(COL_NAME));

        String typeStr =  (String)mapItem.get(COL_TYPE);
        VoucherType type = VoucherType.UNKNOWN;
        try { type = VoucherType.valueOf(typeStr); } catch (Throwable e) { e.printStackTrace(); }
        result.setType(type);

        String statusStr = (String)mapItem.get(COL_STATUS);
        VoucherStatus status = null;
        try { status = VoucherStatus.valueOf(statusStr); } catch (Throwable e) { e.printStackTrace(); }
        result.setStatus(status);

        String currencyStr = (String)mapItem.get(COL_CURRENCY);
        Currency currency = null;
        try { currency = Currency.valueOf(currencyStr); } catch (Throwable e) { e.printStackTrace(); }

        VoucherPrice price = new VoucherPrice();
        price.setCurrency(currency);
        price.setCost(mapItem.get(COL_PRICE) != null ? ((BigDecimal)mapItem.get(COL_PRICE)).doubleValue() : 0);
        result.setPrice(price);

        String unitStr = (String)mapItem.get(COL_UNIT);
        VoucherUnit unit = null;
        try { unit = VoucherUnit.valueOf(unitStr); } catch (Throwable e) { e.printStackTrace(); }
        result.setUnit(unit);

        result.setDataInt((Integer)mapItem.get(COL_DATA_INT));

        ZoneOffset timeZone = ZoneOffset.UTC;
        String timeZoneStr = "-03:00";
        try {
            timeZone = ZoneOffset.of(timeZoneStr);
        } catch (Throwable e) {}

        Timestamp createTimetamp = (Timestamp)mapItem.get(COL_CREATION_TIME);
//        result.setCreationTime(createTimetamp.toLocalDateTime().atOffset(ZoneOffset.UTC));
//        result.setCreationTime(createTimetamp.toLocalDateTime().atOffset(timeZone));
        result.setCreationTime(createTimetamp.toInstant().atOffset(timeZone));

        Timestamp modificationTimetamp = (Timestamp)mapItem.get(COL_MODIFICATION_TIME);
        if (modificationTimetamp != null) {
            result.setModificationTime(modificationTimetamp.toLocalDateTime().atOffset(ZoneOffset.UTC));
        } else {
            result.setModificationTime(null);
        }

        Timestamp deletionTimetamp = (Timestamp)mapItem.get(COL_DELETION_TIME);
        if (deletionTimetamp != null) {
            result.setDeletionTime(deletionTimetamp.toLocalDateTime().atOffset(ZoneOffset.UTC));
        } else {
            result.setDeletionTime(null);
        }
        return result;
    }

    @Override
    public Voucher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> mapItem = new HashMap<>();
        mapItem.put(COL_ID, rs.getLong(COL_ID));
        mapItem.put(COL_NAME, rs.getString(COL_NAME));
        mapItem.put(COL_TYPE, rs.getString(COL_TYPE));
        mapItem.put(COL_STATUS, rs.getString(COL_STATUS));
        mapItem.put(COL_CURRENCY, rs.getString(COL_CURRENCY));
        mapItem.put(COL_PRICE, rs.getBigDecimal(COL_PRICE));
        mapItem.put(COL_UNIT, rs.getString(COL_UNIT));
        mapItem.put(COL_DATA_INT, rs.getInt(COL_DATA_INT));
        mapItem.put(COL_CREATION_TIME, rs.getTimestamp(COL_CREATION_TIME));
        mapItem.put(COL_MODIFICATION_TIME, rs.getTimestamp(COL_MODIFICATION_TIME));
        mapItem.put(COL_DELETION_TIME, rs.getTimestamp(COL_DELETION_TIME));
        return buildObject(mapItem);
    }
}
