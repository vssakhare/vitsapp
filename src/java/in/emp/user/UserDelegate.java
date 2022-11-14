package in.emp.user;

//-- Java Imports
import in.emp.user.bean.UserParameterBean;
import in.emp.user.bean.UserPrezData;
import in.emp.user.bean.UserBean;

import java.util.LinkedList;
/**
 * Interface for UserDelegate
 *
 * @author   MDA
 */
public interface UserDelegate 
{
	/*
	* API to get the search data for populating Drop downs
	* @throws Exception		if an error occurs 
	* @see						in.mda.user.UserDelegate
	* @returns					UserPrezData
	*/
	public UserPrezData getUserSearchData()throws Exception;
	
	/*
	* API to get the search results for selected search criteria
	* @param userPrezDataObj			the UserPrezData
	* @throws Exception						if an error occurs 
	* @see										in.mda.user.UserDelegate
	* @returns									UserPrezData
	*/
	public UserPrezData getUserSearchResults(UserPrezData userPrezDataObj)throws Exception;

	/** 
	* Api to update device type status
	* @param userBeanObj			UserBean
	* @throws Exception				if an error occurs 
	* @see								in.mda.user.UserDelegate
	*/
	public void updateUserStatus(UserBean userBeanObj)throws Exception;

	/** 
	 * api to gets details of an existing user.
	 * @param userBeanObj				UserBean
	 * @returns									UserPrezData
	 * @throws Exception					if an error occurs 
	 * @see										in.mda.user.UserDelegate
	 */
	public UserPrezData getUserDetails(UserBean userBeanObj)throws Exception;

	/**
	 *  API to Create or update the user.
	 * @param userPrezData				UserPrezData
	 * @returns									UserPrezData
	 * @throws Exception					if an error occurs 
	 * @see										in.mda.user.UserDelegate
	 */
	public UserPrezData createUpdateUser(UserPrezData userPrezData) throws Exception;

        /**
	 *  API to fetch user parameter.
	 * @param 				
	 * @returns	UserParameterBean
	 * @throws Exception   if an error occurs
	 * @see	 in.mda.user.UserDelegate
	 */
        public UserParameterBean getUserParameter(UserParameterBean userParameterBean) throws Exception;

    public UserParameterBean editUserparameter(UserParameterBean userParameterBean) throws Exception;
}//-- End class