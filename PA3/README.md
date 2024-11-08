## Oct 16, 2018
# CSCI 4800 Web Application Development Assignment 3

### Objective of the Complete Project
You will design a minimalist version of Twitter 

#### Registration
Sign up
Log in
Recover forgotten password

#### Twitting
Post Twits of length 280 chars, including hashtags and mentions
Re-twit and reply to twits
View Twits that are posted by oneself, their followers, or in which they are mentioned.
Search for hashtags
Follow and unfollow other users
#### Reports
View top 10 trending hashtags
Get recommendations about who to follow (top 5 users with most followers)

For this assignment, you are developing the server-side code for
Login and signup segments of the project.
Used concepts from class
JDBC and MySQL, Servlets, JSP, some EL and JSTL, Session and cookies.

### Step 1: signup.jsp 
Download Mytwitter project template
You have already designed signup.jsp page in Assignment 2.
You just need to update it.
Rename your index.html to “signup.jsp” and replace it with the same file in the project.
Add header and footer to top and bottom of the page.
Add a hidden input for action=signup
Hint: <input type=“hidden” name=“action” value=“signup”>
When client-side validation succeeds, the page is forwarded to membershipServlet
##### Hint: <form action=“membership” …>
membershipServlet handles the rest.
Discussed in Controller part

### Step 2: login.jsp 
Make login.jsp first page that is shown to the user.
Hint: Web.XML
Add header and footer to top and bottom of the page.
The signup link must redirect them to the signup page.
The forgot password link must redirect them to forgotpassword.jsp page
Create the page, but you don’t need to implement it for this phase.
It will be submitted to membershipServlet.
Remember to use <input type=‘hidden’ name=“action” value=‘login’> to send the action you want as a parameter to membershipServlet

### Step 3: home.jsp 
Add header and footer to top and bottom of the page.
If the user has not logged in, when she goes to home.jsp, she will be redirected to login.jsp
Hint: JSTL + Session/Cookies
If the user has logged in, he/she is welcome to his/her homepage. 
Show a customized welcome message using user information

### Step 4: header.jsp 
header.jsp must be included at the top of all pages
If user has logged in, have a link in the top right corner with text: 
Signout
Hint: JSTL If + Session
If not, nothing must be printed there.

### Step 5: footer.jsp 
Must be included at the bottom of all pages
Print current date with some information in the footer.jsp.
Only Use EL and JSTL

### Step 6: Create Database 
Run the sql script to create twitterdb schema and user table.

### Step 7: Create User JavaBean 
#### Implement User JavaBean
Create a JavaBean for User that includes all user attributes from signup page and all get and set methods for them.
fullname
username
emailAddress
password
birthdate
questionNo
answer

### Step 8: Implement UserDB.Insert 
#### Implement Insert
Insert: gets a user JavaBean object and Stores user information in the database
The sample includes codes to help you start. Also in class assignments, we had exercises on how to insert and select from database.

### Step 9: Implement UserDB.Search 
#### Implement search
Looks for an emailAddress String 
If such emailAddress exists, makes a User JavaBean object and return it
If not, it returns null.

### Step 10: Controller part for Signup 
Add a servlet to your project
The controller for signup/login/forgot password is called  membershipServlet

#### Validate information on server-side again
Just check for non-empty values for required ones.
Check password and confirmPassword are equal.
Make sure email does not exist. 
Hint: use userDB.search method

#### what happens when validation fails 
The same signup.jsp page is shown
At the top of sign-up page a message is displayed
Example: “username can not be empty!”
“username already exists!”
The page is reloaded with previous user values in the inputs.
#### what happens when validation succeeds
Call UserDB.insert to insert the user object.
Then, the page is redirected to home.jsp

#### Implement controller code for login
Checks user credentials by accessing DataAccess Layer
UserDB.select method must be called
If valid, redirect to home.jsp
If not, Show an error message “login failed!”


