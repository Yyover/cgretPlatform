package com.javaee6.cgret.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.SocketHandler;


/**
 * @author Administrator
 **/
@Service
public class CustomWebSocketHandler extends TextWebSocketHandler implements WebSocketHandler {

    private static final Logger logger;
    private static final ArrayList<WebSocketSession> sessionss;

    static{
        sessionss = new ArrayList<WebSocketSession>();
        logger = LoggerFactory.getLogger(SocketHandler.class);
    }

    /**
     * Invoked after WebSocket negotiation has succeeded and the WebSocket connection is
     * opened and ready for use.
     *
     * @param session
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("成功建立socket连接,我是handler");
        System.out.println("成功建立socket连接，我是handler");
        sessionss.add(session);
        String userName = session.getAttributes().get("user").toString();
        if(userName != null){
            session.sendMessage(new TextMessage("我们已经建立了连接，快来聊天吧~"));
        }
    }

    /**
     * Invoked when a new WebSocket message arrives.
     *
     * @param session
     * @param message
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    /**
     * Handle an error from the underlying WebSocket message transport.
     *
     * @param session
     * @param exception
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("连接出现错误，我是handler");
        logger.debug("连接出现错误:" + exception.toString());
    }

    /**
     * Invoked after the WebSocket connection has been closed by either side, or after a
     * transport error has occurred. Although the session may technically still be open,
     * depending on the underlying implementation, sending messages at this point is
     * discouraged and most likely will not succeed.
     *
     * @param session
     * @param closeStatus
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("连接已关闭");
        System.out.println("连接关闭，我是handler");
        sessionss.remove(session);
    }

    /**
     * Whether the WebSocketHandler handles partial messages. If this flag is set to
     * {@code true} and the underlying WebSocket server supports partial messages,
     * then a large WebSocket message, or one of an unknown size may be split and
     * maybe received over multiple calls to
     * {@link #handleMessage(WebSocketSession, WebSocketMessage)}. The flag
     * {@link WebSocketMessage#isLast()} indicates if
     * the message is partial and whether it is the last part.
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发信息
     * @param message
     * @throws IOException
     */
    public void sendMessageToOnlineUser(TextMessage message) throws IOException {

        for(WebSocketSession webSocketSession : sessionss){
            if(webSocketSession.isOpen()){
                webSocketSession.sendMessage(message);
            }
        }
    }

    /**
     * 给某个用户发信息
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) throws IOException {
        for(WebSocketSession webSocketSession : sessionss){
            if(webSocketSession.getAttributes().get("user").equals(userName)){
                if(webSocketSession.isOpen()){
                    webSocketSession.sendMessage(message);
                    System.out.println("发送消息，我是handler");
                }
                //break;
            }
        }
    }
}
