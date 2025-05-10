package ar.com.auster.wifi.portal_server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
public class ClientVoucher {

    @Getter @Setter
    private int id;
    @Getter @Setter
    private Client client;
    @Getter @Setter
    private String mac;

    @Getter @Setter
    private Voucher voucher; //voucherId
    @Getter @Setter
    private VoucherType voucherTyope;
    @Getter @Setter
    private String voucherName;

    @Getter @Setter
    private String deviceIp;

    @Getter @Setter
    private VoucherPrice price;

    @Getter @Setter
    private OffsetDateTime startedTime;
    @Getter @Setter
    private OffsetDateTime expiredTime;

    @Getter @Setter
    private OffsetDateTime durationTime;
    @Getter @Setter
    private OffsetDateTime pausedTime;

    private ClientVoucher status;

}
