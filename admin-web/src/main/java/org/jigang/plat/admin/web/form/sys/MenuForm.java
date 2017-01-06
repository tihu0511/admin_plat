package org.jigang.plat.admin.web.form.sys;

import org.jigang.plat.admin.web.form.BaseForm;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/6.
 */
public class MenuForm extends BaseForm {
    private String name;
    private String parentName;
    private String url;
    private String perms;
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
