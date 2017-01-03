package org.jigang.plat.admin.util;

import java.util.HashMap;
import java.util.Map;

/**
 * web返回对象
 *
 * @author wjg
 * @date 2017/1/1.
 */
public class WebResponse extends HashMap<String, Object> {
    public WebResponse() {
        put("code", 0);
    }

    public static WebResponse error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static WebResponse error(String msg) {
        return error(500, msg);
    }

    public static WebResponse error(int code, String msg) {
        WebResponse r = new WebResponse();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static WebResponse ok(String msg) {
        WebResponse r = new WebResponse();
        r.put("msg", msg);
        return r;
    }

    public static WebResponse ok(Map<String, Object> map) {
        WebResponse r = new WebResponse();
        r.putAll(map);
        return r;
    }

    public static WebResponse ok() {
        return new WebResponse();
    }

    public WebResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
