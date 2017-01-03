package org.jigang.plat.admin.service.sys.impl;

import org.jigang.plat.admin.dao.sys.ISysRoleMenuDao;
import org.jigang.plat.admin.service.sys.ISysRoleMenuService;
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
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {
    @Autowired
    private ISysRoleMenuDao sysRoleMenuDao;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        if(menuIdList.size() == 0){
            return ;
        }
        //先删除角色与菜单关系
        sysRoleMenuDao.delete(roleId);

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        sysRoleMenuDao.save(map);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return sysRoleMenuDao.queryMenuIdList(roleId);
    }
}
