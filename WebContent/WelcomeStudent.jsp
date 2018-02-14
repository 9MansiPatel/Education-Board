<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Student</title>
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
	<form action="<%=request.getContextPath() %>/StudentDisplayAvailableCourses" method="post"> 
		<br/><input type="submit" value="View Available Courses"> 
	</form>
	<form action="<%=request.getContextPath() %>/StudentDisplayCourseCatalog" method="post"> 
		<br/><input type="submit" value="View Course Catalog"> 
	</form>
	<form action="<%=request.getContextPath() %>/StudentDisplayGrades.jsp" method="post"> 
		<br/><input type="submit" value="View Grades"> 
	</form>
</center>
</body>
</html>