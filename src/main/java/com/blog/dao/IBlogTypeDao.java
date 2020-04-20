package com.blog.dao;

import com.blog.domain.BlogType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("blogTypeDao")
public interface IBlogTypeDao {
    /**
     * 查询所有博客类型
     * @return
     */
    List<BlogType> countList();

    /**
     * 根据id查询一条记录
     * @param id
     * @return
     */
    BlogType findById(Integer id);

    /**
     * 自定义条件查询
     * @param paramMap
     * @return
     */
    List<BlogType> findList(Map<String, Object> paramMap);

    /**
     * 查询总记录数
     * @param paramMap
     * @return
     */
    Long getTotal(Map<String, Object> paramMap);

    /**
     * 添加博客
     *
     * @param blogType
     * @return
     */
    Integer addBlog(BlogType blogType);


    /**
     * 修改博客
     * @param blogType
     * @return
     */
    Integer updateBlog(BlogType blogType);

    /**
     * 删除博客
     * @param id
     * @return
     */
    Integer deleteBlog(Integer id);
}
