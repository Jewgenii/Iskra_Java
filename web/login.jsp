<%-- 
    Document   : login
    Created on : 13.09.2017, 15:23:37
    Author     : Sergey Nikonenko
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Автентифікація</title>
    <img src="img/logo.png" id="logo"><br>
    <style>
        <%@include file='css/login.css' %>         
    </style>

</head>
<body>
    <h3>Введіть ім'я користувача</h3>
    <%session.invalidate();%>
    <div class="login">

        <form method="POST" name='form-login' action='AuthServlet'>

            <input type="text" name="usename" id="user" placeholder="ім'я користувача">

            <!--<span class="fontawesome-lock"></span>
              <input type="password" id="pass" placeholder="Password">-->

            <input class="login-btn" type="submit" value="Увійти">
        </form>
    </div>
</body>
</html>
