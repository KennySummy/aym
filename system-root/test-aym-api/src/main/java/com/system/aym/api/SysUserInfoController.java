package com.system.aym.api;

import com.system.aym.common.bean.ResponseData;
import com.system.aym.common.controller.BaseController;
import com.system.aym.model.SysUserInfo;
import com.system.service.impl.SysUserInfoServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SysUserInfoController extends BaseController {

    @Resource
    private SysUserInfoServiceImpl service;

    @PostMapping(value = "/into")
    @ResponseBody
    public ResponseData intoUserInfo(@Param("userPhone") String userPhone, @Param("userPwd")String userPwd){
        boolean flag = false;
        // 判断传递参数是否正确
        if(StringUtils.isEmpty(userPhone)) {
            return ResponseData.paramError();
        }
        if(StringUtils.isEmpty(userPwd)){
            return ResponseData.paramError();
        }
        // 判断该用户是否存在系统内，存在系统则不能重复注册
        SysUserInfo isNot_userInfo = service.findByUserPhone(userPhone);
        if(isNot_userInfo != null){
            return ResponseData.objectExists();
        }
        // 实例化 SysUserInfo，将参数写入实体类
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setUserPhone(userPhone);
        userInfo.setUserPwd(userPwd);
        flag = service.insertRecord(userInfo);
        // 判断数据新增是否正常
        if (flag){
            return ResponseData.ok();
        } else {
            return ResponseData.serverInternalError();
        }
    }

    @PostMapping(value = "/findPhone")
    @ResponseBody
    public ResponseData findByUserPhoneForUserInfo(@Param("userPhone") String userPhone){
        // 判断传递参数是否正确
        if(StringUtils.isEmpty(userPhone)) {
            return ResponseData.paramError();
        }
        SysUserInfo userInfo = service.findByUserPhone(userPhone);
        if(userInfo!=null)
            return ResponseData.ok().putDataValue("userInfo", userInfo);
        return ResponseData.notFound();
    }

    @PostMapping(value = "/findCode")
    @ResponseBody
    public ResponseData findByUserCodeForUserInfo(@Param("userCode") String userCode) {
        // 判断传递参数是否正确
        if(StringUtils.isEmpty(userCode)) {
            return ResponseData.paramError();
        }
        SysUserInfo userInfo = service.findByUserCode(userCode);
        if(userInfo!=null)
            return ResponseData.ok().putDataValue("userInfo", userInfo);
        return ResponseData.notFound();
    }

}

