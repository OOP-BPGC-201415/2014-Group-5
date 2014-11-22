<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Home"%></title>
</head>
<body>

<h2>${vertical.getName()}</h2>
<p>${vertical.getDescription()}</p>
<ul>
	<li><a href="/Vertical/${vertical.getId()}/Events">Events</a></li>
	<li><a href="/Vertical/${vertical.getId()}/Volunteers">Volunteers</a></li>
</ul>
</body>
</html>