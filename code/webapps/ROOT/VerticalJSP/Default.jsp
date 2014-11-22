<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title>${vertical.getName()}</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>

<h2>${vertical.getName()}</h2>
<p>${vertical.getDescription()}</p>
<div class="main">
	<ul>
		<li><a href="/Vertical/${vertical.getId()}/Events">Events</a></li>
		<li><a href="/Vertical/${vertical.getId()}/Volunteers">Volunteers</a></li>
	</ul>
</div>
</body>
</html>