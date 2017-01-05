package org.jigang.plat.admin.web.controller.sys;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jigang.plat.admin.constant.MenuTypeEnum;
import org.jigang.plat.admin.constant.PermConstant;
import org.jigang.plat.admin.entity.PageEntityWrapper;
import org.jigang.plat.admin.entity.sys.SysMenuEntity;
import org.jigang.plat.admin.exception.AdminException;
import org.jigang.plat.admin.service.sys.ISysMenuService;
import org.jigang.plat.admin.util.WebResponse;
import org.jigang.plat.admin.web.controller.BaseController;
import org.jigang.plat.admin.web.form.SearchCondition;
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
 * 系统菜单
 *
 * @author wjg
 * @date 2017/1/1.
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @RequiresPermissions(PermConstant.SYS_MENU_LIST)
    public WebResponse list(@RequestBody SearchCondition condition) {
        Integer page = condition.getPage();
        Integer limit = condition.getLimit();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);



        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryList(map);
        int total = sysMenuService.queryTotal(map);

        PageEntityWrapper pageEntity = new PageEntityWrapper(menuList, total, limit, page);

        return WebResponse.ok().put("page", pageEntity);
    }

   /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions(PermConstant.SYS_MENU_SELECT)
    public WebResponse select() {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return WebResponse.ok().put("menuList", menuList);
    }

    /**
     * 角色授权菜单
     */
    @RequestMapping("/perms")
    @RequiresPermissions(PermConstant.SYS_MENU_PERMS)
    public WebResponse perms() {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());

        return WebResponse.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions(PermConstant.SYS_MENU_INFO)
    public WebResponse info(@PathVariable("menuId") Long menuId) {
        SysMenuEntity menu = sysMenuService.queryObject(menuId);
        return WebResponse.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions(PermConstant.SYS_MENU_SAVE)
    public WebResponse save(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return WebResponse.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions(PermConstant.SYS_MENU_UPDATE)
    public WebResponse update(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.update(menu);

        return WebResponse.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions(PermConstant.SYS_MENU_DELETE)
    public WebResponse delete(@RequestBody Long[] menuIds) {
        for (Long menuId : menuIds) { //TODO 系统菜单判断逻辑不严谨，且不应该直接删除菜单，待修改
            if (menuId.longValue() <= 28) {
                return WebResponse.error("系统菜单，不能删除");
            }
        }
        sysMenuService.deleteBatch(menuIds);

        return WebResponse.ok();
    }

    /**
     * 用户菜单列表
     */
    @RequestMapping("/user")
    public WebResponse user() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());

        return WebResponse.ok().put("menuList", menuList);
    }


    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menu) {
        if (StringUtil.notHasLength(menu.getName())) {
            throw new AdminException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new AdminException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == MenuTypeEnum.MENU.getValue()) {
            if (StringUtil.notHasLength(menu.getUrl())) {
                throw new AdminException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = MenuTypeEnum.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == MenuTypeEnum.CATALOG.getValue() ||
                menu.getType() == MenuTypeEnum.MENU.getValue()) {
            if (parentType != MenuTypeEnum.CATALOG.getValue()) {
                throw new AdminException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == MenuTypeEnum.BUTTON.getValue()) {
            if (parentType != MenuTypeEnum.MENU.getValue()) {
                throw new AdminException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
