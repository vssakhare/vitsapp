package in.emp.user.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.text.*;

//--  Imports
import in.emp.dao.QueryHelper;
import in.emp.user.bean.UserBean;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Query helper class to get total number of User
 */
public class GetTotalUserQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetTotalUserQueryHelper.class);
	private UserBean userBeanObj;

	/**
	 * @public Constructor GetTotalUserQueryHelper()
	 */
	public GetTotalUserQueryHelper(UserBean userBeanObj)
	{
		this.userBeanObj = userBeanObj;
	}

	/**
	 * Public API to get data object.
	 * @param results object of ResultSet
	 * @return Object
	 * @throws Exception
	 */
	public Object getDataObject(ResultSet results) throws Exception 
	{
		UserBean userObj = new UserBean();
		try
		{
			// Setting Search Total Records Data in the User Bean object from result set
			userObj.setTotalRecords(results.getDouble("CNT"));	
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetTotalUserQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return userObj;
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
			sql.append(" SELECT COUNT(1) CNT ");
			sql.append(" FROM ");
			sql.append(" SEC_USER secUser, EMPLOYEE emp ");
						
			if(userBeanObj.getLocationTypeId() > -1 && userBeanObj.getLocationId() > 0)
			{
				sql.append(" , LOCATION loc, LOCATION_TYPE locTyp  ");
			}

			sql.append(" WHERE ");
			sql.append(" secUser.EMPLOYEE_ID = emp.EMPLOYEE_ID(+)  ");
			sql.append(" AND secUser.STATUS_CD IN ('A','I') ");
			
	/*		if(userBeanObj.getLocationTypeId() > -1 && userBeanObj.getLocationId() > 0)
			{
				sql.append(" AND emp.LOCATION_ID = loc.LOCATION_ID(+) ");
				sql.append(" AND loc.LOCATION_TYPE_ID  = locTyp.LOCATION_TYPE_ID");
				sql.append(" AND loc.LOCATION_ID = "+userBeanObj.getLocationId());
				sql.append(" AND locTyp.LOCATION_TYPE_ID = "+userBeanObj.getLocationTypeId());  
			}
	*/		
			if (userBeanObj.getSubDivisionId() > 0)
			{
				sql.append(" AND emp.LOCATION_ID = " + userBeanObj.getSubDivisionId());
			}
			else if (userBeanObj.getDivisionId() > 0)
			{
				sql.append(" AND emp.LOCATION_ID = " + userBeanObj.getDivisionId());
			}
			else if (userBeanObj.getCircleId() > 0)
			{
				sql.append(" AND emp.LOCATION_ID = " + userBeanObj.getCircleId());
			}
			else if (userBeanObj.getZoneId() > 0)
			{
				sql.append(" AND emp.LOCATION_ID = " + userBeanObj.getZoneId());
			}

			logger.log(Level.INFO, "GetTotalUserQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetTotalUserQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
}