package edu.training.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jwt.entity.LoginCheck;
import edu.training.jwt.service.LoginCheckService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginCheckController 
{
	
	@Autowired
	private LoginCheckService loginCheckService;
	
	@PostMapping("/loginCheck")
	public Boolean loginCheck(@RequestBody LoginCheck body ) 
	{
		System.out.println("Controller "+body.getUusername()+body.getPassword());
		return this.loginCheckService.loginCheck(body.getUusername(),body.getPassword());
	}
}
