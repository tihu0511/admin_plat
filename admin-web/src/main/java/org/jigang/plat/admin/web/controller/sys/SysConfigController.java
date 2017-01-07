package org.jigang.plat.admin.web.controller.sys;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jigang.plat.admin.constant.PermConstant;
import org.jigang.plat.admin.entity.PageEntityWrapper;
import org.jigang.plat.admin.entity.sys.SysConfigEntity;
import org.jigang.plat.admin.exception.AdminException;
import org.jigang.plat.admin.service.sys.ISysConfigService;
import org.jigang.plat.admin.util.WebResponse;
import org.jigang.plat.admin.web.form.sys.ConfigForm;
import org.jigang.plat.core.lang.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数管理
 *
 * @author wjg
 * @date 2017/1/3.
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 所有配置列表
     */
    @RequestMapping("/list")
    @RequiresPermissions(PermConstant.SYS_CONFIG_LIST)
    public WebResponse list(@RequestBody ConfigForm form){
        Integer page = form.getPage();
        Integer limit = form.getLimit();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        SysConfigEntity configCondition = new SysConfigEntity();
        BeanUtil.copyProperties(configCondition, form);

        //查询列表数据
        List<SysConfigEntity> configList = sysConfigService.queryList(map, configCondition);
        int total = sysConfigService.queryTotal(map, configCondition);

        PageEntityWrapper pageEntity = new PageEntityWrapper(configList, total, limit, page);

        return WebResponse.ok().put("page", pageEntity);
    }


    /**
     * 配置信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions(PermConstant.SYS_CONFIG_INFO)
    public WebResponse info(@PathVariable("id") Long id){
        SysConfigEntity config = sysConfigService.queryObject(id);

        return WebResponse.ok().put("config", config);
    }

    /**
     * 保存配置
     */
    @RequestMapping("/save")
    @RequiresPermissions(PermConstant.SYS_CONFIG_SAVE)
    public WebResponse save(@RequestBody SysConfigEntity config){
        //参数校验
        verifyForm(config);

        sysConfigService.save(config);

        return WebResponse.ok();
    }

    /**
     * 修改配置
     */
    @RequestMapping("/update")
    @RequiresPermissions(PermConstant.SYS_CONFIG_UPDATE)
    public WebResponse update(@RequestBody SysConfigEntity config){
        //参数校验
        verifyForm(config);

        sysConfigService.update(config);

        return WebResponse.ok();
    }

    /**
     * 删除配置
     */
    @RequestMapping("/delete")
    @RequiresPermissions(PermConstant.SYS_CONFIG_DELETE)
    public WebResponse delete(@RequestBody Long[] ids){
        sysConfigService.deleteBatch(ids);

        return WebResponse.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysConfigEntity config){
        if(StringUtils.isBlank(config.getKey())){
            throw new AdminException("参数名不能为空");
        }

        if(StringUtils.isBlank(config.getValue())){
            throw new AdminException("参数值不能为空");
        }
    }
}
