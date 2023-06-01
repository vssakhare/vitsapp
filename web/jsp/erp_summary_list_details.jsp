<%-- 
    Document   : erp_summary_list_details
    Created on : May 18, 2023, 5:23:42 PM
    Author     : Pooja Jadhav
--%>

<%@page import="java.util.List"%>
<%@page import="in.emp.legal.bean.LegalInvoiceInputBean"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>

<%@page import="java.util.LinkedList"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<script type="text/javascript" language="JavaScript" src="<%=ApplicationConstants.JS_PATH%>erp_vendor_legal_input_form.js"></script>
<%
     LinkedList vendorInputList = new LinkedList();
List<LegalInvoiceInputBean> legalInvoiceInputBeanList=null;
String uiAction = "";
      if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }
    String UserNumber = (String)request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
    if (request.getSession().getAttribute(ApplicationConstants.VIEW_SUMMARYLIST_SESSION_DATA) != null) {
        legalInvoiceInputBeanList = (List<LegalInvoiceInputBean>) request.getSession().getAttribute(ApplicationConstants.VIEW_SUMMARYLIST_SESSION_DATA);        
        for (LegalInvoiceInputBean o :  legalInvoiceInputBeanList) {
             if(o.getSaveFlag()!= null){
                 
                      vendorInputList.add(o);
             }
            }
    }
     String viewAction = "";
     String viewActionVerified = "";
    String PODescHdr= "";
    String viewEmpActionVerified="";
    if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_LEGAL_VENDOR_INVOICE)) {
            //actionStatus = "Saved";
            viewAction = "redirectUrl";
            viewActionVerified ="redirLegalAcceptedform";
             viewEmpActionVerified="redirLegalEmpVerifiedform";
        }

    }
   
    
    
 %>   
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Vendor Payment Tracking System</title>
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

      <jsp:include page="nav_jscss.jsp" />
    </head>
    <body>
          <div id="wrapper">
           
            <div id="page-wrapper" >
                <div id="page-inner" style="min-height:500px;">

                    <%@ include file="navJs.jsp"%>
                   
                    <div class="content_container">
                       
                       <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                           <%
                                if (vendorInputList.size() != 0) {
                            %>   
                           <div class="row">                
                                <div class="col-lg-12 col-md-12" align="center">                        
                                    <div class="table-responsive" style="padding-bottom:15px">
                                      <section>
  <!--for demo wrap-->
  <div class="text-center " stye="padding-bottom:20px">
      <h style="font-weight:bold;color:#1434A4" ><fmt:message key='List of Vendor Input Invoices'/></h></div>
      <br>
  <div style="overflow-x:auto;">
    <table class="table table-responsive" id="tableinputinvoices">
      <thead>
        <tr class="success">
        <th width="2%">#</th> 
                                                    <th width="5%"><fmt:message key='Appl ID'/></th> 
                                                    <th width="7%"><fmt:message key='Application Date'/></th> 
                                                   
                                                    <th width="10%"><fmt:message key='Vendor No.'/> <br><fmt:message key='& Name'/></th>
                                                    <th width="8%"><fmt:message key='Court Case No.'/></th>  
                                                      <th width="5%"><fmt:message key='Case Ref No.'/></th>  
                                                      <th width="11%"><fmt:message key='Court Name'/></th>
                                                      <th width="9%"><fmt:message key='Fee Type'/></th>
                                                      <th width="8%"><fmt:message key='Dealing Office'/></th>
                                                      <th width="8%">Department</th>
                                                    <th width="8%"><fmt:message key='Invoice Number'/></th>  
                                                    <th width="7%"><fmt:message key='Invoice Date'/></th> 
                                                    <th width="7%"><fmt:message key='Invoice Amount (Incl. Taxes)'/></th> 
                                                    <th width="8%"><fmt:message key='Status'/></th> 
                                                   
        </tr>
      </thead>
    
 
       
           
                                          
                                            <tbody>
                                         <%
                                
                                     int j = 0;
                                   
                                    
                                    for (LegalInvoiceInputBean vendorInputBean : (LinkedList<LegalInvoiceInputBean>) vendorInputList) {
                                                    j++;
                                    String ApplId = "";
                                    String ApplDate ="";
                                    String vendorNoName ="";
                                    String vendorNo="";
                                    String vendorName="";
                                    String courtCaseNo = "";
                                    String caseRefNo = "";
                                    String InvoiceNum = "";
                                    String InvoiceDate = "";
                                    String InvoiceAmt = "";
                                    String InwardNum = "";
                                    String InwardDate = "";
                                    String courtName = "";
                                     String feeType = "";
                                    String dealingOffice="";
                                    String department="";
                                    String Status = "";
                                    String Invoice_Status="";
                                    
                                    String InvoiceType = "";
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getApplId())) {
                                       ApplId = vendorInputBean.getApplId().toString();
                                     } 
                                     if ((vendorInputBean.getCreatedTimeStamp() != null) && !(vendorInputBean.getCreatedTimeStamp().equals("null")) && !(vendorInputBean.getCreatedTimeStamp().equals(""))) {
                                      ApplDate = ApplicationUtils.dateToString(vendorInputBean.getCreatedTimeStamp(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                    
                                    if (!ApplicationUtils.isBlank(vendorInputBean.getVendorNumber()) && !ApplicationUtils.isBlank(vendorInputBean.getVendorName())) {
                                       vendorNoName = vendorInputBean.getVendorNumber()+" "+vendorInputBean.getVendorName();
                                       vendorNo=vendorInputBean.getVendorNumber();
                                       vendorName=vendorInputBean.getVendorName();
                                     }
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getCourtCaseNo())) {
                                       courtCaseNo = vendorInputBean.getCourtCaseNo();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getCaseRefNo())) {
                                       caseRefNo = vendorInputBean.getCaseRefNo();
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getCourtName())) {//to differentiate between proj id and proj system
                                       courtName = vendorInputBean.getCourtName();
                                     }
                                       if (!ApplicationUtils.isBlank(vendorInputBean.getsFeeType())) {//to differentiate between proj id and proj system
                                       feeType = vendorInputBean.getsFeeType();
                                     }
                                      
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getInvoiceNumber())) {
                                       InvoiceNum = vendorInputBean.getInvoiceNumber();
                                     }
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getDealingOfficeCode())) {
                                       dealingOffice =vendorInputBean.getDealingOfficeName();
                                     }
                                      if ((vendorInputBean.getInvoiceDate()!= null) && !(vendorInputBean.getInvoiceDate().equals("null")) && !(vendorInputBean.getInvoiceDate().equals(""))) {
                                      InvoiceDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getInvoiceAmount())) {
                                       InvoiceAmt = vendorInputBean.getsAmount().toString();
                                       
                                              InvoiceAmt =  ApplicationUtils.formatAmount(Double.valueOf(InvoiceAmt));
                                     }
                                      
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getMsedclInwardNo())) {
                                       InwardNum = vendorInputBean.getMsedclInwardNo();
                                     }
                                     
                                      if ((vendorInputBean.getMsedclInwardDate()!= null) && !(vendorInputBean.getMsedclInwardDate().equals("null")) && !(vendorInputBean.getMsedclInwardDate().equals(""))) {
                                      InwardDate = ApplicationUtils.dateToString(vendorInputBean.getMsedclInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
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
                                       if (!ApplicationUtils.isBlank(vendorInputBean.getStatus())) {
                                       Invoice_Status = vendorInputBean.getStatus();
                                     }
                                       if (!ApplicationUtils.isBlank(vendorInputBean.getDeptCode()) && !ApplicationUtils.isBlank(vendorInputBean.getDeptName())) {
                                       
                                       department = vendorInputBean.getDeptCode()+" "+vendorInputBean.getDeptName();
                                     }
                                      
//                                       if (!ApplicationUtils.isBlank(vendorInputBean.getPendingSince())) {
//                                       PendingSince = vendorInputBean.getPendingSince();
//                                     }
//                                        if (!ApplicationUtils.isBlank(vendorInputBean.getINVOICE_TYPE())) {
//                                       InvoiceType = vendorInputBean.getINVOICE_TYPE();
//                                     }
                            %>

                                       

                                          
                                            
                                                <tr class="info" >
                                            <td width="2%"><%=j%></td>
                                            <td width="5%"><%=ApplId%></td>
                                            <td width="7%"><%=ApplDate%></td> 
                                            <td width="10%"><%=vendorNo%><br><%=vendorName%></td>                                            
                                            <td width="8%"><%=courtCaseNo%></td>
                                            <td width="5%"><%= caseRefNo %></td>
                                            <td width="11%"><%=courtName %></td>
                                            <td width="9%"><%=feeType %></td>
                                            <td width="8%"><%=dealingOffice %></td>
                                            <td width="8%"><%=department%></td>
                                            <td width="8%"><%=InvoiceNum %></td>
                                            <td width="7%"><%= InvoiceDate %></td>                                           
                                            <td width="7%"><%=InvoiceAmt%></td>                                            
                                            <!--<td><%=InwardNum%></td>
                                            <td width="7%"><%=InwardDate%></td>-->
                                           
                                         
                                            <% if (Status.equals("Accepted")) {%>
                                             <td width="8%"><%=Invoice_Status%></td> 
                                             <% } else{%>
                                             <td width="8%"><%=Invoice_Status%></td> 
                                             <% } %>

                                             </tr>
                                           <% } %>
                                            </tbody>
                                        </table>
                                        </div>
                         
        
                                     </section>
                                    </div>
                     

                                </div>
                            </div>  
                                            

                            <%  }%>
                       </div>
                    </div>
                </div>
            </div>
          </div>
                                            
