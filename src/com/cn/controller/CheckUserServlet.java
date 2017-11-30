package com.cn.controller;

import com.cn.model.ConnectDatabase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "CheckUserServlet")
public class CheckUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginMessage="";
        String passwordFromDatabase="";
        boolean findAccountFromDatabase=true;

        String loginAccount=request.getParameter("account");
        String loginPassword=request.getParameter("password");

        ConnectDatabase message=new ConnectDatabase();
        ResultSet resultSet=null;
        try {
            resultSet=message.searchMember(loginAccount);
            if (resultSet.next()==false){//donnot find account
                findAccountFromDatabase=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!findAccountFromDatabase) {
            loginMessage = "no this account!";
        }else {
            try {
                passwordFromDatabase= resultSet.getString("password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!loginPassword.equals(passwordFromDatabase)){
                loginMessage="wrong login!";
            }
            else {
                HttpSession session = request.getSession(true);
                loginMessage = "successfully login!";
                session.setAttribute("account", loginAccount);
                session.setAttribute("loginMessage", loginMessage);
                response.sendRedirect("webShop.jsp");
                return;
            }
        }
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        message.closeConnection();
        request.setAttribute("loginMessage",loginMessage);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
