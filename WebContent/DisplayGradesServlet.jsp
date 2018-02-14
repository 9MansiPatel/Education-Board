<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Grades</title>
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
	<%
		String s=session.getAttribute("name").toString();
		//String s1=(String)request.getAttribute("col");
		
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
        	String sql = "SELECT * FROM grades where User_id='"+s+"'";
        	System.out.println(sql);
	        PreparedStatement statement = conn.prepareStatement(sql);
	        out.print("<table width=25% border='1'>");
	        out.print("<caption>:Grades:</caption>");
	        ResultSet result1 = statement.executeQuery();
	        if(result1.next()){
	        	ResultSet result = statement.executeQuery();
	        	while (result.next()) {
	       			out.print("<tr><td>"+result.getString(2)+"</td><td>"+result.getString(3)+"</td><td>"+result.getString(4)+"</td><td>"+result.getString(5)+"</td></tr>");
	       		}%>
				<form action="<%=request.getContextPath() %>/DisplaySortedGrades.jsp" method="post">
				Course Name: <input type="text" name="course" placeholder="Enter Course Name"/> <br/>
	        	Sort Data by :  
				<select name="col">  
				<option value="First_name">First Name</option>  
				<option value="Last_name">Last Name</option>
				<option value="Course_name">Course</option>
				<option value="Grade">Grade</option>    
				</select> <br/> 
				Sort Manner:
				<select name="sort">  
				<option value="ASC">Ascending</option>  
				<option value="DESC">Descending</option>
				</select>
				<br/><br/>  
				<input type="submit" value="Sort"/>
				</form>
				<%
	        }
	        else{
	        	out.print("No Record");
	        }
		}
		catch(Exception e){
			
		}
	
		%>
		</center>
</body>
</html>