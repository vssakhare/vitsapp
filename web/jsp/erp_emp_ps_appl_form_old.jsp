<%--
    Document   : erp_vendor_appl_form
    Created on : Dec 10, 2017, 4:06:44 PM
    Author     : pooja
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
    String uiAction = "";

    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }

    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    VendorBean vendorBean = new VendorBean();
    String SaveFlag = "";
    int flag = 0;

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA);

        vendorBean = (VendorBean) vendorPrezDataObj.getVendorBean();

    }
    String Zone = "";
    String Circle = "";
    String Division = "";

    String Status = "";

    String VendorNum = "";
    String VendorName = "";

    String MSEDCLInvNo = "";
    String PROJECT_ID = "";
    String PROJECT_DESC = "";
    String CREATION_DATE = "";
    String PLANT = "";
    String SCHEME_CODE = "";
    String MATERIAL_PO = "";
    String CENTAGES_PO = "";
    String SERVICE_PO = "";
    String VENDOR_NAME = "";
    String VENDOR_NUMBER = "";
    String MSEDCL_INV_NO = "";
    String UserType = "";
    String MATERIAL_WS_PARK_DOC = "";
    String MAT_DOC_DATE = "";
    String MAT_WS_PARK_AMT = "";
    String MAT_WS_CLEARING_DOC_NO = "";
    String MAT_WS_AC_DOC_DATE = "";
    String MAT_WS_CLEARING_AMT = "";
    String CENTAGES_PARK_DOC = "";
    String CEN_DOC_DATE = "";
    String CEN_PARK_AMT = "";
    String CEN_CLEARING_DOC_NO = "";
    String CEN_AC_DOC_DATE = "";
    String CEN_CLEARING_AMT = "";
    String CIVIL_PARK_DOC = "";
    String CIVIL_DOC_DATE = "";
    String CIVIL_PARK_AMT = "";
    String CIVIL_CLEARING_DOC_NO = "";
    String CIVIL_AC_DOC_DATE = "";
    String CIVIL_CLEARING_AMT = "";
    String MAT_OS_PARK_DOC_NO = "";
    String MAT_OS_DOC_DATE = "";
    String MAT_OS_PARK_AMT = "";
    String MAT_OS_CLEARING_DOC_NO = "";
    String MAT_OS_AC_DOC_DATE = "";
    String MAT_OS_CLEARING_AMT = "";
    String MSEDCL_INVOICE_NUMBER = "";
    String MAT_OTH_PARK_DOC_NO = "";
    String MAT_OTH_DOC_DATE = "";
    String MAT_OTH_PARK_AMT = "";
    String MAT_OTH_CLEARING_DOC_NO = "";
    String MAT_OTH_AC_DOC_DATE = "";
    String MAT_OTH_CLEARING_AMT = "";
    String MAT_PO_AMOUNT = "";
    String CEN_PO_AMOUNT = "";
    String CIV_PO_AMOUNT = "";
    String INV_PO_AMOUNT = "";
    String ProjectCode = "";
    String ProjectScheme = "";
    String TotalPoAmount = "";
    String InvoiceNumber = "";
    String TAX_CODE = "";
    String INV_TYP = "";
    String WTAX_AMT = "";
    String WIT_TDS = "";
    String WGST_TDS = "";
    String WRPST = "";
    String WRET_AMT = "";
    String OTAX_AMT = "";
    String OIT_TDS = "";
    String OGST_TDS = "";
    String ORPST = "";
    String ORET_AMT = "";
    String CTAX_AMT = "";
    String CIT_TDS = "";
    String CGST_TDS = "";
    String CRPST = "";
    String CRET_AMT = "";
    String STAX_AMT = "";
    String SIT_TDS = "";
    String SGST_TDS = "";
    String SRPST = "";
    String SRET_AMT = "";
    String OTTAX_AMT = "";
    String OTIT_TDS = "";
    String OTGST_TDS = "";
    String OTRPST = "";
    String OTRET_AMT = "";

    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
    Locale locale = new Locale("en", "GB");
    DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
    DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale);
    dfs.setCurrencySymbol("\u20B9");
    decimalFormat.setDecimalFormatSymbols(dfs);
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

        if (!ApplicationUtils.isBlank(vendorBean.getProjectCode())) {
            PROJECT_ID = vendorBean.getProjectCode();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPROJECT_DESC())) {
            PROJECT_DESC = vendorBean.getPROJECT_DESC();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMsedclInvoiceNumber())) {
            MSEDCL_INVOICE_NUMBER = vendorBean.getMsedclInvoiceNumber();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCREATION_DATE())) {
            CREATION_DATE = ApplicationUtils.dateToString(vendorBean.getCREATION_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_DOC_DATE())) {
            MAT_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_AC_DOC_DATE())) {
            MAT_WS_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_WS_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getSCHEME_CODE())) {
            SCHEME_CODE = vendorBean.getSCHEME_CODE();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getSaveFlag())) {
            Status = vendorBean.getSaveFlag();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMsedclInvoiceNumber())) {
            MSEDCLInvNo = vendorBean.getMsedclInvoiceNumber();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMATERIAL_PO())) {
            MATERIAL_PO = vendorBean.getMATERIAL_PO();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCENTAGES_PO())) {
            CENTAGES_PO = vendorBean.getCENTAGES_PO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSERVICE_PO())) {
            SERVICE_PO = vendorBean.getSERVICE_PO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCEN_AC_DOC_DATE())) {
            CEN_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getCEN_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_DOC_DATE())) {
            CIVIL_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getCIVIL_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_CLEARING_DOC_NO())) {
            CIVIL_CLEARING_DOC_NO = vendorBean.getCIVIL_CLEARING_DOC_NO();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMATERIAL_WS_PARK_DOC())) {
            MATERIAL_WS_PARK_DOC = vendorBean.getMATERIAL_WS_PARK_DOC();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_PARK_AMT())) {
            MAT_WS_PARK_AMT = vendorBean.getMAT_WS_PARK_AMT();
            MAT_WS_PARK_AMT = decimalFormat.format(Double.valueOf(MAT_WS_PARK_AMT));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getUserType())) {
            UserType = vendorBean.getUserType();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_CLEARING_DOC_NO())) {
            MAT_WS_CLEARING_DOC_NO = vendorBean.getMAT_WS_CLEARING_DOC_NO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getDispVendorNumber())) {
            VendorNum = vendorBean.getDispVendorNumber();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getDispVendorName())) {
            VendorName = vendorBean.getDispVendorName();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_WS_CLEARING_AMT())) {
            MAT_WS_CLEARING_AMT = vendorBean.getMAT_WS_CLEARING_AMT();
            MAT_WS_CLEARING_AMT = decimalFormat.format(Double.valueOf(MAT_WS_CLEARING_AMT));

        }

        if (!ApplicationUtils.isBlank(vendorBean.getCENTAGES_PARK_DOC())) {
            CENTAGES_PARK_DOC = vendorBean.getCENTAGES_PARK_DOC();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCEN_CLEARING_DOC_NO())) {
            CEN_CLEARING_DOC_NO = vendorBean.getCEN_CLEARING_DOC_NO();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCEN_DOC_DATE())) {
            CEN_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getCEN_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_AC_DOC_DATE())) {
            CIVIL_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getCIVIL_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_PARK_DOC())) {
            CIVIL_PARK_DOC = vendorBean.getCIVIL_PARK_DOC();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCEN_PARK_AMT())) {
            CEN_PARK_AMT = vendorBean.getCEN_PARK_AMT();
            CEN_PARK_AMT = decimalFormat.format(Double.valueOf(CEN_PARK_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCEN_CLEARING_AMT())) {
            CEN_CLEARING_AMT = vendorBean.getCEN_CLEARING_AMT();
            CEN_CLEARING_AMT = decimalFormat.format(Double.valueOf(CEN_CLEARING_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getProjectCode())) {
            ProjectCode = vendorBean.getProjectCode();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getProjectScheme())) {
            ProjectScheme = vendorBean.getProjectScheme();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_PARK_DOC_NO())) {
            MAT_OS_PARK_DOC_NO = vendorBean.getMAT_OS_PARK_DOC_NO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_CLEARING_DOC_NO())) {
            MAT_OS_CLEARING_DOC_NO = vendorBean.getMAT_OS_CLEARING_DOC_NO();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_DOC_DATE())) {
            MAT_OS_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_OS_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_AC_DOC_DATE())) {
            MAT_OS_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_OS_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_PARK_AMT())) {
            MAT_OS_PARK_AMT = vendorBean.getMAT_OS_PARK_AMT();
            MAT_OS_PARK_AMT = decimalFormat.format(Double.valueOf(MAT_OS_PARK_AMT));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OS_CLEARING_AMT())) {
            MAT_OS_CLEARING_AMT = vendorBean.getMAT_OS_CLEARING_AMT();
            MAT_OS_CLEARING_AMT = decimalFormat.format(Double.valueOf(MAT_OS_CLEARING_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_CLEARING_DOC_NO())) {
            CIVIL_CLEARING_DOC_NO = vendorBean.getCIVIL_CLEARING_DOC_NO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_PARK_AMT())) {
            CIVIL_PARK_AMT = vendorBean.getCIVIL_PARK_AMT();
            CIVIL_PARK_AMT = decimalFormat.format(Double.valueOf(CIVIL_PARK_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIVIL_CLEARING_AMT())) {
            CIVIL_CLEARING_AMT = vendorBean.getCIVIL_CLEARING_AMT();
            CIVIL_CLEARING_AMT = decimalFormat.format(Double.valueOf(CIVIL_CLEARING_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_PARK_DOC_NO())) {
            MAT_OTH_PARK_DOC_NO = vendorBean.getMAT_OTH_PARK_DOC_NO();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_CLEARING_DOC_NO())) {
            MAT_OTH_CLEARING_DOC_NO = vendorBean.getMAT_OTH_CLEARING_DOC_NO();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_DOC_DATE())) {
            MAT_OTH_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_OTH_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_AC_DOC_DATE())) {
            MAT_OTH_AC_DOC_DATE = ApplicationUtils.dateToString(vendorBean.getMAT_OTH_AC_DOC_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_PARK_AMT())) {
            MAT_OTH_PARK_AMT = vendorBean.getMAT_OTH_PARK_AMT();
            MAT_OTH_PARK_AMT = decimalFormat.format(Double.valueOf(MAT_OTH_PARK_AMT));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getMAT_OTH_CLEARING_AMT())) {
            MAT_OTH_CLEARING_AMT = vendorBean.getMAT_OTH_CLEARING_AMT();
            MAT_OTH_CLEARING_AMT = decimalFormat.format(Double.valueOf(MAT_OTH_CLEARING_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPO_MAT_AMT())) {
            MAT_PO_AMOUNT = vendorBean.getPO_MAT_AMT();
            MAT_PO_AMOUNT = decimalFormat.format(Double.valueOf(MAT_PO_AMOUNT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPO_CEN_AMT())) {
            CEN_PO_AMOUNT = vendorBean.getPO_CEN_AMT();
            CEN_PO_AMOUNT = decimalFormat.format(Double.valueOf(CEN_PO_AMOUNT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPO_CIV_AMT())) {
            CIV_PO_AMOUNT = vendorBean.getPO_CIV_AMT();
            CIV_PO_AMOUNT = decimalFormat.format(Double.valueOf(CIV_PO_AMOUNT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getPO_INV_AMT())) {
            INV_PO_AMOUNT = vendorBean.getPO_INV_AMT();
            INV_PO_AMOUNT = decimalFormat.format(Double.valueOf(INV_PO_AMOUNT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getTOTAL_PO_AMOUNT())) {
            TotalPoAmount = vendorBean.getTOTAL_PO_AMOUNT();
            TotalPoAmount = decimalFormat.format(Double.valueOf(TotalPoAmount));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getVendorInvoiceNumber())) {
            InvoiceNumber = vendorBean.getVendorInvoiceNumber();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getTaxCode())) {
            TAX_CODE = vendorBean.getTaxCode();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getINV_TYP())) {
            INV_TYP = vendorBean.getINV_TYP();
        }

        if (!ApplicationUtils.isBlank(vendorBean.getWTAX_AMT())) {
            WTAX_AMT = vendorBean.getWTAX_AMT();
            WTAX_AMT = decimalFormat.format(Double.valueOf(WTAX_AMT));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getWIT_TDS())) {
            WIT_TDS = vendorBean.getWIT_TDS();
            WIT_TDS = decimalFormat.format(Double.valueOf(WIT_TDS));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getWGST_TDS())) {
            WGST_TDS = vendorBean.getWGST_TDS();
            WGST_TDS = decimalFormat.format(Double.valueOf(WGST_TDS));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getWRPST())) {
            WRPST = vendorBean.getWRPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getWRET_AMT())) {
            WRET_AMT = vendorBean.getWRET_AMT();
            WRET_AMT = decimalFormat.format(Double.valueOf(WRET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTAX_AMT())) {
            OTAX_AMT = vendorBean.getOTAX_AMT();
            OTAX_AMT = decimalFormat.format(Double.valueOf(OTAX_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOIT_TDS())) {
            OIT_TDS = vendorBean.getOIT_TDS();
            OIT_TDS = decimalFormat.format(Double.valueOf(OIT_TDS));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getOGST_TDS())) {
            OGST_TDS = vendorBean.getOGST_TDS();
            OGST_TDS = decimalFormat.format(Double.valueOf(OGST_TDS));
        }

        if (!ApplicationUtils.isBlank(vendorBean.getORPST())) {
            ORPST = vendorBean.getORPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getORET_AMT())) {
            ORET_AMT = vendorBean.getORET_AMT();
            ORET_AMT = decimalFormat.format(Double.valueOf(ORET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCTAX_AMT())) {
            CTAX_AMT = vendorBean.getCTAX_AMT();
            CTAX_AMT = decimalFormat.format(Double.valueOf(CTAX_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCIT_TDS())) {
            CIT_TDS = vendorBean.getCIT_TDS();
            CIT_TDS = decimalFormat.format(Double.valueOf(CIT_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCGST_TDS())) {
            CGST_TDS = vendorBean.getCGST_TDS();
            CGST_TDS = decimalFormat.format(Double.valueOf(CGST_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCRPST())) {
            CRPST = vendorBean.getCRPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getCRET_AMT())) {
            CRET_AMT = vendorBean.getCRET_AMT();
            CRET_AMT = decimalFormat.format(Double.valueOf(CRET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSTAX_AMT())) {
            STAX_AMT = vendorBean.getSTAX_AMT();
            STAX_AMT = decimalFormat.format(Double.valueOf(STAX_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSIT_TDS())) {
            SIT_TDS = vendorBean.getSIT_TDS();
            SIT_TDS = decimalFormat.format(Double.valueOf(SIT_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSGST_TDS())) {
            SGST_TDS = vendorBean.getSGST_TDS();
            SGST_TDS = decimalFormat.format(Double.valueOf(SGST_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSRPST())) {
            SRPST = vendorBean.getSRPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getSRET_AMT())) {
            SRET_AMT = vendorBean.getSRET_AMT();
            SRET_AMT = decimalFormat.format(Double.valueOf(SRET_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTTAX_AMT())) {
            OTTAX_AMT = vendorBean.getOTTAX_AMT();
            OTTAX_AMT = decimalFormat.format(Double.valueOf(OTTAX_AMT));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTIT_TDS())) {
            OTIT_TDS = vendorBean.getOTIT_TDS();
            OTIT_TDS = decimalFormat.format(Double.valueOf(OTIT_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTGST_TDS())) {
            OTGST_TDS = vendorBean.getOTGST_TDS();
            OTGST_TDS = decimalFormat.format(Double.valueOf(OTGST_TDS));
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTRPST())) {
            OTRPST = vendorBean.getOTRPST();
        }
        if (!ApplicationUtils.isBlank(vendorBean.getOTRET_AMT())) {
            OTRET_AMT = vendorBean.getOTRET_AMT();
            OTRET_AMT = decimalFormat.format(Double.valueOf(OTRET_AMT));
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
                                            <input type="text" class="form-control text-left" name="txtZone" id="txtZone" value="<%=Zone%>" readonly="true" >
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Circle'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" readonly="true" value="<%=Circle%>" size="20" id="txtCircle" name="txtCircle" >
                                        </span>
                                    </div>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Division'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtDiv" id="txtDiv" value="<%=Division%>" readonly="true" >
                                        </span>
                                    </div>
                                </fieldset>
                                <fieldset class="fldst_border">
                                    <legend>Project Details:</legend>


                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PROJECT ID'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtProjNum" id="txtProjNum" value="<%=PROJECT_ID%>" readonly="true" >
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PROJECT DESC'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" value="<%=PROJECT_DESC%>" size="20" id="txtProjDesc" name="txtProjDesc" readonly="true" >
                                        </span>

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='PROJECT CREATION DATE'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" value="<%=CREATION_DATE%>" size="20" id="txtCreation_dt" name="txtCreation_dt" readonly="true" >
                                        </span>
                                    </div>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL PO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMATERIAL_PO" id="txtMATERIAL_PO" type="text" size="20" class="form-control text-left" value="<%=MATERIAL_PO%>" maxlength="15" readonly="true" >
                                        </span>



                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES PO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtCENTAGES_PO" id="txtCENTAGES_PO" value="<%=CENTAGES_PO%>" readonly="true" > 
                                        </span>

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='SERVICE PO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtSERVICE_PO" id="txtSERVICE_PO" type="text" size="20" class="form-control text-left" value="<%=SERVICE_PO%>" maxlength="15" readonly="true" > 
                                        </span>
                                    </div>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL PO AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtSERVICE_PO" id="txtSERVICE_PO" type="text" size="20" class="form-control text-left"  value="<%=MAT_PO_AMOUNT%>"  maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES PO AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCENTAGES_PO" id="txtCENTAGES_PO" type="text" size="20" class="form-control text-left" value="<%=CEN_PO_AMOUNT%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL PO AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtSERVICE_PO" id="txtSERVICE_PO" type="text" size="20" class="form-control text-left" value="<%=CIV_PO_AMOUNT%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='TOTAL PO AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtSERVICE_PO" id="txtSERVICE_PO" type="text" size="20" class="form-control text-left" value="<%=TotalPoAmount%>" maxlength="15" readonly="true" > 
                                        </span>

                                    </div>
                                </fieldset>
                                <fieldset class="fldst_border">
                                    <legend>Vendor Details:</legend>



                                    <div class="row">

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='SAP Vendor Code'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtVNum" id="txtVNum" value="<%=VendorNum%>" readonly="true" > 
                                        </span>

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Vendor Name'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtVName" id="txtVName" type="text" size="20" class="form-control text-left" value="<%=VendorName%>" maxlength="15" readonly="true" > 
                                        </span>

                                    </div>
                                </fieldset>
                                <fieldset class="fldst_border">
                                    <legend>Invoice Details:</legend>  

                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Vendor Invoice Number'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" readonly="true" value="  <%=InvoiceNumber%>" size="25" id="txtStatus" name="txtStatus" >
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Invoice Status/Pending With'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" readonly="true" value=" <%=Status%>" size="25" id="txtStatus" name="txtStatus" >
                                        </span>
                                    </div>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MSEDCL Invoice Number'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" readonly="true" value="<%=MSEDCL_INVOICE_NUMBER%>" size="25" id="txtStatus" name="txtStatus" >
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MSEDCL Invoice Amount'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" readonly="true" value=" <%=INV_PO_AMOUNT%>" size="25" id="txtStatus" name="txtStatus" >
                                        </span>
                                    </div>
                                    <div class="row">    

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='Tax Code'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input name="txtTaxAmt" id="txtTaxAmt" type="text" size="20" class="form-control text-left"   value="<%=TAX_CODE%>" maxlength="15" readonly="true" > 
                                        </span>  
                                        
                                    </div>&nbsp;


                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS PARK DOC'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMATERIAL_WS_PARK_DOC" id="txtMATERIAL_WS_PARK_DOC" value="<%=MATERIAL_WS_PARK_DOC%>" readonly="true" > 
                                        </span>

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_DOC_DATE" id="txtMAT_DOC_DATE" type="text" size="20" class="form-control text-left" value="<%=MAT_DOC_DATE%>" maxlength="15" readonly="true" > 
                                        </span>
                                    </div>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS PARK AMT'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_PARK_AMT" id="txtMAT_WS_PARK_AMT" value="<%=MAT_WS_PARK_AMT%>" readonly="true" > 
                                        </span>


                                    </div>

                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS CLEARING DOC NO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=MAT_WS_CLEARING_DOC_NO%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS CLEARING DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=MAT_WS_AC_DOC_DATE%>" readonly="true" > 
                                        </span>
                                    </div>

                                <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS CLEARING AMT'/>.</span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_AMT" id="txtMAT_WS_CLEARING_AMT" type="text" size="20" class="form-control text-left" value="<%=MAT_WS_CLEARING_AMT%>" maxlength="15" readonly="true" > 
                                        </span> </div>
                                <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS TAX AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=WTAX_AMT%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS IT TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=WIT_TDS%>" readonly="true" > 
                                        </span>
                                   
                                    </div>
                                         <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS GST TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=WGST_TDS%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS RETENSION DOC'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=WRPST%>" readonly="true" > 
                                        </span>
                                         </div>
                                        <div class="row">
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL WS RETENSION AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=WRET_AMT%>" readonly="true" > 
                                        </span>
                                    </div>
                                        &nbsp;
                                    <div class="row">

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS PARK DOC NO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_PARK_DOC_NO" id="txtMAT_OS_PARK_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=MAT_OS_PARK_DOC_NO%>" maxlength="15" readonly="true" > 
                                        </span>  


                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_DOC_DATE" id="txtMAT_OS_DOC_DATE" type="text" size="20" class="form-control text-left" value="<%=MAT_OS_DOC_DATE%>" maxlength="15" readonly="true" > 
                                        </span>  
                                    </div>
                                    <div class="row">

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS PARK AMT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_PARK_AMT" id="txtMAT_OS_PARK_AMT" type="text" size="20" class="form-control text-left" value="<%=MAT_OS_PARK_AMT%>" maxlength="15" readonly="true" > 
                                        </span>  



                                    </div>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS CLEARING DOC NO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_CLEARING_DOC_NO" id="txtMAT_OS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=MAT_OS_CLEARING_DOC_NO%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS CLEARING DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_AC_DOC_DATE" id="txtMAT_OS_AC_DOC_DATE" type="text" size="20" class="form-control text-left" value="<%=MAT_OS_AC_DOC_DATE%>" maxlength="15" readonly="true" > 
                                        </span>  


                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS CLEARING AMT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_CLEARING_AMT" id="txtMAT_OS_CLEARING_AMT" type="text" size="20" class="form-control text-left" value="<%=MAT_OS_CLEARING_AMT%>" maxlength="15" readonly="true" > 
                                        </span>  
                                    </div>
                                        <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS TAX AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=OTAX_AMT%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS IT TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=OIT_TDS%>" readonly="true" > 
                                        </span>
                                   
                                    </div>
                                         <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS GST TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=OGST_TDS%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS RETENSION DOC'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=ORPST%>" readonly="true" > 
                                        </span>
                                         </div>
                                        <div class="row">
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OS RETENSION AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=ORET_AMT%>" readonly="true" > 
                                        </span>
                                    </div>
                                    &nbsp;
                                    <div class="row">

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH PARK DOC'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_PARK_DOC_NO" id="txtMAT_OS_PARK_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=MAT_OTH_PARK_DOC_NO%>" maxlength="15" readonly="true" > 
                                        </span>  


                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_DOC_DATE" id="txtMAT_OS_DOC_DATE" type="text" size="20" class="form-control text-left" value="<%=MAT_OTH_DOC_DATE%>" maxlength="15" readonly="true" > 
                                        </span>  
                                    </div>
                                    <div class="row">

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH PARK AMT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_PARK_AMT" id="txtMAT_OS_PARK_AMT" type="text" size="20" class="form-control text-left" value="<%=MAT_OTH_PARK_AMT%>" maxlength="15" readonly="true" > 
                                        </span>  



                                    </div>
                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH CLEARING DOC NO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_CLEARING_DOC_NO" id="txtMAT_OS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=MAT_OTH_CLEARING_DOC_NO%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MAT OTH CLEARING DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_AC_DOC_DATE" id="txtMAT_OS_AC_DOC_DATE" type="text" size="20" class="form-control text-left" value="<%=MAT_OTH_AC_DOC_DATE%>" maxlength="15" readonly="true" > 
                                        </span>  


                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH CLEARING AMT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_OS_CLEARING_AMT" id="txtMAT_OS_CLEARING_AMT" type="text" size="20" class="form-control text-left" value="<%=MAT_OTH_CLEARING_AMT%>" maxlength="15" readonly="true" > 
                                        </span>  
                                    </div>
                                        <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH TAX AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=OTTAX_AMT%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH IT TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=OTIT_TDS%>" readonly="true" > 
                                        </span>
                                   
                                    </div>
                                         <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH GST TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=OTGST_TDS%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH RETENSION DOC'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=OTRPST%>" readonly="true" > 
                                        </span>
                                         </div>
                                        <div class="row">
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='MATERIAL OTH RETENSION AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=OTRET_AMT%>" readonly="true" > 
                                        </span>
                                    </div>&nbsp;


                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES PARK DOC'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCENTAGES_PARK_DOC" id="txtCENTAGES_PARK_DOC" type="text" size="20" class="form-control text-left" value="<%=CENTAGES_PARK_DOC%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtCEN_DOC_DATE" id="txtCEN_DOC_DATE" value="<%=CEN_DOC_DATE%>" readonly="true" > 
                                        </span>
                                    </div>
                                    <div class="row">

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES PARK AMT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCEN_PARK_AMT" id="txtCEN_PARK_AMT" type="text" size="20" class="form-control text-left" value="<%=CEN_PARK_AMT%>" maxlength="15" readonly="true" > 
                                        </span>  

                                    </div>

                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES CLEARING DOC NO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtCEN_CLEARING_DOC_NO" id="txtTaxCode" value="<%=CEN_CLEARING_DOC_NO%>" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES CLEARING DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCEN_AC_DOC_DATE" id="txtCEN_AC_DOC_DATE" type="text" size="20" class="form-control text-left" value="<%=CEN_AC_DOC_DATE%>" maxlength="15" readonly="true" > 
                                        </span>  
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES CLEARING AMT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCEN_CLEARING_AMT" id="txtCEN_CLEARING_AMT" type="text" size="20" class="form-control text-left" value="<%=CEN_CLEARING_AMT%>" maxlength="15" readonly="true" > 
                                        </span> 
                                    </div>
                                        <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES TAX AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=CTAX_AMT%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES IT TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=CIT_TDS%>" readonly="true" > 
                                        </span>
                                   
                                    </div>
                                         <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES GST TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=CGST_TDS%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES RETENSION DOC'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=CRPST%>" readonly="true" > 
                                        </span>
                                         </div>
                                        <div class="row">
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CENTAGES RETENSION AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=CRET_AMT%>" readonly="true" > 
                                        </span>
                                    </div>&nbsp;
                                    <div class="row">



                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL PARK DOC NO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">

                                            <input type="text" class="form-control text-left" name="txtCIVIL_PARK_DOC" id="txtCIVIL_PARK_DOC" value="<%=CIVIL_PARK_DOC%>" readonly="true" > 

                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCIVIL_DOC_DATE" id="txtCIVIL_DOC_DATE" type="text" size="20" class="form-control text-left" value="<%=CIVIL_DOC_DATE%>" maxlength="15" readonly="true" > 
                                        </span>  
                                    </div>

                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL PARK AMT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCIVIL_CLEARING_AMT" id="txtCIVIL_CLEARING_AMT" type="text" size="20" class="form-control text-left" value="<%=CIVIL_CLEARING_AMT%>" maxlength="15" readonly="true" > 
                                        </span> 




                                    </div>

                                    <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL CLEARING DOC NO'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCIVIL_AC_DOC_NO" id="txtCIVIL_AC_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=CIVIL_CLEARING_DOC_NO%>" maxlength="15" readonly="true" > 
                                        </span>   

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL CLEARING DOC DATE'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCIVIL_AC_DOC_DATE" id="txtCIVIL_AC_DOC_DATE" type="text" size="20" class="form-control text-left" value="<%=CIVIL_AC_DOC_DATE%>" maxlength="15" readonly="true" > 
                                        </span>   

                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL CLEARING AMT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtCIVIL_CLEARING_AMT" id="txtCIVIL_CLEARING_AMT" type="text" size="20" class="form-control text-left" value="<%=CIVIL_CLEARING_AMT%>" maxlength="15" readonly="true" > 
                                        </span>
                                    </div>
                                        <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL TAX AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=STAX_AMT%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL IT TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=SIT_TDS%>" readonly="true" > 
                                        </span>
                                   
                                    </div>
                                         <div class="row">
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL GST TDS AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3"> 
                                            <input name="txtMAT_WS_CLEARING_DOC_NO" id="txtMAT_WS_CLEARING_DOC_NO" type="text" size="20" class="form-control text-left" value="<%=SGST_TDS%>" maxlength="15" readonly="true" > 
                                        </span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL RETENSION DOC'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=SRPST%>" readonly="true" > 
                                        </span>
                                         </div>
                                        <div class="row">
                                    <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5"><fmt:message key='CIVIL RETENSION AMOUNT'/></span>
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                                            <input type="text" class="form-control text-left" name="txtMAT_WS_AC_DOC_DATE" id="txtMAT_WS_AC_DOC_DATE" value="<%=SRET_AMT%>" readonly="true" > 
                                        </span>
                                    </div>
      
                                    <div class="row">                                      
                                        <span class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-right h5">
                                            <% if (UserType.equals("Vendor")) {%>
                                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                            <% } else if (UserType.equals("Emp")) {%>
                                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_PO_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                            <% }%>

                                        </span>





                                    </div>
                                </fieldset>
                            </table>  


                            <hr/>

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