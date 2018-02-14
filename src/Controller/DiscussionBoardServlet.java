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

import com.mysql.jdbc.PreparedStatement;


/**
 * Servlet implementation class DiscussionBoardServlet
 */
@WebServlet("/DiscussionBoardServlet")
public class DiscussionBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiscussionBoardServlet() {
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
		String s1=request.getParameter("comment");
		String s2=request.getParameter("course");
		System.out.println(s1);
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		/*Date dt=new Date();
		Timestamp ts=new Timestamp(dt.getTime());*/
		System.out.println(currentTime);
		String s = session.getAttribute("name").toString();
		System.out.println(s);
		
		
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		try{
			
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
		Statement st=con.createStatement();
		System.out.println("2");
		st.executeUpdate("Insert into discussion_board(User_id,Post,Post_time, Course_name) values('"+s+"','"+s1+"','"+currentTime+"','"+s2+"')"); 
		System.out.println("comment submitted");
		
		String sql= "SELECT * FROM (SELECT User_id,Course_name, Post, DATE(Post_time) AS DATE FROM discussion_board ORDER BY DATE ASC) T WHERE User_id='"+s+"' and Course_name='"+s2+"'";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        System.out.println("comment fetched");
        
         out.print("<html>");
         while(rs.next()){  
         	out.print(""+rs.getString(1)+":  "+rs.getString(3)+"<br/><br/>");             
         }
         out.print("<form action='WelcomeFaculty.jsp' method='post'><input type='submit' value='Back' align='center'></form>");
         out.print("<form action='LogoutServlet' method='post'><br/><input type='submit' align='center' value='Logout'></form>");
         out.print("</html>");
		 
		 
			st.close();
			con.close();
		}
		catch(Exception e){
			
		}

	}

}
