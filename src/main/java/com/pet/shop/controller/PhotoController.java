package com.pet.shop.controller;

import com.pet.shop.model.PhotoShow;
import com.pet.shop.service.PhotoShowService;
import com.pet.shop.service.upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 轮播图管理
 * @author 昂太
 *
 */

@RequestMapping("/Photo")
@Controller
public class PhotoController {
    @Autowired
    private PhotoShowService photoShowService;
    @Autowired
    private upload upload;


    /**
     * 查询全部轮播图
     */
    @RequestMapping("/queryAll")
    public String queryAll(Model model){
            model.addAttribute("Photo",photoShowService.queryAll());
        return "admin/photo-show";
    }

    /**
     * 新增图片
     */
    @RequestMapping("/addPhoto")
    public String addPhoto(@RequestParam(value = "file", required = false) MultipartFile file,
                           @RequestParam(value = "name", required = false) String name){

        String imageName = "PS"+System.currentTimeMillis();
        try {
            upload.upload(file.getBytes(),imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PhotoShow p = new PhotoShow();
        p.setImage(imageName);
        p.setName(name);
        photoShowService.addPhoto(p);
        return "redirect:/Photo/queryAll";
    }

    /**
     * 删除图片
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        photoShowService.deletePhoto(id);
        return "redirect:/Photo/queryAll";
    }

    /**
     * 新增视图
     */
    @RequestMapping("/addView")
    public String addView(){
        return "admin/photo-add";
    }

}