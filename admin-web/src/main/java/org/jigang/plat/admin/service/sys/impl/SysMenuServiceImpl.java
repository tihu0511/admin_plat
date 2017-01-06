package org.jigang.plat.admin.service.sys.impl;

import org.jigang.plat.admin.constant.MenuTypeEnum;
import org.jigang.plat.admin.dao.sys.ISysMenuDao;
import org.jigang.plat.admin.entity.sys.SysMenuEntity;
import org.jigang.plat.admin.service.sys.ISysMenuService;
import org.jigang.plat.admin.service.sys.ISysUserService;
import org.jigang.plat.core.lang.util.CollectionUtil;
import org.jigang.plat.core.lang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/1.
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    @Autowired
    private ISysMenuDao sysMenuDao;

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = sysMenuDao.queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<SysMenuEntity>();
        for(SysMenuEntity menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return sysMenuDao.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == 1){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return sysMenuDao.queryObject(menuId);
    }

    @Override
    public List<SysMenuEntity> queryList(Map<String, Object> map) {
        return sysMenuDao.queryList(map);
    }

    public List<SysMenuEntity> queryList(Map<String, Object> map, SysMenuEntity menuCondition) {
        fillParentIdCondition(map, menuCondition);
        escapeSysMenuCondition(map, menuCondition);
        return queryList(map);
    }

    private void escapeSysMenuCondition(Map<String, Object> map, SysMenuEntity menuCondition) {
        if (null != menuCondition) {
            if (StringUtil.hasLength(menuCondition.getName())) {
                menuCondition.setName("%" + menuCondition.getName() + "%");
            }
            if (StringUtil.hasLength(menuCondition.getUrl())) {
                menuCondition.setUrl("%" + menuCondition.getUrl() + "%");
            }
            if (StringUtil.hasLength(menuCondition.getPerms())) {
                menuCondition.setPerms("%" + menuCondition.getPerms() + "%");
            }
            map.put("sysMenu", menuCondition);
        }
    }

    private void fillParentIdCondition(Map<String, Object> map, SysMenuEntity menuCondition) {
        if (null != menuCondition && StringUtil.hasLength(menuCondition.getParentName())) {
            SysMenuEntity condition = new SysMenuEntity();
            condition.setName("%" + menuCondition.getParentName() + "%");
            List<SysMenuEntity> parentMenus = sysMenuDao.query(condition);
            if (!CollectionUtil.isEmpty(parentMenus)) {
                map.put("parentIds", loopParentId(parentMenus));
            }
        }
    }

    private List<Long> loopParentId(List<SysMenuEntity> parentMenus) {
        if (CollectionUtil.isEmpty(parentMenus)) {
            return null;
        }
        List<Long> parentIds = new ArrayList<Long>();
        for (SysMenuEntity sysMenu : parentMenus) {
            parentIds.add(sysMenu.getMenuId());
        }
        return parentIds;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysMenuDao.queryTotal(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map, SysMenuEntity menuCondition) {
        fillParentIdCondition(map, menuCondition);
        escapeSysMenuCondition(map, menuCondition);
        return sysMenuDao.queryTotal(map);
    }



    @Override
    public void save(SysMenuEntity menu) {
        sysMenuDao.save(menu);
    }

    @Override
    public void update(SysMenuEntity menu) {
        sysMenuDao.update(menu);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] menuIds) {
        sysMenuDao.deleteBatch(menuIds);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for(SysMenuEntity entity : menuList){
            if(entity.getType() == MenuTypeEnum.CATALOG.getValue()){//目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
