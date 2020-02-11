package com.qianfeng.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 时间：  2020/2/10
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
public class MyCache implements Cache {

    //打球mapper的namespace
    private String id;

    public MyCache(){}

    public MyCache(String id){
        this.id=id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    /**
     *  todo:当查询结束后，将数据存入缓存
     * @param key   此次查询的表示其中包含了SQL
     * @param value  此次存入的结果数据  User List<User>
     */

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate template =
                (RedisTemplate)ContextLoader.getCurrentWebApplicationContext().getBean(
                "redisTemplate");
        System.out.println("开始缓存数据 ,key: "+key+"  value:  "+value);
        template.opsForValue().set(key.toString(),value);
    }

    @Override
    public Object getObject(Object key) {

        RedisTemplate template =
                (RedisTemplate)ContextLoader.getCurrentWebApplicationContext().getBean(
                        "redisTemplate");

        System.out.println("检查缓存 key: "+key.getClass());
        Object cache = template.opsForValue().get(key.toString());
        if (cache !=null){
            System.out.println("有缓存，开始查询缓存");
            return cache;
        }else {
            System.out.println("缓存没有命中！！！！！！！！");
            return null;
        }

    }

    /**
     * todo:删除某一个缓存
     * @param key
     * @return
     */

    @Override
    public Object removeObject(Object key) {
        RedisTemplate template =
                (RedisTemplate)ContextLoader.getCurrentWebApplicationContext().getBean(
                        "redisTemplate");
        template.delete(key.toString());
        return null;
    }

    /**
     * 当一个mapper中触发了任意一个写的操作，该mapper的所有缓存都删除
     */
    @Override
    public void clear() {
        System.out.println("namespace :"+this.id+"发生了写操作，，，所有清空了缓存");
        RedisTemplate template =
                (RedisTemplate)ContextLoader.getCurrentWebApplicationContext().getBean(
                        "redisTemplate");
        //获取当前mapper下的所有缓存的key
        Set keys = template.keys("*" + this.id + "*");
       //删除这些key
        template.delete(keys);


    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
