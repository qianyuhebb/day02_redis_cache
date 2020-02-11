package com.qianfeng.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * 时间：  2020/2/3
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
public class MySessionListener extends SessionListenerAdapter {


    /**
     * session 创建时触发
     * @param session
     */
    @Override
    public void onStart(Session session) {
        System.out.println("session create onstart");
    }

    /**
     * session停止时候触发
     * @param session
     */
    @Override
    public void onStop(Session session) {
        System.out.println("session create onStop");
    }

    /**
     * session 过期的时候触发 过期触发存在被动的触发的情况  所以需要检测功能区检测是否过期
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        System.out.println("session create onExpiration");
    }
}
