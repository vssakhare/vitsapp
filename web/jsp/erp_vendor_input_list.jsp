
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="in.emp.vendor.bean.POBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page import="in.emp.vendor.bean.VendorInputBean"%>
<%@page import="in.emp.hrms.bean.HRMSUserBean"%>
<%@page import="in.emp.vendor.bean.VendorBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.LinkedList"%>
<%@page import="in.emp.user.bean.UserBean"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%
    //LinkedList POList = new LinkedList();
    LinkedList VendorInputList = new LinkedList();
    //LinkedList vendorInvoiceList= new LinkedList();
   String lang="";
    String recordsVar = "No Records To Display !!!";
    lang = request.getParameter("language");
    String uiAction = "";
    
    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }
    
     VendorPrezData vendorPrezDataObj = new VendorPrezData();
     
     
    String UserNumber = (String)request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
    LinkedList vendorInputList = new LinkedList();
    LinkedList nonPoVendorList = new LinkedList();

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_INPLIST_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_INPLIST_SESSION_DATA);        
        //POList = (LinkedList) vendorPrezDataObj.getPOList();
        VendorInputList =  (LinkedList) vendorPrezDataObj.getVendorInputList();
        //vendorInvoiceList =  (LinkedList)vendorPrezDataObj.getVendorInvoiceList();
         for (VendorInputBean o : (LinkedList<VendorInputBean>) VendorInputList) {
             if(o.getModuleSaveFlag()!= null){
                 if (o.getModuleSaveFlag().equals("NON_PO") )  {
                       nonPoVendorList.add(o);
                   } else
                     {
                      vendorInputList.add(o);
                }
             }
            }
    }

    String viewAction = "";
     String viewActionVerified = "";
    String PODescHdr= "";
    String viewEmpActionVerified="";
    if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)) {
            //actionStatus = "Saved";
            viewAction = "redir";
            viewActionVerified ="redirVerifiedform";
             viewEmpActionVerified="redirEmpVerifiedform";
        }

    }

    
    
   
   
 
%>


<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Vendor Input List</title>
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <link href="css/autocomplete.css" rel="stylesheet" />
          <script src="assets/js/jquery-1.10.2.js?v=<%=System.getProperty("VERSION")%>"></script>
        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type="text/javascript" language="JavaScript" src="<%=ApplicationConstants.JS_PATH%>erp_vendor_input_list.js"></script>
    </head>
    <body>  
        
         <c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session" />
</c:if>
        <form name="searchform" method="post" action="<%=ApplicationUtils.getActionURL(request)%>">
            <div id="wrapper">
                <%@ include file="nav_emp_header.jsp"%>
                <!-- /. NAV TOP  -->
                <%@ include file="emp_nav_vmenu.jsp"%>
                <!-- /. NAV SIDE  -->
                <div id="page-wrapper" >

                    <div id="page-inner" style="min-height:500px;">


                        <input type="hidden" name=uiActionName" id="uiActionName" value="<%=uiAction%>"/>
                        <input type="hidden" name="redirEmpVerifiedform" id="redirEmpVerifiedform" value="<%=ApplicationConstants.UIACTION_GET_EMP_VERIFIED_FORM_PS%>"/>
                        <input type="hidden" name="redir" id="redir" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM%>"/>
                        <input type="hidden" name="redirVerifiedform" id="redirVerifiedform" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_VERIFIED_FORM%>"/>
                        <input type="hidden" name="deleteAction" id="deleteAction" value="<%=ApplicationConstants.UIACTION_POST_VENDORINPUT_LIST%>"/>
                        <input type="hidden" name="deleteRedirect" id="deleteRedirect" value="<%=ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST%>"/>
                        <input type="hidden" name="poOptions" id="poOptions" value='[<% if(!ApplicationUtils.isBlank(VendorInputList)) {
                                                    int i=0;
                                                    Set <String>poList = new HashSet<String>();
                                                 for (VendorInputBean inpoBean : (LinkedList<VendorInputBean>) VendorInputList) {
                                                     if(inpoBean.getPONumber()!=null){
                                                         if(inpoBean.getPODesc()==null){
                                                             poList.add(inpoBean.getPONumber());
                                                         }
                                                         else{
                                                     poList.add(inpoBean.getPONumber() + "-" + inpoBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-',' ') + "\"");
                                                         }
                                                         i++;
                                                     }
                                                 }
                                                     
                                              int j=0;
                                                 for (String d:poList)
                                                 {
                                                     if(j!=0){ out.print(" , ");}
                                                     out.print("\"" + d.replace('"', ' ').replace('\'', ' ')  +"\"" );
                                                     j++;
                                                 }
                                                    }
                                                %>]'/>
                       
                         <input type="hidden" name="invNumOptions" id="invNumOptions" value='[<% if(!ApplicationUtils.isBlank(VendorInputList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                    
                                                 for (VendorInputBean invBean : (LinkedList<VendorInputBean>) VendorInputList) {
                                                      if(i!=0){ out.print(" , ");}
                                                  out.print("\"" + invBean.getVendorInvoiceNumber().replace('"', ' ').replace('\'', ' ') + "\""  );
                                                     i++;
                                                 }
                                                    }
                                                %>]'/>
                         
                         <input type="hidden" name="applIdOptions" id="applIdOptions" value='[<% if(!ApplicationUtils.isBlank(VendorInputList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                    
                                                 for (VendorInputBean inpBean : (LinkedList<VendorInputBean>) VendorInputList) {
                                                      if(i!=0){ out.print(" , ");}
                                                  out.print("\"" + inpBean.getApplId().replace('"', ' ').replace('\'', ' ') + "\""  );
                                                     i++;
                                                 }
                                                    }
                                                %>]'/>
                        
                           <input type="hidden" name="invDateOptions" id="invDateOptions" value='[<% if(!ApplicationUtils.isBlank(VendorInputList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                    		Set <String>dates = new HashSet<String>();
                                                      
                                                       DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
                                                 for (VendorInputBean indBean : (LinkedList<VendorInputBean>) VendorInputList) {
                                                      dates.add(df3.format(indBean.getVendorInvoiceDate()));
                                                     i++;
                                                 }
                                                 int j=0;
                                                 for (String d:dates)
                                                 {
                                                     if(j!=0){ out.print(" , ");}
                                                     out.print("\"" + d.replace('"', ' ').replace('\'', ' ')  +"\"" );
                                                     j++;
                                                 }
                                                    }
                                                %>]'/>
                         
                         
                         
                    
                        <%@ include file="navJs.jsp"%>
                        <!-- Ends header content -->
                        <div class="content_container">

                            <table width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                                <tr>
                                    <td class="module_tbl_tl" >&nbsp;</td>
                                    <td class="tbl_hline_top">&nbsp;</td>
                                    <td class="module_tbl_tr">&nbsp;</td>
                                </tr>
                                <tr> <!-- Start of Network Search Results tr -->

                                    <td class="bg_white">  <!-- Start of Network Search Results td -->
                                        <!--<div class="form">-->  <!-- Start of  div  form -->
                                        <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                           
                                            <div class="col-md-12"><h3><fmt:message key='Vendor Invoice Details'/></h3></div>
                                            <div >&nbsp;</div> 

                                        </div>
                                    </td>
                                </tr>
                                </table>
                             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="col-md-1">
                                </td>
                                <td class="col-md-2">
                                    <% if ((uiAction.equals(ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)) || (uiAction.equals(ApplicationConstants.UIACTION_POST_VENDORINPUT_LIST))) {
                                    %>
                                    <div >
                                        <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM)%>" class="btn btn-success"><fmt:message key='New Invoice Details'/><img src="images/icon_new.gif" alt="Add" width="25" height="25" border="0" /></a>
                                    </div>
                                    <%
                                        }
                                    %>
                                </td>
                                <td class="col-md-1">
                                </td>
                                <td class="col-md-2">
                                
                                </td>
                            </tr>
                            
                        </table>
                             
                                
                                <table  class="table" style="width:100%" id="tblone1" border="0" cellspacing="0" cellpadding="1" >
                                    <tr><th><left><fmt:message key='Search Criteria'/></left></th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                     <td colspan="2">
                                                         
                                                        <select id="txtSearch" name="txtSearch" class="form-control" style="width: 100%" >
                                                            <option>- <fmt:message key='SELECT'/> -</option> 
                                                            
                                                            <option value=""ALL"><fmt:message key='ALL'/></option> 
                                                            <option value="PO Number"><fmt:message key='PO/Project Id Description'/></option>
                                                            <option value="Application ID"><fmt:message key='Application ID'/></option>
                                                            <option value="Invoice Number"><fmt:message key='Invoice Number'/></option>
                                                            <option value="Invoice Date(dd-mon-yyyy)"><fmt:message key='Invoice Date(dd-mon-yyyy)'/></option>
                                                           
                                                        </select>
                                                      
                                                          </td>
                                                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          <td class="text-right h5">Value:</td>
                                                                <td id="myDropdown">
                                                               <div class="autocomplete" style="width:300px;">
                                                                   <input class="form-control" type="text" text-align="left" name="txtSearch1" value ="<%=PODescHdr%>"  id="txtSearch1"  style="width: 100%" title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> onkeypress="getPOSearchList();" />
                                                            </div>
                                                     </td>
              
                                                 &nbsp;&nbsp;&nbsp;
                                                 <td colspan="3" align="center"><input type="button" name="btnFile" id="btnFile" value=<fmt:message key='Search'/>  class="btn btn-success" style="height:30px;width:70px" onclick="searchButton()"/> </td> 
                                                 
                                               </tr>
                                                </table>
                          
                            <!--<div class="buttons_holder" align="right">
                                <input name="Reset" type="button" class="button_bg" id="Reset" value="Clear" />
                                <input name="Search" type="submit" class="button_bg" id="Search" value="Search" />
                            </div> -->

                            <!--%@ include file="paging_buttons.jsp" %-->
                            <!--<div class="seperator_1"></div>-->

                            <!--  <table class="sub_heading_bg_blue" border="0" cellspacing="0" cellpadding="2" id="table_grid">-->
                           <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#vendorInputList"><fmt:message key='Vendor Input Invoices'/></a></li>
                            <li ><a data-toggle="tab" href="#nonPoList"><fmt:message key='Non PO Invoices'/></a></li>
                         </ul>
                         <div class="tab-content">
                            <%@ include file="sort_paging_hidden_fields.jsp" %>                      
                              <div id="vendorInputList" class="tab-pane fade in active">
                                <div class="content_container_sub"> 
                            <div class="row">
                                <div class="col-lg-12 col-md-12">  
                                      <div class="table-responsive">
                                       <%  if (vendorInputList.size() != 0)
                                           {
                                       %>   
                                  
                                        
                                         <div class="col-md-12 text-center"><h3><fmt:message key='List of Invoices'/></h3></div>
                                        <table class="table" id="table_ven_input_inv">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th>#</th> 
                                                    <th><fmt:message key='Appl ID'/></th> 
                                                    <th><fmt:message key='Application Date'/></th> 
                                                   <!--  <th><fmt:message key='Module Type'/></th> -->
                                                    <th><fmt:message key='PO/Project Id Description'/></th>  
                                                      <th><fmt:message key='Invoice Type'/></th>  
                                                    <th><fmt:message key='Vendor Invoice Number'/></th>  
                                                    <th><fmt:message key='Vendor Invoice Date'/></th> 
                                                    <th><fmt:message key='Vendor Invoice Amount (Incl. Taxes)'/></th> 
                                                    <th><fmt:message key='MSEDCL Inward Number'/></th>
                                                    <th><fmt:message key='MSEDCL Inward Date'/></th>                                                                                                       
                                                    <!--<th>Invoice From Date</th>
                                                    <th>Invoice To Date</th>-->
                                                    <th><fmt:message key='Status'/></th> 
                                                    <th><fmt:message key='Invoice Status'/></th> 
                                                    <th><fmt:message key='Pending for verification Since days'/></th> 
                                                    <th><fmt:message key='View'/></th> 
                                                    
                                                </tr>
                                               
                                            </thead>
                                            <tbody>
                                         <%
                                
                                     int j = 0;
                                   
                                    
                                    for (VendorInputBean vendorInputBean : (LinkedList<VendorInputBean>) vendorInputList) {
                                                    j++;
                                    String ApplId = "";
                                    String ApplDate ="";
                                    String PONumber = "";
                                    String PODesc = "";
                                    String InvoiceNum = "";
                                    String InvoiceDate = "";
                                    String InvoiceAmt = "";
                                    String InwardNum = "";
                                    String InwardDate = "";
                                    String Module = "";
                                    String Module_dtl="";
                                    //String InvoiceFrmDate = "";
                                    //String InvoiceToDate = "";
                                    String Status = "";
                                    String Invoice_Status="";
                                    String PendingSince ="";
                                    String InvoiceType = "";
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getApplId())) {
                                       ApplId = vendorInputBean.getApplId();
                                     } 
                                     if ((vendorInputBean.getApplDate() != null) && !(vendorInputBean.getApplDate().equals("null")) && !(vendorInputBean.getApplDate().equals(""))) {
                                      ApplDate = ApplicationUtils.dateToString(vendorInputBean.getApplDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                    
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getPONumber())) {
                                       PONumber = vendorInputBean.getPONumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getPODesc())) {
                                       PODesc = PONumber + '-' +vendorInputBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-',' ');
                                     }
                                     else{
                                        PODesc = PONumber; 
                                     }
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getSelectedModuleType())) {//to differentiate between proj id and proj system
                                       Module = vendorInputBean.getSelectedModuleType();
                                       if(Module.equals("PM")){
                                           Module_dtl="Procurement/Works";
                                       }else if(Module.equals("PS")){
                                           Module_dtl="Project System";
                                       }
                                     }
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())) {
                                       InvoiceNum = vendorInputBean.getVendorInvoiceNumber();
                                     }
                                     
                                      if ((vendorInputBean.getVendorInvoiceDate() != null) && !(vendorInputBean.getVendorInvoiceDate().equals("null")) && !(vendorInputBean.getVendorInvoiceDate().equals(""))) {
                                      InvoiceDate = ApplicationUtils.dateToString(vendorInputBean.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceAmount())) {
                                       InvoiceAmt = vendorInputBean.getVendorInvoiceAmount();
                                       
                                              InvoiceAmt =  ApplicationUtils.formatAmount(Double.valueOf(InvoiceAmt));
                                     }
                                      
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getInwardNumber())) {
                                       InwardNum = vendorInputBean.getInwardNumber();
                                     }
                                     
                                      if ((vendorInputBean.getInwardDate() != null) && !(vendorInputBean.getInwardDate().equals("null")) && !(vendorInputBean.getInwardDate().equals(""))) {
                                      InwardDate = ApplicationUtils.dateToString(vendorInputBean.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                      
                                  //  if ((vendorInputBean.getInvoiceFromDate() != null) && !(vendorInputBean.getInvoiceFromDate().equals("null")) && !(vendorInputBean.getInvoiceFromDate().equals(""))) {
                                      //InvoiceFrmDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    //}
                                     // if ((vendorInputBean.getInvoiceToDate() != null) && !(vendorInputBean.getInvoiceToDate().equals("null")) && !(vendorInputBean.getInvoiceToDate().equals(""))) {
                                        //InvoiceToDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    //}
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getSaveFlag())) {
                                       Status = vendorInputBean.getSaveFlag();
                                     }
                                       if (!ApplicationUtils.isBlank(vendorInputBean.getInvoiceStatus())) {
                                       Invoice_Status = vendorInputBean.getInvoiceStatus();
                                     }
                                      
                                       if (!ApplicationUtils.isBlank(vendorInputBean.getPendingSince())) {
                                       PendingSince = vendorInputBean.getPendingSince();
                                     }
                                        if (!ApplicationUtils.isBlank(vendorInputBean.getINVOICE_TYPE())) {
                                       InvoiceType = vendorInputBean.getINVOICE_TYPE();
                                     }
                            %>

                                       

                                          
                                            
                                                <tr class="info" >
                                            <td><%=j%></td>
                                            <td width="5%"><%=ApplId%></td>
                                            <td width="8%"><%=ApplDate%></td> 
                                          <!--   <td width="4%"><%=Module_dtl%></td>-->
                                            <td width="12%"><%=PODesc%></td>
                                            <td width="4%"><%=InvoiceType%></td>
                                            <td><%=InvoiceNum%></td>
                                            <td width="7%"><%=InvoiceDate%></td>                                           
                                            <td width="7%"><%=InvoiceAmt%></td>                                            
                                            <td><%=InwardNum%></td>
                                            <td width="7%"><%=InwardDate%></td>                                            
                                           
                                            <td><%=Status%></td> 
                                            <% if (Status.equals("Verified")) {%>
                                             <td width="12%"><%=Invoice_Status%></td> 
                                             <% } else{%>
                                             <td width="12%"></td> 
                                             <% } %>
                                           <td width="7%"><center><%=PendingSince%></center></td> 
                                           <% if (Status.equals("Verified") && (!Module.equals("PS"))) {%>
                                          
                                       <td><a href="#nogo" onclick="viewEmpAppVerified('<%=ApplId%>','<%=InvoiceNum.toUpperCase().replaceAll("[^a-zA-Z0-9]", "")%>',' <%=UserNumber%>','<%=viewActionVerified%>','<%=PONumber%>','<%=Module%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                            
                                       <% } else if (Status.equals("Verified") && (Module.equals("PS"))) {%>
                                          
                                       <td><a href="#nogo" onclick="viewEmpAppVerified_PS('<%=ApplId%>','<%=InvoiceNum.toUpperCase().replaceAll("[^a-zA-Z0-9]", "")%>',' <%=UserNumber%>','<%=viewEmpActionVerified%>','<%=PONumber%>','<%=Module%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                            
                                       <% } else{ %>
                                            <td><a href="#nogo" onclick="viewEmpApp1('<%=ApplId%>', '<%=UserNumber%>', '<%=viewAction%>','<%=PONumber%>','<%=Module%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                         <% } %>
                                             </tr>
                                           <% } %>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>                                                          

                            <%  } else {%>
                            <div class="row">                
                                <div class="col-lg-12 col-md-12">                        
                                    <div class="table-responsive">
                                        <div class="col-md-12 text-center"><h3>List of Invoices</h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                        
                                                     <th><fmt:message key='Appl ID'/></th> 
                                                    <th><fmt:message key='Application Date'/></th>    
                                                  <!--   <th><fmt:message key='Module Type'/></th> -->
                                                    <th><fmt:message key='PO/Project Id Description'/></th>  
                                                       <th><fmt:message key='Invoice Type'/></th>  
                                                    <th><fmt:message key='Vendor Invoice Number'/></th>  
                                                    <th><fmt:message key='Vendor Invoice Date'/></th> 
                                                    <th><fmt:message key='Vendor Invoice Amount (Incl. Taxes)'/></th> 
                                                    <th><fmt:message key='MSEDCL Inward Number'/></th>
                                                    <th><fmt:message key='MSEDCL Inward Date'/></th>                                                                                                       
                                                    <!--<th>Invoice From Date</th>
                                                    <th>Invoice To Date</th>-->
                                                    <th><fmt:message key='Status'/></th> 
                                                    <th><fmt:message key='Invoice Status'/></th> 
                                                    <th><fmt:message key='Pending for verification Since days'/></th> 
                                                    <th><fmt:message key='View'/></th> 
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="info" align="center" >                                                      
                                                    <td colspan="14"><%=recordsVar%></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            
                                                
                            <% }%>    
                            <input type = "hidden" name="lastValue" id="lastValue" value="" />
                            <input type = "hidden" name="lastValueDataType"   id="lastValueDataType" value="" />
                            </div>  
                            </div>
                             <div id="nonPoList" class="tab-pane fade ">
                                <div class="content_container_sub">
                                    
                                     <div class="row">
                                <div class="col-lg-12 col-md-12">  
                                      <div class="table-responsive">
                                       <%
                                           if (nonPoVendorList != null) {
                                       %>   
                                  
                                        
                                         <div class="col-md-12 text-center"><h3><fmt:message key='List of Invoices'/></h3></div>
                                        <table class="table" id="tablenonpoinvoices">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th>#</th> 
                                                    <th><fmt:message key='Appl ID'/></th> 
                                                    <th><fmt:message key='Application Date'/></th> 
                                                 <!--    <th><fmt:message key='Module Type'/></th> -->
                                                    <th><fmt:message key='PO/Project Id Description'/></th>  
                                                    <th><fmt:message key='Vendor Invoice Number'/></th>  
                                                    <th><fmt:message key='Vendor Invoice Date'/></th> 
                                                    <th><fmt:message key='Vendor Invoice Amount (Incl. Taxes)'/></th> 
                                                    <th><fmt:message key='MSEDCL Inward Number'/></th>
                                                    <th><fmt:message key='MSEDCL Inward Date'/></th>                                                                                                       
                                                    <!--<th>Invoice From Date</th>
                                                    <th>Invoice To Date</th>-->
                                                    <th><fmt:message key='Status'/></th> 
                                                    <th><fmt:message key='Pending for verification Since days'/></th> 
                                                    <th><fmt:message key='View'/></th> 
                                                    
                                                </tr>
                                               
                                            </thead>
                                         <%
                                
                                     int j = 0;
                                   
                                    
                                    for (VendorInputBean vendorInputBean : (LinkedList<VendorInputBean>) nonPoVendorList) {
                                                    j++;
                                    String ApplId = "";
                                    String ApplDate ="";
                                    String PONumber = "";
                                    String PODesc = "";
                                    String InvoiceNum = "";
                                    String InvoiceDate = "";
                                    String InvoiceAmt = "";
                                    String InwardNum = "";
                                    String InwardDate = "";
                                    String Module = "";
                                    String ModuleSaveFlag="";
                                    String Module_dtl="";
                                    //String InvoiceFrmDate = "";
                                    //String InvoiceToDate = "";
                                    String Status = "";
                                    String PendingSince ="";
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getApplId())) {
                                       ApplId = vendorInputBean.getApplId();
                                     } 
                                     if ((vendorInputBean.getApplDate() != null) && !(vendorInputBean.getApplDate().equals("null")) && !(vendorInputBean.getApplDate().equals(""))) {
                                      ApplDate = ApplicationUtils.dateToString(vendorInputBean.getApplDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                    
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getPONumber())) {
                                       PONumber = vendorInputBean.getPONumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getPODesc())) {
                                       PODesc = PONumber + '-' +vendorInputBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-',' ');
                                     } else{
                                        PODesc = PONumber; 
                                     }
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getSelectedModuleType())) {//to differentiate between proj id and proj system
                                       Module = vendorInputBean.getSelectedModuleType();
                                         if(Module.equals("PM")){
                                           Module_dtl="Procurement/Works";
                                       }else if(Module.equals("PS")){
                                           Module_dtl="Project System";
                                       }
                                     }
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())) {
                                       InvoiceNum = vendorInputBean.getVendorInvoiceNumber();
                                     }
                                     
                                      if ((vendorInputBean.getVendorInvoiceDate() != null) && !(vendorInputBean.getVendorInvoiceDate().equals("null")) && !(vendorInputBean.getVendorInvoiceDate().equals(""))) {
                                      InvoiceDate = ApplicationUtils.dateToString(vendorInputBean.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceAmount())) {
                                       InvoiceAmt = vendorInputBean.getVendorInvoiceAmount();
                                       
                                        InvoiceAmt =  ApplicationUtils.formatAmount(Double.valueOf(InvoiceAmt));
                                     }
                                      
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getInwardNumber())) {
                                       InwardNum = vendorInputBean.getInwardNumber();
                                     }
                                     
                                      if ((vendorInputBean.getInwardDate() != null) && !(vendorInputBean.getInwardDate().equals("null")) && !(vendorInputBean.getInwardDate().equals(""))) {
                                      InwardDate = ApplicationUtils.dateToString(vendorInputBean.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                      
                                  //  if ((vendorInputBean.getInvoiceFromDate() != null) && !(vendorInputBean.getInvoiceFromDate().equals("null")) && !(vendorInputBean.getInvoiceFromDate().equals(""))) {
                                      //InvoiceFrmDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    //}
                                     // if ((vendorInputBean.getInvoiceToDate() != null) && !(vendorInputBean.getInvoiceToDate().equals("null")) && !(vendorInputBean.getInvoiceToDate().equals(""))) {
                                        //InvoiceToDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    //}
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getSaveFlag())) {
                                       Status = vendorInputBean.getSaveFlag();
                                     }
                                       if (!ApplicationUtils.isBlank(vendorInputBean.getPendingSince())) {
                                       PendingSince = vendorInputBean.getPendingSince();
                                     }
                                         if (!ApplicationUtils.isBlank(vendorInputBean.getModuleSaveFlag())) {
                                             ModuleSaveFlag=vendorInputBean.getModuleSaveFlag();
                                         }
                            %>

                                       

                                          
                                            <tbody>
                                                <tr class="info" >
                                            <td><%=j%></td>
                                            <td width="5%"><%=ApplId%></td>
                                            <td width="8%"><%=ApplDate%></td> 
                                           <!--  <td width="4%"><%=Module_dtl%></td>-->
                                            <td width="12%"><%=PODesc%></td>
                                            <td><%=InvoiceNum%></td>
                                            <td width="7%"><%=InvoiceDate%></td>                                           
                                            <td width="12%"><%=InvoiceAmt%></td>                                            
                                            <td><%=InwardNum%></td>
                                            <td width="7%"><%=InwardDate%></td>                                            
                                           
                                            <td><%=Status%></td> 
                                           <td width="10%"><center><%=PendingSince%></center></td> 
                                            <td><a href="#nogo" onclick="viewEmpApp1('<%=ApplId%>', <%=UserNumber%>, '<%=viewAction%>','<%=PONumber%>','<%=ModuleSaveFlag%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                         
                                             </tr>
                                           <% } %>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>                                                          

                            <%  } else {%>
                            <div class="row">                
                                <div class="col-lg-12 col-md-12">                        
                                    <div class="table-responsive">
                                        <div class="col-md-12 text-center"><h3>List of Invoices</h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                        
                                                     <th><fmt:message key='Appl ID'/></th> 
                                                    <th><fmt:message key='Application Date'/></th>    
                                                  <!--   <th><fmt:message key='Module Type'/></th> -->
                                                    <th><fmt:message key='PO/Project Id Description'/></th>  
                                                    <th><fmt:message key='Vendor Invoice Number'/></th>  
                                                    <th><fmt:message key='Vendor Invoice Date'/></th> 
                                                    <th><fmt:message key='Vendor Invoice Amount (Incl. Taxes)'/></th> 
                                                    <th><fmt:message key='MSEDCL Inward Number'/></th>
                                                    <th><fmt:message key='MSEDCL Inward Date'/></th>                                                                                                       
                                                    <!--<th>Invoice From Date</th>
                                                    <th>Invoice To Date</th>-->
                                                    <th><fmt:message key='Status'/></th> 
                                                    <th><fmt:message key='Pending for verification Since days'/></th> 
                                                    <th><fmt:message key='View'/></th> 
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="info" align="center" >                                                      
                                                    <td colspan="12"><%=recordsVar%></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            
                                                
                            <% }%> 
                                    
                                    
                                     </div>
                             </div>
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" class="btn btn-danger"><fmt:message key='Back'/></a>  


                           
                            
                            

                        
                               
                            <!--%@ include file="paging_buttons.jsp" %-->
                        

                        <!-- /. PAGE inner  -->
                    </div>
                    <!-- /. PAGE wrapper  -->
                         </div>
                        </div></div>
                    </div>
                </div>
                <!-- /.  wrapper  -->                                   
            
            <%@include file="nav_emp_footer.jsp" %>



            <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
            <!-- JQUERY SCRIPTS -->
            
            <!-- BOOTSTRAP SCRIPTS -->
            <script src="assets/js/bootstrap.min.js"></script>
            <!-- CUSTOM SCRIPTS -->
             <script src="assets/js/custom.js?v=<%=System.getProperty("VERSION")%>"></script>
            <script src="assets/js/custom.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
   
         
    </body>
</html>


