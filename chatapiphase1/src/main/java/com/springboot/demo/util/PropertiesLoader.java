package com.springboot.demo.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
	protected static final String DB_DRIVER="database.connection.driver";
	protected static final String DB_USERNAME = "database.username";
	protected static final String DB_PASSWORD = "database.password";
	protected static final String DB_CONNECTION_URL = "database.connection.url";
	protected static final String DB_TYPE = "database.type";
	
	private static Properties properties;
	private static InputStream inputStream;
	
	private static final PropertiesLoader propertiesLoader = new PropertiesLoader();
	private PropertiesLoader()
	{
		initialize();
	}
	public static PropertiesLoader getInstance()
	{
		return propertiesLoader;
	}

	private void initialize()
	{
		try
		{
		properties = new Properties();
		String propertyFile = "/config/DBConfiguration.properties";
		inputStream = getClass().getResourceAsStream(propertyFile);
		properties.load(inputStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static String getDBDriver()
	{
		return properties.getProperty(DB_DRIVER);
	}
	public static String getDBUsername()
	{
		return properties.getProperty(DB_USERNAME);
	}
	public static String getDBPassword()
	{
		return properties.getProperty(DB_PASSWORD);
	}
	public static String getDBConnectionUrl()
	{
		return properties.getProperty(DB_CONNECTION_URL);
	}
	public static String getDBType()
	{
		return properties.getProperty(DB_TYPE);
	}
}
