package com.myrestapp.db;
import java.sql.*;

public class DatabaseConnection {
	private static DatabaseConnection instance = null;
	private static Connection connection;
	
	public DatabaseConnection()
	{
		
	}
	
	public static DatabaseConnection getInstance()
	{
	    if(instance==null)
	    {
	    	instance=new DatabaseConnection();
	    }      
	    return instance;
	}
	
	public void start()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		connection = null;
		try 
		{
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/corso", "root", "");
        }
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}
	
	public void closeConnection() 
	{
		try 
		{
			connection.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
}
