package com.pet.shop.model;


/**
 * 购物车实体类
 * @author 昂太
 *
 */
public class Cart {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 商品ID
     */
    private Integer gid;
    /**
     * 购物者ID
     */
    private Integer bid;
    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 逻辑删除标志
     */
    private Integer status;

    /**
     * 图片名称
     */
    private String image;

    /**
     * 商品名字
     */
    private String name;

    /**
     * 商品价格
     */
    private String price;

    /**
     * 总价格
     */
    private Double totalAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }



    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}