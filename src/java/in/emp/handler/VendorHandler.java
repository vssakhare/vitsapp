/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.handler;

import in.emp.vendor.VendorApplFileDelegate;
import in.emp.vendor.bean.VendorApplFileBean;
import in.emp.vendor.manager.VendorApplFileManager;
import in.emp.arch.GenericFormHandler;
import in.emp.common.ApplicationConstants;
import in.emp.common.FileBean;
import in.emp.common.UploadVendorFile;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.system.dao.helpers.MultipartRequestParser;
//import in.emp.vendor.VendorDelegate;
//import in.emp.vendor.bean.VendorBean;
//import in.emp.vendor.bean.VendorBean;
//import in.emp.vendor.bean.VendorPrezData;
//import in.emp.vendor.manager.VendorManager;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.ClearingDocDetails;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.ProjBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.manager.VendorManager;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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
public class VendorHandler implements GenericFormHandler {
//private static Logger logger = new Logger(RoleHandler.class.getName());

    private static String CLASS_NAME = VendorHandler.class.getName();
    private static Logger logger = Logger.getLogger(VendorHandler.class);

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
        String contentType = "";
        HashMap hashObj = null;

        try {
            logger.log(Level.INFO, "VendorHandler :: execute() :: method called");

            contentType = request.getContentType();

            if ((contentType != null) && (contentType.startsWith("multipart/form-data"))) // Handle multipart request
            {
                MultipartRequestParser mrp = (MultipartRequestParser) request.getSession().getAttribute("MultipartRequestMrp");
                //request.getParameter("inpFile1");

                mrp.parseOnly(); //parse the request
                hashObj = mrp.webVars;

                if (hashObj != null && hashObj.size() > 0) {
                    if (hashObj.get(ApplicationConstants.UIACTION_NAME) != null) {

                        uiActionName = (String) hashObj.get(ApplicationConstants.UIACTION_NAME);
                    }
                    request.getSession().setAttribute("MultipartRequestMrp", mrp);
                }
            } else {
                uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
                String subAction = request.getParameter("subAction");
            }// Getting the parameters from request object for Role application

            // uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME); **********
            // String subAction = request.getParameter("subAction");
            //  System.out.println("TransferHandler :: execute() :: uiActionName :: " + uiActionName);
            // Getting the parameters from request object for Role application
            if (!ApplicationUtils.isBlank(uiActionName)) {
                if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_LIST)) {
                    sReturnPage = getVendorList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_PO_LIST)) {
                    sReturnPage = getPOList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_INVOICE_LIST)) {
                    sReturnPage = getInvoiceList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_APPL_FORM)) {
                    sReturnPage = getVendorApplForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_PS_APPL_FORM)) {
                    sReturnPage = getVendorPsApplForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST)) {
                    sReturnPage = getVendorInputList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM)) {
                    sReturnPage = getVendorInputForm(request);

                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_LIST)) {
                    sReturnPage = getVendorLegalInvoiceInputList(request);

                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_FORM)) {
                    sReturnPage = getVendorLegalInvoiceInputForm(request);

                } else if (uiActionName.equals(ApplicationConstants.UIACTION_VIEW_VENDOR_LEGAL_INPUT_LIST)) {
                    sReturnPage = viewVendorLegalInvoiceDetails(request);

                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_EMP_INPUT_FORM)) {
                    sReturnPage = getEmpInputForm(request);

                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_RESET_LIST)) {
                    sReturnPage = getResetList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_INVOICE_FILE_GET)) {
                    sReturnPage = getInvoiceFile(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_INVOICE_FILE_POST)) {
                    sReturnPage = postInvoiceFile(request);
                } else if (uiActionName.equals(ApplicationConstants.REPORT_MSEDCL_VENDOR)) {
                    sReturnPage = MSEDCLVendorReport(request);
                    //ruchira
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_NONPO_VENDOR_INPUT_FORM)) {
                    sReturnPage = getnonPoVendorList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_VERIFIED_FORM)) {
                    sReturnPage = getVendorVerifiedForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_EMP_VERIFIED_FORM_PS)) {
                    sReturnPage = getEmpVerifiedPSForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_CLEARING_DOC_DETAILS)) {
                    sReturnPage = viewClearingDetails(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_POST)) {
                    sReturnPage = postLegalInvoiceFile(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_SEARCH_COURT_CASE)) {
                    System.out.println("::b4*******getCourtCaseSearch*********b4::");
                    sReturnPage = getVendorSearchCourtCase(request);
                }/* else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS)) {
                 sReturnPage = viewPOLineDetails(request);
                
                 } */ 
                 else if (uiActionName.equals(ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_GET)) {
                    sReturnPage = getLegalInvoiceFile(request);
                }
                else {
                    sReturnPage = ApplicationConstants.UIACTION_HOME_GET;
                }
            }
            logger.log(Level.INFO, "VendorHandler :: execute() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: execute() :: Exception :: " + ex);
            ex.printStackTrace();
        }

        return sReturnPage;

    } //end execute method

    private String getResetList(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_PO_LIST;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObj2 = new VendorPrezData();

        POBean poBeanObj = new POBean();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        LinkedList POList = new LinkedList();
        HttpSession Session = request.getSession();
        String subAction = "";
        try {
            logger.log(Level.INFO, "VendorHandler :: getResetList() :: method called :: ");

            poBeanObj.setVendorNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            if (!ApplicationUtils.isBlank((request.getParameter("subAction")))) {
                subAction = request.getParameter("subAction");
            }

            if (subAction.equals("Reset")) {
                if (Session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                    poBeanObj.setUserType("Vendor");
                }
                vendorPrezDataObj2 = vendorMgrObj.getPOList(poBeanObj);
                vendorBeanObj.setSubAction(subAction);
                vendorPrezDataObj.setVendorBean(vendorBeanObj);
                vendorPrezDataObj.setPOList(vendorPrezDataObj2.getPOList());
                Session.setAttribute(ApplicationConstants.VENDOR_POLIST_SESSION_DATA, vendorPrezDataObj);
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getResetList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }/* End of Method */

 /* private String   viewPOLineDetails(HttpServletRequest request)throws Exception {
     String sReturnPage = ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS; 
     HttpSession Session = request.getSession();
     VendorPrezData vendorPrezDataObj = new VendorPrezData();
     VendorPrezData vendorPrezDataObj1 = new VendorPrezData();
     VendorDelegate vendorMgrObj = new VendorManager();
     PoLineStatusBean poLineStatusBeanObj=new PoLineStatusBean();
     try {
     logger.log(Level.INFO, "VendorHandler :: viewClearingDetails() :: method called :: ");
     if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
     poLineStatusBeanObj.setContract_Document(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
     }
     if (!ApplicationUtils.isBlank((request.getParameter("txtApplId")))) {
     poLineStatusBeanObj.setAPPL_ID(ApplicationUtils.getRequestParameter(request, "txtApplId"));
     }
     //  vendorPrezDataObj1 = vendorMgrObj.getPOLineDetails(poLineStatusBeanObj);
     vendorPrezDataObj.setPoLineDetails(vendorPrezDataObj1.getPoLineDetails());
      
     Session.setAttribute(ApplicationConstants.VENDOR_PO_LINE_SESSION_DATA, vendorPrezDataObj);
     } catch (Exception ex) {
     logger.log(Level.ERROR, "VendorHandler :: viewClearingDetails() :: Exception :: " + ex);
     // //ex.printStackTrace();
     }
     return sReturnPage;
     }*/

    private String viewClearingDetails(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_CLEARING_DOC_DETAILS;
        HttpSession Session = request.getSession();
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObj1 = new VendorPrezData();
        VendorDelegate vendorMgrObj = new VendorManager();
        ClearingDocDetails clearingDocDetailsObj = new ClearingDocDetails();
        try {
            logger.log(Level.INFO, "VendorHandler :: viewClearingDetails() :: method called :: ");
            if (!ApplicationUtils.isBlank((request.getParameter("cl_doc_no")))) {
                clearingDocDetailsObj.setCL_DOC_NO(ApplicationUtils.getRequestParameter(request, "cl_doc_no"));
            }
            vendorPrezDataObj1 = vendorMgrObj.getClearingDocDetails(clearingDocDetailsObj);
            vendorPrezDataObj.setClearingDocDetails(vendorPrezDataObj1.getClearingDocDetails());
            Session.setAttribute(ApplicationConstants.VENDOR_CLEARING_LIST_SESSION_DATA, vendorPrezDataObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: viewClearingDetails() :: Exception :: " + ex);
            // //ex.printStackTrace();
        }
        return sReturnPage;
    }

    private String getPOList(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_PO_LIST;
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
            logger.log(Level.INFO, "VendorHandler :: getPOList() :: method called :: ");

            poBeanObj.setVendorNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));

            if (!ApplicationUtils.isBlank(Session.getAttribute(ApplicationConstants.VENDOR_LIST_SESSION_DATA))) {
                vendorPrezDataObj = (VendorPrezData) Session.getAttribute(ApplicationConstants.VENDOR_LIST_SESSION_DATA);
            }

            if (ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                poBeanObj.setUserType("Vendor");
                vendorPrezDataObj2 = vendorMgrObj.getPOList(poBeanObj);
                vendorPrezDataObj.setPOList(vendorPrezDataObj2.getPOList());

                vendorPrezDataObj3 = vendorMgrObj.getlocationList(poBeanObj);
                vendorPrezDataObj.setLocationList(vendorPrezDataObj3.getLocationList());
            }

            vendorBeanObj.setVendorNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));

            if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                vendorBeanObj.setPONumberHdr(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
                vendorBeanObj.setUserType("Vendor");
                vendorPrezDataObj2 = vendorMgrObj.getInvoiceList(vendorBeanObj);
                vendorPrezDataObj.setVendorInvoiceList(vendorPrezDataObj2.getVendorInvoiceList());

            }
            vendorBeanObj.setSubAction(subAction);
            vendorPrezDataObj.setVendorBean(vendorBeanObj);

            Session.setAttribute(ApplicationConstants.VENDOR_LIST_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getPOList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }/* End of Method */


    private String getVendorList(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_LIST;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObjTwo = new VendorPrezData();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        LinkedList POList = new LinkedList();
        HttpSession session = request.getSession();
        //String PlantCode= "";

        try {
            logger.log(Level.INFO, "VendorHandler :: getVendorList() :: method called :: ");

            vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            vendorBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNumber"));
            //PlantCode = ApplicationUtils.getRequestParameter(request, "txtLoc");

            //vendorBeanObj.setPlantCode(ApplicationUtils.getRequestParameter(request, "txtLoc")); 
            //if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
            // vendorBeanObj.setInvoiceFromDate(ApplicationUtils.stringToDate((String) request.getParameter("txtFrmDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            //}
            //if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
            //vendorBeanObj.setInvoiceToDate(ApplicationUtils.stringToDate((String) request.getParameter("txtToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            //} 
            vendorBeanObj.setUserType("Vendor");

            vendorPrezDataObj = vendorMgrObj.getVendorList(vendorBeanObj);
//            if(!ApplicationUtils.isBlank(PlantCode)){
//                vendorBeanObj.setPlantCode(PlantCode);
//             }
            vendorPrezDataObj.setVendorBean(vendorBeanObj);

            vendorInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorInputBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            //vendorInputBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNumber")); 

            //if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
            // vendorInputBeanObj.setInvoiceFromDate(ApplicationUtils.stringToDate((String) request.getParameter("txtFrmDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            //}
            //if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
            // vendorInputBeanObj.setInvoiceToDate(ApplicationUtils.stringToDate((String) request.getParameter("txtToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            //} 
            vendorInputBeanObj.setUserType("Vendor");
            vendorPrezDataObjTwo = vendorMgrObj.getVendorInputList(vendorInputBeanObj);
            vendorPrezDataObj.setVendorInputList(vendorPrezDataObjTwo.getVendorInputList());
            // vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);

            session.setAttribute(ApplicationConstants.VENDOR_INVLIST_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }/* End of Method */


    private String getInvoiceList(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_INVOICE_LIST;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        LinkedList POList = new LinkedList();
        HttpSession session = request.getSession();

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getInvoiceList() :: method called :: ");

            vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            vendorPrezDataObj = vendorMgrObj.getInvoiceList(vendorBeanObj);

            session.setAttribute(ApplicationConstants.VENDOR_LIST_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getInvoiceList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }/* End of Method */


    private String getVendorVerifiedForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_VERIFIED_FORM;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObjTwo = new VendorPrezData();
        VendorPrezData vendorPrezDataObjThree = new VendorPrezData();
        VendorPrezData vendorPrezDataObjFour = new VendorPrezData();
        VendorPrezData vendorPrezDataObjFive = new VendorPrezData();
        VendorPrezData vendorPrezDataObjSix = new VendorPrezData();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        LinkedList POList = new LinkedList();
        HttpSession session = request.getSession();
        try {
            logger.log(Level.INFO, "VendorHandler :: getVendorList() :: method called :: ");
            vendorInputBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "EmpNo"));
            vendorInputBeanObj.setApplId(ApplicationUtils.getRequestParameter(request, "appl_id"));
            vendorInputBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "PoNumber"));
            vendorInputBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "VendorInvNo"));

            vendorInputBeanObj = vendorMgrObj.getVendorVerifiedInputForm(vendorInputBeanObj);
            if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                vendorInputBeanObj.setUserType("Vendor");
            } else if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                vendorInputBeanObj.setUserType("Emp");
            }
            vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);

            vendorBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "EmpNo"));
            vendorBeanObj.setApplId(ApplicationUtils.getRequestParameter(request, "appl_id"));
            vendorBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "PoNumber"));
            vendorBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "VendorInvNo"));

            vendorBeanObj.setSES_MIGO_INV_TYPE("SES");
            vendorPrezDataObjThree = vendorMgrObj.getVendorVerifiedList(vendorBeanObj);
            vendorPrezDataObj.setSesList(vendorPrezDataObjThree.getVendorList());
            vendorBeanObj.setSES_MIGO_INV_TYPE("MIGO");
            vendorPrezDataObjFour = vendorMgrObj.getVendorVerifiedList(vendorBeanObj);
            vendorPrezDataObj.setMigoList(vendorPrezDataObjFour.getVendorList());
            vendorBeanObj.setSES_MIGO_INV_TYPE("INVOICE");
            vendorPrezDataObjFive = vendorMgrObj.getVendorVerifiedList(vendorBeanObj);
            vendorPrezDataObj.setInvList(vendorPrezDataObjFive.getVendorList());
            vendorBeanObj.setSES_MIGO_INV_TYPE("PAYMENT");
            vendorPrezDataObjSix = vendorMgrObj.getVendorVerifiedList(vendorBeanObj);
            vendorPrezDataObj.setPaymentList(vendorPrezDataObjSix.getVendorList());
            vendorPrezDataObj.setVendorBean(vendorBeanObj);

            session.setAttribute(ApplicationConstants.VENDOR_VERIFIEDLIST_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }

        return sReturnPage;
    }

    private String getnonPoVendorList(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_NONPO_VENDOR_INPUT_FORM;
        String AppID = "";
        HttpSession vendorSession = request.getSession();
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObj1 = new VendorPrezData();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        POBean poBeanObj = new POBean();
        try {
            logger.log(Level.INFO, "VendorHandler :: getnonPoVendorList() :: method called :: ");
            poBeanObj.setPlant("ZON");
            poBeanObj.setOfficeCode("261");
            vendorPrezDataObj1 = vendorMgrObj.getzoneList(poBeanObj);

            vendorPrezDataObj.setZoneList(vendorPrezDataObj1.getZoneList());
            if (!ApplicationUtils.isBlank((request.getParameter("txtApplId")))) {
                vendorInputBeanObj.setApplId(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            }
            vendorInputBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorInputBeanObj.setUserType("Vendor");
            vendorInputBeanObj = vendorMgrObj.getVendorNonpoInputForm(vendorInputBeanObj);
            vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            vendorSession.setAttribute(ApplicationConstants.VENDOR_NON_PO_FORM_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getnonPoVendorList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }

    private String getVendorPsApplForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_PS_APPL_FORM;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        String MsedclInvoiceNumber = "";
        String SOrMNum = "";
        String POType = "";
        try {
            logger.log(Level.INFO, "VendorHandler :: getVendorPsApplForm() :: method called :: ");

            //vendorBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorBeanObj.setVendorNumber((String) request.getParameter("EmpNo"));
            MsedclInvoiceNumber = (String) request.getParameter("MsedclInvNumber");
            vendorBeanObj.setMsedclInvoiceNumber(MsedclInvoiceNumber);
            //vendorBeanObj.setPONumber((String) request.getParameter("PoNumber"));

            vendorBeanObj = vendorMgrObj.getVendorPsApplForm(vendorBeanObj);
            vendorPrezDataObj.setVendorBean(vendorBeanObj);
            vendorSession.setAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorPsApplForm() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;

    }

    private String getEmpVerifiedPSForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_EMP_PS_APPL_FORM;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorBean vendorBeanObj = new VendorBean();
        VendorBean vendorBeanRetentionObj = null;
        //VendorInputBean vendorInputBean=new VendorInputBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        String VendorInvoiceNumber = "";
        String SOrMNum = "";
        String POType = "";
        try {
            logger.log(Level.INFO, "VendorHandler :: getVendorPsApplForm() :: method called :: ");

            //vendorBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorBeanObj.setVendorNumber((String) request.getParameter("EmpNo"));
            VendorInvoiceNumber = (String) request.getParameter("VendorInvNo");
            vendorBeanObj.setVendorInvoiceNumber(VendorInvoiceNumber);
            vendorBeanObj.setApplId((String) request.getParameter("appl_id"));
            vendorBeanObj.setProject_Code(ApplicationUtils.getRequestParameter(request, "PoNumber"));

            vendorBeanObj = vendorMgrObj.getEmpPsApplForm(vendorBeanObj);
            if (vendorBeanObj.getInvoice_Type().equalsIgnoreCase("Retention Claim Charges")) {
                vendorBeanRetentionObj = new VendorBean();
                vendorBeanObj.setSubAction("displayDetails");
                vendorBeanRetentionObj = vendorMgrObj.putRetentionInvoiceStatus(vendorBeanObj).get(0);
                vendorSession.setAttribute(ApplicationConstants.VENDOR_RETENTION_APPL_FORM_SESSION_DATA, vendorBeanRetentionObj);
            }
            //vendorInputBean.setApplId((String) request.getParameter("appl_id"));
            vendorPrezDataObj.setVendorBean(vendorBeanObj);
            vendorSession.setAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorPsApplForm() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;

    }

    private String getVendorApplForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_APPL_FORM;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        String VendorInvoiceNumber = "";
        String SOrMNum = "";
        String POType = "";
        try {
            logger.log(Level.INFO, "VendorHandler :: getVendorApplForm() :: method called :: ");

            vendorBeanObj.setVendorNumber((String) request.getParameter("EmpNo"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                vendorBeanObj.setUserType("Vendor");
                SOrMNum = (String) request.getParameter("SesMigoNum");
                vendorBeanObj.setSesNum(SOrMNum);
                VendorInvoiceNumber = (String) request.getParameter("VendorInvNo");
                vendorBeanObj.setInvoiceNumber(VendorInvoiceNumber);
                vendorBeanObj.setPONumber((String) request.getParameter("PoNumber"));
            }
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                vendorBeanObj.setUserType("Emp");
                SOrMNum = (String) request.getParameter("sesmigoinvno");
                vendorBeanObj.setSesNum(SOrMNum);
                vendorBeanObj.setPONumber((String) request.getParameter("PoNumber"));
                POType = (String) request.getParameter("POType");
                vendorBeanObj.setPOType(POType);
            }

            vendorBeanObj = vendorMgrObj.getVendorApplForm(vendorBeanObj);
            vendorPrezDataObj.setVendorBean(vendorBeanObj);
            vendorSession.setAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorApplForm() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;

    }/* End of Method */


    private String getVendorInputList(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDORINPUT_LIST;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObj2 = new VendorPrezData();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        LinkedList POList = new LinkedList();
        POBean poBeanObj = new POBean();
        HttpSession session = request.getSession();

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getVendorInputList() :: method called :: ");

            vendorInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));

            if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                vendorInputBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber")); //same parameter used for project id and po number

                vendorInputBeanObj.setPONumberHdr(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            }
            vendorInputBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNumber"));

            if (!ApplicationUtils.isBlank((request.getParameter("txtinvdate")))) {
                vendorInputBeanObj.setVendorInvoiceDate(ApplicationUtils.stringToDate((String) request.getParameter("txtinvdate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtapplid")))) {
                vendorInputBeanObj.setApplId(ApplicationUtils.getRequestParameter(request, "txtapplid"));
            }
            vendorInputBeanObj.setUserType("Vendor");
            vendorBeanObj.setUserType("Vendor");
            vendorBeanObj.setInvoiceFlag("true");
            vendorPrezDataObj = vendorMgrObj.getVendorInputList(vendorInputBeanObj);

            vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            // vendorPrezDataObj.setapplicationList(vendorPrezDataObj.getapplicationList());
            poBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            poBeanObj.setUserType("Vendor");
            vendorPrezDataObj2 = vendorMgrObj.getPOList(poBeanObj);
            vendorPrezDataObj.setPOList(vendorPrezDataObj2.getPOList());
            POList = vendorPrezDataObj2.getPOList();
            vendorPrezDataObj2 = vendorMgrObj.getInvoiceList(vendorBeanObj);
            vendorPrezDataObj.setVendorInvoiceList(vendorPrezDataObj2.getVendorInvoiceList());

            session.setAttribute(ApplicationConstants.VENDOR_INPLIST_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorInputList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }/* End of Method */


    private String getVendorInputForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorInputBean vendorInputBeanObj1 = new VendorInputBean();
        VendorBean vendorBeanObj = new VendorBean();
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplFileMgrObj = new VendorApplFileManager();
        VendorPrezData vendorPrezDataObj2 = null;
        VendorPrezData vendorPrezDataObj1 = null;
        VendorPrezData vendorPrezDataObj3 = null;
        VendorPrezData vendorPrezDataObjTwo = null;
        ProjBean projBeanObj = new ProjBean();
        POBean poBeanObj = new POBean();
        POBean poBeanObj2 = new POBean();
        LinkedList FileList = new LinkedList();
        LinkedList FileList1 = new LinkedList();
        LinkedList FileList2 = new LinkedList();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        String ApplID = "";
        String PoNumber = "";
        try {
            logger.log(Level.INFO, "VendorHandler :: getVendorInputForm() :: method called :: ");

            //vendorInputBeanObj.setApplId(ApplicationUtils.getRequestParameter(request, "AppId"));
            vendorBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            System.out.println("user designation:" + vendorSession.getAttribute(ApplicationConstants.DESIGNATION_SESSION));
            String module = ApplicationUtils.getRequestParameter(request, "module_type");
            if (request.getParameter("AppId") != null) {
                ApplID = (String) request.getParameter("AppId");
            } else if (request.getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_APPID) != null) {
                ApplID = (String) request.getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_APPID);
            } else {
                ApplID = null;
            }
            if (request.getParameter("poNumber") != null) {
                PoNumber = (String) request.getParameter("poNumber");
                vendorInputBeanObj.setPONumber(PoNumber);
                vendorBeanObj.setPONumber(PoNumber);
            }
            if (request.getParameter("txtProjId") != null) {
                String proj_id = request.getParameter("txtProjId");
                vendorInputBeanObj.setProjectId(request.getParameter("txtProjId"));
            }
            vendorInputBeanObj.setApplId(ApplID);
            //vendorInputBeanObj.setPONumber(PoNumber);
//        vendorInputBeanObj.setUserType("Vendor");
//        poBeanObj.setUserType("Vendor");
            vendorInputBeanObj1.setPONumber(PoNumber);

            //vendorBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                vendorInputBeanObj.setUserType("Vendor");
                vendorBeanObj.setUserType("Vendor");

                poBeanObj.setUserType("Vendor");
                projBeanObj.setUserType("Vendor");
                projBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                vendorPrezDataObj3 = vendorMgrObj.getProjList(projBeanObj);
                vendorPrezDataObj.setProjList(vendorPrezDataObj3.getProjList());
            }
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                vendorInputBeanObj.setUserType("Emp");
                poBeanObj.setUserType("Emp");
                poBeanObj.setPONumber((String) request.getParameter("PoNumber"));
                poBeanObj.setLocationId((String) vendorSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));

            }
            vendorInputBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorInputBeanObj1.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            poBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorPrezDataObj2 = vendorMgrObj.getPOList(poBeanObj);
            vendorPrezDataObj.setPOList(vendorPrezDataObj2.getPOList());
            if (module.equals("PS")) {
                vendorInputBeanObj = vendorMgrObj.getVendorPsInputForm(vendorInputBeanObj);
                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            } else if (module.equals("NON_PO")) {
                vendorInputBeanObj = vendorMgrObj.getVendorNonpoInputForm(vendorInputBeanObj);
                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            } else {
                vendorInputBeanObj = vendorMgrObj.getVendorInputForm(vendorInputBeanObj);
                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            }

            if (vendorInputBeanObj.getApplId() != null) {
                ApplID = vendorInputBeanObj.getApplId();
                vendorInputBeanObj.setApplId(ApplID);
                vendorapplFileBeanObj.setApplicationId(vendorInputBeanObj.getApplId());
                if (module.equals("PS")) {
                    vendorapplFileBeanObj.setPo_Number(vendorInputBeanObj.getProjectId());
                } else {
                    vendorapplFileBeanObj.setPo_Number(vendorInputBeanObj.getPONumber());
                }
                FileList = vendorapplFileMgrObj.getVendorApplFileList(vendorapplFileBeanObj);
                //FileList1 = vendorapplFileMgrObj.getVendorPOFileList(vendorapplFileBeanObj);  //commented on 21-jul-22 to only show latest uploaded file
                //FileList.addAll(FileList1);//commented on 21-jul-22 to only show latest uploaded file
            }
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                vendorPrezDataObj.getVendorInputBean().setUserType("Vendor");

            }
            /* if (!ApplicationUtils.isBlank(request.getParameter("txtPONumber")))
            
             { 
             if(vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor"))
             poBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
             poBeanObj2 = vendorMgrObj.getPOLocationList(poBeanObj);
             vendorPrezDataObj.setPoBean(poBeanObj2);
             }*/
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                vendorPrezDataObj.getVendorInputBean().setUserType("Emp");
                vendorBeanObj.setUserType("Emp");
                vendorPrezDataObj.getVendorInputBean().setLocationId((String) vendorSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                vendorBeanObj.setLocationId((String) vendorSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                vendorPrezDataObj.getVendorInputBean().setDesignation((String) vendorSession.getAttribute(ApplicationConstants.DESIGNATION_SESSION));
                //  poBeanObj2 = vendorMgrObj.getPOLocationList(poBeanObj);
                //vendorPrezDataObj.setPoBean(poBeanObj2);
            }

            vendorSession.setAttribute(ApplicationConstants.VENDOR_INPUT_FORM_SESSION_DATA, vendorPrezDataObj);

            vendorSession.setAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA, FileList);

            //vendorPrezDataObj1 = vendorMgrObj.getVendorList(vendorBeanObj);
            /*   vendorPrezDataObj1.setVendorBean(vendorBeanObj);
            vendorInputBeanObj1.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorInputBeanObj1.setPONumber(PoNumber);
            vendorInputBeanObj1.setUserType("Vendor");
            vendorPrezDataObjTwo = vendorMgrObj.getVendorInputList(vendorInputBeanObj1);
            vendorPrezDataObj1.setVendorInputList(vendorPrezDataObjTwo.getVendorInputList());

            vendorSession.setAttribute(ApplicationConstants.VENDOR_INPLIST_SESSION_DATA, vendorPrezDataObj1);*/
        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorInputForm() :: Exception :: " + ex);
            ////ex.printStackTrace();
        }
        return sReturnPage;

    }/* End of Method */

    private String getEmpInputForm(HttpServletRequest request) throws Exception {

        String sReturnPage = ApplicationConstants.UIACTION_GET_EMP_INPUT_FORM;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorInputBean vendorInputBeanObj1 = new VendorInputBean();
        VendorBean vendorBeanObj = new VendorBean();
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplFileMgrObj = new VendorApplFileManager();
        VendorPrezData vendorPrezDataObj2 = null;
        VendorPrezData vendorPrezDataObj3 = null;
        VendorPrezData vendorPrezDataObj4 = null;
        ProjBean projBeanObj = new ProjBean();
        POBean poBeanObj = new POBean();
        LinkedList FileList = new LinkedList();
        LinkedList FileList1 = new LinkedList();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        String ApplID = "";
        String PoNumber = "";

        try {
            logger.log(Level.INFO, "VendorHandler :: getVendorInputForm() :: method called :: ");

            //usertype is set to vendor so as to reuse the vendor form as it is without changing much code....
            vendorBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
            String module = ApplicationUtils.getRequestParameter(request, "module_type");
            if (request.getParameter("AppId") != null) {
                ApplID = (String) request.getParameter("AppId");
            } else if (request.getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_APPID) != null) {
                ApplID = (String) request.getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_APPID);
            } else {
                ApplID = null;
            }
            if (request.getParameter("poNumber") != null) {
                PoNumber = (String) request.getParameter("poNumber");
                vendorInputBeanObj.setPONumber(PoNumber);
                vendorBeanObj.setPONumber(PoNumber);
            }
            if (request.getParameter("txtProjId") != null) {
                vendorInputBeanObj.setProjectId(request.getParameter("txtProjId"));
            }
            vendorInputBeanObj.setApplId(ApplID);
            vendorInputBeanObj1.setPONumber(PoNumber);

            vendorInputBeanObj.setUserType("Vendor");
            vendorBeanObj.setUserType("Vendor");
            poBeanObj.setUserType("Vendor");

            projBeanObj.setUserType("Vendor");
            projBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));

            poBeanObj.setLocationId((String) vendorSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            vendorPrezDataObj4 = vendorMgrObj.getVendorDtlList(poBeanObj);
            vendorPrezDataObj.setVendorDtlList(vendorPrezDataObj4.getVendorDtlList());

            vendorInputBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
            vendorInputBeanObj1.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
            poBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
            if (vendorInputBeanObj.getApplId() != null) {
                if (module.equals("PS")) {
                    vendorInputBeanObj = vendorMgrObj.getVendorPsInputForm(vendorInputBeanObj);
                    vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
                    vendorPrezDataObj.getVendorInputBean().setUserType("Vendor");
                } else if (module.equals("NON_PO")) {
                    vendorInputBeanObj = vendorMgrObj.getVendorNonpoInputForm(vendorInputBeanObj);
                    vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
                    vendorPrezDataObj.getVendorInputBean().setUserType("Vendor");
                } else {
                    vendorInputBeanObj = vendorMgrObj.getVendorInputForm(vendorInputBeanObj);
                    vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
                    vendorPrezDataObj.getVendorInputBean().setUserType("Vendor");
                }
            } else {
                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            }

            if (vendorInputBeanObj.getApplId() != null) {
                ApplID = vendorInputBeanObj.getApplId();
                vendorInputBeanObj.setApplId(ApplID);
                vendorapplFileBeanObj.setApplicationId(vendorInputBeanObj.getApplId());
                if (module.equals("PS")) {
                    vendorapplFileBeanObj.setPo_Number(vendorInputBeanObj.getProjectId());
                } else {
                    vendorapplFileBeanObj.setPo_Number(vendorInputBeanObj.getPONumber());
                }
                FileList = vendorapplFileMgrObj.getVendorApplFileList(vendorapplFileBeanObj);
                //FileList1 = vendorapplFileMgrObj.getVendorPOFileList(vendorapplFileBeanObj);
                FileList.addAll(FileList1);
            }

            vendorSession.setAttribute(ApplicationConstants.VENDOR_INPUT_FORM_SESSION_DATA, vendorPrezDataObj);

            vendorSession.setAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA, FileList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorInputForm() :: Exception :: " + ex);
            ex.printStackTrace();
        }
        return sReturnPage;

    }/* End of Method */

    public String getInvoiceFile(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_INVOICE_FILE_GET;
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplFileDelegate = new VendorApplFileManager();
        String AppId = "";
        String FId = "";
        String Option = "";
        String txtPONumber = "";
        FileBean FileObj = null;
        try {
            logger.log(Level.INFO, "VendorHandler :: getInvoiceFile() :: method called :: ");
            if (request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION) != null) {
                vendorapplFileBeanObj.setEmpNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));
                VendorInputBean vendorInputBeanObj = ((VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_INPUT_FORM_SESSION_DATA)).getVendorInputBean();
                vendorapplFileBeanObj.setApplicationId(vendorInputBeanObj.getApplId());
            }
            txtPONumber = (String) request.getParameter("txtPONumber");
            vendorapplFileBeanObj.setPo_Number(txtPONumber);
            if (!ApplicationUtils.isBlank(request.getParameter("AppId"))) {
                AppId = (String) request.getParameter("AppId");
            }
            if (!ApplicationUtils.isBlank(request.getParameter("FId"))) {
                FId = (String) request.getParameter("FId");

            }
            if (!ApplicationUtils.isBlank(request.getParameter("Option"))) {
                Option = (String) request.getParameter("Option");

            }
            vendorapplFileBeanObj.setId(FId);
            vendorapplFileBeanObj.setApplicationId(AppId);
            System.out.println("Option::" + Option);
            if (Option != null) {
                //    if (Option.equals("Letter Of Award") || Option.equals("Copy Of Agreement") || Option.equals("Insurance Copy") || Option.equals("Milestone Chart") || Option.equals("Bank Guarantee")) {
                if (Option.equals("Invoice Document") || Option.equals("Other Supporting Document") || Option.equals("Retention claim Document")) {
                    vendorapplFileBeanObj = vendorapplFileDelegate.getVendorApplFile(vendorapplFileBeanObj);
                } else {
                    vendorapplFileBeanObj = vendorapplFileDelegate.getVendorPOFile(vendorapplFileBeanObj);

                }

            }

            request.getSession().setAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA, vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getInvoiceFile() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }
//method is called when user uploads document

    public String postInvoiceFile(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_INVOICE_FILE_POST;
        HashMap hashObj = null;
        MultipartRequestParser mrp = null;
        byte[] fileContent = null;
        String fileName = "";
        String path = "";
        String fname = "";
        String ext = "";
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplmgrObj = new VendorApplFileManager();
        String remark = "";
        String Option = "";
        String vendor_number = "";
        String Appl_id = "";
        String Po_number = "";
        LinkedList<VendorApplFileBean> FileList = new LinkedList<VendorApplFileBean>();
        try {
            logger.log(Level.INFO, "VendorHandler :: postInvoiceFile() :: method called :: ");

            mrp = (MultipartRequestParser) request.getSession().getAttribute("MultipartRequestMrp");

            mrp.parseOnly(); //parse the request
            hashObj = mrp.webVars;
            if (hashObj != null && hashObj.size() > 0) {
                if (request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION) != null) {
                    VendorInputBean vendorInputBeanObj = ((VendorPrezData) request.getSession().getAttribute(ApplicationConstants.VENDOR_INPUT_FORM_SESSION_DATA)).getVendorInputBean();
                    vendorapplFileBeanObj.setEmpNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));

                    if (request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {

                        sReturnPage = ApplicationConstants.UIACTION_GET_EMP_INPUT_FORM;
                    } else {
                        sReturnPage = ApplicationConstants.UIACTION_INVOICE_FILE_POST;
                    }

                    vendorapplFileBeanObj.setApplicationId(vendorInputBeanObj.getApplId());
                    Appl_id = vendorInputBeanObj.getApplId();
                    if (vendorInputBeanObj.getSelectedModuleType().equals("PS")) {
                        vendorapplFileBeanObj.setPo_Number(vendorInputBeanObj.getProjectId());
                    } else {
                        vendorapplFileBeanObj.setPo_Number(vendorInputBeanObj.getPONumber());
                    }
                    //Po_number=vendorInputBeanObj.getPONumber();
                    if (hashObj.get("FOpt") != null) {

                        Option = (String) hashObj.get("FOpt");

                    }
                    if (request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) != null) {
                        vendorapplFileBeanObj.setLocation((String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                    }
                }
            }
            fileName = mrp.fileName;

            int mid = fileName.lastIndexOf(".");
            fname = fileName.substring(0, mid);
            ext = fileName.substring(mid + 1, fileName.length());       //If in future we need to restrict the file-extensions.
            fileContent = mrp.output;

            remark = (String) hashObj.get("txtRemark");

            // vendorapplFileBeanObj.setFileContents(fileContent);
            vendorapplFileBeanObj.setFileName(fname);
            vendorapplFileBeanObj.setFileType(ext);
            vendorapplFileBeanObj.setRemark(remark);

            if (Option != null) {
                vendorapplFileBeanObj.setOption(Option);
                // if (Option.equals("Letter Of Award") || Option.equals("Copy Of Agreement") || Option.equals("Insurance Copy") || Option.equals("Milestone Chart") || Option.equals("Bank Guarantee")) {
                if (Option.equals("Invoice Document") || Option.equals("Other Supporting Document") || Option.equals("Retention claim Document")) {
                    vendorapplFileBeanObj = vendorapplmgrObj.VendorApplFileTxnHelper(vendorapplFileBeanObj);//to insert record of uploaded file
                    if (vendorapplFileBeanObj != null) {
                        String location = "Application_number";
                        String foldername = vendorapplFileBeanObj.getId();
                        foldername = foldername.replaceAll("^0+(?!$)", "");
                        UploadVendorFile n = new UploadVendorFile();
                        path = n.UploadFile(fileContent, fileName, location, foldername);
                        System.out.println("path:" + path);
                        vendorapplFileBeanObj.setPath(path);
                        vendorapplmgrObj.VendorApplFileTxnHelper(vendorapplFileBeanObj);//to update path field in inserted data
                    }
                } else {
                    vendorapplFileBeanObj = vendorapplmgrObj.VendorPOFileTxnHelper(vendorapplFileBeanObj);
                    if (vendorapplFileBeanObj != null) {
                        String location = "Purchase_order";
                        String foldername = vendorapplFileBeanObj.getId();
                        foldername = foldername.replaceAll("^0+(?!$)", "");
                        UploadVendorFile n = new UploadVendorFile();
                        path = n.UploadFile(fileContent, fileName, location, foldername);
                        System.out.println("path:" + path);
                        vendorapplFileBeanObj.setPath(path);
                        vendorapplmgrObj.VendorPOFileTxnHelper(vendorapplFileBeanObj);
                    }
                }
            }

            String AppId = vendorapplFileBeanObj.getApplicationId();
            request.setAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_APPID, AppId);

            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA))) {
                FileList = (LinkedList) request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA);
            }
            if (!ApplicationUtils.isBlank(vendorapplFileBeanObj)) {
                if (!ApplicationUtils.isBlank(vendorapplFileBeanObj.getId())) {
                    FileList.add(vendorapplFileBeanObj);
                }
            }
            request.getSession().setAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA, FileList);
            if (request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                getEmpInputForm(request);
            } else {
                getVendorInputForm(request);
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: postInvoiceFile() :: Exception :: " + ex);
            ex.printStackTrace();
        }
        return sReturnPage;
    }

    private String MSEDCLVendorReport(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.REPORT_MSEDCL_VENDOR;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObj1 = new VendorPrezData();
        VendorPrezData vendorPrezDataObj2 = new VendorPrezData();

        POBean poBeanObj = new POBean();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        LinkedList POList = new LinkedList();
        HttpSession Session = request.getSession();
        String subAction = "";
        try {
            logger.log(Level.INFO, "VendorHandler :: getPOList() :: method called :: ");

            poBeanObj.setVendorNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));

            poBeanObj.setUserType("Vendor");

            vendorPrezDataObj1 = vendorMgrObj.getPOList(poBeanObj);
            vendorPrezDataObj.setPOList(vendorPrezDataObj1.getPOList());

            vendorPrezDataObj2 = vendorMgrObj.getlocationList(poBeanObj);
            vendorPrezDataObj.setLocationList(vendorPrezDataObj2.getLocationList());

            vendorBeanObj.setVendorNumber((String) Session.getAttribute(ApplicationConstants.USER_NAME_SESSION));

            Session.setAttribute(ApplicationConstants.VENDOR_REPORT_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getPOList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }/* End of Method */

    private String getVendorLegalInvoiceInputForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_FORM;
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplFileMgrObj = new VendorApplFileManager();
        LinkedList FileList = new LinkedList();
        String ApplID = "";
        String userType = (String) request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION);
//        if (request.getParameter("AppId") != null) {
//           
//        } else if(userType.equalsIgnoreCase("Vendor")) {
//            ApplID = null;
//            vendorInputBeanObj.setVendorNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));
//
//            VendorInputBean vendorInputForm = vendorMgrObj.getVendorInputForm(vendorInputBeanObj);
//            legalInvoiceInputBean.setVendorName(vendorInputForm.getVendorName());
//            legalInvoiceInputBean.setVendorNumber(vendorInputForm.getVendorNumber());
//            
//        }
           legalInvoiceInputBean.setSaveFlag(request.getParameter("status"));
        if (request.getParameter("AppId") != null) {
            ApplID = (String) request.getParameter("AppId");
            legalInvoiceInputBean.setApplId(Integer.parseInt(ApplID));
            legalInvoiceInputBean.setWhereClause("applId");
            legalInvoiceInputBean.setCreatedByUsertype(userType);
            List<LegalInvoiceInputBean> legalInvoiceInputList = vendorMgrObj.getLegalInvoiceInputList(legalInvoiceInputBean);
            if (legalInvoiceInputList != null && legalInvoiceInputList.size() > 0) {
                legalInvoiceInputBean = legalInvoiceInputList.get(0);
                System.out.println("legalInvoiceInputBean::::::::::::::::::::::::::::::" + legalInvoiceInputBean.getVendorName());
            }
        } else if (request.getAttribute(ApplicationConstants.VENDOR_LEGAL_FORM_FILE_SESSION_APPID) != null) {
            ApplID = (String) request.getAttribute(ApplicationConstants.VENDOR_LEGAL_FORM_FILE_SESSION_APPID);
            legalInvoiceInputBean.setApplId(Integer.parseInt(ApplID));
            legalInvoiceInputBean.setWhereClause("applId");
            legalInvoiceInputBean.setCreatedByUsertype(userType);
        } else {
            ApplID = null;
            if (userType.equalsIgnoreCase("Vendor")) {
                vendorInputBeanObj.setVendorNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));

                VendorInputBean vendorInputForm = vendorMgrObj.getVendorInputForm(vendorInputBeanObj);
                legalInvoiceInputBean.setVendorName(vendorInputForm.getVendorName());
                legalInvoiceInputBean.setVendorNumber(vendorInputForm.getVendorNumber());
                legalInvoiceInputBean.setMobileNo(vendorInputForm.getContactNumber());
                legalInvoiceInputBean.setEmailId(vendorInputForm.getEmailId());
            }
        }
        if (legalInvoiceInputBean.getApplId() != null) {
            vendorapplFileBeanObj.setApplicationId(legalInvoiceInputBean.getApplId() + "");
            FileList = vendorapplFileMgrObj.getVendorLegalApplFileList(vendorapplFileBeanObj);
        }
        legalInvoiceInputBean.setCreatedByUsertype(userType);
        
        if (legalInvoiceInputBean.getSaveFlag()!=null && legalInvoiceInputBean.getSaveFlag().equalsIgnoreCase("Accepted")){
        String sapStatus=getLegalInvoiceStatusFromSAP(legalInvoiceInputBean);
        legalInvoiceInputBean.setSaveFlag(sapStatus);
        }
//         if (module.equals("PS")) {
//                vendorInputBeanObj = vendorMgrObj.getVendorPsInputForm(vendorInputBeanObj);
//                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
//            } else if (module.equals("NON_PO")) {
//                vendorInputBeanObj = vendorMgrObj.getVendorNonpoInputForm(vendorInputBeanObj);
//                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
//            } else {

//                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
//            }
//         vendorInputBeanObj.setVendorNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));
//        VendorInputBean vendorInputForm = vendorMgrObj.getVendorInputForm(vendorInputBeanObj);
        request.getSession().setAttribute("LegalVendorInputForm", legalInvoiceInputBean);
        System.out.println("legalInvoiceInputBean::" + legalInvoiceInputBean + "    app_id:" + ApplID);
        request.getSession().setAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA, FileList);
        return sReturnPage;
    }
    private String getLegalInvoiceStatusFromSAP(LegalInvoiceInputBean liBean){
        String sapStatus="";
    if (liBean.getStatusFee() != null && liBean.getStatusFee().equalsIgnoreCase("Submitted")&& liBean.getParkPostDocNo()== null){
            sapStatus="With Accounts";
        }
        else if (liBean.getStartPostDocNo() != null &&(liBean.getStartPostDocNo().equals("16" )) && liBean.getPayDoneErpDoc() == null){
             sapStatus="With Cash";
        }
        
       else if (liBean.getStartPostDocNo() != null && (liBean.getStartPostDocNo().equals("16" ))&& liBean.getStartPayDoneErpDoc() !=null && liBean.getStartPayDoneErpDoc().equals("17")){
             sapStatus="Payment Done";
        }
        
       else if (liBean.getStartPostDocNo() != null && (liBean.getStartPostDocNo().equals("16" )) && liBean.getStartPayDoneErpDoc() !=null && liBean.getStartPayDoneErpDoc1().equals("020")){
             sapStatus="Payment Adjusted";
        }
        
      else  if (liBean.getStartPostDocNo() != null && (liBean.getStartPostDocNo().equals("16" )) && liBean.getStartPayDoneErpDoc() !=null && liBean.getStartPayDoneErpDoc().equals("12")){
             sapStatus="Payment Document Reversed";
        }
      else {
       sapStatus="With Technical";
      }
        
        
               
      return  sapStatus;
    }
    private String getVendorLegalInvoiceInputList(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_LIST;
        System.out.println("getVendorLegalInvoiceInputList");
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession session = request.getSession();
        List<LegalInvoiceInputBean> legalInvoiceInputBeanList = null;
        VendorApplFileDelegate vendorapplmgrObj = new VendorApplFileManager();
        
        try {
            logger.log(Level.INFO, "VendorHandler :: getVendorLegalInvoiceInputList() :: method called :: ");
            String userType = (String) request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION);
            System.out.println("userType::" + userType);
            if (userType.equalsIgnoreCase("Vendor")) {
                legalInvoiceInputBean.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
//            vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                legalInvoiceInputBean.setCreatedByUsertype("Vendor");
                legalInvoiceInputBean.setWhereClause("vendor");
//            vendorBeanObj.setInvoiceFlag("true");
            } else {
                VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
                LinkedList FileList = new LinkedList();
                LinkedList FileList1 = new LinkedList();
                legalInvoiceInputBean.setCreatedByUsertype("Emp");
                legalInvoiceInputBean.setWhereClause("Emp");
                if (legalInvoiceInputBean.getApplId() != null) {
                    String ApplID = legalInvoiceInputBean.getApplId() + "";
                    legalInvoiceInputBean.setApplId(Integer.parseInt(ApplID));
                    vendorapplFileBeanObj.setApplicationId(legalInvoiceInputBean.getApplId() + "");
                    FileList = vendorapplmgrObj.getVendorLegalApplFileList(vendorapplFileBeanObj);
                    //FileList1 = vendorapplFileMgrObj.getVendorPOFileList(vendorapplFileBeanObj);
                    FileList.addAll(FileList1);
                }
            }
            legalInvoiceInputBean.setCreatedByUsertype(userType);
            legalInvoiceInputBeanList = (List<LegalInvoiceInputBean>) vendorMgrObj.getLegalInvoiceInputList(legalInvoiceInputBean);

            session.setAttribute(ApplicationConstants.VENDOR_LEGAL_INVOICE_INPLIST_SESSION_DATA, legalInvoiceInputBeanList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getVendorLegalInvoiceInputList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }

    private String viewVendorLegalInvoiceDetails(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_VIEW_VENDOR_LEGAL_INPUT_LIST;

        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession session = request.getSession();
        List<LegalInvoiceInputBean> legalInvoiceInputBeanList = null;
        VendorApplFileDelegate vendorapplmgrObj = new VendorApplFileManager();
        String ApplID = "";
        legalInvoiceInputBean.setSaveFlag(request.getParameter("status"));
        try {
            logger.log(Level.INFO, "VendorHandler :: viewVendorLegalInvoiceDetails() :: method called :: ");
            String userType = (String) request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION);
            System.out.println("userType::" + userType);

            VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
            legalInvoiceInputBean.setCreatedByUsertype("Emp");
            legalInvoiceInputBean.setWhereClause("Emp");
            if (request.getParameter("AppId") != null) {
                ApplID = (String) request.getParameter("AppId");
                legalInvoiceInputBean.setApplId(Integer.parseInt(ApplID));
//                if (legalInvoiceInputBean.getApplId() != null) {
                ApplID = legalInvoiceInputBean.getApplId() + "";
                legalInvoiceInputBean.setApplId(Integer.parseInt(ApplID));
                legalInvoiceInputBean.setApplId(Integer.parseInt(ApplID));
                legalInvoiceInputBean.setWhereClause("applId");
                vendorapplFileBeanObj.setApplicationId(legalInvoiceInputBean.getApplId() + "");
//                    FileList = vendorapplmgrObj.getVendorLegalApplFileList(vendorapplFileBeanObj);
//                    //FileList1 = vendorapplFileMgrObj.getVendorPOFileList(vendorapplFileBeanObj);
//                    FileList.addAll(FileList1);
            }
            legalInvoiceInputBean.setCreatedByUsertype(userType);
            legalInvoiceInputBeanList = (List<LegalInvoiceInputBean>) vendorMgrObj.getLegalInvoiceInputList(legalInvoiceInputBean);
            if (legalInvoiceInputBeanList != null && legalInvoiceInputBeanList.size() > 0) {
                legalInvoiceInputBean = legalInvoiceInputBeanList.get(0);
            }
            session.setAttribute(ApplicationConstants.VENDOR_LEGAL_INVOICE_ACCEPTED_DATA, legalInvoiceInputBean);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: viewVendorLegalInvoiceDetails() :: Exception :: " + ex);
            //ex.printStackTrace();
        }

        return sReturnPage;
    }

    private String postLegalInvoiceFile(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_POST;
        HashMap hashObj = null;
        MultipartRequestParser mrp = null;
        byte[] fileContent = null;
        String fileName = "";
        String path = "";
        String fname = "";
        String ext = "";
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplmgrObj = new VendorApplFileManager();
        String remark = "";
        String Option = "";
        String vendor_number = "";
        String Appl_id = "";
        String Po_number = "";
        LinkedList<VendorApplFileBean> FileList = new LinkedList<VendorApplFileBean>();
        try {
            logger.log(Level.INFO, "VendorHandler :: postLegalInvoiceFile() :: method called :: ");

            mrp = (MultipartRequestParser) request.getSession().getAttribute("MultipartRequestMrp");
            System.out.println("mrp==" + mrp);
            mrp.parseOnly(); //parse the request
            hashObj = mrp.webVars;
            if (hashObj != null && hashObj.size() > 0) {
                if (request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION) != null) {
                    LegalInvoiceInputBean legalInvoiceInputBean = ((LegalInvoiceInputBean) request.getSession().getAttribute("LegalVendorInputForm"));
                    vendorapplFileBeanObj.setEmpNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));

                    if (request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {

                        sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_LEGAL_INPUT_FORM;
                    } else {
                        sReturnPage = ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_POST;
                    }

                    vendorapplFileBeanObj.setApplicationId(legalInvoiceInputBean.getApplId() + "");
                    Appl_id = legalInvoiceInputBean.getApplId() + "";
                    System.out.println("Appl_id::" + Appl_id);
                    //Po_number=vendorInputBeanObj.getPONumber();
                    if (hashObj.get("FOpt") != null) {

                        Option = (String) hashObj.get("FOpt");

                    }
                    if (request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) != null) {
                        vendorapplFileBeanObj.setLocation((String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                    }
                }
            }
            fileName = mrp.fileName;
            int mid = fileName.lastIndexOf(".");
            fname = fileName.substring(0, mid);
            ext = fileName.substring(mid + 1, fileName.length());       //If in future we need to restrict the file-extensions.
            fileContent = mrp.output;
            remark = (String) hashObj.get("txtRemark");

            // vendorapplFileBeanObj.setFileContents(fileContent);
            vendorapplFileBeanObj.setFileName(fname);
            vendorapplFileBeanObj.setFileType(ext);
            vendorapplFileBeanObj.setRemark(remark);

            if (Option != null) {
                vendorapplFileBeanObj.setOption(Option);
                // if (Option.equals("Letter Of Award") || Option.equals("Copy Of Agreement") || Option.equals("Insurance Copy") || Option.equals("Milestone Chart") || Option.equals("Bank Guarantee")) {
                if (Option.equals("Invoice Document") || Option.equals("Advocate Allotment Letter")
                        || Option.equals("Court Order")
                        || Option.equals("Miscellaneous Expenses Bills")
                        || Option.equals("Other Documents")) {
                    vendorapplFileBeanObj = vendorapplmgrObj.VendorLegalApplFileTxnHelper(vendorapplFileBeanObj);//to insert record of uploaded file
                    if (vendorapplFileBeanObj != null) {
                        String location = "Application_number";
                        String foldername = vendorapplFileBeanObj.getId();
                        foldername = foldername.replaceAll("^0+(?!$)", "");
                        UploadVendorFile n = new UploadVendorFile();
                        path = n.UploadLegalFile(fileContent, fileName, location, foldername);//  uncomment on cloud
//------------------------Comment on cloud---------------------
  //                     path = "D:/data/vpts/" + fileName;
  //                    File file = new File(path);
 //                       fileOutputStream.write(fileContent);
 //                       Path path1 = Paths.get(path);
   //                     Path write = Files.write(path1, fileContent);
//------------------------Comment on cloud End only for local file uploading---------------------                
                        System.out.println("path:" + path);
                        vendorapplFileBeanObj.setPath(path);
                        vendorapplmgrObj.VendorLegalApplFileTxnHelper(vendorapplFileBeanObj);//to update path field in inserted data
                    }
                }
            }
            String AppId = vendorapplFileBeanObj.getApplicationId();
            request.setAttribute(ApplicationConstants.VENDOR_LEGAL_FORM_FILE_SESSION_APPID, AppId);

            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA))) {
                FileList = (LinkedList) request.getSession().getAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA);
            }
            if (!ApplicationUtils.isBlank(vendorapplFileBeanObj)) {
                if (!ApplicationUtils.isBlank(vendorapplFileBeanObj.getId())) {
                    FileList.add(vendorapplFileBeanObj);
                }
            }
            request.getSession().setAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA, FileList);
            if (request.getSession().getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                getVendorLegalInvoiceInputForm(request);
            } else {
                getVendorLegalInvoiceInputForm(request);
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: postLegalInvoiceFile() :: Exception :: " + ex);
            ex.printStackTrace();
        }
        return sReturnPage;
    }

    public String getLegalInvoiceFile(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_GET;
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplFileDelegate = new VendorApplFileManager();
        String AppId = "";
        String FId = "";
        String Option = "";
        String txtPONumber = "";
        FileBean FileObj = null;
        try {
            logger.log(Level.INFO, "VendorHandler :: getLegalInvoiceFile() :: method called :: ");
            if (request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION) != null) {
                vendorapplFileBeanObj.setEmpNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));
                LegalInvoiceInputBean vendorInputBeanObj = ((LegalInvoiceInputBean) request.getSession().getAttribute("LegalVendorInputForm"));
                vendorapplFileBeanObj.setApplicationId(vendorInputBeanObj.getApplId() + "");
            }
            txtPONumber = (String) request.getParameter("txtPONumber");
            vendorapplFileBeanObj.setPo_Number(txtPONumber);
            if (!ApplicationUtils.isBlank(request.getParameter("AppId"))) {
                AppId = (String) request.getParameter("AppId");
            }
            if (!ApplicationUtils.isBlank(request.getParameter("FId"))) {
                FId = (String) request.getParameter("FId");

            }
            if (!ApplicationUtils.isBlank(request.getParameter("Option"))) {
                Option = (String) request.getParameter("Option");

            }
            vendorapplFileBeanObj.setId(FId);
            vendorapplFileBeanObj.setApplicationId(AppId);
            System.out.println("Option::" + Option);
            if (Option != null) {
                //    if (Option.equals("Letter Of Award") || Option.equals("Copy Of Agreement") || Option.equals("Insurance Copy") || Option.equals("Milestone Chart") || Option.equals("Bank Guarantee")) {
                if (Option.equals("Invoice Document") || Option.equals("Advocate Allotment Letter")
                        || Option.equals("Court Order")
                        || Option.equals("Miscellaneous Expenses Bills")
                        || Option.equals("Other Documents")) {
                    vendorapplFileBeanObj.setWhereClause("id");
                    vendorapplFileBeanObj = (VendorApplFileBean) vendorapplFileDelegate.getVendorLegalApplFileList(vendorapplFileBeanObj).get(0);
                }

            }

            
             
            vendorapplFileBeanObj.setFileName(vendorapplFileBeanObj.getFileName()+ "." + vendorapplFileBeanObj.getFileType() );
            vendorapplFileBeanObj.setFilePath(vendorapplFileBeanObj.getPath());
            
            request.getSession().setAttribute(ApplicationConstants.VENDOR_FORM_FILE_SESSION_DATA, vendorapplFileBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getLegalInvoiceFile() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }
    
    private String getVendorSearchCourtCase(HttpServletRequest request) {
        //System.out.println("::---------getCourtCaseSearch---------::");
        return ApplicationConstants.UIACTION_GET_VENDOR_SEARCH_COURT_CASE;
    }
}//class ends

