package com.pet.shop.model;

import lombok.Data;

/**
 * 商品类
 * @author 昂太
 */

public class Goods {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格
     */
    private String price;
    /**
     * 一级标题
     */
    private Integer supType;
    /**
     * 一级标题名称
     */
    private String supTypeDesc;
    /**
     * 二级标题
     */
    private Integer subType;
    /**
     * 二级标题名称
     */
    private String subTypeDesc;
    /**
     * 是否推荐(1,推荐，0,普通)
     */
    private Integer isRecommend;
    /**
     * 数据库保存的图片名字
     */
    private String image;
    /**
     * 宠物性别(0:母,1:公的)
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页大小
     */
    private Integer pageSize;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getSex() {
        return sex;
    }

    public Integer getSubType() {
        return subType;
    }

    public Integer getSupType() {
        return supType;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getSubTypeDesc() {
        return subTypeDesc;
    }

    public String getSupTypeDesc() {
        return supTypeDesc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public void setSubTypeDesc(String subTypeDesc) {
        this.subTypeDesc = subTypeDesc;
    }

    public void setSupType(Integer supType) {
        this.supType = supType;
    }

    public void setSupTypeDesc(String supTypeDesc) {
        this.supTypeDesc = supTypeDesc;
    }
}