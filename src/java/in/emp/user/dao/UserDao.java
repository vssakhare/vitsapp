package in.emp.user.dao;

//-- java imports 
import in.emp.user.bean.UserBean;
import in.emp.user.bean.UserParameterBean;
import in.emp.user.bean.UserPrezData;
import java.util.LinkedList;
import java.util.HashMap;


/**
 * Data access object interface for returning User related value objects from the database.
 *
 * @author	
 */

public interface UserDao
{
	/*
	* API to get the search data for populating Drop downs
	* @throws Exception		if an error occurs 
	* @see						in.mda.user.UserDelegate
	* @returns					LinkedList
	*/
	public LinkedList getUserList()throws Exception;
	
	/*
	* API to get the search results for selected search criteria
	* @param userBeanObj	UserBean
	* @throws Exception					if an error occurs 
	* @see									in.mda.user.UserDelegate
	* @returns								LinkedList
	*/
	public LinkedList getUserSearchResults(UserBean userBeanObj)throws Exception;
	
	/*
	* API to get the Total Number of user records 
	* @param userBeanObj		UserBean
	* @throws Exception			if an error occurs 
	* @see							in.mda.user.UserDelegate
	* @returns						UserBean
	*/
	public UserBean getUserTotalRecords(UserBean userBeanObj) throws Exception;

	/** 
	* Api to update User status
	* @param userBeanObj			the UserBean
	* @throws Exception				if an error occurs 
	* @see								in.mda.user.UserDelegate
	*/
	public void updateUserStatus(HashMap attributeMap,UserBean userBeanObj)throws Exception;

	/**
	 * Creates a newUser.
	 * @param userPrezData		UserPrezData 
	 * @returns userPrezData		UserPrezData
	 * @throws Exception			if an error occurs
	 */	
	public UserPrezData createUser(UserPrezData userPrezData) throws Exception;

	/**
	 * Updates an existing user.
	 * @param userPrezData			UserPrezData 
	 * @throws Exception			if an error occurs
	 */	
	public void updateUser(UserPrezData userPrezData) throws Exception;
	
	/**
	 * Public API to get all Selected roles for user
	 *
	 * @param userBeanObj	UserBean
	 * @return						LinkedList
	 * @throws Exception		if an error occurs
 	 */
	public LinkedList getAllSelectedAreas(UserBean userBeanObj) throws Exception;
	/**
	 * Public API to get all employees ID
	 *
	 * @param userBeanObj	UserBean
	 * @return						LinkedList
	 * @throws Exception		if an error occurs
 	 */
	public LinkedList getAllEmployees(UserBean userBeanObj) throws Exception;

	/**
	 * Public API to get Selected user details
	 *
	 * @param roleBeanObj		RoleBean
	 * @return						RoleBean
	 * @throws Exception		if an error occurs
	 */
	public UserBean getUserDetailsById(UserBean userBeanObj) throws Exception;

    public LinkedList getUserParameter(UserParameterBean userParameterBean) throws Exception;

    public UserParameterBean getEditUserParameter(UserParameterBean userParameterBean) throws Exception;

}//end class