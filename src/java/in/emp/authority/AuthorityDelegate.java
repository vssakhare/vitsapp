/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.authority;


import in.emp.authority.bean.AuthorityPrezData;
import in.emp.authority.bean.AuthorityBean;

/**
 *
 * @author Prajakta
 */
public interface AuthorityDelegate {

   
    public AuthorityPrezData getAuthorityList(AuthorityBean authorityBeanObj) throws Exception;
    
    public AuthorityBean getAuthorityFormData(AuthorityBean authorityBeanObj) throws Exception;
    
    public AuthorityPrezData AuthorityFormTxnHelper(AuthorityPrezData authorityPrezDataObj) throws Exception;
    
    
   
}
