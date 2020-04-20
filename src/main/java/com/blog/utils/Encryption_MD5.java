package com.blog.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 使用MD5加密
 */
public class Encryption_MD5 {
    /**
     *
     * @param sources:加密的字符串
     * @param key：密钥  java
     * @return
     */
    public static String md5(String sources,String key){

        return new Md5Hash(sources,key).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("1", "java"));
    }
}
