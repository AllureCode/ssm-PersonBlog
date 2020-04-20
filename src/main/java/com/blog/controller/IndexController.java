package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.domain.PageBean;
import com.blog.service.IBlogService;
import com.blog.utils.PageUtil;
import com.blog.utils.StringHandler;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对index页面的控制
 */
@Controller
public class IndexController {
    @Autowired
    IBlogService blogService;
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "page" ,required = false)String page,
                              @RequestParam(value = "typeId",required = false)String typeId,
                              @RequestParam(value = "releaseDate",required = false)String releaseDate,
                              HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "个人博客系统");
        if(!StringHandler.stringIsOrNotNull(page)){ //page为空则设置为1
            page="1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page),10);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("start",pageBean.getStart() );
        map.put("size", pageBean.getPageSize());
        map.put("typeId",typeId);
        map.put("releaseDate",releaseDate);
        List<Blog> list = blogService.list(map);

        //加入查询条件
        StringBuffer param = new StringBuffer();
        if(StringHandler.stringIsOrNotNull(typeId)){
            param.append("typeId"+typeId+"&");
        }
        if(StringHandler.stringIsOrNotNull(releaseDate)){
            param.append("releaseDate"+releaseDate+"&");
        }
        //实现上一篇下一篇的功能
        String pageCode=PageUtil.genPagination(request.getContextPath()+"/index.html",blogService.getTotal(map),
                Integer.parseInt(page),10,param.toString());
        modelAndView.addObject("mainPage", "foreground/blog/list.jsp");
        modelAndView.addObject("pageCode",pageCode);
        modelAndView.addObject("blogList", list);
        return modelAndView;
    }
}
