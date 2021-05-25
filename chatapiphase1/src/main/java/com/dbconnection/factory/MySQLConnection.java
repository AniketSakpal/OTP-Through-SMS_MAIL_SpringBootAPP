package com.dbconnection.factory;
import java.sql.Connection;
import java.sql.DriverManager;

import com.springboot.demo.util.PropertiesLoader;
public class MySQLConnection implements DBConnection {

	private Connection connection;
	
	@Override
	public void connect() {
		try {
		PropertiesLoader loadDbParameter = PropertiesLoader.getInstance();
		Class.forName(loadDbParameter.getDBDriver());
		connection = DriverManager.getConnection(loadDbParameter.getDBConnectionUrl(), loadDbParameter.getDBUsername(), loadDbParameter.getDBPassword());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return connection;
	}

}
