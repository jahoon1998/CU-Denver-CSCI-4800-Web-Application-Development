<%-- 
    Document   : login.jsp
    Created on : Sep 24, 2015, 6:44:58 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:import url="header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/signin.css" type="text/css"/>
        <script src= "main.js"></script>
    </head>
    <body>
        <h1>
            <script type="text/javascript">
                response.redirect("/membership");
            </script>
        </h1>

        <form action="registration" method="post" style="text-align: center" >
                        
            <p style="font-size: 250%; font:Helvetica Neue; ">Log in</p>
   
            <input type="hidden" name="action" value="login">         
            <input class="pad_top" type="text" name="userName" placeholder="username" id="userName" value ="${user.userName}" required  style="margin-bottom: 0.5em"><br>
            
            <input class="pad_top" type="password" name="password" placeholder="password" id="password" required style="margin-bottom: 0.5em"><br>
            
            <input type="submit" name="submit" value="Log in" class="submit" style=" border-radius: 100px; background-color: skyblue; width: 60px;">
            <input type="checkbox" name="remember" value="remember"> Remember me
            <p style="color: red;">${message}</p>
            <p><a href="forgotpassword.jsp "> Forgot password?</a> </p>
            
            <p>New to Twitter?<a href="signup.jsp "> Sign up now </a> </p>
        </form>    
        
        <c:import url="footer.jsp" />
    </body>
</html>
