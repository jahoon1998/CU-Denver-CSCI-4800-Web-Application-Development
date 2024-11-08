/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Follow;
import business.Hastag;
import business.User;
import business.notification;
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
import static util.PasswordUtil.getSalt;
import static util.PasswordUtil.hashAndSaltPassword;

import business.User;
import business.UserMention;
import dataaccess.HastagDB;
import dataaccess.FollowDB;
import util.MailUtilGmail;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataaccess.UserDB;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import util.CookiesUtil;

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
        if (action == null) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(-1);
            boolean success = false;
            String message;
            User loggedin;
            loggedin = checkUser(request, response);
            // if cookie exists
            if (loggedin != null) {
                try {
                    //int id = UserDB.searchserID(userName);
                    //UserDB.UpdateTime(id);
                    message = "";
                    request.setAttribute("message", message);
                    success = true;
                    session.setAttribute("success", success);
                    ArrayList<String> userS = UserDB.searchUsers(loggedin.getEmailAddress());
                    session.setAttribute("userFollow", userS);
                    int numOfTweets = TwitDB.numOfTweets(loggedin.getEmailAddress());
                    session.setAttribute("numOfTweets", numOfTweets);
                    ArrayList<Follow> follows = FollowDB.searchFollow(loggedin.getUserName());
                    session.setAttribute("Followed", follows);

                    postTweet(request, response, loggedin.getEmailAddress(), loggedin.getUserName());
                    postTweet(request, response, loggedin.getEmailAddress(), loggedin.getUserName());
                    ArrayList<Hastag> trandingHash = HastagDB.searchTrending();
                    session.setAttribute("trandingHash", trandingHash);

                    //notification part
                    int userID = UserDB.searchserID(loggedin.getUserName());
                    ArrayList<Twitt> tweetlist = TwitDB.searchNotification(userID, loggedin.getUserName());

                    ArrayList<Follow> followlist = FollowDB.searchFollowedUser(loggedin.getUserName(), userID);
                    session.setAttribute("notification", tweetlist);
                    session.setAttribute("followlist", followlist);
                    int numfoNoti = tweetlist.size() + followlist.size();
                    session.setAttribute("numofNoti", numfoNoti);
                    int numberOfFollowing = 0;
                    int numberOfFollower = 0;
                    try {
                        numberOfFollowing = FollowDB.numberOfFollowing(loggedin.getUserName());
                        numberOfFollower = FollowDB.numberOfFollower(loggedin.getUserName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    session.setAttribute("numOfFollowing", numberOfFollowing);
                    session.setAttribute("numOfFollwer", numberOfFollower);
                    session.setAttribute("user", loggedin);

                    url = "/home.jsp";
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (action.equals("signup")) {
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
            String salt = getSalt();
            String hashpassword = null;
            try {
                hashpassword = hashAndSaltPassword(password, salt);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            User user = new User();
            user.setFullName(fullName);
            user.setUserName(userName);
            user.setBirthdate(dateOB);
            user.setEmailAddress(email);
            user.setPassword(hashpassword);
            user.setQuestionNo(Integer.parseInt(questionNo));
            user.setAnswer(answer);
            user.setSalt(salt);
            // store User object in request
            // request.setAttribute("user", user);

            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(-1);

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
            session.setAttribute("user", user);

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

            String hashpassword = null;
            try {
                hashpassword = hashAndSaltPassword(password, user.getSalt());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (user != null) {
                try {
                    user.setFullName(fullName);
                    user.setPassword(hashpassword);
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
            User user = (User) session.getAttribute("user");
            int id = 0;
            try {
                id = UserDB.searchserID(user.getUserName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                UserDB.UpdateTime(id);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.removeAttribute("user");
            session.removeAttribute("success");
            session.invalidate();
            //session.invalidate();
              deleteCookie(request, response);
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
                        String hashpassword = null;
                        try {
                            hashpassword = hashAndSaltPassword(newPassword, temp.getSalt());
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        temp.setPassword(hashpassword);
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
                int numOfTweets = TwitDB.numOfTweets(emailAddress);
                session.setAttribute("numOfTweets", numOfTweets);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            User userDB = (User) session.getAttribute("user");
            postTweet(request, response, userDB.getEmailAddress(), userDB.getUserName());

            String[] hasTag = twitt.split(" ");

            for (String a : hasTag) {
                if (a.contains("#") && a.length() > 1) {
                    try {
                        int count = HastagDB.searchHastagcount(a);
                        if (count > 0) {
                            count++;
                            HastagDB.updateHastagCount(count, a);
                        } else {
                            HastagDB.insertHastag(a);
                        }
                        int hID = HastagDB.searchHastag(a);
                        int tID = TwitDB.searchPkey(twitt);
                        HastagDB.insertTweetHastag(tID, hID);

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            ArrayList<Hastag> trandingHash = new ArrayList<Hastag>();
            try {
                trandingHash = HastagDB.searchTrending();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("trandingHash", trandingHash);

            //mentioning part
            String[] mention = twitt.split(" ");
            for (String a : mention) {
                if (a.contains("@") && a.length() > 1) {
                    try {
                        String[] b = a.split("@");
                        UserMentionDB.insertMention(b[1], twitt, userDB.getUserName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

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
            String ID = request.getParameter("tweet");
            int id = Integer.parseInt(ID);
            HttpSession session = request.getSession();
            User userDB = (User) session.getAttribute("user");
             String tweet = null;
            try {
                tweet = TwitDB.searchbyIDforDelete(id).getTwitt();
                int hashtagID = HastagDB.searchHashtagByTID(id);
                HastagDB.deleteTwittHashtag(id);
                HastagDB.deletehashtag(hashtagID);
                
                TwitDB.deleteTwit(id);

                int numOfTweets = TwitDB.numOfTweets(userDB.getEmailAddress());
                session.setAttribute("numOfTweets", numOfTweets);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try { //deleting mention
                
                int Mid = UserMentionDB.searchPkey(tweet);
                UserMentionDB.deleteTwit(Mid);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            postTweet(request, response, userDB.getEmailAddress(), userDB.getUserName());

            url = "/home.jsp";
        } else if (action.equals("home")) {

            url = "/home.jsp";
        } else if (action.equals("notification")) {

            String userName = request.getParameter("username");

            try {
                int userID = UserDB.searchserID(userName);
                ArrayList<Twitt> tweetlist = TwitDB.searchNotification(userID, userName);
                HttpSession session = request.getSession();
                ArrayList<Follow> followlist = FollowDB.searchFollowedUser(userName, userID);
                session.setAttribute("notification", tweetlist);
                session.setAttribute("followlist", followlist);
                int numfoNoti = tweetlist.size() + followlist.size();
                session.setAttribute("numofNoti", numfoNoti);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            url = "/notification.jsp";
        } else if (action.equals("profile")) {
            url = "/signup.jsp";
        } else if (action.equals("Uploadfile")) {

        } else if (action.equals("follow")) {
            HttpSession session = request.getSession();
            String userName = request.getParameter("followname");
            User FollowedUser = (User) session.getAttribute("user");
            try {
                FollowDB.insertFollow(userName, FollowedUser.getUserName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Follow> follows = null;
            try {
                follows = FollowDB.searchFollow(FollowedUser.getUserName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int numberOfFollowing = 0;
            int numberOfFollower = 0;
            try {
                numberOfFollowing = FollowDB.numberOfFollowing(FollowedUser.getUserName());
                numberOfFollower = FollowDB.numberOfFollower(FollowedUser.getUserName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("numOfFollowing", numberOfFollowing);
            session.setAttribute("numOfFollwer", numberOfFollower);
            session.setAttribute("Followed", follows);
            url = "/home.jsp";

        } else if (action.equals("unfollow")) {
            HttpSession session = request.getSession();
            String userName = request.getParameter("unfollowname");
            User FollowedUser = (User) session.getAttribute("user");
            try {
                FollowDB.deleteFollow(userName, FollowedUser.getUserName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Follow> follows = null;
            try {
                follows = FollowDB.searchFollow(FollowedUser.getUserName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int numberOfFollowing = 0;
            int numberOfFollower = 0;
            try {
                numberOfFollowing = FollowDB.numberOfFollowing(FollowedUser.getUserName());
                numberOfFollower = FollowDB.numberOfFollower(FollowedUser.getUserName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("numOfFollowing", numberOfFollowing);
            session.setAttribute("numOfFollwer", numberOfFollower);
            session.setAttribute("Followed", follows);
            url = "/home.jsp";

        } else if (action.equals("hashtag1")) {
            HttpSession session = request.getSession();
            String hastag = request.getParameter("hashtag");
            String newHastag = hastag.replace(hastag, "#" + hastag);

            try {

                ArrayList<Twitt> tweetlist = TwitDB.searchTweetByHastag(newHastag);
                session.setAttribute("tweetlist", tweetlist);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //id = TwitDB.searchPkey(tweet);
            url = "/hashtag.jsp";

        } else if (action.equals("hashtag")) {
            HttpSession session = request.getSession();
            int hashtagID = Integer.parseInt(request.getParameter("hashtagid"));

            try {
                String hashtag = HastagDB.searchHashtagByHID(hashtagID);
                ArrayList<Twitt> tweetlist = TwitDB.searchTweetByHastag(hashtag);
                session.setAttribute("tweetlist", tweetlist);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //id = TwitDB.searchPkey(tweet);
            url = "/hashtag.jsp";
        } else if (action.equals("login")) {
            HttpSession session = request.getSession();
            boolean success = false;
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            //String emailAddress = request.getParameter("emailAddress");
            String[] rememberMe = request.getParameterValues("rememberMe");
            String message;

            try {

                User userDB = UserDB.searchUserName(userName);
                session.setAttribute("user", userDB);
                // username exists
                if (userDB != null) {

                    String hashpassword = null;
                    try {
                        hashpassword = hashAndSaltPassword(password, userDB.getSalt());
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (userDB.getPassword().equals(hashpassword)) {
                        //int id = UserDB.searchserID(userName);
                        //UserDB.UpdateTime(id);
                        message = "";
                        request.setAttribute("message", message);
                        success = true;
                        session.setAttribute("success", success);
                        ArrayList<String> userS = UserDB.searchUsers(userDB.getEmailAddress());
                        session.setAttribute("userFollow", userS);
                        int numOfTweets = TwitDB.numOfTweets(userDB.getEmailAddress());
                        session.setAttribute("numOfTweets", numOfTweets);
                        ArrayList<Follow> follows = FollowDB.searchFollow(userName);
                        session.setAttribute("Followed", follows);

                        postTweet(request, response, userDB.getEmailAddress(), userDB.getUserName());
                        postTweet(request, response, userDB.getEmailAddress(), userDB.getUserName());
                        ArrayList<Hastag> trandingHash = HastagDB.searchTrending();
                        session.setAttribute("trandingHash", trandingHash);

                        //notification part
                        int userID = UserDB.searchserID(userName);
                        ArrayList<Twitt> tweetlist = TwitDB.searchNotification(userID, userName);
                        ArrayList<notification> noti = TwitDB.searchNotifi(userID, userName);
                        session.setAttribute("noti", noti);
                        ArrayList<Follow> followlist = FollowDB.searchFollowedUser(userName, userID);
                        session.setAttribute("notification", tweetlist);
                        session.setAttribute("followlist", followlist);
                        int numfoNoti = noti.size();
                        
                        session.setAttribute("numofNoti", numfoNoti);
                        int numberOfFollowing = 0;
                        int numberOfFollower = 0;                    
                        try {
                            numberOfFollowing = FollowDB.numberOfFollowing(userName);
                            numberOfFollower = FollowDB.numberOfFollower(userName);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        session.setAttribute("numOfFollowing", numberOfFollowing);
                        session.setAttribute("numOfFollwer", numberOfFollower);

                        if (rememberMe != null) {
                            setCookie(request, response, userDB);
                        }
                        session.setAttribute("user", userDB);
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

            ArrayList<Twitt> tweets = TwitDB.searchTweetB(emailAddress, userName);

            request.setAttribute("tweets", tweets);
            session.setAttribute("tweets", tweets);

            // ArrayList<Twitt> tweetsdelete = TwitDB.searchTweet(emailAddress, userName);
            //request.setAttribute("tweetsdelete", tweetsdelete);
            //session.setAttribute("tweetsdelete", tweetsdelete);
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
    private void setCookie(HttpServletRequest request, HttpServletResponse response, User user) {
        Cookie loginEmailCookie = new Cookie("UserEmail", user.getEmailAddress());
        loginEmailCookie.setMaxAge(60 * 60 * 24);       // Set cookie max age to 1 day
        loginEmailCookie.setPath("/");
        response.addCookie(loginEmailCookie);
    }

    private void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("UserEmail")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    private User checkUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) request.getAttribute("user");
        //boolean check = false;
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookiesUtil.getCookieValue(cookies, "UserEmail");
            if (emailAddress == null || emailAddress.equals("")) {
                return null;
            } else {
                try {
                    user = UserDB.searchEmail(emailAddress.toLowerCase());
                    if (user != null) {
                        session.setAttribute("user", user);
                        return user;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

}
