package org.jigang.plat.admin.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jigang.plat.admin.entity.PageEntityWrapper;
import org.jigang.plat.admin.entity.SysRoleEntity;
import org.jigang.plat.admin.service.ISysRoleMenuService;
import org.jigang.plat.admin.service.ISysRoleService;
import org.jigang.plat.admin.util.WebResponse;
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
    @RequiresPermissions("sys:role:list")
    public WebResponse list(Integer page, Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<SysRoleEntity> list = sysRoleService.queryList(map);
        int total = sysRoleService.queryTotal(map);

        PageEntityWrapper pageUtil = new PageEntityWrapper(list, total, limit, page);

        return WebResponse.ok().put("page", pageUtil);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public WebResponse select(){
        //查询列表数据
        List<SysRoleEntity> list = sysRoleService.queryList(new HashMap<String, Object>());

        return WebResponse.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
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
    @RequiresPermissions("sys:role:save")
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
    @RequiresPermissions("sys:role:update")
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
    @RequiresPermissions("sys:role:delete")
    public WebResponse delete(@RequestBody Long[] roleIds){
        sysRoleService.deleteBatch(roleIds);

        return WebResponse.ok();
    }
}
