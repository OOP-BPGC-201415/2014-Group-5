<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Volunteer" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Register Volunteer"%></title>
</head>
<body>

<% if(request.getAttribute("error")!=null){
		out.println("Error");
		out.println("Error: "+(String)request.getAttribute("errorMessage"));
	}
	//else		out.println("No error");
%>
	<form action="/Volunteer/Create" method="post">
		<input name="name" placeholder="name"/>
		<input name="bitsID" placeholder="bitsID"/>
		<input name="year" placeholder="year"/>
		<input name="email" placeholder="email"/>
		<input id="p1" type="password" name="password" placeholder="password"/>
		<input id="p2" type="password" name="confirmPassword" placeholder="password"/>
		
		<select name="vertical">
			<% ArrayList<Vertical> allVerticals = (ArrayList<Vertical>) request.getAttribute("allVerticals");
			for(Vertical v: allVerticals){	%>
				<option value="<%= v.getId()%>"><%= v.getName() %></option><%
			}
			%>
		</select>
		<input type="submit" onsubmit="function(){ alert(1);return false;if(document.getElementById(\"p1\").value!=document.getElementById(\"p2\")){alert(\"Passes don't match\");return false;}"/>
	</form>
</body>
</html>