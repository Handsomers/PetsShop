package com.pet.shop.model;

import lombok.Data;

/**
 * 轮播图管理
 */

@Data
public class PhotoShow {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 图片名字
     */
    private String name;

    /**
     * 图片地址
     */
    private String image;
}