<%-- 
    Document   : header.jsp
    Created on : Sep 24, 2015, 6:47:09 PM
    Author     : xl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>     
    <h1 style="text-align: center">My Twitter</h1>
    
    <c:if test ="${user != null && success}"> 
        <div style="text-align: right"><a href="registration?action=signout">Sign out</a></div>
    </c:if>
    </body>
</html>
