/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Twitt;
import business.Hastag;
//import business.User;
import java.io.*;
import java.sql.Array;
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
public class HastagDB {

    public static long insertHastag(String hastag) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            Statement statement = connection.createStatement();

            String preparedSQL = "Insert into hashtag"
                    + "(hashtag, count)"
                    + "Values"
                    + "('" + hastag + "','" + 1 + "')";

            int result = statement.executeUpdate(preparedSQL);

            return result;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    
    public static long insertTweetHastag(int twittID, int hastagID) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            Statement statement = connection.createStatement();

            String preparedSQL = "Insert into tweetHashtag"
                    + "(twittID, hashtagID)"
                    + "Values"
                    + "('" + twittID + "','" + hastagID + "')";

            int result = statement.executeUpdate(preparedSQL);

            return result;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
   
    public static int updateHastagCount(int count, String hashTag) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            Statement statement = connection.createStatement();
               
            String preparedSQL = "UPDATE hashtag SET "                   
                    + "count = '"+ count +"' WHERE hashtag = '"+ hashTag + "'";

            int result = statement.executeUpdate(preparedSQL);

            return result;
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    public static int searchHastagcount(String hashTag) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT count FROM hashtag Where hashtag = '" + hashTag + "'";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(preparedSQL);
            int val = 0;
            for (; result.next();) {
                val = ((Number) result.getObject(1)).intValue();
               
            }
            
            return val;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    public static ArrayList<Hastag> searchTrending() throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT hashtagID, hashtag FROM hashtag order by count DESC limit 10";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(preparedSQL);
            ArrayList<Hastag> treanding = new ArrayList<Hastag>();
            
            while(result.next()) {
                Hastag tempTrending = new Hastag();
                tempTrending.setHastagID(result.getString("hashtagID"));
                tempTrending.setHastag(result.getString("hashtag"));
                treanding.add(tempTrending);
            }
            
            return treanding;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
    public static long deleteTwittHashtag(int id) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "DELETE FROM tweetHashtag Where twittID = '" + id + "'";
            
            Statement statement = connection.createStatement();

            int result = statement.executeUpdate(preparedSQL);

            return result;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    public static long deletehashtag(int id) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "DELETE FROM hashtag Where hashtagID = '" + id + "'";
            
            Statement statement = connection.createStatement();

            int result = statement.executeUpdate(preparedSQL);

            return result;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    public static int searchHastag(String hashTag) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT hashtagID FROM hashtag Where hashtag = '" + hashTag + "'";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(preparedSQL);
            int val = 0;
            for (; result.next();) {
                val = ((Number) result.getObject(1)).intValue();
            }
            return val;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    public static int searchHashtagByTID(int twittID) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT hashtagID FROM tweetHashtag Where twittID = '" + twittID + "'";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(preparedSQL);
            int val = 0;
            for (; result.next();) {
                val = ((Number) result.getObject(1)).intValue();
            }
            return val;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return 0;
        }
    }
    public static String searchHashtagByHID(int hID) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT hashtag FROM hashtag Where hashtagID = '" + hID + "'";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(preparedSQL);
            String val = null;
            for (; result.next();) {
                val = result.getString("hashtag");
            }
            return val;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
}
