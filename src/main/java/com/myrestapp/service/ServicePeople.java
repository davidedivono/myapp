package com.myrestapp.service;
import java.util.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.container.*;
import com.myrestapp.db.*;
import com.myrestapp.obj.*;
import com.mysql.jdbc.*;

@Path("/people")
public class ServicePeople {
	List<AsyncResponse> listeners;
	
	public ServicePeople(List<AsyncResponse> listeners)
	{
		this.listeners = listeners;
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String getByFilter() 
	{
		return "Hello World!";
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getByFilterWithQueryParam(@QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("date1") String date1, @QueryParam("date2") String date2, @QueryParam("recordNum") String recordNum) 
	{
		FilterPerson filter = new FilterPerson(name, surname, date1, date2, recordNum != null ? Integer.valueOf(recordNum): 5);
		Finder finder = new Finder();
		List<Person> list = new ArrayList<Person>();
    	list = finder.find(filter);
		return list;
	}
	
	@GET
	@Path("/person/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("key") String key) 
	{
		QueryBuilder query = new QueryBuilder();
		Person person = new Person();
		DatabaseConnection dbc = DatabaseConnection.getInstance();
		dbc.start();
		try 
		{
			PreparedStatement stmt = dbc.getConnection().prepareStatement(query.selectAll());
			stmt.setString(1, key);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			person.setKey((rs.getString("uniqueKey")));
			person.setName((rs.getString("name")));
			person.setSurname((rs.getString("surname")));
			person.setDate((rs.getString("birth")));
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
		return person;
	}
	
	@POST
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getByFilter(FilterPerson filterInput)
	{
		if(filterInput.getRecordNum() == 0)
		{
			filterInput.setRecordNum(5);
		}
		Finder finder = new Finder();
		List<Person> list = new ArrayList<Person>();
    	list = finder.find(filterInput);
		return list;
	}

	@POST
	@Path("/person")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Person person) throws URISyntaxException 
	{
		QueryBuilder query = new QueryBuilder();
		DatabaseConnection dbc = DatabaseConnection.getInstance();
		dbc.start();
		try 
		{
			PreparedStatement stmt = dbc.getConnection().prepareStatement(query.insert());
			stmt.setString(1, person.getKey());
			stmt.setString(2, person.getName());
			stmt.setString(3, person.getSurname());
			stmt.setString(4, person.getDate());
			stmt.execute();
			stmt.close();
		}
		catch (NullPointerException ex)
        {
            ex.printStackTrace();
        } 
		catch (SQLException e)
		{
			e.printStackTrace();
			return Response.serverError().build();
		}
		finally
		{
			dbc.closeConnection();
		}
		return Response.created(new URI("/person/" + person.getKey())).build();
	}

	@PUT
	@Path("/person/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("key") String key, Person person) 
	{
		if(person.getKey() == null)
		{
			throw new RuntimeException("Key is mandatory");
		}
		if(person.getName() == null)
		{
			throw new RuntimeException("Name is mandatory");
		}
		if(person.getSurname() == null)
		{
			throw new RuntimeException("Surname is mandatory");
		}
		if(person.getDate() == null)
		{
			throw new RuntimeException("Date is mandatory");
		}
		QueryBuilder query = new QueryBuilder();
		DatabaseConnection dbc = DatabaseConnection.getInstance();
		dbc.start();
		try 
		{
			PreparedStatement stmt = dbc.getConnection().prepareStatement(query.update());
			stmt.setString(1, person.getKey());
			stmt.setString(2, person.getName());
			stmt.setString(3, person.getSurname());
			stmt.setString(4, person.getDate());
			stmt.setString(5, key);
			stmt.execute();
			stmt.close();
		}
		catch (NullPointerException ex)
        {
            ex.printStackTrace();
        } 
		catch (SQLException e)
		{
			e.printStackTrace();
			return Response.serverError().build();
		}
		finally
		{
			dbc.closeConnection();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/person/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("key") String key) 
	{
		QueryBuilder query = new QueryBuilder();
		DatabaseConnection dbc = DatabaseConnection.getInstance();
		dbc.start();
		try 
		{
			PreparedStatement stmt = dbc.getConnection().prepareStatement(query.delete());
			stmt.setString(1, key);
			stmt.execute();
			stmt.close();
		}
		
		catch (NullPointerException ex)
        {
            ex.printStackTrace();
        } 
		catch (SQLException e)
		{
			e.printStackTrace();
			return Response.serverError().build();
		}
		finally
		{
			dbc.closeConnection();
		}
		return Response.ok().build();
	}

}
