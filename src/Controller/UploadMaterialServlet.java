package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadMaterialServlet
 */
@WebServlet("/UploadMaterialServlet")
@MultipartConfig
public class UploadMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadMaterialServlet() {
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
		InputStream inputStream = null; // input stream of the upload file
        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("filepath");
        System.out.println(filePart.toString());
        String course= request.getParameter("coursename");
        System.out.println(course);
        System.out.println("..................");
        String content= request.getParameter("contentname");
        System.out.println(filePart+"   ");
        String filetype=filePart.getContentType();
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();		
		
         
            if("".equals(filePart)){
            	RequestDispatcher rd = request.getRequestDispatcher("UploadMaterial.jsp");
            	System.out.println("<font color=red>Please select a file</font>");
            	rd.include(request, response);
            }
            else{
            	try{
    				Class.forName("com.mysql.jdbc.Driver");
    				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3360/eeducation","root","root");

    				PreparedStatement stmt=con.prepareStatement("INSERT INTO course_content (User_id, Course_name, Content_name, Content_part, Content_type) values (?, ?, ?, ?, ?)");  
    			
    				//stmt.setInt(1,"NULL");//1 specifies the first parameter in the query  
    				
    				if (inputStream != null) {
    	                // fetches input stream of the upload file for the blob column
    					stmt.setString(1, s);
    	                stmt.setString(2, course);
    	                stmt.setString(3, content);
    	                stmt.setBlob(4, inputStream);
    	                stmt.setString(5, filetype);
    	            }
    				
    				int i=stmt.executeUpdate();  
    				System.out.println(i+" Inserted");
    				con.close();
    				status="True";
    				if(status.equals("True")){
    					out.print("Upload Successfull..!!");
    					RequestDispatcher rd = request.getRequestDispatcher("WelcomeFaculty.jsp");
    	    			rd.forward(request, response);
    				}
    				else{
    					out.print("Upload Failed..!!");
    					RequestDispatcher rd = request.getRequestDispatcher("UploadMaterial.jsp");
    					rd.forward(request, response);
    				}
    			}
    			catch(Exception e){ 
    				System.out.println(e);
    			}  
            	RequestDispatcher rd = request.getRequestDispatcher("WelcomeFaculty.jsp");
    			rd.forward(request, response);
    		}
        }
	}
}
