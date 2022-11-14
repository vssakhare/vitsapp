/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.bean;

import in.emp.common.FileBean;

/**
 *
 * @author Prajakta
 */
public class CorrectionFileBean extends FileBean implements java.io.Serializable {

    private String Id = "";
    private String ApplicationId = "";
    private int SrNo = 0;
    private String Option = "";
    private String EmpNumber = "";
    private String Location = "";
    private String Remark = "";

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(String ApplicationId) {
        this.ApplicationId = ApplicationId;
    }

    public int getSrNo() {
        return SrNo;
    }

    public void setSrNo(int SrNo) {
        this.SrNo = SrNo;
    }

    public String getOption() {
        return Option;
    }

    public void setOption(String Option) {
        this.Option = Option;
    }

    public String getEmpNumber() {
        return EmpNumber;
    }

    public void setEmpNumber(String EmpNumber) {
        this.EmpNumber = EmpNumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
}