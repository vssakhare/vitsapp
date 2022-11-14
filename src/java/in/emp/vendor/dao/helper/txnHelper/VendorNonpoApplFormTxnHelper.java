/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.txnHelper;

//-- Java imports
import java.sql.PreparedStatement;

import java.sql.Types;
import java.sql.Connection;
import java.util.*;
import in.emp.common.ApplicationConstants;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.CallableStatement;

import in.emp.dao.TxnHelper;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.util.ApplicationUtils;
import in.emp.util.TxnUtility;



//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class VendorNonpoApplFormTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorApplFormTxnHelper.class);
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor VendorProjApplFormTxnHelper()
     * @param vendorInputBeanObj object of VendorInputBean
     */
    public VendorNonpoApplFormTxnHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }//constructor ends

    /**
     * @public Constructor VendorApplFormTxnHelper()
     *
     * @param vendorPrezDataObj	VendorPrezData
     */
    public VendorNonpoApplFormTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorInputBeanObj = this.vendorPrezDataObj.getVendorInputBean();
    }//constructor ends

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    @Override
    public Object createObject(Connection conn) throws Exception {
        int count = 0;
        int i=1;
        PreparedStatement statement = null;
       
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "VendorNonpoApplFormTxnHelper ::: createObject() :: method called ::");
            
             vendorInputBeanObj = vendorPrezDataObj.getVendorInputBean();
       
            vendorInputBeanObj.setApplId(ApplicationUtils.getNextSequenceId(conn, "XXMIS_ERP_VENDOR_DTL_SEQ").toString());

     
            
            sql.append( "insert into xxmis_erp_non_po_vendor_dtl " );
            sql.append(" ( ID, APPL_ID, INVOICE_NUMBER, INVOICE_AMOUNT, "); // 6 here
               sql.append("  ZONE, CIRCLE, DIVISION, SUBDIVISION, "); // 6 here
            sql.append(" INVOICE_DATE, CREATED_TIME_STAMP, SAVE_FLAG, UPDATED_TIME_STAMP,VENDOR_NUMBER, VENDOR_NAME,VENDOR_INWARD_DATE,INVOICE_SUBMIT_DATE,module_type,INVOICE_FROM_DT,INVOICE_TO_DT,WORK_COMPLETION_DTL,MODULE_SAVE_FLAG ) ");//nature of work added in module type
            sql.append(" SELECT  TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD') || 000000 ) + XXMIS_ERP_VENDOR_DTL_SEQ.CURRVAL, ?, "); // 1 here            
            sql.append("  ?, ?, ?,?,?,?,?, "); // 1 + 2 here
            sql.append("  SYSTIMESTAMP, '10', SYSTIMESTAMP, ?, ?, ?, SYSTIMESTAMP,?,?,?,?,'NON_PO'  FROM dual ");
           sql.append("  WHERE NOT EXISTS(SELECT 1 FROM XXMIS_ERP_NON_PO_VENDOR_DTL,fiscal_year where VENDOR_NUMBER=?  and REGEXP_REPLACE(UPPER(INVOICE_NUMBER), '[^0-9A-Za-z]', '')=REGEXP_REPLACE(UPPER(?), '[^0-9A-Za-z]', '') ");
          sql.append(" and ? between  ");
   sql.append(" to_date(fiscal_year.FISCAL_START,'DD-MON-RRRR') ");
                  
             sql.append("   AND  to_date(fiscal_year.FISCAL_END,'DD-MON-RRRR')  ");
              sql.append("      ) ");
            
            // 7 + 3 till here
            statement = conn.prepareStatement(sql.toString());
            statement.setString(i++, vendorInputBeanObj.getApplId());           
            statement.setString(i++, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setString(i++, vendorInputBeanObj.getVendorInvoiceAmount());
            statement.setString(i++, vendorInputBeanObj.getZone());
            statement.setString(i++, vendorInputBeanObj.getCircle());
            statement.setString(i++, vendorInputBeanObj.getDivision());
            statement.setString(i++, vendorInputBeanObj.getSubDivision());
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getVendorNumber()); 
            statement.setString(i++, vendorInputBeanObj.getVendorName());
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getSelectedModuleType());//nature of work selected for non po invoices
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getWorkDetailDesc());
            statement.setString(i++, vendorInputBeanObj.getVendorNumber());
            statement.setString(i++, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
              
             
             logger.log(Level.INFO, "VendorProjApplFormTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            if (count == 0)
            vendorInputBeanObj.setApplId("");
                    
            if (count > 0)
            {
                
                vendorPrezDataObj.setVendorInputBean(vendorInputBeanObj);
                conn.commit();
                 
            }
             return vendorPrezDataObj;
          
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorNonpoApplFormTxnHelper ::: createObject() :: Exception :: " + ex);
              ex.printStackTrace();
            throw ex;
           
        }

    }//createObject() ends

    /**
     * Public API to update data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    @Override
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        
        
        StringBuilder sql = new StringBuilder();
        int count = 0;
      
        String sf = "10";
        try {
            logger.log(Level.INFO, "VendorNonpoApplFormTxnHelper ::: updateObject() :: method called ::");
            vendorInputBeanObj = vendorPrezDataObj.getVendorInputBean();
            
            if (vendorInputBeanObj.getSaveFlag() != null) {
                if (vendorInputBeanObj.getSaveFlag().equals("Saved")) {
                    sf = "10";
                } 
            }
            
            if((sf.equals("10"))){
                int i=1;
                 sql.append( "update xxmis_erp_non_po_vendor_dtl " );
            sql.append(" set   INVOICE_AMOUNT=?, "); // 6 here
               sql.append("  ZONE=?, CIRCLE=?, DIVISION=?, SUBDIVISION=?, "); // 6 here
            sql.append(" INVOICE_DATE=?, CREATED_TIME_STAMP=SYSTIMESTAMP, SAVE_FLAG='10', UPDATED_TIME_STAMP=SYSTIMESTAMP,VENDOR_NUMBER=?, VENDOR_NAME=?,VENDOR_INWARD_DATE=?,INVOICE_SUBMIT_DATE=SYSTIMESTAMP,module_type=?,INVOICE_FROM_DT=?,INVOICE_TO_DT=?,WORK_COMPLETION_DTL=?,MODULE_SAVE_FLAG='NON_PO'  ");//nature of work added in module type
                sql.append(" WHERE APPL_ID = ? ");
            
            // 7 + 3 till here
            statement = conn.prepareStatement(sql.toString());

            statement.setString(i++, vendorInputBeanObj.getVendorInvoiceAmount());
            statement.setString(i++, vendorInputBeanObj.getZone());
            statement.setString(i++, vendorInputBeanObj.getCircle());
            statement.setString(i++, vendorInputBeanObj.getDivision());
            statement.setString(i++, vendorInputBeanObj.getSubDivision());
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getVendorNumber()); 
            statement.setString(i++, vendorInputBeanObj.getVendorName());
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getVendorInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getSelectedModuleType());//nature of work selected for non po invoices
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceFromDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setDate(i++, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorInputBeanObj.getInvoiceToDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(i++, vendorInputBeanObj.getWorkDetailDesc());
             statement.setString(i++, vendorInputBeanObj.getApplId());
                  logger.log(Level.INFO, "VendorNonPOApplFormTxnHelper ::: createObject() :: SQL :: " + sql.toString());
               count = statement.executeUpdate();
            }
       if (count > 0)
            {
                 conn.commit();
            }
           
    }
              catch (Exception ex) {
            logger.log(Level.ERROR, "VendorNonpoApplFormTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

    }//updateObject() ends

    /**
     * Public API to delete data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    @Override
    public void deleteObject(Connection conn) throws Exception {
    }//deleteObject() ends

    /**
     * Public API to update ObjectAttribute
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
    }//updateObjectAttribute ends
}
