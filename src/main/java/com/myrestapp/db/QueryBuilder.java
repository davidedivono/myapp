package com.myrestapp.db;
import java.sql.*;
import com.myrestapp.obj.Person;

public class QueryBuilder {

	public QueryBuilder()
	{
		
	}
	
	public String selectAll()
	{
		String query = "SELECT * FROM person WHERE uniqueKey = ?";
		return query;
	}
	
	public String insert()
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String query = "INSERT INTO person (uniqueKey, name, surname, birth, tinsert) VALUES (?, ?, ?, ?, '" + timestamp + "')";
		return query;
	}
	
	public String update()
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String query = "UPDATE person SET uniqueKey = ?, name = ?, surname = ?, birth = ?, tinsert = '" + timestamp + "' WHERE uniqueKey = ?";
		return query;
	}
	
	public String delete()
	{
		String query = "DELETE FROM person WHERE uniqueKey = ?";
		return query;
	}
	
}
