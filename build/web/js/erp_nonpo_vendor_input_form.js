window.onload = function() {
     //document.getElementById("txtzone").value = document.getElementById("selectzone").value;
var dd = document.getElementById('txtzone');
for (var i = 0; i < dd.options.length; i++) {
    if (dd.options[i].text === document.getElementById("selectzone").value) {
        dd.selectedIndex = i;
        break;
    }
}
var sel = document.getElementById('txtCircle');
if(document.getElementById('selectcircle').value)
{
var option = document.createElement("option");
  option.text =  document.getElementById('selectcircle').value;
 sel.add(option);
 }
 var dd = document.getElementById('txtCircle');
for (var i = 0; i < dd.options.length; i++) {
    if (dd.options[i].text === document.getElementById("selectcircle").value) {
        dd.selectedIndex = i;
        break;
    }
}
var sel = document.getElementById('txtDivision');
if(document.getElementById('selectdiv').value)
{
var option = document.createElement("option");
  option.text =  document.getElementById('selectdiv').value;
 sel.add(option);
 }
var dd = document.getElementById('txtDivision');
for (var i = 0; i < dd.options.length; i++) {
    if (dd.options[i].text === document.getElementById("selectdiv").value) {
        dd.selectedIndex = i;
        break;
    }
}
var sel = document.getElementById('txtSubDivision');
if(document.getElementById('selectsubdiv').value)
{
var option = document.createElement("option");
  option.text =  document.getElementById('selectsubdiv').value;
 sel.add(option);
 }
var dd = document.getElementById('txtSubDivision');
for (var i = 0; i < dd.options.length; i++) {
    if (dd.options[i].text === document.getElementById("selectsubdiv").value) {
        dd.selectedIndex = i;
        break;
    }
}
  //document.getElementById("txtModule").value = document.getElementById("naturework").value;
}
function save(action) {
  
    var status =  document.getElementById("status").value;
    var txtApplId = document.getElementById('txtApplId').value;
    var txtInvoiceNum = document.getElementById('txtInvoiceNum').value;
    var txtVendorInwardDt = document.getElementById('txtVendorInwardDt').value;
    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    var txtInvSubmitDt = document.getElementById('txtInvSubmitDt').value;
     var txtzone = ''; 
    $("#txtzone option:selected").html() === "Select" ? txtzone='' : txtzone = $("#txtzone option:selected").html();

    var txtCircle = '';
     $("#txtCircle option:selected").html() === "Select" ? txtCircle='' : txtCircle = $("#txtCircle option:selected").html();
   var  txtDivision= '';  
    $("#txtDivision option:selected").html() === "Select" ? txtDivision='' : txtDivision = $("#txtDivision option:selected").html();
   var  txtSubDivision= ''
       $("#txtSubDivision option:selected").html() === "Select" ? txtSubDivision='' : txtSubDivision = $("#txtSubDivision option:selected").html();

    var txtVendorInvoiceFromDate =document.getElementById('txtVendorInvoiceFromDate').value;
     var txtVendorInvoiceToDate =document.getElementById('txtVendorInvoiceToDate').value;
  // var txtModule= document.getElementById('txtModule').value;//Nature of work
   var txtWorkDetail=document.getElementById('txtWorkDetail').value;
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
            var uiactionName = "postnonpoVendorApplForm";
            var params = "uiaction=" + uiactionName
                    + "&txtApplId=" + encodeURIComponent(txtApplId)
                  + "&txtInvoiceNum=" + encodeURIComponent(txtInvoiceNum)
                    + "&txtVendorInwardDt=" + encodeURIComponent(txtVendorInwardDt)
                    + "&txtInvoiceAmt=" + encodeURIComponent(txtInvoiceAmt)
                    + "&txtVendorInvoiceDate=" +encodeURIComponent(txtVendorInvoiceDate)
                    + "&txtInvSubmitDt=" +encodeURIComponent(txtInvSubmitDt)
                    + "&txtzone=" + encodeURIComponent(txtzone)
                    + "&txtCircle=" + encodeURIComponent(txtCircle)
                    + "&txtDivision=" + encodeURIComponent(txtDivision)
            + "&txtSubDivision=" + encodeURIComponent(txtSubDivision)
                    + "&status=" + encodeURIComponent(status)
             + "&txtVendorInvoiceFromDate=" + encodeURIComponent(txtVendorInvoiceFromDate)
      + "&txtVendorInvoiceToDate=" + encodeURIComponent(txtVendorInvoiceToDate)
      // + "&txtModule=" + encodeURIComponent(txtModule)
        + "&txtWorkDetail=" + encodeURIComponent(txtWorkDetail)
                    + "&subAction=" + action
                    ;

            callAjax("POST", url, params, false, out.response);
       
   
}

function saveButton() {
    if (ApplDtlvalidation() && numericVal() && dateVal() ) {
        save("save");

      var txtApplId = document.getElementById("txtApplId").value;
        /* var action = "save";*/
        var uiActionName = document.getElementById("redir").value;
        
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
              + "&txtApplId=" + encodeURIComponent(txtApplId)
                ;
        postForm(url, params, "get");
    }
}
function ApplDtlvalidation_Approve(){
    var txtInwardNum = document.getElementById('txtInwardNum').value;
    
    var txtInwardDt = document.getElementById('txtInwardDt').value;
     
     if (txtInwardDt === null || txtInwardDt === "") {
        alert("Please select Inward Date");
        return false;
    }
    return true;
}
function ApplDtlvalidation() {

        var txtzone = document.getElementById('txtzone').value;
    var txtCircle =document.getElementById('txtCircle').value;
   var  txtDivision= document.getElementById('txtDivision').value;
    var  txtSubDivision= document.getElementById('txtSubDivision').value;
//var txtModule= document.getElementById('txtModule').value;
    
    var txtInvoiceNum = document.getElementById('txtInvoiceNum').value;
    
    var txtVendorInwardDt = document.getElementById('txtVendorInwardDt').value;
    
    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;
    
    var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
    
    var txtVendorInvoiceFromDate =document.getElementById('txtVendorInvoiceFromDate').value;
     var txtVendorInvoiceToDate =document.getElementById('txtVendorInvoiceToDate').value;
     
      if (txtVendorInvoiceFromDate === null || txtVendorInvoiceFromDate === "") {
        alert("Invoice From Date is Required");
        return false;
    }
     if (txtVendorInvoiceToDate === null || txtVendorInvoiceToDate === "") {
        alert("Invoice To Date is Required");
        return false;
    }
     /* if (txtModule === null || txtModule === "") {
        alert("Nature Of Work is Required");
        return false;
    }*/
      if (txtzone === null || txtzone === "") {
        alert("Atleast Zone is Required");
        return false;
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
    
    if(txtVendorInvoiceDate === null || txtVendorInvoiceDate === "") {
        alert("Vendor Invoice Date is Required");
      return false;
    }
    
  
    return true;
}

function submitButton() {
    if (ApplDtlvalidation() && numericVal() && dateVal()) {
 var retVal = confirm("Do you want to Submit?");
               if( retVal == true ) {
        save("submit");

        var uiActionName = document.getElementById("redir").value;
        var action = "submit";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
                + "&subAction=" + encodeURIComponent(action)
                ;

        postForm(url, params, "get");
    return true;
}
    else{
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

function approveButton() {
    if (ApplDtlvalidation() && numericVal() && dateVal() && ApplDtlvalidation_Approve()) {
         var retVal = confirm("Do you want to Verify?");
               if( retVal == true ) {
                  //document.write ("User wants to continue!");
                  save("approve");
                   var uiActionName ="getAuthPOList";
        var action = "approve";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
                //+ "&subAction=" + encodeURIComponent(action)
                ;

        postForm(url, params, "get");
                  return true;
               } else {
                    var uiActionName ="getAuthPOList";
        var action = "approve";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
                //+ "&subAction=" + encodeURIComponent(action)
                ;
                 // document.write ("User does not want to continue!");
                  return false;}
       // save("approve");
        //var uiActionName = document.getElementById("rem").value;
       
    }
}


function myFunction() {
            var nameInput = document.getElementById('txtReason1').value;
            
            if (nameInput == '') {

               document.getElementById('Buttonsubmit1').disabled=true;

            } else {

             document.getElementById('Buttonsubmit1').disabled=false;

            }
    };
function reject() {
document.getElementById('txtReason').style.display='block';
document.getElementById('divheader-div1').style.display='block';
document.getElementById('Buttonsubmit1').style.display='block';
document.getElementById('Buttonsubmit1').disabled=true;
document.getElementById('ButtonApprove').disabled='true';
document.getElementById('Buttonreject').disabled='true';
//document.getElementById('txtReason1').style.display='block';
}
function rejectButton() {

   if (ApplDtlvalidation() && numericVal() && dateVal()) {
         
         var retVal = confirm("Do you want to Reject?");
               if( retVal == true ) {
                  
      save("reject");
var uiActionName ="getAuthPOList";
        var action = "reject";
        var url = "erp";
        var params = "uiActionName=" + encodeURIComponent(uiActionName)
               // + "&subAction=" + encodeURIComponent(action)
                ;

        postForm(url, params, "get");
        return true;
               }
               
               else{
                   var uiActionName ="getAuthPOList";
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
    if(val==="other")
      { document.getElementById('txtReason1').style.display='block';
       //document.getElementById('Buttonreject').disabled=false;
   }
    else
        {
      // document.getElementById('Buttonsubmit1').style.display='block'; 
   document.getElementById('Buttonsubmit1').disabled=false;
       }
}
function numericVal()
{
  var txtInvoiceAmt = document.getElementById('txtInvoiceAmt').value;
  //var regexp = /^\d+\.\d{1,2}$/;
txtInvoiceAmt.replace(/\s/g, "");
   if (!(/^[-+]?\d*\.?\d*$/.test(txtInvoiceAmt))){
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
    
    var txtVendorInvoiceFromDate = document.getElementById('txtVendorInvoiceFromDate').value;
    var txtVendorInvoiceFromDate = txtVendorInvoiceFromDate.replace('-', ' ');
    var txtVendorInvoiceFromDate = txtVendorInvoiceFromDate.replace('-', ' 20');
    var myDate1 = new Date(txtVendorInvoiceFromDate);
    
      var txtVendorInvoiceToDate = document.getElementById('txtVendorInvoiceToDate').value;
    var txtVendorInvoiceToDate = txtVendorInvoiceToDate.replace('-', ' ');
    var txtVendorInvoiceToDate = txtVendorInvoiceToDate.replace('-', ' 20');
    var myDate2 = new Date(txtVendorInvoiceToDate);
   
           if ((myDate2<myDate1))
          { 
            alert(' Invoice To Date should be greater than Invoice From Date!')
            return false;
          }
           var txtVendorInwardDt = document.getElementById('txtVendorInwardDt').value;
    var txtVendorInwardDt1 = txtVendorInwardDt.replace('-', ' ');
    var txtVendorInwardDt2 = txtVendorInwardDt1.replace('-', ' 20');
    var myDate3 = new Date(txtVendorInwardDt2);// application/vendor invoice date and should be greater than All
    
    var txtVendorInvoiceDate = document.getElementById('txtVendorInvoiceDate').value;
    var txtVendorInvoiceDate1 = txtVendorInvoiceDate.replace('-', ' ');
    var txtVendorInvoiceDate2 = txtVendorInvoiceDate1.replace('-', ' 20');
    var myDate4 = new Date(txtVendorInvoiceDate2); //Inward Date Greater than Inv To Date
   
           if ((myDate3<myDate4))
          { 
            alert('VENDOR Inward Date should be greater than VENDOR Invoice Date!')
            return false;
          }
          
          
          return true;
    }
    
/*function viewFile(id,option) {
    var AppId = document.getElementById("txtApplId").value;
    var view = document.getElementById("view").value;
      var txtPONumber = document.getElementById("txtPONumber").value;
var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));

    var url = view + "&AppId=" + AppId
            + "&FId=" + id
    + "&txtPONumber=" + txtPONumber
            + "&Option=" + option;

    window.open(url, 'Attachment', 'toolbar=no,location=no,directories=no,status=no, menubar=no,scrollbars=yes,resizable=yes,width=1200,height=1200');

}

function removeFile(id,option,filename) {
    var AppId = document.getElementById("txtApplId").value;
   var txtPONumber = document.getElementById("txtPONumber").value;
var txtPONumber = txtPONumber.substring(0, txtPONumber.indexOf("-"));
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
            + "&subAction=" + "delete"
            ;

    callAjax("POST", url, params, false, out.response);

    var uiActionName = document.getElementById("rem").value;
    var action = "delete";
    var url = "erp";
    var params = "uiActionName=" + encodeURIComponent(uiActionName)
            + "&AppId=" + encodeURIComponent(AppId)
            + "&subAction=" + encodeURIComponent(action)
            ;

    postForm(url, params, "get");
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

function validFinalFile() {
   
    if (verifyFileUpload()) {
        document.getElementById('btnFile').disabled = false;
    } else {
        document.getElementById('btnFile').disabled = true;
    }
}*/

	
function gethierarchylocation(value){
      var out = {
                response: function validation(info) {
                    var jsonObj = JSON.parse(info);
                    var id = jsonObj.hierarchylistString;
                    var plant=jsonObj.plant;
                    //document.getElementById("txtcircle").value = id;
                   // var id2 = jsonObj.Message1;
                   
                  //  window.alert(id);
                    //window.alert(plant);
                    if(plant=='CIR')
                    {
                       jQuery("#txtCircle").html(id) 
                    }
                     if(plant=='DIV')
                     {
                        jQuery("#txtDivision").html(id)  
                     }
                      if(plant=='SUB')
                     {
                         jQuery("#txtSubDivision").html(id)  
                     }
                }
            };
     
     var officecodevalue = value.substring(0, value.indexOf("-"));
             var officecodetype =value.substring(value.indexOf("-") + 1);
             var officename= $(".selectoption option:selected").eq(0).html();
    var url = "ajax";
    var uiactionName = "getHierarchyLocation";
    var params = "uiaction=" + uiactionName
            + "&officecodevalue=" + officecodevalue
           + "&officecodetype=" +officecodetype
   + "&officename=" +officename
        ;

          callAjax("POST", url, params, false, out.response);
  
}


function getZoneSearchList(){  
     var type = "zoneList";
     var zoneOptions = JSON.parse(document.getElementById("zoneOptions").value); 
     autocomplete(document.getElementById("txtzone"), zoneOptions,type);
  }
  function getCircleSearchList(){  
     var type = "circleList";
     var circleOptions = JSON.parse(document.getElementById("circleOptions").value); 
     autocomplete(document.getElementById("txtCircle"), circleOptions,type);
  }
  function getDivisionSearchList(){  
     var type = "divisionList";
     var divOptions = JSON.parse(document.getElementById("divOptions").value); 
     autocomplete(document.getElementById("txtDivision"), divOptions,type);
  }
 function getSubDivisionSearchList(){  
     var type = "subdivisionList";
     var subdivOptions = JSON.parse(document.getElementById("subdivOptions").value); 
     autocomplete(document.getElementById("txtSubDivision"), subdivOptions,type);
  }
function getPOSearchList(){
  //  var ui = document.getElementById("uiActionName").value;
  var type = "poList";
     var poOptions = JSON.parse(document.getElementById("poOptions").value); 
     // var poOptions = JSON.parse(document.getElementById("hdnPoOption").value);
     
    // var countries = ["Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua &amp; Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia &amp; Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands","Central Arfrican Republic","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cuba","Curacao","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Myanmar","Namibia","Nauro","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre &amp; Miquelon","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Korea","South Sudan","Spain","Sri Lanka","St Kitts &amp; Nevis","St Lucia","St Vincent","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad &amp; Tobago","Tunisia","Turkey","Turkmenistan","Turks &amp; Caicos","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States of America","Uruguay","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"];
    // poOptions = ["4500000121" , "4500000122" , "4500000123" ];
   // alert("poOptions:"+poOptions);
       //autocomplete(document.getElementById("txtPONumber"), poOptions,type);
       autocomplete_fillingHiddenValue(document.getElementById("txtPONumber"), poOptions,type);
  }
function autocomplete_fillingHiddenValue(inp, arr, type) {
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
              //document.getElementById("txtzone").value= inp.value
              //document.getElementById("txtCircle").value= inp.value
             // document.getElementById("txtDivision").value= inp.value
             // document.getElementById("txtSubDivision").value= inp.value
                     //alert(inp.value);
              //getlocation();
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
              //getlocation(inp.value);
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
//    if(type == 'poList'){
//    getInvoiceNum();
//    }
   
});
}

$(function () {
    // if {
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
       $("#txtVendorInvoiceFromDate").datepicker({
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
       $("#txtVendorInvoiceToDate").datepicker({
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
