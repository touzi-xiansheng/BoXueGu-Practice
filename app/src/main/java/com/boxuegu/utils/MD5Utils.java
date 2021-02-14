package com.boxuegu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
    * MD5加密算法
    */
    public static String md5(String text) {
        try {
            /*拿到数据指纹对象，数据为MD5*/
            MessageDigest digest = MessageDigest.getInstance("md5");
            /*对原文生成数据指纹*/
            byte[] result = digest.digest(text.getBytes());
            /*十六进制转换*/
            StringBuilder sb = new StringBuilder();
            /*对数组中所有字节进行转换*/
            for (byte b :result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                /*如果转换后字符是一位，进行拼接*/
                if(hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            /*如果上述代码出现异常，返回null*/
            return null;
        }
    }
}


