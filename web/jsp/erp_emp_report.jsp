<%-- 
    Document   : emp_financial_reports
    Created on : 6 Jun, 2017, 12:18:53 PM
    Author     : Sachin
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

    LinkedList<String> ylist = new LinkedList<String>();
    LinkedList<String> mlist = new LinkedList<String>();

    String tab = "";
    if (!ApplicationUtils.isBlank(request.getParameter("tab"))) {
        tab = (String) request.getParameter("tab");
    }
    if (!ApplicationUtils.isBlank(request.getAttribute("tab"))) {
        tab = (String) request.getAttribute("tab");
    }
    
    LinkedList POList = new LinkedList();
    LinkedList LocationList = new LinkedList();
    LinkedList VendorDtlList = new LinkedList();
    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    
     if (request.getSession().getAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA);        
        POList = (LinkedList) vendorPrezDataObj.getPOList();        
        LocationList = (LinkedList) vendorPrezDataObj.getLocationList();
        VendorDtlList = (LinkedList) vendorPrezDataObj.getVendorDtlList();
        
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
        
        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js?v=<%=System.getProperty("VERSION")%>"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js?v=<%=System.getProperty("VERSION")%>"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js?v=<%=System.getProperty("VERSION")%>"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js?v=<%=System.getProperty("VERSION")%>"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type="text/javascript" language="JavaScript" src="<%=ApplicationConstants.JS_PATH%>erp_emp_report.js?v=<%=System.getProperty("VERSION")%>"></script>
    </head>
    <body>
        <div id="wrapper">
            <%@ include file="nav_emp_header.jsp"%>
            <!-- /. NAV TOP  -->
            <%@ include file="emp_nav_vmenu.jsp"%>
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div id="page-inner" style="min-height:500px;">
                    
                     <input type="hidden" name="poOptions" id="poOptions" value='[<% if(!ApplicationUtils.isBlank(POList)) {
                                                  int i=0;
                                                    Set <String>poList = new HashSet<String>();
                                                 for (POBean pBean : (LinkedList<POBean>) POList) {
                                                      poList.add(pBean.getPONumber() + "-" + pBean.getPODesc());
                                                     i++;
                                                 }
                                                 int j=0;
                                                 for(String d: poList)
                                                 {
                                                      if(j!=0){ out.print(" , ");}
                                                         out.print("\"" + d.replace('"', ' ').replace('\'', ' ')  +"\"" );                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     j++;
                                                      }
                                                  
                                              
                                                   }
                                               // out.print( ']' );
                                                %>]'/>
                        
                        <input type="hidden" name="locOptions" id="locOptions" value='[<% if(!ApplicationUtils.isBlank(LocationList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                 for (POBean pBean : (LinkedList<POBean>) LocationList) {
                                                      if(i!=0){ out.print(" , ");}
                                                  out.print("\"" + pBean.getLocationCode() + "-" + pBean.getLocationName().replace('"', ' ').replace('\'', ' ') + "\""  );
                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     i++;
                                                      }
                                                   }
                                               // out.print( ']' );
                                                %>]'/>
                        
                                                   
                          <input type="hidden" name="VNumOptions" id="VNumOptions" value='[<% if (!ApplicationUtils.isBlank(VendorDtlList)) {
                                                    int i=0;
                                                     Set <String>venList = new HashSet<String>(); 
                                                 for (POBean pBean : (LinkedList<POBean>) VendorDtlList) {
                                                     venList.add(pBean.getVendorNumber() + "-" + pBean.getVendorName());
                                                     i++;
                                                 }
                                                 int j=0;
                                                 for(String d:venList)
                                                 {
                                                 
                                                      if(j!=0){ out.print(" , ");}
                                                  out.print("\"" + d.replace('"', ' ').replace('\'', ' ') + "\""  );
                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     j++;
                                                     
                                                 }
                                                 }
                                               // out.print( ']' );
                                                %>]'/>


                    <%@ include file="navJs.jsp"%>
                    <input type="hidden" name="<%=ApplicationConstants.UIACTION_NAME%>" id="<%=ApplicationConstants.UIACTION_NAME%>" value="<%=ApplicationConstants.REPORT_MSEDCL_EMP%>"/>

                    <div >&nbsp;</div><div class="col-md-12" align="center"><h3><fmt:message key='Procurement/Works Invoices'/></h3></div>
                    <br><br><div class="col-md-12" align="center"><h3><fmt:message key='Employee Reports'/></h3></div>
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
                                                                    <option value="<%=ApplicationConstants.REPORT_MSEDCL_EMP%>"><fmt:message key='Pending Invoices Report'/></option>  
                                                                    <option value="<%=ApplicationConstants.REPORT_EMPLOYEE_DETAIL%>"><fmt:message key='Pending Invoices Detailed Report'/></option>
                                                                 <option value="<%=ApplicationConstants.REPORT_EMPLOYEE_STATISTICS%>"><fmt:message key='Statistics Report'/></option>
                                                                </select>
                                                            </td>
                                                 <td></td><td></td>
                                              </tr>
                                         </table>
                                                            
                                   <table class="table">  
                                   <tr>
                                       <td class="text-right h5"><fmt:message key='PO Number'/></td>
                                        <td id="myDropdown" colspan="2">
                                            <div class="autocomplete" style="width:300px;">
                                                <input type="text" name="txtPONumber" id="txtPONumber" style="width: 100%" class="form-control" value=ALL title="Type and search or use space-bar" placeholder="Type and search or use space-bar" onkeypress="getPOSearchList();"  />
                                             
                                            </div>        
                                          </td>
                                          
                                                <td class="text-right h5"><fmt:message key='Vendor'/></td>
                                         <td id="myDropdownTwo" colspan="2">
                                            <div class="autocomplete" style="width:300px;">
                                                <input type="text" name="txtVendorNumber" id="txtVendorNumber" style="width: 100%" value=ALL title="Type and search or use space-bar" placeholder="Type and search or use space-bar" class="form-control" onkeypress="getVendorSearchList();"  />
                                              </div>        
                                          </td> 
                                          </tr>
                                          <tr>
                                              
                                              <td class="text-right h5" ><fmt:message key='Location'/></td>
                                           <td id="myDropdownThree">
                                            <div class="autocomplete" style="width:300px;">
                                                <input type="text" name="txtLocation" id="txtLocation" style="width: 100%" value=ALL title="Type and search or use space-bar" placeholder="Type and search or use space-bar"  class="form-control" onkeypress="getLocSearchList();"  />
                                              </div>        
                                          </td>   
                                          <td></td>
                                          <td></td>
                                          <td></td>
                                          </tr>
                                          <tr id="trParam">
                                                
                                              <td class="text-right h5" width="20%"><fmt:message key='From Date'/></td>
                                                <td > 
                                                    <input name="txtFrmDt" id="txtFrmDt" type="text" size="20" value=""  class="datefield"  maxlength="15" readonly="readonly" </td>
                                                <td width="5%">
                                                  <!--  <a href="javascript:void(0)" onClick="return callCalender('txtFrmDt');">
                                                        <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />
                                                    </a>-->
                                           </td>
                                           
                                                        <td class="text-right h5" width="20%"><fmt:message key='To Date'/></td>
                                           <td colspan="2"> 
                                                    <input name="txtToDt" id="txtToDt" type="text" size="20" value="" class="datefield"  maxlength="15" readonly="readonly" </td>
                                                <td width="5%">
                                               <!--     <a href="javascript:void(0)" onClick="return callCalender('txtToDt');">
                                                        <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />
                                                    </a>-->
                                           </td>
                                              
                                            </tr>
                                            <tr>
                                                            <td width="5%"></td>
                                                            <td width="5%"></td>
                                                            <td width="5%"></td>
                                                            <td width="15%"><input type="button" value=<fmt:message key='Download'/> id="btnReport" class="btn btn-success" onclick="callReport();"/></td>
                                                            <td width="5%"></td>
                                                            <td width="5%"></td>
                                                        </tr>
                                                        <tr>                                      
                                       <td class="text-centre h5">
                                               
                                           <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                               
                                               
                                        </td>
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
                                </html>