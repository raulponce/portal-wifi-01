package ar.com.auster.wifi.portal_server.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/vouchers")
public class Vouchers {

    @RequestMapping(path = "/voucher/types", method = RequestMethod.GET, consumes = {}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation
    public List<String> getVoucherTypes() {
        return new ArrayList<>();
    }
}
