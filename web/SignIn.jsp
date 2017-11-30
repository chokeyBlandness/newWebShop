<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/4
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
    <a href="login.jsp">login</a>
    <form action="SignInForm" method="post">
        <%
            String signInMessage= (String) request.getAttribute("signInMessage");
            if (signInMessage==null){
                signInMessage="";
            }
        %><%=signInMessage%><br/>
        your account:<input type="text" name="signInAccount" required="required"/><br/>
        your password:<input type="password" name="signInPassword" required="required"/><br/>
        affirm password:<input type="password" name="affrimPassword" required="required"/><br/>
        <input type="submit" value="submit"/>
        <input type="reset" value="reset"/>
    </form>
</body>
</html>
