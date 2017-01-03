package org.jigang.plat.admin.service.sys;

import java.util.List;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
public interface ISysUserRoleService {
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    void delete(Long userId);
}
