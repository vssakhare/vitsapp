package in.emp.user.dao;

//-- java imports 
import in.emp.user.bean.UserParameterBean;
import java.util.LinkedList;
import java.util.HashMap;

//--  Imports
import in.emp.dao.BaseDao;
import in.emp.user.bean.UserBean;
import in.emp.user.bean.UserPrezData;
import in.emp.user.dao.helper.GetUserSearchResultListQueryHelper;
import in.emp.user.dao.helper.GetTotalUserQueryHelper;
import in.emp.user.dao.helper.UserTxnHelper;
import in.emp.user.dao.helper.GetAllEmployeesQueryHelper;
import in.emp.user.dao.helper.GetUserDetailsByIdQueryHelper;
import in.emp.user.dao.helper.GetAllSelectedAreasQueryHelper;
import in.emp.user.dao.helper.GetUserParameterQueryHelper;
import in.emp.user.dao.helper.UserParameterTxnHelper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Data access object for returning user related value objects from the Oracle
 * database.
 *
 * @author
 */
public class OracleUserDao extends BaseDao implements UserDao {

    //static Logger logger = new Logger(OracleUserDao.class.getName());
    private static Logger logger = Logger.getLogger(OracleUserDao.class);

    /*
     * API to get the search data for populating Drop downs
     * @throws Exception		if an error occurs 
     * @see						in.mda.user.UserDelegate
     * @returns					LinkedList
     */
    public LinkedList getUserList() throws Exception {
        LinkedList userList = null;
        try {
            logger.log(Level.INFO, "OracleUserDao ::: getUserList() :: method called");
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: getUserList() :: Exception :: " + ex);
            throw ex;
        }
        return userList;
    }

    /*
     * API to get the search results for selected search criteria
     * @param searchObject	UserBean
     * @throws Exception			if an error occurs 
     * @see							in.mda.device.DeviceDelegate
     * @returns						LinkedList
     */
    public LinkedList getUserSearchResults(UserBean userBeanObj) throws Exception {
        LinkedList userSearchResultList = null;
        try {
            logger.log(Level.INFO, "OracleUserDao ::: getUserSearchResults() :: method called");

            userSearchResultList = (LinkedList) getObjectList(new GetUserSearchResultListQueryHelper(userBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: getUserSearchResults() :: Exception :: " + ex);
            throw ex;
        }
        return userSearchResultList;
    }

    /*
     * API to get the Total Number of Device Type records 
     * @param userBean		UserBean
     * @throws Exception					if an error occurs 
     * @see									in.mda.user.UserDelegate
     * @returns								UserBean
     */
    public UserBean getUserTotalRecords(UserBean userBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleUserDao ::: getUserTotalRecords() :: method called");
            userBeanObj = (UserBean) getDataObject(new GetTotalUserQueryHelper(userBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: getUserTotalRecords() :: Exception :: " + ex);
            throw ex;
        }
        return userBeanObj;
    }

    /**
     * Api to update device type status
     *
     * @param userBeanObj	the UserBean
     * @throws Exception	if an error occurs
     * @see	in.mda.user.UserDelegate
     */
    public void updateUserStatus(HashMap attributeMap, UserBean userBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleUserDao ::: updateUserStatus() :: method called");
            updateObjectAttribute(new UserTxnHelper(userBeanObj), attributeMap);
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: updateUserStatus() :: Exception :: " + ex);
            throw ex;
        }

    }//updateUserStatus ends

    /**
     * Creates a newUser.
     *
     * @param userPrezData	UserPrezData
     * @returns userPrezData	UserPrezData
     * @throws Exception	if an error occurs
     */
    public UserPrezData createUser(UserPrezData userPrezData) throws Exception {
        try {
            logger.log(Level.INFO, "OracleUserDao ::: createUser() :: method called");
            userPrezData = (UserPrezData) createObject(new UserTxnHelper(userPrezData));
            logger.log(Level.INFO, "OracleUserDao ::: createUser() :: SUCCESS");
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: createUser() :: Exception :: " + ex);
            throw ex;
        }
        return userPrezData;
    }

    /**
     * Updates an existing user.
     *
     * @param userPrezData	UserPrezData
     * @throws Exception	if an error occurs
     */
    public void updateUser(UserPrezData userPrezData) throws Exception {
        try {
            logger.log(Level.INFO, "OracleUserDao ::: updateUser() :: method called");
            updateObject(new UserTxnHelper(userPrezData));
            logger.log(Level.INFO, "OracleUserDao ::: updateUser() :: SUCCESS");
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: updateUser() :: Exception :: " + ex);
            throw ex;
        }
    }

    /**
     * Api to get all the roles
     *
     * @param userBeanObj	UserBean
     * @Returns	LinkedList
     * @throws	Exception
     */
    public LinkedList getAllSelectedAreas(UserBean userBeanObj) throws Exception {
        LinkedList allSelectedAreas = null;
        try {
            logger.log(Level.INFO, "OracleUserDao ::: getAllSelectedAreas() :: method called");
            allSelectedAreas = (LinkedList) getObjectList(new GetAllSelectedAreasQueryHelper(userBeanObj));
            logger.log(Level.INFO, "OracleUserDao ::: getAllSelectedAreas() :: allSelectedAreas size == " + allSelectedAreas.size());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: getAllSelectedAreas() :: Exception :: " + ex);
            throw ex;
        }
        return allSelectedAreas;
    }

    /**
     * Public API to get all employees ID
     *
     * @param userBeanObj	UserBean
     * @return	LinkedList
     * @throws Exception	if an error occurs
     */
    public LinkedList getAllEmployees(UserBean userBeanObj) throws Exception {
        LinkedList allEmployees = null;
        try {
            logger.log(Level.INFO, "OracleUserDao ::: getAllEmployees() :: method called");
            allEmployees = (LinkedList) getObjectList(new GetAllEmployeesQueryHelper(userBeanObj));
            logger.log(Level.INFO, "OracleUserDao ::: getAllEmployees() :: allEmployees size == " + allEmployees.size());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: getAllEmployees() :: Exception :: " + ex);
            throw ex;
        }
        return allEmployees;
    }

    /**
     * Public API to get Selected user details
     *
     * @param userBeanObj	UserBean
     * @return	UserBean
     * @throws Exception	if an error occurs
     */
    public UserBean getUserDetailsById(UserBean userBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleUserDao ::: getUserDetailsById() :: method called");
            userBeanObj = (UserBean) getDataObject(new GetUserDetailsByIdQueryHelper(userBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: getUserDetailsById() :: Exception :: " + ex);
            throw ex;
        }
        return userBeanObj;
    }

    public LinkedList getUserParameter(UserParameterBean userParameterBean) throws Exception {
        LinkedList userParameterList = null;
        try {
            logger.log(Level.INFO, "OracleUserDao ::: getUserDetailsById() :: method called");
            userParameterList = (LinkedList) getObjectList(new GetUserParameterQueryHelper());
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: getUserDetailsById() :: Exception :: " + ex);
            throw ex;
        }
        return userParameterList;

    }

    public UserParameterBean getEditUserParameter(UserParameterBean userParameterBean) throws Exception {

        try {
            logger.log(Level.INFO, "OracleUserDao ::: getEditUserParameter() :: method called");
            updateObject(new UserParameterTxnHelper(userParameterBean));
            logger.log(Level.INFO, "OracleUserDao ::: getEditUserParameter() :: SUCCESS");
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleUserDao ::: getEditUserParameter() :: Exception :: " + ex);
            throw ex;
        }
        return userParameterBean;
    }


}//end class
