/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.dao.QueryHelper;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.vendor.bean.VendorInputBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class GetLegalSmsTrackerListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetLegalSmsTrackerListQueryHelper.class);
    private LegalInvoiceInputBean lvendorInputBeanObj;

    public GetLegalSmsTrackerListQueryHelper(LegalInvoiceInputBean lvendorInputBeanObj) {
        this.lvendorInputBeanObj = lvendorInputBeanObj;
    }

    public Object getDataObject(ResultSet result) throws Exception {
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        try {
            logger.log(Level.INFO, "GetLegalSmsTrackerListQueryHelper ::: getDataObject() :: method called ::");

            legalInvoiceInputBean.setApplId(result.getInt("APPL_ID"));
            legalInvoiceInputBean.setVendorNumber(result.getString("VENDOR_NUMBER"));
            legalInvoiceInputBean.setVendorName(result.getString("VENDOR_NAME"));
            legalInvoiceInputBean.setCourtCaseNo(result.getString("COURT_CASE_NO"));
            legalInvoiceInputBean.setCaseRefNo(result.getString("CASE_REF_NO"));
            legalInvoiceInputBean.setCourtName(result.getString("COURT_NAME"));
            legalInvoiceInputBean.setCaseDescription(result.getString("CASE_DESCRIPTION"));
            legalInvoiceInputBean.setDealingOfficeCode(result.getString("DEALING_OFFICE_CODE"));
            legalInvoiceInputBean.setDealingOfficeName(result.getString("DEALING_OFFICE_NAME"));
            legalInvoiceInputBean.setPartyNames(result.getString("PARTY_NAMES"));
            legalInvoiceInputBean.setVsPartyNames(result.getString("VS_PARTY_NAMES"));
            legalInvoiceInputBean.setCaseTypeDesc(result.getString("CASE_TYPE_DESC"));
            legalInvoiceInputBean.setInvoiceNumber(result.getString("INVOICE_NUMBER"));
            legalInvoiceInputBean.setInvoiceDate(result.getDate("INVOICE_DATE"));
            legalInvoiceInputBean.setInvoiceAmount(result.getInt("INVOICE_AMOUNT"));
            legalInvoiceInputBean.setVendorInvDate(result.getDate("VENDOR_INV_DATE"));
            legalInvoiceInputBean.setMsedclInwardNo(result.getString("MSEDCL_INWARD_NUMBER"));
            legalInvoiceInputBean.setMsedclInwardDate(result.getDate("MSEDCL_INWARD_DATE"));
            legalInvoiceInputBean.setInvSubmitDate(result.getDate("INV_SUBMIT_DATE"));
            legalInvoiceInputBean.setCreatedById(result.getString("CREATED_BY_ID"));
            legalInvoiceInputBean.setCreatedByDesignation(result.getString("CREATED_BY_DESIGNATION"));
            legalInvoiceInputBean.setCreatedByName(result.getString("CREATED_BY_NAME"));
            legalInvoiceInputBean.setCreatedByUsertype(result.getString("CREATED_BY_USERTYPE"));
            legalInvoiceInputBean.setSaveFlag(result.getString("SAVE_FLAG"));
            legalInvoiceInputBean.setCreatedTimeStamp(result.getDate("CREATED_TIME_STAMP"));
            legalInvoiceInputBean.setUpdatedTimeStamp(result.getDate("UPDATED_TIME_STAMP"));
            legalInvoiceInputBean.setStatus(result.getString("STATUS"));
            legalInvoiceInputBean.setVendorInwardDate(result.getDate("VENDOR_INWARD_DT"));
            legalInvoiceInputBean.setFeeType(result.getString("FEE_TYPE"));
            legalInvoiceInputBean.setRejectReason(result.getString("REASON"));
            legalInvoiceInputBean.setIsWithCourtCaseNo(result.getString("IS_WITH_COURT_CASE_NO"));
            legalInvoiceInputBean.setCorporateOffice(result.getString("CORPORATE_OFFICE"));
             legalInvoiceInputBean.setRegionText(result.getString("REGION_NAME"));
             legalInvoiceInputBean.setRegionCode(result.getString("REGION_ID"));
            legalInvoiceInputBean.setZoneText(result.getString("ZONE_NAME"));
            legalInvoiceInputBean.setZoneCode(result.getString("ZONE_ID"));
            legalInvoiceInputBean.setCircleText(result.getString("CIRCLE_NAME"));
            legalInvoiceInputBean.setCircleCode(result.getString("CIRCLE_ID"));
            legalInvoiceInputBean.setDivisionText(result.getString("DIVISION_NAME"));
            legalInvoiceInputBean.setDivisionCode(result.getString("DIVISION_ID"));
            legalInvoiceInputBean.setSubDivisionText(result.getString("SUB_DIVISION_NAME"));
            legalInvoiceInputBean.setSubDivisionCode(result.getString("SUB_DIVISION_ID"));
            legalInvoiceInputBean.setSectionCode(result.getString("SECTION_ID"));
            legalInvoiceInputBean.setSectionText(result.getString("SECTION_NAME"));
            legalInvoiceInputBean.setSubStationCode(result.getString("SUB_STATION_ID"));
            legalInvoiceInputBean.setSubStationText(result.getString("SUB_STATION_NAME"));
            legalInvoiceInputBean.setStatusFee(result.getString("STATUS_FEE"));
            legalInvoiceInputBean.setParkPostDocNo(result.getString("ZZPARK_POST_DOC_NO"));
            legalInvoiceInputBean.setPayDoneErpDoc(result.getString("ZZPAY_DONE_ERP_DOC"));
            legalInvoiceInputBean.setStartPostDocNo(result.getString("start_post_doc_no"));
            legalInvoiceInputBean.setStartPayDoneErpDoc(result.getString("start_pay_done_erp_doc"));
            legalInvoiceInputBean.setStartPayDoneErpDoc1(result.getString("start_pay_done_erp_doc1"));
            legalInvoiceInputBean.setMobileNo(result.getString("MOBILE_NO"));
            legalInvoiceInputBean.setEmailId(result.getString("EMAIL_ID"));
            legalInvoiceInputBean.setUTR_NO(result.getString("ZZUTR_NO"));
            legalInvoiceInputBean.setPaymentDate(result.getString("ZZFEE_DT_OF_PAYMENT"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLegalSmsTrackerListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return legalInvoiceInputBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            sql.append(" SELECT distinct SUBMIT_AT_LOCATION,P.PO_NUMBER,LOCATION,OFFICE_CODE,S.APPL_ID,PURCHASING_GROUP,P.VENDOR_NUMBER,P.VENDOR_NAME,S.VENDOR_EMAILID,VENDOR_CONTACT_NO,VENDOR_INVOICE_NUMBER,INV_NO,SORMDATE,INV_DT,(invoice_approval_date+1)invoice_approval_date,INVOICE_STATUS,NVL(TECHNICAL_SMS_SENT,' ') TECHNICAL_SMS_SENT ,NVL(ACCOUNTS_SMS_SENT,' ')ACCOUNTS_SMS_SENT, NVL(CASHIER_SMS_SENT ,' ')CASHIER_SMS_SENT ,    ");
            sql.append(" NVL(EMP_TECH_SMS_SENT,' ') EMP_TECH_SMS_SENT ,NVL(EMP_ACC_SMS_SENT,' ')EMP_ACC_SMS_SENT, NVL(EMP_PAYM_SMS_SENT ,' ')EMP_PAYM_SMS_SENT     ");
            sql.append("  FROM summary_status P,SMS_SENT_TRACKER S ");
            sql.append(" where  p.vendor_number = s.vendor_number  ");
            sql.append(" AND TO_DATE(TRUNC(NVL(P.PROC_INSERT_DATE,SYSDATE))) = TO_DATE(TRUNC(SYSDATE)) ");
            sql.append(" AND INVOICE_STATUS IN('Pending With Technical','Pending With Accounts','Pending For Payment') ");
            sql.append(" AND INV_NO=REGEXP_REPLACE(UPPER(s.vendor_invoice_number ), '[^0-9A-Za-z]', '') ");//MSEDCL INV NUMBER TO MAKE SURE THAT ONLY SAP DATA IS CAPTURED FOR STATUS 
            sql.append(" AND P.APPL_ID=S.APPL_ID AND P.PURCHASING_GROUP IS NOT NULL ");
            sql.append("   UNION ");
            //C.PURCHASING GROUP TO FETCH PURCHASING GROUP FROM PO LINE INV DETAILS TABLE 
             sql.append("  SELECT distinct SUBMIT_AT_LOCATION,P.PO_NUMBER,LOCATION,P.OFFICE_CODE,S.APPL_ID,C.PURCHASING_GROUP,P.VENDOR_NUMBER,P.VENDOR_NAME,S.VENDOR_EMAILID,VENDOR_CONTACT_NO, ");
            sql.append("  VENDOR_INVOICE_NUMBER,INV_NO,SORMDATE,INV_DT,(invoice_approval_date+1)invoice_approval_date,INVOICE_STATUS,NVL(TECHNICAL_SMS_SENT,' ') TECHNICAL_SMS_SENT , ");
   sql.append("  NVL(ACCOUNTS_SMS_SENT,' ')ACCOUNTS_SMS_SENT, NVL(CASHIER_SMS_SENT ,' ')CASHIER_SMS_SENT ,   "); 
   sql.append("    NVL(EMP_TECH_SMS_SENT,' ') EMP_TECH_SMS_SENT ,NVL(EMP_ACC_SMS_SENT,' ')EMP_ACC_SMS_SENT, NVL(EMP_PAYM_SMS_SENT ,' ')EMP_PAYM_SMS_SENT     ");
  sql.append("   FROM summary_status P,SMS_SENT_TRACKER S ,PO_LINE_INV_DETAILS_ORG C ");
  sql.append("   where  p.vendor_number = s.vendor_number  ");
    sql.append(" AND TO_DATE(TRUNC(NVL(P.PROC_INSERT_DATE,SYSDATE))) = TO_DATE(TRUNC(SYSDATE)) ");
   sql.append("  AND INVOICE_STATUS IN('Pending With Technical','Pending With Accounts','Pending For Payment') ");
  sql.append("   AND INV_NO=REGEXP_REPLACE(UPPER(s.vendor_invoice_number ), '[^0-9A-Za-z]', '') ");
 sql.append("   AND P.APPL_ID=S.APPL_ID AND P.PURCHASING_GROUP IS NOT NULL ");
  sql.append("  and S.APPL_ID=C.APPL_ID ");
         /*   sql.append(" UNION ALL ");
            sql.append("  SELECT distinct SUBMIT_AT_LOCATION,LOCATION,OFFICE_CODE,S.APPL_ID,P.PURCHASING_GROUP ,P.VENDOR_NUMBER,P.VENDOR_NAME,S.VENDOR_EMAILID,VENDOR_CONTACT_NO,VENDOR_INVOICE_NUMBER,VENDOR_INV_NO,INV_CREATIONDATE ");
            sql.append("  ,(CASE WHEN acc_date LIKE '01-01-01' THEN NULL ELSE acc_date end )acc_date, ");
            sql.append(" (invoice_approval_date+1)invoice_approval_date,INVOICE_STATUS,NVL(TECHNICAL_SMS_SENT,' ') TECHNICAL_SMS_SENT ,NVL(ACCOUNTS_SMS_SENT,' ')ACCOUNTS_SMS_SENT, NVL(CASHIER_SMS_SENT ,' ')CASHIER_SMS_SENT,    ");
            sql.append(" NVL(EMP_TECH_SMS_SENT,' ') EMP_TECH_SMS_SENT ,NVL(EMP_ACC_SMS_SENT,' ')EMP_ACC_SMS_SENT, NVL(EMP_PAYM_SMS_SENT ,' ')EMP_PAYM_SMS_SENT     ");
            sql.append(" FROM PS_VENDOR_VERIFIED_INPUT_LIST P,SMS_SENT_TRACKER S  ");
            sql.append("  where  p.vendor_number = s.vendor_number   ");
            sql.append("     AND TO_DATE(TRUNC(NVL(P.PROC_INSERT_DATE,SYSDATE))) = TO_DATE(TRUNC(SYSDATE))  ");
                         sql.append(" AND INVOICE_STATUS IN('Pending With Technical','Pending With Accounts','Pending For Payment') ");

            sql.append("     AND REGEXP_REPLACE(UPPER(VENDOR_INV_NO ), '[^0-9A-Za-z]', '')=REGEXP_REPLACE(UPPER(s.vendor_invoice_number ), '[^0-9A-Za-z]', '') ");
            sql.append("  AND   P.APPL_ID=S.APPL_ID ");*/
            logger.log(Level.INFO, "GetSmsTrackerListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());

            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetSmsTrackerListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
