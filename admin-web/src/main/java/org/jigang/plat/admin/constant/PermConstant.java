package org.jigang.plat.admin.constant;

/**
 * 权限
 *
 * @author wjg
 * @date 2017/1/4.
 */
public interface PermConstant {
    //用户
    String SYS_USER_LIST = "sys:user:list"; //用户列表
    String SYS_USER_INFO = "sys:user:info"; //用户信息
    String SYS_USER_SAVE = "sys:user:save"; //用户保存
    String SYS_USER_UPDATE = "sys:user:update"; //用户更新
    String SYS_USER_DELETE = "sys:user:delete"; //用户删除

    //角色
    String SYS_ROLE_LIST = "sys:role:list"; //角色列表
    String SYS_ROLE_SELECT = "sys:role:select"; //角色选择
    String SYS_ROLE_INFO = "sys:role:info"; //角色信息
    String SYS_ROLE_SAVE = "sys:role:save"; //角色新增
    String SYS_ROLE_UPDATE = "sys:role:update"; //角色更新
    String SYS_ROLE_DELETE = "sys:role:delete"; //角色删除

    //菜单
    String SYS_MENU_LIST = "sys:menu:list"; //菜单列表
    String SYS_MENU_SELECT = "sys:menu:select"; //菜单选择
    String SYS_MENU_PERMS = "sys:menu:perms"; //菜单授权
    String SYS_MENU_INFO = "sys:menu:info"; //菜单信息
    String SYS_MENU_SAVE = "sys:menu:save"; //菜单保存
    String SYS_MENU_UPDATE = "sys:menu:update"; //菜单更新
    String SYS_MENU_DELETE = "sys:menu:delete"; //菜单删除

    //参数配置
    String SYS_CONFIG_LIST = "sys:config:list"; //参数列表
    String SYS_CONFIG_INFO = "sys:config:info"; //参数信息
    String SYS_CONFIG_SAVE = "sys:config:save"; //参数保存
    String SYS_CONFIG_UPDATE = "sys:config:update"; //参数更新
    String SYS_CONFIG_DELETE = "sys:config:delete"; //参数删除

    //定时任务
    String SYS_SHCEDULE_LIST = "sys:schedule:list"; //定时任务列表
    String SYS_SHCEDULE_INFO = "sys:schedule:info"; //定时任务信息
    String SYS_SHCEDULE_SAVE = "sys:schedule:save"; //定时任务保存
    String SYS_SHCEDULE_UPDATE = "sys:schedule:update"; //定时任务更新
    String SYS_SHCEDULE_DELETE = "sys:schedule:delete"; //定时任务删除
    String SYS_SHCEDULE_RUN = "sys:schedule:run"; //定时任务运行
    String SYS_SHCEDULE_PAUSE = "sys:schedule:pause"; //定时任务暂停
    String SYS_SHCEDULE_RESUME = "sys:schedule:resume"; //定时任务恢复
    String SYS_SHCEDULE_LOG = "sys:schedule:log"; //定时任务日志

}
