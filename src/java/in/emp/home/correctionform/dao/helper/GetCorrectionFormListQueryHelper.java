/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.home.correctionform.bean.CorrectionFormBean;
import in.emp.home.correctionform.bean.CorrectionFormPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetCorrectionFormListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetCorrectionFormListQueryHelper.class);
    private CorrectionFormBean correctionFormBeanObj;
    private CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();

    public GetCorrectionFormListQueryHelper(CorrectionFormBean correctionFormBeanObj) {
        this.correctionFormBeanObj = correctionFormBeanObj;
    }

    public GetCorrectionFormListQueryHelper(CorrectionFormPrezData correctionFormPrezDataObj) {
        this.correctionFormPrezDataObj = correctionFormPrezDataObj;
        this.correctionFormBeanObj = this.correctionFormPrezDataObj.getCorrectionFormBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        CorrectionFormBean correctionFormBean = new CorrectionFormBean();
        try {
            logger.log(Level.INFO, " GetCorrectionFormListQueryHelper :: getDataObject() :: method called");

            correctionFormBean.setApplicationId(results.getString("APPL_ID"));
            correctionFormBean.setApplicationStatus(results.getString("STATUS"));
            correctionFormBean.setCorrectionType(results.getString("TYPE"));
            correctionFormBean.setCreatedDate(results.getDate("APPL_DATE"));
            correctionFormBean.setEmpNumber(results.getString("EMP_NUMBER"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetCorrectionFormListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return correctionFormBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, " GetCorrectionFormListQueryHelper :: getQueryResults() :: method called");

            sql.append(" SELECT ECL.APPL_ID, ECL.STATUS, ECL.TYPE, ");
            sql.append(" ECL.APPL_DATE, ECL.EMP_NUMBER ");
            sql.append(" FROM XXMIS_EMP_CORRECTION_APPL_LIST ECL ");
            sql.append(" WHERE ECL.EMP_NUMBER =  ? "); // 1 here
            sql.append(" AND NVL(ECL.STATUS, 'Saved') <>  'Deleted' "); // 1 + 1 till here
            sql.append(" ORDER BY ECL.APPL_DATE DESC ");

            logger.log(Level.INFO, "GetCorrectionFormListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, correctionFormBeanObj.getEmpNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetCorrectionFormListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}