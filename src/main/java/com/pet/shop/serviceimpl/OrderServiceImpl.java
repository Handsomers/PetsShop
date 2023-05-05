package com.pet.shop.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.shop.eunm.OrderStatus;
import com.pet.shop.mapper.GoodsMapper;
import com.pet.shop.mapper.OrderInfoMapper;
import com.pet.shop.mapper.OrderMapper;
import com.pet.shop.mapper.UserMapper;
import com.pet.shop.model.Order;
import com.pet.shop.model.OrderInfo;
import com.pet.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

/**
 * 订单服务实现类
 * @author 昂太
 *
 */

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addIntoOrder(Order order) {
        Assert.notNull(order,"订单内容不能为空");
        try {
            //新增订单
            orderMapper.add(order);
        } catch (Exception e) {
          log.error("新增订单失败!",e);
            e.printStackTrace();
        }
    }

    @Override
    public void addOrderInfo(OrderInfo orderInfo) {
        Assert.notNull(orderInfo,"订单详情不能为空");
        try {
            //新增订单详情
            orderInfoMapper.add(orderInfo);
        } catch (Exception e) {
            log.error("新增订单详情失败!",e);
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<Order> queryAllOrder(Order order) {
        if(Objects.isNull(order.getPageNo()) || order.getPageNo() < 0 ){
            order.setPageNo(1);
        }
        if(Objects.isNull(order.getPageSize()) ||  order.getPageSize() < 0){
            order.setPageSize(10);
        }
        PageHelper.startPage(order.getPageNo(),order.getPageSize());
        List<Order> orders = null;
        try {
            orders = orderMapper.queryAllOrder(order);
            orders.forEach(x->{
                //订单状态
                x.setStatusDesc(OrderStatus.getEnum(x.getStatus()).getMsg());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<Order> pageInfo =new PageInfo<>(orders);
        return pageInfo;
    }

    @Override
    public List<OrderInfo> queryOneOrderInfo(String orderId) {
        Assert.notNull(orderId,"订单ID不能为空!");
        List<OrderInfo> orderInfos = null;
        try {
             orderInfos = orderInfoMapper.queryOrderInfo(orderId);
             orderInfos.forEach(x->{
                 x.setImage(goodsMapper.queryOneGoodByGid(x.getGid()).getImage());
             });
        } catch (Exception e) {
            log.error("查看订单详情失败!");
            e.printStackTrace();
        }
        return orderInfos;
    }

}