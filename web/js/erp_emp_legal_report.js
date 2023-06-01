function callLegalReport() {
    if(ReportVal()) {
    var reportType = "PDF";
    var txtReportName = document.getElementById("txtReportName").value;
    var txtFrmDt = document.getElementById("txtFrmDt").value;
    var txtToDt = document.getElementById("txtToDt").value;
    var usertype = document.getElementById("userType").value;
    if (usertype === 'Emp') {
    var txtVendorNumber = document.getElementById("txtVendorNumber").value;
    }if (usertype === 'Vendor') {
     var txtInvNo = document.getElementById("txtInvNo").value;
 }
    var txtLocation = document.getElementById("txtLocation").value;
   
    if ((txtLocation.indexOf("-")) > -1) {        
      txtLocation = txtLocation.substring(0, txtLocation.indexOf("-")); 
    }
    
    var url = "Reports";
    var params = "reportName=" + encodeURIComponent(txtReportName)
            + "&txtFrmDt=" + encodeURIComponent(txtFrmDt)
            + "&txtToDt=" + encodeURIComponent(txtToDt) 
            + "&txtVendorNumber=" + encodeURIComponent(txtVendorNumber)
            + "&txtLocation=" + encodeURIComponent(txtLocation) 
            + "&txtInvNo="+ encodeURIComponent(txtInvNo)
            + "&reportType=" + encodeURIComponent(reportType)
            
            ;

    postForm(url, params, "post");
    
    }
  }

function ReportVal(){
  
          var txtFrmDt = document.getElementById("txtFrmDt").value;
          var txtToDt = document.getElementById("txtToDt").value;
           var txtLocation = document.getElementById("txtLocation").value;
         var usertype = document.getElementById('userType').value;
        if (usertype === 'Emp') {
          var txtVendorNumber = document.getElementById("txtVendorNumber").value;
           if(trim(txtVendorNumber) === "" || trim(txtVendorNumber) ===null){
              alert("Vendor Number is Required. Type 'ALL' For All vendors' report");
              return false;
          }
          } if (usertype === 'Vendor') {
               var txtInvNo = document.getElementById("txtInvNo").value;
           if(trim(txtInvNo) === "" || trim(txtInvNo) ===null){
              alert("Invoice Number is Required. Type 'ALL' For All Invoice report");
              return false;
          }
      }
          
          
        
           if(trim(txtLocation) === "" || trim(txtLocation) ===null){
              alert("Location is Required. Type 'ALL' For All locations' report");
              return false;
          }
          
          
          if(txtFrmDt === "" || txtFrmDt ===null){
              alert("From Date is Required");
              return false;
          }
          
           if(txtToDt === "" || txtToDt ===null){
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
        
    
     return true;
}

function changeFields() {
    var txtReportName = document.getElementById("txtReportName").value;    
    var trParam = document.getElementById("trParam");
   
    
    if (txtReportName === "Employee_detail_reports") {
        trParam.style.display = "table-row";
        
    }
    else if (txtReportName === "MSEDCLEmpReport") {
        trParam.style.display = "table-row";
        
    }
     
     else if (txtReportName === "Employee_Statistics_Reports") {
        trParam.style.display = "table-row";
        
    }
    
}



  


$(function () {
    // if {
    $("#txtFrmDt").datepicker({
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
      $("#txtToDt").datepicker({
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
      
   