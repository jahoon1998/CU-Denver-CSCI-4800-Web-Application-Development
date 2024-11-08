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
public class notification {
    
    private String mentionUser;
    private String userName;
    private String followedUserName;
    private String followDate;
    private String tweet;
    
    
    public notification()
    {
        mentionUser = "";
        userName = "";
        followedUserName = "";
        followDate = "";
        tweet = "";
    }
    public notification(String mentionuser, String userID1, String followedUserID1, String followDate1, String tweeet)
    {
        this.mentionUser = mentionuser;
        this.userName = userID1;
        this.followedUserName = followedUserID1;
        this.followDate = followDate1;
        this.tweet = tweeet;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userID1)
    {
        this.userName = userID1;
    }
     public String getFollowedUserName()
    {
        return followedUserName;
    }
    public void setFollowedUserName(String followedUserID1)
    {
        this.followedUserName = followedUserID1;
    }
    public String getFollowDate()
    {
        return followDate;
    }
    public void setFollowDate(String followDate1)
    {
        this.followDate = followDate1;
    }
    public String getTweet()
    {
        return tweet;
    }
    public void setTweet(String twit)
    {
        this.tweet = twit;
    }
    public String getMentionUser()
    {
        return mentionUser;
    }
    public void setMentionUser(String user)
    {
        this.mentionUser = user;
    }
    
    
}
