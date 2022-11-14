/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao;



//-- java imports
import in.emp.user.bean.UserBean;
import java.util.LinkedList;

// MDA imports --
import in.emp.dao.BaseDao;
import in.emp.security.bean.SecUserBean;
import in.emp.security.bean.ServerAPIBean;
import in.emp.security.dao.SecurityDao;
import in.emp.security.dao.helper.GetUserDetailQueryHelper;
import in.emp.security.dao.helper.GetSelectedFunctionQueryHelper;
import in.emp.security.dao.helper.GetServerAPIDetailsQueryHelper;
import in.emp.security.dao.helper.GetUserAccessBuListQueryHelper;
import in.emp.security.dao.helper.GetUserAccessListQueryHelper;
import in.emp.security.dao.helper.GetUserAccessQueryHelper;
import in.emp.security.dao.helper.UpdatePasswordTxnHelper;
import in.emp.system.dao.SecurityDTO;
import in.emp.system.dao.helpers.BaseDaoSSO;
import in.emp.system.dao.helpers.OfficeNameOtherQueryHelper;
import in.emp.system.dao.helpers.OfficeNameQueryHelper;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Data access object for returning compound related value objects from the Oracle database.
 *
 * @author	MDA
 */

public class OracleSecurityDaoSSO extends BaseDaoSSO implements SecurityDao
{
	//static Logger logger = new Logger(OracleSecurityDao.class.getName());
	private static Logger logger = Logger.getLogger(OracleSecurityDaoSSO.class);


	/*
	* API to get the Login results for given user name and password
	* @param secUserObject		the SecUserBean
	* @throws Exception		if an error occurs
	* @see				in.mda.security.SecurityDelegate
	* @returns			Object
	*/

       public String getOfficeNameOther(SecurityDTO securityDTO) throws Exception {

      return (String) getDataObject(new OfficeNameOtherQueryHelper(securityDTO));

   }

   public String getOfficeName(SecurityDTO securityDTO) throws Exception {

      return (String) getDataObject(new OfficeNameQueryHelper(securityDTO));

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

   public Object getUserDetails(SecUserBean SecUserObj) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public LinkedList getSelectedFunctionList(SecUserBean SecUserObj) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Object getUserAccess(SecUserBean secUserObj) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public LinkedList getServerAPIDetails(ServerAPIBean serverAPIBeanObj) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public void postPasswordChange(UserBean userBeanObj) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public Object getUserAccessList(SecUserBean secUserObj) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.");
   }


    public String createCpfPortalSession(String cpfNo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end class