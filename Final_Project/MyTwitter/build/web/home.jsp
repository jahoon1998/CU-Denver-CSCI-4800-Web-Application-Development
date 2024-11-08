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
                            Tweets: <c:out value="${numOfTweets}"/>
                            Following: <c:out value="${numOfFollowing}"/>
                            Follower: <c:out value="${numOfFollwer}"/>                      
                        </div>
                    </div>
                    <div id="trends">
                        trends <br>
                        <c:forEach var= "trending" items="${trandingHash}"> 
                            <span class = "tredItems">
                            <a href="registration?action=hashtag&amp;hashtagid=${trending.getHastagID()}"> ${trending.getHastag()} </a>  <br>
                            </span>
                        </c:forEach>
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
                                    <a class="d" href="registration?action=deleteTweet&amp;tweet=${tweet.getID()}">Delete Tweet</a>
                                </c:if>
                                   
                                <span class="b">    ${tweet.getDate()}         </span></br>                            
                                <span class="c">    ${tweet.getTwitt()}        </span>
                            </div>
                        
                        </c:forEach>
                    </article>

                </section>
                <div id="dashboard">
                    <div id="dashboard-body">Who To Follow <br>
                        <c:forEach var="users" items="${userFollow}">
                            <span>
                                <option value="${users}">${users}</option>
                                <c:set var="contains" value="false" />
                                <c:forEach var="followed" items="${Followed}">
                                    <span>
                                        <c:if test ="${users == followed.getUserName()}">
                                            <c:set var="contains" value="true"/>
                                        </c:if>    
                                    </span>
                                </c:forEach>
                                <c:choose>
                                    <c:when test ="${contains == true}">
                                        <button onclick="location.href='registration?action=unfollow&AMP;unfollowname=${users}'" type="button">Unfollow</button>
                                        <%--<a href="registration?action=unfollow&AMP;unfollowname=${users}">UnFollow</a>--%> 
                                    </c:when>
                                    <c:otherwise>
                                       <button onclick="location.href='registration?action=follow&AMP;followname=${users}'" type="button">Follow</button>
                                       <%-- <a href="registration?action=follow&amp;followname=${users}">Follow</a> --%>
                                    </c:otherwise>
                                </c:choose>
                            </span>
                            <br>
                        </c:forEach>
                    </div>
                    <div id="dashboard-footer"></div>
                </div>

            </main>

        </div>

        <div id="footer">
            <%@ include file="footer.jsp" %>
        </div>
    </div>
    <nav>

    </nav>

</body>

</html>
