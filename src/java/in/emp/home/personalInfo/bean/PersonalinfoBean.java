/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.bean;

import in.emp.common.ImageBean;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author rushi
 */
public class PersonalinfoBean extends ImageBean implements java.io.Serializable {

    private String EmpNumber;
    private String PersonId;
    private String EmpName;
    private String Designation;
    private String Gender;
    private Date DateofBirth;
    private Date DateofJoining;
    private Date DateofRetirement;
    private String Location;
    private String ZoneName;
    private String CircleName;
    private String DivisionName;
    private String SubDivisionName;
    private String EmpStatus;
    private PersonalinfoaddressBean PermAddress;
    private PersonalinfoaddressBean TempAddress;
    private String HomeTown;
    private String Qualifications;
    private String PhoneNo;
    private String PhoneNo1;
    private String PhoneNo2;
    private String HomeNo;
    private LinkedList HomePhoneList = new LinkedList();
    private String AltNo;
    private String MobileNo;
    private LinkedList MobileNoList;
    //private String MobileNo2;
    private String PEmail;
    private String OEmail;
    private String BloodGroup;
    private String MarStatus;
    private String CCategory;
    private String SCaste;
    private PersonalinfocvcBean CValidDetails;
    private String PFNominee;
    private LinkedList CpfNomList = new LinkedList();
    private String GratNominee;
    private LinkedList GradNomList = new LinkedList();
    private int AgeYY;
    private String Cadre;
    private String Paygroup;
    private int ServiceYY;
    private int ServiceMM;
    private String TempAddr;
    private String PerAddr;
    private String CasteVerDet;
    private String PMobileNo;
    private String panNo;
    private String aadharNo;

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

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public Date getDateofBirth() {
        return DateofBirth;
    }

    public void setDateofBirth(Date DateofBirth) {
        this.DateofBirth = DateofBirth;
    }

    public Date getDateofJoining() {
        return DateofJoining;
    }

    public void setDateofJoining(Date DateofJoining) {
        this.DateofJoining = DateofJoining;
    }

    public Date getDateofRetirement() {
        return DateofRetirement;
    }

    public void setDateofRetirement(Date DateofRetirement) {
        this.DateofRetirement = DateofRetirement;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getZoneName() {
        return ZoneName;
    }

    public void setZoneName(String ZoneName) {
        this.ZoneName = ZoneName;
    }

    public String getCircleName() {
        return CircleName;
    }

    public void setCircleName(String CircleName) {
        this.CircleName = CircleName;
    }

    public String getDivisionName() {
        return DivisionName;
    }

    public void setDivisionName(String DivisionName) {
        this.DivisionName = DivisionName;
    }

    public String getSubDivisionName() {
        return SubDivisionName;
    }

    public void setSubDivisionName(String SubDivisionName) {
        this.SubDivisionName = SubDivisionName;
    }

    public String getEmpStatus() {
        return EmpStatus;
    }

    public void setEmpStatus(String EmpStatus) {
        this.EmpStatus = EmpStatus;
    }

    public PersonalinfoaddressBean getPermAddress() {
        return PermAddress;
    }

    public void setPermAddress(PersonalinfoaddressBean PermAddress) {
        this.PermAddress = PermAddress;
    }

    public PersonalinfoaddressBean getTempAddress() {
        return TempAddress;
    }

    public void setTempAddress(PersonalinfoaddressBean TempAddress) {
        this.TempAddress = TempAddress;
    }

    public String getHomeTown() {
        return HomeTown;
    }

    public void setHomeTown(String HomeTown) {
        this.HomeTown = HomeTown;
    }

    public String getQualifications() {
        return Qualifications;
    }

    public void setQualifications(String Qualifications) {
        this.Qualifications = Qualifications;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    public String getPhoneNo1() {
        return PhoneNo1;
    }

    public void setPhoneNo1(String PhoneNo1) {
        this.PhoneNo1 = PhoneNo1;
    }

    public String getPhoneNo2() {
        return PhoneNo2;
    }

    public void setPhoneNo2(String PhoneNo2) {
        this.PhoneNo2 = PhoneNo2;
    }

    public String getHomeNo() {
        return HomeNo;
    }

    public void setHomeNo(String HomeNo) {
        this.HomeNo = HomeNo;
    }

    public LinkedList getHomePhoneList() {
        return HomePhoneList;
    }

    public void setHomePhoneList(LinkedList HomePhoneList) {
        this.HomePhoneList = HomePhoneList;
    }

    public String getAltNo() {
        return AltNo;
    }

    public void setAltNo(String AltNo) {
        this.AltNo = AltNo;
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

    public String getOEmail() {
        return OEmail;
    }

    public void setOEmail(String OEmail) {
        this.OEmail = OEmail;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String BloodGroup) {
        this.BloodGroup = BloodGroup;
    }

    public String getMarStatus() {
        return MarStatus;
    }

    public void setMarStatus(String MarStatus) {
        this.MarStatus = MarStatus;
    }

    public String getCCategory() {
        return CCategory;
    }

    public void setCCategory(String CCategory) {
        this.CCategory = CCategory;
    }

    public String getSCaste() {
        return SCaste;
    }

    public void setSCaste(String SCaste) {
        this.SCaste = SCaste;
    }

    public PersonalinfocvcBean getCValidDetails() {
        return CValidDetails;
    }

    public void setCValidDetails(PersonalinfocvcBean CValidDetails) {
        this.CValidDetails = CValidDetails;
    }

    public String getPFNominee() {
        return PFNominee;
    }

    public void setPFNominee(String PFNominee) {
        this.PFNominee = PFNominee;
    }

    public LinkedList getCpfNomList() {
        return CpfNomList;
    }

    public void setCpfNomList(LinkedList CpfNomList) {
        this.CpfNomList = CpfNomList;
    }

    public String getGratNominee() {
        return GratNominee;
    }

    public void setGratNominee(String GratNominee) {
        this.GratNominee = GratNominee;
    }

    public LinkedList getGradNomList() {
        return GradNomList;
    }

    public void setGradNomList(LinkedList GradNomList) {
        this.GradNomList = GradNomList;
    }

    public int getAgeYY() {
        return AgeYY;
    }

    public void setAgeYY(int AgeYY) {
        this.AgeYY = AgeYY;
    }

    public String getCadre() {
        return Cadre;
    }

    public void setCadre(String Cadre) {
        this.Cadre = Cadre;
    }

    public String getPaygroup() {
        return Paygroup;
    }

    public void setPaygroup(String Paygroup) {
        this.Paygroup = Paygroup;
    }

    public int getServiceYY() {
        return ServiceYY;
    }

    public void setServiceYY(int ServiceYY) {
        this.ServiceYY = ServiceYY;
    }

    public int getServiceMM() {
        return ServiceMM;
    }

    public void setServiceMM(int ServiceMM) {
        this.ServiceMM = ServiceMM;
    }

    public String getTempAddr() {
        return TempAddr;
    }

    public void setTempAddr(String TempAddr) {
        this.TempAddr = TempAddr;
    }

    public String getPerAddr() {
        return PerAddr;
    }

    public void setPerAddr(String PerAddr) {
        this.PerAddr = PerAddr;
    }

    public String getCasteVerDet() {
        return CasteVerDet;
    }

    public void setCasteVerDet(String CasteVerDet) {
        this.CasteVerDet = CasteVerDet;
    }

    public String getPMobileNo() {
        return PMobileNo;
    }

    public void setPMobileNo(String PMobileNo) {
        this.PMobileNo = PMobileNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }
}