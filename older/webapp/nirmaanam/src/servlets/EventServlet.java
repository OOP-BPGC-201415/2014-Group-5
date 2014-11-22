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
public class EventServlet extends BaseServlet<Event>{
	
	Event loadEntity()  throws SQLException, EntityNotFoundException{
		if(entity!=null && entity.getId() == entityId)
			return entity;
		
		entity = new Event().load( entityId );
		request.setAttribute("entity", entity);
		request.setAttribute("event", entity);
		return entity;
	}
	
	public void getHandler()  throws ServletException, IOException{
      // Set response content type
      response.setContentType("text/html");

		try{	
			switch(action){
				case "AddActivity":
					addActivityHandler();
					break;
				case "Activities":
					activitiesHandler();
					break;
				default:
					defaultHandler();
					break;
			}
			
		}catch(EntityNotFoundException enfe){
			errorPage(enfe, "Entity Not Found", "The requested Entity was not found in our database. <!-- Could also be one of the dependent entities -->");
		}catch(Exception e){
			errorPage(e,"Unexpected behaviour", "An exception occured");
		}
	}
  
	public void defaultHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		request.getRequestDispatcher("/EventJSP/Default.jsp").include(request, response);
	}
	public void activitiesHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		request.setAttribute("activityList",entity.getActivityList());
		request.getRequestDispatcher("/EventJSP/Activities.jsp").include(request, response);
	}
	
	public void addActivityHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		ArrayList<Volunteer> allVolunteers = Volunteer.loadAllVolunteers();
		request.setAttribute("allVolunteers", allVolunteers);
		
		request.getRequestDispatcher("/EventJSP/AddActivity.jsp").include(request, response);
	}
	
	public void postHandler()  throws ServletException, IOException{
      // Set response content type
		response.setContentType("text/html");
		
		try{	
			switch(action){
				case "AddActivity":
					addActivity();
					break;
				default:
					defaultHandler();
					break;
			}
			
		}catch(EntityNotFoundException enfe){
			errorPage(enfe, "Entity Not Found", "The requested Entity was not found in our database. <!-- Could also be one of the dependent entities -->");
		}catch(Exception e){
			errorPage(e,"Unexpected behaviour", "An exception occured");
		}
	}
	
	void addActivity() throws SQLException,IncompleteFieldException, ServletException, IOException, EntityNotFoundException{
			loadEntity();	//We need to know
			String name =(String)request.getParameter("name");
			String description =(String)request.getParameter("description");
			int timeStamp = Utility.StrToTimeStamp((String)request.getParameter("date"));
			int headId =Integer.parseInt((String)request.getParameter("head"));
		try{
			loadEntity();
			Volunteer head = new Volunteer().load(headId);
			//Create a new Store in the database
			
			Activity newActivity= new Activity(name, description, timeStamp, head);
			
			entity.addActivity(newActivity);
			
			response.sendRedirect("/Activity/"+ newActivity.getId());
		}catch(Exception e){
			setError(e);
			
			request.setAttribute("defaultName", name);
			request.setAttribute("defaultDescription", description);
			request.setAttribute("defaultHead", headId);
			addActivityHandler();
		}
	}
}