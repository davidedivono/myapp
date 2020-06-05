package com.myrestapp.test;
import java.sql.*;
import org.junit.*;
import static org.junit.Assert.*;
import javax.servlet.http.HttpServletResponse;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import com.myrestapp.db.*;
import com.myrestapp.obj.*;
import com.myrestapp.service.*;

public class ApplicationTest {

	@Test
	public void select() throws Exception {
	   Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
	   POJOResourceFactory service = new POJOResourceFactory(ServicePeople.class);
	   dispatcher.getRegistry().addResourceFactory(service);
	   MockHttpRequest request = MockHttpRequest.get("/person/60365995");
	   MockHttpResponse response = new MockHttpResponse();
	   dispatcher.invoke(request, response);
	   assertEquals(HttpServletResponse.SC_OK, response.getStatus());
	}
	
}
