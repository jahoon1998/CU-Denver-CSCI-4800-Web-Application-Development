<!DOCTYPE html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ page session="true" %>

        <link href="styles/home.css" rel="stylesheet" type="text/css"/>
        <meta charset="UTF-8">
    <div id="header">
        <%@ include file="header.jsp" %>
    </div>
    <title>Twitter Layout</title>
</head>

<body>

    <div id="container">
        <div id="header"></div>
        <div id="body">
            <main>
                <aside>
                    <div id="profile">
                        <div id="profileInfo">
                            <img src="images/Cr7.jpg" height="100" width="80">
                            <div id="profileUser">
                                <h1><c:out value="${user.fullName}"/></h1><br>
                                <c:out value="${user.userName}"/>                                
                            </div>
                        </div>
                        <div id="belowProfile">
                            Tweets<br><c:out value="${numOfTweets}"/>                             
                        </div>
                    </div>
                    <div id="trends">
                        trends
                    </div>
                </aside>
                <section>
                    <form>
                        <input type="hidden" name="action" value="insertTweet">
                        <div class="post">
                            <div id="insidePost">
                                <input type="hidden" name="email" value="${user.emailAddress}"> 
                                <input type="hidden" name="userName" value="${user.userName}"> 
                                Post: <textarea name="tweet" id="tweetText"> </textarea>
                                <button type="submit" class="tweetButton">TWEET</button>
                            </div>
                        </div>
                    </form>

                    <article>                                                        
                        <c:forEach var="tweet" items="${tweets}">
                            <div class = "tweetPost">
                                <span class="a">    ${tweet.getEmailAddress()} </span>                              
                                <c:if test="${user.emailAddress == tweet.getEmailAddress()}">
                                    <a class="d" href="registration?action=deleteTweet&amp;tweet=${tweet.getTwitt()}">Delete Tweet</a>
                                </c:if>
                                <span class="b">    ${tweet.getDate()}         </span></br>
                                <span class="c">    ${tweet.getTwitt()}        </span>
                                
                            </div>
                        </c:forEach>
                    </article>

                </section>
                <div id="dashboard">
                    <div id="dashboard-body">Who To Follow</div>
                    <div id="dashboard-footer"></div
                </div>

            </main>

        </div>

        <div id="footer">
            <%@ include file="footer.jsp" %></div>
    </div>
    <nav>

    </nav>

</body>

</html>
