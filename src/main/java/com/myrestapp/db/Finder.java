package com.myrestapp.db;
import java.util.*;

import com.myrestapp.obj.FilterPerson;
import com.myrestapp.obj.Person;

import java.sql.*;

public class Finder {
	
	public Finder()
	{
		
	}
	
	public List<Person> find(FilterPerson filter)
	{
		List<Person> list = new ArrayList<Person>();
		String dateFrom = filter.getDate1() != null && !filter.getDate1().isEmpty() ? filter.getDate1() : "1900-01-01";
		String dateTo = filter.getDate2() != null && !filter.getDate2().isEmpty() ? filter.getDate2() : "2100-12-31";
		StringBuilder query = new StringBuilder("SELECT uniqueKey, name, surname, birth FROM person WHERE (birth BETWEEN ");
		query.append("'" + dateFrom + "' AND '" + dateTo + "')");
		if(filter.getName() != null && !filter.getName().isEmpty())
		{
			query.append(" AND (name = '" + filter.getName() + "')");
		}
		if(filter.getSurname() != null && !filter.getSurname().isEmpty())
		{
			query.append(" AND (surname = '" + filter.getSurname() + "')");
		}
		query.append(" LIMIT " + filter.getRecordNum());
		DatabaseConnection dbc = DatabaseConnection.getInstance();
		dbc.start();
		try 
		{
			Statement stmt = dbc.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query.toString());
			while (rs.next()) 
			{
				Person p = new Person(rs.getString("uniqueKey"), rs.getString("name"), rs.getString("surname"), rs.getString("birth"));
				list.add(p);
			}
			stmt.close();
		}
		catch (NullPointerException ex)
        {
            ex.printStackTrace();
        } 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbc.closeConnection();
		}
		return list;
	}

}
