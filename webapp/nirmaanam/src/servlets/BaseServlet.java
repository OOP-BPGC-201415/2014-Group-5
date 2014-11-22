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
abstract class BaseServlet<EntityType extends NirmaanEntity> extends HttpServlet {
	
	String action;
	int entityId;
	EntityType entity;
	Volunteer loggedInVolunteer;
	HttpServletRequest request;
	HttpServletResponse response;
	
	public void init() throws ServletException {
	  // Do required initialization
	}

	public void destroy(){
	  // do nothing.
	}
	
	public void genUtils(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		dissectPath();
	}
	
	
	public Volunteer loggedIn() throws SQLException,EntityNotFoundException{	//Returns the volunteer who is logged in
		try{
			if( loggedInVolunteer==null ){
				int volunteerId = (int)request.getSession().getAttribute("volunteerId");
				loggedInVolunteer = new Volunteer().load(volunteerId);
			}
		}catch(EntityNotFoundException enfe){
			loggedInVolunteer = null;
			try{
				response.getWriter().println("EXCEPTIONNN!!");
			}catch(Exception e){
				//FUCKIT
			}
		}
		
		return loggedInVolunteer;
	}
	
	public void freeUtils(){
		this.request = null;
		this.response = null;
		this.action = null;
		this.entityId = 0;
		this.loggedInVolunteer  = null;
	}
	
	void dissectPath(){
		String[] pathComponents= request.getPathInfo().split("/");
		action = "";
		entityId = 0;
		if(pathComponents.length<2)
			return;
		
		if( pathComponents[1].equals("Create") || pathComponents[1].equals("Login")){
			action=pathComponents[1];
		}
		else{
			entityId = Integer.parseInt(pathComponents[1]);
			action = (pathComponents.length >2)?pathComponents[2]: "";
		}
	}
	
	public void setError(Exception exception){
		request.setAttribute("error", "Exception");
		request.setAttribute("errorMessage",exception.getMessage());
		request.setAttribute("exception", exception);
	}
	public void setError(Exception exception,String error, String errorMessage ){
		request.setAttribute("error", error);
		request.setAttribute("errorMessage",errorMessage);
		request.setAttribute("exception", exception);
	}
	public void errorPage(Exception exception,String error, String errorMessage ) throws ServletException, IOException{	
		setError(exception, error, errorMessage);
		
		request.getRequestDispatcher("/Error.jsp").include(request, response);
		exception.printStackTrace(response.getWriter());
	}
	
	
	
	public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
	{
		genUtils(request, response);
		getHandler();
		freeUtils();
		freeEntity();
	}
	
	public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
	{
		genUtils(request, response);
		postHandler();
		freeUtils();
		freeEntity();
	}
	
	abstract void getHandler() throws ServletException, IOException;
	abstract void postHandler() throws ServletException, IOException;
	
	abstract EntityType loadEntity() throws SQLException,EntityNotFoundException;
	/*EntityType loadEntity() throws SQLException,EntityNotFoundException{
		EntityType entity = new EntityType().load(entityId);
		request.setAttribute("entity", entity);
		return entity;
	}*/
	void freeEntity(){
		entity = null;
	}
	
	//abstract void create() throws SQLException,IncompleteFieldException, ServletException, IOException, EntityNotFoundException;
	
}