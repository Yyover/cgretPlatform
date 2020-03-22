package com.javaee6.cgret.Filter;

import com.javaee6.cgret.model.Client;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Administrator
 **/
@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Client user = (Client) session.getAttribute("clientInfo");

        if(user != null){
            filterChain.doFilter(request,response);
        } else{
            //out.println("<script language='javascript'>alert('你还未登录');");
            response.sendRedirect("/cgret/Login/login");
            //response.sendRedirect("/pages/users/login.jsp");
            //request.getRequestDispatcher("/pages/users/login.jsp").forward(request,response);
        }


    }

    @Override
    public void destroy() {

    }
}
