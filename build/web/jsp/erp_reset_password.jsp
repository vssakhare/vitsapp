<%-- 
    Document   : emp_login_page
    Created on : 25 Apr, 2016, 4:30:08 PM
    Author     : pooja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>VendorPaymentTrackingSystem</title>
       
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css" rel="stylesheet" />
         <!-- EMP STYLES-->
        <link href="css/emp.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />

        <!-- JQUERY SCRIPTS -->
        
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js"></script>
        <!-- jReject CSS -->
        <link href="css/jquery.reject.css" rel="stylesheet" />
        <!-- jReject SCRIPTS -->
        <script src="<%=ApplicationConstants.JS_PATH%>jquery.reject.js"></script>
        <script src="<%=ApplicationConstants.JS_PATH%>erp_reset_password.js"></script>
      <%-- Set language based on user's choice --%>

    </head>
  
    <body>
    <div id="wrapper">
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
         
                            <div class="navbar-ehead col-sm-8 col-padding-0 col-margin-0">
                                <h5 align="center" style="font-size: 28px"><fmt:message key='Maharashtra State Electricity Distribution Co. Ltd.'/></h5>
                                <h5 align="center" style="font-size: 20px"><fmt:message key='Vendor Invoice Tracking Portal'/></h5>
                         
<%-- Language test --%>

   
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            

            <!-- /. NAV TOP  -->
            <!-- /. NAV SIDE  -->
           <div id="page-inner" >

                <%@ include file="navJs.jsp"%>
                <div>&nbsp;</div>

                <div align="center" class="content_container_sub" >  
                    <br>
                        <div class="panel-heading" style="margin-top: 70px;">
                            <h4 style="text-align: center; color: black; font-weight: bold; font-size: x-large;"><fmt:message key='Vendor Login-Reset Password'/></h4>
                            </div>
                                        <!--Start of  content_container_sub div  -->
 
  <div>&nbsp;</div>
                     <% System.out.println("-----Reset Password Message : "+ session.getAttribute(ApplicationConstants.RESET_PASSWORD_SESSION));
                        String msg=null;
                        if(!ApplicationUtils.isBlank(session.getAttribute(ApplicationConstants.RESET_PASSWORD_SESSION))){
                            msg = (String) session.getAttribute(ApplicationConstants.RESET_PASSWORD_SESSION);
                        }
                        
                     %>
        
                    <input type="hidden" id="<%=ApplicationConstants.UIACTION_NAME%>" name="<%=ApplicationConstants.UIACTION_NAME%>" value="getlogin" />

                    <form>
                    <table class="panel panel-info" align="center" border="0" cellspacing="0" cellpadding="1" id="table_content" class="login_screen" style="width: 27%"> <!-- Start of   table_content table -->
                        <tbody style="border: 1px solid #bce8f1;">  <% if(!ApplicationUtils.isBlank(msg)) { %>
                          <tr>
                              <td colspan="2">
                                  <input style="text-align: center" class="form-control-err-msg" name="txtMsg" id="txtMsg" value="<%=msg%>" type="text" readonly="true"  />
                              </td>
                        </tr>
                       <% } %>
                       
                            
                            <tr> 
                                <td class="Label_login" style="padding-top: 30px;"><fmt:message key='OTP'/></td>
                              <td class="Label_login" style="padding-top: 30px;"><input style="text-align: center; width:90%; float:left;" name="reset" class="form-control" id="reset" type="text" required></input>
                                                    </td>
                              
                             
                        </tr>
                              <tr><td></td></tr> 
                          <tr >
                             <td class="Label_login" style="padding-top: 30px;padding-bottom: 50px;"> <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_LOGIN_GET)%>" class="btn btn-danger"><fmt:message key='Back'/></a></td>
                            <td class="Label_login" style="padding-top: 30px;padding-bottom: 50px;"><input type="button" value=<fmt:message key='Reset'/> name="resetOtp" id="resetOtp" class="btn btn-success"/></td>
                          </tr></tbody>
                    </table>    <!-- End of  table_content table  -->
                    </form>
                </div> <!-- End of  content_container_sub div  -->
            </div>  <!-- /. PAGE inner  -->
        </div> 
                        <!-- /.  wrapper  -->
                        
            
        <%@include file="nav_emp_footer.jsp" %>

        <!-- SCRIPTS -AT THE BOTTOM TO REDUCE THE LOAD TIME-->

    </body>
</html>
 