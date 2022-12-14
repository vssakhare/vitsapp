<%--
    Document   : emp_biometricAttend
    Created on : May 4, 2016, 1:14:20 PM
    Author     : Ithelpdesk
--%>

<%@page import="in.emp.vendor.bean.VendorBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page import="in.emp.home.biometric.bean.BiometricAttendADataBean"%>
<%@page import="in.emp.home.biometric.bean.BiometricAttendDataBean"%>
<%@page import="in.emp.home.biometric.bean.BiometricAttendDataReportBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Date"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%
   
    LinkedList summaryList = new LinkedList();
    String uiAction = "";
    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }
    String recordsVar = "No Records To Display !!!";
    
    VendorPrezData vendorPrezDataObj = new VendorPrezData();
   

    if (request.getSession().getAttribute(ApplicationConstants.AUTHORITY_SUMMARY_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.AUTHORITY_SUMMARY_SESSION_DATA);        
        summaryList = (LinkedList) vendorPrezDataObj.getSummaryList();
            
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
        <link href="assets/css/font-awesome.css?v=<%=System.getProperty("VERSION")%>" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css?v=<%=System.getProperty("VERSION")%>" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='<%=ApplicationConstants.CSS_PATH%>google-fonts-OpenSans-css.css?v=<%=System.getProperty("VERSION")%>' rel='stylesheet' type='text/css' /><!--link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' /-->
           <!-- emp-->
        <link href="css/emp.css" rel="stylesheet" />
        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js?v=<%=System.getProperty("VERSION")%>"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js?v=<%=System.getProperty("VERSION")%>"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js?v=<%=System.getProperty("VERSION")%>"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js?v=<%=System.getProperty("VERSION")%>"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>emp_biometricAttend.js?v=<%=System.getProperty("VERSION")%>"></script>
        
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
                            <tr> <!-- Start of Network Search Results tr -->

                                <td class="bg_white">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <br><br><div class="col-md-12"><h3><fmt:message key='Summary'/></h3></div>
                                        <div >&nbsp;</div>

                                    </div>
                                </td>
                            </tr>

                            <tr>   <!-- Start of Network Search Results tr -->
                                <td>  <!-- Start of Network Search Results td -->
                                </td> <!-- End of Network Search Results td -->
                            </tr> <!-- End of Network Search Results tr -->
                        </table>  <!-- End of Network Search results table -->
                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                            <div class="row">                
                               <div class="col-lg-12 col-md-12">
                                  <div class="table-responsive">
                                       <%
                                           if (summaryList != null) {
                                       %>   
                                  
                                        
                                         <div class="col-md-12 text-center"><h3><fmt:message key='Summary of Invoices'/></h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th> 
                                                    <th class="text-center"><fmt:message key='Zone'/></th> 
                                                    <th class="text-center"><fmt:message key='Circle'/></th> 
                                                    <th class="text-center"><fmt:message key='Division'/></th>  
                                                  
                                                    <th class="text-center" id="hdr1" colspan="9"><fmt:message key='Status'/></th>                                                    
                                               </tr>
                                                <tr class="success">
                                                    <th colspan="5"></th>
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending at Technical'/></th>                                                    
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending at Accounts'/></th>                                                                                                       
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending For Payment'/></th>
                                                  <th colspan="1" class="text-center" headers="hdr1"><fmt:message key='Paid Invoices'/></th>
                                                   <th colspan="1"></th>
                                                </tr>
                                                 <tr class="success">
                                                    <th colspan="4"></th>
                                                      <th class="text-center"><fmt:message key='Submitted By Vendor'/></th>
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending more than 30 Days'/></th>                                                    
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending less than 30 Days'/></th>                                                                                                       
                                                     <th class="text-center" headers="hdr1"><fmt:message key='Pending more than 30 Days'/></th>                                                    
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending less than 30 Days'/></th>  
                                                     <th class="text-center" headers="hdr1"><fmt:message key='Pending more than 30 Days'/></th>                                                    
                                                    <th class="text-center" headers="hdr1"><fmt:message key='Pending less than 30 Days'/></th> 
                                                        <th colspan="1"></th>
                                                    <th class="text-center"><fmt:message key='Total'/></th> 
                                                </tr>
                                            </thead>
                                         <%
                                
                                     int j = 0;
                                     int k = summaryList.size();
                                    
                                    for (VendorBean vendorBean : (LinkedList<VendorBean>) summaryList) {
                                                    j++;
                                    String Zone = "";
                                    String Circle ="";
                                    String Division = "";
                                    String pTech_more = "";
                                    String pTech_less = "";
                                    String pAcc_more = "";
                                    String pAcc_less = "";
                                    String pCash_more = "";
                                    String pCash_less = "";
                                    String paid ="";
                                    String pTotal = "";
                                    String vSubmit = "";
                                    
                                    
                                     if (!ApplicationUtils.isBlank(vendorBean.getZone())) {
                                       Zone = vendorBean.getZone();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorBean.getCircle())) {
                                       Circle = vendorBean.getCircle();
                                     }
                                     
                                     if (!ApplicationUtils.isBlank(vendorBean.getDivision())) {
                                       Division = vendorBean.getDivision();
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(vendorBean.getP_Tech_MORE_THAN30DAYS())) {
                                       pTech_more = vendorBean.getP_Tech_MORE_THAN30DAYS();
                                     }
                                      
                                      if (!ApplicationUtils.isBlank(vendorBean.getP_TechLESSTHAN30DAYS())) {
                                       pTech_less = vendorBean.getP_TechLESSTHAN30DAYS();
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(vendorBean.getP_Cash_MORE_THAN30DAYS())) {
                                       pCash_more = vendorBean.getP_Cash_MORE_THAN30DAYS();
                                     }
                                       if (!ApplicationUtils.isBlank(vendorBean.getP_Cash_LESS_THAN30DAYS())) {
                                       pCash_less = vendorBean.getP_Cash_LESS_THAN30DAYS();
                                     }
                                        if (!ApplicationUtils.isBlank(vendorBean.getP_Acc_MORETHAN30DAYS())) {
                                       pAcc_more = vendorBean.getP_Acc_MORETHAN30DAYS();
                                     }
                                         if (!ApplicationUtils.isBlank(vendorBean.getP_Acc_LESSTHAN30DAYS())) {
                                       pAcc_less = vendorBean.getP_Acc_LESSTHAN30DAYS();
                                     }
                                         if (!ApplicationUtils.isBlank(vendorBean.getPaid())) {
                                       paid = vendorBean.getPaid();
                                     }
                                       if (!ApplicationUtils.isBlank(vendorBean.getpTot())) {
                                       pTotal = vendorBean.getpTot();
                                     }
                                       
                                    if (!ApplicationUtils.isBlank(vendorBean.getvSubmit())) {
                                       vSubmit = vendorBean.getvSubmit();
                                     }
                            %>

                                       

                                          
                                            <tbody>
                                            <tr class="info" >
                                           
                                            <% if(j<k){ %>
                                               
                                                  <% if(!Zone.contains("TOTAL")) { %>
                                                 
                                                  <td><%=j%></td>
                                                       <td class="text-center"><%=Zone%></td>
                                                  <% } else { %>
                                                  <th><%=j%></th>
                                                       <th class="text-center"><%=Zone%></th>
                                                  <% } %>
                                            <td class="text-center"><%=Circle%></td>
                                            <td class="text-center"><%=Division%></td>
                                             <% } else {%>
                                             <td></td>
                                              <td></td>
                                              <td></td>
                                             <th class="text-center"><fmt:message key='GRAND TOTAL'/></th>                                          
                                           <% }  %>
                                           <% if(!Zone.contains("TOTAL") & !(k==j) ) { %>
                                            <td align="center"><%=vSubmit%></td>
                                            <td align="center"><%=pTech_more%></td>
                                             <td align="center"><%=pTech_less%></td>
                                            <td align="center"><%=pAcc_more%></td>
                                             <td align="center"><%=pAcc_less%></td>
                                            <td align="center"><%=pCash_more%></td>
                                                    <td align="center"><%=pCash_less%></td>
                                                    <td align="center"><%=paid%></td>  
                                            <td align="center"><%=pTotal%></td>  
                                             <% } else { %>
                                            <th class="text-center"><%=vSubmit%></th>
                                            <th class="text-center"><%=pTech_more%></th>                                           
                                            <th class="text-center"><%=pTech_less%></th>
                                            <th class="text-center"><%=pAcc_more%></th> 
                                            <th class="text-center"><%=pAcc_less%></th> 
                                            <th class="text-center"><%=pCash_more%></th> 
                                            <th class="text-center"><%=pCash_less%></th> 
                                               <th class="text-center"><%=paid%></th>  
                                            <th class="text-center"><%=pTotal%></th>
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
                                                    <th colspan="2" class="text-center" headers="hdr1"><fmt:message key='Invoices Pending at Technical'/></th>                                                    
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
                              </tr>
                           
                            
                            

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
        <script src="assets/js/jquery-1.10.2.js?v=<%=System.getProperty("VERSION")%>"></script>
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js?v=<%=System.getProperty("VERSION")%>"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js?v=<%=System.getProperty("VERSION")%>"></script>

    </body>
</html>


