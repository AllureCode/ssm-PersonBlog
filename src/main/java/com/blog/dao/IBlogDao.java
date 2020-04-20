package com.blog.dao;

import com.blog.domain.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("blogDao")
public interface IBlogDao {
    /**
     * 查询所有博客列表(供首页使用）
     * @return
     */
    List<Blog> countList();
    /**
     * 带参数查询博客列表
     * @param map
     * @return
     */
    List<Blog> list(Map<String,Object> map);
    /**
     * 带参数查询博客总数
     * @param map
     * @return
     */
    Long getTotal(Map<String,Object> map);
    /**
     * 根据id 查询
     */
    Blog findById(Integer id);

    /**
     * 增加博客
     * @param blog
     * @return
     */
    Integer addBlog(Blog blog);

    /**
     * 修改博客
     * @param blog
     * @return
     */
    Integer updateBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    Integer deleteBlog(Integer id);

    /**
     * 根据typeId类型查询博客
     * @param typeId
     * @return
     */
    Integer getBlogByType(Integer typeId);

    /**
     * 查询上一条博客
     * @param id
     * @return
     */
    Blog lastBlog(Integer id);

    /**
     * 查询下一条博客
     * @param id
     * @return
     */
    Blog nextBlog(Integer id);
}
