package com.system.aym.test.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RestController
public class SysUserInfoController {

    @Resource
    private RestTemplate restTemplate;

    /**
     * post 提交多个参数
     * @param userPhone
     * @param userPwd
     * @return
     */
    @PostMapping(value = "/into-ribbon")
    @ResponseBody
    public String intoUserInfo(String userPhone, String userPwd){

        String url = "http://TEST-AYM-API/into";

        // 接收返回数据
        ResponseEntity<String> responseEntity = null;

        // 定制http header格式内容
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> accept = new LinkedList<>();
        accept.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(accept);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 分散参数加入 MultiValueMap 中
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("userPhone", userPhone);
        postParameters.add("userPwd", userPwd);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(postParameters, headers);

        responseEntity  = restTemplate.postForEntity(url, requestEntity, String.class);

        String body = responseEntity.getBody();
        return body;
    }

}
