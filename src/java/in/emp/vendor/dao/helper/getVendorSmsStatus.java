/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper;

//-- Java imports
import in.emp.vendor.dao.helper.FtpFileReadHelper.getVendorStatusTxnHelper;
import in.emp.common.ApplicationConstants;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.*;
import java.util.Date;
import in.emp.dao.TxnHelper;
import in.emp.util.ApplicationUtils;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import java.sql.CallableStatement;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class getVendorSmsStatus implements TxnHelper {

    private static Logger logger = Logger.getLogger(getVendorStatusTxnHelper.class);
    private VendorStatuBean vendorstatusbeanobj;
   

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public getVendorSmsStatus() {
       
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public getVendorSmsStatus(VendorStatuBean vendorstatusbeanobj) {
        this.vendorstatusbeanobj = vendorstatusbeanobj;
      
    }//constructor ends

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object createObject(Connection conn) throws Exception {
          PreparedStatement statement = null;
        int count = 0;
        try {//CALL GOES FIRST TO DELETE OBJECT
            logger.log(Level.INFO, "getVendorSmsStatus ::: createObject() :: method called ::");
StringBuilder sql = new StringBuilder();
           sql.append(" INSERT INTO SMS_SENT_TRACKER ");
            sql.append(" (VENDOR_NUMBER ,VENDOR_INVOICE_NUMBER,SUBMITTED_SMS_SENT,VENDOR_EMAILID,VENDOR_CONTACT_NO,SUBMITTED_SMS_SENT_DATE, "); // 4 here
             sql.append(" SUBMITTED_MAIL_SENT,SUBMITTED_MAIL_SENT_DATE,INVOICE_SUBMIT_DATE,PO_NUMBER_PROJ_ID,APPL_ID) ");
              sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, vendorstatusbeanobj.getVENDOR_CODE());
            statement.setString(2, vendorstatusbeanobj.getVENDOR_INVOICE_NUMBER());
            statement.setString(3, vendorstatusbeanobj.getSUBMITTED_SMS_FLAG());
            statement.setString(4,vendorstatusbeanobj.getEMAIL());
            statement.setString(5,vendorstatusbeanobj.getPHN_LL());
            statement.setDate(6, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getSUBMITTED_SMS_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(7,vendorstatusbeanobj.getSUBMITTED_MAIL_FLAG());
            statement.setDate(8, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getSUBMITTED_MAIL_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setDate(9,  ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getINVOICE_SUBMIT_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(10, vendorstatusbeanobj.getPO_NUMBER());
            statement.setString(11, vendorstatusbeanobj.getAPPL_ID());
             logger.log(Level.INFO, "getVendorSmsStatus ::: createObject() :: SQL :: " + sql.toString());
            
            count = statement.executeUpdate();
           conn.commit();
        
       
        

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getVendorSmsStatus ::: createObject() :: Exception :: " + ex);
            throw ex;
        }

        return vendorstatusbeanobj;
    }//createObject() ends

    /**
     * Public API to update data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
         String sf = "10";
        try {
            logger.log(Level.INFO, "getVendorStatusTxnhelper ::: updateObject() :: method called ::");
              if (vendorstatusbeanobj.getSave_Flag() != null) {
              if (vendorstatusbeanobj.getSave_Flag().equals("Submitted")) {
                    sf = "20";
                }
                else if (vendorstatusbeanobj.getSave_Flag().equals("Verified")) {
                    sf = "30";
                }
                else if (vendorstatusbeanobj.getSave_Flag().equals("Rejected")) {
                    sf = "40";
                }
            }
                if(sf.equals("20")){
           sql.append(" UPDATE SMS_SENT_TRACKER  SET SUBMITTED_MAIL_SENT = ?,SUBMITTED_MAIL_SENT_DATE = ? , ");
           sql.append("  SUBMITTED_SMS_SENT = ?, SUBMITTED_SMS_SENT_DATE = ? ");
            sql.append(" WHERE APPL_ID = ? ");
          

                    statement = conn.prepareStatement(sql.toString());
                    statement.setString(1, vendorstatusbeanobj.getSUBMITTED_MAIL_FLAG());
                    statement.setDate(2, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getSUBMITTED_MAIL_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(3, vendorstatusbeanobj.getSUBMITTED_SMS_FLAG());
                    statement.setDate(4, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getSUBMITTED_SMS_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(5, vendorstatusbeanobj.getAPPL_ID());
                    count = statement.executeUpdate();
                }
               else if(sf.equals("30")){
                     sql.append(" UPDATE SMS_SENT_TRACKER  SET VERIFIED_MAIL_SENT = ?,VERIFIED_MAIL_SENT_DATE = ? , ");
           sql.append("  VERIFIED_SMS_SENT = ?, VERIFIED_SMS_SENT_DATE = ? ");
            sql.append(" WHERE APPL_ID = ? ");
          

                    statement = conn.prepareStatement(sql.toString());
                    statement.setString(1, vendorstatusbeanobj.getVERIFIED_MAIL_FLAG());
                    statement.setDate(2, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getVERIFIED_MAIL_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(3, vendorstatusbeanobj.getVERIFIED_SMS_FLAG());
                    statement.setDate(4, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getVERIFIED_SMS_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(5, vendorstatusbeanobj.getAPPL_ID());
                    count = statement.executeUpdate();
                }
                else if(sf.equals("40")){
                     sql.append(" UPDATE SMS_SENT_TRACKER  SET REJECTED_MAIL_SENT = ?, REJECTED_MAIL_SENT_DATE = ? , ");
           sql.append(" REJECTED_SMS_SENT = ?,REJECTED_SMS_SENT_DATE = ? ");
            sql.append(" WHERE APPL_ID = ? ");
          

                    statement = conn.prepareStatement(sql.toString());
                    statement.setString(1, vendorstatusbeanobj.getREJECTED_MAIL_FLAG());
                    statement.setDate(2, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getREJECTED_MAIL_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(3, vendorstatusbeanobj.getREJECTED_SMS_FLAG());
                    statement.setDate(4, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(vendorstatusbeanobj.getREJECTED_SMS_DATE(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
                    statement.setString(5, vendorstatusbeanobj.getAPPL_ID());
                    count = statement.executeUpdate();
                }
                if (count > 0)
            {
                 conn.commit();
            }
            logger.log(Level.INFO, "getVendorStatusTxnhelper ::: updateObject() :: SQL :: " + sql.toString());

            
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorApplTxnHelper ::: updateObject() :: Exception :: " + ex);
             //ex.printStackTrace();
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
    public void deleteObject(Connection conn) throws Exception {
         PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "getVendorStatusTxnhelper ::: updateObject() :: method called ::");
           sql.append("select * from SMS_SENT_TRACKER where  APPL_ID=?");
          // sql.append("AND DECODE (SIGN (TO_NUMBER (TO_CHAR (INVOICE_SUBMIT_DATE, 'mm')) - 4),");
           // sql.append(" -1, TO_NUMBER (TO_CHAR (INVOICE_SUBMIT_DATE, 'YYYY')) - 1,");
           // sql.append(" TO_NUMBER (TO_CHAR (INVOICE_SUBMIT_DATE, 'YYYY')))= ");
          // sql.append("AND DECODE (SIGN (TO_NUMBER (TO_CHAR (?, 'mm')) - 4),");
          //  sql.append(" -1, TO_NUMBER (TO_CHAR (?, 'YYYY')) - 1,");
          //  sql.append(" TO_NUMBER (TO_CHAR (?, 'YYYY'))) ");

            statement = conn.prepareStatement(sql.toString());
            // statement.setString(1, vendorstatusbeanobj.getVENDOR_CODE());
            // statement.setString(2, vendorstatusbeanobj.getVENDOR_INVOICE_NUMBER());
            // statement.setString(3, vendorstatusbeanobj.getPO_NUMBER());
             statement.setString(1, vendorstatusbeanobj.getAPPL_ID());
             count = statement.executeUpdate();
             if(count > 0)
             {
             updateObject(conn);  
             }
             else{
               createObject(conn);}
            logger.log(Level.INFO, "getVendorStatusTxnhelper ::: updateObject() :: SQL :: " + sql.toString());

            
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorApplTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }
    }//deleteObject() ends

    /**
     * Public API to update ObjectAttribute
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
    }//updateObjectAttribute ends
}