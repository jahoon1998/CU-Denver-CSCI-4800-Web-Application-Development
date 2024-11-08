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
public class Hastag implements Serializable {
    //define attributes fullname, ...

    //define set/get methods for all attributes.
    private String hID;
    private String hastag;
    private String hastagcount;
    
    
    public Hastag()
    {
        hID = "";
        hastag = "";
        hastagcount = "";

    }
    public Hastag(String tweettext, String email, String hId)
    {
        this.hID = hId;
        this.hastag = tweettext;
        this.hastagcount = email;

    }
    public String getHastag()
    {
        return hastag;
    }
    public void setHastag(String has)
    {
        this.hastag = has;
    }
    public String getHastagcont()
    {
        return hastagcount;
    }
    public void setHastagcont(String count)
    {
        this.hastagcount = count;
    }
    public String getHastagID()
    {
        return hID;
    }
    public void setHastagID(String id)
    {
        this.hID = id;
    }
    
}
