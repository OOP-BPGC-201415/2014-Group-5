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
public class MeetingServlet extends BaseServlet<Meeting> {
	
	Meeting loadEntity() throws SQLException,EntityNotFoundException{
		if(entity!=null && entity.getId() == entityId)
			return entity;
		entity = new Meeting().load( entityId );
		request.setAttribute("entity", entity);
		request.setAttribute("meeting", entity);
		return entity;
	}
	
	void getHandler() throws ServletException, IOException{
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
		try{
			
			switch(action){
				case "Create":
					createHandler();
					break;
				
				case "AddAttendee":
					addAttendeeHandler();
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
		ArrayList<Volunteer> attendees = entity.getAttendees();
		request.setAttribute("attendees", attendees); 
		//response.getWriter().println("FOUND " + attendees.size() + " attendees" );
		request.getRequestDispatcher("/MeetingJSP/Default.jsp").include(request, response);
	}
	
	public void createHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		request.getRequestDispatcher("/MeetingJSP/Create.jsp").include(request, response);
	}
	
	public void addAttendeeHandler() throws ServletException, IOException, SQLException,EntityNotFoundException{
		loadEntity();
		ArrayList<Volunteer> allVolunteers = Volunteer.loadAllVolunteers();
		request.setAttribute("allVolunteers", allVolunteers);
		request.getRequestDispatcher("/MeetingJSP/AddAttendee.jsp").include(request, response);
	}
	
	void postHandler()  throws ServletException, IOException{
		try{
			switch(action){
				case "Create":
					create();
					break;
				case "AddAttendee":
					addAttendee();
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
	
	void create() throws SQLException,IncompleteFieldException, ServletException, IOException, EntityNotFoundException{
		String purpose="";
		String location="";
		String date="";
		String timeStr="";
		try{
			location = (String)request.getParameter("location");
			purpose = (String)request.getParameter("purpose");
			date = (String)request.getParameter("date");
			timeStr = (String)request.getParameter("time");
			int time = Utility.StrToTimeStamp(date,timeStr);
			//response.getWriter().println("TIME IS " + time);
			Meeting meeting = new Meeting(time,location,purpose);
			meeting.store();
			response.sendRedirect("/Meeting/"+ meeting.getId());
		}catch(Exception e){
			setError(e);
			request.setAttribute("defaultLocation", location);
			request.setAttribute("defaultPurpose", purpose);
			request.setAttribute("defaultDate", date);
			request.setAttribute("defaultTime", timeStr);
			createHandler();
		}
	}
	
	void addAttendee() throws SQLException,IncompleteFieldException, ServletException, IOException, EntityNotFoundException{
			ArrayList<Integer> attendeeIds = new ArrayList<Integer>();
		try{
			loadEntity();
			
			String[] idString= request.getParameterValues("attendees");
			for( String id: idString){
				attendeeIds.add(Integer.parseInt(id));
			}
			ArrayList<Volunteer> vList = new ArrayList<Volunteer>();
			ArrayList<Integer> failed =new ArrayList<Integer>();
			for(int aid: attendeeIds){
				try{
					vList.add(new Volunteer().load(aid));
				}catch(Exception e){
					failed.add(aid);
				}
			}
			for(Volunteer v: vList){
				try{
					entity.addAttendee(v);
				}catch(Exception e){
					
					failed.add(v.getId());
				}
			}
			if( failed.size()>0 ){
				String failedList = "";
				for(int f: failed){
					failedList+= "<a href='/Volunteer/'"+f+"'>"+f+"</a>, ";
				}
				setError(new Exception("Some of the attendees could not be added"), "Some of the attendees could not be added",failedList);
			}
			else{
				setError(new Exception("Success: All attendees were added"), "Success!","All attendees were added");
			}
		}catch(Exception e){
			setError(e);
		}finally{
			addAttendeeHandler();
		}
	}
}