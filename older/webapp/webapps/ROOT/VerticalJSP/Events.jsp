<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Event" %>
<!DOCTYPE html>
<html>
<head>
<title>${vertical.getName()}</title>
</head>
<body>

<h2>${vertical.getName()}</h2>
<p>${vertical.getDescription()}</p>
<%
	ArrayList<Event> eventList = (ArrayList<Event>)request.getAttribute("eventList");		
	 
	if( eventList.size() ==0){ %>
		<h3>No events found</h3> <%
	}else{
	 %>
	<h3>Events:</h3>
		<ul>
		<%	
		for(Event e: eventList){	%>
			<li><b><a href="/Event/<%=e.getId()%>"><%= e.getName() %></a>:</b> <%= e.getDescription() %></li> <% 
		}
		%>
		</ul><%
	}
	%>
</body>
</html>