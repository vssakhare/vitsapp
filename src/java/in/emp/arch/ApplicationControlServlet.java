package in.emp.arch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.io.InputStream;
import java.util.Properties;
import java.util.Enumeration;

import in.emp.common.ApplicationConstants;
import in.emp.util.ApplicationUtils;
import in.emp.common.ActionClass;
import in.emp.common.ConfigBean;
import in.emp.master.MasterDelegate;
import in.emp.master.bean.MasterBean;
import in.emp.master.manager.MasterManager;

import in.emp.security.bean.SecUserBean;
import in.emp.security.SecurityDelegate;
import in.emp.security.manager.SecurityManager;
import in.emp.system.dao.MasterDTO;
import in.emp.system.dao.SecurityDTO;
import in.emp.system.dao.helpers.MultipartRequestParser;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.ServletContext;

public class ApplicationControlServlet extends HttpServlet {

    private static HashMap hmActionClassMap;
    private static HashMap resourceBundleMap = new HashMap();

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream inputStream = null;
        try {
            super.init(config);
            String path = ApplicationConstants.CONFIG_XML_PATH;
            inputStream = (config.getServletContext()).getResourceAsStream(path);
            hmActionClassMap = ActionClass.buildActionClassMap(inputStream);
            Properties rsProperties = new Properties();
            String rsPath = ApplicationConstants.RESOURCEBUNDLE_PATH;
            InputStream rsInputStream = (config.getServletContext()).getResourceAsStream(rsPath);
            rsProperties.load(rsInputStream);
            for (Enumeration e = rsProperties.propertyNames(); e.hasMoreElements();) {
                String propertyName = (String) e.nextElement();
                String propertyValue = rsProperties.getProperty(propertyName);
                resourceBundleMap.put(propertyName, propertyValue);
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        } finally {
            try {
                if (!ApplicationUtils.isBlank(inputStream)) {
                    inputStream.close();
                    inputStream = null;
                }
            } catch (Exception ioEx) {
            }
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param HttpServletRequest request, servlet request
     * @param HttpServletResponse response, servlet response
     * @return void
     * @throws ServletException , IOException
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "";
        String sResultPage = "";
        String uiActionName = "";
        String successPage = "";
        HttpSession session = request.getSession(true);
        ConfigBean configObj = null;
        String name = "";
        String value = "";
        String department = "";
        String designation = "";
        String uiSubActionName = "";
        String contentType = "";
        String office_loc_id = "";
        HashMap hashObj = null;
       
            //getCircleList(request);
            //getBuList(request);
//            loadServletContextData(request);
            String userPath = request.getServletPath();
            
             if (userPath.equals("/chooseLanguage")) {

    // get language choice
    String language = request.getParameter("language");

    // place in request scope
    request.setAttribute("language", language);
 
    // forward request to welcome page
    try {
        request.getRequestDispatcher("/jsp/emp_login_page.jsp").forward(request, response);
    } catch (Exception ex) {
        //ex.printStackTrace();
    }
    return;
}
             else{
              try {
            if (!ApplicationUtils.isBlank(resourceBundleMap) && !resourceBundleMap.isEmpty()) {
                if (request.getSession().getAttribute(ApplicationConstants.SESSION_APP_RESOURCE) == null) {

                    request.getSession().setAttribute(ApplicationConstants.SESSION_APP_RESOURCE, resourceBundleMap);
                }
            }
           
            if (!ApplicationUtils.isBlank(request.getParameter(ApplicationConstants.UIACTION_NAME))) {
                uiActionName = request.getParameter(ApplicationConstants.UIACTION_NAME);
            } else {
                //uiActionName = ApplicationConstants.UIACTION_GET_LOGIN;
                uiActionName = ApplicationConstants.UIACTION_HOME_GET;
            }

            if (!ApplicationUtils.isBlank(request.getParameter("subAction"))) {
                uiSubActionName = request.getParameter("subAction");
            }

            contentType = request.getContentType();
            if ((!ApplicationUtils.isBlank(contentType)) && (contentType.startsWith("multipart/form-data"))) // Handle multipart request
            {
               MultipartRequestParser mrp = new MultipartRequestParser(request);
                mrp.parseOnly(); //parse the request
                hashObj = mrp.webVars;
                if (!ApplicationUtils.isBlank(hashObj) && !hashObj.isEmpty()) {
                    if (!ApplicationUtils.isBlank(hashObj.get(ApplicationConstants.UIACTION_NAME))) {
                        uiActionName = (String) hashObj.get(ApplicationConstants.UIACTION_NAME);
                    }
                    request.getSession().setAttribute("MultipartRequestMrp", mrp);
                }
            }
            request.setAttribute(ApplicationConstants.UIACTION_NAME, uiActionName);
            if (!ApplicationUtils.isBlank(uiActionName) && !uiActionName.equals(ApplicationConstants.UIACTION_LOGIN_GET)) {
                uiActionName = checkUserAccess(request);
            }
            System.out.println("uiActionName::" + uiActionName);

            if (!ApplicationUtils.isBlank(uiActionName) && !uiActionName.isEmpty() && !uiActionName.equals(ApplicationConstants.UIACTION_ENTITLED_ERROR) && !uiActionName.equals(ApplicationConstants.UIACTION_GET_LOGIN)) {
                configObj = (ConfigBean) hmActionClassMap.get(uiActionName);
                if (!ApplicationUtils.isBlank(configObj)) {
                    command = configObj.getHandlerClassName();
                    Class cls = Class.forName(command);
                    GenericFormHandler formHandlerObj = (GenericFormHandler) cls.newInstance();
                    sResultPage = formHandlerObj.execute(request);
                    if (!ApplicationUtils.isBlank(sResultPage) && !sResultPage.isEmpty()) {
                        uiActionName = sResultPage;
                    }
                } else {
                    uiActionName = ApplicationConstants.UIACTION_ERROR;
                }
            }
           // commented by prajakta as on 18-11-2017
            if (ApplicationUtils.isBlank(session.getAttribute(ApplicationConstants.USER_NAME_SESSION))) {
                uiActionName = ApplicationConstants.UIACTION_REDIRECT_LINK;
            }
            configObj = (ConfigBean) hmActionClassMap.get(uiActionName);
            if (!ApplicationUtils.isBlank(configObj)) {
                uiActionName = configObj.getUiSuccessPage();
            } else {
                uiActionName = ApplicationConstants.UIACTION_REDIRECT_LINK;
            }
        } catch (Exception ex) {
            if (!ApplicationUtils.isBlank(command)) {
                configObj = (ConfigBean) hmActionClassMap.get(uiActionName);
                if (!ApplicationUtils.isBlank(configObj)) {
                    uiActionName = configObj.getUiErrorPage();
                } else {
                    uiActionName = ApplicationConstants.UIACTION_GET_LOGIN;
                }
            }
            //ex.printStackTrace();
        } finally {
            RequestDispatcher rdObj = request.getRequestDispatcher(uiActionName);
            rdObj.forward(request, response);
        }}
    }

    private void getBuList(HttpServletRequest request) throws Exception {
        // Billing Unit List
        List billingUnitList = null;
        if (!ApplicationUtils.isBlank(request.getSession().getAttribute("billingUnitList"))) {
            billingUnitList = (List) request.getSession().getAttribute("billingUnitList");
        }
        if (billingUnitList == null || billingUnitList.isEmpty()) {
            billingUnitList = ApplicationUtils.getBuList(request.getSession());
        }
        if (billingUnitList.isEmpty()) {
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_NO_USER_BU_FOUND);
        }
        request.getSession().setAttribute("billingUnitList", billingUnitList);
        String buListString = "";

        if (request.getSession().getAttribute("accessBUList") == null && !billingUnitList.isEmpty()) {
            Iterator itr = billingUnitList.iterator();
            while (itr.hasNext()) {
                if (!buListString.equals("")) {
                    buListString += ",";
                }
                MasterDTO masterDTO = (MasterDTO) itr.next();
                buListString += "'" + masterDTO.getCode() + "'";
            }
            request.getSession().setAttribute("accessBUList", buListString);
        }
    }

    private void getCircleList(HttpServletRequest request) throws Exception {
        // Circle List
        List circleList = null;
        if (!ApplicationUtils.isBlank(request.getSession().getAttribute("circleList"))) {
            circleList = (List) request.getSession().getAttribute("circleList");
        }
        if (circleList == null || circleList.isEmpty()) {
            circleList = ApplicationUtils.getCircleList(request.getSession());
        }
        if (circleList.isEmpty()) {
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SYS_MSG_NO_USER_CIR_FOUND);
        }
        request.getSession().setAttribute("circleList", circleList);
        String cirListString = "";
        if (request.getSession().getAttribute("accessCircleList") == null && !circleList.isEmpty()) {
            Iterator itr = circleList.iterator();
            while (itr.hasNext()) {
                if (!cirListString.equals("")) {
                    cirListString += ",";
                }
                MasterDTO masterDTO = (MasterDTO) itr.next();
                cirListString += "'" + masterDTO.getCode() + "'";
            }
            request.getSession().setAttribute("accessCircleList", cirListString);
        }
    }

    private String getOfficeName(HttpSession session) throws Exception {
        String officeName = null;

        String officeType = (String) session.getAttribute(ApplicationConstants.OFFICE_TYPE_SESSION);
        SecurityDelegate securityDelegate = new SecurityManager();
        // NewConnectionInterface ncImpl = new NewConnectionImpl();

        if (ApplicationUtils.validateString(officeType)) {

            long officeTyp = Long.parseLong(officeType);

            if (officeTyp == ApplicationConstants.SUBSTATION_TYPE_ID) {
                String parentOfficeCode = (String) session.getAttribute(ApplicationConstants.PARENT_OFFICE_CODE_SESSION);
                SecurityDTO securityDTO = new SecurityDTO(Long.parseLong(parentOfficeCode), 0, 0);
                if (ApplicationUtils.validateString(parentOfficeCode)) {
                    officeName = securityDelegate.getOfficeNameOther(securityDTO);
                }
            }

            if (officeTyp == ApplicationConstants.SECTION_TYPE_ID) {
                String officeCode = (String) session.getAttribute(ApplicationConstants.OFFICE_CODE_SESSION);

                SecurityDTO securityDTO = new SecurityDTO(
                        Long.parseLong(officeCode), 0, 0);
                if (ApplicationUtils.validateString(officeCode)) {
                    officeName = securityDelegate.getOfficeNameOther(securityDTO);
                }
            }

            if (officeTyp == ApplicationConstants.SUBDIVISION_TYPE_ID) {

                String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);

                SecurityDTO securityDTO = new SecurityDTO(0, Long.parseLong(billingDbCode), ApplicationConstants.SUBDIVISION_TYPE_ID);
                if (ApplicationUtils.validateString(billingDbCode)) {
                    officeName = securityDelegate.getOfficeName(securityDTO);
                }
            }

            if (officeTyp == ApplicationConstants.DIVISION_TYPE_ID) {
                String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);

                SecurityDTO securityDTO = new SecurityDTO(0,
                        Long.parseLong(billingDbCode),
                        ApplicationConstants.DIVISION_TYPE_ID);
                if (ApplicationUtils.validateString(billingDbCode)) {
                    officeName = securityDelegate.getOfficeName(securityDTO);
                }
            }

            if (officeTyp == ApplicationConstants.CIRCLE_TYPE_ID) {
                String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);

                SecurityDTO securityDTO = new SecurityDTO(0,
                        Long.parseLong(billingDbCode),
                        ApplicationConstants.CIRCLE_TYPE_ID);
                if (ApplicationUtils.validateString(billingDbCode)) {
                    officeName = securityDelegate.getOfficeName(securityDTO);
                }
            }

            if (officeTyp == ApplicationConstants.ZONE_TYPE_ID) {
                String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);

                SecurityDTO securityDTO = new SecurityDTO(0,
                        Long.parseLong(billingDbCode),
                        ApplicationConstants.ZONE_TYPE_ID);
                if (ApplicationUtils.validateString(billingDbCode)) {
                    officeName = securityDelegate.getOfficeName(securityDTO);
                }
            }

            if (officeTyp == ApplicationConstants.HEAD_OFFICE_TYPE_ID) {
                String billingDbCode = (String) session.getAttribute(ApplicationConstants.BILLING_DB_CODE_SESSION);

                SecurityDTO securityDTO = new SecurityDTO(0,
                        Long.parseLong(billingDbCode),
                        ApplicationConstants.HEAD_OFFICE_TYPE_ID);
                if (ApplicationUtils.validateString(billingDbCode)) {
                    officeName = securityDelegate.getOfficeName(securityDTO);
                }
            }

            if (officeTyp == ApplicationConstants.CIRCLE_TYPE_ID) {
                String parentBillingDbCode = (String) session.getAttribute(ApplicationConstants.PARENT_OFFICE_BILLING_DB_CODE_SESSION);
                //logger.debug("Case: 5: parentBillingDbCode ==>"
                //        + parentBillingDbCode);

                SecurityDTO securityDTO = new SecurityDTO(0,
                        Long.parseLong(parentBillingDbCode),
                        ApplicationConstants.CIRCLE_TYPE_ID);
                if (ApplicationUtils.validateString(parentBillingDbCode)) {
                    officeName = securityDelegate.getOfficeName(securityDTO);
                }
            }

        }

        return officeName;
    }

    private String checkUserAccess(HttpServletRequest request) throws Exception {
        String uiAction = null;
        HashMap functionMap = null;
        try {
            uiAction = (String) request.getAttribute(ApplicationConstants.UIACTION_NAME);

            if (!ApplicationUtils.isBlank(request.getSession().getAttribute(ApplicationConstants.USER_SESSION_NAME))) {
                if (!ApplicationUtils.isBlank(request.getSession().getAttribute("userFunctionMap"))) {
                    functionMap = (HashMap) request.getSession().getAttribute("userFunctionMap");
                    if (!ApplicationUtils.isBlank(functionMap) && !functionMap.isEmpty()) {
                        if (!functionMap.containsKey(uiAction)) {
                            uiAction = ApplicationConstants.UIACTION_HOME_GET;
                        }
                    }
                }
            } else {
                {
                    uiAction = validateUser(request);
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            throw ex;
        }
        return uiAction;

    }

    public String validateUser(HttpServletRequest request) throws Exception {
        String uiActionName = ApplicationConstants.UIACTION_GET_LOGIN;
        SecUserBean secUserBean;
        SecurityDelegate securityDelegateObj = new SecurityManager();
        HttpSession userSession = request.getSession();
        try {
            
            secUserBean = fetchUserDetails(request);
            
            secUserBean = (SecUserBean) securityDelegateObj.getUserDetails(secUserBean);

            if (!ApplicationUtils.isBlank(secUserBean)) {
                if ((secUserBean.getStatusCd()).equals("A")) {
                    
                    userSession.setAttribute(ApplicationConstants.USER_SESSION_NAME, secUserBean);
                    userSession.setAttribute("userFunctionMap", secUserBean.getUserFunctionMap());
                    if (!ApplicationUtils.isBlank(userSession.getAttribute(ApplicationConstants.BILLING_UNIT_SESSION))
                            && userSession.getAttribute(ApplicationConstants.BILLING_UNIT_SESSION).equals("00")) {
                        uiActionName = ApplicationConstants.UIACTION_HOME_GET;
                    } else {
                        userSession.setAttribute(ApplicationConstants.BILLING_UNIT_SESSION, "00");
                        uiActionName = checkUserAccess(request);
                    }
                } else {
                    uiActionName = ApplicationConstants.UIACTION_GET_LOGIN;
                    request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, "Wrong Username Or Password");
                }
            } else {
                uiActionName = ApplicationConstants.UIACTION_GET_LOGIN;
                //secUserBean.getLoginId();
                //secUserBean.getPassword();
                request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, "Wrong Username Or Password");
                //request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE, ApplicationConstants.SEC_MSG_INVALID_USER);
            }
        } catch (Exception ex) {
            uiActionName = ApplicationConstants.UIACTION_GET_LOGIN;
            request.setAttribute(ApplicationConstants.SYS_MSG_ATTRIBUTE_EX, ex);
            throw ex;
        }
        return uiActionName;
    }

    public SecUserBean fetchUserDetails(HttpServletRequest request) throws Exception {
        SecUserBean secUserObj = new SecUserBean();
        try {
        } catch (Exception ex) {
            throw ex;
        }
        return secUserObj;
    }

    @Override
    public void destroy() {
        hmActionClassMap = null;
        resourceBundleMap = null;
    }

    public void loadServletContextData(HttpServletRequest request) throws Exception {
        MasterDelegate masterManagerObj = new MasterManager();
        LinkedList<MasterBean> statusList = new LinkedList<MasterBean>();
        try {
            ServletContext context = request.getSession().getServletContext();
            
            if (!ApplicationUtils.isBlank(context.getAttribute("statusList"))) {
                if (((LinkedList) context.getAttribute("statusList")).isEmpty()) {
                    statusList = masterManagerObj.getMasterList("XXMIS_EMP_APPL_STATUS_MASTER", "ID","CONCAT(TYPE,STATUS_TEXT)", "DESCRIPTION", "1 = 1");
                }
            }
            context.setAttribute("statusList", statusList);
        } catch(Exception ex) {
            
        }
    }
}
