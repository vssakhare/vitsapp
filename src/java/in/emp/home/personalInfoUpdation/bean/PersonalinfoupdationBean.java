/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfoUpdation.bean;

import in.emp.common.ImageBean;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
// java imports
public class PersonalinfoupdationBean implements java.io.Serializable {
    /* bean to collect Data from RULE table	*/

    private String EmpNumber;
    private String PersonId;
    private String EmpName;
    private String Designation;
    private String Location;
    private String Gender;  
    private PersonalinfoupdationaddressBean TempAddress;
    private String MobileNo;
    private LinkedList MobileNoList;
    private String PEmail;    
    private String TempAddrFlag;
    private String PEmailFlag;
    private String PMobFlag;
    private String PIUpdateMsg;
    private String PMStatusFlag;
    private String PBloodGroupFlag;
    private String PMStatus;
    private String PBloodGroup;
    

    public String getEmpNumber() {
        return EmpNumber;
    }

    public void setEmpNumber(String EmpNumber) {
        this.EmpNumber = EmpNumber;
    }

    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String PersonId) {
        this.PersonId = PersonId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
 
    
    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public PersonalinfoupdationaddressBean getTempAddress() {
        return TempAddress;
    }

    public void setTempAddress(PersonalinfoupdationaddressBean TempAddress) {
        this.TempAddress = TempAddress;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    public LinkedList getMobileNoList() {
        return MobileNoList;
    }

    public void setMobileNoList(LinkedList MobileNoList) {
        this.MobileNoList = MobileNoList;
    }

    public String getPEmail() {
        return PEmail;
    }

    public void setPEmail(String PEmail) {
        this.PEmail = PEmail;
    }

    public String getTempAddrFlag() {
        return TempAddrFlag;
    }

    public void setTempAddrFlag(String TempAddrFlag) {
        this.TempAddrFlag = TempAddrFlag;
    }

    public String getPEmailFlag() {
        return PEmailFlag;
    }

    public void setPEmailFlag(String PEmailFlag) {
        this.PEmailFlag = PEmailFlag;
    }

    public String getPMobFlag() {
        return PMobFlag;
    }

    public void setPMobFlag(String PMobFlag) {
        this.PMobFlag = PMobFlag;
    }

    public String getPIUpdateMsg() {
        return PIUpdateMsg;
    }

    public void setPIUpdateMsg(String PIUpdateMsg) {
        this.PIUpdateMsg = PIUpdateMsg;
    }

    public String getPMStatusFlag() {
        return PMStatusFlag;
    }

    public void setPMStatusFlag(String PMStatusFlag) {
        this.PMStatusFlag = PMStatusFlag;
    }

    public String getPBloodGroupFlag() {
        return PBloodGroupFlag;
    }

    public void setPBloodGroupFlag(String PBloodGroupFlag) {
        this.PBloodGroupFlag = PBloodGroupFlag;
    }

    public String getPMStatus() {
        return PMStatus;
    }

    public void setPMStatus(String PMStatus) {
        this.PMStatus = PMStatus;
    }

    public String getPBloodGroup() {
        return PBloodGroup;
    }

    public void setPBloodGroup(String PBloodGroup) {
        this.PBloodGroup = PBloodGroup;
    }
 
   
}
