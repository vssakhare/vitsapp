<%--
    Document   : emp_personal_info
    Created on : Nov 28, 2013, 4:06:44 PM
    Author     : sachin, prajakta
--%>


<%@page import="in.emp.authority.bean.AuthorityBean"%>
<%@page import="in.emp.authority.bean.AuthorityPrezData"%> 
<%          

    

    String recordsVar = "No Records To Display !!!";
    String uiAction ="";
    
    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
       uiAction = request.getParameter("uiActionName");
    }

    AuthorityPrezData authorityPrezDataObj = new AuthorityPrezData();
    AuthorityBean authorityBean = new AuthorityBean();
    String SaveFlag="";
    int flag=0;

    if (request.getSession().getAttribute(ApplicationConstants.AUTHORITY_FORM_SESSION_DATA) != null) {
        authorityPrezDataObj = (AuthorityPrezData) request.getSession().getAttribute(ApplicationConstants.AUTHORITY_FORM_SESSION_DATA);
      
        authorityBean = (AuthorityBean) authorityPrezDataObj.getAuthorityBean();
       
    }
    String UserNumber ="";
    String ApplId="";  
    String AuthorityNumber="";
    String AuthorityName="";
    String PONumber="";
    String InvoiceNumber="";
    String InvoiceDate="";
    String InvoiceAmt="";
    String InwardNumber="";
    String InwardDate="";
    
    
    UserNumber = (String)request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
     if (authorityBean != null) {
         if (!ApplicationUtils.isBlank(authorityBean.getApplId())) {
            ApplId = authorityBean.getApplId();
        }
         
        if (!ApplicationUtils.isBlank(authorityBean.getSaveFlag())) {
            SaveFlag = authorityBean.getSaveFlag();
        }
         
        if (!ApplicationUtils.isBlank(authorityBean.getVendorNumber())) {
            AuthorityNumber = authorityBean.getVendorNumber();
        }
        
        if (!ApplicationUtils.isBlank(authorityBean.getVendorName())) {
            AuthorityName = authorityBean.getVendorName();
        }
        if (!ApplicationUtils.isBlank(authorityBean.getPONumber())) {
            PONumber = authorityBean.getPONumber();
        }
        
        if (!ApplicationUtils.isBlank(authorityBean.getInvoiceNumber())) {
            InvoiceNumber = authorityBean.getInvoiceNumber();
        }
        
        if ((authorityBean.getInvoiceDate() != null) && !(authorityBean.getInvoiceDate().equals("null")) && !(authorityBean.getInvoiceDate().equals(""))) {
            InvoiceDate = ApplicationUtils.dateToString(authorityBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        
        if (!ApplicationUtils.isBlank(authorityBean.getInvoiceAmount())) {
            InvoiceAmt = authorityBean.getInvoiceAmount();
        }
        
        if (!ApplicationUtils.isBlank(authorityBean.getInwardNumber())) {
            InwardNumber = authorityBean.getInwardNumber();
        }
        
        if ((authorityBean.getInwardDate() != null) && !(authorityBean.getInwardDate().equals("null")) && !(authorityBean.getInwardDate().equals(""))) {
            InwardDate = ApplicationUtils.dateToString(authorityBean.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
        }
        
     }
    

%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Authority Payment Tracking System</title>
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
        <script type='text/javascript' src="<%=ApplicationConstants.JS_PATH%>erp_authority_appl_form.js"></script>
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
                    <input type="hidden" name=uiActionName" id="uiActionName" value="<%=uiAction%>"/>
                    <input type="hidden" name=hdnUserNumber" id="hdnUserNumber" value="<%=UserNumber%>"/>
                    
                    <input type="hidden" name="redir" id="redir" value="<%=ApplicationConstants.UIACTION_GET_AUTHORITY_LIST%>"/>                     <input type="hidden" name="redir" id="redir" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_LIST%>"/>
                    
                    
                    <div class="content_container">
                        <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                            <tr> <!-- Start of Network Search Results tr -->
                                <td class="bg_white">  <!-- Start of Network Search Results td -->
                                    <!--<div class="form">-->  <!-- Start of  div  form -->
                                    <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                        <div class="col-md-12"><h3>Invoice Form</h3></div>
                                        <div >&nbsp;</div>
                                    </div>
                                </td>
                            </tr>

                        </table>  <!-- End of Network Search results table -->
                       
                        <div class="content_container_sub">  <!-- Start of  content_container_sub div  -->
                            <table class="table" border="0" cellspacing="0" cellpadding="2" id="table_content"> <!-- Start of  table_content table  -->
                                 <tr>
                                                <td class="text-right h5">Vendor Number</td>
                                                <td>
                                                    <input type="text" class="form-control text-right" name="txtVNum" id="txtVNum" value="<%=AuthorityNumber%>" style="width: 50%" readonly="true" >
                                                </td>
                                                <td class="text-right h5">Vendor Name</td>
                                                <td>
                                                    <input type="text" class="form-control" readonly="true" value="<%=AuthorityName%>" size="20" style="width: 100%" id="txtVName" name="txtVName" >
                                                </td>
                                </tr>
                                <tr>
                                                <td class="text-right h5">Application Id</td>
                                                <td>
                                                    <input type="text" class="form-control text-right" name="txtApplID" id="txtApplID" value="<%=ApplId%>" style="width: 50%" readonly="true" >
                                                </td>
                                                <td class="text-right h5">Invoice Status</td>
                                                <td>
                                                    <input type="text" class="form-control" readonly="true" value="<%=SaveFlag%>" size="20" style="width: 50%" id="txtStatus" name="txtStatus" >
                                                </td>
                                </tr>
                                
                                <tr>
                                                <td class="text-right h5">PO Number</td>
                                                <td>
                                                    <input type="text" class="form-control text-right" name="txtPONum" id="txtPONum" value="<%=PONumber%>" style="width: 50%" readonly="true"  >
                                                </td>
                                                <td class="text-right h5">Invoice Number</td>
                                                <td>
                                                    <input type="text" class="form-control text-right" value="<%=InvoiceNumber%>" size="20" style="width: 50%" id="txtInvoiceNum" name="txtInvoiceNum" readonly="true" >
                                                </td>
                                </tr>
                                
                                
                                 <tr>
                                     <td class="text-right h5">Invoice Amount</td>
                                                <td>
                                                    <input type="text" class="form-control text-right" value="<%=InvoiceAmt%>" size="20" style="width: 50%" id="txtInvoiceAmt" name="txtInvoiceAmt" readonly="true" >
                                                </td>
                                                
                                                <td class="text-right h5">Invoice Date</td>
                                                <td> 
                                                    <input name="txtInvoiceDt" id="txtInvoiceDt" type="text" size="20"class="form-control" value="<%=InvoiceDate%>" maxlength="15" readonly="true" />
                                                </td>
                                               
                                  </tr>
                                
                                
                                 <tr>
                                        
                                                <td class="text-right h5">Inward Number</td>
                                                <td>
                                                    <input type="text" class="form-control text-right" name="txtInwardNum" id="txtInwardNum" value="<%=InwardNumber%>" style="width: 50%" readonly="true" >
                                                </td>
                                 
                                                <td class="text-right h5">Inward Date</td>
                                                <td> 
                                                    <input name="txtInwardDt" id="txtInwardDt" type="text" size="20"class="form-control" value="<%=InwardDate%>" maxlength="15" 
                                                       readonly="true" /></td>
                                                
                                </tr>
                                
                            </table>  
                                    
                           
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