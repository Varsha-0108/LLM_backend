package edu.training.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.training.jwt.entity.Registration;
import edu.training.jwt.repo.RegistrationRepository;

@Service
public class LoginCheckService 
{
	@Autowired
	private RegistrationRepository registrationRepository;
	
	public Boolean loginCheck(String username,String password) 
	{
		Registration registration = registrationRepository.getRegister(username);
		if( registration !=null  && registration.getPassword().equals(password)) 
		{
			return true;
		}
		return false;
		
	}
}
