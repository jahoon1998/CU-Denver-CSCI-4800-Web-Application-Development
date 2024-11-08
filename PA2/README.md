
## Sep 27, 2018 
# CSCI 4800 Web Application Development Assignment 2

### This is the first step toward designing a Mini Twitter Website.
In this assignment, we learn to design the UI page for a sign-up form

### Our goal is to
#### (I) design UI interface of Sign-up page of our MiniTwitter Project
#### (II) add necessary Javascript validation functions
Our objective is to practice the following concepts:
HTML5, CSS3, Javascript
This assignment includes 11 Steps.

#### Step 0: Create Project
Create a new Web App Project in NetBeans
Call it MiniTwitter


#### Step 1: UI Design
##### Add a sign-up form to index.html 
Hint: method=get, action = registration
##### All fields have labels: <label …></label>
##### Fields
Fullname: required
Username: required
Email: Required, Hint: use “email” type
Password: Required, Hint: use “password” type
Confirm Password: Required
Date-of-birth: Required, Hint: use “date” type
Security question: Required
A combo box with three different questions: Your first pet, your first car, your first school
Input for security question response
Submit button
##### Use HTML5 + Div tags (along with float and clear properties) to set the layout of your webpage



##### Step 1: Adding Error Div 
###### Add a error div to the top of the form
Hint:
```html
<form ….>
<div id=“errorMessage” class=“notVisible”></div>
….
</form>
```
Hint: In main.css define class “notVisible” with the following attribute:
Display: none;

#### Step 1: Adding Error Spans
Add error spans for all inputs
Hint: add a 
```html
<span id=“<inputid>_error” class=“notVisible”> *</span> 
```
at the right side of all inputs
All error spans must be hidden at first.
Hint: Class=‘notVisible’

#### Step 1: Adding Input Validation Function to Form
##### Add a “onsubmit” event to the form
Onsubmit = “return validateForm()”
This function will be implemented in this project.	

#### Step 2: Adding CSS
Add an external css to your project: main.css 
Make a styles folder and add the file to it
Link it to index.html
Add necessary styles for your UI to look legitimate

##### Minimum necessary styles
Define a style for body
Body must be centered
Hint: margin: 0 auto; 
Width: 800
Define a style for all inputs
Font = Tahoma, 10
Background: white
Width: 100;
Add other css rules as necessary

#### Step 3: Adding Javascript Input Validation
Add a js file to project: main.js

##### We will implement two functions in this javascript
1-validateForm to validate inputs
Check 1 to check 4
2- elements to change the layout of the form dynamically

