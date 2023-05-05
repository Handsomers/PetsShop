package com.pet.shop.service;

import com.pet.shop.model.User;

/**
 * 用户登录注册服务
 * @author 昂太
 *
 */
public interface UserService {
    /**
     * 用户登录服务
     */
     int login(User user);

    /**
     * 注册服务
     */
    Integer register(User user);

    /**
     * 管理员登录
     */
    Integer adminLogin(User user);

}
