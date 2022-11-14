package in.emp.security;

//--Imports
import in.emp.security.bean.SecUserBean;
import in.emp.security.bean.ServerAPIBean;
import in.emp.system.dao.SecurityDTO;
import in.emp.user.bean.UserBean;

/**
 * Interface for SecurityManager
 *
 * @author MDA
 */
public interface SecurityDelegate {
    /*
     * API to get the user details for entered login information criteria
     * @param searchObject		the SecUserBean
     * @throws Exception		if an error occurs 
     * @returns			Object
     */

    public Object getUserDetails(SecUserBean secUserBeanObj) throws Exception;

    public Object getServerAPIDetails(ServerAPIBean serverAPIBeanObj) throws Exception;

    public UserBean postPasswordChange(UserBean userBeanObj) throws Exception;

    public String getOfficeNameOther(SecurityDTO securityDTO) throws Exception;

    public String getOfficeName(SecurityDTO securityDTO) throws Exception;
    
    public String createCpfPortalSession(String cpfNo) throws Exception;



}//-- End class
