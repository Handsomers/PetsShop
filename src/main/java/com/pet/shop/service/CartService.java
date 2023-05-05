package com.pet.shop.service;

import com.pet.shop.model.Cart;
import java.util.List;


/**
 * 购物车Service
 * @author 昂太
 *
 */
public interface CartService {
    /**
     * 添加购物车
     * @param cart
     */
    void addIntoCart(Cart cart);

    /**
     * 查询当前登陆者的购物车
     * @param id 登陆者ID
     * @return
     */
    List<Cart> queryAll(Integer id);

    /**
     * 逻辑删除
     * @param id 购物车ID
     */
    void deleteCart(Integer id);
}
