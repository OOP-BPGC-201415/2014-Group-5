package servlets;
// Import required java libraries
//Servlet API
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.sql.SQLException;

import nirmaanam.*;
//import nirmaanam.Database;

// Extend HttpServlet class
public class HomeServlet extends BaseServlet<Volunteer> {

  Volunteer loadEntity()throws SQLException,EntityNotFoundException{
	entity=null;
	return entity;
  }

	void dissectPath(){
		action = "";
		entityId = 0;
	}
  public void getHandler() throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
	  try{
		if(loggedIn()==null){
			ArrayList<Vertical> verticalList = Vertical.getVerticalList();
			request.setAttribute("verticalList", verticalList);
			request.getRequestDispatcher("/HomeJSP/NotLoggedIn.jsp").include(request, response);		
		}
		else{
			ArrayList<Activity> activityList= loggedInVolunteer.getActivityList();
			ArrayList<Meeting>  meetingList = loggedInVolunteer.getMeetingList();
			request.setAttribute("activityList", activityList);
			request.setAttribute("meetingList", meetingList);
			
			request.getRequestDispatcher("/HomeJSP/IsLoggedIn.jsp").include(request, response);		
		}
	}catch(Exception e){
		response.getWriter().println("Exception: "+e);
		e.printStackTrace(response.getWriter());
	}
	  
  }
	public void postHandler() throws ServletException, IOException{
		getHandler();
	}
  
}