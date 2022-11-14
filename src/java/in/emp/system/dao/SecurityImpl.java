/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.system.dao;
import in.emp.security.dao.SecurityDao;
import in.emp.system.SecurityInterface;
import in.emp.system.dao.helpers.SecurityDaoImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 *
 * @author admin
 */
public class SecurityImpl implements SecurityInterface {

	private static Logger logger = Logger.getLogger(SecurityImpl.class);
	private SecurityDao securityDao;

	/* (non-Javadoc)
	 * @see in.mahadiscom.ndm.security.SecurityInterface#getOfficeCodeBuList(long)
	 */
	public List getOfficeCodeBuList(SecurityDTO securityDTO) throws Exception {
		securityDao = new SecurityDaoImpl();
		List officeCodeBuList = null;
		try{
			logger.log(Level.DEBUG, "SecurityImpl :: getOfficeCodeBuList() :: ");
			officeCodeBuList = securityDao.getOfficeCodeBuList(securityDTO);

		}catch(Exception ex){
			logger.log(Level.ERROR, "SecurityImpl :: getOfficeCodeBuList() :: Exception" + ex);
			throw ex;
		}
		return officeCodeBuList;
	}

	/* (non-Javadoc)
	 * @see in.mahadiscom.ndm.security.SecurityInterface#getOfficeLocBuList(long, long)
	 */
	public List getOfficeLocBuList(SecurityDTO securityDTO)
			throws Exception {
		securityDao = new SecurityDaoImpl();
		List officeLocBuList = null;
		try{
			logger.log(Level.DEBUG, "SecurityImpl :: getOfficeLocBuList() :: ");
			officeLocBuList = securityDao.getOfficeLocBuList(securityDTO);

		}catch(Exception ex){
			logger.log(Level.ERROR, "SecurityImpl :: getOfficeLocBuList() :: Exception" + ex);
			throw ex;
		}
		return officeLocBuList;
	}

        public List getOfficeCodeCircleList(SecurityDTO securityDTO) throws Exception {

		securityDao = new SecurityDaoImpl();
		List officeCodeCircleList = null;
		try{
			logger.log(Level.DEBUG, "SecurityImpl :: getOfficeLocCircleList() :: ");
			officeCodeCircleList = securityDao.getOfficeCodeCircleList(securityDTO);

		}catch(Exception ex){
			logger.log(Level.ERROR, "SecurityImpl :: getOfficeLocCircleList() :: Exception" + ex);
			throw ex;
		}
		return officeCodeCircleList;

        }

        public List getOfficeLocCircleList(SecurityDTO securityDTO) throws Exception {

		securityDao = new SecurityDaoImpl();
		List officeLocCircleList = null;
		try{
			logger.log(Level.DEBUG, "SecurityImpl :: getOfficeLocCircleList() :: ");
			officeLocCircleList = securityDao.getOfficeLocCircleList(securityDTO);

		}catch(Exception ex){
			logger.log(Level.ERROR, "SecurityImpl :: getOfficeLocCircleList() :: Exception" + ex);
			throw ex;
		}
		return officeLocCircleList;
        }

        
           /*     public String getLocationID(HttpServletRequest request) throws Exception {

		securityDao = new SecurityDaoImpl();
		
                String locationID="";
		try{
			logger.log(Level.DEBUG, "SecurityImpl :: getLocationID() :: ");
			locationID = securityDao.getLocationID(request);

		}catch(Exception ex){
			logger.log(Level.ERROR, "SecurityImpl :: getLocationID() :: Exception" + ex);
			throw ex;
		}
		return locationID;

        }
         */
}
