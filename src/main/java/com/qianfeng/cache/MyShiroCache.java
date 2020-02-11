package com.qianfeng.cache;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * 时间：  2020/2/11
 * 创建者：  Administrator 钟文
 * 描述：  完成每个用户信息的权限的redis缓存
 * 参数：
 * 返回值：
 **/
@Setter
@Getter
public class MyShiroCache implements Cache {

    private  String name;//权限缓存的标识 存取缓存的前缀

    private RedisTemplate<String,Object> template;

    public MyShiroCache(){};

    //缓存管理器会调用此构造方法
    public MyShiroCache(String name){
        this.name=name;
    }


    /**
     *todo: 检查缓存，用户凭证为key
     * @param o = key 用户凭证 =用户名
     * @return
     * @throws CacheException
     */
    @Override
    public Object get(Object o) throws CacheException {
        Object o1 = template.opsForValue().get("name:"+o.toString());
        if (o1==null){
            System.out.println("缓存检查未命中");
            return null;
        }
        System.out.println("缓存检查命中缓存了");
        return o1;
    }


    /**
     *  todo:将用户权限信息存入redis的缓存
     * @param o key
     * @param o2 权限信息的数据
     * @return
     * @throws CacheException
     */
    @Override
    public Object put(Object o, Object o2) throws CacheException {
        template.opsForValue().set("name:"+o.toString(),o2);
        return null;
    }

    /**
     * todo:可以移除某个用户的权限信息的缓存
     * @param o key
     * @return
     * @throws CacheException
     */
    @Override
    public Object remove(Object o) throws CacheException {
        System.out.println(o+"的权限信息缓存信息被删除了");
        template.delete(o.toString());
        return null;
    }

    /**
     *
     * @throws CacheException
     */
    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set keys() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }
}
