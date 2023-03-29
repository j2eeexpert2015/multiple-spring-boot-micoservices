package demo.microservice1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {


    @Value("${demo-microservice2-host:localhost}")
    private String service2Host;

	public String getService2Host() {
		return service2Host;
	}
	
	@Value("${demo-microservice2-port:8082}")
    private String service2Port;

	public String getService2Port() {
		return service2Port;
	}
}

