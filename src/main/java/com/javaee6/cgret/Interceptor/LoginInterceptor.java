package com.javaee6.cgret.Interceptor;

import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.util.ClientThreadLocal;
import com.javaee6.cgret.util.SessionSave;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

/**
 * @author Administrator
 **/

public class LoginInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 占线，强制下线功能
        Client client = ClientThreadLocal.get();
        if(SessionSave.getSessionIdMap().get(client.getClientId().toString()) == null){
            return super.preHandle(request, response, handler);
        }

        String sessionIdInMap = SessionSave.getSessionIdMap().get(client.getClientId().toString());
        String currentSessionId = request.getRequestedSessionId();

        System.out.println("我正在路过登录拦截器,当前用户是" + client.getClientName() + ",的sessionId为" + currentSessionId);
        System.out.println("在map中的该用户的sesionId为" + sessionIdInMap);

        // 如果当前用户的sessionId和map中的sessionId不一样，则意味着用户是在两个地方登录，那么在第二个登陆的地方不允许登录
        // 实现一个用户只能在一个地方登录
        if( !sessionIdInMap.equals(currentSessionId)){
            String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
            response.sendRedirect(serverPath + "/Login/login");
            return false;
        }
        // 如果在跳转到主页面前发现，map中已经有了sessionId，那么意味着这个sessionId对应的浏览器（地址）已经有用户登录，那么当前的用户不允许登录该地址
        // 并且需要remove掉已经设置的clientSession(这种方法不可行，因为新的登录会覆盖之前的登录session，意味着如果remove掉，那么之前的也会remove掉)。
        // 新方法是登陆前判断
        return super.preHandle(request, response, handler);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }
}
