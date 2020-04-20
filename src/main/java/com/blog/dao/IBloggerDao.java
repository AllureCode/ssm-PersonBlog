package com.blog.dao;

import com.blog.domain.Blogger;
import org.springframework.stereotype.Repository;

/**
 *博主信息的Dao层
 */
@Repository("bloggerDao")
public interface IBloggerDao {
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
