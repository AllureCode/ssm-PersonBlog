package com.blog.controller.admin;

import com.blog.domain.Blog;
import com.blog.domain.PageBean;
import com.blog.lucene.BlogIndex;
import com.blog.service.IBlogService;
import com.blog.utils.DateJsonValueProcessor;
import com.blog.utils.ResponseUtil;
import com.blog.utils.StringHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 博客管理
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogController {
    @Autowired
    private IBlogService iBlogService;
    private BlogIndex blogIndex = new BlogIndex();
    /**
     * 保存博客
     * @param blog
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public String saveBlog( Blog blog, HttpServletResponse response) throws Exception {
        System.out.println("--------blogblog");
        System.out.println(blog);
        //根据id判断是新增还是更新
        Integer integer = 0;
        if(blog.getId() == null){
            //新增
            integer = iBlogService.addBlog(blog);
            blogIndex.addIndex(blog);
        }else{
            //更新
            integer = iBlogService.updateBlog(blog);
            blogIndex.updateIndex(blog);
        }
        JSONObject jsonObject = new JSONObject();
        if(integer>0){
            jsonObject.put("success", Boolean.valueOf(true));
        }else{
            jsonObject.put("success", Boolean.valueOf(false));
        }
        ResponseUtil.write(response, jsonObject);
        return null;
    }
    /**
     * 查询博客
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page" ,required = false) String page,
                       @RequestParam(value = "rows" ,required = false) String  rows,
                       Blog blog, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("start",pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("title", StringHandler.PrecentHandler(blog.getTitle()));
        List<Blog> list = iBlogService.list(map);
        System.out.println("list***************************");
        System.out.println(list);
        //查询总数
        Long total = iBlogService.getTotal(map);
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = new JsonConfig();
        //对时间进行处理
        config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(list,config);   //封装为json数据
        System.out.println("jsonArray***************************");
        System.out.println(jsonArray);
        jsonObject.put("rows", jsonArray);
        jsonObject.put("total",total);
        ResponseUtil.write(response, jsonObject);
        System.out.println("jsonObject***************************");
        System.out.println(jsonObject);
        return  null;
    }
    /**
     * 根据主键查询用户信息
     * @return
     */
    @RequestMapping("/findById")
    public String findById(@RequestParam(value = "id") String id,HttpServletResponse response) throws Exception {
        Blog blog = iBlogService.findById(Integer.parseInt(id));
        JSONObject jsObject =  JSONObject.fromObject(blog);
        ResponseUtil.write(response, jsObject);
        return null;
    }
    /**
     *删除博客
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids,HttpServletResponse response) throws Exception {
        System.out.println("delete--************-de");
        System.out.println(ids);
        Integer  integer = 0;
        String[] idsStr = ids.split(",");   //将字符串转为数组
        for(int i=0;i<idsStr.length;i++){
             integer = iBlogService.deleteBlog(Integer.parseInt(idsStr[i]));
             blogIndex.deleteIndex(idsStr[i]);
        }
        JSONObject jsonObject = new JSONObject();
        if(integer>0){
            jsonObject.put("success", Boolean.valueOf(true));
        }
        ResponseUtil.write(response, jsonObject);
        return null;
    }
}
