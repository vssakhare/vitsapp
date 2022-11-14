/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo;

import in.emp.home.personalInfo.bean.PersonalinfoBean;
import in.emp.home.personalInfo.bean.PersonalinfoPrezData;

/**
 *
 * @author rushi
 */
public interface PersonalinfoDelegate {

    /**
     * Api to gets details of an existing DCU.
     *
     * @param DCUBeanObj	DCUBean
     * @returns	DCUPrezData
     * @throws	Exception
     * @see	in.mda.DCU.DCUDelegate
     */
    public PersonalinfoPrezData getPersonalInfo(PersonalinfoBean pinfoBeanObj) throws Exception;
}

