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
 * Servlet implementation class DisplayExplainationServlet
 */
@WebServlet("/DisplayExplainationServlet")
public class DisplayExplainationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayExplainationServlet() {
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
		String course=request.getParameter("course");
		System.out.println(s);
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
	
	        // queries the database
	        String sql = "SELECT * FROM explaination WHERE Course_name = ? and User_id= ?";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, course);
	        statement.setString(2, s);
	        out.print("<table  width=35% border='1' align='center'>");
	        out.print("<caption>Course name: "+course+"</caption>");
	        ResultSet result = statement.executeQuery();
	        if (result.next()) {
	        	out.print("<tr><td>"+result.getString(4)+"</td></tr>");
	        	status="True";
	        }
	        out.print("</table>");
	        out.print("<form action='WelcomeFaculty.jsp' method='post'><input type='submit' value='Back' align='center'></form>");
            out.print("<form action='<LogoutServlet' method='post'><br/><input type='submit' align='center' value='Logout'></form>");
		}
		catch(Exception e)
		{
			
		}
		if(status.equals("False")){
			System.out.println("No Assignmnet");  
            out.print("No Explaination yet, for the course name '"+course+"' OR it might be not your course..!!");
           
		}
	}
}
