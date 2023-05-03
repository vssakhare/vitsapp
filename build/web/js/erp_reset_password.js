



$( document ).ready(function() {
    
    $("#resetOtp").on("click", function(){
   var reset = document.getElementById("reset").value; 
   
    
    if(document.getElementById("reset").value===null || document.getElementById("reset").value==="")
        {
                    alert("Please Enter OTP");
                            return false;


        }
     var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
           var id = jsonObj.UserId;
          window.alert(id);
          
        }
    };
    
      var url = "ajax";
    var uiactionName = "resetOtp";
    var params = "uiaction=" + uiactionName
    + "&reset=" + encodeURIComponent(reset)
         
            ;

    callAjax("POST", url, params, false, out.response);});
});