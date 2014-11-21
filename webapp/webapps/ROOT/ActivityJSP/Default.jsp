<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Event" %>
<!DOCTYPE html>
<html>
<head>
<title>${activity.getEvent().getName()} &gt; ${activity.getName()}</title>
</head>
<body>

<h2>${activity.getName()}</h2>
<p>${activity.getDescription()}</p>
<p>(<b>Event:</b><a href="/Event/${activity.getEvent().getId()}">${activity.getEvent().getName()}</a>)</p>
<h3>Report:</h3>
<p>${activity.getReport()}</p>

<a href="/Activity/${activity.getId()}/PostReport">Update Report</a>
</body>
</html>