/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.ldap;

import in.emp.ldap.bean.LDAPPrezData;
import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public interface LDAPDelegate {

    public LDAPPrezData LDAP(LinkedList Obj) throws Exception;
}
