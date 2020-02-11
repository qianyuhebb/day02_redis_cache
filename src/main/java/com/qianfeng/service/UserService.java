package com.qianfeng.service;

import com.qianfeng.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 时间：  2020/2/9
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
public interface UserService {
    List<User> selectAllUser();
    Integer insertUser(User user);
    User queryUserByUsername(@Param("username") String username);
}
