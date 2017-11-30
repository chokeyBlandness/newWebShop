<%@ page import="com.cn.model.Trolley" %>
<%@ page import="com.cn.model.PurchasingMerchandise" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/30
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>trolley</title>
</head>
<body>
    <%
        Trolley trolley= (Trolley) request.getSession().getAttribute("trolley");
        if (trolley!=null){
            for (PurchasingMerchandise merchandise:trolley.getMerchandises()){
    %>
    <div style="border: solid">
        <img alt="Error show" width="180px" height="240px" src=<%=merchandise.getMerchandise().getPictureSource()%> />
        <%=merchandise.getMerchandise().getName()%>
        PRICE:<%=merchandise.getMerchandise().getPrice()%>
        NUMBER:<%=merchandise.getMerchandiesNumber()%>
    </div><br/>
    <%}}%>
    <b style="float: right">SUM:<%=trolley.getSummaryCost()%></b>
</body>
</html>
