package com.springboot.demo.controller;

import com.springboot.demo.service.OtpService;
import com.springboot.demo.service.ValidateUserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Description(value = "Resource for generating and validating OTP requests.")
@RestController
@RequestMapping("/api/otp")
public class OTPResourceController {
	
	 
	  private OtpService otpService;

	  public OTPResourceController(OtpService otpService) 
	  { 
		this.otpService =otpService; 
	  }
			 
	@PostMapping(value = "generate", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Object> generateOTP(@RequestParam("mailOrPhone") String userData) {
		
		String username = userData;

		
		  Map<String, String> response = new HashMap<>(2);
		  Boolean isGenerated=false;
		   //check authentication 
		  if (username == null) { return new
		  ResponseEntity<>(HttpStatus.UNAUTHORIZED); }
		  
		  if((ValidateUserData.validateUserMail(username)))
		  {
		  // generate OTP. 
			  isGenerated = otpService.generateOtp(username);
			  response.put("status", "success"); 
			  response.put("message","OTP successfully generated. Please check your e-mail!");
		  }
		  else if(ValidateUserData.validateUserPhone(username)) // only tested for indian phone numbers i.e. +91-follows with 10 digit number.
		  {
			  // Need to revised this validation logic.
			  System.out.println("validated mobile number");
			  isGenerated = otpService.generateSmsOtp(username);
			  response.put("status", "success"); 
			  response.put("message","OTP successfully generated. Please check your Phone!");
		  }
		  
		  if(!isGenerated) 
		  { 
			  response.put("status", "error"); response.put("message","OTP can not be generated.");
		  
		  return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
		  }
		  
		  // success message 
		  
		 

		  return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "validate", produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> validateOTP(@RequestBody Map<String, Object> otp)
    {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String)otp.get("mailOrPhone");

        Map<String, String> response = new HashMap<>(2);

        // check authentication
        if (username == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // validate provided OTP.
        Boolean isValid = otpService.validateOTP(username, Integer.parseInt(otp.get("otp").toString()));
        if (!isValid)
        {
            response.put("status", "error");
            response.put("message", "OTP is not valid!");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // success message
        response.put("status", "success");
        response.put("message", "Entered OTP is valid!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
