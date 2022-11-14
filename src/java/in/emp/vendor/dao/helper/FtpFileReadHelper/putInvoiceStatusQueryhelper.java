/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.FtpFileReadHelper;

import in.emp.dao.QueryHelper;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.dao.helper.formHelper.GetVendorInputFormQueryHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class putInvoiceStatusQueryhelper implements QueryHelper {
    private static Logger logger = Logger.getLogger(GetVendorInputFormQueryHelper.class); 
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    public putInvoiceStatusQueryhelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }

    public putInvoiceStatusQueryhelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorInputBeanObj = this.vendorPrezDataObj.getVendorInputBean();
    }

    
    public Object getDataObject(ResultSet results) throws Exception {
        VendorInputBean vendorBeanObj = new VendorInputBean();
        try {
            logger.log(Level.INFO, "putInvoiceStatusQueryhelper ::: getDataObject() :: method called ::");
      
            vendorBeanObj.setApplId(results.getString("APPL_ID"));
            vendorBeanObj.setSerialNo(results.getString("SERIAL_NO"));
           vendorBeanObj.setVendorInvoiceNumber(results.getString("invoice_number"));
           vendorBeanObj.setVendorNumber(results.getString("VENDOR_NUMBER"));
           vendorBeanObj.setVendorName(results.getString("VENDOR_NAME"));
           vendorBeanObj.setVendorUpdatedDate(results.getDate("CREATED_TIME_STAMP"));
            vendorBeanObj.setProjectId(results.getString("PROJECT_ID"));
           vendorBeanObj.setPONumber(results.getString("PO_NUMBER"));
           vendorBeanObj.setPODesc(results.getString("PO_DESC"));
            vendorBeanObj.setPOType(results.getString("PO_TYPE"));
             vendorBeanObj.setINVOICE_TYPE(results.getString("INVOICE_TYPE"));
           vendorBeanObj.setVendorInvoiceNumber(results.getString("INVOICE_NUMBER"));
           vendorBeanObj.setVendorInvoiceDate(results.getDate("INVOICE_DATE"));
           vendorBeanObj.setVendorInvoiceAmount(results.getString("INVOICE_AMOUNT"));
           vendorBeanObj.setInwardNumber(results.getString("INWARD_NUMBER"));
           vendorBeanObj.setInwardDate(results.getDate("INWARD_DATE"));
           vendorBeanObj.setStatus(results.getString("STATUS"));
           
        } catch (Exception ex) {
            logger.log(Level.ERROR, "putInvoiceStatusQueryhelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return vendorBeanObj;
    }
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            
         
          sql.append(" select APPL_ID,CREATED_TIME_STAMP,UPDATED_TIME_STAMP,VENDOR_NUMBER,VENDOR_NAME,' ' PROJECT_ID ,PO_DESC,INVOICE_NUMBER, ");
   sql.append("  INVOICE_DATE,INVOICE_AMOUNT,INWARD_NUMBER,INWARD_DATE,  ");
   sql.append("  STATUS,' ' INVOICE_TYPE,' ' PO_TYPE,PO_NUMBER, '1' SERIAL_NO from vpts.XXMIS_ERP_VENDOR_INPUT_LIST   ");
   sql.append(" where  status='Verified' and TO_DATE(TRUNC(NVL(updated_time_stamp,SYSDATE))) = TO_DATE(TRUNC(SYSDATE))  ");
   sql.append("  UNION ALL ");
  sql.append("  select * from (SELECT  APPL_ID,'1' SERIAL_NO1,'2' SERIAL_NO2,'3' SERIAL_NO3,CREATED_TIME_STAMP,UPDATED_TIME_STAMP,VENDOR_NUMBER,VENDOR_NAME,PROJECT_ID,nvl(PROJECT_DESC,' ')PROJECT_DESC, ");
  sql.append("  INVOICE_NUMBER,INVOICE_DATE,INVOICE_AMOUNT,INWARD_NUMBER,INWARD_DATE,MATERIAL_PO,CENTAGES_PO,SERVICE_PO,STATUS, DECODE(INVOICE_TYPE, 'Original Invoice' ,'OI','RI Charges','RI','Other Charges','OT','Excess Invoice','EX','Migo Based Invoice','MI','Electrical Inspection Charges','EI' ) INVOICE_TYPE  ");
 sql.append("   FROM vpts.XXMIS_ERP_PS_VENDOR_INPUT_LIST   ) ");
  sql.append("   UNPIVOT ");
 sql.append("   ((PO_NUMBER,SERIAL_NO)    ");
   sql.append("  FOR PO_TYPE  ");
   sql.append("  IN (  ");
  sql.append("    ( material_po  ,SERIAL_NO1 )AS 'MATERIAL PO', ");
  sql.append("    (centages_po ,  SERIAL_NO2) AS 'CENTAGES PO', ");
  sql.append("     (service_po, SERIAL_NO3 )AS 'SERVICES PO')) ");
   sql.append("  where   INVOICE_TYPE IN('OI','EX')  ");
   sql.append(" and status='Verified' AND TO_DATE(TRUNC(NVL(updated_time_stamp,SYSDATE))) = TO_DATE(TRUNC(SYSDATE))  ");
   sql.append("  UNION ALL ");
   sql.append(" SELECT  APPL_ID,CREATED_TIME_STAMP,UPDATED_TIME_STAMP,VENDOR_NUMBER,VENDOR_NAME,PROJECT_ID,nvl(PROJECT_DESC,' ')PROJECT_DESC, ");
   sql.append(" INVOICE_NUMBER,INVOICE_DATE,INVOICE_AMOUNT,INWARD_NUMBER,INWARD_DATE,STATUS, DECODE(INVOICE_TYPE, 'Original Invoice' ,'OI','RI Charges','RI','Other Charges','OT','Excess Invoice','EX','Migo Based Invoice','MI','Electrical Inspection Charges','EI' ) INVOICE_TYPE,' ' PO_TYPE,' ' PO_NUMBER,'1' SERIAL_NO ");
   sql.append(" FROM vpts.XXMIS_ERP_PS_VENDOR_INPUT_LIST ");
  sql.append("  where  INVOICE_TYPE IN ('RI Charges','Electrical Inspection Charges','Other Charges')  ");
   sql.append("and status='Verified' AND TO_DATE(TRUNC(NVL(updated_time_stamp,SYSDATE))) = TO_DATE(TRUNC(SYSDATE))  ");
      sql.append("  UNION ALL ");
   sql.append(" SELECT  APPL_ID,CREATED_TIME_STAMP,UPDATED_TIME_STAMP,VENDOR_NUMBER,VENDOR_NAME,PROJECT_ID,nvl(PROJECT_DESC,' ')PROJECT_DESC, ");
   sql.append(" INVOICE_NUMBER,INVOICE_DATE,INVOICE_AMOUNT,INWARD_NUMBER,INWARD_DATE,STATUS, DECODE(INVOICE_TYPE, 'Original Invoice' ,'OI','RI Charges','RI','Other Charges','OT','Excess Invoice','EX','Migo Based Invoice','MI','Electrical Inspection Charges','EI' ) INVOICE_TYPE,'MATERIAL_PO' PO_TYPE,MATERIAL_PO,'1' SERIAL_NO ");
   sql.append(" FROM vpts.XXMIS_ERP_PS_VENDOR_INPUT_LIST ");
  sql.append("  where  INVOICE_TYPE IN ('Migo Based Invoice') ");
   sql.append("and  status='Verified' AND TO_DATE(TRUNC(NVL(updated_time_stamp,SYSDATE))) = TO_DATE(TRUNC(SYSDATE))  ");
  
           logger.log(Level.INFO, "putInvoiceStatusQueryhelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            
                        
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "putInvoiceStatusQueryhelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}


    

