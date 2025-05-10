package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.api.v1.Vouchers;
import ar.com.auster.wifi.portal_server.model.Voucher;
import ar.com.auster.wifi.portal_server.model.VoucherType;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IVoucherService {

    List<VoucherType> getVoucherTypes();

    List<Voucher> getVoucher();

    boolean buyVoucher(Voucher voucher, Vouchers.OmadaQParam omadaQParam);
}
