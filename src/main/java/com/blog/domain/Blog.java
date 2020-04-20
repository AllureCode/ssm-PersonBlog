package com.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 博客表
 */
public class Blog  implements Serializable {
    private Integer id;   //主键
    private  String  title;//标题
    private String summary ;//摘要
    private Date releaseDate;//发表时间
    private  Integer clickHit;//点击数
    private String  content;//内容
    private Integer replyHit;//评论数
    private Integer typeId;
    private String keyWord;//关键字
    private Date createTime;
    private Date updateTime;


    private BlogType blogType; //因为使用到了blog type中的id 所以引入blogtype
    private String contentNoTag; //纯文本格式的内容
    private String releaseDateStr;//发表时间（字符串格式）
    private Integer blogCount ; //博客总数
    //博客里存在的图片 主要用于前端列表展示显示缩略图
    private List<String> imagesList=new LinkedList<String>();

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releaseDate=" + releaseDate +
                ", clickHit=" + clickHit +
                ", content='" + content + '\'' +
                ", replyHit=" + replyHit +
                ", typeId=" + typeId +
                ", keyWord='" + keyWord + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", blogType=" + blogType +
                ", contentNoTag='" + contentNoTag + '\'' +
                ", releaseDateStr='" + releaseDateStr + '\'' +
                ", blogCount=" + blogCount +
                ", imagesList=" + imagesList +
                '}';
    }
}
