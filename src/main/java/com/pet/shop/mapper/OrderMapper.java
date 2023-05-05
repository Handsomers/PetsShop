package com.pet.shop.mapper;

import com.pet.shop.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;
/**
 * 订单实体类
 * @author 昂太
 *
 */
@Mapper
public interface OrderMapper {

    /**
     * 新增订单
     * @param order
     */
   @Insert("insert into orders(order_id,bid,phone,address,status,creat_time) " +
           "values(#{orderId},#{bid},#{phone},#{address},#{status},#{creatTime})")
    void add(Order order);

    /**
     * 后台查询所有订单
     */
   @Select({"<script>",
           "select o.order_id,o.bid,o.phone,o.address,o.status,o.creat_time,o.send_time,u.name from orders o ,user u",
           "<where>",
           "and o.bid = u.id",
           "<if test=\"status != null and status !='' \">",
           "and status = #{status}",
           "</if>",

           "<if test=\"orderId != null and orderId !='' \">",
           "and order_id = #{orderId}",
           "</if>",

           "<if test=\"bid != null and bid !='' \">",
           "and bid = #{bid}",
           "</if>",

           "</where>",
           "</script>"})
    List<Order> queryAllOrder(Order order);


    /**
     * 查询指定订单
     */
    @Select("select * from orders where id = #{id} ")
    Order queryOneOrder(Integer id);

    /**
     * 发货
     */
   @Update("update orders set status = 4,send_time = #{sendTime} where order_id = #{orderId} ")
   void update(Order order);

    /**
     * 支付
     */
    @Update("update orders set status = 3 where order_id = #{orderId} ")
    void pay(String id);


}
