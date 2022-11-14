package in.emp.security.manager;

//-- Java Imports
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

//-- MDA Imports
import in.emp.security.dao.SecurityDao;
import in.emp.security.dao.OracleSecurityDao;
import in.emp.security.SecurityDelegate;
import in.emp.security.bean.SecUserBean;
import in.emp.security.bean.SecFunctionBean;
import in.emp.security.bean.ServerAPIBean;
import in.emp.system.dao.OracleSecurityDaoSSO;
import in.emp.system.dao.SecurityDTO;
import in.emp.user.bean.UserBean;
import in.emp.util.ApplicationUtils;
import in.emp.common.ApplicationConstants;
import in.emp.ws.WSUtility;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import in.emp.security.ws.bean.SecurityOTPWSBean;
import java.text.MessageFormat;

/**
 * Manager for accessing and manipulating security related objects.
 *
 * @author MDA
 */
public class SecurityManager implements SecurityDelegate {

   private static String CLASS_NAME = SecurityManager.class.getName();
   //private static Logger logger = new Logger(CLASS_NAME);
   private static Logger logger = Logger.getLogger(SecurityManager.class);

   /* Public Constructor*/
   public SecurityManager() {
   } /* End Of Constructor*/

   /*
    * API to get the getUserDetails
    * @param secUserObject	the SecUserBean
    * @throws Exception		if an error occurs
    * @see				in.mda.security.SecurityDelegate
    * @returns			Object
    */

   

   public String getOfficeNameOther(SecurityDTO securityDTO) throws Exception {

      SecurityDao securityDaoObj = null;
      securityDaoObj = new OracleSecurityDaoSSO();

      return securityDaoObj.getOfficeNameOther(securityDTO);

   }

   public String getOfficeName(SecurityDTO securityDTO) throws Exception {

     SecurityDao securityDaoObj = null;
      securityDaoObj = new OracleSecurityDao();


      return securityDaoObj.getOfficeName(securityDTO);

   }

   public Object getUserDetails(SecUserBean secUserBeanObj) throws Exception {
      SecUserBean secUserObj = null;
      SecurityDao securityDaoObj = null;
      LinkedList functionList = null;
      SecFunctionBean functionObj = null;
      HashMap functionMap = null;
      try {
         logger.log(Level.INFO, " SecurityManager :: getUserDetails() :: method called");

         /* create dao object to get login results*/
         securityDaoObj = new OracleSecurityDao();

         // call dao method
         //System.out.println("Security Manager :: getQueryResults :: calling");
         secUserObj = (SecUserBean) securityDaoObj.getUserDetails(secUserBeanObj);

//         if (secUserObj != null) {
//            LinkedList accessLocationList = null;
//
//            accessLocationList = (LinkedList) securityDaoObj.getUserAccess(secUserObj);
//
//            secUserObj.setAccessLocationList(accessLocationList);
//
//            String accessLocationListStr = ApplicationUtils.getAccessLocationListStr(accessLocationList);
//
//            secUserObj.setAccessLocationString(accessLocationListStr);
//            accessLocationList = (LinkedList) securityDaoObj.getUserAccessList(secUserObj);
//            accessLocationListStr = ApplicationUtils.getAccessLocationListStr(accessLocationList);
//            secUserObj.setAccessLocationListString(accessLocationListStr);
//
//            functionList = (LinkedList) securityDaoObj.getSelectedFunctionList(secUserObj);
//
//            Iterator itrFunction = null;
//
//            if (functionList != null && functionList.size() > 0) {
//               functionMap = new HashMap();
//               itrFunction = functionList.iterator();
//               while (itrFunction.hasNext()) {
//                  functionObj = null;
//                  functionObj = (SecFunctionBean) itrFunction.next();
//                  if (functionObj != null) {
//                     functionMap.put(functionObj.getUiActionName(), functionObj.getFunctionId());
//                     //System.out.println("FunctionName:"+functionObj.getUiActionName());
//                  }
//               }//while ends
//               //System.out.println("--------------------------------");
//            }
//
//            secUserObj.setUserFunctionMap(functionMap);
//         }
      } catch (Exception ex) {
         logger.log(Level.ERROR, " SecurityManager :: getUserDetails() :: Exception :: " + ex);
         throw ex;
      }

      return secUserObj;

   } /* End of Method */


   public Object getServerAPIDetails(ServerAPIBean serverAPIBeanObj) throws Exception {

      SecurityDao securityDaoObj = null;
      LinkedList serverAPIList = null;

      try {
         logger.log(Level.INFO, " SecurityManager :: getServerAPIDetails() :: method called");

         /* create dao object to get login results*/
         securityDaoObj = new OracleSecurityDao();

         // call dao method
         //System.out.println("Security Manager :: getQueryResults :: calling");
         serverAPIList = securityDaoObj.getServerAPIDetails(serverAPIBeanObj);

         serverAPIBeanObj.setServerAPIList(serverAPIList);

      } catch (Exception ex) {
         logger.log(Level.ERROR, " SecurityManager :: getServerAPIDetails() :: Exception :: " + ex);
         throw ex;
      }

      return serverAPIBeanObj;

   } /* End of Method */


   public UserBean postPasswordChange(UserBean userBeanObj) throws Exception {
      SecurityDao securityDaoObj = null;
      try {
         logger.log(Level.INFO, " SecurityManager :: postPasswordChange() :: method called");
         /* create dao object to get login results*/
         securityDaoObj = new OracleSecurityDao();
         // call dao method
         //System.out.println("Security Manager :: getQueryResults :: calling");
         securityDaoObj.postPasswordChange(userBeanObj);
      } catch (Exception ex) {
         logger.log(Level.ERROR, " SecurityManager :: postPasswordChange() :: Exception :: " + ex);
         throw ex;
      }
      return userBeanObj;
   } /* End of Method */

    public String createCpfPortalSession(String cpfNo) throws Exception {
         SecurityDao securityDaoObj = null;
      try {
         logger.log(Level.INFO, " SecurityManager :: createCpfPortalSession() :: method called");
         /* create dao object to get login results*/
         securityDaoObj = new OracleSecurityDao();
         // call dao method
         //System.out.println("Security Manager :: getQueryResults :: calling");
         securityDaoObj.createCpfPortalSession(cpfNo);
      } catch (Exception ex) {
         logger.log(Level.ERROR, " SecurityManager :: createCpfPortalSession() :: Exception :: " + ex);
         throw ex;
      }
      return "";
    }
//
    public Object getVendorDetails(SecUserBean secUserBeanObj) throws Exception {
      SecUserBean secUserObj = null;
      SecurityDao securityDaoObj = null;
      LinkedList functionList = null;
      SecFunctionBean functionObj = null;
      HashMap functionMap = null;
      try {
         logger.log(Level.INFO, " SecurityManager :: getUserDetails() :: method called");

         /* create dao object to get login results*/
         securityDaoObj = new OracleSecurityDao();

         // call dao method
         //System.out.println("Security Manager :: getQueryResults :: calling");
         secUserObj = (SecUserBean) securityDaoObj.getUserDetails(secUserBeanObj);

      } catch (Exception ex) {
         logger.log(Level.ERROR, " SecurityManager :: getUserDetails() :: Exception :: " + ex);
         throw ex;
      }

      return secUserObj;

   } /* End of Method */
    
}//-- End class

