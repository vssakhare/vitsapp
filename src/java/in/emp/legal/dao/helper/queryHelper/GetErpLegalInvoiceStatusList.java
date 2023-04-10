/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.dao.QueryHelper;
import in.emp.legal.bean.LegalInvoiceBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Rikma Rai
 */
public class GetErpLegalInvoiceStatusList implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetErpLegalInvoiceStatusList.class);
    private LegalInvoiceBean legalInvoiceBean = null;

    public GetErpLegalInvoiceStatusList(LegalInvoiceBean legalInvoiceBean) {
        this.legalInvoiceBean = legalInvoiceBean;
    }

//    public GetErpLegalInvoiceStatusList(VendorPrezData vendorPrezDataObj) {
//        this.vendorPrezDataObj = vendorPrezDataObj;
//        this.vendorBean = this.vendorPrezDataObj.getVendorBean();
//    }
    public Object getDataObject(ResultSet result) throws Exception {
        LegalInvoiceBean legalInvoiceBean = new LegalInvoiceBean();
        try {
            if (this.legalInvoiceBean.getWhereClause().equals("Emp")) {
                legalInvoiceBean.setVENDOR(result.getString("VENDOR"));
            } else if (this.legalInvoiceBean.getWhereClause().equalsIgnoreCase("InvNo")) {
                legalInvoiceBean.setINVOICE_LEGAL(result.getString("INVOICE_NUMBER"));
            } else if (this.legalInvoiceBean.getWhereClause().equalsIgnoreCase("locn")) {
                legalInvoiceBean.setOfficeName(result.getString("LOC"));
            } else if (this.legalInvoiceBean.getWhereClause().startsWith("CaseRefNo")) {
                legalInvoiceBean.setCASEREFNO(result.getInt("CASEREFNO"));
            } else if (this.legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNoNew")) {
                
                logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getDataObject() :: method called ::");
                legalInvoiceBean.setCASEREFNO(result.getInt("CASEREFNO"));                
                legalInvoiceBean.setDOF_LC(result.getDate("DOF_LC"));
                legalInvoiceBean.setCASENO(result.getString("CASENO"));
                legalInvoiceBean.setCASENOCOURT(result.getString("CASENOCOURT"));
                legalInvoiceBean.setCOURTNAME(result.getString("COURTNAME"));
                legalInvoiceBean.setCASETYPE(result.getString("CASETYPE"));
                legalInvoiceBean.setCASETYPEDESC(result.getString("CASETYPEDESC"));
                legalInvoiceBean.setCASEDET(result.getString("CASEDET"));                
                legalInvoiceBean.setOfficeName((result.getString("OFFICE_NAME")));
                legalInvoiceBean.setMsedclPartyName(result.getString("MSEDCL_PARTY_NAME"));
                legalInvoiceBean.setVsPartyName(result.getString("VS_PARTY_NAME"));
            } else {//System.out.println("in else now...");
                logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getDataObject() :: method called ::");
                legalInvoiceBean.setCASEREFNO(result.getInt("CASEREFNO"));
                legalInvoiceBean.setYEAR_L(result.getInt("YEAR_L"));
                legalInvoiceBean.setDOF_LC(result.getDate("DOF_LC"));
                legalInvoiceBean.setVENDOR(result.getString("VENDOR"));
                legalInvoiceBean.setINVOICE_LEGAL(result.getString("INVOICE_LEGAL"));
                legalInvoiceBean.setADV_FEE_TYPE(result.getString("ADV_FEE_TYPE"));
                legalInvoiceBean.setINVOICE_DATE(result.getDate("INVOICE_DATE"));
                legalInvoiceBean.setADVOCATE_NAME(result.getString("ADVOCATE_NAME"));
                legalInvoiceBean.setADVOCATE_TYPE(result.getString("ADVOCATE_TYPE"));
                legalInvoiceBean.setINVOICE_AMOUNT(result.getDouble("INVOICE_AMOUNT"));
                legalInvoiceBean.setRECIEPT_DATE(result.getDate("RECIEPT_DATE"));
                legalInvoiceBean.setFEE_RECOMMENDED(result.getDouble("FEE_RECOMMENDED"));
                legalInvoiceBean.setREASON_FOR_DEDUCTION(result.getString("REASON_FOR_DEDUCTION"));
                legalInvoiceBean.setAPPROVED_BY(result.getString("APPROVED_BY"));
                legalInvoiceBean.setDATE_OF_APPROVAL(result.getDate("DATE_OF_APPROVAL"));
                legalInvoiceBean.setDATE_OF_SUBMISSION(result.getDate("DATE_OF_SUBMISSION"));
                legalInvoiceBean.setSAC_CODE(result.getString("SAC_CODE"));
                legalInvoiceBean.setSTATUS_FEE(result.getString("STATUS_FEE"));
                legalInvoiceBean.setZZADV_PAN_NO(result.getString("ZZADV_PAN_NO"));
                legalInvoiceBean.setZZELIGIBLE_FEE(result.getDouble("ZZELIGIBLE_FEE"));
                legalInvoiceBean.setZZPARK_POST_DOC_NO(result.getString("ZZPARK_POST_DOC_NO"));
                legalInvoiceBean.setZZPARK_DOC_AMT(result.getDouble("ZZPARK_DOC_AMT"));
                legalInvoiceBean.setZZPARK_POST_DATE(result.getDate("ZZPARK_POST_DATE"));
                legalInvoiceBean.setZZPAY_DONE_ERP_DOC(result.getString("ZZPAY_DONE_ERP_DOC"));
                legalInvoiceBean.setZZPAY_DOC_AMT(result.getDouble("ZZPAY_DOC_AMT"));
                legalInvoiceBean.setZZFEE_DT_OF_PAYMENT(result.getDate("ZZFEE_DT_OF_PAYMENT"));
                legalInvoiceBean.setZZFI_COMP_CODE(result.getString("ZZFI_COMP_CODE"));
                legalInvoiceBean.setZZFI_FIS_YR(result.getInt("ZZFI_FIS_YR"));
                legalInvoiceBean.setZZPOST_DATE(result.getDate("ZZPOST_DATE"));
                legalInvoiceBean.setZZPOST_FISCAL(result.getInt("ZZPOST_FISCAL"));
                legalInvoiceBean.setZZUTR_NO(result.getString("ZZUTR_NO"));
                legalInvoiceBean.setCASENO(result.getString("CASENO"));
                legalInvoiceBean.setCASENOCOURT(result.getString("CASENOCOURT"));
                legalInvoiceBean.setCORT_KEY(result.getInt("CORT_KEY"));
                legalInvoiceBean.setCOURTNAME(result.getString("COURTNAME"));
                legalInvoiceBean.setCOOFFICE_BTRTL(result.getString("COOFFICE_BTRTL"));
                legalInvoiceBean.setCOOFFICE_TEXT(result.getString("COOFFICE_TEXT"));
                legalInvoiceBean.setREGION_BTRTL(result.getString("REGION_BTRTL"));
                legalInvoiceBean.setREGION_BTRTL_TEXT(result.getString("REGION_BTRTL_TEXT"));
                legalInvoiceBean.setZZONE_BTRTL(result.getString("ZZONE_BTRTL"));
                legalInvoiceBean.setZZONE_BTRTL_TEXT(result.getString("ZZONE_BTRTL_TEXT"));
                legalInvoiceBean.setCIRCLE_BTRTL(result.getString("CIRCLE_BTRTL"));
                legalInvoiceBean.setCIRCLE_BTRTL_TEXT(result.getString("CIRCLE_BTRTL_TEXT"));
                legalInvoiceBean.setDIVISION_BTRTL(result.getString("DIVISION_BTRTL"));
                legalInvoiceBean.setDIVISION_BTRTL_TEXT(result.getString("DIVISION_BTRTL_TEXT"));
                legalInvoiceBean.setSUBDIV_BTRTL(result.getString("SUBDIV_BTRTL"));
                legalInvoiceBean.setSUBDIV_BTRTL_TEXT(result.getString("SUBDIV_BTRTL_TEXT"));
                legalInvoiceBean.setSECTION_BTRTL(result.getString("SECTION_BTRTL"));
                legalInvoiceBean.setSECTION_BTRTL_TEXT(result.getString("SECTION_BTRTL_TEXT"));
                legalInvoiceBean.setSUBSTATION(result.getString("SUBSTATION"));
                legalInvoiceBean.setSUBSTATION_TEXT(result.getString("SUBSTATION_TEXT"));
                legalInvoiceBean.setDSD(result.getString("DSD"));
                legalInvoiceBean.setCASETYPE(result.getString("CASETYPE"));
                legalInvoiceBean.setCASETYPEDESC(result.getString("CASETYPEDESC"));
                legalInvoiceBean.setCASEDET(result.getString("CASEDET"));
                legalInvoiceBean.setCASESTAT(result.getInt("CASESTAT"));
                legalInvoiceBean.setCASESTATDESC(result.getString("CASESTATDESC"));
//            legalInvoiceBean.setCreatedDt(result.getDate("created_Dt"));
//            legalInvoiceBean.setCreatedBy(result.getString("Created_By"));
                legalInvoiceBean.setOfficeCode((result.getString("OFFICE_CODE")));
                legalInvoiceBean.setOfficeName((result.getString("OFFICE_NAME")));
                legalInvoiceBean.setMsedclPartyName(result.getString("MSEDCL_PARTY_NAME"));
                legalInvoiceBean.setVsPartyName(result.getString("VS_PARTY_NAME"));
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvoiceStatusList :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return legalInvoiceBean;
    }

    /*    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        int i = 1;
        try {
            logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getQueryResults() :: method called ::");
//            sql.append(" SELECT * FROM ERP_LEGAL_INVOICE_STATUS ");
            if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendor") || legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNo")) {
                sql.append(" select CASEREFNO , YEAR_L , DOF_LC, VENDOR  , INVOICE_LEGAL  , ADV_FEE_TYPE  ,");
                sql.append(" INVOICE_DATE, ADVOCATE_NAME  , ADVOCATE_TYPE  , INVOICE_AMOUNT , ");
                sql.append(" RECIEPT_DATE, FEE_RECOMMENDED , REASON_FOR_DEDUCTION  , APPROVED_BY  , ");
                sql.append(" DATE_OF_APPROVAL, DATE_OF_SUBMISSION, SAC_CODE  , STATUS_FEE  , ZZADV_PAN_NO  ,");
                sql.append(" ZZELIGIBLE_FEE , ZZPARK_POST_DOC_NO  , ZZPARK_DOC_AMT , ");
                sql.append(" ZZPARK_POST_DATE, ZZPAY_DONE_ERP_DOC  , ZZPAY_DOC_AMT , ");
                sql.append(" ZZFEE_DT_OF_PAYMENT, ZZFI_COMP_CODE  , ZZFI_FIS_YR , ZZPOST_DATE, ZZPOST_FISCAL ");
                sql.append(" , ZZUTR_NO  , CASENO  , CASENOCOURT  , CORT_KEY , COURTNAME  , COOFFICE_BTRTL  , ");
                sql.append(" COOFFICE_TEXT  , REGION_BTRTL  , REGION_BTRTL_TEXT  , ZZONE_BTRTL  ,");
                sql.append(" ZZONE_BTRTL_TEXT  , CIRCLE_BTRTL  , CIRCLE_BTRTL_TEXT  , DIVISION_BTRTL  , ");
                sql.append(" DIVISION_BTRTL_TEXT  , SUBDIV_BTRTL  , SUBDIV_BTRTL_TEXT  , SECTION_BTRTL  , ");
                sql.append(" SECTION_BTRTL_TEXT  , SUBSTATION  , SUBSTATION_TEXT  , DSD  , CASETYPE ,");
                sql.append(" CASETYPEDESC  , CASEDET  , CASESTAT , CASESTATDESC  , CREATED_DT, CREATED_BY  ,");
                sql.append(" nvl(COOFFICE_BTRTL,(NVL(REGION_BTRTL,NVL(ZZONE_BTRTL,NVL(CIRCLE_BTRTL,NVL(DIVISION_BTRTL,NVL(SUBDIV_BTRTL,NVL(SECTION_BTRTL,SECTION_BTRTL)))))))) OFFICE_CODE,");
                sql.append(" nvl(COOFFICE_TEXT,(NVL(REGION_BTRTL_TEXT,NVL(ZZONE_BTRTL_TEXT,NVL(CIRCLE_BTRTL_TEXT,NVL(DIVISION_BTRTL_TEXT,NVL(SUBDIV_BTRTL_TEXT,NVL(SECTION_BTRTL_TEXT,SECTION_BTRTL_TEXT)))))))) OFFICE_NAME");
                sql.append(" FROM erp_legal_invoice_status");

                if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendor")) {
                    sql.append(" WHERE VENDOR=? ");
                } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNo")) {
                    sql.append(" WHERE VENDOR=? AND CASENOCOURT=?");
                }
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("Emp")) {
                		sql.append(" select  distinct null CASEREFNO , null YEAR_L , null DOF_LC,  VENDOR  , null INVOICE_LEGAL  , null ADV_FEE_TYPE  , ");
                sql.append(" null INVOICE_DATE, null ADVOCATE_NAME  , null ADVOCATE_TYPE  , null INVOICE_AMOUNT ,  ");
                sql.append(" null RECIEPT_DATE,null  FEE_RECOMMENDED ,null  REASON_FOR_DEDUCTION  ,null  APPROVED_BY  ,  ");
                sql.append(" null DATE_OF_APPROVAL,null  DATE_OF_SUBMISSION,null  SAC_CODE  , null STATUS_FEE  ,null  ZZADV_PAN_NO  , ");
                sql.append(" null ZZELIGIBLE_FEE , null ZZPARK_POST_DOC_NO  , null ZZPARK_DOC_AMT ,  ");
                sql.append(" null ZZPARK_POST_DATE, null ZZPAY_DONE_ERP_DOC  ,null  ZZPAY_DOC_AMT ,  ");
                sql.append(" null ZZFEE_DT_OF_PAYMENT,null  ZZFI_COMP_CODE  ,null  ZZFI_FIS_YR , null ZZPOST_DATE, null ZZPOST_FISCAL  ");
                sql.append(" , null ZZUTR_NO  , null CASENO  , null CASENOCOURT  ,null  CORT_KEY ,null  COURTNAME  , null COOFFICE_BTRTL  ,  ");
                sql.append(" null COOFFICE_TEXT  , null REGION_BTRTL  ,null  REGION_BTRTL_TEXT  , null ZZONE_BTRTL  , ");
                sql.append(" null ZZONE_BTRTL_TEXT  ,null  CIRCLE_BTRTL  , null CIRCLE_BTRTL_TEXT  , null DIVISION_BTRTL  ,  ");
                sql.append(" null DIVISION_BTRTL_TEXT  , null SUBDIV_BTRTL  , null SUBDIV_BTRTL_TEXT  ,null  SECTION_BTRTL  ,  ");
                sql.append(" null SECTION_BTRTL_TEXT  , null SUBSTATION  , null SUBSTATION_TEXT  , null DSD  , null CASETYPE , ");
                sql.append(" null CASETYPEDESC  , null CASEDET  , null CASESTAT , null CASESTATDESC  , null CREATED_DT, null CREATED_BY  , ");
                sql.append(" null OFFICE_CODE, ");
                sql.append(" null  OFFICE_NAME ");
                sql.append(" FROM erp_legal_invoice_status ");
                sql.append(" WHERE nvl(COOFFICE_BTRTL,(NVL(REGION_BTRTL,NVL(ZZONE_BTRTL,NVL(CIRCLE_BTRTL,NVL(DIVISION_BTRTL,NVL(SUBDIV_BTRTL,NVL(SECTION_BTRTL,SECTION_BTRTL))))))))=? ");
            }
            statement = connection.prepareStatement(sql.toString());
            if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendor")) {
                statement.setString(1, legalInvoiceBean.getVENDOR());
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNo")) {
                statement.setString(1, legalInvoiceBean.getVENDOR());
                statement.setString(2, legalInvoiceBean.getCASENOCOURT());
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("Emp")) {
                statement.setString(1, legalInvoiceBean.getLocationId());
            }
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvoiceStatusList :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }*/
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        //System.out.println("legalInvoiceBean.getWhereClause() "+legalInvoiceBean.getWhereClause());
        ResultSet rs = null;
        int i = 1;
        try {
            logger.log(Level.INFO, "GetErpLegalInvoiceStatusList ::: getQueryResults() :: method called ::");
//            sql.append(" SELECT * FROM ERP_LEGAL_INVOICE_STATUS ");
            if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendor") || legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNo") || legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseRefNo")) {
                sql.append(" select  ZLH.caserefno, ZLH.year_l, ZLH.dof_lc, ZLF.vendor, ZLF.invoice_legal, ");
                sql.append(" ZLF.adv_fee_type, ZLF.invoice_date, ZLF.advocate_name, ZLF.advocate_type, ");
                sql.append(" ZLF.invoice_amount, ZLF.reciept_date, ZLF.fee_recommended, ");
                sql.append(" ZLF.reason_for_deduction, ZLF.approved_by, ZLF.date_of_approval, ");
                sql.append(" ZLF.date_of_submission, ZLF.sac_code, ZLF.status_fee, ZLF.zzadv_pan_no, ");
                sql.append(" ZLF.zzeligible_fee, ZLF.zzpark_post_doc_no, ZLF.zzpark_doc_amt, ");
                sql.append(" ZLF.zzpark_post_date, ZLF.zzpay_done_erp_doc, ZLF.zzpay_doc_amt, ");
                sql.append(" ZLF.zzfee_dt_of_payment, ZLF.zzfi_comp_code, ZLF.zzfi_fis_yr, ZLF.zzpost_date, ");
                sql.append(" ZLF.zzpost_fiscal, ZLF.zzutr_no, ZLH.caseno, ZLH.casenocourt, CORTN.CORT_KEY ");
                sql.append(" cort_key, nvl(CORTN.COURTNAMEDESC,'N.A.') courtname, ZLH.cooffice_btrtl, ZLH.cooffice_text, ");
                sql.append(" ZLH.region_btrtl, ZLH.region_btrtl_text, ZLH.zzone_btrtl, ZLH.zzone_btrtl_text, ");
                sql.append(" ZLH.circle_btrtl, ZLH.circle_btrtl_text, ZLH.division_btrtl, ");
                sql.append(" ZLH.division_btrtl_text, ZLH.subdiv_btrtl, ZLH.subdiv_btrtl_text, ");
                sql.append(" ZLH.section_btrtl, ZLH.section_btrtl_text, ZLH.substation, ZLH.substation_text, ");
                sql.append(" orgm.organization_name OFFICE_NAME,");
                sql.append(" orgm.organization_id OFFICE_CODE, ");
                sql.append(" ZLH.dsd, ");
                sql.append(" (CASE WHEN ZLH.CASETYPE=0 THEN 60 ELSE ZLH.CASETYPE END)casetype,");
                sql.append(" ZLCT.casetypedesc, ");
                sql.append(" ZLH.casedet,");
                sql.append(" (CASE WHEN ZLH.CASESTAT_LC=0 THEN 10 ELSE  ZLH.CASESTAT_LC END)CASESTAT,");
                sql.append(" ZLCS.casestatdesc,nvl(zmprt.MSEDCL_PARTY_NAME,'N.A.') MSEDCL_PARTY_NAME,");
                sql.append(" nvl(zmvsprt.VS_PARTY_NAME,'N.A.') VS_PARTY_NAME");
                sql.append(" from ");
                sql.append(" ZHRT_LEGAL_H  ZLH,");
                sql.append(" ZHRT_LEGAL_FEE ZLF,");
                sql.append(" (SELECT ");
                sql.append(" CASEREFNO,ZLCNM.CORT_KEY,ZLCNM.COURTNAMEDESC");
                sql.append(" FROM ");
                sql.append(" ZHRT_LE_COURT ZLCO,ZHRT_LE_COURTNAM ZLCNM ");
                sql.append(" WHERE ZLCNM.CORT_KEY=ZLCO.COURTNAME ");
                sql.append(" GROUP BY CASEREFNO,ZLCNM.CORT_KEY,ZLCNM.COURTNAMEDESC ");
                sql.append(" )CORTN, ");
                sql.append(" ZHRT_LE_CASETYPE ZLCT, ");
                sql.append(" ZHRT_LE_CASESTAT ZLCS, ");
                sql.append(" ZHRT_LE_FILED ZLVF, ");
                sql.append(" xxmis_organization_master ORGM, ");
                sql.append(" xxmis_location_master lOCM, ");
                sql.append(" (SELECT CASEREFNO,LISTAGG(VS,',') WITHIN GROUP (ORDER BY TO_NUMBER(SRNO)) MSEDCL_PARTY_NAME");
                sql.append(" FROM ZHRT_MSEDCL_PRTY ");
                sql.append(" GROUP BY CASEREFNO ");
                sql.append(" )ZMPRT, ");
                sql.append(" (SELECT CASEREFNO,LISTAGG(VS,',') WITHIN GROUP (ORDER BY TO_NUMBER(SRNO)) VS_PARTY_NAME");
                sql.append(" FROM ZHRT_LE_PARTY ");
                sql.append(" GROUP BY CASEREFNO ");
                sql.append(" )ZMVSPRT ");
                sql.append(" WHERE ZLH.CASEREFNO=ZLF.CASEREFNO(+) ");
                sql.append(" AND  ZLH.CASEREFNO=CORTN.CASEREFNO(+) ");
                sql.append(" AND  (CASE WHEN ZLH.CASETYPE=0 THEN 60 ELSE ZLH.CASETYPE END)=ZLCT.CASETYPE(+) ");
                sql.append(" AND  (CASE WHEN ZLH.CASESTAT_LC=0 THEN 10 ELSE  ZLH.CASESTAT_LC END)=ZLCS.CASESTAT ");
                sql.append(" AND ZLH.CASEREFNO=ZLVF.CASEREFNO(+) ");
                sql.append(" and orgm.organization_id=locm.org_id ");
                sql.append(" AND ZLH.CASEREFNO=ZMPRT.CASEREFNO(+) AND ZLH.CASEREFNO = ZMVSPRT.CASEREFNO (+) ");
                sql.append(" and lOCM.Personal_Area=(case when ZLH.cooffice_btrtl is not null then '0001' ");
                sql.append("      when ZLH.region_btrtl  is not null  then '0002' ");
                sql.append("      when ZLH.zzone_btrtl   is not null  then '0003' ");
                sql.append("      when ZLH.circle_btrtl  is not null  then '0004' ");
                sql.append("      when ZLH.division_btrtl is not null then '0005' ");
                sql.append("      when ZLH.subdiv_btrtl  is not null then  '0006' ");
                sql.append("      when ZLH.section_btrtl is not null then  '0007'  ");
                sql.append("      when ZLH.substation is not null then     '0008'  ");
                sql.append(" end )   ");
                sql.append(" and lOCM.Personal_Subarea=nvl(COOFFICE_BTRTL,(NVL(REGION_BTRTL,NVL(ZZONE_BTRTL,NVL(CIRCLE_BTRTL,NVL(DIVISION_BTRTL,NVL(SUBDIV_BTRTL,NVL(SECTION_BTRTL,substation))))))))  ");

                if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendor")) {
                    sql.append(" AND VENDOR=? ");
                } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNo")) {
                    sql.append(" AND VENDOR=? AND CASENOCOURT=?");
                } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseRefNo")) {
                    sql.append(" AND VENDOR=? AND ZLH.caserefno=?");
               } } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNoNew")) {
                    
                    sql.append(" select distinct ZLH.caserefno, ZLH.dof_lc, ");
                sql.append(" ZLH.caseno, ZLH.casenocourt, ");
                sql.append(" nvl(CORTN.COURTNAMEDESC,'N.A.') courtname, orgm.organization_name OFFICE_NAME,");
                sql.append(" orgm.organization_id OFFICE_CODE, ");                
                sql.append(" (CASE WHEN ZLH.CASETYPE=0 THEN 60 ELSE ZLH.CASETYPE END)casetype,");
                sql.append(" ZLCT.casetypedesc, ");
                sql.append(" ZLH.casedet,nvl(zmprt.MSEDCL_PARTY_NAME,'N.A.') MSEDCL_PARTY_NAME,");
                sql.append(" nvl(zmvsprt.VS_PARTY_NAME,'N.A.') VS_PARTY_NAME");
                sql.append(" from ");
                sql.append(" ZHRT_LEGAL_H  ZLH,");
                sql.append(" ZHRT_LEGAL_FEE ZLF,");
                sql.append(" (SELECT ");
                sql.append(" CASEREFNO,ZLCNM.CORT_KEY,ZLCNM.COURTNAMEDESC");
                sql.append(" FROM ");
                sql.append(" ZHRT_LE_COURT ZLCO,ZHRT_LE_COURTNAM ZLCNM ");
                sql.append(" WHERE ZLCNM.CORT_KEY=ZLCO.COURTNAME ");
                sql.append(" GROUP BY CASEREFNO,ZLCNM.CORT_KEY,ZLCNM.COURTNAMEDESC ");
                sql.append(" )CORTN, ");
                sql.append(" ZHRT_LE_CASETYPE ZLCT, ");
                sql.append(" ZHRT_LE_CASESTAT ZLCS, ");
                sql.append(" ZHRT_LE_FILED ZLVF, ");
                sql.append(" xxmis_organization_master ORGM, ");
                sql.append(" xxmis_location_master lOCM, ");
                sql.append(" (SELECT CASEREFNO,LISTAGG(VS,',') WITHIN GROUP (ORDER BY TO_NUMBER(SRNO)) MSEDCL_PARTY_NAME");
                sql.append(" FROM ZHRT_MSEDCL_PRTY ");
                sql.append(" GROUP BY CASEREFNO ");
                sql.append(" )ZMPRT, ");
                sql.append(" (SELECT CASEREFNO,LISTAGG(VS,',') WITHIN GROUP (ORDER BY TO_NUMBER(SRNO)) VS_PARTY_NAME");
                sql.append(" FROM ZHRT_LE_PARTY ");
                sql.append(" GROUP BY CASEREFNO ");
                sql.append(" )ZMVSPRT ");
                sql.append(" WHERE ZLH.CASEREFNO=ZLF.CASEREFNO(+) ");
                sql.append(" AND  ZLH.CASEREFNO=CORTN.CASEREFNO(+) ");
                sql.append(" AND  (CASE WHEN ZLH.CASETYPE=0 THEN 60 ELSE ZLH.CASETYPE END)=ZLCT.CASETYPE(+) ");
                sql.append(" AND  (CASE WHEN ZLH.CASESTAT_LC=0 THEN 10 ELSE  ZLH.CASESTAT_LC END)=ZLCS.CASESTAT ");
                sql.append(" AND ZLH.CASEREFNO=ZLVF.CASEREFNO(+) ");
                sql.append(" and orgm.organization_id=locm.org_id ");
                sql.append(" AND ZLH.CASEREFNO=ZMPRT.CASEREFNO(+) AND ZLH.CASEREFNO = ZMVSPRT.CASEREFNO (+) ");
                sql.append(" and lOCM.Personal_Area=(case when ZLH.cooffice_btrtl is not null then '0001' ");
                sql.append("      when ZLH.region_btrtl  is not null  then '0002' ");
                sql.append("      when ZLH.zzone_btrtl   is not null  then '0003' ");
                sql.append("      when ZLH.circle_btrtl  is not null  then '0004' ");
                sql.append("      when ZLH.division_btrtl is not null then '0005' ");
                sql.append("      when ZLH.subdiv_btrtl  is not null then  '0006' ");
                sql.append("      when ZLH.section_btrtl is not null then  '0007'  ");
                sql.append("      when ZLH.substation is not null then     '0008'  ");
                sql.append(" end )   ");
                sql.append(" and lOCM.Personal_Subarea=nvl(COOFFICE_BTRTL,(NVL(REGION_BTRTL,NVL(ZZONE_BTRTL,NVL(CIRCLE_BTRTL,NVL(DIVISION_BTRTL,NVL(SUBDIV_BTRTL,NVL(SECTION_BTRTL,substation))))))))  ");
                    
                    
                    sql.append(" AND VENDOR=? AND CASENOCOURT LIKE ('%' || ? || '%')");
                
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("Emp")) {
//                sql.append(" select  distinct null caserefno, null year_l, null dof_lc, ZLF.vendor, null  ");
//                sql.append(" invoice_legal, null adv_fee_type, null invoice_date, null advocate_name, null  ");
//                sql.append(" advocate_type, null invoice_amount, null reciept_date, null fee_recommended,  ");
//                sql.append(" null reason_for_deduction, null approved_by, null date_of_approval, null  ");
//                sql.append(" date_of_submission, null sac_code, null status_fee, null zzadv_pan_no, null  ");
//                sql.append(" zzeligible_fee, null zzpark_post_doc_no, null zzpark_doc_amt, null  ");
//                sql.append(" zzpark_post_date, null zzpay_done_erp_doc, null zzpay_doc_amt, null  ");
//                sql.append(" zzfee_dt_of_payment, null zzfi_comp_code, null zzfi_fis_yr, null zzpost_date,  ");
//                sql.append(" null zzpost_fiscal, null zzutr_no, null caseno, null casenocourt, null CORT_KEY  ");
//                sql.append(" , null COURTNAMEDESC , null cooffice_btrtl, null cooffice_text, null  ");
//                sql.append(" region_btrtl, null region_btrtl_text, null zzone_btrtl, null zzone_btrtl_text,  ");
//                sql.append(" null circle_btrtl, null circle_btrtl_text, null division_btrtl, null  ");
//                sql.append(" division_btrtl_text, null subdiv_btrtl, null subdiv_btrtl_text, null  ");
//                sql.append(" section_btrtl, null section_btrtl_text, null substation, null substation_text,  ");
//                sql.append(" null OFFICE_NAME, null OFFICE_CODE ,   null dsd, null casetype, null  ");
//                sql.append(" casetypedesc, null casedet, null casestat, null casestatdesc, null  ");
//                sql.append(" MSEDCL_PARTY_NAME ");
//                sql.append(" from ZHRT_LEGAL_H  ZLH,ZHRT_LEGAL_FEE ZLF, ");
//                sql.append(" (SELECT CASEREFNO,ZLCNM.CORT_KEY,ZLCNM.COURTNAMEDESC ");
//                sql.append(" FROM ZHRT_LE_COURT ZLCO,ZHRT_LE_COURTNAM ZLCNM ");
//                sql.append(" WHERE ZLCNM.CORT_KEY=ZLCO.COURTNAME ");
//                sql.append(" GROUP BY CASEREFNO,ZLCNM.CORT_KEY,ZLCNM.COURTNAMEDESC ");
//                sql.append(" )CORTN,ZHRT_LE_CASETYPE ZLCT,ZHRT_LE_CASESTAT ZLCS,ZHRT_LE_FILED ZLVF, ");
//                sql.append(" xxmis_organization_master ORGM,xxmis_location_master lOCM, ");
//                sql.append(" (SELECT CASEREFNO,LISTAGG(VS,',')  WITHIN GROUP (ORDER BY TO_NUMBER(SRNO)) MSEDCL_PARTY_NAME ");
//                sql.append(" FROM ZHRT_MSEDCL_PRTYGROUP BY CASEREFNO ");
//                sql.append(" )ZMPRT ");
//                sql.append(" WHERE ZLH.CASEREFNO=ZLF.CASEREFNO(+) ");
//                sql.append(" AND  ZLH.CASEREFNO=CORTN.CASEREFNO(+) ");
//                sql.append(" AND  (CASE WHEN ZLH.CASETYPE=0 THEN 60 ELSE ZLH.CASETYPE END)=ZLCT.CASETYPE(+) ");
//                sql.append(" AND  (CASE WHEN ZLH.CASESTAT_LC=0 THEN 10 ELSE  ZLH.CASESTAT_LC END)=ZLCS.CASESTAT ");
//                sql.append(" AND ZLH.CASEREFNO=ZLVF.CASEREFNO(+) ");
//                sql.append(" and orgm.organization_id=locm.org_id ");
//                sql.append(" AND ZLH.CASEREFNO=ZMPRT.CASEREFNO(+) ");
//                sql.append(" and lOCM.Personal_Area=(case when ZLH.cooffice_btrtl is not null then '0001' ");
//                sql.append("      when ZLH.region_btrtl  is not null  then '0002' ");
//                sql.append("      when ZLH.zzone_btrtl   is not null  then '0003' ");
//                sql.append("      when ZLH.circle_btrtl  is not null  then '0004' ");
//                sql.append("      when ZLH.division_btrtl is not null then '0005' ");
//                sql.append("      when ZLH.subdiv_btrtl  is not null then  '0006' ");
//                sql.append("      when ZLH.section_btrtl is not null then  '0007'  ");
//                sql.append("      when ZLH.substation is not null then     '0008'  ");
//                sql.append(" end )   ");
//                sql.append(" and lOCM.Personal_Subarea=nvl(COOFFICE_BTRTL,(NVL(REGION_BTRTL,NVL(ZZONE_BTRTL,NVL(CIRCLE_BTRTL,NVL(DIVISION_BTRTL,NVL(SUBDIV_BTRTL,NVL(SECTION_BTRTL,substation))))))))  ");
//                sql.append(" and orgm.organization_id=? ");
                /*sql.append(" select distinct zlvf.FILED_BY VENDOR ");
                sql.append("  from ZHRT_LEGAL_H  ZLH,ZHRT_LE_FILED ZLVF, ");
                sql.append("  xxmis_organization_master ORGM,xxmis_location_master lOCM ");
                sql.append(" where  ZLH.CASEREFNO=ZLVF.CASEREFNO ");
                sql.append(" and orgm.organization_id=locm.org_id ");
                sql.append("  and lOCM.Personal_Area=(case when ZLH.cooffice_btrtl is not null then '0001' ");
                sql.append(" when ZLH.region_btrtl  is not null  then '0002' ");
                sql.append(" when ZLH.zzone_btrtl   is not null  then '0003' ");
                sql.append(" when ZLH.circle_btrtl  is not null  then '0004' ");
                sql.append(" when ZLH.division_btrtl is not null then '0005' ");
                sql.append(" when ZLH.subdiv_btrtl  is not null then  '0006' ");
                sql.append(" when ZLH.section_btrtl is not null then  '0007'  ");
                sql.append(" when ZLH.substation is not null then     '0008'  ");
                sql.append(" end )   ");
                sql.append(" and lOCM.Personal_Subarea=nvl(COOFFICE_BTRTL,(NVL(REGION_BTRTL,NVL(ZZONE_BTRTL,NVL(CIRCLE_BTRTL,NVL(DIVISION_BTRTL,NVL(SUBDIV_BTRTL,NVL(SECTION_BTRTL,substation))))))))  ");
                sql.append(" and orgm.organization_id=? ");*/
                sql.append("SELECT distinct vendor_number vendor FROM XXMIS_ERP_LEGAL_INVOICE_DETAILS where dealing_office_code=?");
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("InvNo")) {
                sql.append("SELECT distinct INVOICE_NUMBER FROM XXMIS_ERP_LEGAL_INVOICE_DETAILS where dealing_office_code=?");
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("locn")) {
                sql.append("SELECT distinct dealing_office_name loc FROM XXMIS_ERP_LEGAL_INVOICE_DETAILS where dealing_office_code=?");
            } else if (legalInvoiceBean.getWhereClause().startsWith("CaseRefNo")) {
                sql.append("        select distinct zlvf.CASEREFNO   ");
                sql.append("         from ZHRT_LEGAL_H  ZLH,ZHRT_LE_FILED ZLVF,  ");
                sql.append("         xxmis_organization_master ORGM,xxmis_location_master lOCM  ");
                sql.append("         where  ZLH.CASEREFNO=ZLVF.CASEREFNO  ");
                sql.append("         and orgm.organization_id=locm.org_id  ");
                sql.append("     and lOCM.Personal_Area=(case when ZLH.cooffice_btrtl is not null then '0001'  ");
                sql.append("      when ZLH.region_btrtl  is not null  then '0002'  ");
                sql.append("      when ZLH.zzone_btrtl   is not null  then '0003'  ");
                sql.append("      when ZLH.circle_btrtl  is not null  then '0004'  ");
                sql.append("      when ZLH.division_btrtl is not null then '0005'  ");
                sql.append("      when ZLH.subdiv_btrtl  is not null then  '0006'  ");
                sql.append("      when ZLH.section_btrtl is not null then  '0007'   ");
                sql.append("      when ZLH.substation is not null then     '0008'  ");
                sql.append(" end )    ");
                sql.append(" and lOCM.Personal_Subarea=nvl(COOFFICE_BTRTL,(NVL(REGION_BTRTL,NVL(ZZONE_BTRTL,NVL(CIRCLE_BTRTL,NVL(DIVISION_BTRTL,NVL(SUBDIV_BTRTL,NVL(SECTION_BTRTL,substation))))))))   ");
                if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("CaseRefNo")) {
                sql.append(" and (Region_id=? or zone_id=? or circle_id=? or division_id=? or sub_division_id=? or (OFFICE_TYPE='HO' and organization_id=? ) )  ");}
                else {
                sql.append(" and '0'||zlvf.filed_by = ? ");
                }
                

            }
            statement = connection.prepareStatement(sql.toString());
            System.out.println(sql.toString());
            if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendor")) {
                statement.setString(1, legalInvoiceBean.getVENDOR().substring(1));
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNo")|| legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseNoNew")) {
                statement.setString(1, legalInvoiceBean.getVENDOR().substring(1));
                statement.setString(2, legalInvoiceBean.getCASENOCOURT());
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("Emp")) {
                statement.setString(1, legalInvoiceBean.getLocationId());
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("InvNo")) {
                statement.setString(1, legalInvoiceBean.getLocationId());
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("locn")) {
                statement.setString(1, legalInvoiceBean.getLocationId());
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("CaseRefNoV")) {
                   statement.setString(1, legalInvoiceBean.getVENDOR());
                   System.out.println(legalInvoiceBean.getVENDOR());
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("CaseRefNo")) {
                statement.setString(i++, legalInvoiceBean.getLocationId());
                statement.setString(i++, legalInvoiceBean.getLocationId());
                statement.setString(i++, legalInvoiceBean.getLocationId());
                statement.setString(i++, legalInvoiceBean.getLocationId());
                statement.setString(i++, legalInvoiceBean.getLocationId());
                statement.setString(i++, legalInvoiceBean.getLocationId());
                //statement.setString(i++, legalInvoiceBean.getVENDOR().substring(1));
            } else if (legalInvoiceBean.getWhereClause().equalsIgnoreCase("vendorCaseRefNo")) {
                statement.setString(1, legalInvoiceBean.getVENDOR().substring(1));
                statement.setInt(2, legalInvoiceBean.getCASEREFNO());
            }
            //System.out.println(legalInvoiceBean.getWhereClause()+" "+legalInvoiceBean.getVENDOR().substring(1)+" "+legalInvoiceBean.getCASEREFNO());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetErpLegalInvoiceStatusList :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        //System.out.println("qry executed sfully!");
        return rs;
    }
}
