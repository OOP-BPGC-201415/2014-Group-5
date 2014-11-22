<%@ page import="java.util.*,servlets.Utility,nirmaanam.Activity,nirmaanam.Event,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title>${activity.getEvent().getName()} &gt; ${activity.getName()}</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>

<h2>${activity.getName()}</h2>
<p>${activity.getDescription()}</p>
<p><%= Utility.TimeStampToStr( ((Activity)request.getAttribute("activity")).getTime() )%></p>
<p>(<b>Event:</b><a href="/Event/${activity.getEvent().getId()}">${activity.getEvent().getName()}</a>)</p>
<h3>Assignees:</h3>
<%
ArrayList<Volunteer> assignees  = (ArrayList<Volunteer>)request.getAttribute("assignees");

if(assignees.size()==0)
	out.println("<p>No assignees yet</p>");
else{
	out.println("<table>");
	for(Volunteer v: assignees){%>
		<tr>
			<td><img src="<%=Utility.getProfileImage(v)%>" class="idpic"/></td>
			<td><%=v.getName()%><br/><%=v.getBitsID()%></td>
		</tr><%
	}
	out.println("</table>");
}
%>
<p><a href="/Activity/${activity.getId()}/AddAssignee">Add Assignee</a></p>
<h3>Report:</h3>
<p>${activity.getReport()}</p>

<a href="/Activity/${activity.getId()}/PostReport">Update Report</a>

</body>
</html>