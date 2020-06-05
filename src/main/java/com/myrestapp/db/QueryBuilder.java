package com.myrestapp.db;
import java.sql.*;
import com.myrestapp.obj.Person;

public class QueryBuilder {

	public QueryBuilder()
	{
		
	}
	
	public String selectAll(String key)
	{
		String query = "SELECT * FROM person WHERE uniqueKey = '" + key + "'";
		return query;
	}
	
	public String insert(Person person)
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String query = "INSERT INTO person (uniqueKey, name, surname, birth, tinsert) VALUES ('" + person.getKey() + "', '" + person.getName() + "', '"+ person.getSurname() + "', '" + person.getDate() + "', '" + timestamp + "')";
		return query;
	}
	
	public String update(String key, Person person)
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String query = "UPDATE person SET uniqueKey = '" + person.getKey() + "', name = '" + person.getName() + "', surname = '" + person.getSurname() + "', birth = '" + person.getDate() + "', tinsert = '" + timestamp + "' WHERE uniqueKey = '" + key + "'";
		return query;
	}
	
	public String delete(String key)
	{
		String query = "DELETE FROM person WHERE uniqueKey = '" + key + "'";
		return query;
	}
	
}
