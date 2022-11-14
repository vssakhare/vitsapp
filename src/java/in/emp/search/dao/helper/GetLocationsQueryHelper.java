package in.emp.search.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//-- MDA Imports
import in.emp.dao.QueryHelper;
import in.emp.search.bean.LocationBean;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class GetLocationsQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetLocationsQueryHelper.class);
	private LocationBean locationBean;

	/**
	 * @public Constructor GetLocationTypesQueryHelper()
	  */
	public GetLocationsQueryHelper()
	{

	} // End of Constructor

	
	/**
	 * @public Constructor GetLocationTypesQueryHelper()
	 * @param locationBean object of LocationBean
	 */
	public GetLocationsQueryHelper(LocationBean locationBean)
	{
		this.locationBean = locationBean;
	} // End of Constructor


	
	/*public API to get the Data object from the result set
	@param Result Set Object
	@ Return Object
	@Throws Exception*/

	public Object getDataObject(ResultSet results) throws Exception 
	{
		LocationBean locationBeanObj = new LocationBean();
		try
		{
			// Setting Locations in the location Bean object from result set
			locationBeanObj.setLocationId(results.getInt("LOCATION_ID"));
			locationBeanObj.setLocationDescription(results.getString("LOCATION_DESC"));
			locationBeanObj.setLocationTypeId(results.getInt("LOCATION_TYPE_ID"));
			locationBeanObj.setParentLocationId(results.getInt("PARENT_LOCATION_ID"));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetLocationsQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return locationBeanObj;
	} // End of Method

		/**
	 * Public API to get query resultss
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
			sql.append(" PARENT_LOCATION_ID, "); 
			sql.append(" LOCATION_ID, ");
			sql.append(" LOCATION_DESC, ");
			sql.append(" LOCATION_TYPE_ID ");
			sql.append(" FROM LOCATION  ");
			sql.append(" WHERE STATUS_CD = 'A'  ");

			if (locationBean != null && locationBean.getParentLocationId() > 0)
			{
				sql.append(" AND PARENT_LOCATION_ID like "+locationBean.getParentLocationId());
			}
                        else if (locationBean !=null && locationBean.getParentLocationString()!=null)
                        {
                            sql.append(" AND PARENT_LOCATION_ID in ("+locationBean.getParentLocationString()+")");
                        }

			if (locationBean != null && locationBean.getLocationTypeId() > 0)
			{
				sql.append(" AND LOCATION_TYPE_ID = "+locationBean.getLocationTypeId());
			}
                       if(locationBean.getLocationTypeId() != 1)
                       {
                            if (locationBean !=null && locationBean.getAccessLocation()!=null)
                            {
                                sql.append(" AND LOCATION_ID in( select location_id from (select lm.location_id from location lm start with location_id in (" + locationBean.getAccessLocation() + ") connect by prior location_id=Parent_location_id))");
                            }
                        }
                        /* if(deviceBeanObj.getLocationQuery()!="")
                            sql.append(" AND dev.DEVICE_LOCATION_ID in ("+deviceBeanObj.getLocationQuery()+")");*/
			sql.append(" ORDER BY LOCATION_DESC "); //PARENT_LOCATION_ID,
			logger.log(Level.INFO, "GetLocationsQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetLocationsQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class
