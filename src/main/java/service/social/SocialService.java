package service.social;

import java.util.List;

import dao.social.SocialDAO;
import model.social.SocialPost;

public class SocialService {
    private SocialDAO socialDAO = new SocialDAO();

    public void addPost(SocialPost post) {
        try {
            socialDAO.addPost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SocialPost> getAllPosts() {
        return socialDAO.getAllPosts();
    }

    
    public List<SocialPost> searchPosts(String keyword) {
            return socialDAO.searchPosts(keyword);
        
    }
    
    public void updatePost(int articleId, String title, String content) {
        socialDAO.updatePost(articleId, title, content);
    }

    public void deletePost(int articleId) {
        socialDAO.deletePost(articleId);
    }

    public SocialPost getPostById(int articleId) {
        return socialDAO.getPostById(articleId);
    }
    
    public int getPreviousArticleId(int articleId) {
        return socialDAO.getPreviousArticleId(articleId);
    }

    public int getNextArticleId(int articleId) {
        return socialDAO.getNextArticleId(articleId);
    }
 
    
}

