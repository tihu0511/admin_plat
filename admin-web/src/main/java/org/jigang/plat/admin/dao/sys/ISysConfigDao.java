package org.jigang.plat.admin.dao.sys;

import org.apache.ibatis.annotations.Param;
import org.jigang.plat.admin.dao.BaseDao;
import org.jigang.plat.admin.entity.sys.SysConfigEntity;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
public interface ISysConfigDao extends BaseDao<SysConfigEntity> {
    /**
     * 根据key，查询value
     */
    String queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);
}
