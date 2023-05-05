package com.pet.shop.service;

import com.pet.shop.model.PhotoShow;
import java.util.List;

/**
 * 轮播图管理
 */
public interface PhotoShowService {

    /**
     * 查看全部轮播图
     */
    List<PhotoShow> queryAll();

    /**
     * 添加轮播图
     * @param photoShow
     */
    void addPhoto(PhotoShow photoShow);

    /**
     * 删除轮播图
     * @param id 图片ID
     */
    void deletePhoto(Integer id);


}
