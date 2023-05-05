package com.pet.shop.controller;

import com.pet.shop.model.FirstTitle;
import com.pet.shop.model.SecondTitle;
import com.pet.shop.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 标题控制器
 * @author 昂太 (angtai@maihaoche.com)
 *
 */

@Controller
@RequestMapping("/Title")
public class TitleController {
    @Autowired
    private TitleService titleService;


    /**
     * 新增一级分类页面控制器
     */
    @RequestMapping("/adminClassAdd")
    public String adminClassAdd(){
        return "admin/class-add";
    }

    /**
     * 新增一级标题
     * @param firstTitle
     */
    @ResponseBody
    @RequestMapping("/addFirst")
    public void addFirst(FirstTitle firstTitle){
        titleService.addFirst(firstTitle);
    }

    /**
     * 查询全部一级标题
     * @param
     */
    @RequestMapping("/queryAllFirst")
    public String queryAllFirst(Model model){
        model.addAttribute("FirstTitle",titleService.queryAllFirst());
        return "admin/class";
    }

    /**
     * 查询一级标题
     * @param
     */
    @RequestMapping("/queryFirst")
    public String queryFirst(Integer id,Model model){
         model.addAttribute("FirstTitle",titleService.queryOneFirst(id));
         return "admin/class";
    }

    /**
     * 修改前查询
     * @param id
     * @return
     */
    @GetMapping("/beforeUpdate")
    public String beforeUpdate(Integer id, Model model){
        model.addAttribute("info",titleService.queryOneFirst(id));
        return "admin/class-update";
    }

    /**
     * 修改一级标题
     * @param
     */
    @ResponseBody
    @RequestMapping("/updateFirst")
    public void updateStatusFirst(FirstTitle firstTitle){
         titleService.updateFirst(firstTitle);
    }

    /**
     * 根据ID删除
     * @param id
     */
    @ResponseBody
    @RequestMapping("/deleteOneFirst")
    public void delete(Integer id){
        titleService.deleteOneFirst(id);
    }

    /**
     * 新增前跳转页面带上父类ID
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/beforeAddSecond")
    public String beforeAddSecond(Integer id,Model model){
        model.addAttribute("FirstId",id);
        return "admin/class-second-add";
    }

    /**
     * 新增二级标题
     * @param secondTitle
     */
    @ResponseBody
    @RequestMapping("/addSecond")
    public void addSecond(SecondTitle secondTitle){
        titleService.addSecond(secondTitle);
    }

    /**
     * 查询全部二级标题
     * @param
     */
    @RequestMapping("/queryAllSecond")
    public String queryAllSecond(Model model){
        model.addAttribute("SecondTitle",titleService.queryAllSecond());
        return "admin/class-second";
    }

    /**
     * 查询二级标题
     * @param
     */
    @RequestMapping("/querySecond")
    public SecondTitle querySecond(Integer id){
        return titleService.queryOneSecond(id);
    }

    /**
     * 根据二级标题模糊查询
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/querySecondByname")
    public String querySecondByname(String name, Model model){
     model.addAttribute("SecondTitle",titleService.querySecondByName(name));
        return "admin/class-second";
    }


    /**
     * 修改二级标题视图
     * @param id 主键ID
     * @param model
     * @return
     */
    @RequestMapping("/beforeUpdateSecond")
    public String beforeUpdateSecond(Integer id,Model model){
        model.addAttribute("info",titleService.queryOneSecond(id));
        return "admin/class-second-update";
    }

    /**
     * 修改二级标题
     * @param
     */
    @ResponseBody
    @RequestMapping("/updateSecond")
    public void updateStatusSecond(SecondTitle secondTitle){
        titleService.updateSecond(secondTitle);
    }


    /**
     * 根据ID删除
     * @param id
     */
    @ResponseBody
    @RequestMapping("/deleteOneSecond")
    public void deleteOne(Integer id){
        titleService.deleteOneSecond(id);
    }

    /**
     * 二级联动
     * @param id 父标题ID
     */
    @ResponseBody
    @RequestMapping(value = "/queryAllSecondByTitleId" ,method = RequestMethod.GET)
    public List<SecondTitle> queryAllSecondByTitleId(Integer id){
      return titleService.queryAllSecondByTitleId(id);
    }
}