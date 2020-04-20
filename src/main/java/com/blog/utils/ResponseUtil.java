package com.blog.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 写入response的公共类
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response,Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(o.toString());
        writer.flush();
        writer.close();
    }
}
