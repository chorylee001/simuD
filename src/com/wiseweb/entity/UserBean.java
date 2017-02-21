package com.wiseweb.entity;

import java.util.Date;

/**
 * Created by Chory on 2017/2/16 0016.
 * 注册用户实体类
 */
public class UserBean {

    /**
     * `id` INT(11) NOT NULL auto_increment,
     * `username` VARCHAR(100) NOT NULL COMMENT '用户名',
     * `password` VARCHAR(100) NOT NULL COMMENT '密码',
     * `certificate_code` VARCHAR(20) NOT NULL COMMENT '身份证号',
     * `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
     * `register_time` DATETIME NOT NULL COMMENT '注册时间',
     * `register_ip` VARCHAR(50) NOT NULL DEFAULT '127.0.0.1' COMMENT '注册IP',
     * `last_login_time` DATETIME NOT NULL COMMENT '最后登录时间',
     * `last_login_ip` VARCHAR(50) NOT NULL DEFAULT '127.0.0.1' COMMENT '最后登录IP',
     * `login_count` INT(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
     * `realname` VARCHAR(100) DEFAULT NULL COMMENT '真实姓名',
     * `gender` TINYINT(1) DEFAULT NULL COMMENT '性别',
     * `birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
     * `intro` VARCHAR(255) DEFAULT NULL COMMENT '个人介绍',
     * `comefrom` VARCHAR(150) DEFAULT NULL COMMENT '来自',
     * `qq` VARCHAR(100) DEFAULT NULL COMMENT 'QQ',
     * `phone` VARCHAR(50) DEFAULT NULL COMMENT '电话',
     * `mobile` VARCHAR(50) DEFAULT NULL COMMENT '手机',
     */
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Date registerTime;
    private String registerIp;
    private Date lastLoginTime;
    private String lastLoginIp;
    private Integer loginCount;
    private String realname;
    private int gender;
    private String birthday;
    private String intro;
    private String comfrom;
    private String QQ;
    private String phone;
    private String mobile;

    public UserBean() {
    }

    public UserBean(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getComfrom() {
        return comfrom;
    }

    public void setComfrom(String comfrom) {
        this.comfrom = comfrom;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
