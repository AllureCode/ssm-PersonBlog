package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.service.IBlogService;
import com.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogManagerController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICommentService commentService;
    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id")Integer id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        //阅读人数加1
        blog.setClickHit(blog.getClickHit()+1);
        blogService.updateBlog(blog);

        modelAndView.addObject("mainPage", "foreground/blog/view.jsp");
        modelAndView.addObject("pageTitle", blog.getTitle()+"_"+"个人博客");
        modelAndView.setViewName("index");
        //上一篇下一篇的功能调用
        modelAndView.addObject("pageCode",  getUpAndDownPageCode(blogService.lastBlog(id),
                blogService.nextBlog(id),request.getServletContext().getContextPath()));
        //查询评论
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("blogId",blog.getId());
        map.put("state", 1);
        modelAndView.addObject("commentList", commentService.listComment(map));
        return modelAndView;
    }

    /**
     * 上一篇下一篇功能
     * @param lastBlog
     * @param nextBlog
     * @param projectContext
     * @return
     */
    private  String getUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext){
        StringBuffer pageCode = new StringBuffer();
        if(lastBlog==null || lastBlog.getId()==null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else {
            pageCode.append("<p>上一篇：<a href='"+projectContext+"/blog/articles/"+lastBlog.getId()+".html'>"+
                    lastBlog.getTitle()+"</a></p>");
        }
        if(nextBlog==null || nextBlog.getId()==null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else {
            pageCode.append("<p>下一篇：<a href='"+projectContext+"/blog/articles/"+nextBlog.getId()+".html'>"+
                    nextBlog.getTitle()+"</a></p>");
        }
        return pageCode.toString();
    }
}
