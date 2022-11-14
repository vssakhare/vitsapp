/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.Date;

/**
 *
 * @author Prajakta
 */
public class VendorInputBean implements java.io.Serializable {

    private String ApplId;
    private String vendorNumber;
    private String vendorName;
    private String PONumberHdr;
    private String PONumber;
    private String PODesc;
    private String POType;
    private String ContactNumber;
    private String Zone;
    private String Circle;
    private String Division;
    private String DaysDelayed;
    private Date POCreationDate;
    private Date ValidityStart;
    private Date ValidityEnd;
    private Date InvoiceFromDate;
    private Date InvoiceToDate;
    private Date ApplDate;
    private Date InvoiceUpdatedDate;

     private Date SormDate;
    private String VendorInvoiceNumber;
    private Date VendorInvoiceDate;
    private String InwardNumber;
    private Date InwardDate;

   
    private Date InvoiceREjDAte;
    
    private Date InvoiceApprDAte;
    private String VendorInvoiceAmount;
    private String MsedclInvoiceNumber;
    private String paidAmt;

    private String saveFlag;
    private String userType;
    private String LocationId;
    private String DispVendorNumber;
    private String DispVendorName;
    private String LocationOpt;
private String Reason = "";
private String Tax_Code;
private String Tax_Amount;
private String Office_Code;
private String Parent_Office_Code;
private String Designation;
private String smsFlag;
private String HigherAuthsmsFlag;
private String emp_Cpf;
private String emp_Name;
private String status;
private String SelectedModule;//use for escalation
private String Location;
private String Plant_desc;
private Date VendorInvoiceResubmit;
private String IsRejectedFlag;
private String IsApprovedFlag;
private String SesMigoInvNo;
private String InvResubmitCounter;
private String EmailId;
private String PendingSince;
private String SelectedModuleType;//use for selecting module to display po number or project id
private String ProjectId;
private String ProjectDesc;
private String WorkDetailDesc;
private String SubDivision;
private String ModuleSaveFlag;
private String TotalPoAmt;
private String BalPoAmt;
private Date msedclInwardDate;
private String  msedclInwardNumber;
private String sesMigoAmt;
private String invAmt;
private String invoiceStatus;
private String IT_TDS_AMOUNT;
private String GST_TDS;
private String RETENSION_AMOUNT;
private String INVOICE_TYPE;
private String SubmitAtPlant;
private String SubmitAtDesc;
private String PurchasingDesc;
private String SerialNo;
private String Technical_sms_flag;
private String Accounts_sms_flag;
private String Cashier_sms_flag;
private String Emp_tech_sms_flag;
private String Emp_Acc_sms_flag;
private String Emp_Cash_sms_flag;
private String Esc_tech_sms_flag;
private String Esc_Acc_sms_flag;
private String Esc_Cash_sms_flag;
private String Purchasing_group;
private String PoPurchasing_group;
private String MATERIAL_PO   ;
private String 	CENTAGES_PO  ;
private String 	SERVICE_PO   ;
private String  SELECTEDPLANT;
private String EMP_ID;
private String EMP_NAME;
private String EMP_DESIGNATION;
private String CREATED_USER_TYPE;
private String ForwardToPlant;
private String ForwardToDesc;

 private String   WTAX_AMT ; 
     private String   WIT_TDS  ; 
     private String   WGST_TDS ; 
     private String   WRPST    ; 
     private String   WRET_AMT ; 
     
     private String   OTAX_AMT ; 
     private String   OIT_TDS  ; 
     private String   OGST_TDS ; 
     private String   ORPST    ; 
     private String   ORET_AMT ; 
   
     private String   CTAX_AMT ; 
     private String   CIT_TDS  ; 
     private String   CGST_TDS ; 
     private String   CRPST    ; 
     private String   CRET_AMT ; 
     ;
     private String   STAX_AMT ; 
     private String   SIT_TDS  ; 
     private String   SGST_TDS ; 
     private String   SRPST    ; 
     private String   SRET_AMT ; 
    
     private String   OTTAX_AMT; 
     private String   OTIT_TDS ; 
     private String   OTGST_TDS; 
     private String   OTRPST   ; 
     private String   OTRET_AMT; 
     
     private String shortText;
     private String fiDocNo;
     private String fiAmount;
     private String newDocNo;
     private String newDocAmount;
     private String retentionDocNo;
     
     private String fullOrPartialRetention;

    public String getFullOrPartialRetention() {
        return fullOrPartialRetention;
    }

    public void setFullOrPartialRetention(String fullOrPartialRetention) {
        this.fullOrPartialRetention = fullOrPartialRetention;
    }
     
     

    public String getRetentionDocNo() {
        return retentionDocNo;
    }

    public void setRetentionDocNo(String retentionDocNo) {
        this.retentionDocNo = retentionDocNo;
    }
     
     

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFiDocNo() {
        return fiDocNo;
    }

    public void setFiDocNo(String fiDocNo) {
        this.fiDocNo = fiDocNo;
    }

    public String getFiAmount() {
        return fiAmount;
    }

    public void setFiAmount(String fiAmount) {
        this.fiAmount = fiAmount;
    }

    public String getNewDocNo() {
        return newDocNo;
    }

    public void setNewDocNo(String newDocNo) {
        this.newDocNo = newDocNo;
    }

    public String getNewDocAmount() {
        return newDocAmount;
    }

    public void setNewDocAmount(String newDocAmount) {
        this.newDocAmount = newDocAmount;
    }
     
     

    public Date getInvoiceUpdatedDate() {
        return InvoiceUpdatedDate;
    }

    public void setInvoiceUpdatedDate(Date InvoiceUpdatedDate) {
        this.InvoiceUpdatedDate = InvoiceUpdatedDate;
    }

    public String getTax_Code() {
        return Tax_Code;
    }

    public void setTax_Code(String Tax_Code) {
        this.Tax_Code = Tax_Code;
    }

    public String getTax_Amount() {
        return Tax_Amount;
    }

    public void setTax_Amount(String Tax_Amount) {
        this.Tax_Amount = Tax_Amount;
    }

    public String getSmsFlag() {
        return smsFlag;
    }

    public void setSmsFlag(String smsFlag) {
        this.smsFlag = smsFlag;
    }

    public String getEmp_Cpf() {
        return emp_Cpf;
    }

    public void setEmp_Cpf(String emp_Cpf) {
        this.emp_Cpf = emp_Cpf;
    }

    public String getEmp_Name() {
        return emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        this.emp_Name = emp_Name;
    }

    public String getWTAX_AMT() {
        return WTAX_AMT;
    }

    public void setWTAX_AMT(String WTAX_AMT) {
        this.WTAX_AMT = WTAX_AMT;
    }

    public String getWIT_TDS() {
        return WIT_TDS;
    }

    public void setWIT_TDS(String WIT_TDS) {
        this.WIT_TDS = WIT_TDS;
    }

    public String getWGST_TDS() {
        return WGST_TDS;
    }

    public void setWGST_TDS(String WGST_TDS) {
        this.WGST_TDS = WGST_TDS;
    }

    public String getWRPST() {
        return WRPST;
    }

    public void setWRPST(String WRPST) {
        this.WRPST = WRPST;
    }

    public String getWRET_AMT() {
        return WRET_AMT;
    }

    public void setWRET_AMT(String WRET_AMT) {
        this.WRET_AMT = WRET_AMT;
    }

    public String getOTAX_AMT() {
        return OTAX_AMT;
    }

    public void setOTAX_AMT(String OTAX_AMT) {
        this.OTAX_AMT = OTAX_AMT;
    }

    public String getOIT_TDS() {
        return OIT_TDS;
    }

    public void setOIT_TDS(String OIT_TDS) {
        this.OIT_TDS = OIT_TDS;
    }

    public String getOGST_TDS() {
        return OGST_TDS;
    }

    public void setOGST_TDS(String OGST_TDS) {
        this.OGST_TDS = OGST_TDS;
    }

    public String getORPST() {
        return ORPST;
    }

    public void setORPST(String ORPST) {
        this.ORPST = ORPST;
    }

    public String getORET_AMT() {
        return ORET_AMT;
    }

    public void setORET_AMT(String ORET_AMT) {
        this.ORET_AMT = ORET_AMT;
    }

    public String getCTAX_AMT() {
        return CTAX_AMT;
    }

    public void setCTAX_AMT(String CTAX_AMT) {
        this.CTAX_AMT = CTAX_AMT;
    }

    public String getCIT_TDS() {
        return CIT_TDS;
    }

    public void setCIT_TDS(String CIT_TDS) {
        this.CIT_TDS = CIT_TDS;
    }

    public String getCGST_TDS() {
        return CGST_TDS;
    }

    public void setCGST_TDS(String CGST_TDS) {
        this.CGST_TDS = CGST_TDS;
    }

    public String getCRPST() {
        return CRPST;
    }

    public void setCRPST(String CRPST) {
        this.CRPST = CRPST;
    }

    public String getCRET_AMT() {
        return CRET_AMT;
    }

    public void setCRET_AMT(String CRET_AMT) {
        this.CRET_AMT = CRET_AMT;
    }

    public String getSTAX_AMT() {
        return STAX_AMT;
    }

    public void setSTAX_AMT(String STAX_AMT) {
        this.STAX_AMT = STAX_AMT;
    }

    public String getSIT_TDS() {
        return SIT_TDS;
    }

    public void setSIT_TDS(String SIT_TDS) {
        this.SIT_TDS = SIT_TDS;
    }

    public String getSGST_TDS() {
        return SGST_TDS;
    }

    public void setSGST_TDS(String SGST_TDS) {
        this.SGST_TDS = SGST_TDS;
    }

    public String getSRPST() {
        return SRPST;
    }

    public void setSRPST(String SRPST) {
        this.SRPST = SRPST;
    }

    public String getSRET_AMT() {
        return SRET_AMT;
    }

    public void setSRET_AMT(String SRET_AMT) {
        this.SRET_AMT = SRET_AMT;
    }

    public String getOTTAX_AMT() {
        return OTTAX_AMT;
    }

    public void setOTTAX_AMT(String OTTAX_AMT) {
        this.OTTAX_AMT = OTTAX_AMT;
    }

    public String getOTIT_TDS() {
        return OTIT_TDS;
    }

    public void setOTIT_TDS(String OTIT_TDS) {
        this.OTIT_TDS = OTIT_TDS;
    }

    public String getOTGST_TDS() {
        return OTGST_TDS;
    }

    public void setOTGST_TDS(String OTGST_TDS) {
        this.OTGST_TDS = OTGST_TDS;
    }

    public String getOTRPST() {
        return OTRPST;
    }

    public void setOTRPST(String OTRPST) {
        this.OTRPST = OTRPST;
    }

    public String getOTRET_AMT() {
        return OTRET_AMT;
    }

    public void setOTRET_AMT(String OTRET_AMT) {
        this.OTRET_AMT = OTRET_AMT;
    }
     
     
     
  public String getPoPurchasing_group() {
        return PoPurchasing_group;
    }

    public void setPoPurchasing_group(String PoPurchasing_group) {
        this.PoPurchasing_group = PoPurchasing_group;
    }
    public String getForwardToPlant() {
        return ForwardToPlant;
    }

    public void setForwardToPlant(String ForwardToPlant) {
        this.ForwardToPlant = ForwardToPlant;
    }

    public String getForwardToDesc() {
        return ForwardToDesc;
    }

    public void setForwardToDesc(String ForwardToDesc) {
        this.ForwardToDesc = ForwardToDesc;
    }

    public String getCREATED_USER_TYPE() {
        return CREATED_USER_TYPE;
    }

    public void setCREATED_USER_TYPE(String CREATED_USER_TYPE) {
        this.CREATED_USER_TYPE = CREATED_USER_TYPE;
    }

    public String getEMP_ID() {
        return EMP_ID;
    }

    public void setEMP_ID(String EMP_ID) {
        this.EMP_ID = EMP_ID;
    }

    public String getEMP_NAME() {
        return EMP_NAME;
    }

    public void setEMP_NAME(String EMP_NAME) {
        this.EMP_NAME = EMP_NAME;
    }

    public String getEMP_DESIGNATION() {
        return EMP_DESIGNATION;
    }

    public void setEMP_DESIGNATION(String EMP_DESIGNATION) {
        this.EMP_DESIGNATION = EMP_DESIGNATION;
    }

  

    public String getSELECTEDPLANT() {
        return SELECTEDPLANT;
    }

    public void setSELECTEDPLANT(String SELECTEDPLANT) {
        this.SELECTEDPLANT = SELECTEDPLANT;
    }

    public String getMATERIAL_PO() {
        return MATERIAL_PO;
    }

    public void setMATERIAL_PO(String MATERIAL_PO) {
        this.MATERIAL_PO = MATERIAL_PO;
    }

    public String getCENTAGES_PO() {
        return CENTAGES_PO;
    }

    public void setCENTAGES_PO(String CENTAGES_PO) {
        this.CENTAGES_PO = CENTAGES_PO;
    }

    public String getSERVICE_PO() {
        return SERVICE_PO;
    }

    public void setSERVICE_PO(String SERVICE_PO) {
        this.SERVICE_PO = SERVICE_PO;
    }


    public String getPurchasing_group() {
        return Purchasing_group;
    }

    public void setPurchasing_group(String Purchasing_group) {
        this.Purchasing_group = Purchasing_group;
    }

    public String getEmp_tech_sms_flag() {
        return Emp_tech_sms_flag;
    }

    public void setEmp_tech_sms_flag(String Emp_tech_sms_flag) {
        this.Emp_tech_sms_flag = Emp_tech_sms_flag;
    }

    public String getEmp_Acc_sms_flag() {
        return Emp_Acc_sms_flag;
    }

    public void setEmp_Acc_sms_flag(String Emp_Acc_sms_flag) {
        this.Emp_Acc_sms_flag = Emp_Acc_sms_flag;
    }

    public String getEmp_Cash_sms_flag() {
        return Emp_Cash_sms_flag;
    }

    public void setEmp_Cash_sms_flag(String Emp_Cash_sms_flag) {
        this.Emp_Cash_sms_flag = Emp_Cash_sms_flag;
    }

    public String getEsc_tech_sms_flag() {
        return Esc_tech_sms_flag;
    }

    public void setEsc_tech_sms_flag(String Esc_tech_sms_flag) {
        this.Esc_tech_sms_flag = Esc_tech_sms_flag;
    }

    public String getEsc_Acc_sms_flag() {
        return Esc_Acc_sms_flag;
    }

    public void setEsc_Acc_sms_flag(String Esc_Acc_sms_flag) {
        this.Esc_Acc_sms_flag = Esc_Acc_sms_flag;
    }

    public String getEsc_Cash_sms_flag() {
        return Esc_Cash_sms_flag;
    }

    public void setEsc_Cash_sms_flag(String Esc_Cash_sms_flag) {
        this.Esc_Cash_sms_flag = Esc_Cash_sms_flag;
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String SerialNo) {
        this.SerialNo = SerialNo;
    }

    public String getPurchasingDesc() {
        return PurchasingDesc;
    }

    public void setPurchasingDesc(String PurchasingDesc) {
        this.PurchasingDesc = PurchasingDesc;
    }

    public String getSubmitAtDesc() {
        return SubmitAtDesc;
    }

    public void setSubmitAtDesc(String SubmitAtDesc) {
        this.SubmitAtDesc = SubmitAtDesc;
    }

    public String getSubmitAtPlant() {
        return SubmitAtPlant;
    }

    public void setSubmitAtPlant(String SubmitAtPlant) {
        this.SubmitAtPlant = SubmitAtPlant;
    }

    public String getINVOICE_TYPE() {
        return INVOICE_TYPE;
    }

    public void setINVOICE_TYPE(String INVOICE_TYPE) {
        this.INVOICE_TYPE = INVOICE_TYPE;
    }

    public String getIT_TDS_AMOUNT() {
        return IT_TDS_AMOUNT;
    }

    public void setIT_TDS_AMOUNT(String IT_TDS_AMOUNT) {
        this.IT_TDS_AMOUNT = IT_TDS_AMOUNT;
    }

    public String getGST_TDS() {
        return GST_TDS;
    }

    public void setGST_TDS(String GST_TDS) {
        this.GST_TDS = GST_TDS;
    }

    public String getRETENSION_AMOUNT() {
        return RETENSION_AMOUNT;
    }

    public void setRETENSION_AMOUNT(String RETENSION_AMOUNT) {
        this.RETENSION_AMOUNT = RETENSION_AMOUNT;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }


    public String getSesMigoAmt() {
        return sesMigoAmt;
    }

    public void setSesMigoAmt(String sesMigoAmt) {
        this.sesMigoAmt = sesMigoAmt;
    }

    public String getInvAmt() {
        return invAmt;
    }

    public void setInvAmt(String invAmt) {
        this.invAmt = invAmt;
    }

    public Date getMsedclInwardDate() {
        return msedclInwardDate;
    }

    public void setMsedclInwardDate(Date msedclInwardDate) {
        this.msedclInwardDate = msedclInwardDate;
    }

    public String getMsedclInwardNumber() {
        return msedclInwardNumber;
    }

    public void setMsedclInwardNumber(String msedclInwardNumber) {
        this.msedclInwardNumber = msedclInwardNumber;
    }

    public String getBalPoAmt() {
        return BalPoAmt;
    }

    public void setBalPoAmt(String BalPoAmt) {
        this.BalPoAmt = BalPoAmt;
    }

    public String getTotalPoAmt() {
        return TotalPoAmt;
    }

    public void setTotalPoAmt(String TotalPoAmt) {
        this.TotalPoAmt = TotalPoAmt;
    }

    public String getModuleSaveFlag() {
        return ModuleSaveFlag;
    }

    public void setModuleSaveFlag(String ModuleSaveFlag) {
        this.ModuleSaveFlag = ModuleSaveFlag;
    }

    public String getSubDivision() {
        return SubDivision;
    }

    public void setSubDivision(String SubDivision) {
        this.SubDivision = SubDivision;
    }

    public String getWorkDetailDesc() {
        return WorkDetailDesc;
    }

    public void setWorkDetailDesc(String WorkDetailDesc) {
        this.WorkDetailDesc = WorkDetailDesc;
    }

    public String getProjectDesc() {
        return ProjectDesc;
    }

    public void setProjectDesc(String ProjectDesc) {
        this.ProjectDesc = ProjectDesc;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getSelectedModuleType() {
        return SelectedModuleType;
    }

    public void setSelectedModuleType(String SelectedModuleType) {
        this.SelectedModuleType = SelectedModuleType;
    }
    public String getPendingSince() {
        return PendingSince;
    }

    public void setPendingSince(String PendingSince) {
        this.PendingSince = PendingSince;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public String getInvResubmitCounter() {
        return InvResubmitCounter;
    }

    public void setInvResubmitCounter(String InvResubmitCounter) {
        this.InvResubmitCounter = InvResubmitCounter;
    }

 public Date getInvoiceREjDAte() {
        return InvoiceREjDAte;
    }

    public void setInvoiceREjDAte(Date InvoiceREjDAte) {
        this.InvoiceREjDAte = InvoiceREjDAte;
    }

    public Date getInvoiceApprDAte() {
        return InvoiceApprDAte;
    }

    public void setInvoiceApprDAte(Date InvoiceApprDAte) {
        this.InvoiceApprDAte = InvoiceApprDAte;
    }
    public String getSesMigoInvNo() {
        return SesMigoInvNo;
    }

    public void setSesMigoInvNo(String SesMigoInvNo) {
        this.SesMigoInvNo = SesMigoInvNo;
    }

    public Date getInvoiceSubmitDate() {
        return InvoiceSubmitDate;
    }

    public void setInvoiceSubmitDate(Date InvoiceSubmitDate) {
        this.InvoiceSubmitDate = InvoiceSubmitDate;
    }
private Date InvoiceSubmitDate;


    public String getIsApprovedFlag() {
        return IsApprovedFlag;
    }

    public void setIsApprovedFlag(String IsApprovedFlag) {
        this.IsApprovedFlag = IsApprovedFlag;
    }

private Date VendorInwardDate;
    public Date getVendorInwardDate() {
        return VendorInwardDate;
    }

    public void setVendorInwardDate(Date VendorInwardDate) {
        this.VendorInwardDate = VendorInwardDate;
    }

    
    public String getIsRejectedFlag() {
        return IsRejectedFlag;
    }

    public void setIsRejectedFlag(String IsRejectedFlag) {
        this.IsRejectedFlag = IsRejectedFlag;
    }

    public Date getVendorInvoiceResubmit() {
        return VendorInvoiceResubmit;
    }

    public void setVendorInvoiceResubmit(Date VendorInvoiceResubmit) {
        this.VendorInvoiceResubmit = VendorInvoiceResubmit;
    }

    public String getPlant_desc() {
        return Plant_desc;
    }

    public void setPlant_desc(String Plant_desc) {
        this.Plant_desc = Plant_desc;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getSelectedModule() {
        return SelectedModule;
    }

    public void setSelectedModule(String SelectedModule) {
        this.SelectedModule = SelectedModule;
    }


    public Date getSormDate() {
        return SormDate;
    }

    public void setSormDate(Date SormDate) {
        this.SormDate = SormDate;
    }
    public String getTechnical_sms_flag() {
        return Technical_sms_flag;
    }

    public void setTechnical_sms_flag(String Technical_sms_flag) {
        this.Technical_sms_flag = Technical_sms_flag;
    }

    public String getAccounts_sms_flag() {
        return Accounts_sms_flag;
    }

    public void setAccounts_sms_flag(String Accounts_sms_flag) {
        this.Accounts_sms_flag = Accounts_sms_flag;
    }

    public String getCashier_sms_flag() {
        return Cashier_sms_flag;
    }

    public void setCashier_sms_flag(String Cashier_sms_flag) {
        this.Cashier_sms_flag = Cashier_sms_flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



 public String getParent_Office_Code() {
    return Parent_Office_Code;
}
public void setParent_Office_Code(String Parent_Office_Code) {
    this.Parent_Office_Code=Parent_Office_Code;
}           
 public String getHigherAuthsmsFlag() {
    return HigherAuthsmsFlag;
}
public void setHigherAuthsmsFlag(String HigherAuthsmsFlag) {
    this.HigherAuthsmsFlag=HigherAuthsmsFlag;
}       
public String getDaysDelayed() {
    return DaysDelayed;
}
public void setDaysDelayed(String DaysDelayed) {
    this.DaysDelayed=DaysDelayed;
}
public String getempCpf() {
    return emp_Cpf;
}
public void setempCpf(String emp_Cpf) {
    this.emp_Cpf=emp_Cpf;
}
public String getempName() {
    return emp_Name;
}
public void setempName(String emp_Name) {
    this.emp_Name=emp_Name;
}

public String getsmsFlag() {
    return smsFlag;
}
public void setsmsFlag(String smsFlag) {
    this.smsFlag=smsFlag;
}
public String getContactNumber() {
    return ContactNumber;
}
public void setContactNumber(String ContactNumber) {
    this.ContactNumber=ContactNumber;
}
public String getDesignation () {
    return Designation;
}
public void setDesignation(String Designation) {
    this.Designation=Designation;
}

public String getOffice_Code () {
    return Office_Code;
}

public void setOffice_Code(String Office_Code) {
    this.Office_Code=Office_Code;
}

 public Date getVendorUpdatedDate() {
        return InvoiceUpdatedDate;
    }
 
   public void setVendorUpdatedDate(Date InvoiceUpdatedDate){
        this.InvoiceUpdatedDate=InvoiceUpdatedDate;
    }
    public String getLocationOpt() {
        return LocationOpt;
    }
  
      
      public void setTaxCode(String Tax_Code){
        this.Tax_Code=Tax_Code;
    }
      
      public void setTaxAmount(String Tax_Amount){
        this.Tax_Amount=Tax_Amount;
    }
       public String getTaxCode(){
        return Tax_Code;
    }
     public String getTaxAmount(){
        return Tax_Amount;
    }

    public void setLocationOpt(String LocationOpt) {
        this.LocationOpt = LocationOpt;
    }

    public String getDispVendorNumber() {
        return DispVendorNumber;
    }

    public void setDispVendorNumber(String DispVendorNumber) {
        this.DispVendorNumber = DispVendorNumber;
    }

    public String getDispVendorName() {
        return DispVendorName;
    }

    public void setDispVendorName(String DispVendorName) {
        this.DispVendorName = DispVendorName;
    }
    
    
    
    //    private String PaidAmt;
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String LocationId) {
        this.LocationId = LocationId;
    }
    
    
    public Date getApplDate() {
        return ApplDate;
    }

    public void setApplDate(Date ApplDate) {
        this.ApplDate = ApplDate;
    }
    
    
    public String getInwardNumber() {
        return InwardNumber;
    }

    public void setInwardNumber(String InwardNumber) {
        this.InwardNumber = InwardNumber;
    }

    public Date getInwardDate() {
        return InwardDate;
    }

    public void setInwardDate(Date InwardDate) {
        this.InwardDate = InwardDate;
    }
        
    public String getApplId() {
        return ApplId;
    }

    public void setApplId(String ApplId) {
        this.ApplId = ApplId;
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

    public String getPONumberHdr() {
        return PONumberHdr;
    }

    public void setPONumberHdr(String PONumberHdr) {
        this.PONumberHdr = PONumberHdr;
    }

    public String getPONumber() {
        return PONumber;
    }

    public void setPONumber(String PONumber) {
        this.PONumber = PONumber;
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

    public String getVendorInvoiceNumber() {
        return VendorInvoiceNumber;
    }

    public void setVendorInvoiceNumber(String VendorInvoiceNumber) {
        this.VendorInvoiceNumber = VendorInvoiceNumber;
    }

    public Date getVendorInvoiceDate() {
        return VendorInvoiceDate;
    }

    public void setVendorInvoiceDate(Date VendorInvoiceDate) {
        this.VendorInvoiceDate = VendorInvoiceDate;
    }

    public String getVendorInvoiceAmount() {
        return VendorInvoiceAmount;
    }

    public void setVendorInvoiceAmount(String VendorInvoiceAmount) {
        this.VendorInvoiceAmount = VendorInvoiceAmount;
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

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }
    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }
    
}