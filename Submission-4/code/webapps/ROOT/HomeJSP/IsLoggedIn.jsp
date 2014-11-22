<%@ page import="java.util.*,nirmaanam.Activity,nirmaanam.Meeting,servlets.Utility" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Home"%></title>
<link rel="stylesheet" type="text/css" href="/css/global.css">
</head>
<body>
<h2>Nirmaan Activity Manager</h2>
<h3>Welcome, ${loggedInVolunteer.getName()}</h3>
<div class="main">
	<h3><a href="/Vertical/${loggedInVolunteer.getVertical().getId()}">${loggedInVolunteer.getVertical().getName()}</a></h3> 
		
	<%! int timeNow = Utility.TimeNow(); %>
	<h4>Your activities:</h4>
	<% 
		ArrayList<Activity> aList = (ArrayList<Activity>) request.getAttribute("activityList");
		if( aList.size()==0){ %>
			<p>You are not involved in any activities</p><%
		}
		else{ %>
		<table>
		
		<%
			
			for(Activity a: aList){%>
				<tr class="<%= (a.getTime()>timeNow)?"upcoming":"past"%>">
					<td>
						<a href="/Activity/<%=a.getId()%>"><b><%= a.getName()%></b></a><br/>
						( <%= Utility.TimeStampToStr(a.getTime()) %> ) 
					</td>
					<td><%= a.getDescription()%></td>
				</tr><%
			}
		%>
		</table><%
		}
	%>	
	
	
	<h4>Your Meetings:</h4>
	<% 
		ArrayList<Meeting> mList= (ArrayList<Meeting>) request.getAttribute("meetingList");
		if( mList.size()==0){ %>
			<p>You are not involved in any meetings</p><%
		}
		else{ %>
		<table>
		<%
			
			for(Meeting m:mList){%>
				<tr class="<%= (m.getTime()>timeNow)?"upcoming":"past"%>">
					<td>
						<a href="/Meeting/<%=m.getId()%>">
							<b><%= Utility.TimeStampToStr(m.getTime()) %> @ <%= m.getLocation()%></b>
						</a>
					</td>
					<td><%= m.getPurpose()%></td>
				</tr><%
			}
		%>
		</table><%
		}
	%>	

</div>
<span style="margin:auto;align:center;"><a href="/Vertical/Create">Create Vertical</a> | <a href="/Meeting/Create">Schedule Meeting</a> | <a href="/Volunteer/Logout">Logout</a></span>
</body>
</html>