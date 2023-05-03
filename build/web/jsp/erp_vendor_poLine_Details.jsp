<%-- 
    Document   : erp_vendor_poLine_details
    Created on : 13 Jul, 2020, 12:26:46 PM
    Author     : Pooja Jadhav
--%>
<%@page import="java.util.HashMap"%>
<%@page import="in.emp.vendor.bean.PoLineStatusBean"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="in.emp.vendor.bean.ClearingDocDetails"%>
<%@page import="java.util.LinkedList"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%
     VendorPrezData vendorPrezDataObj = new VendorPrezData();
         LinkedList poLineDetails = new LinkedList();
         String VendorNumber = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_PO_LINE_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_PO_LINE_SESSION_DATA);        
    poLineDetails= (LinkedList) vendorPrezDataObj.getPoLineDetails();  
    }

    
        String UserNumber = (String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
 %>   
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

      <jsp:include page="nav_jscss.jsp" />
            
   
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_vendor_poLine_Details.js"></script>
    </head>
    <body>
        <form name="searchform" method="post" action="<%=ApplicationUtils.getActionURL(request)%>">
          <div id="wrapper">
           
            <div id="page-wrapper" >
                <div id="page-inner" style="min-height:500px;">

                    <%@ include file="navJs.jsp"%>
                   <input type="hidden" name="UserNumber" id="UserNumber" value ="<%=UserNumber%>"/>
                    <div class="content_container">
                        <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr> <!-- Start of Network Search Results tr -->
                                <td class="bg_white">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <div class="col-md-12"><h3><fmt:message key='PO Line Item Wise Details'/></h3></div>
                                        <div >&nbsp;</div>
                                    </div>
                                </td>
                            </tr>

                        </table>
                       <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                              <fieldset class="fldst_border">
                                     <legend>PO Details:</legend>
                                    <div class="row">                
                               <div class="col-lg-12 col-md-12">
                                  <div class="table-responsive" id="mydiv">
                                       <table class="table">
                                            <thead>
                                         
                                                <tr class="success">                                                                                       
                                                    <th class="text-center">#</th>
                                                     <th class="text-center"> </th>
                                                        <th class="text-center"><fmt:message key='PO Number 1111'/></th>  
                                                    <th class="text-center" ><fmt:message key='PO Desc 2222'/></th>  
                                                    <th class="text-center"><fmt:message key='PLANT'/></th> 
                                                    
                                                                                                  
                                                </tr> </thead><tbody>  
                                                 <%
                                
                                     int j = 0;
                                     int k = poLineDetails.size();
                                    
                                     for (PoLineStatusBean POLineBean : (LinkedList<PoLineStatusBean>) poLineDetails) {
                                                    j++;
                                     
                                    String PO_NUMBER = "";
                                     String PO_Desc = "";
                                      String Plant = "";
                                      String PO_lineId="";
                                      String Appl_Id="";
                               
                                     if (!ApplicationUtils.isBlank(POLineBean.getContract_Document())) {
                                        PO_NUMBER= POLineBean.getContract_Document();
                                     }
                                       if (!ApplicationUtils.isBlank(POLineBean.getShort_Text())) {
                                        PO_Desc=POLineBean.getShort_Text();
                                     }
                                         if (!ApplicationUtils.isBlank(POLineBean.getPlant())) {
                                        Plant= POLineBean.getPlant();
                                     }
                                     if (!ApplicationUtils.isBlank(POLineBean.getPoLineId())) {
                                        PO_lineId= POLineBean.getPoLineId();
                                     }
                                       if (!ApplicationUtils.isBlank(POLineBean.getAPPL_ID())) {
                                        Appl_Id= POLineBean.getAPPL_ID();
                                     }
                                     
                                    %>
                                           

                                  
                                                <tr class="info" >
                                             <td class="text-center"><%=j%></td>
                                             <td class="text-center"> <input type="checkbox" name="search_checkbox" data-applid="<%=Appl_Id%>" id ="poLineSelect" value=<%=PO_lineId%>> </td>
                                             <td width="20%" class="text-center"><%=PO_NUMBER%></td>
                                             <td width="20%" class="text-center"><%=PO_Desc%></td>
                                             <td width="20%" class="text-center"><%=Plant%></td>
                                             </tr>
                                   
                                           <% } %> 
                                            </tbody>
                                       </table>
                                  </div>
                               </div>
                                     </div>
                                      </fieldset>
                                  <input type="button" value=<fmt:message key='Submit'/> name="ButtonSave" id="ButtonSave" style="float: left;" onclick="savePOLineButton()"
                                           class="btn  btn-success"/>                   
                 
                                    
                       </div>
                    </div>
                </div>
            </div>
          </div>
          </form>                           
    </body>
                                           
                                           