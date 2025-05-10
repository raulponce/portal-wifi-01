package ar.com.auster.wifi.portal_server.api.v1;

import ar.com.auster.wifi.portal_server.model.*;
import ar.com.auster.wifi.portal_server.services.IVoucherService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/voucher")
@CrossOrigin("*")
public class Vouchers {

    @Autowired
    private IVoucherService voucherService;

    @RequestMapping(path = "/types", method = RequestMethod.GET, consumes = {}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation
    public List<VoucherType> getVoucherTypes() {
        return voucherService.getVoucherTypes();
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET, consumes = {}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation
    public List<Voucher> getVoucher() {
        return voucherService.getVoucher();
    }

    public static class OmadaQParam {
        public String clientMac;
        public String clientIp;
        public long t;
        public String site;
        public String redirectUrl;
        public String apMac;
        public String ssidName;
        public int radioId;
    }

    public static class BuyVoucherInput {
        public Voucher voucher;
        public OmadaQParam omadaQParam;
    }

    @RequestMapping(path = "/buy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation
    public boolean buyVoucher(@RequestBody BuyVoucherInput input) {
        return voucherService.buyVoucher(input.voucher, input.omadaQParam);
    }

}
