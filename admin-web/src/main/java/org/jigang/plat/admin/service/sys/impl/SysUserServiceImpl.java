package org.jigang.plat.admin.service.sys.impl;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.jigang.plat.admin.dao.sys.ISysUserDao;
import org.jigang.plat.admin.entity.sys.SysUserEntity;
import org.jigang.plat.admin.service.sys.ISysUserRoleService;
import org.jigang.plat.admin.service.sys.ISysUserService;
import org.jigang.plat.core.lang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private ISysUserDao sysUserDao;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public SysUserEntity queryObject(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        sysUserDao.save(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if(StringUtil.notHasLength(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        sysUserDao.update(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        sysUserDao.deleteBatch(userIds);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }
}
