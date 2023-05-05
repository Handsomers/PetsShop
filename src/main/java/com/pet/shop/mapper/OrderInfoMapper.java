package com.pet.shop.mapper;

import com.pet.shop.model.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.*;

/**
 * 订单详情实体类
 * @author 昂太
 *
 */
@Mapper
public interface OrderInfoMapper {

    /**
     * 新增订单详情
     * @param orderInfo 订单详情
     */
    @Insert("INSERT into order_info(order_id,gid,name,num,price)" +
            " values(#{orderId},#{gid},#{name},#{num},#{price}) ")
    void add(OrderInfo orderInfo);

    /**
     * 查看订单详情
     * @param orderId 订单ID
     * @return
     */
    @Select("select * from order_info where order_id = #{orderId}")
    List<OrderInfo> queryOrderInfo(String orderId);

    /**
     * 查询热卖排行
     */
    @Select("SELECT gid,SUM(num) total\n" +
            " from order_info \n" +
            " GROUP BY gid\n" +
            " ORDER BY SUM(num) DESC LIMIT 0,10")
    List<OrderInfo> countHot();
}
