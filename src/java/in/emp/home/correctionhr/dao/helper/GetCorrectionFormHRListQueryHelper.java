/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionhr.dao.helper;

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
public class GetCorrectionFormHRListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetCorrectionFormHRListQueryHelper.class);
    private CorrectionFormBean correctionFormBeanObj;
    private CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();

    public GetCorrectionFormHRListQueryHelper(CorrectionFormBean correctionFormBeanObj) {
        this.correctionFormBeanObj = correctionFormBeanObj;
    }

    public GetCorrectionFormHRListQueryHelper(CorrectionFormPrezData correctionFormPrezDataObj) {
        this.correctionFormPrezDataObj = correctionFormPrezDataObj;
        this.correctionFormBeanObj = this.correctionFormPrezDataObj.getCorrectionFormBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        CorrectionFormBean correctionFormBean = new CorrectionFormBean();
        try {
            logger.log(Level.INFO, " GetCorrectionFormHRListQueryHelper :: getDataObject() :: method called");

            correctionFormBean.setApplicationId(results.getString("APPL_ID"));
            correctionFormBean.setApplicationStatus(results.getString("STATUS"));
            correctionFormBean.setCorrectionType(results.getString("TYPE"));
            correctionFormBean.setCreatedDate(results.getDate("APPL_DATE"));
            correctionFormBean.setEmpNumber(results.getString("EMP_NUMBER"));
            correctionFormBean.setEmpName(results.getString("FULL_NAME"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetCorrectionFormHRListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return correctionFormBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, " GetCorrectionFormHRListQueryHelper :: getQueryResults() :: method called");

            sql.append(" SELECT ECL.APPL_ID, ECL.STATUS, ECL.TYPE, ");
            sql.append(" ECL.APPL_DATE, ECL.EMP_NUMBER, EmpFullName(ECL.PERSON_ID) FULL_NAME ");
            sql.append(" FROM XXMIS_EMP_CORRECTION_APPL_LIST ECL ");
            sql.append(" WHERE ECL.EMP_NUMBER =  ? "); // 1 here
            sql.append(" AND ECL.LOCATION_ID =  ? "); // 2 till here
            sql.append(" AND NVL(ECL.STATUS, 'Saved') <>  'Deleted' "); // 1 + 2 till here
            sql.append(" ORDER BY ECL.APPL_DATE DESC ");

            logger.log(Level.INFO, "GetCorrectionFormHRListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, correctionFormBeanObj.getEmpNumber());
            statement.setString(2, correctionFormBeanObj.getLocation());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetCorrectionFormHRListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}