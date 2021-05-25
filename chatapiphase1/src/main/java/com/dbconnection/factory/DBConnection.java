package com.dbconnection.factory;

public interface DBConnection {

	public void connect();
	
	public java.sql.Connection getConnection();
}
