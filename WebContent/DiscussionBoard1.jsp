<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

	<%
	if (session.getAttribute("name") == null){%>
		<jsp:forward page="Login.jsp" /><% 
	}%>
	<center>
	<form action="<%=request.getContextPath() %>/LogoutServlet" method="post"> 
		<br/><input type="submit" value="Logout"> <br/>
	</form>
	<form action="WelcomeFaculty.jsp" method="post">
		<input type="submit" value="Back"><br/>
	</form>
	<form action="<%=request.getContextPath() %>/DiscussionBoardServlet" method="post" >
	Course Name:<input type="text" name="course" placeholder="Enter Course Name"/><br/><br/>
	<input type="text" name="comment" required="required"/>
	<input type="submit">
	</form>
</center>
</body>
</html>