/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.hrms.dao.helper;

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
import in.emp.hrms.bean.HRMSUserBean;
import in.emp.hrms.bean.HRMSUserPrezData;
import in.emp.util.ApplicationUtils;
import in.emp.util.TxnUtility;
//import in.emp.feedback.dao.helper.TAclaimsFormJdtlTxnHelper;


//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class VendorLoginTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(VendorLoginTxnHelper.class);
    private HRMSUserBean hrmsuserBeanObj;
    private HRMSUserPrezData hrmsuserPrezDataObj;

    /**
     * @public Constructor VendorLoginTxnHelper()
     * @param feedbackBeanObj object of HRMSUserBean
     */
    public VendorLoginTxnHelper(HRMSUserBean hrmsuserBeanObj) {
        this.hrmsuserBeanObj = hrmsuserBeanObj;
    }//constructor ends

    /**
     * @public Constructor HRMSUserTxnHelper()
     *
     * @param feedbackPrezDataObj	HRMSUserPrezData
     */
    public VendorLoginTxnHelper(HRMSUserPrezData hrmsuserPrezDataObj) { 
        this.hrmsuserPrezDataObj = hrmsuserPrezDataObj;
        this.hrmsuserBeanObj = this.hrmsuserPrezDataObj.getHrmsUserBeanObj();
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
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "VendorLoginTxnHelper ::: createObject() :: method called ::");

            sql.append(" INSERT INTO ERP_LOGIN ");
            sql.append(" ( USER_NAME, PASSWORD, USER_TYPE) "); // 10 till here            
            sql.append(" SELECT ");
            sql.append("  ?, ?, ? FROM DUAL"); // 1 here
            sql.append(" WHERE NOT EXISTS(SELECT 1 FROM  ERP_LOGIN WHERE USER_NAME = ?) ");
            

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, hrmsuserBeanObj.getEmpNumber());
            statement.setString(2, hrmsuserBeanObj.getPass());
            statement.setString(3, hrmsuserBeanObj.getUserType());
            statement.setString(4, hrmsuserBeanObj.getEmpNumber());
           

            logger.log(Level.INFO, "VendorLoginTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();

            hrmsuserPrezDataObj.setHrmsUserBeanObj(hrmsuserBeanObj);
            conn.commit();
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "VendorLoginTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        } return hrmsuserPrezDataObj;
        

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
        int count = 0;
        int[] counter = null;
        int length = 0;
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "VendorLoginTxnHelper ::: deleteObject() :: method called ::");

            sql.append(" DELETE FROM ERP_LOGIN ");
            sql.append(" WHERE USER_NAME = ? "); // 1 here
           

            logger.log(Level.INFO, "VendorLoginTxnHelper :: deleteObject() :: SQL :: " + sql.toString());
            
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, hrmsuserBeanObj.getEmpNumber());
          
            
            count = statement.executeUpdate();
            conn.commit();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "VendorLoginTxnHelper ::: deleteObject() :: Exception :: " + ex);
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
    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
    }//updateObjectAttribute ends
}
