package in.emp.user.dao.helper;

//-- Java imports
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.Connection;
import java.util.*;

//--  imports
import in.emp.dao.TxnHelper;
import in.emp.user.bean.UserBean;
import in.emp.user.bean.UserPrezData;
import in.emp.util.ApplicationUtils;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * Transaction helper class for User
 */
public class UserTxnHelper implements TxnHelper
{
	private static Logger logger = Logger.getLogger(UserTxnHelper.class);
	private UserBean userBeanObj;
	private UserPrezData userPrezData;
		
	/**
	 * @public Constructor UserTxnHelper()
	 * @param userBeanObj object of UserBean 
	 */
	public UserTxnHelper(UserBean userBeanObj)
	{
		this.userBeanObj = userBeanObj;
	}//constructor ends

	/**
	 * @public Constructor UserTxnHelper()
	 * @param userPrezData object of UserPrezData 
	 */
	public UserTxnHelper(UserPrezData userPrezData)
	{
		this.userPrezData = userPrezData;
	}//constructor ends
	
	/**
	 * Public API to create data Object.
	 * @param conn object of Connection
	 * @return Object
	 * @throws Exception
	 */
	public Object createObject(Connection conn) throws Exception
	{
		PreparedStatement statement = null;
		StringBuffer sql = new StringBuffer();
		int fldCount = 1;
		int count = 0;
		UserBean userBean = null;
		try
		{
			logger.log(Level.INFO,"UserTxnHelper ::: createObject() :: method called ");
			userBean = userPrezData.getUserBean();
			
			sql.append(" INSERT INTO SEC_USER "+
					" ("   +
					" USER_ID,  "   +
					" LOGIN_ID,  "   +
					" PASSWORD, "+
					" CREATED_BY, "   +
					" CREATED_DT, "   +
					" UPDATED_BY, "   +
					" UPDATED_DT, "   +
					" STATUS_CD,  "   +
					" EMPLOYEE_ID " +
					" )"   +
					" VALUES"   +
					" (?,?,?,?,SYSDATE,?,SYSDATE,?,?)" );

			
			statement = null;
			statement = conn.prepareStatement(sql.toString());
			fldCount = 1;

			userBean.setUserId(ApplicationUtils.getNextSequenceId(conn,"SEQ_SEC_USER").longValue());
			
			if(userBean.getUserId() > 0)
				statement.setLong(fldCount++ , userBean.getUserId());
			else
				statement.setNull(fldCount++, Types.NUMERIC);

			if(userBean.getUserLoginId() != null)
				statement.setString(fldCount++ , userBean.getUserLoginId());
			else
				statement.setNull(fldCount++, Types.VARCHAR);

			if(userBean.getUserPassword() != null)
				statement.setString(fldCount++ , userBean.getUserPassword());
			else
				statement.setNull(fldCount++, Types.VARCHAR);

			if(userBean.getCreatedBy() > 0)
				statement.setLong(fldCount++ , userBean.getCreatedBy());
			else
				statement.setNull(fldCount++, Types.NUMERIC);

			if(userBean.getUpdatedBy() > 0)
				statement.setLong(fldCount++ , userBean.getUpdatedBy());
			else
				statement.setNull(fldCount++, Types.NUMERIC);
			
			/*if(userBean.getStatus() != null)
				statement.setString(fldCount++ , userBean.getStatus());
			else
				statement.setNull(fldCount++, Types.VARCHAR);*/
			statement.setString(fldCount++ , "A");

			if(userBean.getUserEmpId() > 0)
				statement.setLong(fldCount++ , userBean.getUserEmpId());
			else
				statement.setNull(fldCount++, Types.NUMERIC);


			logger.log(Level.INFO,"UserTxnHelper ::: createObject() :: SQL :: "+sql.toString());
			//logger.log(Level.INFO,"UserTxnHelper ::: createObject() :: fldCount is :: "+fldCount);
			
			count = statement.executeUpdate();
			
			if(count > 0)
			{
				this.populateSecUserRole(conn);
                                this.populateSecUserArea(conn);
			}
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			logger.log(Level.ERROR,"UserTxnHelper ::: createObject() :: Exception :: " + ex);
			throw ex;
		}
		return (Object)userPrezData;
	}//createObject() ends
	
	/**
	 * Public API to update data Object.
	 * @param conn object of Connection
	 * @return void
	 * @throws Exception
	 */
	public void updateObject(Connection conn) throws Exception
	{	
		PreparedStatement statement = null;
		StringBuffer sql = null;
		int fldCount = 1;
		UserBean userBean = null;
		int count = 0;
		try
		{
			logger.log(Level.INFO,"RoleTxnHelper ::: updateObject() :: method called");
			userBean = userPrezData.getUserBean();

			sql = new StringBuffer();

			sql.append(" UPDATE SEC_USER "   +
							" SET "   +
							" LOGIN_ID = ?, "   +
							" PASSWORD = ?, "+
							" CREATED_BY = ?, "   +
							" CREATED_DT = SYSDATE, "   +
							" UPDATED_BY= ?, "   +
							" UPDATED_DT = SYSDATE, "   +
							//" STATUS_CD = ?,  "   +
							" EMPLOYEE_ID = ? " +
							" WHERE "   +
							" USER_ID = ? "  );

			statement = null;
			statement = conn.prepareStatement(sql.toString());
			fldCount = 1;

			if(userBean.getUserLoginId() != null)
				statement.setString(fldCount++ , userBean.getUserLoginId());
			else
				statement.setNull(fldCount++, Types.VARCHAR);

			if(userBean.getUserPassword() != null)
				statement.setString(fldCount++ , userBean.getUserPassword());
			else
				statement.setNull(fldCount++, Types.VARCHAR);

			if(userBean.getCreatedBy() > 0)
				statement.setLong(fldCount++ , userBean.getCreatedBy());
			else
				statement.setNull(fldCount++, Types.NUMERIC);

			if(userBean.getUpdatedBy() > 0)
				statement.setLong(fldCount++ , userBean.getUpdatedBy());
			else
				statement.setNull(fldCount++, Types.NUMERIC);
			
			/*if(userBean.getStatus() != null)
				statement.setString(fldCount++ , userBean.getStatus());
			else
				statement.setNull(fldCount++, Types.VARCHAR);*/

			if(userBean.getUserEmpId() > 0)
				statement.setLong(fldCount++ , userBean.getUserEmpId());
			else
				statement.setNull(fldCount++, Types.NUMERIC);
			
			statement.setLong(fldCount++ , userBean.getUserId());

			logger.log(Level.INFO," UserTxnHelper ::: updateObject() :: SQL  :: "+sql.toString());
			count = statement.executeUpdate();

			if(count > 0)
			{
				//-- Delete from SEC_USER_ROLE
				fldCount = 1;
				count = -1;
				sql = new StringBuffer();
				sql.append(" DELETE "   +
					  " FROM "   +
					  " SEC_USER_ROLE "   +
					  " WHERE "   +
					  " USER_ID = ? "  );

				statement = null;
				statement = conn.prepareStatement(sql.toString());

				statement.setLong(fldCount++ , userBean.getUserId());

				count = statement.executeUpdate();
							
				if(count > 0)
				{
					this.populateSecUserRole(conn);				
				}
			}
                        if(count > 0)
			{
				//-- Delete from USER_ACCESS_CONTROL
				fldCount = 1;
				count = 1;
				sql = new StringBuffer();
				sql.append(" DELETE "   +
					  " FROM "   +
					  " USER_ACCESS_CONTROL "   +
					  " WHERE "   +
					  " USER_ID = ? "  );

				statement = null;
				statement = conn.prepareStatement(sql.toString());

				statement.setLong(fldCount++ , userBean.getUserId());

				count = statement.executeUpdate();

				
					this.populateSecUserArea(conn);
				
			}
			conn.commit();
		}
		catch (Exception ex)
		{
			conn.rollback();
			logger.log(Level.ERROR,"UserTxnHelper ::: updateObject() :: Exception :: " + ex);
			throw ex;
		}

	}//updateObject() ends
	
	/**
	 * Public API to delete data Object.
	 * @param conn object of Connection
	 * @return void
	 * @throws Exception
	 */
    public void deleteObject(Connection conn) throws Exception
	{
	}//deleteObject() ends

	
	/**
	 * Public API to update ObjectAttribute
	 * @param conn object of Connection
	 * @return void
	 * @throws Exception
	 */
	public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception
	{		
		PreparedStatement statement = null;
		StringBuffer sql = null;
		StringTokenizer stringTokenizer = null;
		int fldCount = 1;
		int count = -1;
		try 
		{	
			logger.log(Level.INFO,"UserTxnHelper ::: updateObjectAttribute() :: method called");

			sql = new StringBuffer();				
			sql.append( " UPDATE SEC_USER  "
							+" SET " + ApplicationUtils.generateColumnString(attributeMap)
							+" UPDATED_DT = SYSDATE "
							+" WHERE USER_ID = ? ");
					
			statement = null;
			statement = conn.prepareStatement(sql.toString());
								
			if(userBeanObj != null)
			{
				stringTokenizer = new StringTokenizer(userBeanObj.getUserIdStr(),"," );
				Long userId = null;
				while(stringTokenizer.hasMoreTokens())
					{
						userId = new Long(stringTokenizer.nextToken());
						statement.setLong(1, userId.longValue());
						statement.addBatch();
					}
			}
			logger.log(Level.INFO,"UserTxnHelper ::: updateObjectAttribute() :: SQL :: "+sql.toString());
			statement.executeBatch();
			
		/*	
			if(userBeanObj.getUserStatus() != null)
				statement.setString(fldCount++ , userBeanObj.getUserStatus());
			else
				statement.setNull(fldCount++, Types.VARCHAR);

			if(userBeanObj.getUserId() > 0)
				statement.setLong(fldCount++ , userBeanObj.getUserId());
			else
				statement.setNull(fldCount++, Types.NUMERIC);
			
			
	
			count = statement.executeUpdate();
		*/	
			conn.commit();
		}
		catch(Exception ex)
		{
			conn.rollback();
			logger.log(Level.ERROR, "UserTxnHelper ::: updateObjectAttribute() :: Exception :: " + ex + " ");	
			throw ex;
		}
		finally
		{
			try
			{
				sql = null;
			}
			catch (Exception ignore)
			{
			}
			try
			{
				if(statement != null)
				{
					statement.close();
					statement = null;
				}
			}
			catch (Exception ignore)
			{
			}
		}
	}//updateObjectAttribute ends

	 /**
	 * Private API to populate roles and users
	 *
	 * @param conn				the object of Connection  
	 * @throws Exception		if an error occurs
	 */

	public void populateSecUserRole(Connection conn) throws Exception
	{
		PreparedStatement statement = null;
		LinkedList userRoleListObj = null;
		Iterator itUserRoleObj  = null;
		StringBuffer sql = new StringBuffer();
		UserBean userBean = null;
		Long pkRole = null;

		try
		{
			userRoleListObj = userPrezData.getSelectedRoleList();
			logger.log(Level.INFO,"UserTxnHelper :: populateSecUserRole() :: method called :: ");
			userBean = userPrezData.getUserBean();			
			
			if(userRoleListObj != null)
			{
				sql.append(" INSERT INTO "   +
						" SEC_USER_ROLE "   +
						" ( "   +
						" USER_ID, " +
						" ROLE_ID, "+
						" STATUS_CD, " +
						" CREATED_BY, " +
						" CREATED_DT, " +
						" UPDATED_BY, "  +
						" UPDATED_DT "  +
						" ) "   +
						" VALUES "   +
						" (?,?,?,?,SYSDATE,?,SYSDATE) " );

				statement = null;
				statement = conn.prepareStatement(sql.toString());
				itUserRoleObj = userRoleListObj.iterator();
				logger.log(Level.INFO,"UserTxnHelper :: populateSecUserRole() :: SQLl :: " +sql);
					
				while(itUserRoleObj.hasNext())
				{
					int fldCount = 1;
					
					pkRole = (Long)itUserRoleObj.next();
					
					statement.setLong(fldCount++ , userBean.getUserId());	
					statement.setLong(fldCount++ , pkRole.longValue());
					statement.setString(fldCount++ , "A");
					statement.setLong(fldCount++ , userBean.getCreatedBy());
					statement.setLong(fldCount++ , userBean.getUpdatedBy());
					statement.addBatch();
				}
				statement.executeBatch();
			}

		}
		catch(Exception ex)
		{
			////ex.printStackTrace();
			logger.log(Level.ERROR,"UserTxnHelper ::: populateSecUserRole() :: Exception :: "+ex);
			throw ex;
		}
		finally
		{
			try
			{
				sql = null;
				if(statement != null)
				{
					statement.close();
					statement = null;
				}
			}
			catch (Exception ignore){}
		}
	}

        public void populateSecUserArea(Connection conn) throws Exception
	{
		PreparedStatement statement = null;
		LinkedList userAreaListObj = null;
		Iterator itUserAreaObj  = null;
		StringBuffer sql = new StringBuffer();
		UserBean userBean = null;
		Long pkRole = null;

		try
		{
			userAreaListObj = userPrezData.getSelectedAreaList();
			logger.log(Level.INFO,"UserTxnHelper :: populateSecUserArea() :: method called :: ");
			userBean = userPrezData.getUserBean();

			if(userAreaListObj != null)
			{
				sql.append(" INSERT INTO "   +
						" USER_ACCESS_CONTROL"   +
						" ( "   +
						" USER_ID, " +
						" LOC_ID, "+
						" STATUS_CD, " +
						" CREATED_BY, " +
						" CREATED_DT, " +
						" UPDATED_BY, "  +
						" UPDATED_DT "  +
						" ) "   +
						" VALUES "   +
						" (?,?,?,?,SYSDATE,?,SYSDATE) " );

				statement = null;
				statement = conn.prepareStatement(sql.toString());
				itUserAreaObj = userAreaListObj.iterator();
				logger.log(Level.INFO,"UserTxnHelper :: populateSecUserArea() :: SQLl :: " +sql);

				while(itUserAreaObj.hasNext())
				{
					int fldCount = 1;

					pkRole = (Long)itUserAreaObj.next();

					statement.setLong(fldCount++ , userBean.getUserId());
					statement.setLong(fldCount++ , pkRole.longValue());
					statement.setString(fldCount++ , "A");
					statement.setLong(fldCount++ , userBean.getCreatedBy());
					statement.setLong(fldCount++ , userBean.getUpdatedBy());
					statement.addBatch();
				}
				statement.executeBatch();
			}

		}
		catch(Exception ex)
		{
			////ex.printStackTrace();
			logger.log(Level.ERROR,"UserTxnHelper ::: populateSecUserArea() :: Exception :: "+ex);
			throw ex;
		}
		finally
		{
			try
			{
				sql = null;
				if(statement != null)
				{
					statement.close();
					statement = null;
				}
			}
			catch (Exception ignore){}
		}
	}

}//class ends