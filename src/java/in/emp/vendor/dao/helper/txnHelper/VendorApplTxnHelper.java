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

import in.emp.dao.TxnHelper;
import in.emp.vendor.bean.VendorBean;
import in.emp.vendor.bean.VendorPrezData;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class VendorApplTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorApplTxnHelper.class);
    private VendorBean vendorBeanObj;
    private VendorPrezData vendorPrezDataObj;

    /**
     * @public Constructor TAclaimsTxnHelper()
     * @param vendorBeanObj object of TAclaimsBean
     */
    public VendorApplTxnHelper(VendorBean vendorBeanObj) {
        this.vendorBeanObj = vendorBeanObj;
    }//constructor ends

    /**
     * @public Constructor TAclaimsTxnHelper()
     *
     * @param vendorPrezDataObj	TAclaimsPrezData
     */
    public VendorApplTxnHelper(VendorPrezData vendorPrezDataObj) {
        this.vendorPrezDataObj = vendorPrezDataObj;
        this.vendorBeanObj = this.vendorPrezDataObj.getVendorBean();
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
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "VendorApplTxnHelper ::: createObject() :: method called ::");

            sql.append(" ? ");

            statement = conn.prepareStatement(sql.toString());

            logger.log(Level.INFO, "VendorApplTxnHelper ::: createObject() :: SQL :: " + sql.toString());
            
            count = statement.executeUpdate();
            conn.commit();
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorApplTxnHelper ::: createObject() :: Exception :: " + ex);
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
            logger.log(Level.INFO, "VendorApplTxnHelper ::: updateObject() :: method called ::");

            sql.append(" ? ");

            statement = conn.prepareStatement(sql.toString());
        
            logger.log(Level.INFO, "VendorApplTxnHelper ::: updateObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            conn.commit();
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorApplTxnHelper ::: updateObject() :: Exception :: " + ex);
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
            logger.log(Level.INFO, "VendorApplTxnHelper ::: deleteObject() :: method called ::");

            sql.append(" Update XXMIS_ERP_VENDOR_DTL EVD ");
            sql.append(" SET EVD.SAVE_FLAG  = ? ");
            sql.append(" WHERE EVD.APPL_ID = ? ");

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, "05");
            //statement.setString(2, vendorBeanObj.getApplId());

            logger.log(Level.INFO, "VendorApplTxnHelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorApplTxnHelper ::: deleteObject() :: Exception :: " + ex);
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