<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Event,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title>${volunteer.getName()}</title>
</head>
<body>

<h2>${volunteer.getName()}</h2>
<p>${volunteer.getBitsID()}</p>
<p>${volunteer.getVertical().getName()}</p>
<%
	//Do something
%>
</body>
</html>