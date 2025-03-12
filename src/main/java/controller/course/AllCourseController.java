package controller.course;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.course.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/course/findAll")
public class AllCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=servdb;encrypt=false;";
	String USER = "kiwi";
	String PASSWORD = "kiwi";   
	
	Connection conn;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");;
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String SQL = "SELECT * FROM courses";
			PreparedStatement stmt = conn.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			List<Course> courses = new ArrayList<>();
			Course course = null;
			while(rs.next()) {
				course = new Course();
				course.setcourse_id(rs.getString("course_id"));
				course.setcourse_name(rs.getString("course_name"));
				course.setcourse_description(rs.getString("course_description"));
				course.setcourse_date(rs.getString("course_date"));
				course.setcoach_id(rs.getString("coach_id"));
				course.setcoach_name(rs.getString("coach_name"));
				course.setcourse_time(rs.getString("course_time"));
				course.setmax_capacity(rs.getString("max_capacity"));
				courses.add(course);
			}
			request.setAttribute("courses", courses);
			stmt.close();
			request.getRequestDispatcher("/jsp/course/allschedule.jsp")
				.forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
		    e.printStackTrace();
		} finally {
		    if (conn != null) {
		        try {
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
 	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
