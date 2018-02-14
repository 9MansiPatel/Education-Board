package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateAssignmentServlet
 */
@WebServlet("/CreateAssignmentServlet")
public class CreateAssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAssignmentServlet() {
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
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String status = null;
		//HttpSession session = request.getSession(false);
		String s=session.getAttribute("name").toString();
		//String s="abc";
		System.out.println(s);
		String course= request.getParameter("course");
		String aname= request.getParameter("aname");
		String question1= request.getParameter("question1");
		String answer11= request.getParameter("answer11");
		String answer12= request.getParameter("answer12");
		String answer13= request.getParameter("answer13");
		String answer14= request.getParameter("answer14");
		String question2= request.getParameter("question2");
		String answer21= request.getParameter("answer21");
		String answer22= request.getParameter("answer22");
		String answer23= request.getParameter("answer23");
		String answer24= request.getParameter("answer24");
		String answer1= request.getParameter("answer1");
		String answer2= request.getParameter("answer2");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
			Statement st=con.createStatement();
			System.out.println("Strt");
			st.executeUpdate("INSERT INTO assignment(User_id, Course_name, Assignment_name, Question1, Option11, Option12, Option13, Option14, Answer1, Question2, Option21, Option22, Option23, Option24, Answer2) VALUES ('"+s+"','"+course+"','"+aname+"','"+question1+"','"+answer11+"','"+answer12+"','"+answer13+"','"+answer14+"','"+answer1+"','"+question2+"','"+answer21+"','"+answer22+"','"+answer23+"','"+answer24+"','"+answer2+"') ");
			
			System.out.println("Query");
			st.close();
			con.close();
			status="True";
			if(status.equals("True")){
				System.out.println("..............");
				out.print("Upload Successfull..!!");
				//RequestDispatcher rd = request.getRequestDispatcher("WelcomeFaculty.jsp");
    			//rd.forward(request, response);
			}
			else{
				out.print("Upload Failed..!!");
				RequestDispatcher rd = request.getRequestDispatcher("CreateAssignment.jsp");
				rd.forward(request, response);
			}
		}
		catch(Exception e){
			
		}
	}

}
