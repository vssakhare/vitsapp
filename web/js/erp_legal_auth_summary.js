/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
   $(document).ready(function () {
        $("#exportBtn1").click(function(){
         html2canvas($('#tabrr')[0], {
                onrendered: function (canvas) {
                    var data = canvas.toDataURL();
                    var docDefinition = {
                        content: [{
                            image: data,
                            width: 500
                        }]
                    };
                    pdfMake.createPdf(docDefinition).download("Legal_Invoices_Summary.pdf");
                }
            });
             });
     /* $("#exportBtn1").click(function(){
        TableToExcel.convert(document.getElementById("tabrr"), {
            name: "Legal_Invoices_Summary.xlsx",
            sheet: {
            name: "Sheet1"
            }
          });
        });*/
       
  });
$(function () {
  
       $("#txtInvoiceFromDate").datepicker({
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
       $("#txtInvoiceToDate").datepicker({
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
    //}
});

function viewSummaryListDetails(status_dtl,zone,circle,division,subdiv,deptName){
 
var viewList = document.getElementById("viewList").value;
var txtFrmDt = document.getElementById("txtInvoiceFromDate").value;
        var txtToDt = document.getElementById("txtInvoiceToDate").value;
            var url = viewList
            + "&status_dtl=" +status_dtl
            + "&zone=" +zone
            + "&circle=" +circle
            + "&division=" +division
            + "&subdiv=" +subdiv
            + "&deptName=" +deptName
            + "&txtFrmDt=" + txtFrmDt
            + "&txtToDt=" + txtToDt
             ;

window.open(url, 'Attachment', 'toolbar=no,location=no,directories=no,status=no, menubar=no,scrollbars=yes,resizable=yes,width=600,height=600');
}

function Reset()
{
    document.getElementById("txtInvoiceFromDate").value="";
    document.getElementById("txtInvoiceToDate").value="";   
    var url = "erp";
    var uiactionName =document.getElementById("viewStat").value;
    var params = "uiActionName=" + uiactionName;
    postForm(url, params, "POST");
   
    
}
function getLegalSummaryStat() {
    if (getLegalSummaryStatVal()) {   
        
        
        var txtFrmDt = document.getElementById("txtInvoiceFromDate").value;
        var txtToDt = document.getElementById("txtInvoiceToDate").value;
        var url = "erp";
         var uiActionName = document.getElementById("viewStat").value;
        var params = "uiActionName=" + uiActionName
         + "&txtFrmDt=" + txtFrmDt
         + "&txtToDt=" + txtToDt
         ;
       postForm(url, params, "POST");
    }
}

function getList() {
    var txtPONumber = document.getElementById("poNumber").value.trim();
    var url = "erp";
    var uiactionName = "getSummaryStat";
    var params = "uiActionName=" + uiactionName
            + "&txtPONumber=" + txtPONumber
            ;
    postForm(url, params, "POST");

}
function getLegalSummaryStatVal() {
    var txtFrmDt = document.getElementById("txtInvoiceFromDate").value;
    var txtToDt = document.getElementById("txtInvoiceToDate").value;
    
    
    
    if (txtFrmDt === "" && txtToDt !== "") {
        alert("From Date is Required");
        return false;
    }
    if (txtFrmDt !== "" && txtToDt === "") {
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
function createPDF() {
                                            var sTable = document.getElementById('tab').innerHTML;

                                            var style = "<style>";
                                            style = style + "table {width: 100%;font: 17px Calibri;}";
                                            style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
                                            style = style + "padding: 2px 3px;text-align: center;}";
                                            style = style + "</style>";

                                            // CREATE A WINDOW OBJECT.
                                            var win = window.open('', '', 'height=700,width=700');

                                            win.document.write('<html><head>');
                                            win.document.write('<title>Profile</title>');   // <title> FOR PDF HEADER.
                                            win.document.write(style);          // ADD STYLE INSIDE THE HEAD TAG.
                                            win.document.write('</head>');
                                            win.document.write('<body>');
                                            win.document.write(sTable);         // THE TABLE CONTENTS INSIDE THE BODY TAG.
                                            win.document.write('</body></html>');

                                            win.document.close(); 	// CLOSE THE CURRENT WINDOW.

                                            win.print();    // PRINT THE CONTENTS.
                                        }
 