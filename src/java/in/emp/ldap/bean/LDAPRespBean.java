/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.ldap.bean;

/**
 *
 * @author Prajakta
 */
public class LDAPRespBean implements java.io.Serializable {

    private String HRMS_Resp_PSU = "N";
    private String HRMS_Resp_DHU = "N";
    private String EmpNumber;

    public String getEmpNumber() {
        return EmpNumber;
    }

    public void setEmpNumber(String EmpNumber) {
        this.EmpNumber = EmpNumber;
    }

    public String getHRMS_Resp_DHU() {
        return HRMS_Resp_DHU;
    }

    public void setHRMS_Resp_DHU(String HRMS_Resp_DHU) {
        this.HRMS_Resp_DHU = HRMS_Resp_DHU;
    }

    public String getHRMS_Resp_PSU() {
        return HRMS_Resp_PSU;
    }

    public void setHRMS_Resp_PSU(String HRMS_Resp_PSU) {
        this.HRMS_Resp_PSU = HRMS_Resp_PSU;
    }
}
