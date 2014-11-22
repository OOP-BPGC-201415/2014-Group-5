<%@ page import="java.util.*,nirmaanam.Volunteer,nirmaanam.Activity,servlets.Utility" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Assignee: ${activity.getEvent().getName()} &gt; ${activity.getName()}</title>
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
<h2>${activity.getName()}</h2>
<p>${activity.getDescription()}</p>
<form action="" method="post">
	<select name="assignees" multiple id="assigneeSelect">
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
	$("#assigneeSelect").chosen({ width: '400px' })
}
);
</script>
</body>
</html>