package com.pet.shop.mapper;

import com.pet.shop.model.PhotoShow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 轮播图
 */
@Mapper
public interface PhotoMapper {

    /**
     * 查看全部轮播图
     */
    @Select("select * from photo_show")
    List<PhotoShow> queryAll();

    /**
     * 新增轮播图
     * @param photoShow
     */
    @Insert("insert into photo_show(image,name) values(#{image},#{name})")
    void addPhoto(PhotoShow photoShow);

    /**
     * 删除轮播图
     * @param id
     */
    @Delete("delete from photo_show where id=#{id}")
    void deletePhoto(Integer id);


}
