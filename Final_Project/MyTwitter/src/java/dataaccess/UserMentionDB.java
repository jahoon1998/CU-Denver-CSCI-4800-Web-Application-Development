/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import business.Twitt;
import business.User;
import business.UserMention;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.util.List;

public class UserMentionDB {
    
    public static ArrayList<Twitt>  searchMention(String userName) throws IOException, ClassNotFoundException 
    {
      try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT tweet FROM tweets Where tweet LIKE '%@"+userName+"%'";
            Statement statement = connection.createStatement();
            ResultSet mentionResults = statement.executeQuery(preparedSQL);
            //if user is not found
            
           if(mentionResults == null){
                return null;
            }else{
              ArrayList<Twitt> tweets = new ArrayList<Twitt>();
                while(mentionResults.next()) {
               Twitt tempTwitt = new Twitt();
                tempTwitt.setEmailAddress(mentionResults.getString("emailAddress"));
                tempTwitt.setTwitt(mentionResults.getString("tweet"));
                tweets.add(tempTwitt);
            }
                
                
                return tweets;
            }
            
        }
        catch(SQLException e) {
        for (Throwable t : e)
            t.printStackTrace();
        return null;
        }
        
    }
    
    public static long insertMention(String userName, String tweet, String mentionedUser) throws IOException, ClassNotFoundException 
    {
      try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
             Statement statement = connection.createStatement();
            String preparedSQL = "Insert into userMentions"
                                   + "(mentionUser, tweet, userName)"
                                   + "Values"
                                   + "('"+mentionedUser+"','"+tweet+"','"+userName+"')";

            
            int result = statement.executeUpdate(preparedSQL);
            
            return result;
        }
        catch(SQLException e) {
        for (Throwable t : e)
            t.printStackTrace();
        return 0;
        }
        //return 0;
    }
    public static int searchPkey(String twitt) throws IOException, ClassNotFoundException 
    {
      try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT userMentionID FROM userMentions Where tweet = '"+twitt+"'";
            
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
            String preparedSQL = "DELETE FROM userMentions Where userMentionID = '"+id+"'";
            
            Statement statement = connection.createStatement();
           /* ResultSet result = statement.executeQuery(preparedSQL);
            int val = 0;
            for (; result.next();) {
               val =  ((Number) result.getObject(1)).intValue();
            }
            return val;*/
            int result = statement.executeUpdate(preparedSQL);
            
            return result;
          
        }
        catch(SQLException e) {
        for (Throwable t : e)
            t.printStackTrace();
        return 0;
        }
    }
}
