package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    // 註冊
    public boolean registerUser(String name, String email, String password, char gender, String bio) throws SQLException {
        if (userDAO.getUserByEmail(email) != null) {
            return false; // Email 已存在
        }
        User newUser = new User(0, name, email, password, gender, bio);
        userDAO.addUser(newUser);
        return true;
    }

    // 登入
    public User loginUser(String email, String password) throws SQLException {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // ✅ 查詢單一用戶（透過 Email）
    public User getUserByEmail(String email) throws SQLException {
        return userDAO.getUserByEmail(email);
    }

    // ✅ 查詢單一用戶（透過 ID）
    public User getUserById(int userId) throws SQLException {
        return userDAO.getUserById(userId);
    }

    // ✅ 查詢所有用戶
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    // ✅ 更新用戶
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    // ✅ 刪除用戶
    public void deleteUser(String email) throws SQLException {
        userDAO.deleteUser(email);
    }
}


