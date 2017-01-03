package org.jigang.plat.admin.dao.sys;

import org.jigang.plat.admin.dao.BaseDao;
import org.jigang.plat.admin.entity.sys.ScheduleJobEntity;

import java.util.Map;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
public interface IScheduleJobDao extends BaseDao<ScheduleJobEntity> {

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> map);
}
