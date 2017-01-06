package org.jigang.plat.admin.web.form.sys;

import org.jigang.plat.admin.web.form.BaseForm;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/6.
 */
public class RoleForm extends BaseForm {
    private Long roleId;
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
