<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Home"%></title>
</head>
<body>

<h2>${vertical.getName()}</h2>
<p>${vertical.getDescription()}</p>
<%
	ArrayList<Volunteer> volunteerList = (ArrayList<Volunteer>)request.getAttribute("volunteerList");		
	 
	if( volunteerList.size() ==0){ %>
		<h3>No volunteers found</h3> <%
	}else{
	 %>
	<h3>Volunteers:</h3>
		<ul>
		<%	
		for(Volunteer v: volunteerList){	%>
			<li><b><%= v.getName() %>:</b> <%= v.getBitsID() %></li> <% 
		}
		%>
		</ul><%
	}
	%>
</body>
</html>