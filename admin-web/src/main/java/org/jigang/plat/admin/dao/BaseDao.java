package org.jigang.plat.admin.dao;

import java.util.List;
import java.util.Map;

/**
 * dao基本接口(使用时按需在XML中配置SQL)
 *
 * @author wjg
 * @date 2017/1/1.
 */
public interface BaseDao<T> {
    void save(T t);

    void save(Map<String, Object> map);

    void saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int delete(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    T queryObject(Object id);

    List<T> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int queryTotal();
}
