package com.pet.shop.serviceimpl;

import com.pet.shop.mapper.UserMapper;
import com.pet.shop.model.User;
import com.pet.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * 用户服务实现
 *
 * @author 昂太 (angtai@maihaoche.com)
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Override
    public int login(User user) {
        //判断成功与否的标志 0,成功,1,用户名错误;2:密码错误
        int flag = 0;

        //接收登录查询的对象
        User queryUser = new User();
        //普通用户权限为0
        user.setIdentity(0);
        try {
            queryUser = userMapper.login(user);
            if (Objects.isNull(queryUser)) {
                System.out.println("没有该用户");
                flag = 1;
            } else {
                if (queryUser.getPwd().equals(user.getPwd())) {
                    flag = 0;
                } else {
                    System.out.println("密码错误");
                    
                    flag = 2;
                }
            }
        } catch (Exception e) {
            System.out.println("登录查询失败");
            e.printStackTrace();
        }
        return flag;
    }


    @Override
    public Integer register(User user) {
        //手机号码不足11位
        if (user.getPhone().length() != 11) {
            return 2;
        }

        //flag = 0 (注册成功)  flag = 1(用户名重复,禁止注册)
        Integer flag = 0;
        //注册时候检查用户名是否重复,如果重读不允许注册
        User user1 = userMapper.queryByName(user);
        //未根据用户名查到该用户,允许注册
        if (Objects.isNull(user1)) {
            if (Objects.isNull(user)) {
                System.out.println("注册信息为空！");
                throw new RuntimeException("注册用户不能为空");
            }
            //用户权限为0
            user.setIdentity(0);
            user.setCreatTime(new Date());
            try {
                userMapper.regsiter(user);
            } catch (Exception e) {
                System.out.println("注册失败!");
                e.printStackTrace();
            }
        } else {
            //注册的用户名已经存在了
            flag = 1;
        }
        return flag;
    }

    @Override
    public Integer adminLogin(User user) {
        Integer flag = 0;
        if (Objects.isNull(user)) {
            System.out.println("管理员登录账号为空!");
            throw new RuntimeException("管理员登录账号为空!");
        }
        User user1 = null;
        try {
            user.setIdentity(1);
            user1 = userMapper.adminLogin(user);
        } catch (Exception e) {
            System.out.println("管理员登录查询失败");
            e.printStackTrace();
        }
        if (Objects.isNull(user1)) {
            //用户名错误
            flag = 1;
        } else {
            if (!user1.getPwd().equals(user.getPwd())) {
                //密码错误
                flag = 2;
            }
        }
        return flag;
    }

}
