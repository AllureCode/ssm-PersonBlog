package com.blog.controller.admin;
import com.blog.domain.BlogType;
import com.blog.domain.PageBean;
import com.blog.service.IBlogService;
import com.blog.service.IBlogTypeService;
import com.blog.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 博客类型管理
 */
@Controller
@RequestMapping({"/admin/blogType"})
public class BlogTypeAdminController {
    @Autowired
    private IBlogTypeService iBlogTypeService;
    @Autowired
    private IBlogService iBlogService;
    /**
     * 博客类型列表
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page",required = false) String page,
                       @RequestParam(value = "rows",required = false) String rows
    , HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        //查询数据库
        Map<String ,Object> map = new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size",pageBean.getPageSize() );
        List<BlogType> blogTypeList = iBlogTypeService.findList(map);
        //查询总数据
        Long total = iBlogTypeService.getTotal(map);
        //将数据写入response中
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
        jsonObject.put("rows", jsonArray);
        jsonObject.put("total", total);
        ResponseUtil.write(response,jsonObject);
        return null;
    }
    /**
     * 添加博客类型/修改博客类型 根据id进行判断
     * @param blogType
     * @param
     * @return
     */
    @RequestMapping("/save")
    public  String addBlogType( BlogType blogType, HttpServletResponse response) throws Exception{
        System.out.println(blogType);
        Integer integer =0;
        if(blogType.getId()==null){
            //保存方法
            integer = iBlogTypeService.addBlog(blogType);
        }else{
            integer = iBlogTypeService.updateBlog(blogType);
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
     * 删除博客类别
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public String deleteBlogType(String ids,HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String[] idstr = ids.split(",");
        for (int i=0; i<idstr.length;i++){
            Integer sum = iBlogService.getBlogByType(Integer.valueOf(idstr[i]));
            if(sum>0){
                jsonObject.put("exist", "博客类别下当前有发表博客，无法删除");
            }else{
                iBlogTypeService.deleteBlog(Integer.valueOf(idstr[i]));
            }
        }
        jsonObject.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, jsonObject);
        return null;
    }
}
