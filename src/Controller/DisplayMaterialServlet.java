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
 * Servlet implementation class DisplayMaterialServlet
 */
@WebServlet("/DisplayMaterialServlet")
public class DisplayMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayMaterialServlet() {
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
		//HttpSession session = request.getSession(false);
		String s=session.getAttribute("name").toString();
		try{ 
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
            String sql="select * from course_content where User_id='"+s+"'";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);  
            out.print("<table width=25% border=1 align='center'>");  
            out.print("<caption>Materials:</caption>");  
              
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	out.print("</tr><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(6)+"</td><td><form action='DownloadMaterialServlet' method='post'><input type='hidden' name='material' value='"+rs.getString(4)+"'><input type='submit' value='Download'></form></td></tr>");
            }  
            out.print("</table>");
            out.print("<form action='WelcomeFaculty.jsp' method='post'><input type='submit' value='Back' align='center'></form>");
            out.print("<form action='LogoutServlet' method='post'><br/><input type='submit' align='center' value='Logout'></form>");
            
            }
			catch (Exception e2){
				e2.printStackTrace();
			}  
            out.close();  

	}

}
