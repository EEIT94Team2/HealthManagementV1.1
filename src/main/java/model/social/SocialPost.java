package model.social;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "social_posts")
public class SocialPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int articleId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date", nullable = false)
    private Date publishDate = new Date();

    public SocialPost() {}

    public SocialPost(int articleId, String title, String content, int userId, Date publishDate) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.publishDate = publishDate;
    }

    // Getters and Setters
}
