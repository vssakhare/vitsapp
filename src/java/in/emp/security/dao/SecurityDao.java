package in.emp.security.dao;

//-- Java Imports
import in.emp.user.bean.UserBean;
import java.util.LinkedList;

//-- MDA imports
import in.emp.security.bean.SecUserBean;
import in.emp.security.bean.ServerAPIBean;
import in.emp.system.dao.SecurityDTO;
import java.util.List;

/**
 * Data access object interface for returning document related value objects
 * from the database.
 *
 * @author	MDA
 */
public interface SecurityDao {
    /*
     * API to get the search results for selected search criteria
     * @param searchObject		the SearchBean
     * @throws Exception		if an error occurs
     * @see				in.mda.search.SecurityDelegate
     * @returns			Object
     */

    public Object getUserDetails(SecUserBean SecUserObj) throws Exception;

    /*
     * API to get selected Function List
     * @param SecUserObj		the SecUserBean
     * @throws Exception		if an error occurs
     * @see				in.mda.search.SecurityDelegate
     * @returns			LinkedList
     */
   

    public LinkedList getSelectedFunctionList(SecUserBean SecUserObj) throws Exception;

    public Object getUserAccess(SecUserBean secUserObj) throws Exception;

    public LinkedList getServerAPIDetails(ServerAPIBean serverAPIBeanObj) throws Exception;

    public void postPasswordChange(UserBean userBeanObj) throws Exception;

    public List getOfficeCodeBuList(SecurityDTO securityDTO) throws Exception;

    public Object getUserAccessList(SecUserBean secUserObj) throws Exception;

    public List getOfficeLocBuList(SecurityDTO securityDTO) throws Exception;

    public List getOfficeCodeCircleList(SecurityDTO securityDTO) throws Exception;

    public List getOfficeLocCircleList(SecurityDTO securityDTO) throws Exception;

    public String getOfficeNameOther(SecurityDTO securityDTO) throws Exception;

    public String getOfficeName(SecurityDTO securityDTO) throws Exception;

    public String createCpfPortalSession(String cpfNo) throws Exception;
    //public String getLocationID(HttpServletRequest request) throws Exception;
    // public NetworkBean getNetworkList(NetworkBean networkBeanObj)throws Exception;

   
}//end class

