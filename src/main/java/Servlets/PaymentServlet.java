package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int studentId;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String user_id = "";
		String paymentMethod = request.getParameter("paymentMethod");
		double amount = Double.parseDouble(request.getParameter("amount"));
		String remarks = request.getParameter("remarks");

		String email = "";
		if (cookies != null) {

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					user_id = cookie.getValue();
				} else if (cookie.getName().equals("email")) {
					email = cookie.getValue();
				} else {
					System.out.println("cookie not found!!");
				}

			}
		}

		try (Connection connection = DatabaseConnection.getConnection()) {

			String sql = "SELECT std_id FROM students WHERE user_id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(user_id));
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				studentId = rs.getInt("std_id");

			}

			String InserQuery="INSERT INTO payments(std_id,paymentMethod,amount,remarks) VALUES(?,?,?,?)";
			 stmt = connection.prepareStatement(InserQuery);
			 stmt.setInt(1, studentId);
			 stmt.setString(2, paymentMethod);
			 stmt.setDouble(3, amount);
			 stmt.setString(4, remarks);
			 
			 if(stmt.executeUpdate() >0) {
				 System.out.println("Record inserted suceessfully!!");
				 
			 }
			 
			 response.sendRedirect("SuccessPageServlet");
			 
			 
			 
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(paymentMethod + "," + amount + "," + remarks);

	}

}
