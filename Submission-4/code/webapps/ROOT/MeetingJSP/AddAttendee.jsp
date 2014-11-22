<%@ page import="java.util.*,nirmaanam.Volunteer,nirmaanam.Meeting,servlets.Utility" %>
<!DOCTYPE html>
<html>
<head>
<title>Meeting : Add Attendee</title>
<link rel="stylesheet" href="/js/chosen/chosen.css" />
<link rel="stylesheet" href="/css/global.css" />
</head>
<body>

<% if(request.getAttribute("error")!=null){
		out.println(request.getAttribute("error"));
		out.println((String)request.getAttribute("errorMessage"));
	}
	//else		out.println("No error");
%>
<h2>${meeting.getLocation()} @ <%= Utility.TimeStampToStr( ((Meeting)request.getAttribute("meeting")).getTime() )%></h2>
<p>${meeting.getPurpose()}</p>
<form action="" method="post">
	<select name="attendees" multiple id="attendeeSelect">
		<% ArrayList<Volunteer> allVolunteers = (ArrayList<Volunteer>) request.getAttribute("allVolunteers");
		for(Volunteer v: allVolunteers){	%>
			<option value="<%= v.getId()%>"><%= v.getName() %></option><%
		}
		%>
	</select>
	
	<input type="submit"/>
</form>

<script src="/js/jquery.js" type="text/javascript"></script>
<script src="/js/chosen/chosen.jquery.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	$("#attendeeSelect").chosen({ width: '400px' })
}
);
</script>
</body>
</html>