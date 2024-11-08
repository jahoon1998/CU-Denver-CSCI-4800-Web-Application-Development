<%-- 
    Document   : notifications
    Created on : Nov 13, 2018, 4:35:14 PM
    Author     : sagarluitel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ page session="true" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/home.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <div id="header">
        <%@ include file="header.jsp" %>
    </div>
    </head>
    <body>
        <div class = "notification">
            NOTIFICATIONS<br>
            <%--     <c:forEach var="tweet" items="${notification}">
                <div class="notilist">
                <span> ${tweet.getEmailAddress()} Mention you in this tweet </span><br>
                <span> ${tweet.getTwitt()} </span>
                </div>
            </c:forEach>
            <c:forEach var="follow" items="${followlist}">
                <div class="notilist">
                    <span><span style="color: red;"> ${follow.getFollowedUserName()}</span> started following you </span><br>               
                </div>
</c:forEach> --%>
            <c:forEach var="i" items="${noti}">
               <div class="notilist"> 
                <c:if test="${i.getFollowedUserName() == null}">
                      <span> ${i.getMentionUser()} Mention you in this tweet </span><br>
                      <span> ${i.getTweet()}  </span><br>
                </c:if>              
                <c:if test ="${i.getTweet() == null}">   
                         <span><span style="color: red;"> ${i.getFollowedUserName()}</span> started following you </span><br>
</c:if>
                 </div>
            </c:forEach>
            
        </div>
        <div id="footer">
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
