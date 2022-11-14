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

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Query helper class to get list of all uesrs
 */
public class GetUserAccessQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetUserAccessQueryHelper.class);
	private String loginId;
	private String password;
	private SecUserBean secUserObj = null;
	
	/**
	 * @public Constructor GetUserDetailQueryHelper()
	 * @param userBeanObj object of UserBean
	 */
	public GetUserAccessQueryHelper(SecUserBean userObj)
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
                String userAccessString = new String();
		try
		{
			
			// Setting Data in the SecUserBean -- 
			userAccessString = results.getString("LOC_ID");

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetUserAccessQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}

		return userAccessString;

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
			logger.log(Level.INFO, "GetUserAccessQueryHelper :: getQueryResults() :: method called");

			// getting data from SecUserBean--
			sql.append("SELECT LOC_ID ");
			sql.append("FROM USER_ACCESS_CONTROL ");
			sql.append("WHERE USER_ID = ? ");
			sql.append("AND STATUS_CD = 'A' ");
			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1,secUserObj.getUserId());

			logger.log(Level.INFO, "GetUserAccessQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			// Executing Query
			rs = statement.executeQuery();	
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetUserAccessQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}
		
		return rs;

	} // End Of Method

} //End Of Class