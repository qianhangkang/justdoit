package com.hongkong.toolcore.util;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * POST请求工具类
 *
 * @author HANGKANG
 * @date 2018/5/24 下午5:47
 */
@Slf4j
public class HttpClient {

    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * 发送post请求
     * @param url 请求地址
     * @param parameters 请求参数的json（以json格式发送）
     * @return ResponseEntity
     */
    public static ResponseEntity sendPostRequest(String url, String parameters) {
        Preconditions.checkNotNull(url, "url为null");
        Preconditions.checkNotNull(parameters, "parameters为null");
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(parameters, headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response;
    }
}
