/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.bean;

import java.util.Date;

/**
 *
 * @author Rikma Rai
 */
public class LegalInvoiceBean implements java.io.Serializable{
    	private Integer CASEREFNO ;
	private Integer YEAR_L ;
	private Date DOF_LC ; 
	private String VENDOR ;
	private String INVOICE_LEGAL;
	private String ADV_FEE_TYPE ;
	private Date INVOICE_DATE  ;
	private String ADVOCATE_NAME ;
	private String ADVOCATE_TYPE ;
	private Double INVOICE_AMOUNT ;
	private Date RECIEPT_DATE  ;
	private Double FEE_RECOMMENDED ;
	private String REASON_FOR_DEDUCTION ;
	private String APPROVED_BY ;
	private Date DATE_OF_APPROVAL ; 
	private Date DATE_OF_SUBMISSION  ;
	private String SAC_CODE ;
	private String STATUS_FEE ;
	private String ZZADV_PAN_NO ;
	private Double ZZELIGIBLE_FEE ;
	private String ZZPARK_POST_DOC_NO;
	private Double ZZPARK_DOC_AMT ;
	private Date ZZPARK_POST_DATE  ;
	private String ZZPAY_DONE_ERP_DOC ;
	private Double ZZPAY_DOC_AMT;
	private Date ZZFEE_DT_OF_PAYMENT ; 
	private String ZZFI_COMP_CODE   ;
	private int ZZFI_FIS_YR; 
	private Date ZZPOST_DATE ; 
	private int ZZPOST_FISCAL;
	private String ZZUTR_NO  ;
	private String CASENO   ;
	private String CASENOCOURT ;  
	private Integer CORT_KEY ;
	private String COURTNAME ;  
	private String COOFFICE_BTRTL;   
	private String COOFFICE_TEXT ;  
	private String REGION_BTRTL ;  
	private String REGION_BTRTL_TEXT ;  
	private String ZZONE_BTRTL ;  
	private String ZZONE_BTRTL_TEXT   ;
	private String CIRCLE_BTRTL  ; 
	private String CIRCLE_BTRTL_TEXT ;  
	private String DIVISION_BTRTL ;  
	private String DIVISION_BTRTL_TEXT ;  
	private String SUBDIV_BTRTL  ; 
	private String SUBDIV_BTRTL_TEXT ;  
	private String SECTION_BTRTL  ; 
	private String SECTION_BTRTL_TEXT  ; 
	private String SUBSTATION   ;
	private String SUBSTATION_TEXT ;  
	private String DSD  ; 
	private String CASETYPE  ;
	private String CASETYPEDESC ;
	private String CASEDET ;
	private int CASESTAT ;
	private String CASESTATDESC ;
        private Date createdDt;
        private String createdBy;
        private String whereClause;
        private String officeCode;
        private String officeName;
        private String locationId;
        private String msedclPartyName;
        private String vsPartyName;
private String OfficeType;
        private String RegionId;
    public String getOfficeType() {
        return OfficeType;
    }

    public void setOfficeType(String OfficeType) {
        this.OfficeType = OfficeType;
    }

    public String getRegionId() {
        return RegionId;
    }

    public void setRegionId(String RegionId) {
        this.RegionId = RegionId;
    }

   

    public String getMsedclPartyName() {
        return msedclPartyName;
    }

    public void setMsedclPartyName(String msedclPartyName) {
        this.msedclPartyName = msedclPartyName;
    }
        
    public String getVsPartyName() {
        return vsPartyName;
    }

    public void setVsPartyName(String vsPartyName) {
        this.vsPartyName = vsPartyName;
    }    

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
        
        

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
        
        

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public Integer getCASEREFNO() {
        return CASEREFNO;
    }

    public void setCASEREFNO(Integer CASEREFNO) {
        this.CASEREFNO = CASEREFNO;
    }

    public Integer getYEAR_L() {
        return YEAR_L;
    }

    public void setYEAR_L(Integer YEAR_L) {
        this.YEAR_L = YEAR_L;
    }

    public Date getDOF_LC() {
        return DOF_LC;
    }

    public void setDOF_LC(Date DOF_LC) {
        this.DOF_LC = DOF_LC;
    }

    public String getVENDOR() {
        return VENDOR;
    }

    public void setVENDOR(String VENDOR) {
        this.VENDOR = VENDOR;
    }

    public String getINVOICE_LEGAL() {
        return INVOICE_LEGAL;
    }

    public void setINVOICE_LEGAL(String INVOICE_LEGAL) {
        this.INVOICE_LEGAL = INVOICE_LEGAL;
    }

    public String getADV_FEE_TYPE() {
        return ADV_FEE_TYPE;
    }

    public void setADV_FEE_TYPE(String ADV_FEE_TYPE) {
        this.ADV_FEE_TYPE = ADV_FEE_TYPE;
    }

    public Date getINVOICE_DATE() {
        return INVOICE_DATE;
    }

    public void setINVOICE_DATE(Date INVOICE_DATE) {
        this.INVOICE_DATE = INVOICE_DATE;
    }

    public String getADVOCATE_NAME() {
        return ADVOCATE_NAME;
    }

    public void setADVOCATE_NAME(String ADVOCATE_NAME) {
        this.ADVOCATE_NAME = ADVOCATE_NAME;
    }

    public String getADVOCATE_TYPE() {
        return ADVOCATE_TYPE;
    }

    public void setADVOCATE_TYPE(String ADVOCATE_TYPE) {
        this.ADVOCATE_TYPE = ADVOCATE_TYPE;
    }

    public Double getINVOICE_AMOUNT() {
        return INVOICE_AMOUNT;
    }

    public void setINVOICE_AMOUNT(Double INVOICE_AMOUNT) {
        this.INVOICE_AMOUNT = INVOICE_AMOUNT;
    }

    public Date getRECIEPT_DATE() {
        return RECIEPT_DATE;
    }

    public void setRECIEPT_DATE(Date RECIEPT_DATE) {
        this.RECIEPT_DATE = RECIEPT_DATE;
    }

    public Double getFEE_RECOMMENDED() {
        return FEE_RECOMMENDED;
    }

    public void setFEE_RECOMMENDED(Double FEE_RECOMMENDED) {
        this.FEE_RECOMMENDED = FEE_RECOMMENDED;
    }

    public String getREASON_FOR_DEDUCTION() {
        return REASON_FOR_DEDUCTION;
    }

    public void setREASON_FOR_DEDUCTION(String REASON_FOR_DEDUCTION) {
        this.REASON_FOR_DEDUCTION = REASON_FOR_DEDUCTION;
    }

    public String getAPPROVED_BY() {
        return APPROVED_BY;
    }

    public void setAPPROVED_BY(String APPROVED_BY) {
        this.APPROVED_BY = APPROVED_BY;
    }

    public Date getDATE_OF_APPROVAL() {
        return DATE_OF_APPROVAL;
    }

    public void setDATE_OF_APPROVAL(Date DATE_OF_APPROVAL) {
        this.DATE_OF_APPROVAL = DATE_OF_APPROVAL;
    }

    public Date getDATE_OF_SUBMISSION() {
        return DATE_OF_SUBMISSION;
    }

    public void setDATE_OF_SUBMISSION(Date DATE_OF_SUBMISSION) {
        this.DATE_OF_SUBMISSION = DATE_OF_SUBMISSION;
    }

    public String getSAC_CODE() {
        return SAC_CODE;
    }

    public void setSAC_CODE(String SAC_CODE) {
        this.SAC_CODE = SAC_CODE;
    }

    public String getSTATUS_FEE() {
        return STATUS_FEE;
    }

    public void setSTATUS_FEE(String STATUS_FEE) {
        this.STATUS_FEE = STATUS_FEE;
    }

    public String getZZADV_PAN_NO() {
        return ZZADV_PAN_NO;
    }

    public void setZZADV_PAN_NO(String ZZADV_PAN_NO) {
        this.ZZADV_PAN_NO = ZZADV_PAN_NO;
    }

    public Double getZZELIGIBLE_FEE() {
        return ZZELIGIBLE_FEE;
    }

    public void setZZELIGIBLE_FEE(Double ZZELIGIBLE_FEE) {
        this.ZZELIGIBLE_FEE = ZZELIGIBLE_FEE;
    }

    public String getZZPARK_POST_DOC_NO() {
        return ZZPARK_POST_DOC_NO;
    }

    public void setZZPARK_POST_DOC_NO(String ZZPARK_POST_DOC_NO) {
        this.ZZPARK_POST_DOC_NO = ZZPARK_POST_DOC_NO;
    }

    public Double getZZPARK_DOC_AMT() {
        return ZZPARK_DOC_AMT;
    }

    public void setZZPARK_DOC_AMT(Double ZZPARK_DOC_AMT) {
        this.ZZPARK_DOC_AMT = ZZPARK_DOC_AMT;
    }

    public Date getZZPARK_POST_DATE() {
        return ZZPARK_POST_DATE;
    }

    public void setZZPARK_POST_DATE(Date ZZPARK_POST_DATE) {
        this.ZZPARK_POST_DATE = ZZPARK_POST_DATE;
    }

    public String getZZPAY_DONE_ERP_DOC() {
        return ZZPAY_DONE_ERP_DOC;
    }

    public void setZZPAY_DONE_ERP_DOC(String ZZPAY_DONE_ERP_DOC) {
        this.ZZPAY_DONE_ERP_DOC = ZZPAY_DONE_ERP_DOC;
    }

    public Double getZZPAY_DOC_AMT() {
        return ZZPAY_DOC_AMT;
    }

    public void setZZPAY_DOC_AMT(Double ZZPAY_DOC_AMT) {
        this.ZZPAY_DOC_AMT = ZZPAY_DOC_AMT;
    }

    public Date getZZFEE_DT_OF_PAYMENT() {
        return ZZFEE_DT_OF_PAYMENT;
    }

    public void setZZFEE_DT_OF_PAYMENT(Date ZZFEE_DT_OF_PAYMENT) {
        this.ZZFEE_DT_OF_PAYMENT = ZZFEE_DT_OF_PAYMENT;
    }

    public String getZZFI_COMP_CODE() {
        return ZZFI_COMP_CODE;
    }

    public void setZZFI_COMP_CODE(String ZZFI_COMP_CODE) {
        this.ZZFI_COMP_CODE = ZZFI_COMP_CODE;
    }

    public int getZZFI_FIS_YR() {
        return ZZFI_FIS_YR;
    }

    public void setZZFI_FIS_YR(int ZZFI_FIS_YR) {
        this.ZZFI_FIS_YR = ZZFI_FIS_YR;
    }

    public Date getZZPOST_DATE() {
        return ZZPOST_DATE;
    }

    public void setZZPOST_DATE(Date ZZPOST_DATE) {
        this.ZZPOST_DATE = ZZPOST_DATE;
    }

    public int getZZPOST_FISCAL() {
        return ZZPOST_FISCAL;
    }

    public void setZZPOST_FISCAL(int ZZPOST_FISCAL) {
        this.ZZPOST_FISCAL = ZZPOST_FISCAL;
    }

    public String getZZUTR_NO() {
        return ZZUTR_NO;
    }

    public void setZZUTR_NO(String ZZUTR_NO) {
        this.ZZUTR_NO = ZZUTR_NO;
    }

    public String getCASENO() {
        return CASENO;
    }

    public void setCASENO(String CASENO) {
        this.CASENO = CASENO;
    }

    public String getCASENOCOURT() {
        return CASENOCOURT;
    }

    public void setCASENOCOURT(String CASENOCOURT) {
        this.CASENOCOURT = CASENOCOURT;
    }

    public Integer getCORT_KEY() {
        return CORT_KEY;
    }

    public void setCORT_KEY(Integer CORT_KEY) {
        this.CORT_KEY = CORT_KEY;
    }

    public String getCOURTNAME() {
        return COURTNAME;
    }

    public void setCOURTNAME(String COURTNAME) {
        this.COURTNAME = COURTNAME;
    }

    public String getCOOFFICE_BTRTL() {
        return COOFFICE_BTRTL;
    }

    public void setCOOFFICE_BTRTL(String COOFFICE_BTRTL) {
        this.COOFFICE_BTRTL = COOFFICE_BTRTL;
    }

    public String getCOOFFICE_TEXT() {
        return COOFFICE_TEXT;
    }

    public void setCOOFFICE_TEXT(String COOFFICE_TEXT) {
        this.COOFFICE_TEXT = COOFFICE_TEXT;
    }

    public String getREGION_BTRTL() {
        return REGION_BTRTL;
    }

    public void setREGION_BTRTL(String REGION_BTRTL) {
        this.REGION_BTRTL = REGION_BTRTL;
    }

    public String getREGION_BTRTL_TEXT() {
        return REGION_BTRTL_TEXT;
    }

    public void setREGION_BTRTL_TEXT(String REGION_BTRTL_TEXT) {
        this.REGION_BTRTL_TEXT = REGION_BTRTL_TEXT;
    }

    public String getZZONE_BTRTL() {
        return ZZONE_BTRTL;
    }

    public void setZZONE_BTRTL(String ZZONE_BTRTL) {
        this.ZZONE_BTRTL = ZZONE_BTRTL;
    }

    public String getZZONE_BTRTL_TEXT() {
        return ZZONE_BTRTL_TEXT;
    }

    public void setZZONE_BTRTL_TEXT(String ZZONE_BTRTL_TEXT) {
        this.ZZONE_BTRTL_TEXT = ZZONE_BTRTL_TEXT;
    }

    public String getCIRCLE_BTRTL() {
        return CIRCLE_BTRTL;
    }

    public void setCIRCLE_BTRTL(String CIRCLE_BTRTL) {
        this.CIRCLE_BTRTL = CIRCLE_BTRTL;
    }

    public String getCIRCLE_BTRTL_TEXT() {
        return CIRCLE_BTRTL_TEXT;
    }

    public void setCIRCLE_BTRTL_TEXT(String CIRCLE_BTRTL_TEXT) {
        this.CIRCLE_BTRTL_TEXT = CIRCLE_BTRTL_TEXT;
    }

    public String getDIVISION_BTRTL() {
        return DIVISION_BTRTL;
    }

    public void setDIVISION_BTRTL(String DIVISION_BTRTL) {
        this.DIVISION_BTRTL = DIVISION_BTRTL;
    }

    public String getDIVISION_BTRTL_TEXT() {
        return DIVISION_BTRTL_TEXT;
    }

    public void setDIVISION_BTRTL_TEXT(String DIVISION_BTRTL_TEXT) {
        this.DIVISION_BTRTL_TEXT = DIVISION_BTRTL_TEXT;
    }

    public String getSUBDIV_BTRTL() {
        return SUBDIV_BTRTL;
    }

    public void setSUBDIV_BTRTL(String SUBDIV_BTRTL) {
        this.SUBDIV_BTRTL = SUBDIV_BTRTL;
    }

    public String getSUBDIV_BTRTL_TEXT() {
        return SUBDIV_BTRTL_TEXT;
    }

    public void setSUBDIV_BTRTL_TEXT(String SUBDIV_BTRTL_TEXT) {
        this.SUBDIV_BTRTL_TEXT = SUBDIV_BTRTL_TEXT;
    }

    public String getSECTION_BTRTL() {
        return SECTION_BTRTL;
    }

    public void setSECTION_BTRTL(String SECTION_BTRTL) {
        this.SECTION_BTRTL = SECTION_BTRTL;
    }

    public String getSECTION_BTRTL_TEXT() {
        return SECTION_BTRTL_TEXT;
    }

    public void setSECTION_BTRTL_TEXT(String SECTION_BTRTL_TEXT) {
        this.SECTION_BTRTL_TEXT = SECTION_BTRTL_TEXT;
    }

    public String getSUBSTATION() {
        return SUBSTATION;
    }

    public void setSUBSTATION(String SUBSTATION) {
        this.SUBSTATION = SUBSTATION;
    }

    public String getSUBSTATION_TEXT() {
        return SUBSTATION_TEXT;
    }

    public void setSUBSTATION_TEXT(String SUBSTATION_TEXT) {
        this.SUBSTATION_TEXT = SUBSTATION_TEXT;
    }

    public String getDSD() {
        return DSD;
    }

    public void setDSD(String DSD) {
        this.DSD = DSD;
    }

    public String getCASETYPE() {
        return CASETYPE;
    }

    public void setCASETYPE(String CASETYPE) {
        this.CASETYPE = CASETYPE;
    }

    public String getCASETYPEDESC() {
        return CASETYPEDESC;
    }

    public void setCASETYPEDESC(String CASETYPEDESC) {
        this.CASETYPEDESC = CASETYPEDESC;
    }

    public String getCASEDET() {
        return CASEDET;
    }

    public void setCASEDET(String CASEDET) {
        this.CASEDET = CASEDET;
    }

    public int getCASESTAT() {
        return CASESTAT;
    }

    public void setCASESTAT(int CASESTAT) {
        this.CASESTAT = CASESTAT;
    }

    public String getCASESTATDESC() {
        return CASESTATDESC;
    }

    public void setCASESTATDESC(String CASESTATDESC) {
        this.CASESTATDESC = CASESTATDESC;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

      	
  
}
