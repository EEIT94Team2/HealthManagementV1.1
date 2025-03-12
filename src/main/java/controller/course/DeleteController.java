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

@WebServlet("/api/course/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=servdb;encrypt=false;";
	String USER = "kiwi";
	String PASSWORD = "kiwi";   
	
	Connection conn;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String course_id = request.getParameter("course_id");
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context
					.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			String SQL = "DELETE FROM courses WHERE course_id = ?";
			PreparedStatement stmt = conn.prepareStatement(SQL);
			stmt.setString(1, course_id);
			stmt.execute();
			
			Course course = new Course();
			course.setcourse_id(course_id);

			String successMessage = "課程刪除成功！";
            request.setAttribute("successMessage", successMessage);
			request.setAttribute("course", course);
			
			stmt.close();
			request.getRequestDispatcher("/jsp/course/delete.jsp")
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
