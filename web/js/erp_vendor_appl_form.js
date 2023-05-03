
function save(action) {
    var txtApplID = document.getElementById('txtApplID').value;
    var txtVNum = document.getElementById('txtVNum').value;
    var txtVName = document.getElementById('txtVName').value;
    var txtPONum = document.getElementById('txtPONum').value;
    var txtInvoiceNum = document.getElementById('txtInvoiceNum').value;
    var txtInvoiceDt = document.getElementById('txtInvoiceDt').value;
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    var txtInwardNum = document.getElementById('txtInwardNum').value;
    var txtInwardDt = document.getElementById('txtInwardDt').value;

  
            var out = {
                response: function validation(info) {
                    var jsonObj = JSON.parse(info);
                    var id = jsonObj.AppId;
                    document.getElementById("txtApplID").value = id;
                    var id2 = jsonObj.Message1;
                    window.alert(id2);
                }
            };
            var url = "ajax";
            var uiactionName = "postVendorApplForm";
            var params = "uiaction=" + uiactionName
                    + "&txtApplID=" + encodeURIComponent(txtApplID)
                    + "&txtVNum=" + encodeURIComponent(txtVNum)
                    + "&txtVName=" + encodeURIComponent(txtVName)
                    + "&txtPONum=" + encodeURIComponent(txtPONum)
                    + "&txtInvoiceNum=" + encodeURIComponent(txtInvoiceNum)
                    + "&txtInvoiceDt=" + encodeURIComponent(txtInvoiceDt)
                    + "&txtInvoiceAmt=" + encodeURIComponent(txtInvoiceAmt)
                    + "&txtInwardNum=" + encodeURIComponent(txtInwardNum)
                    + "&txtInwardDt=" + encodeURIComponent(txtInwardDt)                   
                    + "&subAction=" + action
                    ;

            callAjax("POST", url, params, false, out.response);
       
   
}

function saveButton() {
    if (ApplDtlvalidation() && numericVal() && dateVal()) {
        save("save");

        var AppId = document.getElementById("txtApplID").value;
        var uiActionName = document.getElementById("rem").value;
        var action = "save";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
                + "&AppId=" + encodeURIComponent(AppId)
                + "&subAction=" + encodeURIComponent(action)
                ;

        postForm(url, params, "get");
    }
}

function ApplDtlvalidation() {

    var txtVNum = document.getElementById('txtVNum').value;
    var txtVName = document.getElementById('txtVName').value;
    var txtPONum = document.getElementById('txtPONum').value;
    var txtInvoiceNum = document.getElementById('txtInvoiceNum').value;
    var txtInvoiceDt = document.getElementById('txtInvoiceDt').value;
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    var txtInwardNum = document.getElementById('txtInwardNum').value;
    var txtInwardDt = document.getElementById('txtInwardDt').value;
    

    if (txtVNum === null || txtVNum === "") {
        alert("Vendor Number is Required");
        return false;
    }
    if (txtVName === null || txtVName === "") {
        alert("Vendor Name is Required");
        return false;
    }
    if (txtPONum === null || txtPONum === "") {
        alert("PO Number is Required");
        return false;
    }
    
    if (txtInvoiceNum === null || txtInvoiceNum === "") {
        alert("Invoice Number is Required");
        return false;
    }
    if (txtInvoiceDt === null || txtInvoiceDt === "") {
        alert("Please select Invoice Date");
        return false;
    }
    if (txtInvoiceAmt === null || txtInvoiceAmt === "") {
        alert("Invoice Amount is Required");
        return false;
    }
     if (txtInwardNum === null || txtInwardNum === "") {
        alert("Inward Num is Required");
        return false;
    }
     if (txtInwardDt === null || txtInwardDt === "") {
        alert("Please select Inward Date");
        return false;
    }
    return true;
}

function submitButton() {
    if (ApplDtlvalidation() && numericVal() && dateVal()) {
        save("submit");

        var uiActionName = document.getElementById("redir").value;
        var action = "submit";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
                + "&subAction=" + encodeURIComponent(action)
                ;

        postForm(url, params, "get");
    }
}

function numericVal()
{
  var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
  if(!/^[0-9]+$/.test(txtInvoiceAmt)){
    alert("Invoice Amount should be numerical Only!!!");
        return false;
    }
    return true;
}

function dateVal()
   {
    var txtInvoiceDt = document.getElementById('txtInvoiceDt').value;
    var txtInvoiceDt1 = txtInvoiceDt.replace('-', ' ');
    var txtInvoiceDt2 = txtInvoiceDt1.replace('-', ' 20');
    var myDate = new Date(txtInvoiceDt2);
    
    var txtInwardDt = document.getElementById('txtInwardDt').value;
    var txtInwardDt1 = txtInwardDt.replace('-', ' ');
    var txtInwardDt2 = txtInwardDt1.replace('-', ' 20');
    var myDate1 = new Date(txtInwardDt2);
    
    var today = new Date();
         if ((myDate>today) || (myDate1>today))
          { 
            alert('Invoice Date or Inward Date cannot be a future date !')
            return false;
          }
          return true;
    }
    
function viewFile(id, option) {
    var AppId = document.getElementById("txtApplicationId").value;
    var view = document.getElementById("view1").value;
//var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
    var url = view + "&AppId=" + AppId
            + "&FId=" + id
            + "&Option=" + option;

    window.open(url, 'Attachment', 'toolbar=no,location=no,directories=no,status=no, menubar=no,scrollbars=yes,resizable=yes,width=1200,height=1200');

}    