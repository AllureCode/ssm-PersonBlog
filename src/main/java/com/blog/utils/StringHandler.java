package com.blog.utils;

/**
 * 对字符串的处理
 */
public class StringHandler {
    /**
     * d对字符串加上%返回
     *
     * @param str
     * @return
     */
    public static String PrecentHandler(String str) {
        if (stringIsOrNotNull(str)) {
            return "%" + str + "%";
        }else {
            return null;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean stringIsOrNotNull(String str) {
        if ((str != null) && !"".equals(str.trim())) {
            return true;      //非空
        } else {
            return false;   //空
        }
    }
}
