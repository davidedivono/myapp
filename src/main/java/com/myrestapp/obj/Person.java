package com.myrestapp.obj;
import java.io.*;

public class Person implements Serializable {
	private String key;
	private String name;
	private String surname;
	private String date;

	public Person() 
	{
		
	}
	
	public Person(String k, String n, String c, String d)
	{
		key = k;
		name = n;
		surname = c;
		date = d;
	}
	
	public void setKey(String k)
	{
		key = k;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setSurname(String c)
	{
		surname = c;
	}
	
	public void setDate(String d)
	{
		date = d;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getDate()
	{
		return date;
	}

}
