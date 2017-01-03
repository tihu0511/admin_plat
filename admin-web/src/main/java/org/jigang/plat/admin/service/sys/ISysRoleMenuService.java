package org.jigang.plat.admin.service.sys;

import java.util.List;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
public interface ISysRoleMenuService {
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
