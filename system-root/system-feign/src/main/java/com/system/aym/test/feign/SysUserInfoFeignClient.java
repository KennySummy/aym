package com.system.aym.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient("TEST-AYM-API")
public interface SysUserInfoFeignClient {

    @PostMapping(value = "/into")
    String intoUserInfo(@RequestParam("userPhone") String userPhone, @RequestParam("userPwd") String userPwd);

    @PostMapping(value = "/findPhone")
    @ResponseBody
    String findByUserPhoneForUserInfo(@RequestParam("userPhone") String userPhone);

    @PostMapping(value = "/findCode")
    @ResponseBody
    String findByUserCodeForUserInfo(@RequestParam("userCode") String userCode);
}
