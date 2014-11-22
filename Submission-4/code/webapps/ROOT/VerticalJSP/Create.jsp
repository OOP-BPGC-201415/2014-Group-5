<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title>Create Vertical</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>

<% if(request.getAttribute("error")!=null){
		out.println("Error");
		out.println("Error: "+(String)request.getAttribute("errorMessage"));
	}
	//else		out.println("No error");
%>
	<div class="main">
		<form action="/Vertical/Create" method="post">
			<input name="name" placeholder="name"/>
			<input name="description" placeholder="description"/>
			<select name="head">
				<% ArrayList<Volunteer> allVolunteers = (ArrayList<Volunteer>) request.getAttribute("allVolunteers");
				for(Volunteer v: allVolunteers){	%>
					<option value="<%= v.getId()%>"><%= v.getName() + "(" + v.getBitsID() + ")"%></option><%
				}
				%>
			</select>
			<input type="submit"/>
		</form>
	</div>
</body>
</html>