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
public class GetCorrectionFormQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetCorrectionFormQueryHelper.class);
    private CorrectionFormBean correctionFormBeanObj;
    private CorrectionFormPrezData correctionFormPrezDataObj = new CorrectionFormPrezData();

    public GetCorrectionFormQueryHelper(CorrectionFormBean correctionFormBeanObj) {
        this.correctionFormBeanObj = correctionFormBeanObj;
    }

    public GetCorrectionFormQueryHelper(CorrectionFormPrezData correctionFormPrezDataObj) {
        this.correctionFormPrezDataObj = correctionFormPrezDataObj;
        this.correctionFormBeanObj = this.correctionFormPrezDataObj.getCorrectionFormBean();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        CorrectionFormBean correctionFormBean = new CorrectionFormBean();
        try {
            logger.log(Level.INFO, " GetCorrectionFormQueryHelper :: getDataObject() :: method called");

            correctionFormBean.setApplicationId(results.getString("APPL_ID"));
            correctionFormBean.setApplicationStatus(results.getString("STATUS"));
            correctionFormBean.setCorrectionType(results.getString("TYPE"));
            correctionFormBean.setCorrectionValue(results.getString("NEW_VALUE"));
            correctionFormBean.setSystemValue(results.getString("SYS_VALUE"));
            correctionFormBean.setCreatedDate(results.getDate("APPL_DATE"));
            correctionFormBean.setEmpNumber(results.getString("EMP_NUMBER"));
            correctionFormBean.setPersonId(results.getString("PERSON_ID"));
            correctionFormBean.setRemark(results.getString("REMARK"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetCorrectionFormQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return correctionFormBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, " GetCorrectionFormQueryHelper :: getQueryResults() :: method called");

            sql.append(" SELECT ECF.APPL_ID, ECF.STATUS, ECF.TYPE, ");
            sql.append(" ECF.NEW_VALUE, ECF.SYS_VALUE, ECF.APPL_DATE, ECF.EMP_NUMBER, ECF.PERSON_ID, ECF.REMARK ");
            sql.append(" FROM XXMIS_EMP_CORRECTION_FORM_DATA ECF ");
            sql.append(" WHERE ECF.APPL_ID =  ? "); // 1 here
            sql.append(" AND ECF.EMP_NUMBER =  ? "); // 2 till here
            sql.append(" AND NVL(ECF.STATUS, 'Saved') <>  'Deleted' "); // 1 + 2 till here

            logger.log(Level.INFO, "GetCorrectionFormQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, correctionFormBeanObj.getApplicationId());
            statement.setString(2, correctionFormBeanObj.getEmpNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetCorrectionFormQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}