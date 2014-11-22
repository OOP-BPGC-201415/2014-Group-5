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
				
				case "AddAssignee":
					addAssigneeHandler();
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
		
		ArrayList<Volunteer> assignees = entity.getAssignees();
		request.setAttribute("assignees", assignees ); 
		
		request.getRequestDispatcher("/ActivityJSP/Default.jsp").include(request, response);
	}
	
	public void postReportHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		request.getRequestDispatcher("/ActivityJSP/PostReport.jsp").include(request, response);
	}
	
	public void addAssigneeHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		ArrayList<Volunteer> allVolunteers = Volunteer.loadAllVolunteers();
		request.setAttribute("allVolunteers", allVolunteers);
		request.getRequestDispatcher("/ActivityJSP/AddAssignee.jsp").include(request, response);
	}
	
	void postHandler()  throws ServletException, IOException{
		try{
			switch(action){
				case "PostReport":
					postReport();
					break;
				case "AddAssignee":
					addAssignee();
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
			
			response.sendRedirect("/Activity/"+ entity.getId());
		}catch(Exception e){
			setError(e);
			request.setAttribute("defaultReport", report);
			postReportHandler();
		}
	}
	
	void addAssignee() throws SQLException,IncompleteFieldException, ServletException, IOException, EntityNotFoundException{
			ArrayList<Integer> assigneeIds = new ArrayList<Integer>();
		try{
			loadEntity();
			
			String[] idString= request.getParameterValues("assignees");
			for( String id: idString){
				assigneeIds.add(Integer.parseInt(id));
			}
			ArrayList<Volunteer> vList = new ArrayList<Volunteer>();
			ArrayList<Integer> failed =new ArrayList<Integer>();
			for(int aid: assigneeIds){
				try{
					vList.add(new Volunteer().load(aid));
				}catch(Exception e){
					failed.add(aid);
				}
			}
			for(Volunteer v: vList){
				try{
					entity.addAssignee(v);
				}catch(Exception e){
					
					failed.add(v.getId());
				}
			}
			if( failed.size()>0 ){
				String failedList = "";
				for(int f: failed){
					failedList+= "<a href='/Volunteer/'"+f+"'>"+f+"</a>, ";
				}
				setError(new Exception("Some of the assignees could not be added"), "Some of the assignees could not be added",failedList);
			}
			else{
				setError(new Exception("Success: All assignees were added"), "Success!","All assignees were added");
			}
		}catch(Exception e){
			setError(e);
		}finally{
			addAssigneeHandler();
		}
	}
}