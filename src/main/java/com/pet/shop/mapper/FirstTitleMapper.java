package com.pet.shop.mapper;

import com.pet.shop.model.FirstTitle;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 一级标题Mapper
 * @author 昂太 (angtai@maihaoche.com)
 *
 */

@Mapper
public interface FirstTitleMapper {
    /**
     * 新增一个一级标题
     * @param firstTitle
     */
    @Insert("INSERT into first_title(name,status) values(#{name},#{status})")
    void add(FirstTitle firstTitle);

    /**
     * 查询全部可用一级标题
     * @return
     */
    @Select("select * from first_title")
    List<FirstTitle> queryAll();

    /**
     * 根据一级标题ID查询详情
     * @param id
     * @return
     */
    @Select("select * from  first_title where id =#{id} ")
    FirstTitle queryOne(Integer id);

    /**
     * 根据ID修改
     * @param firstTitle
     */
    @Update("update first_title set name =#{name},status =#{status} where id=#{id}")
    void update(FirstTitle firstTitle);

    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from first_title where id=#{id} ")
    void deleteOne(Integer id);
}
