package in.emp.user.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//--  Imports
import in.emp.dao.QueryHelper;

import in.emp.search.bean.LocationBean;
import in.emp.user.bean.UserBean;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetAllSelectedAreasQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetAllSelectedAreasQueryHelper.class);
	private UserBean userBeanObj;


	
	public GetAllSelectedAreasQueryHelper(UserBean userBeanObj)
	{
		this.userBeanObj = userBeanObj;
	} // End of Constructor


	
	public Object getDataObject(ResultSet results) throws Exception
	{
		LocationBean locationBeanObj = new LocationBean();
		try
		{
			

			locationBeanObj.setLocationId(results.getInt("LOC_ID"));
			locationBeanObj.setLocationDescription(results.getString("LOCATION_DESC"));
                        locationBeanObj.setLocationCd(results.getString("LOCATION_CD"));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetAllSelectedAreasQueryHelper ::: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return locationBeanObj;
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
		int fldCount = 1;
		try
		{
			sql.append(" SELECT ");
			sql.append(" uac.LOC_ID,LOCATION_DESC,loc.LOCATION_CD");
			sql.append(" FROM ");
			sql.append(" user_access_control uac ,location loc");
			sql.append(" WHERE");
			sql.append(" uac.loc_id=loc.location_id");
			sql.append(" AND uac.USER_ID = ? ");
			sql.append(" AND uac.STATUS_CD = 'A' ");
			sql.append(" ORDER BY UPPER(loc.LOCATION_CD) ASC" );

			statement = connection.prepareStatement(sql.toString());
			if(userBeanObj.getUserId() > 0 )
			{
			statement.setLong(fldCount++, userBeanObj.getUserId());
			}
			logger.log(Level.INFO, "GetAllSelectedAreasQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());

			rs = statement.executeQuery();

		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetAllSelectedAreasQueryHelper ::: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}
		return rs;
	}

}// End of Class