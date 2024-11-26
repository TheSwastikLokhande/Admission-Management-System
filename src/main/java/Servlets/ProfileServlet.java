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
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
        String User_id = ""; // Default application ID if not found

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                	User_id = cookie.getValue();
                    break;
                }
            }
        }
		
		String studentName = "John Doe";
		String studentEmail = "johndoe@example.com";
		String studentPhone = "+91 9876543210";
		String studentAddress = "1234, Main Street, City, State";
		String applicationId = "12345"; // Retrieve this dynamically (e.g., from a cookie or session)
		String applicationStatus = "Pending Review";
		

		try (Connection conn = DatabaseConnection.getConnection()) {
		
			String sql ="SELECT * FROM students WHERE user_id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, Integer.parseInt(User_id));
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				studentName= rs.getString("first_name") +" " +rs.getString("last_name");
				studentEmail = rs.getString("email");
				studentPhone = rs.getString("contact_number");
				studentAddress = rs.getString("address");
				applicationId = User_id;
				applicationStatus = rs.getString("application_status");
				
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Generate the profile page dynamically
		response.setContentType("text/html");
		response.getWriter().println("<!DOCTYPE html>");
		response.getWriter().println("<html lang='en'>");
		response.getWriter().println("<head>");
		response.getWriter().println("<meta charset='UTF-8'>");
		response.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		response.getWriter().println("<title>Student Profile - Engineering College Admission</title>");
		response.getWriter().println(
				"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css' rel='stylesheet'>");
		response.getWriter().println("<style>");
		response.getWriter().println("body { padding-top: 80px; }");
		response.getWriter().println(".profile-card { max-width: 800px; margin: 0 auto; }");
		response.getWriter().println(".btn-custom { background-color: #007bff; color: white; }");
		response.getWriter().println(".badge-custom { background-color: #28a745; }");
		response.getWriter().println("</style>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body style=\"	background: linear-gradient(to bottom right, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8)), url('../images/header-background.jpg') center center no-repeat;\" >");

		// Navbar
		response.getWriter().println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark fixed-top'>");
		response.getWriter().println("<div class='container'>");
		response.getWriter().println("<a class='navbar-brand' href='#'>Engineering College Admission</a>");
		response.getWriter().println(
				"<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
		response.getWriter().println("<span class='navbar-toggler-icon'></span>");
		response.getWriter().println("</button>");
	
		response.getWriter().println("</div>");
		response.getWriter().println("</nav>");

		// Profile Content
		response.getWriter().println("<div class='container'   >");
		response.getWriter().println("<div class='profile-card card mt-5'>");
		response.getWriter().println("<div class='card-body'>");
		response.getWriter().println("<h2 class='text-center mb-4'>Student Profile</h2>");

		// Personal Information
		response.getWriter().println("<h4 class='mb-3'>Personal Information</h4>");
		response.getWriter().println("<div class='mb-3'><strong>Name:</strong> " + studentName + "</div>");
		response.getWriter().println("<div class='mb-3'><strong>Email:</strong> " + studentEmail + "</div>");
		response.getWriter().println("<div class='mb-3'><strong>Phone:</strong> " + studentPhone + "</div>");
		response.getWriter().println("<div class='mb-3'><strong>Address:</strong> " + studentAddress + "</div>");

		// Application Details
		response.getWriter().println("<h4 class='mt-4 mb-3'>Application Details</h4>");
		response.getWriter().println("<div class='mb-3'><strong>Application ID:</strong> #" + applicationId + "</div>");
		response.getWriter()
				.println("<div class='mb-3'><strong>Application Status:</strong> <span class='badge badge-custom'>"
						+ applicationStatus + "</span></div>");

		
		// Next Steps
		response.getWriter().println("<h4 class='mt-4 mb-3'>Next Steps</h4>");
		response.getWriter().println("<ul>");
		response.getWriter().println("<li>Check your email for further instructions on the next steps.</li>");
		response.getWriter()
				.println("<li>Once your documents are verified, you will be notified for the interview round.</li>");
		response.getWriter().println("</ul>");

		// Update Profile Button
		response.getWriter()
				.println("<a href='index.html' class='btn btn-custom w-100 mt-4'>Go homepage</a>");

		response.getWriter().println("</div>"); 
		response.getWriter().println("</div>");
		response.getWriter().println("</div>");

		response.getWriter().println(
				"<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js'></script>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
