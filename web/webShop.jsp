<%@ page import="com.cn.model.MerchandiseList" %>
<%@ page import="com.cn.model.Merchandise" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/27
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>webShop</title>
</head>
<body>
    <form action="webShopForm" method="post">
        <%
            MerchandiseList merchandiseList= (MerchandiseList) request.getAttribute("allMerchandisesList");
            if (merchandiseList!=null){
                for (Merchandise merchandise:merchandiseList.getMerchandiseList()){
        %>
        <div style="height: 200px;width: 180px;float: left">
            <img alt="Error show" width="90px" height="120px"
                 src=<%=merchandise.getPictureSource()%>><br/>
            <b><%=merchandise.getName()%></b><br/>
            <b>PRICE:<%=merchandise.getPrice()%></b><br/>
            <input type="checkbox" name="purchase" value=<%=merchandise.getId()%> />purchase<br/>
            number:<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" 
                          onafterpaste="this.value=this.value.replace(/\D/g,'')"
                          style="width: 60px" name=<%=merchandise.getId()%> />
        </div>
        <%}}%>
        <br/>
        <input type="submit" value="submit"/>
        <input type="reset" value="reset"/>
    </form>
</body>
</html>
