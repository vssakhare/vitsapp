/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.home.personalInfo.bean.PersonalinfoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Ithelpdesk
 */
/**
 *
 * @author rushi
 */
public class GetEmpImageQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetEmpImageQueryHelper.class);
    private PersonalinfoBean pinfoBeanObj;

    /**
     * @public Constructor GetDCUSearchResultListQueryHelper()
     */
    public GetEmpImageQueryHelper(PersonalinfoBean pinfoBeanObj) {
        this.pinfoBeanObj = pinfoBeanObj;
    }

    /*public API to get the Data object from the result set
     @param Result Set Object
     @ Return Object
     @Throws Exception*/
    public Object getDataObject(ResultSet results) throws Exception {
        PersonalinfoBean personalinfoBean = new PersonalinfoBean();
        try {
            logger.log(Level.INFO, "GetEmpImageQueryHelper ::: getDataObject() :: method called ::");

            personalinfoBean.setEmpImg(results.getBytes("IMAGE"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetEmpImageQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return personalinfoBean;
    }

    /**
     * Public API to get query results
     *
     * @param conn object of Connection
     * @return object of ResultSet
     * @throws Exception
     */
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;

        try {
            logger.log(Level.INFO, "GetEmpImageQueryHelper ::: getQueryResults() :: method called ::");

            sql.append(" SELECT IMAGE ");
            sql.append(" FROM XXMIS_EMP_IMAGE EI ");
            sql.append(" WHERE EI.EMPLOYEE_NUMBER = ? ");
            sql.append(" AND EI.PERSON_ID = ? ");

            logger.log(Level.INFO, "GetEmpImageQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, pinfoBeanObj.getEmpNumber());
            statement.setString(2, pinfoBeanObj.getPersonId());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetEmpImageQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
