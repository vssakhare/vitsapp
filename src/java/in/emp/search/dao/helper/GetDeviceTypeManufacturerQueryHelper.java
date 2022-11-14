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


public class GetDeviceTypeManufacturerQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetDeviceTypeManufacturerQueryHelper.class);
	private String isCommDeviceMfg;
	
	/**
	 * @public Constructor GetDeviceTypeManufacturerQueryHelper()
	 */
	public GetDeviceTypeManufacturerQueryHelper(String isCommDeviceMfg)
	{
		this.isCommDeviceMfg = isCommDeviceMfg;
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
				deviceTypeBeanObj.setDeviceMfgName(results.getString("MFG_NAME"));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetDeviceTypeManufacturerQueryHelper ::: getDataObject() :: Exception :: "+ex);
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
			sql.append("SELECT DISTINCT");
			sql.append(" MFG_NAME ");
			sql.append(" FROM DEVICE_TYPE ");
			sql.append(" WHERE STATUS_CD IN ('A','I') "); 
				
			if(isCommDeviceMfg != null && isCommDeviceMfg != "")
			{
				sql.append(" AND IS_COMM_DEVICE = '"+isCommDeviceMfg+"'");
			}

			sql.append(" ORDER BY UPPER(MFG_NAME) ASC ");
				
			//System.out.println("GetDeviceTypeManufacturerQueryHelper ::: getQueryResults() :: SQL :: " + sql.toString());
			logger.log(Level.INFO, "GetDeviceTypeManufacturerQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetDeviceTypeManufacturerQueryHelper ::: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class
