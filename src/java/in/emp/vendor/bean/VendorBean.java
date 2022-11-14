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
public class VendorBean extends SummaryBean implements java.io.Serializable {
    private String contactNumber;
    private String vendorNumber;
    private String vendorName;
    private String PONumberHdr;
    private String PONumber;
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
    
   // private String Status; 
       
    
    private String VendorInvoiceNumber;
    private Date invoiceDate;
   private String invoiceAmount;
   private String MsedclInvoiceNumber;

    private String MsedclInvoiceAmount;

   
 private Date VendorInvoiceDate;
     private Date MsedclInvoiceDate;
   private String paidAmt;
//    private String ApplId;
   private String saveFlag;
   private String subAction;
   private String userType;
   private String LocationId;
   

    //    private String PaidAmt;
   
  private Date SESDate;
  private String SesNum;
  private String SesAmt;
  private String BalAmt;
  private String TotalPOAmt;
  private String ProfitCenter;
  
  private String dispVendorNumber;
  private String dispVendorName;								
												
  private String SESDesc;
  private String CLDocNo;
  private Date CLDocDt;
  private Date InvDt;
    
  private String VendorNumberHdr;
  
  private String hdnPoOption;
  private String LocationOpt;

  private String PlantCode;
  private String InvoiceFlag;
  private String Tax_Code;
  private String Tax_Amount;

   

  private String Paid;
private String MailId;

private String It_Tds_Amount;
private String Gst_Tds;
private String Retension_Amount;
private String Project_Code;
private String Project_Scheme;
private String Password;
private String Psinv_no;
private String ses_ven_invno;

private String Migo_ven_invno;
private String migo_doc;
private Date migo_dt;

  


private String migo_amt;
private String sesormigo_no;
//ps related fields
 
 private String  PROJECT_DESC              ;
 private Date    CREATION_DATE             ;
private String  SCHEME_CODE              ; 

 private String  MATERIAL_PO               ;
 private String  CENTAGES_PO               ;
 private String  SERVICE_PO                ;
 private String  MATERIAL_WS_PARK_DOC       ;
 private Date    MAT_DOC_DATE               ;
 private String  MAT_WS_PARK_AMT           ; 
 private String  MAT_WS_CLEARING_DOC_NO    ; 
 private Date    MAT_WS_AC_DOC_DATE        ; 
private String   MAT_WS_CLEARING_AMT        ;
private String   CENTAGES_PARK_DOC          ;
 private Date   CEN_DOC_DATE                ;
private String   CEN_PARK_AMT                ;
private String   CEN_CLEARING_DOC_NO         ;
private Date    CEN_AC_DOC_DATE              ;
private String   CEN_CLEARING_AMT            ;
private String   CIVIL_PARK_DOC              ;
private Date    CIVIL_DOC_DATE               ;
private String   CIVIL_PARK_AMT             ; 
private String   CIVIL_CLEARING_DOC_NO      ; 
private Date    CIVIL_AC_DOC_DATE           ; 
private String   CIVIL_CLEARING_AMT          ;
private String   MAT_OS_PARK_DOC_NO          ;
private Date     MAT_OS_DOC_DATE             ;
private String   MAT_OS_PARK_AMT             ;
private String   MAT_OS_CLEARING_DOC_NO      ;
private Date     MAT_OS_AC_DOC_DATE          ;
private String   MAT_OS_CLEARING_AMT         ;
private String  SELECTED_MODULE_TYPE ;//MODULE TYPE TO DIFFERENTIATE BETWEEN PO NUMBER AND PROJ ID 
private String   MAT_OTH_PARK_DOC_NO          ;
private Date     MAT_OTH_DOC_DATE             ;
private String   MAT_OTH_PARK_AMT             ;
private String   MAT_OTH_CLEARING_DOC_NO      ;
private Date     MAT_OTH_AC_DOC_DATE          ;
private String   MAT_OTH_CLEARING_AMT         ;
private String PO_MAT_AMT; 
private String PO_CEN_AMT; 
private String PO_CIV_AMT; 
private String PO_INV_AMT;
private String TOTAL_PO_AMOUNT;
private String SES_STATUS;
private String MIGO_STATUS;
private String INV_STATUS;
private String PAYMENT_STATUS;
private String SES_MIGO_INV_TYPE;
private String	INV_TYP        ; 
   
    
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
  private String TECH_STAT;
 private String Invoice_Type;
  private String    MIGST_TX	  ;
   private String    MSGST_TX	  ;
   private String    OSGST_TX	  ;
   private String    OIGST_TX	  ;
   private String    CSGST_TX	  ;
   private String    CIGST_TX	  ;
   private String    SSGST_TX	  ;
   private String    SIGST_TX	  ;
   private String    OTSGST_TX	  ;
   private String    OTIGST_TX	  ;
private String   UNPAID_SUBMITTED;
private Date claimDate;

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getUNPAID_SUBMITTED() {
        return UNPAID_SUBMITTED;
    }

    public void setUNPAID_SUBMITTED(String UNPAID_SUBMITTED) {
        this.UNPAID_SUBMITTED = UNPAID_SUBMITTED;
    }
    
    public String getMIGST_TX() {
        return MIGST_TX;
    }

    public void setMIGST_TX(String MIGST_TX) {
        this.MIGST_TX = MIGST_TX;
    }

    public String getMSGST_TX() {
        return MSGST_TX;
    }

    public void setMSGST_TX(String MSGST_TX) {
        this.MSGST_TX = MSGST_TX;
    }

    public String getOSGST_TX() {
        return OSGST_TX;
    }

    public void setOSGST_TX(String OSGST_TX) {
        this.OSGST_TX = OSGST_TX;
    }

    public String getOIGST_TX() {
        return OIGST_TX;
    }

    public void setOIGST_TX(String OIGST_TX) {
        this.OIGST_TX = OIGST_TX;
    }

    public String getCSGST_TX() {
        return CSGST_TX;
    }

    public void setCSGST_TX(String CSGST_TX) {
        this.CSGST_TX = CSGST_TX;
    }

    public String getCIGST_TX() {
        return CIGST_TX;
    }

    public void setCIGST_TX(String CIGST_TX) {
        this.CIGST_TX = CIGST_TX;
    }

    public String getSSGST_TX() {
        return SSGST_TX;
    }

    public void setSSGST_TX(String SSGST_TX) {
        this.SSGST_TX = SSGST_TX;
    }

    public String getSIGST_TX() {
        return SIGST_TX;
    }

    public void setSIGST_TX(String SIGST_TX) {
        this.SIGST_TX = SIGST_TX;
    }

    public String getOTSGST_TX() {
        return OTSGST_TX;
    }

    public void setOTSGST_TX(String OTSGST_TX) {
        this.OTSGST_TX = OTSGST_TX;
    }

    public String getOTIGST_TX() {
        return OTIGST_TX;
    }

    public void setOTIGST_TX(String OTIGST_TX) {
        this.OTIGST_TX = OTIGST_TX;
    }
  
    public String getInvoice_Type() {
        return Invoice_Type;
    }

    public void setInvoice_Type(String Invoice_Type) {
        this.Invoice_Type = Invoice_Type;
    }
 
    public String getTECH_STAT() {
        return TECH_STAT;
    }

    public void setTECH_STAT(String TECH_STAT) {
        this.TECH_STAT = TECH_STAT;
    }
    
      public Date getVendorInvoiceDate() {
        return VendorInvoiceDate;
    }

    public void setVendorInvoiceDate(Date VendorInvoiceDate) {
        this.VendorInvoiceDate = VendorInvoiceDate;
    }

    public String getINV_TYP() {
        return INV_TYP;
    }

    public void setINV_TYP(String INV_TYP) {
        this.INV_TYP = INV_TYP;
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


    public String getSES_MIGO_INV_TYPE() {
        return SES_MIGO_INV_TYPE;
    }

    public void setSES_MIGO_INV_TYPE(String SES_MIGO_INV_TYPE) {
        this.SES_MIGO_INV_TYPE = SES_MIGO_INV_TYPE;
    }

    public String getSES_STATUS() {
        return SES_STATUS;
    }

    public void setSES_STATUS(String SES_STATUS) {
        this.SES_STATUS = SES_STATUS;
    }

    public String getMIGO_STATUS() {
        return MIGO_STATUS;
    }

    public void setMIGO_STATUS(String MIGO_STATUS) {
        this.MIGO_STATUS = MIGO_STATUS;
    }

    public String getINV_STATUS() {
        return INV_STATUS;
    }

    public void setINV_STATUS(String INV_STATUS) {
        this.INV_STATUS = INV_STATUS;
    }

    public String getPAYMENT_STATUS() {
        return PAYMENT_STATUS;
    }

    public void setPAYMENT_STATUS(String PAYMENT_STATUS) {
        this.PAYMENT_STATUS = PAYMENT_STATUS;
    }

    public String getSes_ven_invno() {
        return ses_ven_invno;
    }

    public void setSes_ven_invno(String ses_ven_invno) {
        this.ses_ven_invno = ses_ven_invno;
    }

    public String getMigo_ven_invno() {
        return Migo_ven_invno;
    }

    public void setMigo_ven_invno(String Migo_ven_invno) {
        this.Migo_ven_invno = Migo_ven_invno;
    }
 public String getPaid() {
        return Paid;
    }

    public void setPaid(String Paid) {
        this.Paid = Paid;
    }
   public Date getMsedclInvoiceDate() {
        return MsedclInvoiceDate;
    }

    public void setMsedclInvoiceDate(Date MsedclInvoiceDate) {
        this.MsedclInvoiceDate = MsedclInvoiceDate;
    }
    public String getMsedclInvoiceAmount() {
        return MsedclInvoiceAmount;
    }

    public void setMsedclInvoiceAmount(String MsedclInvoiceAmount) {
        this.MsedclInvoiceAmount = MsedclInvoiceAmount;
    }

  
    public String getTOTAL_PO_AMOUNT() {
        return TOTAL_PO_AMOUNT;
    }

    public void setTOTAL_PO_AMOUNT(String TOTAL_PO_AMOUNT) {
        this.TOTAL_PO_AMOUNT = TOTAL_PO_AMOUNT;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public String getIt_Tds_Amount() {
        return It_Tds_Amount;
    }

    public void setIt_Tds_Amount(String It_Tds_Amount) {
        this.It_Tds_Amount = It_Tds_Amount;
    }

    public String getGst_Tds() {
        return Gst_Tds;
    }

    public void setGst_Tds(String Gst_Tds) {
        this.Gst_Tds = Gst_Tds;
    }

    public String getRetension_Amount() {
        return Retension_Amount;
    }

    public void setRetension_Amount(String Retension_Amount) {
        this.Retension_Amount = Retension_Amount;
    }

    public String getProject_Code() {
        return Project_Code;
    }

    public void setProject_Code(String Project_Code) {
        this.Project_Code = Project_Code;
    }

    public String getMAT_OTH_PARK_DOC_NO() {
        return MAT_OTH_PARK_DOC_NO;
    }

    public void setMAT_OTH_PARK_DOC_NO(String MAT_OTH_PARK_DOC_NO) {
        this.MAT_OTH_PARK_DOC_NO = MAT_OTH_PARK_DOC_NO;
    }

    public Date getMAT_OTH_DOC_DATE() {
        return MAT_OTH_DOC_DATE;
    }

    public void setMAT_OTH_DOC_DATE(Date MAT_OTH_DOC_DATE) {
        this.MAT_OTH_DOC_DATE = MAT_OTH_DOC_DATE;
    }

    public String getMAT_OTH_PARK_AMT() {
        return MAT_OTH_PARK_AMT;
    }

    public void setMAT_OTH_PARK_AMT(String MAT_OTH_PARK_AMT) {
        this.MAT_OTH_PARK_AMT = MAT_OTH_PARK_AMT;
    }

    public String getMAT_OTH_CLEARING_DOC_NO() {
        return MAT_OTH_CLEARING_DOC_NO;
    }

    public void setMAT_OTH_CLEARING_DOC_NO(String MAT_OTH_CLEARING_DOC_NO) {
        this.MAT_OTH_CLEARING_DOC_NO = MAT_OTH_CLEARING_DOC_NO;
    }

    public Date getMAT_OTH_AC_DOC_DATE() {
        return MAT_OTH_AC_DOC_DATE;
    }

    public void setMAT_OTH_AC_DOC_DATE(Date MAT_OTH_AC_DOC_DATE) {
        this.MAT_OTH_AC_DOC_DATE = MAT_OTH_AC_DOC_DATE;
    }

    public String getMAT_OTH_CLEARING_AMT() {
        return MAT_OTH_CLEARING_AMT;
    }

    public void setMAT_OTH_CLEARING_AMT(String MAT_OTH_CLEARING_AMT) {
        this.MAT_OTH_CLEARING_AMT = MAT_OTH_CLEARING_AMT;
    }

    public String getPO_MAT_AMT() {
        return PO_MAT_AMT;
    }

    public void setPO_MAT_AMT(String PO_MAT_AMT) {
        this.PO_MAT_AMT = PO_MAT_AMT;
    }

    public String getPO_CEN_AMT() {
        return PO_CEN_AMT;
    }

    public void setPO_CEN_AMT(String PO_CEN_AMT) {
        this.PO_CEN_AMT = PO_CEN_AMT;
    }

    public String getPO_CIV_AMT() {
        return PO_CIV_AMT;
    }

    public void setPO_CIV_AMT(String PO_CIV_AMT) {
        this.PO_CIV_AMT = PO_CIV_AMT;
    }

    public String getPO_INV_AMT() {
        return PO_INV_AMT;
    }

    public void setPO_INV_AMT(String PO_INV_AMT) {
        this.PO_INV_AMT = PO_INV_AMT;
    }




  public String getMigo_amt() {
        return migo_amt;
    }

    public void setMigo_amt(String migo_amt) {
        this.migo_amt = migo_amt;
    }

 
    public String getSELECTED_MODULE_TYPE() {//MODULE TYPE TO DIFFERENTIATE BETWEEN PO NUMBER AND PROJ ID 
        return SELECTED_MODULE_TYPE;
    }

    public void setSELECTED_MODULE_TYPE(String SELECTED_MODULE_TYPE) {
        this.SELECTED_MODULE_TYPE = SELECTED_MODULE_TYPE;
    }


    public String getProject_Scheme() {
        return Project_Scheme;
    }

    public void setProject_Scheme(String Project_Scheme) {
        this.Project_Scheme = Project_Scheme;
    }

    

    public String getPROJECT_DESC() {
        return PROJECT_DESC;
    }

    public void setPROJECT_DESC(String PROJECT_DESC) {
        this.PROJECT_DESC = PROJECT_DESC;
    }

    public Date getCREATION_DATE() {
        return CREATION_DATE;
    }

    public void setCREATION_DATE(Date CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }

    public String getSCHEME_CODE() {
        return SCHEME_CODE;
    }

    public void setSCHEME_CODE(String SCHEME_CODE) {
        this.SCHEME_CODE = SCHEME_CODE;
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

    public String getMATERIAL_WS_PARK_DOC() {
        return MATERIAL_WS_PARK_DOC;
    }

    public void setMATERIAL_WS_PARK_DOC(String MATERIAL_WS_PARK_DOC) {
        this.MATERIAL_WS_PARK_DOC = MATERIAL_WS_PARK_DOC;
    }

    public Date getMAT_DOC_DATE() {
        return MAT_DOC_DATE;
    }

    public void setMAT_DOC_DATE(Date MAT_DOC_DATE) {
        this.MAT_DOC_DATE = MAT_DOC_DATE;
    }

    public String getMAT_WS_PARK_AMT() {
        return MAT_WS_PARK_AMT;
    }

    public void setMAT_WS_PARK_AMT(String MAT_WS_PARK_AMT) {
        this.MAT_WS_PARK_AMT = MAT_WS_PARK_AMT;
    }

    public String getMAT_WS_CLEARING_DOC_NO() {
        return MAT_WS_CLEARING_DOC_NO;
    }

    public void setMAT_WS_CLEARING_DOC_NO(String MAT_WS_CLEARING_DOC_NO) {
        this.MAT_WS_CLEARING_DOC_NO = MAT_WS_CLEARING_DOC_NO;
    }

    public Date getMAT_WS_AC_DOC_DATE() {
        return MAT_WS_AC_DOC_DATE;
    }

    public void setMAT_WS_AC_DOC_DATE(Date MAT_WS_AC_DOC_DATE) {
        this.MAT_WS_AC_DOC_DATE = MAT_WS_AC_DOC_DATE;
    }

    public String getMAT_WS_CLEARING_AMT() {
        return MAT_WS_CLEARING_AMT;
    }

    public void setMAT_WS_CLEARING_AMT(String MAT_WS_CLEARING_AMT) {
        this.MAT_WS_CLEARING_AMT = MAT_WS_CLEARING_AMT;
    }

    public String getCENTAGES_PARK_DOC() {
        return CENTAGES_PARK_DOC;
    }

    public void setCENTAGES_PARK_DOC(String CENTAGES_PARK_DOC) {
        this.CENTAGES_PARK_DOC = CENTAGES_PARK_DOC;
    }

    public Date getCEN_DOC_DATE() {
        return CEN_DOC_DATE;
    }

    public void setCEN_DOC_DATE(Date CEN_DOC_DATE) {
        this.CEN_DOC_DATE = CEN_DOC_DATE;
    }

    public String getCEN_PARK_AMT() {
        return CEN_PARK_AMT;
    }

    public void setCEN_PARK_AMT(String CEN_PARK_AMT) {
        this.CEN_PARK_AMT = CEN_PARK_AMT;
    }

    public String getCEN_CLEARING_DOC_NO() {
        return CEN_CLEARING_DOC_NO;
    }

    public void setCEN_CLEARING_DOC_NO(String CEN_CLEARING_DOC_NO) {
        this.CEN_CLEARING_DOC_NO = CEN_CLEARING_DOC_NO;
    }

    public Date getCEN_AC_DOC_DATE() {
        return CEN_AC_DOC_DATE;
    }

    public void setCEN_AC_DOC_DATE(Date CEN_AC_DOC_DATE) {
        this.CEN_AC_DOC_DATE = CEN_AC_DOC_DATE;
    }

    public String getCEN_CLEARING_AMT() {
        return CEN_CLEARING_AMT;
    }

    public void setCEN_CLEARING_AMT(String CEN_CLEARING_AMT) {
        this.CEN_CLEARING_AMT = CEN_CLEARING_AMT;
    }

    public String getCIVIL_PARK_DOC() {
        return CIVIL_PARK_DOC;
    }

    public void setCIVIL_PARK_DOC(String CIVIL_PARK_DOC) {
        this.CIVIL_PARK_DOC = CIVIL_PARK_DOC;
    }

    public Date getCIVIL_DOC_DATE() {
        return CIVIL_DOC_DATE;
    }

    public void setCIVIL_DOC_DATE(Date CIVIL_DOC_DATE) {
        this.CIVIL_DOC_DATE = CIVIL_DOC_DATE;
    }

    public String getCIVIL_PARK_AMT() {
        return CIVIL_PARK_AMT;
    }

    public void setCIVIL_PARK_AMT(String CIVIL_PARK_AMT) {
        this.CIVIL_PARK_AMT = CIVIL_PARK_AMT;
    }

    public String getCIVIL_CLEARING_DOC_NO() {
        return CIVIL_CLEARING_DOC_NO;
    }

    public void setCIVIL_CLEARING_DOC_NO(String CIVIL_CLEARING_DOC_NO) {
        this.CIVIL_CLEARING_DOC_NO = CIVIL_CLEARING_DOC_NO;
    }

    public Date getCIVIL_AC_DOC_DATE() {
        return CIVIL_AC_DOC_DATE;
    }

    public void setCIVIL_AC_DOC_DATE(Date CIVIL_AC_DOC_DATE) {
        this.CIVIL_AC_DOC_DATE = CIVIL_AC_DOC_DATE;
    }

    public String getCIVIL_CLEARING_AMT() {
        return CIVIL_CLEARING_AMT;
    }

    public void setCIVIL_CLEARING_AMT(String CIVIL_CLEARING_AMT) {
        this.CIVIL_CLEARING_AMT = CIVIL_CLEARING_AMT;
    }

    public String getMAT_OS_PARK_DOC_NO() {
        return MAT_OS_PARK_DOC_NO;
    }

    public void setMAT_OS_PARK_DOC_NO(String MAT_OS_PARK_DOC_NO) {
        this.MAT_OS_PARK_DOC_NO = MAT_OS_PARK_DOC_NO;
    }

    public Date getMAT_OS_DOC_DATE() {
        return MAT_OS_DOC_DATE;
    }

    public void setMAT_OS_DOC_DATE(Date MAT_OS_DOC_DATE) {
        this.MAT_OS_DOC_DATE = MAT_OS_DOC_DATE;
    }

    public String getMAT_OS_PARK_AMT() {
        return MAT_OS_PARK_AMT;
    }

    public void setMAT_OS_PARK_AMT(String MAT_OS_PARK_AMT) {
        this.MAT_OS_PARK_AMT = MAT_OS_PARK_AMT;
    }

    public String getMAT_OS_CLEARING_DOC_NO() {
        return MAT_OS_CLEARING_DOC_NO;
    }

    public void setMAT_OS_CLEARING_DOC_NO(String MAT_OS_CLEARING_DOC_NO) {
        this.MAT_OS_CLEARING_DOC_NO = MAT_OS_CLEARING_DOC_NO;
    }

    public Date getMAT_OS_AC_DOC_DATE() {
        return MAT_OS_AC_DOC_DATE;
    }

    public void setMAT_OS_AC_DOC_DATE(Date MAT_OS_AC_DOC_DATE) {
        this.MAT_OS_AC_DOC_DATE = MAT_OS_AC_DOC_DATE;
    }

    public String getMAT_OS_CLEARING_AMT() {
        return MAT_OS_CLEARING_AMT;
    }

    public void setMAT_OS_CLEARING_AMT(String MAT_OS_CLEARING_AMT) {
        this.MAT_OS_CLEARING_AMT = MAT_OS_CLEARING_AMT;
    }

    public String getSesormigo_no() {
        return sesormigo_no;
    }

    public void setSesormigo_no(String sesormigo_no) {
        this.sesormigo_no = sesormigo_no;
    }


    public String getMigo_doc() {
        return migo_doc;
    }

    public void setMigo_doc(String migo_doc) {
        this.migo_doc = migo_doc;
    }

    public Date getMigo_dt() {
        return migo_dt;
    }

    public void setMigo_dt(Date migo_dt) {
        this.migo_dt = migo_dt;
    }
  public String getMailId() {
        return MailId;
    }

    public void setMailId(String MailId) {
        this.MailId = MailId;
    }
  

    public String getPsinv_no() {
        return Psinv_no;
    }

    public void setPsinv_no(String Psinv_no) {
        this.Psinv_no = Psinv_no;
    }

public String getVendorContactNumber(){
    return contactNumber;
}
public void setVendorContactNumber(String contactNumber){
    this.contactNumber=contactNumber;
}
public String getPassword(){
    return Password;
}
public void setPassword(String Password){
    this.Password=Password;
}
public void setProjectCode(String Project_Code){
        this.Project_Code=Project_Code;
    }
public void setProjectScheme(String Project_Scheme){
        this.Project_Scheme=Project_Scheme;
    }

public String getProjectCode(){
        return Project_Code;
    }
public String getProjectScheme(){
        return Project_Scheme;
    }

 public void setGstTds(String Gst_Tds){
        this.Gst_Tds=Gst_Tds;
    }
  public void setRetensionAmount(String Retension_Amount){
        this.Retension_Amount=Retension_Amount;
    }
   public String getGstTds(){
        return Gst_Tds;
    }
    public String getRetensionAmount(){
        return Retension_Amount;
    }
 public void setTaxCode(String Tax_Code){
        this.Tax_Code=Tax_Code;
    }
      
      public void setTaxAmount(String Tax_Amount){
        this.Tax_Amount=Tax_Amount;
    }
       
          public void setItTdsAmount(String It_Tds_Amount){
        this.It_Tds_Amount=It_Tds_Amount;
    }
       public String getTaxCode(){
        return Tax_Code;
    }
     public String getTaxAmount(){
        return Tax_Amount;
    }
    
     public String getItTdsAmount(){
        return It_Tds_Amount;
    }
   public String getPlantCode() {
        return PlantCode;
    }

    public void setPlantCode(String PlantCode) {
        this.PlantCode = PlantCode;
    }
   public void setInvoiceFlag(String InvoiceFlag) {
        this.InvoiceFlag = InvoiceFlag;
    } 
    
public String getInvoiceFlag() {
        return InvoiceFlag;
    }
  
  
  
    public String getLocationOpt() {
        return LocationOpt;
    }

    public void setLocationOpt(String LocationOpt) {
        this.LocationOpt = LocationOpt;
    }
   
    public String getHdnPoOption() {
        return hdnPoOption;
    }

    public void setHdnPoOption(String hdnPoOption) {
        this.hdnPoOption = hdnPoOption;
    }
  
  

    public String getVendorNumberHdr() {
        return VendorNumberHdr;
    }

    public void setVendorNumberHdr(String VendorNumberHdr) {
        this.VendorNumberHdr = VendorNumberHdr;
    }
  
    public Date getInvDt() {
        return InvDt;
    }

    public void setInvDt(Date InvDt) {
        this.InvDt = InvDt;
    }
  
    public String getSESDesc() {
        return SESDesc;
    }

    public void setSESDesc(String SESDesc) {
        this.SESDesc = SESDesc;
    }

    public String getCLDocNo() {
        return CLDocNo;
    }

    public void setCLDocNo(String CLDocNo) {
        this.CLDocNo = CLDocNo;
    }

    public Date getCLDocDt() {
        return CLDocDt;
    }

    public void setCLDocDt(Date CLDocDt) {
        this.CLDocDt = CLDocDt;
    }
  
  

    public String getDispVendorNumber() {
        return dispVendorNumber;
    }

    public void setDispVendorNumber(String dispVendorNumber) {
        this.dispVendorNumber = dispVendorNumber;
    }

    public String getDispVendorName() {
        return dispVendorName;
    }

    public void setDispVendorName(String dispVendorName) {
        this.dispVendorName = dispVendorName;
    }

  
    public Date getSESDate() {
        return SESDate;
    }

    public void setSESDate(Date SESDate) {
        this.SESDate = SESDate;
    }

    public String getSesNum() {
        return SesNum;
    }

    public void setSesNum(String SesNum) {
        this.SesNum = SesNum;
    }

    public String getSesAmt() {
        return SesAmt;
    }

    public void setSesAmt(String SesAmt) {
        this.SesAmt = SesAmt;
    }

    public String getBalAmt() {
        return BalAmt;
    }

    public void setBalAmt(String BalAmt) {
        this.BalAmt = BalAmt;
    }

    public String getTotalPOAmt() {
        return TotalPOAmt;
    }

    public void setTotalPOAmt(String TotalPOAmt) {
        this.TotalPOAmt = TotalPOAmt;
    }

    public String getProfitCenter() {
        return ProfitCenter;
    }

    public void setProfitCenter(String ProfitCenter) {
        this.ProfitCenter = ProfitCenter;
    }
   
    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String LocationId) {
        this.LocationId = LocationId;
    }
   
   
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
   
   
    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }
      
    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
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

    public String getVendorInvoiceNumber() {
        return VendorInvoiceNumber;
    }

    public void setVendorInvoiceNumber(String VendorInvoiceNumber) {
        this.VendorInvoiceNumber = VendorInvoiceNumber;
    }

    

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getPONumberHdr() {
        return PONumberHdr;
    }

    public void setPONumberHdr(String PONumberHdr) {
        this.PONumberHdr = PONumberHdr;
    }
    
    
    
}