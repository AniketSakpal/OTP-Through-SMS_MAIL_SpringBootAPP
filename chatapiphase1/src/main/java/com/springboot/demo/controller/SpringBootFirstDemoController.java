package com.springboot.demo.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.demo.service.ValidateUserData;

@Controller
public class SpringBootFirstDemoController {

	@PostMapping("/login")
	public String validateUser(@RequestParam("mailOrPhone") String userData,Model model) throws SQLException
	{
		boolean isMailCorrect = ValidateUserData.validateUserMail(userData);
		boolean isPhoneCorrect = ValidateUserData.validateUserPhone(userData);
		
		if(isMailCorrect)
		{
			
			return "";
		}
		else if(isPhoneCorrect)
		{
			
			return "";
		}
		else
		{
			
			model.addAttribute("errorMessage", userData);
			return "error";
		}
		
	}
	
	@GetMapping({"/","/index"})
	public String getWelcome()
	{
		return "index";
	}
}
