package org.jigang.plat.admin.web.controller.sys;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.jigang.plat.admin.entity.PageEntityWrapper;
import org.jigang.plat.admin.entity.sys.SysUserEntity;
import org.jigang.plat.admin.service.sys.ISysUserRoleService;
import org.jigang.plat.admin.service.sys.ISysUserService;
import org.jigang.plat.admin.util.ShiroUtil;
import org.jigang.plat.admin.util.WebResponse;
import org.jigang.plat.admin.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户controller
 *
 * @author wjg
 * @date 2017/1/1.
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @RequestMapping("/info")
    public WebResponse info(){
        return WebResponse.ok().put("user", getUser());
    }

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public WebResponse list(Integer page, Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<SysUserEntity> userList = sysUserService.queryList(map);
        int total = sysUserService.queryTotal(map);

        PageEntityWrapper pageEntity = new PageEntityWrapper(userList, total, limit, page);

        return WebResponse.ok().put("page", pageEntity);
    }

    /**
     * 修改登录用户密码
     */
    @RequestMapping("/password")
    public WebResponse password(String password, String newPassword){
        if(StringUtils.isBlank(newPassword)){
            return WebResponse.error("新密码不为能空");
        }

        //sha256加密
        password = new Sha256Hash(password).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), password, newPassword);
        if(count == 0){
            return WebResponse.error("原密码不正确");
        }

        //退出
        ShiroUtil.logout();

        return WebResponse.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public WebResponse info(@PathVariable("userId") Long userId){
        SysUserEntity user = sysUserService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return WebResponse.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public WebResponse save(@RequestBody SysUserEntity user){
        if(StringUtils.isBlank(user.getUsername())){
            return WebResponse.error("用户名不能为空");
        }
        if(StringUtils.isBlank(user.getPassword())){
            return WebResponse.error("密码不能为空");
        }

        sysUserService.save(user);

        return WebResponse.ok();
    }

    /**
     * 修改用户
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public WebResponse update(@RequestBody SysUserEntity user){
        if(StringUtils.isBlank(user.getUsername())){
            return WebResponse.error("用户名不能为空");
        }

        sysUserService.update(user);

        return WebResponse.ok();
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public WebResponse delete(@RequestBody Long[] userIds){
        if(ArrayUtils.contains(userIds, 1L)){
            return WebResponse.error("系统管理员不能删除");
        }

        if(ArrayUtils.contains(userIds, getUserId())){
            return WebResponse.error("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return WebResponse.ok();
    }

}
