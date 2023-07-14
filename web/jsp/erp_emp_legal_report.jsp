<%-- 
    Document   : erp_emp_legal_report
    Created on : May 26, 2023, 10:53:28 AM
    Author     : Pooja Jadhav
--%>



<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="in.emp.vendor.bean.POBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        String uiAction = request.getParameter("uiActionName");
    }
 String UserType = "";
if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
        UserType = "Vendor";
    } else if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
        UserType = "Emp";
    }   

//    ylist = ApplicationUtils.getFiveYYYY();
//    mlist = ApplicationUtils.getAllMM();
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
        <!-- AUTOCOMPLETE-->
        <link href="css/autocomplete.css" rel="stylesheet" />
        <!-- AUTOCOMPLETE-->
        <link href="css/emp.css" rel="stylesheet" />
        <script src="assets/js/jquery-1.10.2.js?v=<%=System.getProperty("VERSION")%>"></script>
        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js?v=<%=System.getProperty("VERSION")%>"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js?v=<%=System.getProperty("VERSION")%>"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js?v=<%=System.getProperty("VERSION")%>"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js?v=<%=System.getProperty("VERSION")%>"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type="text/javascript" language="JavaScript" src="<%=ApplicationConstants.JS_PATH%>erp_emp_legal_report.js?v=<%=System.getProperty("VERSION")%>"></script>
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
                    <input type="hidden" name="<%=ApplicationConstants.UIACTION_NAME%>" id="<%=ApplicationConstants.UIACTION_NAME%>" value="<%=ApplicationConstants.REPORT_MSEDCL_EMP%>"/>
                             <input type="hidden"  id="userType" name="userType" value = "<%=UserType%>"/> 
                    <div >&nbsp;</div> <br><br><div class="col-md-12" align="center"><h3><fmt:message key='Employee Reports'/></h3></div>
                    <div >&nbsp;</div>
                      <div class="content_container">
                                <div class="row">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td class="tbl_vline_left">&nbsp;</td>
                                            <td class="bg_white">
                                                <div class="table-responsive" align="center" >
                                                    <div class="col-md-12"><h3><fmt:message key='Select Report Name and Click Download'/> </h3></div>
                                                    
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <div class="col-lg-12 col-md-12">
                                      <div class="table-responsive">
                                        <center>
                                         <table class="table">
                                             <tr>
                                                 <td></td><td></td>
                                                 <th width="15%" class="text text-center"><fmt:message key='Report Name'/></th>
                                                            <td width="25%">
                                                                <select name="txtReportName" id="txtReportName" class="form-control" onchange="changeFields();">
                                                                    <option value="<%=ApplicationConstants.REPORT_EMPLOYEE_LEGAL_SUBMITTED%>">Invoices Submitted Report</option>  
                                                                    <option value="<%=ApplicationConstants.REPORT_EMPLOYEE_LEGAL_RETURNED%>">Invoices Returned Report</option>
                                                                  <option value="<%=ApplicationConstants.REPORT_EMPLOYEE_ALL_LEGAL_INVOICES%>">All Legal Invoices Report</option>
                                                                </select>
                                                            </td>
                                                 <td></td><td></td>
                                              </tr>
                                         </table>
                                                            
                                   <table class="table">  
                                    <tr>
                                         <td class="text-right h5"><fmt:message key='Vendor'/></td>
                                         <td id="myDropdownTwo">
                                            <div class="autocomplete" style="width:300px;">
                                                <input type="text" name="txtVendorNumber" id="txtVendorNumber" style="width: 100%" value=ALL title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> class="form-control" />
                                              </div>        
                                          </td> 
                                          <td class="text-right h5" colspan="2"><fmt:message key='Location'/></td>
                                           <td id="myDropdownThree">
                                            <div class="autocomplete" style="width:300px;">
                                                <input type="text" name="txtLocation" id="txtLocation" style="width: 100%" value=ALL title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> class="form-control" />
                                              </div>        
                                          </td>   
                                          
                                          </tr>
                                          <tr >
                                              
                                              <td class="text-right h5" width="20%"><fmt:message key='From Date'/></td>
                                                <td colspan="2"> <div  style="width:300px;">  
                                                    <input name="txtFrmDt" id="txtFrmDt" style="width: 100%" type="text"  value=""  class="datefield"  maxlength="15" readonly="readonly" 
                                                        </div>     </td>
                                               
                                           
                                                        <td class="text-right h5" width="20%"><fmt:message key='To Date'/></td>
                                           <td > <div  style="width:300px;">  
                                                    <input name="txtToDt" id="txtToDt" style="width: 100%" type="text"  value="" class="datefield"  maxlength="15" readonly="readonly"
                                           </div></td>
                                               
                                              
                                            </tr>
                                            <tr>
                                                          <td class="text-centre h5">
                                               
                                           <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                               
                                           <td></td>   <td></td>  
                                        </td>
                                                            <td width="15%"><input type="button" value=<fmt:message key='Download'/> id="btnReport" class="btn btn-success" onclick="callLegalReport();"/></td>
                                                           
                                                        </tr>
                                                        <tr>                                      
                                       
                              </tr>
                                                    </table>
                                                </center>
                                            </div>
                                        </div>
                                </div>
                            </div> <!-- End of  content_container_sub div  -->
                    
                            <hr>
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
                                <script src="assets/js/bootstrap.min.js?v=<%=System.getProperty("VERSION")%>"></script>
                                <!-- CUSTOM SCRIPTS -->
                                <script src="assets/js/custom.js?v=<%=System.getProperty("VERSION")%>"></script>

                                </body>
                                 <script>
                                  $("#txtVendorNumber" ).autocomplete({
//      source: availableTags
        source: function(request, response) {
            $.ajax({
                url: "${pageContext.request.contextPath}"+"/LegalServlet?actionName=autocomplete&autoCompleteParam=vendor",
                dataType: "json",
                data: request,
                success: function( data, textStatus, jqXHR) {
                    console.log( data);
                    var items = data;
                    response(items);
                },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                }
            });
        }
    });
    
      $("#txtLocation" ).autocomplete({
//      source: availableTags
        source: function(request, response) {
            $.ajax({
                url: "${pageContext.request.contextPath}"+"/LegalServlet?actionName=autocomplete&autoCompleteParam=allLocn",
                dataType: "json",
                data: request,
                success: function( data, textStatus, jqXHR) {
                    console.log( data);
                    var items = data;
                    response(items);
                },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                }
            });
        }
    }); </script>
                                </html>
