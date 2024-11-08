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
        <link href="styles/home.css" rel="stylesheet" type="text/css"/>
        <%@ page session="true" %>
        <title>JSP Page</title>
    </head>
    <body> 
    
    <c:if test ="${user != null && success}"> 
        <div class="menu">
            <span class ="menuOpt" id = "home">  <a href="registration?action=home">Home</a></span>
            <span class ="menuOpt" id = "noti">  <a href="registration?action=notification&amp;username=${user.userName}">
                    Notification ${numofNoti}</a></span>
            <span class ="menuOpt" id = "pro1">  <a href="registration?action=profile">Profile</a></span>
           
            <span class ="menuOpt" id= "signout"><a href="registration?action=signout">Sign out</a></span>
            <span id=  "myT1">      <a>My Twitter</a></span>
        </div>
    </c:if>
    </body>
</html>
