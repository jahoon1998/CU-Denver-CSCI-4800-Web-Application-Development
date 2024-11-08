/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;
//import java.util.ArrayList;

/**
 *
 * @javabean for User Entity
 */
public class UserMention implements Serializable {
    //define attributes fullname, ...

    //define set/get methods for all attributes.
    private String tweet;
    private String userName;
    
    public UserMention()
    {
        tweet = "";
        userName = "";

    }
    public UserMention(String twitt, String username)
    {
        this.tweet = twitt;
        this.userName = username;
    }
    public String getTwitt()
    {
        return tweet;
    }
    
    public void setTwitt(String Twitt)
    {
        this.tweet = Twitt;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String username)
    {
        this.userName = username;
    }
    
}
