package com.cn.controller;

import com.cn.model.ConnectDatabase;
import com.cn.model.Merchandise;
import com.cn.model.MerchandiseList;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebFilter(filterName = "ReadAllMerchandisesFilter")
public class ReadAllMerchandisesFilter implements Filter {
    private FilterConfig config;
    public void destroy() {
        this.config=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ConnectDatabase connectDatabase=new ConnectDatabase();
        connectDatabase.createMerchandiseTable();
        ResultSet resultSet=connectDatabase.readAllMerchandises();
        MerchandiseList merchandiseList=new MerchandiseList();
        try {
            while (resultSet.next()){
                Merchandise merchandise=connectDatabase.changeResultToMerchandise(resultSet);
                merchandiseList.addMerchandise(merchandise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpServletRequest request= (HttpServletRequest) req;
        request.setAttribute("allMerchandisesList",merchandiseList);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("webShop.jsp");
        requestDispatcher.forward(request,resp);
        chain.doFilter(request,resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config=config;
    }

}
