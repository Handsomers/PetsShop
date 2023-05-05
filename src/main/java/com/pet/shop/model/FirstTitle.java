package com.pet.shop.model;

import lombok.Data;

/**
 * 一级标题实体类
 * @author 昂太
 *
 */

public class FirstTitle {
    /**
     * id
     */
    private Integer id;

    /**
     * 一级标题
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getName() {
        return name;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}