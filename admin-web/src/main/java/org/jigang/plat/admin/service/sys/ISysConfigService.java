package org.jigang.plat.admin.service.sys;

import org.jigang.plat.admin.entity.sys.SysConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/3.
 */
public interface ISysConfigService {
    /**
     * 保存配置信息
     */
    public void save(SysConfigEntity config);

    /**
     * 更新配置信息
     */
    public void update(SysConfigEntity config);

    /**
     * 根据key，更新value
     */
    public void updateValueByKey(String key, String value);


    /**
     * 删除配置信息
     */
    public void deleteBatch(Long[] ids);

    /**
     * 获取List列表
     */
    public List<SysConfigEntity> queryList(Map<String, Object> map);

    List<SysConfigEntity> queryList(Map<String, Object> map, SysConfigEntity configCondition);

    /**
     * 获取总记录数
     */
    int queryTotal(Map<String, Object> map);

    int queryTotal(Map<String, Object> map, SysConfigEntity configCondition);

    public SysConfigEntity queryObject(Long id);

    /**
     * 根据key，获取配置的value值
     *
     * @param key           key
     * @param defaultValue  缺省值
     */
    public String getValue(String key, String defaultValue);

    /**
     * 根据key，获取value的Object对象
     * @param key    key
     * @param clazz  Object对象
     */
    public <T> T getConfigObject(String key, Class<T> clazz) throws Exception;


}
