package com.blog.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表实体类
 */
public class Comment implements Serializable {
    private Integer id;  // 主键
    private String userIp;   //评论者ip
    private Blog blog;    //被评论者博客的主键
    private String content;  //评论的内容
    private Date commentDate;  //评论的时间
    private Integer state;   //状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userIp='" + userIp + '\'' +
                ", blog=" + blog +
                ", content='" + content + '\'' +
                ", commentDate=" + commentDate +
                ", state=" + state +
                '}';
    }
}
