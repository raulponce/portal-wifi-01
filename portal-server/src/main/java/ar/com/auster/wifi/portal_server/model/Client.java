package ar.com.auster.wifi.portal_server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
public class Client {

    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private ClientStatus status;

    @Getter @Setter
    private OffsetDateTime creationTime;
    @Getter @Setter
    private OffsetDateTime lastOperation;

    @Getter @Setter
    private Voucher lastVoucher;
}
