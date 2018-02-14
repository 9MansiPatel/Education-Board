package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class CheckCourseContraintServlet
 */
@WebServlet("/CheckCourseContraintServlet")
public class CheckCourseContraintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCourseContraintServlet() {
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
			System.out.println("null");
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
		      rd.include(request,response);  
		}
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();
		//HttpSession session = request.getSession(false);
		String s=session.getAttribute("name").toString();
		String course= request.getParameter("addCourse");
		System.out.println(s);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
			Statement st=con.createStatement();
			System.out.println("innn");
			st = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("select * from course where User_id='"+s+"'");
			System.out.println("query");
			rs.last();
			System.out.println(rs.getRow());
			if(rs.getRow()>=3)
			{    
				out.println("Sorry! You have 3 Courses, You cannot add more..!!");
			    System.out.println("LIMIT");
			    RequestDispatcher rd= request.getRequestDispatcher("WelcomeFaculty.jsp");
			    rd.forward(request,response);
			}
			else{
				request.setAttribute("course",course);
				System.out.println("add");
		    	RequestDispatcher rd= request.getRequestDispatcher("AddCourseServlet");
			    rd.forward(request,response);
			}
		}
		catch(Exception e){
			
		}
	}

}
