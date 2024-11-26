package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckLoginServlet
 */
@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Cookie[] cookies = request.getCookies();
		 boolean isLoggedIn = false;

	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	              System.out.println(cookie.getName());
	            	if ("userId".equals(cookie.getName())) {
	                    isLoggedIn = true;
	                    break;
	                }
	            }
	        }
	        
	        
	        
	        
	        
	        if (isLoggedIn) {
	            // User is logged in, forward to StudentRegistration.html
	            RequestDispatcher dispatcher = request.getRequestDispatcher("StudentRegistration.html");
	            dispatcher.forward(request, response);
	        } else {
	            // User is not logged in, redirect to index.html with an alert
	            response.setContentType("text/html");
	            response.getWriter().println(
	                "<script>" +
	                "alert('First login then register');" +
	                "window.location.href = 'index.html';" +
	                "</script>"
	            );
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
