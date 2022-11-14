package in.emp.security.dao.helper;

//-- java Imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.Connection;

//-- MDA Imports
import in.emp.dao.QueryHelper;
import in.emp.security.bean.BuAccessBean;
import in.emp.security.bean.SecUserBean;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Query helper class to get list of all uesrs
 */
public class GetUserAccessBuListQueryHelper implements QueryHelper
{
	private static Logger logger = Logger.getLogger(GetUserAccessBuListQueryHelper.class);
	private SecUserBean secUserObj = null;
        private BuAccessBean buAccessBean = null;
	
	/**
	 * @public Constructor GetUserDetailQueryHelper()
	 * @param userBeanObj object of UserBean
	 */
	public GetUserAccessBuListQueryHelper(SecUserBean userObj)
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
                buAccessBean = new BuAccessBean();
		try
		{
			
			// Setting Data in the SecUserBean -- 
			buAccessBean.setBuCode(results.getString("BU_CODE"));

		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "GetUserAccessBuListQueryHelper :: getDataObject() :: Exception :: "+ex);
			throw ex;
		}

		return buAccessBean;

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
			logger.log(Level.INFO, "GetUserAccessBuListQueryHelper :: getQueryResults() :: method called");

			// getting data from SecUserBean--

//select LBM.BU_CODE, LOCATION_ID, LOCATION_CD, LOCATION_TYPE_ID, PARENT_LOCATION_ID, LOCATION_DESC
//from (SELECT LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_TYPE_ID, LM.PARENT_LOCATION_ID, LM.LOCATION_DESC
//FROM LOCATION LM START WITH LOCATION_ID in (2,3) CONNECT BY prior LOCATION_ID = PARENT_LOCATION_ID
//) lm, LOCATION_BU_MAPPING LBM where LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y'

			sql.append("SELECT LBM.BU_CODE BU_CODE, LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_TYPE_ID, LM.PARENT_LOCATION_ID, LM.LOCATION_DESC ");
			sql.append("FROM LOCATION_BU_MAPPING LBM, LOCATION LM ");
			sql.append("WHERE LBM.LOC_ID IN ("+ secUserObj.getAccessLocationString() +") ");
			sql.append("AND STATUS_CD = 'A' ");
                        sql.append("AND LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y' ");
                        sql.append("UNION ");
			sql.append("SELECT LBM.BU_CODE BU_CODE, LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_TYPE_ID, LM.PARENT_LOCATION_ID, LM.LOCATION_DESC ");
			sql.append("FROM LOCATION_BU_MAPPING LBM, LOCATION LM ");
			sql.append("WHERE LOC_ID IN (SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN ("+ secUserObj.getAccessLocationString() +")) ");
			sql.append("AND STATUS_CD = 'A' ");
                        sql.append("AND LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y' ");
                        sql.append("UNION ");
			sql.append("SELECT LBM.BU_CODE BU_CODE, LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_TYPE_ID, LM.PARENT_LOCATION_ID, LM.LOCATION_DESC ");
			sql.append("FROM LOCATION_BU_MAPPING LBM, LOCATION LM ");
			sql.append("WHERE LOC_ID IN (SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN (");
                        sql.append("SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN ("+ secUserObj.getAccessLocationString() +"))) ");
			sql.append("AND STATUS_CD = 'A' ");
                        sql.append("AND LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y' ");
                        sql.append("UNION ");
			sql.append("SELECT LBM.BU_CODE BU_CODE, LM.LOCATION_ID, LM.LOCATION_CD, LM.LOCATION_TYPE_ID, LM.PARENT_LOCATION_ID, LM.LOCATION_DESC ");
			sql.append("FROM LOCATION_BU_MAPPING LBM, LOCATION LM ");
			sql.append("WHERE LOC_ID IN (SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN (");
                        sql.append("SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN (");
                        sql.append("SELECT LOCATION_ID FROM LOCATION WHERE PARENT_LOCATION_ID IN ("+ secUserObj.getAccessLocationString() +")))) ");
			sql.append("AND STATUS_CD = 'A' ");
                        sql.append("AND LBM.LOC_ID = LM.LOCATION_ID AND BU_STATUS = 'Y' ");

			statement = connection.prepareStatement(sql.toString());
			statement.setLong(1,secUserObj.getUserId());

			logger.log(Level.INFO, "GetUserAccessBuListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
			// Executing Query
			rs = statement.executeQuery();	
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "GetUserAccessBuListQueryHelper :: getQueryResults() :: Exception :: " + ex);
			//ex.printStackTrace();
			throw ex;
		}
		
		return rs;

	} // End Of Method

} //End Of Class