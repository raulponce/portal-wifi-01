package ar.com.auster.wifi.portal_server.api.v1;

import ar.com.auster.wifi.portal_server.model.*;
import ar.com.auster.wifi.portal_server.services.IPortalService;
import ar.com.auster.wifi.portal_server.services.IVoucherService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portal")
@CrossOrigin("*")
public class Portal {

    @Autowired
    private IPortalService portalService;

    public class DeviceAccessInput {
        @NotNull
        public PortalInfo portalinfo;
        @NotNull
        public Device device;
    }

    @RequestMapping(path = "/access", method = RequestMethod.GET, consumes = {}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation
    public SessionInfo deviceAccess(@RequestBody @NotNull @Valid DeviceAccessInput input) {
        return portalService.newDeviceAccess(input.portalinfo, input.device);
    }

}
