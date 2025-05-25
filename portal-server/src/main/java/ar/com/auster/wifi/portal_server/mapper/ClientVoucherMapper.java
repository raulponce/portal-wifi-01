package ar.com.auster.wifi.portal_server.mapper;

import ar.com.auster.wifi.portal_server.model.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public class ClientVoucherMapper {

    public static final String COL_ID = "ID";
    public static final String COL_CLIENT_ID = "CLIENT_ID";
    public static final String COL_DEVICE_MAC = "DEVICE_MAC";
    public static final String COL_DEVICE_IP = "DEVICE_IP";
    public static final String COL_VOUCHER_ID = "VOUCHER_ID";
    public static final String COL_VOUCHER_TYPE = "VOUCHER_TYPE";
    public static final String COL_VOUCHER_NAME = "VOUCHER_NAME";
    public static final String COL_CURRENCY = "CURRENCY";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_STARTE_TIME = "STARTED_TIME";
    public static final String COL_EXPIRED_TIME = "EXPIRED_TIME";
    public static final String COL_DURATION_TIME = "DURATION_TIME";
    public static final String COL_PAUSED_TIME = "PAUSED_TIME";
    public static final String COL_STATUS = "STATUS";

    public static ClientVoucher buildObject(Map<String, Object> mapItem) {
        ClientVoucher result = new ClientVoucher();
        result.setId((Long)mapItem.get(COL_ID));
        Client cli = new Client();
        cli.setId((Long)mapItem.get(COL_CLIENT_ID));
        result.setClient(cli);

        result.setMac((String)mapItem.get(COL_DEVICE_MAC));
        result.setDeviceIp((String)mapItem.get(COL_DEVICE_IP));

        Long voucherId = (Long)mapItem.getOrDefault(COL_VOUCHER_ID, null);
        if (voucherId != null) {
            String voucherTypeStr = (String)mapItem.getOrDefault(COL_VOUCHER_TYPE, null);
            VoucherType voucherType = VoucherType.UNKNOWN;
            try {
                voucherType = VoucherType.valueOf(voucherTypeStr);
            } catch (Throwable e) {
                voucherType = VoucherType.UNKNOWN;
            }


            Voucher voucher = new Voucher();
            voucher.setId(voucherId);
            voucher.setType(voucherType);
            voucher.setName((String)mapItem.getOrDefault(COL_VOUCHER_NAME, null));
            result.setVoucher(voucher);
        } else {
            result.setVoucher(null);
        }

        String currencyStr = (String)mapItem.getOrDefault(COL_CURRENCY, null);
        Currency currency = null;
        if (currencyStr != null) {
            try {
                currency = Currency.valueOf(currencyStr);
                VoucherPrice price = new VoucherPrice();
                price.setCurrency(currency);
                BigDecimal priceCost = (BigDecimal)mapItem.getOrDefault(COL_PRICE, null);
                if (priceCost != null) {
                    price.setCost(priceCost.doubleValue());
                } else {
                    price.setCost(0.0);
                }
                result.setPrice(price);
            } catch (Throwable e) {}
        }

        String statusStr = (String)mapItem.getOrDefault(COL_STATUS, null);
        ClientVoucherStatus status = ClientVoucherStatus.READY_TO_ASSIGN;
        try {
            status = ClientVoucherStatus.valueOf(statusStr);
        } catch (Throwable t) {}
        result.setStatus(status);

        Date startedTimeJdbcDate = (Date)mapItem.getOrDefault(COL_STARTE_TIME, null);
        if (startedTimeJdbcDate != null) {
            OffsetDateTime startedTime = startedTimeJdbcDate.toInstant().atOffset(ZoneOffset.UTC);
            result.setStartedTime(startedTime);
        }

        Date expiredTimeJdbcDate = (Date)mapItem.getOrDefault(COL_EXPIRED_TIME, null);
        if (expiredTimeJdbcDate != null) {
            OffsetDateTime expiredTime = expiredTimeJdbcDate.toInstant().atOffset(ZoneOffset.UTC);
            result.setExpiredTime(expiredTime);
        }

        Date durationTimeJdbcDate = (Date)mapItem.getOrDefault(COL_DURATION_TIME, null);
        if (durationTimeJdbcDate != null) {
            OffsetDateTime durationTime = durationTimeJdbcDate.toInstant().atOffset(ZoneOffset.UTC);
            result.setDurationTime(durationTime);
        }

        Date pausedTimeJdbcDate = (Date)mapItem.getOrDefault(COL_PAUSED_TIME, null);
        if (pausedTimeJdbcDate != null) {
            OffsetDateTime pausedTime = pausedTimeJdbcDate.toInstant().atOffset(ZoneOffset.UTC);
            result.setPausedTime(pausedTime);
        }
        return result;
    }
}
