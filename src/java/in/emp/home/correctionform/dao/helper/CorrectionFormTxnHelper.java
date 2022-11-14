/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.dao.helper;

import in.emp.common.ApplicationConstants;
import in.emp.dao.TxnHelper;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionform.bean.CorrectionFormPrezData;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFormTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(CorrectionFormTxnHelper.class);
    private CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
    private CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();

    public CorrectionFormTxnHelper(CorrectionFormBean correctionFormBeanObj) {
        this.correctionFormBeanObj = correctionFormBeanObj;
    }

    public CorrectionFormTxnHelper(CorrectionFormPrezData correctionFormPrezDataObj) {
        this.correctionFormPrezDataObj = correctionFormPrezDataObj;
        this.correctionFormBeanObj = this.correctionFormPrezDataObj.getCorrectionFormBean();
    }

    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "CorrectionFormTxnHelper ::: createObject() :: method called ::");

            correctionFormBeanObj.setApplicationId(ApplicationUtils.getNextSequenceId(conn, "XXMIS_EMP_CORRECTION_APPL_SEQ").toString());

            sql.append(" INSERT INTO XXMIS_EMP_CORRECTION_APPL_DTL ");
            sql.append(" ( ID, APPL_ID, TYPE, PERSON_ID, EMP_NUMBER, SYS_VALUE, NEW_VALUE, SAVE_FLAG, "); // 8 here
            sql.append(" USER_ID_CREATED, CREATED_SITE_ID, CREATED_TIME_STAMP, USER_ID_MODIFIED, MODIFIED_SITE_ID, MODIFIED_TIME_STAMP ) "); // 15 till here
            sql.append(" VALUES ");
            sql.append(" ( TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD') || 000000 ) + XXMIS_EMP_CORRECTION_DTL_SEQ.NEXTVAL, "); // 1 here
            sql.append(" ?, ?, NVL((GET_PERSON_ID( ? )),0), ?, ?, ?, ?, "); // 1 + 7 till here
            sql.append(" ?, ?, SYSTIMESTAMP, NULL, NULL, NULL ) "); // 4 + 9 till here

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, correctionFormBeanObj.getApplicationId());
            statement.setString(2, correctionFormBeanObj.getCorrectionType());
            statement.setString(3, correctionFormBeanObj.getEmpNumber());
            statement.setString(4, correctionFormBeanObj.getEmpNumber());
            statement.setString(5, correctionFormBeanObj.getSystemValue());
            statement.setString(6, correctionFormBeanObj.getCorrectionValue());
            statement.setString(7, "10");
            statement.setString(8, correctionFormBeanObj.getEmpNumber());
            statement.setString(9, correctionFormBeanObj.getLocation());

            logger.log(Level.INFO, "CorrectionFormTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFormTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
        return count;
    }

    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        String sf = "10";
        try {
            logger.log(Level.INFO, "CorrectionFormTxnHelper ::: updateObject() :: method called ::");
            
            if (correctionFormBeanObj.getApplicationStatus()!= null) {
                if (correctionFormBeanObj.getApplicationStatus().equals("Saved")) {
                    sf = "10";
                } else if (correctionFormBeanObj.getApplicationStatus().equals("Submitted")) {
                    sf = "20";
                }
            }

            sql.append(" UPDATE XXMIS_EMP_CORRECTION_APPL_DTL ");
            sql.append(" SET TYPE = ?, SYS_VALUE = ?, NEW_VALUE = ?, SAVE_FLAG = ?, "); // 4 here
            sql.append(" USER_ID_MODIFIED = ?, MODIFIED_SITE_ID = ?, MODIFIED_TIME_STAMP = SYSTIMESTAMP "); // 1 + 6 till here
            sql.append(" WHERE APPL_ID = ? "); // 1 + 7 till here
            sql.append(" AND EMP_NUMBER = ? "); // 1 + 8 till here
            sql.append(" AND NVL(SAVE_FLAG,'Saved') = '10' "); // 2 + 8 till here

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, correctionFormBeanObj.getCorrectionType());
            statement.setString(2, correctionFormBeanObj.getSystemValue());
            statement.setString(3, correctionFormBeanObj.getCorrectionValue());
            statement.setString(4, sf);
            statement.setString(5, correctionFormBeanObj.getEmpNumber());
            statement.setString(6, correctionFormBeanObj.getLocation());
            statement.setString(7, correctionFormBeanObj.getApplicationId());
            statement.setString(8, correctionFormBeanObj.getEmpNumber());

            logger.log(Level.INFO, "CorrectionFormTxnHelper ::: updateObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFormTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    public void deleteObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        String sf = "10";
        try {
            logger.log(Level.INFO, "CorrectionFormTxnHelper ::: deleteObject() :: method called ::");

            sql.append(" UPDATE XXMIS_EMP_CORRECTION_APPL_DTL ");
            sql.append(" SET SAVE_FLAG = '05', "); // 1 here
            sql.append(" USER_ID_MODIFIED = ?, MODIFIED_SITE_ID = ?, MODIFIED_TIME_STAMP = SYSTIMESTAMP "); // 2 + 2 till here
            sql.append(" WHERE APPL_ID = ? "); // 2 + 3 till here
            sql.append(" AND EMP_NUMBER = ? "); // 2 + 4 till here
            sql.append(" AND NVL(SAVE_FLAG,'Saved') = '10' "); // 3 + 4 till here

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, correctionFormBeanObj.getEmpNumber());
            statement.setString(2, correctionFormBeanObj.getLocation());
            statement.setString(3, correctionFormBeanObj.getApplicationId());
            statement.setString(4, correctionFormBeanObj.getEmpNumber());

            logger.log(Level.INFO, "CorrectionFormTxnHelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFormTxnHelper ::: deleteObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
