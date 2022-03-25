package com.php127.wework.utils;

import java.security.MessageDigest;

public class MD5Util {
    public static String md5(String txt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 问题主要出在这里，Java的字符串是unicode编码，不受源码文件的编码影响；而PHP的编码是和源码文件的编码一致，受源码编码影响。
            md.update(txt.getBytes("GBK"));
            StringBuffer buf = new StringBuffer();
            for (byte b : md.digest()) {
                buf.append(String.format("%02x", b & 0xff));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
