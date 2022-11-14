/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.correctionform.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.home.correctionform.bean.CorrectionFileBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetCorrectionFileListQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetCorrectionFileListQueryHelper.class);
    private CorrectionFileBean correctionFileBeanObj;

    public GetCorrectionFileListQueryHelper(CorrectionFileBean correctionFileBeanObj) {
        this.correctionFileBeanObj = correctionFileBeanObj;
    }

    public Object getDataObject(ResultSet results) throws Exception {
        CorrectionFileBean correctionFileBean = new CorrectionFileBean();
        try {
            logger.log(Level.INFO, " GetCorrectionFileListQueryHelper :: getDataObject() :: method called");
            
            correctionFileBean.setId(results.getString("ID"));
            correctionFileBean.setApplicationId(results.getString("APPL_ID"));
            correctionFileBean.setSrNo(results.getInt("SR_NO"));
            correctionFileBean.setFileName(results.getString("FILE_NAME"));
            correctionFileBean.setFileType(results.getString("FILE_TYPE"));
            correctionFileBean.setOption(results.getString("FOPTION"));
            correctionFileBean.setRemark(results.getString("REMARK"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetCorrectionFileListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return correctionFileBean;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, " GetCorrectionFileListQueryHelper :: getCorrectionFile() :: method called");

            sql.append(" SELECT ECF.ID, ECF.APPL_ID, ECF.SR_NO, ");
            sql.append(" ECF.FILE_NAME, ECF.FILE_TYPE, ECF.FOPTION,ECF.REMARK ");
            sql.append(" FROM XXMIS_EMP_CORRECTION_FILES ECF ");
            sql.append(" WHERE ECF.APPL_ID =  ? ");
            logger.log(Level.INFO, "GetCorrectionFileListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, correctionFileBeanObj.getApplicationId());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetCorrectionFileListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
