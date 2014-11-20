<%@ page import="java.util.*,nirmaanam.Vertical" %>
<!DOCTYPE html>
<html>
<head>
<title><%="Home"%></title>
</head>
<body>
Found: ${verticalList.size()}
<%
	ArrayList<Vertical> vList = (ArrayList<Vertical>) request.getAttribute("verticalList");
	for(Vertical v: vList){%>
		<p><b><%= v.getName()%></b>:<%= v.getDescription()%></p><%
	}

%>
</body>
</html>