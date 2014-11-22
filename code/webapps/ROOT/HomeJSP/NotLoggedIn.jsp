<%@ page import="java.util.*,nirmaanam.Vertical" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Home"%></title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>
<h2>Nirmaan Activity Manager</h2>
<div class="main">
<a href="/Volunteer/Login">Login</a> / <a href="/Volunteer/Create">Register</a> 
<h3>Verticals:</h3>
<%
	ArrayList<Vertical> vList = (ArrayList<Vertical>) request.getAttribute("verticalList");
	for(Vertical v: vList){%>
		<p><a href="Vertical/<%=v.getId()%>"><b><%= v.getName()%></b></a>:<%= v.getDescription()%></p><%
	}
%>
</div>
</body>
</html>