<%@ page import="java.util.*,nirmaanam.Activity" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Home"%></title>
</head>
<body>
<h2>Nirmaan Activity Manager</h2>
<h3>Your activities:</h3>
<%
	ArrayList<Activity> aList = (ArrayList<Activity>) request.getAttribute("activityList");
	for(Activity a: aList){%>
		<p><a href="Activity/<%=a.getId()%>"><b><%= a.getName()%></b></a>:<%= a.getDescription()%></p><%
	}
%>
</body>
</html>