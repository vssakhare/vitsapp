/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.legal.dao.helper.queryHelper;

import in.emp.dao.QueryHelper;
import in.emp.legal.bean.LegalInvoiceInputBean;
import in.emp.util.TaxAmountDisplayFromSap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class GetLegalEmailSmsTrackerListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetLegalSmsTrackerListQueryHelper.class);
    private LegalInvoiceInputBean lvendorInputBeanObj;
    public GetLegalEmailSmsTrackerListQueryHelper() {

    }
    public GetLegalEmailSmsTrackerListQueryHelper(LegalInvoiceInputBean lvendorInputBeanObj) {
        this.lvendorInputBeanObj = lvendorInputBeanObj;
    }

    public Object getDataObject(ResultSet result) throws Exception {
        LegalInvoiceInputBean legalInvoiceInputBean = new LegalInvoiceInputBean();
        try {
            logger.log(Level.INFO, "GetLegalSmsTrackerListQueryHelper ::: getDataObject() :: method called ::");
            legalInvoiceInputBean.setInvoiceNumber(result.getString("INVOICE_NUMBER"));
            legalInvoiceInputBean.setMobileNo(result.getString("MOBILE_NO"));
            legalInvoiceInputBean.setEmailId(result.getString("EMAIL_ID"));
            legalInvoiceInputBean.setStatusFee(result.getString("STATUS_FEE"));
            legalInvoiceInputBean.setParkPostDocNo(result.getString("ZZPARK_POST_DOC_NO"));
            legalInvoiceInputBean.setPayDoneErpDoc(result.getString("ZZPAY_DONE_ERP_DOC"));
            legalInvoiceInputBean.setStartPostDocNo(result.getString("start_post_doc_no"));
            legalInvoiceInputBean.setStartPayDoneErpDoc(result.getString("start_pay_done_erp_doc"));
            legalInvoiceInputBean.setStartPayDoneErpDoc1(result.getString("start_pay_done_erp_doc1"));
            
            legalInvoiceInputBean.setCashSmsEmailSent(result.getString("CASH_SMS_EMAIL_SENT"));
            legalInvoiceInputBean.setPaySmsEmailSent(result.getString("PAY_SMS_EMAIL_SENT"));
            legalInvoiceInputBean.setPayAdjSmsEmailSent(result.getString("PAY_ADJ_SMS_EMAIL_SENT"));
            legalInvoiceInputBean.setPayDocSmsEmailSent(result.getString("PAY_DOC_REVRSD_SMS_EMAIL_SENT"));
            legalInvoiceInputBean.setFeeType(result.getString("sFee_type"));
            legalInvoiceInputBean.setApplId(result.getInt("APPL_ID"));
            legalInvoiceInputBean.setTechnicalUpdated(result.getString("TECHNICAL_UPDATED"));
            legalInvoiceInputBean.setAccSmsEmailSent(result.getString("ACC_SMS_EMAIL_SENT"));
            legalInvoiceInputBean.setFiscalYear(result.getString("ZZPOST_FISCAL"));
           
           /* 
            legalInvoiceInputBean.setUTR_NO(result.getString("ZZUTR_NO"));
            legalInvoiceInputBean.setPaymentDate(result.getString("ZZFEE_DT_OF_PAYMENT"));
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
            
            legalInvoiceInputBean.setRejectReason(result.getString("REASON"));
            legalInvoiceInputBean.setIsWithCourtCaseNo(result.getString("IS_WITH_COURT_CASE_NO"));
            legalInvoiceInputBean.setCorporateOffice(result.getString("CORPORATE_OFFICE"));
            legalInvoiceInputBean.setZoneText(result.getString("ZONE"));
            legalInvoiceInputBean.setDivisionText(result.getString("DIVISION"));            
            
            */
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
            sql.append("    select x.*,zf.STATUS_FEE,zf.ZZPOST_FISCAL,zf.ZZPARK_POST_DOC_NO,zf.ZZPAY_DONE_ERP_DOC,substr(zf.ZZPARK_POST_DOC_NO,1,2) as start_post_doc_no, substr(zf.ZZPAY_DONE_ERP_DOC,1,2) as start_pay_done_erp_doc,substr(zf.zzpay_done_erp_doc,1,3) AS start_pay_done_erp_doc1 \n" +
                     "    from  (SELECT	LD.APPL_ID,ld.MOBILE_NO,ld.EMAIL_ID, ld.INVOICE_DATE,ld.INVOICE_NUMBER,ld.CASE_REF_NO,ld.VENDOR_NUMBER,f.FEE_TYPE as sFee_type 	,f.SAP_STATUS	SAP_STATUS,f.TECHNICAL_UPDATED,f.ACC_SMS_EMAIL_SENT,\n" +
                    "  CASH_SMS_EMAIL_SENT,PAY_SMS_EMAIL_SENT,PAY_ADJ_SMS_EMAIL_SENT,PAY_DOC_REVRSD_SMS_EMAIL_SENT \n" +
                    "   from xxmis_erp_legal_invoice_details LD join   xxmis_erp_legal_invoice_fee_type_dtls  f on  \n" +
                    " f.APPL_ID=LD.APPL_ID,xxmis_organization_master OM  where nvl(LD.dealing_office_code,261)=OM.organization_id  	\n" +																		
                    "  and ld.SAVE_FLAG='Accepted')x LEFT JOIN zhrt_legal_fee zf \n" +
                    " ON to_number(x.vendor_number) = zf.vendor AND  x.case_ref_no = zf.caserefno  AND x.invoice_number = zf.invoice_legal AND \n" +
                    " x.invoice_date = zf.invoice_date  AND x.sFee_type = zf.adv_fee_type\n");
     
            logger.log(Level.INFO, "GetLegalEmailSmsTrackerListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());

            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetLegalEmailSmsTrackerListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
