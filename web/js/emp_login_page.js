/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function bsc() {
    $.reject({
        reject: {
            safari: 4, // Apple Safari
            chrome: 45, // Google Chrome
            firefox: 40, // Mozilla Firefox
            msie: 8, // Microsoft Internet Explorer
            opera: 30, // Opera
            konqueror: true, // Konqueror (Linux)
            unknown: true // Everything else
        },
        beforeReject: function() {
            if ($.browser.name === 'chrome') {
                this.display = ['firefox', 'opera'];
            }
            if ($.os.name === 'iphone' || $.os.name === 'ipad') {
                this.browserShow = false;
                this.paragraph2 = '';
            }
        },
        close: false, // Prevent closing of window
        header: 'Your browser is not supported here', // Header Text
        paragraph1: 'You are currently using an unsupported browser version', // Paragraph 1
        paragraph2: 'Please install Latest version of any supported browser from below to proceed',
        display: ['msie', 'opera', 'firefox', 'chrome', 'safari'] // Displays only firefox, chrome, and opera
    });

    return false;
}
function generateOTP()
{ var txtUID = document.getElementById("txtUID").value;
    var txtP = document.getElementById("txtP").value;
   
    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
           var id = jsonObj.UserId;
          window.alert(id);
          
        }
    };
       
    if( document.getElementById("txtUID").value==="" ||  document.getElementById("txtUID").value===null)
        {
            alert("Please Enter Username");
                            return false; 
        }
         if( document.getElementById("txtP").value==="" ||  document.getElementById("txtP").value===null)
        {
            alert("Please Enter Password");
                            return false; 
        }
         if (document.getElementById("rad_VendorOpt").checked)
    {
        UserOpt = "Vendor";
    }
    
    else if (document.getElementById("rad_EmployeeOpt").checked)
    {
        UserOpt = "Emp";
    }
    else
    {
        alert("Please select one option!!!");
        return null;
    }
     if( !(document.getElementById("txtUID").value==="" ||  document.getElementById("txtUID").value===null))
     {
        if((UserOpt === "Emp")  && !(txtUID.length<=8 ))
        {
        alert("Please select Correct User type ");
         return false; 
        }
        else if ((UserOpt === "Vendor")  && !(txtUID.length>=9 ))
        {
        alert("Please select Correct User type ");
         return false; 
        }
}
        disableOtpButton(); 
     var url = "ajax";
    var uiactionName = "getOtp";
    var params = "uiaction=" + uiactionName
    + "&txtUID=" + encodeURIComponent(txtUID)
            + "&txtP=" + encodeURIComponent(txtP)
     + "&UserOpt=" + encodeURIComponent(UserOpt)
            ;

    callAjax("POST", url, params, false, out.response);
}
function disableOtpButton()
{
    var waitTime = 1;
    var currentTime = Date.parse(new Date());
    var deadline = new Date(currentTime + waitTime*60*1000);
    $("#loginOtp").prop('disabled', true);
    setTimeout(function(){ $("#loginOtp").prop('disabled', false); }, Date.parse(deadline) - Date.parse(new Date()));
    update_clock();
    function update_clock(){
        var t = Date.parse(deadline) - Date.parse(new Date());
        var seconds = Math.floor( (t/1000) % 60 );
        $("#loginOtp").text("Wait for "+ ("0"+seconds).slice(-2)+"s");
        if(t<=0){
            clearInterval(timeinterval); 
            $("#loginOtp").text("Resend OTP");
        }
    }
    var timeinterval = setInterval(update_clock,1000);
}



function login() {
     var Otp = document.getElementById("Otp").value;
    var txtUID = document.getElementById("txtUID").value;
    var txtP = document.getElementById("txtP").value;
    var uiActionName = document.getElementById("uiActionName").value;
    
    if(document.getElementById("Otp").value===null || document.getElementById("Otp").value==="")
        {
                    alert("Please Enter OTP");
                            return false;


        }
    if (document.getElementById("rad_VendorOpt").checked)
    {
        UserOpt = "Vendor";
    }
    
    else if (document.getElementById("rad_EmployeeOpt").checked)
    {
        UserOpt = "Emp";
    }
    else
    {
        alert("Please select one option!!!");
        return null;
    }


    var url = "erp";
    var params = "uiActionName=" + uiActionName
            + "&txtUID=" + encodeURIComponent(txtUID)
            + "&txtP=" + encodeURIComponent(txtP)
            + "&UserOpt=" + encodeURIComponent(UserOpt)
    +"&Otp=" + encodeURIComponent(Otp);

    postForm(url, params, "post");
}


function resetPassword() {
    
     var txtUID = document.getElementById("txtUID").value;
      
       if (document.getElementById("rad_VendorOpt").checked)
    {
        UserOpt = "Vendor";
    }
    
    else if (document.getElementById("rad_EmployeeOpt").checked)
    {
        UserOpt = "Emp";
    }
    else
    {
        alert("Please select one option!!!");
        return null;
    }
      if( document.getElementById("txtUID").value==="" ||  document.getElementById("txtUID").value===null)
        {
            alert("Please Enter Username");
                            return false; 
        }
         if( !(document.getElementById("txtUID").value==="" ||  document.getElementById("txtUID").value===null))
     {
          if((UserOpt === "Emp")  && !(txtUID.length<=8 ))
        {
        alert("Please select Correct User type ");
         return false; 
        }
        else if ((UserOpt === "Vendor")  && !(txtUID.length>=9 ))
        {
        alert("Please select Correct User type ");
         return false; 
        }
     }
      if (document.getElementById("rad_EmployeeOpt").checked){
            alert("Password can be reset only for Vendor Login ");
         return false; 
      }
         var url = "erp";
    uiActionName="resetPassword"
     var params = "uiActionName=" + uiActionName
    + "&txtUID=" + encodeURIComponent(txtUID)
     
            ;

   postForm(url, params, "post");
}
  

