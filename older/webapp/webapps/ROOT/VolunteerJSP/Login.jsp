<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Login"%></title>
</head>
<body>

<% if(request.getAttribute("error")!=null){
		out.println("Error");
		out.println("Error: "+(String)request.getAttribute("errorMessage"));
	}
	//else		out.println("No error");
%>
	<form action="/Volunteer/Login" method="post">
		<input name="email" placeholder="email"/>
		<input id="p1" type="password" name="password" placeholder="password"/>
		<input type="submit" onsubmit="function(){ alert(1);return false;if(document.getElementById(\"p1\").value!=document.getElementById(\"p2\")){alert(\"Passes don't match\");return false;}"/>
	</form>
</body>
</html>