package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentCourseDrop
 */
@WebServlet("/StudentCourseDrop")
public class StudentCourseDrop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentCourseDrop() {
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
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
		if (session.getAttribute("name") == null){
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
		      rd.include(request,response);  
		}
		try{
			String course=request.getParameter("course");
			String s=session.getAttribute("name").toString();
		    Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
			//Statement st=con.createStatement();
			
			System.out.println(s);
			
			String sql = "DELETE FROM student_courses WHERE User_id = ? and Course_name = ?";
			String sql1 = "DELETE FROM student_assignments WHERE User_id = ? and Course_name = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, s);
	        statement.setString(2, course);
	        statement.executeUpdate();
	        
	        System.out.println("Half");
	        
	        PreparedStatement statement1 = con.prepareStatement(sql1);
	        statement1.setString(1, s);
	        statement1.setString(2, course);
	        statement1.executeUpdate();
	        status = "True";
		}
		catch(Exception e){
			
		}
		if(status.equals("True")){
			out.println("Course Dropped Succesfully.!!");
			RequestDispatcher rd = request.getRequestDispatcher("WelcomeStudent.jsp");
        	rd.include(request, response);
		}
		else{
			out.println("Course Could not Dropped Succesfully.!!");
			RequestDispatcher rd = request.getRequestDispatcher("WelcomeStudent.jsp");
        	rd.include(request, response);
		}
	}

}
