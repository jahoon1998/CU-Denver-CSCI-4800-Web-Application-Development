/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

/**
 *
 * @author sagarluitel
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import business.Follow;
import business.Twitt;
import business.Hastag;
//import business.User;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author sagarluitel
 */
public class FollowDB {

    public static long insertFollow(String userName, String followedUserName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            Statement statement = connection.createStatement();
            // modify insert code to insert new follow
            
    
            String preparedSQL = "Insert into follow" + "(userName, followedUserName)" + "Values"
                    + "('" + userName + "','" + followedUserName + "')";
            //follwedUser is the foreign key
            int result = statement.executeUpdate(preparedSQL);

            return result;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    
    public static long deleteFollow(String userName, String followedUserName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            Statement statement = connection.createStatement();

            String preparedSQL = "DELETE FROM follow Where userName = '" + userName + "'AND followedUserName = '" + followedUserName + "'";

            int result = statement.executeUpdate(preparedSQL);

            return result;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    
        public static ArrayList<Follow> searchFollow(String userName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM follow Where followedUserName = '" + userName + "'";


            Statement statement = connection.createStatement();
            ResultSet followResult = statement.executeQuery(preparedSQL);
            ArrayList<Follow> Follows = new ArrayList<Follow>();
            if (followResult == null) {
                return null;
            } else {

                while (followResult.next()) {
                    Follow tempFollow = new Follow();
                    tempFollow.setUserName(followResult.getString("userName"));
                    tempFollow.setFollowedUserName(followResult.getString("followedUserName"));
                    tempFollow.setFollowDate(followResult.getString("followDate"));
                   
                    Follows.add(tempFollow);
                }
            }
            return Follows;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
     
    public static ArrayList<Follow> searchFollowedUser(String userName, int id) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            
            String preparedSQL = "SELECT * FROM follow where "
                    + "followDate >= (select lastlogin from twitterdb.user where userID = '"+ id +"')"
                    + " and userName = '"+userName+"'";


            Statement statement = connection.createStatement();
            ResultSet followResult = statement.executeQuery(preparedSQL);
            ArrayList<Follow> Follows = new ArrayList<Follow>();
            if (followResult == null) {
                return null;
            } else {

                while (followResult.next()) {
                    Follow tempFollow = new Follow();
                    tempFollow.setUserName(followResult.getString("userName"));
                    tempFollow.setFollowedUserName(followResult.getString("followedUserName"));
                    tempFollow.setFollowDate(followResult.getString("followDate"));
                   
                    Follows.add(tempFollow);
                }
            }
            return Follows;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    
    }
    
public static int numberOfFollowing(String userName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM follow Where followedUserName = '" + userName + "'";

            Statement statement = connection.createStatement();
            ResultSet followResult = statement.executeQuery(preparedSQL);
            int numberOfFollowing = 0;
            if (followResult == null) {
                return 0;
            } else {

                while (followResult.next()) {
                    numberOfFollowing++;
                }
            }
            return numberOfFollowing;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }

        public static int numberOfFollower(String userName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM follow Where userName = '" + userName + "'";

            Statement statement = connection.createStatement();
            ResultSet followResult = statement.executeQuery(preparedSQL);
            int numberOfFollower = 0;
            if (followResult == null) {
                return 0;
            } else {

                while (followResult.next()) {
                    numberOfFollower++;
                }
            }
            return numberOfFollower;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
}
