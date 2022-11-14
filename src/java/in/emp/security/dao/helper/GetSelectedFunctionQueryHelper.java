package in.emp.security.dao.helper;

//-- java Imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//-- MDA Imports
import in.emp.dao.QueryHelper;
import in.emp.security.bean.SecUserBean;
import in.emp.security.bean.SecFunctionBean;


//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Query helper class to get list of all uesrs
 */
public class GetSelectedFunctionQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetSelectedFunctionQueryHelper.class);	
	private SecUserBean secUserObj = null;
	
	/**
	 * @public Constructor GetUserDetailQueryHelper()
	 * @param userBeanObj object of UserBean
	 */
	public GetSelectedFunctionQueryHelper(SecUserBean userObj)
	{
		this.secUserObj = userObj;
	} // End Of Constructor

	/**
	 * Public API to get data object.
	 * @param results object of ResultSet
	 * @return Object
	 * @throws Exception
	 */
	public Object getDataObject(ResultSet results) throws Exception 
	{
		SecFunctionBean secFuncObj = null;
		try
		{
			secFuncObj = new SecFunctionBean();
			
			// Setting Data in the SecUserBean -- 
			if(results.getString("FUNCTION_NAME") != null)
			{
				secFuncObj.setFunctionName(results.getString("FUNCTION_NAME"));
			}
                       
			if(results.getString("UI_ACTION_NAME") != null)
			{
				secFuncObj.setUiActionName(results.getString("UI_ACTION_NAME"));
			}
			if(results.getLong("FUNCTION_ID") > 0)
			{					
				secFuncObj.setFunctionId(new Long(results.getLong("FUNCTION_ID")));
			}		 		 

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetSelectedFunctionQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}

		return secFuncObj;

	} //getDataObject() ends 
	
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
			logger.log(Level.INFO, "GetSelectedFunctionQueryHelper :: getQueryResults() :: method called");

			// getting data from SecUserBean--
			sql.append(" SELECT DISTINCT fun.FUNCTION_NAME, fun.UI_ACTION_NAME , fun.FUNCTION_ID FROM  " +
						"  SEC_USER u, " +
						"  SEC_USER_ROLE rl, " +
						"  SEC_ROLE_FUNCTION rfun , " +
						"  SEC_FUNCTION fun, " +
						"  SEC_ROLE r" +
						"  WHERE " +
						"  u.USER_ID = rl.USER_ID " +
						"  AND rl.ROLE_ID =  rfun.ROLE_ID " +
						"  AND rfun.FUNCTION_ID = fun.FUNCTION_ID " +
						"  AND r.ROLE_ID = rfun.ROLE_ID" +
						"  AND rl.USER_ID = ? " +
						"  AND r.STATUS_CD='A'" +
						"  AND fun.STATUS_CD='A'" +
						"  AND rfun.STATUS_CD='A'" );
                      
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1,secUserObj.getUserId());
			
			logger.log(Level.INFO, "GetSelectedFunctionQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			// Executing Query
			rs = statement.executeQuery();	
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetSelectedFunctionQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}
		
		return rs;

	} // End Of Method

} //End Of Class