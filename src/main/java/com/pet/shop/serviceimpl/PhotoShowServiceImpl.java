package com.pet.shop.serviceimpl;

import com.pet.shop.mapper.PhotoMapper;
import com.pet.shop.model.PhotoShow;
import com.pet.shop.service.PhotoShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 轮播图管理
 */

@Slf4j
@Service
public class PhotoShowServiceImpl implements PhotoShowService {
    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public List<PhotoShow> queryAll() {
        List<PhotoShow> photoShows = null;
        try {
            photoShows = photoMapper.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return photoShows;
    }

    @Override
    public void addPhoto(PhotoShow photoShow) {
        try {
            photoMapper.addPhoto(photoShow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePhoto(Integer id) {
        Assert.notNull(id,"id不能为空!");
        try {
            photoMapper.deletePhoto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}