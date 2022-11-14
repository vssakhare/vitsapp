/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.personalInfo.dao.helper;

import in.emp.dao.TxnHelper;
import in.emp.home.personalInfo.bean.EmergencyContactsBean;
import in.emp.home.personalInfo.bean.PersonalinfoPrezData;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class EmergencyContactsTxnHelper implements TxnHelper {
    
    private static Logger logger = Logger.getLogger(EmergencyContactsTxnHelper.class);
    private EmergencyContactsBean emergencyContactsBeanObj = new EmergencyContactsBean();
    private PersonalinfoPrezData personalinfoPrezDataObj = new PersonalinfoPrezData();

    public EmergencyContactsTxnHelper(EmergencyContactsBean emergencyContactsBean) {
        this.emergencyContactsBeanObj = emergencyContactsBean;
    }

    public EmergencyContactsTxnHelper(PersonalinfoPrezData personalinfoPrezData) {
        this.personalinfoPrezDataObj = personalinfoPrezData;
        this.emergencyContactsBeanObj = this.personalinfoPrezDataObj.getEmergencyContactsBean();
    }
    
    @Override
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "EmergencyContactsTxnHelper ::: createObject() :: method called ::");
            
            emergencyContactsBeanObj.setId(ApplicationUtils.getNextSequenceId(conn, "XXMIS_EMP_EMR_CONTACT_DTL_SEQ").toString());

            sql.append(" INSERT INTO XXMIS_EMP_EMR_CONTACT_DTL ");
            sql.append(" ( ID, PERSON_ID, EMP_NUMBER, FROM_DATE, TO_DATE, "); // 5 here
            sql.append(" CONTACT1_FULL_NAME, CONTACT1_PHONE_NUM1, CONTACT1_PHONE_NUM2, "); // 8 till here
            sql.append(" CONTACT2_FULL_NAME, CONTACT2_PHONE_NUM1, CONTACT2_PHONE_NUM2, "); // 11 till here
            sql.append(" USER_ID_CREATED, CREATED_SITE_ID, CREATED_TIME_STAMP, "); // 14 till here
            sql.append(" USER_ID_MODIFIED, MODIFIED_SITE_ID, MODIFIED_TIME_STAMP, STATUS ) "); // 18 till here
            sql.append(" VALUES ");
            sql.append(" ( ?, GET_PERSON_ID( ? ), ?, SYSDATE, NULL, "); // 3 + 2 till here
            sql.append(" ?, ?, ?, ?, ?, ?, "); // 9 + 2 till here
            sql.append(" ?, ?, SYSTIMESTAMP, NULL, NULL, NULL, 'A' ) "); // 11 + 7 till here

            statement = conn.prepareStatement(sql.toString());
            
            statement.setString(1, emergencyContactsBeanObj.getId());
            statement.setString(2, emergencyContactsBeanObj.getEmpNumber());
            statement.setString(3, emergencyContactsBeanObj.getEmpNumber());
            statement.setString(4, emergencyContactsBeanObj.getContact1FullName());
            statement.setString(5, emergencyContactsBeanObj.getContact1PhoneNum1());
            statement.setString(6, emergencyContactsBeanObj.getContact1PhoneNum2());
            statement.setString(7, emergencyContactsBeanObj.getContact2FullName());
            statement.setString(8, emergencyContactsBeanObj.getContact2PhoneNum1());
            statement.setString(9, emergencyContactsBeanObj.getContact2PhoneNum2());
            statement.setString(10, emergencyContactsBeanObj.getEmpNumber());
            statement.setString(11, emergencyContactsBeanObj.getLocation());

            logger.log(Level.INFO, "EmergencyContactsTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "EmergencyContactsTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
        return emergencyContactsBeanObj;
    }

    @Override
    public void updateObject(Connection conn) throws Exception {
        try {
            logger.log(Level.INFO, "EmergencyContactsTxnHelper ::: updateObject() :: method called ::");
            
            deleteObject(conn);
            
            if (!((ApplicationUtils.isBlank(emergencyContactsBeanObj.getContact1FullName()))
                    && (ApplicationUtils.isBlank(emergencyContactsBeanObj.getContact1FullName())))) {
                createObject(conn);
            }
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "EmergencyContactsTxnHelper ::: updateObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    @Override
    public void deleteObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        String sf = "10";
        try {
            logger.log(Level.INFO, "EmergencyContactsTxnHelper ::: deleteObject() :: method called ::");

            sql.append(" UPDATE XXMIS_EMP_EMR_CONTACT_DTL ");
            sql.append(" SET TO_DATE = SYSDATE, STATUS = 'D', "); // 2 here
            sql.append(" USER_ID_MODIFIED = ?, MODIFIED_SITE_ID = ?, MODIFIED_TIME_STAMP = SYSTIMESTAMP "); // 2 + 3 till here
            sql.append(" WHERE PERSON_ID = GET_PERSON_ID ( ? ) "); // 3 + 3 till here
            sql.append(" AND EMP_NUMBER = ? "); // 4 + 3 till here
            sql.append(" AND STATUS = 'A' "); // 4 + 4 till here

            statement = conn.prepareStatement(sql.toString());
            
            statement.setString(1, emergencyContactsBeanObj.getEmpNumber());
            statement.setString(2, emergencyContactsBeanObj.getLocation());
            statement.setString(3, emergencyContactsBeanObj.getEmpNumber());
            statement.setString(4, emergencyContactsBeanObj.getEmpNumber());

            logger.log(Level.INFO, "EmergencyContactsTxnHelper ::: deleteObject() :: SQL :: " + sql.toString());

            count = statement.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "EmergencyContactsTxnHelper ::: deleteObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
