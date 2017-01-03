package org.jigang.plat.admin.service.impl;

import org.jigang.plat.admin.dao.IScheduleJobLogDao;
import org.jigang.plat.admin.entity.ScheduleJobLogEntity;
import org.jigang.plat.admin.service.IScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements IScheduleJobLogService {
    @Autowired
    private IScheduleJobLogDao scheduleJobLogDao;

    @Override
    public ScheduleJobLogEntity queryObject(Long jobId) {
        return scheduleJobLogDao.queryObject(jobId);
    }

    @Override
    public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
        return scheduleJobLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scheduleJobLogDao.queryTotal(map);
    }

    @Override
    public void save(ScheduleJobLogEntity log) {
        scheduleJobLogDao.save(log);
    }
}
