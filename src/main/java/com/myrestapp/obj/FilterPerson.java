package com.myrestapp.obj;
import java.io.*;

public class FilterPerson implements Serializable{
	private String name;
	private String surname;
	private String date1;
	private String date2;
	private int recordNum;
	
	public FilterPerson() 
	{
		
	}
	
	public FilterPerson(String n, String c, String d1, String d2, int rn)
	{
		name = n;
		surname = c;
		date1 = d1;
		date2 = d2;
		recordNum = rn;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setSurname(String c)
	{
		surname = c;
	}
	
	public void setDate1(String d1)
	{
		date1 = d1;
	}
	
	public void setDate2(String d2)
	{
		date2 = d2;
	}
	
	public void setRecordNum(int rn)
	{
		recordNum = rn;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getDate1()
	{
		return date1;
	}
	
	public String getDate2()
	{
		return date2;
	}
	
	public int getRecordNum()
	{
		return recordNum;
	}

}