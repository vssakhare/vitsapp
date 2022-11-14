/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority.dao;

//-- java imports
import java.util.LinkedList;
import java.util.HashMap;

//-- Logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import in.emp.dao.BaseDao;
import in.emp.dao.QueryHelper;

import in.emp.authority.bean.AuthorityBean;
import in.emp.authority.bean.AuthorityPrezData;
import in.emp.authority.dao.helper.GetAuthorityFormDataQueryHelper;

import in.emp.authority.dao.helper.GetAuthorityListQueryHelper;
import in.emp.authority.dao.helper.AuthorityFormTxnHelper;



/**
 *
 * @author Sachin
 */
public class OracleAuthorityDao extends BaseDao implements AuthorityDao {

    private static Logger logger = Logger.getLogger(OracleAuthorityDao.class);

    /**
     * API to get the search data for populating Drop downs
     *
     * @throws Exception	if an error occurs
     * @see	in.mda.device.DeviceDelegate
     * @returns	LinkedList
     */  
       
       public LinkedList getAuthorityList(AuthorityBean authorityBeanObj) throws Exception {
        LinkedList authorityList = null;
        try {
            logger.log(Level.INFO, "OracleAuthorityDao ::: getAuthorityList() :: method called ::");

            authorityList = (LinkedList) getObjectList(new GetAuthorityListQueryHelper(authorityBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleAuthorityDao ::: getAuthorityList() :: Exception :: " + ex);
            throw ex;
        }
        return authorityList;
    }
       
       
       public AuthorityBean getAuthorityFormData(AuthorityBean authorityBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OracleAuthorityDao ::: getAuthorityFormData() :: method called ::    ");
            authorityBeanObj = (AuthorityBean) getDataObject(new GetAuthorityFormDataQueryHelper(authorityBeanObj)); 
       // leaveapplFormBeanObj = (AuthorityBean) getDataObject(new GetAuthorityQueryHelper(leaveapplFormBeanObj));
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleAuthorityDao ::: getAuthorityFormData() :: Exception :: " + ex);
            throw ex;
        }
        return authorityBeanObj;
    }
       
    public AuthorityPrezData AuthorityFormTxnHelper(AuthorityPrezData authorityPrezDataObj) throws Exception { 
        try { 
            logger.log(Level.INFO, "OracleAuthorityDao ::: postAuthority() :: method called ::    ");
            if(authorityPrezDataObj.getAuthorityBean().getApplId() != "") {
            updateObject(new AuthorityFormTxnHelper(authorityPrezDataObj));
            }
            else{
                authorityPrezDataObj = (AuthorityPrezData) createObject(new AuthorityFormTxnHelper(authorityPrezDataObj));
            }

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleAuthorityDao ::: postAuthority() :: Exception :: " + ex);
            throw ex;
        }
        return authorityPrezDataObj;
    }
   
   
}
