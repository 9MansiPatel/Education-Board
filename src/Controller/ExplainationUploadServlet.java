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
 * Servlet implementation class ExplainationUploadServlet
 */
@WebServlet("/ExplainationUploadServlet")
public class ExplainationUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExplainationUploadServlet() {
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
		String course= request.getParameter("coursename");
		String explaination= request.getParameter("explaination");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
			Statement st=con.createStatement();
			
			
			st = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("select * from explaination where Course_name='"+course+"'");
			System.out.println("query");
			rs.last();
			System.out.println(rs.getRow());
			if(rs.getRow()<1)
			{    
				
				st.executeUpdate("INSERT INTO explaination(User_id, Course_name, Explaination) VALUES ('"+s+"','"+course+"','"+explaination+"') ");
				st.close();
				con.close();
				status="True";
			}
			else{
				out.println("Sorry! You already have content...");
			}
			
			if(status.equals("True")){
				System.out.println("..............");
				out.print("Upload Successfull..!!");
				RequestDispatcher rd = request.getRequestDispatcher("WelcomeFaculty.jsp");
    			rd.forward(request, response);
			}
			else{
				out.print("Upload Failed..!!");
				RequestDispatcher rd = request.getRequestDispatcher("ExplainationUpload.jsp");
				rd.forward(request, response);
			}
		}
		catch(Exception e){
			
		}
	}

}
