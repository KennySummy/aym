package com.system.aym.model;

import java.util.Date;

public class SysUserInfo {
    /**
     * 主键ID
     * 表字段 : sys_user_info.id
     */
    private Integer id;

    /**
     * 用户账号
     * 表字段 : sys_user_info.user_code
     */
    private String userCode;

    /**
     * 用户手机号码
     * 表字段 : sys_user_info.user_phone
     */
    private String userPhone;

    /**
     * 用户密码
     * 表字段 : sys_user_info.user_pwd
     */
    private String userPwd;

    /**
     * 用户姓名
     * 表字段 : sys_user_info.user_name
     */
    private String userName;

    /**
     * 用户别名
     * 表字段 : sys_user_info.user_alias
     */
    private String userAlias;

    /**
     * 邮箱地址
     * 表字段 : sys_user_info.email_addr
     */
    private String emailAddr;

    /**
     * 新建者
     * 表字段 : sys_user_info.into_user
     */
    private String intoUser;

    /**
     * 新建时间
     * 表字段 : sys_user_info.into_time
     */
    private Date intoTime;

    /**
     * 更新者
     * 表字段 : sys_user_info.upd_user
     */
    private String updUser;

    /**
     * 更新时间
     * 表字段 : sys_user_info.upd_time
     */
    private Date updTime;

    /**
     * 备注
     * 表字段 : sys_user_info.remarks
     */
    private String remarks;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户账号
     * @return user_code 用户账号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 用户账号
     * @param userCode 用户账号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 用户手机号码
     * @return user_phone 用户手机号码
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 用户手机号码
     * @param userPhone 用户手机号码
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 用户密码
     * @return user_pwd 用户密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 用户密码
     * @param userPwd 用户密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    /**
     * 用户姓名
     * @return user_name 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户姓名
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 用户别名
     * @return user_alias 用户别名
     */
    public String getUserAlias() {
        return userAlias;
    }

    /**
     * 用户别名
     * @param userAlias 用户别名
     */
    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias == null ? null : userAlias.trim();
    }

    /**
     * 邮箱地址
     * @return email_addr 邮箱地址
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * 邮箱地址
     * @param emailAddr 邮箱地址
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr == null ? null : emailAddr.trim();
    }

    /**
     * 新建者
     * @return into_user 新建者
     */
    public String getIntoUser() {
        return intoUser;
    }

    /**
     * 新建者
     * @param intoUser 新建者
     */
    public void setIntoUser(String intoUser) {
        this.intoUser = intoUser == null ? null : intoUser.trim();
    }

    /**
     * 新建时间
     * @return into_time 新建时间
     */
    public Date getIntoTime() {
        return intoTime;
    }

    /**
     * 新建时间
     * @param intoTime 新建时间
     */
    public void setIntoTime(Date intoTime) {
        this.intoTime = intoTime;
    }

    /**
     * 更新者
     * @return upd_user 更新者
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * 更新者
     * @param updUser 更新者
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser == null ? null : updUser.trim();
    }

    /**
     * 更新时间
     * @return upd_time 更新时间
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * 更新时间
     * @param updTime 更新时间
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * 备注
     * @return remarks 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}
