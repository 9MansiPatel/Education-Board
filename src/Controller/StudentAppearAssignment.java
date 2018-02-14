package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentAppearAssignment
 */
@WebServlet("/StudentAppearAssignment")
public class StudentAppearAssignment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAppearAssignment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(false);
		if (session.getAttribute("name") == null){
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
		      rd.include(request,response);  
		}
		//String status="False";
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		//HttpSession session = request.getSession(false);
		//String s=session.getAttribute("name").toString();
		String aname=request.getParameter("material");
		String course=request.getParameter("course");
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
	
	        // queries the database
	        String sql = "SELECT * FROM assignment WHERE Assignment_name = ?";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, aname);
	        ArrayList<String> options1 = new ArrayList<String>();
	        ArrayList<String> options2 = new ArrayList<String>();
	        
	        String question1,question2;
	        ResultSet result = statement.executeQuery();
	        //out.print("<table border='1'>");
	        out.print("<center>Assignment name: "+aname+"</center>");
	        out.print("<form action='StudentCheckAnswerServlet' method='post'>");
	        out.print("<input type='hidden' name='a_name' value='"+aname+"'>");
	        if (result.next()) {
	        	question1 = result.getString(5);
	        	options1.add(result.getString(6));
	        	options1.add(result.getString(7));
	        	options1.add(result.getString(8));
	        	options1.add(result.getString(9));
	        	
	        	Collections.shuffle(options1);
	        	//out.print("<table border='1'>");
	        	out.print("<b>Question 1: "+question1+"</b>");
	        	out.print("<br/>");
	        	out.print("<br/>");
	        	out.print("option1: <input type='checkbox' name='o1' value='"+options1.get(0)+"'>"+options1.get(0));out.print("<br/>");
	        	out.print("option2: <input type='checkbox' name='o1' value='"+options1.get(1)+"'>"+options1.get(1));out.print("<br/>");
	        	out.print("option3: <input type='checkbox' name='o1' value='"+options1.get(2)+"'>"+options1.get(2));out.print("<br/>");
	        	out.print("option4: <input type='checkbox' name='o1' value='"+options1.get(3)+"'>"+options1.get(3));out.print("<br/>");
//	        	out.print("<tr><td>Option1"+"</td><td>"+result.getString(6)+"</td></tr>");
//	        	out.print("<tr><td>Option2"+"</td><td>"+result.getString(7)+"</td></tr>");
//	        	out.print("<tr><td>Option3"+"</td><td>"+result.getString(8)+"</td></tr>");
//	        	out.print("<tr><td>Option4"+"</td><td>"+result.getString(9)+"</td></tr>");
//	        	status="True";
//	        }
//	        ResultSet result1 = statement.executeQuery();
//	        if (result1.next()) {
	        	question2 = result.getString(11);
	        	options2.add(result.getString(12));
	        	options2.add(result.getString(13));
	        	options2.add(result.getString(14));
	        	options2.add(result.getString(15));
	        	
	        	Collections.shuffle(options2);
	        	
	        	out.print("<br/>");
	        	out.print("<br/>");
	        	out.print("<br/>");
	        	out.print("<b>Question 2: "+question2+"</b>");
	        	out.print("<br/>");
	        	out.print("<br/>");
	        	out.print("option1: <input type='checkbox' name='o2' value='"+options2.get(0)+"'>"+options2.get(0));out.print("<br/>");
	        	out.print("option2: <input type='checkbox' name='o2' value='"+options2.get(1)+"'>"+options2.get(1));out.print("<br/>");
	        	out.print("option3: <input type='checkbox' name='o2' value='"+options2.get(2)+"'>"+options2.get(2));out.print("<br/>");
	        	out.print("option4: <input type='checkbox' name='o2' value='"+options2.get(3)+"'>"+options2.get(3));out.print("<br/>");
//	        	out.print("<tr><td>Question1"+"</td><td>"+result1.getString(11)+"</td></tr>");
//	        	out.print("<tr><td>Option1"+"</td><td>"+result1.getString(12)+"</td></tr>");
//	        	out.print("<tr><td>Option2"+"</td><td>"+result1.getString(13)+"</td></tr>");
//	        	out.print("<tr><td>Option3"+"</td><td>"+result1.getString(14)+"</td></tr>");
//	        	out.print("<tr><td>Option4"+"</td><td>"+result1.getString(15)+"</td></tr>");
	        }
	        //out.print("</table>");
	        out.print("<input type='hidden' name='course' value='"+course+"'>");
	        out.print("<input type='submit' value='Submit'>");
	        out.print("</form>");
	        out.print("<form action='WelcomeStudent.jsp' method='post'><input type='submit' value='Back' align='center'></form>");
            out.print("<form action='LogoutServlet' method='post'><br/><input type='submit' align='center' value='Logout'></form>");
	        
		}
		catch(Exception e)
		{
			
		}

	}

}
