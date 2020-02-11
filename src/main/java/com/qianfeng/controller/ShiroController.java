package com.qianfeng.controller;

import org.apache.shiro.authz.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 时间：  2020/2/11
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Controller
@RequestMapping("shiro")
public class ShiroController {

    //需要合法的身份，记住我是不能通过验证的
    @RequiresAuthentication
    @RequestMapping("test1")
    public String test1(){
        System.out.println("test1通过验证，已经正常登录");
        return "index";
    }


    //需要合法的身份，或者记住我也可以通过校验
    @RequiresUser
    @RequestMapping("test2")
    public String test2(){
        System.out.println("test2通过验证，已经正常登录");
        return "index";
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"user:query","user:delete"},logical = Logical.OR)
    @RequestMapping("test3")
    public String test3(){
        System.out.println("test3，已经正常登录,有user:delete或者user:query权限");
        return "index";
    }

    @RequiresAuthentication
    @RequiresPermissions(value = {"user:query","user:delete"},logical = Logical.AND)
    @RequestMapping("test32")
    public String test32(){
        System.out.println("test32，已经正常登录,有user:delete 且 user:query权限");
        return "index";
    }
    @RequiresAuthentication
    @RequiresRoles(value = {"banzhang","student"},logical = Logical.OR)
    @RequestMapping("test4")
    public String test4(){
        System.out.println("test4，已经正常登录,有banzhang 或 student角色 ");
        return "index";
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"banzhang","student"},logical = Logical.AND)
    @RequestMapping("test42")
    public String test42(){
        System.out.println("test42，已经正常登录,有banzhang 且 student角色 ");
        return "index";
    }
}
