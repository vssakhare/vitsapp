/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.search.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.master.bean.MasterBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class GetReadingMonthsQueryHelper implements QueryHelper
{
    private static Logger logger = Logger.getLogger(GetReadingMonthsQueryHelper.class);
    private MasterBean masterBean;

    /**
     * @public Constructor GetLocationTypesQueryHelper()
      */
    public GetReadingMonthsQueryHelper()
    {

    } // End of Constructor


    /**
     * @public Constructor GetLocationTypesQueryHelper()
     * @param locationBean object of LocationBean
     */
    public GetReadingMonthsQueryHelper(MasterBean masterBean)
    {
            this.masterBean = masterBean;
    } // End of Constructor


    public Object getDataObject(ResultSet results) throws Exception 
    {
        MasterBean masterBeanObj = new MasterBean();
        try
        {
                // Setting Locations in the location Bean object from result set
                masterBeanObj.setId(results.getInt("ID"));
                masterBeanObj.setCode(results.getString("RD"));
                masterBeanObj.setName(results.getString("RD"));

        }
        catch (Exception ex)
        {
                logger.log(Level.ERROR, "GetReadingMonthsQueryHelper :: getDataObject() :: Exception :: "+ex);
                throw ex;
        }
        return masterBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception
    {
        PreparedStatement statement = null;
        StringBuffer sql = new StringBuffer();
        ResultSet rs = null;

        try
        {
               /* sql.append("SELECT  ");
                sql.append(" DISTINCT TO_CHAR(CAST(READING_DATE AS DATE),'MON-RRRR') RD, ");
                sql.append(" TO_DATE(TO_CHAR(CAST(READING_DATE AS DATE),'MON-RRRR'),'MON-RRRR') RD1, ");
                sql.append(" 1 ID ");
                sql.append(" FROM READING_DETAILS WHERE READING_DATE between to_date('01-01-2002','dd-mm-yyyy') and sysdate ");
                sql.append(" ORDER BY RD1 DESC ");*/
                sql.append("SELECT SR_NO, MMM_YYYY RD, DD_MMM_YYYY RD1, ID "
                        + "FROM READING_MONTH_MASTER "
                        + "WHERE DD_MMM_YYYY <= SYSDATE "
                        + "order by dd_mmm_yyyy desc ");

                logger.log(Level.INFO, "GetReadingMonthsQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
                statement = connection.prepareStatement(sql.toString());
                rs = statement.executeQuery();
        }
        catch(Exception ex)
        {
                logger.log(Level.ERROR, "GetReadingMonthsQueryHelper :: getQueryResults() :: Exception :: " + ex);
                //ex.printStackTrace();
                throw ex;
        }
        return rs;
    }


}
