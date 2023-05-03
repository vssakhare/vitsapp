/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package in.emp.handler;

import in.emp.arch.GenericFormHandler; 
import in.emp.common.ApplicationConstants;
//import in.emp.authority.AuthorityDelegate;
//import in.emp.authority.bean.AuthorityBean;
//import in.emp.authority.bean.AuthorityBean;
//import in.emp.authority.bean.AuthorityPrezData;
//import in.emp.authority.manager.AuthorityManager;
import in.emp.util.ApplicationUtils;
import in.emp.authority.AuthorityDelegate;
import in.emp.authority.bean.AuthorityBean;
import in.emp.authority.bean.AuthorityPrezData;
import in.emp.authority.manager.AuthorityManager;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.ProjBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.manager.VendorManager;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
*
* @author Prajakta
*/
public class AuthorityHandler implements GenericFormHandler {
//private static Logger logger = new Logger(RoleHandler.class.getName());

private static String CLASS_NAME = AuthorityHandler.class.getName();
private static Logger logger = Logger.getLogger(AuthorityHandler.class);

/**
 * API to process a user request
 *
 * @param request object of HttpServletRequest
 * @return String the UI action name
 * @throws Exception
 */
public String execute(HttpServletRequest request) throws Exception {
    String uiActionName = "";
    String sReturnPage = "";

    try {
        logger.log(Level.INFO, "AuthorityHandler :: execute() :: method called");

        uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
        // String subAction = request.getParameter("subAction");
        //  System.out.println("TransferHandler :: execute() :: uiActionName :: " + uiActionName);
        // Getting the parameters from request object for Role application

        if (!ApplicationUtils.isBlank(uiActionName)) {
            
             
            
             if (uiActionName.equals(ApplicationConstants.UIACTION_GET_AUTHORITY_LIST)) {
                sReturnPage = getAuthorityList(request);
             } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_AUTHORITY_FORM)) {
                sReturnPage = getAuthorityApplForm(request);
             } else if (uiActionName.equals(ApplicationConstants.UIACTION_EMP_REPORTS_GET)) {
                sReturnPage = getEmpReports(request);
             } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_AUTH_PO_LIST)) {
                sReturnPage = getAuthPOList(request);
             }
             else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_AUTH_RESET_LIST)) {
                sReturnPage = getAuthResetList(request);
             }
             else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_AUTH_INVOICE_LIST)) {
                sReturnPage = getAuthInvoiceList(request);
             }   else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_AUTH_SUMMARY)) {
                sReturnPage = getAuthSummary(request);
                
             } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_LEGAL_AUTH_SUMMARY)) {
                sReturnPage = getLegalAuthSummary(request);
                
             }
             else if (uiActionName.equals(ApplicationConstants.REPORT_MSEDCL_EMP)) {
                sReturnPage = MSEDCLEmpReport(request);
             }
             else if(uiActionName.equals(ApplicationConstants.UIACTION_GET_LEGAL_VENDOR_INVOICE)) {
                  sReturnPage=  getAuthLegalInvoiceList(request);
                }
            else {
                sReturnPage = ApplicationConstants.UIACTION_HOME_GET;
            }
        }
        logger.log(Level.INFO, "AuthorityHandler :: execute() :: sReturnPage :: " + sReturnPage);
    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: execute() :: Exception :: " + ex);
    }

    return sReturnPage;

} //end execute method

 private String getAuthInvoiceList(HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.UIACTION_GET_AUTH_INVOICE_LIST;  
    VendorPrezData vendorPrezDataObj = new VendorPrezData();  
    VendorPrezData vendorPrezDataObjTwo = new VendorPrezData();   
    VendorInputBean vendorInputBeanObj = new VendorInputBean();
    VendorBean vendorBeanObj = new VendorBean();
    VendorDelegate vendorMgrObj = new VendorManager();
    LinkedList POList = new LinkedList();
    HttpSession session = request.getSession();        
       
        try {
            logger.log(Level.INFO, "AuthorityHandler :: getAuthInvoiceList() :: method called :: ");

           
            vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
             if (session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null) {
            vendorInputBeanObj.setLocationId("");
            vendorBeanObj.setLocationId("");
        } else {
            vendorInputBeanObj.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            vendorBeanObj.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));

        }
             
            vendorBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));    
            vendorBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtVendorNumber"));  
            vendorBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNumber")); 
            vendorBeanObj.setLocationId(ApplicationUtils.getRequestParameter(request, "txtLocation"));    
            
           if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                vendorBeanObj.setInvoiceFromDate(ApplicationUtils.stringToDate((String) request.getParameter("txtFrmDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                vendorBeanObj.setInvoiceToDate(ApplicationUtils.stringToDate((String) request.getParameter("txtToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            } 
            vendorBeanObj.setINV_STATUS(ApplicationUtils.getRequestParameter(request, "pendingval"));
             vendorBeanObj.setUserType("Emp");
             if (vendorBeanObj.getLocationId().equalsIgnoreCase("ALL")  ) {
                if (!(session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null)) {
                    vendorBeanObj.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                }
            }
            vendorPrezDataObj = vendorMgrObj.getVendorList(vendorBeanObj);
             vendorPrezDataObj.setVendorBean(vendorBeanObj);
             
             vendorInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
             vendorInputBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtVendorNumber"));
             vendorInputBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));  
             vendorInputBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNumber")); 
           // vendorInputBeanObj.setLocationOpt(ApplicationUtils.getRequestParameter(request, "txtLocation"));
           if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                vendorInputBeanObj.setInvoiceFromDate(ApplicationUtils.stringToDate((String) request.getParameter("txtFrmDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                vendorInputBeanObj.setInvoiceToDate(ApplicationUtils.stringToDate((String) request.getParameter("txtToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            } 
            vendorInputBeanObj.setSaveFlag(ApplicationUtils.getRequestParameter(request, "val"));
            vendorInputBeanObj.setUserType("Emp");
              vendorPrezDataObjTwo = vendorMgrObj.getVendorInputList(vendorInputBeanObj);
              vendorPrezDataObj.setVendorInputList(vendorPrezDataObjTwo.getVendorInputList());
            // vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);


            session.setAttribute(ApplicationConstants.AUTHORITY_INVLIST_SESSION_DATA, vendorPrezDataObj);

    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: getAuthInvoiceList() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;
}/* End of Method */

 private String getAuthorityList(HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.UIACTION_GET_AUTHORITY_LIST;
    AuthorityPrezData authorityPrezDataObj = new AuthorityPrezData();
    AuthorityBean authorityBeanObj = new AuthorityBean();
    AuthorityDelegate authorityMgrObj = new AuthorityManager();
    LinkedList authorityList = new LinkedList();
    HttpSession Session = request.getSession();
    try {
        logger.log(Level.INFO, "AuthorityHandler :: getAuthorityList() :: method called :: ");

//        if (Session.getAttribute(ApplicationConstants.USER_NAME_SESSION) == null) {
//            authorityBeanObj.setAuhtorityNumber("");
//        } else {
//            authorityBeanObj.setAuhtorityNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
//        }
//        
//         if (Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null) {
//            authorityBeanObj.setLocationId("");
//        } else {
//            authorityBeanObj.setLocationId((String) Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
//        }
//
//        
//                
//                
//        authorityPrezDataObj = authorityMgrObj.getAuthorityList(authorityBeanObj);
//
//        authorityList = authorityPrezDataObj.getAuthorityList();
//        Session.setAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA, authorityList);

    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: getAuthorityList() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;
}/* End of Method */
 
 private String getAuthorityApplForm(HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.UIACTION_GET_AUTHORITY_FORM;
   AuthorityPrezData authorityPrezDataObj = new AuthorityPrezData();
  AuthorityBean authorityBeanObj = new AuthorityBean();
  AuthorityDelegate authorityMgrObj = new AuthorityManager();
    HttpSession authoritySession = request.getSession();
    String AppId ="";
    try {
        logger.log(Level.INFO, "AuthorityHandler :: getAuthorityApplForm() :: method called :: ");

          AppId = (String) request.getParameter("AppId");
          authorityBeanObj.setApplId(AppId);
       // authorityBeanObj.setAuthorityNumber((String) authoritySession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
       

         authorityBeanObj = authorityMgrObj.getAuthorityFormData(authorityBeanObj);
         authorityPrezDataObj.setAuthorityBean(authorityBeanObj);
     authoritySession.setAttribute(ApplicationConstants.AUTHORITY_FORM_SESSION_DATA, authorityPrezDataObj);

    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: getAuthorityApplForm() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;

}/* End of Method */
 
 private String getEmpReports(HttpServletRequest request) throws Exception {
 String sReturnPage = ApplicationConstants.UIACTION_EMP_REPORTS_GET;
 HttpSession authoritySession = request.getSession();
 String AppId ="";
    try {
        logger.log(Level.INFO, "AuthorityHandler :: getEmpReports() :: method called :: ");

        
    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: getEmpReports() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;

}/* End of Method */
 
private String getAuthPOList(HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.UIACTION_GET_AUTH_PO_LIST;
    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    VendorPrezData vendorPrezDataObj2 = new VendorPrezData();
     VendorPrezData vendorPrezDataObj3 = new VendorPrezData();
     VendorPrezData vendorPrezDataObj4 = new VendorPrezData();
     VendorPrezData vendorPrezDataObj5 = new VendorPrezData();
    POBean poBeanObj = new POBean();
   VendorBean vendorBeanObj = new VendorBean();
    VendorDelegate vendorMgrObj = new VendorManager();
    LinkedList POList = new LinkedList();
    HttpSession Session = request.getSession();
    String subAction = "";
    String poOptions ="";
    Integer i=0;
    try {
        logger.log(Level.INFO, "AuthorityHandler :: getAuthPOList() :: method called :: ");

        poBeanObj.setAuhtorityNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
     
        
         if (Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null) {
            poBeanObj.setLocationId("");
        } else {
            poBeanObj.setLocationId((String) Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            vendorBeanObj.setLocationId((String) Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));

        }
      
           if(!ApplicationUtils.isBlank(Session.getAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA))) {
           vendorPrezDataObj =(VendorPrezData) Session.getAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA);
          }
          
        
           if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
               poBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
           }
              poBeanObj.setUserType("Emp");
             vendorPrezDataObj2 = vendorMgrObj.getPOList(poBeanObj);
            vendorPrezDataObj.setPOList(vendorPrezDataObj2.getPOList());
            POList =vendorPrezDataObj2.getPOList();
             

            
            vendorPrezDataObj3 = vendorMgrObj.getVendorDtlList(poBeanObj);
             vendorPrezDataObj.setVendorDtlList(vendorPrezDataObj3.getVendorDtlList());
             
             
             vendorPrezDataObj4 = vendorMgrObj.getlocationList(poBeanObj);
             vendorPrezDataObj.setLocationList(vendorPrezDataObj4.getLocationList());
             
             vendorPrezDataObj5 = vendorMgrObj.getVendorinvList(poBeanObj);//not used 
             vendorPrezDataObj.setVendorInvList(vendorPrezDataObj5.getVendorInvList());
             
           
           
            
            
          vendorBeanObj.setVendorNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
       
//          if (!ApplicationUtils.isBlank((request.getParameter("txtVendorNumber")))) {
//             vendorBeanObj.setVendorNumberHdr(ApplicationUtils.getRequestParameter(request, "txtVendorNumber"));
//           }
        
         if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
             vendorBeanObj.setPONumberHdr(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
         }
              vendorBeanObj.setUserType("Emp");
        
            vendorPrezDataObj2 = vendorMgrObj.getInvoiceList(vendorBeanObj);//used for setting vendor invoice list
            vendorPrezDataObj.setVendorInvoiceList(vendorPrezDataObj2.getVendorInvoiceList()); 
          
         
          vendorBeanObj.setSubAction(subAction);
          vendorPrezDataObj.setVendorBean(vendorBeanObj); 
          
        Session.setAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA, vendorPrezDataObj);
      
      
       

    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: getAuthPOList() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;
}/* End of Method */

 private String getAuthResetList(HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.UIACTION_GET_AUTH_RESET_LIST;
    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    VendorPrezData vendorPrezDataObj2 = new VendorPrezData();
     VendorPrezData vendorPrezDataObj3 = new VendorPrezData();
    POBean poBeanObj = new POBean();
     VendorBean vendorBeanObj = new VendorBean();
    VendorDelegate vendorMgrObj = new VendorManager();
    LinkedList POList = new LinkedList();
    HttpSession Session = request.getSession();
    String subAction = "";
    try {
        logger.log(Level.INFO, "AuthorityHandler :: getAuthResetList() :: method called :: ");

        poBeanObj.setVendorNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
        if (!ApplicationUtils.isBlank((request.getParameter("subAction")))) {
            subAction = request.getParameter("subAction");
        }
        
         if (Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null) {
            poBeanObj.setLocationId("");
        } else {
            poBeanObj.setLocationId((String) Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            //vendorBeanObj.setLocationId((String) Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));

        }
      
       if(subAction.equals("Reset")){
            if(Session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")){
            poBeanObj.setUserType("Emp");
            }
         vendorPrezDataObj2 = vendorMgrObj.getPOList(poBeanObj);
          vendorBeanObj.setSubAction(subAction);
         // vendorPrezDataObj.setVendorBean(vendorBeanObj); 
        vendorPrezDataObj.setPOList(vendorPrezDataObj2.getPOList());
        vendorPrezDataObj3 = vendorMgrObj.getVendorDtlList(poBeanObj);
             vendorPrezDataObj.setVendorDtlList(vendorPrezDataObj3.getVendorDtlList());
        Session.setAttribute(ApplicationConstants.AUTHORITY_POLIST_SESSION_DATA, vendorPrezDataObj);
       }
       

    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: getAuthResetList() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;
}/* End of Method */

 private String getAuthSummary(HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.UIACTION_GET_AUTH_SUMMARY;
    VendorPrezData vendorPrezDataObj = new VendorPrezData();    
    VendorBean vendorBeanObj = new VendorBean();
    VendorDelegate vendorMgrObj = new VendorManager();
    //LinkedList SummaryList = new LinkedList();
    HttpSession session = request.getSession();        
       
        try {
            logger.log(Level.INFO, "AuthorityHandler :: getAuthSummary() :: method called :: ");

           
          if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")){
            vendorBeanObj.setUserType("Emp");
            }
          
          if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")){
            vendorBeanObj.setUserType("Vendor");
            }
           
          
       vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
       
        if (session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null) {           
            vendorBeanObj.setLocationId("");
        } else {
            vendorBeanObj.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
        }
            vendorPrezDataObj = vendorMgrObj.getSummaryList(vendorBeanObj);
            session.setAttribute(ApplicationConstants.AUTHORITY_SUMMARY_SESSION_DATA, vendorPrezDataObj);
            // vendorPrezDataObj = vendorMgrObj.getLegalSummaryList(vendorBeanObj);
            session.setAttribute(ApplicationConstants.AUTHORITY_LEGAL_SUMMARY_SESSION_DATA, vendorPrezDataObj);


    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: getAuthSummary() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;
}/* End of Method */
 

private String MSEDCLEmpReport(HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.REPORT_MSEDCL_EMP;
    VendorPrezData vendorPrezDataObj = new VendorPrezData();
    VendorPrezData vendorPrezDataObj2 = new VendorPrezData();
     VendorPrezData vendorPrezDataObj3 = new VendorPrezData();
     VendorPrezData vendorPrezDataObj4 = new VendorPrezData();
    POBean poBeanObj = new POBean();
     VendorBean vendorBeanObj = new VendorBean();
    VendorDelegate vendorMgrObj = new VendorManager();
    LinkedList POList = new LinkedList();
    HttpSession Session = request.getSession();
    String subAction = "";
    String poOptions ="";
    Integer i=0;
    try {
        logger.log(Level.INFO, "AuthorityHandler :: MSEDCLEmpReport() :: method called :: ");

        poBeanObj.setAuhtorityNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
     
        
         if (Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null) {
            poBeanObj.setLocationId("");
        } else {
            poBeanObj.setLocationId((String) Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            vendorBeanObj.setLocationId((String) Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));

        }
            poBeanObj.setUserType("Emp");
            vendorPrezDataObj2 = vendorMgrObj.getPOList(poBeanObj);
            vendorPrezDataObj.setPOList(vendorPrezDataObj2.getPOList());
                        
            vendorPrezDataObj3 = vendorMgrObj.getVendorDtlList(poBeanObj);
            vendorPrezDataObj.setVendorDtlList(vendorPrezDataObj3.getVendorDtlList());
             
            vendorPrezDataObj4 = vendorMgrObj.getlocationList(poBeanObj);
            vendorPrezDataObj.setLocationList(vendorPrezDataObj4.getLocationList());
       
          
        Session.setAttribute(ApplicationConstants.AUTHORITY_LIST_SESSION_DATA, vendorPrezDataObj);
      
      
       

    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: MSEDCLEmpReport() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;
}/* End of Method */
private String getAuthLegalInvoiceList(HttpServletRequest request) throws Exception{
        String sReturnPage = ApplicationConstants.UIACTION_GET_LEGAL_VENDOR_INVOICE;
         VendorPrezData vendorPrezDataObj = new VendorPrezData();
   VendorBean vendorBeanObj = new VendorBean();
    VendorDelegate vendorMgrObj = new VendorManager();
    LinkedList POList = new LinkedList();
    HttpSession Session = request.getSession();
     LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        HttpSession session = request.getSession();
        List<LegalInvoiceInputBean> legalInvoiceInputBeanList = null;
    String subAction = "";
    String poOptions ="";
    Integer i=0;
    try {

        legalInvoiceInputBean.setAuhtorityNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
         if (Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null) {
            legalInvoiceInputBean.setLocationId("");
        } else {
            legalInvoiceInputBean.setLocationId((String) Session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
        }
         if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                legalInvoiceInputBean.setInvoiceFromDate(ApplicationUtils.stringToDate((String) request.getParameter("txtFrmDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                legalInvoiceInputBean.setInvoiceToDate(ApplicationUtils.stringToDate((String) request.getParameter("txtToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }
             if (!ApplicationUtils.isBlank((request.getParameter("txtVendorNumber")))) {
            legalInvoiceInputBean.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtVendorNumber"));System.out.println("blah::" + request.getParameter("txtVendorNumber"));   
             }
             if (!ApplicationUtils.isBlank((request.getParameter("caseRefNo")))) {
                legalInvoiceInputBean.setCaseRefNo((String) request.getParameter("caseRefNo"));
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtInvoiceNumber")))) {
                legalInvoiceInputBean.setInvoiceNumber((String) request.getParameter("txtInvoiceNumber"));
            }
            logger.log(Level.INFO, "AuthorityHandler :: getAuthLegalInvoiceList() :: method called :: ");
            String userType = (String) request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION);
            System.out.println("userType::" + userType);System.out.println("blah::" + legalInvoiceInputBean.getVendorNumber());
            if (userType.equalsIgnoreCase("Emp")) {
                legalInvoiceInputBean.setCreatedByUsertype("Emp");
                legalInvoiceInputBean.setWhereClause("Emp");
            }else{
                legalInvoiceInputBean.setCreatedByUsertype("Vendor");
            }
            legalInvoiceInputBean.setCreatedByUsertype(userType);
            legalInvoiceInputBeanList = (List<LegalInvoiceInputBean>) vendorMgrObj.getLegalInvoiceInputList(legalInvoiceInputBean);

            session.setAttribute(ApplicationConstants.VENDOR_LEGAL_INVOICE_INPLIST_SESSION_DATA, legalInvoiceInputBeanList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AuthorityHandler :: getAuthLegalInvoiceList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }

private String getLegalAuthSummary(HttpServletRequest request) throws Exception {
    String sReturnPage = ApplicationConstants.UIACTION_GET_LEGAL_AUTH_SUMMARY;

    //VendorBean vendorBeanObj = new VendorBean();
      LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
    VendorDelegate vendorMgrObj = new VendorManager();
    LinkedList legalSummaryList = new LinkedList();
    HttpSession session = request.getSession();        
       
        try {
            logger.log(Level.INFO, "AuthorityHandler :: getLegalAuthSummary() :: method called :: ");

           
         /* if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")){
            legalInvoiceInputBean.setCreatedByUsertype("Emp");
            }
          
          if(session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")){
            legalInvoiceInputBean.setCreatedByUsertype("Vendor");
            }
           
          */
       legalInvoiceInputBean.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
       
        if (session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) == null) {           
            legalInvoiceInputBean.setLocationId("");
        } else {
            legalInvoiceInputBean.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
        }
            legalSummaryList = vendorMgrObj.getLegalSummaryList(legalInvoiceInputBean);
            session.setAttribute(ApplicationConstants.LEGAL_AUTHORITY_SUMMARY_SESSION_DATA, legalSummaryList);


    } catch (Exception ex) {
        logger.log(Level.ERROR, "AuthorityHandler :: getLegalAuthSummary() :: Exception :: " + ex);
        //ex.printStackTrace();
    }
    return sReturnPage;
}/* End of Method */
 

}//class ends

