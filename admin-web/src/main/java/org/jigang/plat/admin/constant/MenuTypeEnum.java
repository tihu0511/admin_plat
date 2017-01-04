package org.jigang.plat.admin.constant;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/4.
 */
public enum MenuTypeEnum {
    CATALOG(0, "目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    private int value;
    private String desc;

    private MenuTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
            return value;
        }
}
