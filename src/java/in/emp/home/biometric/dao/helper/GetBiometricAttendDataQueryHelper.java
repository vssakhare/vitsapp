/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.biometric.dao.helper;

import in.emp.home.biometric.bean.BiometricAttendADataBean;
import in.emp.home.biometric.bean.BiometricAttendDataBean;
import in.emp.home.biometric.bean.BiometricAttendDataReportBean;
import in.emp.dao.QueryHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import in.emp.common.ApplicationConstants;
import in.emp.util.ApplicationUtils;
import java.sql.Date;

/**
 *
 * @author Prajakta
 */
public class GetBiometricAttendDataQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetBiometricAttendDataQueryHelper.class);
    private BiometricAttendDataReportBean biometricAttendDataReportBeanObj;
    private BiometricAttendDataBean biometricAttendDataBeanObj;
    private BiometricAttendADataBean[] AData;

    public GetBiometricAttendDataQueryHelper(BiometricAttendDataReportBean biometricAttendDataReportBeanObj) {
        this.biometricAttendDataReportBeanObj = biometricAttendDataReportBeanObj;
        this.biometricAttendDataBeanObj = biometricAttendDataReportBeanObj.getBlock();
    }

    public Object getDataObject(ResultSet results) throws Exception {
        int dc = 28;
       // biometricAttendDataBeanObj = new BiometricAttendDataBean();
        try {
            dc = ApplicationUtils.getDayCount(biometricAttendDataReportBeanObj.getBlock().getYYYYMM());

            AData = new BiometricAttendADataBean[dc];

            for (int i = 0; (i < 31) && (i < dc); i++) {
               if (i > 0) {
                results.next();
                }
                if (i == 0) {
                    biometricAttendDataBeanObj.setEmpNumber(results.getString("EMP_NUMBER"));
                    biometricAttendDataBeanObj.setFullName(results.getString("FULL_NAME"));
                    biometricAttendDataBeanObj.setDesignation(results.getString("DESIGNATION"));
                    biometricAttendDataBeanObj.setCode(results.getInt("CODE"));
                    biometricAttendDataReportBeanObj.setPNFlag(results.getString("PN_FLAG"));
                    biometricAttendDataReportBeanObj.setHFlag(results.getString("H_FLAG"));
                    biometricAttendDataReportBeanObj.setSaveFlag(results.getString("SAVE_FLAG"));
                }
                AData[i] = new BiometricAttendADataBean();
                AData[i].setEmpNumber(results.getString("EMP_NUMBER"));
                AData[i].setDate(results.getDate("ATTENDANCE_DATE"));
                AData[i].setInTime(results.getString("IN_TIME"));
                AData[i].setOutTime(results.getString("OUT_TIME"));
                AData[i].setStatus(results.getString("STATUS"));
                AData[i].setRemark(results.getString("REMARK"));
                AData[i].setSaveFlag(results.getString("SAVE_FLAG"));

            }
            biometricAttendDataBeanObj.setAData(AData);
            biometricAttendDataReportBeanObj.setBlock(biometricAttendDataBeanObj);


        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetBiometricAttendDataQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return biometricAttendDataReportBeanObj;
    }

    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        String sumStatements = "";
        String maxStatements = "";
        String decodeStatements = "";
        try {

            sql.append(" SELECT EBA.EMP_NUMBER, EBA.FULL_NAME, EBA.DESIGNATION, ");
            sql.append(" EBA.CODE, EBA.ATTENDANCE_DATE, EBA.IN_TIME, EBA.OUT_TIME, ");
            sql.append(" EBA.STATUS, EBA.REMARK, EBA.PN_FLAG, EBA.H_FLAG, EBA.SAVE_FLAG ");
            sql.append(" FROM XXMIS_EMP_BIO_ATTEND_DATA EBA ");
            sql.append(" WHERE EBA.EMP_NUMBER = ? "); // Employee Number here
            sql.append(" AND EBA.YYYYMM = ? "); // YYYYMM here

            logger.log(Level.INFO, "GetBiometricAttendDataQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, biometricAttendDataReportBeanObj.getBlock().getEmpNumber());
            statement.setString(2, biometricAttendDataReportBeanObj.getBlock().getYYYYMM());


            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetBiometricAttendDataQueryHelper :: getQueryResults() :: Exception :: " + ex);
            //ex.printStackTrace();
            throw ex;
        }
        return rs;
    }
}
