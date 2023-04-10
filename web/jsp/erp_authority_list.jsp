<%--
    Document   : erp_vendor_appl_form
    Created on : Jun 10, 2020, 4:06:44 PM
    Author     : pooja
--%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="in.emp.vendor.bean.VendorInputBean"%>
<%@page import="in.emp.vendor.bean.POBean"%>
<%@page import="in.emp.authority.bean.AuthorityBean"%>
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
        LinkedList vendorpsInvList = new LinkedList();
    LinkedList LocationList = new LinkedList();
    LinkedList VendorDtlList = new LinkedList();
    LinkedList VendorInvList = new LinkedList();
    LinkedList VendorInpList = new LinkedList();
    String recordsVar = "No Records To Display !!!";
    String OfficeCode="";
    String OfficeTypeId="";    
    String uiAction = "";
    String LocationName ="ALL";
    
    if (session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) != null) {
        OfficeCode = (String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
        System.out.println("OfficeCode : " + OfficeCode);
    }
     if (session.getAttribute(ApplicationConstants.OFFICE_TYPE_ID_SESSION) != null) {
        OfficeTypeId = (String) session.getAttribute(ApplicationConstants.OFFICE_TYPE_ID_SESSION);
        System.out.println("OfficeTypeId : " + OfficeTypeId);
    }
    
    if (!ApplicationUtils.isBlank(request.getParameter("uiActionName"))) {
        uiAction = request.getParameter("uiActionName");
    }
    
     VendorPrezData vendorPrezDataObj = new VendorPrezData();
     VendorPrezData vendorPrezDataObjOne = new VendorPrezData();
     VendorPrezData vendorPrezDataObjTwo = new VendorPrezData();
     VendorPrezData vendorPrezDataObjThree = new VendorPrezData();
     VendorBean vendorBean = new VendorBean();
     VendorBean vendorBeanOne = new VendorBean();
     String hdnPoOption = "";
     String subAction = "";
     
     if(!(uiAction.equals(ApplicationConstants.UIACTION_GET_RESET_LIST))){
         
    if (request.getSession().getAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA) != null) {
        vendorPrezDataObj = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA);        
        POList = (LinkedList) vendorPrezDataObj.getPOList(); 
        vendorpsInvList=(LinkedList) vendorPrezDataObj.getVendorInvList();
        LocationList = (LinkedList) vendorPrezDataObj.getLocationList();
        VendorDtlList = (LinkedList) vendorPrezDataObj.getVendorDtlList();
        VendorInvList =  (LinkedList) vendorPrezDataObj.getVendorInvoiceList();       
        vendorBean = (VendorBean) vendorPrezDataObj.getVendorBean(); 
        
        if (!ApplicationUtils.isBlank(vendorBean.getHdnPoOption())) {
            hdnPoOption = vendorBean.getHdnPoOption();
           
        }
    }
         if((uiAction.equals(ApplicationConstants.UIACTION_GET_AUTH_INVOICE_LIST))){
     if (request.getSession().getAttribute(ApplicationConstants.AUTHORITY_INVLIST_SESSION_DATA) != null) {
          vendorPrezDataObjOne = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.AUTHORITY_INVLIST_SESSION_DATA);        
        
        vendorList = (LinkedList) vendorPrezDataObjOne.getVendorList();
        vendorBeanOne = (VendorBean) vendorPrezDataObjOne.getVendorBean();  
        VendorInpList = (LinkedList) vendorPrezDataObjOne.getVendorInputList();
        
        
    }}
}
     if(uiAction.equals(ApplicationConstants.UIACTION_GET_AUTH_RESET_LIST)){
     if (request.getSession().getAttribute(ApplicationConstants.AUTHORITY_POLIST_SESSION_DATA) != null) {
        vendorPrezDataObjTwo = (VendorPrezData) request.getSession().getAttribute(ApplicationConstants.AUTHORITY_POLIST_SESSION_DATA);        
        POList = (LinkedList) vendorPrezDataObjTwo.getPOList();
        
        VendorDtlList = (LinkedList) vendorPrezDataObjTwo.getVendorDtlList();
       // VendorInvList =  (LinkedList) vendorPrezDataObj.getVendorInvoiceList();
       
       vendorBean = (VendorBean) vendorPrezDataObjTwo.getVendorBean();        
    }
     }
    
    
     String PONumberHdr = "";
     String PODescHdr = "";
     String VInvHdr = "";
     String FrmDtHdr="";
     String ToDtHdr="";
     String VNumHdr = "";
     String VDescHdr = "";
     String LocationHdr="";
     String Module_type="";////MODULE TYPE TO DIFFERENTIATE BETWEEN PO NUMBER AND PROJ ID  
    
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
          
        if (!ApplicationUtils.isBlank(vendorBeanOne.getLocationId())) {
            LocationHdr = vendorBeanOne.getLocationId();
            
            if (!ApplicationUtils.isBlank(LocationList)) { 
            for (POBean pBean : (LinkedList<POBean>) LocationList) {
                    if((!ApplicationUtils.isBlank(LocationHdr)) && pBean.getLocationCode().equals(LocationHdr) ) {
                       LocationName = pBean.getLocationCode() + "-" +pBean.getLocationName();
                    }
                  }
               } 
           }
           if (!ApplicationUtils.isBlank(vendorBeanOne.getVendorInvoiceNumber())) {
            VInvHdr = vendorBeanOne.getVendorInvoiceNumber();
          }  
           if (!ApplicationUtils.isBlank(vendorBeanOne.getPONumber())) {
            PODescHdr = vendorBeanOne.getPONumber() ;
          } 
           if ((vendorBeanOne.getInvoiceFromDate() != null) && !(vendorBeanOne.getInvoiceFromDate().equals("null")) && !(vendorBeanOne.getInvoiceFromDate().equals(""))) {
            FrmDtHdr = ApplicationUtils.dateToString(vendorBeanOne.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
          }
          if ((vendorBeanOne.getInvoiceToDate() != null) && !(vendorBeanOne.getInvoiceToDate().equals("null")) && !(vendorBeanOne.getInvoiceToDate().equals(""))) {
            ToDtHdr = ApplicationUtils.dateToString(vendorBeanOne.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
          }
          
           if (!ApplicationUtils.isBlank(vendorBeanOne.getVendorNumber())) {
            VNumHdr = vendorBeanOne.getVendorNumber();
            
            if (!ApplicationUtils.isBlank(VendorDtlList)) { 
            for (POBean pBean : (LinkedList<POBean>) VendorDtlList) {
                    if((!ApplicationUtils.isBlank(VNumHdr)) && pBean.getVendorNumber().equals(VNumHdr) ) {
                       VDescHdr = pBean.getVendorNumber() + "-" +pBean.getVendorName();
                    }
                  }
               }
            
          }
       }
    }

   
    String UserNumber = (String)request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION);
   // String actionStatus = "Submitted";
    String viewAction = "";
    String viewActionInput = "";
  String viewAction1 = "";
  String viewActionVerified="";
  String viewEmpActionVerified="";
    
    if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_AUTH_INVOICE_LIST)) {
            //actionStatus = "Saved";
            viewAction = "redir";
            viewActionInput = "redirInput";
            viewAction1 = "redir_ps";
            viewActionVerified ="redirVerifiedform";
            viewEmpActionVerified="redirEmpVerifiedform";
        }
    }

    LinkedList applListNew = new LinkedList();
    LinkedList applListOld = new LinkedList();

    
    
     if (!ApplicationUtils.isBlank(uiAction)) {
        if (uiAction.equals(ApplicationConstants.UIACTION_GET_AUTH_INVOICE_LIST)) {
            for (VendorBean o : (LinkedList<VendorBean>) vendorList) {
                if(o.getINV_STATUS()!=null){
                 if ((o.getINV_STATUS().equals("Pending With Technical")) || (o.getINV_STATUS().equals("Pending With Accounts")) || (o.getINV_STATUS().equals("Pending For Payment")) )  {
                      applListNew.add(o); 
                   } else if (o.getINV_STATUS().equals("Paid")) 
                     {
                      applListOld.add(o);
                }
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
  <script src="assets/js/jquery-1.10.2.js?v=<%=System.getProperty("VERSION")%>"></script>
        <script src="<%=ApplicationConstants.JS_PATH%>html5shiv.js"></script> <!-- <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>-->
        <script src="<%=ApplicationConstants.JS_PATH%>respond.js"></script> <!--<script type='text/javascript' src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>-->
        <jsp:include page="nav_jscss.jsp" />
        <script type="text/javascript" language="JavaScript" src="<%=ApplicationConstants.JS_PATH%>erp_authority_list.js"></script>

    </head>
    
  
   
    <body>          
        <form name="searchform" method="post" action="<%=ApplicationUtils.getActionURL(request)%>">
            <div id="wrapper">
                <%@ include file="nav_emp_header.jsp"%>
                <!-- /. NAV TOP  -->
                <%@ include file="emp_nav_vmenu.jsp"%>
                <!-- /. NAV SIDE  -->
                <div id="page-wrapper" style="width:100%;padding-left: 15%;">

                    <div id="page-inner" style="min-height:500px;">
                     <input type="hidden" name="redirEmpVerifiedform" id="redirEmpVerifiedform" value="<%=ApplicationConstants.UIACTION_GET_EMP_VERIFIED_FORM_PS%>"/>
                    <input type="hidden" name="redirVerifiedform" id="redirVerifiedform" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_VERIFIED_FORM%>"/>
                        <input type="hidden" name="uiActionName" id="uiActionName" value="<%=uiAction%>"/>
                        <input type="hidden" name="redir" id="redir" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_APPL_FORM%>"/>
                          <input type="hidden" name="redir_ps" id="redir_ps" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_PS_APPL_FORM%>"/>
                        <input type="hidden" name="redirInput" id="redirInput" value="<%=ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM%>"/>
                        <input type="hidden" name="deleteAction" id="deleteAction" value="<%=ApplicationConstants.UIACTION_POST_VENDOR_LIST%>"/>
                        <input type="hidden" name="deleteRedirect" id="deleteRedirect" value="<%=ApplicationConstants.UIACTION_GET_AUTHORITY_LIST%>"/>
                        <input type="hidden" name="poOptions" id="poOptions" value='[<% if(!ApplicationUtils.isBlank(POList)) {
                                                    //out.print('[' );
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
                                                        out.print("\" " + d.replace('"', ' ').replace('\'', ' ')  +"\"" );                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     j++;
                                                      }
                                                   }
                                                      
                                               // out.print( ']' );
                                                %>]'/>
                        
                        
                        
                        
                        <input type="hidden" name="locOptions" id="locOptions" value='[<% if(!ApplicationUtils.isBlank(LocationList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                    Set <String>locList = new HashSet<String>();
                                                 for (POBean pBean : (LinkedList<POBean>) LocationList) {
                                                     locList.add(pBean.getLocationCode() + "-" + pBean.getLocationName());
                                                     i++;
                                                 }
                                                 int j=0;
                                                 for(String d:locList)
                                                 {
                                                      if(j!=0){ out.print(" , ");}
                                                         out.print("\"" + d.replace('"', ' ').replace('\'', ' ')  +"\"" );                                                     // out.print("\"" + pBean.getPONumber() + "\""  );
                                                     j++;
                                                      }
                                                   }
                                               // out.print( ']' );
                                                %>]'/>
                        
                          <input type="hidden" name="invNumOptions" id="invNumOptions" value='[<% if(!ApplicationUtils.isBlank(VendorInvList)) {
                                                      int i=0;
                                                 for (VendorBean invBean : (LinkedList<VendorBean>) VendorInvList) {
                                                      if(i!=0){ out.print(" , ");}
                                                  out.print("\" " + invBean.getVendorInvoiceNumber().replace('"', ' ').replace('\'', ' ')+ "\""  );
                                                     i++;
                                                      }
                                                   
                                                 
                                                    }
                                               
                                                %>]'/>
                          
                          <input type="hidden" name="VNumOptions" id="VNumOptions" value='[<% if (!ApplicationUtils.isBlank(VendorDtlList)) {
                                                    //out.print('[' );
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
 <input type="hidden" name="VendorInvOptions" id="VendorInvOptions" value='[<% if(!ApplicationUtils.isBlank(vendorpsInvList)) {
                                                    //out.print('[' );
                                                    int i=0;
                                                 for (POBean pBean : (LinkedList<POBean>) vendorpsInvList) {
                                                      if(i!=0){ out.print(" , ");}
                                                  out.print("\"" + pBean.getVendor_Invoice_Number().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "\""  );
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
                      <!--       <tr> <!-- Start of Network Search Results tr -->
                      <!--            <td class="bg_white">  <!-- Start of Network Search Results td -->
                      <!--               <!--<div class="form">-->  <!-- Start of  div  form -->
                       <!--                    <div class="col-md-12"><h3><fmt:message key='List of Invoices'/></h3></div> -->
                       <!--                   <div >&nbsp;</div> -->
                           <!--           </div> -->
                         <!--         </td> -->
                           <!--   </tr>   -->
                        </table>
                            
                                
                                    <table class="table" style="width:100%" id="tblone" border="0" cellspacing="0" cellpadding="1"   >
                                       
                                        <tr>
                                            <td class="text-right h5"><fmt:message key='PO/Project Id Description'/></td>
                                        <td id="myDropdown">
                                            <div class="autocomplete" style="width:300px;">
                                                <input autocomplete="off" type="text" name="txtPONumber" id="txtPONumber" style="width: 100%" value ="<%=PODescHdr%>"  title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> class="form-control" onkeypress="getPOSearchList();"  />
                                              </div>        
                                          </td>
                                          <td class="text-right h5" colspan="2"><fmt:message key='VENDOR Inv No'/>.</td>
                                          <td id="myDropdownOne">
                                            <div class="autocomplete" style="width:300px;">
                                                <input type="text" name="txtInvoiceNumber" id="txtInvoiceNumber" style="width: 100%" value ="<%=VInvHdr%>" title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> class="form-control" onkeypress="getInvSearchList();"  />
                                              </div>        
                                          </td>                                          
                                    </tr>
                                     <tr>
                                         <td class="text-right h5"><fmt:message key='Vendor'/></td>
                                         <td id="myDropdownTwo">
                                            <div class="autocomplete" style="width:300px;">
                                                <input type="text" name="txtVendorNumber" id="txtVendorNumber" style="width: 100%" value ="<%=VDescHdr%>" title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> class="form-control" onkeypress="getVendorSearchList();"  />
                                              </div>        
                                          </td> 
                                          <td class="text-right h5" colspan="2"><fmt:message key='Location'/></td>
                                           <td id="myDropdownThree">
                                            <div class="autocomplete" style="width:300px;">
                                                <input type="text" name="txtLocation" id="txtLocation" style="width: 100%" value ="<%=LocationName%>" title="Type and search or use space-bar" placeholder=<fmt:message key='"Type and search or use space-bar"'/> class="form-control" onkeypress="getLocSearchList();"  />
                                              </div>        
                                          </td>   
                                          
                                          </tr>
                                          
                                          <tr>
                                              <td width="20%" class="text-right h5"><fmt:message key='Invoice submitted during the period From Date'/></td>
                                                <td width="5%"> 
                                                    <input name="txtFrmDt" id="txtFrmDt" type="text" size="20" value="<%=FrmDtHdr%>"  class="datefield" maxlength="15" readonly="readonly"/> </td>
                                                <td width="5%">
                                                 <!--   <a href="javascript:void(0)" onClick="return callCalender('txtFrmDt','1');">
                                                        <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />
                                                    </a-->
                                           </td>
                                           
                                           <td width="20%" class="text-right h5"><fmt:message key='Invoice submitted during the period To Date'/></td>
                                                <td width="5%"> 
                                                    <input name="txtToDt" id="txtToDt" type="text" size="20" value="<%=ToDtHdr%>" class="datefield" maxlength="15" readonly="readonly" </td>
                                                <td width="5%">
                                                  <!--  <a href="javascript:void(0)" onClick="return callCalender('txtToDt','1');">
                                                        <img src="<%=ApplicationConstants.IMAGE_PATH%>icon_calendar2.gif" alt="Calander" width="25" height="25" border="0" align="absmiddle" />
                                                    </a>-->
                                           </td>
                                          </tr>
                                          <tr>
                                              <td class="col-md-1">
                                              </td>
                                               <td class="col-md-1">
                                              </td>
                                               <td class="col-md-2">
                                                    <div class="hor_nav_normal">
                                                        <input type="button" value=<fmt:message key='"Get List"'/> name="ButtonGetList" id="ButtonGetList" onclick="getList()"
                                                         class="btn  btn-success"/>
                                              </div>
                                              </td>
                                              
                                              <td class="col-md-1">
                                              </td>
                                               <td class="col-md-2">
                                                     <div >
                                                         <input type="button" value=<fmt:message key='Reset'/> name="ButtonReset" id="ButtonReset" onclick="Reset()"
                                                         class="btn  btn-success"/> 
                                               
                                            </div>
                                              </td>
                                             
                                          </tr>
                                           </table>
                                         
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#vendorInputList"><fmt:message key='Vendor Input Invoices'/></a></li>
                            <li ><a data-toggle="tab" href="#pendingList"><fmt:message key='Pending Invoices'/></a></li>
                            <li><a data-toggle="tab" href="#closedList"><fmt:message key='Paid Invoices'/></a></li>
                        
                        </ul>
                              
                        <div class="tab-content">
                            <%@ include file="sort_paging_hidden_fields.jsp" %>                      
                              <div id="vendorInputList" class="tab-pane fade in active">
                                <div class="content_container_sub"> 
                           
                           <div class="row">                
                                <div class="col-lg-12 col-md-12" align="center">                        
                                    <div class="table-responsive" style="padding-bottom:15px">
                                         <%
                                if (VendorInpList.size() != 0) {
                            %>   
                                     <!--   <select id="txtSearchStatus" name="txtSearchStatus" class="form-control" style="width: 20%;height:29px;float:right" >
                                                            <option>- <fmt:message key='SELECT STATUS'/> -</option> 
                                                            <option value="<%=ApplicationConstants.ALL%>"><fmt:message key='ALL'/></option> 
                                                            <option value="<%=ApplicationConstants.VERIFIED%>"><fmt:message key='Verified'/></option>
                                                            <option value="<%=ApplicationConstants.REJECTED%>"><fmt:message key='Rejected'/></option>
                                                            <option value="<%=ApplicationConstants.SUBMITTED%>"><fmt:message key='Submitted'/></option>
                                                           
                                                        </select>-->
                                        
                                        
                                      <!--         <div class="col-md-12 text-center tbl-content"><h3><fmt:message key='List of Vendor Input Invoices'/></h3></div> -->
                                     <section>
  <!--for demo wrap-->
  <div class="text-center " stye="padding-bottom:20px">
      <h style="font-weight:bold;color:#1434A4" ><fmt:message key='List of Vendor Input Invoices'/></h></div>
      <br>
  <div class="tbl-header" >
                                        <table class="table" id="tableinputinvoices">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th>#</th> 
                                                    <th><fmt:message key='Application ID'/></th>  
                                                    <th><fmt:message key='Module Type'/></th> 
                                                    <th><fmt:message key='Location'/></th>  
                                                    <th><fmt:message key='PO/Project Id Description'/></th> 
                                                    <th><fmt:message key='Vendor'/></th>                                                   
                                                    <th><fmt:message key='Invoice Number'/></th>                                                                                                       
                                                    <th><fmt:message key='Invoice Date'/></th>
                                                    <th width="12%"><fmt:message key='Invoice Amount(Incl. Taxes)'/></th>
                                                    <th><fmt:message key='Inward Number'/></th>                                                    
                                                        <th><fmt:message key='Inward Date'/></th>
                                                        <th><fmt:message key='Updated On'/></th>
                                                        <th><fmt:message key='Status'/></th> 
                                                        <th><fmt:message key='View'/></th>                                                    
                                               

 </tr>
                                            </thead>

                                            <tbody>
                                         <%
                                
                                     int j = 0;
                                   
                                    
                                    for (VendorInputBean vendorInputBean : (LinkedList<VendorInputBean>) VendorInpList) {
                                                    j++;
                                       String ApplId = "";
                                    String ApplDate="";
                                    String PONumber = "";
                                    String PODesc = "";
                                    String VendorNumber = "";
                                    String VendorName = "";
                                    String InvoiceNum = "";
                                    String InvoiceDate = "";
                                    String InvoiceAmt = "";
                                    String InwardNum = "";
                                    String InwardDate = "";
                                    String InvoiceFrmDate = "";
                                    String InvoiceToDate = "";
                                    String InvoiceUpdatedOn="";
                                    String Status = "";
                                    String module="";
                                    String Location="";
                                    String Module_dtl="";
                                    String ForwardToPlant="";
                                    
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getApplId())) {
                                       ApplId = vendorInputBean.getApplId();
                                     } 
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getSelectedModuleType())) {//to differentiate between proj id and proj system
                                       module = vendorInputBean.getSelectedModuleType();
                                        if(module.equals("PM")){
                                           Module_dtl="Procurement/Works";
                                       }else if(module.equals("PS")){
                                           Module_dtl="Project System";
                                       }
                                     }
                                      if ((vendorInputBean.getApplDate() != null) && !(vendorInputBean.getApplDate().equals("null")) && !(vendorInputBean.getApplDate().equals(""))) {
                                      ApplDate = ApplicationUtils.dateToString(vendorInputBean.getApplDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getPONumber())) {
                                       PONumber = vendorInputBean.getPONumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getPODesc())) {
                                       PODesc = PONumber + "-" + vendorInputBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-',' ') ;
                                     }
                                      else{
                                        PODesc = PONumber; 
                                     }
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getVendorNumber())) {
                                       VendorNumber = vendorInputBean.getVendorNumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getVendorName())) {
                                       VendorName = VendorNumber + "-" + vendorInputBean.getVendorName();
                                     }
                                     
                                     if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceNumber())) {
                                       InvoiceNum = vendorInputBean.getVendorInvoiceNumber();
                                     }
                                     
                                      if ((vendorInputBean.getVendorInvoiceDate() != null) && !(vendorInputBean.getVendorInvoiceDate().equals("null")) && !(vendorInputBean.getVendorInvoiceDate().equals(""))) {
                                      InvoiceDate = ApplicationUtils.dateToString(vendorInputBean.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getVendorInvoiceAmount())) {
                                       InvoiceAmt = vendorInputBean.getVendorInvoiceAmount();
                                        InvoiceAmt =  ApplicationUtils.formatAmount(Double.valueOf(InvoiceAmt));
                                     }
                                      
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getInwardNumber())) {
                                       InwardNum = vendorInputBean.getInwardNumber();
                                     }
                                     
                                      if ((vendorInputBean.getInwardDate() != null) && !(vendorInputBean.getInwardDate().equals("null")) && !(vendorInputBean.getInwardDate().equals(""))) {
                                      InwardDate = ApplicationUtils.dateToString(vendorInputBean.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                      
                                    if ((vendorInputBean.getInvoiceFromDate() != null) && !(vendorInputBean.getInvoiceFromDate().equals("null")) && !(vendorInputBean.getInvoiceFromDate().equals(""))) {
                                      InvoiceFrmDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    }
                                      if ((vendorInputBean.getInvoiceToDate() != null) && !(vendorInputBean.getInvoiceToDate().equals("null")) && !(vendorInputBean.getInvoiceToDate().equals(""))) {
                                        InvoiceToDate = ApplicationUtils.dateToString(vendorInputBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    }
                                       if ((vendorInputBean.getVendorUpdatedDate() != null) && !(vendorInputBean.getVendorUpdatedDate().equals("null")) && !(vendorInputBean.getVendorUpdatedDate().equals(""))) {
                                       InvoiceUpdatedOn = ApplicationUtils.dateToString(vendorInputBean.getVendorUpdatedDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                       }
                                      if (!ApplicationUtils.isBlank(vendorInputBean.getSaveFlag())) {
                                       Status = vendorInputBean.getSaveFlag();
                                     }
                                       if (!ApplicationUtils.isBlank(vendorInputBean.getLocation())) {
                                                        Location =  vendorInputBean.getLocation();
                                                    }  
                                       if (!ApplicationUtils.isBlank(vendorInputBean.getForwardToPlant())) {
                                                        ForwardToPlant =  vendorInputBean.getForwardToPlant();
                                                    }
                            %>

                                       

                                          
                                            
                                                <tr data-type="<%=Status%>" class="info" >
                                            <td><%=j%></td>
                                            <td><%=ApplId%></td> 
                                            <td><%=Module_dtl%></td>
                                            <td><%=Location%></td>
                                            <td><%=PODesc%></td>                                          
                                            <td><%=VendorName%></td>
                                            <td><%=InvoiceNum%></td>
                                            <td><%=InvoiceDate%></td>                                           
                                            <td><%=InvoiceAmt%></td>                                            
                                            <td><%=InwardNum%></td>
                                            <td><%=InwardDate%></td> 
                                            <td><%=InvoiceUpdatedOn%></td>
                                            <td><%=Status%></td> 
                                             <td><a href="#nogo" onclick="viewEmpApp1('<%=ApplId%>', '<%=UserNumber%>', '<%=viewActionInput%>','<%=PONumber%>','<%=module%>','<%=Status%>','<%=ForwardToPlant%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a></td>
                                             </tr>
                                           <% } %>
                                            </tbody>
                                        </table>
                                    </div>
                                     </section>
                                    </div>
                        <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" class="btn btn-danger"><fmt:message key='Back'/></a>

                                </div>
                            </div>  
                                            

                            <%  } else {%>
                             <div class="row">                
                                <div class="col-lg-12 col-md-12">                        
                                    <div class="table-responsive">
                                         <div class="table-responsive">
                                       <!--<select id="txtSearchStatus" name="txtSearchStatus" class="form-control" style="width: 20%;height:29px;float:right"  onchange='checkstatus(this.value)'>
                                                            <option>- <fmt:message key='SELECT STATUS'/> -</option> 
                                                            <option><fmt:message key='ALL'/></option> 
                                                            <option><fmt:message key='Verified'/></option>
                                                            <option><fmt:message key='Rejected'/></option>
                                                            <option><fmt:message key='Submitted'/></option>
                                                           
                                                           
                                                        </select>-->
                                        <div class="col-md-12 text-center"><h3><fmt:message key='List of Vendor Input Invoices'/></h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                        
                                                    <th>#</th> 
                                                    <th><fmt:message key='Application ID'/></th>  
                                                     <th><fmt:message key='Module Type'/></th> 
                                                    <th><fmt:message key='PO/Project Id Description'/></th>
                                                    <th><fmt:message key='Vendor'/></th>                                                   
                                                    <th><fmt:message key='Invoice Number'/></th>                                                                                                       
                                                    <th><fmt:message key='Invoice Date'/></th>
                                                    <th width="12%"><fmt:message key='Invoice Amount(Incl. Taxes)'/></th>
                                                    <th><fmt:message key='Inward Number'/></th>                                                    
                                                        <th><fmt:message key='Inward Date'/></th> 
                                                        <th><fmt:message key='Status'/></th> 
                                                        <th><fmt:message key='Updated On'/></th>
                                                        <th><fmt:message key='View'/></th>   
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="info" align="center" >                                                      
                                                    <td colspan="13"><%=recordsVar%></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                                
                                           <a href="<%=ApplicationUtils.getRenderURL(request, ApplicationConstants.UIACTION_NAME, ApplicationConstants.UIACTION_HOME_GET)%>" class="btn btn-danger"><fmt:message key='Back'/></a>

                                </div>
                            </div>  

                            <% }%>                            

                                    <!--%@ include file="paging_buttons.jsp" %-->
                                </div>
                            </div>          
                            <div id="pendingList" class="tab-pane fade ">
                                <div class="content_container_sub">
                                         <%
                                if (applListNew.size() != 0) {
                            %>
                                     <div class="row">
                                        <div class="col-lg-12 col-md-12">
                                            <div class="table-responsive">
                                           
                                                <div class="col-md-12 text-center"><h3><fmt:message key='Pending Invoices'/></h3></div>
                                        <table class="table" id="tablependinginvoices">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th>#</th> 
                                                    <th><fmt:message key='Application ID'/></th>  
                                                    <th><fmt:message key='Module Type'/></th> 
                                                    <th><fmt:message key='Location'/></th>  
                                                    <th><fmt:message key='PO/Project Id Description'/></th> 
                                                    <th><fmt:message key='Vendor'/></th>                                                   
                                                    <th><fmt:message key='Invoice Number'/></th>                                                                                                       
                                                    <th><fmt:message key='Invoice Date'/></th>
                                                    <th width="12%"><fmt:message key='Invoice Amount(Incl. Taxes)'/></th>
                                                    <th><fmt:message key='Inward Number'/></th>                                                    
                                                        <th><fmt:message key='Inward Date'/></th>
                                                      
                                                        <th><fmt:message key='Status'/></th> 
                                                        <th><fmt:message key='View'/></th>                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <%                                 
                                                                                             
                                                int j = 0;

                                                for (VendorBean comBean : (LinkedList<VendorBean>) applListNew) {
                                                    String ApplId = "";
                                    String ApplDate="";
                                    String PONumber = "";
                                    String PODesc = "";
                                    String VendorNumber = "";
                                    String VendorName = "";
                                    String InvoiceNum = "";
                                    String InvoiceDate = "";
                                    String InvoiceAmt = "";
                                    String InwardNum = "";
                                    String InwardDate = "";
                                    String InvoiceFrmDate = "";
                                    String InvoiceToDate = "";
                                    String InvoiceUpdatedOn="";
                                    String Status = "";   //MODULE TYPE TO DIFFERENTIATE BETWEEN PO NUMBER AND PROJ ID  
                                    String Module_dtl="";
                                    String module="";
                                    String Location="";
                                    
                                                int flag =0;
                                                    j++;

                                                  if (!ApplicationUtils.isBlank(comBean.getApplId())) {
                                       ApplId = comBean.getApplId();
                                     } 
                                      if (!ApplicationUtils.isBlank(comBean.getSELECTED_MODULE_TYPE())) {//to differentiate between proj id and proj system
                                       module = comBean.getSELECTED_MODULE_TYPE();
                                         if(module.equals("PM")){
                                           Module_dtl="Procurement/Works";
                                       }else if(module.equals("PS")){
                                           Module_dtl="Project System";
                                       }
                                     }
                                      if ((comBean.getInvoiceDate()!= null) && !(comBean.getInvoiceDate().equals("null")) && !(comBean.getInvoiceDate().equals(""))) {
                                      ApplDate = ApplicationUtils.dateToString(comBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     if (!ApplicationUtils.isBlank(comBean.getPONumber())) {
                                       PONumber = comBean.getPONumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(comBean.getPODesc())) {
                                       PODesc = PONumber + "-" + comBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-',' ') ;
                                     }
                                      else{
                                        PODesc = PONumber; 
                                     }
                                      if (!ApplicationUtils.isBlank(comBean.getVendorNumber())) {
                                       VendorNumber = comBean.getVendorNumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(comBean.getVendorName())) {
                                       VendorName = VendorNumber + "-" + comBean.getVendorName();
                                     }
                                     
                                     if (!ApplicationUtils.isBlank(comBean.getInvoiceNumber())) {
                                       InvoiceNum = comBean.getInvoiceNumber();
                                     }
                                     
                                      if ((comBean.getInvoiceDate()!= null) && !(comBean.getInvoiceDate().equals("null")) && !(comBean.getInvoiceDate().equals(""))) {
                                      InvoiceDate = ApplicationUtils.dateToString(comBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(comBean.getInvoiceAmount())) {
                                       InvoiceAmt = comBean.getInvoiceAmount();
                                        InvoiceAmt =  ApplicationUtils.formatAmount(Double.valueOf(InvoiceAmt));
                                     }
                                      
                                      if (!ApplicationUtils.isBlank(comBean.getInwardNumber())) {
                                       InwardNum = comBean.getInwardNumber();
                                     }
                                     
                                      if ((comBean.getInwardDate() != null) && !(comBean.getInwardDate().equals("null")) && !(comBean.getInwardDate().equals(""))) {
                                      InwardDate = ApplicationUtils.dateToString(comBean.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                      
                                    if ((comBean.getInvoiceFromDate() != null) && !(comBean.getInvoiceFromDate().equals("null")) && !(comBean.getInvoiceFromDate().equals(""))) {
                                      InvoiceFrmDate = ApplicationUtils.dateToString(comBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    }
                                      if ((comBean.getInvoiceToDate() != null) && !(comBean.getInvoiceToDate().equals("null")) && !(comBean.getInvoiceToDate().equals(""))) {
                                        InvoiceToDate = ApplicationUtils.dateToString(comBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    }
                                      
                                      if (!ApplicationUtils.isBlank(comBean.getINV_STATUS())) {
                                       Status = comBean.getINV_STATUS();
                                     }
                                       if (!ApplicationUtils.isBlank(comBean.getLocationName())) {
                                                        Location = comBean.getLocationName();
                                                    }
                                            %>


                                           
                                                <tr data-type="<%=Status%>" class="info" >
                                            <td><%=j%></td>
                                            
                                            <td><%=ApplId%></td> 
                                            <td><%=Module_dtl%></td>
                                            <td><%=Location%></td>
                                            <td><%=PODesc%></td>                                          
                                            <td><%=VendorName%></td>
                                            <td><%=InvoiceNum%></td>
                                            <td><%=InvoiceDate%></td>                                           
                                            <td><%=InvoiceAmt%></td>                                            
                                            <td><%=InwardNum%></td>
                                            <td><%=InwardDate%></td> 
                                           
                                            <td><%=Status%></td> 
                                    <td>
                                             <%if(module.equals("PS")){ %>
<a href="#nogo" onclick="viewEmpAppVerified_PS('<%=ApplId%>','<%=InvoiceNum.toUpperCase().replaceAll("[^a-zA-Z0-9]", "")%>',' <%=VendorNumber%>','<%=viewEmpActionVerified%>','<%=PONumber%>','<%=module%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a>
<% }else{ %>
<a href="#nogo" onclick="viewEmpAppVerified('<%=ApplId%>','<%=InvoiceNum.toUpperCase().replaceAll("[^a-zA-Z0-9]", "")%>',' <%=VendorNumber%>','<%=viewActionVerified%>','<%=PONumber%>','<%=module%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a>                                                 <% } %>
                                            </td>
                                          
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
                                          <!--<select id="txtSearchStatus" name="txtSearchStatus" class="form-control" style="width: 20%;height:29px;float:right"  onchange='checkstatuspending(this.value)'>
                                                            <option>- <fmt:message key='SELECT STATUS'/> -</option> 
                                                            <option value="<%=ApplicationConstants.ALL%>"><fmt:message key='ALL'/></option> 
                                                            <option value="<%=ApplicationConstants.TECHNICAL%>"><fmt:message key='Pending With Technical'/></option>
                                                            <option value="<%=ApplicationConstants.ACCOUNTS%>"><fmt:message key='Pending With Accounts'/></option>
                                                            <option value="<%=ApplicationConstants.PAYMENT%>"><fmt:message key='Pending For Payment'/></option>
                                                           
                                                        </select>-->
                                        <div class="col-md-12 text-center"><h3><fmt:message key='Pending Invoices'/></h3></div>
                                        <table class="table">
                                           <thead>
                                                <tr class="success">                                                                                       
                                                    <th>#</th> 
                                                    <th><fmt:message key='Application ID'/></th>  
                                                    <th><fmt:message key='Module Type'/></th> 
                                                    <th><fmt:message key='Location'/></th>  
                                                    <th><fmt:message key='PO/Project Id Description'/></th> 
                                                    <th><fmt:message key='Vendor'/></th>                                                   
                                                    <th><fmt:message key='Invoice Number'/></th>                                                                                                       
                                                    <th><fmt:message key='Invoice Date'/></th>
                                                    <th width="12%"><fmt:message key='Invoice Amount(Incl. Taxes)'/></th>
                                                    <th><fmt:message key='Inward Number'/></th>                                                    
                                                        <th><fmt:message key='Inward Date'/></th>
                                                      
                                                        <th><fmt:message key='Status'/></th> 
                                                        <th><fmt:message key='View'/></th>                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="info" align="center" >                                                      
                                                    <td colspan="14"><%=recordsVar%></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>  

                            <% }%>
                                  

                                    <!--%@ include file="paging_buttons.jsp" %-->
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
                                        
                                        <div class="col-md-12 text-center"><h3><fmt:message key='Paid Invoices'/></h3></div>
                                        <table class="table" id="tablepaidinvoices">
                                                <thead>
                                                <tr class="success">                                                                                       
                                                    <th>#</th> 
                                                    <th><fmt:message key='Application ID'/></th>  
                                                    <th><fmt:message key='Module Type'/></th> 
                                                    <th><fmt:message key='Location'/></th>  
                                                    <th><fmt:message key='PO/Project Id Description'/></th> 
                                                    <th><fmt:message key='Vendor'/></th>                                                   
                                                    <th><fmt:message key='Invoice Number'/></th>                                                                                                       
                                                    <th><fmt:message key='Invoice Date'/></th>
                                                    <th width="12%"><fmt:message key='Invoice Amount(Incl. Taxes)'/></th>
                                                    <th><fmt:message key='Inward Number'/></th>                                                    
                                                        <th><fmt:message key='Inward Date'/></th>
                                                      
                                                        <th><fmt:message key='Status'/></th> 
                                                        <th><fmt:message key='View'/></th>                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                        <%
                                                                                        
                                                int j = 0;

                                                for (VendorBean comBean : (LinkedList<VendorBean>) applListOld) {
                                                    j++;
                                                    
                                                  String ApplId = "";
                                    String ApplDate="";
                                    String PONumber = "";
                                    String PODesc = "";
                                    String VendorNumber = "";
                                    String VendorName = "";
                                    String InvoiceNum = "";
                                    String InvoiceDate = "";
                                    String InvoiceAmt = "";
                                    String InwardNum = "";
                                    String InwardDate = "";
                                    String InvoiceFrmDate = "";
                                    String InvoiceToDate = "";
                                    String InvoiceUpdatedOn="";
                                    String Status = "";
                                    String module="";
                                    String Location="";
                                    String Module_dtl="";
                                    
                                                int flag =0;

                                                    if (!ApplicationUtils.isBlank(comBean.getApplId())) {
                                       ApplId = comBean.getApplId();
                                     } 
                                      if (!ApplicationUtils.isBlank(comBean.getSELECTED_MODULE_TYPE())) {//to differentiate between proj id and proj system
                                       module = comBean.getSELECTED_MODULE_TYPE();
                                          if(module.equals("PM")){
                                           Module_dtl="Procurement/Works";
                                       }else if(module.equals("PS")){
                                           Module_dtl="Project System";
                                       }
                                     }
                                      if ((comBean.getInvoiceDate()!= null) && !(comBean.getInvoiceDate().equals("null")) && !(comBean.getInvoiceDate().equals(""))) {
                                      ApplDate = ApplicationUtils.dateToString(comBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     if (!ApplicationUtils.isBlank(comBean.getPONumber())) {
                                       PONumber = comBean.getPONumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(comBean.getPODesc())) {
                                       PODesc = PONumber + "-" + comBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-',' ') ;
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(comBean.getVendorNumber())) {
                                       VendorNumber = comBean.getVendorNumber();
                                     } 
                                    
                                     if (!ApplicationUtils.isBlank(comBean.getVendorName())) {
                                       VendorName = VendorNumber + "-" + comBean.getVendorName();
                                     }
                                     
                                     if (!ApplicationUtils.isBlank(comBean.getInvoiceNumber())) {
                                       InvoiceNum = comBean.getInvoiceNumber();
                                     }
                                     
                                      if ((comBean.getInvoiceDate()!= null) && !(comBean.getInvoiceDate().equals("null")) && !(comBean.getInvoiceDate().equals(""))) {
                                      InvoiceDate = ApplicationUtils.dateToString(comBean.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                     
                                      if (!ApplicationUtils.isBlank(comBean.getInvoiceAmount())) {
                                       InvoiceAmt = comBean.getInvoiceAmount();
                                        InvoiceAmt =  ApplicationUtils.formatAmount(Double.valueOf(InvoiceAmt));
                                     }
                                      
                                      if (!ApplicationUtils.isBlank(comBean.getInwardNumber())) {
                                       InwardNum = comBean.getInwardNumber();
                                     }
                                     
                                      if ((comBean.getInwardDate() != null) && !(comBean.getInwardDate().equals("null")) && !(comBean.getInwardDate().equals(""))) {
                                      InwardDate = ApplicationUtils.dateToString(comBean.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                     }
                                      
                                    if ((comBean.getInvoiceFromDate() != null) && !(comBean.getInvoiceFromDate().equals("null")) && !(comBean.getInvoiceFromDate().equals(""))) {
                                      InvoiceFrmDate = ApplicationUtils.dateToString(comBean.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    }
                                      if ((comBean.getInvoiceToDate() != null) && !(comBean.getInvoiceToDate().equals("null")) && !(comBean.getInvoiceToDate().equals(""))) {
                                        InvoiceToDate = ApplicationUtils.dateToString(comBean.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT);
                                    }
                                      
                                      if (!ApplicationUtils.isBlank(comBean.getINV_STATUS())) {
                                       Status = comBean.getINV_STATUS();
                                     }
                                       if (!ApplicationUtils.isBlank(comBean.getLocationName())) {
                                                        Location =comBean.getLocationName();
                                                    }
                                            %>


                                            
                                                <tr class="info" >
                                            <td><%=j%></td>
                                            
                                            <td><%=ApplId%></td> 
                                            <td><%=Module_dtl%></td>
                                            <td><%=Location%></td>
                                            <td><%=PODesc%></td>                                          
                                            <td><%=VendorName%></td>
                                            <td><%=InvoiceNum%></td>
                                            <td><%=InvoiceDate%></td>                                           
                                            <td><%=InvoiceAmt%></td>                                            
                                            <td><%=InwardNum%></td>
                                            <td><%=InwardDate%></td> 
                                            
                                            <td><%=Status%></td> 
                                             <td>
                                             <%if(module.equals("PS")){ %>
<a href="#nogo" onclick="viewEmpAppVerified_PS('<%=ApplId%>','<%=InvoiceNum.toUpperCase().replaceAll("[^a-zA-Z0-9]", "")%>',' <%=VendorNumber%>','<%=viewEmpActionVerified%>','<%=PONumber%>','<%=module%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a>
                                             
<% }else{ %>
<a href="#nogo" onclick="viewEmpAppVerified('<%=ApplId%>','<%=InvoiceNum.toUpperCase().replaceAll("[^a-zA-Z0-9]", "")%>',' <%=VendorNumber%>','<%=viewActionVerified%>','<%=PONumber%>','<%=module%>');"><img src="images/instructions1.png" alt="Update" width="20" height="20" border="0" /></a>                                                 <% } %>
                                            </td>
                                           
                                       
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
                                        <div class="col-md-12 text-center"><h3><fmt:message key='Paid Invoices'/></h3></div>
                                        <table class="table">
                                            <thead>
                                                <tr class="success">                                                                                       
                                                    <th>#</th> 
                                                    <th><fmt:message key='Application ID'/></th>  
                                                    <th><fmt:message key='Module Type'/></th> 
                                                    <th><fmt:message key='Location'/></th>  
                                                    <th><fmt:message key='PO/Project Id Description'/></th> 
                                                    <th><fmt:message key='Vendor'/></th>                                                   
                                                    <th><fmt:message key='Invoice Number'/></th>                                                                                                       
                                                    <th><fmt:message key='Invoice Date'/></th>
                                                    <th width="12%"><fmt:message key='Invoice Amount(Incl. Taxes)'/></th>
                                                    <th><fmt:message key='Inward Number'/></th>                                                    
                                                        <th><fmt:message key='Inward Date'/></th>
                                                      
                                                        <th><fmt:message key='Status'/></th> 
                                                        <th><fmt:message key='View'/></th>                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="info" align="center" >                                                      
                                                    <td colspan="14"><%=recordsVar%></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>  

                            <% }%>
                           

                                    <!--%@ include file="paging_buttons.jsp" %-->
                                </div>
                            </div> <!-- closed list ends-->
                          
                            <!-- vendor Input list ends-->              

                            
                         
                        </div>  <!-- /. tab-content  -->

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
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
        


</form>   
    </body>
    
</html>


