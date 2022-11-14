package in.emp.master.dao;

//-- java imports 
import in.emp.dao.BaseDao;
import in.emp.master.bean.MasterBean;
import in.emp.master.dao.helper.GetMasterListQueryHelper;
import in.emp.master.dao.helper.GetLocationsQueryHelper;
import in.emp.master.bean.LocationBean;
import in.emp.master.bean.LocationTypeBean;
import in.emp.master.dao.helper.GetMasterListQueryHelperSSO;

import java.util.LinkedList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Data access object for returning compound related value objects from the Oracle database.
 *
 * @author	MDA
 */

public class OracleMasterDao extends BaseDao implements MasterDao
{
	private static Logger logger = Logger.getLogger(OracleMasterDao.class);

	

	/*
	* API to get the all master list
	* @param String		the tableName
	* @throws Exception		if an error occurs 
	* @returns			List
	*/
	public LinkedList getMasterList(String tableName, String column1, String column2, String column3, String whereClause)throws Exception
	{
		LinkedList masterList = null;
		try
		{
			logger.log(Level.INFO, "OracleMasterDao :: getMasterList() :: method called");
			masterList = (LinkedList)getObjectList(new GetMasterListQueryHelper(tableName, column1, column2, column3, whereClause));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "OracleMasterDao :: getMasterList() :: Exception :: "+ex);
			throw ex;
		}
		return masterList;

	}/* End of Method */
	/*
	* API to get the all master list
	* @param String		the tableName
	* @throws Exception		if an error occurs 
	* @returns			List
	*/
	public LinkedList getMasterListSSO(String tableName, String column1, String column2, String column3, String whereClause)throws Exception
	{
		LinkedList masterList = null;
		try
		{
			logger.log(Level.INFO, "OracleMasterDao :: getMasterListSSO() :: method called");
			masterList = (LinkedList)getObjectList(new GetMasterListQueryHelperSSO(tableName, column1, column2, column3, whereClause));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "OracleMasterDao :: getMasterListSSO() :: Exception :: "+ex);
			throw ex;
		}
		return masterList;

	}/* End of Method */
	/*
	* API to get the Location list from the Database
	* @throws Exception		if an error occurs 
	* @returns			LinkedList
	*/
	public LinkedList getLocations(LocationBean locationBean) throws Exception
	{
		LinkedList locationList = null;
		try
		{
			locationList = (LinkedList)getObjectList(new GetLocationsQueryHelper(locationBean));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "OracleMasterDao :: getLocations() :: Exception :: "+ex);
			throw ex;
		}
		return locationList;
	}
                public LinkedList getMasterListForNDM(String tableName, String column1, String column2, String column3, String statusCDColumn, String whereClause) throws Exception
        {

		LinkedList masterList = null;
		try
		{
			logger.log(Level.INFO, "OracleMasterDao :: getMasterListForNDM() :: method called");
			masterList = (LinkedList)getObjectList(new GetMasterListQueryHelper(tableName, column1, column2, column3, statusCDColumn, whereClause));
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "OracleMasterDao :: getMasterListForNDM() :: Exception :: "+ex);
			throw ex;
		}
		return masterList;

        }


}//end class