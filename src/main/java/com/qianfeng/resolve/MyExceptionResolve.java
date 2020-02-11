package com.qianfeng.resolve;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 时间：  2020/2/2
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
public class MyExceptionResolve implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println(ex.getClass());
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView();
        if (ex instanceof IncorrectCredentialsException ){

         System.out.println("密码错误");
         //重定向发的是get请求
         mv.setViewName("redirect:/login");
     }else if (ex instanceof UnknownAccountException){
         System.out.println("用户名错误");
            mv.setViewName("redirect:/login");
     }else if (ex instanceof UnauthorizedException || ex instanceof  UnauthenticatedException){
            //权限不足跳转的页面

            mv.setViewName("redirect:/error");
        }

       return mv;
    }
}
