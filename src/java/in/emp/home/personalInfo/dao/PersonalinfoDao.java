/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.dao;

import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.home.personalInfo.bean.PersonalinfoBean;

/**
 *
 * @author rushi
 */
public interface PersonalinfoDao {

    public PersonalinfoBean getPersonalInfo(PersonalinfoBean pinfoBeanObj) throws Exception;

    public EmergencyContactsBean getEmergencyContactInfo(EmergencyContactsBean emergencyContactsBeanObj) throws Exception;
}