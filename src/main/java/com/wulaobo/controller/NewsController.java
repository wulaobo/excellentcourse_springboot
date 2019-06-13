package com.wulaobo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulaobo.bean.News;
import com.wulaobo.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


}
