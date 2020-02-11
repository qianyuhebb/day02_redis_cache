package com.qianfeng.test;

import com.qianfeng.dao.IUserDao;
import com.qianfeng.pojo.User;
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
public class TestDao {

    @Autowired
    private IUserDao userDao;

    @Test
    public void testDao(){
        List<User> users = userDao.selectAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
