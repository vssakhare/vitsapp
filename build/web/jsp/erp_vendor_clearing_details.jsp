<%-- 
    Document   : erp_vendor_clearing_details
    Created on : 13 Jul, 2020, 12:26:46 PM
    Author     : Pooja Jadhav
--%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="in.emp.vendor.bean.ClearingDocDetails"%>
<%@page import="java.util.LinkedList"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%
     VendorPrezData vendorPrezDataObj = new VendorPrezData();
         LinkedList clearingDocDetailsList = new LinkedList();
    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_CLEARING_LIST_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_CLEARING_LIST_SESSION_DATA);        
    clearingDocDetailsList= (LinkedList) vendorPrezDataObj.getClearingDocDetails();  
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
                        <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr> <!-- Start of Network Search Results tr -->
                                <td class="bg_white">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <div class="col-md-12"><h3><fmt:message key='Clearing Document Details'/></h3></div>
                                        <div >&nbsp;</div>
                                    </div>
                                </td>
                            </tr>

                        </table>
                       <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                              <fieldset class="fldst_border">
                                     <legend></legend>
                                    <div class="row">                
                               <div class="col-lg-12 col-md-12">
                                  <div class="table-responsive">
                                       <table class="table">
                                            <thead>
                                         
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th>
                                                        <th class="text-center"><fmt:message key='CLEARING DOC NO'/></th>  
                                                    <th class="text-center" ><fmt:message key='CLARING DOC DATE'/></th>  
                                                    <th class="text-center"><fmt:message key='INVOICE NUMBER'/></th> 
                                                    <th class="text-center"><fmt:message key='INVOICE AMOUNT'/></th> 
                                                    <th class="text-center"><fmt:message key='INVOICE DATE'/></th> 
                                                    <th class="text-center"><fmt:message key='PO NUMBER'/></th>  
                                                                                                  
                                               </tr>
                                                 <%
                                
                                     int j = 0;
                                     int k = clearingDocDetailsList.size();
                                     for (ClearingDocDetails ClearingDocBean : (LinkedList<ClearingDocDetails>) clearingDocDetailsList) {
                                                    j++;
                                      String INVOICE_NUMBER = "";
                                    String INVOICE_Amt ="";
                                    String INVOICE_Date = "";
                                    String PO_NUMBER = "";
                                     String CLEARING_DOC_NO = "";
                                      String CLARING_DOC_DATE = "";
                                     if (!ApplicationUtils.isBlank(ClearingDocBean.getINVOICE_NUMBER())) {
                                       INVOICE_NUMBER = ClearingDocBean.getINVOICE_NUMBER();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(ClearingDocBean.getINVOICE_DATE())) {
                                      INVOICE_Date = ApplicationUtils.dateToString(ClearingDocBean.getINVOICE_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);

                                     }
                                     
                                     if (!ApplicationUtils.isBlank(ClearingDocBean.getINVOICE_AMOUNT())) {
                                        INVOICE_Amt= ClearingDocBean.getINVOICE_AMOUNT();
                                        INVOICE_Amt =  ApplicationUtils.formatAmount(Double.valueOf(INVOICE_Amt));
                                     }
                                      if (!ApplicationUtils.isBlank(ClearingDocBean.getPO_NUMBER())) {
                                        PO_NUMBER= ClearingDocBean.getPO_NUMBER();
                                     }
                                      if (!ApplicationUtils.isBlank(ClearingDocBean.getCL_DOC_NO())) {
                                        CLEARING_DOC_NO= ClearingDocBean.getCL_DOC_NO();
                                     }
                                                     if (!ApplicationUtils.isBlank(ClearingDocBean.getCL_DOC_DT())) {
                                      CLARING_DOC_DATE = ApplicationUtils.dateToString(ClearingDocBean.getCL_DOC_DT(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);

                                     }
                                     
                                    %>
                                           

                                            <tbody>
                                                <tr class="info" >
                                             <td class="text-center"><%=j%></td>
                                             <td width="20%" class="text-center"><%=CLEARING_DOC_NO%></td>
                                             <td width="20%" class="text-center"><%=CLARING_DOC_DATE%></td> 
                                             <td width="20%" class="text-center"><%=INVOICE_NUMBER%></td>
                                             <td width="20%" class="text-center"><%=INVOICE_Amt%></td>
                                             <td width="20%" class="text-center"><%=INVOICE_Date%></td>
                                             <td width="20%" class="text-center"><%=PO_NUMBER%></td>
                                             </tr>
                                           <% } %>
                                            </tbody>
                                       </table>
                                  </div>
                               </div>
                                     </div>
                                      </fieldset>
                                    
                       </div>
                    </div>
                </div>
            </div>
          </div>
                                            