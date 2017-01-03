package org.jigang.plat.admin.service.sys.impl;

import org.jigang.plat.admin.dao.sys.IScheduleJobDao;
import org.jigang.plat.admin.entity.sys.ScheduleJobEntity;
import org.jigang.plat.admin.quartz.ScheduleStatus;
import org.jigang.plat.admin.quartz.ScheduleUtil;
import org.jigang.plat.admin.service.sys.IScheduleJobService;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
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
public class ScheduleJobServiceImpl implements IScheduleJobService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private IScheduleJobDao schedulerJobDao;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
        List<ScheduleJobEntity> scheduleJobList = schedulerJobDao.queryList(new HashMap<String, Object>());
        for(ScheduleJobEntity scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtil.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtil.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtil.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public ScheduleJobEntity queryObject(Long jobId) {
        return schedulerJobDao.queryObject(jobId);
    }

    @Override
    public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
        return schedulerJobDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return schedulerJobDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(ScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        schedulerJobDao.save(scheduleJob);

        ScheduleUtil.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional
    public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtil.updateScheduleJob(scheduler, scheduleJob);

        schedulerJobDao.update(scheduleJob);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtil.deleteScheduleJob(scheduler, jobId);
        }

        //删除数据
        schedulerJobDao.deleteBatch(jobIds);
    }

    @Override
    public int updateBatch(Long[] jobIds, int status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", jobIds);
        map.put("status", status);
        return schedulerJobDao.updateBatch(map);
    }

    @Override
    @Transactional
    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtil.run(scheduler, queryObject(jobId));
        }
    }

    @Override
    @Transactional
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtil.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional
    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtil.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }
}
