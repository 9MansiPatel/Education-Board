package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
		try{
			String s1=request.getParameter("firstName");
			String s2=request.getParameter("lastName");
			String s3=request.getParameter("userName");
			String s4=request.getParameter("userPassword");
			String s5=request.getParameter("userPassword1");
			String s6=request.getParameter("birthdate");
			String s7=request.getParameter("email");
			Long s8=Long.parseLong(request.getParameter("phoneNumber"));
			String s9=request.getParameter("userType");
			String user_id_status;
			String password_status;
			response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();
			System.out.println(s1 +" " +s2 +" " +s3 +" " +s4 +" " +s5 +" " +s6 +" " +s7 +" " +s8 +" " +s9);
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
			Statement st=con.createStatement();
			System.out.println("2");
			
			//Checks if if this user_id is exist or not.
			//String query="select * from registration where User_id=" +s3+";"; 
			PreparedStatement pst = con.prepareStatement("select * from registration where User_id = ?");
			//ResultSet rs=st.executeQuery(query);
			pst.setString(1, s3);
			ResultSet r1=pst.executeQuery();
			if(r1.next())
			{
			  user_id_status= "True";
			  out.print("Sorry username already exist..");  
		      RequestDispatcher rd=request.getRequestDispatcher("Index.jsp");  
		      rd.include(request,response);  
			}
			else
			{
				user_id_status="False";
			}
			//Checks if both passwords matches or not.
			if(s4.equals(s5))
			{
				password_status="True";
				
			}
			else
			{
				password_status="False";
				out.print("Sorry Passwords do not match...");  
			    RequestDispatcher rd=request.getRequestDispatcher("Index.jsp");  
			    rd.include(request,response);  
			}
			
			System.out.println(user_id_status);
			System.out.println(password_status);
			if(user_id_status.equals("False") && password_status.equals("True") )
			{
				st.executeUpdate("INSERT INTO registration(First_Name,Last_Name,User_id,Password,Confirm_Password,Birthdate,E_mail,Phone_number,userType) VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"') ");
				System.out.println(s1 +" " +s2 +" " +s3 +" " +s4 +" " +s5 +" " +s6 +" " +s7 +" " +s8 +" " +s9);
			}
			st.close();
			con.close();
			
			}
			catch(Exception e){
				
				
				
			}
		response.sendRedirect("Login.jsp");
	}

}
