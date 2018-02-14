<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Assignment</title>
</head>
<body>
<center>
	<%
	if (session.getAttribute("name") == null){%>
		<jsp:forward page="Login.jsp" /><% 
	}%>
	<form action="<%=request.getContextPath() %>/LogoutServlet" method="post"> 
		<br/><input type="submit" value="Logout"> 
	</form>
	<form action="WelcomeFaculty.jsp" method="post" style="padding: 0; margin: 0">
		<input type="submit" value="Back">
	</form>
	<form action="<%=request.getContextPath() %>/DisplayAssignmentServlet" method="post">
	Assignment Name:<input type="text" name="aname" placeholder="Enter Assignment Name" required="required"/><br/><br/>
	<input type="submit">
	</form>
	</center>
</body>
</html>