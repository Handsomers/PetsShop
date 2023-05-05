package com.pet.shop.controller;


import com.github.pagehelper.PageInfo;
import com.pet.shop.model.Goods;
import com.pet.shop.service.GoodsService;
import com.pet.shop.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商城Controller
 * @author 昂太
 *
 */
@RequestMapping("/shop")
@Controller
public class ShopController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TitleService titleService;

    /**
     * 前台查看全部商品
     * @return
     */
    @RequestMapping("/queryAllGoods")
    public String queryAllGoods(Goods goods, Model model){
        PageInfo<Goods> pageInfo = goodsService.queryAllGoods(goods);
        model.addAttribute("Goods",pageInfo.getList());
        model.addAttribute("Pages",pageInfo);
        //分类
        model.addAttribute("Title",titleService.queryAllTitle());
        return "goodshop";
    }


    /**
     * 前台搜索商品
     */
    @RequestMapping("/queryGood")
    public String queryGood(Goods goods, Model model){
        PageInfo<Goods> pageInfo = goodsService.queryAllGoods(goods);
        model.addAttribute("Goods",pageInfo.getList());
        model.addAttribute("Pages",pageInfo);
        //分类
        model.addAttribute("Title",titleService.queryAllTitle());
        return "goodshop";
    }

}