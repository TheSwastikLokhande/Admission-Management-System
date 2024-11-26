package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		
		try(Connection conn = DatabaseConnection.getConnection()){
			String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
			
			 PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            preparedStatement.setString(1, email);
	            preparedStatement.setString(2, password);
	            ResultSet rs = preparedStatement.executeQuery();
			
	            if (rs.next()) {
	                // User exists, redirect to home.html
	            	
	            	Cookie userIDCookie = new Cookie("userId", ""+rs.getInt("id"));
	               
	            	Cookie UserEmailCookie = new Cookie("uemail", ""+rs.getString("email"));
	            	
	            	 userIDCookie.setMaxAge(60 * 60 * 24* 30);
	            	
	            	 
	            	 UserEmailCookie.setMaxAge(60 * 60 * 24* 30);
	        		
	            	 userIDCookie.setPath("/"); 
	            	
	            	 UserEmailCookie.setPath("/");
;	            	 // Make cookie accessible across the entire application
	            	 response.addCookie(userIDCookie);
	            	 response.addCookie(UserEmailCookie);
	            	
	            	 
	            	 
	                response.sendRedirect("index.html");
	            } else {
	                // User does not exist, redirect back to login.html with error message
	                response.sendRedirect("Login.html?errorMessagel=Invalid email or password.");
	            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}

}
