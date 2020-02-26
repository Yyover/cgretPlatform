package com.javaee6.cgret.Filter;


import com.javaee6.cgret.model.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.xml.registry.infomodel.User;
import java.io.IOException;

/**
 * session过滤器
 * @author Administrator
 **/
public class SessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        Client userSession = (Client) request.getSession().getAttribute("clientInfo");

        if (userSession != null) {
            //重新设值session
            //需要先销毁，再添加，否则无法触发监听器的addAttribute
            request.getSession().removeAttribute("clientInfo");
            request.getSession().setAttribute("clientInfo", userSession);
        }


        filterChain.doFilter(req, res);

    }

    @Override
    public void destroy() {

    }
}
