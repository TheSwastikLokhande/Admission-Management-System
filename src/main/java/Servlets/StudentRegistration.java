package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentRegistration
 */
@WebServlet("/StudentRegistration")
public class StudentRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Cookie[] cookies = request.getCookies();
		 String user_id="";
		 String User_email="";
		 if (cookies != null) {
			 
			 for (Cookie cookie : cookies) {
				 if (cookie.getName().equals("userId")) {
					 user_id =  cookie.getValue();
					 System.out.println(User_email);
				 }else if(cookie.getName().equals("uemail")) {
					 User_email = cookie.getValue();
					 System.out.println(User_email);
				 }
				 
			 }
		 }
		 
		 System.out.println("User Email: "+User_email+" UserId: "+user_id);
		 if (user_id == null || user_id.isEmpty() || User_email == null || User_email.isEmpty()) {
		        response.getWriter().write("Error: Missing user information.");
		        return; // Stop further processing if cookies are missing
		    }
		 
		
		
		String firstName = request.getParameter("firstName");
		String lastName =  request.getParameter("lastName");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String contactNumber = request.getParameter("contactNumber");
		//email
		String qualification = request.getParameter("qualification");
		String percentage = request.getParameter("percentage");
		String hostelRequired = request.getParameter("hostelRequired");
		try(Connection conn = DatabaseConnection.getConnection()){
			
			String sql="INSERT INTO students (user_id,first_name,last_name,dob,gender,address,city,state,zip,contact_number,email,qualification,percentage,hostel_required) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, Integer.parseInt(user_id));
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, dob);
            stmt.setString(5, gender);
            stmt.setString(6, address);
            stmt.setString(7, city);
            stmt.setString(8, state);
            stmt.setString(9, zip);
            stmt.setString(10, contactNumber);
            stmt.setString(11, User_email);
            stmt.setString(12, qualification);
            stmt.setString(13, percentage);
            
            boolean hostelreq;
           
            if(hostelRequired.contentEquals("Yes")) {
            	hostelreq= true;
            }else {
            	hostelreq=false;
            }
            
            stmt.setBoolean(14, hostelreq);
            stmt.executeUpdate();
            
            
            
            response.sendRedirect("UploadDocments.html");
            
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
