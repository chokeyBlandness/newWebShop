package com.cn.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "webShopAccountFilter")
public class webShopAccountFilter implements Filter {
    private FilterConfig config;
    public void destroy() {
        this.config=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        String loginMessage= (String) request.getSession().getAttribute("loginMessage");
        if (loginMessage==null||!loginMessage.equals("successfully login!")){
            response.sendRedirect("login.jsp");
        }else {
            chain.doFilter(request,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.config=config;
    }

}
