/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority.dao.helper;

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
import in.emp.authority.bean.AuthorityBean;
import in.emp.authority.bean.AuthorityPrezData;
import in.emp.util.ApplicationUtils;
import in.emp.util.TxnUtility;
//import in.emp.authority.dao.helper.TAclaimsFormJdtlTxnHelper;


//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class AuthorityFormTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(AuthorityFormTxnHelper.class);
    private AuthorityBean authorityBeanObj;
    private AuthorityPrezData authorityPrezDataObj;

    /**
     * @public Constructor AuthorityFormTxnHelper()
     * @param authorityBeanObj object of AuthorityBean
     */
    public AuthorityFormTxnHelper(AuthorityBean authorityBeanObj) {
        this.authorityBeanObj = authorityBeanObj;
    }//constructor ends

    /**
     * @public Constructor AuthorityFormTxnHelper()
     *
     * @param authorityPrezDataObj	AuthorityPrezData
     */
    public AuthorityFormTxnHelper(AuthorityPrezData authorityPrezDataObj) {
        this.authorityPrezDataObj = authorityPrezDataObj;
        this.authorityBeanObj = this.authorityPrezDataObj.getAuthorityBean();
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
            logger.log(Level.INFO, "AuthorityFormTxnHelper ::: createObject() :: method called ::");
            
             authorityBeanObj = authorityPrezDataObj.getAuthorityBean();
       
            authorityBeanObj.setApplId(ApplicationUtils.getNextSequenceId(conn, "XXMIS_ERP_VENDOR_DTL_SEQ").toString());

            sql.append(" INSERT INTO XXMIS_ERP_VENDOR_DTL ");
            sql.append(" ( ID, APPL_ID, VENDOR_NUMBER, VENDOR_NAME, PO_NUMBER, INVOICE_NUMBER, INVOICE_AMOUNT, "); // 6 here
            sql.append(" INVOICE_DATE, CREATED_TIME_STAMP, INWARD_NUMBER, INWARD_DATE, SAVE_FLAG, UPDATED_TIME_STAMP ) "); // 10 till here            
            sql.append(" VALUES ");
            sql.append(" ( TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD') || 000000 ) + XXMIS_ERP_VENDOR_DTL_SEQ.NEXTVAL, ?, "); // 1 here            
            sql.append(" ?, ?, ?, ?, ?, "); // 1 + 2 here
            sql.append(" ?, SYSTIMESTAMP, ?, ?, '10', null ) "); // 7 + 3 till here

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, authorityBeanObj.getApplId());
            statement.setString(2, authorityBeanObj.getVendorNumber());
            statement.setString(3, authorityBeanObj.getVendorName());
            statement.setString(4, authorityBeanObj.getPONumber());
            statement.setString(5, authorityBeanObj.getInvoiceNumber());
            statement.setString(6, authorityBeanObj.getInvoiceAmount());
            statement.setDate(7, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(authorityBeanObj.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(8, authorityBeanObj.getInwardNumber());
            statement.setDate(9, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(authorityBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
           

            logger.log(Level.INFO, "AuthorityFormTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            if (count > 0)
            {
                authorityPrezDataObj.setAuthorityBean(authorityBeanObj);
                conn.commit();
            }
            
        } catch (Exception ex) {
            conn.rollback();
            logger.log(Level.ERROR, "AuthorityFormTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        } finally {
            return authorityPrezDataObj;
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
            logger.log(Level.INFO, "AuthorityFormTxnHelper ::: updateObject() :: method called ::");
            authorityBeanObj = authorityPrezDataObj.getAuthorityBean();
            
            if (authorityBeanObj.getSaveFlag() != null) {
                if (authorityBeanObj.getSaveFlag().equals("Saved")) {
                    sf = "10";
                } else if (authorityBeanObj.getSaveFlag().equals("Submitted")) {
                    sf = "20";
                }
            }
            
            sql.append(" UPDATE XXMIS_ERP_VENDOR_DTL ");
            sql.append(" SET PO_NUMBER = ?, INVOICE_NUMBER = ?,  INVOICE_AMOUNT = ?, "); // 3 here
            sql.append(" INVOICE_DATE = ? , INWARD_NUMBER = ?, INWARD_DATE = ? , "); // 6 till here
            sql.append(" SAVE_FLAG = ?, UPDATED_TIME_STAMP = SYSTIMESTAMP "); // 16-18 till here           
            sql.append(" WHERE APPL_ID = ? "); // 19 till here
            sql.append(" AND NVL(SAVE_FLAG,'10') = '10' "); 

            statement = conn.prepareStatement(sql.toString());           
            statement.setString(1, authorityBeanObj.getPONumber());
            statement.setString(2, authorityBeanObj.getInvoiceNumber());
            statement.setString(3, authorityBeanObj.getInvoiceAmount());
            statement.setDate(4, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(authorityBeanObj.getInvoiceDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(5, authorityBeanObj.getInwardNumber());
            statement.setDate(6, ApplicationUtils.stringToDate(ApplicationUtils.dateToString(authorityBeanObj.getInwardDate(), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT), ApplicationConstants.DEFAULT_DISPLAY_DATE_FORMAT));
            statement.setString(7, sf);            
            statement.setString(8, authorityBeanObj.getApplId());
           
            count = statement.executeUpdate();

           // authorityPrezDataObj.setAuthorityBean(authorityBeanObj);
            if (count > 0)
            {
                 conn.commit();
            }
           
            
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "AuthorityFormTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }

            //leaveapplFormBeanObj = leaveapplFormPrezDataObj.getLeaveApplFormBean();
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
