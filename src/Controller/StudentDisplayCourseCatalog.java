package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentDisplayCourseCatalog
 */
@WebServlet("/StudentDisplayCourseCatalog")
public class StudentDisplayCourseCatalog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDisplayCourseCatalog() {
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
		
		String status="False";
		HttpSession session = request.getSession(false);
		if (session.getAttribute("name") == null){
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
		      rd.include(request,response);  
		}
		try{
			
			String s=session.getAttribute("name").toString();
			response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();
		    Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
			//Statement st=con.createStatement();
			
			System.out.println(s);
			
			String sql = "SELECT * FROM student_courses WHERE User_id= ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, s);
	        
	        out.print("<table width=50% border=1 align='center'>");  
            out.print("<caption> -------Course Catalog------</caption>");  
              
            ResultSet rs=statement.executeQuery();
            out.println("<tr><td>Course_name</td><td>Click</td><td>Click</td></tr>");
            while(rs.next()){
            	
            	out.print("<tr><td>"+rs.getString(3)+"</td><td><form action='StudentDisplayParticularCourseServlet.jsp' method='post'><input type='hidden' name='course' value='"+rs.getString(3)+"'><input type='submit' value='View Course'></form></td><td><form action='StudentCourseDrop' method='post'><input type='hidden' name='course' value='"+rs.getString(3)+"'><input type='submit' value='Drop Course'></form></td></tr>");
            }  
            out.print("</table>");
            out.print("<form action='WelcomeStudent.jsp' method='post'><input type='submit' value='Back' align='center'></form>");
            out.print("<form action='LogoutServlet' method='post'><br/><input type='submit' align='center' value='Logout'></form>");
	        status = "True";
			statement.close();
			con.close();
			if(status.equals("False")){
				out.print("Sorry, something went wrong... TRY AGAIN!");  
			    RequestDispatcher rd=request.getRequestDispatcher("WelcomeStudent.jsp");  
			    rd.include(request,response);
			}
		}	
		catch(Exception e)
		{
			
		}
	}
}
