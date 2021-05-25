package com.dbconnection.helper;

import java.sql.Connection;

import com.dbconnection.factory.DBConnection;
import com.dbconnection.factory.DBFactory;

public class DBConnectionHelper {
	
	private static Connection con;
	public DBConnectionHelper()
	{
		DBFactory dbFactory = new DBFactory();
		DBConnection connection = dbFactory.createConnection();
		connection.connect();
		con = connection.getConnection();
	}
	
	public static Connection getConnection()
	{
		return con;
	}
}
