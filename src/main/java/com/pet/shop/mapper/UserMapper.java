package com.pet.shop.mapper;

import com.pet.shop.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 用户Mapper
 *
 * @author 昂太 (angtai@maihaoche.com)
 */
@Mapper
public interface UserMapper {
    /**
     * 用户登录
     */
    @Select("select * from user where phone =#{phone} or identity =#{identity}")
    User login(User user);

    /**
     * 用户注册名是重复验证
     */
    @Select("select * from user where name =#{name}")
    User queryByName(User user);

    /**
     * 注册
     */
    @Insert("insert into user(name,pwd,phone,identity,creat_time)" +
            " values(#{name},#{pwd},#{phone},#{identity},#{creatTime} ) ")
    void regsiter(User user);

    /**
     * 管理员登录
     * @return
     */
    @Select("select * from user where name = #{name} and identity =#{identity}")
    User adminLogin(User user);

    /**
     * 查询全部用户
     * @return
     */
    @Select("select * from user")
    List<User> queryAllUser();
}
