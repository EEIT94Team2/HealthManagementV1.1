package dao.social;

import model.social.SocialPost;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class SocialDAO {

    public void addPost(SocialPost post) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(post);
            transaction.commit();
        }
    }

    public List<SocialPost> getAllPosts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM SocialPost ORDER BY articleId DESC", SocialPost.class).list();
        }
    }

    public List<SocialPost> searchPosts(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM SocialPost WHERE title LIKE :keyword OR content LIKE :keyword ORDER BY publishDate DESC", SocialPost.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .list();
        }
    }

    public void updatePost(int articleId, String title, String content) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            SocialPost post = session.get(SocialPost.class, articleId);
            if (post != null) {
                post.setTitle(title);
                post.setContent(content);
                session.merge(post);
            }
            transaction.commit();
        }
    }

    public void deletePost(int articleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            SocialPost post = session.get(SocialPost.class, articleId);
            if (post != null) {
                session.remove(post);
            }
            transaction.commit();
        }
    }

    public SocialPost getPostById(int articleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(SocialPost.class, articleId);
        }
    }
}
