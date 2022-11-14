/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.home.personalInfo.bean.PersonalinfoBean;
import in.emp.home.personalInfo.bean.PersonalinfoPrezData;
import in.emp.home.personalInfo.bean.PersonalinfoaddressBean;
import in.emp.home.personalInfo.bean.PersonalinfocvcBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author rushi
 */
public class GetEmergencyContactsQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetEmergencyContactsQueryHelper.class);
    private EmergencyContactsBean emergencyContactsBeanObj = new EmergencyContactsBean();
    private PersonalinfoPrezData personalinfoPrezDataObj = new PersonalinfoPrezData();

    public GetEmergencyContactsQueryHelper(EmergencyContactsBean emergencyContactsBeanObj) {
        this.emergencyContactsBeanObj = emergencyContactsBeanObj;
    }
    public GetEmergencyContactsQueryHelper(PersonalinfoPrezData personalinfoPrezDataObj) {
        this.personalinfoPrezDataObj = personalinfoPrezDataObj;
        this.emergencyContactsBeanObj = this.personalinfoPrezDataObj.getEmergencyContactsBean();
    }
    
    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        EmergencyContactsBean emergencyContactsBean = new EmergencyContactsBean();
        try {
            logger.log(Level.INFO, "GetEmergencyContactsQueryHelper ::: getDataObject() :: method called ::    ");

            emergencyContactsBean.setContact1FullName(results.getString("CONTACT1_FULL_NAME"));
            emergencyContactsBean.setContact1PhoneNum1(results.getString("CONTACT1_PHONE_NUM1"));
            emergencyContactsBean.setContact1PhoneNum2(results.getString("CONTACT1_PHONE_NUM2"));
            emergencyContactsBean.setContact2FullName(results.getString("CONTACT2_FULL_NAME"));
            emergencyContactsBean.setContact2PhoneNum1(results.getString("CONTACT2_PHONE_NUM1"));
            emergencyContactsBean.setContact2PhoneNum2(results.getString("CONTACT2_PHONE_NUM2"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetEmergencyContactsQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return emergencyContactsBean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetEmergencyContactsQueryHelper ::: getQueryResults() :: method called ::    ");

            sql.append(" SELECT ECD.CONTACT1_FULL_NAME, ECD.CONTACT1_PHONE_NUM1, ECD.CONTACT1_PHONE_NUM2, ");
            sql.append(" ECD.CONTACT2_FULL_NAME, ECD.CONTACT2_PHONE_NUM1, ECD.CONTACT2_PHONE_NUM2 ");
            sql.append(" FROM XXMIS_EMP_EMR_CONTACT_DTL ECD ");
            sql.append(" WHERE ECD.EMP_NUMBER = ? ");
            sql.append(" AND ECD.STATUS = 'A' ");

            logger.log(Level.INFO, "GetEmergencyContactsQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, emergencyContactsBeanObj.getEmpNumber());
            rs = statement.executeQuery();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetEmergencyContactsQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
}
