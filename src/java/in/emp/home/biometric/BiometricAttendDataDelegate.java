/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.biometric;

import in.emp.home.biometric.bean.BiometricAttendDataReportBean;

/**
 *
 * @author Prajakta
 */
public interface BiometricAttendDataDelegate {

    public BiometricAttendDataReportBean getBiometricAttendDataReport(BiometricAttendDataReportBean biometricAttendDataReportBeanObj) throws Exception;
}
