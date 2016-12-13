package eu.got.bootprime.base.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import eu.got.bootprime.base.service.MessageService;

//@Named("indexView")
//@RequestScoped
/**
 * @Scope può assumere i valori:
 * singleton 
 * prototype 
 * request 
 * session 
 * globalSession 
 * application
 * @author luca
 *
 */
@Component("indexView")
@Scope("request")
public class IndexView {
	
	 Logger log = LoggerFactory.getLogger(IndexView.class);
	
	 //@Inject
	 @Autowired
	 private MessageService messageService = null;
	 
	 
	 private String welcomeMessagePrefix = "Populated by JSF created bean and ";
	 
	 @PostConstruct
	 public void init(){
		 log.info("init()");
	 }
	 
	 
	 public String getWelcomeMessage() {
		 log.info("getWelcomeMessage()");
			return welcomeMessagePrefix+messageService.getWelcomeMessage();
		}

}
