package org.jigang.plat.admin.constant;

/**
 * 在此处添加类注释
 *
 * @author wjg
 * @date 2017/1/1.
 */
public class AdminConstant {
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
