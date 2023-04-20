<%-- 
    Document   : emp_nav_vmenu
    Created on : 31 Aug, 2017, 4:22:51 PM
    Author     : Prajakta
--%>
<%@page import="java.util.Calendar"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.emp.security.bean.SecUserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
   <link href="assets/css/sideMenu.css" rel="stylesheet" />
 <script src="assets/js/sideMenu.js"></script>
 <div id="mySidebar" class="sidebar-collapse">
     <div id="menu">
		<div class="hamburger">
			<div class="line"></div>
			<div class="line"></div>
			<div class="line"></div>
		</div>
		<div class="menu-inner">
			
			<ul_menu>
				<li id="menu1" style="align-items: center;  justify-content: center"> <a  href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" title="All Notifications">
                                <img src="images/home.png" width="16" height="16"> <b>Home</b> </a></li>
                                 
                                
                                   <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) { %>
				<li   > 
                                    <div>
                                        <div id="menu1">
                                    <a  id="M1" data-toggle="collapse" data-parent="#accordion" href="#collapseVendorInfo" class="collapsed"
                                       title="Your Submitted Invoices"      ><img src="images/vendor.png" width="18" height="16" style=""><b>Vendor</b>
                                    </a></div>
                                      <div id="collapseVendorInfo" class="panel-collapse collapse subMenu" style="line-height: 4;">
                   <!--   <% /*if(!session.getAttribute(ApplicationConstants.IS_LEGAL_USER).equals("Y")) {*/ %> -->
                        <div class="panel-menu" id="M1I1">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)%>" title="" ><img src="images/bullet.png" width="16" height="16" style="">Submit Vendor Invoice</a>
                        </div>
                         <div class="panel-menu" id="M1I2">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_NONPO_VENDOR_INPUT_FORM)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">Submit Non PO Invoice</a>
                        </div>
                        <div class="panel-menu" id="M1I2">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_PO_LIST)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">Vendor Invoice Status</a>
                        </div>
                        <div class="panel-menu" id="M1I3">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_SUMMARY)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">Summary</a>
                        </div>
                        <div class="panel-menu" id="M1I4">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.REPORT_MSEDCL_VENDOR)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">Vendor Reports</a>
                        </div> <!-- <% /*} */%>-->
                      <!--  <% /*if(session.getAttribute(ApplicationConstants.IS_LEGAL_USER).equals("Y")) {*/ %> 
                        <div class="panel-menu" id="M1I5">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_LIST)%>" title=""><img src="images/invoice.png" width="16" height="16"><fmt:message key='Submit Legal Invoices'/></a>
                        </div>
<!--                        <div class="panel-menu" id="M1I6">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_VIEW_VENDOR_LEGAL_INPUT_LIST)%>" title=""><fmt:message key='View Legal Invoices'/></a>
                        </div>-->
                     <!-- <% /*} */%>--></div>
                                    
                                    </div>
                                    </li>
                                      <% } %>
                                               <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) { %>
            <li>
                <div >
                    <div id="menu1">
                      
                            <a  data-toggle="collapse" data-parent="#accordion" href="#collapseEmployeeInfo" class="collapsed" id="M2"
                               title="Your Location's Invoices"><img src="images/vendor.png" width="18" height="16" style=""><b>Employee</b></a>
                      
                    </div>
                    <div id="collapseEmployeeInfo" class="panel-collapse collapse subMenu" style="line-height: 2; font-size:12px">
                         <div class="panel-menu" id="M2I1">

                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_EMP_INPUT_FORM)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">Submit Vendor Invoice</a>
                        </div>
                        <div class="panel-menu" id="M2I2">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_PO_LIST)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">Vendor Bills of Your Location</a>
                        </div>
                        <div class="panel-menu" id="M2I3">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_SUMMARY)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">Summary</a>
                        </div>
                        
                        <div class="panel-menu" id="M2I4">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.REPORT_MSEDCL_EMP)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">MSEDCL MIS Reports</a>
                        </div> 
                         <div class="panel-menu" id="M1I5">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_FORM)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">Submit Vendor Legal Invoice</a>
                        </div>
                        <div class="panel-menu" id="M1I6">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_LEGAL_VENDOR_INVOICE)%>" title=""><img src="images/bullet.png" width="16" height="16" style="">View Legal Invoices</a>
                        </div>
                    </div>           
                   
                        
                </div>
            </li>
            
           <% } %>
                                      <li id="menu2" style="align-items: center;  justify-content: center"> 
                                          
                                        <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) { %>  
                                          <a  href="/VendorBillTracking/pdfFiles/Vendorreportmanual.pdf" title="Downloads">
                                <img src="images/download.png" width="16" height="16"> <b>Download Manual</b> </a>
                                      <% } %>
                                    <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) { %>   
                                 <a  href="/VendorBillTracking/pdfFiles/EmployeeReportManual.pdf" title="Downloads">
                                <img src="images/download.png" width="16" height="16"> <b>Download Manual</b> </a>     
                                      <% } %>
                                      
                                      </li>
				
			</ul_menu>
		</div>
  
  
		
		<svg version="1.1" id="blob"xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
			<path id="blob-path" d="M60,500H0V0h60c0,0,20,172,20,250S60,900,60,500z"/>
		</svg>
	</div>


<h2> </h2>

    </div> 
   