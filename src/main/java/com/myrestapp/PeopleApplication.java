package com.myrestapp;
import java.util.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.*;
import com.myrestapp.service.ServicePeople;

@ApplicationPath("/")
public class PeopleApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public PeopleApplication()
	{
		List<AsyncResponse> listeners = new ArrayList<AsyncResponse>();
		singletons.add(new ServicePeople(listeners));
	}

	@Override
	public Set<Class<?>> getClasses()
	{
		return empty;
	}

	@Override
	public Set<Object> getSingletons()
	{
		return singletons;
	}
	
}
