package org.jigang.plat.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * html页面引导
 *
 * @author wjg
 * @date 2017/1/1.
 */
@Controller
public class SysPageController {
    @RequestMapping("/sys/{url}.html")
    public String page(@PathVariable("url") String url) {
        return "sys/" + url + ".html";
    }
}
