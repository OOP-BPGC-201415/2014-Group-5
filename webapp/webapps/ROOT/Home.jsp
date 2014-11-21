<%@ page import="java.util.*,nirmaanam.Vertical" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Home"%></title>
</head>
<body>
<h2>Nirmaan Activity Manager</h2>
<h3>Verticals:</h3>
<%
	ArrayList<Vertical> vList = (ArrayList<Vertical>) request.getAttribute("verticalList");
	for(Vertical v: vList){%>
		<p><a href="Vertical/<%=v.getId()%>"><b><%= v.getName()%></b></a>:<%= v.getDescription()%></p><%
	}

%>
</body>
</html>