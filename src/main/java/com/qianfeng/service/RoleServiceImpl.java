package com.qianfeng.service;

import com.qianfeng.dao.RoleDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 时间：  2020/2/2
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Set<String> queryRoleByUsername(String username) {
        Set<String> roles = roleDao.queryRoleByUsername(username);

        return roles;
    }
}
