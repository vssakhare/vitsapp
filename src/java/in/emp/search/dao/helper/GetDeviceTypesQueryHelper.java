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



public class GetDeviceTypesQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetDeviceTypesQueryHelper.class);
	
	
	/**
	 * @public Constructor GetDeviceTypesQueryHelper()
	 */
	public GetDeviceTypesQueryHelper()
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
			deviceTypeBeanObj.setDeviceTypeId(results.getInt("DEVICE_TYPE_ID"));
			deviceTypeBeanObj.setDeviceTypeCd(results.getString("DEVICE_TYPE_CD"));
			deviceTypeBeanObj.setDeviceTypeDesc(results.getString("DEVICE_TYPE_DESC"));
			deviceTypeBeanObj.setDeviceMfgName(results.getString("MFG_NAME"));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetDeviceTypesQueryHelper :: getDataObject() :: Exception :: "+ex);
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
			sql.append("DEVICE_TYPE_ID, ");
			sql.append("DEVICE_TYPE_CD, ");
			sql.append("DEVICE_TYPE_DESC, ");
			sql.append("MFG_NAME ");
			sql.append("FROM DEVICE_TYPE ");
			sql.append("WHERE STATUS_CD = 'A' "); 
			sql.append("AND IS_COMM_DEVICE = 'N' "); 
		
			logger.log(Level.INFO, "GetDeviceTypesQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetDeviceTypesQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class
