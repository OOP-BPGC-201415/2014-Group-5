package servlets;
// Import required java libraries
//Servlet API
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

import nirmaanam.*;
import java.sql.SQLException;
//import nirmaanam.Database;

// Extend HttpServlet class
public class VerticalServlet extends HttpServlet {

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
		if(request.getPathInfo()!=null){
			
			String[] pathComponents = request.getPathInfo().split("/");
			//for(int i=0;i<pathComponents.length;i++) 				response.getWriter().println("<!--"+i+ "=> " + pathComponents[i]+"-->");
			
			int verticalId = Integer.parseInt(pathComponents[1]);
			
			Vertical vertical = new Vertical().load( verticalId );
			request.setAttribute("vertical", vertical);
			
			if(pathComponents.length>2){
				switch(pathComponents[2]){
					
					case "Events": 
						eventHandler(request, response, vertical);
						break;
					
					default:
						defaultHandler(request, response, vertical);
						break;
				}
			}else{
				defaultHandler(request, response, vertical);
			}
		}
		else{
			response.sendRedirect("/Home");	//Not enough to work with
		}
	}catch(Exception e){
		response.getWriter().println("Exception: "+e);
		e.printStackTrace(response.getWriter());
	}
  }
  
  public void defaultHandler(HttpServletRequest request, HttpServletResponse response, Vertical vertical) throws ServletException, IOException, SQLException,EntityNotFoundException{
		request.getRequestDispatcher("/Vertical.jsp").include(request, response);
  }
  
  public void eventHandler(HttpServletRequest request, HttpServletResponse response, Vertical vertical) throws ServletException, IOException, SQLException,EntityNotFoundException{
	ArrayList<Event> eventList = vertical.getEventList();
	request.setAttribute("eventList", eventList);
	request.getRequestDispatcher("/Vertical_Event.jsp").include(request, response);
  }
  
  public void destroy()
  {
      // do nothing.
  }
}