package edu.training.jwt.service;

import java.util.List;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.training.jwt.entity.License;
import edu.training.jwt.entity.Registration;
import edu.training.jwt.entity.UserRequest;
import edu.training.jwt.entity.UserStatus;
import edu.training.jwt.repo.LicenseRepository;
import edu.training.jwt.repo.RegistrationRepository;
import edu.training.jwt.repo.UserRequestRepository;
import edu.training.jwt.repo.UserStatusRepository;

@Service
public class LicenseService 
{
	@Autowired
	 LicenseRepository licenseRepository;
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	UserRequestRepository userRequestRepository;
	
	@Autowired
	UserStatusRepository userStatusRepository;

//-----------------------------------------------------------------------------------------------------
	   //add license 
	    public void add(License license) 
	    {
	        licenseRepository.save(license);
	    }
	   
//----------------------------------------------------------------------------------------------------
	   //view license
	    public List<License> getAll() 
	    {
	        return licenseRepository.findAll();
	    }
	   
//----------------------------------------------------------------------------------------------------
	  //delete license
	    public void delete(String lid) 
	    {
	    	licenseRepository.deleteById(lid);
	    }
	    
//-----------------------------------------------------------------------------------------------------
      //add user request
	    public Boolean addUserRequest(String lid, String email) 
	    {
	        Optional<License> license = licenseRepository.findById(lid);
	        Optional<Registration> register = registrationRepository.findById(email);
	        License l = null;
	        Registration r = null;
	        UserRequest userRequest = new UserRequest();

	        if (license.isPresent()) 
	        {
	            l = license.get();
	        }
	        if (register.isPresent()) 
	        {
	            r = register.get();
	        }

	   //check if a record with the same lid and email already exists
	      UserRequest existingUserRequest = userRequestRepository.findByLidAndEmail(lid, email);
	      
	      if (l != null && r != null && existingUserRequest == null) 
	      {
	            userRequest.setEmail(r.getUemail());
	            userRequest.setFname(r.getUfname());
	            userRequest.setLid(l.getLid());
	            userRequest.setSid(r.getUuid());
	            userRequest.setSname(l.getSname());
	            userRequest.setUid(r.getUid());
	            userRequestRepository.save(userRequest);
	            return true;
	        }

	      return false;
	    }

      //display the request
		public List<UserRequest> getAllUserRequest() 
		{
			return userRequestRepository.findAll();
			
		}
		
//---------------------------------------------------------------------------------------------------  
		//add user status
		   public Boolean addStatus(String lid, String email) 
		   {
		        Optional<License> license = licenseRepository.findById(lid);
		        Optional<Registration> register = registrationRepository.findById(email);
		        License l = null;
		        Registration r = null;
		        UserStatus userStatus = new UserStatus();

		        if (license.isPresent()) 
		        {
		            l = license.get();
		        }
		        if (register.isPresent()) 
		        {
		            r = register.get();
		        }

	   //check if a record with the same lid and email already exists
		      UserStatus existingUserStatus = userStatusRepository.findByLidAndEmail(lid, email);
		      
		      if (l != null && r != null && existingUserStatus == null) 
		      {
		    	  userStatus.setEmail(r.getUemail());
		    	  userStatus.setLid(l.getLid());
		    	  userStatus.setSid(r.getUuid());
		    	  userStatus.setSname(l.getSname());
		    	  userStatus.setUid(r.getUid());
		    	  userStatus.setInsdate(LocalDate.now());
		    	  userStatus.setExpdate(LocalDate.now().plusDays(Long.parseLong(licenseRepository.findById(l.getLid()).get().getValidity())));
		          userStatusRepository.save(userStatus);
		          return true;
		      }
		      return false;
		    }

	   //display the request
			public List<UserStatus> getAllUserStatus() 
			{
				return userStatusRepository.findAll();
				
			}

			public List<UserStatus> getSingleStatus(String email) 
			{
				return userStatusRepository.findByEmail(email);
			}

			public void updateExpiry(String lid, String uid) 
			{
				System.out.println("ciiii");
				UserStatus userStatus = userStatusRepository.findByLidandUid(lid,uid);
				License license =  licenseRepository.findById(lid).get();
				userStatus.setExpdate(userStatus.getExpdate().plusDays(Long.parseLong(license.getValidity()+"")));
				userStatusRepository.save(userStatus);
			}

//-----------------------------------------------------------------------------------------------------
      //graph
			public List<Object[]> getUserStatusCounts() 
			{
		        return userStatusRepository.countUsersBySname();
		    }
			
//-----------------------------------------------------------------------------------------------------
}
