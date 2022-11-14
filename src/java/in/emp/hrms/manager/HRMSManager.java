/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.manager;

import in.emp.hrms.dao.HRMSDao;
import in.emp.hrms.HRMSDelegate;
import in.emp.hrms.bean.HRMSRespBean;
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.hrms.bean.HRMSUserPrezData;
import in.emp.hrms.dao.HRMSDaoImpl;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class HRMSManager implements HRMSDelegate {

    private static String CLASS_NAME = HRMSManager.class.getName();
    private static Logger logger = Logger.getLogger(HRMSManager.class);

    /* Public Constructor*/
    public HRMSManager() {
    } /* End Of Constructor*/


    public HRMSUserPrezData getLogin(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception {
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        HRMSRespBean hrmsRespBeanObj = new HRMSRespBean();
        HRMSDao hrmsDaoObj = new HRMSDaoImpl();
        String valLogin = "";
        try {
            logger.log(Level.INFO, " HRMSManager :: getLogin() :: method called");

            hrmsUserBeanObj = hrmsUserPrezDataObj.getHrmsUserBeanObj();
            
            hrmsUserBeanObj = hrmsDaoObj.getLogin(hrmsUserBeanObj);
            hrmsUserPrezDataObj.setHrmsUserBeanObj(hrmsUserBeanObj);
             valLogin = hrmsUserBeanObj.getValLogin();
            
             if(!hrmsUserBeanObj.getValLogin().equals("INVALID"))
                {
                  hrmsUserPrezDataObj = hrmsDaoObj.getUserDetails(hrmsUserPrezDataObj); 
                  hrmsUserPrezDataObj.getHrmsUserBeanObj().setValLogin(valLogin);
                     
                }
            
//       if (!ApplicationUtils.isBlank(hrmsUserBeanObj.getEmpNumber())) {
//                
    //           hrmsUserPrezDataObj = hrmsDaoObj.getUserDetails(hrmsUserPrezDataObj);
//                
//                hrmsRespBeanObj.setEmpNumber(hrmsUserBeanObj.getEmpNumber());
//                hrmsRespBeanObj = hrmsDaoObj.getHRMSRespDetails(hrmsRespBeanObj);
//                
//                hrmsUserPrezDataObj.setHrmsRespBeanObj(hrmsRespBeanObj);
//            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, " HRMSManager :: getLogin() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserPrezDataObj;
    }
    public HRMSUserPrezData getMobileDetails(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception {
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
 
        HRMSDao hrmsDaoObj = new HRMSDaoImpl();
        String valLogin = "";
        try {
            logger.log(Level.INFO, " HRMSManager :: getLogin() :: method called");

            hrmsUserBeanObj = hrmsUserPrezDataObj.getHrmsUserBeanObj();
            
            hrmsUserBeanObj = hrmsDaoObj.getMobileDetails(hrmsUserBeanObj);
           
} catch (Exception ex) {
            logger.log(Level.ERROR, " HRMSManager :: getLogin() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserPrezDataObj;
    }
    public HRMSUserPrezData postVendorLogin(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception {
        HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
        HRMSRespBean hrmsRespBeanObj = new HRMSRespBean();
        HRMSDao hrmsDaoObj = new HRMSDaoImpl();
        try {
            logger.log(Level.INFO, " HRMSManager :: getLogin() :: method called");

           // hrmsUserBeanObj = hrmsUserPrezDataObj.getHrmsUserBeanObj();
            
            hrmsUserPrezDataObj = hrmsDaoObj.postVendorLogin(hrmsUserPrezDataObj);
          //  hrmsUserPrezDataObj.setHrmsUserBeanObj(hrmsUserBeanObj);
            

        } catch (Exception ex) {
            logger.log(Level.ERROR, " HRMSManager :: getLogin() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserPrezDataObj;
    }
    
      public void  resetPassword(HRMSUserBean hrmsuserBeanObj){
      HRMSDao hrmsDaoObj = new HRMSDaoImpl();
          try {
            logger.log(Level.INFO, " VendorManager :: resetPassword() :: method called");
            hrmsDaoObj.resetPassword(hrmsuserBeanObj);
          } catch (Exception ex) {
            logger.log(Level.ERROR, " VendorManager :: resetPassword() :: Exception :: " + ex);

        }  
    }
}
