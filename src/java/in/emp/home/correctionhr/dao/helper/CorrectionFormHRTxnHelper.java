/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionhr.dao.helper;

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
public class CorrectionFormHRTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(CorrectionFormHRTxnHelper.class);
    private CorrectionFormBean correctionFormBeanObj = new CorrectionFormBean();
    private CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();

    public CorrectionFormHRTxnHelper(CorrectionFormBean correctionFormBeanObj) {
        this.correctionFormBeanObj = correctionFormBeanObj;
    }

    public CorrectionFormHRTxnHelper(CorrectionFormPrezData correctionFormPrezDataObj) {
        this.correctionFormPrezDataObj = correctionFormPrezDataObj;
        this.correctionFormBeanObj = this.correctionFormPrezDataObj.getCorrectionFormBean();
    }

    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "CorrectionFormHRTxnHelper ::: createObject() :: method called ::");

            logger.log(Level.INFO, "CorrectionFormHRTxnHelper ::: createObject() :: SQL :: " + sql.toString());

        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFormHRTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
        return count;
    }

    public void updateObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        String sf = "20";
        try {
            logger.log(Level.INFO, "CorrectionFormHRTxnHelper ::: updateObject() :: method called ::");
            
            if (correctionFormBeanObj.getApplicationStatus()!= null) {
                if (correctionFormBeanObj.getApplicationStatus().equals("Approve")) {
                    sf = "30";
                } else if (correctionFormBeanObj.getApplicationStatus().equals("Reject")) {
                    sf = "25";
                }
            }

            sql.append(" UPDATE XXMIS_EMP_CORRECTION_APPL_DTL ");
            sql.append(" SET NEW_VALUE = ?, SAVE_FLAG = ?, REMARK = ?, "); // 3 here
            sql.append(" USER_ID_MODIFIED = ?, MODIFIED_SITE_ID = ?, MODIFIED_TIME_STAMP = SYSTIMESTAMP "); // 1 + 5 till here
            sql.append(" WHERE APPL_ID = ? "); // 1 + 6 till here
            sql.append(" AND GET_HR_LOCATION_ID(EMP_NUMBER ) = ? "); // 1 + 7 till here
            sql.append(" AND NVL(SAVE_FLAG,'10') = '20' "); // 2 + 7 till here

            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, correctionFormBeanObj.getCorrectionValue());
            statement.setString(2, sf);
            statement.setString(3, correctionFormBeanObj.getRemark());
            statement.setString(4, correctionFormBeanObj.getEmpNumber());
            statement.setString(5, correctionFormBeanObj.getLocation());
            statement.setString(6, correctionFormBeanObj.getApplicationId());
            statement.setString(7, correctionFormBeanObj.getLocation());

            logger.log(Level.INFO, "CorrectionFormHRTxnHelper ::: updateObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFormHRTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    public void deleteObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        String sf = "10";
        try {
            logger.log(Level.INFO, "CorrectionFormHRTxnHelper ::: deleteObject() :: method called ::");

            logger.log(Level.INFO, "CorrectionFormHRTxnHelper ::: deleteObject() :: SQL :: " + sql.toString());

        } catch (Exception ex) {
            logger.log(Level.ERROR, "CorrectionFormHRTxnHelper ::: deleteObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
