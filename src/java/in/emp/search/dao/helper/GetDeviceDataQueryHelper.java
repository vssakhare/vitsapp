package in.emp.search.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

//-- MDA Imports
import in.emp.dao.QueryHelper;
import in.emp.search.bean.DeviceDataBean;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * Query helper class to get list Device Data
 */
public class GetDeviceDataQueryHelper implements QueryHelper
{
	//private static Logger logger = new Logger(GetDeviceDataQueryHelper.class.getName());
	private static Logger logger = Logger.getLogger(GetDeviceDataQueryHelper.class);

	private DeviceDataBean deviceDataBeanObj;
	
	/**
	 * @public Constructor GetDeviceDataQueryHelper()
	 * @param deviceDataBeanObj object of DeviceDataBean
	 */
	public GetDeviceDataQueryHelper(DeviceDataBean deviceDataBeanObj)
	{
		this.deviceDataBeanObj = deviceDataBeanObj;
	}

	/**
	 * Public API to get data object.
	 * @param results object of ResultSet
	 * @return Object
	 * @throws Exception
	 */
	public Object getDataObject(ResultSet results) throws Exception 
	{
		DeviceDataBean deviceDataBean = new DeviceDataBean();
		try
		{
			//logger.log(Level.INFO, "GetDeviceDataQueryHelper :: getDataObject() :: method called");	
			
			// Setting Result Data in the DeviceDataBean object from result set

			deviceDataBean.setDeviceDataHistoryId(results.getLong("DEVICE_DATA_HISTORY_ID"));
			deviceDataBean.setDeviceId(results.getLong("DEVICE_ID"));
			deviceDataBean.setLocationId(results.getLong("LOCATION_ID"));
			deviceDataBean.setXmlFileName(results.getString("OLD_XML_FILE_REF"));
	//		deviceDataBean.setWipXmlFileRef(results.getString("WIP_XML_FILE_REF"));
			deviceDataBean.setDeviceSerialNum(results.getString("DEVICE_SERIAL_NO"));
			deviceDataBean.setXmlFilePath(results.getString("NEW_XML_FILE_REF"));
			
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetDeviceDataQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return deviceDataBean;
	}//getDataObject() ends 
	
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
			sql.append(" SELECT DEVICE_DATA_HISTORY.DEVICE_DATA_HISTORY_ID, ");
			sql.append(" DEVICE_DATA_HISTORY.NEW_XML_FILE_REF,  ");
			sql.append(" DEVICE_DATA_HISTORY.OLD_XML_FILE_REF,  ");
			sql.append(" DEVICE.DEVICE_ID, ");
			sql.append(" DEVICE.DEVICE_SERIAL_NO, ");
			sql.append(" LOCATION.LOCATION_ID ");
			sql.append(" FROM ");
			sql.append(" LOCATION, ");
			sql.append(" DEVICE, ");
			sql.append(" DEVICE_DATA_HISTORY, ");
			sql.append(" DEVICE_SCHEDULE_HISTORY  ");
			sql.append(" WHERE 1=1 ");
			sql.append(" AND DEVICE_SCHEDULE_HISTORY.DEVICE_SCHEDULE_HISTORY_ID = DEVICE_DATA_HISTORY.DEVICE_SCHEDULE_HISTORY_ID ");
			sql.append(" AND DEVICE_SCHEDULE_HISTORY.DEVICE_ID = DEVICE.DEVICE_ID ");
			sql.append(" AND DEVICE.DEVICE_LOCATION_ID = LOCATION.LOCATION_ID ");
			
			if (deviceDataBeanObj.getDeviceDataHistoryId() > 0)
			{
				sql.append(" AND DEVICE_DATA_HISTORY.DEVICE_SCHEDULE_HISTORY_ID =" + deviceDataBeanObj.getDeviceDataHistoryId() );
			}
			
			//System.out.println("GetDeviceDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

			logger.log(Level.INFO,"GetDeviceDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetDeviceDataQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
}