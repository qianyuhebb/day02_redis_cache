package com.qianfeng.controller;

import com.qianfeng.pojo.User;
import com.qianfeng.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 时间：  2020/2/9
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){

        System.out.println(" goto登录页面");

        return "forward:/WEB-INF/login.jsp";
    }

    @PostMapping("/login")
    public  String loginlogic(User user){
        System.out.println("login logic ");
        //获取subject ，调用login
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //登录失败会抛出异常，会交给异常解析器处理
        //默认是false  记住我
        token.setRememberMe(true);
        subject.login(token);
        Session session = subject.getSession();
        // session.setAttribute("");

        //登录成功，就跳转success
        return "forward:/WEB-INF/success.jsp";
    }

    @GetMapping("/users")
    public String selectAllUser(Model model){
        List<User> users = userService.selectAllUser();
      /*  for (User user : users) {

            System.out.println(user);
            System.out.println(user.getUsername());
        }*/
      model.addAttribute("users",users);
       // System.out.println(model);

        return "forward:/WEB-INF/users.jsp";
    }

    @PostMapping("regist")
    public String saveUser(User user){
          userService.insertUser(user);
        return "redirect:/users";
    }

    @GetMapping("regist")
    public String registpage(){

        return "forward:/WEB-INF/regist.jsp";
    }

    @RequestMapping("/error")
    public  String permission(){
        System.out.println("permission ---------------- ");

        return "forward:/WEB-INF/error.jsp";
    }
}
