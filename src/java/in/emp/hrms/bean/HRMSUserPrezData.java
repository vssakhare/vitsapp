/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.bean;

/**
 *
 * @author Prajakta
 */
public class HRMSUserPrezData implements java.io.Serializable {

    HRMSUserBean hrmsUserBeanObj = new HRMSUserBean();
    HRMSRespBean hrmsRespBeanObj = new HRMSRespBean();

    public HRMSUserBean getHrmsUserBeanObj() {
        return hrmsUserBeanObj;
    }

    public void setHrmsUserBeanObj(HRMSUserBean hrmsUserBeanObj) {
        this.hrmsUserBeanObj = hrmsUserBeanObj;
    }

    public HRMSRespBean getHrmsRespBeanObj() {
        return hrmsRespBeanObj;
    }

    public void setHrmsRespBeanObj(HRMSRespBean hrmsRespBeanObj) {
        this.hrmsRespBeanObj = hrmsRespBeanObj;
    }
}
