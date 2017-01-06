package org.jigang.plat.admin.web.controller.sys;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jigang.plat.admin.constant.PermConstant;
import org.jigang.plat.admin.entity.PageEntityWrapper;
import org.jigang.plat.admin.entity.sys.SysRoleEntity;
import org.jigang.plat.admin.service.sys.ISysRoleMenuService;
import org.jigang.plat.admin.service.sys.ISysRoleService;
import org.jigang.plat.admin.util.WebResponse;
import org.jigang.plat.admin.web.form.sys.RoleForm;
import org.jigang.plat.core.lang.util.BeanUtil;
import org.jigang.plat.core.lang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色controller
 *
 * @author wjg
 * @date 2017/1/3.
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions(PermConstant.SYS_ROLE_LIST)
    public WebResponse list(@RequestBody RoleForm form){
        Integer limit = form.getLimit();
        Integer page = form.getPage();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        SysRoleEntity roleCondition = new SysRoleEntity();
        BeanUtil.copyProperties(roleCondition, form);

        //查询列表数据
        List<SysRoleEntity> list = sysRoleService.queryList(map, roleCondition);
        int total = sysRoleService.queryTotal(map, roleCondition);

        PageEntityWrapper pageUtil = new PageEntityWrapper(list, total, limit, page);

        return WebResponse.ok().put("page", pageUtil);
    }

    /**
     * 角色选择
     */
    @RequestMapping("/select")
    @RequiresPermissions(PermConstant.SYS_ROLE_SELECT)
    public WebResponse select(){
        //查询列表数据
        List<SysRoleEntity> list = sysRoleService.queryList(new HashMap<String, Object>());

        return WebResponse.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions(PermConstant.SYS_ROLE_INFO)
    public WebResponse info(@PathVariable("roleId") Long roleId){
        SysRoleEntity role = sysRoleService.queryObject(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return WebResponse.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @RequestMapping("/save")
    @RequiresPermissions(PermConstant.SYS_ROLE_SAVE)
    public WebResponse save(@RequestBody SysRoleEntity role){
        if(StringUtil.notHasLength(role.getRoleName())){
            return WebResponse.error("角色名称不能为空");
        }

        sysRoleService.save(role);

        return WebResponse.ok();
    }

    /**
     * 修改角色
     */
    @RequestMapping("/update")
    @RequiresPermissions(PermConstant.SYS_ROLE_UPDATE)
    public WebResponse update(@RequestBody SysRoleEntity role){
        if(StringUtil.notHasLength(role.getRoleName())){
            return WebResponse.error("角色名称不能为空");
        }

        sysRoleService.update(role);

        return WebResponse.ok();
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    @RequiresPermissions(PermConstant.SYS_ROLE_DELETE)
    public WebResponse delete(@RequestBody Long[] roleIds){
        sysRoleService.deleteBatch(roleIds);

        return WebResponse.ok();
    }
}
