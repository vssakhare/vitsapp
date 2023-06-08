
window.onload = function () {

    document.getElementById("Module").value = document.getElementById("ps_pm_mod").value;//for selecting module to display po number or project id
    if (document.getElementById("Module").value === 'PS') {

        document.getElementById("selecttxtInvoiceType").value = document.getElementById("inv_type").value;
    }


    //the below check is done to display location only when application is saved, not before it is created
    if (document.getElementById("Module").value === 'PS') {

        if (document.getElementById("txtPROJNumber").value === null || document.getElementById("txtPROJNumber").value === "")
        {
            document.getElementById("txtPlant").value = " ";

        }

    }

    if (document.getElementById("txtPONumber").value === null || document.getElementById("txtPONumber").value === "")
    {
        document.getElementById("txtPlant").value = " ";

    }

    if (document.getElementById("txtStatus").value === "Saved" || document.getElementById("txtStatus").value === "Rejected")
    {
        var module_type = document.getElementById("Module").value;
        if (module_type == 'PM')
        {
            document.getElementById("myBtn").disabled = false;
        }

    }


//onload set the dropdown value to the one saved and add dropdown element of location if not exist.
    var userType = document.getElementById("userType").value;
    if (userType === "Vendor" && (document.getElementById("txtStatus").value === "Saved" || document.getElementById("txtStatus").value === "Rejected")) {

        if (document.getElementById("Module").value === 'PS') {
            // $("#SubmitAtPlant").append("<option value='"+$("#submit_at_location").val()+"'>"+$("#submit_at_location").val()+"</option>");  
            $("#SubmitAtPlant").append("<option value='" + $("#submit_at_location").val() + "' data-purchasing='" + $('#txtPurchasing_group').val() + "'>" + $("#submit_at_location").val() + "</option>");

            document.getElementById("SubmitAtPlant").value = document.getElementById("submit_at_location").value;
            /*   var txtprojId = document.getElementById("txtPROJNumber").value;
             // var txtprojId = txtprojId.substring(0, txtprojId.lastIndexOf("-"));
             var out = {
             response: function validation(info) {
             
             var jsonObj = JSON.parse(info);
             $("#SubmitAtPlant").append("<option value='"+$("#submit_at_location").val()+"'>"+$("#submit_at_location").val()+"</option>");  
             document.getElementById("SubmitAtPlant").value=document.getElementById("submit_at_location").value; 
             var exists = 0 !== $("#SubmitAtPlant option[value='"+$('#txtPlant').val()+"']").length; 
             if(!exists){
             var plnt=$('#txtPlant').val();
             $("#SubmitAtPlant").append("<option value='"+plnt+"'>"+$('#txtPlant').val()+"</option>"); 
             
             }
             var exists = 0 !== $("#SubmitAtPlant option[value='HO01-CORPORATE OFFICE']").length; 
             if(!exists){ $("#SubmitAtPlant").append("<option value='HO01-CORPORATE OFFICE'>HO01-CORPORATE OFFICE</option>"); } 
             var circle_desc=jsonObj.circle_code.concat('-'+jsonObj.circle);
             var zone_desc=jsonObj.zone_code.concat('-'+jsonObj.zone); 
             var exists = 0 !== $("#SubmitAtPlant option[value='"+circle_desc+"']").length;   
             if(!exists){ $("#SubmitAtPlant").append("<option value='"+circle_desc+"'>"+circle_desc+"</option>");}
             var exists = 0 !== $("#SubmitAtPlant option[value='"+zone_desc+"']").length;   
             if(!exists){ $("#SubmitAtPlant").append("<option value='"+zone_desc+"'>"+zone_desc+"</option>");}
             }
             };
             var url = "ajax";
             var uiactionName = "getPOLocation";
             var params = "uiaction=" + uiactionName
             + "&txtPONumber=" + txtPONumber
             + "&txtprojId=" +txtprojId
             + "&module_type=" +'PS'
             ;
             
             callAjax("POST", url, params, false, out.response);*/
        } else {
            if (document.getElementById("submit_at_location").value !== null && document.getElementById("submit_at_location").value !== "") {
                $("#SubmitAtPlant").append("<option value='" + $("#submit_at_location").val() + "' data-purchasing='" + $('#txtPurchasing_group').val() + "'>" + $("#submit_at_location").val() + "</option>");
                document.getElementById("SubmitAtPlant").value = document.getElementById("submit_at_location").value;
                var exists = 0 !== $("#SubmitAtPlant option[value='" + $('#txtPlant').val() + "']").length;
                if (!exists) {
                    getlocationpurchasing();//METHOD CALLED TO FETCH PURCHASING GROUP OF LOCATION FIELD.

                    var plnt = $('#txtPlant').val();
                    $("#SubmitAtPlant").append("<option value='" + plnt + "' data-purchasing='" + $('#txtOnloadPurchasing_group').val() + "'>" + $('#txtPlant').val() + "</option>");

                }
            }
        }
    }
    //to fetch the values of total invoice amount and count.
    if (document.getElementById("Module").value !== null && document.getElementById("Module").value !== "") {


        var module_type = document.getElementById("Module").value;
        if (module_type == 'PS')
        {
            var txtprojId = document.getElementById("txtPROJNumber").value;
            //  var txtprojId = txtprojId.substring(0, txtprojId.lastIndexOf("-"));
        }

        var txtPONumber = document.getElementById("txtPONumber").value;
        var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();

        if (document.getElementById("txtVNum"))
        {
            var txtvendorId = document.getElementById("txtVNum").value;
            var index = document.getElementById("txtVNum").value.indexOf("-");
            if (index === -1) {
                var txtvendorId = document.getElementById("txtVNum").value;
            } else {
                var txtvendorId = document.getElementById("txtVNum").value;
                var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
                var vendor_name = document.getElementById("txtVNum").value;
                var vendor_name = vendor_name.substring(vendor_name.indexOf('-') + 1);
            }
        }

        var out = {
            response: function validation(info) {

                var jsonObj = JSON.parse(info);
                var total_inv_amt = jsonObj.total_inv_amt;
                var total_invoicable_amt = jsonObj.total_invoicable_amt;
                var n = new Number(total_inv_amt);
                var m = new Number(total_invoicable_amt);
                var myObj = {
                    style: "currency",
                    currency: "INR"
                }
                if (document.getElementById("Module").value === 'PS') {

                    if (document.getElementById("txtPROJNumber").value === null || document.getElementById("txtPROJNumber").value === "")
                    {
                        document.getElementById("txtinvamt").value = " ";
                        document.getElementById("txtinvoicablamt").value = " ";

                    } else {

                        document.getElementById("txtinvamt").value = n.toLocaleString("en-IN", myObj);
                        document.getElementById("txtinvoicablamt").value = m.toLocaleString("en-IN", myObj);
                    }

                } else {
                    if (document.getElementById("txtPONumber").value === null || document.getElementById("txtPONumber").value === "")
                    {
                        document.getElementById("txtinvamt").value = " ";
                        document.getElementById("txtinvoicablamt").value = " ";

                    } else {
                        document.getElementById("txtinvamt").value = n.toLocaleString("en-IN", myObj);

                        document.getElementById("txtinvoicablamt").value = m.toLocaleString("en-IN", myObj);
                    }

                }




            }
        };
        var url = "ajax";
        var uiactionName = "getInvDetails";
        var params = "uiaction=" + uiactionName
                + "&txtPONumber=" + txtPONumber
                + "&txtprojId=" + txtprojId
                + "&module_type=" + module_type
                + "&txtvendorId=" + txtvendorId
                ;

        callAjax("POST", url, params, false, out.response);
    }

    /*  if(document.getElementById("mod")){
     document.getElementById("txtModule").value = document.getElementById("mod").value;//to set if module for escalation is selected
     }*/
}




function getList() {
    var txtPONumber = document.getElementById("poNumber").value.trim();
    var url = "erp";
    var uiactionName = "getVendorList";
    var params = "uiActionName=" + uiactionName
            + "&txtPONumber=" + txtPONumber
            ;
    postForm(url, params, "POST");

}
function SelectModuleButton() {
    var module_type = document.getElementById("Module").value;
    if (module_type == 'PS')
    {
        jQuery("#ProjectId").show();
        jQuery("#MaterialPo").show();
        jQuery("#CentagesPo").show();
        jQuery("#ServicePo").show();
        jQuery("#txtPROJNumber").show();
        jQuery("#txtMaterialPo").show();
        jQuery("#txtCentagesPo").show();
        jQuery("#txtServicePo").show();
        jQuery("#txtInvoiceType").show();
        jQuery("#selecttxtInvoiceType").show();
        document.getElementById("myBtn").disabled = true;
        jQuery("#podetail").hide();
    } else
    {
        jQuery("#txtInvoiceType").hide();
        jQuery("#selecttxtInvoiceType").hide();
        jQuery("#ProjectId").hide();
        jQuery("#MaterialPo").hide();
        jQuery("#CentagesPo").hide();
        jQuery("#ServicePo").hide();
        jQuery("#txtPROJNumber").hide();
        jQuery("#txtMaterialPo").hide();
        jQuery("#txtCentagesPo").hide();
        jQuery("#txtServicePo").hide();


    }

}
/*function SelectModuleButton(){
 var module_type = document.getElementById("Module").value;
 if(module_type=='PS')
 {
 jQuery("#mydrop_down_label").html("Project ID");
 jQuery("#txtPONumber").hide();
 jQuery("#txtPROJNumber").show();
 jQuery("#projcheckbox").show();
 jQuery("#txtSCHEME").show();
 jQuery("#selecttxtSCHEME").show();
 jQuery("#txtInvoiceType").show();
 jQuery("#selecttxtInvoiceType").show();
 
 jQuery("#myBtn").hide();
 //jQuery("#SubmitAtPlant").hide(); 
 // jQuery("#submit_at").hide();  
 
 //  jQuery("#txtpobal").hide();//hide as for ps no check for po balance amt
 //  jQuery("#txtpobalance").hide();
 }
 else
 {
 jQuery("#mydrop_down_label").html("PO Number");
 jQuery("#txtPONumber").show();
 jQuery("#txtPROJNumber").hide();
 jQuery("#projcheckbox").hide();
 jQuery("#txtSCHEME").hide();
 jQuery("#selecttxtSCHEME").hide();
 jQuery("#txtInvoiceType").hide();
 jQuery("#selecttxtInvoiceType").hide();
 jQuery("#myBtn").show();
 //  jQuery("#SubmitAtPlant").show(); 
 //    jQuery("#submit_at").show();  
 }
 }*/
function save(action) {
    var rejFlag = document.getElementById("rejflag").value;
    var showinwflag = document.getElementById("showinwflag").value;
    var status = document.getElementById("status").value;
    var txtApplId = document.getElementById('txtApplId').value;
    var module_type = document.getElementById("Module").value;//module type to differentiate between proj id and po number
    var ModuleFlag = document.getElementById("ModuleFlag").value;
    if (module_type == 'PS')
    {
        var txtInvoiceType = document.getElementById("selecttxtInvoiceType").value;
    }


    var txtPONumber = document.getElementById("txtPONumber").value;
    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
    var SubmitAtPlant = document.getElementById("SubmitAtPlant").value;
    var SubmitAtDesc = SubmitAtPlant.substring(SubmitAtPlant.indexOf("-") + 1);
    var SubmitAtPlant = SubmitAtPlant.substring(0, SubmitAtPlant.indexOf("-"));
    if (module_type != 'PS')
    {
        if (document.getElementById("status").value === "" || document.getElementById("status").value === "Saved")
        {
            var i = 0;//to save the po line items which are selected
            var po_line = "";
            var selectedPlant = "";
            var obj = document.getElementsByName("search_checkbox");
            // var selectedPlant=document.getElementsByAttribute("data-purchasing");
            // document.getElementById('SubmitAtPlant').selectedOptions[0].getAttribute("data-purchasing"); 
            var vendor_number = document.getElementById("UserNumber").value;

            for (i = 0; i < obj.length; i++)
            {
                if (obj[i].checked == true)
                {
                    if (po_line == "" && selectedPlant == "")
                    {
                        // selectedPlant = selectedPlant + obj[i].getAttribute("data-purchasing");
                        po_line = po_line + obj[i].value;
                    } else
                    {
                        po_line = po_line + "," + obj[i].value;
                        //   selectedPlant = selectedPlant + "," + obj[i].getAttribute("data-purchasing");
                    }
                }
            }
        }
    }
    /* if(module_type==='PS'){
     if (txtprojId.indexOf('-') === -1)
     {
     alert("Please Select Project ID from drop down list only!!!");
     return false;
     } 
     } 
     
     else
     {  if (txtPONumber.indexOf('-') === -1)
     {
     alert("Please Select PO Number from drop down list only!!!");
     return false;
     } 
     }*/
    if (module_type == 'PS')
    {
        var txtPROJNumber = document.getElementById('txtPROJNumber').value;
        // var txtPROJNumber = txtPROJNumber.substring(0, txtPROJNumber.lastIndexOf("-"));
        var txtMaterialPo = document.getElementById('txtMaterialPo').value;
        var txtCentagesPo = document.getElementById('txtCentagesPo').value;
        var txtServicePo = document.getElementById('txtServicePo').value;

    }


    var txtInvoiceNum = document.getElementById('txtInvoiceNum').value;

    var txtVendorInwardDt = document.getElementById('txtVendorInwardDt').value;
    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    var txtInvoiceAmt = txtInvoiceAmt.replace(/[^\d\.\-]/g, '');

    var txtInvSubmitDt = document.getElementById('txtInvSubmitDt').value;
    var txtPlant = document.getElementById('txtPlant').value;
    var txtOnloadPurchasing_group = document.getElementById('txtOnloadPurchasing_group').value;
    // var Purchasing_group = document.getElementById('txtPurchasing_group').value;
    // var element = document.getElementById('SubmitAtPlant');
    if (action == "save" || action == "submit") {
        var Purchasing_group = document.getElementById('SubmitAtPlant').selectedOptions[0].getAttribute("data-purchasing");
    }
    if (action == "forward")//to get purchasing group when forwarded
    {
        var Purchasing_group = document.getElementById('forwardAtPlant').selectedOptions[0].getAttribute("data-purchasing");

    }
    var PurchasingDesc = txtPlant.substring(txtPlant.indexOf("-") + 1);
    var txtPlant = txtPlant.substring(0, txtPlant.indexOf("-"));
    if (txtPlant === null || txtPlant === "") {
        alert("Please Select PO Number from drop down list only!!!");
        return false;
    }

    //var txtInvoiceToDt = document.getElementById('txtInvoiceToDt').value;
    /*  if(txtPlant=='HO01')
     {
     var txtModule = document.getElementById('txtModule').value;
     }
     else
     {var txtModule = "" ;}*/
    if (showinwflag == 'Y')


    {
        var txtInwardNum = document.getElementById('txtInwardNum').value;

        var txtInwardDt = document.getElementById('txtInwardDt').value;
    } else
    {
        txtInwardNum = "";

    }
    if (rejFlag == "Y") {
        var txtResubmitDt = document.getElementById('txtVRDate').value;
    }
    if (action == "reject") {
        if (document.getElementById("txtReason").value == "other")
        {
            var txtReason = document.getElementById("txtReason1").value;
        } else
        {
            var txtReason = document.getElementById("txtReason").value;
        }
    }
    if (action == "forward") {
        var forwardAtPlant = document.getElementById("forwardAtPlant").value;
        var ForwardToDesc = forwardAtPlant.substring(forwardAtPlant.indexOf("-") + 1);
        var ForwardToPlant = forwardAtPlant.substring(0, forwardAtPlant.indexOf("-"));

    }

    if (action == "reject" || action == "approve")
    {
        var vendor_number = document.getElementById("txtVNum").value;
        var vendor_name = document.getElementById("txtVName").value;
    } else if (document.getElementById("txtVNum"))

    {
        var txtvendorId = document.getElementById("txtVNum").value;
        var index = document.getElementById("txtVNum").value.indexOf("-");
        if (index === -1) {
            var txtvendorId = document.getElementById("txtVNum").value;
        } else {
            var txtvendorId = document.getElementById("txtVNum").value;
            var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
            var vendor_name = document.getElementById("txtVNum").value;
            var vendor_name = vendor_name.substring(vendor_name.indexOf('-') + 1);
        }
    }
    if (module_type == 'PS')
    {
    var retentionCheckBoxValue = "blank";
    var fullOrPartial="";
    if (txtInvoiceType === 'Retention Claim Charges') {
        retentionCheckBoxValue = "";
        $(".retention_checkbox").each(function () {
            if ($(this).is(":checked"))
                retentionCheckBoxValue += ($(this).val()) + "#";
        });
        if(document.getElementById('rad_Retention_100').checked) {
            fullOrPartial="full";
        }else if(document.getElementById('rad_Retention_balance').checked) {
            fullOrPartial="partial";
        }
    }
    }
    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var id = jsonObj.AppId;
            document.getElementById("txtApplId").value = id;
            var id2 = jsonObj.Message1;
            window.alert(id2);
        }
    };
    var url = "ajax";
    var uiactionName = "postVendorApplForm";
    var params = "uiaction=" + uiactionName
            + "&txtApplId=" + encodeURIComponent(txtApplId)
            + "&txtPONumber=" + encodeURIComponent(txtPONumber)
            + "&txtprojId=" + encodeURIComponent(txtPROJNumber)
            + "&txtMaterialPo=" + encodeURIComponent(txtMaterialPo)
            + "&txtCentagesPo=" + encodeURIComponent(txtCentagesPo)
            + "&txtServicePo=" + encodeURIComponent(txtServicePo)
            + "&txtInvoiceNum=" + encodeURIComponent(txtInvoiceNum)
            + "&txtVendorInwardDt=" + encodeURIComponent(txtVendorInwardDt)
            + "&txtInvoiceAmt=" + encodeURIComponent(txtInvoiceAmt)
            + "&txtVendorInvoiceDate=" + encodeURIComponent(txtVendorInvoiceDate)
            + "&txtInvSubmitDt=" + encodeURIComponent(txtInvSubmitDt)
            //+ "&txtInvoiceToDt=" + encodeURIComponent(txtInvoiceToDt)
            + "&vendor_number=" + encodeURIComponent(vendor_number)
            + "&txtvendorId=" + encodeURIComponent(txtvendorId)
            + "&vendor_name=" + encodeURIComponent(vendor_name)
            + "&txtInwardNum=" + encodeURIComponent(txtInwardNum)
            + "&txtInwardDt=" + encodeURIComponent(txtInwardDt)
            + "&txtResubmitDt= " + encodeURIComponent(txtResubmitDt)
            + "&txtReason=" + encodeURIComponent(txtReason)
            + "&txtPlant=" + encodeURIComponent(txtPlant)
            + "&module_type=" + encodeURIComponent(module_type)//differentiate between po number and proj id
            //  + "&txtModule=" + encodeURIComponent(txtModule)
            + "&txtOnloadPurchasing_group=" + encodeURIComponent(txtOnloadPurchasing_group)
            + "&txtInvoiceType=" + encodeURIComponent(txtInvoiceType)
            + "&status=" + encodeURIComponent(status)
            + "&ModuleFlag=" + encodeURIComponent(ModuleFlag)
            + "&SubmitAtPlant=" + encodeURIComponent(SubmitAtPlant)
            + "&PurchasingDesc=" + encodeURIComponent(PurchasingDesc)
            + "&Purchasing_group=" + encodeURIComponent(Purchasing_group)
            + "&SubmitAtDesc=" + encodeURIComponent(SubmitAtDesc)
            + "&po_line=" + encodeURIComponent(po_line)
            + "&selectedPlant=" + encodeURIComponent(selectedPlant)
            + "&vendor_number=" + encodeURIComponent(vendor_number)
            + "&ForwardToPlant=" + encodeURIComponent(ForwardToPlant)
            + "&ForwardToDesc=" + encodeURIComponent(ForwardToDesc)
            + "&subAction=" + action
            +"&retentionCheckBoxValue="+retentionCheckBoxValue
            +"&fullOrPartial="+fullOrPartial;

    callAjax("POST", url, params, false, out.response);


}

function saveButton() {
        if (ApplDtlvalidation() && numericVal() && dateVal() && RetentionApplDtlvalidation()) {
            save("save");
            var userType = document.getElementById("userType").value;
            var uiActionName = document.getElementById("rem").value;
            var txtInvoiceType = document.getElementById("selecttxtInvoiceType").value;
            var AppId = document.getElementById("txtApplId").value;
            var module_type = document.getElementById("Module").value;//module type to differentiate between proj id and po number
            if (document.getElementById("txtVNum"))

            {
                var txtvendorId = document.getElementById("txtVNum").value;
                var index = document.getElementById("txtVNum").value.indexOf("-");
                if (index === -1) {
                    var txtvendorId = document.getElementById("txtVNum").value;
                } else {
                    var txtvendorId = document.getElementById("txtVNum").value;
                    var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
                    var vendor_name = document.getElementById("txtVNum").value;
                    var vendor_name = vendor_name.substring(vendor_name.indexOf('-') + 1);
                }
            }
            var action = "save";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    + "&AppId=" + encodeURIComponent(AppId)
                    + "&subAction=" + encodeURIComponent(action)
                    + "&txtvendorId=" + encodeURIComponent(txtvendorId)
                    + "&module_type=" + encodeURIComponent(module_type)
            +"&txtInvoiceType="+txtInvoiceType
                    ;

            postForm(url, params, "get");
        }

}

function ApplDtlvalidation_Approve() {
    var txtInwardNum = document.getElementById('txtInwardNum').value;

    var txtInwardDt = document.getElementById('txtInwardDt').value;
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
function ApplEmpDtlvalidation()
{
    
    var txtinvamt = document.getElementById('txtinvamt').value;
    var txtinvamt = txtinvamt.replace(/[^\d\.\-]/g, '');
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    var txtInvoiceAmt = txtInvoiceAmt.replace(/[^\d\.\-]/g, '');
    var txtpoamt = document.getElementById('txtpoamt').value;
    var txtpoamt = txtpoamt.replace(/[^\d\.\-]/g, '');
    if (document.getElementById('txtpobal')) {
        var txtpobal = document.getElementById('txtpobal').value;
        var txtpobal = txtpobal.replace(/[^\d\.\-]/g, '');
    }
    var txtModuleType = document.getElementById('Module').value;//module type to differentiate between proj id and po number
    if (txtModuleType == 'PS') {
        if (parseFloat(txtinvamt, 10) > parseFloat(txtpoamt, 10))

        {
            alert("Total Invoices Submitted Amount cannot be greater than TOTAL PO Amount");
            return false;
        }
    } else {

        if (parseFloat(txtinvamt, 10) > parseFloat(txtpobal, 10))

        {
            alert("Total Invoices Submitted Amount cannot be greater than PO Balance Amount");
            return false;
        }
        if (txtpoamt === null || txtpoamt === "") {
            alert("Total PO Amount cannot be blank...Please Raise Issue!!!");
            return false;
        }
        if (txtpobal === null || txtpobal === "") {
            alert(" PO Balance Amount cannot be blank...Please Raise Issue!!!");
            return false;
        }
        if (txtinvamt === null || txtinvamt === "") {
            alert(" Total Invoices Created Amount cannot be blank...Please Raise Issue!!!");
            return false;
        }
        if (txtinvoicablamt === null || txtinvoicablamt === "") {
            alert(" Total Invoicable Amount cannot be blank...Please Raise Issue!!!");
            return false;
        }
    }
    return true;
}
function ApplDtlvalidation() {

    var txtModuleType = document.getElementById('Module').value;//module type to differentiate between proj id and po number

    var SubmitAtPlant = document.getElementById('SubmitAtPlant').value;
    var SubmitAtPlant = SubmitAtPlant.substring(0, SubmitAtPlant.indexOf("-"));
    if (txtModuleType == 'PS') {
        var txtinvoicablamt = document.getElementById('txtinvoicablamt').value;
        var txtinvoicablamt = txtinvoicablamt.replace(/[^\d\.\-]/g, '');
        var txtpoamt = document.getElementById('txtpoamt').value;
        var txtpoamt = txtpoamt.replace(/[^\d\.\-]/g, '');
    } else {
        var txtinvoicablamt = document.getElementById('txtinvoicablamt').value;
        var txtinvoicablamt = txtinvoicablamt.replace(/[^\d\.\-]/g, '');
    }
    var txtInvoiceNum = document.getElementById('txtInvoiceNum').value;

    var txtVendorInwardDt = document.getElementById('txtVendorInwardDt').value;

    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;

    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    var txtInvoiceAmt = txtInvoiceAmt.replace(/[^\d\.\-]/g, '');
    var txtModuleType = document.getElementById('Module').value;//module type to differentiate between proj id and po number
    if (txtModuleType == 'PS')
    {
        var txtprojId = document.getElementById("txtPROJNumber").value;
        var txtInvoiceType = document.getElementById("selecttxtInvoiceType").value;
    } else {
        var txtPONumber = document.getElementById('txtPONumber').value.trim();

    }
    var txtPlant = document.getElementById('txtPlant').value;
    var txtPlant = txtPlant.substring(0, txtPlant.indexOf("-"));
    /* if(txtPlant=='HO01')
     {
     var txtModule = document.getElementById('txtModule').value;
     }*/


    /*var txtInvoiceFrmDt = document.getElementById('txtInvoiceFrmDt').value;
     
     var txtInvoiceToDt = document.getElementById('txtInvoiceToDt').value;
     
     
     var txtInwardNum = document.getElementById('txtInwardNum').value;
     
     var txtInwardDt = document.getElementById('txtInwardDt').value;*/
    if (SubmitAtPlant === null || SubmitAtPlant === "") {
        alert("Please select Submit At Location");
        return false;
    }
    if (txtpoamt === null || txtpoamt === "") {
        alert("Total PO Amount cannot be blank...Please Raise Issue!!!");
        return false;
    }
    /*   if(txtModuleType!=='PS'){
     if (txtpobal === null || txtpobal === "") {
     alert(" PO Balance Amount cannot be blank...Please Raise Issue!!!");
     return false;
     }}
     if (txtinvamt === null || txtinvamt === "") {
     alert(" Total Invoices Created Amount cannot be blank...Please Raise Issue!!!");
     return false;
     }
     if (txtinvoicablamt === null || txtinvoicablamt === "") {
     alert(" Total Invoicable Amount cannot be blank...Please Raise Issue!!!");
     return false;
     }*/
    /* if(txtModuleType=='PS'){
     if((parseFloat(txtInvoiceAmt,10)>parseFloat(txtinvoicablamt,10))&& 
     ( document.getElementById('selecttxtInvoiceType').value=='Original Invoice' ||
     document.getElementById('selecttxtInvoiceType').value=='Excess Invoice' ||
     document.getElementById('selecttxtInvoiceType').value=='Migo Based Invoice'))
     
     {
     alert("Invoice Amount cannot be greater than TOTAL Invoicable Amount");
     return false;  
     }
     }else{
     if(parseFloat(txtInvoiceAmt,10)>parseFloat(txtinvoicablamt,10))
     
     {
     alert("Invoice Amount cannot be greater than TOTAL Invoicable Amount");
     return false;  
     }
     }*/
    if (txtModuleType === null || txtModuleType === "") {//differentiate between po number and proj id
        alert("Please select Module Type");
        return false;
    }
    /*  if(txtPlant === "HO01" && txtModule === ""){
     alert("Please select Module");
     return false;
     }*/
    if (txtModuleType === 'PS') {
        if (txtInvoiceType === null || txtInvoiceType === "") {
            alert("Please select Invoice Type");
            return false;
        }
    }
    if (txtModuleType === 'PS') {
        if (txtprojId === null || txtprojId === "") {
            alert("Please select Project ID");
            return false;
        }
    } else {
        if (txtPONumber === null || txtPONumber === "") {
            alert("Please select PO Number");
            return false;
        }
    }

    if (txtInvoiceNum === null || txtInvoiceNum === "") {
        alert("Invoice Number is Required");
        return false;
    }
    if (txtVendorInwardDt === null || txtVendorInwardDt === "") {
        alert(" Vendor Inward Date is Required");
        return false;
    }
    if (txtInvoiceAmt === null || txtInvoiceAmt === "") {
        alert("Invoice Amount is Required");
        return false;
    }

    if (txtVendorInvoiceDate === null || txtVendorInvoiceDate === "") {
        alert("Vendor Invoice Date is Required");
        return false;
    }
    
    /*if (txtInvoiceFrmDt === null || txtInvoiceFrmDt === "") {
     alert("Invoice From Date is Required");
     return false;
     }
     
     
     
     
     if (txtInwardNum === null || txtInwardNum === "") {
     alert("Inward Num is Required");
     return false;
     }
     if (txtInwardDt === null || txtInwardDt === "") {
     alert("Please select Inward Date");
     return false;
     }*/
    return true;
}

function submitButton() {
    if (ApplDtlvalidation() && numericVal() && dateVal()) {
        if (document.getElementById("txtVNum"))

        {
            var txtvendorId = document.getElementById("txtVNum").value;
            var index = document.getElementById("txtVNum").value.indexOf("-");
            if (index === -1) {
                var txtvendorId = document.getElementById("txtVNum").value;
            } else {
                var txtvendorId = document.getElementById("txtVNum").value;
                var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
                var vendor_name = document.getElementById("txtVNum").value;
                var vendor_name = vendor_name.substring(vendor_name.indexOf('-') + 1);
            }
        }

        var retVal = confirm("Do you want to Submit?");
        if (retVal == true) {
            save("submit");

            var uiActionName = document.getElementById("redir").value;
            var action = "submit";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    + "&txtvendorId=" + encodeURIComponent(uiActionName)
                    + "&subAction=" + encodeURIComponent(action)
                    ;

            postForm(url, params, "get");
            return true;
        } else {
            var uiActionName = document.getElementById("redir").value;
            var action = "submit";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    + "&subAction=" + encodeURIComponent(action)
                    ;

            postForm(url, params, "get");
            return false;

        }
    }
}
function ApplDtlvalidation_Forward() {
    var forwardAtPlant = document.getElementById('forwardAtPlant').value;

    if (forwardAtPlant === null || forwardAtPlant === "") {
        alert("Forward To Field is required");
        return false;
    }
    var forwardAtPlant = document.getElementById('forwardAtPlant').value;
    var Forward = forwardAtPlant.substring(0, forwardAtPlant.indexOf("-")).trim();
    var txtPlant = document.getElementById('txtPlant').value;
    var Plant = txtPlant.substring(0, txtPlant.indexOf("-")).trim();
    if (Forward === Plant) {
        alert("Order Issuing Authority and Forward To are Same!!!");
        return false;
    }


    return true;
}
function forwardButton()
{
    if (ApplDtlvalidation() && numericVal() && dateVal() && ApplDtlvalidation_Forward()) {
        var retVal = confirm("Do you want to Forward?");
        if (retVal == true) {

            save("forward");
            var uiActionName = "getAuthPOList";
            var action = "approve";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)

                    ;

            postForm(url, params, "get");
            return true;
        } else {
            return false;
        }
    }

}
function approveButton() {
    if (ApplEmpDtlvalidation() && numericVal() && dateVal() && ApplDtlvalidation_Approve()) {

        var retVal = confirm("Do you want to Verify?");
        if (retVal == true) {

            save("approve");
            var uiActionName = "getAuthPOList";
            var action = "approve";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)

                    ;

            postForm(url, params, "get");
            return true;
        } else {
            var uiActionName = "getAuthPOList";
            var action = "approve";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName);
            return false;
        }

    }
}


function myFunction() {
    var nameInput = document.getElementById('txtReason1').value;

    if (nameInput == '') {

        document.getElementById('Buttonsubmit1').disabled = true;

    } else {

        document.getElementById('Buttonsubmit1').disabled = false;

    }
}
;
function reject() {
    document.getElementById('txtReason').style.display = 'block';
    document.getElementById('divheader-div1').style.display = 'block';
    document.getElementById('Buttonsubmit1').style.display = 'block';
    document.getElementById('Buttonsubmit1').disabled = true;
    document.getElementById('ButtonApprove').disabled = 'true';
    document.getElementById('Buttonreject').disabled = 'true';
//document.getElementById('txtReason1').style.display='block';
}
function rejectButton() {

    //  if (ApplEmpDtlvalidation() && numericVal() && dateVal()&& ApplDtlvalidation_Approve()) {
    if (ApplDtlvalidation_Approve()) {
        var retVal = confirm("Do you want to Reject?");
        if (retVal == true) {

            save("reject");
            var uiActionName = "getAuthPOList";
            var action = "reject";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    // + "&subAction=" + encodeURIComponent(action)
                    ;

            postForm(url, params, "get");
            return true;
        } else {
            var uiActionName = "getAuthPOList";
            //var uiActionName = document.getElementById("rem").value;
            var action = "reject";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    // + "&subAction=" + encodeURIComponent(action)
                    ;

            postForm(url, params, "get");
            return false;
        }
    }
}
function checkvalue(val)
{
    if (val === "other")
    {
        document.getElementById('txtReason1').style.display = 'block';
        //document.getElementById('Buttonreject').disabled=false;
    } else
    {
        // document.getElementById('Buttonsubmit1').style.display='block'; 
        document.getElementById('Buttonsubmit1').disabled = false;
    }
}
function numericVal()
{
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    var txtInvoiceAmt = txtInvoiceAmt.replace(/[^\d\.\-]/g, '');
    //var regexp = /^\d+\.\d{1,2}$/;
    txtInvoiceAmt.replace(/\s/g, "");
    if (!(/^[-+]?\d*\.?\d*$/.test(txtInvoiceAmt))) {
        alert("Invoice Amount should be numerical and upto two decimal places Only!!!");
        return false;
    }
    return true;
}

function dateVal()
{
    var txtVendorInwardDt = document.getElementById('txtVendorInwardDt').value;
    var txtVendorInwardDt1 = txtVendorInwardDt.replace('-', ' ');
    var txtVendorInwardDt2 = txtVendorInwardDt1.replace('-', ' 20');
    var myDate = new Date(txtVendorInwardDt2);// application/vendor invoice date and should be greater than All

    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;
    var txtVendorInvoiceDate1 = txtVendorInvoiceDate.replace('-', ' ');
    var txtVendorInvoiceDate2 = txtVendorInvoiceDate1.replace('-', ' 20');
    var myDate1 = new Date(txtVendorInvoiceDate2); //Inward Date Greater than Inv To Date

    //var txtInvoiceFrmDt = document.getElementById('txtInvoiceFrmDt').value;
    //var txtInvoiceFrmDt1 = txtInvoiceFrmDt.replace('-', ' ');
    // var txtInvoiceFrmDt2 = txtInvoiceFrmDt1.replace('-', ' 20');
    // var myDate2 = new Date(txtInvoiceFrmDt2);// Invoice From Date Should be less than all

    //var txtInvoiceToDt = document.getElementById('txtInvoiceToDt').value;
    //var txtInvoiceToDt1 = txtInvoiceToDt.replace('-', ' ');
    //var txtInvoiceToDt2 = txtInvoiceToDt1.replace('-', ' 20');
    //var myDate3 = new Date(txtInvoiceToDt2); // Invoice To Date Should be less than all and Greater than From Date


    //if (myDate2>myDate3)
    //{ 
    // alert('Invoice From Date cannot be greater than Invoice To Date!')
    // return false;
    // }
    // if (!(myDate1>myDate3))
    // { 
    //   alert('MSEDCL Inward Date should be greater than Invoice To Date!')
    //  return false;
    //}

    if ((myDate < myDate1))
    {
        alert('VENDOR Inward Date should be greater than VENDOR Invoice Date!')
        return false;
    }

    return true;
}

function viewFile(id, option) {
    var AppId = document.getElementById("txtApplId").value;
    var view = document.getElementById("view1").value;
    var txtPONumber = document.getElementById("txtPONumber").value.trim();
//var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));

    var url = view + "&AppId=" + AppId
            + "&FId=" + id
            + "&txtPONumber=" + txtPONumber
            + "&Option=" + option;

    window.open(url, 'Attachment', 'toolbar=no,location=no,directories=no,status=no, menubar=no,scrollbars=yes,resizable=yes,width=1200,height=1200');

}

function removeFile(id, option, filename) {
    var AppId = document.getElementById("txtApplId").value;
    if (document.getElementById("txtVNum"))

    {
        var txtvendorId = document.getElementById("txtVNum").value;
        var index = document.getElementById("txtVNum").value.indexOf("-");
        if (index === -1) {
            var txtvendorId = document.getElementById("txtVNum").value;
        } else {
            var txtvendorId = document.getElementById("txtVNum").value;
            var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
            var vendor_name = document.getElementById("txtVNum").value;
            var vendor_name = vendor_name.substring(vendor_name.indexOf('-') + 1);
        }
    }
    var module_type = document.getElementById("Module").value;//module type to differentiate between proj id and po number
    if (module_type == 'PS') {
        var txtPONumber = document.getElementById("txtPROJNumber").value;
        var txtPONumber = txtPONumber.substring(0, txtPONumber.lastIndexOf("-"));
    } else {
        var txtPONumber = document.getElementById("txtPONumber").value.trim();
        //var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
    }

    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var de = jsonObj.AppId;
        }
    };
    var url = "ajax";
    var uiactionName = "postInvoiceFile";
    var params = "uiaction=" + uiactionName
            + "&AppId=" + AppId
            + "&FId=" + id
            + "&Option=" + option
            + "&FileName=" + filename
            + "&txtPONumber=" + txtPONumber
            + "&module_type=" + module_type
            + "&txtvendorId=" + txtvendorId
            + "&subAction=" + "delete"
            ;

    callAjax("POST", url, params, false, out.response);

    var uiActionName = document.getElementById("rem").value;
    var action = "delete";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
            + "&module_type=" + module_type
            + "&AppId=" + encodeURIComponent(AppId)
            + "&subAction=" + encodeURIComponent(action)
            + "&txtvendorId=" + encodeURIComponent(txtvendorId)
            ;

    postForm(url, params, "get");
}

function verifyFileUpload() {
    var valid = true;
var INVOICE_FILESIZE_LIMIT = document.getElementById('INVOICE_FILESIZE_LIMIT').value;
    if (document.getElementById('inpFile').value === "")

    {
        alert("Please upload all Files.");
        valid = false;
    }

    var file = inpFile.files[0];
    var fileName = file.name;
    var extension = fileName.substr(fileName.lastIndexOf('.') + 1).toLowerCase();
    var validExtensions = ["jpg", "jpeg", "png", "pdf"];
   // alert(file.size);
    var fileSizeInKb = Math.round(file.size / 1024);

    if (fileName.length > 0)
    {
        if (validExtensions.indexOf(extension) === -1) {
            alert('Invalid file Format. Only ' + validExtensions.join(', ') + ' are allowed.');
            valid = false;
        } else if (fileSizeInKb > INVOICE_FILESIZE_LIMIT) {
            alert(file.name + " has size " + fileSizeInKb + " KB. Please upload a file within 1MB.");
            valid = false;
        }
    }
    return valid;
}

function validFinalFile() {

    if (verifyFileUpload()) {
        document.getElementById('btnFile').disabled = false;
    } else {
        document.getElementById('btnFile').disabled = true;
    }
}
function getInvDetails() {//to fetch the details of Invoice amount and invoicable amount at the time of invoice creation.
    var module_type = document.getElementById("Module").value;
    if (module_type == 'PS')
    {
        var txtprojId = document.getElementById("txtPROJNumber").value;
        //    var txtprojId = txtprojId.substring(0, txtprojId.lastIndexOf("-"));
    } else {
        var txtPONumber = document.getElementById("txtPONumber").value;
        var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
    }
    if (document.getElementById("txtVNum"))

    {
        var txtvendorId = document.getElementById("txtVNum").value;
        var index = document.getElementById("txtVNum").value.indexOf("-");
        if (index === -1) {
            var txtvendorId = document.getElementById("txtVNum").value;
        } else {
            var txtvendorId = document.getElementById("txtVNum").value;
            var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
        }
    }

    var out = {
        response: function validation(info) {

            var jsonObj = JSON.parse(info);
            var total_inv_amt = jsonObj.total_inv_amt;
            var total_invoicable_amt = jsonObj.total_invoicable_amt;
            var n = new Number(total_inv_amt);
            var m = new Number(total_invoicable_amt);
            var myObj = {
                style: "currency",
                currency: "INR"
            }
            document.getElementById("txtinvamt").value = n.toLocaleString("en-IN", myObj);
            //document.getElementById('txtinvamt').innerHTML = total_inv_amt.toLocaleString('en-IN');
            document.getElementById("txtinvoicablamt").value = m.toLocaleString("en-IN", myObj);


        }
    };
    var url = "ajax";
    var uiactionName = "getInvDetails";
    var params = "uiaction=" + uiactionName
            + "&txtPONumber=" + txtPONumber
            + "&txtprojId=" + txtprojId
            + "&module_type=" + module_type
            + "&txtvendorId=" + txtvendorId
            ;

    callAjax("POST", url, params, false, out.response);
}

function  enablePOLineDetails() {
    var module_type = document.getElementById("Module").value;
    if (module_type == 'PM')
    {
        document.getElementById("myBtn").disabled = false;
    }

}

$(function () {

    $(document).on('change', '.search_checkbox', function () {//search_checkbox class of checkbox
        if ($(this).is(":checked")) {
            var out = {
                response: function validation(info) {
                    var jsonObj = JSON.parse(info);
                    var id = jsonObj.PlantDetailString;
                    $("#SubmitAtPlant").append(id);

                }
            };
            var url = "ajax";
            var txtPlantDesc = $(this).data('plant');
            var txtPlant = txtPlantDesc.substring(0, txtPlantDesc.indexOf("-")).trim();
            var uiactionName = "fetchPlantDetails";
            var params = "uiaction=" + uiactionName
                    + "&txtPlant=" + txtPlant
                    + "&txtPlantDesc=" + txtPlantDesc
                    ;
            callAjax("POST", url, params, false, out.response);

            //alert($(this).data("plant") );
            var exists = 0 !== $("#SubmitAtPlant option[value='" + $(this).data('plant') + "']").length;
            if (!exists)
            {
                //alert("PLANT"+$(this).data('plant'));
                var plnt = $(this).data('plant');
                $("#SubmitAtPlant").append("<option value='" + plnt + "'  data-purchasing='" + $(this).data('purchasing') + "'>" + $(this).data('plant') + "</option>");
            }
        } else {
            //  alert("UNCHECKED");
            curr_value = $(this).data("plant");
            count_check = 0;
            $("#SubmitAtPlant option[data-plant='" + $(this).data("plant") + "']").remove();
            $(".search_checkbox").each(function () {
                if ($(this).data("plant") === curr_value && $(this).is(":checked"))
                    count_check += 1;
            });
            //alert(count_check);
            if (count_check < 1)
                $("#SubmitAtPlant option[value='" + $(this).data("plant") + "']").remove();
        }
    });
    $(document).on('change', '#selectallcheck', function () {//selectallcheck id of checkbox of header of table
        if ($(this).is(":checked")) {
            $(".search_checkbox").prop("checked", true);

            $("#SubmitAtPlant").find('option').remove();

            $("#SubmitAtPlant").append('<option value="">- Select -</option>');
            var plnt1 = $('#txtPlant').val();
            $("#SubmitAtPlant").append("<option value='" + plnt1 + "' data-purchasing='" + $('#txtPurchasing_group').val() + "'>" + $('#txtPlant').val() + "</option>");
            $(".search_checkbox").each(function () {
                var out = {
                    response: function validation(info) {
                        var jsonObj = JSON.parse(info);
                        var id = jsonObj.PlantDetailString;
                        $("#SubmitAtPlant").append(id);

                    }
                };
                var url = "ajax";
                var txtPlantDesc = $(this).data('plant');
                var txtPlant = txtPlantDesc.substring(0, txtPlantDesc.indexOf("-")).trim();
                var uiactionName = "fetchPlantDetails";
                var params = "uiaction=" + uiactionName
                        + "&txtPlant=" + txtPlant
                        + "&txtPlantDesc=" + txtPlantDesc
                        ;
                callAjax("POST", url, params, false, out.response);
                var exists = 0 !== $("#SubmitAtPlant option[value='" + $(this).data('plant') + "']").length;
                if (!exists) {
                    var plnt = $(this).data('plant');
                    $("#SubmitAtPlant").append("<option value='" + plnt + "' data-purchasing='" + $(this).data('purchasing') + "'>" + $(this).data('plant') + "</option>");
                }
            });
        } else {
            $(".search_checkbox").prop("checked", false);
            $("#SubmitAtPlant").find('option').remove();
            $("#SubmitAtPlant").append('<option value="">- Select -</option>');
            var plnt = $('#txtPlant').val();
            $("#SubmitAtPlant").append("<option value='" + plnt + "' data-purchasing='" + $('#txtPurchasing_group').val() + "'>" + $('#txtPlant').val() + "</option>");
        }
    });

});
function  getPOLineDetails() {//open pop up window
    var x = document.getElementById("podetail");
    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var id = jsonObj.POLineDetailString;


            jQuery("#podetail").html(id);
            jQuery("#podetail").show();
            $('input[type="checkbox"][data-applid!=""]').prop('checked', true);//to check only those checkbox which are having appl id
            //to diffentiate between the one already selected
            $(".search_checkbox").each(function () {
                if ($(this).is(":checked")) {
                    var exists = 0 !== $("#forwardAtPlant option[value='" + $(this).data('plant') + "']").length;
                    if (!exists) {
                        var plnt = $(this).data('plant');
                        var purchasing = $(this).data('purchasing');

                        $("#forwardAtPlant").append("<option value='" + plnt + "' data-purchasing='" + purchasing + "'>" + $(this).data('plant') + "</option>");
                    }
                }
            });
        }
    };
    var module_type = document.getElementById("Module").value;
    if (module_type == 'PM') {
        var txtPONumber = document.getElementById("txtPONumber").value;
        var txtStatus = document.getElementById("txtStatus").value;
        var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
        if (document.getElementById("txtApplId"))
        {
            var txtApplId = document.getElementById('txtApplId').value;
        }
        var url = "ajax";
        var uiactionName = "viewPOLineDetails";
        var params = "uiaction=" + uiactionName
                + "&txtPONumber=" + txtPONumber
                + "&txtApplId=" + txtApplId
                + "&txtStatus=" + txtStatus
                ;
        callAjax("POST", url, params, false, out.response);
    }
}
function getProjectDetails() {//to fetch the details of poject and po numbers if po number selected is from project system
    var txtPONumber = document.getElementById("txtPONumber").value;
    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
    if (document.getElementById("txtVNum"))

    {
        var txtvendorId = document.getElementById("txtVNum").value;
        var index = document.getElementById("txtVNum").value.indexOf("-");
        if (index === -1) {
            var txtvendorId = document.getElementById("txtVNum").value;
        } else {
            var txtvendorId = document.getElementById("txtVNum").value;
            var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
        }
    }
    var out = {
        response: function validation(info) {



            var jsonObj = JSON.parse(info);
            var material_po = jsonObj.MATERIAL_PO;
            var centages_po = jsonObj.CENTAGES_PO;
            var service_po = jsonObj.SERVICE_PO;
            var project_id = jsonObj.PROJECT_CODE;
            document.getElementById("txtMaterialPo").value = material_po;
            document.getElementById("txtCentagesPo").value = centages_po;
            document.getElementById("txtServicePo").value = service_po;
            document.getElementById("txtPROJNumber").value = project_id;
        }
    };
    var url = "ajax";
    var uiactionName = "getProjectDetails";
    var params = "uiaction=" + uiactionName
            + "&txtPONumber=" + txtPONumber
            + "&txtvendorId=" + txtvendorId
            ;

    callAjax("POST", url, params, false, out.response);
}
function getPOListDetails() {
    if (document.getElementById("txtVNum")) {
        var vendor_number = document.getElementById("txtVNum").value;
        var vendor_number = vendor_number.substring(0, vendor_number.indexOf("-")).trim();
        var out = {
            response: function validation(info) {
                var jsonObj = JSON.parse(info);
                var id1 = jsonObj.POListDetailString;
                var id2 = jsonObj.POListWithTypeString;


                document.getElementById("poOptions").value = id1;

                document.getElementById("poOptionsWithType").value = id2;

            }
        };
        var url = "ajax";
        var uiactionName = "getPOListDetails";
        var params = "uiaction=" + uiactionName
                + "&txtVendorNumber=" + vendor_number

                ;

        callAjax("POST", url, params, false, out.response);
    }
}
function getPODetails() {//to fetch the details of PO Amount and po balance amount at the time of invoice creation.
    var module_type = document.getElementById("Module").value;
    if (module_type == 'PS')
    {
        getProjectDetails();
        var txtprojId = document.getElementById("txtPROJNumber").value;
        //  var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
    } else {
        var txtPONumber = document.getElementById("txtPONumber").value;
        var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
    }
    if (document.getElementById("txtVNum"))

    {
        var txtvendorId = document.getElementById("txtVNum").value;
        var index = document.getElementById("txtVNum").value.indexOf("-");
        if (index === -1) {
            var txtvendorId = document.getElementById("txtVNum").value;
        } else {
            var txtvendorId = document.getElementById("txtVNum").value;
            var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
        }
    }
    var out = {
        response: function validation(info) {



            var jsonObj = JSON.parse(info);
            var po_amt = jsonObj.po_amt;
            var bal_amt = jsonObj.bal_amt;
            var n = new Number(po_amt);
            var m = new Number(bal_amt);
            var myObj = {
                style: "currency",
                currency: "INR"
            }
            document.getElementById("txtpoamt").value = n.toLocaleString("en-IN", myObj);
            document.getElementById("txtpobal").value = m.toLocaleString("en-IN", myObj);
        }
    };
    var url = "ajax";
    var uiactionName = "getPODetails";
    var params = "uiaction=" + uiactionName
            + "&txtPONumber=" + txtPONumber
            + "&txtprojId=" + txtprojId
            + "&module_type=" + module_type
            + "&txtvendorId=" + txtvendorId
            ;

    callAjax("POST", url, params, false, out.response);
}
function getlocationpurchasing() {//to fetch details of purchasing group
    var module_type = document.getElementById("Module").value;
    var txtPONumber = document.getElementById("txtPONumber").value;
    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
    var out = {
        response: function validation(info) {

            var jsonObj = JSON.parse(info);
            var plant = jsonObj.plant;


            var purchasing_group = jsonObj.purchasing_group;
            document.getElementById("txtOnloadPurchasing_group").value = purchasing_group;
        }
    };
    var url = "ajax";
    var uiactionName = "getPOLocation";
    var params = "uiaction=" + uiactionName
            + "&txtPONumber=" + txtPONumber
            + "&module_type=" + module_type
            ;

    callAjax("POST", url, params, false, out.response);
}

function getlocation() {//to fetch details of location from po number/proj id

    var module_type = document.getElementById("Module").value;
    if (module_type == 'PS')
    {
        var txtprojId = document.getElementById("txtPROJNumber").value;
        //var txtprojId = txtprojId.substring(0, txtprojId.lastIndexOf("-"));
    } else {
        var txtPONumber = document.getElementById("txtPONumber").value;
        var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
    }
    if (document.getElementById("txtVNum"))

    {
        var txtvendorId = document.getElementById("txtVNum").value;
        var index = document.getElementById("txtVNum").value.indexOf("-");
        if (index === -1) {
            var txtvendorId = document.getElementById("txtVNum").value;
        } else {
            var txtvendorId = document.getElementById("txtVNum").value;
            var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
        }
    }
    var out = {
        response: function validation(info) {

            var jsonObj = JSON.parse(info);
            var plant = jsonObj.plant;
            var plant_desc = jsonObj.plant.concat('-' + jsonObj.plant_desc);
            document.getElementById("txtPlant").value = plant_desc;

            $("#SubmitAtPlant").find('option').remove();
            var plnt = $('#txtPlant').val();

            if (module_type == 'PS') {
                //  $("#SubmitAtPlant").append("<option value='"+plnt+"'>"+$('#txtPlant').val()+"</option>");
                /*$("#SubmitAtPlant").append("<option value='HO01-CORPORATE OFFICE'>HO01-CORPORATE OFFICE</option>");  
                 var circle_desc=jsonObj.circle_code.concat('-'+jsonObj.circle);
                 var zone_desc=jsonObj.zone_code.concat('-'+jsonObj.zone); 
                 $("#SubmitAtPlant").append("<option value='"+circle_desc+"'>"+circle_desc+"</option>");
                 $("#SubmitAtPlant").append("<option value='"+zone_desc+"'>"+zone_desc+"</option>");*/
                var purchasing_group = jsonObj.purchasing_group;
                document.getElementById("txtPurchasing_group").value = purchasing_group;
                $("#SubmitAtPlant").append("<option value='" + plnt + "' data-purchasing='" + purchasing_group + "'>" + $('#txtPlant').val() + "</option>");

            } else {
                var purchasing_group = jsonObj.purchasing_group;
                document.getElementById("txtPurchasing_group").value = purchasing_group;
                $("#SubmitAtPlant").append("<option value='" + plnt + "' data-purchasing='" + purchasing_group + "'>" + $('#txtPlant').val() + "</option>");
            }

            /* if(plant=="HO01")
             {
             if(document.getElementById('module-div1')){
             document.getElementById('module-div1').style.display='block';}
             if(document.getElementById('txtModule')){
             document.getElementById('txtModule').style.display='block';}
             
             }
             else
             {if(document.getElementById('module-div1')){
             document.getElementById('module-div1').style.display='none';}
             if(document.getElementById('txtModule')){
             document.getElementById('txtModule').style.display='none';}
             
             }*/
        }
    };
    var url = "ajax";
    var uiactionName = "getPOLocation";
    var params = "uiaction=" + uiactionName
            + "&txtPONumber=" + txtPONumber
            + "&txtprojId=" + txtprojId
            + "&module_type=" + module_type
            + "&txtvendorId=" + txtvendorId
            ;

    callAjax("POST", url, params, false, out.response);
}

function getPROJSearchList()
{
    var type = "projList";
    var projOptions = JSON.parse(document.getElementById("projOptions").value);
    autocomplete_fillingHiddenValue(document.getElementById("txtPROJNumber"), projOptions, type);
}
function getPOSearchList() {
    var type = "poList";
    var poOptions = JSON.parse(document.getElementById("poOptions").value);
    var poOptionsWithType = JSON.parse(document.getElementById("poOptionsWithType").value);
    autocomplete_fillingHiddenValue(document.getElementById("txtPONumber"), poOptions, type);
}
function   getVendorSearchList() {
    var type = "vendorList";
    var vendorOptions = JSON.parse(document.getElementById("VNumOptions").value);

    autocomplete_fillingHiddenValue(document.getElementById("txtVNum"), vendorOptions, type);
}

function autocomplete_fillingHiddenValue(inp, arr, type) {
    /*the autocomplete function takes two arguments,
     the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    // var type = type;

    inp.addEventListener("input", function (e) {

        var a, b, i, val = this.value;
        /*close any already open lists of autocompleted values*/
        closeAllLists();

        if (!val) {
            return false;
        }
        currentFocus = -1;
        /*create a DIV element that will contain the items (values):*/
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");

//      var elmnt = document.getElementById("txtPONumberautocomplete-list");
//       elmnt.scrollIntoView(true); // Top

        /*append the DIV element as a child of the autocomplete container:*/
        this.parentNode.appendChild(a);
        /*for each item in the array...*/



        for (i = 0; i < arr.length; i++) {
//           alert("arr[i].toUpperCase() : "+arr[i].toUpperCase() );
//       alert("val.toUpperCase() :"+val.toUpperCase()) ;
            /*check if the item starts with the same letters as the text field value:*/
            //if (arr[i].substr(0, arr[i].length).toUpperCase() == val.toUpperCase()) { 
            if (arr[i].toUpperCase().indexOf(val.toUpperCase()) > -1) {


                /*create a DIV element for each matching element:*/
                b = document.createElement("DIV");
                /*make the matching letters bold:*/
                // b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
//          b.innerHTML =  arr[i].substr(0, val.length);         
//          b.innerHTML += arr[i].substr(val.length); 
                /*make the matching letters bold:*/

                // b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                //  b.innerHTML += arr[i].substr(val.length);
                b.innerHTML = arr[i];
                /*insert a input field that will hold the current array item's value:*/
                b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";

                //code to make bold of input ends
                /*execute a function when someone clicks on the item value (DIV element):*/
                b.addEventListener("click", function (e) {
                    var poOptions = JSON.parse(document.getElementById("poOptions").value);
                    var poOptionsWithType = JSON.parse(document.getElementById("poOptionsWithType").value);
                    /*insert the value for the autocomplete text field:*/
                    var selectedValue = this.getElementsByTagName("input")[0].value;
                    var indexOf = poOptions.indexOf(selectedValue);

                    if (indexOf > -1) {
                        var typeValue = poOptionsWithType[indexOf]
                        var type = typeValue.split("$$$$")[1];
                        document.getElementById("Module").value = type;
                        inp.value = this.getElementsByTagName("input")[0].value;
                        document.getElementById("selectedTxtPONumber").value = inp.value;
                        getPODetails();
                        getInvDetails();
                        getlocation();//fetch location
                        enablePOLineDetails();
                        SelectModuleButton();
                    }
                    inp.value = this.getElementsByTagName("input")[0].value;
                    document.getElementById("selectedTxtPONumber").value = inp.value;

                    getPOListDetails();
                    /*close the list of autocompleted values,
                     (or any other open lists of autocompleted values:*/
                    closeAllLists();
                });

                a.appendChild(b);

            }
        }
    });
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function (e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x)
            x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
             increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
             decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x)
                    x[currentFocus].click();
            }
        }
    });
    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x)
            return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length)
            currentFocus = 0;
        if (currentFocus < 0)
            currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }
    function removeActive(x) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }
    function closeAllLists(elmnt) {
        /*close all autocomplete lists in the document,
         except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }
    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
        // alert("type :- " + type);
//    if(type == 'poList'){
//    getInvoiceNum();
//    }

    });

}

function autocomplete(inp, arr, type) {
    /*the autocomplete function takes two arguments,
     the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    // var type = type;

    inp.addEventListener("input", function (e) {

        var a, b, i, val = this.value;
        /*close any already open lists of autocompleted values*/
        closeAllLists();
        if (!val) {
            return false;
        }
        currentFocus = -1;
        /*create a DIV element that will contain the items (values):*/
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");

//      var elmnt = document.getElementById("txtPONumberautocomplete-list");
//       elmnt.scrollIntoView(true); // Top

        /*append the DIV element as a child of the autocomplete container:*/
        this.parentNode.appendChild(a);
        /*for each item in the array...*/



        for (i = 0; i < arr.length; i++) {
//           alert("arr[i].toUpperCase() : "+arr[i].toUpperCase() );
//       alert("val.toUpperCase() :"+val.toUpperCase()) ;
            /*check if the item starts with the same letters as the text field value:*/
            //if (arr[i].substr(0, arr[i].length).toUpperCase() == val.toUpperCase()) { 
            if (arr[i].toUpperCase().indexOf(val.toUpperCase()) > -1) {


                /*create a DIV element for each matching element:*/
                b = document.createElement("DIV");
                /*make the matching letters bold:*/
                // b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
//          b.innerHTML =  arr[i].substr(0, val.length);         
//          b.innerHTML += arr[i].substr(val.length); 
                /*make the matching letters bold:*/

                // b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                //  b.innerHTML += arr[i].substr(val.length);
                b.innerHTML = arr[i];
                /*insert a input field that will hold the current array item's value:*/
                b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";

                //code to make bold of input ends
                /*execute a function when someone clicks on the item value (DIV element):*/
                b.addEventListener("click", function (e) {
                    /*insert the value for the autocomplete text field:*/
                    inp.value = this.getElementsByTagName("input")[0].value;
                    //alert("test"+inp.value)
                    /*close the list of autocompleted values,
                     (or any other open lists of autocompleted values:*/
                    closeAllLists();
                });

                a.appendChild(b);

            }
        }
    });
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function (e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x)
            x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
             increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
             decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x)
                    x[currentFocus].click();
            }
        }
    });
    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x)
            return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length)
            currentFocus = 0;
        if (currentFocus < 0)
            currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }
    function removeActive(x) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }
    function closeAllLists(elmnt) {
        /*close all autocomplete lists in the document,
         except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }
    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
        // alert("type :- " + type);
//    if(type == 'poList'){
//    getInvoiceNum();
//    }

    });
}
$(function () {
    // if {
    $("#txtVendorInvoiceDate").datepicker({
        changeMonth: true,
        changeYear: true,
        //showOn: 'button',
        //buttonImage: 'images/calendar.png',
        //buttonImageOnly: true,
        //yearRange: '2016:2020',
        dateFormat: 'dd-M-yy',
        minDate: '-3Y',
        maxDate: '+3Y'
    });
    $("#txtVendorInwardDt").datepicker({
        changeMonth: true,
        changeYear: true,
        //showOn: 'button',
        //buttonImage: 'images/calendar.png',
        //buttonImageOnly: true,
        //yearRange: '2016:2020',
        dateFormat: 'dd-M-yy',
        minDate: '-3Y',
        maxDate: '+3Y'
    });
});

function showOrHideRTDetails() {
    var txtInvoiceType = document.getElementById("selecttxtInvoiceType").value;
    if (txtInvoiceType === 'Retention Claim Charges') {
        //jQuery("#table_content").hide();
        jQuery("#retention_table_content").show();
        jQuery("#retention_inward_table_content").show();
        jQuery("#retentionRadioButton").show();
        document.getElementById("txtInvoiceAmt").readOnly = true;
        var module_type = document.getElementById("Module").value;
        if (module_type == 'PS')
        {
            var txtprojId = document.getElementById("txtPROJNumber").value;
            //var txtprojId = txtprojId.substring(0, txtprojId.lastIndexOf("-"));
        }

        var out = {
            response: function validation(info) {

                var jsonObj = JSON.parse(info);
                var retentionDetails = jsonObj.retentionDetails;
                if(retentionDetails!== 'undefined' && retentionDetails!==""){
                    jQuery("#retention_table_content").html(retentionDetails);
                }else{
                     jQuery("#retention_table_content").html("<center><h4>No retention record found to display.</h4></center>");
//                    alert("No retention record to display.");
//                    location.reload();
                }
//jQuery("#retention_table_content").show();
            }
        };
        var url = "ajax";
        var uiactionName = "getRetentionDetails";
        var params = "uiaction=" + uiactionName
                + "&txtprojId=" + txtprojId
                + "&module_type=" + module_type
                ;

        callAjax("POST", url, params, false, out.response);

    } else {
        //jQuery("#table_content").show();
        jQuery("#retention_table_content").hide();
        jQuery("#retention_inward_table_content").hide();
        jQuery("#retentionRadioButton").hide();
        document.getElementById("txtInvoiceAmt").readOnly = false;
        document.getElementById("txtInvoiceAmt").value = '';
    }
}
function RetentionApplDtlvalidation() {
    if (document.getElementById("Module").value === 'PS') {
    var retentionCheckBoxValue="";
    var txtInvoiceType = document.getElementById("selecttxtInvoiceType").value;
    if (txtInvoiceType === 'Retention Claim Charges') {
        retentionCheckBoxValue = "";
        $(".retention_checkbox").each(function () {
            if ($(this).is(":checked"))
                retentionCheckBoxValue += ($(this).val()) + "#";
        });
        if(retentionCheckBoxValue===""){
            alert("Please select invoice to be submitted.");
            return false;
        }
    }
}
    return true;
}

function getInvoiceAmount(retentionAmount,checkbox){
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    if(txtInvoiceAmt===""){
        document.getElementById('txtInvoiceAmt').value=0;
    }
    if(checkbox.checked === true){
        txtInvoiceAmt=Number(txtInvoiceAmt)+Number(retentionAmount);
        document.getElementById('txtInvoiceAmt').value=txtInvoiceAmt.toFixed(2);
    }else{
        txtInvoiceAmt=Number(txtInvoiceAmt)-Number(retentionAmount);
        document.getElementById('txtInvoiceAmt').value=txtInvoiceAmt.toFixed(2);
    }
    
}

function showFullRetentionClaimDetails(){
    var txtInvoiceType = document.getElementById("selecttxtInvoiceType").value;
    var txtprojId = document.getElementById("txtPROJNumber").value;
    var module_type = document.getElementById("Module").value;
    document.getElementById('txtInvoiceAmt').value=0;
    var out = {
            response: function validation(info) {

                var jsonObj = JSON.parse(info);
                var retentionDetails = jsonObj.retentionDetails;
                if(retentionDetails!== 'undefined' && retentionDetails!==""){
                    jQuery("#retention_table_content").html(retentionDetails);
                }else{
                    alert("No retention invoice found.");
                    jQuery("#retention_table_content").html("");
                    location.reload();
                }
//jQuery("#retention_table_content").show();
            }
        };
        var url = "ajax";
        var uiactionName = "getRetentionDetails";
        var params = "uiaction=" + uiactionName
                + "&txtprojId=" + txtprojId
                + "&module_type=" + module_type
                ;

        callAjax("POST", url, params, false, out.response);
}

function showPartialRetentionClaimDetails(){
    var txtInvoiceType = document.getElementById("selecttxtInvoiceType").value;
    var txtprojId = document.getElementById("txtPROJNumber").value;
    var module_type = document.getElementById("Module").value;
    document.getElementById('txtInvoiceAmt').value=0;
    var out = {
            response: function validation(info) {

                var jsonObj = JSON.parse(info);
                var retentionDetails = jsonObj.retentionDetails;
                if(retentionDetails!== 'undefined' && retentionDetails!==""){
                    jQuery("#retention_table_content").html(retentionDetails);
                }else{
                    alert("No balance retention invoice found.");
                    jQuery("#retention_table_content").html("");
//                    location.reload();

            document.getElementById("rad_Retention_100").checked = true;
            showFullRetentionClaimDetails();
                }
//jQuery("#retention_table_content").show();
            }
        };
        var url = "ajax";
        var uiactionName = "getPartialRetentionDetails";
        var params = "uiaction=" + uiactionName
                + "&txtprojId=" + txtprojId
                + "&module_type=" + module_type
                ;

        callAjax("POST", url, params, false, out.response);
}

function selectAllRetentionInvoice(source,colIndex) {
            var checkboxes = document.getElementsByName('retention_checkbox');
            var amount=0;
            for(var i=0, n=checkboxes.length;i<n;i++) {
                checkboxes[i].checked = source.checked;
                amount=amount+ Number($('#retention_table_content tr:nth-child('+(i+1)+') td:nth-child('+colIndex+')').text());
                
            }
            if(source.checked === true){
                document.getElementById('txtInvoiceAmt').value=amount.toFixed(2);
            }else{
                document.getElementById('txtInvoiceAmt').value=0;
            }
        
}