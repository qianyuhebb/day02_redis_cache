package com.qianfeng.cache;

import lombok.Setter;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 时间：  2020/2/11
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Setter
public class MyShiroCacheManager extends AbstractCacheManager {


    private RedisTemplate<String ,Object> template;
    /**
     * shiro需要缓存数据（身份信息，权限信息）的时候，都会需要cacheManager
     * cacheManager的核心工作，就是创建一个缓存对象
     * @param s
     * @return
     * @throws CacheException
     */
    @Override
    protected Cache createCache(String s) throws CacheException {
        MyShiroCache cache = new MyShiroCache(s);
        cache.setTemplate(template);
        return cache;
    }
}
