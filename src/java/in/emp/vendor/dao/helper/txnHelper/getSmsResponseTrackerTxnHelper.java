/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.txnHelper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.*;

import in.emp.dao.TxnHelper;
import in.emp.vendor.bean.SMSResponseBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import java.sql.CallableStatement;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */
public class getSmsResponseTrackerTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(getSmsResponseTrackerTxnHelper.class);
    private SMSResponseBean smsresponsebeanbeanobj;
     private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public getSmsResponseTrackerTxnHelper(SMSResponseBean smsresponsebeanbeanobj) {
        this.smsresponsebeanbeanobj = smsresponsebeanbeanobj;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public getSmsResponseTrackerTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
       
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
        try {
            logger.log(Level.INFO, "getSmsResponseTrackerTxnHelper ::: createObject() :: method called ::");

	
              StringBuilder sql = new StringBuilder();
           
           sql.append(" INSERT INTO SMS_RESPONSE_TRACKER ");
            sql.append(" (MOBILE_NUMBER ,TEMPLATEID,F1 , F2  ,F3,F4 ,F5 ,INSERT_DATE,RESPONSE_ID,F6  ) "); // 4 here
             sql.append(" VALUES ");
            sql.append(" ( ?, ?, ?, "); // 2 here
            sql.append(" ?, ?, ?, ?,  SYSTIMESTAMP ,?,? ) "); // 1 + 9 till here


            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, smsresponsebeanbeanobj.getMOBILE_NUMBER());
            statement.setString(2, smsresponsebeanbeanobj.getTEMPLATEID());
            statement.setString(3, smsresponsebeanbeanobj.getF1());
            statement.setString(4, smsresponsebeanbeanobj.getF2());
            statement.setString(5, smsresponsebeanbeanobj.getF3());
            statement.setString(6, smsresponsebeanbeanobj.getF4());
            statement.setString(7, smsresponsebeanbeanobj.getF5());
            statement.setString(8, smsresponsebeanbeanobj.getRESPONSE_ID());
            statement.setString(9, smsresponsebeanbeanobj.getF6());
           
           
            
            logger.log(Level.INFO, "getSmsResponseTrackerTxnHelper ::: createObject() :: SQL :: " + sql.toString());
            
            count = statement.executeUpdate();
           conn.commit();
        
       
        

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getSmsResponseTrackerTxnHelper ::: createObject() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
            
        }
        return smsresponsebeanbeanobj;
    }//createObject() ends

    /**
     * Public API to update data Object.
     *
     * @param conn object of Connection
     * @return void
     * @throws Exception
     */
    public void updateObject(Connection conn) throws Exception {
      
        try {
          
            
            
        } catch (Exception ex) {
         
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
      
        try {
        
        } catch (Exception ex) {
           
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