package dao.social;

import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.social.SocialPost;

public class SocialDAO {
    private Connection getConnection() throws SQLException {
        return DBUtil.getConnection();
    }

    public void addPost(SocialPost post) {
        String sql = "INSERT INTO social_posts (title, content, user_id, publish_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setInt(3, post.getUserId());
            stmt.setTimestamp(4, new Timestamp(post.getPublishDate().getTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SocialPost> getAllPosts() {
        List<SocialPost> posts = new ArrayList<>();
        String sql = "SELECT article_id, title, content, user_id, publish_date FROM social_posts ORDER BY article_id DESC"; // 按 article_id 由大到小排序

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                posts.add(new SocialPost(
                    rs.getInt("article_id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("user_id"),
                    rs.getTimestamp("publish_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ SQL Error in getAllPosts(): " + e.getMessage());
        }

        return posts;
    }
    
    public List<SocialPost> searchPosts(String keyword) {
        List<SocialPost> posts = new ArrayList<>();
        String sql = "SELECT * FROM social_posts WHERE title LIKE ? OR content LIKE ? ORDER BY publish_date DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    posts.add(new SocialPost(
                        rs.getInt("article_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("publish_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
    
    public void updatePost(int articleId, String title, String content) {
        String sql = "UPDATE social_posts SET title = ?, content = ? WHERE article_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, articleId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletePost(int articleId) {
        String sql = "DELETE FROM social_posts WHERE article_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, articleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  //文章
    public SocialPost getPostById(int articleId) {
        String sql = "SELECT * FROM social_posts WHERE article_id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, articleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new SocialPost(
                    rs.getInt("article_id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("user_id"),
                    rs.getTimestamp("publish_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null; // 找不到文章時回傳 null
    }
    //文章上下頁
    public int getPreviousArticleId(int currentId) {
        String sql = "SELECT MAX(article_id) FROM social_posts WHERE article_id < ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, currentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 沒有上一篇
    }

    public int getNextArticleId(int currentId) {
        String sql = "SELECT MIN(article_id) FROM social_posts WHERE article_id > ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, currentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 沒有下一篇
    }

}
