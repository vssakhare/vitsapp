/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.dao;

import in.emp.hrms.dao.helper.GetHRMSRespQueryHelper;
import in.emp.dao.BaseDao;
import in.emp.hrms.bean.HRMSRespBean;
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.hrms.bean.HRMSUserPrezData;
import in.emp.hrms.dao.helper.GetHRMSLoginQueryHelper;
import in.emp.hrms.dao.helper.GetHRMSMobDetailsQueryHelper;
import in.emp.hrms.dao.helper.GetHRMSUserDetailsQueryHelper;
import in.emp.hrms.dao.helper.VendorLoginTxnHelper;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class HRMSDaoImpl extends BaseDao implements HRMSDao {

    private static String CLASS_NAME = HRMSDaoImpl.class.getName();
    private static Logger logger = Logger.getLogger(HRMSDaoImpl.class);

    /* Public Constructor*/
    public HRMSDaoImpl() {
    } /* End Of Constructor*/
    
    public HRMSUserBean getLogin(HRMSUserBean hrmsUserBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " HRMSDaoImpl :: getLogin() :: method called");
            logger.log(Level.INFO, "Inside getLogin in HRMSDaoImpl");
            logger.log(Level.INFO, "calling GetHRMSLoginQueryHelper");

            hrmsUserBeanObj = (HRMSUserBean) getDataObject(new GetHRMSLoginQueryHelper(hrmsUserBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, " HRMSDaoImpl :: getLogin() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserBeanObj;
    }
    public HRMSUserBean getMobileDetails(HRMSUserBean hrmsUserBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " HRMSDaoImpl :: getMobileDetails() :: method called");

            hrmsUserBeanObj = (HRMSUserBean) getDataObject(new GetHRMSMobDetailsQueryHelper(hrmsUserBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, " HRMSDaoImpl :: getMobileDetails() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserBeanObj;
    }
    public HRMSUserPrezData getUserDetails(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception {
        try {
            logger.log(Level.INFO, "HRMSDaoImpl ::: getUserDetails() :: method called ::    ");

            hrmsUserPrezDataObj = (HRMSUserPrezData) getDataObject(new GetHRMSUserDetailsQueryHelper(hrmsUserPrezDataObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "HRMSDaoImpl ::: getUserDetails() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserPrezDataObj;
    }
    

    public HRMSRespBean getHRMSRespDetails(HRMSRespBean hrmsRespBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "HRMSDaoImpl ::: getHRMSRespDetails() :: method called ::    ");

            hrmsRespBeanObj = (HRMSRespBean) getDataObject(new GetHRMSRespQueryHelper(hrmsRespBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "HRMSDaoImpl ::: getHRMSRespDetails() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsRespBeanObj;
    }
    
     public HRMSUserPrezData postVendorLogin(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception {
        try {
            logger.log(Level.INFO, "HRMSDaoImpl ::: postVendorLogin() :: method called ::    ");
            
            hrmsUserPrezDataObj = (HRMSUserPrezData) createObject(new VendorLoginTxnHelper(hrmsUserPrezDataObj)); 

        } catch (Exception ex) {
            logger.log(Level.ERROR, "HRMSDaoImpl ::: postVendorLogin() :: Exception :: " + ex);
            throw ex;
        }
        return hrmsUserPrezDataObj;
    }
   public void resetPassword (HRMSUserBean hrmsuserBeanObj)throws Exception {
           try {
            logger.log(Level.INFO, "OracleVendorDao ::: resetPassword() :: method called ::");
            removeObject(new VendorLoginTxnHelper(hrmsuserBeanObj)); 
          }
          catch (Exception ex) {
            logger.log(Level.ERROR, "OracleVendorDao ::: resetPassword() :: Exception :: " + ex);
            throw ex;
        }
      }
}
