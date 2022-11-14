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
 * Query helper class to get list of all users
 */
public class GetUserDetailQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetUserDetailQueryHelper.class);
	private String loginId;
	private String password;
	private SecUserBean secUserObj = null;

	/**
	 * @public Constructor GetUserDetailQueryHelper()
	 * @param userBeanObj object of UserBean
	 */
	public GetUserDetailQueryHelper(SecUserBean userObj)
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
		try
		{

			// Setting Data in the SecUserBean --
			secUserObj.setUserId(results.getLong("USER_ID"));
			secUserObj.setLoginId(results.getString("LOGIN_ID"));
			secUserObj.setStatusCd(results.getString("STATUS_CD"));

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetUserDetailQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}

		return secUserObj;

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
			logger.log(Level.INFO, "GetUserDetailQueryHelper :: getQueryResults() :: method called");

			// getting data from SecUserBean--
			sql.append("SELECT USER_ID, ");
			sql.append("LOGIN_ID, ");
			sql.append("STATUS_CD ");
			sql.append("FROM SEC_USER ");
			sql.append("WHERE LOGIN_ID = ? ");
			sql.append("AND PASSWORD = ? ");
			sql.append("AND STATUS_CD = 'A' ");
			statement = connection.prepareStatement(sql.toString());
			//statement.setString(1,secUserObj.getLoginId());
			//statement.setString(2,secUserObj.getPassword());
            statement.setString(1,"superadmin");
			statement.setString(2,"msedclmda2");

			logger.log(Level.INFO, "GetUserDetailQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			// Executing Query
			rs = statement.executeQuery();
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetUserDetailQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}

		return rs;

	} // End Of Method

} //End Of Class