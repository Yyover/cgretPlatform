package com.javaee6.cgret.controller;

import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.service.impl.CustomWebSocketHandler;
import com.javaee6.cgret.util.ClientThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.SocketHandler;

/**
 * @author Administrator
 **/

@RequestMapping("/socket")
@Controller
public class SocketController {

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Resource
    private CustomWebSocketHandler socketHandler;

    @RequestMapping("/chat")
    public String buildConnection(HttpSession session){

        Client client = ClientThreadLocal.get();
        logger.info("用户" + client.getClientName() + "建立了连接");

        // 这里将user的name写进了user中
        // 这个时候还没有握手
        // 进入聊天室
        session.setAttribute("user", client.getClientName());

        return "Client_ChatRoom";
    }


}
