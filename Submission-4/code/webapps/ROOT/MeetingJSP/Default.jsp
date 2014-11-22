<%@ page import="java.util.*,servlets.Utility,nirmaanam.Meeting,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title>Meeting</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>

<h2>Meeting @  
<%= Utility.TimeStampToStr( ((Meeting)request.getAttribute("meeting")).getTime() )%>
( ${meeting.getLocation()} ) </h2>
<h3>Attendees:</h3>
<%
ArrayList<Volunteer> attendees = (ArrayList<Volunteer>)request.getAttribute("attendees");

if(attendees.size()==0)
	out.println("No attendees yet");
else{
	out.println("<table>");
	for(Volunteer v: attendees){%>
		<tr>
			<td><img src="<%=Utility.getProfileImage(v)%>" class="idpic"/></td>
			<td><%=v.getName()%><br/><%=v.getBitsID()%></td>
		</tr><%
	}
	out.println("</table>");
}
%>

<p><a href="/Meeting/${meeting.getId()}/AddAttendee">Add attendees</a></p>

</body>
</html>