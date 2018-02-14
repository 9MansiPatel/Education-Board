<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Material</title>
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
	<form action="<%=request.getContextPath() %>/UploadMaterialServlet" method="post" enctype="multipart/form-data">
	Browse File: <input type="file" name="filepath" /><br/><br/>
	Course Name:<input type="text" name="coursename"/><br/><br/>
	Content Name:<input type="text" name="contentname"/><br/><br/>
	<input type="submit" value="Upload"/>  
	<input type="reset"/>
	</form>

</center>
</body>
</html>