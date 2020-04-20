package com.blog.controller.admin;

import com.blog.domain.Comment;
import com.blog.domain.PageBean;
import com.blog.service.ICommentService;
import com.blog.utils.DateJsonValueProcessor;
import com.blog.utils.ResponseUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
    @Autowired
    private ICommentService iCommentService;

    /**
     * 评论列表展示
     * @param page
     * @param rows
     * @param state
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page" ,required = false) String page,
                       @RequestParam(value = "rows" ,required = false) String rows,
                       @RequestParam(value = "state" ,required = false) String state,
                       HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String ,Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("state",state);
        //查询评论
        List<Comment> comments = iCommentService.listComment(map);
        //查询评论总数
        Long total = iCommentService.getCommentTotal(map);
        JSONObject jsonObject=new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(comments,jsonConfig);
        jsonObject.put("rows", jsonArray);
        jsonObject.put("total", total);
        ResponseUtil.write(response,jsonObject);
        return  null;
    }

    /**
     * 删除评论
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids")String ids,HttpServletResponse response)throws Exception{
        String[] strIds = ids.split(",");
        JSONObject jsonObject = new JSONObject();
        for(int i=0;i<strIds.length;i++){
            iCommentService.deleteComment(Integer.parseInt(strIds[i]));
        }
        jsonObject.put("success",Boolean.TRUE);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * 评论审核
     * @param ids
     * @param comment
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateState")
    public String updateState(@RequestParam(value = "ids")String ids,
                              Comment comment,HttpServletResponse response) throws Exception {
        String[] strIds = ids.split(",");
        for(int i=0;i<strIds.length;i++){
            comment.setId(Integer.parseInt(strIds[i]));
            comment.setState(comment.getState());
            iCommentService.updateComment(comment);
        }
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("success", Boolean.TRUE);
        ResponseUtil.write(response,jsonObject);
        return null;
    }
}
