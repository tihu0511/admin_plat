package org.jigang.plat.admin.web.form.sys;

import org.jigang.plat.admin.web.form.BaseForm;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/7.
 */
public class ScheduleForm extends BaseForm {
    private Long jobId;
    private String beanName;
    private String methodName;
    private Integer status;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
