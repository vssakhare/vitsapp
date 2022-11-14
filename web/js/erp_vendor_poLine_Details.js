/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function() {
$('input[type="checkbox"][data-applid!=""]').prop('checked', true);
}
function savePOLineButton(){
var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            
        }
    };
    var i = 0;
    var po_line = "";
    var obj = document.getElementsByName("search_checkbox");
   var vendor_number=document.getElementById("UserNumber").value;
       
    for (i = 0 ; i<obj.length ; i++ )
    {
        if (obj[i].checked == true)
        {	
            if (po_line == "")
            {
                po_line = po_line + obj[i].value
            }
            else
            {
                po_line = po_line + "," + obj[i].value;
            }
        }	
    }
     var url = "ajax";
    var ui = "savePOLineDetails";
    var params = "uiaction="+ ui + "&po_line="+ po_line
                 + "&vendor_number="+ vendor_number;
 
    alert(po_line);
    callAjax("POST", url, params, false, out.response);

}