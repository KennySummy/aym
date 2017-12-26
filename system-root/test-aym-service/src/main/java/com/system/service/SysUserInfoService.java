package com.system.service;

import com.system.aym.model.SysUserInfo;

public interface SysUserInfoService {

    /**
     * 新增用户信息
     * @param record
     * @return
     */
    boolean insertRecord(SysUserInfo record);

    /**
     * 根据用户UserCode查询用户信息
     * @param userCode
     * @return
     */
    SysUserInfo findByUserCode(String userCode);

    /**
     * 根据用户电话号码查询用户信息
     * @param userPhone
     * @return
     */
    SysUserInfo findByUserPhone(String userPhone);

}
