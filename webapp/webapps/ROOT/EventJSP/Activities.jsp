<%@ page import="java.util.*,nirmaanam.Vertical,nirmaanam.Event,nirmaanam.Activity" %>
<!DOCTYPE html>
<html>
<head>
<title>Event: ${event.getName()}</title>
</head>
<body>

<h2>${event.getName()}</h2>
<p>${event.getDescription()}</p>
<p>(${event.getVertical().getName()})</p>
<h3>Activities</h3>
<%
	ArrayList<Activity> activityList= (ArrayList<Activity>) request.getAttribute("activityList");
	if(activityList.size()==0){
		out.println("No activities found");
	}
	else{
		for(Activity a: activityList){	%>
			<a href="/Activity/<%=a.getId()%>"><%=a.getName()%></a><%
		}
	}
%>
</body>
</html>