package com.qianfeng.session;

import lombok.Setter;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 时间：  2020/2/11
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Setter
public class MySessionDao extends AbstractSessionDAO {

    private RedisTemplate<String,Object> template;

    /**
     *  当首次请求过来时候,会创建session,参数session就是的。
     *  此时的session 是由 simplesessionfactory创建,但是没有sessionId
     * @param session
     * @return  sessiomId会随着响应回到浏览器的cookie中
     */
    @Override
    protected Serializable doCreate(Session session) {

        System.out.println("create sesion *************");
        Serializable sessionId = this.generateSessionId(session);
        //为session绑定sessionID
        this.assignSessionId(session,sessionId);
        //将session存入到redis
        template.opsForValue().set("session04:"+sessionId,session,10, TimeUnit.SECONDS);
        return sessionId;
    }


    /**
     * 通过sessionid 查询session
     * @param sessionId  参数是从浏览器的cookie中传递过来的
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("read session ***********"+sessionId);
      //设置某个key的过期时间
     template.expire("session04:"+sessionId,10,TimeUnit.SECONDS);
        return (SimpleSession)template.opsForValue().get("session04:"+sessionId);
    }


    /**
     * todo:在用户更新了session中的作用域以后，需要将用户修改后的session，覆盖存储
     *
     * @param session 以后修改后的session对象（sessionid 对象不改变）
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        System.out.println("update sesson :"+session.getId());
        //设置某个key的过期时间
        template.expire("session04:"+session.getId(),10,TimeUnit.SECONDS);
        template.opsForValue().set("session04:"+session.getId(),session);
    }

    /**
     * todo:在发现session过期以后，删除session
     * @param session
     */
    @Override
    public void delete(Session session) {
        System.out.println("delete session"+session.getId());
        template.delete("session04:"+session.getId());
    }

    /**
     * todo:查询出所有的session。以供检测
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        Set  keys = template.keys("session04:*");
        List<Session> sessions = template.opsForValue().multiGet(keys);
        return sessions;
    }
}
