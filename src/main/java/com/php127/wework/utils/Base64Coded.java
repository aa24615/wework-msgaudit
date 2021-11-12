/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */


package com.php127.wework.utils;


import org.apache.commons.codec.binary.Base64;

public class Base64Coded {

    //base64 解码
    public static String decode(String text) {
        return new String(Base64.decodeBase64(text.getBytes()));
    }

    //base64 编码
    public static String encode(String text) {
        return new String(Base64.encodeBase64(text.getBytes()));
    }

}


