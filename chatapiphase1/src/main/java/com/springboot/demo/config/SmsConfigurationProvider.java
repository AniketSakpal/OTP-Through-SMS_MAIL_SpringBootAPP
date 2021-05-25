package com.springboot.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sms")
public class SmsConfigurationProvider 
{
	private String accountSid;
	private String authToken;
	private String fromnumber;
	
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getFromnumber() {
		return fromnumber;
	}
	public void setFromnumber(String fromnumber) {
		this.fromnumber = fromnumber;
	}
	
	
}
