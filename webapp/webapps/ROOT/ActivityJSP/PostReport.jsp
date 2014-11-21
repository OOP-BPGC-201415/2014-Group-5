<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Activity,servlets.Utility" %>
<!DOCTYPE html>
<html>
<head>
<title>Post Report: ${activity.getEvent().getName()} &gt; ${activity.getName()}</title>
</head>
<body>

<% if(request.getAttribute("error")!=null){
		out.println("Error");
		out.println("Error: "+(String)request.getAttribute("errorMessage"));
	}
	//else		out.println("No error");
%>
<h2>${activity.getName()}</h2>
<p>${activity.getDescription()}</p>
<form action="" method="post">
	<textarea name="report" width="80%" height="80px" placeholder="report">
	<%
	Activity a = ((Activity)request.getAttribute("activity"));
	String escaped = Utility.escape( a.getReport());
	out.println(escaped);
 %>
	</textarea>
	<input type="submit"/>
</form>
</body>
</html>