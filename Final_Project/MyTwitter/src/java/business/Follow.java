/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author sagarluitel
 */
import java.io.Serializable;
//import java.util.ArrayList;

/**
 *
 * @javabean for User Entity
 */
public class Follow implements Serializable {
    //define attributes fullname, ...

    //define set/get methods for all attributes.
    private String userID;
    private String followedUserID;
    private String followDate;
    
    
    public Follow()
    {
        userID = "";
        followedUserID = "";
        followDate = "";

    }
    public Follow(String userID1, String followedUserID1, String followDate1)
    {
        this.userID = userID1;
        this.followedUserID = followedUserID1;
        this.followDate = followDate1;
    }
    public String getUserName()
    {
        return userID;
    }
    public void setUserName(String userID1)
    {
        this.userID = userID1;
    }
     public String getFollowedUserName()
    {
        return followedUserID;
    }
    public void setFollowedUserName(String followedUserID1)
    {
        this.followedUserID = followedUserID1;
    }
    public String getFollowDate()
    {
        return followDate;
    }
    public void setFollowDate(String followDate1)
    {
        this.followDate = followDate1;
    }
    
}
