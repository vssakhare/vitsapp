package in.emp.user.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//--  Imports
import in.emp.dao.QueryHelper;

import in.emp.user.bean.EmployeeBean;
import in.emp.user.bean.UserBean;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetAllEmployeesQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetAllEmployeesQueryHelper.class);
	private UserBean userBeanObj;
	
	/**
	 * @public Constructor GetAllRolesQueryHelper()
	 */
	public GetAllEmployeesQueryHelper(UserBean userBeanObj)
	{	
		this.userBeanObj = userBeanObj;
	} // End of Constructor
	
	
	/*public API to get the Data object from the result set
	@param Result Set Object
	@ Return Object
	@Throws Exception*/

	public Object getDataObject(ResultSet results) throws Exception 
	{
		EmployeeBean empBeanObj = new EmployeeBean();
		try
		{
			// Setting Rule  Info in the EmployeeBean object from result set
			empBeanObj.setEmployeeId(results.getLong("EMPLOYEE_ID"));
			empBeanObj.setExtEmpRefNo(results.getString("CPF_NUMBER"));
			empBeanObj.setEmpName(results.getString("EMPLOYEE_NAME"));
			//empBeanObj.setEmpLastName(results.getString("LAST_NAME"));
			empBeanObj.setStatus(results.getString("STATUS_CD"));
			
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetAllEmployeesQueryHelper ::: getDataObject() :: Exception :: "+ex);
			throw ex;
		}
		return empBeanObj;
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
			sql.append(" emp.EMPLOYEE_ID, emp.CPF_NUMBER, emp.EMPLOYEE_NAME, ");
                        //sql.append(" emp.LAST_NAME, emp.STATUS_CD ");
                        sql.append(" emp.STATUS_CD ");
			sql.append(" FROM ");
			sql.append(" EMPLOYEE emp");
			sql.append(" WHERE");
			sql.append(" emp.STATUS_CD = 'A' ");
			sql.append(" AND emp.EMPLOYEE_ID NOT IN (SELECT EMPLOYEE_ID FROM SEC_USER  WHERE EMPLOYEE_ID > 0)");
			sql.append(" ORDER BY emp.EMPLOYEE_NAME ASC" );
						
		
			logger.log(Level.INFO, "GetAllEmployeesQueryHelper ::: getQueryResults() :: SQL :: "+sql.toString());

			statement = connection.prepareStatement(sql.toString());
			rs = statement.executeQuery();		
			
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetAllEmployeesQueryHelper ::: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}		
		return rs;
	}
	
}// End of Class