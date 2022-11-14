/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.dao.helper;

import in.emp.dao.TxnHelper;
import in.emp.home.correctionform.bean.CorrectionFileBean;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class CorrectionFileTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(CorrectionFileTxnHelper.class);
    private CorrectionFileBean correctionFileBeanObj;

    public CorrectionFileTxnHelper(CorrectionFileBean correctionFileBeanObj) {
        this.correctionFileBeanObj = correctionFileBeanObj;
    }

    public Object createObject(Connection conn) throws Exception {
        int count = 0;
        int[] counter = null;
        int length = 0;
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        Long id = 0L;
        String fileId = "";
        try {
            logger.log(Level.INFO, "CorrectionFileTxnHelper ::: createObject() :: method called ::");

            id = ApplicationUtils.getNextSequenceId(conn, "XXMIS_EMP_CORRECTION_FILES_SEQ");
            fileId = ((ApplicationUtils.dateToString(new Date(), "yyyyMMdd") + "0000000") + (String.valueOf(id))).substring((String.valueOf(id)).length());
            correctionFileBeanObj.setId(fileId);

            sql.append(" INSERT INTO XXMIS_EMP_CORRECTION_FILES ");
            sql.append(" ( ID, APPL_ID, SR_NO, FILE_NAME, "); // 4 here
            sql.append(" FILE_TYPE, FILE_BLOB, FOPTION, USER_ID_CREATED, CREATED_SITE_ID, CREATED_TIME_STAMP ,REMARK) "); // 11 till here
            sql.append(" VALUES ");
            sql.append(" ( ?, ?, "); // 2 here
            sql.append(" NVL(( SELECT MAX(SR_NO) FROM XXMIS_EMP_CLAIM_FILES WHERE APPL_ID = ? ),0) + 1, "); // 3 till here
            sql.append(" ?, ?, ?, ?, ?, ?, SYSTIMESTAMP,? ) "); // 1 + 10 till here

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, correctionFileBeanObj.getId());
            statement.setString(2, correctionFileBeanObj.getApplicationId());
            statement.setString(3, correctionFileBeanObj.getApplicationId());
            statement.setString(4, correctionFileBeanObj.getFileName());
            statement.setString(5, correctionFileBeanObj.getFileType());
            statement.setBytes(6, correctionFileBeanObj.getFileContents());
            statement.setString(7, correctionFileBeanObj.getOption());
            statement.setString(8, correctionFileBeanObj.getEmpNumber());
            statement.setString(9, correctionFileBeanObj.getLocation());
            statement.setString(10, correctionFileBeanObj.getRemark());

            count = statement.executeUpdate();
            conn.commit();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFileTxnHelper ::: createObject() :: Exception :: " + ex);
            conn.rollback();
            throw ex;
        }
        return correctionFileBeanObj;
    }

    public void updateObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteObject(Connection conn) throws Exception {
        int count = 0;
        int[] counter = null;
        int length = 0;
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        try {
            logger.log(Level.INFO, "CorrectionFileTxnHelper ::: deleteObject() :: method called ::");

            sql.append(" DELETE FROM XXMIS_EMP_CORRECTION_FILES ");
            sql.append(" WHERE APPL_ID = ? "); // 1 here
            sql.append(" AND ID = ? "); // 2 till here
            sql.append(" AND USER_ID_CREATED = ? "); // 3 till here

            logger.log(Level.INFO, "CorrectionFileTxnHelper :: deleteObject() :: SQL :: " + sql.toString());

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, correctionFileBeanObj.getApplicationId());
            statement.setString(2, correctionFileBeanObj.getId());
            statement.setString(3, correctionFileBeanObj.getEmpNumber());

            count = statement.executeUpdate();
            conn.commit();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFileTxnHelper ::: deleteObject() :: Exception :: " + ex);
            correctionFileBeanObj.setId("");
            conn.rollback();
            throw ex;
        }
    }

    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
