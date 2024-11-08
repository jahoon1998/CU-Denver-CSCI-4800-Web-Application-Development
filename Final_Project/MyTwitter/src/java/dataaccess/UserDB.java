/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Twitt;
import business.User;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.util.List;

public class UserDB {

    public static long /*boolean */ insert(User user) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();

            String preparedSQL = "Insert into user"
                    + "(fullname, username, emailAddress, password, birthdate, questionNo, answer, salt) "
                    + "Values"
                    + " ('" + user.getFullName() + "','" + user.getUserName() + "','" + user.getEmailAddress() + "','" + user.getPassword() + "','" + user.getBirthdate() + "','" + user.getQuestionNo() + "','" + user.getAnswer() + "','" + user.getSalt() + "')";

            int result = statement.executeUpdate(preparedSQL);

            return result;

            /*statement.executeUpdate(preparedSQL);
            connection.close();
            return true;*/
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }

    public static User searchEmail(String emailAddress) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String preparedSQL = "SELECT * FROM user Where emailAddress = '" + emailAddress + "'";
            Statement statement = connection.createStatement();
            ResultSet userEmailResult = statement.executeQuery(preparedSQL);
            //if user is not found
            if (userEmailResult.next() == false) {
                return null;
            } else {
                User user = new User(userEmailResult.getString(2), userEmailResult.getString(3), userEmailResult.getString(4),
                        userEmailResult.getString(5), userEmailResult.getString(6), userEmailResult.getString(7), userEmailResult.getString(8), userEmailResult.getString("salt"));
                connection.close();
                return user;
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<String> searchUsers(String emailAddress) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String preparedSQL = "SELECT username FROM user Where NOT emailAddress = '" + emailAddress + "'";
            Statement statement = connection.createStatement();
            ResultSet userResults = statement.executeQuery(preparedSQL);
            //if user is not found
            ArrayList<String> users = new ArrayList();
            if (userResults == null) {
                return null;
            } 
            int i = 0;
            while(userResults.next()){
                users.add(userResults.getString("username"));
                i++;               
            }
            return users;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        return null;
    }

    public static User searchUserName(String userName) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String preparedSQL = "SELECT * FROM user Where username = '" + userName + "'";
            Statement statement = connection.createStatement();
            ResultSet userEmailResult = statement.executeQuery(preparedSQL);
            //if user is not found
            if (userEmailResult.next() == false) {
                return null;
            } else {
                User user = new User(userEmailResult.getString(2), userEmailResult.getString(3), userEmailResult.getString(4),
                        userEmailResult.getString(5), userEmailResult.getString(6), userEmailResult.getString(7), userEmailResult.getString(8), userEmailResult.getString("salt"));
                connection.close();
                return user;
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        return null;
    }

    public static int searchserID(String userName) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String preparedSQL = "SELECT userID FROM user Where username = '" + userName + "'";
            Statement statement = connection.createStatement();
            ResultSet userEmailResult = statement.executeQuery(preparedSQL);
            int a = 0;
            if (userEmailResult.next() == false) {
                return 0;
            } else {
                String b = userEmailResult.getString(1);
                a = Integer.parseInt(b);
                return a;
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        return 0;
    }
    public static long /*boolean */ Update(User user) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();
            String query = "UPDATE user SET "
                    + "fullName = ?," + "birthdate = ?," + "password = ?," + "questionNo = ?," + "answer = ?" + "WHERE emailAddress = ?";
            try {
                // fix these later
                PreparedStatement ps = null;
                ps = connection.prepareStatement(query);
                ps.setString(1, user.getFullName());
                ps.setString(2, user.getBirthdate());
                ps.setString(3, user.getPassword());
                ps.setString(4, Integer.toString(user.getQuestionNo()));
                ps.setString(5, user.getAnswer());
                ps.setString(6, user.getEmailAddress());
                //ps.setString(7, user.getSalt());

                return ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
                return 0;
            }

            /*statement.executeUpdate(preparedSQL);
            connection.close();
            return true;*/
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }

    }
    public static long UpdateTime(int useriD) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = connection.createStatement();
            
            String  preparedSQL = "UPDATE user SET "
                    + "lastlogin = CURRENT_TIMESTAMP WHERE userID = '" + useriD + "'";
            
            int result = statement.executeUpdate(preparedSQL);

            return result;
         
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
        

    }
}
