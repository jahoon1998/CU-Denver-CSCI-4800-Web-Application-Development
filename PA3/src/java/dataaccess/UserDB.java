/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import business.User;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.List;

public class UserDB {
    public static long /*boolean */ insert(User user) throws IOException, ClassNotFoundException 
    {
      try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Dhfhfk80014!";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
                        Statement statement = connection.createStatement();

            String preparedSQL = "Insert into user"
                                   + "(fullname, username, emailAddress, password, birthdate, questionNo, answer) "
                                   + "Values"
                                   + " ('"+user.getFullName()+"','"+user.getUserName()+"','"+user.getEmailAddress()+"','"+user.getPassword()+"','"+user.getBirthdate()+"','"+user.getQuestionNo()+"','"+user.getAnswer()+"')";
            
            int result = statement.executeUpdate(preparedSQL);
            
            return result;
          
            /*statement.executeUpdate(preparedSQL);
            connection.close();
            return true;*/
        }
        catch(SQLException e) {
        for (Throwable t : e)
            t.printStackTrace();
        return 0;
        }
    }
    public static User searchEmail(String emailAddress) throws ClassNotFoundException 
    {
    try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Dhfhfk80014!";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String preparedSQL = "SELECT * FROM user Where emailAddress = '"+emailAddress+"'";
            Statement statement = connection.createStatement();
            ResultSet userEmailResult = statement.executeQuery(preparedSQL);
            //if user is not found
            if(userEmailResult.next() == false)
            {
                return null;
            }
            else
            {
                User user = new User(userEmailResult.getString(2), userEmailResult.getString(3), userEmailResult.getString(4), 
                    userEmailResult.getString(5), userEmailResult.getString(6), userEmailResult.getString(7), userEmailResult.getString(8));
            connection.close();
            return user;
            }
            /*
            ArrayList<String> data = new ArrayList<String>();
            for(int i = 1; i <= userEmailResult.getRow(); i++)
            {
                data.add(userEmailResult.getString(i));
            }
*/
        }
    catch(SQLException e) 
    {
        for (Throwable t : e)
            t.printStackTrace();
    }
    return null;
    }
    public static User searchUserName(String userName) throws ClassNotFoundException 
    {
    try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/twitterdb";
            String username = "root";
            String password = "Dhfhfk80014!";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            String preparedSQL = "SELECT * FROM user Where username = '"+userName+"'";
            Statement statement = connection.createStatement();
            ResultSet userEmailResult = statement.executeQuery(preparedSQL);
            //if user is not found
            if(userEmailResult.next() == false)
            {
                return null;
            }
            else
            {
                User user = new User(userEmailResult.getString(2), userEmailResult.getString(3), userEmailResult.getString(4), 
                    userEmailResult.getString(5), userEmailResult.getString(6), userEmailResult.getString(7), userEmailResult.getString(8));
            connection.close();
            return user;
            }
        }
    catch(SQLException e) 
    {
        for (Throwable t : e)
            t.printStackTrace();
    }
    return null;
    }
    
}
