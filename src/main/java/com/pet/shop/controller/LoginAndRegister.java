package com.pet.shop.controller;

import com.pet.shop.mapper.UserMapper;
import com.pet.shop.model.User;
import com.pet.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 登录注册Controller
 * @Package com.pet.shop.controller
 * @author: sunjie
 *
 */
@Controller
@RequestMapping("/Login")
public class LoginAndRegister {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public int login(User user, HttpSession httpSession){
        int result = userService.login(user);
        if(result == 0){
            //将当前用户信息保存在Session中,过期时间为一天
            httpSession.setAttribute("User",userMapper.login(user));
            httpSession.setMaxInactiveInterval(60*60*24);
        }
        return result;

    }

    /**
     * 用户注册
     * @param user
     * @return Integer 0:注册成功 1:用户名重复
     *
     */
    @ResponseBody
    @RequestMapping("/register")
    public Integer register(User user){
        Integer result = userService.register(user);
        return  result;
    }

    /**
     *  管理员登录
     * @param user
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "adminLogin",method = RequestMethod.POST)
    public Integer adminLogin(User user,HttpSession httpSession){
        Integer flag = userService.adminLogin(user);
        if(flag == 0){
            httpSession.setAttribute("admin",userMapper.adminLogin(user));
            httpSession.setMaxInactiveInterval(60*60*24);
        }
        return flag;
    }

    /**
     * 查看所有用户
     */
    @RequestMapping("/queryAllUser")
    public String queryAllUser(Model model){
        model.addAttribute("Users",userMapper.queryAllUser());
        return "admin/admin-list";
    }
}