<%@ page import="java.util.*,nirmaanam.Volunteer,servlets.Utility" %>
<!DOCTYPE html>
<html>
<head>
<title>${volunteer.getName()}</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>
<h2>${volunteer.getName()}</h2>
<table>
<tr>
	<td><img src="https://10.10.10.120/css/studentImg/${volunteer.getBitsID().substring(0,volunteer.getBitsID().length()-1)}.jpg" class="idpic"/> </td>
	<td>
		
		<p>${volunteer.getBitsID()}</p>
		<p><a href="/Vertical/${volunteer.getVertical().getId()}">${volunteer.getVertical().getName()}</a></p>
	</td>
</tr>
</table>
<%
	//Do something
%>
</body>
</html>