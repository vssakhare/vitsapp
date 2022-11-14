/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority.bean;

import java.util.Date;

/**
 *
 * @author Prajakta
 */
public class AuthorityBean implements java.io.Serializable {

    private String vendorNumber;
    private String vendorName;
    private String PONumber;
    private String invoiceNumber;
    private Date invoiceDate;
    private String invoiceAmount;
    private String inwardNumber;
    private Date inwardDate;
    private String ApplId;
    private String saveFlag;
    private String AuhtorityNumber;
    private String LocationId;
    
    private String PONumberHdr;
    
    private String PODesc;
    private String POType;
    
    private String Zone;
    private String Circle;
    private String Division;
    
    private Date POCreationDate;
    private Date ValidityStart;
    private Date ValidityEnd;
    private Date InvoiceFromDate;
    private Date InvoiceToDate;
    
    private String subAction;
    
   private String VendorInvoiceNumber;
   
   private String MsedclInvoiceNumber;
   private String paidAmt;
   private String UserType;
   private String LocationCode;
   private String LocationName;
   private String LocationType;
   
   private String PlantCode;
   private String OrgUnit;
  
    public String getPlantCode() {
        return PlantCode;
    }

    public void setPlantCode(String PlantCode) {
        this.PlantCode = PlantCode;
    }

    public String getOrgUnit() {
        return OrgUnit;
    }

    public void setOrgUnit(String OrgUnit) {
        this.OrgUnit = OrgUnit;
    }
   
   
    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String LocationCode) {
        this.LocationCode = LocationCode;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String LocationName) {
        this.LocationName = LocationName;
    }

    public String getLocationType() {
        return LocationType;
    }

    public void setLocationType(String LocationType) {
        this.LocationType = LocationType;
    }
   
    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }
      
    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getPONumber() {
        return PONumber;
    }

    public void setPONumber(String PONumber) {
        this.PONumber = PONumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInwardNumber() {
        return inwardNumber;
    }

    public void setInwardNumber(String inwardNumber) {
        this.inwardNumber = inwardNumber;
    }

    public Date getInwardDate() {
        return inwardDate;
    }

    public void setInwardDate(Date inwardDate) {
        this.inwardDate = inwardDate;
    }

    public String getApplId() {
        return ApplId;
    }

    public void setApplId(String ApplId) {
        this.ApplId = ApplId;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getAuhtorityNumber() {
        return AuhtorityNumber;
    }

    public void setAuhtorityNumber(String AuhtorityNumber) {
        this.AuhtorityNumber = AuhtorityNumber;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String LocationId) {
        this.LocationId = LocationId;
    }

    public String getPONumberHdr() {
        return PONumberHdr;
    }

    public void setPONumberHdr(String PONumberHdr) {
        this.PONumberHdr = PONumberHdr;
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

    public String getZone() {
        return Zone;
    }

    public void setZone(String Zone) {
        this.Zone = Zone;
    }

    public String getCircle() {
        return Circle;
    }

    public void setCircle(String Circle) {
        this.Circle = Circle;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String Division) {
        this.Division = Division;
    }

    public Date getPOCreationDate() {
        return POCreationDate;
    }

    public void setPOCreationDate(Date POCreationDate) {
        this.POCreationDate = POCreationDate;
    }

    public Date getValidityStart() {
        return ValidityStart;
    }

    public void setValidityStart(Date ValidityStart) {
        this.ValidityStart = ValidityStart;
    }

    public Date getValidityEnd() {
        return ValidityEnd;
    }

    public void setValidityEnd(Date ValidityEnd) {
        this.ValidityEnd = ValidityEnd;
    }

    public Date getInvoiceFromDate() {
        return InvoiceFromDate;
    }

    public void setInvoiceFromDate(Date InvoiceFromDate) {
        this.InvoiceFromDate = InvoiceFromDate;
    }

    public Date getInvoiceToDate() {
        return InvoiceToDate;
    }

    public void setInvoiceToDate(Date InvoiceToDate) {
        this.InvoiceToDate = InvoiceToDate;
    }

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }

    public String getVendorInvoiceNumber() {
        return VendorInvoiceNumber;
    }

    public void setVendorInvoiceNumber(String VendorInvoiceNumber) {
        this.VendorInvoiceNumber = VendorInvoiceNumber;
    }

    public String getMsedclInvoiceNumber() {
        return MsedclInvoiceNumber;
    }

    public void setMsedclInvoiceNumber(String MsedclInvoiceNumber) {
        this.MsedclInvoiceNumber = MsedclInvoiceNumber;
    }

    public String getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(String paidAmt) {
        this.paidAmt = paidAmt;
    }
    

   
    
}