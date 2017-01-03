package org.jigang.plat.admin.quartz;

/**
 * 定时任务状态
 *
 * @author wjg
 * @date 2017/1/3.
 */
public enum ScheduleStatus {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 暂停
     */
    PAUSE(1);

    private int value;

    private ScheduleStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
