package in.emp.master.dao.helper;

//-- java Imports
import in.emp.dao.QueryHelper;
import in.emp.master.bean.MasterBean;
import in.emp.util.ApplicationUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Query helper class to get list of all mastere
 */
public class GetMasterListQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetMasterListQueryHelper.class);
	private String tableName = null;
	private String column1 = null;
	private String column2 = null;
	private String column3 = null;
	private String statusCDcolumn = null;
        private String whereClause = null;
	
	/**
	 * @public Constructor GetMasterListQueryHelper()
	 * @param String object of tableName
	 */
	public GetMasterListQueryHelper(String tableName, String column1, String column2, String column3, String whereClause)
	{
		this.tableName = tableName;
		this.column1 = column1;
		this.column2 = column2;
		this.column3 = column3;
                this.whereClause = whereClause;
	} // End Of Constructor
	/**
	 * @public Constructor GetMasterListQueryHelper()
	 * @param String object of tableName
	 */
	public GetMasterListQueryHelper(String tableName, String column1, String column2, String column3, String statusCDColumn, String whereClause)
	{
		this.tableName = tableName;
		this.column1 = column1;
		this.column2 = column2;
		this.column3 = column3;
                this.whereClause = whereClause;
                this.statusCDcolumn = statusCDColumn;
	} // End Of Constructor

	/**
	 * Public API to get data object.
	 * @param results object of ResultSet
	 * @return Object
	 * @throws Exception
	 */
	public Object getDataObject(ResultSet results) throws Exception 
	{
		MasterBean masterBean = new MasterBean();
		try
		{
			if(statusCDcolumn!=null || !ApplicationUtils.isBlank(statusCDcolumn))
                        {
                                ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc
                                // Setting Data in the SecUserBean --
                                masterBean.setId(results.getLong(""+column1+""));
                                masterBean.setCode(results.getString(""+column2+""));
                                masterBean.setName(results.getString(""+column3+""));
                                masterBean.setStatus(results.getString(""+statusCDcolumn+""));
                        }
                        else
                        {
                                // Setting Data in the SecUserBean --
                                masterBean.setId(results.getLong(""+column1+""));
                                masterBean.setCode(results.getString(""+column2+""));
                                masterBean.setName(results.getString(""+column3+""));
                                masterBean.setStatus(results.getString("STATUS_CD"));
                        }

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetMasterListQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}

		return masterBean;

	} //getDataObject() ends 
	
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
                        if(statusCDcolumn!=null || !ApplicationUtils.isBlank(statusCDcolumn))
                        {
                                ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc
                                logger.log(Level.INFO, "GetMasterListQueryHelper For NDM :: getQueryResults() :: ");

                                sql.append("SELECT "+column1+", ");
                                sql.append(""+column2+", ");
                                sql.append(""+column3+", ");
                                sql.append(""+statusCDcolumn+" ");
                                sql.append("FROM "+tableName+" ");
                                sql.append("WHERE "+statusCDcolumn+" = 'A' ");
                                if(!whereClause.equals("")){
                                    sql.append("AND " + whereClause + "");
                                }
                                statement = connection.prepareStatement(sql.toString());
                                //System.out.println("Master List : " + sql.toString());
                                logger.log(Level.INFO, "GetMasterListQueryHelper For NDM :: getQueryResults() :: SQL :: " + sql.toString());
                                //System.out.println("GetMasterListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
                                // Executing Query
                                rs = statement.executeQuery();
                        }else if(tableName.equalsIgnoreCase("DEVICE_CONNECTION") || tableName.contains("DCU"))
                        {
                             logger.log(Level.INFO, "GetMasterListQueryHelper :: getQueryResults() :: ");

                                sql.append("SELECT "+column1+", ");
                                sql.append(""+column2+", ");
                                sql.append(""+column3+", ");
                                sql.append("STATUS_CD ");
                                sql.append("FROM "+tableName+" ");
                                sql.append("WHERE STATUS_CD IN ('A','I') ");
                                if(!whereClause.equals("")){
                                    sql.append("AND " + whereClause + "");
                                }
                                statement = connection.prepareStatement(sql.toString());
                                //System.out.println("Master List : " + sql.toString());
                                logger.log(Level.INFO, "GetMasterListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
                                //System.out.println("GetMasterListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
                                // Executing Query
                                rs = statement.executeQuery();
                        }
                        else
                        {
                                logger.log(Level.INFO, "GetMasterListQueryHelper :: getQueryResults() :: ");

                                sql.append("SELECT "+column1+", ");
                                sql.append(""+column2+", ");
                                sql.append(""+column3+", ");
                                sql.append("STATUS_CD ");
                                sql.append("FROM "+tableName+" ");
                                sql.append("WHERE STATUS_CD = 'A' ");
                                if(!whereClause.equals("")){
                                    sql.append("AND " + whereClause + "");
                                }
                                statement = connection.prepareStatement(sql.toString());
                                //System.out.println("Master List : " + sql.toString());
                                logger.log(Level.INFO, "GetMasterListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
                                //System.out.println("GetMasterListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
                                // Executing Query
                                rs = statement.executeQuery();
                        }
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetMasterListQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}
		finally
		{
			try
			{
				sql = null;
			}
			catch (Exception ignore){}
		}
		
		return rs;

	} // End Of Method

} //End Of Class