package com.blog.domain;

/**
 * 分页
 */
public class PageBean {
    private  int page;  //当前页码
    private int  pageSize; //页面大小
    private int  start; //查询的开始页面

    public PageBean(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {

        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (this.page-1)*this.pageSize;
    }

}
