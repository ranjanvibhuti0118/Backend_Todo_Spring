package com.project.rest.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
//Controller : handles a http request

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class BasicAuthenticationController {
	

	
	@GetMapping(path="/basicauth")
	public AuthenticationBean helloworldBean() {
		
		return new AuthenticationBean("You are authenticated");
	}
	

}
