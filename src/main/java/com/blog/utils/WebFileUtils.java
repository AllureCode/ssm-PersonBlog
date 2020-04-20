package com.blog.utils;


import javax.servlet.http.HttpServletRequest;

public class WebFileUtils {
    public static String getSystemRootPath(HttpServletRequest request){
        String rootPath=request.getServletContext().getRealPath("/");
        //request.getContextPath();
        return rootPath;
    }
}
