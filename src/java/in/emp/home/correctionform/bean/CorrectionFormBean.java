/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.bean;

import java.util.Date;

/**
 *
 * @author Prajakta
 */
public class CorrectionFormBean implements java.io.Serializable {

    private String personId = "";
    private String empNumber = "";
    private String applicationId = "";
    private Date createdDate;
    private String applicationStatus = "";
    private String correctionType = "";
    private String systemValue = "";
    private String correctionValue = "";
    private String location = "";
    private String remark = "";
    private String empName = "";

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getCorrectionType() {
        return correctionType;
    }

    public void setCorrectionType(String correctionType) {
        this.correctionType = correctionType;
    }

    public String getSystemValue() {
        return systemValue;
    }

    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }

    public String getCorrectionValue() {
        return correctionValue;
    }

    public void setCorrectionValue(String correctionValue) {
        this.correctionValue = correctionValue;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}