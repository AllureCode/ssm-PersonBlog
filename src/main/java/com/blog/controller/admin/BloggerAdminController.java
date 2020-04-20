package com.blog.controller.admin;

import com.blog.domain.Blogger;
import com.blog.service.IBloggerService;
import com.blog.utils.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {
    //打印日志信息
    private static final Logger log= LoggerFactory.getLogger(BloggerAdminController.class);

    @Autowired
    private IBloggerService iBloggerService;
    @RequestMapping("/save")
    public String saveBlogger(@RequestParam(value = "imageFile")MultipartFile imageFile,
                              Blogger blogger, HttpServletResponse response,
                              HttpServletRequest request) throws Exception{
        if(!imageFile.isEmpty()){
//            String path = request.getSession().getServletContext().getRealPath("/");
//            String filename=imageFile.getName();
//            String uuid=UUID.randomUUID().toString().replace("-", "");
//            filename=uuid+"_"+filename;
//            imageFile.transferTo(new File(path+"static/userImages/"+filename+".jpg"));
//            blogger.setImageName(filename);
            String filePath= WebFileUtils.getSystemRootPath(request);
            String imageName=DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
            log.debug("目标图片的路径： {} ",filePath+"static/userImages/"+imageName);
            imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
            blogger.setImageName(imageName);
        }
        StrBuilder result = new StrBuilder();
        Integer integerTotal = iBloggerService.updateBlogger(blogger);
        if(integerTotal>0){
            result.append("<script language='javascript'>alert('修改成功');</script> ");
        }else {
            result.append("<script language='javascript'>alert('修改失败');</script> ");
        }
        ResponseUtil.write(response, result);
        return null;
    }
    /**
     * 获取博主JSON格式信息
     */
    @RequestMapping("/find")
    public String findBlogger(HttpServletResponse response) throws Exception {
        Blogger blogger = (Blogger) SecurityUtils.getSubject().getSession().getAttribute(CommonParamUtils.CURRENT_USER);
        JSONObject jsonObject = JSONObject.fromObject(blogger);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * 修改密码
     * @param id
     * @param newPassword
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyPassword")
    public String modifyPassword(String id,String newPassword,HttpServletResponse response)throws Exception{
        Blogger blogger = new Blogger();
        blogger.setId(Integer.parseInt(id));
        blogger.setPassword(Encryption_MD5.md5(newPassword, "java"));
        Integer integer = iBloggerService.updateBlogger(blogger);
        JSONObject jsonObject = new JSONObject();
        if(integer>0){
            jsonObject.put("success", Boolean.TRUE);
        }else {
            jsonObject.put("success", Boolean.FALSE);
        }
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
