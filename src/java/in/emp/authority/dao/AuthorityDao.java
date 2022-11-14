/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority.dao;

//-- java imports
import in.emp.authority.bean.AuthorityBean;
import in.emp.authority.bean.AuthorityBean;
import java.util.LinkedList;


import in.emp.authority.bean.AuthorityPrezData;

/**
 *
 * @author Prajakta
 */
public interface AuthorityDao {

    public LinkedList getAuthorityList(AuthorityBean authorityBeanObj) throws Exception;
       
    public AuthorityBean getAuthorityFormData(AuthorityBean authorityBeanObj) throws Exception;
    
    public AuthorityPrezData AuthorityFormTxnHelper(AuthorityPrezData authorityPrezDataObj) throws Exception;
    
}
