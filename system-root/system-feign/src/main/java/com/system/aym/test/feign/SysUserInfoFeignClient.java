package com.system.aym.test.feign;

import com.system.aym.common.bean.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("TEST-AYM-API")
public interface SysUserInfoFeignClient {

    @PostMapping(value = "/into")
    String intoUserInfo(@PathVariable("userPhone") String userPhone, @PathVariable("userPwd")String userPwd);

    @PostMapping(value = "/findPhone")
    String findByUserPhoneForUserInfo(@PathVariable("userPhone") String userPhone);

    @PostMapping(value = "/findCode")
    String findByUserCodeForUserInfo(@PathVariable("userCode") String userCode);
}
