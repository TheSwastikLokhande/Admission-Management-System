package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckEligibility
 */
@WebServlet("/CheckEligibility")
public class CheckEligibility extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 double percentage = Double.parseDouble(request.getParameter("percentage"));
	     int Age = Integer.parseInt(request.getParameter("age"));   
	        // Eligibility criteria: Marks should be greater than or equal to 60
	        boolean isEligible = percentage >= 60;

	        // Store eligibility result and percentage in session to use on the redirected page
	        HttpSession session = request.getSession();
	        session.setAttribute("isEligible", isEligible);
	        session.setAttribute("studentPercentage", percentage);

	        if(isEligible && (Age>17)) {
	        	 response.sendRedirect("CheckEligibility.html?errorMessagel=You are eligible for admission to the Engineering College&color=green");
	        }
	        else {
	        	 response.sendRedirect("CheckEligibility.html?errorMessagel=You are not eligible for admission to the Engineering College.&color=red");

	        }
	        
	}

}
