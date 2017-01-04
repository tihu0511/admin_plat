package org.jigang.plat.admin.web.controller.sys;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jigang.plat.admin.constant.PermConstant;
import org.jigang.plat.admin.entity.PageEntityWrapper;
import org.jigang.plat.admin.entity.sys.ScheduleJobEntity;
import org.jigang.plat.admin.exception.AdminException;
import org.jigang.plat.admin.service.sys.IScheduleJobService;
import org.jigang.plat.admin.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务管理
 *
 * @author wjg
 * @date 2017/1/3.
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
    @Autowired
    private IScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @RequestMapping("/list")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_LIST)
    public WebResponse list(Integer page, Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<ScheduleJobEntity> jobList = scheduleJobService.queryList(map);
        int total = scheduleJobService.queryTotal(map);

        PageEntityWrapper pageEntity = new PageEntityWrapper(jobList, total, limit, page);

        return WebResponse.ok().put("page", pageEntity);
    }

    /**
     * 定时任务信息
     */
    @RequestMapping("/info/{jobId}")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_INFO)
    public WebResponse info(@PathVariable("jobId") Long jobId){
        ScheduleJobEntity schedule = scheduleJobService.queryObject(jobId);

        return WebResponse.ok().put("schedule", schedule);
    }

    /**
     * 保存定时任务
     */
    @RequestMapping("/save")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_SAVE)
    public WebResponse save(@RequestBody ScheduleJobEntity scheduleJob){
        //数据校验
        verifyForm(scheduleJob);

        scheduleJobService.save(scheduleJob);

        return WebResponse.ok();
    }

    /**
     * 修改定时任务
     */
    @RequestMapping("/update")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_UPDATE)
    public WebResponse update(@RequestBody ScheduleJobEntity scheduleJob){
        //数据校验
        verifyForm(scheduleJob);

        scheduleJobService.update(scheduleJob);

        return WebResponse.ok();
    }

    /**
     * 删除定时任务
     */
    @RequestMapping("/delete")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_DELETE)
    public WebResponse delete(@RequestBody Long[] jobIds){
        scheduleJobService.deleteBatch(jobIds);

        return WebResponse.ok();
    }

    /**
     * 立即执行任务
     */
    @RequestMapping("/run")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_RUN)
    public WebResponse run(@RequestBody Long[] jobIds){
        scheduleJobService.run(jobIds);

        return WebResponse.ok();
    }

    /**
     * 暂停定时任务
     */
    @RequestMapping("/pause")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_PAUSE)
    public WebResponse pause(@RequestBody Long[] jobIds){
        scheduleJobService.pause(jobIds);

        return WebResponse.ok();
    }

    /**
     * 恢复定时任务
     */
    @RequestMapping("/resume")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_RESUME)
    public WebResponse resume(@RequestBody Long[] jobIds){
        scheduleJobService.resume(jobIds);

        return WebResponse.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(ScheduleJobEntity scheduleJob){
        if(StringUtils.isBlank(scheduleJob.getBeanName())){
            throw new AdminException("bean名称不能为空");
        }

        if(StringUtils.isBlank(scheduleJob.getMethodName())){
            throw new AdminException("方法名称不能为空");
        }

        if(StringUtils.isBlank(scheduleJob.getCronExpression())){
            throw new AdminException("cron表达式不能为空");
        }
    }
}
