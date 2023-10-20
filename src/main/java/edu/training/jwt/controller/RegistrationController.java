package edu.training.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jwt.entity.Registration;
import edu.training.jwt.service.RegistrationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController 
{
	
	@Autowired
    RegistrationService registrationService;
	
		 @PostMapping("/registeruser")
		    public String regiserUser(@RequestBody Registration registration)
		    {
			 registrationService.add(registration);
		     return "added";
		    }
		    
		 @GetMapping("/registeruser/check/{uemail}")
		 @ResponseBody
		    public List<Registration> checkExisting(@PathVariable("uemail") String uemail)
		    {
			 return registrationService.existingUser(uemail);
		    }
		 
}
