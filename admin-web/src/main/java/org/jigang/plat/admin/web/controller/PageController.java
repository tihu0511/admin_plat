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
public class PageController {
    @RequestMapping("/{module}/{url}.html")
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        return module + "/" + url + ".html";
    }
}
