package org.jigang.plat.admin.service.sys.impl;

import org.jigang.plat.admin.dao.sys.ISysUserRoleDao;
import org.jigang.plat.admin.service.sys.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {
    @Autowired
    private ISysUserRoleDao sysUserRoleDao;

    @Override
    @Transactional
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if(roleIdList.size() == 0){
            return ;
        }

        //先删除用户与角色关系
        sysUserRoleDao.delete(userId);

        //保存用户与角色关系
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        sysUserRoleDao.save(map);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return sysUserRoleDao.queryRoleIdList(userId);
    }

    @Override
    public void delete(Long userId) {
        sysUserRoleDao.delete(userId);
    }
}
