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
public class VolunteerServlet extends BaseServlet<Volunteer> {

	Volunteer loadEntity() throws SQLException, EntityNotFoundException{
		if(entity!=null && entity.getId() == entityId)
			return entity;
		
		entity = new Volunteer().load( entityId );
		request.setAttribute("entity", entity);
		request.setAttribute("volunteer", entity);
		return entity;
	}
	
	//Override
	void dissectPath(){
		String[] pathComponents= request.getPathInfo().split("/");
		action = "";
		entityId = 0;
		if(pathComponents.length<2)
			return;
		
		if( pathComponents[1].equals("Create") || pathComponents[1].equals("Login") || pathComponents[1].equals("Logout") ){
			action=pathComponents[1];
		}
		else{
			entityId = Integer.parseInt(pathComponents[1]);
			action = (pathComponents.length >2)?pathComponents[2]: "";
		}
	}
	void getHandler() throws ServletException, IOException
	{
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
		try{		
			switch(action){
				case "Create":
					createHandler();
					break;
					
				case "Login":
					loginHandler();
					break;
					
				case "Logout":
					logoutHandler();
					break;
					
				default:
					defaultHandler();
					break;
			}
		}catch(EntityNotFoundException enfe){
			errorPage(enfe,"Entity Not Found","The requested Entity was not found in our database. <!-- Could also be one of the dependent entities -->");
		}catch(Exception e){
			errorPage(e, "Unexpected behaviour", e.getMessage());
		}
	}
  
	void defaultHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		request.getRequestDispatcher("/VolunteerJSP/Default.jsp").include(request, response);
	}
	
	void loginHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		request.getRequestDispatcher("/VolunteerJSP/Login.jsp").include(request, response);
	}
	
	void logoutHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		request.getSession().setAttribute("volunteerId",null);
		response.sendRedirect("/Home");
	}
	
	void createHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		request.setAttribute("allVerticals", Vertical.getVerticalList());
		request.getRequestDispatcher("/VolunteerJSP/Create.jsp").include(request, response);
	}
	
	void postHandler() throws ServletException, IOException
	{
		// Actual logic goes here.
		try{
			switch(action){
				case "Create":
					create();
					break;
				case "Login":
					login();
					break;
				default:
					defaultHandler();
					break;
			}
		
		}catch(EntityNotFoundException enfe){
			errorPage(enfe,"Entity Not Found","The requested Entity was not found in our database. <!-- Could also be one of the dependent entities -->");
		}catch(Exception e){
			errorPage(e, "Unexpected behaviour", e.getMessage());
		}
	}
	
	public void create() throws ServletException, IOException, SQLException,EntityNotFoundException{
	
		String name = (String)request.getParameter("name");
		String bitsID = (String)request.getParameter("bitsID");
		int year = Integer.parseInt((String)request.getParameter("year"));
		
		
		String email =(String)request.getParameter("email");
		String passHash = (String)request.getParameter("password");
		int verticalId = Integer.parseInt( (String) request.getParameter("vertical") );
		try{
			passHash = Utility.MD5Hash(passHash);
			Volunteer newVolunteer = new Volunteer(name,bitsID,year);
			newVolunteer.setVertical(new Vertical().load(verticalId));
			newVolunteer.store();
			
			newVolunteer.updateLogin(email,passHash);
			request.getSession().setAttribute("volunteerId", newVolunteer.getId());
			response.sendRedirect("/Home");
		}catch(Exception e){
			request.setAttribute("defaultName", name);
			request.setAttribute("defaultBitsID", bitsID);
			request.setAttribute("defaultYear", year);
			
			setError(e);
			createHandler();
			//request.getRequestDispatcher("/VolunteerJSP/Create.jsp").include(request, response);
			
		}
	}
	
	public void login() throws ServletException, IOException, SQLException,EntityNotFoundException{
		String email =(String)request.getParameter("email");
		String passHash = (String)request.getParameter("password");
		Volunteer volunteer = new Volunteer();
		try{
			passHash = Utility.MD5Hash(passHash);
			if(volunteer.tryLogin(email,passHash)){
				request.getSession().setAttribute("volunteerId",volunteer.getId());
				response.sendRedirect("/Home");
			}
			else 
				throw(new Exception("Login failed"));
		}catch(Exception e){
			request.setAttribute("defaultEmail", email);
			
			setError(e);
			loginHandler();
		}
	}
	
}