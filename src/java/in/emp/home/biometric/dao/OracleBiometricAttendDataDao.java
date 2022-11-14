/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.emp.home.biometric.dao;

//-- java imports
import java.util.LinkedList;
import java.util.HashMap;

//-- Logger Imports
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import in.emp.dao.BaseDao;
import in.emp.home.biometric.bean.BiometricAttendDataReportBean;
import in.emp.home.biometric.dao.helper.GetBiometricAttendDataQueryHelper;
/**
 *
 * @author Prajakta
 */
public class OracleBiometricAttendDataDao extends BaseDao implements BiometricAttendDataDao{

    private static Logger logger = Logger.getLogger(OracleBiometricAttendDataDao.class);

    public BiometricAttendDataReportBean getBiometricAttendDataReport(BiometricAttendDataReportBean biometricAttendDataReportBeanObj) throws Exception {
        LinkedList FileList = null;
        try {
            logger.log(Level.INFO, "OracleBiometricAttendDataDao ::: getTAclaimsFile() :: method called ::    ");
            biometricAttendDataReportBeanObj = (BiometricAttendDataReportBean) getDataObject(new GetBiometricAttendDataQueryHelper(biometricAttendDataReportBeanObj));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OracleBiometricAttendDataDao ::: getTAclaimsFile() :: Exception :: " + ex);
            throw ex;
        }
        return biometricAttendDataReportBeanObj;
    }

}
