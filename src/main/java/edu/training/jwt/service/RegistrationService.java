package edu.training.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.training.jwt.entity.Registration;
import edu.training.jwt.repo.RegistrationRepository;

@Service
public class RegistrationService 
{
	@Autowired
	 RegistrationRepository registartionRepository;
	 
	    public void add(Registration registration) 
	    {
	    	registartionRepository.save(registration);
	    }

	    public List<Registration> existingUser(String uemail)
	    {
	    	List<Registration> existingList = registartionRepository.findByUemail(uemail);
			return existingList;
	    	
	    }    
}
