/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.user.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.user.bean.UserParameterBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class GetUserParameterQueryHelper implements QueryHelper{
    private static Logger logger = Logger.getLogger(GetAllEmployeesQueryHelper.class);

    /**
     * @public Constructor GetAllRolesQueryHelper()
     */
    public GetUserParameterQueryHelper()
    {
            
    } // End of Constructor


    /*public API to get the Data object from the result set
    @param Result Set Object
    @ Return Object
    @Throws Exception*/

    public Object getDataObject(ResultSet results) throws Exception
    {
           
            UserParameterBean userParameterBean = null ;
            try
            {
                    userParameterBean = new UserParameterBean();
                    userParameterBean.setParamId(results.getLong("SORT_ORDER"));
                    userParameterBean.setParamName(results.getString("DESCRIPTION"));
                    userParameterBean.setParamValue(results.getString("PARAMETER_VALUE"));
            }
            catch (Exception ex)
            {
                    logger.log(Level.ERROR, "GetUserParameterQueryHelper ::: getDataObject() :: Exception :: "+ex);
                    throw ex;
            }
            return userParameterBean;
    } // End of Method

            /**
     * Public API to get query results
     * @param conn object of Connection
     * @return object of ResultSet
     * @throws Exception
     */
    public ResultSet getQueryResults(Connection connection) throws Exception
    {
            PreparedStatement statement = null;
            StringBuffer sql = new StringBuffer();
            ResultSet rs = null;

            try
            {
                    sql.append(" SELECT ");
                    sql.append(" SORT_ORDER, ");
                    sql.append(" DESCRIPTION, ");
                    sql.append(" PARAMETER_VALUE ");
                    sql.append(" FROM SYSTEM_PARAMETER");
                    sql.append(" WHERE USER_PARAMETER = 'Y' ");
                    sql.append(" ORDER BY SORT_ORDER ");
                    logger.log(Level.INFO, "GetUserParameterQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
                   // System.out.println("user parameter "+sql.toString());
                    statement = connection.prepareStatement(sql.toString());
                    rs = statement.executeQuery();

            }
            catch(Exception ex)
            {
                    logger.log(Level.ERROR, "GetUserParameterQueryHelper ::: getQueryResults() :: Exception :: " + ex);
                    //ex.printStackTrace();
                    throw ex;
            }
            return rs;
    }

}
