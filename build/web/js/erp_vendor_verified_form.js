/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function viewClearingDetails(cl_doc_no){
  
var view = document.getElementById("view").value;
            var url = view
                    + "&cl_doc_no=" +cl_doc_no
                    ;
//postForm(url, params, "get");
window.open(url, 'Attachment', 'toolbar=no,location=no,directories=no,status=no, menubar=no,scrollbars=yes,resizable=yes,width=600,height=600');
}

 