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
public class ActivityServlet extends BaseServlet<Activity> {
	
	Activity loadEntity() throws SQLException,EntityNotFoundException{
		if(entity!=null && entity.getId() == entityId)
			return entity;
		entity = new Activity().load( entityId );
		request.setAttribute("entity", entity);
		request.setAttribute("activity", entity);
		return entity;
	}
	
	void getHandler() throws ServletException, IOException{
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
		try{
			
			switch(action){
				case "PostReport":
					postReportHandler();
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
  
	public void defaultHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		request.getRequestDispatcher("/ActivityJSP/Default.jsp").include(request, response);
	}
	
	public void postReportHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		request.getRequestDispatcher("/ActivityJSP/PostReport.jsp").include(request, response);
	}
	
	void postHandler()  throws ServletException, IOException{
		try{
			switch(action){
				case "PostReport":
					postReport();
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
	
	void postReport() throws SQLException,IncompleteFieldException, ServletException, IOException, EntityNotFoundException{
		loadEntity();	//We need to know
		String report="";
		try{
			report = (String)request.getParameter("report");
			loadEntity();
			
			entity.setReport(report);
			response.getWriter().println("EHHH");
			response.sendRedirect("/Activity/"+ entity.getId());
		}catch(Exception e){
			setError(e);
			request.setAttribute("defaultReport", report);
			postReportHandler();
		}
	}

}