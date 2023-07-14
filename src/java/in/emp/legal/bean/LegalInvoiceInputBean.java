/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.bean;

import java.util.Date;

/**
 *
 * @author Rikma Rai
 */
public class LegalInvoiceInputBean extends LegalSummaryBean implements java.io.Serializable{
    	private Integer applId  ;
	private String vendorNumber ;  
	private String vendorName   ;
	private String courtCaseNo  ; 
	private String caseRefNo ;  
	private String courtName   ;
	private String caseDescription;
        private String caseTypeDesc;
	private String dealingOfficeCode   ;
	private String dealingOfficeName;   
	private String partyNames  ;
        private String vsPartyNames  ;
	private String invoiceNumber ;  
	private Date invoiceDate ; 
	private Integer invoiceAmount ; 
	private Date vendorInvDate ; 
	private String msedclInwardNo  ; 
	private Date msedclInwardDate ; 
	private Date invSubmitDate ; 
	private String createdById ;  
	private String createdByDesignation ;  
	private String createdByName;   
	private String createdByUsertype  ; 
	private String saveFlag  ; 
	private Date createdTimeStamp  ; 
	private Date updatedTimeStamp  ; 
        private String status;
        private Date vendorInwardDate;
        private String approveRejectFlag;
        private Date invoiceApprDAte;
private Date invoiceRejDate;
private String rejectReason;
private String whereClause;
private String auhtorityNumber;
private String locationId;
private String approvedBy;
private Date InvoiceFromDate;
    private Date InvoiceToDate;
    private String feeType;
    private String regionCode;
    private String regionText;
    private String zoneCode;
     private String zoneText;
     private String circleCode;
     private String circleText;
     private String divisionCode;
     private String divisionText;
     private String subDivisionCode;
     private String subDivisionText;
private String liabilityDocNo ;
    private String liabilityDocDate ;
    private String liabilityDocAmt ;
    
    private String taxAmount ;
    private String paidAmount ;
    private String paymentDate ;
    private String UTR_NO ;
    
    private String paymentDocNo ;
    private String paymentDocAmount ;
    private String paymentDocDate ;
    private String paymentStatus ;
    private String mobileNo ;
    private String emailId ; 
    
    private String isWithCourtCaseNo;
    private String corporateOffice;
    
    private String sectionCode;
    private String sectionText;
    private String subStationCode;
    private String subStationText;
    
    private String statusFee;
    private String parkPostDocNo;
    private String payDoneErpDoc ;
    private String startPostDocNo;
    private String startPayDoneErpDoc;
    private String startPayDoneErpDoc1;
    private String deptCode;
    private String deptName;
    private String sFeeType;
    private Integer sAmount;
    private String cashSmsEmailSent;
    private String paySmsEmailSent;
    private String payAdjSmsEmailSent;
    private String payDocSmsEmailSent;
    private String fiscalYear;
    private String tdsAmount;
    private String cgstAmount;
    private String sgstAmount;
    private String igstAmount;
    private String cgstTdsAmount;
    private String sgstTdsAmount;
    private String igstTdsAmount;
    private String DeductionAmount;
    private String reasonForDeduction  ;    
    private String  userType;
    private String accSmsEmailSent;
    private String technicalUpdated;
    private String businessCategory;
    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }
    
    public String getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(String tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public String getCgstAmount() {
        return cgstAmount;
    }

    public void setCgstAmount(String cgstAmount) {
        this.cgstAmount = cgstAmount;
    }

    public String getSgstAmount() {
        return sgstAmount;
    }

    public void setSgstAmount(String sgstAmount) {
        this.sgstAmount = sgstAmount;
    }

    public String getIgstAmount() {
        return igstAmount;
    }

    public void setIgstAmount(String igstAmount) {
        this.igstAmount = igstAmount;
    }

    public String getCgstTdsAmount() {
        return cgstTdsAmount;
    }

    public void setCgstTdsAmount(String cgstTdsAmount) {
        this.cgstTdsAmount = cgstTdsAmount;
    }

    public String getSgstTdsAmount() {
        return sgstTdsAmount;
    }

    public void setSgstTdsAmount(String sgstTdsAmount) {
        this.sgstTdsAmount = sgstTdsAmount;
    }

    public String getIgstTdsAmount() {
        return igstTdsAmount;
    }

    public void setIgstTdsAmount(String igstTdsAmount) {
        this.igstTdsAmount = igstTdsAmount;
    }

   
    public String getTechnicalUpdated() {
        return technicalUpdated;
    }

    public void setTechnicalUpdated(String technicalUpdated) {
        this.technicalUpdated = technicalUpdated;
    }
    
    public String getAccSmsEmailSent() {
        return accSmsEmailSent;
    }

    public void setAccSmsEmailSent(String accSmsEmailSent) {
        this.accSmsEmailSent = accSmsEmailSent;
    }
  
    
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
 

    public String getDeductionAmount() {
        return DeductionAmount;
    }

    public void setDeductionAmount(String DeductionAmount) {
        this.DeductionAmount = DeductionAmount;
    }

    public String getReasonForDeduction() {
        return reasonForDeduction;
    }

    public void setReasonForDeduction(String reasonForDeduction) {
        this.reasonForDeduction = reasonForDeduction;
    }
  
    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }
    

    public String getCashSmsEmailSent() {
        return cashSmsEmailSent;
    }

    public void setCashSmsEmailSent(String cashSmsEmailSent) {
        this.cashSmsEmailSent = cashSmsEmailSent;
    }

    public String getPaySmsEmailSent() {
        return paySmsEmailSent;
    }

    public void setPaySmsEmailSent(String paySmsEmailSent) {
        this.paySmsEmailSent = paySmsEmailSent;
    }

    public String getPayAdjSmsEmailSent() {
        return payAdjSmsEmailSent;
    }

    public void setPayAdjSmsEmailSent(String payAdjSmsEmailSent) {
        this.payAdjSmsEmailSent = payAdjSmsEmailSent;
    }

    public String getPayDocSmsEmailSent() {
        return payDocSmsEmailSent;
    }

    public void setPayDocSmsEmailSent(String payDocSmsEmailSent) {
        this.payDocSmsEmailSent = payDocSmsEmailSent;
    }

    

    public Integer getsAmount() {
        return sAmount;
    }

    public void setsAmount(Integer sAmount) {
        this.sAmount = sAmount;
    }

    public String getsFeeType() {
        return sFeeType;
    }

    public void setsFeeType(String sFeeType) {
        this.sFeeType = sFeeType;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

   

   

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionText() {
        return sectionText;
    }

    public void setSectionText(String sectionText) {
        this.sectionText = sectionText;
    }

    public String getSubStationCode() {
        return subStationCode;
    }

    public void setSubStationCode(String subStationCode) {
        this.subStationCode = subStationCode;
    }

    public String getSubStationText() {
        return subStationText;
    }

    public void setSubStationText(String subStationText) {
        this.subStationText = subStationText;
    }
    
    

    public String getSubDivisionCode() {
        return subDivisionCode;
    }

    public void setSubDivisionCode(String subDivisionCode) {
        this.subDivisionCode = subDivisionCode;
    }

    public String getSubDivisionText() {
        return subDivisionText;
    }

    public void setSubDivisionText(String subDivisionText) {
        this.subDivisionText = subDivisionText;
    }

    public String getCorporateOffice() {
        return corporateOffice;
    }

    public void setCorporateOffice(String corporateOffice) {
        this.corporateOffice = corporateOffice;
    }
    

    public String getIsWithCourtCaseNo() {
        return isWithCourtCaseNo;
    }

    public void setIsWithCourtCaseNo(String isWithCourtCaseNo) {
        this.isWithCourtCaseNo = isWithCourtCaseNo;
    }

    public String getLiabilityDocNo() {
        return liabilityDocNo;
    }

    public void setLiabilityDocNo(String liabilityDocNo) {
        this.liabilityDocNo = liabilityDocNo;
    }

    public String getLiabilityDocDate() {
        return liabilityDocDate;
    }

    public void setLiabilityDocDate(String liabilityDocDate) {
        this.liabilityDocDate = liabilityDocDate;
    }

    public String getLiabilityDocAmt() {
        return liabilityDocAmt;
    }

    public void setLiabilityDocAmt(String liabilityDocAmt) {
        this.liabilityDocAmt = liabilityDocAmt;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getUTR_NO() {
        return UTR_NO;
    }

    public void setUTR_NO(String UTR_NO) {
        this.UTR_NO = UTR_NO;
    }

    public String getPaymentDocNo() {
        return paymentDocNo;
    }

    public void setPaymentDocNo(String paymentDocNo) {
        this.paymentDocNo = paymentDocNo;
    }

    public String getPaymentDocAmount() {
        return paymentDocAmount;
    }

    public void setPaymentDocAmount(String paymentDocAmount) {
        this.paymentDocAmount = paymentDocAmount;
    }

    public String getPaymentDocDate() {
        return paymentDocDate;
    }

    public void setPaymentDocDate(String paymentDocDate) {
        this.paymentDocDate = paymentDocDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    
    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionText() {
        return regionText;
    }

    public void setRegionText(String regionText) {
        this.regionText = regionText;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneText() {
        return zoneText;
    }

    public void setZoneText(String zoneText) {
        this.zoneText = zoneText;
    }

    public String getCircleCode() {
        return circleCode;
    }

    public void setCircleCode(String circleCode) {
        this.circleCode = circleCode;
    }

    public String getCircleText() {
        return circleText;
    }

    public void setCircleText(String circleText) {
        this.circleText = circleText;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getDivisionText() {
        return divisionText;
    }

    public void setDivisionText(String divisionText) {
        this.divisionText = divisionText;
    }

     
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
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

    public String getApproveRejectFlag() {
        return approveRejectFlag;
    }

    public void setApproveRejectFlag(String approveRejectFlag) {
        this.approveRejectFlag = approveRejectFlag;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getAuhtorityNumber() {
        return auhtorityNumber;
    }

    public void setAuhtorityNumber(String auhtorityNumber) {
        this.auhtorityNumber = auhtorityNumber;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public Date getInvoiceRejDate() {
        return invoiceRejDate;
    }

    public void setInvoiceRejDate(Date invoiceRejDate) {
        this.invoiceRejDate = invoiceRejDate;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }


    public Date getInvoiceApprDAte() {
        return invoiceApprDAte;
    }

    public void setInvoiceApprDAte(Date invoiceApprDAte) {
        this.invoiceApprDAte = invoiceApprDAte;
    }

    public Date getVendorInwardDate() {
        return vendorInwardDate;
    }

    public void setVendorInwardDate(Date vendorInwardDate) {
        this.vendorInwardDate = vendorInwardDate;
    }
        

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        

    public Integer getApplId() {
        return applId;
    }

    public void setApplId(Integer applId) {
        this.applId = applId;
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

    public String getCourtCaseNo() {
        return courtCaseNo;
    }

    public void setCourtCaseNo(String courtCaseNo) {
        this.courtCaseNo = courtCaseNo;
    }

    public String getCaseRefNo() {
        return caseRefNo;
    }

    public void setCaseRefNo(String caseRefNo) {
        this.caseRefNo = caseRefNo;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public String getCaseTypeDesc() {
        return caseTypeDesc;
    }

    public void setCaseTypeDesc(String caseTypeDesc) {
        this.caseTypeDesc = caseTypeDesc;
    }
    
    public String getDealingOfficeCode() {
        return dealingOfficeCode;
    }

    public void setDealingOfficeCode(String dealingOfficeCode) {
        this.dealingOfficeCode = dealingOfficeCode;
    }

    public String getDealingOfficeName() {
        return dealingOfficeName;
    }

    public void setDealingOfficeName(String dealingOfficeName) {
        this.dealingOfficeName = dealingOfficeName;
    }

    public String getPartyNames() {
        return partyNames;
    }

    public void setPartyNames(String partyNames) {
        this.partyNames = partyNames;
    }

    public String getVsPartyNames() {
        return vsPartyNames;
    }

    public void setVsPartyNames(String vsPartyNames) {
        this.vsPartyNames = vsPartyNames;
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

    public Integer getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Integer invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Date getVendorInvDate() {
        return vendorInvDate;
    }

    public void setVendorInvDate(Date vendorInvDate) {
        this.vendorInvDate = vendorInvDate;
    }

    public String getMsedclInwardNo() {
        return msedclInwardNo;
    }

    public void setMsedclInwardNo(String msedclInwardNo) {
        this.msedclInwardNo = msedclInwardNo;
    }

    public Date getMsedclInwardDate() {
        return msedclInwardDate;
    }

    public void setMsedclInwardDate(Date msedclInwardDate) {
        this.msedclInwardDate = msedclInwardDate;
    }

    public Date getInvSubmitDate() {
        return invSubmitDate;
    }

    public void setInvSubmitDate(Date invSubmitDate) {
        this.invSubmitDate = invSubmitDate;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByDesignation() {
        return createdByDesignation;
    }

    public void setCreatedByDesignation(String createdByDesignation) {
        this.createdByDesignation = createdByDesignation;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedByUsertype() {
        return createdByUsertype;
    }

    public void setCreatedByUsertype(String createdByUsertype) {
        this.createdByUsertype = createdByUsertype;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Date getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(Date updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
        
     public String getStatusFee() {
        return statusFee;
    }

    public void setStatusFee(String statusFee) {
        this.statusFee = statusFee;
    }    
    
    public String getParkPostDocNo() {
        return parkPostDocNo;
    }

    public void setParkPostDocNo(String parkPostDocNo) {
        this.parkPostDocNo = parkPostDocNo;
    }

    public String getPayDoneErpDoc() {
        return payDoneErpDoc;
    }

    public void setPayDoneErpDoc(String payDoneErpDoc) {
        this.payDoneErpDoc = payDoneErpDoc;
    }

    public String getStartPostDocNo() {
        return startPostDocNo;
    }

    public void setStartPostDocNo(String startPostDocNo) {
        this.startPostDocNo = startPostDocNo;
    }

    public String getStartPayDoneErpDoc() {
        return startPayDoneErpDoc;
    }

    public void setStartPayDoneErpDoc(String startPayDoneErpDoc) {
        this.startPayDoneErpDoc = startPayDoneErpDoc;
    }
    
     public String getStartPayDoneErpDoc1() {
        return startPayDoneErpDoc1;
    }

    public void setStartPayDoneErpDoc1(String startPayDoneErpDoc1) {
        this.startPayDoneErpDoc1 = startPayDoneErpDoc1;
    }

}
