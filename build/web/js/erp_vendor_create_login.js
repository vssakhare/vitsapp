function save() {

   var txtOldPwd = document.getElementById('txtOldPwd').value;
   var txtNewPwd = document.getElementById('txtNewPwd').value;
   var txtConfPwd = document.getElementById("txtConfPwd").value;


    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
           
          window.alert("Login Created Successfully !");
          
        }
    };
    var url = "ajax";
    var uiactionName = "postVendorLogin";
    var params = "uiaction=" + uiactionName
            + "&txtOldPwd=" + encodeURIComponent(txtOldPwd)
            + "&txtNewPwd=" + encodeURIComponent(txtNewPwd)
            + "&txtConfPwd=" + encodeURIComponent(txtConfPwd)
            ;

    callAjax("POST", url, params, false, out.response);
}

function submitButton() {
    if (ApplDtlvalidation()) {
        save();
        
        var uiActionName = document.getElementById("redir").value;
       // var action = "submit";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
              //  + "&subAction=" + encodeURIComponent(action)
                ;

        postForm(url, params, "get");
    }
}

function ApplDtlvalidation()
{
    var txtOldPwd = document.getElementById('txtOldPwd').value;
    var txtNewPwd = document.getElementById('txtNewPwd').value;
    var txtConfPwd = document.getElementById("txtConfPwd").value;

    if (txtOldPwd === null || txtOldPwd === "" || txtOldPwd !== 'welcome')
    {
        alert("Invalid Old password");
        return false;
    }
    if (txtNewPwd === null || txtNewPwd === "")
    {
        alert("New Password can not be blank");
        return false;
    }
    if (txtConfPwd === null || txtConfPwd === "") {
        alert("Confirm Password can not be blank");
        return false;
    }
    if (txtNewPwd === 'welcome' || txtConfPwd ==='welcome') {
        alert("Old password and new password can not be same ");
        return false;
    }
    if (txtNewPwd !== txtConfPwd)
        {
          alert("New Password and Confirm Password are not same. Please enter same password");
          return false;
        }
    return true;
}