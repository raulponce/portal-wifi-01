package ar.com.auster.wifi.portal_server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Voucher<T> {

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
    private VoucherData<T> data;

}
