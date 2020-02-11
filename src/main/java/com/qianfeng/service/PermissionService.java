package com.qianfeng.service;

import java.util.Set;

/**
 * 时间：  2020/2/2
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
public interface PermissionService {

    Set<String> queryPermsByusername(String username);
}
