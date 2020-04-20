package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.domain.Comment;
import com.blog.service.IBlogService;
import com.blog.service.ICommentService;
import com.blog.utils.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 发表评论的控制层
*/
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IBlogService blogService;
    /**
     *提交评论
     * @return
     */
    @RequestMapping("/save")
    public String saveComment(Comment comment, @RequestParam(value = "imageCode")String imageCode,
                              HttpServletRequest request, HttpSession session,
                              HttpServletResponse response)throws Exception{
        String sRand = (String) session.getAttribute("sRand");
        JSONObject jsonObject = new JSONObject();
        int result = 0;
        if(!sRand.equals(imageCode)){
            jsonObject.put("success", Boolean.FALSE);
            jsonObject.put("errorInfo", "验证码错误");
        }else{
            String userIp = request.getRemoteAddr(); //获取到用户IP
            comment.setUserIp(userIp);
            if(comment.getId()==null){
                 result = commentService.addComment(comment);//保存评论
                //给相对的博客评论数加1
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit()+1);
                blogService.updateBlog(blog);
            }
        }
        if(result>0){
            jsonObject.put("success", Boolean.TRUE);
        }else {
            jsonObject.put("success", Boolean.FALSE);
            jsonObject.put("errorInfo","评论失败");
        }
        ResponseUtil.write(response,jsonObject);
        return null;
    }
}
