/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.dao;

import in.emp.hrms.bean.HRMSRespBean;
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.hrms.bean.HRMSUserPrezData;

/**
 *
 * @author Prajakta
 */
public interface HRMSDao {
        
    public void resetPassword(HRMSUserBean hrmsuserBeanObj) throws Exception;
    public HRMSUserBean getLogin(HRMSUserBean hrmsUserBeanObj) throws Exception;
    public HRMSUserBean getMobileDetails(HRMSUserBean hrmsUserBeanObj) throws Exception;
    public HRMSUserPrezData getUserDetails(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception;
    
    public HRMSRespBean getHRMSRespDetails(HRMSRespBean hrmsRespBeanObj) throws Exception;   
    
    public HRMSUserPrezData postVendorLogin(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception;
    
    
  
}
