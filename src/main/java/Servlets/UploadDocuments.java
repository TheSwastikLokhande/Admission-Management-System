package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.*;

/**
 * Servlet implementation class UploadDocuments
 */
@WebServlet("/UploadDocuments")
public class UploadDocuments extends HttpServlet {
	 private String uploadDirectory;
	 private static int studentId;
	 private static int document_name;
	 public void init() throws ServletException {
	        // Initialize the upload directory once the servlet context is available
		 String projectPath = new File("E:/archer info/java servlet/engineering_collage_addmition_system").getAbsolutePath();
		    
		    // Set the upload directory path relative to the project directory
		    uploadDirectory = projectPath + "/src/main/webapp/uploads";

	        File uploadDir = new File(uploadDirectory);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		 String user_id="";
		
		 String email="";
		 if (cookies != null) {
			 
			 for (Cookie cookie : cookies) {
				 if (cookie.getName().equals("userId")) {
					 user_id =  cookie.getValue();
					 System.out.println(user_id);
				 }else if(cookie.getName().equals("email")) {
					 email = cookie.getValue();
				 }else {
					 System.out.println("cookie not found!!");
				 }
				 
			 }
		 }
		 
		 
		 
		 try (Connection connection = DatabaseConnection.getConnection()){
			 
			String sql = "SELECT std_id FROM students WHERE user_id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(user_id));
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				studentId = rs.getInt("std_id");
				
				
			}
			
			System.out.println(studentId);
			
			
            

			 
		 } catch (Exception e) {
			// TODO: handle exception
		}
		 
		
		
		
		
		
		
		
	        try {
	            // Process the uploaded files
	            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
	            List<FileItem> items = upload.parseRequest(request);

	            for (FileItem item : items) {
	                if (!item.isFormField()) {
	                    String fileName = item.getName();
	                    if (fileName != null && !fileName.isEmpty()) {
	                        File file = new File(uploadDirectory, fileName);

	                        // Rename file if it already exists
	                        if (file.exists()) {
	                            String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
	                            String extension = fileName.substring(fileName.lastIndexOf('.'));
	                            fileName = baseName + "_" + System.currentTimeMillis() + extension;
	                            file = new File(uploadDirectory, fileName);
	                        }

	                        item.write(file); // Save the uploaded file
	                        System.out.println("Uploaded: " + fileName + " to " + uploadDirectory);
	                        
	                        insertFilePathIntoDatabase(fileName, file.getAbsolutePath());
	                    }
	                }
	            }
	            
	            response.sendRedirect("PaymentPage.html");
	            // Send success response
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }


	        
	}
	
	private void insertFilePathIntoDatabase(String fileName, String filePath) {
        
        try (Connection connection = DatabaseConnection.getConnection()){
        	String insertQuery = "INSERT INTO documents (student_id,document_name, file_path,upload_date) VALUES (?,?,?,CURDATE())";
             
        	PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, fileName);
            preparedStatement.setString(3, "uploads/"+fileName);
           
            

            preparedStatement.executeUpdate();
            System.out.println("File path inserted into the database: " + filePath);
        
        }catch (Exception e) {
            e.printStackTrace();
        }
    
	}
	
}


