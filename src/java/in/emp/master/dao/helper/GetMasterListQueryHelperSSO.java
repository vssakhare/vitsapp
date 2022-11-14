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
public class GetMasterListQueryHelperSSO implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetMasterListQueryHelperSSO.class);
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
	public GetMasterListQueryHelperSSO(String tableName, String column1, String column2, String column3, String whereClause)
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
//	public GetMasterListQueryHelperSSO(String tableName, String column1, String column2, String column3, String statusCDColumn, String whereClause)
	//{
//		this.tableName = tableName;
//		this.column1 = column1;
//		this.column2 = column2;
//		this.column3 = column3;
  //              this.whereClause = whereClause;
    //            this.statusCDcolumn = statusCDColumn;//
//	} // End Of Constructor

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
			//if(statusCDcolumn!=null || !ApplicationUtils.isBlank(statusCDcolumn))
                       // {
                                ///This is for changing the NDM Table Changes for Substation,Feeder,DTC etc
                                // Setting Data in the SecUserBean --
                            //    masterBean.setId(results.getLong(""+column1+""));
                           //     masterBean.setCode(results.getString(""+column2+""));
                          //      masterBean.setName(results.getString(""+column3+""));
                         //       masterBean.setStatus(results.getString(""+statusCDcolumn+""));
                       // }
                        //else
                      //  {
                                // Setting Data in the SecUserBean --
                                masterBean.setId(results.getLong(""+column1+""));
                                masterBean.setCode(results.getString(""+column2+""));
                                masterBean.setName(results.getString(""+column3+""));
                                masterBean.setStatus(results.getString("STATUS_CD"));
                        //}

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetMasterListQueryHelperSSO :: getDataObject() :: Exception :: "+ex);
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
                     
                                logger.log(Level.INFO, "GetMasterListQueryHelperSSO :: getQueryResults() :: ");

                                sql.append("SELECT TM."+column1+", ");
                                sql.append("TM."+column2+", ");
                                sql.append("TM."+column3+", ");
                                sql.append("TM.STATUS_CD ");
                                sql.append(" FROM "+tableName+" ");
                                sql.append("WHERE  " + whereClause + " ");
                               // if(!whereClause.equals("")){
                               //     sql.append("AND ");
                               /// }
                                statement = connection.prepareStatement(sql.toString());
                                //System.out.println("Master List : " + sql.toString());
                                logger.log(Level.INFO, "GetMasterListQueryHelperSSO :: getQueryResults() :: SQL :: " + sql.toString());
                                //System.out.println("GetMasterListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
                                // Executing Query
                                rs = statement.executeQuery();
                      
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetMasterListQueryHelperSSO :: getQueryResults() :: Exception :: " + ex);
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