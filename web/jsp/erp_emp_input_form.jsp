<%--
    Document   : erp_vendor_appl_form
    Created on : Dec 10, 2017, 4:06:44 PM
    Author     : pooja
--%>


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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%

    String recordsVar = "No Records To Display !!!";
    String uiAction = "";
    LinkedList FileList = new LinkedList();
    LinkedList vendorList = new LinkedList();

    LinkedList applListNew = new LinkedList();
    LinkedList applListOld = new LinkedList();

    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }

    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    POBean POBeanObj = new POBean();
    VendorInputBean vendorInputBean = new VendorInputBean();
    LinkedList POList = new LinkedList();
    LinkedList PROJList = new LinkedList();
 LinkedList VendorDtlList = new LinkedList();
    //String Status="";
    int flag = 0;

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_INPUT_FORM_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_INPUT_FORM_SESSION_DATA);
        POList = (LinkedList) vendorPrezDataObj.getPOList();
        PROJList = (LinkedList) vendorPrezDataObj.getProjList();
        vendorInputBean = (VendorInputBean) vendorPrezDataObj.getVendorInputBean();
        VendorDtlList=(LinkedList) vendorPrezDataObj.getVendorDtlList();
        // POBeanObj = (POBean) vendorPrezDataObj.getPoBean();
    }

    //if (request.getSession().getAttribute(ApplicationConstants.VENDOR_LOCATION_SESSION_DATA) != null) {
    //  POBeanObj=(POBean)request.getSession().getAttribute(ApplicationConstants.VENDOR_LOCATION_SESSION_DATA);
    //}
    // if (request.getSession().getAttribute(ApplicationConstants.VENDOR_INPLIST_SESSION_DATA) != null) {
    // vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_INPLIST_SESSION_DATA);
    // vendorList  =   vendorPrezDataObj.getVendorList();
    // }
    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_INPLIST_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_INPLIST_SESSION_DATA);

        vendorList = (LinkedList) vendorPrezDataObj.getVendorList();

    }
    String UserNumber = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
    String viewAction = "";
    String viewAction1 = "";
    String viewAction2 = "";

    if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM)) {
            //actionStatus = "Saved";
            viewAction = "redir";
            viewAction1 = "redi";
            viewAction2 = "redi_ps";
        }
    }

    String ApplId = "";
    String PONumber = "";
    String PODesc = "";
    String ProjDesc = "";
    String InvoiceNum = "";
    Date sysdate = new Date();
    String VendorInvoiceDt = "";
    String VendorInwardDate = "";
    String InvoiceResubmissionDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
    String InvoiceAmt = "";
    String InwardNum = "";
    String InwardDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
    String InvoiceSubmitDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
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
    String TaxCode = "";
    String TaxAmount = "";
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
    String ModuleFlag = "";//defines from which table input invoice is from
    String Total_PO_Amt = "";
    String Total_Bal_amt = "";
    String SUBMIT_AT_LOCATION = "";
    String zone = "";

    String circle = "";
    String division = "";
     String Purchasing_group="";
      String MATERIAL_PO = "";
          String CENTAGES_PO = "";
          String SERVICE_PO = "";


    if (vendorInputBean != null) {

        if (!ApplicationUtils.isBlank(vendorInputBean.getApplId())) {
            ApplId = vendorInputBean.getApplId();
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getPONumber())) {
            PONumber = vendorInputBean.getPONumber();
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getProjectId())) {
            projectid = vendorInputBean.getProjectId();
        }
         if (!ApplicationUtils.isBlank(vendorInputBean.getMATERIAL_PO())) {
            MATERIAL_PO = vendorInputBean.getMATERIAL_PO();
        }
          if (!ApplicationUtils.isBlank(vendorInputBean.getCENTAGES_PO())) {
            CENTAGES_PO = vendorInputBean.getCENTAGES_PO();
        }
           if (!ApplicationUtils.isBlank(vendorInputBean.getSERVICE_PO())) {
            SERVICE_PO = vendorInputBean.getSERVICE_PO();
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getINVOICE_TYPE())) {
            Invoice_type = vendorInputBean.getINVOICE_TYPE();
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getTotalPoAmt())) {
            Total_PO_Amt = vendorInputBean.getTotalPoAmt();
            
            Total_PO_Amt = ApplicationUtils.formatAmount(Double.valueOf(Total_PO_Amt));
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getBalPoAmt())) {
            Total_Bal_amt = vendorInputBean.getBalPoAmt();
            Total_Bal_amt = ApplicationUtils.formatAmount(Double.valueOf(Total_Bal_amt));
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getPODesc())) {
            PODesc = PONumber + "-" + vendorInputBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-', ' ');
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getProjectDesc())) {
            ProjDesc = projectid + "-" + vendorInputBean.getProjectDesc().replace('"', ' ').replace('\'', ' ').replace('-', ' ');
        }
        if ((vendorInputBean.getVendorInvoiceDate() != null) && !(vendorInputBean.getVendorInvoiceDate().equals("null")) && !(vendorInputBean.getVendorInvoiceDate().equals(""))) {

            VendorInvoiceDt = ApplicationUtils.dateToString(vendorInputBean.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())) {
            InvoiceNum = vendorInputBean.getVendorInvoiceNumber();
        }

        if ((vendorInputBean.getVendorInwardDate() != null) && !(vendorInputBean.getVendorInwardDate().equals("null")) && !(vendorInputBean.getVendorInwardDate().equals(""))) {
            VendorInwardDate = ApplicationUtils.dateToString(vendorInputBean.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
       
        }
        
        if ((vendorInputBean.getVendorInvoiceResubmit() != null) && !(vendorInputBean.getVendorInvoiceResubmit().equals("null")) && !(vendorInputBean.getVendorInvoiceResubmit().equals(""))) {
            InvoiceResubmissionDate = ApplicationUtils.dateToString(vendorInputBean.getVendorInvoiceResubmit(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getIsRejectedFlag())) {
            IsRejectFlag = vendorInputBean.getIsRejectedFlag();
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getIsApprovedFlag())) {
            IsApproveFlag = vendorInputBean.getIsApprovedFlag();
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

        if ((vendorInputBean.getInvoiceSubmitDate() != null) && !(vendorInputBean.getInvoiceSubmitDate().equals("null")) && !(vendorInputBean.getInvoiceSubmitDate().equals(""))) {
            InvoiceSubmitDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceSubmitDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if ((vendorInputBean.getInvoiceFromDate() != null) && !(vendorInputBean.getInvoiceFromDate().equals("null")) && !(vendorInputBean.getInvoiceFromDate().equals(""))) {
            InvoiceFrmDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if ((vendorInputBean.getInvoiceToDate() != null) && !(vendorInputBean.getInvoiceToDate().equals("null")) && !(vendorInputBean.getInvoiceToDate().equals(""))) {
            InvoiceToDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getSaveFlag())) {
            Status = vendorInputBean.getSaveFlag();
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getReason())) {
            Reason = vendorInputBean.getReason();
        }

        if (request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA) != null) {
            FileList = (LinkedList) request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA);
        }
        UserType = "Vendor";
        if (!ApplicationUtils.isBlank(vendorInputBean.getLocationId())) {
            LocationId = vendorInputBean.getLocationId();
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getOffice_Code())) {
            Office_Code = vendorInputBean.getOffice_Code();
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getDispVendorNumber())) {
            VendorNum = vendorInputBean.getDispVendorNumber()+"-"+vendorInputBean.getDispVendorName();
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getDispVendorName())) {
            VendorName = vendorInputBean.getDispVendorName();
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getTaxCode())) {
            TaxCode = vendorInputBean.getTaxCode();
        }
        /* if (!ApplicationUtils.isBlank(vendorInputBean.getTaxAmount())) {
         TaxAmount = vendorInputBean.getTaxAmount();
         }*/
        if (!ApplicationUtils.isBlank(vendorInputBean.getDesignation())) {
            Designation = vendorInputBean.getDesignation();
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getLocation())) {
            Plant = vendorInputBean.getLocation();
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getInvoiceApprDAte())) {
            InvoiceApprDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceApprDAte(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        if (!ApplicationUtils.isBlank(vendorInputBean.getInvoiceREjDAte())) {
            InvoiceRejDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceREjDAte(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
     
             if (!ApplicationUtils.isBlank(vendorInputBean.getPurchasingDesc())) {
            Plant_Desc = Plant + "-" + vendorInputBean.getPurchasingDesc();
        } 
         
         if (!ApplicationUtils.isBlank(vendorInputBean.getPurchasing_group())) {
            Purchasing_group =  vendorInputBean.getPurchasing_group();
        }

        if ((vendorInputBean.getSelectedModuleType() != null)) {//for selecting module to display po number or project id
            selectedmodule = vendorInputBean.getSelectedModuleType();
            if (selectedmodule.equals("PM")) {
                Module_dtl = "Procurement/Works";
            } else if (selectedmodule.equals("PS")) {
                Module_dtl = "Project System";
            }
        }

        if ((vendorInputBean.getModuleSaveFlag() != null)) {
            ModuleFlag = vendorInputBean.getModuleSaveFlag();
        }

        if (((Status == "") || (Status.equals("Saved")) || Status.equals("Rejected")) && UserType.equals("Vendor")) {
            flag = 2;//can edit 
        }
        if ((Status.equals("Verified") || Status.equals("Completed")) && UserType.equals("Vendor")) {
            flag = 4;
        }

        if (((Status.equals("Submitted"))) && UserType.equals("Emp")) {
            flag = 3;
        }
        if (((IsRejectFlag.equals("Y") || IsApproveFlag.equals("Y")) && UserType.equals("Vendor")) || (UserType.equals("Emp"))) {
            showinwflag = "Y";
        }

        if (InvoiceApprDate != null && (!InvoiceApprDate.equals("null")) && (!InvoiceApprDate.equals("")) && UserType.equals("Vendor")) {
            showapprdateflag = "Y";
        }
        if (InvoiceRejDate != null && (!InvoiceRejDate.equals("null")) && (!InvoiceRejDate.equals("")) && UserType.equals("Vendor")) {
            showrejdateflag = "Y";
        }
        if ((vendorInputBean.getSubmitAtPlant() != null)) {
            SUBMIT_AT_LOCATION = vendorInputBean.getSubmitAtPlant();
        }
        if ((vendorInputBean.getSubmitAtDesc() != null)) {
            SUBMIT_AT_LOCATION = SUBMIT_AT_LOCATION + "-" + vendorInputBean.getSubmitAtDesc();
        }
        if ((vendorInputBean.getZone() != null)) {
            zone = vendorInputBean.getZone();
        }
    }
   /* if ((vendorInputBean.getCircle() != null)) {
        circle = vendorInputBean.getCircle();
    }
    if ((vendorInputBean.getDivision() != null)) {
        division = vendorInputBean.getDivision();
    }*/
String INVOICE_FILESIZE_LIMIT  ="0";
            if(System.getProperty("INVOICE_FILESIZE_LIMIT") != null)
            {
               INVOICE_FILESIZE_LIMIT    = System.getProperty("INVOICE_FILESIZE_LIMIT");
            }

%>
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
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_vendor_input_form.js"></script>


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
                  <!-- <input type="hidden" name ="poNumber" id="poNumber" value="<%=PONumber%>">-->
                    <input type="hidden" name="view" id="view" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS)%>"/>
                    <input type="hidden" name="redi" id="redi" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_APPL_FORM%>"/>
                    <input type="hidden" name="redi_ps" id="redi_ps" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_PS_APPL_FORM%>"/>
                    <input type="hidden" name="mod" id="mod" value="<%=Module%>"/>
                    <input type="hidden" name="inv_type" id="inv_type" value="<%=Invoice_type%>" />
                    <input type="hidden" name="ps_pm_mod" id="ps_pm_mod" value="<%=selectedmodule%>" />
                    <input type="hidden" name="redir" id="redir" value="<%=ApplicationConstants.UIACTION_GET_AUTH_PO_LIST%>"/>
                    <input type="hidden" name="rem" id="rem" value="<%=ApplicationConstants.UIACTION_GET_EMP_INPUT_FORM%>"/>
                    <input type="hidden" name="rejflag" id="rejflag"  value="<%=IsRejectFlag%>"/>
                    <input type="hidden" name="apprflag" id="apprflag"  value="<%=IsApproveFlag%>"/>
                    <input type="hidden" name="showinwflag" id="showinwflag"  value="<%=showinwflag%>"/>
                    <input type="hidden" name="status" id="status" value ="<%=Status%>"/>
                    <input type="hidden" name="submit_at_location" id="submit_at_location" value ="<%=SUBMIT_AT_LOCATION%>"/>
                    <input type="hidden" name="file" id="file" value ="<%=file%>"/>
                    <input type="hidden" name="ModuleFlag" id="ModuleFlag" value="<%=ModuleFlag%>" />
                    <input type="hidden" name="zone" id="zone" value="<%=zone%>" />
                    <input type="hidden" name="circle" id="circle" value="<%=circle%>" />
                    <input type="hidden" name="division" id="division" value="<%=division%>" />
                    <input type="hidden" name="txtPurchasing_group" id="txtPurchasing_group" value="<%=Purchasing_group%>" />
                    <input type="hidden" name="txtOnloadPurchasing_group" id="txtOnloadPurchasing_group"  />
                    <input type="hidden" name="txtOnloadPurchasing_group" id="txtOnloadPurchasing_group"  />
                    <input type="hidden"  id="Module" name="txtModule" value = "<%=Module_dtl%>"  /> 
                    <input type="hidden"  id="userType" name="userType" value = "<%=UserType%>"  /> 
                    <input type="hidden"  id="txtpobal" name="txtpobal"   value="<%=Total_Bal_amt%>"  /> 
                     <input type="hidden"  id="selectedPlant" name="selectedPlant"   /> 
                     <input type="hidden"  id="INVOICE_FILESIZE_LIMIT" name="INVOICE_FILESIZE_LIMIT" value="<%=INVOICE_FILESIZE_LIMIT%>"  />

                     <input type="hidden" name="poOptions" id="poOptions" value='[<% if (!ApplicationUtils.isBlank(POList)) {
                            int i = 0;
                            for (POBean pBean : (LinkedList<POBean>) POList) {
                                if (i != 0) {
                                    out.print(" , ");
                                }
                                out.print("\" " + pBean.getPONumber() + "-" + pBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-',' ') + "\"");
                               i++;
                            }
                        }
                       
                           %>]'
                           
                           />
                          <input type="hidden" name="poOptionsWithType" id="poOptionsWithType" value='[<% if (!ApplicationUtils.isBlank(POList)) {
                           
                            int i = 0;
                            for (POBean pBean : (LinkedList<POBean>) POList) {
                                if (i != 0) {
                                    out.print(" , ");
                                }
                                out.print("\"" + pBean.getPONumber() + "-" + pBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-',' ')+"$$$$"+ pBean.getSelectedModuleType() + "\"");
                             
                                i++;
                            }
                        }
                       
                           %>]'
                           
                           />
                             <input type="hidden" name="VNumOptions" id="VNumOptions" value='[<% if (!ApplicationUtils.isBlank(VendorDtlList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                     Set <String>venList = new HashSet<String>(); 
                                                 for (POBean pBean : (LinkedList<POBean>) VendorDtlList) {
                                                     venList.add(pBean.getVendorNumber() + "-" + pBean.getVendorName());
                                                     i++;
                                                 }
                                                 int j=0;
                                                 for(String d:venList)
                                                 {
                                                 
                                                      if(j!=0){ out.print(" , ");}
                                                  out.print("\"" + d.replace('"', ' ').replace('\'', ' ') + "\""  );
                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     j++;
                                                      }
                                                    }
                                               // out.print( ']' );
                                                %>]'/>
                 
                    <div class="content_container">
                        <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr> <!-- Start of Network Search Results tr -->
                                <td class="bg_white">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <br><br><div class="col-md-12"><h3><fmt:message key='Vendor Invoice Form'/></h3></div>
                                        <div >&nbsp;</div>
                                    </div>
                                </td>
                            </tr>

                        </table>  <!-- End of Network Search results table -->

                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->

                            <table class="table" style="width:100%" id="tblone" border="0" cellspacing="0" cellpadding="1"   >
                               <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">
                                        <div>

                                        </div>
                                    </td>
                                    <td class="text-right h5" ><fmt:message key='Vendor Number'/>.</td>
                                    <td id="myDropdown">
                                        <div class="autocomplete" style="width:500px;">
                                           
                                               <% if (UserType.equals("Vendor")) {%>
                                               <input autocomplete="off" type="text" name="txtVNum" id="txtVNum" style="width: 100%;"  value ="<%=VendorNum%>" class="form-control" title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> onkeypress="getVendorSearchList();" <% if (flag == 2) {
                                                      } else {
                                                          out.print("disabled='true'");
                                                      }%> /> 
                                                    <% }%>
                                           

                                        </div>        
                                    </td>
                                
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">
                                       
                                    </td>
                                </tr>
                                <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">
                                        <div>

                                        </div>
                                    </td>
                                    <td class="text-right h5" ><fmt:message key='PO Number'/>.</td>
                                    <td id="myDropdown">
                                        <div class="autocomplete" style="width:500px;">
                                              <input autocomplete="off" type="hidden" id="selectedTxtPONumber"/>
                                               <% if (UserType.equals("Vendor")) {%>
                                               <input autocomplete="off" type="text" name="txtPONumber" id="txtPONumber" style="width: 72%;float:left"  value ="<%=PODesc%>" class="form-control" title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> onkeypress="getPOSearchList();" <% if (flag == 2) {
                                                      } else {
                                                          out.print("disabled='true'");
                                                      }%> /> <button type="button" class="btn  btn-success" style="height:34px;width:28%" id="myBtn"  disabled onClick="getPOLineDetails()">Show PO Details</button> 
                                                    <% }%>
                                            <% if (UserType.equals("Emp")) {
                                             	if (selectedmodule.equals("PS")) {%> <input type="text" name="txtPONumber" id="txtPONumber" style="width: 100%" value ="<%=PODesc%>" class="form-control" readonly="true" >
                                            <%} else {%>
                                            <input type="text" name="txtPONumber" id="txtPONumber" style="width: 72%;float:left" value ="<%=PODesc%>" class="form-control" readonly="true" >
                                            <button type="button" class="button_allign" style="height:34px;width:28%" id="myBtn"   onClick="getPOLineDetails()">Show PO Details</button> 
                                            <% }
                                             }%>

                                        </div>        
                                    </td>
                                
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">
                                       
                                    </td>
                                </tr>
                                <% if (Status == "" || ModuleFlag.equals("NON_PO")){%>
                                <tr>
                                    <td class="col-md-1" style="border:none">
                                    </td>
                                    <td class="col-md-2" style="border:none">

                                    </td>
                                    <td class="text-right h5" style="border:none"> <label for="ProjectId" id="ProjectId" style="display: none;"><fmt:message key='Project ID'/>.</label></td> 
                                     <td style="border:none"> <input type="text" name="txtPROJNumber" id="txtPROJNumber"  value ="<%=projectid%>" class="form-control" style="width: 500px ;display: none" readonly="true" >

                                </tr>
                                <% }else if (selectedmodule.equals("PS") && (Status != "")){%>
                                
                                        <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">

                                    </td>
                                    <td class="text-right h5"> <label for="ProjectId" id="ProjectId"><fmt:message key='Project ID'/>.</label></td> 
                                     <td> <input type="text" name="txtPROJNumber" id="txtPROJNumber"  value ="<%=projectid%>" class="form-control" style="width: 500px ;" readonly="true" >

                                </tr>
                                
                                <% }%>
                                
                                <% if (Status == "" || ModuleFlag.equals("NON_PO")){%> 
                               <tr>
                                    <td class="col-md-1" style="border:none">
                                    </td>
                                    <td class="col-md-2" style="border:none">

                                    </td>
                                    <td class="text-right h5" style="border:none"> <label for="MaterialPo" id="MaterialPo" style="display: none;"><fmt:message key='Material PO'/>.</label></td> 
                                     <td style="border:none"> <input type="text" name="txtMaterialPo" id="txtMaterialPo"  value ="<%=MATERIAL_PO%>" class="form-control" style="width: 500px ;display: none" readonly="true" >
                                      </tr>
                                       <% }else if (selectedmodule.equals("PS") && (Status != "")){%>
                                        <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">

                                    </td>
                                    <td class="text-right h5"> <label for="MaterialPo" id="MaterialPo"><fmt:message key='Material PO'/>.</label></td> 
                                     <td> <input type="text" name="txtMaterialPo" id="txtMaterialPo"  value ="<%=MATERIAL_PO%>" class="form-control" style="width: 500px ;" readonly="true" >
                                      </tr>
                                       
                                         <% }%>
                                                   <% if (Status == "" || ModuleFlag.equals("NON_PO")){%> 
                                                   <tr>
                                    <td class="col-md-1" style="border:none">
                                    </td>
                                    <td class="col-md-2" style="border:none">

                                    </td>
                                    <td class="text-right h5" style="border:none"> <label for="CentagesPo" id="CentagesPo" style="display: none;"><fmt:message key='Centages PO'/>.</label></td> 
                                     <td style="border:none"> <input type="text" name="txtCentagesPo" id="txtCentagesPo"  value ="<%=CENTAGES_PO%>" class="form-control" style="width: 500px ;display: none" readonly="true" >
                                    </tr>
                                     <% }else if (selectedmodule.equals("PS") && (Status != "")){%>
                                                             <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">

                                    </td>
                                    <td class="text-right h5"> <label for="CentagesPo" id="CentagesPo"><fmt:message key='Centages PO'/>.</label></td> 
                                     <td> <input type="text" name="txtCentagesPo" id="txtCentagesPo"  value ="<%=CENTAGES_PO%>" class="form-control" style="width: 500px ;" readonly="true" >
                                    </tr>
                                       <% }%>
                                                   <% if (Status == "" || ModuleFlag.equals("NON_PO")){%> 
                                                   <tr>
                                    <td class="col-md-1" style="border:none">
                                    </td>
                                    <td class="col-md-2" style="border:none">

                                    </td>
                                    <td class="text-right h5" style="border:none"> <label for="ServicePo" id="ServicePo" style="display: none;" ><fmt:message key='Service PO'/>.</label></td> 
                                     <td style="border:none"> <input type="text" name="txtServicePo" id="txtServicePo"  value ="<%=SERVICE_PO%>" class="form-control" style="width: 500px ;display: none" readonly="true" >
                                    </tr>
                                      <% }else if (selectedmodule.equals("PS") && (Status != "")){%>
                                       <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">

                                    </td>
                                    <td class="text-right h5"> <label for="ServicePo" id="ServicePo"  ><fmt:message key='Service PO'/>.</label></td> 
                                     <td> <input type="text" name="txtServicePo" id="txtServicePo"  value ="<%=SERVICE_PO%>" class="form-control" style="width: 500px ;" readonly="true" >
                                    </tr>
                                        <% }%>
                                


                                <%  if ((Status.equals("")||ModuleFlag.equals("NON_PO")) && UserType.equals("Vendor")) {%>
                                <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">

                                    </td>
                                    <td class="text-right h5"> <label for="txtInvoiceType" id='txtInvoiceType' data-id='txtInvoiceType' style="display: none;"><fmt:message key='Select Invoice Type'/>.</label></td> 
                                    <td><select id="selecttxtInvoiceType" name="selecttxtInvoiceType" class="form-control text-left" value = "<%=Invoice_type%>" style="width: 500px ;display: none" onchange="showOrHideRTDetails()" >
                                            <option value="">- <fmt:message key='Select'/> -</option>                                              
                                            <option value="Original Invoice">Original Invoice</option>
                                            <option value="RI Charges">RI Charges</option>
                                            <option value="Electrical Inspection Charges">Electrical Inspection Charges</option>
                                            <option value="Other Charges">Other Charges</option>
                                            <option value="Excess Invoice">Excess Invoice</option>
                                            <option value="Migo Based Invoice">Migo Based Invoice</option>
                                            <option value="Retention Claim Charges">Retention Claim Charges</option>
                                        </select></td></tr>
                                        <%} else if (((Status.equals("Saved")) || Status.equals("Rejected")) && UserType.equals("Vendor") && selectedmodule.equals("PS")) {%>
                                <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">

                                    </td>
                                    <td class="text-right h5"> <label for="txtInvoiceType" id='txtInvoiceType' data-id='txtInvoiceType' ><fmt:message key='Select Invoice Type'/>.</label></td> 
                                    <td><select id="selecttxtInvoiceType" name="selecttxtInvoiceType" class="form-control text-left" value = "<%=Invoice_type%>" style="width: 500px ;" onchange="showOrHideRTDetails()">
                                            <option value="">- <fmt:message key='Select'/> -</option>                                              
                                            <option value="Original Invoice">Original Invoice</option>
                                            <option value="RI Charges">RI Charges</option>
                                            <option value="Electrical Inspection Charges">Electrical Inspection Charges</option>
                                            <option value="Other Charges">Other Charges</option>
                                            <option value="Excess Invoice">Excess Invoice</option>
                                            <option value="Migo Based Invoice">Migo Based Invoice</option>
                                            <option value="Retention Claim Charges">Retention Claim Charges</option>
                                        </select></td></tr>
                                        <%} else if (((Status.equals("Submitted") || (Status.equals("Verified"))) && UserType.equals("Vendor") && selectedmodule.equals("PS")) || (UserType.equals("Emp") && selectedmodule.equals("PS"))) {%>
                                <tr>
                                    <td class="col-md-1">
                                    </td>
                                    <td class="col-md-2">

                                    </td><td class="text-right h5" ><fmt:message key='Select Invoice Type'/>.</td>
                                    <td><input type="text" name="selecttxtInvoiceType" id="selecttxtInvoiceType" style="width: 500px" value ="<%=Invoice_type%>"  class="form-control text-left" readonly="true"  ></td>
                                </tr>
                                <%}%>

                            </table>
                                
                            <div id="podetail" class="poDetail_list" ></div>
                            <table class="table" border="0" cellspacing="0" cellpadding="1"  id="retentionRadioButton" style="display:none;width:100%">
                                <tr>
                                    <td class="Label_login"><input type="radio"  name="rad_retentionClaim" id="rad_Retention_100" value="full" checked="checked" onclick="showFullRetentionClaimDetails()"/>&nbsp;Invoice
                                    </td>
                                    <td class="Label_login"><input type="radio"  name="rad_retentionClaim" id="rad_Retention_balance" value="partial" onclick="showPartialRetentionClaimDetails()"/> &nbsp;Balance amount
                                    </td>
                                </tr>
                            </table>
 <table class="table" border="0" cellspacing="0" cellpadding="2" id="retention_table_content"> 
                                <!-- Start of  table_content table  -->
                            </table>

                            <table class="table" border="0" cellspacing="0" cellpadding="2" id="table_content"> <!-- Start of  table_content table  -->
                                <tr>
                                    <td class="text-right h5"><fmt:message key='Application ID'/>.</td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtApplId" id="txtApplId" value="<%=ApplId%>" style="width: 100%" readonly="true" >
                                    </td>
                                    <td colspan="2" class="text-right h5"><fmt:message key='Status'/></td>
                                    <td >
                                        <input type="text" class="form-control" readonly="true" value="<%=Status%>" size="20" style="width: 100%" id="txtStatus" name="txtStatus" >
                                    </td>
                                </tr>
                      

                               
                                 
                                    <td class="text-right h5"><fmt:message key='Total PO Amount'/></td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtpoamt" id="txtpoamt" value="<%=Total_PO_Amt%>" style="width: 100%" readonly="true" /> 
                                    </td>
                                    <td colspan="2" class="text-right h5">Order Issuing Authority </td>
                                    <td> 
                                        <input name="txtPlant" id="txtPlant" type="text"  value="<%=Plant_Desc%>" size="20" class="form-control" maxlength="15" readonly="readonly" />
                                    </td> 
                                   

                                </tr>

                                

                                <td class="text-right h5" style="display:none"><fmt:message key='Total Invoices Submitted Amount'/></td>
                                <td>
                                    <input type="text" class="form-control text-right" name="txtinvamt" id="txtinvamt" style="width: 100%;display:none" readonly="true" /> 
                                </td>

                                <td class="text-right h5" colspan="2"style="display:none"><fmt:message key='Total Invoicable Amount'/></td>
                                <td > 
                                    <input name="txtinvoicablamt" id="txtinvoicablamt" type="text" class="form-control" size="20" style="width: 100%;display:none"   readonly="true" /> 
                                </td>

                                </tr>
                                <tr>
                                    <td  class="text-right h5"><fmt:message key='Invoice Number'/></td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtInvoiceNum" id="txtInvoiceNum"   maxlength="15" placeholder=<fmt:message key='"Max length is 15"'/>  value="<%=InvoiceNum%>"  <% if (Status == "") {
                                            } else {
                                                out.print("disabled='true'");
                                            }%> /></td>
                                     <td    colspan ="2" width="10%" class="text-right h5"><fmt:message key='Invoice Submit Date'/> </td>
                                    <td> 
                                        <input name="txtInvSubmitDt" style="text-align: left;" id="txtInvSubmitDt" type="text" size="20"class="form-control" value="<%=InvoiceSubmitDate%>" maxlength="15" readonly="readonly" />
                                    </td>  
                                  


                                </tr>
                                <tr>
                                    <td width="10%" class="text-right h5"><fmt:message key='Invoice Amount(Incl. Taxes)'/></td>
                                    <td width="20%">
                                        <input type="text" class="form-control text-right" value="<%=InvoiceAmt%>" size="20" placeholder=<fmt:message key='"Max length is 15"'/> maxlength="15" id="txtInvoiceAmt" name="txtInvoiceAmt" <% if (flag == 2) {
                                            } else {
                                                out.print("disabled='true'");
                                            }%> /></td>
                                  
  <td colspan ="2" width="10%" class="text-right h5">Invoice Inward Date </td>
                                    <td width="20%"> 
                                        <% if (flag == 2) {%>
                                             <input name="txtVendorInwardDt" id="txtVendorInwardDt" readonly type="text" size="20" class="datefield" value="<%=VendorInwardDate%>" maxlength="15" 
                                          <%   } else {%>
                                                <input name="txtVendorInwardDt" id="txtVendorInwardDt" readonly type="text" size="20" class="form-control" value="<%=VendorInwardDate%>" maxlength="15" readonly="readonly"
                                           <%   }%> /></td>
                                        <% if (flag == 2) {%>
                                    <td width="05%">
                                      <!--  <a href="javascript:void(0)" onClick="return callCalender('txtVendorInwardDt', 'yyyyFormat');">
                                            <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />-->
                                        <%}%></td>
                                </tr>


                                <% if (showinwflag.equals("Y")) {%>
                                <tr>
                                    <td class="text-right h5"><fmt:message key='MSEDCL Inward Number'/></td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtInwardNum" id="txtInwardNum" value="<%=InwardNum%>" placeholder=<fmt:message key='"Max length is 10"'/> maxlength="10"  style="width: 100%"
                                               <% if (UserType.equals("Emp") && Status.equals("Submitted")) {
                                                   } else {
                                                       out.print("disabled='true'");
                                                   }%>

                                               />
                                    </td>




                                    <td colspan ="2" width="10%" class="text-right h5"><fmt:message key='MSEDCL Inward Date'/> </td>
                                    <td> 
                                        <input name="txtInwardDt" id="txtInwardDt" type="text"  size="20"class="form-control" value="<%=InwardDate%>" maxlength="15" readonly="readonly" />
                                    </td>
                                </tr>
                                <%}%>

                                <tr> 
                                      <%  if ((Status == "") || (Status.equals("Rejected") || (Status.equals("Saved"))) && UserType.equals("Vendor")) {%>


                                    <td class="text-right h5">
                                        <label for="submit_at" id='submit_at' ><fmt:message key='Submit At'/></label></td>
                                    <td ><select class ="form-control text-right selectoption" style="width: 100%" id="SubmitAtPlant" name="SubmitAtPlant" value="<%=SUBMIT_AT_LOCATION%>" class="form-control text-right" style="width: 400px ;"  >
                                            <option value="">- <fmt:message key='Select'/> -</option>                                              


                                        </select></td>
                                        <%} else {%>
                                    <td class="text-right h5">
                                        <label for="submit_at" id='submit_at' ><fmt:message key='Submit At'/></label></td>
                                    <td width="20%">  <input type="text" name="SubmitAtPlant" id="SubmitAtPlant" style="width: 100%" value ="<%=SUBMIT_AT_LOCATION%>" class="form-control text-right" readonly="true" >
                                        <% }%> 

                                    <td colspan ="2" width="10%" class="text-right h5">Invoice Date </td>
                                    <td width="20%"> 
                                        <% if (flag == 2) {%>
                                             <input name="txtVendorInvoiceDate" id="txtVendorInvoiceDate" type="text" readonly size="20" class="datefield" value="<%=VendorInvoiceDt%>" maxlength="15" 
                                            <%  } else {%>
                                                 <input name="txtVendorInvoiceDate" id="txtVendorInvoiceDate" type="text" readonly size="20" class="form-control" value="<%=VendorInvoiceDt%>" maxlength="15" readonly="readonly"
                                          <%   }%> /></td>
                                        <% if (flag == 2) {%>
                                    <td width="05%">
                                      <!--  <a href="javascript:void(0)" onClick="return callCalender('txtVendorInvoiceDate');">
                                            <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />-->
                                        <%}%></td>

                                </tr>


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

                                <tr>



                                

                                        <%  if (IsRejectFlag.equals("Y")) {%>

                                    <td  class="text-right h5"></td>
                                    <td> 
                                    </td> 

                                    <td class="text-right h5" colspan ="2" width="10%" ><fmt:message key='Invoice Resubmission Date'/></td>
                                    <td width="20%"> 
                                        <input type="text" class="form-control text-left" name="txtVRDate" id="txtVRDate" value="<%=InvoiceResubmissionDate%>" style="width: 100%" readonly="true" > 
                                    </td>                                                    

                                    <% }%>  

                                </tr>





                            </table>  
                            <tr>
                                <td class="col-md-1">
                                </td>

                            <hr/>
                        </div>                          

                        <tr>
                            <%  if (flag == 2 ||Status.equals("Submitted")|| UserType.equals("Emp")) {%> 

                            <%  if (Status.equals("Rejected")) {%> 
                            <td  width="20%" class="text-right h5">Reason For Rejection </td>
                            <td width="20%">
                                <div class="autocomplete" style="height:10px; width:500px;">

                                    <input name="txtrejReason" id="textrejReason" type="textrejReason" class="form-control text-left" style="width: 100%;height:50px"  value="<%=Reason%>" maxlength="200" readonly="readonly"  />
                                    <%}%></div>
                                <BR><BR><BR><BR>
                            </td>                                     
                        </tr>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="col-md-1">
                                </td>
                                <td class="col-md-2">
                                    <% if ((flag==2)) {%> 
                                    <input type="button" value=<fmt:message key='Save'/> name="ButtonSave" id="ButtonSave" style="float: left;" onclick="saveButton()"
                                           class="btn  btn-success"/>
                                    <%}%>

                                </td>

                                <td class="col-md-1">
                                    <% if (UserType.equals("Vendor")) {%>
                                    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)%>" class="btn btn-danger" style="float: left;"><fmt:message key='Back'/></a>
                                    <%}%>                                            
                                </td>


                            </tr>
                        </table>

                        <!-- div file upload starts -->
                        <div id="fileUpload" class="content_container" >

                            <div id="FU_container" <% if ((Status == null)) {%> style="visibility: hidden;" <% }%> >
                                <form  method="post" enctype="multipart/form-data">
                                    <input type="hidden" id="<%=ApplicationConstants.UIACTION_NAME%>" name ="<%=ApplicationConstants.UIACTION_NAME%>" value="<%=ApplicationConstants.UIACTION_INVOICE_FILE_POST%>"/>
                                    <input type="hidden" name="view" id="view" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS)%>"/>
                                       <input type="hidden" name="view" id="view1" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_INVOICE_FILE_GET)%>"/>
  <!--<input type="hidden" name="dele" id="dele" value="<%//=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_TAFORM_GET)%>"/>-->
                                    <center><%  if ((flag == 2) && (Status.equals("Saved"))) {%>
                                        <div class="row">
                                            <div class="col-lg-2 col-md-2"> </div>
                                            <div class="col-lg-8 col-md-8">
                                            <!--    <table class="table" border="0">
                                                    <br>
                                                    <tr style="border-top:1px solid #ddd">
                                                        <td  align="left"> <h3>List Of Documents To Be Attached </h3></td>   
                                                    </tr>  <ul style="list-style-type:disc;">
                                                        <tr >
                                                            <td  align="left" style="border-top:none"> <li>Attach Letter Of Award(One Time Upload)</li></td></tr>
                                                        <tr><td  align="left" style="border-top:none"> <li>Attach Copy Of Agreement(One Time Upload)</li></td></tr>
                                                        <tr><td  align="left" style="border-top:none"> <li>Attach Insurance Copy(One Time Upload)</li></td></tr>
                                                        <tr><td  align="left" style="border-top:none"><li>Attach Milestone Chart(One Time Upload)</li></td></tr>
                                                        <tr><td  align="left" style="border-top:none"> <li>Attach Performance Bank Guarantee(One Time Upload)</li> </td></tr></ul>
                                                </table>-->
                                                <table class="table" border="0">

                                                    <tr><td colspan="3" align="center"><h4><fmt:message key='Upload File'/></h4>

                                                            <% if (((Status != null) && (Status.equals("Saved")))) {%>
                                                            <div class="form-control-err-msg"><fmt:message key='File Format should be either of jpg, jpeg, png or pdf'/>.</div>
                                                            <div class="form-control-err-msg">Max File size should be 1024 kb</div>
                                                            <%  }%>
                                                        </td> </tr>


                                                    <tr  align="center"><td ><select name="FOpt" class="form-control" id="FOpt">
                                                                <option value="Invoice Document">Invoice Document</option>
                                                                <!--<option value="Copy Of Agreement">Copy Of Agreement(One Time Upload)</option>
                                                                <option value="Insurance Copy">Insurance Copy(One Time Upload)</option>
                                                                <option value="Milestone Chart">Milestone Chart(One Time Upload)</option>
                                                                <option value="Bank Guarantee">Performance Bank Guarantee(One Time Upload)</option>-->

                                                                <option value="Other Supporting Document">Other Supporting Document</option>
                                                                <option value="Retention claim Document">Retention claim Document</option>
                                                            </select></td>
                                                        <td><b> <fmt:message key='Upload File'/> </b></td>
                                                        <td><input type="file" name="inpFile" id="inpFile" accept="image/jpeg,application/pdf" class="btn btn-success" onchange="validFinalFile();" /></td>
                                                    </tr>
                                                    <tr>
                                                        <th><center><fmt:message key='Remark'/></center></th>
                                                        <td colspan="3"><input class="form-control" type="text" maxlength="50" id="txtRemark" name="txtRemark"/></td></tr><tr>
                                                        <td colspan="3" align="center"><input type="submit" name="btnFile" id="btnFile" value=<fmt:message key='Upload'/> disabled="true" class="btn btn-success"> </td>
                                                    </tr>
                                                </table></div> </div>
                                                <%  }%>
                                    </center>
                                </form>   
                                <br>
                                <div class="table-responsive">
                                    <table class="table"  align="center">


                                        <%  
                                            Iterator itr = FileList.iterator();
                                            int j = 0;
                                            while (itr.hasNext()) {%>
                                        <tr class="success">
                                            <%  if (((flag == 2||(Status.equals("Submitted") && UserType.equals("Vendor"))) && j == 0) || (UserType.equals("Emp") && j == 0)) {%>
                                            <th>#</th>                                                
                                            <th><fmt:message key='File Name'/></th>
                                              <th><fmt:message key='File Type'/></th>
                                            <th><fmt:message key='Remark'/></th>
                                                <% if (UserType.equals("Vendor")) {%>
                                            <th><fmt:message key='Remove'/></th> 
                                                <% }%>
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
                                        <td><a href="#nogo" onclick="viewFile('<%=flb.getId()%>', '<%=flb.getOption()%>')"> <%=(flb.getFileName() + "." + flb.getFileType())%></a></td>
                                           <td><%=type%></td>
                                           <td><%=remark%></td>
                                    
                                        <%  if ((flag == 2) && (flb.getDELETION_FLAG().equals("Y"))) {%>

                                        <td><a href="#nogo" onclick="removeFile('<%=flb.getId()%>', '<%=flb.getOption()%>', '<%=(flb.getFileName() + "." + flb.getFileType())%>')"><img src="images/icon_delete.gif" alt="Remove" width="16" height="16" border="0" /></a></td>
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
                                    <br><br><center> <input type="button" value=<fmt:message key='Submit'/> name="ButtonSubmit" id="ButtonSubmit" onclick="submitButton()" class="btn btn-primary"/></center> <br>
                                        <% }%>
                                    </td>
                                    <td class="col-md-1">
                                    </td>
                                    <% }%>
                                    <form>
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <%  if (UserType.equals("Emp")) {%>
                                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_PO_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>   
                                            <%}%>
                                            <%  if (flag == 3) {%> 

                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>      <td class="col-md-1">
                                                    </td>
                                                    <td class="col-md-4">        

                                                        <%  if (UserType.equals("Emp") && (LocationId.equals(Office_Code) || LocationId.equals(ApplicationConstants.HO_OFFICE_CODE))) {%>
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
                                                            <option>Wrong PO Mapping</option>
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
                                            </table> 
                                    </form>

                                    <% }%>

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
                                <%  if (flag == 4) {

                                        if (!ApplicationUtils.isBlank(uiAction)) {
                                            if (uiAction.equals(ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM)) {
                                                if (vendorList != null) {
                                                    System.out.println("size of vendor list:" + vendorList.size());
                                                    for (VendorBean o : (LinkedList<VendorBean>) vendorList) {
                                                        if (o.getSaveFlag() != null) {
                                                            if ((o.getSaveFlag().equals("Technical")) || (o.getSaveFlag().equals("Accounts")) || (o.getSaveFlag().equals("Pending For Payment"))) {
                                                                applListNew.add(o);
                                                            } else if (o.getSaveFlag().equals("Paid")) {
                                                                applListOld.add(o);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                %> 
                                <li class="active"><a data-toggle="tab" href="#pendingList"><fmt:message key='Pending Invoices'/></a></li>
                                <li><a data-toggle="tab" href="#closedList"><fmt:message key='Paid Invoices'/>.</a></li>

                            </ul> 
                            <div class="tab-content">
                                <%@ include file="sort_paging_hidden_fields.jsp" %>                       

                                <div id="pendingList" class="tab-pane fade in active">
                                    <div class="content_container_sub">

                                        <%                                            if (applListNew.size() != 0) {
                                        %>
                                        <div class="row">
                                            <div class="col-lg-12 col-md-12">
                                                <div class="table-responsive">
                                                    <div class="col-md-12 text-center"><h3><fmt:message key='Pending Invoices'/>.</h3></div>
                                                    <table class="table">
                                                        <thead>
                                                            <tr class="success">                                                                                       
                                                                <th>#</th>
                                                                <th class="text-center"><fmt:message key='Location'/></th>  
                                                                <th class="text-center"><fmt:message key='Application ID'/></th>
                                                                <th class="text-center"><fmt:message key='PO/Project Id Description'/></th>
                                                                 <!--<th class="text-center"><fmt:message key='PO Type'/></th>--> 
                                                                <th class="text-center"><fmt:message key='PO/Project Id Creation Date'/></th> 
                                                               <!-- <th class="text-center"><fmt:message key='SES Number'/></th>
                                                                <th class="text-center"><fmt:message key='MIGO Number'/></th>
                                                                <th class="text-center"><fmt:message key='Liability Date'/></th>
                                                                <th class="text-center"><fmt:message key='Payment Date'/></th> 
                                                                <th class="text-center"><fmt:message key='SES Vendor Invoice Number'/></th> -->
                                                                <th class="text-center"><fmt:message key='VENDOR Inv No'/>.</th>
                                                                <th class="text-center"><fmt:message key='VENDOR Invoice Date'/>.</th>
                                                                <th class="text-center"><fmt:message key='VENDOR Invoice Amount'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Inv No'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Invoice Date'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Invoice Amount'/>.</th>
                                                                <th class="text-center"><fmt:message key='Pending With'/></th>                                                   
                                                                <th class="text-center"><fmt:message key='View'/></th>                                                  
                                                            </tr>
                                                        </thead>

                                                        <%
                                                            int j = 0;
                                                            int k = 0;
                                                            for (VendorBean comBean : (LinkedList<VendorBean>) applListNew) {
                                                                String Zone = "";
                                                                String Circle = "";
                                                                String Division = "";
                                                                String Location = "";
                                                                String PONumber1 = "";
                                                                String PODesc1 = "";
                                                                // String POType = "";
                                                                String POCreationDate = "";
                                                                String SesNum = "";
                                                                String MigoNum = "";
                                                                String SesorMigoNum = "";
                                                                String LiabilityDate = "";
                                                                String PaymentDate = "";
                                                                String ValidityStart = "";
                                                                String ValidityEnd = "";
                                                                String VendorInvoiceNumber = "";
                                                                String SesMigoInvNumber = "";
                                                                String VendorInvNumber = "";
                                                                String VendorInvoiceDate = "";
                                                                String Status1 = "";
                                                                String VendorInvoiceAmount = "";
                                                                String MsedclInvoiceDate = "";
                                                                String MsedclInvoiceAmount = "";
                                                                String MsedclInvoiceNumber = "";
                                                                int flag1 = 0;

                                                                if (!ApplicationUtils.isBlank(comBean.getZone())) {
                                                                    Location = comBean.getZone();
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getCircle())) {
                                                                    Location = Location + "-" + comBean.getCircle();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getDivision())) {
                                                                    Location = Location + "-" + comBean.getDivision();
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getPONumber())) {
                                                                    PONumber1 = comBean.getPONumber();

                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getPODesc())) {
                                                                    PODesc1 = PONumber1 + "-" + comBean.getPODesc();
                                                                }

                                                                /* if (!ApplicationUtils.isBlank(comBean.getPOType())) {
                                                                 POType = comBean.getPOType();
                                                                 }*/
                                                                if (!ApplicationUtils.isBlank(comBean.getPOCreationDate())) {
                                                                    POCreationDate = ApplicationUtils.dateToString(comBean.getPOCreationDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getSesNum())) {
                                                                    SesNum = comBean.getSesNum();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getMigo_doc())) {
                                                                    MigoNum = comBean.getMigo_doc();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getSesormigo_no())) {
                                                                    SesorMigoNum = comBean.getSesormigo_no();
                                                                }

                                                                /* if (!ApplicationUtils.isBlank(comBean.getSes_migo_ven_invno())) {
                                                                 SesMigoInvNumber = comBean.getSes_migo_ven_invno();
                                                                 }*/
                                                                if (!ApplicationUtils.isBlank(comBean.getInvDt())) {
                                                                    LiabilityDate = ApplicationUtils.dateToString(comBean.getInvDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getCLDocDt())) {
                                                                    PaymentDate = ApplicationUtils.dateToString(comBean.getCLDocDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getValidityStart())) {
                                                                    ValidityStart = ApplicationUtils.dateToString(comBean.getValidityStart(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getValidityEnd())) {
                                                                    ValidityEnd = ApplicationUtils.dateToString(comBean.getValidityEnd(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getVendorInvoiceNumber())) {
                                                                    VendorInvoiceNumber = comBean.getVendorInvoiceNumber();
                                                                    //flag1 = 2;
                                                                    VendorInvNumber = VendorInvoiceNumber.replace("\\", "\\\\");

                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getSaveFlag())) {
                                                                    Status1 = comBean.getSaveFlag();
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getInvoiceDate())) {
                                                                    VendorInvoiceDate = ApplicationUtils.dateToString(comBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getInvoiceAmount())) {
                                                                    VendorInvoiceAmount = comBean.getInvoiceAmount();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getInvDt())) {
                                                                    MsedclInvoiceDate = ApplicationUtils.dateToString(comBean.getInvDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getInvoiceAmount())) {
                                                                    MsedclInvoiceAmount = comBean.getInvoiceAmount();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getMsedclInvoiceNumber())) {
                                                                    MsedclInvoiceNumber = comBean.getMsedclInvoiceNumber();
                                                                }


                                                        %>


                                                        <tbody>
                                                            <% if (selectedmodule.equals("PS")) {
                                                                    if (VendorInvoiceNumber.equals(InvoiceNum) || (VendorInvoiceNumber.equals("Pending")) || (VendorInvoiceNumber.equals("")))//Vendorinvoice number from sap
                                                                    {%>
                                                            <tr class="info" >
                                                                <td align="center"><%=++j%></td>
                                                                <td align="center" width="15%"><%=Location%></td>  
                                                                <td align="center"><%=ApplId%></td> 
                                                                <td align="center" width="15%"><%=PODesc1%></td>
                                                                <!--<td align="center"><//%=POType%></td> -->                                         
                                                                <td align="center"><%=POCreationDate%></td>  
                                                                <!--<td align="center"><%=SesNum%></td>
                                                                <td align="center"><%=MigoNum%></td>
                                                                <td align="center"><%=LiabilityDate%></td>   
                                                                <td align="center"><%=PaymentDate%></td>
                                                                <td align="center"><%=SesMigoInvNumber%></td>-->  

                                                                <td align="center"></td>

                                                                <td align="center"><%=VendorInvoiceDt%></td>
                                                                <td align="center"><%=InvoiceAmt%></td>

                                                                <td align="center"><%=MsedclInvoiceNumber%></td>

                                                                <td align="center"><%=MsedclInvoiceDate%></td>
                                                                <td align="center"><%=MsedclInvoiceAmount%></td>
                                                                <td align="center"><%=Status1%></td> 

                                                                <td align="center"><a href="#nogo" onclick="viewvendorApp_ps('<%=VendorInvoiceNumber%>', '<%=UserNumber%>', '<%=viewAction2%>', '<%=MsedclInvoiceNumber%>', '<%=selectedmodule%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>

                                                                <% }
                                                                } else {%>

                                                                <%if (VendorInvoiceNumber.replaceAll("[^a-zA-Z0-9]", "").equalsIgnoreCase(InvoiceNum.replaceAll("[^a-zA-Z0-9]", "")) || (VendorInvoiceNumber.equals("Pending")) || (VendorInvoiceNumber.equals("")))//Vendorinvoice number from sap
                                                                    {%>  

                                                            <tr class="info" >
                                                                <td align="center"><%=++k%></td>
                                                                <td align="center" width="15%"><%=Location%></td>  
                                                                <td align="center"><%=ApplId%></td> 
                                                                <td align="center" width="15%"><%=PODesc1%></td>
                                                                <!--<td align="center"><//%=POType%></td> -->                                         
                                                                <td align="center"><%=POCreationDate%></td>  
                                                                <!--<td align="center"><%=SesNum%></td>
                                                                <td align="center"><%=MigoNum%></td>
                                                                <td align="center"><%=LiabilityDate%></td>   
                                                                <td align="center"><%=PaymentDate%></td>
                                                                <td align="center"><%=SesMigoInvNumber%></td>-->  

                                                                <td align="center"><%=InvoiceNum%></td>

                                                                <td align="center"><%=VendorInvoiceDt%></td>
                                                                <td align="center"><%=InvoiceAmt%></td>

                                                                <td align="center"><%=VendorInvoiceNumber%></td>

                                                                <td align="center"><%=VendorInvoiceDate%></td>
                                                                <td align="center"><%=VendorInvoiceAmount%></td>
                                                                <td align="center"><%=Status1%></td> 
                                                                <%if (UserType.equals("Vendor")) {%>

                                                                <td align="center"><a href="#nogo" onclick="viewEmpApp('<%=SesorMigoNum%>', '<%=VendorInvoiceNumber%>', '<%=UserNumber%>', '<%=viewAction1%>', '<%=PONumber1%>', '<%=selectedmodule%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                                                        <%} else {%>
                                                                <td align="center"><a href="#nogo" onclick="viewEmpApp('<%=SesorMigoNum%>', '<%=UserNumber%>', '<%=viewAction1%>', '<%=PONumber1%>', '<%=selectedmodule%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                                                        <%}%>
                                                            </tr><% }
                                                                    }
                                                                }
                                                            %>
                                                        </tbody>
                                                    </table>
                                                </div>

                                            </div>
                                        </div>                                                 

                                        <% } else {%>
                                        <div class="row">                
                                            <div class="col-lg-12 col-md-12">                        
                                                <div class="table-responsive">
                                                    <div class="col-md-12 text-center"><h3><fmt:message key='Pending Invoices'/></h3></div>
                                                    <table class="table">
                                                        <thead>
                                                            <tr class="success">                                        
                                                                <th>#</th>
                                                                <th class="text-center"><fmt:message key='Location'/></th>  
                                                                <th class="text-center"><fmt:message key='Application ID'/></th>
                                                                <th class="text-center"><fmt:message key='PO/Project Id Description'/></th>
                                                                 <!--<th class="text-center"><fmt:message key='PO Type'/></th>--> 
                                                                <th class="text-center"><fmt:message key='PO/Project Id Creation Date'/></th> 
                                                               <!-- <th class="text-center"><fmt:message key='SES Number'/></th>
                                                                <th class="text-center"><fmt:message key='MIGO Number'/></th>
                                                                <th class="text-center"><fmt:message key='Liability Date'/></th>
                                                                <th class="text-center"><fmt:message key='Payment Date'/></th> 
                                                                <th class="text-center"><fmt:message key='SES Vendor Invoice Number'/></th> -->
                                                                <th class="text-center"><fmt:message key='VENDOR Inv No'/>.</th>
                                                                <th class="text-center"><fmt:message key='VENDOR Invoice Date'/>.</th>
                                                                <th class="text-center"><fmt:message key='VENDOR Invoice Amount'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Inv No'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Invoice Date'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Invoice Amount'/>.</th>
                                                                <th class="text-center"><fmt:message key='Pending With'/></th>                                                   
                                                                <th class="text-center"><fmt:message key='View'/></th>         
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr class="info" align="center" >                                                      
                                                                <td colspan="13"><%=recordsVar%></td>
                                                            </tr>

                                                        </tbody>
                                                    </table>
                                                </div>

                                            </div>
                                        </div>  

                                        <% }%>
                                    </div>
                                </div><!--pending List ends-->

                                <div id="closedList" class="tab-pane fade">
                                    <div class="content_container_sub"> 

                                        <%
                                            if (applListOld.size() != 0) {
                                        %>

                                        <div class="row">                
                                            <div class="col-lg-12 col-md-12">                        
                                                <div class="table-responsive">
                                                    <div class="col-md-12 text-center"><h3>Paid Invoices</h3></div>
                                                    <table class="table">
                                                        <thead>
                                                            <tr class="success">                                                                                       
                                                                <th>#</th>
                                                                <th class="text-center"><fmt:message key='Location'/></th>  
                                                                <th class="text-center"><fmt:message key='Application ID'/></th>
                                                                <th class="text-center"><fmt:message key='PO/Project Id Description'/></th>
                                                                 <!--<th class="text-center"><fmt:message key='PO Type'/></th>--> 
                                                                <th class="text-center"><fmt:message key='PO/Project Id Creation Date'/></th> 
                                                               <!-- <th class="text-center"><fmt:message key='SES Number'/></th>
                                                                <th class="text-center"><fmt:message key='MIGO Number'/></th>
                                                                <th class="text-center"><fmt:message key='Liability Date'/></th>
                                                                <th class="text-center"><fmt:message key='Payment Date'/></th> 
                                                                <th class="text-center"><fmt:message key='SES Vendor Invoice Number'/></th> -->
                                                                <th class="text-center"><fmt:message key='VENDOR Inv No'/>.</th>
                                                                <th class="text-center"><fmt:message key='VENDOR Invoice Date'/>.</th>
                                                                <th class="text-center"><fmt:message key='VENDOR Invoice Amount'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Inv No'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Invoice Date'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Invoice Amount'/>.</th>
                                                                <th class="text-center"><fmt:message key='Pending With'/></th>                                                   
                                                                <th class="text-center"><fmt:message key='View'/></th>                                                  
                                                            </tr>
                                                        </thead>
                                                        <%
                                                            int j = 0;

                                                            for (VendorBean comBean : (LinkedList<VendorBean>) applListOld) {
                                                                j++;

                                                                String Zone = "";
                                                                String Circle = "";
                                                                String Division = "";
                                                                String Location = "";
                                                                String PONumber1 = "";
                                                                String PODesc1 = "";
                                                                // String POType = "";
                                                                String POCreationDate = "";
                                                                String SesNum = "";
                                                                String MigoNum = "";
                                                                String SesMigoInvNumber = "";
                                                                String LiabilityDate = "";
                                                                String PaymentDate = "";
                                                                String ValidityStart = "";
                                                                String ValidityEnd = "";
                                                                String VendorInvoiceNumber = "";
                                                                String VendorInvNumber = "";
                                                                String MsedclInvoiceNumber = "";
                                                                String VendorInvoiceDate = "";
                                                                String SesorMigoNum = "";
                                                                String Status1 = "";
                                                                String VendorInvoiceAmount = "";
                                                                String MsedclInvoiceDate = "";
                                                                String MsedclInvoiceAmount = "";
                                                                int flag1 = 0;

                                                                if (!ApplicationUtils.isBlank(comBean.getZone())) {
                                                                    Location = comBean.getZone();
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getCircle())) {
                                                                    Location = Location + "-" + comBean.getCircle();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getDivision())) {
                                                                    Location = Location + "-" + comBean.getDivision();
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getPONumber())) {
                                                                    PONumber1 = comBean.getPONumber();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getPODesc())) {
                                                                    PODesc1 = PONumber1 + "-" + comBean.getPODesc();
                                                                }

                                                                /*  if (!ApplicationUtils.isBlank(comBean.getPOType())) {
                                                                 POType = comBean.getPOType();
                                                                 }*/
                                                                if (!ApplicationUtils.isBlank(comBean.getPOCreationDate())) {
                                                                    POCreationDate = ApplicationUtils.dateToString(comBean.getPOCreationDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getSesNum())) {
                                                                    SesNum = comBean.getSesNum();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getMigo_doc())) {
                                                                    MigoNum = comBean.getMigo_doc();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getSesormigo_no())) {
                                                                    SesorMigoNum = comBean.getSesormigo_no();
                                                                }
                                                                /* if (!ApplicationUtils.isBlank(comBean.getSes_migo_ven_invno())) {
                                                                 SesMigoInvNumber = comBean.getSes_migo_ven_invno();
                                                                 }*/

                                                                if (!ApplicationUtils.isBlank(comBean.getInvDt())) {
                                                                    LiabilityDate = ApplicationUtils.dateToString(comBean.getInvDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getCLDocDt())) {
                                                                    PaymentDate = ApplicationUtils.dateToString(comBean.getCLDocDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getValidityStart())) {
                                                                    ValidityStart = ApplicationUtils.dateToString(comBean.getValidityStart(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getValidityEnd())) {
                                                                    ValidityEnd = ApplicationUtils.dateToString(comBean.getValidityEnd(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getVendorInvoiceNumber())) {
                                                                    VendorInvoiceNumber = comBean.getVendorInvoiceNumber();
                                                                    flag1 = 2;
                                                                    VendorInvNumber = VendorInvoiceNumber.replace("\\", "\\\\");
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getMsedclInvoiceNumber())) {
                                                                    MsedclInvoiceNumber = comBean.getMsedclInvoiceNumber();

                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getSaveFlag())) {
                                                                    Status1 = comBean.getSaveFlag();
                                                                }

                                                                if (!ApplicationUtils.isBlank(comBean.getInvoiceDate())) {
                                                                    VendorInvoiceDate = ApplicationUtils.dateToString(comBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getInvoiceAmount())) {
                                                                    VendorInvoiceAmount = comBean.getInvoiceAmount();
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getInvDt())) {
                                                                    MsedclInvoiceDate = ApplicationUtils.dateToString(comBean.getInvDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                                }
                                                                if (!ApplicationUtils.isBlank(comBean.getInvoiceAmount())) {
                                                                    MsedclInvoiceAmount = comBean.getInvoiceAmount();
                                                                }


                                                        %>



                                                        <% if (selectedmodule.equals("PS")) {%>
                                                        <tr class="info" >
                                                            <td align="center"><%=j%></td>
                                                            <td align="center" width="15%"><%=Location%></td>  
                                                            <td align="center"><%=ApplId%></td> 
                                                            <td align="center" width="15%"><%=PODesc1%></td>
                                                            <!--<td align="center"><//%=POType%></td> -->                                         
                                                            <td align="center"><%=POCreationDate%></td>  
                                                            <!--<td align="center"><%=SesNum%></td>
                                                            <td align="center"><%=MigoNum%></td>
                                                            <td align="center"><%=LiabilityDate%></td>   
                                                            <td align="center"><%=PaymentDate%></td>
                                                            <td align="center"><%=SesMigoInvNumber%></td>-->  

                                                            <td align="center"></td>

                                                            <td align="center"><%=VendorInvoiceDt%></td>
                                                            <td align="center"><%=InvoiceAmt%></td>

                                                            <td align="center"><%=MsedclInvoiceNumber%></td>

                                                            <td align="center"><%=MsedclInvoiceDate%></td>
                                                            <td align="center"><%=MsedclInvoiceAmount%></td>
                                                            <td align="center"><%=Status1%></td> 

                                                            <td align="center"><a href="#nogo" onclick="viewVendorApp_ps('<%=VendorInvoiceNumber%>', '<%=UserNumber%>', '<%=viewAction2%>', '<%=MsedclInvoiceNumber%>', '<%=selectedmodule%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>

                                                            <% } else {%>
                                                            <%if (VendorInvoiceNumber.equals(InvoiceNum) || (VendorInvoiceNumber.equals("")))//Vendorinvoice number from sap
                                                            {%>  

                                                        <tr class="info" >
                                                            <td align="center"><%=j%></td>
                                                            <td align="center" width="15%"><%=Location%></td>  
                                                            <td align="center"><%=ApplId%></td> 
                                                            <td align="center" width="15%"><%=PODesc1%></td>
                                                            <!--<td align="center"><//%=POType%></td> -->                                         
                                                            <td align="center"><%=POCreationDate%></td>  
                                                            <!--<td align="center"><%=SesNum%></td>
                                                            <td align="center"><%=MigoNum%></td>
                                                            <td align="center"><%=LiabilityDate%></td>   
                                                            <td align="center"><%=PaymentDate%></td>
                                                            <td align="center"><%=SesMigoInvNumber%></td>-->  

                                                            <td align="center"><%=InvoiceNum%></td>

                                                            <td align="center"><%=VendorInvoiceDt%></td>
                                                            <td align="center"><%=InvoiceAmt%></td>

                                                            <td align="center"><%=VendorInvoiceNumber%></td>

                                                            <td align="center"><%=VendorInvoiceDate%></td>
                                                            <td align="center"><%=VendorInvoiceAmount%></td>
                                                            <td align="center"><%=Status1%></td> 

                                                            <%if (UserType.equals("Vendor")) {%>
                                                            <td align="center"><a href="#nogo" onclick="viewEmpApp('<%=SesorMigoNum%>', '<%=VendorInvoiceNumber%>', '<%=UserNumber%>', '<%=viewAction1%>', '<%=PONumber1%>', '<%=selectedmodule%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>

                                                            <%} else {%>
                                                            <td align="center"><a href="#nogo" onclick="viewEmpApp('<%=SesorMigoNum%>', '<%=UserNumber%>', '<%=viewAction1%>', '<%=PONumber1%>', '<%=selectedmodule%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                                                    <% }%>
                                                        </tr><% }
                                                                }
                                                            }
                                                        %>
                                                        </tbody>
                                                    </table>
                                                </div>

                                            </div>
                                        </div>                                                          

                                        <% } else {%>
                                        <div class="row">                
                                            <div class="col-lg-12 col-md-12">                        
                                                <div class="table-responsive">
                                                    <div class="col-md-12 text-center"><h3><fmt:message key='Paid Invoices'/></h3></div>
                                                    <table class="table">
                                                        <thead>
                                                            <tr class="success">                                        
                                                                <th>#</th>
                                                                <th class="text-center"><fmt:message key='Location'/></th>  
                                                                <th class="text-center"><fmt:message key='Application ID'/></th>
                                                                <th class="text-center"><fmt:message key='PO/Project Id Description'/></th>
                                                                 <!--<th class="text-center"><fmt:message key='PO Type'/></th>--> 
                                                                <th class="text-center"><fmt:message key='PO/Project Id Creation Date'/></th> 
                                                               <!-- <th class="text-center"><fmt:message key='SES Number'/></th>
                                                                <th class="text-center"><fmt:message key='MIGO Number'/></th>
                                                                <th class="text-center"><fmt:message key='Liability Date'/></th>
                                                                <th class="text-center"><fmt:message key='Payment Date'/></th> 
                                                                <th class="text-center"><fmt:message key='SES Vendor Invoice Number'/></th> -->
                                                                <th class="text-center"><fmt:message key='VENDOR Inv No'/>.</th>
                                                                <th class="text-center"><fmt:message key='VENDOR Invoice Date'/>.</th>
                                                                <th class="text-center"><fmt:message key='VENDOR Invoice Amount'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Inv No'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Invoice Date'/>.</th>
                                                                <th class="text-center"><fmt:message key='MSEDCL Invoice Amount'/>.</th>
                                                                <th class="text-center"><fmt:message key='Pending With'/></th>                                                   
                                                                <th class="text-center"><fmt:message key='View'/></th>                                                          
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr class="info" align="center" >                                                      
                                                                <td colspan="13"><%=recordsVar%></td>
                                                            </tr>

                                                        </tbody>
                                                    </table>
                                                </div>

                                            </div>
                                        </div>  

                                        <% }%>   
                                        <input type = "hidden" name="lastValue" id="lastValue" value="" />
                                        <input type = "hidden" name="lastValueDataType"   id="lastValueDataType" value="" />
                                    </div>
                                </div> <!-- Closed list ends-->              
                            </div>  <!-- /. tab-content  -->
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>           
                            <br><br>
                            <% }%>  

                            <%@include file="nav_emp_footer.jsp" %>

                            <!-- SCRIPTS -AT THE BOtTOM TO REDUCE THE LOAD TIME-->

                            <!-- BOOTSTRAP SCRIPTS -->
                            <script src="assets/js/bootstrap.min.js"></script>
                            <!-- CUSTOM SCRIPTS -->
                            <script src="assets/js/custom.js"></script>

                            </body>
                            </html>