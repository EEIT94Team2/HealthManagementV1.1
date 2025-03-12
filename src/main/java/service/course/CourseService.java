package service.course;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import model.course.*;
import util.DBUtil;  // ✅ 改用 DBUtil 連線

public class CourseService {

    // ✅ 直接使用 DBUtil 連接 SQL Server
    private Connection getConnection() throws SQLException {
        return DBUtil.getConnection(); 
    }

    // 執行 SQL 更新操作
    private void executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParameters(stmt, params);
            stmt.executeUpdate();
        }
    }

    // 設置 PreparedStatement 的參數
    private void setParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    // 新增課程
    public void insert(Course course) throws SQLException {
        String sql = "INSERT INTO courses(course_id, course_name, course_description, course_date, coach_id, coach_name, course_time, max_capacity) "
                   + "VALUES(?,?,?,?,?,?,?,?)";
        executeUpdate(sql, course.getcourse_id(), course.getcourse_name(), course.getcourse_description(),
                      course.getcourse_date(), course.getcoach_id(), course.getcoach_name(),
                      course.getcourse_time(), course.getmax_capacity());
    }

    // 刪除課程
    public void delete(String course_id) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        executeUpdate(sql, course_id);
    }

    // 查詢單筆課程
    public Course find(String course_id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        return executeQuery(sql, course_id);
    }

    // 查詢所有課程
    public List<Course> findAll() throws SQLException {
        String sql = "SELECT * FROM courses";
        return executeQueryList(sql);
    }

    // 查詢單個 Course 物件
    private Course executeQuery(String sql, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapCourse(rs);
                }
            }
        }
        return null;
    }

    // 查詢所有課程並返回 List
    private List<Course> executeQueryList(String sql, Object... params) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(mapCourse(rs));
                }
            }
        }
        return courses;
    }

    // 更新課程資料
    public Course updatedata(String course_id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        return executeQuery(sql, course_id);
    }

    public void update(Course course) throws SQLException {
        String sql = "UPDATE courses SET course_name = ?, course_description = ?, course_date = ?, "
                   + "coach_id = ?, coach_name = ?, course_time = ?, max_capacity = ? WHERE course_id = ?";
        executeUpdate(sql, course.getcourse_name(), course.getcourse_description(),
                      course.getcourse_date(), course.getcoach_id(), course.getcoach_name(),
                      course.getcourse_time(), course.getmax_capacity(), course.getcourse_id());
    }

    // 將 ResultSet 映射為 Course 物件
    private Course mapCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setcourse_id(rs.getString("course_id"));
        course.setcourse_name(rs.getString("course_name"));
        course.setcourse_description(rs.getString("course_description"));
        course.setcourse_date(rs.getString("course_date"));
        course.setcoach_id(rs.getString("coach_id"));
        course.setcoach_name(rs.getString("coach_name"));
        course.setcourse_time(rs.getString("course_time"));
        course.setmax_capacity(rs.getString("max_capacity"));
        return course;
    }
}
