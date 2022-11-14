


jQuery(document).ready(function() {

	var inputinvoicestable =	jQuery('#tableinputinvoices').DataTable(
							{
							"order": [[ 0, "asc" ]],
							
							language: { search: "",searchPlaceholder: "Search..." }
							}
	);
var pendinginvoicestable =	jQuery('#tablependinginvoices').DataTable(
							{
							"order": [[ 0, "asc" ]],
							
							language: { search: "",searchPlaceholder: "Search..." }
							}
	);

var paidinvoicestable =	jQuery('#tablepaidinvoices').DataTable(
							{
							"order": [[ 0, "asc" ]],
							
							language: { search: "",searchPlaceholder: "Search..." }
							}
	);

	jQuery('#txtSearchStatus').on('change', function() {
		if (jQuery("#txtSearchStatus").val()==="ALL" || jQuery("#txtSearchStatus").val()==="-SELECT STATUS-")
			{
				jQuery.fn.dataTable.ext.search.pop();
				inputinvoicestable.draw();
			}
		else
			{
				jQuery.fn.dataTable.ext.search.pop();
				jQuery.fn.dataTable.ext.search.push(
				  function(settings, data, dataIndex) {
					  return jQuery(table.row(dataIndex).node()).attr('data-type') === jQuery("#txtSearchStatus").val();
					}
				);
				inputinvoicestable.draw();
			}
	});
        jQuery('#txtPendingSearchStatus').on('change', function() {
		if (jQuery("#txtPendingSearchStatus").val()==="ALL" || jQuery("#txtPendingSearchStatus").val()==="-SELECT STATUS-" )
			{
				jQuery.fn.dataTable.ext.search.pop();
				pendinginvoicestable.draw();
			}
		else
			{
				jQuery.fn.dataTable.ext.search.pop();
				jQuery.fn.dataTable.ext.search.push(
				  function(settings, data, dataIndex) {
					  return jQuery(table.row(dataIndex).node()).attr('data-type') === jQuery("#txtPendingSearchStatus").val();
					}
				);
				pendinginvoicestable.draw();
			}
	});
});



function getInvoiceNum()
{   
    //alert("getInvoiceNum called");
    var txtPONumber = document.getElementById("txtPONumber").value;
      var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).value.trim();
             var txtPONumber = document.getElementById('txtPONumber').value.trim();
/*   if (txtPONumber.indexOf('-') > -1)
{
    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
}*/
    //alert("txtPONumber"+txtPONumber);
        var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var de = jsonObj.AppId;
        }
    };
    var url = "ajax";
    var uiactionName = "getAuthPOList";
    var params = "uiActionName=" + uiactionName
            + "&txtPONumber=" + txtPONumber           
            ;

       callAjax("POST", url, params, false, out.response);
}
function viewEmpAppVerified(appl_id,VendorInvNo, EmpNo, viewAction,PoNumber,module_type) { 
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
      uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
             + "&appl_id=" + encodeURIComponent(appl_id)
            + "&VendorInvNo=" + encodeURIComponent(VendorInvNo)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&PoNumber=" + encodeURIComponent(PoNumber)
            + "&subAction=" + encodeURIComponent(action)
            + "&module_type=" + encodeURIComponent(module_type)
            ;

    postForm(url, params, "get");
}
function viewEmpAppVerified_PS(appl_id,VendorInvNo, EmpNo, viewAction,PoNumber,module_type) { 
    var uiActionName = "";
    if (viewAction !== null && viewAction !== undefined) {
        uiActionName = document.getElementById(viewAction).value;
    } else {
      uiActionName = document.getElementById("viewAction").value;
    }
    var action = "view";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
             + "&appl_id=" + encodeURIComponent(appl_id)
            + "&VendorInvNo=" + encodeURIComponent(VendorInvNo)
            + "&EmpNo=" + encodeURIComponent(EmpNo)
            + "&PoNumber=" + encodeURIComponent(PoNumber)
            + "&subAction=" + encodeURIComponent(action)
            + "&module_type=" + encodeURIComponent(module_type)
            ;

    postForm(url, params, "get");
}
/*function checkstatus(val)
{if(getListVal()){
      var url = "erp";
    var uiactionName = "getAuthInvoiceList";
    var params = "";
    var txtVendorNumber = document.getElementById("txtVendorNumber").value;
    var txtVendorNumber = txtVendorNumber.substring(0, txtVendorNumber.indexOf("-"));
    var txtPONumber = document.getElementById("txtPONumber").value;
    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
    var txtLocation = document.getElementById("txtLocation").value;
    if ((txtLocation.indexOf("-")) > -1) {        
      var txtLocation = txtLocation.substring(0, txtLocation.indexOf("-")); 
    }
    var txtInvoiceNumber = document.getElementById("txtInvoiceNumber").value; 
    var txtFrmDt = document.getElementById("txtFrmDt").value;
    var txtToDt = document.getElementById("txtToDt").value;  
        if(val==="ALL"){
             params = "uiActionName=" + uiactionName
            + "&txtVendorNumber=" + txtVendorNumber  
            + "&txtPONumber=" + txtPONumber 
            + "&txtLocation=" + txtLocation    
            + "&txtInvoiceNumber=" + txtInvoiceNumber           
            + "&txtFrmDt=" + txtFrmDt           
            + "&txtToDt=" + txtToDt
            ;}
  else
        {
             params = "uiActionName=" + uiactionName
            + "&txtVendorNumber=" + txtVendorNumber  
            + "&txtPONumber=" + txtPONumber 
            + "&txtLocation=" + txtLocation    
            + "&txtInvoiceNumber=" + txtInvoiceNumber           
            + "&txtFrmDt=" + txtFrmDt           
            + "&txtToDt=" + txtToDt
            + "&val=" +val
       ;}
    postForm(url, params, "POST");
       
}
}
function checkstatuspending(val)
{if(getListVal()){
      var url = "ajax";
    var uiactionName = "getAuthInvoiceList";
    var params = "";
    var txtVendorNumber = document.getElementById("txtVendorNumber").value;
    var txtVendorNumber = txtVendorNumber.substring(0, txtVendorNumber.indexOf("-"));
    var txtPONumber = document.getElementById("txtPONumber").value;
    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
    var txtLocation = document.getElementById("txtLocation").value;
    if ((txtLocation.indexOf("-")) > -1) {        
      var txtLocation = txtLocation.substring(0, txtLocation.indexOf("-")); 
    }
    var txtInvoiceNumber = document.getElementById("txtInvoiceNumber").value; 
    var txtFrmDt = document.getElementById("txtFrmDt").value;
    var txtToDt = document.getElementById("txtToDt").value;  
        if(val==="ALL"){
             params = "uiaction=" + uiactionName
            + "&txtVendorNumber=" + txtVendorNumber  
            + "&txtPONumber=" + txtPONumber 
            + "&txtLocation=" + txtLocation    
            + "&txtInvoiceNumber=" + txtInvoiceNumber           
            + "&txtFrmDt=" + txtFrmDt           
            + "&txtToDt=" + txtToDt
            ;}
  else
        {
             params = "uiaction=" + uiactionName
            + "&txtVendorNumber=" + txtVendorNumber  
            + "&txtPONumber=" + txtPONumber 
            + "&txtLocation=" + txtLocation    
            + "&txtInvoiceNumber=" + txtInvoiceNumber           
            + "&txtFrmDt=" + txtFrmDt           
            + "&txtToDt=" + txtToDt
            + "&pendingval=" +val
       ;}
    var out = {
        response: function validation(info) {
            var jsonObj = JSON.parse(info);
            var de = jsonObj.AppId;
        }
    };
  

       callAjax("POST", url, params, false, out.response);

       
}
}*/

function getList(){
  if(getListVal()){
    var txtVendorNumber = document.getElementById("txtVendorNumber").value;
    var txtVendorNumber = txtVendorNumber.substring(0, txtVendorNumber.indexOf("-"));
       var txtPONumber = document.getElementById("txtPONumber").value;
    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-")).trim();
/*   if (txtPONumber.indexOf('-') > -1)
{
    var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
}*/
    var txtLocation = document.getElementById("txtLocation").value;
    if ((txtLocation.indexOf("-")) > -1) {        
      var txtLocation = txtLocation.substring(0, txtLocation.indexOf("-")); 
    }
    var txtInvoiceNumber = document.getElementById("txtInvoiceNumber").value.trim(); 
    var txtFrmDt = document.getElementById("txtFrmDt").value;
    var txtToDt = document.getElementById("txtToDt").value;  

    
    var url = "erp";
    var uiactionName = "getAuthInvoiceList";
    var params = "uiActionName=" + uiactionName
            + "&txtVendorNumber=" + txtVendorNumber  
            + "&txtPONumber=" + txtPONumber 
            + "&txtLocation=" + txtLocation    
            + "&txtInvoiceNumber=" + txtInvoiceNumber           
            + "&txtFrmDt=" + txtFrmDt           
            + "&txtToDt=" + txtToDt
            ;

    postForm(url, params, "POST");
   }
}

function getListVal(){
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

        var yearDiff=ToDate.getFullYear()-FromDate.getFullYear();// for year difference
        var y1=ToDate.getFullYear();
        var y2=FromDate.getFullYear();
        var monthDiff=(ToDate.getMonth() + y1*12)-(FromDate.getMonth() +y2*12);
        var day1=parseInt(ToDate.getDate());
        var day2=parseInt(FromDate.getDate());
        var dayDiff= (day1-day2)+ (monthDiff * 30);
        // alert("Number of day difference : "+dayDiff);
  
  
  
  /////////////////
  
    if(Number(dayDiff)>90){
        alert("Get List Period should not be greater than 3 months");
        return false;
    }
    return true;
    
}

function Reset()
{
    document.getElementById("txtPONumber").value="";
    document.getElementById("txtInvoiceNumber").value="";
    document.getElementById("txtVendorNumber").value="";   
    document.getElementById("txtFrmDt").value="";
    document.getElementById("txtToDt").value="";   
    var subAction = "Reset";
    var url = "erp";
    var uiactionName = "getAuthResetList";
    var params = "uiActionName=" + uiactionName
                 +"&subAction=" + subAction;
    postForm(url, params, "POST");
   
    
}

//  function getPOSearchList(){
//  var input, filter, ul, li, a, i, div;
//  input = document.getElementById("txtPONumber");
//  filter = input.value.toUpperCase();
//  a = document.getElementById("poOptions").value;
//  //a = div.getElementsByTagName("a");
//  for (i = 0; i < a.length; i++) {
//    if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
//      a[i].style.display = "";
//    } else {
//      a[i].style.display = "none";
//    }
//  }
//  }

function getLocSearchList(){
  var type = "locDet";
  var LocOptions = JSON.parse(document.getElementById("locOptions").value); 
  autocomplete(document.getElementById("txtLocation"), LocOptions,type);
  }
  

function getVendorSearchList(){  
     var type = "vendorList";
     var VNumOptions = JSON.parse(document.getElementById("VNumOptions").value); 
     autocomplete(document.getElementById("txtVendorNumber"), VNumOptions,type);
  }

 function getInvSearchList(){  
     var type = "invList";
     var invNumOptions = JSON.parse(document.getElementById("invNumOptions").value); 
     autocomplete(document.getElementById("txtInvoiceNumber"), invNumOptions,type);
  }


   function getPOSearchList(){
  //  var ui = document.getElementById("uiActionName").value;
  var type = "poList";
     var poOptions = JSON.parse(document.getElementById("poOptions").value); 
       autocomplete(document.getElementById("txtPONumber"), poOptions,type);
  }
  function getVendorInvListSearchList()
  { var type = "invList";
  var VendorInvOptions = JSON.parse(document.getElementById("VendorInvOptions").value); 
  autocomplete(document.getElementById("txtvendorinvlist"), VendorInvOptions,type);
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
              if(type == 'poList'){
                  //document.getElementById("selectedTxtPONumber").value= inp.value
                 getInvoiceNum();
    }
                
             
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
   // alert("type :- " + type);
    if(type == 'poList'){
 getInvoiceNum();
    }
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