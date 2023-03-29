package demo.microservice1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import demo.microservice1.config.AppConfig;

@RestController
public class Microservice1Controller {

	private final static Logger LOGGER = LoggerFactory.getLogger(Microservice1Controller.class);
	
	@Autowired
    private AppConfig appConfig;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/")
	String displayDefaultMessage() {
		String message = "Hello World, from Microservice1!";
		LOGGER.info("{}", message);
		return message;
	}

	@RequestMapping("/callmicroservice2")
	String displayDefaultMessageWithParameter() {
		LOGGER.info("Host name to access is :{},and port:{}", appConfig.getService2Host(),appConfig.getService2Port());
		final String microservice2Url = "http://"+appConfig.getService2Host()+":"+appConfig.getService2Port()+"/testcall";
		final String response = (String) restTemplate.exchange(microservice2Url, HttpMethod.GET, null, String.class)
				.getBody();

		LOGGER.info("The response received from microservice2= " + response);
		// Returning the response to the user.
		return response;
	}
}
