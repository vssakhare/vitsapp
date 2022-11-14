/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.ldap.dao;

import in.emp.ldap.bean.LDAPRespBean;
import in.emp.ldap.dao.helper.GetLDAPRespQueryHelper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import in.emp.dao.BaseDao;

/**
 *
 * @author Prajakta
 */
public class LDAPDao extends BaseDao {

    private static Logger logger = Logger.getLogger(LDAPDao.class);

    public LDAPRespBean getHRMSResp(LDAPRespBean LDAPRespBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "LDAPDao ::: getHRMSResp() :: method called ::    ");

            LDAPRespBeanObj = (LDAPRespBean) getDataObject(new GetLDAPRespQueryHelper(LDAPRespBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "LDAPDao ::: getHRMSResp() :: Exception :: " + ex);
            throw ex;
        }
        return LDAPRespBeanObj;
    }

}
