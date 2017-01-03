package org.jigang.plat.admin.dao.sys;

import org.jigang.plat.admin.dao.BaseDao;
import org.jigang.plat.admin.entity.sys.SysUserRoleEntity;

import java.util.List;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
public interface ISysUserRoleDao extends BaseDao<SysUserRoleEntity> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);
}
