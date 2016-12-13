package eu.got.bootprime.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	    
	@RequestMapping("/")
    public String getIndexPage() {
        return "index.xhtml";
    }
	
	@RequestMapping("/home")
    public String getHomePage() {
        return "home.xhtml";
    }
	
	
	
	 @RequestMapping("/testRedirect")
	   public String redirect() {
	       return "redirect:/home";
	   }
	
//	@RequestMapping("/error")
//    public String getErrorPage() {
//        return "error.xhtml";
//    }

}
