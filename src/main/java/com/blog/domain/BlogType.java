package com.blog.domain;

import java.io.Serializable;

/**
 * 博客类别
 */
public class BlogType implements Serializable {
    private Integer id;
    private String typeName;  //类型名称
    private  Integer orderNo;  //序号
    private  Integer blogCount; //博客总数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", orderNo=" + orderNo +
                ", blogCount=" + blogCount +
                '}';
    }
}
