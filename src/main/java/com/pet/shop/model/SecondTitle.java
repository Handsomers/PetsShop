package com.pet.shop.model;

import lombok.Data;

/**
 * 二级标题实体类
 * @author 昂太
 *
 */

@Data
public class SecondTitle {
    /**
     * id
     */
    private Integer id;
    /**
     * 关联的一级标题ID
     */
    private Integer refId;
    /**
     * 一级标题对应的名称
     */
    private String refIdDesc;

    /**
     * 二级标题
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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getRefId() {
        return refId;
    }

    public String getRefIdDesc() {
        return refIdDesc;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public void setRefIdDesc(String refIdDesc) {
        this.refIdDesc = refIdDesc;
    }
}