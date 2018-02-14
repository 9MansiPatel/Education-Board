package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayAssignmentServlet
 */
@WebServlet("/DisplayAssignmentServlet")
public class DisplayAssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAssignmentServlet() {
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
		String status="False";
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		//HttpSession session = request.getSession(false);
		String s=session.getAttribute("name").toString();
		String aname=request.getParameter("aname");
		
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
	
	        // queries the database
	        String sql = "SELECT * FROM assignment WHERE Assignment_name = ? and User_id= ?";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, aname);
	        statement.setString(2, s);
	       
	        ResultSet result = statement.executeQuery();
	        out.print("<table border='1'>");
	        out.print("<caption>Assignment name: "+aname+"</caption>");
	        if (result.next()) {
	        	out.print("<tr><td>Question1"+"</td><td>"+result.getString(5)+"</td></tr>");
	        	out.print("<tr><td>Option1"+"</td><td>"+result.getString(6)+"</td></tr>");
	        	out.print("<tr><td>Option2"+"</td><td>"+result.getString(7)+"</td></tr>");
	        	out.print("<tr><td>Option3"+"</td><td>"+result.getString(8)+"</td></tr>");
	        	out.print("<tr><td>Option4"+"</td><td>"+result.getString(9)+"</td></tr>");
	        	status="True";
	        }
	        ResultSet result1 = statement.executeQuery();
	        if (result1.next()) {
	        	out.print("<tr><td>Question1"+"</td><td>"+result1.getString(11)+"</td></tr>");
	        	out.print("<tr><td>Option1"+"</td><td>"+result1.getString(12)+"</td></tr>");
	        	out.print("<tr><td>Option2"+"</td><td>"+result1.getString(13)+"</td></tr>");
	        	out.print("<tr><td>Option3"+"</td><td>"+result1.getString(14)+"</td></tr>");
	        	out.print("<tr><td>Option4"+"</td><td>"+result1.getString(15)+"</td></tr>");
	        }
	        out.print("</table>");
	        
		}
		catch(Exception e)
		{
			
		}

		if(status.equals("False")){
			System.out.println("No Assignmnet");  
            out.print("No Assignmnet yet, for the content name '"+aname+"' under your courses..!!");
            out.print("<form action='WelcomeFaculty.jsp' method='post'><input type='submit' value='Back' align='center'></form>");
            out.print("<form action='LogoutServlet' method='post'><br/><input type='submit' align='center' value='Logout'></form>");
		}
		
	}

}
