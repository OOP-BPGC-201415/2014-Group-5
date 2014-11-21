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
public class VerticalServlet extends BaseServlet<Vertical> {
	
	Vertical loadEntity() throws SQLException, EntityNotFoundException{
		if(entity!=null && entity.getId() == entityId)
			return entity;
		
		entity = new Vertical().load( entityId );
		request.setAttribute("entity", entity);
		request.setAttribute("vertical", entity);
		return entity;
	}
	
	public void getHandler() throws ServletException, IOException{
      // Set response content type
		response.setContentType("text/html");
		
		try{
			switch(action){
				case "Events": 
					eventsHandler();
					break;
					
				case "Volunteers": 
					volunteersHandler();
					break;
				
				case "Create":
					createHandler();
					break;
					
					
				case "AddEvent": 
					addEventHandler();
					break;
				
				default:
					defaultHandler();
					break;
			}
		}catch(EntityNotFoundException enfe){
			errorPage(enfe, "Entity Not Found", "The requested Entity was not found in our database. <!-- Could also be one of the dependent entities -->");
		}catch(Exception e){
			errorPage(e, "Unexpected behaviour", "An exception occured");
		}
	}
  
	public void defaultHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		request.getRequestDispatcher("/VerticalJSP/Default.jsp").include(request, response);
	}
  
	public void eventsHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		ArrayList<Event> eventList = entity.getEventList();
		request.setAttribute("eventList", eventList);
		request.getRequestDispatcher("/VerticalJSP/Events.jsp").include(request, response);
	}
	
	public void volunteersHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		ArrayList<Volunteer> volunteerList = entity.getVolunteerList();
		request.setAttribute("volunteerList", volunteerList);
		request.getRequestDispatcher("/VerticalJSP/Volunteers.jsp").include(request, response);
	}  
	
	public void createHandler() throws SQLException,EntityNotFoundException, ServletException, IOException{
		ArrayList<Volunteer> allVolunteers = Volunteer.loadAllVolunteers();
		request.setAttribute("allVolunteers", allVolunteers);
		request.getRequestDispatcher("/VerticalJSP/Create.jsp").include(request, response);
	}
	
	public void addEventHandler() throws SQLException,EntityNotFoundException, ServletException, IOException{
		loadEntity();
		ArrayList<Volunteer> allVolunteers = Volunteer.loadAllVolunteers();
		request.setAttribute("allVolunteers", allVolunteers);
		request.getRequestDispatcher("/VerticalJSP/AddEvent.jsp").include(request, response);
	}
	
  
  public void postHandler() throws ServletException, IOException{	//Creates a new Vertical
      // Actual logic goes here.
		try{
			switch(action){
				case "Create":
					create();
					break;
				/*case "AddVolunteer": 
					addVolunteer();
					break;
				*/
				case "AddEvent": 
					addEvent();
					break;
				default:
					defaultHandler();
					break;
			}
		}catch(EntityNotFoundException enfe){
			errorPage(enfe, "Entity Not Found","The requested Entity was not found in our database. <!-- Could also be one of the dependent entities -->");
		}catch(Exception e){
			errorPage(e,"Unexpected behaviour", "An exception occured");
		}
  }
  
	void create() throws ServletException, IOException, SQLException, IncompleteFieldException, EntityNotFoundException{	
			String name =(String)request.getParameter("name");
			String description =(String)request.getParameter("description");
			int headId =Integer.parseInt((String)request.getParameter("head"));
		try{
			Volunteer head = new Volunteer().load(headId);
			//Create a new Store in the database
			Vertical newVertical = new Vertical(name, description, head);
			newVertical.store();
			
			response.sendRedirect("/VerticalJSP/"+ newVertical.getId());
		}catch(Exception e){
			setError(e);
			
			request.setAttribute("defaultName", name);
			request.setAttribute("defaultDescription", description);
			request.setAttribute("defaultHead", headId);
			
			request.getRequestDispatcher("/VerticalJSP/Create.jsp").include(request, response);
		}
	}
	
	public void addEvent() throws ServletException, IOException, SQLException, IncompleteFieldException, EntityNotFoundException{
			loadEntity();	//We need to know
			String name =(String)request.getParameter("name");
			String description =(String)request.getParameter("description");
			int headId =Integer.parseInt((String)request.getParameter("head"));
		try{
			loadEntity();
			Volunteer head = new Volunteer().load(headId);
			//Create a new Store in the database
			
			Event newEvent = new Event(name, description, head);
			
			
			entity.addEvent(newEvent);
			
			response.sendRedirect("/Event/"+ newEvent.getId());
		}catch(Exception e){
			setError(e);
			
			request.setAttribute("defaultName", name);
			request.setAttribute("defaultDescription", description);
			request.setAttribute("defaultHead", headId);
			addEventHandler();
			//request.getRequestDispatcher("/VerticalJSP/AddEvent.jsp").include(request, response);
		}
	}
	
	
	/*
	public void addVolunteer() throws ServletException, IOException, SQLException, IncompleteFieldException, EntityNotFoundException{
			loadEntity();	//We need to know
			String name =(String)request.getParameter("name");
			String bitsID =(String)request.getParameter("bitsID");
			int year = Integer.parseInt((String)request.getParameter("year"));
			
			String email =(String)request.getParameter("email");
			String passHash = Utility.MD5Hash((String)request.getParameter("password"));
			
		try{
			loadEntity();
			Volunteer newVolunteer = new Volunteer(name, bitsID, year);
			newVolunteer.store();
			newVolunteer.updateLogin(email,passHash);
			//Create a new Store in the database
			
			
			
		}catch(Exception e){
			setError(e);
			
			request.setAttribute("defaultName", name);
			request.setAttribute("defaultDescription", description);
			request.setAttribute("defaultHead", headId);
			addEventHandler();
			//request.getRequestDispatcher("/VerticalJSP/AddEvent.jsp").include(request, response);
		}
	}*/
	
}