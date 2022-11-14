package in.emp.user.manager;

//-- Java Imports
import in.emp.user.bean.UserParameterBean;
import java.util.LinkedList;
import java.util.*;

//--  imports
import in.emp.common.AttributeData;
import in.emp.user.UserDelegate;
import in.emp.user.dao.UserDao;
import in.emp.user.dao.OracleUserDao;
import in.emp.user.bean.UserPrezData;
import in.emp.user.bean.UserBean;
import in.emp.search.bean.LocationBean;
import in.emp.common.ApplicationConstants;

import in.emp.search.dao.SearchDao;
import in.emp.search.dao.OracleSearchDao;

//-- logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Manager class
 *
 * @author 
 */
public class UserManager implements UserDelegate 
{
	private static String CLASS_NAME = UserManager.class.getName();
	//private static Logger logger = new Logger(CLASS_NAME);
	private static Logger logger = Logger.getLogger(UserManager.class);
	/**
	 * public Constructor of class UserManager
	 *
	 */
	public UserManager()
	{	}


	/*
	* API to get the search data for populating Drop downs
	* @throws Exception		if an error occurs 
	* @see						in.mda.user.UserDelegate
	* @returns					UserPrezData
	*/
	public UserPrezData getUserSearchData()throws Exception
	{
		UserPrezData userPrezDataObj = null;
		try
		{
			logger.log(Level.INFO, "UserManager :: getUserSearchData() :: method called");
			
			//-- Calling populateAllDropDowns method--
			userPrezDataObj =  populateAllDropDowns();
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserManager :: getUserSearchData() :: Exception :: "+ex);
		}
		return userPrezDataObj;
	}
	
	/*
	* API to get the search results for selected search criteria
	* @param userPrezDataObj		UserPrezData
	* @throws Exception					if an error occurs 
	* @see									in.mda.user.UserDelegate
	* @returns								UserPrezData
	*/
	public UserPrezData getUserSearchResults(UserPrezData userPrezDataObj )throws Exception
	{
		//UserPrezData userPrezDataObj = null;
		UserDao userDaoObj = null;
		LinkedList userList = null;
		try
		{
			logger.log(Level.INFO, "UserManager :: getUserSearchResults() :: method called");
			
			//-- create the dao object
			userDaoObj = new OracleUserDao();
			
			UserBean userBeanObj = userPrezDataObj.getUserBean();
			//-- call dao 
			userList = (LinkedList) userDaoObj.getUserSearchResults(userBeanObj);
			userBeanObj.setTotalRecords((userDaoObj. getUserTotalRecords(userBeanObj)).getTotalRecords());

			//-- Calling populateAllDropDowns method--
			//userPrezDataObj =  populateAllDropDowns();
			
			//--setting in userPrezDataObj bean
			userPrezDataObj.setUserList(userList);
			userPrezDataObj.setUserBean(userBeanObj);			
			
			logger.log(Level.INFO, "UserManager :: getUserSearchResults() :: userList Size :: " + userList.size());
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, "UserManager :: getUserSearchResults() :: Exception :: "+ex);
		}
		return userPrezDataObj;
	}//getUserSearchResults ends

	/** 
	* Api to update user status
	* @param userBeanObj			the UserBean
	* @throws Exception							if an error occurs 
	* @see											in.mda.user.UserDelegate
	*/
	public void updateUserStatus(UserBean userBeanObj)throws Exception
	{
		UserDao userDaoObj = null;
		HashMap attributeMap = null;
		try
		{
			logger.log(Level.INFO, " UserManager :: updateUserStatus() :: method called");
			
			if(userBeanObj != null)
			{
				attributeMap = new LinkedHashMap();
				attributeMap.put("STATUS_CD",new AttributeData(userBeanObj.getUserStatus(),"String"));
				attributeMap.put("UPDATED_BY",new AttributeData(userBeanObj.getUserId(),"Long"));
				//-- create the dao object
				userDaoObj = new OracleUserDao();
				userDaoObj.updateUserStatus(attributeMap,userBeanObj);	
			}
				
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, " UserManager :: updateUserStatus() :: Exception :: " + ex);
			throw ex;
		}	
	}//updateUserStatus ends

	/** 
	 * api to gets details of an existing user.
	 * @param userBeanObj				UserBean
	 * @returns									UserPrezData
	 * @throws Exception					if an error occurs 
	 * @see										in.mda.user.UserDelegate
	 */
	public UserPrezData getUserDetails(UserBean userBeanObj)throws Exception
	{
		UserPrezData userPrezData = null;
		UserDao userDao = null;
		LinkedList allRolesList = null;
		LinkedList selRolesList = null;
                LinkedList selAreaList = null;
		LinkedList allEmployeesList = null;
		try
		{
			logger.log(Level.INFO, " UserManager :: getUserDetails() :: method called");
			
			userPrezData = new UserPrezData();

			//-- create the dao object
			userDao = new OracleUserDao();
			
			allEmployeesList = userDao.getAllEmployees(userBeanObj);
			if(allEmployeesList != null && allEmployeesList.size() > 0)
			{
				userPrezData.setAllEmployeeList(allEmployeesList);
			}
			
			if(allRolesList != null && allRolesList.size() > 0)
			{
				userPrezData.setAllRoleList(allRolesList);
			}

						
			if(userBeanObj.getUserId() > 0 )
			{
				
                                selAreaList=userDao.getAllSelectedAreas(userBeanObj);
				userPrezData.setSelectedRoleList(selRolesList);
                                userPrezData.setSelectedAreaList(selAreaList);
				logger.log(Level.INFO, " UserManager :: getUserDetails() :: selRolesList size :: " + selRolesList.size());

				UserBean userBean = userDao.getUserDetailsById( userBeanObj);				
				userPrezData.setUserBean(userBean);
			}
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, " UserManager :: getRoleDetails() :: Exception :: " + ex);
			throw ex;
		}	
		return userPrezData;
	}

	/**
	 *  API to Create or update the user.
	 * @param userPrezData				UserPrezData
	 * @returns									UserPrezData
	 * @throws Exception					if an error occurs 
	 * @see										in.mda.user.UserDelegate
	 */
	public UserPrezData createUpdateUser(UserPrezData userPrezData) throws Exception
	{
		long userId = -1;
		UserBean userBean = null;
		UserDao userDao = null;
		try
		{

			logger.log(Level.INFO, " UserManager :: createUpdateUser() :: method called");
			if(userPrezData != null)
			{
				userBean = userPrezData.getUserBean();
				userId = userBean.getUserId();	
			}

			// -- Call Dao --
			userDao = new OracleUserDao();

			if(userId > 0)
			{
				logger.log(Level.INFO, " UserManager :: createUpdateUser() :: updateUser() method called");
				userDao.updateUser(userPrezData);
			}
			else
			{
				logger.log(Level.INFO, " UserManager :: createUpdateUser() :: createUser() method called");
				userPrezData = userDao.createUser(userPrezData);
			}
		}
		catch(Exception ex)
		{
			logger.log(Level.ERROR, "UserManager :: createUpdateUser() :: Exception :: " + ex);
			throw ex;
		}
		return userPrezData;
	}

	/*
	* Private api to get the search data for populating Drop downs
	* @throws Exception		if an error occurs 
	* @returns					DevicePrezData
	*/
	private UserPrezData populateAllDropDowns() throws Exception
	{
		LinkedList locationTypeList = null;
		LinkedList locationList = null;
		SearchDao searchDaoObj = null;
		UserPrezData userPrezDataObj = null;
		LinkedList defaultTypeLocList = null;
		try
		{
			logger.log(Level.INFO, " UserManager :: populateAllDropDowns() :: method called");
			
			LocationBean locationBeanObj = new  LocationBean();
			// setting location type for the first dropdown
			locationBeanObj.setLocationTypeId(ApplicationConstants.DEFAULT_LOCATION_TYPE);

			userPrezDataObj = new UserPrezData();

			//-- create the search dao object to get serach results.
			searchDaoObj = new OracleSearchDao();
			
			//-- call oracle search dao 
			//locationTypeList = searchDaoObj.getLocationTypesList();
			//locationList = searchDaoObj.getLocationsList();
			defaultTypeLocList = searchDaoObj.getLocations(locationBeanObj);
		
			// Setting in the devicePrezData bean--
			userPrezDataObj.setLocationTypeList(locationTypeList);
			userPrezDataObj.setLocationList(locationList);
			userPrezDataObj.setDefaultLocList(defaultTypeLocList);
			
		}
		catch (Exception ex)
		{
			logger.log(Level.ERROR, " UserManager :: populateAllDropDowns() :: Exception :: " + ex);
			throw ex;
		}
		return userPrezDataObj;
	} // End Of Method

    public UserParameterBean getUserParameter(UserParameterBean userParameterBean) throws Exception {
        LinkedList userParameterList = null;
        UserDao userDao = null;
        try{
            logger.log(Level.INFO, " UserManager :: populateAllDropDowns() :: method called");
            userDao = new OracleUserDao();
            userParameterList = userDao.getUserParameter(userParameterBean);
            userParameterBean.setUserParameterList(userParameterList);
            
        }
        catch (Exception ex)
        {
                logger.log(Level.ERROR, " UserManager :: populateAllDropDowns() :: Exception :: " + ex);
                throw ex;
        }
        return userParameterBean;

    }

    public UserParameterBean editUserparameter(UserParameterBean userParameterBean) throws Exception {
        LinkedList userParameterList = null;
        UserDao userDao = null;
        try{
            logger.log(Level.INFO, " UserManager :: editUserparameter() :: method called");
            userDao = new OracleUserDao();
            userParameterBean = userDao.getEditUserParameter(userParameterBean);

        }
        catch (Exception ex)
        {
                logger.log(Level.ERROR, " UserManager :: editUserparameter() :: Exception :: " + ex);
                throw ex;
        }
        return userParameterBean;
    }
} //-- End class
