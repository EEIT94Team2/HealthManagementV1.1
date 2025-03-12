package controller.course;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.course.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/course/updatedata")
public class UpdateDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=servdb;encrypt=false;";
	String USER = "kiwi";
	String PASSWORD = "kiwi";   
	
	Connection conn;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		String course_id = request.getParameter("course_id");
 		Course course = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");;
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String SQL = "SELECT * FROM courses WHERE course_id = ?";
			PreparedStatement stmt = conn.prepareStatement(SQL);
			stmt.setString(1, course_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				course = new Course();
				course.setcourse_id(rs.getString("course_id"));
				course.setcourse_name(rs.getString("course_name"));
				course.setcourse_description(rs.getString("course_description"));
				course.setcourse_date(rs.getString("course_date"));
				course.setcoach_id(rs.getString("coach_id"));
				course.setcoach_name(rs.getString("coach_name"));
				course.setcourse_time(rs.getString("course_time"));
				course.setmax_capacity(rs.getString("max_capacity"));
			}
			request.setAttribute("course", course);
			stmt.close();
			request.getRequestDispatcher("/jsp/course/updatedata.jsp")
				.forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
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
