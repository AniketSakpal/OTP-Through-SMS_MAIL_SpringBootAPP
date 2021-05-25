package com.dbconnection.factory;

import com.springboot.demo.util.PropertiesLoader;

public class DBFactory {
	
	protected String databaseType = PropertiesLoader.getInstance().getDBType();
	
	public DBConnection createConnection()
	{
		
		if(databaseType.equals("MYSQL"))
		{
			return new MySQLConnection();
		}
		else
		{
			return null;
		}
			
	}
}
