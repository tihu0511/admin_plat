package org.jigang.plat.admin.util;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.AuthorizationException;
import org.jigang.plat.admin.exception.AdminException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 *
 * @author wjg
 * @date 2017/1/3.
 */
@Component
public class AdminExceptionHandler implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        WebResponse r = new WebResponse();
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");

            if (ex instanceof AdminException) {
                AdminException adminEx = (AdminException) ex;
                r.put("code", adminEx.getCode());
                r.put("msg", adminEx.getMessage());
            }else if(ex instanceof DuplicateKeyException){
                r = WebResponse.error("数据库中已存在该记录");
            }else if(ex instanceof AuthorizationException){
                r = WebResponse.error("没有权限，请联系管理员授权");
            }else{
                r = WebResponse.error();
            }

            //记录异常日志
            logger.error(ex.getMessage(), ex);

            String json = JSON.toJSONString(r);
            response.getWriter().print(json);
        } catch (Exception e) {
            logger.error("AdminExceptionHandler 异常处理失败", e);
        }
        return new ModelAndView();
    }
}
