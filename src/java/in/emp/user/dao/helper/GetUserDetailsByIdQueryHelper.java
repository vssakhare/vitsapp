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


public class GetUserDetailsByIdQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetUserDetailsByIdQueryHelper.class);
	private UserBean userBeanObj;
	private String columnName;
	private String sortOrder;
	
	
	/**
	 * @public Constructor GetUserSearchResultListQueryHelper()
	 */
	public GetUserDetailsByIdQueryHelper(UserBean userBeanObj)
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
			userObj.setUserLoginId(results.getString("LOGIN_ID"));
			userObj.setUserPassword(results.getString("PASSWORD"));
			userObj.setStatus(results.getString("STATUS_CD"));
			userObj.setUserEmpId(results.getLong("EMPLOYEE_ID"));
			userObj.setUserName(results.getString("EMPLOYEE_NAME"));
			//userObj.setUserLastName(results.getString("LAST_NAME"));
			userObj.setCreatedBy(results.getLong("CREATED_BY"));
			
			if(results.getTimestamp("CREATED_DT") != null)
				userObj.setCreatedDt(new java.sql.Date((results.getTimestamp("CREATED_DT")).getTime()));
			
			userObj.setUpdatedBy(results.getLong("UPDATED_BY"));

			if(results.getTimestamp("UPDATED_DT") != null)
				userObj.setUpdatedDt(new java.sql.Date((results.getTimestamp("UPDATED_DT")).getTime()));

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetUserDetailsByIdQueryHelper ::: getDataObject() :: Exception :: "+ex);
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
			sql.append(" SELECT ");
			sql.append(" secUser.USER_ID, secUser.LOGIN_ID, secUser.PASSWORD, secUser.STATUS_CD ");
			//sql.append(" , secUser.EMPLOYEE_ID, emp.FIRST_NAME, emp.LAST_NAME ");
                        sql.append(" , secUser.EMPLOYEE_ID, emp.EMPLOYEE_NAME ");
			sql.append(" , secUser.CREATED_BY, secUser.CREATED_DT, secUser.UPDATED_BY, secUser.UPDATED_DT");
			sql.append(" FROM ");
			sql.append(" SEC_USER secUser, EMPLOYEE emp ");
			sql.append("WHERE ");
			sql.append(" secUser.EMPLOYEE_ID = emp.EMPLOYEE_ID(+) ");
			
			if(userBeanObj.getUserId() > 0)
			{
				sql.append(" AND secUser.USER_ID = "+ userBeanObj.getUserId());
			}
						
			logger.log(Level.INFO, "GetUserDetailsByIdQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
			
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetUserDetailsByIdQueryHelper ::: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class
