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
 * Servlet implementation class StudentCheckAnswerServlet
 */
@WebServlet("/StudentCheckAnswerServlet")
public class StudentCheckAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentCheckAnswerServlet() {
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
		String sta = "False";
		if (session.getAttribute("name") == null){
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
		      rd.include(request,response);  
		}
		//String status="False";
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		//HttpSession session = request.getSession(false);
		String s=session.getAttribute("name").toString();
		String aname=request.getParameter("a_name");
		String course=request.getParameter("course");
		
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
	
	        // queries the database
	        String sql = "SELECT * FROM assignment WHERE Assignment_name = ?";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, aname);
	        String s_answer1[]= request.getParameterValues("o1");
	        String s_answer2[]= request.getParameterValues("o2");
	        System.out.println(s_answer1);
	        System.out.println(s_answer2);
	        if(s_answer1 == null || s_answer2 == null){
	        	out.println(" You had submitted Blank Answer/s..!!");
	        }
	        int s1 = 0;
	        int s2 = 0;
	        String sj_answer1 = String.join(",", s_answer1);
	        String sj_answer2 = String.join(",", s_answer2);
	        System.out.println(sj_answer1);
	        System.out.println(sj_answer2);
	        ResultSet result = statement.executeQuery();
	        if (result.next()) {
	        	if(sj_answer1.equals(result.getString(10))){
	        		System.out.println("YES1");
	        		s1 = 10;
	        	}
	        	if(sj_answer2.equals(result.getString(16))){
	        		System.out.println("YES2");
	        		s2 = 10;
	        	}
	        	
	        }        
	        
        int grade = s1 + s2;
        Statement st=conn.createStatement();
        String sql1 = ("INSERT INTO student_assignments(User_id,Student_answer1,Student_answer2,Course_name,Assignment_name,Student_grade) VALUES ('"+s+"','"+sj_answer1+"','"+sj_answer2+"','"+course+"','"+aname+"','"+grade+"') ");
        st.executeUpdate(sql1);
        System.out.println("Success");
        sta = "True";
	        
        
        String sql3 = "SELECT * FROM student_assignments WHERE Course_name = ?";
        PreparedStatement statement3 = conn.prepareStatement(sql3);
        statement3.setString(1, course);
        System.out.println("hereeeee");
        ResultSet result3 = statement3.executeQuery();
        int course_total = 0;
        if (result3.next()) {
        	System.out.println("in");
        	System.out.println(course_total);
        	course_total = course_total + Integer.parseInt(result3.getString(7));
        	System.out.println("out");
        	System.out.println(course_total);
        }
        System.out.println(course_total);
        
        
        String sql2 = "Update student_courses set Grades = ? WHERE Course_name = ?";
        PreparedStatement statement2 = conn.prepareStatement(sql2);
        statement2.setString(1, Integer.toString(course_total));
        statement2.setString(2, course);
        statement2.executeUpdate();
        System.out.println("final");
	                               
		}
		catch(Exception e){
			
		}
		if(sta.equals("True")){
			
			out.print("Submitted Successfully");
			RequestDispatcher rd = request.getRequestDispatcher("WelcomeStudent.jsp");
        	rd.include(request, response);
        	
			
		}
		else{
			out.print("Not Submitted Successfully");
			RequestDispatcher rd = request.getRequestDispatcher("WelcomeStudent.jsp");
        	rd.include(request, response);
			
		}
		
		
}
		
		

}
