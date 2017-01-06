package org.jigang.plat.admin.dao.sys;

import org.apache.ibatis.annotations.Param;
import org.jigang.plat.admin.dao.BaseDao;
import org.jigang.plat.admin.entity.sys.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜单dao
 *
 * @author wjg
 * @date 2017/1/1.
 */
public interface ISysMenuDao extends BaseDao<SysMenuEntity> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    List<SysMenuEntity> query(@Param("sysMenu") SysMenuEntity sysMenu);

}
