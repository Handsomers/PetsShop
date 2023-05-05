package com.pet.shop.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.shop.mapper.*;
import com.pet.shop.model.Goods;
import com.pet.shop.model.OrderInfo;
import com.pet.shop.service.GoodsService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 商品服务类实现类
 * @author 昂太 (angtai@maihaoche.com)
 */

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private FirstTitleMapper firstTitleMapper;
    @Resource
    private SecondTitleMapper secondTitleMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Goods> queryAllGoods(Goods goods) {
        if(Objects.isNull(goods.getPage()) || goods.getPage() < 0 ){
           goods.setPage(1);
        }
        if(Objects.isNull(goods.getPageSize()) ||  goods.getPageSize() < 0){
            goods.setPageSize(10);
        }
        PageHelper.startPage(goods.getPage(),goods.getPageSize());
        try {
            List<Goods> result = goodsMapper.queryAllgoods(goods);
            result.stream().forEach(x->{
                //一级标题二级标题取名字
                x.setSupTypeDesc(firstTitleMapper.queryOne(x.getSupType()).getName());
                x.setSubTypeDesc(secondTitleMapper.queryOne(x.getSubType()).getName());
            });
            PageInfo<Goods> pageInfo =new PageInfo<>(result);
            return pageInfo;
        } catch (Exception e) {
            //log.error("查询商品信息失败,入参{}",goods.toString(),e);
            System.out.println("查询商品信息失败,入参{}");
            e.printStackTrace();
        }
        return new PageInfo<>();
    }

    @Override
    public void deleteOneGood(Integer id) {
        Assert.notNull(id,"根据ID删除ID不能为空");
        try {
            goodsMapper.deleteOneGood(id);
        } catch (Exception e) {
            //log.error("根据ID={}删除失败",id,e);
            System.out.println("根据ID={}删除失败");
            e.printStackTrace();
        }
    }

    @Override
    public void addGoods(Goods goods) {
        Assert.notNull(goods,"新增商品不能为空");
        try {
            goodsMapper.addGood(goods);
        } catch (Exception e) {
            //log.error("新增商品失败,入参{}",goods,e);
            System.out.println("新增商品失败,入参{}");
            e.printStackTrace();
        }


    }

    @Override
    public List<Goods> queryAllGoodsByRecommend() {
        List<Goods> goods = null;
        try {
             goods = goodsMapper.queryAllgoodsByRecommend();
        } catch (Exception e) {
            //log.error("查询推荐商品失败",e);
            System.out.println("查询推荐商品失败");
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public List<Goods> queryDiffRecommendByType(Integer supType) {
        Assert.notNull(supType,"一级类型不能为空");
        List<Goods> goods = null;
        try {
            goods = goodsMapper.queryDiffRecommendByType(supType);
        } catch (Exception e) {
            //log.error("查询推荐商品失败",e);
            System.out.println("查询推荐商品失败");
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public List<OrderInfo> queryHot() {
        List<OrderInfo> orderInfos = null;
        try {
            orderInfos = orderInfoMapper.countHot();
            orderInfos.forEach(x->{
                //补充商品信息
                Goods goods = goodsMapper.queryOneGoodByGid(x.getGid());
                x.setName(goods.getName());
                x.setImage(goods.getImage());
                x.setPrice(goods.getPrice());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderInfos;
    }

    @Override
    public void updateGood(Goods goods) {
        try {
            goodsMapper.update(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}