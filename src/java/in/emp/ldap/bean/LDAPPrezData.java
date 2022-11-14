/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.ldap.bean;

import javaldapapp.AssignOfficeDTO;

/**
 *
 * @author Prajakta
 */
public class LDAPPrezData {

    AssignOfficeDTO AssignOfficeDTO = new AssignOfficeDTO();
    LDAPRespBean LDAPRespBean = new LDAPRespBean();

    public AssignOfficeDTO getAssignOfficeDTO() {
        return AssignOfficeDTO;
    }

    public void setAssignOfficeDTO(AssignOfficeDTO AssignOfficeDTO) {
        this.AssignOfficeDTO = AssignOfficeDTO;
    }

    public LDAPRespBean getLDAPRespBean() {
        return LDAPRespBean;
    }

    public void setLDAPRespBean(LDAPRespBean LDAPRespBean) {
        this.LDAPRespBean = LDAPRespBean;
    }
}
