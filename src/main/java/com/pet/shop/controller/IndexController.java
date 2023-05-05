package com.pet.shop.controller;

import com.pet.shop.eunm.OrderStatus;
import com.pet.shop.model.Goods;
import com.pet.shop.model.Order;
import com.pet.shop.service.GoodsService;
import com.pet.shop.service.OrderService;
import com.pet.shop.service.PhotoShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图控制器
 * @author 孙杰
 *
 */

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PhotoShowService photoShowService;

    /**
     * 首页视图
     */
    @RequestMapping("/")
    public String  hello(Model model){
        //log.info("准备进入首页！");
        System.out.println("准备进入首页！");
        //全部推荐
        model.addAttribute("Recommend",goodsService.queryAllGoodsByRecommend());
        //宠物狗推荐
        model.addAttribute("PetsRecommend",goodsService.queryDiffRecommendByType(1));
        //宠物粮食推荐
        model.addAttribute("PetsFood",goodsService.queryDiffRecommendByType(2));
        //宠物玩具推荐
        model.addAttribute("PetsToys",goodsService.queryDiffRecommendByType(3));
        //轮播图
        model.addAttribute("Photo",photoShowService.queryAll());
        //热卖排行榜前十位
        model.addAttribute("Hot",goodsService.queryHot());
        return "index";
    }

    /**
     * 用户登录视图
     */
    @RequestMapping("/login")
    public  String login(){
        return "login";
    }

    /**
     * 返回首页
     */
    @RequestMapping("toIndex")
    public String toindex(){
        return "index";
    }

    /**
     * 跳转到注册页面
     */
    @RequestMapping("goToRegister")
    public String toRegister(){
        return "register";
    }

    /**
     * 管理员登录
     */
    @RequestMapping("adminLogin")
    public String adminLogin(){
        return "admin/login";
    }

    /**
     * 后台首页
     */
    @RequestMapping("adminIndex")
    public String adminIndex(){
        return "admin/index";
    }

    /**
     * 后台欢迎页面（代办中心）
     */
    @RequestMapping("adminWelcome")
    public String adminWelcome(Model model){
        Order o = new Order();
        o.setStatus(OrderStatus.WAITSEND.getCode());
        model.addAttribute("totalGoods",goodsService.queryAllGoods(new Goods()).getTotal());
        model.addAttribute("totalOrders",orderService.queryAllOrder(new Order()).getTotal());
        model.addAttribute("waitSend",orderService.queryAllOrder(o).getTotal());
        return "admin/welcome";
    }

    /**
     * 购物车页面
     */
    @RequestMapping("/goToShopCart")
    public String goToShopCart(){
        return "shopcart";
    }

    /**
     * 商品详情页面
     */
    @RequestMapping("/goodsInfo")
    public String goodsInfo(Integer id,Model model){
        Assert.notNull(id,"商品Id不能为空");
        Goods goods = new Goods();
        goods.setId(id);
        model.addAttribute("goodInfo",goodsService.queryAllGoods(goods).getList());
        return "goodinfo";
    }

    /**
     * 宠物资讯
     */
    @RequestMapping("/inforMation")
    public String inforMation(){
        return "information";
    }

    /**
     * 关于我们
     * @return
     */
    @RequestMapping("/about")
    public String about(){
        return "about";
    }

}
