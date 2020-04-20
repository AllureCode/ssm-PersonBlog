package com.blog.controller;

import com.blog.domain.Blogger;
import com.blog.utils.Encryption_MD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 博主控制器
 */
@Controller
public class BloggerController {
    @RequestMapping("/login")
    public String login(Blogger blogger, Model model) {
        String userName = blogger.getUserName();
        String password = blogger.getPassword();
        password = Encryption_MD5.md5(password, "java");
        //让shiro去验证
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            try {
                //执行登录方法
                subject.login(token);
                //登录成功 重定向到主页面
                return "redirect:/admin/main.jsp";
            } catch (AuthenticationException e) {
                e.printStackTrace();
                //认证失败 将用户名密码存入request域中 返回到前台页面修改
//            request.setAttribute("blogger", blogger);
                model.addAttribute("blogger", blogger);
                //提示错误信息给前台页面
//            request.setAttribute("errorInfo", "用户名或密码错误");
                model.addAttribute("errorInfo", "用户名或密码错误");
            }
        }
        //返回登录页面
        return "login";
    }
}
