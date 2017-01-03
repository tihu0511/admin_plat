package org.jigang.plat.admin.dao;

import org.jigang.plat.admin.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户dao
 *
 * @author wjg
 * @date 2017/1/1.
 */
public interface ISysUserDao extends BaseDao<SysUserEntity> {
    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);
}
