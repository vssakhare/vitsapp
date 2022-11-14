/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.bean;

/**
 *
 * @author Prajakta
 */
public class HRMSUserBean {

    private String userName = "";
    private String empNumber = "";
    private String empName = "";
    private String officeType = "";
    private String officeName = "";
    private String locationId = "";
    private String department = "";
    private String designation = "";
    private String emailId = "";
    private String phoneNo = "";
    private String mobileNo = "";
    private String parentOrgId = "101";
    private String pass = "";
    private String personId = "";    
    
    private String valLogin = ""; 
    private String userType = ""; 

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String getValLogin() {
        return valLogin;
    }

    public void setValLogin(String valLogin) {
        this.valLogin = valLogin;
    }
    

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    private String location = "";
    // Legacy
    private String ROLENAME_SESSION = "Superadmin";
    private String GRANULAR_ACL_SESSION = "";
    private String BILLING_UNIT_SESSION = "0";
    private String BILLING_DB_CODE_SESSION = "000";
    private String PARENT_OFFICE_BILLING_DB_CODE_SESSION = "";
    private String OFFICE_LOCATION_ID_SESSION = "0";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getROLENAME_SESSION() {
        return ROLENAME_SESSION;
    }

    public void setROLENAME_SESSION(String ROLENAME_SESSION) {
        this.ROLENAME_SESSION = ROLENAME_SESSION;
    }

    public String getGRANULAR_ACL_SESSION() {
        return GRANULAR_ACL_SESSION;
    }

    public void setGRANULAR_ACL_SESSION(String GRANULAR_ACL_SESSION) {
        this.GRANULAR_ACL_SESSION = GRANULAR_ACL_SESSION;
    }

    public String getBILLING_UNIT_SESSION() {
        return BILLING_UNIT_SESSION;
    }

    public void setBILLING_UNIT_SESSION(String BILLING_UNIT_SESSION) {
        this.BILLING_UNIT_SESSION = BILLING_UNIT_SESSION;
    }

    public String getBILLING_DB_CODE_SESSION() {
        return BILLING_DB_CODE_SESSION;
    }

    public void setBILLING_DB_CODE_SESSION(String BILLING_DB_CODE_SESSION) {
        this.BILLING_DB_CODE_SESSION = BILLING_DB_CODE_SESSION;
    }

    public String getPARENT_OFFICE_BILLING_DB_CODE_SESSION() {
        return PARENT_OFFICE_BILLING_DB_CODE_SESSION;
    }

    public void setPARENT_OFFICE_BILLING_DB_CODE_SESSION(String PARENT_OFFICE_BILLING_DB_CODE_SESSION) {
        this.PARENT_OFFICE_BILLING_DB_CODE_SESSION = PARENT_OFFICE_BILLING_DB_CODE_SESSION;
    }

    public String getOFFICE_LOCATION_ID_SESSION() {
        return OFFICE_LOCATION_ID_SESSION;
    }

    public void setOFFICE_LOCATION_ID_SESSION(String OFFICE_LOCATION_ID_SESSION) {
        this.OFFICE_LOCATION_ID_SESSION = OFFICE_LOCATION_ID_SESSION;
    }
}
