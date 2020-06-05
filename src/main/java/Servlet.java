import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.myrestapp.db.*;
import com.myrestapp.obj.*;

@WebServlet("/servlet")

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public Servlet() 
	{
		super();
    }

@Override              
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	response.setContentType("text/html");
    	PrintWriter writer = response.getWriter();
    	FilterPerson filter = new FilterPerson(request.getParameter("name"), request.getParameter("surname"), request.getParameter("dateFrom"), request.getParameter("dateTo"), (Integer.parseInt(request.getParameter("record"))));
    	Finder finder = new Finder();
    	List<Person> list = new ArrayList<Person>();
    	list = finder.find(filter);
    	ObjectMapper mapper = new ObjectMapper();
    	String json = mapper.writeValueAsString(list);
    	System.out.println(json);
    	writer.println("<!DOCTYPE html>");
    	writer.println("<html>");
    	writer.println("<head>");
    	writer.println("<title>Result");
    	writer.println("</title>");
    	writer.println("<script type='text/javascript' src='result.js'>");
    	writer.println("</script>");
    	writer.println("</head>");
    	writer.println("<body>");
    	writer.println("<resultlist id='myresult' value='" + json + "'></resultlist>");
    	writer.println("<div>");
    	writer.println("<script>createbody();");
    	writer.println("</script>");
    	writer.println("</div>");
    	writer.println("</body>");
    	writer.println("</html>");	
    }

}
