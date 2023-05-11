package in.emp.handler;

import javax.servlet.http.*;
import java.util.*;
import in.emp.arch.GenericFormHandler;
import in.emp.util.ApplicationUtils;
import in.emp.common.ApplicationConstants;
import in.emp.sms.SmsController;
import in.emp.hrms.HRMSDelegate;
import in.emp.security.bean.SecUserBean;
import in.emp.security.SecurityDelegate;
import in.emp.hrms.bean.HRMSRespBean;
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.hrms.bean.HRMSUserPrezData;
import in.emp.hrms.manager.HRMSManager;
import in.emp.ldap.LDAP;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.security.manager.SecurityManager;
import in.emp.security.bean.ServerAPIBean;
import in.emp.sms.bean.TemplateIdBean;
import in.emp.user.bean.UserBean;
import in.emp.vendor.VendorDelegate;
import in.emp.vendor.bean.SmsDTO;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.manager.VendorManager;
import java.util.Enumeration;
import java.util.LinkedList;
import javaldapapp.AssignOfficeDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class SecurityHandler implements GenericFormHandler {

    private static String CLASS_NAME = SecurityHandler.class.getName();
    private static Logger logger = Logger.getLogger(SecurityHandler.class);

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String uiActionName = "";
        String sReturnPage = "";

        try {
            logger.log(Level.INFO, "SecurityHandler :: execute() :: method called");

            uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);

            if (!ApplicationUtils.isBlank(uiActionName)) {
                if (uiActionName.equals(ApplicationConstants.UIACTION_HOME_GET)) {
                    sReturnPage = ApplicationConstants.UIACTION_HOME_GET;
                }else if (uiActionName.equals(ApplicationConstants.UIACTION_LEGALDASHBOARD_GET)) {
                     sReturnPage =ApplicationConstants.UIACTION_LEGALDASHBOARD_GET;
                
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_LOGIN_GET)) {
                     sReturnPage =getLogin(request);
                } 
                else if (uiActionName.equals(ApplicationConstants.UIACTION_PWD_RESET)) {
                    sReturnPage = resetPassword(request);
                }
                else if (uiActionName.equals(ApplicationConstants.UIACTION_LOGIN_POST)) {
                    sReturnPage = postLogin(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_PASSWORD_CHANGE)) {
                    sReturnPage = getPasswordChange(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_POST_PASSWORD_CHANGE)) {
                    sReturnPage = postPasswordChange(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_SERVER_API)) {
                    sReturnPage = getServerAPI(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CPF_LOGIN)) {
                    sReturnPage = LoginCpfPortal(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_FACILITY_NOT_AVAILABLE)) {
                    sReturnPage = ApplicationConstants.UIACTION_FACILITY_NOT_AVAILABLE;
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_GET_VENDOR_LOGIN)) {
                    sReturnPage = getVendorLogin(request);
                }

            } else {
                sReturnPage = ApplicationConstants.UIACTION_GET_LOGIN;
            }

            logger.log(Level.INFO, "SecurityHandler :: execute() :: sReturnPage :: " + sReturnPage);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: execute() :: Exception :: " + ex);
            sReturnPage = ApplicationConstants.UIACTION_GET_LOGIN;
        }

        return sReturnPage;
    }
    
    private String resetPassword(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_PWD_RESET;
         VendorBean vendorBeanObj = new VendorBean();
         VendorDelegate vendorMgrObj = new VendorManager();
        String uid = "";
        String userContactNo = "";
         List<String> lstParams = new ArrayList<String>();
        List<String> lstcredential1 = new ArrayList<String>();
         SmsController sms = new SmsController();
           SmsDTO objSms = new SmsDTO();
                 HttpSession vendorSession = request.getSession();
                TemplateIdBean templateBeanObj =new TemplateIdBean();
        try {
            logger.log(Level.INFO, "Security Handler :: resetPassword() :: method called");
            uid = request.getParameter("txtUID");
            vendorSession.setAttribute(ApplicationConstants.USER_NAME_SESSION, String.valueOf(uid));
               vendorBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtUID"));
              vendorBeanObj.setUserType("Vendor");
              vendorBeanObj.setPassword("");
               vendorBeanObj = vendorMgrObj.getContactNumber(vendorBeanObj);
                     userContactNo=vendorBeanObj.getVendorContactNumber();
              String AlphaNumericString = "0123456789"; 
  
     
        StringBuilder sb = new StringBuilder(4); 
  
        for (int i = 0; i < 4; i++) { 
  
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
         request.getSession().setAttribute(ApplicationConstants.PASSWORD_RESET_OTP,sb.toString());
          lstParams.add(uid);
     lstParams.add(sb.toString());
     //lstcredential1.add("639748");
    // lstcredential1.add("mse@12");
      lstcredential1.add(ApplicationConstants.OTP_URL);
      templateBeanObj.setTemplate_Id_Desc(ApplicationConstants.SMS_TEMPLATE_ID1);
     templateBeanObj=vendorMgrObj.getTemplateDetails(templateBeanObj);
     objSms.setLstParams(lstParams);    
     objSms.setMobileNumber(userContactNo);
     try{
     sms.sendSMS(objSms, templateBeanObj.getTemplate_Id(),lstcredential1);
     
     String messageOne=  "OTP Sent Successfully on " +userContactNo.substring(0, 3)+"XXXXXX"+userContactNo.substring(7, 10);
     vendorSession.setAttribute(ApplicationConstants.RESET_PASSWORD_SESSION, messageOne);
     }
     catch(Exception e){
      logger.log(Level.ERROR, "Security Handler :: resetPassword()  :: Exception :: " + e);
      String messageTwo= "Please Register Your Mobile Number to receive OTP";
      vendorSession.setAttribute(ApplicationConstants.RESET_PASSWORD_SESSION, messageTwo);
     }    
            
        } catch (Exception e) {
            logger.log(Level.ERROR, "Security Handler :: resetPassword() :: Exception :: " + e);
 String messageTHREE= "Please Register Your Mobile Number to receive OTP";
      vendorSession.setAttribute(ApplicationConstants.RESET_PASSWORD_SESSION, messageTHREE);
        }
        return sReturnPage;
    }
    private String getLogin(HttpServletRequest request) throws Exception {

        String sReturnPage = ApplicationConstants.UIACTION_LOGIN_GET;
        HRMSDelegate hrmsManagerObj = new HRMSManager();
        String uid = "";
        String pa = "";
        String userType = "";
        String otp_received = "";
        VendorPrezData vendorPrezDataObj = new VendorPrezData();
        VendorPrezData vendorPrezDataObjOne = new VendorPrezData();
        VendorPrezData vendorPrezDataObjtwo = new VendorPrezData();
        VendorBean vendorBeanObj = new VendorBean();
        VendorInputBean vendorInputBeanObj=new VendorInputBean();
        VendorBean vendorBeanObjOne = new VendorBean();
        VendorDelegate vendorMgrObj = new VendorManager();
        HttpSession session = request.getSession();
        HRMSRespBean hrmsRespBeanObj = new HRMSRespBean();
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        HRMSUserPrezData hrmsUserPrezDataObj = new HRMSUserPrezData();
        PersonalInfoHandler piHandlerObj = new PersonalInfoHandler();
        AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        LDAP ldap = new LDAP();
        LinkedList legalSummaryList = new LinkedList();
         LegalInvoiceInputBean legalInvoiceInputBeanObj = new LegalInvoiceInputBean();
        try {
            logger.log(Level.INFO, "SecurityHandler :: getLogin() :: method called");

            uid = request.getParameter("txtUID");
            pa = request.getParameter("txtP");
            userType = request.getParameter("UserOpt");
            otp_received = request.getParameter("Otp");
            String userName = "";
            String userDesig = "";
            String userOfficeName = "";
            String userOfficeId = "";
            String userOfficeTypeId = "";
            String userContactNo = "";
            String otp = "";
            session.setAttribute(ApplicationConstants.USER_TYPE_SESSION, userType);
            
            System.out.println("-----USER TYPE : " + session.getAttribute(ApplicationConstants.USER_TYPE_SESSION));
            if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Emp")) {
                vendorBeanObj.setUserType("Emp");
                vendorInputBeanObj.setUserType("Emp");
                legalInvoiceInputBeanObj.setUserType("Emp");
            }

            if (session.getAttribute(ApplicationConstants.USER_TYPE_SESSION).equals("Vendor")) {
                vendorBeanObj.setUserType("Vendor");
                legalInvoiceInputBeanObj.setUserType("Vendor");
                vendorInputBeanObj.setUserType("Vendor");
                vendorBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtUID"));
                vendorBeanObj.setPassword(pa);
                legalInvoiceInputBeanObj.setVendorNumber(ApplicationUtils.getRequestParameter(request, "txtUID"));
                vendorBeanObjOne = vendorMgrObj.getContactNumber(vendorBeanObj);

                System.out.println("Vendor contact number :" +vendorBeanObjOne.getVendorContactNumber());
            }
            hrmsUserBeanObj.setEmpNumber(uid);
            hrmsUserBeanObj.setPass(pa);
            hrmsUserPrezDataObj.setHrmsUserBeanObj(hrmsUserBeanObj);

            //Vendor Login
            if (userType.equals("Vendor")) {
                hrmsUserPrezDataObj = hrmsManagerObj.getLogin(hrmsUserPrezDataObj);
                hrmsUserBeanObj = hrmsUserPrezDataObj.getHrmsUserBeanObj();
                // hrmsRespBeanObj = hrmsUserPrezDataObj.getHrmsRespBeanObj();
                if (!ApplicationUtils.isBlank(hrmsUserBeanObj.getValLogin())) {
                    if (hrmsUserBeanObj.getValLogin().equals("NEW")) {
                         if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.OTP_NUMBER)))
                         {
                         otp=(String) request.getSession().getAttribute(ApplicationConstants.OTP_NUMBER);
                         }
                        
                        if(otp.equals(otp_received)){
                        
                        sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_LOGIN;}
                        else
                        {
                           String msgFive = "OTP Mismatch!Please Regenerate OTP and enter again ";
                    session.setAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION, msgFive);
                    sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST; 
                        }
                        session.setAttribute(ApplicationConstants.USER_NAME_SESSION, String.valueOf(hrmsUserBeanObj.getEmpNumber()));
                        session.setAttribute(ApplicationConstants.DISPLAY_NAME_SESSION, hrmsUserBeanObj.getEmpName());
                        vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                        legalInvoiceInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                        vendorInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                        session.setAttribute(ApplicationConstants.IS_LEGAL_USER, String.valueOf(hrmsUserBeanObj.getIsLegal()));
                    }

                    if (hrmsUserBeanObj.getValLogin().equals("OLD")) {
                         if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.OTP_NUMBER)))
                         {
                         otp=(String)request.getSession().getAttribute(ApplicationConstants.OTP_NUMBER);
                         }
                        if(otp.equals(otp_received)){
                        sReturnPage = ApplicationConstants.UIACTION_HOME_GET;}//for VITS
//                        sReturnPage = ApplicationConstants.REPORT_MSEDCL_VENDOR;}// for LEgal vits
                        else
                        {
                           String msgFive = "OTP Mismatch!Please regenerate OTP and enter again";
                    session.setAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION, msgFive);
                    sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST; 
                        }
                        session.setAttribute(ApplicationConstants.USER_NAME_SESSION, String.valueOf(hrmsUserBeanObj.getEmpNumber()));
                        session.setAttribute(ApplicationConstants.DISPLAY_NAME_SESSION, hrmsUserBeanObj.getEmpName());
                        vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                        legalInvoiceInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                        vendorInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                        session.setAttribute(ApplicationConstants.IS_LEGAL_USER, String.valueOf(hrmsUserBeanObj.getIsLegal()));
                    }

                    if (hrmsUserBeanObj.getValLogin().equals("INVALID")) {
                        String msg = "Invalid Login! Please try again";
                        session.setAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION, msg);
                        sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;

                    }

                } else {
                    sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;
                }
                System.out.println("-----USER TYPE : " + session.getAttribute(ApplicationConstants.USER_TYPE_SESSION));

            }//Vendor Login endgetlogin
            else if (userType.equals("Emp")) {
                if (!pa.equals("")) {
                    assignOfficeDTO = ldap.getLogin(uid, pa);

                    if (!(assignOfficeDTO.getOfficeCode().equals("")) || (assignOfficeDTO.getOfficeCode().equals(null))) {
                        System.out.println("*****" + assignOfficeDTO);

                        System.out.println("-----getOfficeCode.getOfficeCode : " + assignOfficeDTO.getOfficeCode());
                        System.out.println("-----getOfficerDesignation : " + assignOfficeDTO.getOfficerDesignation());
                        System.out.println("-----getOfficerName : " + assignOfficeDTO.getOfficerName());
                        System.out.println("-----getOfficerEmailId : " + assignOfficeDTO.getOfficerEmailId());

                        // Following parameters are newly added......Start
                        System.out.println("-----getOfficeIncharge : " + assignOfficeDTO.getOfficeIncharge());
                        System.out.println("-----getOfficeName : " + assignOfficeDTO.getOfficeName());
                        System.out.println("-----getOfficeTypeId : " + assignOfficeDTO.getOfficeTypeId());
                        System.out.println("-----getOfficeTypeName : " + assignOfficeDTO.getOfficeTypeName());
                        System.out.println("-----getOfficerContactNo : " + assignOfficeDTO.getOfficerContactNo());
                        System.out.println("-----getOfficerCpfNo : " + assignOfficeDTO.getOfficerCpfNo());
                        System.out.println("-----getOfficerRef : " + assignOfficeDTO.getOfficerRef());
                        System.out.println("-----getOfficerContactNo:" + assignOfficeDTO.getOfficerContactNo());
                        userName = assignOfficeDTO.getOfficerName();
                        userDesig = assignOfficeDTO.getOfficerDesignation();
                        userOfficeName = assignOfficeDTO.getOfficeName();
                        userOfficeId = assignOfficeDTO.getOfficeCode();
                        userOfficeTypeId = assignOfficeDTO.getOfficeTypeId();
                        userContactNo = assignOfficeDTO.getOfficerContactNo();
                        session.setAttribute(ApplicationConstants.USER_NAME_SESSION, String.valueOf(uid));
                        session.setAttribute(ApplicationConstants.DISPLAY_NAME_SESSION, userName);
                         vendorBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                         legalInvoiceInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                         vendorInputBeanObj.setVendorNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                        session.setAttribute(ApplicationConstants.DESIGNATION_SESSION, userDesig);
                        session.setAttribute(ApplicationConstants.OFFICE_NAME_SESSION, userOfficeName);
                        session.setAttribute(ApplicationConstants.OFFICE_CODE_SESSION, userOfficeId);
                        vendorInputBeanObj.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                        session.setAttribute(ApplicationConstants.OFFICE_TYPE_ID_SESSION, userOfficeTypeId);
                        vendorBeanObj.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                        legalInvoiceInputBeanObj.setLocationId((String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));


                        System.out.println("-----USER ID : " + session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                        System.out.println("-----USER NAME : " + session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION));

                        System.out.println("-----USER DESIG : " + session.getAttribute(ApplicationConstants.DESIGNATION_SESSION));
                        System.out.println("-----USER OFFICE NAME : " + session.getAttribute(ApplicationConstants.OFFICE_NAME_SESSION));
                        System.out.println("-----USER OFFICE CODE : " + session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                        System.out.println("-----USER OFFICE TYPE ID : " + session.getAttribute(ApplicationConstants.OFFICE_TYPE_ID_SESSION));
                        System.out.println("-----USER TYPE : " + session.getAttribute(ApplicationConstants.USER_TYPE_SESSION));


                        if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.OTP_NUMBER)))
                         {
                         otp=(String)request.getSession().getAttribute(ApplicationConstants.OTP_NUMBER);
                         }
                        if(otp.equals(otp_received)){
                        sReturnPage = ApplicationConstants.UIACTION_HOME_GET;//FOR VITS
//                            sReturnPage = ApplicationConstants.REPORT_MSEDCL_EMP;//FOR LEGAL VITS

                        }
                        else
                        {
                           String msgFive = "OTP Mismatch!Please regenerate OTP and enter again";
                    session.setAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION, msgFive);
                    sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST; 
                        }


                    } else {
                        String msgTwo = "Invalid Login! Please try again";
                        session.setAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION, msgTwo);
                        sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;
                    }
                    System.out.println("-----USER TYPE : " + session.getAttribute(ApplicationConstants.USER_TYPE_SESSION));
                }// password null ends
                else {
                    String msgFour = "Invalid Login! Password can not be blank!!";
                    session.setAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION, msgFour);
                    sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;
                }

            }//employee login ends
            //vendorPrezDataObj = vendorMgrObj.getTableList( vendorInputBeanObj);
            vendorPrezDataObj = vendorMgrObj.getSummaryList(vendorBeanObj);
            session.setAttribute(ApplicationConstants.AUTHORITY_SUMMARY_SESSION_DATA, vendorPrezDataObj);
             legalSummaryList = vendorMgrObj.getLegalSummaryList(legalInvoiceInputBeanObj);
            session.setAttribute(ApplicationConstants.AUTHORITY_LEGAL_SUMMARY_SESSION_DATA, legalSummaryList);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: getLogin() :: Exception :: " + ex);
            String msgThree = "Invalid Login! Please try again";
            session.setAttribute(ApplicationConstants.USER_LOGIN_MSG_SESSION, msgThree);
            sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;
        }

        return sReturnPage;
    }

  
   

    private String postLogin(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;
        HttpSession session = request.getSession();
        try {
            logger.log(Level.INFO, "SecurityHandler :: postLogin() :: method called");

            Enumeration list = session.getAttributeNames();

            for (; list.hasMoreElements();) {
                String s = (String) list.nextElement();
                session.removeAttribute(s);
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: postLogin() :: Exception :: " + ex);
        }
        return sReturnPage;
    }

    private String getUserDetails(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;
        SecurityDelegate securityDelegateObject = null;
        SecUserBean secUserObj = null;

        try {
            logger.log(Level.INFO, "SecurityHandler :: getUserDetails() :: method called");

            //-- get all selected data from request and set into SearchBean object
            secUserObj = fetchDataFromRequest(request);

            //== create a delegate object to get the serach results for seletced search criteria

            securityDelegateObject = new SecurityManager();
            secUserObj = (SecUserBean) securityDelegateObject.getUserDetails(secUserObj);
            //	request.setAttribute("SecUserObj",secUserObj);

            sReturnPage = ApplicationConstants.UIACTION_LOGIN_POST;

            //System.out.println("SecurityHandler :: getUserDetails :: sReturnPage :: " + sReturnPage);
            logger.log(Level.INFO, "SecurityHandler :: getUserDetails() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: getUserDetails() :: Exception :: " + ex);
            sReturnPage = ApplicationConstants.UIACTION_LOGOUT;
        }

        return sReturnPage;
    }

    private SecUserBean fetchDataFromRequest(HttpServletRequest request) throws Exception {
        SecUserBean secUserBeanObj = new SecUserBean();
        try {
            logger.log(Level.INFO, "SecurityHandler :: fetchDataFromRequest() :: method called");

            //-- get data from request and setting into SearchBean data object
            //	secUserBeanObj.setLoginId(request.getParameter("username"));
            //	secUserBeanObj.setPassword(request.getParameter("password"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: fetchDataFromRequest() :: Exception :: " + ex);
            throw ex;
        }

        return secUserBeanObj;

    }

    private String getPasswordChange(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_PASSWORD_CHANGE;

        SecUserBean secUserBeanObj = null;
        HttpSession userSession = null;
        try {
            logger.log(Level.INFO, "SecurityHandler :: fetchDataFromRequest() :: method called");
            userSession = request.getSession();
            secUserBeanObj = (SecUserBean) userSession.getAttribute(ApplicationConstants.USER_SESSION_NAME);
            request.setAttribute("secUserBean", secUserBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: fetchDataFromRequest() :: Exception :: " + ex);
            throw ex;
        }

        return sReturnPage;

    }

    private String postPasswordChange(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_POST_PASSWORD_CHANGE;

        String oldPass;
        String newPass;
        String confPass;
        String userName;
        UserBean userBeanObj = null;



        SecUserBean secUserBeanObj = null;
        HttpSession userSession = null;
        try {
            logger.log(Level.INFO, "SecurityHandler :: postPasswordChange() :: method called");
            userSession = request.getSession();
            oldPass = request.getParameter("oldPass");
            confPass = request.getParameter("confPass");
            newPass = request.getParameter("newPass");
            userName = request.getParameter("userName");

            System.out.println("user name P: " + userName);

            ///userBeanObj =  (UserBean) userSession.getAttribute(ApplicationConstants.USER_SESSION_NAME);
            userBeanObj = new UserBean();
            userBeanObj.setNewPass(newPass);
            userBeanObj.setOldPass(oldPass);
            userBeanObj.setConfPass(confPass);
            userBeanObj.setUserLoginId(userName);
            secUserBeanObj = (SecUserBean) userSession.getAttribute(ApplicationConstants.USER_SESSION_NAME);
            userBeanObj.setUserId(secUserBeanObj.getUserId());


            //System.out.println("user ID P: "+userBeanObj.getUserId());

            SecurityDelegate securityDelegate = new SecurityManager();

            userBeanObj = securityDelegate.postPasswordChange(userBeanObj);

            if (userBeanObj.getChangPassCount() > 0) {
                request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, "Password is Changed");
            } else {
                request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, "Password Not Changed");
            }

            ///request.setAttribute("secUserBean", secUserBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: postPasswordChange() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }

        return sReturnPage;

    }

    private String getServerAPI(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_SERVER_API;

        ServerAPIBean serverAPIBeanObj = null;
        SecurityDelegate secDelegateObj = null;
        String subAction = "";

        try {
            logger.log(Level.INFO, "SecurityHandler :: getServerAPI() :: method called");

            subAction = request.getParameter("subAction");

            String uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
            subAction = request.getParameter("subAction");



            serverAPIBeanObj = new ServerAPIBean();

            secDelegateObj = new SecurityManager();
            //-- Calling Manager to set search results from userbean
            serverAPIBeanObj = (ServerAPIBean) secDelegateObj.getServerAPIDetails(serverAPIBeanObj);

            request.getSession().setAttribute("serverAPIBeanObj", serverAPIBeanObj);

            // -- calling Page Manager--
            //-- Page persistence change starts --
            /**
             * if(uiActionName.equals(ApplicationConstants.UIACTION_POST_CREATE_ROLE))
             * { if(request.getParameter("lastRow") != null)
             * PagingManager.startPaging(request, (rolePrezData.getRoleList()),
             * false,
             * CLASS_NAME,((rolePrezData.getRoleBean()).getTotalRecords()));
             * else PagingManager.startPaging(request,
             * (rolePrezData.getRoleList()), true,
             * CLASS_NAME,((rolePrezData.getRoleBean()).getTotalRecords())); }
             * else PagingManager.startPaging(request,
             * (rolePrezData.getRoleList()), true,
             * CLASS_NAME,((rolePrezData.getRoleBean()).getTotalRecords()));
             * //-- Page persistence change ends --
             *
             */
            sReturnPage = ApplicationConstants.UIACTION_GET_SERVER_API;
            logger.log(Level.INFO, "SecurityHandler :: getServerAPI() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: getServerAPI() :: Exception :: " + ex);
            sReturnPage = ApplicationConstants.UIACTION_ROLE_SEARCH_POST;
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_SEARCH_RESULTS_FAILURE);
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX, ex);
            //ex.printStackTrace();
        }

        return sReturnPage;

    }

    private String LoginCpfPortal(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_CPF_LOGIN;
        String cpfNo = "";
        ServerAPIBean serverAPIBeanObj = null;
        SecurityDelegate secDelegateObj = null;
        String subAction = "";
        HttpSession session = request.getSession();

        try {
            logger.log(Level.INFO, "SecurityHandler :: LoginCpfPortal() :: method called");

            if (session.getAttribute(ApplicationConstants.USER_NAME_SESSION) != null) {
                cpfNo = ((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                //((String) session.getAttribute(ApplicationConstants.DISPLAY_NAME_SESSION));             
            }

            String uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
            subAction = request.getParameter("subAction");
            serverAPIBeanObj = new ServerAPIBean();

            secDelegateObj = new SecurityManager();
            //-- Calling Manager to set search results from userbean
            String msg = secDelegateObj.createCpfPortalSession(cpfNo);


            logger.log(Level.INFO, "SecurityHandler :: LoginCpfPortal() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: LoginCpfPortal() :: Exception :: " + ex);
            //ex.printStackTrace();
        }

        return sReturnPage;

    }

    private String getVendorLogin(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_LOGIN;
        HttpSession session = request.getSession();
        try {
            logger.log(Level.INFO, "SecurityHandler :: getVendorLogin() :: method called");

//            Enumeration list = session.getAttributeNames();
//
//            for (; list.hasMoreElements();) {
//                String s = (String) list.nextElement();
//                session.removeAttribute(s);
//            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "SecurityHandler :: getVendorLogin() :: Exception :: " + ex);
        }
        return sReturnPage;
    }
//      private String getVendorList(HttpServletRequest request) throws Exception {
//        String sReturnPage = ApplicationConstants.UIACTION_GET_VENDOR_LIST;
//        HttpSession session = request.getSession();
//        try {
//            logger.log(Level.INFO, "SecurityHandler :: getVendorList() :: method called");
//
////            Enumeration list = session.getAttributeNames();
////
////            for (; list.hasMoreElements();) {
////                String s = (String) list.nextElement();
////                session.removeAttribute(s);
////            }
//
//        } catch (Exception ex) {
//            logger.log(Level.ERROR, "SecurityHandler :: getVendorList() :: Exception :: " + ex);
//        }
//        return sReturnPage;
//    }

   
}
