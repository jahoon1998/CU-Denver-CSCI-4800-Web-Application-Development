/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import business.Twitt;
//import business.User;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.util.List;

public class TwitDB {
    public static long /*boolean */ insertTwit(Twitt twitt) throws IOException, ClassNotFoundException 
    {
      try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
                        Statement statement = connection.createStatement();

            String preparedSQL = "Insert into tweets"
                                   + "(tweet, emailAddress)"
                                   + "Values"
                                   + "('"+twitt.getTwitt()+"','"+twitt.getEmailAddress()+"')";

            
            int result = statement.executeUpdate(preparedSQL);
            
            return result;
          
        }
        catch(SQLException e) {
        for (Throwable t : e)
            t.printStackTrace();
        return 0;
        }
    }
   public static int numOfTweets(String email) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);

            Statement statement = connection.createStatement();

            String preparedSQL = "SELECT COUNT(*) AS tweetCount FROM tweets WHERE emailAddress = '" + email + "';";

            //add values to the above SQL statement and execute it.
            ResultSet rs = statement.executeQuery(preparedSQL);

            int result = 0;
            if (rs.next()) {
                result = rs.getInt(1);
            }
            
            return result;
            
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        return 0;
    }
    public static int searchPkey(String twitt) throws IOException, ClassNotFoundException 
    {
      try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT twittID FROM tweets Where tweet = '"+twitt+"'";
            
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(preparedSQL);
            int val = 0;
            for (; result.next();) {
               val =  ((Number) result.getObject(1)).intValue();
            }
            return val;
          
        }
        catch(SQLException e) {
        for (Throwable t : e)
            t.printStackTrace();
        return 0;
        }
    }
    public static long deleteTwit(int id) throws IOException, ClassNotFoundException 
    {
      try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "DELETE FROM tweets Where twittID = '"+id+"'";
            
            Statement statement = connection.createStatement();
           
            int result = statement.executeUpdate(preparedSQL);
            
            return result;
          
        }
        catch(SQLException e) {
        for (Throwable t : e)
            t.printStackTrace();
        return 0;
        }
    }
    public static ArrayList<Twitt> searchTweet(String emailAddress, String  userName) throws IOException, ClassNotFoundException 
    {
      try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM tweets Where emailAddress = '"+emailAddress+"' order by postDate DESC";
            
            Statement statement = connection.createStatement();
            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            ArrayList<Twitt> tweets = new ArrayList<Twitt>();
            if(tweetResult == null){
                return null;
            }else{
              
                while(tweetResult.next()) {
               Twitt tempTwitt = new Twitt();
                tempTwitt.setEmailAddress(tweetResult.getString("emailAddress"));
                tempTwitt.setTwitt(tweetResult.getString("tweet"));
                tempTwitt.setDate(tweetResult.getString("postDate"));
                tweets.add(tempTwitt);
            }
            }
                
            String preparedSQL1 = "SELECT * FROM tweets Where tweet LIKE '%@"+userName+"%' order by postDate DESC";
            
           // Statement statement = connection.createStatement();
            ResultSet tweetResult1 = statement.executeQuery(preparedSQL1);
            
            if(tweetResult1 == null){
                return null;
            }else{
               while(tweetResult1.next()) {
               Twitt tempTwitt = new Twitt();
                tempTwitt.setEmailAddress(tweetResult1.getString("emailAddress"));
                tempTwitt.setTwitt(tweetResult1.getString("tweet"));
                tempTwitt.setDate(tweetResult1.getString("postDate"));
                tweets.add(tempTwitt);
            }
            }   
            return tweets;
            
        }
        catch(SQLException e) {
        for (Throwable t : e)
            t.printStackTrace();
        return null;
        }
    }
}
