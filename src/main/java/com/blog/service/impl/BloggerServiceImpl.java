package com.blog.service.impl;

import com.blog.dao.IBloggerDao;
import com.blog.domain.Blogger;
import com.blog.service.IBloggerService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bloggerService")
public class BloggerServiceImpl implements IBloggerService {
    @Autowired
    private IBloggerDao iBloggerDao;
    @Override
    public Blogger findUsername(String userName) {
        return iBloggerDao.findUsername(userName);
    }

    @Override
    public Integer updateBlogger(Blogger blogger) {
        //更新之前先将值放入session域
        SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
        return iBloggerDao.updateBlogger(blogger);
    }
    @Override
    public Blogger find() {
        return iBloggerDao.find();
    }
}
