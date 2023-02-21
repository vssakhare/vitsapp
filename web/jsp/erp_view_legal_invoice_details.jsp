<%-- 
    Document   : erp_view_legal_invoice_details
    Created on : 16 Sep, 2022, 4:10:32 PM
    Author     : Rikma Rai
--%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.emp.legal.bean.LegalInvoiceInputBean"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="in.emp.vendor.bean.VendorBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%

    String recordsVar = "No Records To Display !!!";
    String uiAction = "";

    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }

    LegalInvoiceInputBean legalInvoiceInputBean=null;
    String SaveFlag = "";
    int flag = 0;

    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_LEGAL_INVOICE_ACCEPTED_DATA) != null) {
        legalInvoiceInputBean = (LegalInvoiceInputBean) request.getSession().getAttribute(ApplicationConstants.VENDOR_LEGAL_INVOICE_ACCEPTED_DATA);
    }
    
    String Zone = "";
    String Circle = "";
    String Division = "";
    String subdiv="";
    String section="";
    String subStation="";
    int i = 0;
    // Set <String>clearing_amt = new HashSet<String>();
    HashMap<String, String> clearing_amt = new HashMap<String, String>();
    String Status = "";

    String VendorNum = "";
    String VendorName = "";

    String MSEDCLInvNo = "";
    String MSEDCLInvDate = "";
    String VENDORInvDate = "";
    String region = "";
    String PROJECT_DESC = "";
    String CREATION_DATE = "";
    String dealingOffice = "";
    String courtName = "";
    String courtCaseNo = "";
    String caseRefNo = "";
    String caseDesc = "";    
    String partyNames = "";
    String VENDOR_NAME = "";
    String VENDOR_NUMBER = "";
    String MSEDCL_INV_NO = "";
    String UserType = "";
    String deductionAmount = "";
    String invoiceDate = "";
    String reasonForDeduction = "";
    String liabilityDocNo = "";
    String liabilityDocDate = "";
    String liabilityDocAmt = "";
    String taxAmount = "";
    String paidAmount = "";
    String paymentDate = "";
    String UTR_NO = "";
    String msedclInwardNo = "";
    String msedclInwardDate = "";
    String invoiceAmount = "";
    String paymentDocNo = "";
    String paymentDocAmount = "";
    String paymentDocDate = "";
    String paymentStatus = "";
    String mobileNo = "";
    String emailId = "";
    String feeType = "";
    String invoiceStatus = "";
    String MAT_OS_CLEARING_DOC_NO = "";
    String MAT_OS_AC_DOC_DATE = "";
    String MAT_OS_CLEARING_AMT = "";
    String MSEDCL_INVOICE_NUMBER = "";
    String MAT_OTH_PARK_DOC_NO = "";
    String MAT_OTH_DOC_DATE = "";
    String MAT_OTH_PARK_AMT = "";
    String MAT_OTH_CLEARING_DOC_NO = "";
    String MAT_OTH_AC_DOC_DATE = "";
    String MAT_OTH_CLEARING_AMT = "";
    String MAT_PO_AMOUNT = "";
    String CEN_PO_AMOUNT = "";
    String CIV_PO_AMOUNT = "";
    String INV_PO_AMOUNT = "";
    String ProjectCode = "";
    String ProjectScheme = "";
    String TotalPoAmount = "";
    String InvoiceNumber = "";
    String TAX_CODE = "";
    String INV_TYP = "";
    String WTAX_AMT = "";
    String WIT_TDS = "";
    String WGST_TDS = "";
    String WRPST = "";
    String WRET_AMT = "";
    String OTAX_AMT = "";
    String OIT_TDS = "";
    String OGST_TDS = "";
    String ORPST = "";
    String ORET_AMT = "";
    String CTAX_AMT = "";
    String CIT_TDS = "";
    String CGST_TDS = "";
    String CRPST = "";
    String CRET_AMT = "";
    String STAX_AMT = "";
    String SIT_TDS = "";
    String SGST_TDS = "";
    String SRPST = "";
    String SRET_AMT = "";
    String OTTAX_AMT = "";
    String OTIT_TDS = "";
    String OTGST_TDS = "";
    String OTRPST = "";
    String OTRET_AMT = "";
    String SubStatus = "";
    String INVOICE_TYPE = "";
    String MIGST_TX = "";
    String MSGST_TX = "";
    String OSGST_TX = "";
    String OIGST_TX = "";
    String CSGST_TX = "";
    String CIGST_TX = "";
    String SSGST_TX = "";
    String SIGST_TX = "";
    String OTSGST_TX = "";
    String OTIGST_TX = "";
//UserType="Vendor";
    if (legalInvoiceInputBean != null) {
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getRegionText())) {
            region = legalInvoiceInputBean.getRegionText();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getZoneText())) {
            Zone = legalInvoiceInputBean.getZoneText();
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCircleText())) {
            Circle = legalInvoiceInputBean.getCircleText();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getDivisionText())) {
            Division = legalInvoiceInputBean.getDivisionText();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getSubDivisionText())) {
            subdiv = legalInvoiceInputBean.getSubDivisionText();
        }
         if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getSectionText())) {
            section = legalInvoiceInputBean.getSectionText();
        }
         if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getSubStationText())) {
            subStation = legalInvoiceInputBean.getSubStationText();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceNumber())) {
            InvoiceNumber = legalInvoiceInputBean.getInvoiceNumber();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceAmount())) {
            invoiceAmount = legalInvoiceInputBean.getInvoiceAmount().toString();
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCreatedTimeStamp())) {
            CREATION_DATE = ApplicationUtils.dateToString(legalInvoiceInputBean.getCreatedTimeStamp(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceDate())) {
            invoiceDate = ApplicationUtils.dateToString(legalInvoiceInputBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPaymentDate())) {
            paymentDate = legalInvoiceInputBean.getPaymentDate();
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getVendorInvDate())) {
            VENDORInvDate = ApplicationUtils.dateToString(legalInvoiceInputBean.getVendorInvDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCourtName())) {
            courtName = legalInvoiceInputBean.getCourtName();
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getSaveFlag())) {
            Status = legalInvoiceInputBean.getSaveFlag();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getStatus())) {
            SubStatus = legalInvoiceInputBean.getStatus();
        }
//        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getMsedclInvoiceNumber())) {
//            MSEDCLInvNo = legalInvoiceInputBean.getMsedclInvoiceNumber();
//        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getMsedclInwardNo())) {
            msedclInwardNo = legalInvoiceInputBean.getMsedclInwardNo();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getMsedclInwardDate())) {
            msedclInwardDate = ApplicationUtils.dateToString(legalInvoiceInputBean.getMsedclInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCaseRefNo())) {
            caseRefNo = legalInvoiceInputBean.getCaseRefNo();
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCaseDescription())) {
            caseDesc = legalInvoiceInputBean.getCaseDescription();
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPartyNames())) {
            partyNames = legalInvoiceInputBean.getPartyNames();
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCourtCaseNo())) {
            courtCaseNo = legalInvoiceInputBean.getCourtCaseNo();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getDealingOfficeName())) {
            dealingOffice = legalInvoiceInputBean.getDealingOfficeName();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPartyNames())) {
            partyNames = legalInvoiceInputBean.getPartyNames();
        }

        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getFeeType())) {
            feeType = legalInvoiceInputBean.getFeeType();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getVendorName())) {
            VendorName = legalInvoiceInputBean.getVendorName();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getMobileNo())) {
            mobileNo = legalInvoiceInputBean.getMobileNo();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getEmailId())) {
            emailId = legalInvoiceInputBean.getEmailId();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getUTR_NO())) {
            UTR_NO = legalInvoiceInputBean.getUTR_NO();
        }
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getVendorNumber())) {
            VENDOR_NUMBER = legalInvoiceInputBean.getVendorNumber();
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLiabilityDocNo())) {
            liabilityDocNo = legalInvoiceInputBean.getLiabilityDocNo();
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLiabilityDocDate())) {
            liabilityDocDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("yyyy-mm-dd").parse(legalInvoiceInputBean.getLiabilityDocDate()));
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLiabilityDocAmt())) {
            liabilityDocAmt = legalInvoiceInputBean.getLiabilityDocAmt();
        }
        
                if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getStatus())) {
            invoiceStatus = legalInvoiceInputBean.getStatus();
        }
        int appl_ID;
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getApplId())) {
            appl_ID = legalInvoiceInputBean.getApplId();
        }
         if (request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION)!=null) {
            UserType = (String) request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION);
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPaidAmount())) {
            paidAmount = legalInvoiceInputBean.getPaidAmount();
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPayDoneErpDoc())) {
            paymentDocNo = legalInvoiceInputBean.getPayDoneErpDoc();
        }
        
        if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPaymentDocDate())) {
            paymentDocDate = legalInvoiceInputBean.getPaymentDocDate();
            System.out.println("paymentDocDate "+paymentDocDate);
        }

    }
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

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_vendor_appl_form.js"></script>
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

                    <div class="content_container" style="border:1px solid black;">
                        <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr> <!-- Start of Network Search Results tr -->
                                <td class="bg_inv">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <div style="padding-top:20px" ><h  style="font-size: 30px;color:#FFFFFF;font-weight: bold " >INVOICE </h>
                                          
                                        </div>
                                      
                                        
                                    </div>
                                </td>
                            </tr>
                                        <tr>
                                            <td>
                                                 <div class="table-responsive" align="left" >
                                            <div class=" invoiceheadleft" style="float:left"><label>Invoice Number :  <%= InvoiceNumber %> </label>
                                                <br><label>Invoice Date :  <%= invoiceDate %> </label> </div>
                                            
                                            <div class=" invoiceheadright " style="float:right; padding-bottom: 10px">
                                               <label  style="padding-right: 10px">Vendor Name:</label> <label  ><%= VendorName %></label><br>
                                             
                                                <label  style="padding-right: 10px">Vendor no:</label><label> <%= VENDOR_NUMBER %></label><br>
                                             <label style="padding-right: 10px" >Mobile:</label> <label><%= mobileNo %></label><br>
                                                <label style="padding-right: 10px"> Email:</label> <label><%= emailId %>  </label>
                                              
                                              </div>
                                                 </div>
                                            </td>
                                        </tr>
                                                 
                                                  

                        </table>  <!-- End of Network Search results table -->

                        <div class="content_container_sub"  >  <!-- Start of  content_container_sub div  -->
                            <div class="form-group invoicesubdiv"  >
                            <div class="col-sm-12 invoiceBlueHead" >  
                               <label>Invoice Details</label>
                            </div>
                            
                            <div class="col-sm-3">
				<div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>MSEDCL Inward Number :</label>  <label><%= msedclInwardNo %></label>
                                </div>
                            </div>
                            
                            <div class="col-sm-3">
                                           <div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>MSEDCL Inward Date :</label>  <label><%= msedclInwardDate %> </label>
                                           </div>
                            </div>
                            
                            <div class="col-sm-3">
                                           <div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Status :</label>  <label><%= invoiceStatus %> </label>
                                           </div>
                            </div>
                                           
                            <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Invoice Amount : </label> <label><%= invoiceAmount %></label>
				</div>
			    </div>                
                                                                       
                            <div class="col-sm-3">
				<div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Deduction Amount :</label>  <label><%= deductionAmount %></label>
                                </div>
                            </div>
                            
                            <div class="col-sm-3">
                                           <div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Reason for Deduction :</label>  <label><%= reasonForDeduction %> </label>
                                           </div>
                            </div>
                            <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Tax Amount : </label> <label><%= taxAmount %></label>
				</div>
			    </div>
                                <div class="col-sm-3">
				<div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Paid Amount :</label>  <label><%= paidAmount %></label>
                                </div>
                            </div>                            
                            
                            <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Payment Date : </label> <label><%= paymentDate %></label>
				</div>
			    </div>
                                 <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>UTR No : </label> <label><%= UTR_NO %></label>
				</div>
			    </div>
                            </div>
                            
                           <div class="invoicesubdiv" >
                            <div class="col-sm-12 invoiceBlueHead" >  
                               <label>Case Details</label>
                            </div>
                                
                               
                               <div class="col-sm-3">
				<div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Case Reference Number :</label>  <label><%= caseRefNo %></label>
                                </div>
                            </div>
                            
                            <div class="col-sm-3">
                                           <div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Court Case Number :</label>  <label><%= courtCaseNo %> </label>
                                           </div>
                            </div>
                            <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Court Name : </label> <label><%= courtName %></label>
				</div>
			    </div>
                                <div class="col-sm-8"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Location : </label> <label><%= subStation %> <%= section %>  <%= subdiv %> <%= Division %> <%= Circle %> <%= Zone %></label>
				</div>
                               
                           </div>
                           </div>
                            
                                
                                  <%if(UserType.equalsIgnoreCase("Emp")){%>
                               <div class="invoicesubdiv">
                            <div class="col-sm-12 invoiceBlueHead" >   <label>Liability Details</label>
                            </div>
                                
                               
                               <div class="col-sm-3">
				<div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Liability Document :</label>  <label><%= liabilityDocNo %></label>
                                </div>
                            </div>
                            
                            <div class="col-sm-3">
                                           <div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Liability Document Date :</label>  <label><%= liabilityDocDate %> </label>
                                           </div>
                            </div>
                            <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Liability Amount : </label> <label><%= liabilityDocAmt %></label>
				</div>
			    </div>
                               
                           </div>
                                
                                   <div class="invoicesubdiv">
                            <div class="col-sm-12 invoiceBlueHead" >   <label>Payment/Adjustment Details</label>
                            </div>
                                
                               
                               <div class="col-sm-3">
				<div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Payment Document :</label>  <label><%= paymentDocNo %></label>
                                </div>
                            </div>
                            
                            <div class="col-sm-3">
                                           <div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Payment Document Amount :</label>  <label><%= paidAmount//paymentDocAmount
                                        %> </label>
                                           </div>
                            </div>
                            <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Payment Date : </label> <label><%= paymentDocDate %></label>
				</div>
			    </div>
                                
                                <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>UTR Number : </label> <label><%= UTR_NO %></label>
				</div>
			    </div>
                                
                                <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Payment Status : </label> <label><%= paymentStatus %></label>
				</div>
			    </div>
                                
                                
			    </div>
                                <%}%>
                           </div>
                            
                            

                      
               
                           


                            <div class="row" align="center">                                      
                               
                                    <% if (UserType.equals("Vendor")) {%>
                                    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_LIST)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                    <% } else if (UserType.equals("Emp")) {%>
                                    <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_GET_LEGAL_VENDOR_INVOICE)%>" class="btn btn-danger"><fmt:message key='Back'/></a>
                                    <% }%>

                               





                            </div>



                            <hr/>

                            <!-- /. PAGE inner  -->
                        </div>
                        <!-- /. PAGE wrapper  -->
                    </div>
                    <!-- /.  wrapper  -->
                </div>
            </div>
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
