/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority.manager;

import in.emp.master.dao.MasterDao;
import in.emp.authority.AuthorityDelegate;
import in.emp.authority.bean.AuthorityBean;
import in.emp.authority.bean.AuthorityBean;
import in.emp.authority.bean.AuthorityPrezData;
import in.emp.authority.dao.AuthorityDao;
import in.emp.authority.dao.OracleAuthorityDao;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class AuthorityManager implements AuthorityDelegate {

    private static String CLASS_NAME = AuthorityManager.class.getName();
    private static Logger logger = Logger.getLogger(AuthorityManager.class);

    public AuthorityManager() {
    }

   
     public AuthorityPrezData getAuthorityList(AuthorityBean authorityBeanObj) {
        AuthorityPrezData authorityPrezDataObj = new AuthorityPrezData();
        AuthorityDao authorityDaoObj = new OracleAuthorityDao();
        LinkedList authorityList = new LinkedList();
        try {
            logger.log(Level.INFO, " AuthorityManager :: getAuthorityList() :: method called");

            //authorityPrezDataObj.setAuthorityBean(authorityBeanObj);
            authorityList = (LinkedList) authorityDaoObj.getAuthorityList(authorityBeanObj);
            authorityPrezDataObj.setAuthorityList(authorityList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " AuthorityManager :: getAuthorityList() :: Exception :: " + ex);

        }
        return authorityPrezDataObj;
    }
    
    public AuthorityBean getAuthorityFormData(AuthorityBean authorityBeanObj) throws Exception {
        AuthorityDao authorityDaoObj = new OracleAuthorityDao();

        try {
            logger.log(Level.INFO, " AuthorityManager :: getAuthorityForm() :: method called");

          
                authorityBeanObj = authorityDaoObj.getAuthorityFormData(authorityBeanObj);
          
        }catch (Exception ex) {
            logger.log(Level.ERROR, " AuthorityManager :: getAuthorityForm() :: Exception :: " + ex);
        }
        return authorityBeanObj;
    }
    
    public AuthorityPrezData AuthorityFormTxnHelper(AuthorityPrezData authorityPrezDataObj) throws Exception {
        AuthorityDao authorityDaoObj = new OracleAuthorityDao();
        try {
            logger.log(Level.INFO, " AuthorityManager :: postComplaint() :: method called");
           
            authorityDaoObj.AuthorityFormTxnHelper(authorityPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " AuthorityManager :: postComplaint() :: Exception :: " + ex);
        }
        return authorityPrezDataObj;
    }
        
   
}