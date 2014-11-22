<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Event" %>
<!DOCTYPE html>
<html>
<head>
<title>Event: ${event.getName()}</title>
</head>
<body>

<h2>${event.getName()}</h2>
<p>${event.getDescription()}</p>
<p>(${event.getVertical().getName()})</p>
<p><a href="/Event/${event.getId()}/Activities">Activities</a></p>
</body>
</html>