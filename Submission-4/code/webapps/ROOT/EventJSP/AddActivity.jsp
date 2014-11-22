<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title>${event.getName()}: Add Activity</title>
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
	<form action="/Event/${event.getId()}/AddActivity" method="post">
	<table>
		<tr><td>Name:</td><td>	<input name="name" placeholder="name"/>	</td></tr>
		<tr><td>Description:</td><td>	<input name="description" placeholder="description"/>			</td></tr>
		<tr><td>date:</td><td>	<input name="date" placeholder="DD/MM/YYYY"/>			</td></tr>
		<tr><td>time:</td><td>	<input name="time" placeholder="hh:mm"/>			</td></tr>
		<tr><td>Head:</td>
			<td>	<select name="head">
				<% ArrayList<Volunteer> allVolunteers = (ArrayList<Volunteer>) request.getAttribute("allVolunteers");
				for(Volunteer v: allVolunteers){	%>
					<option value="<%= v.getId()%>"><%= v.getName() + "(" + v.getBitsID() + ")"%></option><%
				}
				%>
			</select>			
			</td>
		</tr>
	</table>
		<input type="submit" value="Add Activity"/>
	</form>
</div>
</body>
</html>