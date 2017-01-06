package org.jigang.plat.admin.web.form;

import java.io.Serializable;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/6.
 */
public class BaseForm implements Serializable {
    private Integer page; //页号
    private Integer limit; //行数
    private String order; //排序标识 asc desc

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
