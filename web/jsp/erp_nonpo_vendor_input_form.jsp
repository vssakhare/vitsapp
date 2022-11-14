<%--
    Document   : erp_vendor_appl_form
    Created on : Dec 10, 2017, 4:06:44 PM
    Author     : pooja
--%>

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
<%@page import="in.emp.vendor.bean.VendorInputBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%

    String uiAction = "";

    LinkedList applZoneList = new LinkedList();
    LinkedList applCircleList = new LinkedList();
    LinkedList applDivisionList = new LinkedList();
    LinkedList applSubDivisionList = new LinkedList();
    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }
    VendorPrezData vendorPrezDataObjone = new VendorPrezData();
    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    VendorInputBean vendorInputBean = new VendorInputBean();

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_NON_PO_FORM_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_NON_PO_FORM_SESSION_DATA);
        applZoneList = (LinkedList) vendorPrezDataObj.getZoneList();
        vendorInputBean = (VendorInputBean) vendorPrezDataObj.getVendorInputBean();

        applCircleList = (LinkedList) vendorPrezDataObj.getCircleList();
        applDivisionList = (LinkedList) vendorPrezDataObj.getDivisionList();
        applSubDivisionList = (LinkedList) vendorPrezDataObj.getSubdivisionList();
//
      //  if (!ApplicationUtils.isBlank(vendorPrezDataObj.getVendorInputBean())) {
      //      vendorInputBean = vendorPrezDataObj.getVendorInputBean();
     //   }
    }

    String UserNumber = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
    String viewAction = "";

    String Status = "";

    if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM)) {
            //actionStatus = "Saved";
            viewAction = "redir";

        }
    }
    Date sysdate = new Date();
    String InvoiceSubmitDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);

    String ApplId = "";
    String Zone = "";
    String Circle = "";
    String SubDivision = "";
    String Division = "";
    String InvoiceNum = "";
    String VendorInvoiceDt = "";
    String VendorInwardDate = "";
    String InvoiceResubmissionDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
    String InvoiceAmt = "";
    String InwardNum = "";
    // String InwardDate = ApplicationUtils.dateToString(sysdate, ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
    String InvoiceFrmDate = "";
    String InvoiceToDate = "";
    String UserType = "";
    String Plant = "";
    String Plant_Desc = "";
    String selectedmodule = "";
    ArrayList zonelist = new ArrayList();
    ArrayList circlelist = new ArrayList();
    ArrayList subdivlist = new ArrayList();
    ArrayList divlist = new ArrayList();
    String zonelistString = "";
    String circlelistString = "";
    String subdivlistString = "";
    String divlistString = "";
    String workDetail = "";
    //file =request.getSession().getAttribute("filName").toString();
    if (vendorInputBean != null) {

        if (!ApplicationUtils.isBlank(vendorInputBean.getApplId())) {
            ApplId = vendorInputBean.getApplId();
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceDate())) {
            VendorInvoiceDt = ApplicationUtils.dateToString(vendorInputBean.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())) {
            InvoiceNum = vendorInputBean.getVendorInvoiceNumber();
        }

        if ((vendorInputBean.getVendorInwardDate() != null) && !(vendorInputBean.getVendorInwardDate().equals("null")) && !(vendorInputBean.getVendorInwardDate().equals(""))) {
            VendorInwardDate = ApplicationUtils.dateToString(vendorInputBean.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceAmount())) {
            InvoiceAmt = vendorInputBean.getVendorInvoiceAmount();
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getInwardNumber())) {
            InwardNum = vendorInputBean.getInwardNumber();
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

        if (!ApplicationUtils.isBlank(vendorInputBean.getUserType())) {
            UserType = vendorInputBean.getUserType();
        }

        if (!ApplicationUtils.isBlank(vendorInputBean.getPlant_desc())) {
            Plant_Desc = Plant + "-" + vendorInputBean.getPlant_desc();
        }

        if ((vendorInputBean.getSelectedModuleType() != null)) {//nature of work in non po invoices 
            selectedmodule = vendorInputBean.getSelectedModuleType();
        }
        if ((vendorInputBean.getZone() != null)) {
            Zone = vendorInputBean.getZone();
        }
        if ((vendorInputBean.getCircle() != null)) {
            Circle = vendorInputBean.getCircle();
        }
        if ((vendorInputBean.getSubDivision() != null)) {
            SubDivision = vendorInputBean.getSubDivision();
        }
        if ((vendorInputBean.getDivision() != null)) {
            Division = vendorInputBean.getDivision();
        }
        if ((vendorInputBean.getWorkDetailDesc() != null)) {
            workDetail = vendorInputBean.getWorkDetailDesc();
        }
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
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_nonpo_vendor_input_form.js"></script>


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
                    <input type="hidden" name="selectzone" id="selectzone" value ="<%=Zone%>"/>
                    <input type="hidden" name="selectcircle" id="selectcircle" value ="<%=Circle%>"/>
                    <input type="hidden" name="selectdiv" id="selectdiv" value ="<%=SubDivision%>"/>
                    <input type="hidden" name="selectsubdiv" id="selectsubdiv" value ="<%=Division%>"/>
                    <input type="hidden" name="naturework" id="naturework" value ="<%=selectedmodule%>"/>
                    <input type="hidden" name="status" id="status" value ="<%=Status%>"/>
                    <input type="hidden" name="redir" id="redir" value="<%=ApplicationConstants.UIACTION_NONPO_VENDOR_INPUT_FORM%>"/>
                    <input type="hidden" name="zoneOptions" id="zoneOptions"  value='[<% if (!ApplicationUtils.isBlank(applZoneList)) {
                            //out.print('[' );
                            int i = 0;

                            for (POBean pBean : (LinkedList<POBean>) applZoneList) {
                                if (i != 0) {
                                    out.print(" , ");
                                }
                                out.print("\"" + pBean.getLocationName().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "\"");
                                // out.print("\"" + pBean.getPONumber() + "\""  );
                                //zonelist.add(pBean.getLocationName().replace('"', ' ').replace('\'', ' ').replace('-', ' '));
                                i++;
                                zonelistString += "<option value = " + pBean.getLocationCode().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "-" + pBean.getLocationType() + ">"
                                        + pBean.getLocationName() + "</option>";
                            }
                        }
                        // out.print( ']' );
                           %>]'/>
                    <input type="hidden" name="circleOptions" id="circleOptions" value='[<% if (!ApplicationUtils.isBlank(applCircleList)) {
                            //out.print('[' );
                            int i = 0;
                            for (POBean poBean : (LinkedList<POBean>) applCircleList) {
                                if (i != 0) {
                                    out.print(" , ");
                                }
                                out.print("\"" + poBean.getLocationName().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "\"");
                                // zonelist.add(poBean.getLocationName().replace('"', ' ').replace('\'', ' ').replace('-', ' ')+ "-" + poBean.getLocationCode());
                                circlelistString += "<option value = " + poBean.getLocationCode() + ">"
                                        + poBean.getLocationName() + "</option>";
                                i++;
                            }
                        }
                        // out.print( ']' );
                           %>]'/>
                    <input type="hidden" name="divOptions" id="divOptions" value='[<% if (!ApplicationUtils.isBlank(applDivisionList)) {
                            //out.print('[' );
                            int i = 0;
                            for (POBean poBean : (LinkedList<POBean>) applDivisionList) {
                                if (i != 0) {
                                    out.print(" , ");
                                }
                                out.print("\"" + poBean.getLocationName().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "\"");
                                //  zonelist.add(poBean.getLocationName().replace('"', ' ').replace('\'', ' ').replace('-', ' ')+ "-" + poBean.getLocationCode());
                                i++;
                                subdivlistString += "<option value = " + poBean.getLocationCode() + ">"
                                        + poBean.getLocationName() + "</option>";
                            }
                        }
                        // out.print( ']' );
                           %>]'/>
                    <input type="hidden" name="subdivOptions" id="subdivOptions" value='[<% if (!ApplicationUtils.isBlank(applSubDivisionList)) {
                            //out.print('[' );
                            int i = 0;
                            for (POBean poBean : (LinkedList<POBean>) applSubDivisionList) {
                                if (i != 0) {
                                    %>, <%
                                }
                                out.print("\"" + poBean.getLocationName().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "\"");
                                //zonelist.add(poBean.getLocationName().replace('"', ' ').replace('\'', ' ').replace('-', ' ')+ "-" + poBean.getLocationCode());
                                i++;
                                divlistString += "<option value = " + poBean.getLocationCode().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "-" + poBean.getLocationType() + ">"
                                        + poBean.getLocationName() + "</option>";
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
                                        <br><br><div class="col-md-12"><h3><fmt:message key='Non PO Vendor Invoice Form'/></h3></div>
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
                                    </td>
                                    <td class="text-right h5" ><label id="mydrop_down_label"><fmt:message key='Zone'/></label>.</td>

                                    <td >

                                        <select class ="form-control selectoption" id="txtzone"  style="width: 500px " onchange="gethierarchylocation(this.value)" >
                                            <option value ="">Select</option>
                                            <%=zonelistString%>
                                        </select>
                                    </td>
                                    <td class="col-md-2">   
                                    </td>
                                    <td class="col-md-1">
                                    </td>
                                </tr>
                                <tr></tr>
                                <tr>
                                    <td colspan="6" class="loc">
                                        <div style="width: 100%;display: flex;">
                                            <div>    <label><fmt:message key='Circle'/></label>

                                                <select class ="form-control selectoption" id="txtCircle"  style="width: 200px "  onchange="gethierarchylocation(this.value)" >
                                                    <option value ='' selected >Select</option>
                                                </select></div> 
                                            <div>   <label><fmt:message key='Division'/></label>

                                                <select class ="form-control selectoption" id="txtDivision"  style="width: 200px "  onchange="gethierarchylocation(this.value)" >
                                                    <option value ="">Select</option>
                                                </select></div> 


                                            <div>  <label><fmt:message key='SubDivision'/></label>
                                                <select class ="form-control selectoption" style="width: 200px "  id="txtSubDivision"   >
                                                    <option value ="">Select</option>
                                                </select></div> 
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <table class="table" border="0" cellspacing="0" cellpadding="2" id="table_content"> <!-- Start of  table_content table  -->
                                <tr>
                                    <td class="text-right h5"><fmt:message key='Application ID'/>.</td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtApplId"  id="txtApplId"  style="width: 100%" value="<%=ApplId%>" readonly="true" >
                                    </td>
                                    <td colspan="2" class="text-right h5"><fmt:message key='Status'/></td>
                                    <td >
                                        <input type="text" class="form-control" readonly="true"  size="20" style="width: 100%" id="txtStatus" name="txtStatus" value="<%=Status%>" >
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="text-right h5"><fmt:message key='Invoice Number'/></td>
                                    <td>
                                        <input type="text" class="form-control text-right" name="txtInvoiceNum" id="txtInvoiceNum"   value="<%=InvoiceNum%>" maxlength="15" placeholder=<fmt:message key='"Max length is 15"'/>   
                                               <% if (Status.equals("Saved")) {
                                                           out.print("disabled='true'");
                                                       } else {

                                                       }%>   /></td>
                                    
                                     <td colspan ="2" width="10%" class="text-right h5">Invoice Date </td>
                                    <td width="20%"> 
                                        <input name="txtVendorInvoiceDate" id="txtVendorInvoiceDate" readonly type="text" size="20" class="datefield"  maxlength="15"   value="<%=VendorInvoiceDt%>"/></td>


                                </tr>
                                <tr>
                                    <td width="10%" class="text-right h5"><fmt:message key='Invoice Amount(Incl. Taxes)'/></td>
                                    <td width="20%">
                                        <input type="text" class="form-control text-right"  size="20" placeholder=<fmt:message key='"Max length is 15"'/> maxlength="15" id="txtInvoiceAmt" name="txtInvoiceAmt" value="<%=InvoiceAmt%>"/></td>

                                    <td colspan ="2" width="10%" class="text-right h5" ><fmt:message key='Vendor Inward Date'/> </td>
                                    <td width="20%"> 
                                        <input name="txtVendorInwardDt" id="txtVendorInwardDt" type="text" size="20" class="datefield"  maxlength="15" readonly  value="<%=VendorInwardDate%>"
                                               /></td>

                                   <!-- <td width="05%">
                                        <a href="javascript:void(0)" onClick="return callCalender('txtVendorInwardDt', 'yyyyFormat');">
                                            <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />
                                    </td>-->


                                </tr>



                                 
                                <tr>
                                    <td width="10%" class="text-right h5"><fmt:message key='Invoice From Date'/> </td>
                                    <td width="20%"> 
                                        <input name="txtVendorInvoiceFromDate" readonly id="txtVendorInvoiceFromDate" type="text" size="20" style="text-align: right;" class="datefield"   maxlength="15"  value="<%=InvoiceFrmDate%>" /></td>

                                   <td width="05%">
                                      <!--  <a href="javascript:void(0)" onClick="return callCalender('txtVendorInvoiceFromDate');">
                                            <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />-->
                                    </td>
                                    <td  width="10%" class="text-right h5"><fmt:message key='Invoice To Date'/> </td>
                                    <td width="20%"> 
                                        <input name="txtVendorInvoiceToDate" id="txtVendorInvoiceToDate" readonly type="text" size="20" class="datefield"  maxlength="15"  value="<%=InvoiceToDate%>" /></td>

                                  <!--  <td width="05%">
                                        <a href="javascript:void(0)" onClick="return callCalender('txtVendorInvoiceToDate');">
                                            <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />
                                    </td>-->
                                </tr>
 <tr>
           <td width="10%" class="text-right h5"></td>
                                    <td width="20%">
                                      </td>
                                <td  colspan="2" width="10%" class="text-right h5"><fmt:message key='Invoice Submit Date'/> </td>
                                    <td> 
                                        <input name="txtInvSubmitDt" style="text-align: left;" id="txtInvSubmitDt" value="<%=InvoiceSubmitDate%>" type="text" size="20"class="form-control"  maxlength="15" readonly="readonly" />
                                    </td> 

                                    
                                   <!-- <td width="05%">
                                        <a href="javascript:void(0)" onClick="return callCalender('txtVendorInvoiceDate');">
                                            <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />
                                    </td>-->

                                </tr>    
                                <tr><td></td></tr>

                                <tr>
                                    <td  class="text-right h5"><fmt:message key='Work Completion Detail'/> </td>
                                    <td colspan ="4"><input class="form-control" style="height:80px;" value="<%=workDetail%>" type="text" text-align="right" name="txtWorkDetail"  id="txtWorkDetail" maxlength="100" placeholder=<fmt:message key='"Specify Details here(Max 100 Chars)...."'/>..../>
                                    </td></tr>


                            </table>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr></tr><tr><br><br><br><br>
                                <td class="col-md-1">
                                </td>
                                <td class="col-md-2">

                                    <input type="button" value=<fmt:message key='Save'/> name="ButtonSave" id="ButtonSave" style="float: left;" onclick="saveButton()"
                                           class="btn  btn-success"/>


                                </td>

                                <td class="col-md-1">

                                    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" class="btn btn-danger" style="float: left;"><fmt:message key='Back'/></a>

                                </td>


                                </tr><br><br><br><br>
                            </table>
                            <br><br><br><br>

                            <%@include file="nav_emp_footer.jsp" %>

                            <!-- SCRIPTS -AT THE BOtTOM TO REDUCE THE LOAD TIME-->

                            <!-- BOOTSTRAP SCRIPTS -->
                            <script src="assets/js/bootstrap.min.js"></script>
                            <!-- CUSTOM SCRIPTS -->
                            <script src="assets/js/custom.js"></script>

                            </body>
                            </html>