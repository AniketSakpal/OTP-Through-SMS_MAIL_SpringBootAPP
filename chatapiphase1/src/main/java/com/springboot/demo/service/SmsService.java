package com.springboot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.springboot.demo.config.SmsConfigurationProvider;
import com.springboot.demo.dto.NumberDTO;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsService 
{
	@Autowired
	private SmsConfigurationProvider smsConfiguration;
	
	
	public boolean sendMessage(NumberDTO numberDto)
	{
		boolean successFlag =false;
		try {
			
		Twilio.init(smsConfiguration.getAccountSid(), smsConfiguration.getAuthToken());
		Message message = Message.creator(new PhoneNumber(numberDto.getRecipeintNumber()),new PhoneNumber(smsConfiguration.getFromnumber()),numberDto.getBody()).create();
		successFlag = true;
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return successFlag;
	}
}
