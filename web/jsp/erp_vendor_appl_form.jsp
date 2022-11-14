<%--
    Document   : erp_vendor_appl_form
    Created on : Dec 10, 2017, 4:06:44 PM
    Author     : prajakta
--%>

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
    VendorBean vendorBean = new VendorBean();
    String SaveFlag="";
    int flag=0;

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA);
      
        vendorBean = (VendorBean) vendorPrezDataObj.getVendorBean();
       
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
    String TaxAmount = "";
    String ItTdsAmount = "";
    String GstTds = "";
    String RetensionAmount = "";
    String ProjectCode = "";
    String ProjectScheme = "";
    String MIGODate="";
    String MIGONum="";
    String MIGOAmt="";
    
  
     if (vendorBean != null) {
         if (!ApplicationUtils.isBlank(vendorBean.getZone())) {
                                                        Zone = vendorBean.getZone();
                                                    }

                                                    if (!ApplicationUtils.isBlank(vendorBean.getCircle())) {
                                                        Circle = vendorBean.getCircle();
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorBean.getDivision())) {
                                                        Division = vendorBean.getDivision();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorBean.getPONumber())) {
                                                        PONumber = vendorBean.getPONumber();
                                                    }
                                                    if (!ApplicationUtils.isBlank(vendorBean.getPODesc())) {
                                                        PODesc = vendorBean.getPODesc();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorBean.getPOType())) {
                                                        POType = vendorBean.getPOType();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorBean.getPOCreationDate())) {
                                                        POCreationDate = ApplicationUtils.dateToString(vendorBean.getPOCreationDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorBean.getValidityStart())) {
                                                        ValidityStart = ApplicationUtils.dateToString(vendorBean.getValidityStart(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorBean.getValidityEnd())) {
                                                        ValidityEnd = ApplicationUtils.dateToString(vendorBean.getValidityEnd(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())) {
                                                        VendorInvoiceNumber = vendorBean.getVendorInvoiceNumber();                                                        
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(vendorBean.getSaveFlag())) {
                                                        Status = vendorBean.getSaveFlag();
                                                    }
                                                     if (!ApplicationUtils.isBlank(vendorBean.getMsedclInvoiceNumber())) {
                                                        MSEDCLInvNo = vendorBean.getMsedclInvoiceNumber();                                                        
                                                    }
                                                     if (!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())) {
                                                        VENDORInvNo = vendorBean.getVendorInvoiceNumber();                                                        
                                                    }
                                                     
                                                     if (!ApplicationUtils.isBlank(vendorBean.getInvoiceAmount())) {
                                                        InvoiceAmt = vendorBean.getInvoiceAmount();   
                                                         InvoiceAmt =  ApplicationUtils.formatAmount(Double.valueOf(InvoiceAmt));
                                                    }
                                                     
                                                      if (!ApplicationUtils.isBlank(vendorBean.getPaidAmt())) {
                                                        PaidAmt = vendorBean.getPaidAmt(); 
                                                        PaidAmt=  ApplicationUtils.formatAmount(Double.valueOf(PaidAmt));
                                                    }
                                                      if (!ApplicationUtils.isBlank(vendorBean.getInvoiceDate())) {
                                                        InvoiceDate = ApplicationUtils.dateToString(vendorBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                      if (!ApplicationUtils.isBlank(vendorBean.getUserType())) {
                                                        UserType = vendorBean.getUserType();
                                                    }
                                                      
                                                   
                                                    
                                                     if (!ApplicationUtils.isBlank(vendorBean.getSesNum())) {
                                                        SESNum = vendorBean.getSesNum();
                                                          if (!ApplicationUtils.isBlank(vendorBean.getSESDate())) {
                                                        SESDate = ApplicationUtils.dateToString(vendorBean.getSESDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                           if (!ApplicationUtils.isBlank(vendorBean.getSesAmt())) {
                                                        SESAmt = vendorBean.getSesAmt();
                                                        SESAmt = ApplicationUtils.formatAmount(Double.valueOf(SESAmt));
                                                    }
                                                    }
                                                     
                                                    
                                                     
                                                       if (!ApplicationUtils.isBlank(vendorBean.getMigo_doc())) {
                                                        MIGONum =vendorBean.getMigo_doc();
                                                         if (!ApplicationUtils.isBlank(vendorBean.getMigo_dt())) {
                                                        MIGODate = ApplicationUtils.dateToString(vendorBean.getMigo_dt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                          if (!ApplicationUtils.isBlank(vendorBean.getMigo_amt())) {
                                                        MIGOAmt = vendorBean.getMigo_amt();
                                                        MIGOAmt =  ApplicationUtils.formatAmount(Double.valueOf(MIGOAmt));
                                                    }
                                                    }
                                                    
                                                    
                                                     
                                                     
                                                     
                                                       if (!ApplicationUtils.isBlank(vendorBean.getTotalPOAmt())) {
                                                        TotalPOAmt = vendorBean.getTotalPOAmt();
                                                          TotalPOAmt = ApplicationUtils.formatAmount(Double.valueOf(TotalPOAmt));
                                                    }
                                                       
                                                     if (!ApplicationUtils.isBlank(vendorBean.getBalAmt())) {
                                                        BalAmt = vendorBean.getBalAmt();
                                                        BalAmt=  ApplicationUtils.formatAmount(Double.valueOf(BalAmt));
                                                    }
                                                     
                                                      if (!ApplicationUtils.isBlank(vendorBean.getProfitCenter())) {
                                                        ProfitCenter = vendorBean.getProfitCenter();
                                                    }
                                                      if (!ApplicationUtils.isBlank(vendorBean.getDispVendorNumber())) {
                                                        VendorNum = vendorBean.getDispVendorNumber();
                                                    }
                                                      if (!ApplicationUtils.isBlank(vendorBean.getDispVendorName())) {
                                                        VendorName = vendorBean.getDispVendorName();
                                                    }
                                                        
                                                      if (!ApplicationUtils.isBlank(vendorBean.getSESDesc())) {
                                                        SESDesc = vendorBean.getSESDesc();
                                                    }
                                                    
                                                      if (!ApplicationUtils.isBlank(vendorBean.getCLDocNo())) {
                                                        CLDocNo = vendorBean.getCLDocNo();
                                                    }
                                                       
                                                      if (!ApplicationUtils.isBlank(vendorBean.getCLDocDt())) {
                                                        CLDocDt = ApplicationUtils.dateToString(vendorBean.getCLDocDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                      
                                                      if (!ApplicationUtils.isBlank(vendorBean.getInvDt())) {
                                                        LiabilityDate = ApplicationUtils.dateToString(vendorBean.getInvDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                      if (!ApplicationUtils.isBlank(vendorBean.getTaxCode())) {
                                                        TaxCode = vendorBean.getTaxCode();
                                                    }
                                                        /*if (!ApplicationUtils.isBlank(vendorBean.getTaxAmount())) {
                                                        TaxAmount = vendorBean.getTaxAmount();
                                                 
                                                        TaxAmount= formatter.format(Double.valueOf(TaxAmount));
                                                    }*/
                                                      
                                                          if (!ApplicationUtils.isBlank(vendorBean.getItTdsAmount())) {
                                                        ItTdsAmount = vendorBean.getItTdsAmount();
                                                        ItTdsAmount=  ApplicationUtils.formatAmount(Double.valueOf(ItTdsAmount));

                                                    }
                                                 if (!ApplicationUtils.isBlank(vendorBean.getGstTds())) {
                                                        GstTds = vendorBean.getGstTds();
                                                        GstTds=  ApplicationUtils.formatAmount(Double.valueOf(GstTds));
                                                    }
                                                          if (!ApplicationUtils.isBlank(vendorBean.getRetensionAmount())) {
                                                        RetensionAmount = vendorBean.getRetensionAmount();
                                                        RetensionAmount=  ApplicationUtils.formatAmount(Double.valueOf(RetensionAmount));
                                                    }
                                                     if (!ApplicationUtils.isBlank(vendorBean.getProjectCode())) {
                                                        ProjectCode = vendorBean.getProjectCode();
                                                    }
                                                      if (!ApplicationUtils.isBlank(vendorBean.getProjectScheme())) {
                                                        ProjectScheme = vendorBean.getProjectScheme();
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
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_vendor_appl_form.js"></script>
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
                                        <div >&nbsp;</div>
                                    </div>
                                </td>
                            </tr>

                        </table>  <!-- End of Network Search results table -->
                       
                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                            <table class="table" border="0" cellspacing="0" cellpadding="2" id="table_content"> <!-- Start of  table_content table  -->
                                <fieldset class="fldst_border">
                                    <legend>Location Details:</legend>

                                    <div class="row">

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
                                    <legend>SES Details:</legend>
                                        <div class="row">

                                           


                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='SES Number'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtSOrMNum" id="txtSOrMNum" value="<%=SESNum%>"   readonly="true" > 
                                            </span> 
                                         <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='SES Date'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtSOrMNum" id="txtSOrMNum" value="<%=SESDate%>"   readonly="true" > 
                                            </span>
                                              <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='SES Amount'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtSOrMNum" id="txtSOrMNum" value="<%=SESAmt%>"   readonly="true" > 
                                            </span>
                                            </div>
                                </fieldset>
                                             <fieldset class="fldst_border">
                                    <legend>MIGO Details:</legend>
                                        <div class="row"> 
                                             <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MIGO Number'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtSOrMNum" id="txtSOrMNum" value="<%=MIGONum%>"   readonly="true" > 
                                            </span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MIGO Date'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtSOrMNum" id="txtSOrMNum" value="<%=MIGODate%>"   readonly="true" > 
                                            </span>
                                            
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MIGO Amount'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtSOrMNum" id="txtSOrMNum" value="<%=MIGOAmt%>"   readonly="true" > 
                                            </span>
                                        </div>
                                      </fieldset>

         <fieldset class="fldst_border">
                                    <legend>Invoice Details:</legend>
                                        <div class="row">
                                           <!-- <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='VENDOR Invoice Number'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtVInvNum" id="txtVInvNum" value="<%=VENDORInvNo%>"   readonly="true" > -->
                                           

                                             <!-- <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='VENDOR Invoice Date'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtVendorInvDt" id="txtVendorInvDt" type="text" size="20" class="form-control text-left"   value="<%=InvoiceDate%>" maxlength="15" readonly="true" > -->
                                            </span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MSEDCL Inv No'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtVInvNum" id="txtVInvNum" value="<%=MSEDCLInvNo%>"   readonly="true" > 
                                            </span>
                                             <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MSEDCL Inv Amount'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" readonly="true" value="<%=InvoiceAmt%>" size="25"   id="txtStatus" name="txtStatus" >
                                            </span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MIRO Invoice Date'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtVendorInvDt" id="txtVendorInvDt" type="text" size="20" class="form-control text-left"   value="<%=LiabilityDate%>" maxlength="15" readonly="true" > 
                                            </span>
                                             <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Invoice Status'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" readonly="true" value="<%=Status%>" size="25"   id="txtStatus" name="txtStatus" >
                                            </span>

                                        </div>

                                    </fieldset>
                                            <fieldset class="fldst_border">
                                    <legend>Payment Details:</legend>
                                    <div class="row"> 
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Payment Doc Number'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtCLDocNo" id="txtCLDocNo" value="<%=CLDocNo%>"   readonly="true" > 
                                            </span>
                                        
                                        

                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Payment Doc Date'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtCLDocDt" id="txtCLDocDt" type="text" size="20" class="form-control text-left"   value="<%=CLDocDt%>" maxlength="15" readonly="true" > 
                                            </span>




                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Paid Amount'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtInwardDt" id="txtInwardDt" type="text" size="20" class="form-control text-left"   value="<%=PaidAmt%>" maxlength="15" readonly="true" > 
                                            </span>
                                        </div>
                                            


                                        <%  if (UserType.equals("Emp")) {%> 

                                        <div class="row"> 
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Profit Center'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtProfitCenter" id="txtProfitCenter" type="text" size="20" class="form-control text-left"   value="<%=ProfitCenter%>" maxlength="15" readonly="true" > 
                                            </span>

                                            

                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Liability Doc Number'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtMSEDCLInvNo" id="txtMSEDCLInvNo" value="<%=MSEDCLInvNo%>"   readonly="true" > 
                                            </span>
                                        </div>
                                        <div class="row"> 
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Liability Date'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtLiabilityDate" id="txtLiabilityDate" value="<%=LiabilityDate%>"   readonly="true" > 
                                            </span>


                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Liability Amount'/>.</span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input type="text" class="form-control text-left" name="txtInvoiceAmout" id="txtInvoiceAmout" value="<%=InvoiceAmt%>"   readonly="true" > 
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
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='RETENSION Amount'/></span>
                                            <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                                <input name="txtRetensionAmt" id="txtRetensionAmt" type="text" size="20" class="form-control text-left"   value="<%=RetensionAmount%>" maxlength="15" readonly="true" > 
                                            </span>  
                                            <% }%> 
                                        

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



                                               
                                           
                                           
                                               
                                           
                            </table>  
                                    
                           
                        <hr/>
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