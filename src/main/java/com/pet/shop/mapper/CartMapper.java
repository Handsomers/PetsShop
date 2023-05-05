package com.pet.shop.mapper;

import com.pet.shop.model.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;


/**
 * 购物车 Mapper
 * @author 昂太
 *
 */
@Mapper
public interface CartMapper {

    /**
     * 添加购物车
     * @param cart
     */
    @Insert("INSERT into cart(gid,bid,num,status) values(#{gid},#{bid},#{num},#{status})")
    void addIntoCart(Cart cart);

    /**
     * 查询当前登录用户购物车数据
     * @return
     */
    @Select("SELECT * from cart where bid=#{bid} and status = 1")
    List<Cart> queryAll(Integer id);

    /**
     * 逻辑删除购物车(下单之后需要逻辑删除购物车)
     * @param id 购物车ID
     */
    @Update("UPDATE cart set status = 0 where id = #{id}")
    void updateStatus(Integer id);

}
