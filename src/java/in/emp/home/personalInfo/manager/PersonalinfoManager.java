/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.manager;

import in.emp.master.dao.MasterDao;
import in.emp.home.personalInfo.PersonalinfoDelegate;
import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.home.personalInfo.bean.PersonalinfoBean;
import in.emp.home.personalInfo.bean.PersonalinfoPrezData;
import in.emp.home.personalInfo.dao.OraclePersonalinfoDao;
import in.emp.home.personalInfo.dao.PersonalinfoDao;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author rushi
 */
public class PersonalinfoManager implements PersonalinfoDelegate {

    private static String CLASS_NAME = PersonalinfoManager.class.getName();
    
    private static Logger logger = Logger.getLogger(PersonalinfoManager.class);

    /**
     * public Constructor of class DCUManager
     *
     */
    public PersonalinfoManager() {
    }

    /**
     * Api to gets details of an existing DCU.
     *
     * @param dcuBeanObj	DCUBean
     * @returns	DCUPrezData
     * @throws	Exception
     * @see	in.mda.dcu.DCUDelegate
     */
    @Override
    public PersonalinfoPrezData getPersonalInfo(PersonalinfoBean pinfoBeanObj) {
        PersonalinfoPrezData pinfoPrezDataObj = new PersonalinfoPrezData();
        PersonalinfoDao pinfoDaoObj = new OraclePersonalinfoDao();
        PersonalinfoBean pinfoBean = new PersonalinfoBean();
        EmergencyContactsBean emergencyContactsBeanObj = new EmergencyContactsBean();
        try {
            logger.log(Level.INFO, " PersonalinfoManager :: getPersonalInfo() :: method called");

            pinfoBean = pinfoDaoObj.getPersonalInfo(pinfoBeanObj);
            emergencyContactsBeanObj.setEmpNumber(pinfoBeanObj.getEmpNumber());
            emergencyContactsBeanObj.setLocation(pinfoBeanObj.getLocation());
            
            pinfoBean = pinfoDaoObj.getPersonalInfo(pinfoBeanObj);
            emergencyContactsBeanObj = pinfoDaoObj.getEmergencyContactInfo(emergencyContactsBeanObj);
                    
            pinfoPrezDataObj.setPinfoBean(pinfoBean);
            pinfoPrezDataObj.setEmergencyContactsBean(emergencyContactsBeanObj);
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, " PersonalinfoManager :: getPersonalInfo() :: Exception :: " + ex);

        }
        return pinfoPrezDataObj;
    }

} //-- End class

