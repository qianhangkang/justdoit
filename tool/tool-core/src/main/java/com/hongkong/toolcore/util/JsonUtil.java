package com.hongkong.toolcore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * JSON 工具类
 *
 * @author HANGKANG
 * @date 2018/6/8 下午5:23
 */

@Slf4j
public class JsonUtil {
    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象转换成json，若出现异常则返回null
     *
     * @param object 被转换的对象
     * @return json
     */
    public static String convertObjectToJson(Object object) {
        Preconditions.checkNotNull(object, "转换的对象为null");
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.debug("对象{}解析为json异常", object.toString());
            return null;
        }
    }

    /**
     * 将json字符串转换成Object，若出现异常则返回null
     *
     * @param json json
     * @return 对象
     */
    public static Object convertJsonToObject(String json, Class<?> valueType) {
        Preconditions.checkNotNull(json, "被转换的json为null");
        try {
            return mapper.readValue(json, valueType);
        } catch (IOException e) {
            log.debug("解析json异常{}，原始数据为{}", e.getMessage(),json);
            return null;
        }
    }
}
