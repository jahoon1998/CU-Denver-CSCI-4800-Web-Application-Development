<%-- 
    Document   : hashTag
    Created on : Dec 3, 2018, 2:48:59 PM
    Author     : sagarluitel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page session="true" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <div id="header">
        <%@ include file="header.jsp" %>
    </head>
    <body>
        <div>
            
            <c:forEach var="tweet" items="${tweetlist}">
                <div class="Tlist">
               
                <span> ${tweet.getTwitt()} </span>
                </div>
            </c:forEach>
        </div>
        
        <div id="footer">
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
