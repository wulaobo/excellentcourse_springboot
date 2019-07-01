package com.wulaobo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulaobo.bean.News;
import com.wulaobo.service.NewService;
import com.wulaobo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewService newService;

    //网站前台首页展示文章列表
    @RequestMapping(value = "/getAllNews")
    public String getAllNews(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, ModelMap model) {
        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("id desc");
        List<News> list = newService.getAllNews();

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        return "home";
    }


    //网站前台
    @GetMapping(value = "/getNewsById")
    public String getNewsById(Integer id,ModelMap model) {
        News news = newService.getNewsById(id);
        if(news!=null){
            model.addAttribute("news",news);
        }
        return "news";
    }

    @GetMapping("/page/{pageNum}")
    public String getPageNewsByPageNum(@PathVariable("pageNum") Integer pageNum,ModelMap model) {

        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("id desc");

        List<News> list = newService.getAllNews();
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);

        return "home";
    }


    //管理员获取文章列表
    @GetMapping(value="/getNewsList")
    public String getNewsList(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              ModelMap model) {

        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("time desc");

        List<News> list = newService.getAllNews();
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("newsList",pageInfo);
        return "admin/news/newsList";
    }

    //管理员根据title模糊查询文章
    @PostMapping(value = "/selectNewsByTitle")
    public String selectNewsByTitle(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                    ModelMap model,String title) {
        PageHelper.startPage(pageNum,5);
        PageHelper.orderBy("time desc");

        List<News> list = newService.selectNewsByTitle(title);

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("newsList",pageInfo);

        return "admin/news/newsList";
    }

    //网站后台管理员
    @GetMapping(value = "/getNewsByIdAndAdmin")
    public String getNewsByIdAndAdmin(Integer id,ModelMap model) {
        News news = newService.getNewsById(id);
        model.addAttribute("news",news);
        return "admin/news/editNews";
    }


    @PostMapping(value = "/updateNews")
    public String updateNews(News news) {

        news.setTime(DateUtil.getNowTime());
        newService.updateNews(news);
        return "redirect:/getNewsList";
    }


    @GetMapping(value = "/deleteNewsById")
    public String deleteNewsById(Integer id) {
        newService.deleteNewsById(id);
        return "forward:/getNewsList";
    }

    @PostMapping(value = "/addNews")
    public String addNews(News news) {
        news.setTime(DateUtil.getNowTime());
        newService.addNews(news);

        return "redirect:/getNewsList";
    }



}
