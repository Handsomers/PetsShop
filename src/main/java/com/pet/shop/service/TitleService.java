package com.pet.shop.service;

import com.pet.shop.model.FirstTitle;
import com.pet.shop.model.SecondTitle;
import com.pet.shop.vo.ClassVo;

import java.util.List;

/**
 * 分类服务类
 * @author 昂太 (angtai@maihaoche.com)
 *
 */
public interface TitleService {
    /**
     * 新增一个一级标题
     * @param firstTitle
     */
    void addFirst(FirstTitle firstTitle);

    /**
     * 查询全部可用一级标题
     * @return
     */
    List<FirstTitle> queryAllFirst();

    /**
     * 根据一级标题ID查询详情
     * @param id
     * @return
     */
    FirstTitle queryOneFirst(Integer id);

    /**
     * 根据ID修改标题状态
     * @param firstTitle
     */
    void updateFirst(FirstTitle firstTitle);

    /**
     * 根据ID删除
     * @param id
     */
    void deleteOneFirst(Integer id);

    /**
     * 新增一个二级标题
     * @param secondTitle
     */
    void addSecond(SecondTitle secondTitle);

    /**
     * 查询全部二级标题
     * @return
     */
    List<SecondTitle> queryAllSecond();

    /**
     * 通过父标题ID查询全部二级标题
     * @return
     */
    List<SecondTitle> queryAllSecondByTitleId(Integer id);


    /**
     * 根据二级标题ID查询详情
     * @param id
     * @return
     */
    SecondTitle queryOneSecond(Integer id);

    /**
     * 根据二级标题模糊查询
     * @param name
     * @return
     */
    List<SecondTitle> querySecondByName(String name);

    /**
     * 根据ID修改标题状态
     * @param secondTitle
     */
    void updateSecond(SecondTitle secondTitle);

    /**
     * 根据ID删除
     * @param id
     */
    void deleteOneSecond(Integer id);

    /**
     * 查询全部标题分类
     */
    List<ClassVo> queryAllTitle();
}
