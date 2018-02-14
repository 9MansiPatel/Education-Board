<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<center>
<form action="<%=request.getContextPath() %>/RegistrationServlet" method="post">  
 
First Name:<input type="text" name="firstName" placeholder="Enter First Name" required="required"/><br/><br/>
Last Name:<input type="text" name="lastName" placeholder="Enter Last Name" required="required"/><br/><br/>
User id:<input type="text" name="userName" placeholder="Enter Username" required="required"/><br/><br/>  
Password:<input type="password" name="userPassword" placeholder="Enter Password" required="required"/><br/><br/>  
Confirm Password:<input type="password" name="userPassword1" placeholder="Enter same Password" required="required"/><br/><br/>
Birth-date:<input type="date" name="birthdate" placeholder="DD--MON--YEAR" required="required"><br/><br/>
Email Id:<input type="text" name="email" placeholder="abc@xyz.com" required="required"/><br/><br/>
Phone number:<input type="text" name="phoneNumber" required="required"><br/><br/>
User:  
<select name="userType">  
<option value="Student">Student</option>  
<option value="Faculty">Faculty</option>    
</select>  
  
<br/><br/>  
<input type="submit" value="register"/>  
<input type="reset"/>
</form>
<form action="<%=request.getContextPath() %>/Login.jsp" method="post"> 
		<br/><input type="submit" value="Login"> 
	</form>
</center>  
</body>
</html>