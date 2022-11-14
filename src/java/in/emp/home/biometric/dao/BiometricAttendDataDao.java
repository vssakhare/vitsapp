/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.biometric.dao;

import in.emp.home.biometric.bean.BiometricAttendDataReportBean;

/**
 *
 * @author Prajakta
 */
public interface BiometricAttendDataDao {

    public BiometricAttendDataReportBean getBiometricAttendDataReport(BiometricAttendDataReportBean biometricAttendDataReportBeanObj) throws Exception;
}
