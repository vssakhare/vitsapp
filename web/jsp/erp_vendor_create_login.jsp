<%--
    Document   : emp_personal_info
    Created on : Nov 28, 2013, 4:06:44 PM
    Author     : sachin, prajakta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
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

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_vendor_create_login.js"></script>
         <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>emp.js"></script>
    </head>
    <body >

        <div id="wrapper">
            <%@ include file="nav_emp_header.jsp"%>
            <!-- /. NAV TOP  -->
          
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner" style="min-height:500px;">

                    <%@ include file="navJs.jsp"%>
                    
                     <input type="hidden" name="redir" id="redir" value="<%=ApplicationConstants.UIACTION_HOME_GET%>"/>
                   
                    <div class="content_container">
                        <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr> <!-- Start of Network Search Results tr -->
                                <td class="bg_white">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <div class="col-md-12"><h3><fmt:message key='New Vendor Login'/></h3></div>
                                        <div >&nbsp;</div>
                                    </div>
                                </td>
                            </tr>

                        </table>  <!-- End of Network Search results table -->
                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                          <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                  <th class="success" width="20%"><fmt:message key='Old Password'/>:- </th>
                                    <td>
                                        <input name="txtOldPwd" id="txtOldPwd" type="password" size="20" style="width: 20%" class="form-control text-left"  maxlength="15" />
                                    </td>
                              </tr>
                            <tr>
                                <th class="success" width="20%"><fmt:message key='New Password'/>:-    </th>
                           <td>
                               <input name="txtNewPwd" id="txtNewPwd" type="password" size="20" style="width: 25%" class="form-control text-left"  maxlength="25" /> 
                           </td>
                             </tr>
                              <tr>
                                  <th class="success" width="20%"><fmt:message key='Confirm Password'/>:- </th>
                               <td> 
                                   <input name="txtConfPwd" id="txtConfPwd" type="password" size="20" style="width: 25%" class="form-control text-left"  maxlength="25" />
                               </td>
                              </tr>
                                  <tr><td colspan="3" align="center" ><input type="submit" name="btnSubmit" id="btnSubmit" value='Submit' class="btn btn-success" onclick="submitButton()"> </td> </tr>
                          </table>
                           
                        </div> <!-- End of  content_container_sub div  -->
                        <hr/>
                    </div>
                    <!-- /. PAGE inner  -->
                </div>
                <!-- /. PAGE wrapper  -->
            </div>
            <!-- /.  wrapper  -->
        </div>

        <%@include file="nav_emp_footer.jsp" %>

        <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
        <!-- JQUERY SCRIPTS -->
        
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- CUSTOM SCRIPTS -->
        <script src="assets/js/custom.js"></script>

    </body>
</html>