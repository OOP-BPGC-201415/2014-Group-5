package servlets;
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
 
  private String message;

  public void init() throws ServletException
  {
      // Do required initialization
      message = "Hello World!!!";
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>" + message + "</h1>");
	  request.setAttribute("passedObject", this);
	  request.getRequestDispatcher("/HelloWorld/HelloWorld.jsp").include(request, response);
	  
  }
  public String getMessage(){
	return message;
  }
  
  public void destroy()
  {
      // do nothing.
  }
}