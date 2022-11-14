package in.emp.search.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//-- MDA Imports
import in.emp.dao.QueryHelper;
import in.emp.search.bean.LocationTypeBean;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class GetLocationTypesQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetLocationTypesQueryHelper.class);
	
	/**
	 * @public Constructor GetLocationTypesQueryHelper()
	 * @param locationTypeBeanObj object of LocationTypeBean
	 */
	public GetLocationTypesQueryHelper()
	{

	} // End of Constructor
	
	
	
	/*public API to get the Data object from the result set
	@param Result Set Object
	@ Return Object
	@Throws Exception*/

	public Object getDataObject(ResultSet results) throws Exception 
	{
		LocationTypeBean locationTypeBeanObj = new LocationTypeBean();
		try
		{
			
			// Setting Location Types in the LocationTypeBean object from result set
				locationTypeBeanObj.setLocationTypeId(results.getInt("LOCATION_TYPE_ID"));
				locationTypeBeanObj.setLocationTypeDescription(results.getString("LOCATION_TYPE_DESC"));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetLocationTypesQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return locationTypeBeanObj;
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
			sql.append("SELECT  ");
			sql.append("LOCATION_TYPE_ID, ");
			sql.append("LOCATION_TYPE_DESC ");
			sql.append("FROM LOCATION_TYPE "); 
			sql.append("WHERE STATUS_CD = 'A' "); 
		
			logger.log(Level.INFO, "GetLocationTypesQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetLocationTypesQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class
