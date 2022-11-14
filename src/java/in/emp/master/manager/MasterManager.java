package in.emp.master.manager;

//-- Java Imports
import in.emp.master.MasterDelegate;
import in.emp.master.bean.MasterBean;
import in.emp.master.dao.OracleMasterDao;
import in.emp.master.dao.MasterDao;
import in.emp.master.bean.LocationBean;
import in.emp.master.bean.LocationTypeBean;

import java.util.LinkedList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;



/**
 * Manager for accessing and manipulating system related objects.
 *
 * @author 
 */
public class MasterManager implements MasterDelegate 
{
	/** The logger object for this class */
	private static Logger logger = Logger.getLogger(MasterManager.class);

	/**
	 * API to get the all master list
	* @param String		the tableName
	* @throws Exception		if an error occurs 
	* @returns			List
	 */	
	public LinkedList getMasterList(String tableName, String column1, String column2, String column3, String whereClause) throws Exception
	{
		logger.log(Level.INFO, "MasterManager :: getMasterList()");
		LinkedList masterList = null;
		MasterDao masterDao = null;

		try
		{
			masterDao = new OracleMasterDao();
			masterList = (LinkedList)masterDao.getMasterList(tableName, column1, column2, column3, whereClause);
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "MasterManager :: getMasterList() :: Exception :: " + ex);
			throw ex;
		}		
		finally
		{
			try
			{
				masterDao = null;
			}
			catch (Exception ignore){}
		}
		return masterList;
	}

	/*
	* API to get the Location list from the Database
	* @throws Exception		if an error occurs 
	* @returns			LinkedList
	*/
	public LinkedList getLocations(LocationBean locationBean) throws Exception
	{
		LinkedList locationList = null;
		MasterDao masterDao = null;
		try
		{	
			masterDao = new OracleMasterDao();
			locationList = masterDao.getLocations(locationBean);
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "OracleMasterDao :: getLocations() :: Exception :: "+ex);
			throw ex;
		}
		return locationList;
	}

	
}//-- End class