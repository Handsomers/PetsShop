package com.pet.shop.controller;

import com.github.pagehelper.PageInfo;
import com.pet.shop.mapper.OrderMapper;
import com.pet.shop.model.Cart;
import com.pet.shop.model.Order;
import com.pet.shop.model.OrderInfo;
import com.pet.shop.model.User;
import com.pet.shop.service.CartService;
import com.pet.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 订单Controller
 */

@Controller("/Order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Resource
    private OrderMapper orderMapper;



    /**
     * 购物下单
     * @param order
     * @param httpSession
     * @return
     */
    @RequestMapping("/order")
    public String addIntoOrder(Order order, HttpSession httpSession) {
        //页面只传手机号和地址

        Object user = httpSession.getAttribute("User");
        if (Objects.isNull(user)) {
            throw new RuntimeException("未登录!");
        }
        User loginUser = (User) user;
        //购物者ID
        order.setBid(loginUser.getId());
        //订单状态 2,待支付
        order.setStatus(2);
        //订单编号
        String orderId = "SF" + System.currentTimeMillis();
        order.setOrderId(orderId);
        //订单创建时间
        order.setCreatTime(new Date());
        orderService.addIntoOrder(order);


        //将购物车的信息添加到订单详情中
        List<Cart> carts = cartService.queryAll(loginUser.getId());
        carts.forEach(x -> {
            OrderInfo orderInfo = new OrderInfo();
            //订单详情的订单号
            orderInfo.setOrderId(orderId);
            //商品Id
            orderInfo.setGid(x.getGid());
            //商品名字
            orderInfo.setName(x.getName());
            //商品价格
            orderInfo.setPrice(x.getPrice());
            //商品数量
            orderInfo.setNum(x.getNum());
            //商品详情表
            orderService.addOrderInfo(orderInfo);

            //将购物车状态 逻辑删除
            cartService.deleteCart(x.getId());

        });
        return "redirect:/queryCurrentAllOrders";
    }

    /**
     * 删除购物车中的商品
     */
    @RequestMapping("/deleteCartGood")
    public String deleteCartGoods(Integer id){
        Assert.notNull(id,"商品ID不能为空");
        try {
            cartService.deleteCart(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/Cart/queryCart";
    }

    /**
     * 下单选择收货地址和手机号
     */
    @RequestMapping("/receipt")
    public String receipt(){
        return "receipt";
    }


    /**
     * 前台查看当前登陆者的全部订单
     */
    @RequestMapping("/queryCurrentAllOrders")
    public String queryCurrentAllOrders(Order order, Model model,HttpSession httpSession){
        Object user = httpSession.getAttribute("User");
        if (Objects.isNull(user)) {
                return "redirect:/login";
        }
        User loginUser = (User) user;
        //购物者ID
        order.setBid(loginUser.getId());
        PageInfo<Order> orderPageInfo = orderService.queryAllOrder(order);
        model.addAttribute("orders",orderPageInfo.getList());
        model.addAttribute("Pages",orderPageInfo);
        return "order-list";
    }

    /**
     * 后台查看全部订单
     */
    @RequestMapping("/queryAllOrders")
    public String queryAllOrders(Order order, Model model){
        PageInfo<Order> orderPageInfo = orderService.queryAllOrder(order);
        model.addAttribute("orders",orderPageInfo.getList());
        model.addAttribute("Pages",orderPageInfo);
        return "admin/order-list";
    }

    /**
     * 查看订单详情
     */
    @RequestMapping("/queryOrderInfo")
    public String queryOrderInfo(String id,Model model){
        model.addAttribute("orderInfo",orderService.queryOneOrderInfo(id));
        return "admin/orderInfo";
    }

    /**
     * 发货
     */
    @RequestMapping("/send")
    public String sendGoods(String id){
        try {
            Order order = new Order();
            order.setOrderId(id);
            order.setSendTime(new Date());

            orderMapper.update(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/queryAllOrders";

    }

    /**
     * 二维码
     * @param orderId 订单号
     */
    @RequestMapping("/qrCode")
        public String qrCode(String orderId,Model model){
         model.addAttribute("orderId",orderId);
        return "qrCode";
    }

    /**
     * 支付
     */
    @ResponseBody
    @RequestMapping("/pay")
    public Integer pay(String id){
        try {
            orderMapper.pay(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
//        return "redirect:/queryCurrentAllOrders";

    }
}