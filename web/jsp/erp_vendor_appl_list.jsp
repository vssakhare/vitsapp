
<%@page import="in.emp.vendor.bean.VendorInputBean"%>
<%@page import="in.emp.vendor.bean.POBean"%>
<%@page import="in.emp.vendor.bean.VendorPrezData"%>
<%@page import="in.emp.vendor.bean.VendorBean"%>
<%@page import="in.emp.hrms.bean.HRMSUserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.emp.common.ApplicationConstants"%>
<%@page import="in.emp.util.ApplicationUtils"%>
<%@page import="java.util.*"%>
<%@page import="in.emp.user.bean.UserBean"%>
<%
    LinkedList vendorList = new LinkedList();
    LinkedList POList = new LinkedList();
    LinkedList VendorInvList = new LinkedList();
    LinkedList VendorInpList = new LinkedList();
    LinkedList VendorLocList = new LinkedList();
    String recordsVar = "No Records To Display !!!";
    
    String uiAction = "";
   
    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }
    
     VendorPrezData vendorPrezDataObj = new VendorPrezData();
     VendorPrezData vendorPrezDataObjOne = new VendorPrezData();
     VendorPrezData vendorPrezDataObjTwo = new VendorPrezData();     
    VendorBean vendorBean = new VendorBean();
     VendorBean vendorBeanOne = new VendorBean();
     String subAction = "";
     
     if(!(uiAction.equals(ApplicationConstants.UIACTION_GET_RESET_LIST))){
         
    if (request.getSession().getAttribute(ApplicationConstants.VENDOR_LIST_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_LIST_SESSION_DATA);        
        POList = (LinkedList) vendorPrezDataObj.getPOList();
        VendorInvList =  (LinkedList) vendorPrezDataObj.getVendorInvoiceList();  
        VendorLocList = (LinkedList) vendorPrezDataObj.getLocationList();
        vendorBean = (VendorBean) vendorPrezDataObj.getVendorBean();        
    }
         if((uiAction.equals(ApplicationConstants.UIACTION_GET_VENDOR_LIST))){
     if (request.getSession().getAttribute(ApplicationConstants.VENDOR_INVLIST_SESSION_DATA) != null) {
          vendorPrezDataObjOne = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_INVLIST_SESSION_DATA);        
        
        vendorList = (LinkedList) vendorPrezDataObjOne.getVendorList();
        vendorBeanOne = (VendorBean) vendorPrezDataObjOne.getVendorBean();  
        VendorInpList = (LinkedList) vendorPrezDataObjOne.getVendorInputList();
        
        
    }}
}
     if(uiAction.equals(ApplicationConstants.UIACTION_GET_RESET_LIST)){
     if (request.getSession().getAttribute(ApplicationConstants.VENDOR_POLIST_SESSION_DATA) != null) {
        vendorPrezDataObjTwo = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_POLIST_SESSION_DATA);        
        POList = (LinkedList) vendorPrezDataObjTwo.getPOList();
        VendorLocList = (LinkedList) vendorPrezDataObjTwo.getLocationList();
       // VendorInvList =  (LinkedList) vendorPrezDataObj.getVendorInvoiceList();
       
       vendorBean = (VendorBean) vendorPrezDataObjTwo.getVendorBean();        
    }
     }
    
    
     String PONumberHdr = "";
     String PODescHdr = "";
     String VInvHdr = "";
     String FrmDtHdr="";
     String ToDtHdr="";
     
     String PlantCodeHdr = "";
     String LocDescHdr = "";
     
    
    if (vendorBean != null) {
       
        if (!ApplicationUtils.isBlank(vendorBean.getSubAction())) {
            subAction = vendorBean.getSubAction();
           
        } 
      if(!(subAction.equals("Reset")))
       {
        if (!ApplicationUtils.isBlank(vendorBean.getPONumberHdr())) {
            PONumberHdr = vendorBean.getPONumberHdr();
            
            if (!ApplicationUtils.isBlank(POList)) { 
            for (POBean pBean : (LinkedList<POBean>) POList) {
                    if((!ApplicationUtils.isBlank(PONumberHdr)) && pBean.getPONumber().equals(PONumberHdr) ) {
                       PODescHdr = pBean.getPONumber() + "-" +pBean.getPODesc();
                    }
                }
            }
        }  
        
         if (!ApplicationUtils.isBlank(vendorBeanOne.getPlantCode())) {
            PlantCodeHdr = vendorBeanOne.getPlantCode();
            
            if (!ApplicationUtils.isBlank(VendorLocList)) { 
            for (POBean pBean : (LinkedList<POBean>) VendorLocList) {
                    if((!ApplicationUtils.isBlank(PlantCodeHdr)) && pBean.getPlantCode().equals(PlantCodeHdr) ) {
                       LocDescHdr = pBean.getPlantCode() + "-" +pBean.getOrgUnit();
                    }
                }
            }
        }  
         
         
        
           if (!ApplicationUtils.isBlank(vendorBeanOne.getVendorInvoiceNumber())) {
            VInvHdr = vendorBeanOne.getVendorInvoiceNumber();
          }  
           if ((vendorBeanOne.getInvoiceFromDate() != null) && !(vendorBeanOne.getInvoiceFromDate().equals("null")) && !(vendorBeanOne.getInvoiceFromDate().equals(""))) {
            FrmDtHdr = ApplicationUtils.dateToString(vendorBeanOne.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
          }
          if ((vendorBeanOne.getInvoiceToDate() != null) && !(vendorBeanOne.getInvoiceToDate().equals("null")) && !(vendorBeanOne.getInvoiceToDate().equals(""))) {
            ToDtHdr = ApplicationUtils.dateToString(vendorBeanOne.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
          }
        
       }
    }

   
    String UserNumber = (String)request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
   // String actionStatus = "Submitted";
    String viewAction = "";
  
    
    if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_VENDOR_LIST)) {
            //actionStatus = "Saved";
            viewAction = "redir";
        }

       
    }

    LinkedList applListNew = new LinkedList();
    LinkedList applListOld = new LinkedList();

    
    
     if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_VENDOR_LIST)) {
            for (VendorBean o : (LinkedList<VendorBean>) vendorList) {
                    if ((o.getSaveFlag().equals("Technical")) || (o.getSaveFlag().equals("Accounts")) || (o.getSaveFlag().equals("Pending For Payment")) )  {
                      applListNew.add(o); 
                   } else if (o.getSaveFlag().equals("Paid")) 
                     {
                      applListOld.add(o);
                }
            }
        }
    }
    
    
    
    
        
    
%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Vendor Bills</title>
        <!-- BOOTSTRAP STYLES-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
        <link href="assets/css/custom.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
         <!-- AUTOCOMPLETE-->
        <link href="css/autocomplete.css" rel="stylesheet" />

        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
                        <script type="text/javascript" language="JavaScript" src="<%=ApplicationConstants.JS_PATH%>erp_vendor_list.js"></script>

        <jsp:include page="nav_jscss.jsp" />

        
    </head>
    <body>          
        <form name="searchform" method="post" action="<%=ApplicationUtils.getActionURL(request)%>">
            <div id="wrapper">
                <%@ include file="nav_emp_header.jsp"%>
                <!-- /. NAV TOP  -->
                <%@ include file="emp_nav_vmenu.jsp"%>
                <!-- /. NAV SIDE  -->
                <div id="page-wrapper" >

                    <div id="page-inner" style="min-height:500px;">


                        <input type="hidden" name=uiActionName" id="uiActionName" value="<%=uiAction%>"/>
                        <input type="hidden" name="redir" id="redir" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_APPL_FORM%>"/>
                        <input type="hidden" name="deleteAction" id="deleteAction" value="<%=ApplicationConstants.UIACTION_POST_VENDOR_LIST%>"/>
                        <input type="hidden" name="deleteRedirect" id="deleteRedirect" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_LIST%>"/>
                        <input type="hidden" name="poOptions" id="poOptions" value='[<% if(!ApplicationUtils.isBlank(POList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                 for (POBean pBean : (LinkedList<POBean>) POList) {
                                                      if(i!=0){ out.print(" , ");}
                                                  out.print("\"" + pBean.getPONumber() + "-" + pBean.getPODesc().replace('"', ' ').replace('\'', ' ') + "\""  );
                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     i++;
                                                      }
                                                   }
                                               // out.print( ']' );
                                                %>]'/>
                        
                          <input type="hidden" name="invNumOptions" id="invNumOptions" value='[<% if(!ApplicationUtils.isBlank(VendorInvList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                    
                                                 for (VendorBean invBean : (LinkedList<VendorBean>) VendorInvList) {
                                                      if(i!=0){ out.print(" , ");}
                                                  out.print("\"" + invBean.getVendorInvoiceNumber().replace('"', ' ').replace('\'', ' ') + "\""  );
                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     i++;
                                                 }
                                                    }
                                               // out.print( ']' );
                                                %>]'/>
                          
                           <input type="hidden" name="locOptions" id="locOptions" value='[<% if(!ApplicationUtils.isBlank(VendorLocList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                    
                                                 for (POBean poBean : (LinkedList<POBean>) VendorLocList) {
                                                      if(i!=0){ out.print(" , ");}
                                                 out.print("\"" + poBean.getPlantCode() + "-" + poBean.getOrgUnit().replace('"', ' ').replace('\'', ' ') + "\""  );
                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     i++;
                                                 }
                                                    }
                                               // out.print( ']' );
                                                %>]'/>
                           
                        <%@ include file="navJs.jsp"%>
                        <!-- Ends header content -->
                      

                            <table width="100%" border="0" cellspacing="0" cellpadding="0"> <!-- Start of Network Search results table -->
                                <tr>
                                    <td class="module_tbl_tl" >&nbsp;</td>
                                    <td class="tbl_hline_top">&nbsp;</td>
                                    <td class="module_tbl_tr">&nbsp;</td>
                                </tr>
                                <tr> <!-- Start of Network Search Results tr -->

                                    <td class="bg_white">  <!-- Start of Network Search Results td -->
                                        <!--<div class="form">-->  <!-- Start of  div  form -->
                                        <div class="table-responsive" align="center" >  <!-- Start of  content_container_sub div  -->
                                                                   
                                                 <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#pendingList">Pending Invoices</a></li>
                            <li><a data-toggle="tab" href="#closedList">Paid Invoices</a></li>
                            
                        </ul> 
                                            
                          <div class="tab-content">
                            <%@ include file="sort_paging_hidden_fields.jsp" %>                       
                                        
                            <div id="pendingList" class="tab-pane fade in active">
                                <div class="content_container_sub">
                                         <%
                                if (applListNew.size() != 0) {
                            %>
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12">
                                            <div class="table-responsive">
                                        <div class="col-md-12 text-center"><h3>Pending Invoices</h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                      <th>#</th>
                                                    <th class="text-center">Location</th>                                                    
                                                    <th class="text-center">PO Description</th>
                                                    <th class="text-center">PO Type</th>
                                                    <th class="text-center">PO Creation Date</th> 
                                                    <th class="text-center">SES/MIGO Date</th>
                                                    <th class="text-center">Liability Date</th>
                                                    <th class="text-center">Payment Date</th>  
                                                    <th class="text-center">MSEDCL Inv No.</th>
                                                    <th class="text-center">MSEDCL Inv Dt.</th>
                                                    <th class="text-center">Pending With</th>                                                   
                                                    <th class="text-center">View</th>                                                  
                                                </tr>
                                            </thead>

                                            <%                                 
                                                                                             
                                                int j = 0;

                                                for (VendorBean comBean : (LinkedList<VendorBean>) applListNew) {
                                                    String Zone = "";
                                                String Circle = "";
                                                String Division = "";
                                                String Location = "";
                                                String PONumber = "";
                                                String PODesc = "";
                                                String POType = "";
                                                String POCreationDate = "";
                                                String SORMDate = "";
                                                String LiabilityDate = "";
                                                String PaymentDate = "";
                                                String ValidityStart = "";
                                                String ValidityEnd = "";
                                                String VendorInvoiceNumber = "";
                                                String VendorInvNumber = "";
                                                String VendorInvoiceDate = "";
                                                String Status = "";  
                                                int flag =0;
                                                    j++;

                                                    if (!ApplicationUtils.isBlank(comBean.getZone())) {
                                                        Location = comBean.getZone();
                                                    }

                                                    if (!ApplicationUtils.isBlank(comBean.getCircle())) {
                                                        Location = Location + "-" + comBean.getCircle();
                                                    }
                                                    if (!ApplicationUtils.isBlank(comBean.getDivision())) {
                                                        Location = Location + "-" + comBean.getDivision();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getPONumber())) {
                                                        PONumber =  comBean.getPONumber();
                                                        
                                                    }
                                                    if (!ApplicationUtils.isBlank(comBean.getPODesc())) {
                                                        PODesc = PONumber + "-" + comBean.getPODesc();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getPOType())) {
                                                        POType = comBean.getPOType();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getPOCreationDate())) {
                                                        POCreationDate = ApplicationUtils.dateToString(comBean.getPOCreationDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                     if (!ApplicationUtils.isBlank(comBean.getSESDate())) {
                                                        SORMDate = ApplicationUtils.dateToString(comBean.getSESDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                     
                                                      if (!ApplicationUtils.isBlank(comBean.getInvDt())) {
                                                        LiabilityDate = ApplicationUtils.dateToString(comBean.getInvDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                      
                                                       if (!ApplicationUtils.isBlank(comBean.getCLDocDt())) {
                                                        PaymentDate = ApplicationUtils.dateToString(comBean.getCLDocDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getValidityStart())) {
                                                        ValidityStart = ApplicationUtils.dateToString(comBean.getValidityStart(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getValidityEnd())) {
                                                        ValidityEnd = ApplicationUtils.dateToString(comBean.getValidityEnd(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getVendorInvoiceNumber())) {
                                                        VendorInvoiceNumber = comBean.getVendorInvoiceNumber();
                                                        flag=2;
                                                      VendorInvNumber =  VendorInvoiceNumber.replace("\\", "\\\\");
                                                         
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getSaveFlag())) {
                                                        Status = comBean.getSaveFlag();
                                                    }
                                                    
                                                   if (!ApplicationUtils.isBlank(comBean.getInvoiceDate())) {
                                                        VendorInvoiceDate = ApplicationUtils.dateToString(comBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }

                                            %>


                                            <tbody>
                                                <tr class="info" >
                                            <td align="center"><%=j%></td>
                                            <td align="center" width="15%"><%=Location%></td>                                           
                                            <td align="center" width="15%"><%=PODesc%></td>
                                            <td align="center"><%=POType%></td>                                            
                                            <td align="center"><%=POCreationDate%></td>  
                                            <td align="center"><%=SORMDate%></td>   
                                            <td align="center"><%=LiabilityDate%></td>   
                                            <td align="center"><%=PaymentDate%></td>   
                                            <td align="center"><%=VendorInvoiceNumber%></td>
                                            <td align="center"><%=VendorInvoiceDate%></td>                                            
                                            <td align="center"><%=Status%></td> 
                                            <% if(flag==2) { %>
                                            <td align="center"><a href="#nogo" onclick="viewEmpApp('<%=VendorInvNumber%>', '<%=UserNumber%>', '<%=viewAction%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                           <% } else { %>
                                            <td></td>
                                            <% } %>
                                                </tr>
                                            <%
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                          </div>                                                 

                            <% } else {%>
                            <div class="row">                
                                <div class="col-lg-12 col-md-12">                        
                                    <div class="table-responsive">
                                        <div class="col-md-12 text-center"><h3>Pending Invoices</h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                        
                                                     <th>#</th>
                                                    <th class="text-center">Location</th>                                                   
                                                    <th class="text-center">PO Description</th>
                                                    <th class="text-center">PO Type</th>
                                                    <th class="text-center">PO Creation Date</th> 
                                                    <th class="text-center">SES/MIGO Date</th>
                                                    <th class="text-center">Liability Date</th>
                                                    <th class="text-center">Payment Date</th>  
                                                    <th class="text-center">MSEDCL Inv No.</th>
                                                    <th class="text-center">MSEDCL Inv Dt.</th>
                                                    <th class="text-center">Pending With</th>                                                   
                                                    <th class="text-center">View</th>                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="info" align="center" >                                                      
                                                    <td colspan="12"><%=recordsVar%></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>  

                            <% }%>
                                 </div>
                            </div><!--pending List ends-->

                            <div id="closedList" class="tab-pane fade">
                                <div class="content_container_sub"> 

                            <%
                                if (applListOld.size() != 0) {
                            %>

                            <div class="row">                
                                <div class="col-lg-12 col-md-12">                        
                                    <div class="table-responsive">
                                        <div class="col-md-12 text-center"><h3>Paid Invoices</h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                     <th>#</th>
                                                    <th class="text-center">Location</th>                                                   
                                                    <th class="text-center">PO Description</th>
                                                    <th class="text-center">PO Type</th>
                                                    <th class="text-center">PO Creation Date</th> 
                                                   <th class="text-center">SES/MIGO Date</th>
                                                    <th class="text-center">Liability Date</th>
                                                    <th class="text-center">Payment Date</th>  
                                                    <th class="text-center">MSEDCL Inv No.</th>
                                                    <th class="text-center">MSEDCL Inv Dt.</th>
                                                    <th class="text-center">Pending With</th>                                                   
                                                    <th class="text-center">View</th>                                                       
                                                </tr>
                                            </thead>
                                        <%
                                                                                        
                                                int j = 0;

                                                for (VendorBean comBean : (LinkedList<VendorBean>) applListOld) {
                                                    j++;
                                                    
                                                     String Zone = "";
                                                String Circle = "";
                                                String Division = "";
                                                String Location = "";
                                                String PONumber = "";
                                                String PODesc = "";
                                                String POType = "";
                                                String POCreationDate = "";
                                                String SORMDate = "";
                                                String LiabilityDate = "";
                                                String PaymentDate = "";
                                                String ValidityStart = "";
                                                String ValidityEnd = "";
                                                String VendorInvoiceNumber = "";
                                                String VendorInvNumber = "";
                                                String VendorInvoiceDate = "";
                                                String Status = ""; 
                                                int flag =0;

                                                    if (!ApplicationUtils.isBlank(comBean.getZone())) {
                                                        Location = comBean.getZone();
                                                    }

                                                    if (!ApplicationUtils.isBlank(comBean.getCircle())) {
                                                        Location = Location + "-" + comBean.getCircle();
                                                    }
                                                    if (!ApplicationUtils.isBlank(comBean.getDivision())) {
                                                         Location = Location + "-" + comBean.getDivision();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getPONumber())) {
                                                        PONumber = comBean.getPONumber();
                                                    }
                                                    if (!ApplicationUtils.isBlank(comBean.getPODesc())) {
                                                        PODesc = PONumber + "-" + comBean.getPODesc();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getPOType())) {
                                                        POType = comBean.getPOType();
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getPOCreationDate())) {
                                                        POCreationDate = ApplicationUtils.dateToString(comBean.getPOCreationDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                     if (!ApplicationUtils.isBlank(comBean.getSESDate())) {
                                                        SORMDate = ApplicationUtils.dateToString(comBean.getSESDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                     
                                                      if (!ApplicationUtils.isBlank(comBean.getInvDt())) {
                                                        LiabilityDate = ApplicationUtils.dateToString(comBean.getInvDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                      
                                                       if (!ApplicationUtils.isBlank(comBean.getCLDocDt())) {
                                                        PaymentDate = ApplicationUtils.dateToString(comBean.getCLDocDt(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getValidityStart())) {
                                                        ValidityStart = ApplicationUtils.dateToString(comBean.getValidityStart(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getValidityEnd())) {
                                                        ValidityEnd = ApplicationUtils.dateToString(comBean.getValidityEnd(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getVendorInvoiceNumber())) {
                                                        VendorInvoiceNumber = comBean.getVendorInvoiceNumber();
                                                        flag=2;
                                                        VendorInvNumber =  VendorInvoiceNumber.replace("\\", "\\\\");
                                                    }
                                                    
                                                    if (!ApplicationUtils.isBlank(comBean.getSaveFlag())) {
                                                        Status = comBean.getSaveFlag();
                                                    }
                                                    
                                                     if (!ApplicationUtils.isBlank(comBean.getInvoiceDate())) {
                                                        VendorInvoiceDate = ApplicationUtils.dateToString(comBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                                    }
                                                   

                                            %>



                                            <tbody>
                                          <tr class="info" >                                           
                                            <td align="center"><%=j%></td>                                            
                                            <td align="center" width="15%"><%=Location%></td>                                           
                                            <td align="center" width="15%"><%=PODesc%></td>
                                            <td align="center"><%=POType%></td>                                            
                                            <td align="center"><%=POCreationDate%></td>
                                            <td align="center"><%=SORMDate%></td>   
                                            <td align="center"><%=LiabilityDate%></td>   
                                            <td align="center"><%=PaymentDate%></td> 
                                            <td align="center"><%=VendorInvoiceNumber%></td>
                                            <td align="center"><%=VendorInvoiceDate%></td>
                                            <td align="center"><%=Status%></td> 
                                            <% if(flag==2) { %>
                                            <td align="center"><a href="#nogo" onclick="viewEmpApp('<%=VendorInvNumber%>', '<%=UserNumber%>', '<%=viewAction%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                            <% } %>
                                          </tr>                                        
                                           <%
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>                                                          

                            <% } else {%>
                            <div class="row">                
                                <div class="col-lg-12 col-md-12">                        
                                    <div class="table-responsive">
                                        <div class="col-md-12 text-center"><h3>Paid Invoices</h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                        
                                                    <th>#</th>
                                                    <th class="text-center">Location</th>                                                   
                                                    <th class="text-center">PO Description</th>
                                                    <th class="text-center">PO Type</th>
                                                    <th class="text-center">PO Creation Date</th> 
                                                    <th class="text-center">SES/MIGO Date</th>
                                                    <th class="text-center">Liability Date</th>
                                                    <th class="text-center">Payment Date</th>   
                                                    <th class="text-center">MSEDCL Inv No.</th>
                                                    <th class="text-center">MSEDCL Inv Dt.</th>
                                                    <th class="text-center">Pending With</th>                                                   
                                                    <th class="text-center">View</th>                                                   
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="info" align="center" >                                                      
                                                    <td colspan="12"><%=recordsVar%></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>  

                            <% }%>    
                                 </div>
                            </div> <!-- Closed list ends-->              
               </div>  <!-- /. tab-content  -->
                       
  
                            
                            

                            <input type = "hidden" name="lastValue" id="lastValue" value="" />
                            <input type = "hidden" name="lastValueDataType"   id="lastValueDataType" value="" />

                            <!--%@ include file="paging_buttons.jsp" %-->
                        

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

        </form>   
    </body>
</html>


