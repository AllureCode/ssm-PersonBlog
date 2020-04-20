package com.blog.service;

import com.blog.domain.Blogger;

public interface IBloggerService {
    /**
     * 根据博主姓名查询
     * @param userName
     * @return
     */
    Blogger findUsername(String userName);
    /**
     * 更新博主信息
     * @param blogger
     * @return
     */
    Integer updateBlogger(Blogger blogger);
    /**
     *查询博主
     * @return
     */
    Blogger find();
}
