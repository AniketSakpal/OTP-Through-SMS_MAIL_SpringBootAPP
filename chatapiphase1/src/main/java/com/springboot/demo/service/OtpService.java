package com.springboot.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.demo.dto.EmailDTO;
import com.springboot.demo.dto.NumberDTO;

@Service
public class OtpService {

	private final Logger LOGGER = LoggerFactory.getLogger(OtpService.class);

    private OtpGenerator otpGenerator;
    private EmailService emailService;
    private SmsService smsService;

    /**
     * Constructor dependency injector
     * @param otpGenerator - otpGenerator dependency
     * @param emailService - email service dependency
     * @param smsService - sms service dependency
     */
    public OtpService(OtpGenerator otpGenerator, EmailService emailService,SmsService smsService)
    {
        this.otpGenerator = otpGenerator;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    /**
     * Method for generate OTP number
     *
     * @param key - provided key (username in this case)
     * @return boolean value (true|false)
     */
    public Boolean generateOtp(String key)
    {
        // generate otp
        Integer otpValue = otpGenerator.generateOTP(key);
        if (otpValue == -1)
        {
            LOGGER.error("OTP generator is not working...");
            return  false;
        }

        LOGGER.info("Generated OTP: {}", otpValue);

        // fetch user e-mail from database
        String userEmail = key;
        List<String> recipients = new ArrayList<>();
        recipients.add(userEmail);

        // generate emailDTO object
        EmailDTO emailDTO = new com.springboot.demo.dto.EmailDTO();
        emailDTO.setSubject("Verify Your OTP");
        emailDTO.setBody("OTP Password: " + otpValue);
        emailDTO.setRecipients(recipients);

        // send generated e-mail
        return emailService.sendSimpleMessage(emailDTO);
    }
    
    public boolean generateSmsOtp(String key)
    {
    	 Integer otpValue = otpGenerator.generateOTP(key);
         if (otpValue == -1)
         {
             LOGGER.error("OTP generator is not working...");
             return  false;
         }
         
         String userNumber = key;
         NumberDTO numberDTO = new NumberDTO();
         numberDTO.setBody("Your One Time Password is "+otpValue);
         numberDTO.setRecipeintNumber(userNumber);
         
         return smsService.sendMessage(numberDTO);
    }

    /**
     * Method for validating provided OTP
     *
     * @param key - provided key
     * @param otpNumber - provided OTP number
     * @return boolean value (true|false)
     */
    public Boolean validateOTP(String key, Integer otpNumber)
    {
        // get OTP from cache
        Integer cacheOTP = otpGenerator.getOPTByKey(key);
        if (cacheOTP.equals(otpNumber))
        {
            otpGenerator.clearOTPFromCache(key);
            return true;
        }
        return false;
    }
	
}
