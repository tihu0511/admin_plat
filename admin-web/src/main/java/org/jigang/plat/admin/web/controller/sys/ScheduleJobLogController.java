package org.jigang.plat.admin.web.controller.sys;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jigang.plat.admin.constant.PermConstant;
import org.jigang.plat.admin.entity.PageEntityWrapper;
import org.jigang.plat.admin.entity.sys.ScheduleJobLogEntity;
import org.jigang.plat.admin.service.sys.IScheduleJobLogService;
import org.jigang.plat.admin.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 *
 * @author wjg
 * @date 2017/1/4.
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private IScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @RequestMapping("/list")
    @RequiresPermissions(PermConstant.SYS_SHCEDULE_LOG)
    public WebResponse list(Integer page, Integer limit, Integer jobId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("jobId", jobId);
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<ScheduleJobLogEntity> jobList = scheduleJobLogService.queryList(map);
        int total = scheduleJobLogService.queryTotal(map);

        PageEntityWrapper pageEntity = new PageEntityWrapper(jobList, total, limit, page);

        return WebResponse.ok().put("page", pageEntity);
    }

    /**
     * 定时任务日志信息
     */
    @RequestMapping("/info/{logId}")
    public WebResponse info(@PathVariable("logId") Long logId){
        ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);

        return WebResponse.ok().put("log", log);
    }
}
