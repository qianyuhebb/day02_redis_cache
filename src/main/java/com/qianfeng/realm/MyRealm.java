package com.qianfeng.realm;

import com.qianfeng.pojo.User;
import com.qianfeng.service.PermissionService;
import com.qianfeng.service.RoleService;
import com.qianfeng.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 时间：  2020/2/2
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Setter
@Getter
public class MyRealm extends AuthorizingRealm {



      private UserService userService;
      private RoleService roleService;
      private PermissionService permissionService;

    /*作用：查询权限信息
    *何时触发：/user/query = roles["admin"]  /user/delete = authc,perms["user:update","user:delete"] <shiro:hasRole name="admin">
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        //查询用户信息
        /*RoleService roleService = ContextLoader.getCurrentWebApplicationContext().getBean("roleServiceImpl",
                RoleService.class);
        PermissionService permissionService = ContextLoader.getCurrentWebApplicationContext().getBean(
                "permissionServiceImpl",
                PermissionService.class);*/
        Set<String> roles =  roleService.queryRoleByUsername(username);
        Set<String> perms = permissionService.queryPermsByusername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.setStringPermissions(perms);

        return simpleAuthorizationInfo;
    }

    /*作用：查询身份信息,并且返回即可,不用任何对比
     查询方式：通过用户名查询用户信息
    * 何时触发：登录的时候,
    *
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {



        String  username = (String) token.getPrincipal();

        //查询用户信息  和spring整合以后，就不需要这一句代码
       /* UserService userService =
                ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl", UserService.class);*/
        User user = userService.queryUserByUsername(username);
         //判断用户是否为空
        if (user == null) {
            return null;    //后续会抛出异常
        }
     /*    AuthenticationInfo authenticationInfo  = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword()
                 , this.getName());*/

        AuthenticationInfo authenticationInfo  = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                //把盐传入
                ByteSource.Util.bytes(user.getSalt())
                , this.getName());

        return authenticationInfo;
    }
}
