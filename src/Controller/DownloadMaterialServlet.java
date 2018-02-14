package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class DownloadMaterialServlet
 */
@WebServlet("/DownloadMaterialServlet")
public class DownloadMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadMaterialServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// size of byte buffer to send file
		response.setContentType("text/html");  
		
		HttpSession session = request.getSession(false);
		if (session.getAttribute("name") == null){
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
		      rd.include(request,response);  
		}
	    final int BUFFER_SIZE = 4096;   
		/////////////////////////int uploadId = Integer.parseInt(request.getParameter("id"));
        String contentname = request.getParameter("material");
        System.out.println(contentname);
        System.out.println("aboveeeee");
        String s = session.getAttribute("name").toString();
        try {
            // connects to the database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");
 
            // queries the database
            String sql = "SELECT * FROM course_content WHERE Content_name = ? and User_id= ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, contentname);
            statement.setString(2, s);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                // gets file name and file blob data
            	String fileType = result.getString("Content_type");
            	System.out.println(fileType);
                Blob blob = result.getBlob("Content_part");
                InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();
                 
                System.out.println("fileLength = " + fileLength);
 
                //ServletContext context = getServletContext();
                // set content properties and header attributes for the response
                response.setContentType(fileType);
                response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", contentname);
                
                response.setHeader(headerKey, headerValue);
 
                // writes the file to the client
                OutputStream outStream = response.getOutputStream();
                 
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                    System.out.println("hello");
                }
                 
                inputStream.close();
                outStream.close();             
            } 
            else {
                // no file found
               PrintWriter out = response.getWriter();
               System.out.println("File not found..");  
               out.print("File Not Found!!");
               out.print("<form action='WelcomeFaculty.jsp' method='post'><input type='submit' value='Back' align='center'></form>");
               out.print("<form action='LogoutServlet' method='post'><br/><input type='submit' align='center' value='Logout'></form>");
            }
        }
        catch (Exception ex) {
        	ex.printStackTrace();
                }
        }          
	}


