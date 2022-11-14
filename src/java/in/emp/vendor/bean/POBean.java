/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import in.emp.authority.bean.AuthorityBean;
import java.util.Date;

/**
 *
 * @author Prajakta
 */
public class POBean extends AuthorityBean implements java.io.Serializable {

   
    private String PONumber;

    private String ModuleType;
    private String PODesc;
    private String POType;
    private String MobileNumber;
    private String Plant;
    private String OfficeCode;
    private String ParentOfficeCode;
    private String OrgUnit;
    private String Vendor_Invoice_Number;
    private String Vendor_Invoice_Amount;
    private Date Vendor_Invoice_Date;
 private String Project_Id;
 private String Po_amt;
 private String bal_po_amt;
 private String Total_Inv_Amt;
private String Total_Inv_Cnt;
private String Total_Invoicable_Amt;
private String PURCHASING_GROUP;
private String SelectedModuleType;
private String FormStatus;
private String Emp_Po_YN;
    public String getEmp_Po_YN() {
        return Emp_Po_YN;
    }

    public void setEmp_Po_YN(String Emp_Po_YN) {
        this.Emp_Po_YN = Emp_Po_YN;
    }

    public String getModuleType() {
        return ModuleType;
    }

    public void setModuleType(String ModuleType) {
        this.ModuleType = ModuleType;
    }
    public String getFormStatus() {
        return FormStatus;
    }

    public void setFormStatus(String FormStatus) {
        this.FormStatus = FormStatus;
    }

    public String getSelectedModuleType() {
        return SelectedModuleType;
    }

    public void setSelectedModuleType(String SelectedModuleType) {
        this.SelectedModuleType = SelectedModuleType;
    }

    public String getPURCHASING_GROUP() {
        return PURCHASING_GROUP;
    }

    public void setPURCHASING_GROUP(String PURCHASING_GROUP) {
        this.PURCHASING_GROUP = PURCHASING_GROUP;
    }

    public String getTotal_Invoicable_Amt() {
        return Total_Invoicable_Amt;
    }

    public void setTotal_Invoicable_Amt(String Total_Invoicable_Amt) {
        this.Total_Invoicable_Amt = Total_Invoicable_Amt;
    }

    public String getTotal_Inv_Cnt() {
        return Total_Inv_Cnt;
    }

    public void setTotal_Inv_Cnt(String Total_Inv_Cnt) {
        this.Total_Inv_Cnt = Total_Inv_Cnt;
    }
 
    public String getTotal_Inv_Amt() {
        return Total_Inv_Amt;
    }

    public void setTotal_Inv_Amt(String Total_Inv_Amt) {
        this.Total_Inv_Amt = Total_Inv_Amt;
    }

    public String getPo_amt() {
        return Po_amt;
    }

    public void setPo_amt(String Po_amt) {
        this.Po_amt = Po_amt;
    }

    public String getBal_po_amt() {
        return bal_po_amt;
    }

    public void setBal_po_amt(String bal_po_amt) {
        this.bal_po_amt = bal_po_amt;
    }

    public String getProject_Id() {
        return Project_Id;
    }

    public void setProject_Id(String Project_Id) {
        this.Project_Id = Project_Id;
    }
   
private String PlantDesc;
    public String getVendor_Invoice_Amount() {
        return Vendor_Invoice_Amount;
    }

    public void setVendor_Invoice_Amount(String Vendor_Invoice_Amount) {
        this.Vendor_Invoice_Amount = Vendor_Invoice_Amount;
    }

    public Date getVendor_Invoice_Date() {
        return Vendor_Invoice_Date;
    }

    public void setVendor_Invoice_Date(Date Vendor_Invoice_Date) {
        this.Vendor_Invoice_Date = Vendor_Invoice_Date;
    }


    public String getVendor_Invoice_Number() {
        return Vendor_Invoice_Number;
    }

    public void setVendor_Invoice_Number(String Vendor_Invoice_Number) {
        this.Vendor_Invoice_Number = Vendor_Invoice_Number;
    }

    public String getSelectedModule() {
        return SelectedModule;
    }

    public void setSelectedModule(String SelectedModule) {
        this.SelectedModule = SelectedModule;
    }
private String SelectedModule;
    public String getPlantDesc() {
        return PlantDesc;
    }

    public void setPlantDesc(String PlantDesc) {
        this.PlantDesc = PlantDesc;
    }
    
   // private String vendorNumber;

//    public String getVendorNumber() {
//        return vendorNumber;
//    }
//
//    public void setVendorNumber(String vendorNumber) {
//        this.vendorNumber = vendorNumber;
//    }
//    
    public String getPONumber() {
        return PONumber;
    }

    public void setPONumber(String PONumber) {
        this.PONumber = PONumber;
    }
    public String getPlant() {
        return Plant;
    }

    public void setPlant(String Plant) {
        this.Plant = Plant;
    }
    public String getOfficeode() {
        return OfficeCode;
    }

    public void setOfficeCode(String OfficeCode) {
        this.OfficeCode = OfficeCode;
    }
    public String getParentOfficeode() {
        return ParentOfficeCode;
    }

    public void setParentOfficeCode(String ParentOfficeCode) {
        this.ParentOfficeCode = ParentOfficeCode;
    }
public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String MobileNumber) {
        this.MobileNumber = MobileNumber;
    }
     public String OrgUnit() {
        return OrgUnit;
    }

    public void setOrgUnit(String OrgUnit) {
        this.OrgUnit = OrgUnit;
    }
    public String getPODesc() {
        return PODesc;
    }

    public void setPODesc(String PODesc) {
        this.PODesc = PODesc;
    }

    public String getPOType() {
        return POType;
    }

    public void setPOType(String POType) {
        this.POType = POType;
    }    
   
    
}