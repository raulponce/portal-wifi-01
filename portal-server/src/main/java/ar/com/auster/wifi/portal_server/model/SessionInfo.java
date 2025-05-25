package ar.com.auster.wifi.portal_server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class SessionInfo {
    @Getter @Setter
    public Device device;
    @Getter @Setter
    public List<ClientVoucher> listRecentVouchers = new ArrayList<>();
    @Getter @Setter
    public ClientVoucher activeVoucher;
}
