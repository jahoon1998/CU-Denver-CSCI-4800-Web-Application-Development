<%-- 
    Document   : forgotpassword.jsp
    Created on : Sep 24, 2015, 6:47:09 PM
    Author     : xl
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
            <form action="registration" method="post" >
               
               <div id=“errorMessage” class="notVisible"></div>

               <p style="font-size: 250%; font:Helvetica Neue; text-align: center;">Forgot Password?</p><br>
               <input type="hidden" name="action" value="forgotpassword"> 
             
               <label class="pad_top">Email:</label>
               <input type="email" id="email" name="emailAddress" value="${user.emailAddress}" required>
               <span id="email_error" class="notVisible"> *</span><br>  
     
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
                   <input type="submit" name="FGpassword" value="Submit" class="submit" style="border-radius: 100px;">    
               <span id="submit_error" class="notVisible"> *</span><br>
           </form>
      <img src="https://pbs.twimg.com/profile_images/1013798240683266048/zRim1x6M.jpg" alt="Twitter" style="width: 50%; ">

      
      </div>
    <c:import url="footer.jsp" />
    </body>
</html>
