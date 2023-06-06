
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page import="in.emp.home.notifications.bean.NotificationsBean"%>
<%@page import="in.emp.home.notifications.bean.NotificationsPrezData"%>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="java.util.*"%>
<%@page import="in.emp.legal.bean.LegalInvoiceInputBean"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <%
        
         VendorPrezData vendorPrezDataObj = new VendorPrezData();
         if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        String uiAction = request.getParameter("uiActionName");
    }
LinkedList legalSummaryList = new LinkedList();
         if (request.getSession().getAttribute(ApplicationConstants.AUTHORITY_LEGAL_SUMMARY_SESSION_DATA) != null) {
          vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.AUTHORITY_LEGAL_SUMMARY_SESSION_DATA);        
        
        legalSummaryList = (LinkedList) vendorPrezDataObj.getLegalSummaryList();
        
        
        
        
    } %>
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

        <!-- to support html5-->
        <script src="<%=ApplicationConstants.JS_PATH%>jquery-2.1.4.min.js"></script>
        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script src="<%=ApplicationConstants.JS_PATH%>legal_dashboard.js"></script>
         <script src="assets/js/custom.js"></script>
    </head>
           <script type="text/javascript">
          
       </script>
         <body>
        <c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session" />
</c:if>
        <div id="wrapper">
            <%@ include file="nav_emp_header.jsp"%>
             <%@ include file="navJs.jsp"%>
          
                      <%@ include file="emp_nav_vmenu.jsp"%>
                     
          
            <div id="page-wrapper" style="min-height:80%;" >
 <div  style="margin-bottom:20px;margin-top:20px" >
                       <table width="100%">  <tr> <td>
                          <div>
               <input type="radio" id="non-legal" name="brand" value="non-legal" onclick="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>">
                             <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" 
                                style="color: #000;font-size: 12"><strong>Vendor Invoice Dashboard</strong></a></div></td>
                                <td>
                   <div> 
                   <input type="radio" id="legal" name="brand" value="legal" checked onclick="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_LEGALDASHBOARD_GET)%>">
                             <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_LEGALDASHBOARD_GET)%>" 
                 data-wahfont="14"  style="color: #000;font-size: 12"><strong>Legal Vendor Invoice Dashboard</strong></a>
           </div></td></tr>
                       </table>
                   </div>
                <div id="page-inner ">
 
                   
              
                     <div class="content_container ">
                      
                        
                            <div><!--start of first chart of pending at cashier-->
                                <div class="animated fadeIn">
                                   
                                <div class="row">
  <div class="col-sm-6 col-lg-3">
        <div class="card text-black bg-danger">
            <div class="card-body pb-0">
                <div class="text-value"><center><fmt:message key='Total Pending Legal Invoices'/></center></div>
            </div>
        <div class="chart-wrapper mt-3 mx-3" style="height:20%;"><div class="chartjs-size-monitor" style="left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; visibility: hidden; position: absolute; z-index: -1; pointer-events: none;">
                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
            <canvas width="300" height="100" class="chart chartjs-render-monitor" id="card-chart4" style="display: block;"></canvas>
        </div>
       </div>
</div> 
      <div class="col-sm-6 col-lg-3">
        <div class="card text-white bg-warning">
            <div class="card-body pb-0">
                <div class="text-value"><center><fmt:message key='Invoices Pending at Department'/></center></div>
            </div>
        <div class="chart-wrapper mt-3" style="height:20%;">
            <div class="chartjs-size-monitor" style="left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; visibility: hidden; position: absolute; z-index: -1; pointer-events: none;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
            <canvas width="216" height="100" class="chart chartjs-render-monitor" id="card-chart3" style="display: block;"></canvas>
        </div>
        </div>
</div>

<div class="col-sm-6 col-lg-3">
        <div class="card text-white bg-info">
            <div class="card-body">
            <div class="text-value"><center><fmt:message key='Invoices Pending at Accounts'/></center></div>
        </div>
        <div class="chart-wrapper mt-3 mx-3" style="height:20%;">
            <div class="chartjs-size-monitor" style="left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; visibility: hidden; position: absolute; z-index: -1; pointer-events: none;">
                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div>
                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
            <canvas width="300" height="100" class="chart chartjs-render-monitor" id="card-chart2" style="display: block;"></canvas>
        </div>
        </div>
</div><!--end of second chart in first row-->
<div class="col-sm-6 col-lg-3">
<div class="card text-white bg-prim">
    <div class="card-body pb-0">
        <div class="text-value"><center><fmt:message key='Invoices Pending For Payment'/></center></div>
    </div>
    <div class="chart-wrapper mt-3 mx-3" style="height:20%;">
        <div class="chartjs-size-monitor" style="left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; visibility: hidden; position: absolute; z-index: -1; pointer-events: none;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
        <div id="chartdiv1">
            <canvas width="300" height="100" class="chart chartjs-render-monitor" id="card-chart1" style="display: block;"></canvas>
        </div>
    <div class="chartjs-tooltip top" id="card-chart1-tooltip" style="left: 124.55px; top: 93.27px; opacity: 0;"><div class="tooltip-header"><div class="tooltip-header-item">April</div></div><div class="tooltip-body"><div class="tooltip-body-item"><span class="tooltip-body-item-color" style="background-color: rgb(0, 165, 224);"></span><span class="tooltip-body-item-label">My First dataset</span><span class="tooltip-body-item-value">84</span></div></div></div></div>
</div>
</div><!--end of first chart in first row-->



                                    
                                 </div> 
       
                               </div>
                            </div>
                            <br>
                            <div class="row" > <!-- start of total pending chart-->
                            <div class="col-lg-12 col-sm-12" style="padding:0">
                         
         
                    <table style="width: 100%;">
                        <tr>
                            <td>
                               
                                       
<%
  
                                
                               
    if(legalSummaryList!=null)
    {
                                        int j = 0;int i=0;
                                     int k = legalSummaryList.size();
                                      int max = 0; 
                                    for (LegalInvoiceInputBean legalInvoiceInputBeanObj : (LinkedList<LegalInvoiceInputBean>) legalSummaryList) {
                                      // if(legalInvoiceInputBeanObj.getFeeType()==null) {
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
                                    String pTotal = "";
                                    String vSubmit = "";
                                    String firstSubString="";
                                    String paid="";
                                    
                                    
                                     if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getZone())) {
                                       Zone = legalInvoiceInputBeanObj.getZone();
                                       String[] split = Zone.split("ZONE TOTAL");
                                        firstSubString = split[0];
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
                                     
                                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getUNPAID_SUBMITTED())) {
                                       vSubmit = legalInvoiceInputBeanObj.getUNPAID_SUBMITTED();
                                       if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getZone())) {
                                         Zone = legalInvoiceInputBeanObj.getZone();
                                     if (Zone.contains("TOTAL")) {
                                       int vSubmitmax=Integer.parseInt(vSubmit);  
                                      if (max < vSubmitmax) 
                                       { 
                                           max = vSubmitmax; 
                                       } 
                                     }
                                     }
                                     }
                                    
                            
                                    
%>
<% if (j <= k) {%>
<% if (Zone.contains("TOTAL")) {%>    
<input type="hidden" name="pTotal<%=j%>" id="pTotal<%=j%>" value="<%=pTotal%>">
    <input type="hidden" name="zone<%=j%>" id="zone<%=j%>" value="<%=firstSubString%>">
    <input type="hidden" name="k" id="k" value="<%=k%>">
 <input type="hidden" name="pAcc_more<%=j%>" id="pAcc_more<%=j%>" value="<%=pAcc_more%>">
    <input type="hidden" name="pAcc_less<%=j%>" id="pAcc_less<%=j%>" value="<%=pAcc_less%>">   
 <input type="hidden" name="pCash_more<%=j%>" id="pCash_more<%=j%>" value="<%=pCash_more%>">
     <input type="hidden" name="pCash_less<%=j%>" id="pCash_less<%=j%>" value="<%=pCash_less%>">
     <input type="hidden" name="pTech_more<%=j%>" id="pTech_more<%=j%>" value="<%=pTech_more%>">
   <input type="hidden" name="pTech_less<%=j%>" id="pTech_less<%=j%>" value="<%=pTech_less%>">
        <input type="hidden" name="paid<%=j%>" id="paid<%=j%>" value="<%=paid%>">
  <input type="hidden" name="vSubmit<%=j%>" id="vSubmit<%=j%>" value="<%=vSubmit%>">

<% }%> 
  <% }%>   
   <% /*}else{
                    
                    i++;
                    String ZonefeeType = "";
                    String vSubmitFeeType = "";
                    String feeType = "";
                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getZone())) {
                        ZonefeeType = legalInvoiceInputBeanObj.getZone();

                    }
                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getV_submit_FeeType())) {
                        vSubmitFeeType = legalInvoiceInputBeanObj.getV_submit_FeeType();
                    }

                    if (!ApplicationUtils.isBlank(legalInvoiceInputBeanObj.getFeeType())) {
                        feeType = legalInvoiceInputBeanObj.getFeeType();
                    }*/
%>
   
 <!--<input type="hidden" name="vSubmitFeeType<%//=i%>" id="vSubmitFeeType<//%=i%>" value="<%//=vSubmitFeeType%>">
  <input type="hidden" name="feeType<%=i%>" id="feeType<%=i%>" value="<%//=feeType%>">
  <input type="hidden" name="zoneFeeType<%=i%>" id="zoneFeeType<%=i%>" value="<%//=ZonefeeType%>">-->


<% //}%>
  <% }%> 
    <input type="hidden" name="vSubmitmax" id="vSubmitmax" value="<%=max%>">  
    <input type="hidden" name="j" id="j" value="<%=j%>">  
    <input type="hidden" name="i" id="i" value="<%=i%>">  
  <%}%>

                                
                            </td>
                                
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div id="chartdiv" style="width: 82vw; height: 50vh;margin: auto;">
                                     <div class="text-value"><center><fmt:message key='Zone Wise Total Pending Invoices'/></center></div>
                                    <div class="chart whitechart chartjs-render-monitor" id="columnChart">
                                        
       <canvas class="chart whitechart" id="test_chart_area" style=" width: 100%; height: 20%;" width="100%" height="30%"></canvas>
                                    </div></div>
                            </td>
                        </tr>  
                    </table>
                                      
                         </div>             
                          
</div>  <!-- end of total pending invoices chart-->  
  

 </div> <!-- end of page inner-->  
 
</div>  <!-- end of page inner-->
        </div>  <!-- end of page wrapper-->
 <!--else -->
        </div>  <!-- end of wrapper-->
                                                <br><br>
        <%@include file="nav_emp_footer.jsp" %>
        <script src='js/Chart.bundle.js'></script>
        <script src='js/utils.js'></script>
     <script src="<%=ApplicationConstants.JS_PATH%>canvasjs.min.js"></script>
  
<script src="<%=ApplicationConstants.JS_PATH%>jquery.canvasjs.min.js"></script>
       <script src="<%=ApplicationConstants.JS_PATH%>Chart.js"></script>
       <script src="<%=ApplicationConstants.JS_PATH%>Chart.min.js"></script>

        <!-- SCRIPTS -AT THE BOTTOM TO REDUCE THE LOAD TIME-->
        <!-- JQUERY SCRIPTS -->
        <script src="assets/js/jquery-1.10.2.js"></script>
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js"></script>
     
    </body>
</taglib>
</taglib>
</html>