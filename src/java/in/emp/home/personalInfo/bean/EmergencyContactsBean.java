/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.bean;

import java.util.Date;

/**
 *
 * @author Prajakta
 */
public class EmergencyContactsBean implements java.io.Serializable {

    private String id = "";
    private String personId = "";
    private String empNumber = "";
    private Date fromDate;
    private Date toDate;
    private String contact1FullName = "";
    private String contact1PhoneNum1 = "";
    private String contact1PhoneNum2 = "";
    private String contact2FullName = "";
    private String contact2PhoneNum1 = "";
    private String contact2PhoneNum2 = "";
    private String createdBy = "";
    private String location = "";
    private String status = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getContact1FullName() {
        return contact1FullName;
    }

    public void setContact1FullName(String contact1FullName) {
        this.contact1FullName = contact1FullName;
    }

    public String getContact1PhoneNum1() {
        return contact1PhoneNum1;
    }

    public void setContact1PhoneNum1(String contact1PhoneNum1) {
        this.contact1PhoneNum1 = contact1PhoneNum1;
    }

    public String getContact1PhoneNum2() {
        return contact1PhoneNum2;
    }

    public void setContact1PhoneNum2(String contact1PhoneNum2) {
        this.contact1PhoneNum2 = contact1PhoneNum2;
    }

    public String getContact2FullName() {
        return contact2FullName;
    }

    public void setContact2FullName(String contact2FullName) {
        this.contact2FullName = contact2FullName;
    }

    public String getContact2PhoneNum1() {
        return contact2PhoneNum1;
    }

    public void setContact2PhoneNum1(String contact2PhoneNum1) {
        this.contact2PhoneNum1 = contact2PhoneNum1;
    }

    public String getContact2PhoneNum2() {
        return contact2PhoneNum2;
    }

    public void setContact2PhoneNum2(String contact2PhoneNum2) {
        this.contact2PhoneNum2 = contact2PhoneNum2;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}