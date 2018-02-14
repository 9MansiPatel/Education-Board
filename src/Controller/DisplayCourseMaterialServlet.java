package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class DisplayCourseMaterialServlet
 */
@WebServlet("/DisplayCourseMaterialServlet")
public class DisplayCourseMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCourseMaterialServlet() {
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
		          
		String course=request.getParameter("course");
		try{ 
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
            String sql="select * from course_content where Course_name=?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);  
            ps.setString(1,course); 
            out.print("<table width=50% border=1 align='center'>");  
            out.print("<caption>Materials:</caption>");  
              
            ResultSet rs=ps.executeQuery();  
            /* Printing result */  
              
            while(rs.next()){  
            	out.print("</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(6)+"</td><td>"+"<a href='DownloadMaterial.jsp'>Download</a>"+"</td></td>");             
            }  
              
            out.print("</table>");  
            out.print("<form action='WelcomeFaculty.jsp' method='post'><input type='submit' value='Back' align='center'></form>");
            out.print("<form action='LogoutServlet' method='post'><br/><input type='submit' align='center' value='Logout'></form>");
                          
            }catch (Exception e2) {e2.printStackTrace();}  
                      
            out.close();  

	}

}
