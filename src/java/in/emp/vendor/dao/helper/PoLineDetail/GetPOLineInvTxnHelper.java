/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.vendor.dao.helper.PoLineDetail;

/**
 *
 * @author Pooja Jadhav
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//-- Java imports
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.*;

import in.emp.dao.TxnHelper;
import in.emp.vendor.bean.VendorInputBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.vendor.bean.VendorStatuBean;
import java.sql.CallableStatement;
import java.sql.SQLException;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author pooja
 */
public class GetPOLineInvTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(GetPOLineInvTxnHelper.class);
    private VendorInputBean vendorInputBeanObj;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public GetPOLineInvTxnHelper(VendorInputBean vendorInputBeanObj) {
        this.vendorInputBeanObj = vendorInputBeanObj;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public GetPOLineInvTxnHelper(VendorPrezData vendorPrezDataObj) {
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
    
   //// NOT IN USE  
    
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;

        CallableStatement proc_stmt = null;
        int count = 0;
        try {
            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: createObject() :: method called ::");

            StringBuilder sql = new StringBuilder();
            deleteall(conn);
            sql.append(" INSERT INTO PO_LINE_INV_DETAILS ");
            sql.append(" (CONTRACT_DOCUMENT,SHORT_TEXT,PLANT,PO_LINE_ID,APPL_ID,INVOICE_NUMBER,VENDOR_NUMBER,PURCHASING_GROUP) "); // 4 here
            sql.append(" select  ");
            sql.append(" CONTRACT_DOCUMENT,SHORT_TEXT,PLANT,PO_LINE_ID,APPL_ID,INVOICE_NUMBER,VENDOR_NUMBER ,PURCHASING_GROUP"); // 2 here
            sql.append(" from PO_LINE_STATUS_TEMP "); // 1 + 9 till here
            //sql.append(" WHERE CONTRACT_DOCUMENT=?");
            //sql.append(" AND VENDOR_NUMBER=?");
           // sql.append(" AND INVOICE_NUMBER=?");
            sql.append(" WHERE  APPL_ID=?");

            statement = conn.prepareStatement(sql.toString());
            //statement.setString(1, vendorInputBeanObj.getPONumber());
            //statement.setString(2, vendorInputBeanObj.getVendorNumber());
           // statement.setString(3, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setString(1, vendorInputBeanObj.getApplId());

            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            conn.commit();
            if (count > 0){

                deleteObject(conn);
            }

            

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "getVendorStatusTxnhelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }

        return vendorPrezDataObj;
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
        try {
            vendorInputBeanObj = vendorPrezDataObj.getVendorInputBean();
            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: updateObject() :: method called ::");

            sql.append(" UPDATE   PO_LINE_STATUS_TEMP ");
            sql.append(" SET INVOICE_NUMBER=? ,VENDOR_NUMBER=?,APPL_ID=? ");
            sql.append(" WHERE CONTRACT_DOCUMENT=?");
            sql.append(" AND VENDOR_NUMBER=?");

            statement = conn.prepareStatement(sql.toString());

            statement.setString(1, vendorInputBeanObj.getVendorInvoiceNumber());
            statement.setString(2, vendorInputBeanObj.getVendorNumber());
            statement.setString(3, vendorInputBeanObj.getApplId());
            statement.setString(4, vendorInputBeanObj.getPONumber());
            statement.setString(5, vendorInputBeanObj.getVendorNumber());

            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();

            createObject(conn);
            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: updateObject() :: SQL :: " + sql.toString());

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "GetPOLineInvTxnHelper ::: updateObject() :: Exception :: " + ex);
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
            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: deleteObject() :: method called ::");

            sql.append("DELETE FROM  PO_LINE_STATUS_TEMP ");
          
            sql.append(" WHERE   APPL_ID=?");
            statement = conn.prepareStatement(sql.toString());
 
            statement.setString(1, vendorInputBeanObj.getApplId());

            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "GetPOLineInvTxnHelper ::: deleteObject() :: Exception :: " + ex);
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

    public void deleteall(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            vendorInputBeanObj = vendorPrezDataObj.getVendorInputBean();
            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: deleteall() :: method called ::");
            sql.append(" UPDATE   PO_LINE_INV_DETAILS ");
            sql.append(" SET STATUS_CD='D'");
            sql.append(" WHERE APPL_ID=?");
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, vendorInputBeanObj.getApplId());
            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: deleteall() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();

            logger.log(Level.INFO, "GetPOLineInvTxnHelper ::: deleteall() :: SQL :: " + sql.toString());

        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "GetPOLineInvTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

    }

}
