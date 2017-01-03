package org.jigang.plat.admin.service.sys.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.jigang.plat.admin.dao.sys.ISysConfigDao;
import org.jigang.plat.admin.entity.sys.SysConfigEntity;
import org.jigang.plat.admin.service.sys.ISysConfigService;
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
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    @Autowired
    private ISysConfigDao sysConfigDao;

    @Override
    public void save(SysConfigEntity config) {
        sysConfigDao.save(config);
    }

    @Override
    public void update(SysConfigEntity config) {
        sysConfigDao.update(config);
    }

    @Override
    public void updateValueByKey(String key, String value) {
        sysConfigDao.updateValueByKey(key, value);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysConfigDao.deleteBatch(ids);
    }

    @Override
    public List<SysConfigEntity> queryList(Map<String, Object> map) {
        return sysConfigDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysConfigDao.queryTotal(map);
    }

    @Override
    public SysConfigEntity queryObject(Long id) {
        return sysConfigDao.queryObject(id);
    }

    @Override
    public String getValue(String key, String defaultValue) {
        String value = sysConfigDao.queryByKey(key);
        if(StringUtils.isBlank(value)){
            return defaultValue;
        }
        return value;
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) throws Exception {
        String value = getValue(key, null);
        if(StringUtils.isNotBlank(value)){
            return JSON.parseObject(value, clazz);
        }

        return clazz.newInstance();
    }
}
