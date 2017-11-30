package com.cn.controller;

import com.cn.model.ConnectDatabase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signInAccount="";
        String signInPassword="";
        String affirmPassword="";
        String signInMessage="";
        signInAccount=request.getParameter("signInAccount");
        signInPassword=request.getParameter("signInPassword");
        affirmPassword=request.getParameter("affrimPassword");
        boolean signInAccountExist=false;


        ConnectDatabase message=new ConnectDatabase();
        message.createMemberTable();
        ResultSet resultSet=null;
        resultSet=message.searchMember(signInAccount);
        try {
            if (resultSet.next()==true){
                signInAccountExist=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (signInAccountExist){
            signInMessage="exist account";
        }else {
            if (!signInPassword.equals(affirmPassword)){
                signInMessage="password wrong";
            }else {
                message.insertNewMember(signInAccount,signInPassword);
                message.closeConnection();
                response.sendRedirect("login.jsp");
                return;
            }
        }
        message.closeConnection();
        request.setAttribute("signInMessage",signInMessage);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("signIn.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("signIn.jsp");
    }
}
