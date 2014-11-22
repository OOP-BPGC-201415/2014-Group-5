<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Volunteer,servlets.Utility" %>
<!DOCTYPE html>
<html>
<head>
<title>${vertical.getName()} : Volunteers</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>

<h2>${vertical.getName()}</h2>
<p>${vertical.getDescription()}</p>
<div class="main">
<%
	ArrayList<Volunteer> volunteerList = (ArrayList<Volunteer>)request.getAttribute("volunteerList");		
	 
	if( volunteerList.size() ==0){ %>
		<h3>No volunteers found</h3> <%
	}else{
	 %>
	<h3>Volunteers:</h3>
	<table>
		<%	
		for(Volunteer v: volunteerList){	%>
			<tr>
				<td>
					<img src="<%= Utility.getProfileImage(v) %>" class="idpic"/>
				</td>
				<td>
					<a href="/Volunteer/<%= v.getId()%>"><%= v.getName() %></b></a><br/> <%= v.getBitsID() %>
				</td><% 
		}
		%>
		</ul><%
	}
	%>
</div>
</body>
</html>