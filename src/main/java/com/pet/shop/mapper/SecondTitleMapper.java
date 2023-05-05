package com.pet.shop.mapper;

import com.pet.shop.model.SecondTitle;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 二级标题Mapper
 * @author 昂太 (angtai@maihaoche.com)
 *
 */

@Mapper
public interface SecondTitleMapper {
    /**
     * 新增一个二级标题
     * @param secondTitle
     */
    @Insert("INSERT into second_title(ref_id,name,status) values(#{refId},#{name},#{status})")
    void add(SecondTitle secondTitle);

    /**
     * 查询全部二级标题
     * @return
     */
    @Select("select * from second_title")
    List<SecondTitle> queryAll();

    /**
     * 根据二级标题ID查询详情
     * @param id
     * @return
     */
    @Select("select * from  second_title where id =#{id} ")
    SecondTitle queryOne(Integer id);

    /**
     * 根据父级Id查询二级标题ID查询详情
     * @param id
     * @return
     */
    @Select("select * from  second_title where  ref_id=#{refId} ")
    List<SecondTitle> queryAllByFirstId(Integer id);

    /**
     * 根据二级标题模糊查询
     * @param name
     * @return
     */
    @Select("select * from second_title where name like concat('%',#{name},'%')")
    List<SecondTitle> queryByName(String name);

    /**
     * 根据ID修改标题状态
     * @param secondTitle
     */
    @Update("update second_title set name=#{name}, status =#{status} where id=#{id}")
    void update(SecondTitle secondTitle);


    @Delete("delete from second_title where id=#{id} ")
    void deleteOne(Integer id);
}
