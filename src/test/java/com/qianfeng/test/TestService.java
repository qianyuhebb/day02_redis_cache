package com.qianfeng.test;

import com.qianfeng.dao.IUserDao;
import com.qianfeng.pojo.User;
import com.qianfeng.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 时间：  2020/2/9
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestService {

    @Autowired
    private UserService userService;

    @Test
    public void testService(){

        List<User> users = userService.selectAllUser();

        for (User user : users) {
            System.out.println(user);
        }
    }
}
