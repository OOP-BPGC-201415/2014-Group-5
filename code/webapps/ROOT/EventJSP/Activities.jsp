<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Event,nirmaanam.Activity" %>
<!DOCTYPE html>
<html>
<head>
<title>Event: ${event.getName()}</title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>

<h2>${event.getName()}</h2>
<p>${event.getDescription()}</p>
<p>(${event.getVertical().getName()})</p>
<div class="main">
<h3>Activities</h3>
<ul>
<%
	ArrayList<Activity> activityList= (ArrayList<Activity>) request.getAttribute("activityList");
	if(activityList.size()==0){
		out.println("No activities found");
	}
	else{
		for(Activity a: activityList){	%>
			<li><a href="/Activity/<%=a.getId()%>"><%=a.getName()%></a></li><%
		}
	}
%>
</ul>
</div>
</body>
</html>