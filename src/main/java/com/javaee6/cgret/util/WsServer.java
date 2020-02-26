package com.javaee6.cgret.util;

import com.javaee6.cgret.listener.SessionAttributeListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 **/

@ServerEndpoint("/webSocket/chat/{roomName}")
public class WsServer {

    // map存储各个用户的session,key为clientId，value就是同一个房间的用户的session
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap<>();

    @OnOpen
    public void connect(@PathParam("roomName") String roomName, Session session){
        // 将各个房间的session隔离
        if(!rooms.containsKey(roomName)){
            // 不存在，创建
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            rooms.put(roomName, room);
        } else{
            // 房间存在，则将对应的session（用户）加入这个房间
            rooms.get(roomName).add(session);
        }
        System.out.println( "client has connected! ");
        Thread thread = new Thread();

    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, Session session){
        rooms.get(roomName).remove(session);
        System.out.println( roomName + "has disconnected");
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName, String msg, Session session) throws IOException {
        // 此处应该有html过滤
        msg = session.getId() + ":" + msg;
        System.out.println(msg);

        // 接收到信息后广播
        broadcast(roomName, msg);
    }

    // 按照房间名字广播
    private static void broadcast(String roomName, String msg) throws IOException {
        for(Session session : rooms.get(roomName)){
            session.getBasicRemote().sendText(msg);
        }
    }


}

