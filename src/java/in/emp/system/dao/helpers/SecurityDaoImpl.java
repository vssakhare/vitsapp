/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao.helpers;
import in.emp.security.bean.SecUserBean;
import in.emp.security.bean.ServerAPIBean;
import in.emp.security.dao.SecurityDao;
import in.emp.system.dao.SecurityDTO;
import in.emp.user.bean.UserBean;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 *
 * @author admin
 */
public class SecurityDaoImpl extends BaseDaoSSO implements SecurityDao {
	private static Logger logger = Logger.getLogger(SecurityDaoImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see in.mahadiscom.ndm.security.dao.SecurityDao#getOfficeCodeBuList(long)
	 */
	public List getOfficeCodeBuList(SecurityDTO securityDTO) throws Exception {
		List officeCodeBuList = null;
		try {
			logger.log(Level.DEBUG,
					"SecurityDaoImpl :: getOfficeCodeBuList() :: ");
			officeCodeBuList = getObjectList(new GetOfficeCodeBuListQueryHelper(securityDTO));
		} catch (Exception ex) {
			logger.log(Level.ERROR,
					"SecurityDaoImpl :: getOfficeCodeBuList() :: Exception"
							+ ex);
			throw ex;
		}
		return officeCodeBuList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see in.mahadiscom.ndm.security.dao.SecurityDao#getOfficeLocBuList(long,
	 * long)
	 */
	public List getOfficeLocBuList(SecurityDTO securityDTO)
			throws Exception {


		List billingUnitList = null;
		try {
			logger.log(Level.DEBUG,
					"SecurityDaoImpl :: getOfficeLocBuList() :: ");
			billingUnitList = getObjectList(new GetOfficeLocBuListQueryHelper(securityDTO));
		} catch (Exception ex) {
			logger.log(Level.ERROR,
					"SecurityDaoImpl :: getOfficeLocBuList() :: Exception" + ex);
			throw ex;
		}
		return billingUnitList;
	}

        public List getOfficeCodeCircleList(SecurityDTO securityDTO) throws Exception {
		List circleList = null;
		try {
			logger.log(Level.DEBUG, "SecurityDaoImpl :: getOfficeCodeCircleList :: ");
			circleList = getObjectList(new GetOfficeCodeCircleListQueryHelper(securityDTO));
		} catch (Exception ex) {
			logger.log(Level.ERROR,
					"SecurityDaoImpl :: getOfficeCodeCircleList :: Exception" + ex);
			throw ex;
		}
		return circleList;
        }

        public List getOfficeLocCircleList(SecurityDTO securityDTO) throws Exception {

		List circleList = null;
		try {
			logger.log(Level.DEBUG, "SecurityDaoImpl :: getOfficeLocCircleList() :: ");
			circleList = getObjectList(new GetOfficeLocCircleListQueryHelper(securityDTO));
		} catch (Exception ex) {
			logger.log(Level.ERROR,
					"SecurityDaoImpl :: getOfficeLocCircleList() :: Exception" + ex);
			throw ex;
		}
		return circleList;

        }

    /*    public String getLocationID(HttpServletRequest request)throws Exception {

		List billingUnitList = null;
                String locID;
		try {
			logger.log(Level.DEBUG,"SecurityDaoImpl :: getLocationID() :: ");
			locID = getDataObject(new GetOfficeLocIDQueryHelper(request));
		} catch (Exception ex) {
			logger.log(Level.ERROR,"SecurityDaoImpl :: getLocationID() :: Exception" + ex);
			throw ex;
		}
		return locID;
	}*/
                
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

   public String getOfficeNameOther(SecurityDTO securityDTO) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public String getOfficeName(SecurityDTO securityDTO) throws Exception {
      throw new UnsupportedOperationException("Not supported yet.");
   }

    public String createCpfPortalSession(String cpfNo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
