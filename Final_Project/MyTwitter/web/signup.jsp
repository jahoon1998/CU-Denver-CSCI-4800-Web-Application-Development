<%-- 
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:import url="header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title >My Twitter</title>
        <link rel="stylesheet" href="styles/signup.css" type="text/css"/>
        <script src= "main.js"></script>   
    </head>
    <body>
        <div class = "mainDIV" > 
            <%--<div class = "header"> 
                <img src="http://www.stickpng.com/assets/images/580b57fcd9996e24bc43c53e.png" alt="logo">

               <h3>Mini Twitter</h3>
           </div> --%>
            <form action="registration" method="post" onsubmit="return validateForm()" >

                <div id=“errorMessage” class="notVisible"></div>

                <p style="font-size: 250%; font:Helvetica Neue; text-align: center;">Create Your Account</p><br>
                <c:choose>
                    <c:when test ="${user != null && success}">
                        <input type="hidden" name="action" value="update"> 
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="action" value="signup"> 
                    </c:otherwise>
                </c:choose>
                <%-- this
                --%>

                <label class="pad_top">Full Name:</label>
                <input type="text" id="fullName" name="fullName" required value="${user.fullName}">
                <span id="fullName_error" class="notVisible"> *</span><br>
                <c:choose>
                    <c:when test ="${user != null && success}">

                        <label class="pad_top">Username:</label>
                        <input type="text" id="userName" name="userName" value="${user.userName}" required readonly >
                        <span id="userName_error" class="notVisible"> *</span><br>

                        <label class="pad_top">Email:</label>
                        <input type="email" id="email" name="emailAddress" value="${user.emailAddress}" required readonly>
                        <span id="email_error" class="notVisible"> *</span><br>
                    </c:when>

                    <c:otherwise>
                        <label class="pad_top">Username:</label>
                        <input type="text" id="userName" name="userName" value="${user.userName}" required>
                        <span id="userName_error" class="notVisible"> *</span><br>

                        <label class="pad_top">Email:</label>
                        <input type="email" id="email" name="emailAddress" value="${user.emailAddress}" required>
                        <span id="email_error" class="notVisible"> *</span><br>  
                    </c:otherwise>
                </c:choose>
                <label class="pad_top">Password:</label>
                <input type="password" id="password"  name="password" required>
                <span id="password_error" class="notVisible"> *</span><br>

                <label class="pad_top">Confirm Password:</label>
                <input type="password" id="confirmpassword" required>
                <span id="confirmPassword_error" class="notVisible"> *</span><br>
                <div id="errorDiv" class="PWerrorDiv">
                </div><br>

                <label class="pad_top"> Date-of-birth:</label>
                <input type="date" id="dateOfbirth" name="birthdate" value="${user.birthdate}" required>
                <span id="dob_error" class="notVisible"> *</span><br>

                <p>
                    <label>Security Question:</label>
                    <span id="question_error" class="notVisible"> *</span>
                    <select class = "option" name="questionNo" id ="questionNo" value ="${user.questionNo}" onchange="inputBox();">
                        <option  value="SelectOne"> Select one</option>
                        <option  value="1"> What is your first pet?</option>
                        <option  value="2"> What is your first car?</option>
                        <option  value="3"> What is your first school?</option>
                    </select>
                </p>

                <div id = "box" class = "notVisible">
                    <input type="text" id = "InputBox" name = "answer" value = "${user.answer}" required>
                </div>

                <br>
                <h1 style="font:Helvetica Neue; text-align: center; color:red;">${message}</h1>
                <br>

                <c:choose>
                    <c:when test ="${user != null && success}">
                        <input type="submit" name="update" value="Update" class="submit" style="border-radius: 100px;">    
                    </c:when>

                    <c:otherwise>
                        <input type="submit" name="submit" value="Submit" class="submit" style="border-radius: 100px;">    
                    </c:otherwise>
                </c:choose>
                <span id="submit_error" class="notVisible"> *</span><br>
            </form>
            <img src="https://pbs.twimg.com/profile_images/1013798240683266048/zRim1x6M.jpg" alt="Twitter" style="width: 50%; ">


        </div>
        <c:import url="footer.jsp" />
    </body>
</html>
