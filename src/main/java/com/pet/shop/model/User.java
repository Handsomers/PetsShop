package com.pet.shop.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户类
 */
public class User {
    /**
     * 自增id
     */
    public Integer id;
    /**
     * 用户名
     */
    public String name;
    /**
     * 密码
     */
    public String pwd;
    /**
     * 手机号
     */
    public String phone;
    /**
     * 权限
     */
    public Integer identity;
    /**
     * 地址
     */
    public String place;

    /**
     * 注册时间
     */
    public Date creatTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
