/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.bean;

import java.util.Date;

/**
 *
 * @author Pooja Jadhav
 */
public class PSBean  implements java.io.Serializable  {
    private String  PROJECT_DESC              ;
 private Date    CREATION_DATE             ;
private String  SCHEME_CODE              ; 
private String  MATERIAL_PO               ;
 private String  CENTAGES_PO               ;
 private String  SERVICE_PO                ;
 private String  MATERIAL_WS_PARK_DOC       ;
private Date    MAT_WS_DOC_DATE               ;
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
private String  SELECTED_MODULE_TYPE ;
private String PLANT;
private String ZONE;
private String CIRCLE;
private String DIVISION;
private String PROJECT_ID;
private String  SCHEME_DESC;
private String VENDOR_NAME;
private String VENDOR_NUMBER;
private String INVNO;
private String TECH_STAT;
private String OTH_PARK_DOC_NO;
private Date OTH_DOC_DATE;
private Date OTH_AC_DOC_DATE;
private String OTH_PARK_AMT;
private String OTH_CLEARING_DOC_NO;
private String OTH_CLEARING_AMT;
private String INV_STAT;
 private String    PO_MAT_AMT;
 private String    PO_CEN_AMT;
  private String   PO_CIV_AMT;
  private String   PO_INV_AMT;
 private Date   INV_CREATIONDATE;
  private Date  INV_PSDATE;
 private String   ACC_STAT;
 private String TAX_CODE;
 private String IT_TDS;
 private String GST_TDS;
private String RETENSION_AMOUNT;
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
     private String SAP_APPL_ID;
     private String VENDOR_INV_NO;
 private String    ZONE_CODE	  ;
   private String    CIRCLE_CODE  ;
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
   private String PURCHASING_GROUP;
    public String getPURCHASING_GROUP() {
        return PURCHASING_GROUP;
    }

    public void setPURCHASING_GROUP(String PURCHASING_GROUP) {
        this.PURCHASING_GROUP = PURCHASING_GROUP;
    }

    public String getZONE_CODE() {
        return ZONE_CODE;
    }

    public void setZONE_CODE(String ZONE_CODE) {
        this.ZONE_CODE = ZONE_CODE;
    }

    public String getCIRCLE_CODE() {
        return CIRCLE_CODE;
    }

    public void setCIRCLE_CODE(String CIRCLE_CODE) {
        this.CIRCLE_CODE = CIRCLE_CODE;
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
  
      

    public String getSAP_APPL_ID() {
        return SAP_APPL_ID;
    }

    public void setSAP_APPL_ID(String SAP_APPL_ID) {
        this.SAP_APPL_ID = SAP_APPL_ID;
    }

    public String getVENDOR_INV_NO() {
        return VENDOR_INV_NO;
    }

    public void setVENDOR_INV_NO(String VENDOR_INV_NO) {
        this.VENDOR_INV_NO = VENDOR_INV_NO;
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



    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public String getIT_TDS() {
        return IT_TDS;
    }

    public void setIT_TDS(String IT_TDS) {
        this.IT_TDS = IT_TDS;
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
    public String getACC_STAT() {
        return ACC_STAT;
    }

    public void setACC_STAT(String ACC_STAT) {
        this.ACC_STAT = ACC_STAT;
    }
   
    public Date getINV_CREATIONDATE() {
        return INV_CREATIONDATE;
    }

    public void setINV_CREATIONDATE(Date INV_CREATIONDATE) {
        this.INV_CREATIONDATE = INV_CREATIONDATE;
    }

    public Date getINV_PSDATE() {
        return INV_PSDATE;
    }

    public void setINV_PSDATE(Date INV_PSDATE) {
        this.INV_PSDATE = INV_PSDATE;
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

   
    public Date getOTH_DOC_DATE() {
        return OTH_DOC_DATE;
    }

    public void setOTH_DOC_DATE(Date OTH_DOC_DATE) {
        this.OTH_DOC_DATE = OTH_DOC_DATE;
    }

    public Date getOTH_AC_DOC_DATE() {
        return OTH_AC_DOC_DATE;
    }

    public void setOTH_AC_DOC_DATE(Date OTH_AC_DOC_DATE) {
        this.OTH_AC_DOC_DATE = OTH_AC_DOC_DATE;
    }

    public String getINV_STAT() {
        return INV_STAT;
    }

    public void setINV_STAT(String INV_STAT) {
        this.INV_STAT = INV_STAT;
    }



    public String getOTH_PARK_DOC_NO() {
        return OTH_PARK_DOC_NO;
    }

    public void setOTH_PARK_DOC_NO(String OTH_PARK_DOC_NO) {
        this.OTH_PARK_DOC_NO = OTH_PARK_DOC_NO;
    }

  public String getOTH_PARK_AMT() {
        return OTH_PARK_AMT;
    }

    public void setOTH_PARK_AMT(String OTH_PARK_AMT) {
        this.OTH_PARK_AMT = OTH_PARK_AMT;
    }

    public String getOTH_CLEARING_DOC_NO() {
        return OTH_CLEARING_DOC_NO;
    }

    public void setOTH_CLEARING_DOC_NO(String OTH_CLEARING_DOC_NO) {
        this.OTH_CLEARING_DOC_NO = OTH_CLEARING_DOC_NO;
    }

    public String getOTH_CLEARING_AMT() {
        return OTH_CLEARING_AMT;
    }

    public void setOTH_CLEARING_AMT(String OTH_CLEARING_AMT) {
        this.OTH_CLEARING_AMT = OTH_CLEARING_AMT;
    }




 public Date getMAT_WS_DOC_DATE() {
        return MAT_WS_DOC_DATE;
    }

    public void setMAT_WS_DOC_DATE(Date MAT_WS_DOC_DATE) {
        this.MAT_WS_DOC_DATE = MAT_WS_DOC_DATE;
    }
    public String getINVNO() {
        return INVNO;
    }

    public void setINVNO(String INVNO) {
        this.INVNO = INVNO;
    }

    public String getTECH_STAT() {
        return TECH_STAT;
    }

    public void setTECH_STAT(String TECH_STAT) {
        this.TECH_STAT = TECH_STAT;
    }


    public String getVENDOR_NAME() {
        return VENDOR_NAME;
    }

    public void setVENDOR_NAME(String VENDOR_NAME) {
        this.VENDOR_NAME = VENDOR_NAME;
    }

    public String getVENDOR_NUMBER() {
        return VENDOR_NUMBER;
    }

    public void setVENDOR_NUMBER(String VENDOR_NUMBER) {
        this.VENDOR_NUMBER = VENDOR_NUMBER;
    }

    public String getSCHEME_DESC() {
        return SCHEME_DESC;
    }

    public void setSCHEME_DESC(String SCHEME_DESC) {
        this.SCHEME_DESC = SCHEME_DESC;
    }

    public String getPROJECT_ID() {
        return PROJECT_ID;
    }

    public void setPROJECT_ID(String PROJECT_ID) {
        this.PROJECT_ID = PROJECT_ID;
    }


    public String getZONE() {
        return ZONE;
    }

    public void setZONE(String ZONE) {
        this.ZONE = ZONE;
    }

    public String getCIRCLE() {
        return CIRCLE;
    }

    public void setCIRCLE(String CIRCLE) {
        this.CIRCLE = CIRCLE;
    }

    public String getDIVISION() {
        return DIVISION;
    }

    public void setDIVISION(String DIVISION) {
        this.DIVISION = DIVISION;
    }
    public String getPLANT() {
        return PLANT;
    }

    public void setPLANT(String PLANT) {
        this.PLANT = PLANT;
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

    public String getSELECTED_MODULE_TYPE() {
        return SELECTED_MODULE_TYPE;
    }

    public void setSELECTED_MODULE_TYPE(String SELECTED_MODULE_TYPE) {
        this.SELECTED_MODULE_TYPE = SELECTED_MODULE_TYPE;
    }
    
}
