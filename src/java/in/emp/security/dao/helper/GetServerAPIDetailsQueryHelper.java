/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.security.dao.helper;

import in.emp.dao.QueryHelper;

import in.emp.security.bean.ServerAPIBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author ipchaudhari
 */
public class GetServerAPIDetailsQueryHelper implements QueryHelper
{
        private static Logger logger = Logger.getLogger(GetServerAPIDetailsQueryHelper.class);
	
	private ServerAPIBean serverAPIBeanObj = null;
        

	/**
	 * @public Constructor GetUserDetailQueryHelper()
	 * @param userBeanObj object of UserBean
	 */
	public GetServerAPIDetailsQueryHelper(ServerAPIBean serverAPIBeanObj)
	{
		this.serverAPIBeanObj = serverAPIBeanObj;
	} // End Of Constructor


        public Object getDataObject(ResultSet results) throws Exception
        {
                ServerAPIBean serverAPIBeanObj = null;
                try
		{
                        logger.log(Level.INFO, "GetServerAPIDetailsQueryHelper :: getDataObject() :: method called");

                        serverAPIBeanObj = new ServerAPIBean();

                        serverAPIBeanObj.setCommand_Id(results.getString("COMMAND_ID"));
                        serverAPIBeanObj.setMfg_Name(results.getString("MFG_NAME"));
                        serverAPIBeanObj.setExecution_Command(results.getString("EXECUTION_COMMAND"));
                        serverAPIBeanObj.setCommand_Config_File_Path(results.getString("COMMAND_CONFIG_FILE_PATH"));
                        serverAPIBeanObj.setApi_Port(results.getString("API_PORT"));
                        serverAPIBeanObj.setSs_Status(results.getString("SS_STATUS"));
                        serverAPIBeanObj.setSs_Action(results.getString("SS_ACTION"));                        

                        ///API_ID is now becomes EXECUTION_SEQ in COMMAND Table
                        ///serverAPIBeanObj.setApi_Id(results.getString("API_ID"));
                        serverAPIBeanObj.setApi_Id(results.getString("EXECUTION_SEQ"));

                        serverAPIBeanObj.setDevice_Type_Id(results.getString("DEVICE_TYPE_ID"));


		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetServerAPIDetailsQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}

		return serverAPIBeanObj;
        }

        public ResultSet getQueryResults(Connection connection) throws Exception
        {
                PreparedStatement statement = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;

		try
		{
			logger.log(Level.INFO, "GetServerAPIDetailsQueryHelper :: getQueryResults() :: method called");

			// getting data from SecUserBean--
			sql.append(" SELECT CMD.COMMAND_ID, ");
			sql.append(" DT.MFG_NAME, ");
			sql.append(" CMD.EXECUTION_COMMAND, ");
			sql.append(" CMD.COMMAND_CONFIG_FILE_PATH, ");
			sql.append(" CMD.API_PORT, ");
			sql.append(" CMD.SS_STATUS, ");
			sql.append(" CMD.SS_ACTION, ");
			sql.append(" CMD.EXECUTION_SEQ, ");
			sql.append(" CMD.DEVICE_TYPE_ID ");
			sql.append(" FROM ");
                        sql.append(" COMMAND CMD,");
                        sql.append(" DEVICE_TYPE DT");
			sql.append(" WHERE 1 = 1 ");
			sql.append(" AND CMD.DEVICE_TYPE_ID = DT.DEVICE_TYPE_ID ");
			///sql.append(" AND EXECUTION_SEQ IS NOT NULL ");
			///sql.append(" AND EXECUTION_SEQ = 2 ");

			///sql.append(" AND CMD.API_ID = 1 ");
			sql.append(" ORDER BY ");
			sql.append(" DT.MFG_NAME, ");
			sql.append(" CMD.EXECUTION_SEQ, ");
			sql.append(" CMD.COMMAND_ID ");
			
			statement = connection.prepareStatement(sql.toString());
			

			logger.log(Level.INFO, "GetServerAPIDetailsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			// Executing Query
			rs = statement.executeQuery();
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetServerAPIDetailsQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}

		return rs;
        }

}
