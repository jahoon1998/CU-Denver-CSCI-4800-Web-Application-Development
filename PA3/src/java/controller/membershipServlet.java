/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.User;
import dataaccess.UserDB;
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

import business.User;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataaccess.UserDB;
 
//@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
public class membershipServlet extends HttpServlet {

    
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
        if(action.equals("signup"))
        {

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
            
           
            if(fullName == null || email == null || password == null || confirmpassword == null|| 
                dateOB == null || questionNo == null || answer == null ||
                fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || 
                dateOB.isEmpty() || questionNo.isEmpty() || answer.isEmpty())
        {
            message = "Please fill out all the fields.";
            request.setAttribute("message", message);
        }
            if(password != confirmpassword)
            {
            message = "Password and Confirm passwrod don't match";
            request.setAttribute("message", message);
            }
            try {
            
                if(UserDB.searchEmail(email) != null)
                {
                    // display email already exists
                    message = "Email already exists!";
                    request.setAttribute("message", message);
                }
                else if(UserDB.searchUserName(userName) != null)
                {
                    // display username already exists 
                    message = "Username already exists!";
                    request.setAttribute("message", message);
                }
                else
                {
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
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
        else if (action.equals("signout"))
        {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.removeAttribute("success");
            //session.invalidate();
            url = "/login.jsp";
        }
         else if(action.equals("login"))
        {
            HttpSession session = request.getSession();
            boolean success = false;
            String userName = request.getParameter ("userName");
            String password = request.getParameter("password");
            String message;
            
            
            try {
                
                User userDB = UserDB.searchUserName(userName);
                session.setAttribute("user", userDB);
                // username exists
                if(userDB != null)
                {
                    
                    if(userDB.getPassword().equals(password)){
                        message = "";
                        request.setAttribute("message", message);
                        success = true;
                        session.setAttribute("success", success);
                        url = "/home.jsp";
                    }
                    else{   
                        message = "Password does not match";
                        request.setAttribute("message", message);
                        //session.setAttribute("user", userDB);
                         url = "/login.jsp";
                    }
                    
                }
                else{
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
