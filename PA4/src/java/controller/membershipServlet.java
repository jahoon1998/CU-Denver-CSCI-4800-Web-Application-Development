/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.User;
import business.Twitt;
import dataaccess.UserDB;
import dataaccess.TwitDB;
import dataaccess.UserMentionDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.MessagingException;
import java.util.*;

import business.User;
import business.UserMention;
import util.MailUtilGmail;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataaccess.UserDB;
import java.io.File;
import java.security.SecureRandom;

//@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
public class membershipServlet extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }*/
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String url = "/signup.jsp";
        if (action.equals("signup")) {
            // get parameters from the request
            boolean success = false;
            String fullName = request.getParameter("fullName");
            String userName = request.getParameter("userName");
            String email = request.getParameter("emailAddress");
            String dateOB = request.getParameter("birthdate");
            String password = request.getParameter("password");
            String confirmpassword = request.getParameter("confirmpassword");
            String questionNo = request.getParameter("questionNo");
            String answer = request.getParameter("answer");
            String message;

            User user = new User();
            user.setFullName(fullName);
            user.setUserName(userName);
            user.setBirthdate(dateOB);
            user.setEmailAddress(email);
            user.setPassword(password);
            user.setQuestionNo(Integer.parseInt(questionNo));
            user.setAnswer(answer);
            // store User object in request
            // request.setAttribute("user", user);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (fullName == null || email == null || password == null || confirmpassword == null
                    || dateOB == null || questionNo == null || answer == null
                    || fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()
                    || dateOB.isEmpty() || questionNo.isEmpty() || answer.isEmpty()) {
                message = "Please fill out all the fields.";
                request.setAttribute("message", message);
            }
            if (password != confirmpassword) {
                message = "Password and Confirm passwrod don't match";
                request.setAttribute("message", message);
            }
            try {

                if (UserDB.searchEmail(email) != null) {
                    // display email already exists
                    message = "Email already exists!";
                    request.setAttribute("message", message);
                } else if (UserDB.searchUserName(userName) != null) {
                    // display username already exists 
                    message = "Username already exists!";
                    request.setAttribute("message", message);
                } else {
                    // store data in User object
                    message = "";
                    request.setAttribute("message", message);

                    try {
                        UserDB.insert(user);
                        success = true;
                        session.setAttribute("success", success);
                        //session.setAttribute("user", user);
                        // set another attribute
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // forward request to JSP
                    url = "/home.jsp";
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equals("update")) {
            String fullName = request.getParameter("fullName");
            String userName = request.getParameter("userName");
            String email = request.getParameter("emailAddress");
            String dateOB = request.getParameter("birthdate");
            String password = request.getParameter("password");
            String questionNo = request.getParameter("questionNo");
            String answer = request.getParameter("answer");

            User user = null;
            try {
                user = UserDB.searchEmail(email);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (user != null) {
                try {
                    user.setFullName(fullName);
                    user.setPassword(password);
                    user.setBirthdate(dateOB);
                    user.setAnswer(answer);
                    user.setQuestionNo(Integer.parseInt(questionNo));
                    UserDB.Update(user);

                    // set updated user as session attribute
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    // set message
                    //String message = "Updated";
                    //request.setAttribute("message", message);
                    // forward back to home after update
                    
                    url = "/home.jsp";
                } // else just return null?
                // update code
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } else if (action.equals("signout")) {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.removeAttribute("success");
            //session.invalidate();
            url = "/login.jsp";
        } else if (action.equals("forgotpassword")) {

            try {
                String email = request.getParameter("emailAddress");
                String questionNo = request.getParameter("questionNo");
                String answer = request.getParameter("answer");
                String message;

                User temp = UserDB.searchEmail(email);
                if (temp != null) {
                    char[] tempPassword = new char[8];
                    // display email already exists
                    if (temp.getQuestionNo() == Integer.parseInt(questionNo) && temp.getAnswer().equals(answer)) {
                        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                        SecureRandom rnd = new SecureRandom();
                        for (int i = 0; i < 8; i++) {
                            tempPassword[i] = characters.charAt(rnd.nextInt(characters.length()));
                        }
                        String testEmail = temp.getEmailAddress();
                        String test = temp.getFullName();
                        String newPassword = new String(tempPassword);
                        temp.setPassword(newPassword);
                        UserDB.Update(temp);

                        String to = email;
                        String from = "webappPHSK@gmail.com";
                        Boolean isBodyHtml = false;

                        String subject = "Password Reset from MiniTwitter";
                        String body = "Hello " + temp.getFullName() + "!\n"
                                + "This is your temporary password. Please login and change it to your password!\n"
                                + "Password: " + newPassword + "\nThank you.\n";

                        try {
                            MailUtilGmail.sendMail(to, from, subject, body, isBodyHtml);
                        } catch (MessagingException e) {
                            System.out.println(e);
                        }
                        message = "Temporary password sent to " + email;
                        request.setAttribute("message", message);
                        url = "/login.jsp";
                    } else {
                        message = "User info is not correct";
                        request.setAttribute("message", message);
                        url = "/forgotpassword.jsp";
                    }
                } else {
                    message = "User is not found";
                    request.setAttribute("message", message);
                    url = "/forgotpassword.jsp";
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("insertTweet")) {  // insert twitt 
            String twitt = request.getParameter("tweet");
            String emailAddress = request.getParameter("email");

            boolean success = false;

            Twitt newtwitt = new Twitt();
            newtwitt.setTwitt(twitt);
            newtwitt.setEmailAddress(emailAddress);

            HttpSession session = request.getSession();
            //session.setAttribute("twitt", newtwitt);

            try {
                TwitDB.insertTwit(newtwitt);
                success = true;
                session.setAttribute("success", success);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            User userDB = (User) session.getAttribute("user");
            postTweet(request, response, userDB.getEmailAddress(), userDB.getUserName());

            /* try {
                ArrayList<Twitt> tweets = TwitDB.searchTweet(emailAddress);        
                request.setAttribute("tweets", tweets);
                session.setAttribute("tweets", tweets);
                //url = "/home.jsp"; 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
             */
            url = "/home.jsp";

        } else if (action.equals("searchMention")) {
            /*   String username = request.getParameter("userName");
            String tempTweet = null; 
            try {
                tempTweet = UserMentionDB.searchMention(username);  // searh for @mention   
                if(tempTweet != null){
                    UserMentionDB.insertMention(username, tempTweet);   // insert into mention db 
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }   */
        } else if (action.equals("deleteTweet")) {
            String tweet = request.getParameter("tweet");
            int id = 0;
            try {
                id = TwitDB.searchPkey(tweet);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                TwitDB.deleteTwit(id);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                id = UserMentionDB.searchPkey(tweet);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                UserMentionDB.deleteTwit(id);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpSession session = request.getSession();
            User userDB = (User) session.getAttribute("user");
            postTweet(request, response, userDB.getEmailAddress(), userDB.getUserName());
            url = "/home.jsp";
        } else if (action.equals("home")) {

            url = "/home.jsp";
        } else if (action.equals("notification")) {
            url = "/notification.jsp";
        } else if (action.equals("profile")) {
            url = "/signup.jsp";
        } else if (action.equals("Uploadfile")) {
            
        } else if (action.equals("login")) {
            HttpSession session = request.getSession();
            boolean success = false;
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            //String emailAddress = request.getParameter("emailAddress");
            String message;

            try {

                User userDB = UserDB.searchUserName(userName);
                session.setAttribute("user", userDB);
                // username exists
                if (userDB != null) {

                    if (userDB.getPassword().equals(password)) {
                        message = "";
                        request.setAttribute("message", message);
                        success = true;
                        session.setAttribute("success", success);
                        int numOfTweets = TwitDB.numOfTweets(userDB.getEmailAddress());
                        session.setAttribute("numOfTweets", numOfTweets);
                        postTweet(request, response, userDB.getEmailAddress(), userDB.getUserName());

                        url = "/home.jsp";
                    } else {
                        message = "Password does not match";
                        request.setAttribute("message", message);

                        url = "/login.jsp";
                    }

                } else {
                    message = "Username does not exist";
                    request.setAttribute("message", message);
                    session.removeAttribute("user");
                    url = "/login.jsp";
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    private String postTweet(HttpServletRequest request,
            HttpServletResponse response, String emailAddress, String userName) throws IOException {
        HttpSession session = request.getSession();
        // String emailAddress = request.getParameter("email");
        try {
            ArrayList<Twitt> tweets = TwitDB.searchTweet(emailAddress, userName);

            request.setAttribute("tweets", tweets);
            session.setAttribute("tweets", tweets);
            //url = "/home.jsp"; 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/home.jsp";
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
