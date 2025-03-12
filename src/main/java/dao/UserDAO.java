package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DBUtil;

public class UserDAO {

    // 1. 新增會員（註冊）
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, gender, bio) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, String.valueOf(user.getGender()));
            stmt.setString(5, user.getBio());
            stmt.executeUpdate();
        }
    }

    // 2. 查詢單一會員（根據 Email）
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("gender").charAt(0),
                        rs.getString("bio")
                    );
                }
            }
        }
        return null;
    }

    // 3. 查詢單一會員（根據 ID）
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("gender").charAt(0),
                        rs.getString("bio")
                    );
                }
            }
        }
        return null;
    }

    // 4. 查詢所有會員
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("gender").charAt(0),
                    rs.getString("bio")
                ));
            }
        }
        return users;
    }

    // 5. 更新會員資料
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET name=?, password=?, gender=?, bio=? WHERE email=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, String.valueOf(user.getGender()));
            stmt.setString(4, user.getBio());
            stmt.setString(5, user.getEmail());
            stmt.executeUpdate();
        }
    }

    // 6. 刪除會員
    public void deleteUser(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // 🔹 開啟事務，確保數據一致性

            // 🔹 先刪除 `exercise_records` 內與該會員相關的數據
            String deleteExerciseRecordsSQL = "DELETE FROM exercise_records WHERE user_id = (SELECT id FROM users WHERE email = ?)";
            stmt1 = conn.prepareStatement(deleteExerciseRecordsSQL);
            stmt1.setString(1, email);
            stmt1.executeUpdate();

            // 🔹 再刪除 `users` 表中的會員
            String deleteUserSQL = "DELETE FROM users WHERE email = ?";
            stmt2 = conn.prepareStatement(deleteUserSQL);
            stmt2.setString(1, email);
            stmt2.executeUpdate();

            conn.commit(); // 🔹 交易成功，提交變更
        } catch (SQLException e) {
            if (conn != null) conn.rollback(); // 🔹 出錯時回滾
            throw e;
        } finally {
            if (stmt1 != null) stmt1.close();
            if (stmt2 != null) stmt2.close();
            if (conn != null) conn.close();
        }
    }

}


