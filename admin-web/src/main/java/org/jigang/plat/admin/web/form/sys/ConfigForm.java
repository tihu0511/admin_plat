package org.jigang.plat.admin.web.form.sys;

import org.jigang.plat.admin.web.form.BaseForm;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/7.
 */
public class ConfigForm extends BaseForm {
    private Integer id;
    private String key;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
