function callReport() {
  if(ReportVal()){
    var reportType = "PDF";
     var txtReportName = document.getElementById("txtReportName").value;
    var txtFrmDt = document.getElementById("txtFrmDt").value;
    var txtToDt = document.getElementById("txtToDt").value;
    var txtLoc = document.getElementById("txtLoc").value; 
    var txtPONumber = document.getElementById("txtPONumber").value;
if ((txtPONumber.indexOf("-")) > -1) {  
    txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
    }    if(txtLoc === 'ALL') {
        var txtLoc = txtLoc;
    }
   else {
        var txtLoc = txtLoc.substring(0, txtLoc.indexOf("-"));
    }
    if(txtPONumber === 'ALL') {
        var txtPONumber = txtPONumber;
    }
   else {
        var txtPONumber = txtPONumber.trim();
    }
    var url = "Reports";
    var params = "reportName=" + encodeURIComponent(txtReportName)
            + "&txtFrmDt=" + encodeURIComponent(txtFrmDt)
            + "&txtToDt=" + encodeURIComponent(txtToDt)  
            + "&txtLoc=" + encodeURIComponent(txtLoc) 
             + "&txtPONumber=" + encodeURIComponent(txtPONumber) 
            + "&reportType=" + encodeURIComponent(reportType)
            ;

    postForm(url, params, "post");
    
     }
    
}


function ReportVal(){
    var txtReportName = document.getElementById("txtReportName").value;
    if (txtReportName === "Vendor_detail_report") {
          var txtFrmDt = document.getElementById("txtFrmDt").value;
          var txtToDt = document.getElementById("txtToDt").value;
          var txtLoc = document.getElementById("txtLoc").value;
          var txtPONumber = document.getElementById("txtPONumber").value;
          
          if(txtFrmDt === "" || txtFrmDt ===null){
              alert("From Date is Required");
              return false;
          }
          
           if(txtToDt === "" || txtToDt ===null){
              alert("To Date is Required");
              return false;
          }
          
          if(txtLoc === "" || txtLoc ===null){
              alert("Location is Required. Type 'ALL' For All locations");
              return false;
          }
          if(txtPONumber === "" || txtPONumber ===null){
              alert("PO NUMBER is Required. Type 'ALL' For All locations");
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
    
    if (txtReportName === "MSEDCL_Vendor_Detail_Report") {
          var txtFrmDt = document.getElementById("txtFrmDt").value;
          var txtToDt = document.getElementById("txtToDt").value;
          var txtLoc = document.getElementById("txtLoc").value;
           var txtPONumber = document.getElementById("txtPONumber").value;
          if(txtFrmDt === "" || txtFrmDt ===null){
              alert("From Date is Required");
              return false;
          }
          
           if(txtToDt === "" || txtToDt ===null){
              alert("To Date is Required");
              return false;
          }
          
          if(txtLoc === "" || txtLoc ===null){
              alert("Location is Required. Type 'ALL' For All locations");
              return false;
          }
          if(txtPONumber === "" || txtPONumber ===null){
              alert("PO NUMBER is Required. Type 'ALL' For All locations");
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
    
    if (txtReportName === "Vendor_Statistics_Reports") {
          var txtFrmDt = document.getElementById("txtFrmDt").value;
          var txtToDt = document.getElementById("txtToDt").value;
          var txtLoc = document.getElementById("txtLoc").value;
           var txtPONumber = document.getElementById("txtPONumber").value.trim();
          if(txtFrmDt === "" || txtFrmDt ===null){
              alert("From Date is Required");
              return false;
          }
          
           if(txtToDt === "" || txtToDt ===null){
              alert("To Date is Required");
              return false;
          }
          
          if(txtLoc === "" || txtLoc ===null){
              alert("Location is Required. Type 'ALL' For All locations");
              return false;
          }
          if(txtPONumber === "" || txtPONumber ===null){
              alert("PO NUMBER is Required. Type 'ALL' For All locations");
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
    
  return true;
}

function changeFields() {
    var txtReportName = document.getElementById("txtReportName").value;    
    var trParam = document.getElementById("trParam");
   
    
    if (txtReportName === "Vendor_detail_report") {
        //trParam.style.display = "none";
        trParam.style.display = "table-row";
        
    }

    else if (txtReportName === "MSEDCL_Vendor_detail_Report") {
       // trParam.style.display = "show";
        trParam.style.display = "table-row";
        
    }
    
    else if (txtReportName === "Vendor_Statistics_Reports") {
        trParam.style.display = "table-row";
    }
    
}

function changeYearOptions() {
    var txtYYYY = document.getElementById("txtYYYY");
    var txtReportName = document.getElementById("txtReportName").value;
    if (txtReportName === "AnnualSalaryCerti") {
        var year = new Date().getFullYear();
        var options = '';
        for (var i = 0; i < 5; i++, year--) {
            options = options + '<option value="' + year + '">' + year + '-' + (Number(year) + 1)+'</option>';
        }
        txtYYYY.innerHTML = options;
    } else {
        var year = new Date().getFullYear();
        var options = '';
        for (var i = 0; i < 5; i++, year--) {
            options = options + '<option value="' + year + '">' + year + '</option>';
        }
        txtYYYY.innerHTML = options;
    }
}

function getLocSearchList(){  
     var type = "locList";
     var locOptions = JSON.parse(document.getElementById("locOptions").value); 
     autocomplete(document.getElementById("txtLoc"), locOptions,type);
  }  
  function getPOSearchList(){
  var type = "poList";
     var poOptions = JSON.parse(document.getElementById("poOptions").value); 
       autocomplete(document.getElementById("txtPONumber"), poOptions,type);
  }
  
  function autocomplete(inp, arr, type) {
  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /*execute a function when someone writes in the text field:*/
 // var type = type;
 
 inp.addEventListener("input", function(e) {        
     
      var a, b, i, val = this.value;
      /*close any already open lists of autocompleted values*/
      closeAllLists();
      if (!val) { return false;}
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
          b.innerHTML =  arr[i];  
          /*insert a input field that will hold the current array item's value:*/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          
           //code to make bold of input ends
          /*execute a function when someone clicks on the item value (DIV element):*/
              b.addEventListener("click", function(e) {
              /*insert the value for the autocomplete text field:*/
              inp.value = this.getElementsByTagName("input")[0].value;
              /*close the list of autocompleted values,
              (or any other open lists of autocompleted values:*/
              closeAllLists();
          });
           
            a.appendChild(b);
         
        }
      }
  });
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");      
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
          if (x) x[currentFocus].click();
        }
      }
  });
  function addActive(x) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
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
    
});
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