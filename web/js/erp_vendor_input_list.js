jQuery(document).ready(function() {

	var invtable =	jQuery('#table_ven_input_inv').DataTable(
							{
							"order": [[ 0, "asc" ]],
							
							language: { search: "",searchPlaceholder: "Search..." }
							}
                                                                
	);
var nonpotable =	jQuery('#tablenonpoinvoices').DataTable(
							{
							"order": [[ 0, "asc" ]],
							
							language: { search: "",searchPlaceholder: "Search..." }
							}
                                                                
	);

});
function searchButton()
{
    var url = "erp";
    var uiactionName = "getVendorInputList";
    var params = "";
        var val = document.getElementById("txtSearch").value;
        if(val==="PO Number" ){
        var txtPONumber = document.getElementById("txtSearch1").value; 
        if (txtPONumber.indexOf('-') > -1)
{
    var txtPONumber = txtPONumber.substring(0, txtPONumber.lastIndexOf("-"));
}
   
      params = "uiActionName=" + uiactionName
            + "&txtPONumber=" + txtPONumber                    
            ;}
        else if(val==="Application ID" ){
        var txtapplid= document.getElementById("txtSearch1").value;
          params = "uiActionName=" + uiactionName         
            + "&txtapplid=" + txtapplid                         
            ;}
        else if(val==="Invoice Number" ){
        var txtInvoiceNumber = document.getElementById("txtSearch1").value;
          params = "uiActionName=" + uiactionName          
            + "&txtInvoiceNumber=" + txtInvoiceNumber                     
            ;}
        else if(val==="Invoice Date(dd-mon-yyyy)" ){
        var txtinvdate = document.getElementById("txtSearch1").value;
          params = "uiActionName=" + uiactionName                  
            + "&txtinvdate=" + txtinvdate           
            ;}
       else if(val==="ALL"  ){
          params = "uiActionName=" + uiactionName                                        
            ;}
        else {params = "uiActionName=" + uiactionName                                        
            ;
            
        }
    postForm(url, params, "POST");
    
}
  function getPOSearchList(){
var val = document.getElementById("txtSearch").value;
  var type = "poList";
  if(val==="PO Number" ){
     var poOptions = JSON.parse(document.getElementById("poOptions").value); 
    autocomplete(document.getElementById("txtSearch1"), poOptions,type);
  } 
  if (val==="Invoice Number" ){
       var type = "invList";
      var invNumOptions = JSON.parse(document.getElementById("invNumOptions").value); 
    autocomplete(document.getElementById("txtSearch1"), invNumOptions,type);
  }
  if (val==="Application ID" ){
      var type = "applList";
  var applIdOptions = JSON.parse(document.getElementById("applIdOptions").value); 
  autocomplete(document.getElementById("txtSearch1"), applIdOptions,type);
  }
  if(val==="Invoice Date(dd-mon-yyyy)" ){
      var type = "invDate";
      var invDateOptions = JSON.parse(document.getElementById("invDateOptions").value); 
  autocomplete(document.getElementById("txtSearch1"), invDateOptions,type);
  }

  }
function getInvoiceNum()
{
    var txtPONumber = document.getElementById("txtPONumber").value;

    var url = "erp";
    var uiactionName = "getPOList";
    var params = "uiActionName=" + uiactionName
            + "&txtPONumber=" + txtPONumber           
            ;

    postForm(url, params, "POST");
}

function getList(){
    
    var txtPONumber = document.getElementById("txtPONumber").value;
    var txtInvoiceNumber = document.getElementById("txtInvoiceNumber").value; 
    var txtFrmDt = document.getElementById("txtFrmDt").value;
    var txtToDt = document.getElementById("txtToDt").value;     

    
    var url = "erp";
    var uiactionName = "getVendorList";
    var params = "uiActionName=" + uiactionName
            + "&txtPONumber=" + txtPONumber           
            + "&txtInvoiceNumber=" + txtInvoiceNumber           
            + "&txtFrmDt=" + txtFrmDt           
            + "&txtToDt=" + txtToDt           
            ;

    postForm(url, params, "POST");
}
function autocomplete(inp, arr, type) {
  var currentFocus;
 inp.addEventListener("input", function(e) {        
     
      var a, b, i, val = this.value;
      if(val==" ")
          val="";
     /*close any already open lists of autocompleted values*/
      closeAllLists();
    
      currentFocus = -1;
      /*create a DIV element that will contain the items (values):*/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      /*append the DIV element as a child of the autocomplete container:*/
      this.parentNode.appendChild(a);
      /*for each item in the array...*/
      for (i = 0; i < arr.length; i++) {
        /*check if the item starts with the same letters as the text field value:*/
             if (arr[i].toUpperCase().indexOf(val.toUpperCase()) > -1) { 
          /*create a DIV element for each matching element:*/
          
          b = document.createElement("DIV");
          b.innerHTML =  arr[i];  
          /*insert a input field that will hold the current array item's value:*/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
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
   // alert("type :- " + type);
    if(type == 'poList'){
   // getInvoiceNum();
    }
});
}
