<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Courses</title>
</head>
<body>

<center>
	<%
	if (session.getAttribute("name") == null){%>
		<jsp:forward page="Login.jsp" /><% 
	}%>
	<h3>Hello</h3>
	<form action="<%=request.getContextPath() %>/LogoutServlet" method="post"> 
		<br/><input type="submit" value="Logout"> 
	</form>
	
	<form action="<%=request.getContextPath() %>/WelcomeStudent.jsp" method="post"> 
		<br/><input type="submit" value="Back"> 
	</form>
	<form action="<%=request.getContextPath() %>/StudentDisplayCourseMaterialServlet" method="post">
		<input type='hidden' name='course' value=<%=request.getParameter("course")%>/> 
		<br/><input type="submit" value="Material"> 
	</form>
	<form action="<%=request.getContextPath() %>/StudentDisplayAssignment" method="post">
		<input type='hidden' name='course' value=<%=request.getParameter("course")%>/> 
		<br/><input type="submit" value="Assignment"> 
	</form>
	<form action="<%=request.getContextPath() %>/StudentDiscussionBoard.jsp" method="post">
		<input type='hidden' name='course' value=<%=request.getParameter("course")%>/> 
		<br/><input type="submit" value="Discussion Board"> 
	</form>
</center>

</body>
</html>