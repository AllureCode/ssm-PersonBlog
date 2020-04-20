package com.blog.utils;

import org.springframework.test.context.TestExecutionListeners;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * title:DateUtil.java
     * description: 获取当前年月日时分秒组成的串：可用于命名图片、文件
     * time:2018年1月16日 下午10:48:48
     * author:debug-steadyjack
     * @return String
     * @throws Exception
     */
    public static String getCurrentDateStr()throws Exception{
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }

    public  static  String formatDate(Date date,String format){
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(date!=null){
           result = sdf.format(date);
        }
        return result;
    }
}
