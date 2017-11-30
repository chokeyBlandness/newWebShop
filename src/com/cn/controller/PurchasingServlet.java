package com.cn.controller;

import com.cn.model.ConnectDatabase;
import com.cn.model.Merchandise;
import com.cn.model.PurchasingMerchandise;
import com.cn.model.Trolley;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "PurchasingServlet")
public class PurchasingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] purchasedMerchandise=request.getParameterValues("purchase");
        if (purchasedMerchandise!=null){
            Trolley trolley= (Trolley) request.getSession().getAttribute("trolley");
            if (trolley==null){
                trolley=new Trolley();
            }
            ResultSet resultSet;
            ConnectDatabase connectDatabase=new ConnectDatabase();
            for (String merchandiseId:purchasedMerchandise){
                try {
                    String newMerchandiseNumberString=request.getParameter(merchandiseId);
                    int newMerchandiseNumber=1;
                    if (newMerchandiseNumberString!=null&&!newMerchandiseNumberString.equals("")) {
                        newMerchandiseNumber = Integer.parseInt(newMerchandiseNumberString);
                    }
                    if (trolley.changePurchasingMerchandiseNumber(merchandiseId,newMerchandiseNumber)){
                    }else {
                        resultSet=connectDatabase.searchMerchandiseById(merchandiseId);
                        while (resultSet.next()){
                            Merchandise newMerchandise=connectDatabase.changeResultToMerchandise(resultSet);
                            PurchasingMerchandise newPurchasingMerchandise=new PurchasingMerchandise();
                            newPurchasingMerchandise.setMerchandise(newMerchandise);
                            newPurchasingMerchandise.setMerchandiesNumber(newMerchandiseNumber);
                            trolley.addPurchaseMechandise(newPurchasingMerchandise);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            trolley.calculateSummaryCost();
            request.getSession().setAttribute("trolley",trolley);
        }
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("trolley.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("webShop.jsp");
    }
}
