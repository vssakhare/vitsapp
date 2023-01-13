/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function saveLegalInvoiceButton() {
    if (LegalApplDtlvalidation() && LegalNumericVal() && LegalDateVal()) {
        saveLegalInvoice('save');
        var userType = document.getElementById("userType").value;
        var uiActionName = document.getElementById("rem").value;
        var AppId = document.getElementById("txtApplicationId").value;
        var action = "save";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
                + "&AppId=" + encodeURIComponent(AppId)
                + "&subAction=" + encodeURIComponent(action)
                ;

        postForm(url, params, "get");
    }
}
function LegalApplDtlvalidation() {
    var WithOrWithoutCourtCase = $('input[name="rad_courtCase"]:checked').val();
    var isWithCourtCaseFlag = "";
    if (WithOrWithoutCourtCase === 'withCourtCase') {
        isWithCourtCaseFlag = "Y";
    } else if (WithOrWithoutCourtCase === 'withoutCourtCase') {
        isWithCourtCaseFlag = "N";
    }
    var txtCourtCaseNo = document.getElementById('txtCourtCaseNo').value;
    var txtInvoiceAmount = document.getElementById('txtInvoiceAmt').value;
    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;
    var txtInvSubmitDt = document.getElementById('txtInvSubmitDt').value;
    var txtInvoiceNum = document.getElementById('txtInvoiceNum').value;

    if (isWithCourtCaseFlag === 'Y') {
        if (txtCourtCaseNo === null || txtCourtCaseNo === "") {
            alert("Please select court case no.");
            return false;
        }
    } else if (isWithCourtCaseFlag === 'N') {
        var txtRegion = document.getElementById('txtRegion').value;
        var txtCorporateOffice = document.getElementById('txtCorporateOffice').value;
        var txtCorpSection = document.getElementById('txtCorpSection').value;
        if ((txtRegion === null || txtRegion === "" || txtRegion === "Select") && (txtCorporateOffice === null || txtCorporateOffice === "" || txtCorporateOffice === "Select")) {
            alert("Please select Region or Corporate office.");
            return false;
        }
        
        if (!(txtCorporateOffice === null || txtCorporateOffice === "" || txtCorporateOffice === "Select")&&(txtCorpSection === null || txtCorpSection === "" || txtCorpSection === "Select")) {
            alert("Please select Corporate office section.");
            return false;
        }
        
        txtInvoiceNum = document.getElementById('txtInvoiceNumWithoutCase').value;

        var usertype = document.getElementById('userType').value;
        if (usertype === 'Emp') {
            var caseRefNoWithout = document.getElementById('txtCaseRefNoWithout').value;
            if (caseRefNoWithout === null || caseRefNoWithout === "") {
                alert("Please select case ref no.");
                return false;
            }
        }
        
        var feeType = document.getElementById('feeTypeSelectWo').value;
            if (feeType === null || feeType === "" || feeType === "Select") {
                alert("Please select fee type");
                return false;
        }
    }
    if (txtInvoiceAmount === null || txtInvoiceAmount === "") {
        alert("Please enter invoice amount.");
        return false;
    }
    if (txtVendorInvoiceDate === null || txtVendorInvoiceDate === "") {
        alert("Please select invoice date.");
        return false;
    }
    if (txtInvSubmitDt === null || txtInvSubmitDt === "") {
        alert("Please select invoice submit date.");
        return false;
    }
    if (txtInvoiceNum === null || txtInvoiceNum === "") {
        alert("Please enter invoice number.");
        return false;
    }
  //  var regEx = /^[0-9a-zA-Z]+$/;
   var regEx =/^[0-9a-zA-Z/\s-]+$/;
   //   /^[0-9a-zA-Z/\s-]+$/;
 //    /^(?=[a-z\d]*\/)(?=\/*[a-z\d])[a-z\d\/]+$/;
    if (txtInvoiceNum.match(regEx))
    {
        return true;
    } else
    {
        alert("Please enter only alphanumeric value for invoice number.");
        return false;
    }
    return true;
}
function LegalNumericVal() {
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    // var txtInvoiceAmt = txtInvoiceAmt.replace(/[^\d\.\-]/g, '');
    //var regexp = /^\d+\.\d{1,2}$/;
    // alert(txtInvoiceAmt);
    // txtInvoiceAmt.replace(/\s/g, "");
    if (!(/^[-+]?\d*\.?\d*$/.test(txtInvoiceAmt))) {
        alert("Invoice Amount should be numerical and upto two decimal places Only!!!");
        return false;
    }
    return true;
}
function LegalDateVal() {
    var txtInvSubmitDt = document.getElementById('txtInvSubmitDt').value;
    var txtInvSubmitDt1 = txtInvSubmitDt.replace('-', ' ');
    var txtInvSubmitDt2 = txtInvSubmitDt1.replace('-', ' ');
    var myDate = new Date(txtInvSubmitDt2);// application/vendor invoice date and should be greater than All
    
    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;
    var txtVendorInvoiceDate1 = txtVendorInvoiceDate.replace('-', ' ');
    var txtVendorInvoiceDate2 = txtVendorInvoiceDate1.replace('-', ' ');
    var myDate1 = new Date(txtVendorInvoiceDate2); //Inward Date Greater than Inv To Date

    var txtVendorInwardDt = document.getElementById('txtVendorInwardDt').value;
    var txtVendorInwardDt1 = txtVendorInwardDt.replace('-', ' ');
    var txtVendorInwardDt2 = txtVendorInwardDt.replace('-', ' ');
    var myDate2 = new Date(txtVendorInwardDt2);// Invoice From Date Should be less than all

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

    var today= new Date();    
    
    if ((myDate < myDate1))
    {
        alert('Invoice Received Date should be greater than Invoice Date!');
        return false;
    }
    alert((today));alert(myDate);alert(myDate1);
    if ((myDate > today || today < myDate1))
    {        
        alert('Invoice Date or Inward Date cannot be a future date!');
        return false;
    }
    return true;
}

function saveLegalInvoice(action) {alert("saving process...");
//    var rejFlag=document.getElementById("rejFlag").value;
    var WithOrWithoutCourtCase = $('input[name="rad_courtCase"]:checked').val();
    var isWithCourtCaseFlag = "";
    var region = "";
    var zone = "";
    var circle = "";
    var division = "";
    var subdiv = "";
    var corporateOffice = "";
    var corpSection = "";
    var feeType = "";    
       
    if (WithOrWithoutCourtCase === 'withCourtCase') {
        isWithCourtCaseFlag = "Y";
    } else if (WithOrWithoutCourtCase === 'withoutCourtCase') {
        isWithCourtCaseFlag = "N";
        var regionSelect = document.getElementById("txtRegion");
        region = regionSelect.options[regionSelect.selectedIndex].text;
        if(region==='Select'){
            region="";
        }
        var zoneSelect = document.getElementById("txtZone");
        zone = zoneSelect.options[zoneSelect.selectedIndex].text;
        if(zone==='Select'){
            zone="";
        }
        var circleSelect = document.getElementById("txtCircle");
        circle = circleSelect.options[circleSelect.selectedIndex].text;
        if(circle==='Select'){
            circle="";
        }
        var divisionSelect = document.getElementById("txtDivision");
        division = divisionSelect.options[divisionSelect.selectedIndex].text;
        if(division==='Select'){
            division="";
        }
        var subDivisionSelect = document.getElementById("txtSubDivision");
        subdiv = subDivisionSelect.options[subDivisionSelect.selectedIndex].text;
        if(subdiv==='Select'){
            subdiv="";
        }
        var coSelect = document.getElementById("txtCorporateOffice");
        corporateOffice = coSelect.options[coSelect.selectedIndex].text;
        if(corporateOffice==='Select'){
            corporateOffice="";
        }
        var coSection = document.getElementById("txtCorpSection");
        corpSection = coSection.options[coSection.selectedIndex].text;
        if(corpSection==='Select'){
            corpSection="";
        }
        var feeTypeSelect = document.getElementById("feeTypeSelectWo");
        feeType = feeTypeSelect.options[feeTypeSelect.selectedIndex].text;
        if(feeType==='Select'){
            feeType="";
        }
    }
    var txtVendorName = document.getElementById("txtVendorName").value;
    var showinwflag = document.getElementById("showinwflag").value;
    var status = document.getElementById("status").value;
    var txtApplicationId = document.getElementById('txtApplicationId').value;
//    var module_type = document.getElementById("Module").value;//module type to differentiate between proj id and po number
//    var ModuleFlag = document.getElementById("ModuleFlag").value;
//    if (module_type == 'PS')
//    {
//        var txtInvoiceType = document.getElementById("selecttxtInvoiceType").value;
//    }


    var txtVendorCode = document.getElementById("txtVendorCode").value;
//    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
    var txtCourtCaseNo = document.getElementById("txtCourtCaseNo").value;
    var txtCaseRefNo = document.getElementById("txtCaseRefNo").value;
    var txtCourtName = document.getElementById("txtCourtName").value;
    var txtCaseDescription = document.getElementById("txtCaseDescription").value;
    var txtDealingOffice = document.getElementById("txtDealingOffice").value;
    var txtPartyNames = document.getElementById("txtPartyNames").value;
    var txtVsPartyNames = document.getElementById("txtVsPartyNames").value;
    var txtCaseType = document.getElementById("txtCaseType").value;
//    var SubmitAtDesc = SubmitAtPlant.substring(SubmitAtPlant.indexOf("-") + 1);
//    var SubmitAtPlant = SubmitAtPlant.substring(0, SubmitAtPlant.indexOf("-"));
    var txtInvoiceNum = document.getElementById('txtInvoiceNum').value;

    var txtVendorInwardDt = document.getElementById('txtVendorInwardDt').value;
    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    var txtInvoiceAmt = txtInvoiceAmt.replace(/[^\d\.\-]/g, '');
    var selectedOffieCode = document.getElementById('selectedOffieCode').value;
    var txtInvSubmitDt = document.getElementById('txtInvSubmitDt').value;
    var txtStatus = document.getElementById("txtStatus").value;
    
    var txtFeeType ="";
    
    if (WithOrWithoutCourtCase === 'withCourtCase') {
        var sel = document.getElementById("feeTypeSelect");
        txtFeeType = sel.options[sel.selectedIndex].text;
    } else if (WithOrWithoutCourtCase === 'withoutCourtCase') {
        //if (sel === null && sel === ''){
        var sel = document.getElementById("feeTypeSelectWo");
        txtFeeType = sel.options[sel.selectedIndex].text;
    } else {var sel = document.getElementById("feeTypeSelect");txtFeeType = sel.options[sel.selectedIndex].text;}
    
    if (isWithCourtCaseFlag === 'N') {
        
        if (txtDealingOffice === '')
        { txtDealingOffice = document.getElementById('txtDealingOfficewithoutCaseNo').value;}
        txtInvoiceNum = document.getElementById('txtInvoiceNumWithoutCase').value;
        var usertype = document.getElementById('userType').value;
        if (usertype === 'Emp') {
            txtCourtCaseNo = document.getElementById("txtCourtCaseNoWithout").value;
            alert(txtCourtCaseNo);
            txtCaseRefNo = document.getElementById("txtCaseRefNoWithout").value;
            txtCourtName = document.getElementById("txtCourtNameWithout").value;
            txtCaseDescription = document.getElementById("txtCaseDescriptionWithout").value;
            txtDealingOffice = document.getElementById("txtDealingOfficeWithout").value;
            txtPartyNames = document.getElementById("txtPartyNamesWithout").value;
        }
    }
//    var txtFeeType=$('#feeTypeSelect').val();
    if (action === "return") {
        if (document.getElementById("txtReason").value === "other")
        {
            var txtReason = document.getElementById("txtReason1").value;
        } else
        {
            var txtReason = document.getElementById("txtReason").value;
        }
    }
//    var txtPlant = document.getElementById('txtPlant').value;
//    var txtOnloadPurchasing_group = document.getElementById('txtOnloadPurchasing_group').value;
    // var Purchasing_group = document.getElementById('txtPurchasing_group').value;
    // var element = document.getElementById('SubmitAtPlant');
    /* if (action == "save" || action == "submit") {
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
    var txtInwardNum = "";
    var txtInwardDt = "";
    if (action === "return" || action === "approve") {

        txtInwardNum = document.getElementById('txtInwardNum').value;

        txtInwardDt = document.getElementById('txtInwardDt').value;
    } else
    {
        txtInwardNum = "";

    }
//    if (rejFlag === "Y") {
//        var txtResubmitDt = document.getElementById('txtVRDate').value;
//    }
//    if (action === "reject") {
//        if (document.getElementById("txtReason").value === "other")
//        {
//            var txtReason = document.getElementById("txtReason1").value;
//        } else
//        {
//            var txtReason = document.getElementById("txtReason").value;
//        }
//    }
    if (action === "forward") {
        var forwardAtPlant = document.getElementById("forwardAtPlant").value;
        var ForwardToDesc = forwardAtPlant.substring(forwardAtPlant.indexOf("-") + 1);
        var ForwardToPlant = forwardAtPlant.substring(0, forwardAtPlant.indexOf("-"));

    }
    var mobile = document.getElementById("mobileNO").value;
    var email = document.getElementById("emailID").value;
//    if (action === "reject" || action === "approve")
//    {
//        var vendor_number = document.getElementById("txtVNum").value;
//        var vendor_name = document.getElementById("txtVName").value;
//    } else if (document.getElementById("txtVNum"))
//
//    {
//        var txtvendorId = document.getElementById("txtVNum").value;
//        var index = document.getElementById("txtVNum").value.indexOf("-");
//        if (index === -1) {
//            var txtvendorId = document.getElementById("txtVNum").value;
//        } else {
//            var txtvendorId = document.getElementById("txtVNum").value;
//            var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
//            var vendor_name = document.getElementById("txtVNum").value;
//            var vendor_name = vendor_name.substring(vendor_name.indexOf('-') + 1);
//        }
//    }
    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var id = jsonObj.AppId;
            document.getElementById("txtApplicationId").value = id;
            document.getElementById("txtStatus").value = jsonObj.status;
            var id2 = jsonObj.Message1;
            $('#ButtonSubmit').css('display', 'block');
            $('#ButtonSave').hide();
            window.alert(id2);

        }
    };
   
    var url = "ajax";
    var uiactionName = "postLegalApplicationForm";
    var params = "uiaction=" + uiactionName
            + "&txtApplicationId=" + encodeURIComponent(txtApplicationId)
            + "&txtVendorName=" + encodeURIComponent(txtVendorName)
            + "&txtVendorCode=" + encodeURIComponent(txtVendorCode)
            + "&txtCourtCaseNo=" + encodeURIComponent(txtCourtCaseNo)
            + "&txtCaseRefNo=" + encodeURIComponent(txtCaseRefNo)
            + "&txtCourtName=" + encodeURIComponent(txtCourtName)
            + "&txtInvoiceNum=" + encodeURIComponent(txtInvoiceNum)
            + "&txtVendorInwardDt=" + encodeURIComponent(txtVendorInwardDt)
            + "&txtInvoiceAmt=" + encodeURIComponent(txtInvoiceAmt)
            + "&txtVendorInvoiceDate=" + encodeURIComponent(txtVendorInvoiceDate)
            + "&txtInvSubmitDt=" + encodeURIComponent(txtInvSubmitDt)
            + "&txtStatus=" + encodeURIComponent(txtStatus)
            + "&txtDealingOffice=" + encodeURIComponent(txtDealingOffice)
            + "&txtPartyNames=" + encodeURIComponent(txtPartyNames)
            + "&txtCaseDescription=" + encodeURIComponent(txtCaseDescription)
            + "&txtVsPartyNames=" + encodeURIComponent(txtVsPartyNames)
            + "&txtCaseType=" + encodeURIComponent(txtCaseType)
            + "&selectedOffieCode=" + encodeURIComponent(selectedOffieCode)
            + "&txtFeeType=" + encodeURIComponent(txtFeeType)
            + "&txtReason=" + encodeURIComponent(txtReason)
            + "&isWithCourtCaseFlag=" + encodeURIComponent(isWithCourtCaseFlag)
            + "&txtInwardNum=" + encodeURIComponent(txtInwardNum)
            + "&txtInwardDt=" + encodeURIComponent(txtInwardDt)
            + "&mobile=" + encodeURIComponent(mobile)
            + "&email=" + encodeURIComponent(email)
            + "&region=" + encodeURIComponent(region)
            + "&zone=" + encodeURIComponent(zone)
            + "&circle=" + encodeURIComponent(circle)
            + "&division=" + encodeURIComponent(division)
            + "&subDivision=" + encodeURIComponent(subdiv)
            + "&corporateOffice=" + encodeURIComponent(corporateOffice)
            + "&corpSection=" + encodeURIComponent(corpSection)
//            + "&txtResubmitDt= " + encodeURIComponent(txtResubmitDt)
//            + "&vendor_number=" + encodeURIComponent(vendor_number)
//            + "&ForwardToPlant=" + encodeURIComponent(ForwardToPlant)
//            + "&ForwardToDesc=" + encodeURIComponent(ForwardToDesc)
            + "&subAction=" + action
            ;
//alert(params);
    callAjax("POST", url, params, false, out.response);


}


function submitLegalInvoiceButton() {
    if (LegalApplDtlvalidation() && LegalNumericVal() && LegalDateVal()) {
//        if (document.getElementById("txtVNum"))
//
//        {
//            var txtvendorId = document.getElementById("txtVNum").value;
//            var index = document.getElementById("txtVNum").value.indexOf("-");
//            if (index === -1) {
//                var txtvendorId = document.getElementById("txtVNum").value;
//            } else {
//                var txtvendorId = document.getElementById("txtVNum").value;
//                var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
//                var vendor_name = document.getElementById("txtVNum").value;
//                var vendor_name = vendor_name.substring(vendor_name.indexOf('-') + 1);
//            }
//        }

        var retVal = confirm("Do you want to Submit?");
        if (retVal === true) {
            saveLegalInvoice("submit");
            var uiActionName = document.getElementById("redirectUrl").value;
            // var txtDealingOffice = document.getElementById("txtDealingOffice").value;
           //    var txtDealingOfficeCode = document.getElementById("txtDealingOfficeCode").value;
            var action = "submit";
            var url = "erp";

            var params = "uiActionName=" + encodeURIComponent(uiActionName)
              //      + "&txtDealingOffice=" + encodeURIComponent(txtDealingOffice)
              //      + "&txtDealingOfficeCode=" + encodeURIComponent(txtDealingOfficeCode)
                    + "&txtvendorId=" + encodeURIComponent(uiActionName)
                    + "&subAction=" + encodeURIComponent(action)
                    ;

            postForm(url, params, "get");
            return true;
        } else {
//            var uiActionName = document.getElementById("redir").value;
//            var action = "submit";
//            var url = "erp";
//            var params = "uiActionName=" + encodeURIComponent(uiActionName)
//                    + "&subAction=" + encodeURIComponent(action)
//                    ;
//
//            postForm(url, params, "get");
//            return false;

        }
    }
}
function viewLeaglEmpAcceptedApp1() {

}
function viewLeaglEmpApp1(AppId, EmpNo, viewAction, status) {
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
        uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
            + "&AppId=" + encodeURIComponent(AppId)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&subAction=" + encodeURIComponent(action)
            + "&status=" + encodeURIComponent(status)
            ;
//alert(params);
    postForm(url, params, "get");
}
function legalInvoiceApproveButton() {
    if (LegalApplDtlvalidation() && LegalNumericVal() && LegalDateVal() && LegalApplDtlvalidation_Approve()) {
        var retVal = confirm("Do you want to Verify?");
        if (retVal === true) {
            //document.write ("User wants to continue!");
            saveLegalInvoice("approve");
            var uiActionName = "getAuthLegalInvoiceList";
            var action = "approve";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    //+ "&subAction=" + encodeURIComponent(action)
                    ;

            postForm(url, params, "get");alert(params);
            return true;
        } else {
            var uiActionName = "getAuthLegalInvoiceList";
            var action = "approve";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    //+ "&subAction=" + encodeURIComponent(action)
                    ;
            // document.write ("User does not want to continue!");
            return false;
        }
        // save("approve");
        //var uiActionName = document.getElementById("rem").value;

    }
}

function LegalApplDtlvalidation_Approve() {
    
    var txtInvSubmitDt = document.getElementById('txtInvSubmitDt').value;
    var txtInvSubmitDt1 = txtInvSubmitDt.replace('-', ' ');
    var txtInvSubmitDt2 = txtInvSubmitDt1.replace('-', ' ');
    var myDate = new Date(txtInvSubmitDt2);
    
    var txtInwardNum = document.getElementById('txtInwardNum').value;

    var txtInwardDt = document.getElementById('txtInwardDt').value;   
    var txtInvSubmitDt1 = txtInwardDt.replace('-', ' ');
    var txtInvSubmitDt2 = txtInvSubmitDt1.replace('-', ' ');
    var myDate1 = new Date(txtInvSubmitDt2);
    
    if (txtInwardNum === null || txtInwardNum === "") {
        alert("Inward Num is Required");
        return false;
    }
    if (txtInwardDt === null || txtInwardDt === "") {
        alert("Please select Inward Date");
        return false;
    }
    if ((myDate > myDate1))
    {
        alert('Inward Date should be greater than Invoice Received Date!');
        return false;
    }
    var today = new Date();
    if (myDate1 > today)
    {
        alert('Inward Date cannot be a future date!');
        return false;
    }
    return true;
}
function legalInvoiceRejectButton() {
    document.getElementById('txtReason').style.display = 'block';
    document.getElementById('divheader-div1').style.display = 'block';
    document.getElementById('rejectSubmitButton').style.display = 'block';
    document.getElementById('rejectSubmitButton').disabled = true;
    document.getElementById('ButtonApprove').disabled = 'true';
    document.getElementById('Buttonreject').disabled = 'true';
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
        document.getElementById('rejectSubmitButton').disabled = false;
    }
}
function myFunction() {
    var nameInput = document.getElementById('txtReason1').value;
    if (nameInput == '') {
        document.getElementById('rejectSubmitButton').disabled = true;
    } else {
        document.getElementById('rejectSubmitButton').disabled = false;
    }
}
function legalInvoiceRejectSubmitButton() {
    if (LegalApplDtlvalidation_Approve()) {
        var retVal = confirm("Do you want to Return ?");
        if (retVal === true) {
            saveLegalInvoice("return");
            var uiActionName = "getAuthLegalInvoiceList";
            var action = "return";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    // + "&subAction=" + encodeURIComponent(action)
                    ;
            postForm(url, params, "get");
            return true;
        } else {
            var uiActionName = "getAuthLegalInvoiceList";
            //var uiActionName = document.getElementById("rem").value;
            var action = "return";
            var url = "erp";
            var params = "uiActionName=" + encodeURIComponent(uiActionName)
                    // + "&subAction=" + encodeURIComponent(action)
                    ;

            postForm(url, params, "get");
            return false;
        }
    }
}
function getLegalList() {
    if (getLegalListVal()) {
        var txtVendorNumber = document.getElementById("txtVendorNumber").value;
        var txtVendorNumber = txtVendorNumber.substring(0, txtVendorNumber.indexOf("-"));
        /*   if (txtPONumber.indexOf('-') > -1)
         {
         var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
         }*/
        var txtLocation = document.getElementById("txtLocation").value;
        if ((txtLocation.indexOf("-")) > -1) {
            var txtLocation = txtLocation.substring(0, txtLocation.indexOf("-"));
        }
//    var txtInvoiceNumber = document.getElementById("txtInvoiceNumber").value.trim(); 
        var txtFrmDt = document.getElementById("txtFrmDt").value;
        var txtToDt = document.getElementById("txtToDt").value;


        var url = "erp";
        var uiactionName = "getAuthLegalInvoiceList";
        var params = "uiActionName=" + uiactionName
                + "&txtVendorNumber=" + txtVendorNumber
//            + "&txtPONumber=" + txtPONumber 
                + "&txtLocation=" + txtLocation
//            + "&txtInvoiceNumber=" + txtInvoiceNumber           
                + "&txtFrmDt=" + txtFrmDt
                + "&txtToDt=" + txtToDt
                ;

        postForm(url, params, "POST");
    }
}
function getLegalListVal() {
    var txtFrmDt = document.getElementById("txtFrmDt").value;
    var txtToDt = document.getElementById("txtToDt").value;

    if (txtFrmDt === null || txtFrmDt === "") {
        alert("From Date is Required");
        return false;
    }
    if (txtToDt === null || txtToDt === "") {
        alert("To Date is Required");
        return false;
    }


    var CurrentDate = new Date();
    GivenDate = new Date(txtToDt);
    if (GivenDate > CurrentDate) {
        alert("The Date must be Lesser or Equal to today date")
        return false;
    }


    var txtFrmDt1 = txtFrmDt.replace('-', ' ');
    var txtFrmDt2 = txtFrmDt1.replace('-', ' 20');
    var FromDate = new Date(txtFrmDt2);// Invoice From Date Should be less than all


    var txtToDt1 = txtToDt.replace('-', ' ');
    var txtToDt2 = txtToDt1.replace('-', ' 20');
    var ToDate = new Date(txtToDt2); // Invoice To Date Should be less than all and Greater than From Date
    if (FromDate > ToDate) {
        alert("The From Date must be Lesser or Equal to To date")
        return false;
    }

    ///////////////////


    // for other date you can get the another date from a textbox by
    // var Newdate=document.getElementById('<%=textBox1.ClientID%>').value;
    // convert Newdate to dateTime by......   var date2=New Date(Newdate);

    var yearDiff = ToDate.getFullYear() - FromDate.getFullYear();// for year difference
    var y1 = ToDate.getFullYear();
    var y2 = FromDate.getFullYear();
    var monthDiff = (ToDate.getMonth() + y1 * 12) - (FromDate.getMonth() + y2 * 12);
    var day1 = parseInt(ToDate.getDate());
    var day2 = parseInt(FromDate.getDate());
    var dayDiff = (day1 - day2) + (monthDiff * 30);
    // alert("Number of day difference : "+dayDiff);



    /////////////////

    if (Number(dayDiff) > 90) {
        alert("Get List Period should not be greater than 3 months");
        return false;
    }
    return true;

}

function validFinalFile() {
    if (verifyFileUpload()) {
        document.getElementById('btnFile').disabled = false;
    } else {
        document.getElementById('btnFile').disabled = true;
    }
}

function verifyFileUpload() {
    var valid = true;

    if (document.getElementById('inpFile').value === "")

    {
        alert("Please upload all Files.");
        valid = false;
    }

    var file = inpFile.files[0];
    var fileName = file.name;
    var extension = fileName.substr(fileName.lastIndexOf('.') + 1).toLowerCase();
    var validExtensions = ["jpg", "jpeg", "png", "pdf"];
    var fileSizeInKb = Math.round(file.size / 1024);

    if (fileName.length > 0)
    {
        if (validExtensions.indexOf(extension) === -1) {
            alert('Invalid file Format. Only ' + validExtensions.join(', ') + ' are allowed.');
            valid = false;
        } else if (fileSizeInKb > 1025) {
            alert(file.name + " has size " + fileSizeInKb + " KB. Please upload a file within 1MB.");
            valid = false;
        }
    }
    return valid;
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

function showWithOrWithoutCourtCaseFields() {document.getElementById("txtRegion").onclick = "";
    var WithOrWithoutCourtCase = $('input[name="rad_courtCase"]:checked').val();
    if (WithOrWithoutCourtCase === 'withCourtCase') {
        $('#withCourtCaseNoBody').show(); //$("#withoutCourtCaseNoBody :input[type='text']").val(''); $("#withoutCourtCaseNoBody :select").val('');
        $('#withoutCourtCaseNoBody').hide();
    } else {
        $('#withCourtCaseNoBody').hide();
        $('#withoutCourtCaseNoBody').show(); //$("#withCourtCaseNoBody :input[type='text']").val(''); $("#withCourtCaseNoBody :select").val('');
        var out = {
            response: function validation(info) {

                var jsonObj = JSON.parse(info);
                var regionList = jsonObj.hierarchylistString;
                if (regionList !== 'undefined' && regionList !== "") {
                    jQuery("#txtRegion").html(regionList);
                } else {
                    jQuery("#txtRegion").html("<center><h4>No record found to display.</h4></center>");
//                    alert("No retention record to display.");
//                    location.reload();
                }
//jQuery("#retention_table_content").show();
            }
        };
        var officeType = "REG";
        var officeCode = "";
        var url = "ajax";
        var uiactionName = "getLegalHierarchyLocation";
        var params = "uiaction=" + uiactionName
                + "&officeType=" + officeType
                + "&officeCode=" + officeCode
                ;
        callAjax("POST", url, params, false, out.response);
    }
}

function getLegalHierarchyLocation(value, officeType) {
    var office=value.options[value.selectedIndex].text;
    
    if (officeType === 'ZON') {
        if (office !== 'Select') {
            document.getElementById("txtCorporateOffice").disabled = true;
            document.getElementById("txtCorpSection").disabled = true;
        } else {
            document.getElementById("txtCorporateOffice").disabled = false;
            document.getElementById("txtCorpSection").disabled = false;
        }
    }
    document.getElementById('txtDealingOffice').value = office;
    document.getElementById('txtDealingOfficewithoutCaseNo').value = office;
    var officeCode = office.substring(0, office.indexOf("-"));//alert(officeCode);
    document.getElementById('selectedOffieCode').value = officeCode;
    if (officeType !== 'SUB-DIV') {
        var out = {
            response: function validation(info) {

                var jsonObj = JSON.parse(info);
                var id = jsonObj.hierarchylistString;
                var plant = jsonObj.plant;
                //document.getElementById("txtcircle").value = id;
                // var id2 = jsonObj.Message1;

                if (plant === 'ZON')
                {
                    jQuery("#txtZone").html(id)
                }
                if (plant === 'CIR')
                {
                    jQuery("#txtCircle").html(id)
                }
                if (plant === 'DIV')
                {
                    jQuery("#txtDivision").html(id)
                }
                if (plant === 'SUB')
                {
                    jQuery("#txtSubDivision").html(id)
                }
                if (plant === 'DEPT')
                {
                    //jQuery("#txtCorporateOffice").html(id)
                    jQuery("#txtCorpSection").html(id)
                }
            }
        };

//             var officeType =value.substring(value.indexOf("-") + 1);
        var url = "ajax";
        var uiactionName = "getLegalHierarchyLocation";
        var params = "uiaction=" + uiactionName
                + "&officeType=" + officeType
                + "&officeCode=" + officeCode
                ;

        callAjax("POST", url, params, false, out.response);
    }

}
function disableOtherLocation(value) {
    if (value !== 'Select') {
        document.getElementById("txtRegion").disabled = true;
        document.getElementById("txtZone").disabled = true;
        document.getElementById("txtCircle").disabled = true;
        document.getElementById("txtDivision").disabled = true;
        document.getElementById("txtSubDivision").disabled = true;
    } else {
        document.getElementById("txtRegion").disabled = false;
        document.getElementById("txtZone").disabled = false;
        document.getElementById("txtCircle").disabled = false;
        document.getElementById("txtDivision").disabled = false;
        document.getElementById("txtSubDivision").disabled = false;
    }
    
    
    }
    function removeLegalFile(id, option, filename) {
    var AppId = document.getElementById("txtApplicationId").value;
   
        var txtvendorId = document.getElementById("txtVendorCode").value;
         var vendor_name = document.getElementById("txtVendorName").value;
        //var index = document.getElementById("txtVNum").value.indexOf("-");
     /*   if (index === -1) {
            var txtvendorId = document.getElementById("txtVendorCode").value;
        } else {
            var txtvendorId = document.getElementById("txtVNum").value;
            var txtvendorId = txtvendorId.substring(0, txtvendorId.indexOf("-")).trim();
            var vendor_name = document.getElementById("txtVNum").value;
            var vendor_name = vendor_name.substring(vendor_name.indexOf('-') + 1);
        }*/
  
    var module_type = document.getElementById("Module").value;//module type to differentiate between proj id and po number
   

    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var de = jsonObj.AppId;
        }
    };
   var url = "ajax";
    var uiactionName = "deleteLegalInvoiceFile";
    var params = "uiaction=" + uiactionName
            + "&AppId=" + AppId
            + "&FId=" + id
            + "&Option=" + option
            + "&FileName=" + filename
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



