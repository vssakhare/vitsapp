/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms;

import in.emp.hrms.bean.HRMSUserBean;
import in.emp.hrms.bean.HRMSUserPrezData;

/**
 *
 * @author Prajakta
 */
public interface HRMSDelegate {

    public HRMSUserPrezData getLogin(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception;
    
    public void resetPassword(HRMSUserBean hrmsuserBeanObj) throws Exception;
    public HRMSUserPrezData getMobileDetails(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception;
    
    public HRMSUserPrezData postVendorLogin(HRMSUserPrezData hrmsUserPrezDataObj) throws Exception;
    
    
      
}
