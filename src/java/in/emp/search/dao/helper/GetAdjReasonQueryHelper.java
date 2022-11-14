/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.search.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//-- MDA Imports
import in.emp.dao.QueryHelper;
import in.emp.search.bean.DeviceTypeBean;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class GetAdjReasonQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetAdjReasonQueryHelper.class);
	
	
	/**
	 * @public Constructor GetDeviceTypesQueryHelper()
	 */
	public GetAdjReasonQueryHelper()
	{

	} // End of Constructor
	
	
	
	/*public API to get the Data object from the result set
	@param Result Set Object
	@ Return Object
	@Throws Exception*/

	public Object getDataObject(ResultSet results) throws Exception 
	{
		DeviceTypeBean deviceTypeBeanObj = new DeviceTypeBean();
		try
		{
			// Setting Device Types in the DeviceTypeBean object from result set
			deviceTypeBeanObj.setProjectId(results.getInt("REASON_ID"));
			deviceTypeBeanObj.setProjectCd(results.getString("REASON_ID"));
			deviceTypeBeanObj.setProjectName(results.getString("REASON_DESC"));
			//deviceTypeBeanObj.setDeviceMfgName(results.getString("MFG_NAME"));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetAdjReasonQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return deviceTypeBeanObj;
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
			sql.append("SELECT ");
			sql.append("ADJUSTMENT_CODE REASON_ID, ");
			sql.append("ADJUSTMENT_CODE REASON_ID, ");
			sql.append("DESCRIPTION REASON_DESC ");
			//sql.append("MFG_NAME ");
			sql.append("FROM ADJUSTMENT_UNIT_CODE_MASTER order by ADJUSTMENT_CODE ");
			//sql.append("WHERE STATUS_CD = 'A' "); 
			//sql.append("AND IS_COMM_DEVICE = 'N' "); 
		
			logger.log(Level.INFO, "GetAdjReasonQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetAdjReasonQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class

