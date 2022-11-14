
<%@page import="in.emp.common.ApplicationConstants"%>
<%
// common paging logic and hidden fields -->

	String lastRow = (String)request.getAttribute("lastRow");	
	String hasNext = (String)request.getAttribute("hasNext");
	String hasPrevious = (String)request.getAttribute("hasPrevious");
	String pageSize = (String)System.getProperty("UI_PAGE_SIZE");	
	String cachePageSize = (String)System.getProperty("CACHE_PAGE_SIZE");	

	//System.out.println("sort_Paging_hidden Fieldas  :: :: PageSize" +pageSize);
	//System.out.println("sort_Paging_hidden Fieldas  :: :: lastRow" +lastRow);

	String displayStr= "Page 1 of 1";
	String totalRecordsStr= "0 record(s) found";
	if(request.getAttribute("displayStr")!=null)
	{
	 displayStr = (String)request.getAttribute("displayStr");
	}
	if(request.getAttribute("totalRecordsStr")!=null)
	{
	 totalRecordsStr = (String)request.getAttribute("totalRecordsStr");
	}

// common sorting logic and hidden fields -->

	String columnName = "";
	String sortOrder = "";
	if(request.getAttribute("columnName") != null)
		columnName  = (String)request.getAttribute("columnName");
	if(request.getAttribute("sortOrder") != null)
		 sortOrder = (String)request.getAttribute("sortOrder");
	 
	String sortImage = "<img src='" + ApplicationConstants.IMAGE_PATH + "arrow_sort_down.gif' border='0'>";

	if(sortOrder != null && sortOrder.equalsIgnoreCase("desc")) 
		sortImage = "<img src='" + ApplicationConstants.IMAGE_PATH + "arrow_sort.gif' border='0'>";

%>

<!-- Hidden Fields for paging-->
<input type="hidden" name="lastRow" id="lastRow" value="<%=lastRow%>">
<input type="hidden" name="pageSize" id = "pageSize" value="<%=pageSize%>">
<input type="hidden" name="cachePageSize" id = "cachePageSize" value="<%=cachePageSize%>">
<input type="hidden" name="nextOrPrevious" id="nextOrPrevious">
<input type="hidden" name="subAction" id="subAction">

<!-- Hidden Fields for sorting-->
<input type="hidden" name="columnName" id="columnName" value="<%=columnName%>">
<input type="hidden" name="sortOrder" id="sortOrder" value="<%=sortOrder%>">


