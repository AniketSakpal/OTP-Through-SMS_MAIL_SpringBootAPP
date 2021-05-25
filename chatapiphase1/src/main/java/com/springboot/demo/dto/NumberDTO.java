package com.springboot.demo.dto;


import org.springframework.context.annotation.Description;

@Description(value = "Number DTO class.")

public class NumberDTO 
{
	private String recipeintNumber;
	private String body;
	
	public String getRecipeintNumber() {
		return recipeintNumber;
	}
	public void setRecipeintNumber(String recipeintNumber) {
		this.recipeintNumber = recipeintNumber;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
