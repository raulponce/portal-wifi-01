package ar.com.auster.wifi.portal_server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
public class Device {

    @Getter @Setter
    private Client client;
    @Getter @Setter
    private String ip;
    @Getter @Setter
    private String mac;

    @Getter @Setter
    private ClientStatus status;

    @Getter @Setter
    private OffsetDateTime startedTime;
    @Getter @Setter
    private OffsetDateTime expiredTime;

    @Getter @Setter
    private Voucher lastVoucher;

}
