

<%@page import="in.emp.legal.bean.LegalSummaryBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page import="in.emp.legal.bean.LegalInvoiceInputBean"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Date"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%
    String recordsVar = "No Records To Display !!!";
     String InvoiceFrmDate = "";
    String InvoiceToDate = "";
     VendorPrezData vendorPrezDataObj = new VendorPrezData();
     LegalSummaryBean legalSummaryBeanObj = new LegalSummaryBean();
    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        String uiAction = request.getParameter("uiActionName");
    }
    LinkedList legalSummaryList = new LinkedList();
    
 if (request.getSession().getAttribute(ApplicationConstants.AUTHORITY_LEGAL_SUMMARY_SESSION_DATA) != null) {
          vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.AUTHORITY_LEGAL_SUMMARY_SESSION_DATA);        
        
        legalSummaryList = (LinkedList) vendorPrezDataObj.getLegalSummaryList();
        legalSummaryBeanObj = (LegalSummaryBean) vendorPrezDataObj.getLegalSummaryBean();
        
        
        
    }
 if (legalSummaryBeanObj != null) {
     if ((legalSummaryBeanObj.getInvoiceFromDate() != null) && !(legalSummaryBeanObj.getInvoiceFromDate().equals("null")) && !(legalSummaryBeanObj.getInvoiceFromDate().equals(""))) {
            InvoiceFrmDate = ApplicationUtils.dateToString(legalSummaryBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
          }
          if ((legalSummaryBeanObj.getInvoiceToDate() != null) && !(legalSummaryBeanObj.getInvoiceToDate().equals("null")) && !(legalSummaryBeanObj.getInvoiceToDate().equals(""))) {
            InvoiceToDate = ApplicationUtils.dateToString(legalSummaryBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
          }  
 }

%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Vendor Payment Tracking System</title>
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css?v=<%=System.getProperty("VERSION")%>" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <link href="assets/css/font-awesome.css?v=<%=System.getProperty("VERSION")%>" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css?v=<%=System.getProperty("VERSION")%>" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='<%=ApplicationConstants.CSS_PATH%>google-fonts-OpenSans-css.css?v=<%=System.getProperty("VERSION")%>' rel='stylesheet' type='text/css' /><!--link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' /-->
        <!-- emp-->
        <link href="css/emp.css" rel="stylesheet" />
        <script src="assets/js/jquery-1.10.2.js?v=<%=System.getProperty("VERSION")%>"></script>
        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js?v=<%=System.getProperty("VERSION")%>"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js?v=<%=System.getProperty("VERSION")%>"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js?v=<%=System.getProperty("VERSION")%>"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js?v=<%=System.getProperty("VERSION")%>"></script>-->
        <script src="https://cdn.jsdelivr.net/gh/linways/table-to-excel@v1.0.4/dist/tableToExcel.js"></script>
        <jsp:include page="nav_jscss.jsp" />
<script type="text/javascript" language="JavaScript" src="<%=ApplicationConstants.JS_PATH%>erp_legal_auth_summary.js"></script>

        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"
            integrity="sha512-GsLlZN/3F2ErC5ifS5QtgpiJtWd43JWSuIgh7mbzZ8zBps+dvLusV+eNQATqgA/HdeKFVgA5v3S/cIrLF7QnIg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>
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

                        <table width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr>
                                <td class="module_tbl_tl" >&nbsp;</td>
                                <td class="tbl_hline_top">&nbsp;</td>
                                <td class="module_tbl_tr">&nbsp;</td>
                            </tr>


                            <tr>   <!-- Start of Network Search Results tr -->
                                <td>  <!-- Start of Network Search Results td -->
                                </td> <!-- End of Network Search Results td -->
                            </tr> <!-- End of Network Search Results tr -->
                        </table>  <!-- End of Network Search results table -->
                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                            <div class="row">                
                                <div class="col-lg-12 col-md-12">
                                    <div class="table-responsive" id="tabdiv">
                                                  <table class="table" border="0" cellspacing="0" cellpadding="2" id="table_content"> 
                                              
                                <tr>
                                    <td width="10%" class="text-right h5"><fmt:message key='Invoice From Date'/> </td>
                                    <td width="20%"> 
                                        <input name="txtInvoiceFromDate" readonly id="txtInvoiceFromDate" type="text" size="20" style="text-align: right;" class="datefield"   maxlength="15"  value="<%=InvoiceFrmDate%>" /></td>

                                   <td width="05%">
                                     </td>
                                    <td  width="10%" class="text-right h5"><fmt:message key='Invoice To Date'/> </td>
                                    <td width="20%"> 
                                        <input name="txtInvoiceToDate" id="txtInvoiceToDate" readonly type="text" size="20" class="datefield"  maxlength="15"  value="<%=InvoiceToDate%>" /></td>
<td width="05%">
                                     </td>
                                  
                                </tr>
                                                  <tr>
                                              <td class="col-md-1">
                                              </td>
                                               <td class="col-md-1">
                                              </td>
                                               <td class="col-md-2">
                                                    <div class="hor_nav_normal">
                                                        <input type="button" value="Get Summary" name="ButtonGetSummaryLegalStat" id="ButtonGetSummaryLegalStat" onclick="getLegalSummaryStat()"
                                                         class="btn  btn-success"/>
                                              </div>
                                              </td>
                                              
                                               <td class="col-md-2">
                                                 <div >
                                                         <input type="button" value=<fmt:message key='Reset'/> name="ButtonReset" id="ButtonReset" onclick="Reset()"
                                                         class="btn  btn-success"/> 
                                               
                                            </div>  
                                              </td>
                                             
                                          </tr></table>
                                        <%
                                            if (legalSummaryList != null) {
                                        %>   

                                        <div  id="tab">
                                        <div class="col-md-12 text-center"><h3>Summary of Legal Invoices</h3>  <div >&nbsp;</div></div>
                                        <input type="hidden" name="viewList" id="viewList" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_SUMMARY_LIST_DETAILS)%>"/>
                                        <input type="hidden" name="viewStat" id="viewStat" value="<%=ApplicationConstants.UIACTION_GET_LEGAL_AUTH_SUMMARY%>"/>
                                        <table class="table" id="tabtbl" >
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th> 
                                                    <th class="text-center"><fmt:message key='Zone'/></th> 
                                                    <th class="text-center"><fmt:message key='Circle'/></th> 
                                                    <th class="text-center"><fmt:message key='Division'/></th>  
                                                    <th class="text-center"><fmt:message key='Dept'/></th> 
                                                    <th class="text-center" id="hdr1" colspan="9"><fmt:message key='Status'/></th>                                                    
                                                </tr>
                                                <tr class="summary1">
                                                    <th colspan="6"></th>
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending at Department'/></th>                                                    
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending at Accounts'/></th>                                                                                                       
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending For Payment'/></th>
                                                    <th colspan="1"></th>
                                                    <th colspan="1" class="text-center" headers="hdr1"><fmt:message key='Paid Invoices'/></th>

                                                </tr>
                                                <tr class="summary2">
                                                    <th colspan="5"></th>
                                                    <th class="text-center"><fmt:message key='Submitted By Vendor'/></th>
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending less than 30 Days'/></th>     
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending more than 30 Days'/></th>                                                    
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending less than 30 Days'/></th>                                                                                                 
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending more than 30 Days'/></th>                                                    
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending less than 30 Days'/></th> 
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending more than 30 Days'/></th>                                                    


                                                    <th class="text-center"><fmt:message key='Total Pending'/></th> 
                                                    <th colspan="1"></th>
                                                </tr>
                                            </thead>
                                            <%

                                                int j = 0;
                                                int k = legalSummaryList.size();

                                                for (LegalInvoiceInputBean legalInvoiceInputBeanObj : (LinkedList<LegalInvoiceInputBean>) legalSummaryList) {
                                                    j++;
                                                    String Zone = "";
                                                    String Circle = "";
                                                    String Division = "";
                                                    String SubDivision = "";
                                                    String DeptName ="";
                                                    String pTech_more = "";
                                                    String pTech_less = "";
                                                    String pAcc_more = "";
                                                    String pAcc_less = "";
                                                    String pCash_more = "";
                                                    String pCash_less = "";
                                                    String paid = "";
                                                    String pTotal = "";
                                                    String vSubmit = "";
                                                    

                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getZone())) {
                                                        Zone = legalInvoiceInputBeanObj.getZone();
                                                    }

                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getCircle())) {
                                                        Circle = legalInvoiceInputBeanObj.getCircle();
                                                    }

                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getDivision())) {
                                                        Division = legalInvoiceInputBeanObj.getDivision();
                                                    }
                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getSubDivisionText())) {
                                                        SubDivision = legalInvoiceInputBeanObj.getSubDivisionText();
                                                    }
                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getDeptName())) {
                                                        DeptName = legalInvoiceInputBeanObj.getDeptName();
                                                    }
                                                        
                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getP_Tech_MORE_THAN30DAYS())) {
                                                        pTech_more = legalInvoiceInputBeanObj.getP_Tech_MORE_THAN30DAYS();
                                                    }

                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getP_TechLESSTHAN30DAYS())) {
                                                        pTech_less = legalInvoiceInputBeanObj.getP_TechLESSTHAN30DAYS();
                                                    }

                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getP_Cash_MORE_THAN30DAYS())) {
                                                        pCash_more = legalInvoiceInputBeanObj.getP_Cash_MORE_THAN30DAYS();
                                                    }
                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getP_Cash_LESS_THAN30DAYS())) {
                                                        pCash_less = legalInvoiceInputBeanObj.getP_Cash_LESS_THAN30DAYS();
                                                    }
                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getP_Acc_MORETHAN30DAYS())) {
                                                        pAcc_more = legalInvoiceInputBeanObj.getP_Acc_MORETHAN30DAYS();
                                                    }
                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getP_Acc_LESSTHAN30DAYS())) {
                                                        pAcc_less = legalInvoiceInputBeanObj.getP_Acc_LESSTHAN30DAYS();
                                                    }
                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getPaid())) {
                                                        paid = legalInvoiceInputBeanObj.getPaid();
                                                    }
                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getpTot())) {
                                                        pTotal = legalInvoiceInputBeanObj.getpTot();
                                                    }

                                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getvSubmit())) {
                                                        vSubmit = legalInvoiceInputBeanObj.getvSubmit();
                                                    }
                                            %>




                                            <tbody style="font-color:#ddd">
                                                <tr class="info" style="border-bottom: 1px solid #ddd;border-left: 1px solid #ddd;" >

                                                    <% if (j < k) { %>

                                                    <% if (!Zone.contains("TOTAL")) {%>

                                                    <td style=" border-left: 1px solid #ddd;"><%=j%></td>
                                                    <td class="text-center"  style="border-left: 1px solid #ddd; border-right: 1px solid #ddd;"><%=Zone%></td>
                                                    <% } else {%>
                                                    <th style="border-left: 1px solid #ddd; border-right: 1px solid #ddd;font-weight:normal"><%=j%></th>
                                                    <th class="text-center" ><%=Zone%></th>
                                                        <% }%>
                                                    <td class="text-center"><%=Circle%></td>
                                                    <td class="text-center"><%=Division%></td>
                                                    <td class="text-center"><%=DeptName%></td>
                                                    <% } else {%>
                                                    <td class="info1" ></td>
                                                    <td class="info1"></td>
                                                    <td class="info1"></td>
                                                    <td class="info1"></td>
                                                    <th class="text-center info1" style=""><fmt:message key='GRAND TOTAL'/></th>                                          
                                                        <% }  %>
                                                        <% if (!Zone.contains("TOTAL") & !(k == j)) {%>
                                                    <td align="center">  <% if (vSubmit!="0") {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('vSubmit', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=vSubmit%></a> <% } else{%><%=vSubmit%> <%}%></td>
                                                    <td align="center" class="less30"><% if (!pTech_less.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pTech_less','<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTech_less%></a><% } else{%><%=pTech_less%> <%}%></td>
                                                    <td align="center" class="more30"><% if (!pTech_more.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pTech_more', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTech_more%></a><% } else{%><%=pTech_more%> <%}%></td>
                                                    <td align="center"  class="less30"><% if (!pAcc_less.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pAcc_less', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pAcc_less%></a><% } else{%><%=pAcc_less%> <%}%></td>
                                                    <td align="center" class="more30"><% if (!pAcc_more.equals("0")) {%><a href="#nogo" style="color:#FF8C00;"onclick="viewSummaryListDetails('pAcc_more', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pAcc_more%></a><% } else{%><%=pAcc_more%> <%}%></td>
                                                    <td align="center"  class="less30"><% if (!pCash_less.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pCash_less', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pCash_less%></a><% } else{%><%=pCash_less%> <%}%></td>
                                                    <td align="center" class="more30"><% if (!pCash_more.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pCash_more', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pCash_more%></a><% } else{%><%=pCash_more%> <%}%></td>
                                                    <td align="center"  class="totpend"><% if (!pTotal.equals("0")) {%><a href="#nogo" style="color:#0000FF;" onclick="viewSummaryListDetails('pTotal', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTotal%></a><% } else{%><%=pTotal%> <%}%></td> 
                                                    <td align="center" class="paid"><% if (!paid.equals("0")) {%><a href="#nogo" style="color:#008000;" onclick="viewSummaryListDetails('paid', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=paid%></a><% } else{%><%=paid%> <%}%></td>  

                                                    <% } else if (k == j) {%>
                                                    <th class="text-center info1 "><% if (!vSubmit.equals("0")) {%><a href="#nogo"  onclick="viewSummaryListDetails('vSubmit','<%=Zone%>','<%=Circle%>','<%=Division%>','<%=SubDivision%>','<%=DeptName%>');"><%=vSubmit%></a> <% } else{%><%=vSubmit%> <%}%></th>
                                                    <th class="text-center info1" ><% if (!pTech_less.equals("0")) {%><a href="#nogo"  onclick="viewSummaryListDetails('pTech_less','<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTech_less%></a><% } else{%><%=pTech_less%> <%}%></th>
                                                    <th class="text-center info1" ><% if (!pTech_more.equals("0")) {%><a href="#nogo"  onclick="viewSummaryListDetails('pTech_more','<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTech_more%> </a><% } else{%><%=pTech_more%> <%}%></th>                                           
                                                    <th class="text-center info1"><% if (!pAcc_less.equals("0")) {%><a href="#nogo"  onclick="viewSummaryListDetails('pAcc_less','<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pAcc_less%></a><% } else{%><%=pAcc_less%> <%}%></th> 
                                                    <th class="text-center info1"><% if (!pAcc_more.equals("0")) {%><a href="#nogo" onclick="viewSummaryListDetails('pAcc_more', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pAcc_more%></a><% } else{%><%=pAcc_more%> <%}%></th> 
                                                    <th class="text-center info1"><% if (!pCash_less.equals("0")) {%><a href="#nogo"  onclick="viewSummaryListDetails('pCash_less','<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pCash_less%></a><% } else{%><%=pCash_less%> <%}%></th> 
                                                    <th class="text-center info1"><% if (!pCash_more.equals("0")) {%><a href="#nogo"  onclick="viewSummaryListDetails('pCash_more','<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pCash_more%></a><% } else{%><%=pCash_more%> <%}%></th> 
                                                    <th class="text-center info1"><% if (!pTotal.equals("0")) {%><a href="#nogo"  onclick="viewSummaryListDetails('pTotal','<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTotal%></a><% } else{%><%=pTotal%> <%}%></th>
                                                    <th class="text-center info1"><% if (!paid.equals("0")) {%><a href="#nogo"  onclick="viewSummaryListDetails('paid','<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=paid%></a><% } else{%><%=paid%> <%}%></th>  


                                                    <% } else {%>
                                                    <th class="text-center"><% if (!vSubmit.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('vSubmitTotal', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=vSubmit%> </a><% } else{%><%=vSubmit%> <%}%></th>
                                                    <th class="text-center less30" ><% if (!pTech_less.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pTech_less', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTech_less%></a><% } else{%><%=pTech_less%> <%}%></th>
                                                    <th class="text-center more30" ><% if (!pTech_more.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pTech_more', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTech_more%></a> <% } else{%><%=pTech_more%> <%}%></th>                                           
                                                    <th class="text-center less30"><% if (!pAcc_less.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pAcc_less', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pAcc_less%></a><% } else{%><%=pAcc_less%> <%}%></th> 
                                                    <th class="text-center more30"><% if (!pAcc_more.equals("0")) {%><a href="#nogo" style="color:#FF8C00;"onclick="viewSummaryListDetails('pAcc_more', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pAcc_more%></a><% } else{%><%=pAcc_more%> <%}%></th> 
                                                    <th class="text-center less30"><% if (!pCash_less.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pCash_less', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pCash_less%></a><% } else{%><%=pCash_less%> <%}%></th> 
                                                    <th class="text-center more30"><% if (!pCash_more.equals("0")) {%><a href="#nogo" style="color:#FF8C00;" onclick="viewSummaryListDetails('pCash_more', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pCash_more%></a><% } else{%><%=pCash_more%> <%}%></th> 
                                                    <th class="text-center totpend"><% if (!pTotal.equals("0")) {%><a href="#nogo" style="color:0000FF;" onclick="viewSummaryListDetails('pTotal', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=pTotal%></a><% } else{%><%=pTotal%> <%}%></th>
                                                    <th class="text-center paid"><% if (!paid.equals("0")) {%><a href="#nogo" style="color:#008000;" onclick="viewSummaryListDetails('paid', '<%=Zone%>', '<%=Circle%>', '<%=Division%>', '<%=SubDivision%>','<%=DeptName%>');"><%=paid%></a><% } else{%><%=paid%> <%}%></th>  
                                                        <% } %>

                                                </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                        </div>
                                    </div>

                                </div>
                            </div>                                                          

                            <%  } else {%>
                            <div class="row">                
                                <div class="col-lg-12 col-md-12">                        
                                    <div class="table-responsive" id="tab">
                                        <div class="col-md-12 text-center"><h3><fmt:message key='Summary of Invoices'/></h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th> 
                                                    <th class="text-center"><fmt:message key='Zone'/></th> 
                                                    <th class="text-center"><fmt:message key='Circle'/></th> 
                                                    <th class="text-center"><fmt:message key='Division'/></th>  
                                                    <th class="text-center"><fmt:message key='Submitted By Vendor'/></th>
                                                    <th class="text-center" id="hdr1" colspan="3"><fmt:message key='Pending with MSEDCL'/></th>                                                    
                                                    <th class="text-center" id="hdr1" colspan="9"><fmt:message key='Status'/></th>                                                    
                                                </tr>
                                                <tr class="success">
                                                    <th colspan="4"></th>
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending at Department'/></th>                                                    
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending at Accounts'/></th>                                                                                                       
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending For Payment'/></th>
                                                    <th colspan="1" class="text-center" headers="hdr1"><fmt:message key='Paid Invoices'/></th>
                                                    <th colspan="1"></th>
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

                            <tr>                                      
                                <td class="text-centre h5">

                                    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" class="btn btn-danger"><fmt:message key='Back'/></a>


                                </td>
                                <td class="">

                                    <a href="" class="btn btn-primary"  onclick="createPDF();" >Print</a>


                                </td><td>
                                    
                                    <button id="exportBtn1">Export To Excel</button><br><br>
                                </td></tr>




                            <input type = "hidden" name="lastValue" id="lastValue" value="" />
                            <input type = "hidden" name="lastValueDataType"   id="lastValueDataType" value="" />

                        </div> <!-- End of  content_container_sub div  -->

                        <hr/>

                    </div>

                    <!-- /. PAGE inner  -->
                </div>
                <!-- /. PAGE wrapper  -->
            </div>
            <!-- /.  wrapper  -->
        </div>


        <BR><BR>

                <%@include file="nav_emp_footer.jsp" %>



                <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
                <!-- JQUERY SCRIPTS -->
                
               
                <!-- BOOTSTRAP SCRIPTS -->
                <script src="assets/js/bootstrap.min.js?v=<%=System.getProperty("VERSION")%>"></script>
                <!-- CUSTOM SCRIPTS -->
                <script src="assets/js/custom.js?v=<%=System.getProperty("VERSION")%>"></script>
        
                <script>
                                        function createPDF() {
                                            var sTable = document.getElementById('tab').innerHTML;

                                            var style = "<style>";
                                            style = style + "table {width: 100%;font: 17px Calibri;}";
                                            style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
                                            style = style + "padding: 2px 3px;text-align: center;}";
                                            style = style + "</style>";

                                            // CREATE A WINDOW OBJECT.
                                            var win = window.open('', '', 'height=700,width=700');

                                            win.document.write('<html><head>');
                                            win.document.write('<title>Profile</title>');   // <title> FOR PDF HEADER.
                                            win.document.write(style);          // ADD STYLE INSIDE THE HEAD TAG.
                                            win.document.write('</head>');
                                            win.document.write('<body>');
                                            win.document.write(sTable);         // THE TABLE CONTENTS INSIDE THE BODY TAG.
                                            win.document.write('</body></html>');

                                            win.document.close(); 	// CLOSE THE CURRENT WINDOW.

                                            win.print();    // PRINT THE CONTENTS.
                                        }
                </script>



                </body>
                </html>


