package org.jigang.plat.admin.dao.sys;

import org.jigang.plat.admin.dao.BaseDao;
import org.jigang.plat.admin.entity.sys.SysRoleMenuEntity;

import java.util.List;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
public interface ISysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
