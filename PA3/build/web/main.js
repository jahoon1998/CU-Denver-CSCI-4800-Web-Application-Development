function validateForm()
{
    //validate password and confirmpassword 
    var password= document.getElementById("password");
    var error_password = document.getElementById("password_error");
    var confirmpassword = document.getElementById("confirmpassword");
    var error_confirmpassword=document.getElementById("confirmPassword_error");
    
    // validate if full name is not only one word
    var fullName = document.getElementById("fullName");
    var error_fullName = document.getElementById("fullName_error");
    var error_message=document.getElementById("errorDiv");
    
    // validate any input has “single quotation” mark in it
    var userName= document.getElementById("userName");
    var error_userName = document.getElementById("userName_error");
    var email = document.getElementById("email");
    var error_email = document.getElementById("email_error");
    var question = document.getElementById("questionNo");
    var error_question = document.getElementById("question_error");
    
    var visibleErrors = document.getElementsByClassName("isVisible");
    //reset all visible errors
    for(var i = 0; i < visibleErrors.length; i++)
    {
        visibleErrors[i].className="notVisible";
    }
    //reset all background Color to white
    var backGroundColor = document.getElementsByTagName("input");
    for(var x = 0; x < backGroundColor.length; x++)
    {
        backGroundColor[x].style.backgroundColor="white";
    }
    
    //validate password
    if(password.value !== confirmpassword.value)
    {
        confirmpassword.style.backgroundColor="yellow";
        error_confirmpassword.className="isVisible";
        error_message.innerHTML="Error! Password and confirm password do not match!";
        return false;		
    }
    else
    {
        error_confirmpassword.className="notVisible";
        confirmpassword.style.backgroundColor="white";
        error_message.innerHTML="";
	
    }
    // validate full name
    var fullNameCheck = fullName.value.split(" "); //returns a string with substrings splited by commas 
    if((fullNameCheck.length) <= 1)
    {
        fullName.style.backgroundColor="yellow";
        error_fullName.className="isVisible";
        error_message.innerHTML="Full name is not valid!";
        return false;
    }
    else
    {
        error_fullName.className="notVisible";
        fullName.style.backgroundColor="white";
        error_message.innerHTML="";
    }
    fullNameCheck.length = 0;
    //validate invalid input
    if(fullName.value.includes("'"))
    {
        fullName.style.backgroundColor="yellow";
        error_fullName.className="isVisible";
        error_message.innerHTML="Full name has invalid characters!";
        return false;
    }
    
    if(userName.value.includes("'"))
    {
        userName.style.backgroundColor="yellow";
        error_userName.className="isVisible";
        error_message.innerHTML="User name has invalid characters!";
        return false;
    }    

    if(email.value.includes("'"))
    {
        email.style.backgroundColor="yellow";
        error_email.className="isVisible";
        error_message.innerHTML="Email has invalid characters!";
        return false;
    }
    
    if(password.value.includes("'"))
    {
        password.style.backgroundColor="yellow";
        error_password.className="isVisible";
        error_message.innerHTML="Password has invalid characters!";
        return false;
    }
    
    if(question.value.includes("'"))
    {
        question.style.backgroundColor="yellow";
        error_question.className="isVisible";
        error_message.innerHTML="Question has invalid characters!";
        return false;
    }
    
    //validate if password contains a small letter, a capital letter, and a number
    var containsDigits = /[0-9]/.test(password.value);
    var containsUpper = /[A-Z]/.test(password.value);
    var containsLower = /[a-z]/.test(password.value);
    if (!(containsDigits && containsUpper && containsLower))
    {
        password.style.backgroundColor="yellow";
        error_password.className="isVisible";
        error_message.innerHTML="Password must contain a small letter, a capital letter, and a number!";
        return false;
    }
    
    return true;
}

function inputBox() {
   document.getElementById("box").style.display = 'block';
}