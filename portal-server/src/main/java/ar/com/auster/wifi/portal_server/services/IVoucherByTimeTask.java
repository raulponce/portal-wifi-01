package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.api.v1.Vouchers;
import ar.com.auster.wifi.portal_server.model.Voucher;
import ar.com.auster.wifi.portal_server.model.VoucherTimeUnit;

public interface IVoucherByTimeTask {

    void setSessionService(ISessionService service);

    void add(Vouchers.OmadaQParam qparam, Voucher<VoucherTimeUnit> voucher);

    void startTask();

    void stopTask();

    boolean isRunning();
}
