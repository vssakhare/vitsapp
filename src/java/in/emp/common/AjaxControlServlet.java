package in.emp.common;

// java imports--

import in.emp.vendor.VendorApplFileDelegate;
import in.emp.vendor.bean.VendorApplFileBean;
import in.emp.vendor.bean.VendorApplFilePrezData;
import in.emp.vendor.manager.VendorApplFileManager;
import in.emp.home.biometric.BiometricAttendDataDelegate;
import in.emp.home.biometric.bean.BiometricAttendDataBean;
import in.emp.home.biometric.bean.BiometricAttendDataReportBean;
import in.emp.home.biometric.manager.BiometricAttendDataManager;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.LinkedList;
import in.emp.sms.SmsController;
import in.emp.sms.SmsEmployee;
import in.emp.controller.VendorFormController;

import in.emp.util.ApplicationUtils;
import in.emp.security.bean.SecUserBean;
import in.emp.security.SecurityDelegate;
import in.emp.security.manager.SecurityManager;

import in.emp.search.bean.SearchBean;
import in.emp.search.SearchDelegate;
import in.emp.search.manager.SearchManager;
import in.emp.search.bean.SearchPrezData;
import in.emp.search.bean.LocationBean;

import in.emp.user.bean.UserPrezData;
import in.emp.user.bean.UserBean;
import in.emp.handler.UserHandler;
import in.emp.user.UserDelegate;
import in.emp.user.manager.UserManager;

import in.emp.handler.SearchHandler;
import in.emp.util.PagingManager;

//-- logger Imports
import in.emp.master.dao.MasterDao;
import in.emp.master.dao.OracleMasterDao;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import in.emp.home.correctionform.CorrectionFileDelegate;
import in.emp.home.correctionform.CorrectionFormDelegate;
import in.emp.home.correctionform.bean.CorrectionFileBean;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionform.bean.CorrectionFormPrezData;
import in.emp.home.correctionform.manager.CorrectionFileManager;
import in.emp.home.correctionform.manager.CorrectionFormManager;
import in.emp.home.correctionhr.CorrectionFormHRDelegate;
import in.emp.home.correctionhr.manager.CorrectionFormHRManager;
import in.emp.home.personalInfoUpdation.PersonalinfoupdationDelegate;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationBean;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationPrezData;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationaddressBean;
import in.emp.home.personalInfoUpdation.manager.PersonalinfoupdationManager;

//import java.util.Date;
import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.hrms.HRMSDelegate;
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.hrms.bean.HRMSUserPrezData;
import in.emp.hrms.manager.HRMSManager;
import in.emp.ldap.LDAP;
import in.emp.legal.bean.FeeTypeDtlsBean;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.legal.bean.OrganizationMasterBean;
import in.emp.legal.common.RemoveLegalInvoiceFile;
import in.emp.sms.bean.TemplateIdBean;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.HOBean;
import in.emp.vendor.bean.POBean;
import in.emp.vendor.bean.PoLineStatusBean;
import in.emp.vendor.bean.ProjBean;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.manager.VendorManager;
import in.emp.vendor.bean.SmsDTO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import javaldapapp.AssignOfficeDTO;
import javax.imageio.ImageIO;


/* public Class AjaxServlet extends Http Servlet to validate the login information of the user */
public class AjaxControlServlet extends HttpServlet {
    //private static Logger logger = new Logger(AjaxControlServlet.class.getName());

    private static String CLASS_NAME = AjaxControlServlet.class.getName();
    private static Logger logger = Logger.getLogger(AjaxControlServlet.class);
    private static String url;
    private HOBean hobeanObj;

    // Initializing the servlet --
    public void init(ServletConfig config) throws ServletException {
        String autoScheduleOnLoad = "N";
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: init() :: method called");
            if (System.getProperty("AUTO_SCHEDULE_ON_LOAD") != null) {
                autoScheduleOnLoad = System.getProperty("AUTO_SCHEDULE_ON_LOAD");
            }

            if (autoScheduleOnLoad.equals("Y")) {
                //ApplicationUtils.autoScheduleAll();
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: init() :: Exception :: " + ex);
        }
    }

    /* public doGet method that calls ececute method
     @param HttpServletRequest and HttpServletResponse
     @throws IOException and Servlet Exception*/
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            execute(request, response);
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: execute() :: Exception :: " + e);
        }
    } //-- End of method

    /* public doPostt method that calls doGet method
     @param HttpServletRequest and HttpServletResponse
     @throws IOException and Servlet Exception*/
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            doGet(request, response);
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: doPost() :: Exception :: " + e);
        }

    } // -- End of method

    /**
     * API to process a user request
     *
     * @param request object of HttpServletRequest and HttpServletResponse
     * @return String the UI action name
     * @throws Exception
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uiActionName = "";
        String responseString = "";

        try {
            if (!ApplicationUtils.isBlank(request.getParameter("uiaction"))) {
                uiActionName = request.getParameter("uiaction");

                if (uiActionName.equals(ApplicationConstants.UIACTION_VALIDATE_USER)) {
                    responseString = validateUser(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_LOGOUT)) {
                    responseString = logoutUser(request);
                } else if (uiActionName.equals("paging")) {
                    getPagingSearch(request);
                } else if (uiActionName.equals("getLocation")) {
                    responseString = getLocations(request);
                } else if (uiActionName.equals("getAjaxFeederList")) {
                    responseString = getNextList(request);
                } else if (uiActionName.equals("getAjaxSubstationList")) {
                    responseString = getSubstationList(request);
                } else if (uiActionName.equals("getAjaxAreaList")) {
                    responseString = getAllAreaList(request);
                } else if (uiActionName.equals("getAjaxDateList")) {
                    responseString = getDateList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_OTP_GET)) {
                    responseString = getOtp(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_OTP_RESET)) {
                    responseString = resetOtp(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_BIO_ATTEND_POST)) {
                    responseString = postBioAttend(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_POST)) {
                    responseString = postCorrectionForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FILE_POST)) {
                    responseString = postCorrectionFile(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_HR_POST)) {
                    responseString = postCorrectionFormHR(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_PI_UPDATION_FORM_POST)) {
                    responseString = postPiUpdationForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_EMERGENCY_CONTACTS_UPDATE_POST)) {
                    responseString = postUpdateEmergencyContacts(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_POST_VENDOR_LOGIN)) {
                    responseString = postVendorLogin(request);
//                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_INVOICE_LIST)) {
//                    responseString = getInvoiceList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_LIST)) {
                    responseString = getVendorList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_POST_VENDOR_APPL_FORM)) {
                    responseString = postVendorApplForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_INVOICE_FILE_POST)) {
                    responseString = postInvoiceFile(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_DELETE)) {
                    responseString = deleteLegalInvoiceFile(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_LOCATION)) {
                    responseString = getPOLocation(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_PO_DETAILS)) {
                    responseString = getPODetails(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_POLIST_DETAILS)) {
                    responseString = getPOListDetails(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_PROJECT_DETAILS)) {
                    responseString = getProjectDetails(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_INV_DETAILS)) {
                    responseString = getInvDetails(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_Hierarchy_LOCATION)) {
                    responseString = getHierarchyLocation(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_INVOICE)) {
                    responseString = getAuthPOList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_NONPO_POST_VENDOR_APPL_FORM)) {
                    responseString = postnonpoVendorApplForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_AUTH_INVOICE_LIST)) {
                    responseString = getAuthInvoiceList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS)) {
                    responseString = viewPOLineDetails(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_FORWARD_TO_OFFICECODE_DETAILS)) {
                    responseString = getForwardToOfficeCode(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_PLANT_DETAILS)) {
                    responseString = getPlantDetails(request);
                } else if (uiActionName.equals("getRetentionDetails")) {
                    responseString = getRetentionDetails(request);
                } else if (uiActionName.equals("saveClaimedRetentionDetails")) {
                    responseString = saveClaimedRetentionDetails(request);
                } else if (uiActionName.equals("getPartialRetentionDetails")) {
                    responseString = getPartialRetentionDetails(request);
                } else if (uiActionName.equals("postLegalApplicationForm")) {
                    responseString = postLegalApplicationForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_LEGAL_Hierarchy_LOCATION)) {
                    responseString = getLegalHierarchyLocation(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_LEGAL_INVOICE_FEE_TYPE_DELETE)) {
                    responseString = deleteFeeTypeDtl(request);
                }else if (uiActionName.equals(ApplicationConstants.UIACTION_OTP_CAPTCHA)) {
                  responseString = getCaptcha(request);
                } 
//                else if (uiActionName.equals(ApplicationConstants.UIACTION_POST_VENDOR_LIST)) {
//                    responseString = postVendorList(request);
//                }
            } else {
                responseString = ApplicationConstants.SYS_MSG_INVALID_USER;
            }

            //logger.log(Level.INFO, "AjaxControlServlet :: execute() :: responseString == "+responseString);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.log(Level.ERROR, "AjaxControlServlet :: execute() :: Exception :: " + e);
            responseString = ApplicationConstants.SYS_MSG_INVALID_USER;
        } finally {
            try {
                response.setContentType("text/xml");
                response.setHeader("Cache", "no-cache");
                System.out.println("response");
                response.getWriter().write(responseString);
            } catch (Exception ex) {
                logger.log(Level.ERROR, "AjaxControlServlet :: finally :: Exception :: " + ex);
                ex.printStackTrace();
            }
        }
        return responseString;

    }//--End of method

    /* public validateUser method that validates user info
     @param HttpServletRequest
     @returns String
     @throws Exception*/
    private String getPOListDetails(HttpServletRequest request) throws Exception {//get list of po numbers for employee login inoice creation form
        JSONObject obj = new JSONObject();
        LinkedList POList = new LinkedList();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession session = request.getSession();
        POBean poBeanObj = new POBean();
        String POListDetailString = "";
        String POListWithTypeString = "";
        try {
            poBeanObj.setUserType("Vendor");
            poBeanObj.setEmp_Po_YN("Y");
            poBeanObj.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            poBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtVendorNumber"));

            POList = vendorMgrObj.getPONumberList(poBeanObj);
            if (POList != null) {
                int i = 0;
                POListDetailString += "[";
                for (POBean pBean : (LinkedList<POBean>) POList) {
                    if (i != 0) {
                        POListDetailString += " , ";
                    }
                    POListDetailString += "\" " + pBean.getPONumber() + "-" + pBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "\"";
                    i++;
                }
                POListDetailString += "]";
                int j = 0;
                POListWithTypeString += "[";
                for (POBean pBean : (LinkedList<POBean>) POList) {
                    if (j != 0) {
                        POListWithTypeString += " , ";
                    }
                    POListWithTypeString += "\"" + pBean.getPONumber() + "-" + pBean.getPODesc().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "$$$$" + pBean.getSelectedModuleType() + "\"";

                    j++;
                }
                POListWithTypeString += "]";
            }
            obj.put("POListDetailString", POListDetailString);
            obj.put("POListWithTypeString", POListWithTypeString);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: getVendorList() :: Exception :: " + ex);
            //ex.printStackTrace();
            obj.put("Message1", "Error");
        } finally {
            return obj.toString();
        }

    }

    //thid method is called when employee opens a invoice which is having status as forwarded o retrieve the office code of forwarded to plant
    private String getForwardToOfficeCode(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        HttpSession Session = request.getSession();
        VendorDelegate vendorMgrObj = new VendorManager();

        POBean poBeanObj = new POBean();

        if (!ApplicationUtils.isBlank((request.getParameter("forwardToOfficeCode")))) {
            poBeanObj.setPlant(ApplicationUtils.getRequestParameter(request, "forwardToOfficeCode"));
        }
        poBeanObj = vendorMgrObj.getForwardedToOfficeCodeDetails(poBeanObj);
        Session.setAttribute(ApplicationConstants.VENDOR_FORWARD_TO_OFFICECODE_SESSION_DATA, poBeanObj);
        //Session attribute is set and is used to retrive the office code value at erp venodr form jsp
        return obj.toString();

    }

    private String getPlantDetails(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        String PlantDetailString = "";
        VendorDelegate vendorMgrObj = new VendorManager();
        LinkedList PlantList = new LinkedList();
        POBean poBeanObj = new POBean();

        if (!ApplicationUtils.isBlank((request.getParameter("txtPlant")))) {
            poBeanObj.setPlant(ApplicationUtils.getRequestParameter(request, "txtPlant"));
            poBeanObj.setPlantDesc(ApplicationUtils.getRequestParameter(request, "txtPlantDesc"));
        }
        PlantList = vendorMgrObj.getPlantDetails(poBeanObj);

        for (POBean pobeanobj : (LinkedList<POBean>) PlantList) {
            PlantDetailString += "<option value = '" + pobeanobj.getPlantDesc() + "' data-plant='" + poBeanObj.getPlantDesc() + "' >" + pobeanobj.getPlantDesc() + "</option>";

        }
        obj.put("PlantDetailString", PlantDetailString);
        return obj.toString();

    }

    //FUNCTION IS CALLED WHEN SHOW PO DETAILS BUTTON IS PRESSED.
    private String viewPOLineDetails(HttpServletRequest request) throws Exception {
        // String sReturnPage = ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS; 
        JSONObject obj = new JSONObject();
        HttpSession Session = request.getSession();
        LinkedList POLineDetail = new LinkedList();
        String POLineDetailString = "";
        String Status = "";
        if (!ApplicationUtils.isBlank((request.getParameter("txtStatus")))) {
            Status = ApplicationUtils.getRequestParameter(request, "txtStatus");
        }

        if (Status.equals("") || Status.equals("Saved") || Status.equals("Rejected")) {
            POLineDetailString += "<table class='table' id='podetailslist'><thead><tr class='success'><th width='10%'  class='text-center'>#</th><th width='10%'  class='text-center'><input type='checkbox' data-applid id='selectallcheck' data-applid=''/>  </th><th width='40%'  class='text-center'>PO Number</th><th width='40%' class='text-center' >PO Desc</th><th width='40%' class='text-center'>PLANT</th></tr></thead><tbody>";

        } else {
            POLineDetailString += " <table class='table' id='podetailslist' ><thead><tr class='success'><th width='10%'  class='text-center'>#</th><th width='10%'  class='text-center'><input type='checkbox' disabled data-applid id='selectallcheck' data-applid=''/>  </th><th width='40%'  class='text-center'>PO Number</th><th width='40%' class='text-center' >PO Desc</th><th width='40%' class='text-center'>PLANT</th></tr></thead><tbody>";

        }
//   System.out.println(POLineDetailString);
        VendorDelegate vendorMgrObj = new VendorManager();
        PoLineStatusBean poLineStatusBeanObj = new PoLineStatusBean();
        try {
            //TAKE THE INPUT OF APPL ID AND PO NUMBER TO FETCH THE PO LINE ITEMS HAVING APPL IDAND  BLANK APPL IDS 
            logger.log(Level.INFO, "VendorHandler :: viewClearingDetails() :: method called :: ");
            if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
                poLineStatusBeanObj.setContract_Document(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtApplId")))) {
                poLineStatusBeanObj.setAPPL_ID(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            }

            POLineDetail = vendorMgrObj.getPOLineDetails(poLineStatusBeanObj);
            int j = 0;
            for (PoLineStatusBean POLineBean : (LinkedList<PoLineStatusBean>) POLineDetail) {
                j++;
                String PO_NUMBER = "";
                String PO_Desc = "";
                String Plant = "";
                String PO_lineId = "";
                String Appl_Id = "";
                String PLANT_DESC = "";
                String Purchasing_Group = "";
                String Purchasing_Desc = "";
                if (!ApplicationUtils.isBlank(POLineBean.getContract_Document())) {
                    PO_NUMBER = POLineBean.getContract_Document();
                }
                if (!ApplicationUtils.isBlank(POLineBean.getShort_Text())) {
                    PO_Desc = POLineBean.getShort_Text();
                }
                if (!ApplicationUtils.isBlank(POLineBean.getPlant())) {
                    Plant = POLineBean.getPlant();
                }
                if (!ApplicationUtils.isBlank(POLineBean.getPLANT_DESC())) {
                    PLANT_DESC = POLineBean.getPLANT_DESC();
                }
                if (!ApplicationUtils.isBlank(POLineBean.getPoLineId())) {
                    PO_lineId = POLineBean.getPoLineId();
                }
                if (!ApplicationUtils.isBlank(POLineBean.getAPPL_ID())) {
                    Appl_Id = POLineBean.getAPPL_ID();
                }
                if (!ApplicationUtils.isBlank(POLineBean.getPurchasing_group())) {
                    Purchasing_Group = POLineBean.getPurchasing_group();
                }
                if (!ApplicationUtils.isBlank(POLineBean.getPurchasing_desc())) {
                    Purchasing_Desc = POLineBean.getPurchasing_desc();
                }

                if (Status.equals("") || Status.equals("Saved") || Status.equals("Rejected")) {
                    POLineDetailString += "<tr class='info' ><td width='5%' class='text-center'>" + j + "</td><td width='5%' class='text-center'> <input type='checkbox'  class='search_checkbox' name='search_checkbox' data-purchasing='" + Purchasing_Group + "' data-purchdesc='" + Purchasing_Desc + "' data-plant='" + Plant + "-" + PLANT_DESC + "' id ='poLineSelect'  value=" + PO_lineId + " data-applid=" + Appl_Id + " ></td><td width='20%' class='text-center'>" + PO_NUMBER + "</td><td width='20%' class='text-center'>" + PO_Desc + "</td><td width='20%' class='text-center'>" + Plant + "</td></tr>";

                } else {
                    POLineDetailString += "<tr class='info' ><td width='5%' class='text-center'>" + j + "</td><td width='5%' class='text-center'> <input type='checkbox' disabled class='search_checkbox' name='search_checkbox' data-purchasing='" + Purchasing_Group + "' data-purchdesc='" + Purchasing_Desc + "' data-plant='" + Plant + "-" + PLANT_DESC + "' id ='poLineSelect'  value=" + PO_lineId + " data-applid=" + Appl_Id + " ></td><td width='20%' class='text-center'>" + PO_NUMBER + "</td><td width='20%' class='text-center'>" + PO_Desc + "</td><td width='20%' class='text-center'>" + Plant + "</td></tr>";

                }
            }
            POLineDetailString += "</tbody></table>";
            obj.put("POLineDetailString", POLineDetailString);

            // SET THE SESSION ATTRIBUTE POLineDetail 
            Session.setAttribute(ApplicationConstants.VENDOR_PO_LINE_SESSION_DATA, POLineDetail);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: viewClearingDetails() :: Exception :: " + ex);

        }
        return obj.toString();
    }

    private String getAuthPOList(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_AUTH_PO_LIST;
        POBean poBeanObj = new POBean();
        VendorBean vendorBeanObj = new VendorBean();
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObj2 = new VendorPrezData();
        VendorDelegate vendorMgrObj = new VendorManager();
        if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
            poBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
        }
        poBeanObj.setUserType("Emp");
        vendorPrezDataObj2 = vendorMgrObj.getInvoiceList(vendorBeanObj);
        vendorPrezDataObj.setVendorInvoiceList(vendorPrezDataObj2.getVendorInvoiceList());
        return sReturnPage;
    }

    /* private String   getSubmitPlantlocation(HttpServletRequest request)throws Exception {
          JSONObject obj = new JSONObject();
                 PoLineStatusBean poLinestatusbeanobj = new PoLineStatusBean(); 
           LinkedList SubmitAtList = new LinkedList();
          String submitAtListString ="<option value =''>Select</option>";
           VendorDelegate vendorMgrObj = new VendorManager();
            poLinestatusbeanobj.setVendor_Number(ApplicationUtils.getRequestParameter(request, "vendor_number"));
            poLinestatusbeanobj.setContract_Document(ApplicationUtils.getRequestParameter(request, "po_number"));
            try{
                
      SubmitAtList = vendorMgrObj.getSubmitAtList(poLinestatusbeanobj);
        // vendorPrezDataObj.setCircleList(vendorPrezDataObj2.getCircleList());
        for (PoLineStatusBean poLinestatusbeanobj1 : (LinkedList<PoLineStatusBean>) SubmitAtList) {
        submitAtListString+="<option value = "+poLinestatusbeanobj1.getPlant()+ ">"+poLinestatusbeanobj1.getPlant()+"</option>";
         
        }
        obj.put("submitAtListString",submitAtListString);
      
      } catch (Exception ex) {
        logger.log(Level.ERROR, "VendorHandler :: getSubmitPlantlocation() :: Exception :: " + ex);
        //ex.printStackTrace();}
           return obj.toString();
   }*/
    private String getHierarchyLocation(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        POBean poBeanObj = new POBean();
        LinkedList hierarchyList = new LinkedList();
        HttpSession vendorSession = request.getSession();
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObj2 = new VendorPrezData();
        String hierarchylistString = "<option value =''>Select</option>";
        VendorDelegate vendorMgrObj = new VendorManager();
        poBeanObj.setOfficeCode(ApplicationUtils.getRequestParameter(request, "officecodevalue"));
        if (ApplicationUtils.getRequestParameter(request, "officecodetype").equals("ZON")) {
            poBeanObj.setPlant("CIR");
        } else if (ApplicationUtils.getRequestParameter(request, "officecodetype").equals("CIR")) {
            poBeanObj.setPlant("DIV");
        } else if (ApplicationUtils.getRequestParameter(request, "officecodetype").equals("DIV")) {
            poBeanObj.setPlant("SUB");
        }
        try {
            hierarchyList = vendorMgrObj.getcircleList(poBeanObj);
            // vendorPrezDataObj.setCircleList(vendorPrezDataObj2.getCircleList());
            for (POBean pBean : (LinkedList<POBean>) hierarchyList) {
                hierarchylistString += "<option value = " + pBean.getLocationCode().replace('"', ' ').replace('\'', ' ').replace('-', ' ') + "-" + pBean.getLocationType() + ">" + pBean.getLocationName() + "</option>";

            }
            obj.put("hierarchylistString", hierarchylistString);
            obj.put("plant", poBeanObj.getPlant());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getnonPoVendorList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

    private String getInvDetails(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();

        HttpSession vendorSession = request.getSession();
        ProjBean projbeanobj = new ProjBean();
        POBean POBeanObj = new POBean();
        String module = ApplicationUtils.getRequestParameter(request, "module_type");
        if (module.equals("PS")) {
            projbeanobj.setPROJECT_CODE(ApplicationUtils.getRequestParameter(request, "txtprojId"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                projbeanobj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
            } else {
                projbeanobj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            }
            projbeanobj = vendorMgrObj.getProjInvDetails(projbeanobj);
        } else {
            POBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                POBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
            } else {
                POBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            }
            POBeanObj = vendorMgrObj.getPOInvDetails(POBeanObj);

        }
        if (module.equals("PS"))//for setting changes as per ps or mm/pm
        {
            obj.put("PONumber", projbeanobj.getPROJECT_CODE());
            obj.put("total_inv_amt", projbeanobj.getTotal_Inv_Amt());
            //obj.put("total_inv_cnt", projbeanobj.getTotal_Inv_Cnt());
            obj.put("total_invoicable_amt", projbeanobj.getTotal_Invoicable_Amt());
            return obj.toString();
        } else {

            obj.put("PONumber", POBeanObj.getPONumber());
            obj.put("total_inv_amt", POBeanObj.getTotal_Inv_Amt());
            // obj.put("total_inv_cnt", POBeanObj.getTotal_Inv_Cnt());
            obj.put("total_invoicable_amt", POBeanObj.getTotal_Invoicable_Amt());
            return obj.toString();
        }
    }

    private String getPODetails(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        ProjBean projbeanobj = new ProjBean();
        POBean POBeanObj = new POBean();
        String module = ApplicationUtils.getRequestParameter(request, "module_type");
        if (module.equals("PS")) {
            projbeanobj.setPROJECT_CODE(ApplicationUtils.getRequestParameter(request, "txtprojId"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                projbeanobj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));

            }
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                projbeanobj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));

            }
            projbeanobj = vendorMgrObj.getProjDetails(projbeanobj);
        } else {
            POBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                POBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            }
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                projbeanobj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));

            }

            POBeanObj = vendorMgrObj.getPODetails(POBeanObj);

        }
        if (module.equals("PS"))//for setting changes as per ps or mm/pm
        {
            obj.put("PONumber", projbeanobj.getPROJECT_CODE());
            obj.put("po_amt", projbeanobj.getTotal_Po_Amt());
            obj.put("bal_amt", projbeanobj.getBal_amt());
            return obj.toString();
        } else {

            obj.put("PONumber", POBeanObj.getPONumber());
            obj.put("po_amt", POBeanObj.getPo_amt());
            obj.put("bal_amt", POBeanObj.getBal_po_amt());
            return obj.toString();
        }
    }

    private String getProjectDetails(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        ProjBean projbeanobj = new ProjBean();
        POBean POBeanObj = new POBean();
        projbeanobj.setPo_number(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
        if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
            projbeanobj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
        } else {
            projbeanobj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
        }
        projbeanobj = vendorMgrObj.getProjectDetails(projbeanobj);
        obj.put("PROJECT_CODE", projbeanobj.getPROJECT_CODE());
        obj.put("MATERIAL_PO", projbeanobj.getMaterial_PO());
        obj.put("CENTAGES_PO", projbeanobj.getCentages_PO());
        obj.put("SERVICE_PO", projbeanobj.getService_PO());
        return obj.toString();

    }

    private String getPOLocation(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_INPUT_FORM;
        JSONObject obj = new JSONObject();
        POBean POBeanObj = new POBean();
        ProjBean projbeanobj = new ProjBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        String module = ApplicationUtils.getRequestParameter(request, "module_type");
        if (module.equals("PS")) {
            projbeanobj.setUserType("Vendor");
            projbeanobj.setPROJECT_CODE(ApplicationUtils.getRequestParameter(request, "txtprojId"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                projbeanobj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
            } else {
                projbeanobj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            }
            projbeanobj = vendorMgrObj.getProjLocationList(projbeanobj);
        } else {
            POBeanObj.setUserType("Vendor");
            POBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                POBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
            } else {
                POBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            }
            POBeanObj = vendorMgrObj.getPOLocation(POBeanObj);
        }

        //vendorSession.setAttribute(ApplicationConstants.VENDOR_LOCATION_SESSION_DATA, POBeanObj);
        if (module.equals("PS"))//for setting changes as per ps or mm/pm
        {
            obj.put("plant", projbeanobj.getPLANT());
            obj.put("PONumber", projbeanobj.getPROJECT_CODE());
            obj.put("plant_desc", projbeanobj.getPLANT_DESC());
            obj.put("zone", projbeanobj.getZONE());
            obj.put("circle", projbeanobj.getCIRCLE());
            obj.put("division", projbeanobj.getDIVISION());
            obj.put("circle_code", projbeanobj.getCircle_code());
            obj.put("zone_code", projbeanobj.getZone_code());
            obj.put("purchasing_group", projbeanobj.getPurchasing_group());
            return obj.toString();
        } else {
            obj.put("plant", POBeanObj.getPlant());
            obj.put("PONumber", POBeanObj.getPONumber());
            obj.put("plant_desc", POBeanObj.getPlantDesc());
            obj.put("purchasing_group", POBeanObj.getPURCHASING_GROUP());
            return obj.toString();
        }
    }

    private String resetOtp(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        String resetOtp = request.getParameter("reset");
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        HRMSDelegate hrmsManagerObj = new HRMSManager();
        HttpSession vendorSession = request.getSession();

        String otp = "";
        try {
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                hrmsUserBeanObj.setEmpNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            }
            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.PASSWORD_RESET_OTP))) {
                otp = (String) request.getSession().getAttribute(ApplicationConstants.PASSWORD_RESET_OTP);
            }
            if (otp.equals(resetOtp)) {
                hrmsManagerObj.resetPassword(hrmsUserBeanObj);
                obj.put("UserId", "Password is reset to welcome");
            } else {
                obj.put("UserId", "OTP Mismatch!");

            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: resetotp() :: Exception :: " + e);
            obj.put("UserId", "Password is reset to welcome");
        }
        return obj.toString();
    }

    private String getOtp(HttpServletRequest request) throws Exception {

        String sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;
        String uid = "";
        String pa = "";
        String userType = "";
        SmsDTO objSms = new SmsDTO();
        JSONObject obj = new JSONObject();

        List<String> lstParams = new ArrayList<String>();
        List<String> lstcredential1 = new ArrayList<String>();
        VendorBean vendorBeanObj = new VendorBean();
        String responseString = ApplicationConstants.SYS_MSG_INVALID_USER;
        AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        VendorDelegate vendorMgrObj = new VendorManager();
        LDAP ldap = new LDAP();
        SmsController sms = new SmsController();
        SmsEmployee smsemp = new SmsEmployee();
        String userContactNo = "";
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        HRMSUserPrezData hrmsUserPrezDataObj = new HRMSUserPrezData();
        HRMSDelegate hrmsManagerObj = new HRMSManager();
        HttpSession vendorSession = request.getSession();
        TemplateIdBean templateBeanObj =new TemplateIdBean();
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: GetOtp() :: method called");
            uid = request.getParameter("txtUID");
            pa = request.getParameter("txtP");
            userType = request.getParameter("UserOpt");
            hrmsUserBeanObj.setEmpNumber(uid);
            hrmsUserBeanObj.setPass(pa);
            hrmsUserPrezDataObj.setHrmsUserBeanObj(hrmsUserBeanObj);
            if (userType.equals("Vendor")) {
                vendorBeanObj.setUserType("Vendor");
                hrmsUserPrezDataObj = hrmsManagerObj.getLogin(hrmsUserPrezDataObj);
                hrmsUserBeanObj = hrmsUserPrezDataObj.getHrmsUserBeanObj();
                vendorBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtUID"));
                if (!ApplicationUtils.isBlank(hrmsUserBeanObj.getValLogin())) {
                    if ((hrmsUserBeanObj.getValLogin().equals("NEW")) || (hrmsUserBeanObj.getValLogin().equals("OLD"))) {
                        vendorBeanObj.setPassword(pa);
                        vendorBeanObj = vendorMgrObj.getContactNumber(vendorBeanObj);
                        userContactNo = vendorBeanObj.getVendorContactNumber();
                        System.out.println("Vendor contact number :" + vendorBeanObj.getVendorContactNumber());
                    }
                }

                if (hrmsUserBeanObj.getValLogin().equals("INVALID")) {
                    obj.put("UserId", "Invalid Credentials! Please try again");
                }
            } else {
                assignOfficeDTO = ldap.getLogin(uid, pa);
                if (!(assignOfficeDTO.getOfficeCode().equals("")) || (assignOfficeDTO.getOfficeCode().equals(null))) {

                    userContactNo = assignOfficeDTO.getOfficerContactNo();
                }
                System.out.println("-----getOfficerContactNo : " + assignOfficeDTO.getOfficerContactNo());
            }

            System.out.println("Generating OTP using random() : ");
            System.out.print("Your OTP is : ");
            String AlphaNumericString = "0123456789";

            // create StringBuffer size of AlphaNumericString 
            StringBuilder sb = new StringBuilder(4);

            for (int i = 0; i < 4; i++) {

                // generate a random number between 
                // 0 to AlphaNumericString variable length 
                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb 
                sb.append(AlphaNumericString
                        .charAt(index));
            }

            //return sb.toString(); 
            // Using numeric values 
            System.out.println(sb.toString());
            // Using random method 

            request.getSession().setAttribute(ApplicationConstants.OTP_NUMBER, sb.toString());
            lstParams.add(uid);
            lstParams.add(sb.toString());
            //lstcredential1.add("639748");
            //lstcredential1.add("mse@12");
            lstcredential1.add(ApplicationConstants.OTP_URL);
            // lstcredential1.add("http://pod2-japi.instaalerts.zone/failsafe/HttpLink");
            objSms.setLstParams(lstParams);
            objSms.setMobileNumber(userContactNo);
            try {
                templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID1);
                templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);
               // sms.sendSMS(objSms, "5009", lstcredential1);
               sms.sendSMS(objSms, templateBeanObj.getTemplate_Id(), lstcredential1);
                System.out.println("usercontact No:" + userContactNo);
                obj.put("UserId", "OTP Sent Successfully on " + userContactNo.substring(0, 3) + "XXXXXX" + userContactNo.substring(7, 10));
                //obj.put("UserId","OTP Sent Successfully  ");
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.ERROR, "AjaxControlServlet :: generateotp() :: Exception :: " + e);
                if (obj.isEmpty()) {
                    obj.put("UserId", "Please Register Your Mobile Number");
                }
            }
            // obj.put("UserId", uid);
            //return sReturnPage;
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: generateotp() :: Exception :: " + e);
            obj.put("UserId", "Invalid Credentials! Please try again");
        }
        return obj.toString();
    }

    public String validateUser(HttpServletRequest request) throws Exception {
        String responseString = ApplicationConstants.SYS_MSG_INVALID_USER;
        SecUserBean secUserBean = null;
        SecurityDelegate securityDelegateObj = null;
        HttpSession userSession = request.getSession();
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: validateUser() :: method called");
            // calling fetchUserData
            secUserBean = fetchUserDetails(request);
            // create a delegate object
            securityDelegateObj = new SecurityManager();
            secUserBean = (SecUserBean) securityDelegateObj.getUserDetails(secUserBean);

            if (secUserBean != null) {
                if ((secUserBean.getStatusCd()).equals("A")) {
                    // setting User session--
                    userSession.setAttribute(ApplicationConstants.USER_SESSION_NAME, secUserBean);
                    userSession.setAttribute("userFunctionMap", secUserBean.getUserFunctionMap());
                    responseString = ApplicationConstants.SYS_MSG_ACTIVE_USER;
                } else {
                    responseString = ApplicationConstants.SYS_MSG_INACTIVE_USER;
                }
            } else {
                responseString = ApplicationConstants.SYS_MSG_INVALID_USER;
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: validateUser() :: Exception :: " + e);
            responseString = ApplicationConstants.SYS_MSG_INVALID_USER;
        }
        return responseString;
    } // - End of method

    /* public fetchUserDetails method that sets user info in bean object
     @param HttpServletRequest
     @returns secUserObj   the SecUserBean
     @throws Exception*/
    public SecUserBean fetchUserDetails(HttpServletRequest request) throws Exception {
        SecUserBean secUserObj = new SecUserBean();
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: fetchUserDetails() :: method called");
            secUserObj.setLoginId(request.getParameter("login_id"));
            secUserObj.setPassword(request.getParameter("password"));
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: fetchUserDetails() :: Exception :: " + e);
        }
        return secUserObj;
    }

    /* public API that removes user from session
     @param HttpServletRequest object
     @returns String responseString
     @throws Exception
     */
    public String logoutUser(HttpServletRequest request) throws Exception {
        String responseString = ApplicationConstants.SYS_MSG_LOGOUT_SUCESS;
        HttpSession userSession = request.getSession();

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: logoutUser() :: method called");
            userSession.removeAttribute(ApplicationConstants.USER_SESSION_NAME);
            userSession.invalidate();
            responseString = ApplicationConstants.SYS_MSG_LOGOUT_SUCESS;
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: logoutUser() :: Exception :: " + e);
            responseString = ApplicationConstants.SYS_MSG_LOGOUT_SUCESS;
        }

        return responseString;

    }// -- End of method

    /*Public API to get the Schedule Search Results while paging and passing it to paging Manager
     * @Param HttpServletRequest Object
     * @return void
     */
    public void getPagingSearch(HttpServletRequest request) throws Exception {
        SearchDelegate searchDelegateObject = null;
        SearchBean searchBeanObj = null;
        SearchPrezData searchPrezData = null;
        UserPrezData userPrezData = null;
        UserBean userBean = null;
        UserDelegate userDelegate = null;
        HttpSession session = request.getSession(false);
        LinkedList searchList = null;

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getPagingSearch() :: method called");
            String uiType = request.getParameter("uiType");
            if (!ApplicationUtils.isBlank(uiType) && uiType.equals("search")) {
                logger.log(Level.INFO, "AjaxControlServlet :: getPagingSearch() :: method called :: search");
                // fetch data from request----
                searchPrezData = (SearchPrezData) session.getAttribute("searchPrezData");
                searchBeanObj = searchPrezData.getSearchBean();
                searchBeanObj.setMax(Integer.parseInt(request.getParameter("max")));
                searchBeanObj.setMin(Integer.parseInt(request.getParameter("min")));
                searchBeanObj.setLastRowValue(request.getParameter("lastValue"));
                searchBeanObj.setLastValueDataType(request.getParameter("dataType"));

                // create Delegate object
                searchDelegateObject = new SearchManager();

                // calling Delegate--
//                searchList = searchDelegateObject.getSearchResultList(searchBeanObj); // Change here later // Prajakta
                searchBeanObj = searchDelegateObject.getSearchTotalRecords(searchBeanObj);

                // -- setting Data in SearchPrezData
                searchPrezData.setSearchBean(searchBeanObj);
                searchPrezData.setSearchResultList(searchList);

                //-- setting schedulePrezData object in session
                session.setAttribute("searchPrezData", searchPrezData);

                // -- calling Page Manager--
                PagingManager.startPaging(request, (searchPrezData.getSearchResultList()), true, SearchHandler.class.getName(), ((searchPrezData.getSearchBean()).getTotalRecords()));
            } else if (!ApplicationUtils.isBlank(uiType) && uiType.equals("rule")) {
                logger.log(Level.INFO, "AjaxControlServlet :: getPagingSearch() :: method called :: rule ");
            } else if (!ApplicationUtils.isBlank(uiType) && uiType.equals("workitem")) {
                logger.log(Level.INFO, "AjaxControlServlet :: getPagingSearch() :: method called :: workitem ");
            } else if (!ApplicationUtils.isBlank(uiType) && uiType.equals("user")) {
                logger.log(Level.INFO, "AjaxControlServlet :: getPagingSearch() :: method called :: user");

                // fetch data from request----
                userPrezData = (UserPrezData) session.getAttribute("userPrezData");
                userBean = userPrezData.getUserBean();
                userBean.setMax(Integer.parseInt(request.getParameter("max")));
                userBean.setMin(Integer.parseInt(request.getParameter("min")));
                userBean.setLastRowValue(request.getParameter("lastValue"));
                userBean.setLastValueDataType(request.getParameter("dataType"));
                userPrezData.setUserBean(userBean);
                // create Delegate object
                userDelegate = new UserManager();

                //-- Calling Manager--
                userPrezData = userDelegate.getUserSearchResults(userPrezData);

                //-- setting userPrezData object in session
                session.setAttribute("userPrezData", userPrezData);

                // -- calling Page Manager--
                PagingManager.startPaging(request, (userPrezData.getUserList()), true, UserHandler.class.getName(), ((userPrezData.getUserBean()).getTotalRecords()));
            }

        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: getPagingSearch() :: Exception :: " + e);
            //e.printStackTrace();
        }

    } // End Of Method

    /*
     *public getLocations OptionString method to get the locationOptionString for the given parent locationId
     *@param HttpServletRequest
     *@returns String
     *@throws Exception
     */
    public String getLocations(HttpServletRequest request) throws Exception {
        String responseString = "";
        String responseString1 = "";
        SearchDelegate searchDelegateObj = null;
        LocationBean locationBean = null;
        String selectedValue = "-1";
        String type = "";
        String ss_code = "";
        String variable = "";

        LinkedList feederList = null;
        LinkedList feederList1 = null;
        MasterDao masterDao = null;
        masterDao = new OracleMasterDao();
        JSONObject obj = null;
        String office_cd = "";
        String office_type_id = "";
        String office_loc_id = "";
        HttpSession session = request.getSession(true);

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getLocations() :: method called");
            obj = new JSONObject();

            if (!ApplicationUtils.isBlank(request.getParameter("type"))) {
                type = (request.getParameter("type"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("selectedValue"))) {
                selectedValue = (String) request.getParameter("selectedValue");
            }
            if (!ApplicationUtils.isBlank(request.getParameter("flag")) && (request.getParameter("flag").equals("1"))) {
                variable = request.getParameter("flag");
                ss_code = "%";
            } else if (!ApplicationUtils.isBlank(request.getParameter("parentLocId"))) {
                ss_code = (request.getParameter("parentLocId"));
            }

            String selectedSubstation = request.getParameter("selectedSubstation");
            String selectedFeeder = request.getParameter("selectedFeeder");
            String selectedDtc = request.getParameter("selectedDtc");
            String selectedConsumer = request.getParameter("selectedConsumer");

            if (type.equals("substation")) {
                ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------Start
                // feederList = masterDao.getMasterList("SUBSTATION", "SUBSTATION_ID", "SUBSTATION_CD", "SUBSTATION_NAME", "SUBSTATION_ID in (select SS_ID from lOCATION_SS_MAPPING WHERE LOC_ID like '" + ss_code + "')");
                feederList = masterDao.getMasterListForNDM("NDM_SUBSTATION", "SUBSTATION_ID_N", "SUBSTATION_CD_C", "SUBSTATION_NAME_C", "STATUS_CD_C", "BU_C in (select BU_CODE from LOCATION_BU_MAPPING WHERE LOC_ID like '" + ss_code + "')");
                ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------End
                //responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, selectedValue);
                if (!selectedValue.equals("undefined")) {
                    responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, selectedValue);
                } else {
                    responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, request.getParameter("selectedSubstation"));
                }

            } else if (type.equals("feeder")) {

                if (!selectedValue.equals("undefined")) {

                    ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------Start
                    //feederList = masterDao.getMasterList("FEEDER", "FEEDER_ID", "FEEDER_CD", "FEEDER_DESC", "LOC_SS_ID in (select LOC_SS_ID from location_ss_mapping where ss_id like '" + ss_code + "')");
                    feederList = masterDao.getMasterListForNDM("NDM_FEEDER", "FEEDER_ID_N", "FEEDER_CD_C", "FEEDER_NAME_C", "STATUS_CD_C", "SRC_SUBSTATION_ID_N like '" + ss_code + "'");
                    ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------End

                    responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, selectedValue);
                } else {
                    ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------Start
                    //feederList = masterDao.getMasterList("FEEDER", "FEEDER_ID", "FEEDER_CD", "FEEDER_DESC", "LOC_SS_ID in (select LOC_SS_ID from location_ss_mapping where ss_id like '" + request.getParameter("selectedSubstation") + "')");
                    feederList = masterDao.getMasterListForNDM("NDM_FEEDER", "FEEDER_ID_N", "FEEDER_CD_C", "FEEDER_NAME_C", "STATUS_CD_C", "SRC_SUBSTATION_ID_N like '" + request.getParameter("selectedSubstation") + "'");
                    ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------End

                    responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, request.getParameter("selectedFeeder"));
                }

            } else if (type.equals("dtc")) {
                if (!selectedValue.equals("undefined")) {
                    ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------Start
                    //feederList = masterDao.getMasterList("TRANSFORMER", "TRANSFORMER_ID", "TRANSFORMER_CD", "TRANSFORMER_NAME", "FEEDER_ID like '" + ss_code + "'");
                    feederList = masterDao.getMasterListForNDM("NDM_TRANSFORMER", "TRANSFORMER_ID_N", "TRANSFORMER_CD_C", "TRANFORMER_NAME_C", "STATUS_CD_C", "FEEDER_ID_N like '" + ss_code + "'");
                    responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, selectedValue);
                    feederList1 = masterDao.getMasterList("CONSUMER", "CONSUMER_ID", "CONSUMER_NUMBER", "CONSUMER_NAME", "FEEDER_ID like '" + ss_code + "'");
                    responseString1 = ApplicationUtils.getMasterIdNameALLOptionString(feederList1, selectedValue);
                    ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------End
                } else {
                    ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------Start
                    //feederList = masterDao.getMasterList("TRANSFORMER", "TRANSFORMER_ID", "TRANSFORMER_CD", "TRANSFORMER_NAME", "FEEDER_ID like '" + selectedFeeder + "'");
                    feederList = masterDao.getMasterListForNDM("NDM_TRANSFORMER", "TRANSFORMER_ID_N", "TRANSFORMER_CD_C", "TRANFORMER_NAME_C", "STATUS_CD_C", "FEEDER_ID_N like '" + selectedFeeder + "'");
                    ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc----------End
                    responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, request.getParameter("selectedDtc"));
                    feederList1 = masterDao.getMasterList("CONSUMER", "CONSUMER_ID", "CONSUMER_NUMBER", "CONSUMER_NAME", "FEEDER_ID like '" + selectedFeeder + "'");
                    responseString1 = ApplicationUtils.getMasterIdNameALLOptionString(feederList1, request.getParameter("selectedDtc"));
                }

            } else if (type.equals("town")) {
                //if (ss_code.equals("3")||ss_code.equals("1")) {
                if (ss_code.equals("1")) {
                    //    feederList = masterDao.getMasterList("TOWN_MASTER", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "STATUS_CD='A' ORDER BY TOWN_NAME");
                    feederList = masterDao.getMasterListSSO("TOWN_MASTER TM,PROJECT_TOWN PT", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "  TM.STATUS_CD   ='A'  AND TM.TOWN_ID=PT.TOWN_ID  AND PT.PROJECT_ID=1 ORDER BY TM.TOWN_NAME ");
                } else if (ss_code.equals("2")) {
                    feederList = masterDao.getMasterListSSO("TOWN_MASTER TM,PROJECT_TOWN PT", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "  TM.STATUS_CD   ='A'  AND TM.TOWN_ID=PT.TOWN_ID  AND PT.PROJECT_ID=2 ORDER BY TM.TOWN_NAME ");
                } else if (ss_code.equals("3")) {
                    feederList = masterDao.getMasterListSSO("TOWN_MASTER TM,PROJECT_TOWN PT", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "  TM.STATUS_CD   ='A'  AND TM.TOWN_ID=PT.TOWN_ID  AND PT.PROJECT_ID=3 ORDER BY TM.TOWN_NAME ");
                } else if (ss_code.equals("5")) {
                    feederList = masterDao.getMasterListSSO("TOWN_MASTER TM,PROJECT_TOWN PT", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "  TM.STATUS_CD   ='A'  AND TM.TOWN_ID=PT.TOWN_ID  AND PT.PROJECT_ID=5 ORDER BY TM.TOWN_NAME ");
                } else if (ss_code.equals("6")) {
                    feederList = masterDao.getMasterListSSO("TOWN_MASTER TM,PROJECT_TOWN PT", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "  TM.STATUS_CD   ='A'  AND TM.TOWN_ID=PT.TOWN_ID  AND PT.PROJECT_ID=6 ORDER BY TM.TOWN_NAME ");
                } else if (ss_code.equals("7")) {
                    feederList = masterDao.getMasterListSSO("TOWN_MASTER TM,PROJECT_TOWN PT", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "  TM.STATUS_CD   ='A'  AND TM.TOWN_ID=PT.TOWN_ID  AND PT.PROJECT_ID=7 ORDER BY TM.TOWN_NAME ");
                } else if (ss_code.equals("8")) {
                    feederList = masterDao.getMasterListSSO("TOWN_MASTER TM,PROJECT_TOWN PT", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "  TM.STATUS_CD   ='A'  AND TM.TOWN_ID=PT.TOWN_ID  AND PT.PROJECT_ID=8 ORDER BY TM.TOWN_NAME ");
                } else {
                    //feederList = masterDao.getMasterList("TOWN", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "STATUS_CD='A' ORDER BY TOWN_NAME");
                    feederList = masterDao.getMasterList("TOWN_MASTER TM,PROJECT_TOWN PT", "TOWN_ID", "TOWN_CODE", "TOWN_NAME", "WHERE  TM.STATUS_CD   ='A'  AND TM.TOWN_ID=PT.TOWN_ID   ORDER BY TM.TOWN_NAME ");
                }
                responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, selectedValue);
            } else if (type.equals("consumer")) {
                if (!selectedValue.equals("undefined")) {
                    feederList = masterDao.getMasterList("CONSUMER", "CONSUMER_ID", "CONSUMER_NUMBER", "CONSUMER_NAME", " CONSUMER_NUMBER = '" + selectedConsumer + "' OR  TRANSFORMER_ID like '" + ss_code + "'");
                    responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, selectedValue);
                } else {
                    feederList = masterDao.getMasterList("CONSUMER", "CONSUMER_ID", "CONSUMER_NUMBER", "CONSUMER_NAME", " CONSUMER_NUMBER = '" + selectedConsumer + "' OR   TRANSFORMER_ID like '" + selectedDtc + "'");
                    responseString = ApplicationUtils.getMasterIdNameALLOptionString(feederList, request.getParameter("selectedDtc"));
                }
            } else {

                // calling fetchUserData
                locationBean = new LocationBean();
                SecUserBean secUserBean = null;
                secUserBean = (SecUserBean) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_NAME);
                office_cd = (String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);
                office_type_id = (String) session.getAttribute(ApplicationConstants.OFFICE_TYPE_SESSION);
                office_loc_id = (String) session.getAttribute(ApplicationConstants.OFFICE_LOCATION_ID_SESSION);

                locationBean.setAccessLocation(office_loc_id);

                if (!ApplicationUtils.isBlank(request.getParameter("parentLocId"))) {
                    locationBean.setParentLocationId(Integer.parseInt(request.getParameter("parentLocId")));
                }
                if (variable.equals("1")) {
                    if (type.equals("circle")) {
                        locationBean.setLocationTypeId(2);
                        locationBean.setParentLocationString(locationBean.getAccessLocation());
                    }
                    if (type.equals("division")) {
                        locationBean.setLocationTypeId(3);
                        // locationBean.setParentLocationString("Select location_id from location where parent_location_id in("+secUserBean.getAccessLocationString()+") and location_type_id=2");

                    }
                    if (type.equals("subDivision")) {
                        locationBean.setLocationTypeId(4);
                    }

                    locationBean.setParentLocationId(0);
                }

                // create a delegate object
                searchDelegateObj = new SearchManager();
                locationBean.setAccessLocation(secUserBean.getAccessLocationString());
                LinkedList locationList = searchDelegateObj.getLocations(locationBean);

                responseString = ApplicationUtils.getLocationOptionALLString(locationList, selectedValue);
            }
            obj.put("responseString", responseString);
            obj.put("responseString1", responseString1);
            logger.log(Level.INFO, "AjaxControlServlet :: getLocations() :: responseString :: " + responseString);
            logger.log(Level.INFO, "AjaxControlServlet :: getLocations() :: responseString1 :: " + responseString1);
        } catch (Exception e) {
            logger.log(Level.ERROR, "AjaxControlServlet :: getLocations() :: Exception :: " + e);
            responseString = "";
        }

        return obj.toString();
    } // - End of method

    /*destroy the servlet*/
    public void destroy() {
    }

    private String getNextList(HttpServletRequest request) {
        MasterDao masterDao = null;
        masterDao = new OracleMasterDao();
        String feederOptionString = "";
        LinkedList feederList = null;
        String ss_code = "";
        String type = "";

        try {

            if (!ApplicationUtils.isBlank(request.getParameter("parent_code"))) {
                ss_code = (request.getParameter("parent_code"));
            }
            if (!ApplicationUtils.isBlank(request.getParameter("type"))) {
                type = (request.getParameter("type"));
            }
            if (type.equals("substation")) {
                feederList = masterDao.getMasterList("SUBSTATION", "SUBSTATION_ID", "SUBSTATION_CD", "SUBSTATION_NAME", "SUBSTATION_ID in (select SS_ID from lOCATION_SS_MAPPING WHERE LOC_SS_ID =" + ss_code + ")");
                feederOptionString = ApplicationUtils.getMasterIdNameOptionString(feederList, "-1");
            } else if (type.equals("feeder")) {
                feederList = masterDao.getMasterList("FEEDER", "FEEDER_ID", "FEEDER_CD", "FEEDER_DESC", "LOC_SS_ID in (select LOC_SS_ID from location_ss_mapping where loc_id=" + ss_code + ")");
                feederOptionString = ApplicationUtils.getMasterIdNameOptionString(feederList, "-1");
            } else if (type.equals("dtc")) {
                feederList = masterDao.getMasterList("TRANSFORMER", "TRANSFORMER_ID", "TRANSFORMER_CD", "TRANSFORMER_NAME", "FEEDER_ID=" + ss_code);
                feederOptionString = ApplicationUtils.getMasterIdNameOptionString(feederList, "-1");
            } else {
                feederList = masterDao.getMasterList("CONSUMER", "CONSUMER_ID", "CONSUMER_NUMBER", "CONSUMER_NAME", "TRANSFORMER_ID=" + ss_code);
                feederOptionString = ApplicationUtils.getMasterIdNameOptionString(feederList, "-1");
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: ajaxFeederList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }

        return feederOptionString;
    }

    private String getSubstationList(HttpServletRequest request) {
        MasterDao masterDao = null;
        masterDao = new OracleMasterDao();
        String substationOptionString = "";
        LinkedList substationList = null;
        String ss_code = "";
        String type = "";

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getSubstationList() :: called :: ");

            if (!ApplicationUtils.isBlank(request.getParameter("subdivisionId"))) {
                ss_code = (request.getParameter("subdivisionId"));
            }

            substationList = masterDao.getMasterList("SUBSTATION", "SUBSTATION_ID", "SUBSTATION_CD", "SUBSTATION_NAME", "SUBSTATION_ID in (select SS_ID from lOCATION_SS_MAPPING WHERE LOC_SS_ID =" + ss_code + ")");
            substationOptionString = ApplicationUtils.getMasterIdNameOptionString(substationList, "-1");

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: ajaxSUBSTATIONList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }

        return substationOptionString;
    }

    private String getAllAreaList(HttpServletRequest request) {
        MasterDao masterDao = null;
        masterDao = new OracleMasterDao();
        String strallAreaString = "";
        LinkedList AreaList = null;
        SecUserBean secUserBean = null;
        String area_code = "";
        String checkArea = "";
        String checkAreaStr = "";
        String selectedLocation = "";

        String strArea = "";

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getAllAreaList() :: called :: ");
            secUserBean = (SecUserBean) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_NAME);

            if (!ApplicationUtils.isBlank(request.getParameter("area_code"))) {
                area_code = request.getParameter("area_code");
            }
            if (area_code != "") {
                strArea = " and LOCATION_TYPE_ID= " + area_code;
            }
            if (!ApplicationUtils.isBlank(request.getParameter("selectedLocation"))) {
                selectedLocation = request.getParameter("selectedLocation");

            }

            if (!ApplicationUtils.isBlank(request.getParameter("checkAreaStr"))) {
                checkArea = request.getParameter("checkAreaStr");
            }
            if (checkArea != "") {
                checkAreaStr = " and location_id not in(" + checkArea + ")";
            }

            AreaList = masterDao.getMasterList("LOCATION", "LOCATION_ID", "LOCATION_CD", "LOCATION_DESC", "LOCATION_ID in( select location_id from (select lm.location_id from location lm start with location_id in (" + secUserBean.getAccessLocationString() + ") connect by prior location_id=Parent_location_id)) " + strArea + " " + checkAreaStr + " "
                    + " order by LOCATION_DESC");
            strallAreaString = ApplicationUtils.getMasterIdNameALLOptionString(AreaList, selectedLocation);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: getAllAreaList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }

        return strallAreaString;
    }

    private String getDateList(HttpServletRequest request) {
        MasterDao masterDao = null;
        masterDao = new OracleMasterDao();
        String strDateString = "";
        LinkedList readingList = null;
        String auth_code = "";
        String selectedDate = "-1";
        String condn = "";

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getDateList() :: called :: ");
            if (!ApplicationUtils.isBlank(request.getParameter("auth_code"))) {
                auth_code = request.getParameter("auth_code");
            }
            if (auth_code.equals("1")) {
                condn = "'A'";
            } else if (auth_code.equals("2")) {
                condn = "'R','Y'";
            }

            readingList = masterDao.getMasterList("Reading_Details", "DEVICE_ID", "substr(to_char(READING_DATE),1,18)||substr(to_char(READING_DATE),26,3)", "substr(to_char(READING_DATE),1,18)||substr(to_char(READING_DATE),26,3)", "AUTH_FLAG in (" + condn + ") order by reading_date");
            strDateString = ApplicationUtils.getMasterCodeOptionString(readingList, selectedDate);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: getDateList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }

        return strDateString;
    }

    private String postBioAttend(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_BIO_ATTEND_POST;
        BiometricAttendDataReportBean rbObj = new BiometricAttendDataReportBean();
        BiometricAttendDataBean BADBObj = new BiometricAttendDataBean();
        HttpSession BioAttendSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        String yyyymm;
        BiometricAttendDataReportBean rbTempObj = new BiometricAttendDataReportBean();
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postBioAttend() :: method called :: ");

            yyyymm = (String) request.getParameter("txtYYYYMM");
            int daycount = ApplicationUtils.getDayCount(yyyymm);
            String monyyyy = ApplicationUtils.dateToString(ApplicationUtils.stringToDate(yyyymm, "yyyyMM"), "MMM yyyy");

            BADBObj.setYYYYMM(yyyymm);
            if (((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION)) != null) {
                BADBObj.setEmpNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));
            }
            BADBObj.setAData(null);

            rbObj.setBlock(BADBObj);

            // -- Calling Manager--
            BiometricAttendDataDelegate bioAttObj = new BiometricAttendDataManager();

            rbTempObj = bioAttObj.getBiometricAttendDataReport(rbObj);

            if (rbTempObj != null) {
                rbObj = rbTempObj;

                obj.put("YYYYMM", rbObj.getBlock().getYYYYMM());
                obj.put("PNF", rbObj.getPNFlag());
                obj.put("SF", rbObj.getSaveFlag());
                obj.put("DC", daycount);
                for (int i = 0; i < 31 && i < daycount; i++) {
                    obj.put("D_" + (i), ApplicationUtils.dateToString(rbObj.getBlock().getAData()[i].getDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    obj.put("I_" + (i), rbObj.getBlock().getAData()[i].getInTime());
                    obj.put("O_" + (i), rbObj.getBlock().getAData()[i].getOutTime());
                    obj.put("S_" + (i), rbObj.getBlock().getAData()[i].getStatus());
                    obj.put("R_" + (i), rbObj.getBlock().getAData()[i].getRemark() != null ? rbObj.getBlock().getAData()[i].getRemark() : "");
                }
            }

            BioAttendSession.setAttribute(ApplicationConstants.BIOMETRIC_SESSION_DATA, rbObj);

            //obj.put("AppId", claimapprovalBeanObj.getApplicationId());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postBioAttend() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();

    }

    private String postCorrectionForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_POST;
        CorrectionFormPrezData correctionFormPrezData = new CorrectionFormPrezData();
        CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
        CorrectionFormDelegate correctionFormMgrObj = new CorrectionFormManager();
        HttpSession correctionSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postCorrectionForm() :: method called :: ");

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA))) {
                correctionFormPrezData = (CorrectionFormPrezData) correctionSession.getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA);
                correctionFormBeanObj = (CorrectionFormBean) correctionFormPrezData.getCorrectionFormBean();
            }

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                correctionFormBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                correctionFormBeanObj.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                correctionFormBeanObj.setLocation((String) correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }

            correctionFormBeanObj.setApplicationId(ApplicationUtils.getRequestParameter(request, "txtApplID"));
            correctionFormBeanObj.setCorrectionType(ApplicationUtils.getRequestParameter(request, "txtCorrectionType"));
            correctionFormBeanObj.setSystemValue(ApplicationUtils.getRequestParameter(request, "txtSystemValue"));
            correctionFormBeanObj.setCorrectionValue(ApplicationUtils.getRequestParameter(request, "txtCorrectValue"));

            subAction = ApplicationUtils.getRequestParameter(request, "subAction");

            if (subAction.equals("submit")) {
                correctionFormBeanObj.setApplicationStatus("Submitted");
            } else {
                correctionFormBeanObj.setApplicationStatus("Saved");
            }
            correctionFormBeanObj = correctionFormMgrObj.postCorrectionForm(correctionFormBeanObj);

            correctionFormPrezData.setCorrectionFormBean(correctionFormBeanObj);
            correctionSession.setAttribute(ApplicationConstants.CORRECTION_SESSION_DATA, correctionFormPrezData);
            obj.put("AppId", correctionFormBeanObj.getApplicationId());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postCorrectionForm() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

    private String postCorrectionFile(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FILE_POST;
        CorrectionFormPrezData correctionFilePrezDataObj = new CorrectionFormPrezData();
        CorrectionFileBean correctionFileBeanObj = new CorrectionFileBean();
        CorrectionFileDelegate correctionFileMgrObj = new CorrectionFileManager();
        LinkedList fileList = null;
        HttpSession correctionSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        String AppId = "";
        String FId = "";
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postCorrectionFile() :: method called :: ");

            subAction = (String) request.getParameter("subAction");
            AppId = (String) request.getParameter("AppId");
            FId = (String) request.getParameter("FId");

            if (subAction.equals("delete")) {

                correctionFileBeanObj.setApplicationId(AppId);
                correctionFileBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                correctionFileBeanObj.setId(FId);
                correctionFileBeanObj = correctionFileMgrObj.CorrectionFileDelHelper(correctionFileBeanObj);
            }
            obj.put("AppId", AppId);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postCorrectionFile() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

    private String postCorrectionFormHR(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_HR_POST;
        CorrectionFormPrezData correctionFormPrezData = new CorrectionFormPrezData();
        CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
        CorrectionFormHRDelegate correctionFormHRMgrObj = new CorrectionFormHRManager();
        HttpSession correctionSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        String remark = "";
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postCorrectionFormHR() :: method called :: ");

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA))) {
                correctionFormPrezData = (CorrectionFormPrezData) correctionSession.getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA);
                correctionFormBeanObj = (CorrectionFormBean) correctionFormPrezData.getCorrectionFormBean();
            }

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                correctionFormBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                correctionFormBeanObj.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                correctionFormBeanObj.setLocation((String) correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }

            correctionFormBeanObj.setApplicationId(ApplicationUtils.getRequestParameter(request, "txtApplID"));
            correctionFormBeanObj.setCorrectionValue(ApplicationUtils.getRequestParameter(request, "txtCorrectValue"));
            correctionFormBeanObj.setRemark(ApplicationUtils.getRequestParameter(request, "txtRemark"));
            subAction = ApplicationUtils.getRequestParameter(request, "subAction");

            if (subAction.equals("Approve")) {
                correctionFormBeanObj.setApplicationStatus("Approve");
            } else {
                correctionFormBeanObj.setApplicationStatus("Reject");
            }
            correctionFormBeanObj = correctionFormHRMgrObj.postCorrectionFormHR(correctionFormBeanObj);

            correctionFormPrezData.setCorrectionFormBean(correctionFormBeanObj);
            correctionSession.setAttribute(ApplicationConstants.CORRECTION_SESSION_DATA, correctionFormPrezData);
            obj.put("AppId", correctionFormBeanObj.getApplicationId());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postCorrectionFormHR() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

    private String postPiUpdationForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_PI_UPDATION_FORM_POST;
        PersonalinfoupdationPrezData personalinfoupdationPrezData = new PersonalinfoupdationPrezData();
        PersonalinfoupdationBean personalinfoupdationBeanObj = new PersonalinfoupdationBean();
        PersonalinfoupdationaddressBean personalinfoupdationaddressBeanObj = new PersonalinfoupdationaddressBean();
        PersonalinfoupdationDelegate piupdationMgrObj = new PersonalinfoupdationManager();
        HttpSession piupdationSession = request.getSession();
        JSONObject obj = new JSONObject();
        int ret2 = 0;
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postPiUpdationForm() :: method called :: ");

            if (!ApplicationUtils.isBlank(piupdationSession.getAttribute(ApplicationConstants.PERSONAL_INFO_UPDATION_SESSION_DATA))) {
                personalinfoupdationPrezData = (PersonalinfoupdationPrezData) piupdationSession.getAttribute(ApplicationConstants.PERSONAL_INFO_UPDATION_SESSION_DATA);
                personalinfoupdationBeanObj = (PersonalinfoupdationBean) personalinfoupdationPrezData.getPinfoupdationBean();
            }

            if (!ApplicationUtils.isBlank(piupdationSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                personalinfoupdationBeanObj.setEmpNumber((String) piupdationSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                personalinfoupdationBeanObj.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(piupdationSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                personalinfoupdationBeanObj.setLocation((String) piupdationSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtPEmailFlag"))) {
                personalinfoupdationBeanObj.setPEmailFlag((String) request.getParameter("txtPEmailFlag"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtPEmail"))) {
                personalinfoupdationBeanObj.setPEmail((String) request.getParameter("txtPEmail"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtPMobFlag"))) {
                personalinfoupdationBeanObj.setPMobFlag((String) request.getParameter("txtPMobFlag"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtMobileNo"))) {
                personalinfoupdationBeanObj.setMobileNo((String) request.getParameter("txtMobileNo"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtTAddrFlag"))) {
                personalinfoupdationBeanObj.setTempAddrFlag((String) request.getParameter("txtTAddrFlag"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtAddrLine1"))) {
                personalinfoupdationaddressBeanObj.setAddressLine1((String) request.getParameter("txtAddrLine1"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtAddrLine2"))) {
                personalinfoupdationaddressBeanObj.setAddressLine2((String) request.getParameter("txtAddrLine2"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtAddrLine3"))) {
                personalinfoupdationaddressBeanObj.setAddressLine3((String) request.getParameter("txtAddrLine3"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtAddrLine4"))) {
                personalinfoupdationaddressBeanObj.setAddressLine4((String) request.getParameter("txtAddrLine4"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtcity"))) {
                personalinfoupdationaddressBeanObj.setCity((String) request.getParameter("txtcity"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtstate"))) {
                personalinfoupdationaddressBeanObj.setState((String) request.getParameter("txtstate"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtpincode"))) {
                personalinfoupdationaddressBeanObj.setPincode((String) request.getParameter("txtpincode"));
            }
            personalinfoupdationBeanObj.setTempAddress(personalinfoupdationaddressBeanObj);

            if (!ApplicationUtils.isBlank(request.getParameter("txtMStatusFlag"))) {
                personalinfoupdationBeanObj.setPMStatusFlag((String) request.getParameter("txtMStatusFlag"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtMarStatus"))) {
                personalinfoupdationBeanObj.setPMStatus((String) request.getParameter("txtMarStatus"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtBloodGroupFlag"))) {
                personalinfoupdationBeanObj.setPBloodGroupFlag((String) request.getParameter("txtBloodGroupFlag"));
            }

            if (!ApplicationUtils.isBlank(request.getParameter("txtBloodGroup"))) {
                personalinfoupdationBeanObj.setPBloodGroup((String) request.getParameter("txtBloodGroup"));
            }

//            ret2 = piupdationMgrObj.postPiUpdationForm(personalinfoupdationBeanObj);
//
//            obj.put("ret1", ret2);
            personalinfoupdationBeanObj = piupdationMgrObj.postPiUpdationForm(personalinfoupdationBeanObj);
//
            personalinfoupdationPrezData.setPinfoupdationBean(personalinfoupdationBeanObj);
            piupdationSession.setAttribute(ApplicationConstants.PERSONAL_INFO_UPDATION_SESSION_DATA, personalinfoupdationPrezData);

            obj.put("PIUpdateMessage", personalinfoupdationBeanObj.getPIUpdateMsg());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postPiUpdationForm() :: Exception :: " + ex);
            //ex.printStackTrace();
            obj.put("PIUpdateMessage", "Error");
        }
        return obj.toString();
    }

    private String postUpdateEmergencyContacts(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_EMERGENCY_CONTACTS_UPDATE_POST;
        PersonalinfoupdationDelegate piupdationMgrObj = new PersonalinfoupdationManager();
        HttpSession piupdationSession = request.getSession();
        JSONObject obj = new JSONObject();
        EmergencyContactsBean emergencyContactsBean = new EmergencyContactsBean();
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postUpdateEmergencyContacts() :: method called :: ");

            if (!ApplicationUtils.isBlank(piupdationSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                emergencyContactsBean.setEmpNumber((String) piupdationSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                emergencyContactsBean.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(piupdationSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                emergencyContactsBean.setLocation((String) piupdationSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }

            emergencyContactsBean.setContact1FullName(ApplicationUtils.getRequestParameter(request, "txtContact1FullName"));
            emergencyContactsBean.setContact1PhoneNum1(ApplicationUtils.getRequestParameter(request, "txtContact1PhoneNum1"));
            emergencyContactsBean.setContact1PhoneNum2(ApplicationUtils.getRequestParameter(request, "txtContact1PhoneNum2"));
            emergencyContactsBean.setContact2FullName(ApplicationUtils.getRequestParameter(request, "txtContact2FullName"));
            emergencyContactsBean.setContact2PhoneNum1(ApplicationUtils.getRequestParameter(request, "txtContact2PhoneNum1"));
            emergencyContactsBean.setContact2PhoneNum2(ApplicationUtils.getRequestParameter(request, "txtContact2PhoneNum2"));

            emergencyContactsBean = piupdationMgrObj.postUpdateEmergencyContacts(emergencyContactsBean);

            obj.put("PIUpdateMessage", emergencyContactsBean.getStatus());

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postUpdateEmergencyContacts() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

    private String postVendorLogin(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_POST_VENDOR_LOGIN;
        HRMSDelegate hrmsManagerObj = new HRMSManager();
        HttpSession session = request.getSession();
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        HRMSUserPrezData hrmsUserPrezDataObj = new HRMSUserPrezData();
        JSONObject obj = new JSONObject();

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postVendorLogin() :: method called :: ");

            hrmsUserBeanObj.setEmpNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            hrmsUserBeanObj.setPass(ApplicationUtils.getRequestParameter(request, "txtConfPwd"));
            hrmsUserBeanObj.setUserType("V");
            hrmsUserPrezDataObj.setHrmsUserBeanObj(hrmsUserBeanObj);

            hrmsUserPrezDataObj = hrmsManagerObj.postVendorLogin(hrmsUserPrezDataObj);

            // obj.put("Message1", "Feedback Saved Successfully");
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postVendorLogin() :: Exception :: " + ex);
            //ex.printStackTrace();
            obj.put("Message1", "Error");
        } finally {
            return obj.toString();
        }

    }

    private String getVendorList(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_LIST;
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorBean vendorBeanObj = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        LinkedList POList = new LinkedList();
        HttpSession session = request.getSession();

        JSONObject obj = new JSONObject();
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: getVendorList() :: method called :: ");

            vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
            vendorBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNumber"));

            if (!ApplicationUtils.isBlank((request.getParameter("txtFrmDt")))) {
                vendorBeanObj.setInvoiceFromDate(ApplicationUtils.stringToDate((String) request.getParameter("txtFrmDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }
            if (!ApplicationUtils.isBlank((request.getParameter("txtToDt")))) {
                vendorBeanObj.setInvoiceToDate(ApplicationUtils.stringToDate((String) request.getParameter("txtToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            }

            vendorPrezDataObj = vendorMgrObj.getVendorList(vendorBeanObj);

            session.setAttribute(ApplicationConstants.VENDOR_LIST_SESSION_DATA, vendorPrezDataObj);
            // obj.put("Message1", "Feedback Saved Successfully");

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: getVendorList() :: Exception :: " + ex);
            //ex.printStackTrace();
            obj.put("Message1", "Error");
        } finally {
            return obj.toString();
        }

    }

    private String getAuthInvoiceList(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
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
            if (vendorBeanObj.getLocationId().equalsIgnoreCase("ALL")) {
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
        return obj.toString();
    }/* End of Method */
    private String postnonpoVendorApplForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_NONPO_POST_VENDOR_APPL_FORM;

        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession vendorSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        Date today = ApplicationUtils.getCurrentDate();
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postnonpoVendorApplForm() :: method called :: ");
            vendorInputBeanObj.setZone(ApplicationUtils.getRequestParameter(request, "txtzone"));
            vendorInputBeanObj.setCircle(ApplicationUtils.getRequestParameter(request, "txtCircle"));
            vendorInputBeanObj.setDivision(ApplicationUtils.getRequestParameter(request, "txtDivision"));
            vendorInputBeanObj.setSubDivision(ApplicationUtils.getRequestParameter(request, "txtSubDivision"));
            vendorInputBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));
            vendorInputBeanObj.setStatus(ApplicationUtils.getRequestParameter(request, "status"));
            vendorInputBeanObj.setApplId(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            vendorInputBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            vendorInputBeanObj.setVendorName((String) vendorSession.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION));
            vendorInputBeanObj.setInvoiceSubmitDate((ApplicationUtils.stringToDate((String) request.getParameter("txtInvSubmitDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT)));
            vendorInputBeanObj.setVendorInvoiceDate(ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            vendorInputBeanObj.setVendorInwardDate(ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInwardDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            vendorInputBeanObj.setVendorInvoiceAmount(ApplicationUtils.getRequestParameter(request, "txtInvoiceAmt"));
            vendorInputBeanObj.setInvoiceFromDate((ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceFromDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT)));
            vendorInputBeanObj.setInvoiceToDate((ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceToDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT)));
            vendorInputBeanObj.setSelectedModuleType(ApplicationUtils.getRequestParameter(request, "txtModule"));//Nature of work for non po 
            vendorInputBeanObj.setWorkDetailDesc(ApplicationUtils.getRequestParameter(request, "txtWorkDetail"));
            Date invoiceDate = (ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            Date inwardDate = (ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInwardDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            Date invoiceFrmDate = (ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceFromDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            Date invoiceToDate = (ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceToDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            subAction = (String) request.getParameter("subAction");

            if (subAction.equals("submit")) {
                vendorInputBeanObj.setSaveFlag("Submitted");

            }
            if (subAction.equals("save")) {

                vendorInputBeanObj.setSaveFlag("Saved");
            }
            vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            if (!((invoiceDate.after(today) || inwardDate.after(today) || invoiceFrmDate.after(today) || invoiceToDate.after(today)))
                    && (vendorInputBeanObj.getSaveFlag().equals("Saved"))) {
                vendorPrezDataObj = vendorMgrObj.VendorNonpoApplFormTxnHelper(vendorPrezDataObj);

                if (vendorPrezDataObj.getVendorInputBean().getApplId() != "") {
                    obj.put("Message1", "Form Saved Successfully with Application ID " + vendorPrezDataObj.getVendorInputBean().getApplId());
                } else {
                    obj.put("Message1", "Invoice number already created..Please Check!!! ");
                }
            } else {
                obj.put("Message1", "Invoice Date or Inward Date cannot be a future date !");
            }
            obj.put("AppId", vendorPrezDataObj.getVendorInputBean().getApplId());
            vendorSession.setAttribute(ApplicationConstants.VENDOR_NON_PO_FORM_SESSION_DATA, vendorPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postnonpoVendorApplForm() :: Exception :: " + ex);
            //ex.printStackTrace();

        } finally {
            if (obj.isEmpty()) {
                obj.put("Message1", "Something Went Wrong..Please Raise Issue!!! ");
            }
            return obj.toString();
        }
    }

    private String postVendorApplForm(HttpServletRequest request) throws Exception {
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        VendorInputBean vendorInputBeanObj1 = new VendorInputBean();
        POBean POBeanObj = new POBean();
        Date sysdate = new Date();
        HttpSession vendorSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        Date today = ApplicationUtils.getCurrentDate();

        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postVendorApplForm() :: method called :: ");
            vendorInputBeanObj.setSelectedModuleType(ApplicationUtils.getRequestParameter(request, "module_type"));//module type selected to differentiate proj id and po number
            if (ApplicationUtils.getRequestParameter(request, "module_type").equals(ApplicationConstants.PROJECT_SYSTEM)) {
                vendorInputBeanObj.setProjectId(ApplicationUtils.getRequestParameter(request, "txtprojId"));
                vendorInputBeanObj.setINVOICE_TYPE(ApplicationUtils.getRequestParameter(request, "txtInvoiceType"));
                POBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtprojId"));//to find the details of proj id

                vendorInputBeanObj.setMATERIAL_PO(ApplicationUtils.getRequestParameter(request, "txtMaterialPo"));
                vendorInputBeanObj.setCENTAGES_PO(ApplicationUtils.getRequestParameter(request, "txtCentagesPo"));
                vendorInputBeanObj.setSERVICE_PO(ApplicationUtils.getRequestParameter(request, "txtServicePo"));

            } else {
                POBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
                //vendorInputBeanObj.setSELECTEDPLANT(ApplicationUtils.getRequestParameter(request, "selectedPlant") );
            }
            POBeanObj.setSelectedModule(ApplicationUtils.getRequestParameter(request, "module_type"));
            POBeanObj.setApplId(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                vendorInputBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtvendorId"));
                vendorInputBeanObj.setVendorName(ApplicationUtils.getRequestParameter(request, "vendor_name"));
                vendorInputBeanObj.setEMP_ID((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                vendorInputBeanObj.setEMP_NAME((String) vendorSession.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION));
                vendorInputBeanObj.setEMP_DESIGNATION((String) vendorSession.getAttribute(ApplicationConstants.DESIGNATION_SESSION));
                vendorInputBeanObj.setCREATED_USER_TYPE("EMP");

            } else {
                vendorInputBeanObj.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                vendorInputBeanObj.setVendorName((String) vendorSession.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION));
                vendorInputBeanObj.setCREATED_USER_TYPE("VENDOR");
            }
            vendorInputBeanObj.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));

            vendorInputBeanObj.setVendorInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));

            Date invoiceDate = (ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            Date vendorinwardDate = (ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInwardDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            vendorInputBeanObj.setStatus(ApplicationUtils.getRequestParameter(request, "status"));;
            //Date inwardToDate= (ApplicationUtils.stringToDate((String) request.getParameter("txtInvoiceToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            vendorInputBeanObj.setApplId(ApplicationUtils.getRequestParameter(request, "txtApplId"));
            vendorInputBeanObj.setDesignation((String) vendorSession.getAttribute(ApplicationConstants.DESIGNATION_SESSION));
            vendorInputBeanObj.setOffice_Code((String) vendorSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            vendorInputBeanObj.setInvoiceSubmitDate((ApplicationUtils.stringToDate((String) request.getParameter("txtInvSubmitDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT)));

            vendorInputBeanObj.setVendorInvoiceDate(ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            vendorInputBeanObj.setVendorInwardDate(ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInwardDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            vendorInputBeanObj.setVendorInvoiceAmount(ApplicationUtils.getRequestParameter(request, "txtInvoiceAmt"));
            vendorInputBeanObj.setModuleSaveFlag(ApplicationUtils.getRequestParameter(request, "ModuleFlag"));
            vendorInputBeanObj.setInwardNumber(ApplicationUtils.getRequestParameter(request, "txtInwardNum"));
            vendorInputBeanObj.setLocation(ApplicationUtils.getRequestParameter(request, "txtPlant"));
            vendorInputBeanObj.setInwardDate(ApplicationUtils.stringToDate((String) request.getParameter("txtInwardDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            /* if (!ApplicationUtils.isBlank(request.getParameter("txtModule"))) {//used for sms escalation
                   vendorInputBeanObj.setSelectedModule(ApplicationUtils.getRequestParameter(request, "txtModule"));
              
            }*/
            vendorInputBeanObj.setSubmitAtPlant(ApplicationUtils.getRequestParameter(request, "SubmitAtPlant"));
            vendorInputBeanObj.setSubmitAtDesc(ApplicationUtils.getRequestParameter(request, "SubmitAtDesc"));
            vendorInputBeanObj.setPurchasingDesc(ApplicationUtils.getRequestParameter(request, "PurchasingDesc"));
            vendorInputBeanObj.setPurchasing_group(ApplicationUtils.getRequestParameter(request, "Purchasing_group"));
            vendorInputBeanObj.setPoPurchasing_group(ApplicationUtils.getRequestParameter(request, "txtOnloadPurchasing_group"));
            vendorInputBeanObj.setVendorInvoiceResubmit(ApplicationUtils.stringToDate((String) request.getParameter("txtResubmitDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            // vendorInputBeanObj.setInvoiceFromDate(ApplicationUtils.stringToDate((String) request.getParameter("txtInvoiceFrmDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            //vendorInputBeanObj.setInvoiceToDate(ApplicationUtils.stringToDate((StringgetMobileNumberDetails) request.getParameter("txtInvoiceToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            subAction = (String) request.getParameter("subAction");

            if (subAction.equals("submit")) {
                vendorInputBeanObj.setSaveFlag("Submitted");

            }
            if (subAction.equals("save")) {

                vendorInputBeanObj.setSaveFlag("Saved");
            }
            if (subAction.equals("approve")) {

                vendorInputBeanObj.setSaveFlag("Verified");
                vendorInputBeanObj.setIsApprovedFlag("Y");
                vendorInputBeanObj.setInvoiceApprDAte(sysdate);
            }
            if (subAction.equals("reject")) {
                vendorInputBeanObj.setSaveFlag("Rejected");
                vendorInputBeanObj.setIsRejectedFlag("Y");

                vendorInputBeanObj.setInvoiceREjDAte(sysdate);
                vendorInputBeanObj.setReason(ApplicationUtils.getRequestParameter(request, "txtReason"));
            }
            if (subAction.equals("forward")) {
                vendorInputBeanObj.setSaveFlag("Forwarded");
                vendorInputBeanObj.setForwardToPlant(ApplicationUtils.getRequestParameter(request, "ForwardToPlant"));
                vendorInputBeanObj.setForwardToDesc(ApplicationUtils.getRequestParameter(request, "ForwardToDesc"));
            }

            vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            if ((!((invoiceDate.after(today)) && (vendorinwardDate.after(today))))// || (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (vendorInputBeanObj.getSaveFlag().equals("Submitted"))) {
                obj = VendorFormController.getSubmittedFormStatus(POBeanObj, vendorInputBeanObj, vendorInputBeanObj1, vendorPrezDataObj, request);
                if (vendorInputBeanObj.getApplId() != null && vendorInputBeanObj.getINVOICE_TYPE() != null && vendorInputBeanObj.getINVOICE_TYPE().equalsIgnoreCase("Retention Claim Charges")) {
                    VendorFormController.submittedClaimedRetentionDetailsFormStatus(vendorPrezDataObj, request);
                }
            } else if ((!(invoiceDate.after(today) || (vendorinwardDate.after(today))))// || (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (vendorInputBeanObj.getSaveFlag().equals("Saved"))) {

                obj = VendorFormController.getSaveFormStatus(vendorPrezDataObj, request);
                /* if(vendorInputBeanObj.getApplId()!=null && vendorInputBeanObj.getINVOICE_TYPE()!=null 
                        && vendorInputBeanObj.getINVOICE_TYPE().equalsIgnoreCase("Retention Claim Charges")){
                   //obj=
                           VendorFormController.saveClaimedRetentionDetailsFormStatus(vendorPrezDataObj,request);
               }*/
            } else if ((!(invoiceDate.after(today) || (vendorinwardDate.after(today))))//|| (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (vendorInputBeanObj.getSaveFlag().equals("Verified"))) {
                obj = VendorFormController.getVerifiedFormStatus(vendorPrezDataObj, request);
                if (vendorInputBeanObj.getApplId() != null && vendorInputBeanObj.getINVOICE_TYPE() != null && vendorInputBeanObj.getINVOICE_TYPE().equalsIgnoreCase("Retention Claim Charges")) {
                    //obj=
                    VendorFormController.verifiedClaimedRetentionDetailsFormStatus(vendorPrezDataObj, request);
                }
            } else if ((!(invoiceDate.after(today) || (vendorinwardDate.after(today))))// || (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (vendorInputBeanObj.getSaveFlag().equals("Rejected"))) {

                obj = VendorFormController.getRejectedFormStatus(vendorPrezDataObj, request);
            } else if ((!(invoiceDate.after(today) || (vendorinwardDate.after(today))))// || (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (vendorInputBeanObj.getSaveFlag().equals("Forwarded"))) {

                obj = VendorFormController.getForwardedFormStatus(POBeanObj, vendorPrezDataObj, vendorInputBeanObj, request);
            } else {
                obj.put("Message1", "Invoice Date or Inward Date cannot be a future date !");
            }
            obj.put("AppId", vendorPrezDataObj.getVendorInputBean().getApplId());
            vendorSession.setAttribute(ApplicationConstants.VENDOR_APPL_FORM_SESSION_DATA, vendorPrezDataObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postVendorApplForm() :: Exception :: " + ex);
            //ex.printStackTrace();
            //obj.put("Message1", "Error!! Please check Invoice From Date and Invoice To Date.");
        } finally {
            if (obj.isEmpty()) {
                obj.put("Message1", "Something Went Wrong..Please Raise Issue!!! ");
            }

            return obj.toString();
        }

    }

//   private String postVendorList(HttpServletRequest request) throws Exception {
//        String sReturnPage = ApplicationConstants.UIACTION_POST_VENDOR_LIST;
//        VendorBean vendorFormBeanObj = new VendorBean();
//        VendorDelegate vendorFormMgrObj = new VendorManager();
//        HttpSession vendorlistSession = request.getSession();
//        JSONObject obj = new JSONObject();
//        String subAction = "";
//        String AppId = "";
//        try {
//            logger.log(Level.INFO, "AjaxControlServlet :: postVendorList() :: method called :: ");
//
//            subAction = (String) request.getParameter("subAction");
//            AppId = (String) request.getParameter("AppId");
//
//            if (subAction.equals("delete")) {
//                vendorFormBeanObj.setApplId(AppId);
//                vendorFormBeanObj.setVendorNumber((String) vendorlistSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
//                vendorFormBeanObj = vendorFormMgrObj.VendorApplTxnHelper(vendorFormBeanObj);
//            }
//
//            obj.put("AppId", AppId);
//
//        } catch (Exception ex) {
//            logger.log(Level.ERROR, "AjaxControlServlet :: postVendorList() :: Exception :: " + ex);
//            //ex.printStackTrace();
//        }
//        return obj.toString();
//
//    }
    //method is called when user deletes the uploaded file.
    private String postInvoiceFile(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_INVOICE_FILE_POST;
        VendorApplFilePrezData vendorapplFilePrezDataObj = new VendorApplFilePrezData();
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileBean vendorapplFileBeanObj_ftp = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplFileMgrObj = new VendorApplFileManager();
        LinkedList vendorapplList = new LinkedList();
        HttpSession vendorapplSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        String AppId = "";
        String FId = "";
        String Option = "";
        String txtPONumber = "";
        String FileName = "";
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postInvoiceFile() :: method called :: ");

            subAction = (String) request.getParameter("subAction");
            AppId = (String) request.getParameter("AppId");
            txtPONumber = (String) request.getParameter("txtPONumber");
            FId = (String) request.getParameter("FId");
            Option = (String) request.getParameter("Option");
            FileName = (String) request.getParameter("FileName");
            FileBean FileObj = null;
            if (subAction.equals("delete")) {
                vendorapplFileBeanObj.setApplicationId(AppId);
                vendorapplFileBeanObj.setPo_Number(txtPONumber);
                vendorapplFileBeanObj.setEmpNumber((String) vendorapplSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));

                vendorapplFileBeanObj.setId(FId);

                if (Option != null) {
                    // if(Option.equals("Letter Of Award") ||Option.equals("Copy Of Agreement")||Option.equals("Insurance Copy")||Option.equals("Milestone Chart")||Option.equals("Bank Guarantee") ){
                    if (Option.equals("Invoice Document") || Option.equals("Other Supporting Document") || Option.equals("Retention claim Document")) {
                        vendorapplFileBeanObj_ftp = vendorapplFileMgrObj.getVendorApplFile(vendorapplFileBeanObj);
                        String filepath = vendorapplFileBeanObj_ftp.getPath();
                        filepath = filepath.substring(0, filepath.lastIndexOf("/"));
                        RemoveVendorFile rem = new RemoveVendorFile();
                        rem.RemoveFile(filepath, FileName);//remove file from ftp location
                        vendorapplFileBeanObj = vendorapplFileMgrObj.VendorApplFileDelHelper(vendorapplFileBeanObj);//remove entry from database

                    } else {
                        vendorapplFileBeanObj_ftp = vendorapplFileMgrObj.getVendorPOFile(vendorapplFileBeanObj);
                        String filepath = vendorapplFileBeanObj_ftp.getPath();
                        filepath = filepath.substring(0, filepath.lastIndexOf("/"));
                        RemoveVendorFile rem = new RemoveVendorFile();
                        rem.RemoveFile(filepath, FileName);
                        vendorapplFileBeanObj = vendorapplFileMgrObj.VendorPOFileDelHelper(vendorapplFileBeanObj);
                    }

                }
            }
            obj.put("AppId", AppId);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postInvoiceFile() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

    //FUNCTION IS CALLED WHEN retention claim charges invoice type is selected.
    private String getRetentionDetails(HttpServletRequest request) throws Exception {
        // String sReturnPage = ApplicationConstants.UIACTION_GET_PO_LINE_DETAILS; 
        System.out.println("getRetentionDetails");
        JSONObject obj = new JSONObject();
        HttpSession Session = request.getSession();
        LinkedList retentionDetail = new LinkedList();
        String retentionDetails = "";
        String Status = "";
        if (!ApplicationUtils.isBlank((request.getParameter("txtStatus")))) {
            Status = ApplicationUtils.getRequestParameter(request, "txtStatus");
        }

        if (Status.equals("") || Status.equals("Saved") || Status.equals("Rejected")) {
            retentionDetails += "<table class='table' id='retentionDetailsList'><thead><tr class='success'><th width='10%'  class='text-center'>#</th><th width='10%'  class='text-center'><input type='checkbox' data-applid id='selectallcheck' data-applid='' onclick='selectAllRetentionInvoice(this,4)'/>  </th><th width='40%'  class='text-center'>Invoice Number</th><th width='40%' class='text-center' >Total Retention Amount</th></tr></thead><tbody>";

        } else {
            retentionDetails += " <table class='table' id='retentionDetailsList' ><thead><tr class='success'><th width='10%'  class='text-center'>#</th><th width='10%'  class='text-center'><input type='checkbox' disabled data-applid id='selectallcheck' data-applid=''/>  </th><th width='40%'  class='text-center'>Invoice Number</th><th width='40%' class='text-center' >Total Retention Amount</th></tr></thead><tbody>";

        }
//   System.out.println(POLineDetailString);
        VendorDelegate vendorMgrObj = new VendorManager();
        VendorBean vendorBean = new VendorBean();
        try {
            //TAKE THE INPUT OF APPL ID AND PO NUMBER TO FETCH THE PO LINE ITEMS HAVING APPL IDAND  BLANK APPL IDS 
            logger.log(Level.INFO, "AjaxControlServelet :: viewClearingDetails() :: method called :: ");
//        if (!ApplicationUtils.isBlank((request.getParameter("txtPONumber")))) {
//               vendorBean.setPONumber(ApplicationUtils.getRequestParameter(request, "txtPONumber"));
//           }
            if (!ApplicationUtils.isBlank((request.getParameter("txtprojId")))) {
                vendorBean.setProjectCode(ApplicationUtils.getRequestParameter(request, "txtprojId"));
            }

            retentionDetail = vendorMgrObj.getRetentionDetails(vendorBean);
            int j = 0;
            for (VendorBean vendorBean1 : (LinkedList<VendorBean>) retentionDetail) {
                j++;
                String PO_NUMBER = "";
                String msedclInvoiceNo = "", projectID = "";
                BigDecimal wretAmount = BigDecimal.ZERO, oretAmt = BigDecimal.ZERO, cretAmt = BigDecimal.ZERO, sretAmt = BigDecimal.ZERO;

                BigDecimal retentionAmount = BigDecimal.ZERO;

                if (!ApplicationUtils.isBlank(vendorBean1.getMsedclInvoiceNumber())) {
                    msedclInvoiceNo = vendorBean1.getMsedclInvoiceNumber();
                }
                if (!ApplicationUtils.isBlank(vendorBean1.getProjectCode())) {
                    projectID = vendorBean1.getProjectCode();
                }
                if (vendorBean1.getWRET_AMT() != null) {
                    wretAmount = new BigDecimal(vendorBean1.getWRET_AMT());
                }
                if (vendorBean1.getCRET_AMT() != null) {
                    cretAmt = new BigDecimal(vendorBean1.getCRET_AMT());
                }
                if (vendorBean1.getORET_AMT() != null) {
                    oretAmt = new BigDecimal(vendorBean1.getORET_AMT());
                }
                if (vendorBean1.getSRET_AMT() != null) {
                    sretAmt = new BigDecimal(vendorBean1.getSRET_AMT());
                }
                retentionAmount = wretAmount.add(oretAmt.add(cretAmt.add(sretAmt)));

                if (Status.equals("") || Status.equals("Saved") || Status.equals("Rejected")) {
                    retentionDetails += "<tr class='info' ><td width='5%' class='text-center'>" + j + "</td><td width='5%' class='text-center'> <input type='checkbox'  class='retention_checkbox' name='retention_checkbox'  id ='retentionLineSelect'  value=" + msedclInvoiceNo + "," + projectID + " onclick='getInvoiceAmount(" + retentionAmount + ",this)' data-projectid=" + msedclInvoiceNo + " ></td><td width='20%' class='text-center'>" + msedclInvoiceNo + "</td><td width='20%' class='text-center'>" + retentionAmount + "</td></tr>";

                } else {
                    retentionDetails += "<tr class='info' ><td width='5%' class='text-center'>" + j + "</td><td width='5%' class='text-center'> <input type='checkbox' disabled class='retention_checkbox' name='retention_checkbox' id ='retentionLineSelect'  value=" + msedclInvoiceNo + "," + projectID + " data-projectid=" + msedclInvoiceNo + " ></td><td width='20%' class='text-center'>" + msedclInvoiceNo + "</td><td width='20%' class='text-center'>" + retentionAmount + "</td></tr>";

                }
            }
            retentionDetails += "</tbody></table>";
            if (retentionDetail != null && retentionDetail.size() > 0) {
                obj.put("retentionDetails", retentionDetails);
            } else {
                System.out.println("NO record to display.");
                logger.log(Level.ERROR, "AjaxControlServelet :: getRetentionDetails() ::  " + retentionDetail);
                obj.put("retentionDetails", "");
            }

            // SET THE SESSION ATTRIBUTE POLineDetail 
            Session.setAttribute(ApplicationConstants.VENDOR_RETENSION_SESSION_DATA, retentionDetails);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServelet :: viewClearingDetails() :: Exception :: " + ex);

        }
        return obj.toString();
    }

    private String saveClaimedRetentionDetails(HttpServletRequest request) {
        JSONObject obj = new JSONObject();
        VendorDelegate vendorMgrObj = new VendorManager();
        VendorBean bean = null;
        String message = "";
        VendorBean savedVendorBean = null;
        if (!ApplicationUtils.isBlank((request.getParameter("retentionCheckBoxValue")))) {
            try {
                String retentionCheckBoxValue = ApplicationUtils.getRequestParameter(request, "retentionCheckBoxValue");

                if (retentionCheckBoxValue != null && !retentionCheckBoxValue.equals("")) {
                    String[] invoiceAndProjectIdList = retentionCheckBoxValue.split("#");
                    for (int i = 0; i < invoiceAndProjectIdList.length; i++) {
                        bean = new VendorBean();
                        if (!invoiceAndProjectIdList.equals("")) {
                            String[] invoiceAndProjectId = invoiceAndProjectIdList[i].split(",");
                            for (int j = 0; j < invoiceAndProjectId.length; j++) {
                                String invoiceNo = invoiceAndProjectId[0];
                                String projectId = invoiceAndProjectId[1];
                                bean.setMsedclInvoiceNumber(invoiceNo);
                                bean.setProjectCode(projectId);
                            }
                            if (savedVendorBean != null && savedVendorBean.getApplId() != null) {
                                System.out.println("savedVendorBean.getApplId()::" + savedVendorBean.getApplId());
                                bean.setApplId(savedVendorBean.getApplId());
                            }
                            bean.setSesNum((i + 1) + "");
                            // savedVendorBean = vendorMgrObj.saveClaimedRetentionDetails(bean);
                        }

                    }
                } else {

                    message = "Please select retention invoice to be submitted.";
                }
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(AjaxControlServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        message = "Retention Claim details is saved with application Id " + savedVendorBean.getApplId();
        obj.put("message", message);
        return obj.toString();
    }

    private String getPartialRetentionDetails(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        HttpSession Session = request.getSession();
        LinkedList partialRetentionDetail = new LinkedList();
        String retentionDetails = "";
        String Status = "", wRPST = "";
        BigDecimal retentionAmount = BigDecimal.ZERO;
        if (!ApplicationUtils.isBlank((request.getParameter("txtStatus")))) {
            Status = ApplicationUtils.getRequestParameter(request, "txtStatus");
        }

        if (Status.equals("") || Status.equals("Saved") || Status.equals("Rejected")) {
            retentionDetails += "<table class='table' id='retentionDetailsList'><thead><tr class='success'><th width='10%'  class='text-center'>#</th><th width='10%'  class='text-center'><input type='checkbox' data-applid id='selectallcheck' data-applid='' onclick='selectAllRetentionInvoice(this,5)'/>  </th>"
                    + "<th width='40%'  class='text-center'>Invoice Number</th><th width='40%'  class='text-center'>Document Number</th><th width='40%' class='text-center' >Total Retention Amount</th></tr></thead><tbody>";

        } else {
            retentionDetails += " <table class='table' id='retentionDetailsList' ><thead><tr class='success'><th width='10%'  class='text-center'>#</th><th width='10%'  class='text-center'><input type='checkbox' disabled data-applid id='selectallcheck' data-applid=''/>  </th>"
                    + "<th width='40%'  class='text-center'>Invoice Number</th><th width='40%'  class='text-center'>Document Number</th><th width='40%' class='text-center' >Total Retention Amount</th></tr></thead><tbody>";

        }
        VendorDelegate vendorMgrObj = new VendorManager();
        VendorBean vendorBean = new VendorBean();
        try {
            //TAKE THE INPUT OF APPL ID AND PO NUMBER TO FETCH THE PO LINE ITEMS HAVING APPL IDAND  BLANK APPL IDS 
            logger.log(Level.INFO, "AjaxControlServelet :: getPartialRetentionDetails() :: method called :: ");
            if (!ApplicationUtils.isBlank((request.getParameter("txtprojId")))) {
                vendorBean.setProjectCode(ApplicationUtils.getRequestParameter(request, "txtprojId"));
            }

            partialRetentionDetail = vendorMgrObj.getPartialRetentionDetails(vendorBean);
            int j = 0;
            if (partialRetentionDetail != null) {
                for (VendorBean vendorBean1 : (LinkedList<VendorBean>) partialRetentionDetail) {
                    j++;
                    String PO_NUMBER = "";
                    String msedclInvoiceNo = "", projectID = "";
                    BigDecimal wretAmount = BigDecimal.ZERO, oretAmt = BigDecimal.ZERO, cretAmt = BigDecimal.ZERO, sretAmt = BigDecimal.ZERO;

                    if (!ApplicationUtils.isBlank(vendorBean1.getInvoiceNumber())) {
                        msedclInvoiceNo = vendorBean1.getInvoiceNumber();
                    }
                    if (!ApplicationUtils.isBlank(vendorBean1.getProjectCode())) {
                        projectID = vendorBean1.getProjectCode();
                    }
                    if (!ApplicationUtils.isBlank(vendorBean1.getWRPST())) {
                        wRPST = vendorBean1.getWRPST();
                    }

                    if (!ApplicationUtils.isBlank(vendorBean1.getInvoiceAmount())) {
                        retentionAmount = new BigDecimal(vendorBean1.getInvoiceAmount());
                    }

                    if (Status.equals("") || Status.equals("Saved") || Status.equals("Rejected")) {
                        retentionDetails += "<tr class='info' ><td width='5%' class='text-center'>" + j + "</td><td width='5%' class='text-center'> <input type='checkbox'  class='retention_checkbox' name='retention_checkbox'  id ='retentionLineSelect'  value=" + msedclInvoiceNo + "," + projectID + "," + wRPST + "," + retentionAmount + " onclick='getInvoiceAmount(" + retentionAmount + ",this)' data-projectid=" + msedclInvoiceNo + " ></td><td width='20%' class='text-center'>" + msedclInvoiceNo + "</td><td width='20%' class='text-center'>" + wRPST + "</td><td width='20%' class='text-center'>" + retentionAmount + "</td></tr>";

                    } else {
                        retentionDetails += "<tr class='info' ><td width='5%' class='text-center'>" + j + "</td><td width='5%' class='text-center'> <input type='checkbox' disabled class='retention_checkbox' name='retention_checkbox' id ='retentionLineSelect'  value=" + msedclInvoiceNo + "," + projectID + "," + wRPST + "," + retentionAmount + " data-projectid=" + msedclInvoiceNo + " ></td><td width='20%' class='text-center'>" + msedclInvoiceNo + "</td><td width='20%' class='text-center'>" + wRPST + "</td><td width='20%' class='text-center'>" + retentionAmount + "</td></tr>";

                    }
                }
            }
            retentionDetails += "</tbody></table>";
            if (partialRetentionDetail != null && partialRetentionDetail.size() > 0) {
                obj.put("retentionDetails", retentionDetails);
            } else {
                obj.put("retentionDetails", "");
            }

            // SET THE SESSION ATTRIBUTE VENDOR_RETENSION_SESSION_DATA 
            Session.setAttribute(ApplicationConstants.VENDOR_RETENSION_SESSION_DATA, retentionDetails);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServelet :: getPartialRetentionDetails() :: Exception :: " + ex);

        }
        return obj.toString();
    }

    private String postLegalApplicationForm(HttpServletRequest request) throws Exception {
        //System.out.println("$postLegalApplicationForm$");
//         VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorInputBean vendorInputBeanObj = new VendorInputBean();
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
       
        Date sysdate = new Date();
        HttpSession vendorSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        Date today = ApplicationUtils.getCurrentDate();
        
          LinkedList<FeeTypeDtlsBean> feeTypeDtlsBeanList = new LinkedList<FeeTypeDtlsBean>();
           String feeTypeDtls=request.getParameter("feetypeDtlArray");
          feeTypeDtls=feeTypeDtls.replace("[", "");
          feeTypeDtls=feeTypeDtls.replace("]", "");
           String[] strArr=feeTypeDtls.split(","); 
          for (int i=0 ; i<strArr.length; )
          {
              
               FeeTypeDtlsBean  feeTypeDtlsBean=new FeeTypeDtlsBean();

              if (strArr[i] == ""){
                  feeTypeDtlsBean.setFeeTypeDtlsId(0);
              }else
              feeTypeDtlsBean.setFeeTypeDtlsId(Integer.parseInt(strArr[i].replace("\"", "")));
              feeTypeDtlsBean.setFeeType(strArr[i+1].replace("\"", ""));
              feeTypeDtlsBean.setAmount(Integer.parseInt(strArr[i+2].replace("\"", "")));
               feeTypeDtlsBean.setRemark(strArr[i+3].replace("\"", ""));
             //  feeTypeDtlsBean.setApplId(Integer.parseInt(ApplicationUtils.getRequestParameter(request, "txtApplicationId")));
               i=i+4;
               
               feeTypeDtlsBeanList.add(feeTypeDtlsBean);
          }
           
           
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postLegalApplicationForm() :: method called :: ");
            if (ApplicationUtils.getRequestParameter(request, "txtApplicationId") != null && !ApplicationUtils.getRequestParameter(request, "txtApplicationId").equals("")) {
                legalInvoiceInputBean.setApplId(Integer.parseInt(ApplicationUtils.getRequestParameter(request, "txtApplicationId")));
               
            }
            if (vendorSession.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                legalInvoiceInputBean.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtVendorCode"));
                legalInvoiceInputBean.setVendorName(ApplicationUtils.getRequestParameter(request, "txtVendorName"));
                legalInvoiceInputBean.setCreatedById((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                legalInvoiceInputBean.setCreatedByName((String) vendorSession.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION));
                legalInvoiceInputBean.setCreatedByDesignation((String) vendorSession.getAttribute(ApplicationConstants.DESIGNATION_SESSION));
                legalInvoiceInputBean.setCreatedByUsertype("Emp");

            } else {
                legalInvoiceInputBean.setVendorNumber((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                legalInvoiceInputBean.setVendorName((String) vendorSession.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION));
                legalInvoiceInputBean.setCreatedByUsertype("VENDOR");
            }
            legalInvoiceInputBean.setInvoiceNumber(ApplicationUtils.getRequestParameter(request, "txtInvoiceNum"));

            Date invoiceDate = (ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            Date vendorinwardDate = (ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInwardDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            legalInvoiceInputBean.setStatus(ApplicationUtils.getRequestParameter(request, "txtStatus"));
            if (request.getParameter("isWithCourtCaseFlag") != null && !request.getParameter("isWithCourtCaseFlag").equals("")) {
                legalInvoiceInputBean.setIsWithCourtCaseNo(ApplicationUtils.getRequestParameter(request, "isWithCourtCaseFlag"));
            }
            //Date inwardToDate= (ApplicationUtils.stringToDate((String) request.getParameter("txtInvoiceToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            if (ApplicationUtils.getRequestParameter(request, "txtApplicationId") != null && !ApplicationUtils.getRequestParameter(request, "txtApplicationId").equals("")) {
                legalInvoiceInputBean.setApplId(Integer.parseInt(ApplicationUtils.getRequestParameter(request, "txtApplicationId")));
            }
//            legalInvoiceInputBean.setDesignation((String) vendorSession.getAttribute(ApplicationConstants.DESIGNATION_SESSION));
//            legalInvoiceInputBean.setOffice_Code((String) vendorSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            //legalInvoiceInputBean.setInvSubmitDate((ApplicationUtils.stringToDate((String) request.getParameter("txtInvSubmitDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT)));
            legalInvoiceInputBean.setDealingOfficeCode(ApplicationUtils.getRequestParameter(request, "selectedOffieCode"));
            legalInvoiceInputBean.setInvoiceDate(ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInvoiceDate"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            legalInvoiceInputBean.setVendorInwardDate(ApplicationUtils.stringToDate((String) request.getParameter("txtVendorInwardDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            legalInvoiceInputBean.setInvoiceAmount(Integer.parseInt(ApplicationUtils.getRequestParameter(request, "txtInvoiceAmt")));
            legalInvoiceInputBean.setMsedclInwardNo(ApplicationUtils.getRequestParameter(request, "txtInwardNum"));
//            vendorInputBeanObj.setLocation(ApplicationUtils.getRequestParameter(request, "txtPlant"));
            legalInvoiceInputBean.setMsedclInwardDate(ApplicationUtils.stringToDate((String) request.getParameter("txtInwardDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            legalInvoiceInputBean.setCourtCaseNo(ApplicationUtils.getRequestParameter(request, "txtCourtCaseNo"));
            System.out.println("court case no::::::::::::"+ApplicationUtils.getRequestParameter(request, "txtCourtCaseNo"));
            legalInvoiceInputBean.setCaseRefNo(ApplicationUtils.getRequestParameter(request, "txtCaseRefNo"));
            System.out.println("case ref no:::::::::::::::::::"+ApplicationUtils.getRequestParameter(request, "txtCaseRefNo"));
            legalInvoiceInputBean.setCourtName(ApplicationUtils.getRequestParameter(request, "txtCourtName"));
            legalInvoiceInputBean.setDealingOfficeName(ApplicationUtils.getRequestParameter(request, "txtDealingOffice"));
          //  legalInvoiceInputBean.setDealingOfficeCode(ApplicationUtils.getRequestParameter(request, "txtDealingOfficeCode"));
            legalInvoiceInputBean.setPartyNames(ApplicationUtils.getRequestParameter(request, "txtPartyNames"));
            legalInvoiceInputBean.setCaseDescription(ApplicationUtils.getRequestParameter(request, "txtCaseDescription"));
            
            legalInvoiceInputBean.setVsPartyNames(ApplicationUtils.getRequestParameter(request, "txtVsPartyNames"));
            legalInvoiceInputBean.setCaseTypeDesc(ApplicationUtils.getRequestParameter(request, "txtCaseType"));
            
            legalInvoiceInputBean.setFeeType(ApplicationUtils.getRequestParameter(request, "txtFeeType"));
            if(ApplicationUtils.getRequestParameter(request, "region")!=null && !ApplicationUtils.getRequestParameter(request, "region").equals("Select")){
                legalInvoiceInputBean.setRegionText(ApplicationUtils.getRequestParameter(request, "region"));
            
                
                
            }
             if(ApplicationUtils.getRequestParameter(request, "zone")!=null && !ApplicationUtils.getRequestParameter(request, "zone").equals("Select")){
            legalInvoiceInputBean.setZoneText(ApplicationUtils.getRequestParameter(request, "zone"));
          
             }
             if(ApplicationUtils.getRequestParameter(request, "circle")!=null && !ApplicationUtils.getRequestParameter(request, "circle").equals("Select")){
            legalInvoiceInputBean.setCircleText(ApplicationUtils.getRequestParameter(request, "circle"));
         
             }
             if(ApplicationUtils.getRequestParameter(request, "division")!=null && !ApplicationUtils.getRequestParameter(request, "division").equals("Select")){
            legalInvoiceInputBean.setDivisionText(ApplicationUtils.getRequestParameter(request, "division"));
       
             }
                     
            legalInvoiceInputBean.setSubDivisionText(ApplicationUtils.getRequestParameter(request, "subDivision"));
            legalInvoiceInputBean.setCorporateOffice(ApplicationUtils.getRequestParameter(request, "corporateOffice"));
            
            //System.out.println("blah blah "+ApplicationUtils.getRequestParameter(request, "corpSection"));
            //System.out.println(ApplicationUtils.getRequestParameter(request, "corpSection").indexOf("-"));
            
            if (ApplicationUtils.getRequestParameter(request, "corporateOffice").equals("261-Corporate Office")){
               legalInvoiceInputBean.setDealingOfficeCode("261"); 
               legalInvoiceInputBean.setDealingOfficeName(ApplicationUtils.getRequestParameter(request, "corpSection").substring(ApplicationUtils.getRequestParameter(request, "corpSection").indexOf("-")));
                legalInvoiceInputBean.setDeptCode(ApplicationUtils.getRequestParameter(request, "corpSection").substring(0, ApplicationUtils.getRequestParameter(request, "corpSection").indexOf("-")));
legalInvoiceInputBean.setDeptName(ApplicationUtils.getRequestParameter(request, "corpSection").substring(ApplicationUtils.getRequestParameter(request, "corpSection").indexOf("-")+1));             
//legalInvoiceInputBean.setDeptName("Testing");
                
            }            
            
            if (ApplicationUtils.getRequestParameter(request, "txtDealingOffice").equals("261-Corporate Office")){
               legalInvoiceInputBean.setDealingOfficeCode("261"); 
               legalInvoiceInputBean.setDealingOfficeName("261-Corporate Office");
                //legalInvoiceInputBean.setDeptCode("13077");
                //System.out.println("blah blah "+ApplicationUtils.getRequestParameter(request, "corpSection"));
                if(ApplicationUtils.getRequestParameter(request, "corpSection")!=null && !ApplicationUtils.getRequestParameter(request, "corpSection").isEmpty()){
                legalInvoiceInputBean.setDeptCode(ApplicationUtils.getRequestParameter(request, "corpSection").substring(0, ApplicationUtils.getRequestParameter(request, "corpSection").indexOf("-")));
             //legalInvoiceInputBean.setDeptName("Testing");
             legalInvoiceInputBean.setDeptName(ApplicationUtils.getRequestParameter(request, "corpSection").substring(ApplicationUtils.getRequestParameter(request, "corpSection").indexOf("-")+1));
             //legalInvoiceInputBean.setDeptName("Testing");                 
            }}
            if (ApplicationUtils.getRequestParameter(request, "office_type").equals("DEPT") && ApplicationUtils.getRequestParameter(request, "region_id").equals(ApplicationConstants.HO_OFFICE_CODE)) {
                if (ApplicationUtils.getRequestParameter(request, "corpSection") == null || ApplicationUtils.getRequestParameter(request, "corpSection").isEmpty()) {
                    legalInvoiceInputBean.setDeptCode(ApplicationUtils.getRequestParameter(request, "selectedOffieCode"));
                    legalInvoiceInputBean.setDeptName(ApplicationUtils.getRequestParameter(request, "txtDealingOffice").substring(ApplicationUtils.getRequestParameter(request, "txtDealingOffice").indexOf("-") + 1));
                }
            }
            /* if (!ApplicationUtils.isBlank(request.getParameter("txtModule"))) {//used for sms escalation
                   vendorInputBeanObj.setSelectedModule(ApplicationUtils.getRequestParameter(request, "txtModule"));
              
            }*/
//            vendorInputBeanObj.setSubmitAtPlant(ApplicationUtils.getRequestParameter(request, "SubmitAtPlant"));
//            vendorInputBeanObj.setSubmitAtDesc(ApplicationUtils.getRequestParameter(request, "SubmitAtDesc"));
//            vendorInputBeanObj.setPurchasingDesc(ApplicationUtils.getRequestParameter(request, "PurchasingDesc"));
//            vendorInputBeanObj.setPurchasing_group(ApplicationUtils.getRequestParameter(request, "Purchasing_group"));
//            vendorInputBeanObj.setPoPurchasing_group(ApplicationUtils.getRequestParameter(request, "txtOnloadPurchasing_group"));
//                 vendorInputBeanObj.setVendorInvoiceResubmit(ApplicationUtils.stringToDate((String) request.getParameter("txtResubmitDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            // vendorInputBeanObj.setInvoiceFromDate(ApplicationUtils.stringToDate((String) request.getParameter("txtInvoiceFrmDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));

            //vendorInputBeanObj.setInvoiceToDate(ApplicationUtils.stringToDate((StringgetMobileNumberDetails) request.getParameter("txtInvoiceToDt"), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            subAction = (String) request.getParameter("subAction");

            if (subAction.equals("submit")) {
                legalInvoiceInputBean.setSaveFlag("Submitted");

            }
            if (subAction.equals("save")) {

                legalInvoiceInputBean.setSaveFlag("Saved");
            }
            if (subAction.equals("approve")) {

                legalInvoiceInputBean.setSaveFlag("Accepted");
                legalInvoiceInputBean.setApproveRejectFlag("A");
                legalInvoiceInputBean.setInvoiceApprDAte(sysdate);
                legalInvoiceInputBean.setApprovedBy((String) vendorSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            }
            if (subAction.equals("return")) {
                legalInvoiceInputBean.setSaveFlag("Returned");
                legalInvoiceInputBean.setApproveRejectFlag("R");

                legalInvoiceInputBean.setInvoiceRejDate(sysdate);
                legalInvoiceInputBean.setRejectReason(ApplicationUtils.getRequestParameter(request, "txtReason"));
            }
            if (subAction.equals("forward")) {
                legalInvoiceInputBean.setSaveFlag("Forwarded");
//                legalInvoiceInputBean.setForwardToPlant(ApplicationUtils.getRequestParameter(request, "ForwardToPlant"));
//                legalInvoiceInputBean.setForwardToDesc(ApplicationUtils.getRequestParameter(request, "ForwardToDesc"));
            }
            
            
            
            
            
            
            
            
            
//            vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
            if ((!((invoiceDate.after(today)) && (vendorinwardDate.after(today))))// || (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (legalInvoiceInputBean.getSaveFlag().equals("Submitted"))) {
                obj = VendorFormController.getSubmittedLegalInvoiceFormStatus(legalInvoiceInputBean, request);
            } else if ((!(invoiceDate.after(today) || (vendorinwardDate.after(today))))// || (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (legalInvoiceInputBean.getSaveFlag().equals("Saved"))) {
                obj = VendorFormController.getSaveLegalInvoiceFormStatus(legalInvoiceInputBean,feeTypeDtlsBeanList, request);
                /* if(vendorInputBeanObj.getApplId()!=null && vendorInputBeanObj.getINVOICE_TYPE()!=null 
                        && vendorInputBeanObj.getINVOICE_TYPE().equalsIgnoreCase("Retention Claim Charges")){
                   //obj=
                           VendorFormController.saveClaimedRetentionDetailsFormStatus(vendorPrezDataObj,request);
               }*/
            } else if ((!(invoiceDate.after(today) || (vendorinwardDate.after(today))))//|| (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (legalInvoiceInputBean.getSaveFlag().equals("Accepted"))) {
                obj = VendorFormController.getVerifiedLegalInvoiceFormStatus(legalInvoiceInputBean, request);
            } else if (!(invoiceDate.after(today) || (vendorinwardDate.after(today)))// || (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (legalInvoiceInputBean.getSaveFlag().equals("Rejected") || legalInvoiceInputBean.getSaveFlag().equals("Returned"))) {
                obj = VendorFormController.getRejectedLegalInvoiceFormStatus(legalInvoiceInputBean, request);
            } else if ((!(invoiceDate.after(today) || (vendorinwardDate.after(today))))// || (invoiceFrmDate.after(today)) || (inwardToDate.after(today)))
                    && (legalInvoiceInputBean.getSaveFlag().equals("Forwarded"))) {
//               obj=VendorFormController.getForwardedFormStatus(POBeanObj,vendorPrezDataObj,vendorInputBeanObj,request);
            } else {
                obj.put("Message1", "Invoice Date or Inward Date cannot be a future date !");
            }
            obj.put("AppId", legalInvoiceInputBean.getApplId());
            vendorSession.setAttribute(ApplicationConstants.VENDOR_LEGAL_INVOICE_APPL_FORM_SESSION_DATA, legalInvoiceInputBean);
             LinkedList FeeTypeDtlList = new LinkedList();
               VendorDelegate vendorMgrObj = new VendorManager();
            if (legalInvoiceInputBean.getApplId() != null) {
                  FeeTypeDtlsBean  feeTypeDtlsBeanObj=new FeeTypeDtlsBean();
            feeTypeDtlsBeanObj.setApplId(legalInvoiceInputBean.getApplId());
            FeeTypeDtlList = vendorMgrObj.getVendorLegalInvoiceFeeTypeDtlList(feeTypeDtlsBeanObj);
             vendorSession.setAttribute(ApplicationConstants.VENDOR_FEE_TYPE_DTL_SESSION_DATA, FeeTypeDtlList);
            
        }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postLegalApplicationForm() :: Exception :: " + ex);
            ex.printStackTrace();
            //obj.put("Message1", "Error!! Please check Invoice From Date and Invoice To Date.");
        } finally {
            if (obj.isEmpty()) {
                obj.put("Message1", "Something Went Wrong..Please Raise Issue!!! ");
            }

            return obj.toString();
        }

    }

    private String getLegalHierarchyLocation(HttpServletRequest request) throws Exception {
        JSONObject obj = new JSONObject();
        POBean poBeanObj = new POBean();
        List hierarchyList = new LinkedList();
        HttpSession vendorSession = request.getSession();
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObj2 = new VendorPrezData();
        String hierarchylistString = "<option value ='Select'>Select</option>";
        VendorDelegate vendorMgrObj = new VendorManager();
        OrganizationMasterBean organizationMasterBean = new OrganizationMasterBean();
        organizationMasterBean.setOfficeType(ApplicationUtils.getRequestParameter(request, "officeType"));
        if (organizationMasterBean.getOfficeType() != null && !organizationMasterBean.getOfficeType().equals("")) {
            if (ApplicationUtils.getRequestParameter(request, "officeCode") != null && !ApplicationUtils.getRequestParameter(request, "officeCode").equals("")) {
                organizationMasterBean.setSelectedOfficeCode(ApplicationUtils.getRequestParameter(request, "officeCode"));
            }
        }
        try {
            hierarchyList = vendorMgrObj.getLegalHierarchyLocation(organizationMasterBean);
            // vendorPrezDataObj.setCircleList(vendorPrezDataObj2.getCircleList());
            for (OrganizationMasterBean masterBean : (LinkedList<OrganizationMasterBean>) hierarchyList) {
                if (masterBean.getOfficeType().equals("REG")) {
                    hierarchylistString += "<option value = " + masterBean.getOrganizationId() + "-" + masterBean.getRegionName() + ">" + masterBean.getRegionId() + "-" + masterBean.getRegionName() + "</option>";
                } else if (masterBean.getOfficeType().equals("ZON")) {
                    hierarchylistString += "<option value = " + masterBean.getOrganizationId() + "-" + masterBean.getZoneName() +">" + masterBean.getZoneId() + "-" + masterBean.getZoneName() + "</option>";
                } else if (masterBean.getOfficeType().equals("CIR")) {
                    hierarchylistString += "<option value = " + masterBean.getOrganizationId() + "-" + masterBean.getCircleName() +">" + masterBean.getCircleId() + "-" + masterBean.getCircleName() + "</option>";
                } else if (masterBean.getOfficeType().equals("DIV")) {
                    hierarchylistString += "<option value = " +  masterBean.getOrganizationId() + "-" + masterBean.getDivisionNAme() +">" + masterBean.getDivisionId() + "-" + masterBean.getDivisionNAme() + "</option>";
                } else if (masterBean.getOfficeType().equals("SUB")) {
                    hierarchylistString += "<option value = " +  masterBean.getOrganizationId() + "-" + masterBean.getSubDivName() +">" + masterBean.getSubDivId() + "-" + masterBean.getSubDivName() + "</option>";
                } else if (masterBean.getOfficeType().equals("DEPT")) {
                    hierarchylistString += "<option value = " +  masterBean.getOrganizationId() + "-" + masterBean.getDeptName()+">" + masterBean.getDeptId()+ "-" + masterBean.getDeptName() + "</option>";
                }
            }
            obj.put("hierarchylistString", hierarchylistString);
            //System.out.println("hierarchylistString "+hierarchylistString);
            obj.put("plant", organizationMasterBean.getOfficeType());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorHandler :: getnonPoVendorList() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

    
    
    
     private String deleteLegalInvoiceFile(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_LEGAL_INVOICE_FILE_DELETE;
   
        VendorApplFileBean vendorapplFileBeanObj = new VendorApplFileBean();
        VendorApplFileBean vendorapplFileBeanObj_ftp = new VendorApplFileBean();
        VendorApplFileDelegate vendorapplFileMgrObj = new VendorApplFileManager();
     
        HttpSession vendorapplSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        String AppId = "";
        String FId = "";
        String Option = "";
        String txtPONumber = "";
        String FileName = "";
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: postInvoiceFile() :: method called :: ");

            subAction = (String) request.getParameter("subAction");
            AppId = (String) request.getParameter("AppId");
         
            FId = (String) request.getParameter("FId");
            Option = (String) request.getParameter("Option");
            FileName = (String) request.getParameter("FileName");
            FileBean FileObj = null;
            if (subAction.equals("delete")) {
                vendorapplFileBeanObj.setApplicationId(AppId);
                //vendorapplFileBeanObj.setPo_Number(txtPONumber);
                vendorapplFileBeanObj.setEmpNumber((String) vendorapplSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));

                vendorapplFileBeanObj.setId(FId);

                if (Option != null) {
                    // if(Option.equals("Letter Of Award") ||Option.equals("Copy Of Agreement")||Option.equals("Insurance Copy")||Option.equals("Milestone Chart")||Option.equals("Bank Guarantee") ){
                    if (Option.equals("Invoice Document") || Option.equals("Other Supporting Document") || Option.equals("Retention claim Document")) {
                        vendorapplFileBeanObj_ftp = vendorapplFileMgrObj.getVendorLegalApplFile(vendorapplFileBeanObj);
                        String filepath = vendorapplFileBeanObj_ftp.getPath();
                        filepath = filepath.substring(0, filepath.lastIndexOf("/"));
                        RemoveLegalInvoiceFile rem = new RemoveLegalInvoiceFile();
                        rem.RemoveFile(filepath, FileName);//remove file from ftp location
                        vendorapplFileBeanObj = vendorapplFileMgrObj.legalInvoiceFileDelHelper(vendorapplFileBeanObj);//remove entry from database

                    } 
                    /*else {
                        vendorapplFileBeanObj_ftp = vendorapplFileMgrObj.getVendorPOFile(vendorapplFileBeanObj);
                        String filepath = vendorapplFileBeanObj_ftp.getPath();
                        filepath = filepath.substring(0, filepath.lastIndexOf("/"));
                        RemoveVendorFile rem = new RemoveVendorFile();
                        rem.RemoveFile(filepath, FileName);
                        vendorapplFileBeanObj = vendorapplFileMgrObj.VendorPOFileDelHelper(vendorapplFileBeanObj);
                    }*/

                }
            }
            obj.put("AppId", AppId);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: postInvoiceFile() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

     
      private String deleteFeeTypeDtl(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_LEGAL_INVOICE_FEE_TYPE_DELETE;
   
        FeeTypeDtlsBean  feeTypeDtlsBean=new FeeTypeDtlsBean();
        VendorDelegate vendorMgrObj = new VendorManager();
     
        HttpSession vendorapplSession = request.getSession();
        JSONObject obj = new JSONObject();
        String subAction = "";
        Integer feeTypeDtlId = 0;
       
        try {
            logger.log(Level.INFO, "AjaxControlServlet :: deleteFeeTypeDtl() :: method called :: ");

            subAction = (String) request.getParameter("subAction");
            feeTypeDtlId = Integer.parseInt(request.getParameter("feeTypeDtlId"));
         
           
            FileBean FileObj = null;
            if (subAction.equals("delete")) {
                feeTypeDtlsBean.setFeeTypeDtlsId(feeTypeDtlId);
                //vendorapplFileBeanObj.setPo_Number(txtPONumber);
                
            
                        feeTypeDtlsBean = vendorMgrObj.feeTypeDtlDelHelper(feeTypeDtlsBean);//remove entry from database

                

               
            }
            obj.put("feeTypeDtlId", feeTypeDtlId);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "AjaxControlServlet :: deleteFeeTypeDtl() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return obj.toString();
    }

            private  String getCaptcha(HttpServletRequest request) throws Exception {
            
            String captchaStr=ApplicationUtils.generateCaptchatMethod2(6); 
            
            JSONObject obj = new JSONObject();
            HttpSession session = request.getSession();
            try {
                
                
                session.setAttribute(ApplicationConstants.CAPTCHA_CODE, captchaStr);
             
                  obj.put("CAPTCHA", captchaStr);
                        
            } catch (Exception e) {
                    e.printStackTrace();
            }
             return obj.toString();
            }
}
