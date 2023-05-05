package com.pet.shop.vo;

import com.pet.shop.model.SecondTitle;
import lombok.Data;

import java.util.*;

/**
 * 分类vo对象
 */

@Data
public class ClassVo {
    /**
     * 一级标题ID
     */
    private Integer id;

    /**
     * 一级标题名称
     */
    private String supTypeDesc;

    /**
     * 二级标题
     */
    private List<SecondTitle> secondTitles;
}