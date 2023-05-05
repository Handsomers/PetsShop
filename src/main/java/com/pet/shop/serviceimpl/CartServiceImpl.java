package com.pet.shop.serviceimpl;

import com.pet.shop.mapper.CartMapper;
import com.pet.shop.mapper.GoodsMapper;
import com.pet.shop.model.Cart;
import com.pet.shop.model.Goods;
import com.pet.shop.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 购物车Service 实现类
 * @author 昂太 (angtai@maihaoche.com)
 *
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addIntoCart(Cart cart) {
        try {
            cartMapper.addIntoCart(cart);
        } catch (Exception e) {
            log.error("添加到购物车失败",e);
            e.printStackTrace();
        }

    }

    @Override
    public List<Cart> queryAll(Integer id) {
        List<Cart> carts = null;
        try {
            carts = cartMapper.queryAll(id);
            if(!CollectionUtils.isEmpty(carts)){
                carts.stream().forEach(x->{
                    Goods goods = new Goods();
                    goods.setId(x.getGid());
                    List<Goods> result = goodsMapper.queryAllgoods(goods);
                    Assert.notNull(result,"未查到该用户的购物信息");
                    //商品图片地址
                    x.setImage(result.get(0).getImage());
                    //商品图片名称
                    x.setName(result.get(0).getName());
                    //商品价格
                    x.setPrice(result.get(0).getPrice());
                    //总价格
                    x.setTotalAmount(Double.parseDouble(x.getPrice()) * x.getNum());
                });
            }
        } catch (Exception e) {
            log.error("查询购物车信息失败！入参ID={}",id,e);
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public void deleteCart(Integer id) {
        Assert.notNull(id,"购物车ID不能为空");

        try {
            cartMapper.updateStatus(id);
        } catch (Exception e) {
            log.error("根据购物车ID逻辑删除失败",id);
            e.printStackTrace();
        }
    }
}