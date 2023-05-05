package com.pet.shop.controller;

import com.pet.shop.model.Cart;
import com.pet.shop.model.User;
import com.pet.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 购物车 Controller
 * @author 昂太 (angtai@maihaoche.com)
 *
 */

@RequestMapping("/Cart")
@Controller
public class CartController {
    @Autowired
    private CartService cartService;


    /**
     * 查看购物车
     * @return
     */
    @RequestMapping("/queryCart")
    public String queryAll(Model model,HttpSession httpSession){
        Object user = httpSession.getAttribute("User");
        if(Objects.isNull(user)){
            return "redirect:/login";
        }
        User user1 = (User) user;
        Assert.notNull(user1.getId(),"当前登录者ID不能为空!");
         model.addAttribute("Cart",cartService.queryAll(user1.getId()));
         return "shopcart";
    }

    /**
     * 添加购物车
     */
    @ResponseBody
    @RequestMapping("/addIntoCart")
    public String addIntoCart(Cart cart,HttpSession httpSession){
        Assert.notNull(cart,"添加到购物车的商品不能为空");
        //订单可用
        Object user = httpSession.getAttribute("User");
        if(Objects.isNull(user)){
            return "未登录";
        }
        User user1 = (User) user;
        //登录用户ID
        cart.setBid(user1.getId());
        //订单状态可用
        cart.setStatus(1);
        cartService.addIntoCart(cart);
        return "success";
    }
}