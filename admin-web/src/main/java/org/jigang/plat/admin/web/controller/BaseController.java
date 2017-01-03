package org.jigang.plat.admin.web.controller;

import org.jigang.plat.admin.entity.sys.SysUserEntity;
import org.jigang.plat.admin.util.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller基类
 *
 * @author wjg
 * @date 2017/1/1.
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return ShiroUtil.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
