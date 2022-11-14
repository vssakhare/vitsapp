/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.security.ws.bean;

import in.emp.ws.SMSWSBean;

/**
 *
 * @author Sachin
 */
public class PassResetSMSWSBean extends SMSWSBean implements java.io.Serializable {
    private String templateid = "";
    private String F1 = "";
    private String F2 = "";

    public String getTemplateid() {
        return templateid;
    }

    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }

    public String getF1() {
        return F1;
    }

    public void setF1(String F1) {
        this.F1 = F1;
    }

    public String getF2() {
        return F2;
    }

    public void setF2(String F2) {
        this.F2 = F2;
    }
    
    
}
