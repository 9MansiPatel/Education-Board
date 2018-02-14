<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Faculty</title>
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
	<form action="<%=request.getContextPath() %>/CheckCourseContraintServlet" method="post">
		<br/><input type="text" name="addCourse" placeholder="Enter Course Name"> 
		<input type="submit" value="Add Course"> 
	</form>
	<form action="<%=request.getContextPath() %>/DisplayCoursesServlet" method="post"> 
		<br/><input type="submit" value="Display Courses"> 
	</form>
	<form action="<%=request.getContextPath() %>/UploadMaterial.jsp" method="post"> 
		<br/><input type="submit" value="Upload Material"> 
	</form>
	<form action="<%=request.getContextPath() %>/DisplayMaterialServlet" method="post"> 
		<br/><input type="submit" value="Material"> 
	</form>
<%-- 	<form action="<%=request.getContextPath() %>/DownloadMaterial.jsp" method="post">  --%>
<!-- 		<br/><input type="submit" value="Download Material">  -->
<!-- 	</form> -->
	<form action="<%=request.getContextPath() %>/CreateAssignment.jsp" method="post"> 
		<br/><input type="submit" value="Create Assignment"> 
	</form>
	<form action="<%=request.getContextPath() %>/DisplayAssignment.jsp" method="post"> 
		<br/><input type="submit" value="Display Assignment"> 
	</form>
	<form action="<%=request.getContextPath() %>/ExplainationUpload.jsp" method="post"> 
		<br/><input type="submit" value="Upload Explaination"> 
	</form>
	<form action="<%=request.getContextPath() %>/DisplayExplaination.jsp" method="post"> 
		<br/><input type="submit" value="Display Explaination"> 
	</form>
	<form action="<%=request.getContextPath() %>/DiscussionBoard1.jsp" method="post"> 
		<br/><input type="submit" value="Discussion Board"> 
	</form>
	<form action="<%=request.getContextPath() %>/DisplayGradesServlet.jsp" method="post"> 
		<br/><input type="submit" value="Display Grades"> 
	</form>
	</center>
</body>
</html>
