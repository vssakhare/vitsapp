/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.biometric.manager;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import in.emp.home.biometric.BiometricAttendDataDelegate;
import in.emp.home.biometric.bean.BiometricAttendDataReportBean;
import in.emp.home.biometric.dao.OracleBiometricAttendDataDao;

/**
 *
 * @author Prajakta
 */
public class BiometricAttendDataManager implements BiometricAttendDataDelegate {

    private static String CLASS_NAME = BiometricAttendDataManager.class.getName();
    private static Logger logger = Logger.getLogger(BiometricAttendDataManager.class);

    public BiometricAttendDataManager() {
    }

    public BiometricAttendDataReportBean getBiometricAttendDataReport(BiometricAttendDataReportBean biometricAttendDataReportBeanObj) throws Exception {
        OracleBiometricAttendDataDao biometricAttendDataReportDao = new OracleBiometricAttendDataDao();
        try {
            logger.log(Level.INFO, " BiometricAttendDataManager :: getBiometricAttendDataReport() :: method called");

            if (biometricAttendDataReportBeanObj.getBlock().getEmpNumber() != null) {
            biometricAttendDataReportBeanObj = biometricAttendDataReportDao.getBiometricAttendDataReport(biometricAttendDataReportBeanObj);
            }
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, " BiometricAttendDataManager :: getBiometricAttendDataReport() :: Exception :: " + ex);

        }
        return biometricAttendDataReportBeanObj;
    }
}
