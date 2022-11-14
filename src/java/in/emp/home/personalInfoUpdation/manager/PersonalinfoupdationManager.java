/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfoUpdation.manager;

import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.master.dao.MasterDao;
import in.emp.home.personalInfoUpdation.PersonalinfoupdationDelegate;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationBean;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationPrezData;
import in.emp.home.personalInfoUpdation.dao.OraclePersonalinfoupdationDao;
import in.emp.home.personalInfoUpdation.dao.PersonalinfoupdationDao;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author prajakta
 */
public class PersonalinfoupdationManager implements PersonalinfoupdationDelegate {

    private static String CLASS_NAME = PersonalinfoupdationManager.class.getName();
    private static Logger logger = Logger.getLogger(PersonalinfoupdationManager.class);

    /**
     * public Constructor of class DCUManager
     *
     */
    public PersonalinfoupdationManager() {
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
    public PersonalinfoupdationBean getPiUpdationForm(PersonalinfoupdationBean pinfoupdationBeanObj) {
        PersonalinfoupdationDao pinfoupdationDaoObj = new OraclePersonalinfoupdationDao();
        PersonalinfoupdationBean pinfoupdationBean = new PersonalinfoupdationBean();
        try {
            logger.log(Level.INFO, " PersonalinfoupdationManager :: getPiUpdationForm() :: method called");

            pinfoupdationBean = pinfoupdationDaoObj.getPiUpdationForm(pinfoupdationBeanObj);
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, " PersonalinfoupdationManager :: getPiUpdationForm() :: Exception :: " + ex);
        }
        return pinfoupdationBean;
    }

    @Override
    public PersonalinfoupdationBean postPiUpdationForm(PersonalinfoupdationBean pinfoupdationBeanObj) throws Exception {
        PersonalinfoupdationDao personalinfoupdationDao = new OraclePersonalinfoupdationDao();
        try {
            logger.log(Level.INFO, " PersonalinfoupdationManager :: postPiUpdationForm() :: method called");

            pinfoupdationBeanObj = personalinfoupdationDao.postPiUpdationForm(pinfoupdationBeanObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " PersonalinfoupdationManager :: postPiUpdationForm() :: Exception :: " + ex);
        }
        return pinfoupdationBeanObj;
    }

    @Override
    public EmergencyContactsBean postUpdateEmergencyContacts(EmergencyContactsBean emergencyContactsBean) throws Exception {
        PersonalinfoupdationDao personalinfoupdationDao = new OraclePersonalinfoupdationDao();
        try {
            logger.log(Level.INFO, " PersonalinfoupdationManager :: postUpdateEmergencyContacts() :: method called");

            emergencyContactsBean = personalinfoupdationDao.postUpdateEmergencyContacts(emergencyContactsBean);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " PersonalinfoupdationManager :: postUpdateEmergencyContacts() :: Exception :: " + ex);
        }
        return emergencyContactsBean;
    }
}