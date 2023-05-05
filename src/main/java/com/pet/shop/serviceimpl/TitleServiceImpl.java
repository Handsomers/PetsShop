package com.pet.shop.serviceimpl;

import com.pet.shop.mapper.FirstTitleMapper;
import com.pet.shop.mapper.SecondTitleMapper;
import com.pet.shop.model.FirstTitle;
import com.pet.shop.model.SecondTitle;
import com.pet.shop.service.TitleService;
import com.pet.shop.vo.ClassVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 分类服务实现类
 * @author 昂太 (angtai@maihaoche.com)
 *
 */
@Slf4j
@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    private FirstTitleMapper firstTitleMapper;

    @Autowired
    private SecondTitleMapper secondTitleMapper;

    @Autowired
    private TitleService titleService;

    @Override
    public void addFirst(FirstTitle firstTitle) {
        Assert.notNull(firstTitle,"新增一级标题不能为空");
        try {
            //新增的大类默认可用
            firstTitle.setStatus(1);
            firstTitleMapper.add(firstTitle);
        } catch (Exception e) {
            log.error("新增一级标题失败,入参{}",firstTitle,e.getMessage());
            throw new RuntimeException("新增一级标题失败");
        }
    }

    @Override
    public List<FirstTitle> queryAllFirst() {
        try {
            List<FirstTitle> firstTitles = firstTitleMapper.queryAll();
            return firstTitles;
        } catch (Exception e) {
            log.error("查询全部一级标题失败,入参{}",e.getMessage());
            throw new RuntimeException("查询一级标题失败");
        }
    }

    @Override
    public FirstTitle queryOneFirst(Integer id) {
//        Assert.notNull(id,"一级标题ID不能为空");
        FirstTitle firstTitle = null;
        try {
              firstTitle = firstTitleMapper.queryOne(id);
        } catch (Exception e) {
            log.error("查询一级标题失败,入参{}",id,e.getMessage());
            e.printStackTrace();
        }
        return firstTitle;
    }

    @Override
    public void updateFirst(FirstTitle firstTitle) {
        Assert.notNull(firstTitle,"更新一级状态入参不能为空");
        try {
            firstTitleMapper.update(firstTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteOneFirst(Integer id) {
        Assert.notNull(id,"要删除得ID不能为空");
        try {
            firstTitleMapper.deleteOne(id);
        } catch (Exception e) {
            log.error("根据ID={},删除失败",id,e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void addSecond(SecondTitle secondTitle) {
        Assert.notNull(secondTitle,"新增二级标题入参不能为空");
        secondTitle.setStatus(1);
        try {
            secondTitleMapper.add(secondTitle);
        } catch (Exception e) {
            log.error("新增二级标题失败,入参",secondTitle,e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public List<SecondTitle> queryAllSecond() {
        List<SecondTitle> secondTitles = null;
        try {
             secondTitles = secondTitleMapper.queryAll();
             //补全显示的父类名称
            secondTitles.forEach(x->{
                if(Objects.nonNull(firstTitleMapper.queryOne(x.getRefId())) && !StringUtils.isEmpty(firstTitleMapper.queryOne(x.getRefId()).getName())){
                x.setRefIdDesc(firstTitleMapper.queryOne(x.getRefId()).getName());
                }else{
                    x.setRefIdDesc("一级标题不存或者已经被删除");
                }
            });
        } catch (Exception e) {
            log.error("查询全部二级标题失败",e.getMessage());
            e.printStackTrace();
        }
        return secondTitles;
    }

    @Override
    public List<SecondTitle> queryAllSecondByTitleId(Integer id) {
        List<SecondTitle> secondTitles = null;
        try {
            secondTitles = secondTitleMapper.queryAllByFirstId(id);
        } catch (Exception e) {
            log.error("根据一级标题ID查询二级标题失败!",id);
            e.printStackTrace();
        }
        return secondTitles;
    }

    @Override
    public SecondTitle queryOneSecond(Integer id) {
        Assert.notNull(id,"查询二级标题入参ID不能为空");
        SecondTitle secondTitle = null;
        try {
            secondTitle = secondTitleMapper.queryOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secondTitle;
    }

    @Override
    public List<SecondTitle> querySecondByName(String name) {
        if(StringUtils.isEmpty(name)){
           return titleService.queryAllSecond();
        }
        try {
            List<SecondTitle> secondTitles = secondTitleMapper.queryByName(name);
            if(Objects.isNull(secondTitles)){
                return Collections.EMPTY_LIST;
            }
            secondTitles.stream().forEach(x->{
                x.setRefIdDesc(firstTitleMapper.queryOne(x.getRefId()).getName());
            });
            return secondTitles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void updateSecond(SecondTitle secondTitle) {
        Assert.notNull(secondTitle,"更新二级标题状态入参不能为空");
        try {
            secondTitleMapper.update(secondTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOneSecond(Integer id) {
        Assert.notNull(id,"要删除得ID不能为空");
        try {
            secondTitleMapper.deleteOne(id);
        } catch (Exception e) {
            log.error("根据ID={},删除失败",id,e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public List<ClassVo> queryAllTitle() {
        //返回对象
        List<ClassVo> result = new ArrayList<>();
        List<FirstTitle> firstTitles = firstTitleMapper.queryAll();
        firstTitles.forEach(x->{
            //一级标题信息
            ClassVo classVo = new ClassVo();
            classVo.setId(x.getId());
            classVo.setSupTypeDesc(x.getName());

            //二级标题信息
            List<SecondTitle> secondTitles = secondTitleMapper.queryAllByFirstId(x.getId());
            classVo.setSecondTitles(secondTitles);
            result.add(classVo);
        });
        return result;
    }
}