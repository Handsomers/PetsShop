package com.pet.shop.mapper;

import com.pet.shop.model.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品Mapper
 * @author 昂太 (angtai@maihaoche.com)
 *
 */
@Mapper
public interface GoodsMapper {

    /**
     * 查询全部商品
     * @param goods
     * @return
     */
    @Select({
    "<script>",
    "select * from goods",
    "<where>",

    "<if test=\"name != null and name !='' \">",
    "and name like concat('%',#{name},'%')",
    "</if>",

    "<if test='id != null'>",
    "and id = #{id}",
    "</if>",

    "<if test='sex != null'>",
    "and sex = #{sex}",
    "</if>",

    "<if test='isRecommend != null'>",
    "and isRecommend = #{isRecommend}",
    "</if>",

    "<if test='supType != null'>",
    "and sup_type = #{supType}",
    "</if>",

    "<if test='subType != null'>",
    "and sub_type = #{subType}",
    "</if>",

    "</where>",
    "</script>"})
    List<Goods> queryAllgoods(Goods goods);


    /**
     * 查询全部推荐商品
     * @param
     * @return
     */
    @Select("select * from goods where isRecommend = 1")
    List<Goods> queryAllgoodsByRecommend();

    /**
     * 查询5个不同类型的推荐
     * @param supType
     * @return
     */
    @Select("select * from goods where sup_type = #{supType}  limit 0,5")
    List<Goods> queryDiffRecommendByType(Integer supType);

    /**
     * 新增商品
     * @param goods
     */
    @Insert("insert into goods(name,price,sup_type,sub_type,isRecommend,sex,image,age)" +
            "values(#{name},#{price},#{supType},#{subType},#{isRecommend},#{sex},#{image},#{age}) ")
    void addGood(Goods goods);

    /**
     * 删除商品
     * @param id
     */
    @Delete("delete from goods where id=#{id}")
    void deleteOneGood(Integer id);

    /**
     * 更新商品
     * @param goods
     */
    @Update({
            "<script>",
            "UPDATE  goods",
            "<set>",

            "<if test='name != null'>",
            " name = #{name},",
            "</if>",

            "<if test='price != null'>",
            "price = #{price},",
            "</if>",

            "<if test='supType != null'>",
            "sup_type = #{supType},",
            "</if>",

            "<if test='subType != null'>",
            "sub_type = #{subType},",
            "</if>",

            "<if test='isRecommend != null'>",
            "isRecommend = #{isRecommend}",
            "</if>",

            "</set>",
            "where id = #{id}",
            "</script>"})
    void update(Goods goods);

    /**
     * 根据商品ID查看商品信息
     * @return
     */
    @Select("select * from goods where id =#{gid}")
    Goods queryOneGoodByGid(Integer gid);
}
