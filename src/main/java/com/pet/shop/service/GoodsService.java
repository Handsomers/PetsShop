package com.pet.shop.service;

import com.github.pagehelper.PageInfo;
import com.pet.shop.model.Goods;
import com.pet.shop.model.OrderInfo;

import java.util.List;
/**
 * 商品服务类
 * @author 昂太 (angtai@maihaoche.com)
 */
public interface GoodsService {
    /**
     * 查询所有商品（支持多条件查询）
     */
    PageInfo<Goods> queryAllGoods(Goods goods);

    /**
     * 根据主键ID删除商品
     * @param id
     */
    void deleteOneGood(Integer id);

    /**
     * 添加商品
     */
    void addGoods(Goods goods);

    /**
     * 查询所有推荐商品(首页显示使用)
     */
    List<Goods> queryAllGoodsByRecommend();

    /**
     * 查询5个不同类型的推荐
     */
    List<Goods> queryDiffRecommendByType(Integer supType);

    /**
     * 查看热销
     * @return
     */
    List<OrderInfo> queryHot();

    void updateGood(Goods goods);
}
