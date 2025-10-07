package ar.com.auster.wifi.portal_server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
public class Voucher {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private VoucherType type;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private VoucherStatus status;
    @Getter @Setter
    private VoucherPrice price;
    @Getter @Setter
    private VoucherUnit unit;
    @Getter @Setter
    private Integer dataInt;

    @Getter @Setter
    private OffsetDateTime creationTime;
    @Getter @Setter
    private OffsetDateTime modificationTime;
    @Getter @Setter
    private OffsetDateTime deletionTime;
}
