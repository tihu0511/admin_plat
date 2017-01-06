package org.jigang.plat.admin.web.form.sys;

import org.jigang.plat.admin.web.form.BaseForm;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/6.
 */
public class UserForm extends BaseForm {
    private Long userId;
    private String username;
    private String email;
    private String mobile;
    private Integer status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
