/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.bean;

/**
 *
 * @author Prajakta
 */
public class HRMSRespBean implements java.io.Serializable {

    private String hrms_Resp_PSU = "N";
    private String hrms_Resp_HMC = "N";
    private String hrms_Resp_HM = "N";
    private String empNumber;
    private String hrms_Resp_String = "";

    public String getHrms_Resp_PSU() {
        return hrms_Resp_PSU;
    }

    public void setHrms_Resp_PSU(String hrms_Resp_PSU) {
        this.hrms_Resp_PSU = hrms_Resp_PSU;
    }

    public String getHrms_Resp_HMC() {
        return hrms_Resp_HMC;
    }

    public void setHrms_Resp_HMC(String hrms_Resp_HMC) {
        this.hrms_Resp_HMC = hrms_Resp_HMC;
    }

    public String getHrms_Resp_HM() {
        return hrms_Resp_HM;
    }

    public void setHrms_Resp_HM(String hrms_Resp_HM) {
        this.hrms_Resp_HM = hrms_Resp_HM;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getHrms_Resp_String() {
        return hrms_Resp_String;
    }

    public void setHrms_Resp_String(String hrms_Resp_String) {
        this.hrms_Resp_String = hrms_Resp_String;
    }
}