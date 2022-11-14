package in.emp.user.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//--  Imports
import in.emp.dao.QueryHelper;

import in.emp.user.bean.UserBean;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class GetUserSearchResultListQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetUserSearchResultListQueryHelper.class);
	private UserBean userBeanObj;
	private String columnName;
	private String sortOrder;
	
	
	/**
	 * @public Constructor GetUserSearchResultListQueryHelper()
	 */
	public GetUserSearchResultListQueryHelper(UserBean userBeanObj)
	{	
		this.userBeanObj = userBeanObj;
	} // End of Constructor
	
	
	/*public API to get the Data object from the result set
	@param Result Set Object
	@ Return Object
	@Throws Exception*/

	public Object getDataObject(ResultSet results) throws Exception 
	{
		UserBean userObj = new UserBean();
		try
		{
			// Setting Rule  Info in the UserBean object from result set
			userObj.setUserId(results.getLong("USER_ID"));
			userObj.setUserName(results.getString("EMPLOYEE_NAME"));
			//userObj.setUserLastName(results.getString("LAST_NAME"));
			userObj.setUserLoginId(results.getString("LOGIN_ID"));
			userObj.setUserLocation(results.getString("LOCATION_DESC"));
			userObj.setUserOrganization(results.getString("ORGANIZATION_NAME"));
			userObj.setStatus(results.getString("STATUS_CD"));
			
			if(results.getTimestamp("UPDATED_DT") != null)
				userObj.setUpdatedDt(new java.sql.Date((results.getTimestamp("UPDATED_DT")).getTime()));
				
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetUserSearchResultListQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return userObj;
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
			sql.append("SELECT * FROM ");
			sql.append(" ( SELECT ROWNUM RNUM, A.* 	FROM (	 ");
			sql.append(" SELECT ");
			sql.append(" secUser.USER_ID, emp.EMPLOYEE_NAME, ");
			//sql.append(" emp.LAST_NAME, secUser.LOGIN_ID, ");
                        sql.append(" secUser.LOGIN_ID, ");
			sql.append(" loc.LOCATION_DESC, org.ORGANIZATION_NAME,  ");
			sql.append(" secUser.STATUS_CD, secUser.UPDATED_DT ");
			sql.append(" FROM ");
			sql.append(" SEC_USER secUser, EMPLOYEE emp, ");
			sql.append(" LOCATION loc, ORGANIZATION org ");
			
			//if(userBeanObj.getLocationTypeId() > -1 && userBeanObj.getLocationId() > 0)
		//	{
		//		sql.append(" , LOCATION_TYPE locTyp ");
		//	}

			sql.append(" WHERE ");
			sql.append(" secUser.EMPLOYEE_ID = emp.EMPLOYEE_ID(+) ");
			sql.append(" AND emp.LOCATION_ID = loc.LOCATION_ID(+) ");
			sql.append(" AND emp.ORGANIZATION_ID = org.ORGANIZATION_ID(+) ");
			sql.append(" AND secUser.STATUS_CD IN ('A','I') ");
		
			
	/*		if(userBeanObj.getLocationTypeId() > -1 && userBeanObj.getLocationId() > 0)
			{
				sql.append(" AND loc.LOCATION_TYPE_ID  = locTyp.LOCATION_TYPE_ID");
				sql.append(" AND emp.LOCATION_ID = "+userBeanObj.getLocationId());
				sql.append(" AND locTyp.LOCATION_TYPE_ID = "+userBeanObj.getLocationTypeId());  
			}
		

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
			}*/

			if (!ApplicationUtils.isBlank(userBeanObj.getSortColumnName()) && !ApplicationUtils.isBlank(userBeanObj.getLastRowValue()) && !ApplicationUtils.isBlank(userBeanObj.getLastValueDataType()))
			{
				if (!ApplicationUtils.isBlank(userBeanObj.getSortOrder()) && (userBeanObj.getSortOrder()).equals("ASC"))
				{
					sql.append(ApplicationUtils.getQueryConditionString(userBeanObj.getSortColumnName(),userBeanObj.getLastRowValue(),userBeanObj.getLastValueDataType(),">"));
				}
				else
				{
					sql.append(ApplicationUtils.getQueryConditionString(userBeanObj.getSortColumnName(),userBeanObj.getLastRowValue(),userBeanObj.getLastValueDataType(),"<"));
				}
			} 

			//-- Appending Order By Clause--
			if (userBeanObj.getSortColumnName() != null && userBeanObj.getSortOrder() != null)
			{
				sql.append(" ORDER BY "+ userBeanObj.getSortColumnName()+ " " + userBeanObj.getSortOrder());
			}

			sql.append(" ) A "); 
			sql.append("WHERE ROWNUM <=" + userBeanObj.getMax() + ") ");
			sql.append("WHERE RNUM >" + userBeanObj.getMin()); 

			logger.log(Level.INFO, "GetUserSearchResultListQueryHelper :: getQueryResults() :: SQL :: "+sql.toString());
			//System.out.println("GetUserSearchResultListQueryHelper :: getQueryResults() :: SQL :: "+sql.toString());

			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetUserSearchResultListQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class
