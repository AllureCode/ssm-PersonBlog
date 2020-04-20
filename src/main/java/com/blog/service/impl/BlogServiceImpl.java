package com.blog.service.impl;
import com.blog.dao.IBlogDao;
import com.blog.dao.ICommentDao;
import com.blog.domain.Blog;
import com.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service("blogService")
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private IBlogDao iBlogDao;
    @Autowired
    private ICommentDao iCommentDao;
    @Override
    public List<Blog> countList() {
        return iBlogDao.countList();
    }
    @Override
    public List<Blog> list(Map<String, Object> map) {
        return iBlogDao.list(map);
    }
    @Override
    public Long getTotal(Map<String, Object> map) {
        return iBlogDao.getTotal(map);
    }
    @Override
    public Blog findById(Integer id) {
        return iBlogDao.findById(id);
    }
    @Override
    public Integer addBlog(Blog blog) {
        return iBlogDao.addBlog(blog);
    }
    @Override
    public Integer updateBlog(Blog blog) {
        return iBlogDao.updateBlog(blog);
    }
    @Override
    public Integer deleteBlog(Integer id) {
        /**
         * 设置级联删除 删除博客之前先删除此博客的评论
         * 因为使用spring进行管理 所以此处带有spring的事务处理
         */
        iCommentDao.deleteByBlogId(id);
        return iBlogDao.deleteBlog(id);
    }

    @Override
    public Integer getBlogByType(Integer typeId) {
        return iBlogDao.getBlogByType(typeId);
    }

    @Override
    public Blog lastBlog(Integer id) {
        return iBlogDao.lastBlog(id);
    }

    @Override
    public Blog nextBlog(Integer id) {
        return iBlogDao.nextBlog(id);
    }
}
