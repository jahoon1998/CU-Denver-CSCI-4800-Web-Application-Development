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
public class Twitt implements Serializable {
    //define attributes fullname, ...

    //define set/get methods for all attributes.
    private String tweetID;
    private String tweettext;
    private String emailAddress;
    private String postDate;
    
    
    public Twitt()
    {
        tweetID = "";
        tweettext = "";
        emailAddress = "";
        postDate = "";

    }
    public Twitt(String tweettext, String email, String date, String iD)
    {
        this.tweetID = iD;
        this.tweettext = tweettext;
        this.emailAddress = email;
        this.postDate = date;
    }
    public String getID()
    {
        return tweetID;
    }
    public void setID(String tweetiD)
    {
        this.tweetID = tweetiD;
    }
    public String getTwitt()
    {
        return tweettext;
    }
    public void setTwitt(String tweettext)
    {
        this.tweettext = tweettext;
    }
     public String getDate()
    {
        return postDate;
    }
    public void setDate(String date)
    {
        this.postDate = date;
    }
    public String getEmailAddress()
    {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }
    
}
