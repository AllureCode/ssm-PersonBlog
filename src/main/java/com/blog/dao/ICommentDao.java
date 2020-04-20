package com.blog.dao;

import com.blog.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("commentDao")
public interface ICommentDao {
    /**
     * 增加评论
     * @param comment
     * @return
     */
    Integer addComment(Comment comment);

    /**
     * 更新评论
     * @param comment
     * @return
     */
    Integer updateComment(Comment comment);

    /**
     *删除评论
     * @param id
     * @return
     */
    Integer deleteComment(Integer id);

    /**
     * 查询评论
     * @param paramMap
     * @return
     */
    List<Comment> listComment(Map<String,Object> paramMap);
    /**
     * 查询评论总记录数
     * @param paramMap
     * @return
     */
    Long getCommentTotal(Map<String, Object> paramMap);

    /**
     * 根据博客id删除
   * @param blogId
     * @return
     */
    Integer deleteByBlogId(Integer blogId);
}
