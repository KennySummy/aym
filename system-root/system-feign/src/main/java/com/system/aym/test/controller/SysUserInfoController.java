package com.system.aym.test.controller;

import com.system.aym.common.bean.ResponseData;
import com.system.aym.test.feign.SysUserInfoFeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SysUserInfoController {

    @Resource
    private SysUserInfoFeignClient sysUserInfoFeignClient;

    @PostMapping(value = "/into")
    String intoUserInfo(String userPhone, String userPwd){
        return sysUserInfoFeignClient.intoUserInfo(userPhone,userPwd);
    }

    @PostMapping(value = "/findPhone")
    String findByUserPhoneForUserInfo(String userPhone){
        return sysUserInfoFeignClient.findByUserPhoneForUserInfo(userPhone);
    }

    @PostMapping(value = "/findCode")
    String findByUserCodeForUserInfo(String userCode){
        return sysUserInfoFeignClient.findByUserCodeForUserInfo(userCode);
    }



}
