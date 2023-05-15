<%-- 
    Document   : erp_view_legal_invoice_details
    Created on : 16 Sep, 2022, 4:10:32 PM
    Author     : Rikma Rai
--%>


<%@page import="in.emp.vendor.bean.VendorApplFileBean"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Iterator"%>
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
    LinkedList FileList = new LinkedList();
    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA) != null) {
        FileList = (LinkedList) request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA);
    }
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
        String subdiv = "";
        String section = "";
        String subStation = "";
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
        String InvoiceNumber = "";
        String SubStatus = "";
        String TdsAmount = "";
        String CgstAmount = "";
        String SgstAmount = "";
        String CgstTdsAmount = "";
        String SgstTdsAmount = "";
        String IgstAmount = "";
        String IgstTdsAmount = "";
        String appl_ID="";

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
                invoiceAmount = ApplicationUtils.formatAmount(Double.valueOf(invoiceAmount));
            }

            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCreatedTimeStamp())) {
                CREATION_DATE = ApplicationUtils.dateToString(legalInvoiceInputBean.getCreatedTimeStamp(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
            }

            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getInvoiceDate())) {
                invoiceDate = ApplicationUtils.dateToString(legalInvoiceInputBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
            }

            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPaymentDate())) {
                paymentDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(legalInvoiceInputBean.getPaymentDate()));
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
                liabilityDocDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(legalInvoiceInputBean.getLiabilityDocDate()));
            }

            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getLiabilityDocAmt())) {
                liabilityDocAmt = legalInvoiceInputBean.getLiabilityDocAmt();
                liabilityDocAmt = ApplicationUtils.formatAmount(Double.valueOf(liabilityDocAmt));
            }

            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getStatus())) {
                invoiceStatus = legalInvoiceInputBean.getStatus();
            }
            //int appl_ID;
            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getApplId())) {
                appl_ID = legalInvoiceInputBean.getApplId()+"";
            }
            if (request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION) != null) {
                UserType = (String) request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION);
            }

            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPaidAmount())) {
                paidAmount = legalInvoiceInputBean.getPaidAmount();
                paidAmount = ApplicationUtils.formatAmount(Double.valueOf(paidAmount));
            }

            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPayDoneErpDoc())) {
                paymentDocNo = legalInvoiceInputBean.getPayDoneErpDoc();
            }

            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPaymentDocDate())) {
                paymentDocDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(legalInvoiceInputBean.getPaymentDocDate()));
                //System.out.println("paymentDocDate "+paymentDocDate);
            }
            if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getTdsAmount())) {
                TdsAmount = legalInvoiceInputBean.getTdsAmount();
                TdsAmount = ApplicationUtils.formatAmount(Double.valueOf(TdsAmount));
            }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCgstAmount())) {
                 CgstAmount = legalInvoiceInputBean.getCgstAmount();
                 CgstAmount = ApplicationUtils.formatAmount(Double.valueOf(CgstAmount));
             }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getSgstAmount())) {
                 SgstAmount = legalInvoiceInputBean.getSgstAmount();
                 SgstAmount = ApplicationUtils.formatAmount(Double.valueOf(SgstAmount));
             }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getSgstTdsAmount())) {
                 SgstTdsAmount = legalInvoiceInputBean.getSgstTdsAmount();
                 SgstTdsAmount = ApplicationUtils.formatAmount(Double.valueOf(SgstTdsAmount));
             }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getIgstAmount())) {
                 IgstAmount = legalInvoiceInputBean.getIgstAmount();
                 IgstAmount = ApplicationUtils.formatAmount(Double.valueOf(IgstAmount));
             }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getIgstTdsAmount())) {
                 IgstTdsAmount = legalInvoiceInputBean.getIgstTdsAmount();
                 IgstTdsAmount = ApplicationUtils.formatAmount(Double.valueOf(IgstTdsAmount));
             }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getCgstTdsAmount())) {
                 CgstTdsAmount = legalInvoiceInputBean.getCgstTdsAmount();
                 CgstTdsAmount = ApplicationUtils.formatAmount(Double.valueOf(CgstTdsAmount));
             }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getPaymentStatus())) {
                 paymentStatus = legalInvoiceInputBean.getPaymentStatus();
             }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getReasonForDeduction())) {
                 reasonForDeduction = legalInvoiceInputBean.getReasonForDeduction();
             }
             if (!ApplicationUtils.isBlank(legalInvoiceInputBean.getDeductionAmount())) {
                 deductionAmount = legalInvoiceInputBean.getDeductionAmount();
                 deductionAmount = ApplicationUtils.formatAmount(Double.valueOf(deductionAmount));
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
                                        <div style="" ><h  style="font-size: 30px;color:#FFFFFF;font-weight: bold " >INVOICE </h>
                                          
                                        </div>
                                      
                                        
                                    </div>
                                </td>
                            </tr>
                                        <tr>
                                            <td>
                                                 <div class="table-responsive" align="left" >
                                            <div class=" invoiceheadleft" style="float:left"><label>Invoice Number : </label><label><a href="#nogo" onclick="postForm('Reports','reportName=dtlsofapplid&applid=<%= appl_ID %>&reportType=PDF');" style="background-color: orange"> <%= InvoiceNumber %> </a><i class="bi bi-arrow-up-right-square"></i></label>
                                                <br><label>Invoice Date :  <%= invoiceDate %> </label>
                                            <br><label>Invoice Amount :  <%= invoiceAmount %> </label>
                                            </div>
                                            
                                            <div class=" invoiceheadright " style="float:right; ">
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
                                 <!--<div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>UTR No : </label> <label><%= UTR_NO %></label>
				</div>
			    </div>-->
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
                                
                            
                            <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Party Name : </label> <label><%= partyNames %></label>
				</div>
			    </div>   
                                <div class="col-sm-3"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Case Desc. : <%= caseDesc %></label>
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
                                
                               
                               <div class="col-sm-4">
				<div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Payment Document :</label>  <label><%= paymentDocNo %></label>
                                </div>
                            </div>
                            
                            <div class="col-sm-4">
                                           <div class="styled-input" style="font-size:12px;padding-top:10px">
                                    <label>Payment Document Amount :</label>  <label><%= paidAmount//paymentDocAmount
                                        %> </label>
                                           </div>
                            </div>
                            <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Payment Date : </label> <label><%= paymentDocDate %></label>
				</div>
			    </div>
                                
                                <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>UTR Number : </label> <label><a href="#nogo" onclick="postForm('Reports','reportName=dtlsofutrno&utrno=<%= UTR_NO %>&reportType=PDF');" style="background-color: orange"><%= UTR_NO %></a></label>
				</div>
			    </div>
                                 <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>TDS Amount: </label> <label><%= TdsAmount %></label>
				</div>
			    </div>
                                 <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>CGST Amount: </label> <label><%= CgstAmount %></label>
				</div>
			    </div>
                                 <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>SGST Amount: </label> <label><%= SgstAmount %></label>
				</div>
			    </div>
                                 <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>CGST TDS Amount: </label> <label><%= CgstTdsAmount %></label>
				</div>
			    </div>
                                 <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>SGST TDS Amount: </label> <label><%= SgstTdsAmount %></label>
				</div>
			    </div>
                                 <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>IGST Amount: </label> <label><%= IgstAmount %></label>
				</div>
			    </div>
                                 <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>IGST TDS Amount: </label> <label><%= IgstTdsAmount %></label>
				</div>
			    </div>
                               <div class="col-sm-4"><div class="styled-input" style="font-size:12px;padding-top:10px">
                                     <label>Payment Status : </label> <label><%= paymentStatus %></label>
				</div>
			    </div>                             
                            </div>
                                
                            <div class="col-sm-12 invoiceBlueHead" >   <label>Uploaded Files</label>
                            </div>    
                            <form  method="post" enctype="multipart/form-data">    
                            <input type="hidden" name="view" id="view1" value="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_GET)%>"/>
                            <input type="hidden" name="txtApplicationId" id="txtApplicationId" value="<%=appl_ID%>"/>
                            </form>
                                
                            <div class="table-responsive">
                            <table class="table"  align="center">


                                <%  
                                    Iterator itr = FileList.iterator();
                                    System.out.println("FileList::"+FileList);
                                    int j = 0;
                                    while (itr.hasNext()) {
                                        String remark = "";
                                    String type = "";
                                    VendorApplFileBean flb = new VendorApplFileBean();
                                    flb = (VendorApplFileBean) itr.next();
                                    j++; 
                                    if (!ApplicationUtils.isBlank(flb.getRemark())) {
                                        remark = flb.getRemark();
                                    }
                                    //System.out.println("flb.getOption()"+flb.getOption()); 
                                    if (!ApplicationUtils.isBlank(flb.getOption())) {
                                        type = flb.getOption();
                                    }
                                %>

                                <tr class="info" style="font-weight: bold;font-size:12px">
                                <td><%=j%></td>
                                <td class="blackfont"  style="color:blue"><a class="blackfont" href="#nogo" onclick="viewFile('<%=flb.getId()%>', '<%=flb.getOption()%>')"> <%=(flb.getFileName() + "." + flb.getFileType())%></a></td>
                                <td><%=type%></td>
                                <td><%=remark%></td>    
                                </tr><%  }%> </table>
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
