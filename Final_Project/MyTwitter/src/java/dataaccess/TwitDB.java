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
import javax.servlet.http.HttpSession;
import business.notification;
//import java.util.List;

public class TwitDB {

    public static long /*boolean */ insertTwit(Twitt twitt) throws IOException, ClassNotFoundException {
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
                    + "('" + twitt.getTwitt() + "','" + twitt.getEmailAddress() + "')";

            int result = statement.executeUpdate(preparedSQL);

            return result;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
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

    public static int searchPkey(String twitt) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT twittID FROM tweets Where tweet = '" + twitt + "'";

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

    public static long deleteTwit(int id) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "DELETE FROM tweets Where twittID = '" + id + "'";

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
    public static Twitt searchbyID(int id) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM tweets Where twittID = '" + id + "'";

            Statement statement = connection.createStatement();

            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            Twitt tweets = new Twitt();
            if (tweetResult == null) {
                return null;
            } else {

                while (tweetResult.next()) {                   
                    tweets.setEmailAddress(tweetResult.getString("emailAddress"));
                    tweets.setTwitt(colorMention(colorHashtag(tweetResult.getString("tweet"))));
                    
                    tweets.setDate(tweetResult.getString("postDate"));                   
                }
            }


            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
public static Twitt searchbyIDforDelete(int id) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM tweets Where twittID = '" + id + "'";

            Statement statement = connection.createStatement();

            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            Twitt tweets = new Twitt();
            if (tweetResult == null) {
                return null;
            } else {

                while (tweetResult.next()) {                   
                    tweets.setEmailAddress(tweetResult.getString("emailAddress"));
                    tweets.setTwitt(tweetResult.getString("tweet"));
                    
                    tweets.setDate(tweetResult.getString("postDate"));                   
                }
            }


            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
    public static ArrayList<Twitt> searchTweet(String emailAddress, String userName) throws IOException, ClassNotFoundException {
        try {
                Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM tweets Where emailAddress = '" + emailAddress + "' order by postDate DESC";

            Statement statement = connection.createStatement();
            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            ArrayList<Twitt> tweets = new ArrayList<Twitt>();
            if (tweetResult == null) {
                return null;
            } else {

                while (tweetResult.next()) {
                    Twitt tempTwitt = new Twitt();
                    tempTwitt.setID(tweetResult.getString("twittID"));
                    tempTwitt.setEmailAddress(tweetResult.getString("emailAddress"));
                    tempTwitt.setTwitt(colorMention(colorHashtag(tweetResult.getString("tweet"))));
                    
                    tempTwitt.setDate(tweetResult.getString("postDate"));
                    tweets.add(tempTwitt);
                }
            }

            String preparedSQL1 = "SELECT * FROM tweets Where tweet LIKE '%@" + userName + "%' order by postDate DESC";

            // Statement statement = connection.createStatement();
            ResultSet tweetResult1 = statement.executeQuery(preparedSQL1);

            if (tweetResult1 == null) {
                return null;
            } else {
                while (tweetResult1.next()) {
                    Twitt tempTwitt = new Twitt();
                    tempTwitt.setID(tweetResult1.getString("twittID"));
                    tempTwitt.setEmailAddress(tweetResult1.getString("emailAddress"));
                    tempTwitt.setTwitt(colorMention(colorHashtag(tweetResult1.getString("tweet"))));
                    tempTwitt.setDate(tweetResult1.getString("postDate"));
                    tweets.add(tempTwitt);
                }
            }
            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
    public static ArrayList<Twitt> searchTweetB(String emailAddress, String userName) throws IOException, ClassNotFoundException {
        try {
                Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM (SELECT * FROM twitterdb.tweets Where emailAddress = '"+emailAddress+"') as A \n" +
                                "UNION\n" +
                                "(SELECT * FROM twitterdb.tweets Where tweet LIKE \"%@"+userName+"%\") \n" +
                                "order by postDate desc";
                                

            Statement statement = connection.createStatement();
            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            ArrayList<Twitt> tweets = new ArrayList<Twitt>();
            if (tweetResult == null) {
                return null;
            } else {

                while (tweetResult.next()) {
                    Twitt tempTwitt = new Twitt();
                    tempTwitt.setID(tweetResult.getString("twittID"));
                    tempTwitt.setEmailAddress(tweetResult.getString("emailAddress"));
                    tempTwitt.setTwitt(colorMention(colorHashtag(tweetResult.getString("tweet"))));
                    
                    tempTwitt.setDate(tweetResult.getString("postDate"));
                    tweets.add(tempTwitt);
                }
            }

            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
    public static ArrayList<Twitt> searchNotification(int Uid, String userName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            
            String preparedSQL = "SELECT * FROM tweets where "
                    + "postDate >= (select lastlogin from user where userID = "+ Uid + ")"
                    + " and tweet LIKE '%@" +userName+ "%'";

            Statement statement = connection.createStatement();
            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            ArrayList<Twitt> tweets = new ArrayList<Twitt>();
            if (tweetResult == null) {
                return null;
            } else {

                while (tweetResult.next()) {
                    Twitt tempTwitt = new Twitt();
                    tempTwitt.setID(tweetResult.getString("twittID"));
                    tempTwitt.setEmailAddress(tweetResult.getString("emailAddress"));
                    tempTwitt.setTwitt(colorMention(colorHashtag(tweetResult.getString("tweet"))));                  
                    tempTwitt.setDate(tweetResult.getString("postDate"));
                    tweets.add(tempTwitt);
                }
            }
            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
        public static ArrayList<notification> searchNotifi(int Uid, String userName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            
            String preparedSQL = "select * FROM \n" +
                        "(SELECT NULL As mentionUser, userName, followedUserName, followDate, Null as tweet FROM twitterdb.follow \n" +
                        "where followDate >= (select lastlogin from twitterdb.user where userID = "+ Uid +") and userName = '" +userName+ "') A\n" +
                        "union all \n" +
                        "(SELECT mentionUser, userName, Null as followedUserName, postDate, tweet FROM twitterdb.userMentions where \n" +
                        "postDate >= (select lastlogin from twitterdb.user where userID = "+ Uid +")\n" +
                        "and userName = '" +userName+ "')  \n" +
                        "order by followDate desc";

            Statement statement = connection.createStatement();
            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            ArrayList<notification> tweets = new ArrayList<notification>();
            if (tweetResult == null) {
                return null;
            } else {

                while (tweetResult.next()) {
                    notification temp = new notification();
                    //String a = tweetResult.getString("mentionUser");
                    temp.setMentionUser(tweetResult.getString("mentionUser"));
                    temp.setUserName(tweetResult.getString("userName"));
                    temp.setFollowedUserName(tweetResult.getString("followedUserName"));
                    temp.setFollowDate(tweetResult.getString("followDate"));
                    temp.setTweet(tweetResult.getString("tweet"));
                    tweets.add(temp);
                }
            }
            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }

    public static ArrayList<Twitt> searchTweetForDelete(String emailAddress, String userName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM tweets Where emailAddress = '" + emailAddress + "' order by postDate DESC";

            Statement statement = connection.createStatement();
            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            ArrayList<Twitt> tweets = new ArrayList<Twitt>();
            if (tweetResult == null) {
                return null;
            } else {

                while (tweetResult.next()) {
                    Twitt tempTwitt = new Twitt();
                    tempTwitt.setID(tweetResult.getString("twittID"));
                    tempTwitt.setEmailAddress(tweetResult.getString("emailAddress"));
                    tempTwitt.setTwitt(tweetResult.getString("tweet"));                   
                    tempTwitt.setDate(tweetResult.getString("postDate"));
                    tweets.add(tempTwitt);
                }
            }

            String preparedSQL1 = "SELECT * FROM tweets Where tweet LIKE '%@" + userName + "%' order by postDate DESC";

            // Statement statement = connection.createStatement();
            ResultSet tweetResult1 = statement.executeQuery(preparedSQL1);

            if (tweetResult1 == null) {
                return null;
            } else {
                while (tweetResult1.next()) {
                    Twitt tempTwitt = new Twitt();
                    tempTwitt.setEmailAddress(tweetResult1.getString("emailAddress"));
                    tempTwitt.setTwitt(tweetResult1.getString("tweet"));
                    tempTwitt.setDate(tweetResult1.getString("postDate"));
                    tweets.add(tempTwitt);
                }
            }
            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
    public static ArrayList<Twitt> searchTweetByHastag( String hashtag) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);

            Statement statement = connection.createStatement();
            ArrayList<Twitt> tweets = new ArrayList<Twitt>();

            String preparedSQL1 = "SELECT * FROM tweets Where tweet LIKE '%" + hashtag + "%' order by postDate DESC";

            ResultSet tweetResult1 = statement.executeQuery(preparedSQL1);

            if (tweetResult1 == null) {
                return null;
            } else {
                while (tweetResult1.next()) {
                    Twitt tempTwitt = new Twitt();
                    tempTwitt.setEmailAddress(tweetResult1.getString("emailAddress"));
                    tempTwitt.setTwitt(colorMention(colorHashtag(tweetResult1.getString("tweet"))));
                    tempTwitt.setDate(tweetResult1.getString("postDate"));
                    tweets.add(tempTwitt);
                }
            }
            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    public static ArrayList<Twitt> searchHashtag(String emailAddress, String userName) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dBURL = "jdbc:mysql://localhost:3306/twitterdb";
            String usernameRoot = "root";
            String password = "D3goldhap";
            Connection connection = DriverManager.getConnection(dBURL, usernameRoot, password);
            String preparedSQL = "SELECT * FROM tweets Where emailAddress = '" + emailAddress + "' order by postDate DESC";

            Statement statement = connection.createStatement();
            ResultSet tweetResult = statement.executeQuery(preparedSQL);
            ArrayList<Twitt> tweets = new ArrayList<Twitt>();
            if (tweetResult == null) {
                return null;
            } else {

                while (tweetResult.next()) {
                    Twitt tempTwitt = new Twitt();
                    tempTwitt.setEmailAddress(tweetResult.getString("emailAddress"));
                    tempTwitt.setTwitt(tweetResult.getString("tweet"));
                    tempTwitt.setDate(tweetResult.getString("postDate"));
                    tweets.add(tempTwitt);
                }
            }

            return tweets;

        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
            return null;
        }
    }
    public static String colorHashtag(String tweet) {
        String message = tweet;
        String newMessage = "";
        //String tempmessage = "";
        
        int startInd = 0;
        int i = 0;
        while (message.indexOf("#", startInd) != -1) {
            int indexOf = message.indexOf("#", startInd);
            int indexOfSpace = message.indexOf(" ", indexOf + 1);
            if (indexOfSpace == -1) {
                indexOfSpace = message.length();
            }
            String hashTag = message.substring(indexOf, indexOfSpace);
            if(hashTag.contains("</a>")){
                
            }else{
                String[] temphas = hashTag.split("#");
                newMessage = message.replace(hashTag, "<a style='color:red;' href='registration?action=hashtag1&amp;hashtag="+temphas[1]+"'>" + hashTag
                    + "</a>");
            }
            message = newMessage;
            startInd = indexOf + 1;
            i++;
        }
        

        if (newMessage.isEmpty()) {
            newMessage = message;
        }
        return newMessage;
    }
    public static String colorMention(String tweet) {
        String message = tweet;
        String newMessage = "";
        int startInd = 0;
        while (message.indexOf("@", startInd) != -1) {
            int indexOf = message.indexOf("@", startInd);
            int indexOfSpace = message.indexOf(" ", indexOf + 1);
            if (indexOfSpace == -1) {
                indexOfSpace = message.length();
            }
            String mention = message.substring(indexOf, indexOfSpace);
            if(mention.contains("</a>")){
                
            }else{
            newMessage = message.replace(mention, "<a style='color:red;'>" + mention
                    + "</a>");
            }
            message = newMessage;
            startInd = indexOf + 1;
//blueX is a class in CSS files, defining a blue color for text.

        }

        if (newMessage.isEmpty()) {
            newMessage = message;
        }
        return newMessage;
    }
}
