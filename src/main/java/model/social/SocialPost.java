package model.social;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SocialPost {
    private int articleId;
    private String title;
    private String content;
    private int userId;
    private Date publishDate;

    public SocialPost() {}

    public SocialPost(int articleId, String title, String content, int userId, Date publishDate) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.publishDate = publishDate;
    }
    
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    
    // 新增方法來格式化 publishDate 只顯示到分鐘
    public String getFormattedPublishDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(publishDate);
    }
}
