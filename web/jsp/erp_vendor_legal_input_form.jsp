<%-- 
    Document   : erp_vendor_legal_input_form
    Created on : 15 Sep, 2022, 4:42:50 PM
    Author     : Rikma Rai
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.emp.legal.bean.LegalInvoiceInputBean"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="in.emp.vendor.bean.VendorBean"%>
<%@page import="in.emp.hrms.bean.HRMSUserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="java.util.*"%>
<%@page import="in.emp.user.bean.UserBean"%>


<%@page import="in.emp.vendor.bean.VendorApplFileBean"%>
<%@page import="in.emp.vendor.bean.POBean"%>
<%@page import="in.emp.vendor.bean.ProjBean"%>
<%@page import="java.util.*"%>
<%@page import="in.emp.vendor.bean.VendorInputBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%> 
<%@page import="in.emp.legal.bean.FeeTypeDtlsBean"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<%

    String recordsVar = "No Records To Display !!!";
    String uiAction = "";
    LinkedList FileList = new LinkedList();
    LinkedList vendorList = new LinkedList();
    LinkedList FeeTypeDtlList = new LinkedList();

    LinkedList applListNew = new LinkedList();
    LinkedList applListOld = new LinkedList();

    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }

    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    POBean POBeanObj = new POBean();
    LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
    LinkedList POList = new LinkedList();
    LinkedList PROJList = new LinkedList();
    String Forward_to_office_code = "";

    //String Status="";
    int flag = 0;
    if (request.getSession().getAttribute("LegalVendorInputForm") != null) {
        legalInvoiceInputBean = (LegalInvoiceInputBean) request.getSession().getAttribute("LegalVendorInputForm");
    }

    String UserNumber = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
    String viewAction = "";
    String viewAction1 = "";
    String viewAction2 = "";

    if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_FORM)) {
            //actionStatus = "Saved";
            viewAction = "redir";
            viewAction1 = "redi";
            viewAction2 = "redi_ps";
        }
    }

    String ApplId = "";
    String courtCaseNo = "";
    String caseRefNo = "";
    String courtName = "";
    String caseDescription = "";
    String caseTypeDesc = "";
    Date sysdate = new Date();
    String VendorInvoiceDt = "";
    String VendorInwardDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
    String EDCLInwardDate = "";
    String InvoiceResubmissionDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
    String InvoiceAmt = "";
    String InwardNum = "";
    String InwardDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
    String InvoiceSubmitDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
    String InvoiceDate = "";
    String InvoiceFrmDate = "";
    String InvoiceToDate = "";
    String InvoiceRejDate = "";
    String InvoiceApprDate = "";
    String Status = "";
    String Reason = "";
    String UserType = "";
    String LocationId = "";
    String VendorNum = "";
    String VendorName = "";
    String partyNames = "";
    String vsPartyNames = "";
    String dealingOffice = "";
    String Office_Code = "";
    String Designation = "";
    String Plant = "";
    String Module = " ";
    String Plant_Desc = " ";
    String IsRejectFlag = " ";
    String IsApproveFlag = " ";
    String showinwflag = " ";
    String showrejdateflag = " ";
    String showapprdateflag = " ";
    String file = " ";
    String projectid = "";
    String selectedmodule = "";
    String Module_dtl = "";
    String Invoice_type = "";
    String InvoiceNum = "";
    String Total_PO_Amt = "";
    String Total_Bal_amt = "";
    String SUBMIT_AT_LOCATION = "";
    String zone = "";
    String feeType = "";
    boolean isWithCourtCaseNo = true;
    String circle = "";
    String division = "";
    String Purchasing_group = "";
    String MATERIAL_PO = "";
    String CENTAGES_PO = "";
    String SERVICE_PO = "";
    String FORWARD_AT_PLANT = "";
    String FORWARD_AT_DESC = "";
    String rejectReason = "";
    if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
        UserType = "Vendor";
    } else if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
        UserType = "Emp";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getApplId())) {
        ApplId = legalInvoiceInputBean.getApplId() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCourtCaseNo())) {
        courtCaseNo = legalInvoiceInputBean.getCourtCaseNo() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCaseRefNo())) {
        caseRefNo = legalInvoiceInputBean.getCaseRefNo() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCourtName())) {
        courtName = legalInvoiceInputBean.getCourtName() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCaseDescription())) {
        caseDescription = legalInvoiceInputBean.getCaseDescription() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCaseTypeDesc())) {
        caseTypeDesc = legalInvoiceInputBean.getCaseTypeDesc() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getDealingOfficeCode())) {
        dealingOffice = legalInvoiceInputBean.getDealingOfficeName();
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPartyNames())) {
        partyNames = legalInvoiceInputBean.getPartyNames() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getVsPartyNames())) {
        vsPartyNames = legalInvoiceInputBean.getVsPartyNames() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getFeeType())) {
        feeType = legalInvoiceInputBean.getFeeType() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceAmount())) {
        InvoiceAmt = legalInvoiceInputBean.getInvoiceAmount() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getInvSubmitDate())) {
        InvoiceSubmitDate = new SimpleDateFormat("dd-MMM-yyyy").format(legalInvoiceInputBean.getInvSubmitDate());
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceDate())) {
        InvoiceDate = new SimpleDateFormat("dd-MMM-yyyy").format(legalInvoiceInputBean.getInvoiceDate());
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getMsedclInwardDate())) {
        EDCLInwardDate = new SimpleDateFormat("dd-MMM-yyyy").format(legalInvoiceInputBean.getMsedclInwardDate());
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getSaveFlag())) {
        Status = legalInvoiceInputBean.getSaveFlag() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceNumber())) {
        InvoiceNum = legalInvoiceInputBean.getInvoiceNumber() + "";
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getRejectReason())) {
        rejectReason = legalInvoiceInputBean.getRejectReason() + "";
    }
    String regionText = "", zoneText = "", circleText = "", divText = "", subDivText = "", coText = "", coSection = "";
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getRegionText())) {
        regionText = legalInvoiceInputBean.getRegionText();
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getZoneText())) {
        zoneText = legalInvoiceInputBean.getZoneText();
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCircleText())) {
        circleText = legalInvoiceInputBean.getCircleText();
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getDivisionText())) {
        divText = legalInvoiceInputBean.getDivisionText();
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getSubDivisionText())) {
        subDivText = legalInvoiceInputBean.getSubDivisionText();
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCorporateOffice())) {
        coText = legalInvoiceInputBean.getCorporateOffice();
    }
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getDeptName())) {
        coSection = legalInvoiceInputBean.getDeptCode() + "-" + legalInvoiceInputBean.getDeptName();
        //System.out.println("coSection "+coSection);
    }
    String checkedValueCaseNo = "", checkedValueWithoutCaseNo = "";
    if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getIsWithCourtCaseNo())) {
        if (legalInvoiceInputBean.getIsWithCourtCaseNo().equalsIgnoreCase("Y")) {
            isWithCourtCaseNo = true;
            checkedValueCaseNo = "checked";
        } else {
            checkedValueWithoutCaseNo = "checked";
            isWithCourtCaseNo = false;
        }
    }
    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA) != null) {
        FileList = (LinkedList) request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA);
    }

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_FEE_TYPE_DTL_SESSION_DATA) != null) {
        FeeTypeDtlList = (LinkedList) request.getSession().getAttribute(ApplicationConstants.VENDOR_FEE_TYPE_DTL_SESSION_DATA);
    }

    if (((Status == "") || (Status.equals("Saved")) || Status.equals("Rejected")) && UserType.equals("Vendor")) {

        flag = 2;//can edit 
    }
    if ((Status.equals("Accepted") || Status.equals("Completed")) && UserType.equals("Vendor")) {
        flag = 4;
    }

    if (((Status.equals("Submitted"))) && UserType.equals("Emp")) {
        flag = 3;
    }
    if (((Status.equals("Forwarded"))) && UserType.equals("Emp")) {
        flag = 5;
    }

    String redirectUrl = "";
    if (UserType.equals("Vendor")) {
        redirectUrl = ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_LIST;
    } else {
        redirectUrl = ApplicationConstants.UIACTION_GET_LEGAL_VENDOR_INVOICE;
    }

    if (flag != 2) {
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getVendorInwardDate())) {
            VendorInwardDate = new SimpleDateFormat("dd-MMM-yyyy").format(legalInvoiceInputBean.getVendorInwardDate());
        }
    }
String LEGALINVOICE_FILESIZE_LIMIT  ="0";
            if(System.getProperty("LEGALINVOICE_FILESIZE_LIMIT") != null)
            {
               LEGALINVOICE_FILESIZE_LIMIT    = System.getProperty("LEGALINVOICE_FILESIZE_LIMIT");
            }
%>

<style> 
    table td{
        border-top: 0px;
    }
</style> 
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Vendor Payment Tracking System</title>
        <!-- JQUERY SCRIPTS -->
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <!-- AUTOCOMPLETE-->
        <link href="css/autocomplete.css" rel="stylesheet" />

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_vendor_legal_input_form.js"></script>


    </head>
    <body>
        <c:if test="${!empty language}">
            <fmt:setLocale value="${language}" scope="session" />
        </c:if>
        <div id="wrapper">
            <%@ include file="nav_emp_header.jsp"%>
            <!-- /. NAV TOP  -->
            <%@ include file="emp_nav_vmenu.jsp"%>
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner" style="min-height:500px;">

                    <%@ include file="navJs.jsp"%>
                    <input type="hidden" name="UserNumber" id="UserNumber" value ="<%=UserNumber%>"/>
                    <input type="hidden" name="view" id="view" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS)%>"/>
                    <input type="hidden" name="redi" id="redi" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_APPL_FORM%>"/>
                    <input type="hidden" name="redi_ps" id="redi_ps" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_PS_APPL_FORM%>"/>
                    <input type="hidden" name="mod" id="mod" value="<%=Module%>"/>
                    <input type="hidden" name="inv_type" id="inv_type" value="<%=Invoice_type%>" />
                    <input type="hidden" name="ps_pm_mod" id="ps_pm_mod" value="<%=selectedmodule%>" />
                    <input type="hidden" name="redirectUrl" id="redirectUrl" value="<%=redirectUrl%>"/>
                    <input type="hidden" name="rem" id="rem" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_FORM%>"/>
                    <input type="hidden" name="rejflag" id="rejflag"  value="<%=IsRejectFlag%>"/>
                    <input type="hidden" name="apprflag" id="apprflag"  value="<%=IsApproveFlag%>"/>
                    <input type="hidden" name="showinwflag" id="showinwflag"  value="<%=showinwflag%>"/>
                    <input type="hidden" name="status" id="status" value ="<%=Status%>"/>
                    <input type="hidden" name="submit_at_location" id="submit_at_location" value ="<%=SUBMIT_AT_LOCATION%>"/>
                    <input type="hidden" name="file" id="file" value ="<%=file%>"/>
                    <input type="hidden" name="zone" id="zone" value="<%=zone%>" />
                    <input type="hidden" name="circle" id="circle" value="<%=circle%>" />
                    <input type="hidden" name="division" id="division" value="<%=division%>" />
                    <input type="hidden" name="txtPurchasing_group" id="txtPurchasing_group" value="<%=Purchasing_group%>" />
                    <input type="hidden" name="txtOnloadPurchasing_group" id="txtOnloadPurchasing_group"  />
                    <input type="hidden" name="txtOnloadPurchasing_group" id="txtOnloadPurchasing_group"  />
                    <input type="hidden"  id="Module" name="txtModule" value = "<%=Module_dtl%>"  /> 
                    <input type="hidden"  id="userType" name="userType" value = "<%=UserType%>"  /> 
                    <input type="hidden"  id="txtpobal" name="txtpobal" value="<%=Total_Bal_amt%>"  /> 
                    <input type="hidden"  id="selectedPlant" name="selectedPlant"   /> 
                    <input type="hidden"  id="selectedOffieCode" name="selectedOffieCode"   /> 
                    <input type="hidden"  id="mobileNO" name="mobileNO" value="<%= legalInvoiceInputBean.getMobileNo()%>"  /> 
                    <input type="hidden"  id="emailID" name="emailID"  value="<%= legalInvoiceInputBean.getEmailId()%>" /> 
                    <input type="hidden"  id="isWithCourtCaseNo" name="isWithCourtCaseNo" value="<%=isWithCourtCaseNo%>"  /> 
                    <input type="hidden" name="office_type" id="office_type" value=""/>
                    <input type="hidden" name="region_id" id="region_id" value=""/>
                    <input type="hidden"  id="LEGALINVOICE_FILESIZE_LIMIT" name="LEGALINVOICE_FILESIZE_LIMIT" value="<%=LEGALINVOICE_FILESIZE_LIMIT%>"  />

                    <div class="content_container">

                        <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                            <div class="col-md-12"><h3><fmt:message key='Legal Invoice Form'/></h3></div>
                            <div >&nbsp;</div>
                        </div>
                        <!-- End of Network Search results table -->

                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                            <%if (UserType.equalsIgnoreCase("Vendor") && Status == "") {%>
                            <table class="table" border="0" cellspacing="0" cellpadding="1"  id="withOrWithoutCourtCaseRadio" style="width:100%">
                                <tr>
                                    <td class="Label_login"><input type="radio"  name="rad_courtCase" id="rad_withCourtCase" value="withCourtCase" checked onclick="showWithOrWithoutCourtCaseFields()">&nbsp;<fmt:message key='With Court Case No'/>
                                    </td>
                                    <td class="Label_login"><input type="radio"  name="rad_courtCase" id="rad_withoutCourtCase" value="withoutCourtCase" onclick="showWithOrWithoutCourtCaseFields()">&nbsp;<fmt:message key='Without Court Case No'/>
                                    </td>
                                </tr>
                            </table>
                            <%} else if ((UserType.equalsIgnoreCase("Vendor") && Status != "") || (UserType.equalsIgnoreCase("Emp") && Status != "")) {%>
                            
                            <table class="table" border="0px" cellspacing="0" cellpadding="1"  id="withOrWithoutCourtCaseRadio" style="width:100%">
                                <tr style="border-top: 0px">
                                    <td class="Label_login" style="border-top: 0px"><input type="radio"  name="rad_courtCase" id="rad_withCourtCase" value="withCourtCase" <%=checkedValueCaseNo%> onclick="showWithOrWithoutCourtCaseFields()" disabled >&nbsp;<fmt:message key='With Court Case No'/>
                                    </td>
                                    <td class="Label_login" style="border-top: 0px"><input type="radio"  name="rad_courtCase" id="rad_withoutCourtCase" value="withoutCourtCase" <%=checkedValueWithoutCaseNo%> onclick="showWithOrWithoutCourtCaseFields()" disabled> &nbsp;<fmt:message key='Without Court Case No'/>
                                    </td>
                                </tr>
                            </table>
                            <%}%>
                            <table class="table" border="0" cellspacing="0" cellpadding="2" id="table_content"> <!-- Start of  table_content table  -->
                                <tr >
                                    <td class="text-right h5"><fmt:message key='Application ID'/></td>
                                    <td>

                                        <input type="text" class="form-control text-left" name="txtApplicationId" id="txtApplicationId"  style="width: 100%"  value="<%= ApplId%>" readonly/> 
                                    </td>
                                    <td colspan="2" class="text-right h5"><fmt:message key='Status'/></td>
                                    <td ><input type="text" name="txtStatus" id="txtStatus" style="width: 100%" value ="<%= Status%>" class="form-control text-left" readonly="readonly" >
                                    </td>
                                </tr>
                                <%  if (!UserType.equals("Emp")) {%>
                                <tr>
                                    <td class="text-right h5"><fmt:message key='Vendor Code.'/></td>
                                    <td>
                                        <input type="text" class="form-control text-left" name="txtVendorCode" id="txtVendorCode"  style="width: 100%"  readonly value="<%= legalInvoiceInputBean.getVendorNumber()%>" >
                                    </td>
                                    <td colspan="2" class="text-right h5"><fmt:message key='Vendor Name'/></td>
                                    <td >
                                        <input type="text" class="form-control"   size="20" style="width: 100%" id="txtVendorName" name="txtVendorName" value="<%= legalInvoiceInputBean.getVendorName()%>" readonly>
                                    </td>
                                </tr>
                                <% } else if (UserType.equals("Emp") && (Status.equals("Submitted") || Status.equals("Saved"))) {%>
                                <tr>
                                    <td class="text-right h5"><fmt:message key='Vendor Code.'/></td>
                                    <td>
                                        <input type="text" class="form-control text-left" name="txtVendorCode" id="txtVendorCode"  style="width: 100%" value="<%= legalInvoiceInputBean.getVendorNumber()%>" readonly /> 
                                    </td>
                                    <td colspan="2" class="text-right h5"><fmt:message key='Vendor Name'/></td>
                                    <td >
                                        <input type="text" class="form-control"   size="20" style="width: 100%" id="txtVendorName" name="txtVendorName" value="<%= legalInvoiceInputBean.getVendorName()%>" readonly/>
                                    </td>
                                </tr>
                                <% } else if (UserType.equals("Emp") && Status.equals("")) {%>
                                <tr>
                                    <td class="text-right h5"><fmt:message key='Vendor Code.'/></td>
                                    <td>
                                        <input type="text" class="form-control text-left" name="txtVendorCode" id="txtVendorCode" onblur="populateAllVendors()" style="width: 100%"  title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> /> 
                                    </td>
                                    <td colspan="2" class="text-right h5"><fmt:message key='Vendor Name'/></td>
                                    <td >
                                        <input type="text" class="form-control"   size="20" style="width: 100%" id="txtVendorName" name="txtVendorName" value="" readonly>
                                    </td>
                                </tr>
                                <% }%>


                               
                                <tbody id="withCourtCaseNoBody">
                                    <tr>
                                        <td class="text-right h5"><fmt:message key='Court Case No.'/></td>
                                        <td   class="text-right h5 ">
                                            <% if (!UserType.equals("Emp")){%>
                                            <table width="100%"><tr><td>   <input type="text" name="txtCourtCaseNo" class="form-control" title="Type & click Search button" id="txtCourtCaseNo" style="width: 100%; float:left" placeholder=<fmt:message key='"Type & click Search button"'/> value="<%= (legalInvoiceInputBean.getCourtCaseNo() == null ? "" : legalInvoiceInputBean.getCourtCaseNo())%>" 
                                                  <% if (Status == "" || Status.equals("Saved")) {
                                                } else {
                                                    out.print("disabled='true'");
        }%>/></td>
                                                        <%}%>

              <%if (!(UserType.equals ("Emp")) && (flag==0 || flag==2)) {%>
              <td><input type="button" value=<fmt:message key='Search'/> name="ButtonSrch" id="ButtonSrch" style="float: right;" onclick="openSearcher('<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDOR_SEARCH_COURT_CASE)%>')"
                                                            class="btn btn-primary"/></td></tr></table>
                                                            <%}%>

                                            <% if (UserType.equals ("Emp")){%>
                                            <input type="text" name="txtCourtCaseNo" class="form-control" title="Type & click Search button" id="txtCourtCaseNo" style="width: 100%;" placeholder=<fmt:message key='"Type & click Search button"'/> value="<%= (legalInvoiceInputBean.getCourtCaseNo() == null ? "" : legalInvoiceInputBean.getCourtCaseNo())%>" onblur="openSearcher('<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDOR_SEARCH_COURT_CASE)%>')" <% if (Status == "" || Status.equals("Saved")) {
                                                } else {
                                                    out.print("disabled='true'");
                                                }%> />
                                            <% }%>
                                        </td>

                                        <td colspan="2" class="text-right h5"><fmt:message key='Case Reference No.'/></td>
                                        <td> 
                                            <input name="txtCaseRefNo" id="txtCaseRefNo" type="text"  value ="<%= caseRefNo%>" size="20" class="form-control" maxlength="15" readonly />
                                        </td> 


                                    </tr><tr>
                                        <td class="text-right h5" ><fmt:message key='Court Name'/></td>
                                        <td>
                                            <input type="text" class="form-control text-left" name="txtCourtName" id="txtCourtName" style="width: 100%;" readonly value ="<%= courtName%>" /> 
                                        </td>

                                        <td class="text-right h5" colspan="2"><fmt:message key='Case Description'/></td>
                                        <td > 
                                            <input name="txtCaseDescription" id="txtCaseDescription" type="text" class="form-control" size="20" style="width: 100%;"   readonly value ="<%= caseDescription%>"/> 
                                        </td>

                                    </tr>
                                    <tr>
                                        <td class="text-right h5" ><fmt:message key='Dealing Office'/></td>
                                        <%  if (!UserType.equals ("Emp")) {%>
                                        <td>
                                            <input type="text" class="form-control text-left" name="txtDealingOffice" id="txtDealingOffice" style="width: 100%;" readonly value ="<%= dealingOffice%>"/> 
                                        </td>
                                        <%}
                                    else if(flag == 3 || (UserType.equals ( "Emp") && (Status.equalsIgnoreCase("Accepted")|| Status.equalsIgnoreCase("Saved")||Status.equalsIgnoreCase("Rejected")||Status.equalsIgnoreCase("Returned")))){%>
                                        <td><input type="text" class="form-control text-left" name="txtDealingOffice" id="txtDealingOffice" style="width: 100%;" readonly value ="<%= dealingOffice%>"/>  </td>
                                            <%}else{%>
                                        <td>
                                            <input type="text" class="form-control text-left" name="txtDealingOffice" id="txtDealingOffice" style="width: 100%;"  title="Type and search or use space-bar" 
                                                   placeholder=<fmt:message key='"Type and search or use space-bar"'/> onblur="populateDealingOffice();" /> </td>
                                            <%}%>

                                        <td class="text-right h5" colspan="2"><fmt:message key='Case Type'/></td>
                                        <td > 
                                            <input name="txtCaseType" id="txtCaseType" type="text" class="form-control" size="20" style="width: 100%;"  readonly  value ="<%= caseTypeDesc%>"/> 
                                        </td>

                                    </tr>

                                    <tr>
                                        <td class="text-right h5" ><fmt:message key='Party Name'/></td>
                                        <td>
                                            <input type="text" class="form-control text-left" name="txtPartyNames" id="txtPartyNames" style="width: 100%;" readonly value ="<%= partyNames%>" /> 
                                        </td>

                                        <td class="text-right h5" colspan="2"><fmt:message key='v/s Party Name'/></td>
                                        <td > 
                                            <input name="txtVsPartyNames" id="txtVsPartyNames" type="text" class="form-control" size="20" style="width: 100%;"   readonly value ="<%= vsPartyNames%>"/> 
                                        </td>
                                    </tr>

                                    <tr>

                                        <td  class="text-right h5"><fmt:message key='Invoice Entry Date'/> </td>
                                        <td> 

                                            <input name="txtVendorInwardDt" id="txtVendorInwardDt" type="text" size="20" class="form-control text-left"  value="<%=VendorInwardDate%>" maxlength="15" readonly />

                                        </td>          

                                        <td colspan="2"  class="text-right h5"><fmt:message key='Invoice Number'/></td>
                                        <td>
                                            <input type="text" class="form-control text-right" name="txtInvoiceNum" id="txtInvoiceNum"   maxlength="40" placeholder=<fmt:message key='"Max length is 15"'/>  value="<%=InvoiceNum%>"  
                                                   <% if (Status == "" || Status.equals ("Saved")) {
                                                } else {
                                                    out.print("disabled='true'");
                                                }%> /></td>




                                    </tr>
                                </tbody>
                                 <!--WITHOUT COURT CASE BODY  for showing region ,zone,circle,division-->
                                <tbody id="withoutCourtCaseNoBody">
                                <input type="hidden"  id="txtDealingOfficewithoutCaseNo" name="txtDealingOfficewithoutCaseNo"   /> 
                                <%if (UserType.equals("Emp")) {%>
                                <tr>

                                    <td class="text-right h5"><fmt:message key='Case Reference No.'/></td>
                                    <td> 
                                        <input name="txtCaseRefNoWithout" id="txtCaseRefNoWithout" type="text"    class="form-control"  title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/>  onblur="populateCaseRefDetails();"/>
                                    </td> 
                                    <td class="text-right h5" colspan="2" ><fmt:message key='Court Case No.'/></td>
                                    <td  class="text-right h5">
                                        <input autocomplete="off" type="text" name="txtCourtCaseNoWithout" id="txtCourtCaseNoWithout" style="width: 100%;" value="<%= courtCaseNo%>"  class="form-control"  placeholder=<fmt:message key='"Type and search or use space-bar"'/> 
                                               <% if (Status == "" || Status.equals ("Saved")) {
                                            } else {
                                                out.print("disabled='true'");
                                            }%> /> </td>
                                                </tr>
                                                <tr>
                                               <td class="text-right h5" ><fmt:message key='Court Name'/></td>
                                    <td>
                                        <input type="text" class="form-control text-left" name="txtCourtNameWithout" id="txtCourtNameWithout" style="width: 100%;" readonly value ="<%= courtName%>" /> 
                                    </td>

                                    <td class="text-right h5" colspan="2"><fmt:message key='Case Description'/></td>
                                    <td > 
                                        <input name="txtCaseDescriptionWithout" id="txtCaseDescriptionWithout" type="text" class="form-control" size="20" style="width: 100%;"   readonly value ="<%= caseDescription%>"/> 
                                    </td>

                                </tr>
                                <tr>
                                    <td class="text-right h5" ><fmt:message key='Dealing Office'/></td>

                                    <td>
                                        <input type="text" class="form-control text-left" name="txtDealingOfficeWithout" id="txtDealingOfficeWithout" style="width: 100%;" readonly value ="<%= dealingOffice%>"/> 
                                    </td>


                                    <td class="text-right h5" colspan="2"><fmt:message key='Party Names'/></td>
                                    <td > 
                                        <input name="txtPartyNamesWithout" id="txtPartyNamesWithout" type="text" class="form-control" size="20" style="width: 100%;"  readonly  value ="<%= partyNames%>"/> 
                                    </td>

                                </tr><%}%>

                                <tr>


                                    <td class="text-right h5" ><label id="mydrop_down_label"><fmt:message key='Region'/></label>.</td>

                                    <td>
                                        <% if(Status == "") { %>
                                        <select class="form-control text-left" id="txtRegion"   onchange="getLegalHierarchyLocation(this, 'ZON')" >
                                            <option value =""><fmt:message key='Select'/></option>
                                        </select>
                                        <%
                                            } else {%>
                                        <select class="form-control text-left" id="txtRegion" <% if (Status.equals("Saved")&&(coText==null||coText=="")) { out.print("onclick=\"showWithOrWithoutCourtCaseFields();\" onchange=\"getLegalHierarchyLocation(this,\'ZON\')\"");
                                            } else {
                                                out.print("readonly");
                                            } %>  >
                                            <option value ="<%= regionText %>" selected><%= regionText %></option>

                                        </select>
                                        <%}%>
                                    </td>
                                    <td  class="text-right h5" colspan="2"><fmt:message key='Zone.'/></td>
                                    <td>
                                        <% if (Status == "") {
                                        %>


                                        <select class="form-control text-left" id="txtZone"    onchange="getLegalHierarchyLocation(this, 'CIR')" >
                                            <option value ='' selected ><fmt:message key='Select'/></option>
                                        </select>
                                        <%
                                            } else {%>

                                        <select class="form-control text-left" id="txtZone" <% if (Status.equals("Saved")&&(coText==null||coText=="")) { out.print("onchange=\"getLegalHierarchyLocation(this,\'CIR\')\"");
                                           } else {
                                               out.print("readonly");
                                           } %>  >
                                            <option value ='' selected ><%= zoneText %></option>
                                        </select>

                                        <%}%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-right h5" ><fmt:message key='Circle'/></td>
                                    <td>
                                        <% if (Status == "") {%>
                                        <select class="form-control text-left" id="txtCircle"    onchange="getLegalHierarchyLocation(this, 'DIV')" >
                                            <option value ="" >Select</option>
                                        </select>
                                        <%} else {%>
                                        <select class="form-control text-left" id="txtCircle" <% if (Status.equals("Saved")&&(coText==null||coText=="")) { out.print("onchange=\"getLegalHierarchyLocation(this,\'DIV\')\"");
                                             } else {
                                                 out.print("readonly");
                                             } %> >
                                            <option value ="" selected><%= circleText %></option>
                                        </select>
                                        <%}%>
                                    </td>

                                    <td class="text-right h5 " colspan="2"><fmt:message key='Division'/></td>
                                    <td > 
                                        <% if (Status == "") {%>
                                        <select class="form-control text-left" id="txtDivision"    onchange="getLegalHierarchyLocation(this, 'SUB')" >
                                            <option value ="">Select</option>
                                        </select>
                                        <%} else {%>
                                        <select class="form-control text-left" id="txtDivision" <% if (Status.equals("Saved")&&(coText==null||coText=="")) { out.print("onchange=\"getLegalHierarchyLocation(this,\'SUB\')\"");
                                                } else {
                                                    out.print("readonly");
                                                } %> >
                                            <option value =""><%= divText %></option>
                                        </select>
                                        <%}%>
                                    </td>


                                </tr>
                                <tr>
                                    <td class="text-right h5" ><fmt:message key='Sub-Division'/></td>
                                    <td > 
                                        <% if (Status == "") {%>
                                        <select class="form-control text-left" id="txtSubDivision"    onchange="getLegalHierarchyLocation(this, 'SUB-DIV')" >
                                            <option value ="">Select</option>
                                        </select>
                                        <%} else {%>
                                        <select class="form-control text-left" id="txtSubDivision" <% if (Status.equals("Saved")&&(coText==null||coText=="")) { out.print("onchange=\"getLegalHierarchyLocation(this,\'SUB-DIV\')\"");
                                        } else {
                                            out.print("readonly");
                                        } %> >
                                            <option value =""><%= subDivText %></option>
                                        </select>
                                        <%}%>
                                    </td>
                                    <td class="text-right h5" colspan="2"><fmt:message key='Dealing Office'/></td>
                                    <td>
                                        <input type="text" class="form-control text-left" name="txtDealingOffice" id="txtDealingOffice" style="width: 100%;" readonly value ="<%= dealingOffice %>"/> 
                                    </td>
                                </tr>

                                <tr>
                                    <td class="text-right h5"><fmt:message key='Corporate Office'/></td>
                                    <td>
                                        <% if (Status == "") {%>
                                        <select class="form-control text-left" id="txtCorporateOffice"   onchange="disableOtherLocation(this.value);getLegalHierarchyLocation(this, 'DEPT')"  >
                                            <option value ="Select"><fmt:message key='Select'/></option>
                                            <option value ="261-Corporate Office">261-Corporate Office</option>
                                        </select>
                                        <%} else {%>
                                        <select class="form-control text-left" id="txtCorporateOffice"   readonly  >
                                            <option><%=coText %></option>
                                        </select>
                                        <%}%>
                                    </td>
                                    <td class="text-right h5" colspan="2"><fmt:message key='Section'/></td>
                                    <td> 
                                        <% if (Status == "") { %>
                                        <select class="form-control text-left" id="txtCorpSection" >
                                            <option value =""><fmt:message key='Select'/></option>
                                        </select>
                                        <%} else {%>
                                        <select class="form-control text-left" id="txtCorpSection" readonly >
                                            <option><%=coSection %></option>
                                        </select>
                                        <% } %>
                                    </td>
                                </tr>     

                                <tr>



                                    <td class="text-right h5"><fmt:message key='Invoice Entry Date'/> </td>
                                    <td > 

                                        <input name="txtVendorInwardDt" id="txtVendorInwardDt" type="text" size="20" class="form-control text-left"  value="<%=VendorInwardDate%>" maxlength="15" readonly />

                                    </td>          





                                    <td colspan="2"  class="text-right h5"><fmt:message key='Invoice Number'/></td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtInvoiceNumWithoutCase" id="txtInvoiceNumWithoutCase"   maxlength="40" placeholder=<fmt:message key='"Max length is 15"'/>  value="<%=InvoiceNum%>"  
                                               <% if (Status == "" || Status.equals("Saved")) {
                                            } else {
                                                out.print("disabled='true'");
                                            }%> /></td>
                                </tr>
                                </tbody>
                                
                                <tr>
                                    <td width="10%" ></td>
                                    <td width="20%">
                                    </td>

                                    <td colspan ="2" width="10%" ></td>
                                    <td width="20%"> 



                                    </td>          

                                    <% if (flag == 2) {%>
                                    <td width="05%">
                                        <!--  <a href="javascript:void(0)" onClick="return callCalender('txtVendorInwardDt', 'yyyyFormat');">
                                              <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />-->
                                        <%}%></td>
                                </tr>


                                <%if(flag == 3){%>
                                <tr>
                                    <td class="text-right h5"><fmt:message key='MSEDCL Inward Number'/></td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtInwardNum" id="txtInwardNum" value="<%=InwardNum%>" placeholder=<fmt:message key='"Max length is 10"'/> maxlength="10"  style="width: 100%" />
                                    </td>
                                    <td colspan ="2" width="10%" class="text-right h5"><fmt:message key='MSEDCL Inward Date'/> </td>
                                    <td> 
                                        <input name="txtInwardDt" id="txtInwardDt" type="text"  size="20"class="form-control" value="<%=EDCLInwardDate%>" maxlength="15"  />
                                    </td>
                                </tr>
                                <%}%>

                                <tr> 
                                    <td  class="text-right h5"><fmt:message key='Invoice Date'/></td>
                                    <td width="20%"> 
                                        <% if (flag == 2) {%>
                                        <input name="" id="txtVendorInvoiceDate" type="text" readonly size="20" class="datefield" value="<%=InvoiceDate%>" maxlength="15" 
                                               <% if (Status == "" || Status.equals("Saved")) {
                                       } else {
                                           out.print("disabled='true'");
                                       }%> /> 
                                        <%  } else {%>
                                        <input name="txtVendorInvoiceDate" id="txtVendorInvoiceDate" type="text"  size="20" class="form-control" value="<%=InvoiceDate%>" maxlength="15" <% if (Status == "" || Status.equals("Saved")) {
                                   } else {
                                       out.print("disabled='true'");
                                   }%> />
                                        <%}%> </td>  
                                    <% if (flag == 2) {%>
                                    <td width="05%">
                                        <%}%></td>

                                </tr>
                                <%if(Status.equals("Returned")){%>
                                <tr>
                                    <td class="text-right h5"><fmt:message key='Reason'/></td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtInwardNum" id="txtInwardNum" value="<%=rejectReason%>" readonly style="width: 100%" />
                                    </td>
                                    <td colspan ="2" width="10%" class="text-right h5"> </td>
                                    <td> 

                                    </td>
                                </tr>
                                <%}%>

                                <% if (showapprdateflag.equals("Y") && (!showrejdateflag.equals("Y"))) {%>
                                <tr> 
                                    <td  class="text-right h5"><fmt:message key='Invoice Approval Date'/> </td>
                                    <td> 
                                        <input name="txtInvApprDt" style="text-align: right;" id="txtInvApprDt" type="text" size="20"class="form-control" value="<%=InvoiceApprDate%>" maxlength="15" readonly="readonly" />
                                    </td>  
                                    <td></td><td></td></tr> <%}%>

                                <%if (showrejdateflag.equals("Y") && (!showapprdateflag.equals("Y"))) {%>
                                <tr>
                                    <td></td><td></td>
                                    <td colspan="2" class="text-right h5"><fmt:message key='Invoice Rejection Date'/> </td>
                                    <td> 
                                        <input name="txtInvRejDt" id="txtInvRejDt" type="text" size="20"class="form-control" value="<%=InvoiceRejDate%>" maxlength="15" readonly="readonly" />
                                    </td>  </tr>
                                    <%}%>
                                    <%if (showrejdateflag.equals("Y") && (showapprdateflag.equals("Y"))) {%>
                                <tr>
                                    <td  class="text-right h5"><fmt:message key='Invoice Approval Date'/> </td>
                                    <td> 
                                        <input name="txtInvApprDt" style="text-align: right;" id="txtInvApprDt" type="text" size="20"class="form-control" value="<%=InvoiceApprDate%>" maxlength="15" readonly="readonly" />
                                    </td> 
                                    <td  width="10%" class="text-right h5" colspan="2"><fmt:message key='Invoice Rejection Date'/> </td>
                                    <td> 
                                        <input name="txtInvRejDt"  id="txtInvRejDt" type="text" size="20"class="form-control" value="<%=InvoiceRejDate%>" maxlength="15" readonly="readonly" />
                                    </td>  </tr>
                                    <%}%>

                            </table>  


                            <!--<hr/>-->
                        </div>                          
                        <div  class="content_container_sub" >
                            <!--   <form  method="post" enctype="multipart/form-data"> -->
                            <div    class="row">
                                <div class="col-lg-2 col-md-2"  border=""> </div>
                                <div class="col-lg-8 col-md-8"  align="center"  border="0">
                                    <label><h4><fmt:message key='Add Fee Type Details'/></h4></label>
                                    <br>
                                    <div align="left">
                                        <%if(Status!=null && Status.equals("") || Status.equals("Saved")){ %>
                                        <input type="button" name="addRowBtn" id="addRowBtn" class="btn btn-success" onclick="addRow('feeTypeDtlTbl')" value='Add Row'/> 
                                        <% } %>
                                    </div>
                                    <table class="table" border="0" id="feeTypeDtlTbl"  name="feeTypeDtlTbl" >
                                        <thead>
                                            <tr class="success">

                                                <th>#</th>                                                
                                                <th><b><fmt:message key='Fee Type'/></b></th>
                                                <th><b><fmt:message key='Invoice Amount (Incl. Taxes)'/></b></th>
                                                <th><b><fmt:message key='Remark'/></b></th>

                                                <th><b><fmt:message key='Remove'/></b></th> 


                                            </tr>   
                                        </thead>
                                        <tbody> 
                                            <%  
                                            Iterator itrdtl = FeeTypeDtlList.iterator();
                                            System.out.println("FeeTypeDtlList::"+FeeTypeDtlList);
                                            int kk = 0;
                                            while (itrdtl.hasNext()) {    
                                           
                                           String feeTypedtl = "";
                                           Integer  feeTypeDtlId=0;
                                              Integer  amount = 0;
                                              String remarkDtl="";
                                              FeeTypeDtlsBean fdb = new FeeTypeDtlsBean();
                                              fdb = (FeeTypeDtlsBean) itrdtl.next();
                                              kk++; 
                                              if (!ApplicationUtils.isBlank(fdb.getRemark())) {
                                                remarkDtl = fdb.getRemark();
                                             }
                                               if (!ApplicationUtils.isBlank(fdb.getFeeType())) {
                                                feeTypedtl = fdb.getFeeType();
                                             }
                                              if (!ApplicationUtils.isBlank(fdb.getFeeTypeDtlsId())) {
                                                feeTypeDtlId = fdb.getFeeTypeDtlsId();
                                             }
                                             if (!ApplicationUtils.isBlank(fdb.getAmount())) {
                                                amount = fdb.getAmount();
                                             }
                                             if (!ApplicationUtils.isBlank(fdb.getFeeType())) {
                                                feeTypedtl = fdb.getFeeType();
                                             }
                                            %>

                                            <tr >
                                                <td><%=kk%>    <input type="hidden"  id="feeTypeDtlId" name="feeTypeDtlId" value = "<%=feeTypeDtlId%>"  /> </td>


                                                <td>
                                                    <select class="form-control text-left" id="sel0"  <% if (Status == "" || Status.equals("Saved")) { out.print("onclick='dynamicfeeType(this)'");
                                            } else {
                                                out.print("disabled='true'");
                                            } %> >
                                                        <option><% if (Status.equals("Saved")) { out.print(feeTypedtl);}
                                            else out.print(feeTypedtl); %></option>
                                                    </select></td>



                                                <td><input name="" style="text-align:right" id="" type="text" size="20"class="form-control" value="<%=amount%>" maxlength="15"  onfocusout='getTotal()' <%  if (Status != "" && !Status.equals("Saved")) { %>readonly="readonly"  <% } %>/></td>

                                                <td><input name=""  id="" type="text" size="20"class="form-control" value="<%=remarkDtl%>" maxlength="15"<%  if (Status != "" && !Status.equals("Saved")) { %> readonly="readonly" <% } %>/></td>

                                                <!--  <td><a href="#nogo" onclick="addRow('feeTypeDtlTbl')"><img src="images/icon_add.gif" alt="Add" width="16" height="16" border="0" /></a></td> -->

                                                <td align="center"><a href="#nogo"    <%  if ((Status.equals("Saved") && UserType.equals("Vendor"))) {%> onclick="deleteFeeTypeDtl('<%=(feeTypeDtlId)%>')" <%  }%>><img onload="getTotal()" src="images/icon_delete.gif" alt="Remove" width="16" height="16" border="0" /></a>  </td></tr>
                                                        <%  }%> 
                                        </tbody>  
                                        <tfoot>
                                        <td></td>
                                        <td class=""> <b><fmt:message key='Total Invoice Amount:'/> </b>
                                        </td>
                                        <td align="right" ><label  id='totalInvAmtLbl'></label>
                                            <input type="hidden" id ='totalInvAmt'></td>  
                                        </tfoot>
                                    </table>
                                </div>

                            </div>
                            <!--      </form> -->
                        </div>

                        <table width="100%" border="0" cellspacing="0" cellpadding="0"><!--save button-->
                            <tr >
                                <td class="col-md-1">
                                </td>  
                                <%if(Status!=null && Status.equals("") || Status.equals("Saved")){ %>
                                <td class="col-md-2" align="center">

                                    <input type="button" value=<fmt:message key='Save'/> name="ButtonSave" id="ButtonSave" style="float: center;" onclick="saveLegalInvoiceButton()"
                                           class="btn  btn-success"/>


                                </td>
                                <td class="col-md-1"> <%if (UserType.equals("Emp")) {%>
                                    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_LEGAL_VENDOR_INVOICE)%>" class="btn btn-danger"><fmt:message key='Back'/></a>   
                                    <%}%></td>
                                    <%}%>
                            </tr>
                        </table>


                        <%
 if ((Status != null) && (Status.equals("Saved")|| Status.equals("Submitted"))){%>

                        <form  method="post" enctype="multipart/form-data">
                            <input type="hidden" id="<%=ApplicationConstants.UIACTION_NAME%>" name ="<%=ApplicationConstants.UIACTION_NAME%>" value="<%=ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_POST%>"/>
                            <!--<input type="hidden" name="view" id="view" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS)%>"/>-->
                            <input type="hidden" name="view" id="view1" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_GET)%>"/>
<!--<input type="hidden" name="dele" id="dele" value="<%//=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_TAFORM_GET)%>"/>-->
                            <center><%  if (((flag == 2)|| UserType.equals("Emp")) && (Status.equals("Saved"))) { %>
                                <div class="row">
                                    <div class="col-lg-2 col-md-2"> </div>
                                    <div class="col-lg-8 col-md-8">

                                        <table class="table" border="0">

                                            <tr><td colspan="3" align="center"><h4><fmt:message key='Upload File'/></h4>

                                                    <% if (((Status != null) && (Status.equals("Saved")) )) {%>
                                                    <div class="form-control-err-msg"><fmt:message key='File Format should be either of jpg, jpeg, png or pdf'/>.</div>
                                                    <div class="form-control-err-msg">Max File size should be 1024 kb </div>
                                                    <%  }%>
                                                </td> </tr>


                                            <tr  align="center">
                                                <!--<td>Upload Legal Invoice Document</td>-->
                                                <td >
                                                    <select name="FOpt" class="form-control" id="FOpt">
                                                        <option value="Invoice Document">Invoice Document</option>
                                                        <option value="Advocate Allotment Letter">Advocate Allotment Letter</option>
                                                        <option value="Court Order">Court Order</option>
                                                        <option value="Miscellaneous Expenses Bills">Miscellaneous Expenses Bills</option>
                                                        <option value="Other Documents">Other Documents</option>
                                                        <!--                                                                <option value="Copy Of Agreement">Copy Of Agreement(One Time Upload)</option>
                                                                                                                        <option value="Insurance Copy">Insurance Copy(One Time Upload)</option>
                                                                                                                        <option value="Milestone Chart">Milestone Chart(One Time Upload)</option>
                                                                                                                        <option value="Bank Guarantee">Performance Bank Guarantee(One Time Upload)</option>
                                                        
                                                                                                                        <option value="Other Supporting Document">Other Supporting Document</option>-->
                                                    </select></td>
                                                <td><b> <fmt:message key='Upload File'/> </b></td>
                                                <td><input type="file" name="inpFile" id="inpFile" accept="image/jpeg,application/pdf" class="btn btn-success" onchange="validFinalFile();" /></td>
                                            </tr>
                                            <tr>
                                                <th><center><fmt:message key='Remark'/></center></th>
                                                <td colspan="3"><input class="form-control" type="text" maxlength="50" id="txtRemark" name="txtRemark"/></td></tr><tr>
                                                <td colspan="3" align="center"><input type="submit" name="btnFile" id="btnFile" value=<fmt:message key='Upload'/> disabled="true" class="btn btn-success"> </td>
                                            </tr>
                                        </table>
                                    </div> </div>
                                    <%  }%>
                            </center>
                        </form>   
                        <br>
                        <div class="table-responsive">
                            <table class="table"  align="center">


                                <%  
                                    Iterator itr = FileList.iterator();
                                    System.out.println("FileList::"+FileList);
                                    int j = 0;
                                    while (itr.hasNext()) {%>
                                <tr class="success">
                                    <%  if (((flag == 2||((Status.equals("Submitted")||Status.equals("Forwarded")) && UserType.equals("Vendor"))) && j == 0) || (UserType.equals("Emp") && j == 0)) {%>
                                    <th>#</th>                                                
                                    <th><fmt:message key='File Name'/></th>
                                    <th><fmt:message key='File Type'/></th>
                                    <th><fmt:message key='Remark'/></th>
                                     <th><fmt:message key='Remove'/></th> 
                                        <% }%>
                                </tr>
                                <%   String remark = "";
                                    String type = "";
                                    VendorApplFileBean flb = new VendorApplFileBean();
                                    flb = (VendorApplFileBean) itr.next();
                                    j++; 
                                    if (!ApplicationUtils.isBlank(flb.getRemark())) {
                                        remark = flb.getRemark();
                                    }
                                     if (!ApplicationUtils.isBlank(flb.getOption())) {
                                        type = flb.getOption();
                                    }
                                %>

                                <!--<tr class="info">-->
                                <td><%=j%></td>
                                <td class="blackfont" ><a class="blackfont" href="#nogo" onclick="viewFile('<%=flb.getId()%>', '<%=flb.getOption()%>')"> <%=(flb.getFileName() + "." + flb.getFileType())%></a></td>
                                <td><%=type%></td>
                                <td><%=remark%></td>

                                <%  if (((flag == 2) && (flb.getDELETION_FLAG().equals("Y")))||(Status.equals("Saved") && UserType.equals("Emp"))) {%>

                                <td><a href="#nogo" onclick="removeLegalFile('<%=flb.getId()%>', '<%=flb.getOption()%>', '<%=(flb.getFileName() + "." + flb.getFileType())%>')"><img src="images/icon_delete.gif" alt="Remove" width="16" height="16" border="0" /></a></td>
                                        <%  }%>
                                </tr>   



                                <%  }%> </table>
                            <td class="col-md-2">


                                <% if (((Status != null) && (Status.equals("Saved")))
                                                    && ((FileList.size() > 0 && (!(ApplicationUtils.isBlank(((VendorApplFileBean) FileList.get(0)).getId())))))) {%>


                            <tr>      <div class="form-control-err-msg"><fmt:message key='Please check all details including File attachment before submit.Once you submit invoice you can not make any changes'/>.</div>
                            <%  }%>

                            </tr>
                            <% if (((Status != null) && (Status.equals("Saved")))
                                                && ((FileList.size() > 0 && (!(ApplicationUtils.isBlank(((VendorApplFileBean) FileList.get(0)).getId())))))) {%>
                            <br><br><center> 
                                <input type="button" value=<fmt:message key='Submit'/> name="ButtonSubmit" id="ButtonSubmit" onclick="submitLegalInvoiceButton()" class="btn btn-primary" /></center> <br>
                                <% }%>
                            </td>
                            <td class="col-md-1">
                            </td>
                            <% }%>
                            <form>
                               
                                        <!--emp and status is submitted-->
                                        <%  if (flag == 3) {%> 

                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>      <td class="col-md-1">
                                            </td>
                                            <td class="col-md-4">        

                                                <%  if ((LocationId.equals(Office_Code)) )
                                                        {%>
                                                <br<br> <input type="button" value="Accept" name="ButtonApprove" id="ButtonApprove"  onclick="legalInvoiceApproveButton()"
                                                               class="btn  btn-success pull-center"/><br><br><br><br>

                                            </td>
                                            <td class="col-md-1">
                                                <input type="button" value="Return" name="Buttonreject" id="Buttonreject"  onclick="legalInvoiceRejectButton()"
                                                       class="btn  btn-danger"/>

                                                <br><br><br><br>
                                            </td></tr>
                                        <tr>
                                            <td>
                                                <label for="txtReason" id='divheader-div1' data-id='div1' style="display: none;font-family : monospace;font-size : 10pt">Reason For Return</label></td>

                                            <td><select id="txtReason" name="txtReason" class="textfield" style="width: 300px ;height:25px;display:none; font-family : monospace;font-size : 10pt" onchange='checkvalue(this.value)'>
                                                    <option value="">- <fmt:message key='Select'/> -</option>                                              
                                                    <option>Mismatch Portal Invoice Date With Inward Invoice Date</option>
                                                    <option>Mismatch Portal Invoice Amount With Inward Invoice Amount</option>
                                                    <option>Invoice Documents uploaded are not readable</option>
                                                    <option>Wrong Court Case no Mapping</option>
                                                    <option value="other">other</option>
                                                </select></td>
                                        <br><br>
                                        <td></td>
                                        </tr>
                                        <tr height="10"><td></td></tr>
                                        <tr><td></td><td>
                                                <input class="form-control" type="text" text-align="right" name="txtReason"  id="txtReason1" maxlength="100" placeholder=<fmt:message key='"Specify Reason here(Max 100 Chars)...."'/> style='display:none' onkeyup="myFunction()"/>
                                            </td> <td>
                                                <input type="button" value=<fmt:message key='Submit'/> name="rejectSubmitButton" id="rejectSubmitButton" style='display:none' onclick="legalInvoiceRejectSubmitButton()"
                                                       class="btn  btn-primary" />
                                            </td>
                                        </tr>
                                    </table>

                                        <% }%>


                                        <% }else if (flag == 5){%>

                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>      <td class="col-md-1">
                                                </td>
                                                <td class="col-md-4">        
                                                    <!-- if status is forwarded then check for forward to offce code--> 
                                                    <!--Forward_to_office_code velue is retrieved from ajax call through emp.js viewEmpApp1 function-->
                                                    <%  if ((LocationId.equals(Forward_to_office_code) )) {%>
                                                    <br<br> <input type="button" value=<fmt:message key='Verify'/> name="ButtonApprove" id="ButtonApprove"  onclick="approveButton()"
                                                                   class="btn  btn-success pull-center"/><br><br><br><br>

                                                </td>

                                                <td class="col-md-1">
                                                    <input type="button" value=<fmt:message key='PutOnHold'/> name="Buttonreject" id="Buttonreject"  onclick="reject()"
                                                           class="btn  btn-danger"/>

                                                    <br><br><br><br>


                                                </td></tr>


                                            <tr>
                                                <td>
                                                    <label for="txtReason" id='divheader-div1' data-id='div1' style="display: none;font-family : monospace;font-size : 10pt"><fmt:message key='Reasons For Rejection'/></label></td>

                                                <td><select id="txtReason" name="txtReason" class="textfield" style="width: 300px ;height:25px;display:none; font-family : monospace;font-size : 10pt" onchange='checkvalue(this.value)'>
                                                        <option value="">- <fmt:message key='Select'/> -</option>                                              
                                                        <option>Mismatch Of Portal Invoice Date With Inward Invoice Date</option>
                                                        <option>Mismatch Of Portal Invoice Amount With Inward Invoice Amount</option>
                                                        <option>Invoice Documents uploaded are not readable</option>
                                                        <option>Wrong Court Case no Mapping</option>
                                                        <option value="other">other</option>

                                                    </select></td>
                                            <br><br>
                                            <td></td>
                                            </tr>
                                            <tr height="10"><td></td></tr>
                                            <tr><td></td><td>
                                                    <input class="form-control" type="text" text-align="right" name="txtReason"  id="txtReason1" maxlength="70" placeholder=<fmt:message key='"Specify Reason here(Max 70 Chars)"'/>.... style='display:none' onkeyup="myFunction()"/>
                                                </td> <td>
                                                    <input type="button" value=<fmt:message key='Submit'/> name="Buttonsubmit1" id="Buttonsubmit1" style='display:none' onclick="rejectButton()"
                                                           class="btn  btn-primary" />
                                                </td>
                                            </tr>


                                            <% }%>









                                            <% }%>
                                        </table> 
                                        
                                        </form>
                                        </div>


                                     
                                            </table> 
                                            </form>
                                            </div>
                                            </div>
                                            </div>  <!-- div file upload ends -->

                                            <!-- /. PAGE inner  -->
                                            </div>
                                            <!-- /. PAGE wrapper  -->
                                            </div>
                                            <!-- /.  wrapper  -->
                                            </div>


                                            <table width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                                                <tr>
                                                    <td class="module_tbl_tl" >&nbsp;</td>
                                                    <td class="tbl_hline_top">&nbsp;</td>
                                                    <td class="module_tbl_tr">&nbsp;</td>
                                                </tr>
                                                <tr> <!-- Start of Network Search Results tr -->

                                                    <td class="bg_white">  <!-- Start of Network Search Results td -->
                                                        <!--<div class="form">-->  <!-- Start of  div  form -->
                                                        <div class="table-responsive" align="center" > 
                                                            <ul class="nav nav-tabs">
                                                              
                                                                        <input type = "hidden" name="lastValue" id="lastValue" value="" />
                                                                        <input type = "hidden" name="lastValueDataType"   id="lastValueDataType" value="" />
                                                                    </div>
                                                                </div> <!-- Closed list ends-->              
                                                            </div>  <!-- /. tab-content  -->
                                                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>           
                                                            <br><br>
                                                            <%@include file="nav_emp_footer.jsp" %>

                                                            <!-- SCRIPTS -AT THE BOtTOM TO REDUCE THE LOAD TIME-->

                                                            <!-- BOOTSTRAP SCRIPTS -->
                                                            <script src="assets/js/bootstrap.min.js"></script>
                                                            <!-- CUSTOM SCRIPTS -->
                                                            <script src="assets/js/custom.js"></script>

                                                            </body>
                                                            <script>
                                                                                                $(function () {
                                                                                                    var isWithCourtCaseNoFlag = $('#isWithCourtCaseNo').val();
                                                                                                    if (isWithCourtCaseNoFlag == "true") {
                                                                                                        $('#withCourtCaseNoBody').show();
                                                                                                        $('#withoutCourtCaseNoBody').hide();
                                                                                                    } else {
                                                                                                        $('#withCourtCaseNoBody').hide();
                                                                                                        $('#withoutCourtCaseNoBody').show();
                                                                                                    }
                                //      $('#withoutCourtCaseNoBody').hide();
                                                                                                    $("#txtVendorInvoiceDate").datepicker({
                                                                                                        changeMonth: true,
                                                                                                        changeYear: true,
                                                                                                        //showOn: 'button',
                                                                                                        //buttonImage: 'images/calendar.png',
                                                                                                        //buttonImageOnly: true,
                                                                                                        //yearRange: '2016:2020',
                                                                                                        dateFormat: 'dd-M-yy',
                                                                                                        minDate: '-8Y',
                                                                                                        maxDate: '+1Y'
                                                                                                    });
                                                                                                    $("#txtInvSubmitDt").datepicker({
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
                                                                                                    $("#txtInwardDt").datepicker({
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

                                                                                                    var value = "getCourtCaseDetailsForVendor";
                                                                                                    var ctx = "${pageContext.request.contextPath}" + "/erp?";
                                                                                                    $("#txtInvoiceNum").attr('title', 'Please Enter Full Invoice Number');
                                                                                                    $("#txtCourtCaseNo").autocomplete({
                                //      source: availableTags
                                                                                                        source: function (request, response) {
                                                                                                            $.ajax({
                                                                                                                url: "${pageContext.request.contextPath}" + "/LegalServlet?txtVendorCode=" + document.getElementById("txtVendorCode").value + "&actionName=autocomplete",
                                                                                                                dataType: "json",
                                                                                                                data: request,
                                                                                                                success: function (data, textStatus, jqXHR) {
                                //                    console.log( data);
                                                                                                                    var items = data;
                                                                                                                    response(items);
                                                                                                                },
                                                                                                                error: function (jqXHR, textStatus, errorThrown) {
                                                                                                                    console.log(textStatus);
                                                                                                                }
                                                                                                            });
                                                                                                        }
                                                                                                    });

                                                                                                    $("#txtCaseRefNoWithout").autocomplete({
                                //      source: availableTags
                                                                                                        source: function (request, response) {
                                                                                                            $.ajax({
                                                                                                                url: "${pageContext.request.contextPath}" + "/LegalServlet?actionName=autocomplete&autoCompleteParam=caseRefNo&txtVendorCode=" + document.getElementById("txtVendorCode").value,
                                                                                                                dataType: "json",
                                                                                                                data: request,
                                                                                                                success: function (data, textStatus, jqXHR) {
                                                                                                                    var items = data;
                                                                                                                    response(items);
                                                                                                                },
                                                                                                                error: function (jqXHR, textStatus, errorThrown) {
                                                                                                                    console.log(textStatus);
                                                                                                                }
                                                                                                            });
                                                                                                        }
                                                                                                    });
                                                                                                    $("#txtVendorCode").autocomplete({
                                //      source: availableTags
                                                                                                        source: function (request, response) {
                                                                                                            $.ajax({
                                                                                                                url: "${pageContext.request.contextPath}" + "/LegalServlet?actionName=autocomplete&autoCompleteParam=vendor",
                                                                                                                dataType: "json",
                                                                                                                data: request,
                                                                                                                success: function (data, textStatus, jqXHR) {
                                                                                                                    var items = data;
                                                                                                                    response(items);
                                                                                                                },
                                                                                                                error: function (jqXHR, textStatus, errorThrown) {
                                                                                                                    console.log(textStatus);
                                                                                                                }
                                                                                                            });
                                                                                                        }
                                                                                                    });
                                                                                                    var availableDealingOffice = [
                                                                                                        "Khed Division",
                                                                                                        "Mauja Division",
                                                                                                        "Ganeshkhind Division ",
                                                                                                        "Congress Division",
                                                                                                        "Pune Zone",
                                                                                                        "Nagpur Zone",
                                                                                                        "Gondia Zone"
                                                                                                    ];
                                                                                                    $("#txtDealingOffice").autocomplete({
                                                                                                        source: availableDealingOffice
                                                                                                    });

                                                                                                });
                                                                                                function addRow(tableID) {

                                                                                                    //  var table = document.getElementById(tableID);
                                                                                                    var table = document.getElementById(tableID).getElementsByTagName('tbody')[0];

                                                                                                    var rowCount = table.rows.length;
                                                                                                    var row = table.insertRow(rowCount);

                                                                                                    // table[rowCount].offsetHeight= "20px";
                                                                                                    var cell1 = row.insertCell(0);
                                                                                                    var element1 = document.createElement("label");
                                                                                                    element1.innerHTML = rowCount + 1;
                                                                                                    cell1.appendChild(element1);
                                                                                                    var elName = "feeTypeDtlId";
                                                                                                    var elementc = document.createElement("input");
                                                                                                    elementc.type = "hidden";
                                                                                                    elementc.setAttribute("name", elName);
                                                                                                    elementc.setAttribute("id", elName);
                                                                                                    elementc.setAttribute("value", "0");
                                                                                                    cell1.appendChild(elementc);



                                                                                                    var cell2 = row.insertCell(1);
                                                                                                    var element2 = document.createElement("select");
                                                                                                    var element2Id = "sel" + rowCount;
                                                                                                    element2.setAttribute("id", element2Id);
                                                                                                    var opt = document.createElement('option');
                                                                                                    opt.innerHTML = "Select";
                                                                                                    element2.appendChild(opt);
                                                                                                    element2.setAttribute("class", "form-control text-left");
                                                                                                    element2.setAttribute("onclick", "dynamicfeeType(this)");

                                                                                                    cell2.appendChild(element2);

                                                                                                    var cell3 = row.insertCell(2);
                                                                                                    var element3 = document.createElement("input");
                                                                                                    element3.type = "text";
                                                                                                    element3.setAttribute("class", "form-control text-right");
                                                                                                    element3.setAttribute("onfocusout", "getTotal()");
                                                                                                    element3.name = "txtAmt" + rowCount;
                                                                                                    element3.height = 20;
                                                                                                    cell3.appendChild(element3);

                                                                                                    var cell4 = row.insertCell(3);
                                                                                                    var element4 = document.createElement("input");
                                                                                                    element4.type = "text";
                                                                                                    element4.setAttribute("class", "form-control text-right");
                                                                                                    element4.name = "txtRemark" + rowCount;
                                                                                                    element4.height = 20;

                                                                                                    cell4.appendChild(element4);


                                                                                                    /*  var cell5 = row.insertCell(4);
                                                                                                     var element5 = document.createElement("a");
                                                                                                     var img1 = document.createElement("img");
                                                                                                     img1.setAttribute("src","images/icon_add.gif");
                                                                                                     element5.appendChild(img1);
                                                                                                     element5.setAttribute("onClick", "addRow('feeTypeDtlTbl')");
                                                                                                     element5.setAttribute("href", "#nogo");
                                                                                                     cell5.appendChild(element5);*/


                                                                                                    var cell5 = row.insertCell(4);
                                                                                                    //    cell5.defineProperty("align","center");
                                                                                                    var element5 = document.createElement("a");
                                                                                                    var img2 = document.createElement("img");
                                                                                                    img2.setAttribute("src", "images/icon_delete.gif");
                                                                                                    element5.appendChild(img2);
                                                                                                    element5.setAttribute("onClick", "");
                                                                                                    element5.setAttribute("href", "#nogo");
                                                                                                    cell5.appendChild(element5);


                                                                                                }
                                                                                                function openSearcher(url) {
                                                                                                    var url = "${pageContext.request.contextPath}" + "/LegalServlet?txtVendorCode=" + document.getElementById("txtVendorCode").value + "&actionName=populateCaseDetailsNew" + "&caseNo=" + document.getElementById("txtCourtCaseNo").value;
                                                                                                    //alert(url);  
                                                                                                    //window.open('erp?uiActionName=getSearchCourtCase',null,'height=500,width=400');
                                                                                                    window.open(url, null, 'height=400,width=1060');
                                                                                                }

                                                                                                function populateCaeseDetails() {//alert(document.getElementById("txtCaseRefNo").value);
                                                                                                    $.ajax({
                                                                                                        url: "${pageContext.request.contextPath}" + "/LegalServlet?txtVendorCode=" + document.getElementById("txtVendorCode").value + "&actionName=populateCaseDetails"
                                                                                                                + "&caseRefNo=" + document.getElementById("txtCaseRefNo").value,
                                                                                                        dataType: "json",
                                                                                                        //data: request,
                                                                                                        success: function (data, textStatus, jqXHR) {
                                                                                                            $('#txtCaseRefNo').val(data.CaseRefNo);
                                                                                                            $('#txtCourtName').val(data.CourtName);
                                                                                                            $('#txtCaseDescription').val(data.CaseDescription);
                                                                                                            $('#txtCaseType').val(data.CaseTypeDesc);
                                                                                                            $('#txtPartyNames').val(data.PartyNames);
                                                                                                            $('#txtVsPartyNames').val(data.VsPartyNames);
                                                                                                            $('#txtDealingOffice').val(data.DealingOffice);
                                                                                                            $("#selectedOffieCode").val(data.selectedOffieCode);
                                                                                                            $("#feeTypeSelect").html(data.feeTypeList);
                                                                                                            $("#office_type").val(data.officeType);
                                                                                                            $("#region_id").val(data.regionId);
                                                                                                            },
                                                                                                        error: function (jqXHR, textStatus, errorThrown) {
                                                                                                            console.log(textStatus);
                                                                                                        }
                                                                                                    });
                                                                                                }
                                                                                                function populateAllVendors() {
                                                                                                    $.ajax({
                                                                                                        url: "${pageContext.request.contextPath}" + "/LegalServlet?txtVendorCode=" + document.getElementById("txtVendorCode").value + "&actionName=populateVendorDetails",
                                                                                                        dataType: "json",
                                                                                                        //data: request,
                                                                                                        success: function (data, textStatus, jqXHR) {
                                                                                                            alert(data.vendorName);
                                                                                                            $('#txtVendorName').val(data.vendorName);
                                                                                                        },
                                                                                                        error: function (jqXHR, textStatus, errorThrown) {
                                                                                                            console.log(textStatus);
                                                                                                        }
                                                                                                    });
                                                                                                }
                                                                                                function populateCaseRefDetails() {//alert(document.getElementById("txtCaseRefNoWithout").value);
                                                                                                    $.ajax({
                                                                                                        url: "${pageContext.request.contextPath}" + "/LegalServlet?txtVendorCode=" + document.getElementById("txtVendorCode").value
                                                                                                                + "&actionName=populateCaseRefDetails&txtCaseRefNoWithout=" + document.getElementById("txtCaseRefNoWithout").value,
                                                                                                        dataType: "json",
                                                                                                        //data: request,
                                                                                                        success: function (data, textStatus, jqXHR) {
                                                                                                            //alert(data.vendorName);
                                                                                                            $('#txtCourtCaseNoWithout').val(data.CourtCaseNo);
                                                                                                            $('#txtCourtNameWithout').val(data.CourtName);
                                                                                                            $('#txtCaseDescriptionWithout').val(data.CaseDescription);
                                                                                                            $('#txtPartyNamesWithout').val(data.PartyNames);
                                                                                                            $('#txtDealingOfficeWithout').val(data.DealingOffice);
                                                                                                            $("#selectedOffieCodeWithout").val(data.selectedOffieCode);
                                                                                                            $("#txtFeeTypeWithout").val(data.feeTypeList);
                                                                                                        },
                                                                                                        error: function (jqXHR, textStatus, errorThrown) {
                                                                                                            console.log(textStatus);
                                                                                                        }
                                                                                                    });
                                                                                                }
                                                                                                var isCalled = false;
                                                                                                function feeType(vid) {//alert("in feeType() fn");

                                                                                                    if (isCalled === false) {//alert("in feeType() fn");
                                                                                                        $.ajax({url: '${pageContext.request.contextPath}' + '/LegalServlet?txtVendorCode=' + document.getElementById('txtVendorCode').value + '&actionName=populateCaseDetails'
                                                                                                                    + '&caseRefNo=' + document.getElementById('txtCaseRefNo').value,
                                                                                                            dataType: 'json',

                                                                                                            success: function (data, textStatus, jqXHR) {//alert('#'+vid.id);
                                                                                                                $('#' + vid.id).html(data.feeTypeList);
                                                                                                                //$("#feeTypeSelect").html($.unique(data.feeTypeListArray.map(function (d) {return d.feetype;})));
                                                                                                                isCalled = true;
                                                                                                            },
                                                                                                            error: function (jqXHR, textStatus, errorThrown) {
                                                                                                                console.log(textStatus);
                                                                                                            }
                                                                                                        });

                                                                                                    }
                                                                                                }

                                                                                                function dynamicfeeType(vid) {//alert("in feeType() fn");

                                                                                                    if (vid.length == 1) {


                                                                                                        $.ajax({url: '${pageContext.request.contextPath}' + '/LegalServlet?txtVendorCode=' + document.getElementById('txtVendorCode').value + '&actionName=populateCaseDetails'
                                                                                                                    + '&caseRefNo=' + document.getElementById('txtCaseRefNo').value,
                                                                                                            dataType: 'json',

                                                                                                            success: function (data, textStatus, jqXHR) {//alert('#'+vid.id);
                                                                                                                $('#' + vid.id).html(data.feeTypeList);
                                                                                                                //$("#feeTypeSelect").html($.unique(data.feeTypeListArray.map(function (d) {return d.feetype;})));
                                                                                                                // isCalled = true;
                                                                                                            },
                                                                                                            error: function (jqXHR, textStatus, errorThrown) {
                                                                                                                console.log(textStatus);
                                                                                                            }
                                                                                                        });

                                                                                                    }
                                                                                                }
                                                                                                $('#txtInvoiceAmt').on('input', function () {
                                                                                                    this.value = this.value.match(/^\d+\.?\d{0,2}/);
                                                                                                });
                                                            </script>
                                                            </html>
