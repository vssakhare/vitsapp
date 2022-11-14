/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

import in.emp.dao.QueryHelper;
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
public class GetSmsTrackerListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetSmsTrackerListQueryHelper.class);
    private VendorInputBean vendorInputBeanObj;

    public GetSmsTrackerListQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "GetSmsTrackerListQueryHelper ::: getDataObject() :: method called ::");

            vendorBeanObj.setVendorInvoiceNumber(results.getString("VENDOR_INVOICE_NUMBER"));
            vendorBeanObj.setPONumber(results.getString("PO_NUMBER"));
            vendorBeanObj.setMsedclInvoiceNumber(results.getString("INV_NO"));
            vendorBeanObj.setVendorNumber(results.getString("vendor_number"));
            vendorBeanObj.setVendorName(results.getString("VENDOR_NAME"));
            vendorBeanObj.setEmailId(results.getString("VENDOR_EMAILID"));
            vendorBeanObj.setContactNumber(results.getString("VENDOR_CONTACT_NO"));
            vendorBeanObj.setVendorInvoiceDate(results.getDate("INV_DT"));
            vendorBeanObj.setSormDate(results.getDate("SORMDATE"));
            vendorBeanObj.setStatus(results.getString("INVOICE_STATUS"));
            vendorBeanObj.setLocation(results.getString("LOCATION"));
            vendorBeanObj.setTechnical_sms_flag(results.getString("TECHNICAL_SMS_SENT"));
            vendorBeanObj.setAccounts_sms_flag(results.getString("ACCOUNTS_SMS_SENT"));
            vendorBeanObj.setCashier_sms_flag(results.getString("CASHIER_SMS_SENT"));
            vendorBeanObj.setEmp_tech_sms_flag(results.getString("EMP_TECH_SMS_SENT"));
            vendorBeanObj.setEmp_Acc_sms_flag(results.getString("EMP_ACC_SMS_SENT"));
          //  vendorBeanObj.setEmp_Cash_sms_flag(results.getString("EMP_PAYM_SMS_SENT"));
             vendorBeanObj.setPurchasing_group(results.getString("PURCHASING_GROUP"));
             vendorBeanObj.setApplId(results.getString("APPL_ID"));
                     vendorBeanObj.setOffice_Code(results.getString("OFFICE_CODE"));
                  vendorBeanObj.setInvoiceApprDAte(results.getDate("invoice_approval_date"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetSmsTrackerListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
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
