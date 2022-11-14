/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfoUpdation;

import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationBean;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationPrezData;

/**
 *
 * @author prajakta
 */
public interface PersonalinfoupdationDelegate {

    /**
     * Api to gets details of an existing DCU.
     *
     * @param DCUBeanObj	DCUBean
     * @returns	DCUPrezData
     * @throws	Exception
     * @see	in.mda.DCU.DCUDelegate
     */
    public PersonalinfoupdationBean getPiUpdationForm(PersonalinfoupdationBean pinfoupdationBeanObj) throws Exception;

    public PersonalinfoupdationBean postPiUpdationForm(PersonalinfoupdationBean pinfoupdationBeanObj) throws Exception;

    public EmergencyContactsBean postUpdateEmergencyContacts(EmergencyContactsBean emergencyContactsBean) throws Exception;
}