<%--
    Document   : erp_vendor_appl_form
    Created on : Dec 10, 2017, 4:06:44 PM
    Author     : pooja
--%>

<%@page import="java.util.Date"%>
<%@page import="in.emp.vendor.bean.VendorInputBean"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="in.emp.vendor.bean.VendorBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%          

    

    String recordsVar = "No Records To Display !!!";
    String uiAction ="";
    
    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
       uiAction = request.getParameter("uiActionName");
    }

    VendorPrezData vendorPrezDataObj = new VendorPrezData();
   VendorInputBean vendorInputBeanObj=new VendorInputBean();
        LinkedList sesList = new LinkedList();
         LinkedList migoList = new LinkedList();
          LinkedList invList = new LinkedList();
           LinkedList paymentList = new LinkedList();
    String SaveFlag="";
    int flag=0;

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_VERIFIEDLIST_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_VERIFIEDLIST_SESSION_DATA);
        vendorInputBeanObj =  vendorPrezDataObj.getVendorInputBean();
        sesList= (LinkedList) vendorPrezDataObj.getSesList();
        migoList= (LinkedList) vendorPrezDataObj.getMigoList();
        invList= (LinkedList) vendorPrezDataObj.getInvList();
       paymentList= (LinkedList) vendorPrezDataObj.getPaymentList();
    }
    String Zone = "";
    String Circle = "";
    String Division = "";
    String PONumber = "";
    String PODesc = "";
    String POType = "";
    String POCreationDate = "";
    String ValidityStart = "";
    String ValidityEnd = "";
    String VendorInvoiceNumber = "";
    String Status = ""; 
    //String MsedclInvoiceNumber = "";
    String InvoiceAmt="";
    String PaidAmt=""; 
    String InvoiceDate ="";
    String UserType ="";
    
    String SESNum="";
    String SESDate="";
    String SESAmt="";
    String BalAmt="";
    String TotalPOAmt="";
    String ProfitCenter="";
    String VendorNum="";
    String VendorName="";
    String SESDesc ="";
    String MSEDCLInvNo="";
    String VENDORInvNo="";
    String CLDocNo="";
    String CLDocDt="";
    String InvDate ="";
    String LiabilityDate = "";
    String TaxCode = "";
    String ProjectCode = "";
    String TaxAmount = "";
    String ItTdsAmount = "";
    String GstTds = "";
    String RetensionAmount = "";
    String ProjectScheme = "";
    String MIGODate="";
    String MIGONum="";
    String MIGOAmt="";
    String ApplId="";
      String ApplDate="";
    String VendorInvNo="";
     String VendorInvDate="";
     String msedclInwDate="";
     String msedclInwNumber="";
     String sesMigoAmt="";
      String invAmt="";
      String invStatus="";
    String plant="";
    String Desc="";
    
     if (vendorInputBeanObj != null) {
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getSubmitAtPlant())) {
                                                        plant = vendorInputBeanObj.getSubmitAtPlant();
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getSubmitAtDesc())) {
                                                        Desc = plant+"-"+vendorInputBeanObj.getSubmitAtDesc();
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getZone())) {
                                                        Zone = vendorInputBeanObj.getZone();
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getCircle())) {
                                                        Circle = vendorInputBeanObj.getCircle();
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getDivision())) {
                                                        Division = vendorInputBeanObj.getDivision();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getPONumber())) {
                                                        PONumber = vendorInputBeanObj.getPONumber();
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getPODesc())) {
                                                        PODesc = vendorInputBeanObj.getPODesc();
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getPOCreationDate())) {
                                                        POCreationDate = ApplicationUtils.dateToString(vendorInputBeanObj.getPOCreationDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getTotalPoAmt())) {
                                                        TotalPOAmt = vendorInputBeanObj.getTotalPoAmt();   
                                                         TotalPOAmt =  ApplicationUtils.formatAmount(Double.valueOf(TotalPOAmt));
                                                    }
                                                     
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getValidityStart())) {
                                                        ValidityStart = ApplicationUtils.dateToString(vendorInputBeanObj.getValidityStart(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getValidityEnd())) {
                                                        ValidityEnd = ApplicationUtils.dateToString(vendorInputBeanObj.getValidityEnd(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getVendorInvoiceNumber())) {
                                                        VendorInvoiceNumber = vendorInputBeanObj.getVendorInvoiceNumber();                                                        
                                                    }
                                                   
                                                    
                                                     if (!ApplicationUtils.isBlank(vendorInputBeanObj.getVendorInvoiceAmount())) {
                                                        InvoiceAmt = vendorInputBeanObj.getVendorInvoiceAmount();   
                                                         InvoiceAmt = ApplicationUtils.formatAmount(Double.valueOf(InvoiceAmt));
                                                    }
                                                     
                                                      if (!ApplicationUtils.isBlank(vendorInputBeanObj.getPaidAmt())) {
                                                        PaidAmt = vendorInputBeanObj.getPaidAmt(); 
                                                        PaidAmt=  ApplicationUtils.formatAmount(Double.valueOf(PaidAmt));
                                                    }
                                                          if (!ApplicationUtils.isBlank(vendorInputBeanObj.getSesMigoAmt())) {
                                                        sesMigoAmt = vendorInputBeanObj.getSesMigoAmt(); 
                                                        sesMigoAmt=  ApplicationUtils.formatAmount(Double.valueOf(sesMigoAmt));
                                                    }
                                                              if (!ApplicationUtils.isBlank(vendorInputBeanObj.getInvAmt())) {
                                                        invAmt = vendorInputBeanObj.getInvAmt(); 
                                                        invAmt=  ApplicationUtils.formatAmount(Double.valueOf(invAmt));
                                                    }
                                                      if (!ApplicationUtils.isBlank(vendorInputBeanObj.getVendorInvoiceDate())) {
                                                        InvoiceDate = ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                       if (!ApplicationUtils.isBlank(vendorInputBeanObj.getInvoiceStatus())) {
                                                        invStatus = vendorInputBeanObj.getInvoiceStatus();
                                                       }
                                                    
                                                      if (!ApplicationUtils.isBlank(vendorInputBeanObj.getVendorNumber())) {
                                                        VendorNum = vendorInputBeanObj.getVendorNumber();
                                                    }
                                                      if (!ApplicationUtils.isBlank(vendorInputBeanObj.getVendorName())) {
                                                        VendorName = vendorInputBeanObj.getVendorName();
                                                    }
                                                       if (!ApplicationUtils.isBlank(vendorInputBeanObj.getApplId())) {
                                                        ApplId = vendorInputBeanObj.getApplId();
                                                    }
                                                        if (!ApplicationUtils.isBlank(vendorInputBeanObj.getApplDate())) {
                                                        ApplDate = ApplicationUtils.dateToString(vendorInputBeanObj.getApplDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                        if (!ApplicationUtils.isBlank(vendorInputBeanObj.getVendorInvoiceNumber())) {
                                                        VendorInvNo = vendorInputBeanObj.getVendorInvoiceNumber();
                                                    }
                                                         if (!ApplicationUtils.isBlank(vendorInputBeanObj.getVendorInvoiceDate())) {
                                                          VendorInvDate = ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    } 
                                                         if (!ApplicationUtils.isBlank(vendorInputBeanObj.getInwardDate())) {
                                                          msedclInwDate = ApplicationUtils.dateToString(vendorInputBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);

                                                    }
                                                         if (!ApplicationUtils.isBlank(vendorInputBeanObj.getInwardNumber())) {
                                                          msedclInwNumber = vendorInputBeanObj.getInwardNumber();

                                                              
                                                                   if (!ApplicationUtils.isBlank(vendorInputBeanObj.getTaxAmount())) {
                                                          TaxAmount = vendorInputBeanObj.getTaxAmount();
                                                           TaxAmount= ApplicationUtils.formatAmount(Double.valueOf(TaxAmount));
                                                         }
                                                                    if (!ApplicationUtils.isBlank(vendorInputBeanObj.getIT_TDS_AMOUNT())) {
                                                          ItTdsAmount = vendorInputBeanObj.getIT_TDS_AMOUNT();
                                                           ItTdsAmount= ApplicationUtils.formatAmount(Double.valueOf(ItTdsAmount));
                                                         }
                                                                     if (!ApplicationUtils.isBlank(vendorInputBeanObj.getGST_TDS())) {
                                                          GstTds = vendorInputBeanObj.getGST_TDS();
                                                           GstTds= ApplicationUtils.formatAmount(Double.valueOf(GstTds));
                                                         }
                                                                      if (!ApplicationUtils.isBlank(vendorInputBeanObj.getRETENSION_AMOUNT())) {
                                                          RetensionAmount = vendorInputBeanObj.getRETENSION_AMOUNT();
                                                           RetensionAmount= ApplicationUtils.formatAmount(Double.valueOf(RetensionAmount));
                                                         }
     }                                                      if (!ApplicationUtils.isBlank(vendorInputBeanObj.getUserType())) {
                                                          UserType = vendorInputBeanObj.getUserType();
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

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_vendor_verified_form.js"></script>
    </head>
    <body>

        <div id="wrapper">
            <%@ include file="nav_emp_header.jsp"%>
            <!-- /. NAV TOP  -->
            <%@ include file="emp_nav_vmenu.jsp"%>
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner" style="min-height:500px;">

                    <%@ include file="navJs.jsp"%>
                   
                    <div class="content_container">
                        <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr> <!-- Start of Network Search Results tr -->
                                <td class="bg_white">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <div class="col-md-12"><h3><fmt:message key='Invoice Form'/></h3></div>
                                         <input type="hidden" name="view" id="view" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_CLEARING_DOC_DETAILS)%>"/>
                                        <div >&nbsp;</div>
                                    </div>
                                </td>
                            </tr>

                        </table>  <!-- End of Network Search results table -->
                       
                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                                <fieldset class="fldst_border">
                                    <legend>Location Details:</legend>

                                    <div class="row">
                                   <!-- <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Location'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtZone" id="txtZone" value="<%=Desc%>"   readonly="true" >
                                        </span>-->
                                       <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Zone'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtZone" id="txtZone" value="<%=Zone%>"   readonly="true" >
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Circle'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" readonly="true" value="<%=Circle%>" size="20"   id="txtCircle" name="txtCircle" >
                                        </span>


                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Division'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtDiv" id="txtDiv" value="<%=Division%>"   readonly="true" >
                                        </span>
                                    </div>
                                </fieldset>
                                <fieldset class="fldst_border">
                                    <legend>PO Details:</legend>

                                    

                                        
                                        <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PO Number'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtPONum" id="txtPONum" value="<%=PONumber%>"   readonly="true" >
                                        </span>
                                   
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PO Description'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" value="<%=PODesc%>" size="20"   id="txtInvoiceAmt" name="txtInvoiceAmt" readonly="true" >
                                        </span>

                                    </div>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PO Creation Date'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input name="txtInvoiceDt" id="txtInvoiceDt" type="text" size="20" class="form-control text-left"   value="<%=POCreationDate%>" maxlength="15" readonly="true" >
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PO Validity From'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtInwardNum" id="txtInwardNum" value="<%=ValidityStart%>"   readonly="true" > 
                                        </span>
                                                  <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PO Validity To'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input name="txtInwardDt" id="txtInwardDt" type="text" size="20" class="form-control text-left"   value="<%=ValidityEnd%>" maxlength="15" readonly="true" > 
                                        </span>
                                    
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Total PO Amount(Incl. Taxes)'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtTotalPOAmt" id="txtTotalPOAmt" value="<%=TotalPOAmt%>"   readonly="true" > 
                                            </span>
                                          <!--  <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PO Balance Amount'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtBalanceAmt" id="txtBalanceAmt" type="text" size="20" class="form-control text-left"   value="<%=BalAmt%>" maxlength="15" readonly="true" > 
                                            </span>  -->

                                        </div>
                                    
                                </fieldset>
                                <fieldset class="fldst_border">
                                    <legend>Vendor Details:</legend>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='SAP Vendor Code'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=VendorNum%>"   readonly="true" > 
                                        </span>
                                   

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Vendor Name'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input name="txtVName" id="txtVName" type="text" size="20" class="form-control text-left"   value="<%=VendorName%>" maxlength="15" readonly="true" > 
                                        </span>
                                    </div>
                                    </fieldset>
                                         <fieldset class="fldst_border">
                                    <legend>Vendor Invoice Details:</legend>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Application ID'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=ApplId%>"   readonly="true" > 
                                        </span>
                                   <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Application Date'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input name="txtVName" id="txtVName" type="text" size="20" class="form-control text-left"   value="<%=ApplDate%>" maxlength="15" readonly="true" > 
                                        </span>
                                    </div>
                                        <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Vendor Invoice Number'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=VendorInvNo%>"   readonly="true" > 
                                        </span>
                                   <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Vendor Invoice Amount'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input name="txtVName" id="txtVName" type="text" size="20" class="form-control text-left"   value="<%=InvoiceAmt%>" maxlength="15" readonly="true" > 
                                        </span>
                                    </div>
                                        <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Vendor Invoice Date'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=VendorInvDate%>"   readonly="true" > 
                                        </span>
                                   <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MSEDCL Inward Date'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input name="txtVName" id="txtVName" type="text" size="20" class="form-control text-left"   value="<%=msedclInwDate%>" maxlength="15" readonly="true" > 
                                        </span>
                                    </div>
                                          <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MSEDCL Inward Number'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=msedclInwNumber%>"   readonly="true" > 
                                        </span>
                           <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Total Ses/Migo Amount'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=sesMigoAmt%>"   readonly="true" > 
                                        </span>
                                    </div>
                                             <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Total MSEDCL Invoice Amount'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=invAmt%>"   readonly="true" > 
                                        </span>
                           <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Total Paid Amount'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=PaidAmt%>"   readonly="true" > 
                                        </span>
                                    </div>
                                          
                                             <div class="row">    

                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Tax Amount'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtTaxAmt" id="txtTaxAmt" type="text" size="20" class="form-control text-left"   value="<%=TaxAmount%>" maxlength="15" readonly="true" > 
                                            </span>  
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='IT TDS Amount'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtItTdsAmt" id="txtItTdsAmt" type="text" size="20" class="form-control text-left"   value="<%=ItTdsAmount%>" maxlength="15" readonly="true" > 
                                            </span> 

                                        </div>


                                        <div class="row"> 
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='GST TDS Amount'/></span>

                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtGstTdsAmt" id="txtGstTdsAmt" value="<%=GstTds%>"   readonly="true" > 

                                            </span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='RETENTION Amount'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtRetensionAmt" id="txtRetensionAmt" type="text" size="20" class="form-control text-left"   value="<%=RetensionAmount%>" maxlength="15" readonly="true" > 
                                            </span>   </div>
                                           
                                        

                                       
                                         <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Invoice Status'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=invStatus%>"   readonly="true" > 
                                        </span>
                                         </div>
                                    </fieldset> 
                                
                                        <fieldset class="fldst_border">
                                     <legend>SES Details:</legend>
                                    <div class="row">                
                               <div class="col-lg-12 col-md-12">
                                  <div class="table-responsive">
                                       <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th> 
                                               <%  if (UserType.equals("Emp")) {%><th width="20%"  class="text-center"><fmt:message key='SES Number'/></th> <%}%> 
                                                    <th width="20%"  class="text-center"><fmt:message key='SES DATE'/></th> 
                                                    <th width="20%"  class="text-center"><fmt:message key='SES AMOUNT'/></th>  
                                                  
                                                    <th width="20%"  class="text-center" ><fmt:message key='SES STATUS'/></th>                                                    
                                               </tr>
                                       <%
                                
                                     int j = 0;
                                     int k = sesList.size();
                                     for (VendorBean vendorBean : (LinkedList<VendorBean>) sesList) {
                                                    j++;
                                      String Ses_No = "";
                                    String Ses_Amt ="";
                                    String Ses_Date = "";
                                    String Ses_Status = "";
                                     if (!ApplicationUtils.isBlank(vendorBean.getSesNum())) {
                                       Ses_No = vendorBean.getSesNum();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorBean.getSESDate())) {
                                      Ses_Date = ApplicationUtils.dateToString(vendorBean.getSESDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);

                                     }
                                     
                                     if (!ApplicationUtils.isBlank(vendorBean.getSesAmt())) {
                                        Ses_Amt= vendorBean.getSesAmt();
                                        Ses_Amt =  ApplicationUtils.formatAmount(Double.valueOf(Ses_Amt));
                                     }
                                      if (!ApplicationUtils.isBlank(vendorBean.getSES_STATUS())) {
                                        Ses_Status= vendorBean.getSES_STATUS();
                                     }
                                     
                                    %>
                                           

                                          
                                            <tbody>
                                                <tr class="info" >
                                            <td class="text-center"><%=j%></td>
                                          <%  if (UserType.equals("Emp")) {%>  <td width="20%" class="text-center"><%=Ses_No%></td><%}%>
                                            <td width="20%" class="text-center"><%=Ses_Date%></td> 
                                             <td width="20%" class="text-center"><%=Ses_Amt%></td>
                                            <td width="20%" class="text-center"><%=Ses_Status%></td>
                                         
                                             </tr>
                                           <% } %>
                                            </tbody>
                                       </table>
                                  </div>
                               </div>
                                     </div>
                                      </fieldset>
                                    
                                    
                             
                                               <fieldset class="fldst_border">
                                     <legend>MIGO Details:</legend>
                                    <div class="row">                
                               <div class="col-lg-12 col-md-12">
                                  <div class="table-responsive">
                                       <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th> 
                                              <%  if (UserType.equals("Emp")) {%><th width="20%"  class="text-center"><fmt:message key='MIGO Number'/></th> <% }%>
                                                    <th width="20%"  class="text-center"><fmt:message key='MIGO DATE'/></th> 
                                                    <th width="20%"  class="text-center"><fmt:message key='MIGO AMOUNT'/></th>  
                                                  
                                                    <th width="20%"  class="text-center" ><fmt:message key='MIGO STATUS'/></th>                                                    
                                               </tr>
                                       <%
                                
                                     int l = 0;
                                     int m = migoList.size();
                                     for (VendorBean vendorBean : (LinkedList<VendorBean>) migoList) {
                                                    l++;
                                      String Migo_No = "";
                                    String Migo_Amt ="";
                                    String Migo_Date = "";
                                    String Migo_Status = "";
                                     if (!ApplicationUtils.isBlank(vendorBean.getMigo_doc())) {
                                       Migo_No = vendorBean.getMigo_doc();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorBean.getMigo_dt())) {
                                      Migo_Date = ApplicationUtils.dateToString(vendorBean.getMigo_dt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);

                                     }
                                     
                                     if (!ApplicationUtils.isBlank(vendorBean.getMigo_amt())) {
                                        Migo_Amt= vendorBean.getMigo_amt();
                                          Migo_Amt =  ApplicationUtils.formatAmount(Double.valueOf(Migo_Amt));
                                     }
                                      if (!ApplicationUtils.isBlank(vendorBean.getMIGO_STATUS())) {
                                        Migo_Status= vendorBean.getMIGO_STATUS();
                                     }
                                     
                                    %>
                                           

                                          
                                            <tbody>
                                                <tr class="info" >
                                            <td class="text-center"><%=l%></td>
                                        <%  if (UserType.equals("Emp")) {%>    <td width="20%" class="text-center"><%=Migo_No%></td><%}%> 
                                            <td width="20%" class="text-center"><%=Migo_Date%></td> 
                                             <td width="20%" class="text-center"><%=Migo_Amt%></td>
                                            <td width="20%" class="text-center"><%=Migo_Status%></td>
                                         
                                             </tr>
                                           <% } %>
                                            </tbody>
                                       </table>
                                  </div>
                               </div>
                                     </div>
                                      </fieldset>

                                <fieldset class="fldst_border">
                                    <legend>Invoice Details:</legend>
                                        <div class="row">
                                        
<div class="col-lg-12 col-md-12">
                                  <div class="table-responsive">
                                       <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th> 
                                                <%  if (UserType.equals("Emp")) {%>         <th width="20%"  class="text-center"><fmt:message key='INVOICE NUMBER'/></th>   <%  }%>   
                                                    <th width="20%"  class="text-center"><fmt:message key='INVOICE DATE'/></th> 
                                                    <th width="20%"  class="text-center"><fmt:message key='INVOICE AMOUNT'/></th>  
                                                  
                                                    <th width="20%"  class="text-center" ><fmt:message key='INVOICE STATUS'/></th>                                                    
                                               </tr>
                                       <%
                                
                                     int p = 0;
                                     int q = migoList.size();
                                     for (VendorBean vendorBean : (LinkedList<VendorBean>) invList) {
                                                    p++;
                                      String Invoice_No = "";
                                    String Invoice_Amt ="";
                                    String Invoice_Date = "";
                                    String Invoice_Status = "";
                                     if (!ApplicationUtils.isBlank(vendorBean.getMsedclInvoiceNumber())) {
                                       Invoice_No = vendorBean.getMsedclInvoiceNumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorBean.getInvoiceDate())) {
                                      Invoice_Date = ApplicationUtils.dateToString(vendorBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);

                                     }
                                     
                                     if (!ApplicationUtils.isBlank(vendorBean.getInvoiceAmount())) {
                                        Invoice_Amt= vendorBean.getInvoiceAmount();
                                          Invoice_Amt = ApplicationUtils.formatAmount(Double.valueOf(Invoice_Amt));
                                     }
                                      if (!ApplicationUtils.isBlank(vendorBean.getINV_STATUS())) {
                                        Invoice_Status= vendorBean.getINV_STATUS();
                                     }
                                     
                                    %>
                                           

                                          
                                            <tbody>
                                                <tr class="info" >
                                            <td class="text-center"><%=p%></td>
                                      <%  if (UserType.equals("Emp")) {%>       <td width="20%" class="text-center"><%=Invoice_No%></td><% }%>
                                            <td width="20%" class="text-center"><%=Invoice_Date%></td> 
                                             <td width="20%" class="text-center"><%=Invoice_Amt%></td>
                                            <td width="20%" class="text-center"><%=Invoice_Status%></td>
                                         
                                             </tr>
                                           <% } %>
                                            </tbody>
                                       </table>
                                  </div>
                               </div>
                                        </div>

                                    </fieldset>
                                            <fieldset class="fldst_border">
                                    <legend>Payment Details:</legend>
                                  <div class="col-lg-12 col-md-12">
                                  <div class="table-responsive">
                                       <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th> 
                                                  <%  if (UserType.equals("Emp")) {%>      <th width="20%"  class="text-center"><fmt:message key='CLEARING DOC NUMBER'/></th> <% }%>
                                                    <th width="20%"  class="text-center"><fmt:message key='CLEARING DOC DATE'/></th> 
                                                    <th width="20%"  class="text-center"><fmt:message key='Paid AMOUNT'/></th>  
                                                  
                                                    <th width="20%"  class="text-center" ><fmt:message key='PAYMENT STATUS'/></th>                                                    
                                               </tr>
                                       <%
                                
                                     int n = 0;
                                     int o = paymentList.size();
                                     for (VendorBean vendorBean : (LinkedList<VendorBean>) paymentList) {
                                                    n++;
                                      String Payment_No = "";
                                    String Payment_Amt ="";
                                    String Payment_Date = "";
                                    String Payment_Status = "";
                                     if (!ApplicationUtils.isBlank(vendorBean.getCLDocNo())) {
                                       Payment_No = vendorBean.getCLDocNo();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorBean.getCLDocDt())) {
                                      Payment_Date = ApplicationUtils.dateToString(vendorBean.getCLDocDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);

                                     }
                                     
                                     if (!ApplicationUtils.isBlank(vendorBean.getPaid())) {
                                        Payment_Amt= vendorBean.getPaid();
                                          Payment_Amt =  ApplicationUtils.formatAmount(Double.valueOf(Payment_Amt));
                                     }
                                      if (!ApplicationUtils.isBlank(vendorBean.getPAYMENT_STATUS())) {
                                        Payment_Status= vendorBean.getPAYMENT_STATUS();
                                     }
                                     
                                    %>
                                           

                                          
                                            <tbody>
                                                <tr class="info" >
                                            <td class="text-center"><%=n%></td>
                                       <%  if (UserType.equals("Emp")) {%>        <td width="20%" class="text-center"><a href="#nogo" style="color:blue;" onclick="viewClearingDetails('<%=Payment_No%>');"><%=Payment_No%></a></td> <% }%>
                                            
                                            <td width="20%" class="text-center"><%=Payment_Date%></td> 
                                             <td width="20%" class="text-center"><%=Payment_Amt%></td>
                                            <td width="20%" class="text-center"><%=Payment_Status%></td>
                                         
                                             </tr>
                                           <% } %>
                                            </tbody>
                                       </table>
                                  </div>
                               </div>
                                            
                                      
                                      </fieldset>
                                
                                        <div class="row"> 

                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5">
                                                <% if (UserType.equals("Vendor")) {%>
                                                <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                                <% } else if (UserType.equals("Emp")) {%>
                                                <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_PO_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                                <% }%>

                                            </span>
                                        </div>
   


                                               
                                           
                                           
                                               
                                           
                         
                                    
                           
                        <hr/>  </div>
                    </div>
                        
                    <!-- /. PAGE inner  -->
                </div>
                <!-- /. PAGE wrapper  -->
            </div>
            <!-- /.  wrapper  -->
        </div>

        <%@include file="nav_emp_footer.jsp" %>

        <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
        <!-- JQUERY SCRIPTS -->
        
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js"></script>

    </body>
</html>