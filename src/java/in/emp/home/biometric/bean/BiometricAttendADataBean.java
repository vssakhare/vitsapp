/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.biometric.bean;

import java.sql.Date;

/**
 *
 * @author Prajakta
 */
public class BiometricAttendADataBean implements java.io.Serializable {

    private String EmpNumber;
    private Date Date;
    private String InTime;
    private String OutTime;
    private String Status;
    private String Remark;
    private String SaveFlag;

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getEmpNumber() {
        return EmpNumber;
    }

    public void setEmpNumber(String EmpNumber) {
        this.EmpNumber = EmpNumber;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String InTime) {
        this.InTime = InTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String OutTime) {
        this.OutTime = OutTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getSaveFlag() {
        return SaveFlag;
    }

    public void setSaveFlag(String SaveFlag) {
        this.SaveFlag = SaveFlag;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
