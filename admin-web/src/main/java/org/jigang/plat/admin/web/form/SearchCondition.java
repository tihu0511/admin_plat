package org.jigang.plat.admin.web.form;

/**
 * 搜索条件
 *
 * @author wjg
 * @date 2017/1/5.
 */
public class SearchCondition {
    private Integer limit; //记录行数
    private Integer page; //页数
    private String order; //排序 asc desc
    private String filters; //过滤条件

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }
}
