/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.handler;

import in.emp.arch.GenericFormHandler;
import in.emp.common.ApplicationConstants;
import in.emp.home.correctionform.CorrectionFileDelegate;
import in.emp.home.correctionform.CorrectionFormDelegate;
import in.emp.home.correctionform.bean.CorrectionFileBean;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionform.bean.CorrectionFormPrezData;
import in.emp.home.correctionform.manager.CorrectionFileManager;
import in.emp.home.correctionform.manager.CorrectionFormManager;
import in.emp.home.correctionhr.CorrectionFormHRDelegate;
import in.emp.home.correctionhr.manager.CorrectionFormHRManager;
import in.emp.home.notifications.NotificationsDelegate;
import in.emp.home.notifications.bean.NotificationsBean;
import in.emp.home.notifications.bean.NotificationsPrezData;
import in.emp.home.notifications.manager.NotificationsManager;
import in.emp.home.personalInfo.PersonalinfoDelegate;
import in.emp.home.personalInfo.bean.PersonalinfoBean;
import in.emp.home.personalInfo.bean.PersonalinfoPrezData;
import in.emp.home.personalInfo.manager.PersonalinfoManager;
import in.emp.home.personalInfoUpdation.PersonalinfoupdationDelegate;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationBean;
import in.emp.home.personalInfoUpdation.manager.PersonalinfoupdationManager;
import in.emp.system.dao.helpers.MultipartRequestParser;
import in.emp.util.ApplicationUtils;
import java.util.HashMap;
import java.util.LinkedList;
import jakarta.servlet.http.HttpServletRequest  ;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author rushi
 */
public class PersonalInfoHandler implements GenericFormHandler {
    //private static Logger logger = new Logger(RoleHandler.class.getName());

    private static String CLASS_NAME = PersonalInfoHandler.class.getName();
    private static Logger logger = Logger.getLogger(PersonalInfoHandler.class);

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
            logger.log(Level.INFO, "PersonalInfoHandler :: execute() :: method called");
            contentType = request.getContentType();
            if ((contentType != null) && (contentType.startsWith("multipart/form-data"))) // Handle multipart request
            {
                MultipartRequestParser mrp = (MultipartRequestParser) request.getSession().getAttribute("MultipartRequestMrp");
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
            }

            if (!ApplicationUtils.isBlank(uiActionName)) {
                if (uiActionName.equals(ApplicationConstants.UIACTION_PERSONALINFO_GET)) {
                    sReturnPage = getPersonalInfo(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_BIO_ATTEND_GET)) {
                    sReturnPage = ApplicationConstants.UIACTION_BIO_ATTEND_GET;
                    request.getSession().setAttribute(ApplicationConstants.BIOMETRIC_SESSION_DATA, null);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_BIO_ATTEND_POST)) {
                    sReturnPage = ApplicationConstants.UIACTION_BIO_ATTEND_GET;
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_GET)) {
                    sReturnPage = getCorrectionFormList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_GET)) {
                    sReturnPage = getCorrectionForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FILE_GET)) {
                    sReturnPage = getCorrectionFile(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FILE_POST)) {
                    sReturnPage = postCorrectionFile(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_POST)) {
                    sReturnPage = postCorrectionFormList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_HR_GET)) {
                    sReturnPage = getCorrectionFormHRList(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_HR_GET)) {
                    sReturnPage = getCorrectionFormHR(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_PI_UPDATION_FORM_GET)) {
                    sReturnPage = getPiUpdationForm(request);
                } else if (uiActionName.equals(ApplicationConstants.UIACTION_LEGALDASHBOARD_GET)) {
                    sReturnPage = getLegalDashboard(request);
                }  else {
                    sReturnPage = getHome(request);
                }
            }
            logger.log(Level.INFO, "PersonalInfoHandler :: execute() :: sReturnPage :: " + sReturnPage);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: execute() :: Exception :: " + ex);
        }

        return sReturnPage;

    } //end execute method

    public String getPersonalInfo(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_PERSONALINFO_GET;
        PersonalinfoBean pinfoBeanObj = new PersonalinfoBean();
        PersonalinfoDelegate personalinfoMgrObj = new PersonalinfoManager();
        HttpSession pinfoSession = request.getSession();
        PersonalinfoPrezData personalinfoPrezData = new PersonalinfoPrezData();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getPersonalInfo() :: method called :: ");

            if (!ApplicationUtils.isBlank(pinfoSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                pinfoBeanObj.setEmpNumber((String) pinfoSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                pinfoBeanObj.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(pinfoSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                pinfoBeanObj.setLocation((String) pinfoSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }

            personalinfoPrezData = personalinfoMgrObj.getPersonalInfo(pinfoBeanObj);

            pinfoSession.setAttribute(ApplicationConstants.PERSONAL_INFO_SESSION_DATA, personalinfoPrezData);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getPersonalInfo() :: Exception :: " + ex);
        }
        return sReturnPage;
    }

    public String getHome(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_HOME_GET;
        NotificationsPrezData notificationsPrezDataObj = new NotificationsPrezData();
        NotificationsBean notificationsBeanObj = new NotificationsBean();
        NotificationsDelegate notificationsMgrObj = new NotificationsManager();
        HttpSession session = request.getSession();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getHome() :: method called :: ");

//            if (!ApplicationUtils.isBlank(session.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
//                notificationsBeanObj.setEmpNumber((String) session.getAttribute(ApplicationConstants.USER_NAME_SESSION));
//            } else {
//                notificationsBeanObj.setEmpNumber("");
//            }
//            notificationsPrezDataObj.setNotificationBeanObj(notificationsBeanObj);
//            notificationsPrezDataObj = notificationsMgrObj.getNotificationsList(notificationsPrezDataObj);
//
//            session.setAttribute(ApplicationConstants.NOTIFICATIONS_SESSION_DATA, notificationsPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getHome() :: Exception :: " + ex);
        }
        return sReturnPage;
    }
     public String getLegalDashboard(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_LEGALDASHBOARD_GET;
     
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getLegalDashboard() :: method called :: ");
        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getLegalDashboard() :: Exception :: " + ex);
        }
        return sReturnPage;
    }
    

    private String getCorrectionFormList(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_GET;
        CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
        CorrectionFormDelegate correctionFormMgr = new CorrectionFormManager();
        HttpSession correctionSession = request.getSession();
        LinkedList<CorrectionFormBean> applList = new LinkedList<CorrectionFormBean>();
        CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getCorrectionFormList() :: method called :: ");

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                correctionFormBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                correctionFormBeanObj.setEmpNumber("");
            }

            applList = correctionFormMgr.getCorrectionFormList(correctionFormBeanObj);
            correctionFormPrezDataObj.setApplicationList(applList);
            correctionSession.setAttribute(ApplicationConstants.CORRECTION_SESSION_DATA, correctionFormPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getCorrectionFormList() :: Exception :: " + ex);
        }
        return sReturnPage;
    }

    private String getCorrectionForm(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_GET;
        CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();
        CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
        CorrectionFormDelegate correctionFormMgrObj = new CorrectionFormManager();
        HttpSession correctionSession = request.getSession();
        LinkedList FileList = new LinkedList();
        CorrectionFileDelegate correctionFileMgrObj = new CorrectionFileManager();
        CorrectionFileBean correctionFileBeanObj = new CorrectionFileBean();
        PersonalinfoBean pinfoBeanObj = new PersonalinfoBean();
        PersonalinfoDelegate personalinfoMgrObj = new PersonalinfoManager();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getCorrectionForm() :: method called :: ");

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                correctionFormBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                pinfoBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                correctionFormBeanObj.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                correctionFormBeanObj.setLocation((String) correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }
            String AppId = ApplicationUtils.getRequestParameter(request, "AppId");
            if (!ApplicationUtils.isBlank(AppId)) {
                correctionFormBeanObj.setApplicationId(AppId);
                correctionFileBeanObj.setApplicationId(AppId);
                FileList = correctionFileMgrObj.getCorrectionFileList(correctionFileBeanObj);
            }

            correctionFormBeanObj = correctionFormMgrObj.getCorrectionForm(correctionFormBeanObj);
            pinfoBeanObj = ((PersonalinfoPrezData) personalinfoMgrObj.getPersonalInfo(pinfoBeanObj)).getPinfoBean();

            correctionFormPrezDataObj.setCorrectionFormBean(correctionFormBeanObj);
            correctionFormPrezDataObj.setFileList(FileList);
            correctionFormPrezDataObj.setpInfoBean(pinfoBeanObj);

            correctionSession.setAttribute(ApplicationConstants.CORRECTION_SESSION_DATA, correctionFormPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getCorrectionForm() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }

    private String postCorrectionFormList(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_GET;
        CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();
        CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
        CorrectionFormDelegate correctionFormMgrObj = new CorrectionFormManager();
        HttpSession correctionSession = request.getSession();
        LinkedList FileList = new LinkedList();
        CorrectionFileDelegate correctionFileMgrObj = new CorrectionFileManager();
        CorrectionFileBean correctionFileBeanObj = new CorrectionFileBean();
        PersonalinfoBean pinfoBeanObj = new PersonalinfoBean();
        PersonalinfoDelegate personalinfoMgrObj = new PersonalinfoManager();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getCorrectionForm() :: method called :: ");

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                correctionFormBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
                pinfoBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                correctionFormBeanObj.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                correctionFormBeanObj.setLocation((String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }
            String AppId = ApplicationUtils.getRequestParameter(request, "AppId");
            if (!ApplicationUtils.isBlank(AppId)) {
                correctionFormBeanObj.setApplicationId(AppId);
                correctionFileBeanObj.setApplicationId(AppId);
                FileList = correctionFileMgrObj.getCorrectionFileList(correctionFileBeanObj);
            }

            correctionFormBeanObj = correctionFormMgrObj.getCorrectionForm(correctionFormBeanObj);
            pinfoBeanObj = ((PersonalinfoPrezData) personalinfoMgrObj.getPersonalInfo(pinfoBeanObj)).getPinfoBean();

            correctionFormPrezDataObj.setCorrectionFormBean(correctionFormBeanObj);
            correctionFormPrezDataObj.setFileList(FileList);
            correctionFormPrezDataObj.setpInfoBean(pinfoBeanObj);

            correctionSession.setAttribute(ApplicationConstants.CORRECTION_SESSION_DATA, correctionFormPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getCorrectionForm() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }

    private String getCorrectionFile(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FILE_GET;
        CorrectionFileBean correctionFileBeanObj = new CorrectionFileBean();
        CorrectionFileDelegate correctionFileMgr = new CorrectionFileManager();
        String AppId = "";
        String FId = "";
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getCorrectionFile() :: method called :: ");

            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                correctionFileBeanObj.setEmpNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));
                CorrectionFormBean correctionFormBeanObj = ((CorrectionFormPrezData) request.getSession().getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA)).getCorrectionFormBean();
                correctionFileBeanObj.setApplicationId(correctionFormBeanObj.getApplicationId());
            }

            if (!ApplicationUtils.isBlank(request.getParameter("AppId"))) {
                AppId = (String) request.getParameter("AppId");
            }
            if (!ApplicationUtils.isBlank(request.getParameter("FId"))) {
                FId = (String) request.getParameter("FId");
            }
            correctionFileBeanObj.setId(FId);
            correctionFileBeanObj.setApplicationId(AppId);

            correctionFileBeanObj = correctionFileMgr.getCorrectionFile(correctionFileBeanObj);

            request.getSession().setAttribute(ApplicationConstants.CORRECTION_FILE_SESSION_DATA, correctionFileBeanObj);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getCorrectionFile() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }

    private String postCorrectionFile(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FILE_POST;
        HashMap hashObj = null;
        MultipartRequestParser mrp = null;
        byte[] fileContent = null;
        String fileName = "";
        String fname = "";
        String ext = "";
        CorrectionFileBean correctionFileBeanObj = new CorrectionFileBean();
        CorrectionFileDelegate correctionFileDelegate = new CorrectionFileManager();
        String remark = "";
        LinkedList FileList = new LinkedList();
        String fOpt = "";
        CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: postCorrectionFile() :: method called :: ");

            mrp = (MultipartRequestParser) request.getSession().getAttribute("MultipartRequestMrp");

            mrp.parseOnly(); //parse the request
            hashObj = mrp.webVars;
            if (hashObj != null && hashObj.size() > 0) {
                if (request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION) != null) {
                    correctionFileBeanObj.setEmpNumber((String) request.getSession().getAttribute(ApplicationConstants.USER_NAME_SESSION));
                    CorrectionFormBean correctionFormBeanObj = ((CorrectionFormPrezData) request.getSession().getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA)).getCorrectionFormBean();
                    correctionFileBeanObj.setApplicationId(correctionFormBeanObj.getApplicationId());
                }
                if (request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION) != null) {
                    correctionFileBeanObj.setLocation((String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
                }
            }
            fileName = mrp.fileName;
            int mid = fileName.lastIndexOf(".");
            fname = fileName.substring(0, mid);
            ext = fileName.substring(mid + 1, fileName.length());       //If in future we need to restrict the file-extensions.
            fileContent = mrp.output;
            remark = (String) hashObj.get("txtRemark");
            fOpt = (String) hashObj.get("FOpt");

            correctionFileBeanObj.setFileContents(fileContent);
            correctionFileBeanObj.setFileName(fname);
            correctionFileBeanObj.setFileType(ext);
            correctionFileBeanObj.setRemark(remark);
            correctionFileBeanObj.setOption(fOpt);

            correctionFileBeanObj = correctionFileDelegate.CorrectionFileTxnHelper(correctionFileBeanObj);
            String AppId = correctionFileBeanObj.getApplicationId();

            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA))) {
                correctionFormPrezDataObj = (CorrectionFormPrezData) request.getSession().getAttribute(ApplicationConstants.CORRECTION_SESSION_DATA);
            }
            if (!ApplicationUtils.isBlank(correctionFormPrezDataObj.getFileList())) {
                FileList = correctionFormPrezDataObj.getFileList();
            }
            if (!ApplicationUtils.isBlank(correctionFileBeanObj)) {
                if (!ApplicationUtils.isBlank(correctionFileBeanObj.getId())) {
                    FileList.add(correctionFileBeanObj);
                }
            }
            correctionFormPrezDataObj.setFileList(FileList);
            request.getSession().setAttribute(ApplicationConstants.CORRECTION_SESSION_DATA, correctionFormPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: postCorrectionFile() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }

    private String getCorrectionFormHRList(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_HR_GET;
        CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
        CorrectionFormHRDelegate correctionFormHRMgr = new CorrectionFormHRManager();
        HttpSession correctionSession = request.getSession();
        LinkedList<CorrectionFormBean> applList = new LinkedList<CorrectionFormBean>();
        CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getCorrectionFormHRList() :: method called :: ");

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                correctionFormBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                correctionFormBeanObj.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                correctionFormBeanObj.setLocation((String) correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }

            applList = correctionFormHRMgr.getCorrectionFormHRList(correctionFormBeanObj);
            correctionFormPrezDataObj.setApplicationList(applList);
            correctionSession.setAttribute(ApplicationConstants.CORRECTION_SESSION_DATA, correctionFormPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getCorrectionFormHRList() :: Exception :: " + ex);
        }
        return sReturnPage;
    }

    private String getCorrectionFormHR(HttpServletRequest request) {
        String sReturnPage = ApplicationConstants.UIACTION_CORRECTION_APPLICATION_FORM_HR_GET;
        CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();
        CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
        CorrectionFormHRDelegate correctionFormHRMgr = new CorrectionFormHRManager();
        HttpSession correctionSession = request.getSession();
        LinkedList FileList = new LinkedList();
        CorrectionFileDelegate correctionFileMgrObj = new CorrectionFileManager();
        CorrectionFileBean correctionFileBeanObj = new CorrectionFileBean();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getCorrectionFormHR() :: method called :: ");

            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                correctionFormBeanObj.setEmpNumber((String) correctionSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                correctionFormBeanObj.setEmpNumber("");
            }
            if (!ApplicationUtils.isBlank(correctionSession.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION))) {
                correctionFormBeanObj.setLocation((String) request.getSession().getAttribute(ApplicationConstants.OFFICE_CODE_SESSION));
            }
            String AppId = ApplicationUtils.getRequestParameter(request, "AppId");
            if (!ApplicationUtils.isBlank(AppId)) {
                correctionFormBeanObj.setApplicationId(AppId);
                correctionFileBeanObj.setApplicationId(AppId);
                FileList = correctionFileMgrObj.getCorrectionFileList(correctionFileBeanObj);
            }

            correctionFormBeanObj = correctionFormHRMgr.getCorrectionFormHR(correctionFormBeanObj);

            correctionFormPrezDataObj.setCorrectionFormBean(correctionFormBeanObj);
            correctionFormPrezDataObj.setFileList(FileList);

            correctionSession.setAttribute(ApplicationConstants.CORRECTION_SESSION_DATA, correctionFormPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getCorrectionFormHR() :: Exception :: " + ex);
            //ex.printStackTrace();
        }
        return sReturnPage;
    }

    public String getPiUpdationForm(HttpServletRequest request) throws Exception {
        String sReturnPage = ApplicationConstants.UIACTION_PI_UPDATION_FORM_GET;
        PersonalinfoupdationBean pinfoupdationBeanObj = new PersonalinfoupdationBean();
        PersonalinfoupdationDelegate personalinfoupdationMgrObj = new PersonalinfoupdationManager();
        HttpSession pinfoupdationSession = request.getSession();
        try {
            logger.log(Level.INFO, "PersonalInfoHandler :: getPiUpdationForm() :: method called :: ");

            if (!ApplicationUtils.isBlank(pinfoupdationSession.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                pinfoupdationBeanObj.setEmpNumber((String) pinfoupdationSession.getAttribute(ApplicationConstants.USER_NAME_SESSION));
            } else {
                pinfoupdationBeanObj.setEmpNumber("");
            }

            pinfoupdationBeanObj = personalinfoupdationMgrObj.getPiUpdationForm(pinfoupdationBeanObj);

            pinfoupdationSession.setAttribute(ApplicationConstants.PERSONAL_INFO_UPDATION_SESSION_DATA, pinfoupdationBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, "PersonalInfoHandler :: getPiUpdationForm() :: Exception :: " + ex);
        }
        return sReturnPage;
    }
}
