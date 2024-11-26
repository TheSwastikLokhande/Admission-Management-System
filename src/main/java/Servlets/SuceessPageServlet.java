package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SuccessPageServlet
 */
@WebServlet("/SuccessPageServlet")
public class SuceessPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuceessPageServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve userId from cookies
        Cookie[] cookies = request.getCookies();
        String applicationId = "N/A"; // Default application ID if not found

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    applicationId = cookie.getValue();
                    break;
                }
            }
        }

        // Generate the success page dynamically
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html lang='en'>");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset='UTF-8'>");
        response.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        response.getWriter().println("<title>Application Successful - Engineering College Admission</title>");
        response.getWriter().println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css' rel='stylesheet'>");
        response.getWriter().println("<style>");
        response.getWriter().println("body { padding-top: 80px; }");
        response.getWriter().println(".success-message { max-width: 600px; margin: 0 auto; text-align: center; }");
        response.getWriter().println(".btn-custom { background-color: #007bff; color: white; }");
        response.getWriter().println("ul li{list-style: none;}");
        
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");

        // Navbar
        response.getWriter().println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark fixed-top'>");
        response.getWriter().println("<div class='container'>");
        response.getWriter().println("<a class='navbar-brand' href='#'>Engineering College Admission</a>");
        response.getWriter().println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
        response.getWriter().println("<span class='navbar-toggler-icon'></span>");
        response.getWriter().println("</button>");
        response.getWriter().println("<div class='collapse navbar-collapse' id='navbarNav'>");
        response.getWriter().println("<ul class='navbar-nav ms-auto'>");
        response.getWriter().println("<li class='nav-item'><a class='nav-link' href='profile.html'>Profile</a></li>");
        response.getWriter().println("<li class='nav-item'><a class='nav-link' href='logout.html'>Logout</a></li>");
        response.getWriter().println("</ul>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");
        response.getWriter().println("</nav>");

        // Success Message Section
        response.getWriter().println("<div class='container'>");
        response.getWriter().println("<div class='success-message card mt-5'>");
        response.getWriter().println("<div class='card-body'>");
        response.getWriter().println("<h2 class='text-success'>Application Submitted Successfully!</h2>");
        response.getWriter().println("<p class='lead'>Your application for admission to the Engineering College has been successfully submitted. Below are the details of your application:</p>");
        response.getWriter().println("<div class='mb-3'><strong>Application ID:</strong> <span id='applicationId'>#" + applicationId + "</span></div>");
        response.getWriter().println("<div class='mb-3'><strong>Application Status:</strong> <span class='text-success'>Pending Review</span></div>");
        response.getWriter().println("<div class='mb-3'><strong>Next Steps:</strong><ul><li>Check your email for confirmation and further instructions.</li>");
        response.getWriter().println("<li>Once your application is reviewed, you will be notified about the next steps.</li></ul></div>");
        response.getWriter().println("<button class='btn btn-custom w-100' onclick=\"window.location.href='ProfileServlet'\">Go to Profile</button>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");

        response.getWriter().println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js'></script>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Handle POST requests by redirecting to GET method
    }
}
