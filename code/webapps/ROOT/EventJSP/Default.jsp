<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Event" %>
<!DOCTYPE html>
<html>
<head>
<title>Event: ${event.getName()}</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>

<h2>${event.getName()}</h2>
<h3><a href="/Vertical/${event.getVertical().getId()}">(${event.getVertical().getName()})</a></h3>
<p>${event.getDescription()}</p>

<p><a href="/Event/${event.getId()}/Activities">Activities</a></p>
<p><a href="/Event/${event.getId()}/AddActivity">Add Activity</a></p>
</body>
</html>