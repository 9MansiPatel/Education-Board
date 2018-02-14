<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<center>
<%
	
	if (session.getAttribute("name") == null){%>
		<jsp:forward page="Login.jsp" /><% 
	}%>
	<form action="<%=request.getContextPath() %>/LogoutServlet" method="post"> 
		<br/><input type="submit" value="Logout" > 
	</form>
	<form action="WelcomeFaculty.jsp" method="post" style="padding: 0; margin: 0">
		<input type="submit" value="Back">
	</form>
	<form action="<%=request.getContextPath() %>/CreateAssignmentServlet" method="post" id="usrform">
	Course Name:<input type="text" name="course" placeholder="Enter Course Name" required="required"/><br/><br/>
	Assignment Name:<input type="text" name="aname" placeholder="Enter Assignment Name" required="required"/><br/><br/>
	<input type="submit">
	</form>
	<br>
	Question1:
	<textarea rows="5" cols="50" name="question1" required="required" form="usrform">Enter text here...</textarea><br/>
	Option1.1:
	<textarea rows="3" cols="50" name="answer11" required="required" form="usrform">Enter text here...</textarea><br/>
	Option1.2:
	<textarea rows="3" cols="50" name="answer12" required="required" form="usrform">Enter text here...</textarea><br/>
	Option1.3:
	<textarea rows="3" cols="50" name="answer13" required="required" form="usrform">Enter text here...</textarea><br/>
	Option1.4:
	<textarea rows="3" cols="50" name="answer14" required="required" form="usrform">Enter text here...</textarea><br/>
	Correct Answer1:
	<textarea rows="3" cols="50" name="answer1" required="required" form="usrform">Enter text here...</textarea><br/>
	<br/>
	<br/>
	Question2:
	<textarea rows="5" cols="50" name="question2" required="required" form="usrform">Enter text here...</textarea><br/>
	Option2.1:
	<textarea rows="3" cols="50" name="answer21" required="required" form="usrform">Enter text here...</textarea><br/>
	Option2.2:
	<textarea rows="3" cols="50" name="answer22" required="required" form="usrform">Enter text here...</textarea><br/>
	Option2.3:
	<textarea rows="3" cols="50" name="answer23" required="required" form="usrform">Enter text here...</textarea><br/>
	Option2.4:
	<textarea rows="3" cols="50" name="answer24" required="required" form="usrform">Enter text here...</textarea><br/>
	Correct Answer2:
	<textarea rows="3" cols="50" name="answer2"  required="required" form="usrform">Enter text here...</textarea><br/>
</center>	
</body>
</html>