<!-- Common Javascript functions -->
<script language="JavaScript">
<!--
// To navigate between pages.
function pagingNavigation(nextOrPrevious)
{
	document.searchform.nextOrPrevious.value = nextOrPrevious;
	var cachepageSize = document.getElementById('cachePageSize').value;
	var pageSize = document.getElementById('pageSize').value;
	var lastRow = document.getElementById('lastRow').value;
	var calc = Math.ceil(lastRow/pageSize) % cachepageSize;
	var isAjaxPaging = false;

//		alert("nextOrPrevious ::" + nextOrPrevious);
//		alert("calc ::" + calc);
//		alert("lastRow " + lastRow);
//		alert("cachepageSize " + cachepageSize);
//		alert("pageSize " + pageSize);

	if(nextOrPrevious == 'go')
	{
		if(isNaN(document.searchform.pageNo[0].value))
			document.searchform.pageNo[0].value = '1';
		if(isNaN(document.searchform.pageNo[1].value))
			document.searchform.pageNo[0].value = '1';
		if(document.searchform.pageNo[0].value == '' && document.searchform.pageNo[1].value == '')
			document.searchform.pageNo[0].value = '1';
		if(document.searchform.pageNo[0].value == '' && document.searchform.pageNo[1].value != '')
			document.searchform.pageNo[0].value = document.searchform.pageNo[1].value;

		if(document.searchform.pageNo[0].value == '0' || document.searchform.pageNo[0].value == '-1')
			document.searchform.pageNo[0].value = '1';


		var modVal = document.searchform.pageNo[0].value % cachepageSize;		
		var expectedPageNum = document.searchform.pageNo[0].value;
		var currentpageNum = lastRow  / pageSize;
		
		//alert("modVal " + modVal);
		//alert("expectedPageNum " + expectedPageNum);
		//alert("currentpageNum " + currentpageNum);
		
		// Commeted by sandeep & rohan for change paging logic
		/*if(expectedPageNum != currentpageNum && Math.max(expectedPageNum,currentpageNum) - Math.min(expectedPageNum,currentpageNum) >= cachepageSize)
		{
			if(modVal != 0)
				document.getElementById('lastRow').value = (expectedPageNum-modVal)*pageSize;
			else
				document.getElementById('lastRow').value = (expectedPageNum-cachepageSize)*pageSize;

			isAjaxPaging = true;
		//	alert("isAjaxPaging " + isAjaxPaging);
		}*/

		var firstPageCurrent;
		var firstPageExpected;
		
		if(modVal != 0)
		{
				firstPageCurrent = (currentpageNum-modVal);
				firstPageExpected = (expectedPageNum-modVal);
		}
		else
		{
				firstPageCurrent = (currentpageNum-cachepageSize);
				firstPageExpected = (expectedPageNum-cachepageSize);
		}

		if(firstPageCurrent != firstPageExpected)
		{
			document.getElementById('lastRow').value = firstPageExpected*pageSize; 
			isAjaxPaging = true;
		}


	}
	else if (nextOrPrevious == 'next')
	{	
		if (calc == 0)
			isAjaxPaging = true;
	}
	else if (nextOrPrevious == 'previous')
	{
		if (calc == 1)
			isAjaxPaging = true;
	}
	
	if(isAjaxPaging)
	{		
		getSearchPaging();
	}
	else
		changeSubAction('paging');
}

// Sets the new sort order and column names
function sortColumns(columnNameNew)
{
	var sortOrderOld = document.searchform.sortOrder.value;
	var columnNameOld = document.searchform.columnName.value;
	if(columnNameNew != columnNameOld)
	{
		sortOrderNew = 'ASC';
	}
	if(columnNameNew == columnNameOld)
	{
		if(sortOrderOld == 'ASC')
			sortOrderNew = 'DESC';
		else
			sortOrderNew = 'ASC';
	}
	document.searchform.sortOrder.value = sortOrderNew;
	document.searchform.columnName.value = columnNameNew;
	changeSubAction('sort');
}
function manageCheckAll(obj){
    if(obj.checked == true){
        manageRecords('checkAll');
    } else {
        manageRecords('checkNone');
    }
}
function manageRecords(objOper){
    var chkStatus = "";
    if (objOper == 'go'){
        if(document.manageForm.operation.value != '-1'){
            if(document.manageForm.operation.value == '1'){
                var operation = "delete";
                var obj = document.manageForm.chkbox;
                var operationValue = "";
                for(x=0;x<obj.length;x++){
                    if(obj[x].checked){
                        if (operationValue == "")
                            operationValue += obj[x].value;
                        else
                            operationValue += ", " + obj[x].value;
                    }
                }
                //alert(operationValue);
                manageAllRecords(operation, operationValue);
            }
        }
    }else if(objOper == 'checkAll'){
        //var obj = document.getElementById("chkbox");
        var obj = document.manageForm.chkbox;
        checkAssigned(obj);
        document.getElementById("chkboxAll").checked = true;
    }else if(objOper == 'checkNone'){
        //var obj = document.getElementById("chkbox");
        var obj = document.manageForm.chkbox;
        for(x=0;x<obj.length;x++){
            obj[x].checked = false;
        }
        document.getElementById("chkboxAll").checked = false;
    }
}

function checkAssigned(obj){
    var Assigned = 0;
    var type = document.getElementById("uiType").value;
    var delStr = "";
    //alert(obj.length);
    if(obj.length > 0){
        for(x=0;x<obj.length;x++){
            if(obj[x].title == "1"){
                //alert("A");
                Assigned = 1;
            } else {
                //alert("U");
                obj[x].checked = true;
            }
        }
    } else {
        if(obj.checked == true) {
            if(obj.title == "1"){
                //alert("A");
                Assigned = 1;
                obj.checked = false;
            } else {
                //alert("U");
                obj.checked = true;
            }
        }
    }
    if(type == "meter") delStr = "Assigned Meter/s";
    else if (type == "regConsumer") delStr = "Billed Consumers";

    if(Assigned == 1) alert("Cannot select " + delStr);
}

function doSubmit(){
    document.manageForm.submit();
}

// Gives a new value to the hidden sub action field.
function changeSubAction(subActionVal)
{
	document.searchform.subAction.value = subActionVal;
	document.searchform.submit();
}

//-->
</script>