## Dec 06, 2018
# CSCI 4800 Web Application Development Final Project

We will complete our minimalist implementation of twitter in this final project.
You can add new fields, methods, etc. as needed, as long as you commit to the MVC architecture.

### Notifications
After logging in, in the top menu, we have a notifications link/tab. 
The notification menu will redirect us to a notifications.jsp.
This page complies with the general structure (header, footer, etc.)
This page lists 
##### All the tweets in which this user is mentioned, since her last visit (login).
##### All users who started following this user, since her last visit (login).
For simplicity, you can show these two lists separately.
##### One list for new tweets, one list for new followers.

Add a column to user table, for example called last_login_time
After a user logs in, do a select on tweet and follow tables to find all records that occurred after user’s last login.
```sql
Select * from vwtwit where userID = ? And tweetDate >= ?
```

### Hashtags
Each tweet can have hashtags.
Each hashtag starts with # and does not have space. 
Clicking on each hashtag such as #x will redirect us to page hashtag.jsp
This page lists all the tweets in which #x is mentioned.
You need to make two new tables for hashtags
```java
Hashtag (hashtagID, hashtagText, hashtagCount) 
```
##### Stores all unique hashtags, along with their counts.  
```java
tweetHashtag (tweetID, hashtagID)
```
##### Stores which hashtag is mentioned in which tweet.

### Trending Hashtags
This will go in the “Trending topics” section.
From the Hashtag table find 10 topics with the most count.
Show these hashtags in the trending topics section: Bottom Left
Clicking on each hashtag in this section must take us to hashtag.jsp page, as mentioned earlier.

Each time a new tweet is inserted
#### For each hashtag #x in the tweet 
Check if #x exists in Hashtag table.
If not, insert a new record for #x
If Yes, update the count field for #x
Insert a record in tweetHashtag table, using tweetID, and hashtagID

### Follow
A user can follow another user.
For each user in the “Who to Follow” section, you will add a follow/unfollow button (depending on the case).
Using this button, a user can decide to follow or unfollow another user.
#### If the user is following a user, an unfollow button appears next to her name.
#### If a user is not followed, a follow button appears next to her name. 
#### Clicking on a follow/unfollow button will finally return to the same home.jsp
For simplicity, you can list all users in the who to follow section.

To implement follow, you need to make one new table
#### Follow(userID, followedUserID, followDate)
#### A record in this table means that user with UserID follows user with ID followedUserID on datetime followDate

### Salted and Hashed Password
Using the notion of salted and hash passwords
All passwords must be salted and hashed, using SHA-256
For this you need to add a column salt to user table
Instead of storing a cleartext password, you must store a salted+hashed password for the user.
Your login authorization must be changed to reflect this.
Your forgot password must also consider this change.


### Home
In Homepage, a user sees twits from those she follows + those she is mentioned in + those posted by her
In home.jsp under user’s profile picture (Top Left)
#### The count for tweets, following and followers must be updated after each change.
#### For example: once you delete a twit, the twit count must be updated accordingly.

