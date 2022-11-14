/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.dao;

import in.emp.dao.BaseDao;
import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.home.personalInfo.bean.PersonalinfoBean;
import in.emp.home.personalInfo.dao.helper.GetEmergencyContactsQueryHelper;
import in.emp.home.personalInfo.dao.helper.GetEmpImageQueryHelper;
import in.emp.home.personalInfo.dao.helper.GetPersonalinfoQueryHelper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author rushi
 */
public class OraclePersonalinfoDao extends BaseDao implements PersonalinfoDao {

    private static Logger logger = Logger.getLogger(OraclePersonalinfoDao.class);

    @Override
    public PersonalinfoBean getPersonalInfo(PersonalinfoBean pinfoBeanObj) throws Exception {
            PersonalinfoBean pinfoBean = new PersonalinfoBean();
        try {
            logger.log(Level.INFO, "OraclePersonalinfoDao ::: getPersonalInfo() :: method called ::    ");//+ pinfoBeanObj.getDCUId());
            pinfoBeanObj = (PersonalinfoBean) getDataObject(new GetPersonalinfoQueryHelper(pinfoBeanObj));
            pinfoBean = (PersonalinfoBean) getDataObject(new GetEmpImageQueryHelper(pinfoBeanObj));

            if ((pinfoBean != null) && (pinfoBean.getEmpImg() != null)) {
                pinfoBeanObj.setEmpImg(pinfoBean.getEmpImg());
            }
        } catch (Exception ex) {
            logger.log(Level.ERROR, "OraclePersonalinfoDao ::: getPersonalInfo() :: Exception :: " + ex);
            throw ex;
        }
        return pinfoBeanObj;
    }

    @Override
    public EmergencyContactsBean getEmergencyContactInfo(EmergencyContactsBean emergencyContactsBeanObj) throws Exception {
        try {
            logger.log(Level.INFO, "OraclePersonalinfoDao ::: getEmergencyContactInfo() :: method called ::    ");

            emergencyContactsBeanObj = (EmergencyContactsBean) getDataObject(new GetEmergencyContactsQueryHelper(emergencyContactsBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OraclePersonalinfoDao ::: getEmergencyContactInfo() :: Exception :: " + ex);
            throw ex;
        }
        return emergencyContactsBeanObj;
    }
}