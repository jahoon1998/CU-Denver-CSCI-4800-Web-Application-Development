## Nov 11, 2018
# CSCI 4800 Web Application Devlopment Assignment 4

## New functionalities that you are implementing in this phase:

### Membership
adding the forgot password section for users.
users are able to update their profile information.

### Tweets/Twits
Users can post twits with mentions and hashtags
see twits
Mention other users in their twits
Delete their own twits

### Step 1: Update header.jsp
#### Change the code to include three menus
Home
Notification
Profile
And we have Signout from before
#### This menu is only visible when a user has logged in!
Home redirects to home.jsp
Notification is not linked to any page for now.
Profile redirects user to signup.jsp, filled with user information
Only if user is logged in

### Step 2: Update Signup.jsp
When user clicks on “Profile” in the top menu in the header, she is redirected to signup.jsp
The signup.jsp is filled with user information.
The username and email inputs are not changeable.
All other information can be edited.
The submit button label changes to “update”
Once submitted, the information is posted to membershipServlet for update.

### Step 4: Forgot Password

#### Develop your page forgotpassword.jsp to include a form (with POST method) that includes
A textbox for user’s email address.
The security question dropbox
The answer textbox (dynamic as in signup page)
A submit button

#### In Controller, 
When user submits this information, first user info is validated, and if it is correct, an email will be sent to user with a temporary randomly-generated 8-character password.
##### Use a random string generator to generate a random string of 8 characters as password.
##### Show a message “email sent!” to the user that says password was sent to your email.
##### Update user’s password in database to the new password
If information is incorrect, show a message “information incorrect”.

### Step 5: Posting a Twit
When user posts a twit (using the middle-top section of home.jsp)
The new twit is inserted in the database
Then redirects to home.jsp
Note that every time we want to redirect to home.jsp, we must ensure that
The list of twits has been updated: It means, if a user inserts or deletes a new twit, the home page is updated.

### Step 6: Mentioning
A user can mention other users in his/her twits.
A user is mentioned by @username
Thank you guys @john_smith @sarah_smith
##### Remember: If a user is mentioned in a twit, it must be shown in her home in the set of twits. 
You need to find which users are mentioned in which twits.

### Step 7: Deleting a Twit
A user can delete her own twits
A delete button appears next to every twit that is posted by the user.
Clicking this link will delete the twit and update the homepage with the list of new twits.
Remember, when you delete a twit, you must also delete all relevant data to it.
For example, if you have a table for UserMentions, then you must delete records related to a deleted twit from that table too.