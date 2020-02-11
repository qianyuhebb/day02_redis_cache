package com.qianfeng.service;

import com.qianfeng.dao.PermissionDao;
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
public class PermissionServiceImpl implements  PermissionService {

   @Resource
   private PermissionDao permissionDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Set<String> queryPermsByusername(String username) {
        Set<String> permsByusernames = permissionDao.queryPermsByusername(username);
        return permsByusernames;
    }
}
