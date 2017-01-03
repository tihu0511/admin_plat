package org.jigang.plat.admin.service.sys.impl;

import org.jigang.plat.admin.dao.sys.ISysRoleDao;
import org.jigang.plat.admin.entity.sys.SysRoleEntity;
import org.jigang.plat.admin.service.sys.ISysRoleMenuService;
import org.jigang.plat.admin.service.sys.ISysRoleService;
import org.jigang.plat.admin.service.sys.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private ISysRoleDao sysRoleDao;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Override
    public SysRoleEntity queryObject(Long roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    @Override
    public List<SysRoleEntity> queryList(Map<String, Object> map) {
        return sysRoleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysRoleEntity role) {
        role.setCreateTime(new Date());
        sysRoleDao.save(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void update(SysRoleEntity role) {
        sysRoleDao.update(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        sysRoleDao.deleteBatch(roleIds);
    }
}
