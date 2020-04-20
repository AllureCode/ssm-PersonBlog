package com.blog.service.impl;

import com.blog.dao.IBlogTypeDao;
import com.blog.domain.BlogType;
import com.blog.service.IBlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements IBlogTypeService {
    @Autowired
    private IBlogTypeDao iBlogTypeDao;
    @Override
    public List<BlogType> countList() {
        return iBlogTypeDao.countList();
    }

    @Override
    public BlogType findById(Integer id) {
        return iBlogTypeDao.findById(id);
    }

    @Override
    public List<BlogType> findList(Map<String, Object> paramMap) {
        return iBlogTypeDao.findList(paramMap);
    }

    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return iBlogTypeDao.getTotal(paramMap);
    }

    @Override
    public Integer addBlog(BlogType blogType) {
        return iBlogTypeDao.addBlog(blogType);
    }

    @Override
    public Integer updateBlog(BlogType blogType) {
        return iBlogTypeDao.updateBlog(blogType);
    }

    @Override
    public Integer deleteBlog(Integer id) {
        return iBlogTypeDao.deleteBlog(id);
    }
}
