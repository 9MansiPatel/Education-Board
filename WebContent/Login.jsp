<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<center>
	<%
	if (session.getAttribute("name") != null){%>
		You are already Logged in..!!
		<jsp:forward page="WelcomeFaculty.jsp" /><% 
	}%>

<form action="<%=request.getContextPath() %>/LoginServlet" method="post">  
User id:<input type="text" name="username" placeholder="Enter Username" required="required"/><br/><br/>  
Password:<input type="password" name="userpass" placeholder="Enter Password" required="required"/><br/><br/>
User:  
<select name="usertype">  
<option value="Student">Student</option>  
<option value="Faculty">Faculty</option>    
</select>  
  
<br/><br/>  
<input type="submit" value="Login"/>  
<input type="reset"/>
</form>
<form action="<%=request.getContextPath() %>/Index.jsp" method="post"> 
		<br/><input type="submit" value="Register"> 
	</form>
</center>  
</body>
</html>