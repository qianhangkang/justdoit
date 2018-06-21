package com.hongkong.toolcore.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.Objects;

/**
 * Base64加解密类
 *
 * @author HANGKANG
 * @date 2018/5/25 上午9:46
 */

@Slf4j
public class Base64Encode {

    /**
     * 将base64编码的字符串解密
     * @param s 加密的字符串
     * @return 解密后的字符串
     */
    public static String decode(String s) {
        if (Objects.isNull(s)) {
            return null;
        }
        /*
         * base64编码规定一行不能超过76字符，否则添加回车换行符，因此解析时需要去掉，
         * 而windows回车换行符为\r\n，Linux下为\n，Mac下为\r
         */
        byte[] results = Base64.getDecoder().decode(s.replaceAll("\r|\n", ""));
        return new String(results);
    }

    /**
     * 将字符串加密
     * @param s 待加密字符串
     * @return base64加密后的字符串
     */
    public static String encode(String s) {
        if (Objects.isNull(s)) {
            return null;
        }
        return Base64.getEncoder().encodeToString(s.getBytes());
    }
}
