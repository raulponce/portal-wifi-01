package ar.com.auster.wifi.portal_server.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/v1/authorization")
public class Authorization {

    @RequestMapping(path = "/access/check", method = RequestMethod.POST, consumes = {}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation
    public ResponseEntity<Void> checkAccess() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path =  "/access/buy", method = RequestMethod.POST, consumes = {}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> buyAccess() {
        return ResponseEntity.ok().build();
    }
}
