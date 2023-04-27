/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.listHelper;

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
public class GetEscalationSmsStatusQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetEscalationSmsStatusQueryHelper.class);
    private VendorInputBean vendorInputBeanObj;

    public GetEscalationSmsStatusQueryHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "GetEscalationSmsStatusQueryHelper ::: getDataObject() :: method called ::");

            vendorBeanObj.setVendorInvoiceNumber(results.getString("VENDOR_INVOICE_NUMBER"));
            vendorBeanObj.setMsedclInvoiceNumber(results.getString("INV_NO"));
            vendorBeanObj.setVendorNumber(results.getString("vendor_number"));
            vendorBeanObj.setVendorName(results.getString("VENDOR_NAME"));
          //  vendorBeanObj.setEmailId(results.getString("VENDOR_EMAILID"));
           // vendorBeanObj.setContactNumber(results.getString("VENDOR_CONTACT_NO"));
          //  vendorBeanObj.setVendorInvoiceDate(results.getDate("INV_DT"));
            vendorBeanObj.setSormDate(results.getDate("SORMDATE"));
            vendorBeanObj.setStatus(results.getString("INVOICE_STATUS"));
          /*  vendorBeanObj.setTechnical_sms_flag(results.getString("TECHNICAL_SMS_SENT"));
            vendorBeanObj.setAccounts_sms_flag(results.getString("ACCOUNTS_SMS_SENT"));
            vendorBeanObj.setCashier_sms_flag(results.getString("CASHIER_SMS_SENT"));
            vendorBeanObj.setEmp_tech_sms_flag(results.getString("EMP_TECH_SMS_SENT"));
            vendorBeanObj.setEmp_Acc_sms_flag(results.getString("EMP_ACC_SMS_SENT"));
            vendorBeanObj.setEmp_Cash_sms_flag(results.getString("EMP_PAYM_SMS_SENT"));*/
            vendorBeanObj.setPurchasing_group(results.getString("PURCHASING_GROUP"));
            vendorBeanObj.setempName(results.getString("TECH_EMP_NAME"));
            vendorBeanObj.setParent_Office_Code(results.getString("PARENT_OFFICE_CODE"));
            vendorBeanObj.setEsc_tech_sms_flag(results.getString("ESC_TECH_SMS_SENT"));
            vendorBeanObj.setDaysDelayed(results.getString("days_delayed"));
            vendorBeanObj.setEsc_tech_sms_flag(results.getString("ESC_TECH_SMS_SENT"));
            vendorBeanObj.setLocation(results.getString("LOCATION"));
            vendorBeanObj.setApplId(results.getString("APPL_ID"));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetEscalationSmsStatusQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
         sql.append("	(SELECT distinct SUBMIT_AT_LOCATION,LOCATION,S.APPL_ID,PURCHASING_GROUP,PARENT_OFFICE_CODE,P.VENDOR_NUMBER,P.VENDOR_NAME,");
         sql.append("    VENDOR_INVOICE_NUMBER,INV_NO,SORMDATE,INV_DT,INVOICE_STATUS,to_char(EMP_TECH_SMS_DATE, 'yyyy-mm-dd')days_delayed ,    ");
         sql.append("    NVL(EMP_TECH_SMS_SENT,' ') EMP_TECH_SMS_SENT  ,NVL(ESC_TECH_SMS_SENT,' ') ESC_TECH_SMS_SENT  , EMP_TECH_SMS_DATE ,TECH_EMP_CPF,TECH_EMP_NAME ");
         sql.append("     FROM summary_status P,SMS_SENT_TRACKER S ");
         sql.append("    where  p.vendor_number = s.vendor_number  ");
         sql.append("   AND TO_NUMBER(trunc(sysdate) - trunc(EMP_TECH_SMS_DATE))-(SELECT EMPPORTAL.XXMIS_EMP_NOOFHOLIDAYS(trunc(EMP_TECH_SMS_DATE),sysdate,TECH_EMP_CPF) FROM DUAL) >7 "); 
         sql.append("    AND REGEXP_REPLACE(UPPER(INV_NO ), '[^0-9A-Za-z]', '')=REGEXP_REPLACE(UPPER(s.vendor_invoice_number ), '[^0-9A-Za-z]', '') ");
         sql.append("   AND P.APPL_ID=S.APPL_ID AND INVOICE_STATUS='Pending With Technical'   ");
         sql.append("   and  ESC_TECH_SMS_SENT is null and EMP_TECH_SMS_SENT='Y' AND  PURCHASING_GROUP IS NOT NULL   ");
           sql.append("			UNION 																   ");
  sql.append("          	SELECT distinct SUBMIT_AT_LOCATION,LOCATION,S.APPL_ID,C.PURCHASING_GROUP,PARENT_OFFICE_CODE,P.VENDOR_NUMBER,P.VENDOR_NAME,	   ");
  sql.append("        VENDOR_INVOICE_NUMBER,INV_NO,SORMDATE,INV_DT,INVOICE_STATUS,to_char(EMP_TECH_SMS_DATE, 'yyyy-mm-dd')days_delayed ,    			   ");
  sql.append("        NVL(EMP_TECH_SMS_SENT,' ') EMP_TECH_SMS_SENT  ,NVL(ESC_TECH_SMS_SENT,' ') ESC_TECH_SMS_SENT  , EMP_TECH_SMS_DATE ,TECH_EMP_CPF,TECH_EMP_NAME ");
  sql.append("         FROM summary_status P,SMS_SENT_TRACKER S ,PO_LINE_INV_DETAILS C								   ");
  sql.append("        where  p.vendor_number = s.vendor_number  												   ");
    sql.append("   AND TO_NUMBER(trunc(sysdate) - trunc(EMP_TECH_SMS_DATE))-(SELECT EMPPORTAL.XXMIS_EMP_NOOFHOLIDAYS(trunc(EMP_TECH_SMS_DATE),sysdate,TECH_EMP_CPF) FROM DUAL) >7  "); 												  

  sql.append("        AND REGEXP_REPLACE(UPPER(INV_NO ), '[^0-9A-Za-z]', '')=REGEXP_REPLACE(UPPER(s.vendor_invoice_number ), '[^0-9A-Za-z]', '') 								   ");
  sql.append("       AND P.APPL_ID=S.APPL_ID AND S.APPL_ID=C.APPL_ID AND INVOICE_STATUS='Pending With Technical'   						   ");
  sql.append("       and  ESC_TECH_SMS_SENT is null and EMP_TECH_SMS_SENT='Y' AND  C.PURCHASING_GROUP IS NOT NULL   	)					   ");

            sql.append(" UNION ALL ");
            	sql.append("  (  SELECT distinct SUBMIT_AT_LOCATION,LOCATION,S.APPL_ID,PURCHASING_GROUP,PARENT_OFFICE_CODE,P.VENDOR_NUMBER,P.VENDOR_NAME,     ");
        sql.append("    VENDOR_INVOICE_NUMBER,INV_NO,SORMDATE,INV_DT,INVOICE_STATUS,to_char(EMP_ACC_SMS_DATE, 'yyyy-mm-dd')days_delayed , ");  
        sql.append("    NVL(EMP_ACC_SMS_SENT,' ') EMP_ACC_SMS_SENT   ,NVL(ESC_ACC_SMS_SENT,' ') ESC_ACC_SMS_SENT , EMP_ACC_SMS_DATE,ACC_EMP_CPF ,ACC_EMP_NAME ");
        sql.append("    FROM summary_status P,SMS_SENT_TRACKER S	");
        sql.append("    where  p.vendor_number = s.vendor_number   ");
        sql.append("    	 AND TO_NUMBER(trunc(sysdate) - trunc(EMP_ACC_SMS_DATE))-(SELECT EMPPORTAL.XXMIS_EMP_NOOFHOLIDAYS(trunc(EMP_ACC_SMS_DATE),sysdate,ACC_EMP_CPF) FROM DUAL) >7	");
        sql.append("    AND REGEXP_REPLACE(UPPER(INV_NO ), '[^0-9A-Za-z]', '')=REGEXP_REPLACE(UPPER(s.vendor_invoice_number ), '[^0-9A-Za-z]', '')	  ");
        sql.append("    AND P.APPL_ID=S.APPL_ID AND INVOICE_STATUS='Pending With Accounts' ");
        sql.append("    and  ESC_ACC_SMS_SENT is null and EMP_ACC_SMS_SENT='Y' AND  PURCHASING_GROUP IS NOT NULL 	");
  sql.append("			UNION 																   ");
  sql.append("          	SELECT distinct SUBMIT_AT_LOCATION,LOCATION,S.APPL_ID,C.PURCHASING_GROUP,PARENT_OFFICE_CODE,P.VENDOR_NUMBER,P.VENDOR_NAME,	   ");
  sql.append("        VENDOR_INVOICE_NUMBER,INV_NO,SORMDATE,INV_DT,INVOICE_STATUS,to_char(EMP_TECH_SMS_DATE, 'yyyy-mm-dd')days_delayed ,    			   ");
  sql.append("        NVL(EMP_TECH_SMS_SENT,' ') EMP_TECH_SMS_SENT  ,NVL(ESC_TECH_SMS_SENT,' ') ESC_TECH_SMS_SENT  , EMP_TECH_SMS_DATE ,TECH_EMP_CPF,TECH_EMP_NAME ");
  sql.append("         FROM summary_status P,SMS_SENT_TRACKER S ,PO_LINE_INV_DETAILS C								   ");
  sql.append("        where  p.vendor_number = s.vendor_number ");
   sql.append("   	 AND TO_NUMBER(trunc(sysdate) - trunc(EMP_ACC_SMS_DATE))-(SELECT EMPPORTAL.XXMIS_EMP_NOOFHOLIDAYS(trunc(EMP_ACC_SMS_DATE),sysdate,ACC_EMP_CPF) FROM DUAL) >7 	"); 												  
  sql.append("        AND REGEXP_REPLACE(UPPER(INV_NO ), '[^0-9A-Za-z]', '') =REGEXP_REPLACE(UPPER(s.vendor_invoice_number ), '[^0-9A-Za-z]', '') 								   ");
  sql.append("       AND P.APPL_ID=S.APPL_ID AND S.APPL_ID=C.APPL_ID AND INVOICE_STATUS='Pending With Accounts'   						   ");
  sql.append("       and  ESC_TECH_SMS_SENT is null and EMP_TECH_SMS_SENT='Y' AND  C.PURCHASING_GROUP IS NOT NULL   	)					   ");

            logger.log(Level.INFO, "GetEscalationSmsStatusQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());

            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetEscalationSmsStatusQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }

}
