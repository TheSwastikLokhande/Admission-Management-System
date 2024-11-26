package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin_panel
 */
@WebServlet("/Admin_panel")
public class Admin_panel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_panel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
		
		
	        out.println("<html><head><title>Admin Panel</title>");
	        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
	        out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script>");
	        
	        out.println("<style>"
	                + "table {"
	                + "    width: 50%;"
	                + "    border-collapse: collapse;"
	                +"     height: 50%;"
	                
	                + "}"
	                + "th, td {"
	                + "    border: 1px solid #ddd;"
	                + "    padding: 8px;"
	                + "    text-align: center;"
	                + "}"
	                + "th {"
	                + "    background-color: #4CAF50;"
	                + "    color: white;"
	                + "}"
	                + "tr:nth-child(even) {background-color: #ddd;}"
	                + "tr:nth-child(odd) {background-color: #ddd;}"
	                + "tr:hover {background-color: #ddd;}"
	                + "table.container {"
	                + "    max-width: 80%;"
	                + "    overflow-x: auto;"
	                + "    display: block;"
	                + "}"
	                
	                +".container::-webkit-scrollbar {"
	                + "    display: none; "
	                + "}"
	                + "</style>");
	        
	        
	        
	        
	        
	        
	        
	        out.println("<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,400i,600,700,700i&display=swap\" rel=\"stylesheet\">\r\n"
	        		+ "    <link href=\"css/bootstrap.css\" rel=\"stylesheet\">\r\n"
	        		+ "    <link href=\"css/fontawesome-all.css\" rel=\"stylesheet\">\r\n"
	        		+ "    <link href=\"css/swiper.css\" rel=\"stylesheet\">\r\n"
	        		+ "	<link href=\"css/magnific-popup.css\" rel=\"stylesheet\">\r\n"
	        		+ "	<link href=\"css/styles.css\" rel=\"stylesheet\">");
	        
	        
	        out.println("</head><body class='header'>");
	        out.println("<center><h1>Admin Panel</h1></center>");
	        out.println("<div class='container' style='overflow: scroll;'>");
	      
	        out.println(" <form action=\"Admin_panel\" method=\"POST\">");
	        
	        out.println("<table>");
	        out.println("<tr>"
	                + "<th>Student ID</th>"
	                + "<th>First Name</th>"
	                + "<th>Last Name</th>"
	                + "<th>DOB</th>"
	                + "<th>Gender</th>"
	              
	                + "<th>Contact Number</th>"
	                + "<th>Email</th>"
	                + "<th>Qualification</th>"
	                + "<th>Percentage</th>"
	                
	                + "<th>Application Status</th>"
	             
	                + "<th>Document Name</th>"
	                
	                + "<th>Fee Amount</th>"
	                + "<th>Approve application</th>"
	                + "</tr>");

	        // Database connection
	        
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try(Connection conn = DatabaseConnection.getConnection()) {

	            // Establish the connection
	           
	            // SQL query
	        	String query = "SELECT * From students";


	            // Prepare the statement
	            stmt = conn.prepareStatement(query);

	            // Execute the query
	            rs = stmt.executeQuery();
	           
	            // Process the result set
	            while (rs.next()) {
	                out.println("<tr>"
	                        + "<td>" + rs.getInt("std_id") + "</td>"
	                        + "<td>" + rs.getString("first_name") + "</td>"
	                        + "<td>" + rs.getString("last_name") + "</td>"
	                        + "<td>" + rs.getString("dob") + "</td>"
	                        + "<td>" + rs.getString("gender") + "</td>"
	                      
	                        + "<td>" + rs.getString("contact_number") + "</td>"
	                        + "<td>" + rs.getString("email") + "</td>"
	                        + "<td>" + rs.getString("qualification") + "</td>"
	                        + "<td>" + rs.getDouble("percentage") + "</td>"
	                       
	                        + "<td>" + rs.getString("application_status") + "</td>"
	                 //-----------------------------------------------------------------------------      
	                        
	                        + "<td>" + getDocumentsname(rs.getInt("std_id")) + "</td>"
	                        
	                   //--------------------------------------------------------------------------------
	                        + "<td>" + getamount(rs.getInt("std_id")) + "</td>"
	                        
	                        + "<td>" +"<input type=\"checkbox\" name=\"selectedStudents\" value=\"" + rs.getInt("std_id") + "\">" + "</td>"
	                        
	                        + "</tr>");
	                
	                
	                
	               
	            }
	        } catch (Exception e) {
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	        } finally {
	            // Close resources
	            try(Connection conn = DatabaseConnection.getConnection()) {
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                out.println("<p>Error while closing resources: " + e.getMessage() + "</p>");
	            }
	        }

	        // Close HTML table
	        out.println("</table>");
	        out.println("</div>");
	        out.println(" <input class=\"btn btn-success\" type=\"submit\" value=\"Confirm\">");
	        
	        out.println("</form>");
	        
	        out.println("</body></html>");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   String[] selectedStudentIds = request.getParameterValues("selectedStudents");
		   
		   if (selectedStudentIds != null && selectedStudentIds.length > 0) {
			   try (Connection conn = DatabaseConnection.getConnection()) {
				   String sql = "UPDATE students SET application_status = 'Accepted' WHERE std_id = ?";
		            PreparedStatement stmt = conn.prepareStatement(sql);
		            
		            
		            for (String studentId : selectedStudentIds) {
		            	
		            	 stmt.setInt(1, Integer.parseInt(studentId));
		            	 stmt.executeUpdate();
		            }
			   } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
		   }
		   
		   
		   
		   response.sendRedirect("Admin_panel");
	}
	

	protected String getDocumentsname( int id) {
		 StringBuilder documentNames = new StringBuilder();
		try(Connection conn = DatabaseConnection.getConnection()) {
        
        	String sql = "select document_name from documents WHERE student_id=?";
        	PreparedStatement stmt=conn.prepareStatement(sql);
        	int cnt=0;
        	stmt.setInt(1, id);
        	 ResultSet rs = stmt.executeQuery();
        	 
        	 while(rs.next()) {
        		 documentNames.append(rs.getString("document_name")).append(", ");
        		 cnt++;
        	}
        	 
        	 
        	 String result = documentNames.toString();
        	 System.out.println(result+" cnt"+cnt);
        
        
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return documentNames.toString();
	}
	
	protected int getamount(int id) {
		try(Connection conn = DatabaseConnection.getConnection()) {


        	String sql = "select amount from payments WHERE std_id=?";
        	PreparedStatement stmt=conn.prepareStatement(sql);
        	stmt.setInt(1, id);
       	 	ResultSet rs = stmt.executeQuery();
        	
       	 	if(rs.next()) {
       	 		return rs.getInt("amount");
       	 	}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
