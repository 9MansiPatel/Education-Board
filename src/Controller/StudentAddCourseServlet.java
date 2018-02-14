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
 * Servlet implementation class StudentAddCourseServlet
 */
@WebServlet("/StudentAddCourseServlet")
public class StudentAddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAddCourseServlet() {
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
		try{
			String s1=(String)request.getParameter("courseToAdd");
			String course_status;
			//HttpSession session = request.getSession(false);
			String s=session.getAttribute("name").toString();
			response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();
		    Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
			Statement st=con.createStatement();
			
			System.out.println(s);
			//Checks if course already exist or not
			PreparedStatement pst = con.prepareStatement("select * from student_courses where Course_name = ? and User_id = ?");
			pst.setString(1, s1);
			pst.setString(2, s);
			ResultSet r1=pst.executeQuery();
			System.out.println(s);
			if(r1.next())
			{
			  course_status= "True";
			  out.print("Sorry Course already exist..");  
		      RequestDispatcher rd=request.getRequestDispatcher("WelcomeStudent.jsp");  
		      rd.include(request,response);  
			}
			else
			{
				course_status="False";
			}
			System.out.println(s);
			System.out.println(course_status);
			if(course_status.equals("False"))
			{
				st.executeUpdate("INSERT INTO student_courses(User_id,Course_name) VALUES ('"+s+"','"+s1+"')");
				System.out.println(s +" " +s1 );
				out.print("Course added...");  
			    RequestDispatcher rd=request.getRequestDispatcher("WelcomeStudent.jsp");  
			    rd.include(request,response);  
			}
			else
			{
				out.print("Sorry, something went wrong... TRY AGAIN!");  
			    RequestDispatcher rd=request.getRequestDispatcher("WelcomeStudent.jsp");  
			    rd.include(request,response);
			}
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			
		}
		//out.print("Added");
		//response.sendRedirect("WelcomeStudent.jsp");

	}

}
