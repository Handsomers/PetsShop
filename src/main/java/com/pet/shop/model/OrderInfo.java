package com.pet.shop.model;

import lombok.Data;

/**
 * 订单详情表
 * @author 昂太
 *
 */

public class OrderInfo {
    /**
     * 自增ID
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 商品ID
     */
    private Integer gid;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 商品价格
     */
    private String price;

    /**
     * 商品购买总量
     */
    private Integer total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}