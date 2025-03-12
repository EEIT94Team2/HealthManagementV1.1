package controller.course;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.course.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/course/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=servdb;encrypt=false;";
	String USER = "kiwi";
	String PASSWORD = "kiwi";   
	
	Connection conn;
 		protected void doGet(HttpServletRequest request, HttpServletResponse response)
 				throws ServletException, IOException {
 	 		String course_id = request.getParameter("course_id");
 	 		String course_name = request.getParameter("course_name");
 	 		String course_description = request.getParameter("course_description");
 	 		String course_date = request.getParameter("course_date");
 	 		String coach_id = request.getParameter("coach_id");
 	 		String coach_name = request.getParameter("coach_name");
 	 		String course_time = request.getParameter("course_time");
 	 		String max_capacity = request.getParameter("max_capacity");
 			try {
 				Context context = new InitialContext();
 				DataSource ds = (DataSource)context
 						.lookup("java:/comp/env/jdbc/servdb");
 				conn = ds.getConnection();
 				String SQL = "UPDATE courses SET course_name = ?, course_description = ?, course_date = ?, coach_id = ?, coach_name = ?, course_time = ?, max_capacity = ? WHERE course_id = ?";
 				PreparedStatement stmt = conn.prepareStatement(SQL);
 				stmt.setString(8, course_id);
 				stmt.setString(1, course_name);
 				stmt.setString(2, course_description);
 				stmt.setString(3, course_date);
 				stmt.setString(4, coach_id);
 				stmt.setString(5, coach_name);
 				stmt.setString(6, course_time);
 				stmt.setString(7, max_capacity);
 				stmt.execute();
 				
 				Course course = new Course();
 				course.setcourse_id(course_id);
 				course.setcourse_name(course_name);
 				course.setcourse_description(course_description);
 				course.setcourse_date(course_date);
 				course.setcoach_id(coach_id);
 				course.setcoach_name(coach_name);
 				course.setcourse_time(course_time);
 				course.setmax_capacity(max_capacity);
 				
 				String successMessage = "課程修改成功！";
 	            request.setAttribute("successMessage", successMessage);
 				
 				request.setAttribute("course", course);
 				request.getRequestDispatcher("/jsp/course/update.jsp")
 					.forward(request, response);
 			} catch (SQLException | NamingException e) {
 				e.printStackTrace();
 			} finally {
 				try {
 				     conn.close();
 				    } catch (SQLException e) {
 				     e.printStackTrace();
 				    }
 			}
 		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
