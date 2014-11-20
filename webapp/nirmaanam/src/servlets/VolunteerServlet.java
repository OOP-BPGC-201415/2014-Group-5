package servlets;
// Import required java libraries
//Servlet API
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

import nirmaanam.Vertical;
//import nirmaanam.Database;

// Extend HttpServlet class
public class VolunteerServlet extends HttpServlet {

  public void init() throws ServletException
  {
      // Do required initialization
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
	  try{
		ArrayList<Vertical> verticalList = Vertical.getVerticalList();
		request.setAttribute("verticalList", verticalList);
		request.getRequestDispatcher("/Vertical.jsp").include(request, response);
		
	}catch(Exception e){
		response.getWriter().println("Exception: "+e);
		e.printStackTrace(response.getWriter());
	}
	  
  }
  
  public void destroy()
  {
      // do nothing.
  }
}