package com.blog.service.impl;

import com.blog.domain.Blog;
import com.blog.domain.BlogType;
import com.blog.domain.Blogger;
import com.blog.domain.Link;
import com.blog.service.IBlogService;
import com.blog.service.IBlogTypeService;
import com.blog.service.IBloggerService;
import com.blog.service.ILinkService;
import com.blog.utils.CommonParamUtils;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * 当程序启动时就查询数据库  查询出博客类别
 */
@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {
    private  static ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取ServletContext application对象
        ServletContext application   = servletContextEvent.getServletContext();
        /**
         * 博客类别
         */
        //查询数据库
        IBlogTypeService iblogTypeService = (IBlogTypeService) applicationContext.getBean("blogTypeService");
        List<BlogType> blogTypeList = iblogTypeService.countList();
        //将值设置到域中
        application.setAttribute(CommonParamUtils.BLOG_TYPE_COUNT_LIST,blogTypeList);

        /**
         * 博主信息
         */
        //查询数据库
        IBloggerService bloggerService = (IBloggerService) applicationContext.getBean("bloggerService");
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        //将值设置到域中
        application.setAttribute(CommonParamUtils.BLOGGER,blogger);
        /**
         * 按年月分类的博客数量
         */
        IBlogService blogService = (IBlogService) applicationContext.getBean("blogService");
        List<Blog> blogCountList = blogService.countList();
        application.setAttribute(CommonParamUtils.BLOG_COUNT_LIST,blogCountList);
        /**
         * 友情连接
         */
        ILinkService linkService = (ILinkService) applicationContext.getBean("linkService");
        List<Link> linkList = linkService.findLinkList(null);
        application.setAttribute(CommonParamUtils.LINK_LIST,linkList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
