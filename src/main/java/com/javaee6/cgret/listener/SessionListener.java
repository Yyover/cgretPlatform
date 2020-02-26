package com.javaee6.cgret.listener;

import com.javaee6.cgret.util.OnlineCount;
import com.javaee6.cgret.util.SessionSave;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collection;
import java.util.Map;

/**
 * @author Administrator
 **/

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        System.out.println("采用sessionId统计的人数：" + SessionSave.getSessionIdMap().size());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        Map<String, String> map = SessionSave.getSessionIdMap();

        String sessionId = httpSessionEvent.getSession().getId();

        // 根据value删除
        Collection<String> col = map.values();
        while ( true == col.contains(sessionId)){
            col.remove(sessionId);
        }

        System.out.println("当前在线人数为：" + SessionSave.getSessionIdMap().size() );
    }
}
