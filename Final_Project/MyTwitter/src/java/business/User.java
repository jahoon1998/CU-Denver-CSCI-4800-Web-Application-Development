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
public class User implements Serializable {
    //define attributes fullname, ...

    //define set/get methods for all attributes.
    private String fullName;
    private String userName;
    private String emailAddress;
    private String birthdate;
    private String password;
    private int questionNo;
    private String answer;
    private String salt;
   
    public User()
    {
        fullName = "";
        userName = "";
        emailAddress = "";
        birthdate = "";
        password = "";
        questionNo = 1;
        answer = "";
        salt = "";
       
    }
    public User(String fullName, String userName, String emailAddress, String birthdate, String password, String questionNo, String answer, String salt)
    {
        this.fullName = fullName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.birthdate = birthdate;
        this.questionNo = Integer.parseInt(questionNo);
        this.answer = answer;
        this.salt = salt;
    }
    public String getFullName()
    {
        return fullName;
    }
    
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String username)
    {
        this.userName = username;
    }
     public String getEmailAddress()
    {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }
    public String getBirthdate()
    {
        return birthdate;
    }
    public void setBirthdate(String birthday)
    {
        this.birthdate = birthday;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public int getQuestionNo()
    {
        return questionNo;
    }
    public void setQuestionNo(int questionNo)
    {
        this.questionNo = questionNo;
    }
    public String getAnswer()
    {
        return answer;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
      public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    /*
    @Override

    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("[%s,%s, %s,%s, %s,%s, %s]", this.getFullName(), this.getUserName(), this.getEmailAddress(), this.getPassword(), this.getBirthday(), Integer.toString(this.getQuestionNo()), this.getAnswer()));
      return sb.toString();
    }
    */

}
