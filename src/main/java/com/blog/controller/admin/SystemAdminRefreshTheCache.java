package com.blog.controller.admin;

import com.blog.domain.Blog;
import com.blog.domain.BlogType;
import com.blog.domain.Blogger;
import com.blog.domain.Link;
import com.blog.service.IBlogService;
import com.blog.service.IBlogTypeService;
import com.blog.service.IBloggerService;
import com.blog.service.ILinkService;
import com.blog.utils.CommonParamUtils;
import com.blog.utils.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 刷新系统缓存
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminRefreshTheCache {
    @Autowired
    private IBlogTypeService iBlogTypeService;
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ILinkService linkService;
    @Autowired
    private IBloggerService bloggerService;
    @RequestMapping("/refreshTheCache")
    public String refreshTheCache(HttpServletRequest request, HttpServletResponse response) throws Exception {
         //获取到ServletContext上下文
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
//        ServletContext application = webApplicationContext.getServletContext();
        ServletContext application = RequestContextUtils.findWebApplicationContext(request).getServletContext();
        List<BlogType> blogTypeList = iBlogTypeService.countList();
        application.setAttribute(CommonParamUtils.BLOG_TYPE_COUNT_LIST,blogTypeList);

        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        //将值设置到域中
        application.setAttribute(CommonParamUtils.BLOGGER,blogger);

        List<Blog> blogCountList = blogService.countList();
        application.setAttribute(CommonParamUtils.BLOG_COUNT_LIST,blogCountList);

        List<Link> linkList = linkService.findLinkList(null);
        application.setAttribute(CommonParamUtils.LINK_LIST,linkList);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, jsonObject);
        return null;
    }
}
