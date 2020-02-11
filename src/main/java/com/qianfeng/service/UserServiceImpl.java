package com.qianfeng.service;

import com.qianfeng.constant.MyConstant;
import com.qianfeng.dao.IUserDao;
import com.qianfeng.pojo.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 时间：  2020/2/9
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public Integer insertUser(User user) {
        String salt = UUID.randomUUID().toString();
        String sha256Hash = new Sha256Hash(user.getPassword(), salt, MyConstant.ITERACOUNT).toBase64();
         user.setPassword(sha256Hash);
         user.setSalt(salt);

        return userDao.insertUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryUserByUsername(String username) {

        User user = userDao.queryUserByUsername(username);

        return user;
    }
}
