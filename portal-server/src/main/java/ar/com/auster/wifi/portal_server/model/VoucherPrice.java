package ar.com.auster.wifi.portal_server.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class VoucherPrice {

    @Getter @Setter
    private Currency currency;
    @Getter @Setter
    private double cost;

}
