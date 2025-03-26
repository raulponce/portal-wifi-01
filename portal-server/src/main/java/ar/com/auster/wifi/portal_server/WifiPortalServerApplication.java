package ar.com.auster.wifi.portal_server;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class WifiPortalServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WifiPortalServerApplication.class, args);
	}

}
