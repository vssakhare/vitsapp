<%-- 
    Document   : nav_emp_header
    Created on : Apr 11, 2016, 12:31:39 PM
    Author     : Ithelpdesk
--%>

<%@page import="in.emp.common.ApplicationConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="adjust-nav">
        <div class="container-fluid">
            <div class="row">

                <div class="navbar-header col-sm-2">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-ebrand" href="#">                 
                        <img src="images/MAHAVITARAN_LOGO.png" class="mahavitranlogo"/>
                    </a>
                </div>    
                <div class="navbar-ehead col-sm-7">
                    <h5 align="center" style="font-size: 28px"><fmt:message key='Vendor Invoice Tracking System'/> </h5>
                   
                </div>
                <div class="logout-espn col-sm-3" <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) { %> style="padding:1.6%;"<% } %> >
                    <% System.out.println("-----USER TYPE in header : "+ session.getAttribute(ApplicationConstants.USER_TYPE_SESSION)); %>
                    <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) { %>                    
                    <p><fmt:message key='Logged in User Id'/>: <b><%=session.getAttribute(ApplicationConstants.USER_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.USER_NAME_SESSION)%></b></p>
                    <p><fmt:message key='Name'/>: <%=session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION)%></p>
                    <% } else if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) { %>                    
                    <p><fmt:message key='Logged in User Id'/>: <b><%=session.getAttribute(ApplicationConstants.USER_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.USER_NAME_SESSION)%></b></p>
                    <p><fmt:message key='Name'/>: <%=session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION)%></p>
                        <p><fmt:message key='Designation'/>: <%=session.getAttribute(ApplicationConstants.DESIGNATION_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.DESIGNATION_SESSION)%></p>
                        <p><fmt:message key='Office Code'/> : <%=session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION)%></p>
                       <!-- <p><fmt:message key='Office Name'/>: <%=session.getAttribute(ApplicationConstants.OFFICE_NAME_SESSION) == null ? "" : session.getAttribute(ApplicationConstants.OFFICE_NAME_SESSION)%></p> -->
                    <% } %>
                   
                    <p><a href="/legalInvoiceTracking/erp?uiActionName=postlogin" class="btn-default"><fmt:message key='Logout'/></a></p>
                     <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) { %>
                     <p><fmt:message key='Download Manual'/> <a href="/VendorBillTracking/pdfFiles/Vendorreportmanual.pdf" class="btn-default"><fmt:message key='Click here'/>!!!</a></p>
                    <% } %>
                      <% if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) { %>
                      <p><fmt:message key='Download Manual'/> <a href="/VendorBillTracking/pdfFiles/EmployeeReportManual.pdf" class="btn-default"><fmt:message key='Click here'/>!!!</a></p>
                    <% } %>
                    

                </div>                           
            </div>
        </div>
    </div>
</div>

     