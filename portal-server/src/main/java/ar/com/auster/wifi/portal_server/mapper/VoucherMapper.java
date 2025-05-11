package ar.com.auster.wifi.portal_server.mapper;

import ar.com.auster.wifi.portal_server.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class VoucherMapper implements RowMapper<Voucher<?>> {

    public static String COL_ID = "ID";
    public static String COL_NAME = "NAME";
    public static String COL_TYPE = "TYPE";
    public static String COL_STATUS = "STATUS";
    public static String COL_CURRENCY = "CURRENCY";
    public static String COL_PRICE = "PRICE";
    public static String COL_DATA_ENUM = "DATA_ENUM";
    public static String COL_DATA_UNIT = "DATA_UNIT";

    public static Voucher<?> buildObject(Map<String, Object> mapItem) {
        String typeStr =  (String)mapItem.get(COL_TYPE);
        VoucherType type = VoucherType.UNKNOWN;
        try {
            type = VoucherType.valueOf(typeStr);
        } catch (Throwable e) {
            type = VoucherType.UNKNOWN;
        }

        Voucher<?> result = null;
        if (type == VoucherType.BY_TIME) {
            result = new Voucher<VoucherTimeUnit>();
        } else if (type == VoucherType.BY_DATA) {
            result = new Voucher<VoucherData>();
        }

        if (result != null) {
            result.setId((Long)mapItem.get(COL_ID));
            result.setName((String)mapItem.get(COL_NAME));
            result.setType(type);

            String statusStr = (String)mapItem.get(COL_STATUS);
            VoucherStatus status = null;
            try { status = VoucherStatus.valueOf(statusStr); } catch (Throwable e) { }
            result.setStatus(status);

            String currencyStr = (String)mapItem.get(COL_CURRENCY);
            Currency currency = null;
            try { currency = Currency.valueOf(currencyStr); } catch (Throwable e) { }

            VoucherPrice price = new VoucherPrice();
            price.setCurrency(currency);
            price.setCost(mapItem.get(COL_PRICE) != null ? ((BigDecimal)mapItem.get(COL_PRICE)).doubleValue() : 0);

            result.setPrice(price);

            if (type == VoucherType.BY_TIME) {
                VoucherData<VoucherTimeUnit> data = new VoucherData<>();
                String unitStr = (String)mapItem.get(COL_DATA_ENUM);
                VoucherTimeUnit unit = null;
                try {
                    unit = VoucherTimeUnit.valueOf(unitStr);
                } catch (Throwable e) {
                }
                data.setUnit(unit);

                ((Voucher<VoucherTimeUnit>) result).setData(data);
            } else if (type == VoucherType.BY_DATA) {
                VoucherData<VoucherDataUnit> data = new VoucherData<>();
                String unitStr = (String)mapItem.get(COL_DATA_ENUM);
                VoucherDataUnit unit = null;
                try {
                    unit = VoucherDataUnit.valueOf(unitStr);
                } catch (Throwable e) {
                }
                data.setUnit(unit);

                ((Voucher<VoucherDataUnit>) result).setData(data);
            } else {
                result.setData(null);
            }

            if (result.getData() != null) {
                result.getData().setValue(mapItem.get(COL_DATA_UNIT) != null ? ((Integer)mapItem.get(COL_DATA_UNIT)).intValue() : 0);
            }
        }
        return result;
    }

    @Override
    public Voucher<?> mapRow(ResultSet rs, int rowNum) throws SQLException {
        String typeStr =  rs.getString(COL_TYPE);
        VoucherType type = VoucherType.UNKNOWN;
        try {
            type = VoucherType.valueOf(typeStr);
        } catch (Throwable e) {
            type = VoucherType.UNKNOWN;
        }

        Voucher<?> result = null;
        if (type == VoucherType.BY_TIME) {
            result = new Voucher<VoucherTimeUnit>();
        } else if (type == VoucherType.BY_DATA) {
            result = new Voucher<VoucherData>();
        }
        if (result != null) {
            result.setId(rs.getInt(COL_ID));
            result.setName(rs.getString(COL_NAME));
            result.setType(type);

            String statusStr = rs.getString(COL_STATUS);
            VoucherStatus status = null;
            try { status = VoucherStatus.valueOf(statusStr); } catch (Throwable e) { }
            result.setStatus(status);

            String currencyStr = rs.getString(COL_CURRENCY);
            Currency currency = null;
            try { currency = Currency.valueOf(currencyStr); } catch (Throwable e) { }

            VoucherPrice price = new VoucherPrice();
            price.setCurrency(currency);
            price.setCost(rs.getDouble(COL_PRICE));

            result.setPrice(price);

            if (type == VoucherType.BY_TIME) {
                VoucherData<VoucherTimeUnit> data = new VoucherData<>();
                String unitStr = rs.getString(COL_DATA_ENUM);
                VoucherTimeUnit unit = null;
                try {
                    unit = VoucherTimeUnit.valueOf(unitStr);
                } catch (Throwable e) {
                }
                data.setUnit(unit);
                data.setValue(rs.getInt(COL_DATA_UNIT));

                ((Voucher<VoucherTimeUnit>) result).setData(data);
            } else if (type == VoucherType.BY_DATA) {
                VoucherData<VoucherDataUnit> data = new VoucherData<>();
                String unitStr = rs.getString(COL_DATA_ENUM);
                VoucherDataUnit unit = null;
                try {
                    unit = VoucherDataUnit.valueOf(unitStr);
                } catch (Throwable e) {
                }
                data.setUnit(unit);
                data.setValue(rs.getInt(COL_DATA_UNIT));

                ((Voucher<VoucherDataUnit>) result).setData(data);
            }
        }
        return result;
    }
}
