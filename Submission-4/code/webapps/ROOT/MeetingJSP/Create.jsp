<%@ page import="java.util.*,nirmaanam.Meeting,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title>Schedule Meeting</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>

<% if(request.getAttribute("error")!=null){
		out.println((String)request.getAttribute("error"));
		Exception e = (Exception)(request.getAttribute("exception"));
		e.printStackTrace(new java.io.PrintWriter(out));
		out.println("Error: "+(String)request.getAttribute("errorMessage"));
	}
	//else		out.println("No error");
%>
<div class="main">
	<form action="/Meeting/Create" method="post">
	<table>
		<tr><td>Location:</td><td>	<input name="location" placeholder="location"/>	</td></tr>
		
		<tr><td>date</td><td>	<input name="date" placeholder="DD/MM/YYYY"/>			</td></tr>
		<tr><td>time: ( 24hr format )</td><td>	<input name="time" placeholder="hh:mm"/>	</td></tr>
		
	</table>
	Purpose:<br/>
	<textarea name="purpose"></textarea>
		<input type="submit" value="Schedule Meeting"/>
	</form>
</div>
</body>
</html>