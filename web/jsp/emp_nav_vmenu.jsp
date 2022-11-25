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

 <div id="mySidebar" class="sidebar-collapse">
<nav class="navbar-default navbar-side" role="navigation">
        <ul class="nav" id="main-menu">

            <li class="nav-item">
                        
                <div class="panel panel-default panel-title">
                    <div class="panel-heading">
                        <h5 class="panel-emptitle">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" title="All Notifications">
                                <b><fmt:message key='Home'/></b></a>
                        </h5>
                    </div>
                </div>
            </li>
            <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) { %>
            <li>
        
                <div class="panel panel-default panel-title">
                    <div class="panel-heading">
                        <h5 class="panel-emptitle">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseVendorInfo" class="collapsed" id="M1"
                               title="Your Submitted Invoices"><b><fmt:message key='Vendor'/></b></a>
                        </h5>
                    </div>
                    <div id="collapseVendorInfo" class="panel-collapse collapse" style="line-height: 2;">
                     <!--    <div class="panel-menu" id="M1I1">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)%>" title=""><fmt:message key='Submit Vendor Invoice'/></a>
                        </div>
                         <div class="panel-menu" id="M1I2">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_NONPO_VENDOR_INPUT_FORM)%>" title=""><fmt:message key='Submit Non PO Invoice'/></a>
                        </div>
                        <div class="panel-menu" id="M1I2">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_PO_LIST)%>" title="">Vendor Invoice Status</a>
                        </div>
                        <div class="panel-menu" id="M1I3">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_SUMMARY)%>" title=""><fmt:message key='Summary'/></a>
                        </div>
                        <div class="panel-menu" id="M1I4">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.REPORT_MSEDCL_VENDOR)%>" title=""><fmt:message key='Vendor Reports'/></a>
                        </div> -->
                        <div class="panel-menu" id="M1I5">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_LIST)%>" title=""><fmt:message key='Submit Legal Invoices'/></a>
                        </div>
<!--                        <div class="panel-menu" id="M1I6">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_VIEW_VENDOR_LEGAL_INPUT_LIST)%>" title=""><fmt:message key='View Legal Invoices'/></a>
                        </div>-->
                    </div>
                </div>
            </li>
           <% } %>
           
           <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) { %>
            <li>
                <div class="panel panel-default panel-title">
                    <div class="panel-heading">
                        <h5 class="panel-emptitle">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseEmployeeInfo" class="collapsed" id="M2"
                               title="Your Location's Invoices"><b><fmt:message key='Employee'/></b></a>
                        </h5>
                    </div>
                    <div id="collapseEmployeeInfo" class="panel-collapse collapse" style="line-height: 2;">
                         <div class="panel-menu" id="M2I1">

                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_EMP_INPUT_FORM)%>" title=""><fmt:message key='Submit Vendor Invoice'/></a>
                        </div>
                        <div class="panel-menu" id="M2I2">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_PO_LIST)%>" title=""><fmt:message key='Vendor Bills of Your Location'/></a>
                        </div>
                        <div class="panel-menu" id="M2I3">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_AUTH_SUMMARY)%>" title=""><fmt:message key='Summary'/></a>
                        </div>
                        
                        <div class="panel-menu" id="M2I4">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.REPORT_MSEDCL_EMP)%>" title=""><fmt:message key='MSEDCL MIS Reports'/></a>
                        </div> 
                         <div class="panel-menu" id="M1I5">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_FORM)%>" title="">Submit Vendor Legal Invoice</a>
                        </div>
                        <div class="panel-menu" id="M1I6">
                            <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_LEGAL_VENDOR_INVOICE)%>" title="">View Legal Invoices</a>
                        </div>
                    </div>           
                   
                        
                </div>
            </li>
            
           <% } %>
             <% 
                 String userId=null;
                 String regUrl=null;
                 String claimDtlUrl=null;
                 if (session.getAttribute(ApplicationConstants.USER_NAME_SESSION) != null) {
                 userId=((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));   
                 regUrl="http://mseb.mdindia.com:82/msebenrol.aspx?empid="+userId+"&type=vitaran";
                 claimDtlUrl="https://mdindiaonline.com/mseb.aspx?empid="+userId;
            }%>
        </ul>
           
</nav>
    </div> 
   