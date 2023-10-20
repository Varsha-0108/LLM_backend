package edu.training.jwt.controller;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jwt.entity.License;
import edu.training.jwt.entity.UserRequest;
import edu.training.jwt.entity.UserStatus;
import edu.training.jwt.repo.UserStatusRepository;
import edu.training.jwt.service.LicenseService;
import edu.training.jwt.service.MailService;
import jakarta.mail.MessagingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LicenseController 
{
	
	@Autowired
    LicenseService licenseService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	UserStatusRepository userStatusRepository;

//------------------------------------------------------------------------------------------------------
	   //add license
		 @PostMapping("/addlicense")
		    public String addProduct(@RequestBody License license)
		    {
			 	System.out.println(license);
		        licenseService.add(license);
		        return "added";
		    }
		 
//-------------------------------------------------------------------------------------------------------		 
	  //view license
		 @GetMapping("/viewlicense")
		    public List<License> getLicense()
		    {
		        return licenseService.getAll();
		    }
		 
//-------------------------------------------------------------------------------------------------------		 
	  //delete license
		    @DeleteMapping("/delete/{lid}")
				public void deleteProduct(@PathVariable("lid") String lid)
				{
		    	licenseService.delete(lid);
				}
		    
//--------------------------------------------------------------------------------------------------------
	  //user request
		    @PostMapping("/addUserRequest")
		    public Boolean addUserRequest(@RequestBody Map<String,String> data) //key value pair
		    {
		    	String lid = data.get("lid");
		    	String email = data.get("email");
		    	System.out.println(lid+" "+email);
		    	
		    	return this.licenseService.addUserRequest(lid,email);
		    }
		    
		    @GetMapping("/getAllUserRequest")
		    public List<UserRequest> getAllUserRequest1(){
		    	return this.licenseService.getAllUserRequest();
		    }
		    
// --------------------------------------------------------------------------------------------------
	  //user status
		    @PostMapping("/add")
		    public Boolean addStatus(@RequestBody Map<String,String> data) //key value pair
		    {
		    	String lid = data.get("lid");
		    	String email = data.get("email");
		    	System.out.println(lid+" "+email);
		    	
		    	return this.licenseService.addStatus(lid,email);
		    }
		    
		    @GetMapping("/all")
		    public List<UserStatus> getAllUserStatus(){
		    	return this.licenseService.getAllUserStatus();
		    }
		    
//--------------------------------------------------------------------------------------------------
	  //displaying only that user's data in homepage 
		    @PostMapping("/singleUser")
		    public List<UserStatus> getSingleStatus(@RequestBody Map<String,String> data) //key value pair
		    {
		    	String email = data.get("email");
		    	return this.licenseService.getSingleStatus(email);
		    }
		    
//--------------------------------------------------------------------------------------------------		    
      //update expiry date after renewing 
		    @PostMapping("/updateExpiry")
		    public void updateExpiry(@RequestBody Map<String,String> data) //key value pair
		    {
		    	System.out.println("biii");
		    	String lid = data.get("lid");
		    	String uid = data.get("uid");
		    	this.licenseService.updateExpiry(lid,uid);
		    }
		   	   
//--------------------------------------------------------------------------------------------------
	  //graph
		    @GetMapping("/graph-data")
		    public ResponseEntity<List<Object[]>> getUserStatusGraphData() {
		        List<Object[]> graphData = licenseService.getUserStatusCounts();
		        return ResponseEntity.ok(graphData);
		    }
		    
//--------------------------------------------------------------------------------------------------
     //mail
		    @Scheduled(cron = "0 00 16 * * ?")
		    public void sendLicenseExpiryEmails() throws MessagingException 
		    {
		        System.out.println("Mail at 04:00 PM.");
		        LocalDate currentDate = LocalDate.now();
		        LocalDate expiryDate2Days = currentDate.plusDays(2); 
		        LocalDate expiryDate1Day = currentDate.plusDays(1); 

		        List<UserStatus> usersWithExpiringLicenses2Days = userStatusRepository.findByExpdateBetween(currentDate, expiryDate2Days);
		        List<UserStatus> usersWithExpiringLicenses1Day = userStatusRepository.findByExpdateBetween(currentDate, expiryDate1Day);

		        for (UserStatus user : usersWithExpiringLicenses2Days) 
		        {
		            sendExpiryReminderEmail(user, 2);
		        }

		        for (UserStatus user : usersWithExpiringLicenses1Day) 
		        {
		            sendExpiryReminderEmail(user, 1);
		        }
		    }

		    private void sendExpiryReminderEmail(UserStatus user, int daysBeforeExpiry) throws MessagingException 
		    {
		        String emailBody = "<html><body>"
		                + "<h2 style='color: red;'>License Expiry Notification</h2>"
		                + "<p style='font-size: 16px;'>Dear " + user.getUid() + ",</p>"
		                + "<p style='font-size: 16px;'>This is to inform you that your license for " + user.getSname() + " is expiring in " + daysBeforeExpiry + " day(s). Please take action to renew it to avoid any service interruptions.</p>"
		                + "<p style='font-size: 16px;'>If you have already renewed, kindly ignore.</p>"
		                + "<p style='font-size: 16px;'>For queries, please contact our support team.</p>"
		                + "</body></html>";

		        mailService.sendMail(user.getEmail(), "License Expiry Notification", emailBody);
		    }

//--------------------------------------------------------------------------------------------------
}
