/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfoUpdation.dao;

import in.emp.dao.BaseDao;
import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.home.personalInfo.dao.helper.EmergencyContactsTxnHelper;
import in.emp.home.personalInfoUpdation.bean.PersonalinfoupdationBean;
import in.emp.home.personalInfoUpdation.dao.helper.GetPersonalinfoupdationQueryHelper;
import in.emp.home.personalInfoUpdation.dao.helper.postPiUpdationFormProcHelper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author prajakta
 */
public class OraclePersonalinfoupdationDao extends BaseDao implements PersonalinfoupdationDao {

    private static Logger logger = Logger.getLogger(OraclePersonalinfoupdationDao.class);

    /**
     * Api to gets details of an existing DCU.
     *
     * @param dcuBeanObj	DCUBean
     * @returns	DCUBean
     * @throws	Exception
     * @see	in.mda.dcu.DCUDelegate
     */
    @Override
    public PersonalinfoupdationBean getPiUpdationForm(PersonalinfoupdationBean pinfoupdationBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OraclePersonalinfoupdationDao ::: getPersonalInfo() :: method called ::    ");
            
            pinfoupdationBeanObj = (PersonalinfoupdationBean) getDataObject(new GetPersonalinfoupdationQueryHelper(pinfoupdationBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OraclePersonalinfoupdationDao ::: getPersonalInfo() :: Exception :: " + ex);
            throw ex;
        }
        return pinfoupdationBeanObj;
    }

    @Override
    public PersonalinfoupdationBean postPiUpdationForm(PersonalinfoupdationBean pinfoupdationBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, " OraclePersonalinfoupdationDao :: postPiUpdationForm() :: method called");
            
            pinfoupdationBeanObj = (PersonalinfoupdationBean) execProcedure(new postPiUpdationFormProcHelper(pinfoupdationBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, " OraclePersonalinfoupdationDao :: postPiUpdationForm() :: Exception :: " + ex);
        }
        return pinfoupdationBeanObj;
    }

    @Override
    public EmergencyContactsBean postUpdateEmergencyContacts(EmergencyContactsBean emergencyContactsBean) throws Exception {
        try {
            logger.log(Level.INFO, " OraclePersonalinfoupdationDao :: postUpdateEmergencyContacts() :: method called");
            
            updateObject(new EmergencyContactsTxnHelper(emergencyContactsBean));

        } catch (Exception ex) {
            logger.log(Level.ERROR, " OraclePersonalinfoupdationDao :: postUpdateEmergencyContacts() :: Exception :: " + ex);
        }
        return emergencyContactsBean;
    }
}