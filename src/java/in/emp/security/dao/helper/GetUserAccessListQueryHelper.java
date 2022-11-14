/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.security.dao.helper;

/**
 *
 * @author Administrator
 */



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
public class GetUserAccessListQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetUserAccessListQueryHelper.class);
	private String loginId;
	private String password;
	private SecUserBean secUserObj = null;

	/**
	 * @public Constructor GetUserDetailQueryHelper()
	 * @param userBeanObj object of UserBean
	 */
	public GetUserAccessListQueryHelper(SecUserBean userObj)
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
			userAccessString = results.getString("location_id");

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetUserAccessListQueryHelper :: getDataObject() :: Exception :: "+ex);
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
			logger.log(Level.INFO, "GetUserAccessListQueryHelper :: getQueryResults() :: method called");
              sql.append(" SELECT location_id FROM (SELECT lm.location_id FROM location lm START WITH location_id"
                      + " IN ("+secUserObj.getAccessLocationString()+") CONNECT BY prior location_id=Parent_location_id  )");
			// getting data from SecUserBean--
			statement = connection.prepareStatement(sql.toString());

			logger.log(Level.INFO, "GetUserAccessListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			// Executing Query
			rs = statement.executeQuery();
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetUserAccessListQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}

		return rs;

	} // End Of Method

} //End Of Class


