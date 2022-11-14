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
import in.emp.vendor.bean.VendorApplFileBean;
import in.emp.vendor.bean.VendorApplFilePrezData;
import in.emp.util.ApplicationUtils;
import in.emp.util.TxnUtility;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class VendorApplFileTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorApplFileTxnHelper.class);
    private VendorApplFileBean vendorapplFileBeanObj;
    private VendorApplFilePrezData vendorapplFilePrezDataObj;

    /**
     * @public Constructor VendorApplTxnHelper()
     * @param vendorapplBeanobj object of VendorApplBean
     */
    public VendorApplFileTxnHelper(VendorApplFileBean vendorapplFileBeanObj) {
        this.vendorapplFileBeanObj = vendorapplFileBeanObj;
    }//constructor ends

    /**
     * @public Constructor VendorApplTxnHelper()
     *
     * @param vendorapplPrezDataObj	VendorApplPrezData
     */
    public VendorApplFileTxnHelper(VendorApplFilePrezData vendorapplFilePrezDataObj) {
        this.vendorapplFilePrezDataObj = vendorapplFilePrezDataObj;
    }//constructor ends

    /**
     * Public API to create data Object.
     *
     * @param conn object of Connection
     * @return Object
     * @throws Exception
     */
    public Object createObject(Connection conn) throws Exception {
        int count = 0;
        int[] counter = null;
        int length = 0;
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        Long id = 0L;
        String fileId = "";
        try {
            logger.log(Level.INFO, "VendorApplFileTxnHelper ::: createObject() :: method called ::");

            id = ApplicationUtils.getNextSequenceId(conn, "XXMIS_ERP_INVOICE_FILES_SEQ");
            fileId = ((ApplicationUtils.dateToString(new Date(), "yyyyMMdd") + "0000000") + (String.valueOf(id))).substring((String.valueOf(id)).length());
            vendorapplFileBeanObj.setId(fileId);

            sql.append(" INSERT INTO XXMIS_ERP_INVOICE_FILES ");
            sql.append(" ( ID, APPL_ID, SR_NO, FILE_NAME, "); // 4 here
            sql.append(" FILE_TYPE,  USER_ID_CREATED, CREATED_TIME_STAMP ,REMARK,TYPE_OF_FILE) "); // 11 till here
            sql.append(" VALUES ");
            sql.append(" ( ?, ?, "); // 2 here
            sql.append(" NVL(( SELECT MAX(SR_NO) FROM XXMIS_ERP_INVOICE_FILES WHERE APPL_ID = ? ),0) + 1, "); // 3 till here
            sql.append(" ?, ?, ?, SYSTIMESTAMP,?,? ) "); // 1 + 9 till here

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, vendorapplFileBeanObj.getId());
            statement.setString(2, vendorapplFileBeanObj.getApplicationId());
            statement.setString(3, vendorapplFileBeanObj.getApplicationId());
            statement.setString(4, vendorapplFileBeanObj.getFileName());
            statement.setString(5, vendorapplFileBeanObj.getFileType());
            //statement.setBytes(6, vendorapplFileBeanObj.getFileContents());
            // statement.setString(6, vendorapplFileBeanObj.getOption());
            statement.setString(6, vendorapplFileBeanObj.getEmpNumber());
          
            statement.setString(7, vendorapplFileBeanObj.getRemark());
            statement.setString(8, vendorapplFileBeanObj.getOption());
            count = statement.executeUpdate();
            conn.commit();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorApplFileTxnHelper ::: createObject() :: Exception :: " + ex);
            vendorapplFileBeanObj.setId("");
            conn.rollback();
            throw ex;
        }
        return vendorapplFileBeanObj;
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
            logger.log(Level.INFO, "VendorApplFileTxnHelper ::: updateObject() :: method called ::");
 sql.append(" UPDATE XXMIS_ERP_INVOICE_FILES ");
            sql.append("  SET PATH = ?  WHERE APPL_ID = ? "); // 1 here
            sql.append(" AND ID = ? "); // 2 till here
            sql.append(" AND USER_ID_CREATED = ? "); // 3 till here
 logger.log(Level.INFO, "VendorApplFileTxnHelper :: UPDATEObject() :: SQL :: " + sql.toString());
          
            statement = conn.prepareStatement(sql.toString());
              statement.setString(1, vendorapplFileBeanObj.getPath());
            statement.setString(2, vendorapplFileBeanObj.getApplicationId());
            statement.setString(3, vendorapplFileBeanObj.getId().replaceAll("^0+(?!$)", ""));
            statement.setString(4, vendorapplFileBeanObj.getEmpNumber().replaceAll("^0+(?!$)", ""));
               count = statement.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
           conn.rollback();
            logger.log(Level.ERROR, "VendorApplFileTxnHelper ::: updateObject() :: Exception :: " + ex);
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
        int count = 0;
        int[] counter = null;
        int length = 0;
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "VendorApplFileTxnHelper ::: deleteObject() :: method called ::");

            sql.append(" DELETE FROM XXMIS_ERP_INVOICE_FILES ");
            sql.append(" WHERE APPL_ID = ? "); // 1 here
            sql.append(" AND ID = ? "); // 2 till here
            sql.append(" AND USER_ID_CREATED = ? "); // 3 till here

            logger.log(Level.INFO, "VendorApplFileTxnHelper :: deleteObject() :: SQL :: " + sql.toString());
            
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, vendorapplFileBeanObj.getApplicationId());
            statement.setString(2, vendorapplFileBeanObj.getId());
            statement.setString(3, vendorapplFileBeanObj.getEmpNumber());
            
            count = statement.executeUpdate();
            conn.commit();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorApplFileTxnHelper ::: deleteObject() :: Exception :: " + ex);
            conn.rollback();
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
