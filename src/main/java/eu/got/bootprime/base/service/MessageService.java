package eu.got.bootprime.base.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
	
	Logger log = LoggerFactory.getLogger(MessageService.class);

	private String welcomeMessage = "Spring!!!!";

	public String getWelcomeMessage() {
		log.info("getWelcomeMessage()");
		return welcomeMessage;
	}
}
