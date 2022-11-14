/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.bean;

/**
 *
 * @author rushi
 */
public class PersonalinfoPrezData implements java.io.Serializable {

    private PersonalinfoBean pinfoBean = new PersonalinfoBean();
    private EmergencyContactsBean emergencyContactsBean = new EmergencyContactsBean();

    public EmergencyContactsBean getEmergencyContactsBean() {
        return emergencyContactsBean;
    }

    public void setEmergencyContactsBean(EmergencyContactsBean emergencyContactsBean) {
        this.emergencyContactsBean = emergencyContactsBean;
    }

    public PersonalinfoBean getPinfoBean() {
        return pinfoBean;
    }

    public void setPinfoBean(PersonalinfoBean pinfoBean) {
        this.pinfoBean = pinfoBean;
    }
}