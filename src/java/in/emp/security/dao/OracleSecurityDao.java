package in.emp.security.dao;

//-- java imports 
import in.emp.user.bean.UserBean;
import java.util.LinkedList;

// MDA imports -- 
import in.emp.dao.BaseDao;
import in.emp.security.bean.SecUserBean;
import in.emp.security.bean.ServerAPIBean;
import in.emp.security.dao.helper.CpfPortalSessionTxnHelper;
import in.emp.security.dao.helper.GetUserDetailQueryHelper;
import in.emp.security.dao.helper.GetSelectedFunctionQueryHelper;
import in.emp.security.dao.helper.GetServerAPIDetailsQueryHelper;
import in.emp.security.dao.helper.GetUserAccessBuListQueryHelper;
import in.emp.security.dao.helper.GetUserAccessListQueryHelper;
import in.emp.security.dao.helper.GetUserAccessQueryHelper;
import in.emp.security.dao.helper.GetOTPQueryHelper;
import in.emp.security.dao.helper.OTPTxnHelper;
import in.emp.security.dao.helper.UpdatePasswordTxnHelper;
import in.emp.system.dao.SecurityDTO;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Data access object for returning compound related value objects from the
 * Oracle database.
 *
 * @author	MDA
 */
public class OracleSecurityDao extends BaseDao implements SecurityDao {
    //static Logger logger = new Logger(OracleSecurityDao.class.getName());

    private static Logger logger = Logger.getLogger(OracleSecurityDao.class);

   

    public Object getUserDetails(SecUserBean secUserBeanObj) throws Exception {
        SecUserBean secUserObj = null;
        try {
            logger.log(Level.INFO, "OracleSecurityDao :: getUserDetails() :: method called");

            //System.out.println("OracleDao :: getQueryResults :: calling get data objects");
            //System.out.println("OracleDao :: getQueryResults :: Bean objects Login id ::" + secUserBeanObj.getLoginId());
            //System.out.println("OracleDao :: getQueryResults :: Bean objects Login id ::" + secUserBeanObj.getPassword());
            secUserObj = (SecUserBean) getDataObject(new GetUserDetailQueryHelper(secUserBeanObj));
            //System.out.println("OracleDao :: getQueryResults :: returned fron get data object");

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSecurityDao :: getUserDetails() :: Exception :: " + ex);
            throw ex;
        }
        return secUserObj;

    }/* End of Method */

   
    
    public LinkedList getServerAPIDetails(ServerAPIBean serverAPIBeanObj) throws Exception {
        LinkedList serverAPIList = null;

        try {
            logger.log(Level.INFO, "OracleSecurityDao :: getServerAPIDetails() :: method called");

            //System.out.println("OracleDao :: getQueryResults :: calling get data objects");
            //System.out.println("OracleDao :: getQueryResults :: Bean objects Login id ::" + secUserBeanObj.getLoginId());
            //System.out.println("OracleDao :: getQueryResults :: Bean objects Login id ::" + secUserBeanObj.getPassword());

            ///serverAPIBeanObj = (ServerAPIBean)getDataObject(new GetServerAPIDetailsQueryHelper(serverAPIBeanObj));
            serverAPIList = (LinkedList) getObjectList(new GetServerAPIDetailsQueryHelper(serverAPIBeanObj));

            //System.out.println("OracleDao :: getQueryResults :: returned fron get data object");

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSecurityDao :: getServerAPIDetails() :: Exception :: " + ex);
            throw ex;
        }
        return serverAPIList;

    }/* End of Method */

    /*
     * API to get the Function List for LoggedIn User
     * @param secUserBeanObj		the SecUserBean
     * @throws Exception		if an error occurs 
     * @see				in.mda.security.SecurityDelegate
     * @returns			LinkedList 
     */

    public LinkedList getSelectedFunctionList(SecUserBean secUserBeanObj) throws Exception {
        LinkedList userFunctionList = null;
        try {
            logger.log(Level.INFO, "OracleSecurityDao :: getSelectedFunctionList() :: method called");
            userFunctionList = (LinkedList) getObjectList(new GetSelectedFunctionQueryHelper(secUserBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSecurityDao :: getSelectedFunctionList() :: Exception :: " + ex);
            throw ex;
        }
        return userFunctionList;

    }/* End of Method */


    public LinkedList getUserAccess(SecUserBean secUserObj) throws Exception {
        LinkedList userAccessLocationList = null;
        try {
            logger.log(Level.INFO, "OracleSecurityDao :: getUserAccess() :: method called");
            userAccessLocationList = (LinkedList) getObjectList(new GetUserAccessQueryHelper(secUserObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSecurityDao :: getUserAccess() :: Exception :: " + ex);
            throw ex;
        }
        return userAccessLocationList;
    }

    public LinkedList getBuAccessList(SecUserBean secUserObj) throws Exception {
        LinkedList buAccessList = null;

        try {
            logger.log(Level.INFO, "OracleSecurityDao :: getUserAccess() :: method called");
            buAccessList = (LinkedList) getDataObject(new GetUserAccessBuListQueryHelper(secUserObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSecurityDao :: getUserAccess() :: Exception :: " + ex);
            throw ex;
        }
        return buAccessList;

    }

    public void postPasswordChange(UserBean userBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleSecurityDao :: postPasswordChange() :: method called");
            updateObject(new UpdatePasswordTxnHelper(userBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSecurityDao :: postPasswordChange() :: Exception :: " + ex);
            throw ex;
        }
        ///return userBeanObj;
    }

    public LinkedList getUserAccessList(SecUserBean secUserObj) throws Exception {
        LinkedList userAccessLocationList = null;
        try {
            logger.log(Level.INFO, "OracleSecurityDao :: getUserAccess() :: method called");
            userAccessLocationList = (LinkedList) getObjectList(new GetUserAccessListQueryHelper(secUserObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSecurityDao :: getUserAccess() :: Exception :: " + ex);
            throw ex;
        }
        return userAccessLocationList;
    }

    public List getOfficeCodeBuList(SecurityDTO securityDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getOfficeLocBuList(SecurityDTO securityDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getOfficeCodeCircleList(SecurityDTO securityDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getOfficeLocCircleList(SecurityDTO securityDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getOfficeNameOther(SecurityDTO securityDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getOfficeName(SecurityDTO securityDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String createCpfPortalSession(String cpfNo) throws Exception {
        LinkedList userAccessLocationList = null;
        try {
            logger.log(Level.INFO, "OracleSecurityDao :: createCpfPortalSession() :: method called");
            createObject(new CpfPortalSessionTxnHelper(cpfNo));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleSecurityDao :: createCpfPortalSession() :: Exception :: " + ex);
            throw ex;
        }
        return "";
    }
}//end class