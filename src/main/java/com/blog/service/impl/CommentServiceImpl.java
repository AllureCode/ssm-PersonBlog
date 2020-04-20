package com.blog.service.impl;

import com.blog.dao.ICommentDao;
import com.blog.domain.Comment;
import com.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("commentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentDao iCommentDao;
    @Override
    public Integer addComment(Comment comment) {
        return iCommentDao.addComment(comment);
    }
    @Override
    public Integer updateComment(Comment comment) {
        return iCommentDao.updateComment(comment);
    }
    @Override
    public Integer deleteComment(Integer id) {
        return iCommentDao.deleteComment(id);
    }
    @Override
    public List<Comment> listComment(Map<String, Object> paramMap) {
        return iCommentDao.listComment(paramMap);
    }
    @Override
    public Long getCommentTotal(Map<String, Object> paramMap) {
        return iCommentDao.getCommentTotal(paramMap);
    }
}
