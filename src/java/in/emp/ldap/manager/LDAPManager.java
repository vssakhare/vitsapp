/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.ldap.manager;
import in.emp.ldap.LDAP;
import in.emp.ldap.LDAPDelegate;
import in.emp.ldap.bean.LDAPPrezData;
import in.emp.ldap.bean.LDAPRespBean;
import in.emp.ldap.dao.LDAPDao;
import java.util.Iterator;
import java.util.LinkedList;
import javaldapapp.AssignOfficeDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class LDAPManager implements LDAPDelegate {

    private static String CLASS_NAME = LDAPManager.class.getName();
    private static Logger logger = Logger.getLogger(LDAPManager.class);

    public LDAPPrezData LDAP(LinkedList Obj) throws Exception {
        LDAP l = new LDAP();
        Iterator i = Obj.iterator();
        AssignOfficeDTO assignOfficeDTO = new AssignOfficeDTO();
        LDAPPrezData LDAPPrezDataObj = new LDAPPrezData();
        String EmpNumber = "";
        LDAPRespBean LDAPRespBeanObj = new LDAPRespBean();
        LDAPDao LDAPDaoObj = new LDAPDao();
        try {
            logger.log(Level.INFO, "LDAPManager :: LDAP() :: method called");
            assignOfficeDTO = l.getLogin((String) i.next(), (String) i.next());
            if (assignOfficeDTO == null) {
            assignOfficeDTO = new AssignOfficeDTO();
            }
            EmpNumber = String.valueOf(Integer.parseInt(assignOfficeDTO.getOfficerCpfNo()));
            LDAPRespBeanObj.setEmpNumber(EmpNumber);

            LDAPPrezDataObj.setAssignOfficeDTO(assignOfficeDTO);
            LDAPPrezDataObj.setLDAPRespBean(LDAPDaoObj.getHRMSResp(LDAPRespBeanObj));

            logger.log(Level.INFO, "LDAPManager :: LDAP() :: assignOfficeDTO " + assignOfficeDTO.getOfficerCpfNo());
            
            return LDAPPrezDataObj;
        } catch (Exception ex) {
            logger.log(Level.ERROR, "LDAPManager :: LDAP() :: Exception :: " + ex);
            throw ex;
        }
    }
}
