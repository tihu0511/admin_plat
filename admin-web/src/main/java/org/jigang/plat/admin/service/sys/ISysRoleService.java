package org.jigang.plat.admin.service.sys;

import org.jigang.plat.admin.entity.sys.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
public interface ISysRoleService {
    SysRoleEntity queryObject(Long roleId);

    List<SysRoleEntity> queryList(Map<String, Object> map);
    List<SysRoleEntity> queryList(Map<String, Object> map, SysRoleEntity roleCondition);

    int queryTotal(Map<String, Object> map);
    int queryTotal(Map<String, Object> map, SysRoleEntity roleCondition);

    void save(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(Long[] roleIds);

}
