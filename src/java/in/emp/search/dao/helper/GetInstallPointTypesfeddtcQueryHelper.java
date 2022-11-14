package in.emp.search.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//-- MDA Imports
import in.emp.dao.QueryHelper;
import in.emp.search.bean.InstallPointTypeBean;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class GetInstallPointTypesfeddtcQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetInstallPointTypesfeddtcQueryHelper.class);
	
	
	/**
	 * @public Constructor GetDeviceTypesQueryHelper()
	 */
	public GetInstallPointTypesfeddtcQueryHelper()
	{

	} // End of Constructor
	
	
	
	/*public API to get the Data object from the result set
	@param Result Set Object
	@ Return Object
	@Throws Exception*/

	public Object getDataObject(ResultSet results) throws Exception 
	{
		InstallPointTypeBean installPointTypeBeanObj = new InstallPointTypeBean();
		try
		{
				// Setting Device Types in the InstallPointTypeBean object from result set
				installPointTypeBeanObj.setInstallPointTypeId(results.getInt("INSTALL_POINT_TYPE_ID"));
				installPointTypeBeanObj.setInstallPointTypeCd(results.getString("INSTALL_POINT_TYPE_CD"));

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetInstallPointTypesfeddtcQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return installPointTypeBeanObj;
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
			sql.append("SELECT ");
			sql.append("INSTALL_POINT_TYPE_ID, ");
			sql.append("INSTALL_POINT_TYPE_CD ");
			sql.append("FROM INSTALL_POINT_TYPE ");
			sql.append("WHERE STATUS_CD = 'A' AND INSTALL_POINT_TYPE_ID IN(1,2) "); 
			sql.append("ORDER BY UPPER(INSTALL_POINT_TYPE_CD) ASC "); 

			logger.log(Level.INFO, "GetInstallPointTypesfeddtcQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());
			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetInstallPointTypesfeddtcQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class
