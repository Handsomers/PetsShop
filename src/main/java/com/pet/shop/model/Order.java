package com.pet.shop.model;


import lombok.Data;

import java.util.Date;

/**
 * 订单实体类
 *
 * @author 昂太 (angtai@maihaoche.com)
 *
 */

public class Order {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 购物者ID
     */
    private Integer bid;

    /**
     * 购物着姓名
     */
    private String name;

    /**
     * 收货手机号
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 订单状态 0,废弃1,完成，2,待支付,3,待发货,4,已发货
     */
    private Integer status;

    private String statusDesc;

    /**
     * 订单创建时间
     */
    private Date creatTime;

    /**
     * 订单发货时间
     */
    private Date sendTime;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页大小
     */
    private Integer pageSize;

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

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}