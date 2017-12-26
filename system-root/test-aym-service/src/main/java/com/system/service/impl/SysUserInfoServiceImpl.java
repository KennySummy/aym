package com.system.service.impl;

import com.system.aym.common.util.RandomUtil;
import com.system.aym.mapper.SysUserInfoMapper;
import com.system.aym.model.SysUserInfo;
import com.system.service.SysUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

    @Resource
    private SysUserInfoMapper mapper;

    @Override
    public boolean insertRecord(SysUserInfo record) {
        boolean flag = false;
        // 生成UUID——userCode
        String uuid_code = UUID.randomUUID().toString().replaceAll("-", "");
        // 用户账号生成随机串
        record.setUserCode(uuid_code);
        // 判断用户电话号码和邮箱地址不能同时为null
        if(StringUtils.isEmpty(record.getUserPhone()) && StringUtils.isEmpty(record.getEmailAddr()))
            return false;
        // 判断登录密码不能为null
        if(StringUtils.isEmpty(record.getUserPwd()))
            return false;
        // 生成随机串作为用户别名；
        record.setUserAlias(RandomUtil.getRandomString(10));
        // 添加新增时间
        record.setIntoTime(new Date());
        // 判断操作者是否为null，为null时
        if(StringUtils.isEmpty(record.getIntoUser()))
            record.setIntoUser("ADMIN");
        // 以上条件判断都为正常，新增数据
        int result = mapper.intoRecord(record);
        // 返回 1：为成功；反之失败；
        if(result == 1)
            flag = true;
        return flag;
    }

    @Override
    public SysUserInfo findByUserCode(String userCode) {
        SysUserInfo userInfo = null;
        // 判断 userCode 是否为null，为 null 则返回 null；
        if(StringUtils.isEmpty(userCode))
            return userInfo;
        userInfo = mapper.findByUserCode(userCode);
        return userInfo;
    }

    @Override
    public SysUserInfo findByUserPhone(String userPhone) {
        SysUserInfo userInfo = null;
        // 判断 userPhone 是否为 null，为 null 则返回 null；
        if(StringUtils.isEmpty(userPhone))
            return userInfo;
        userInfo = mapper.findByUserPhone(userPhone);
        return userInfo;
    }

}

