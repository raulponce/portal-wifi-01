package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.model.*;

import java.util.List;

public interface IPortalService {

    SessionInfo newDeviceAccess(PortalInfo portalinfo, Device device);

    List<Voucher> getVoucherAvailables(PortalInfo portalinfo);

    BuyVoucherStatus buyVoucher(PortalInfo portalinfo, Client client, Device device, Voucher voucher);

    BuyVoucherStatus checkVoucherBuyed(PortalInfo portalinfo, Client client, Device device, Voucher voucher);


}
