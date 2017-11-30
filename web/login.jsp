<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/29
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <a href="SignIn.jsp">sign in</a>
    <form action="CheckUserForm" method="post">
        <%
            String loginMessage= (String) request.getAttribute("loginMessage");
            if (loginMessage==null){
                loginMessage="";
            }
        %>
        <b><%=loginMessage%></b><br/>
        <b>please input your account and password:</b><br/>
        account:<input type="text" name="account" required="required"/><br/>
        password:<input type="password" name="password"required="required"/><br/>
        <input type="submit" value="submit"/>
        <input type="reset" value="reset"/>
    </form>
</body>
</html>
