package in.emp.security.dao.helper;

import in.emp.dao.TxnHelper;
import in.emp.user.bean.UserBean;
import in.emp.util.ApplicationUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Sachin
 */
public class OTPTxnHelper implements TxnHelper {

    private static Logger logger = Logger.getLogger(OTPTxnHelper.class);
    private UserBean userBeanObj = new UserBean();

    public OTPTxnHelper(UserBean userBeanObj) {
        this.userBeanObj = userBeanObj;
    }

    @Override
    public Object createObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "OTPTxnHelper ::: createObject() :: method called ::");

            deleteObject(conn);
            
            userBeanObj.setUserId(ApplicationUtils.getNextSequenceId(conn, "XXMIS_EMP_LOGIN_OTP_SEQ"));
            
            sql.append(" INSERT INTO XXMIS_EMP_LOGIN_OTP ");
            sql.append(" ( ID, USER_ID, OTP, EXPIRE_TIME, ");
            sql.append(" USER_ID_CREATED, CREATED_SITE_ID, CREATED_TIME_STAMP, ");
            sql.append(" USER_ID_MODIFIED, MODIFIED_SITE_ID, MODIFIED_TIME_STAMP, STATUS ) ");
            sql.append(" VALUES ");
            sql.append(" ( ?, ?, ?, (SYSDATE + (1 / (24 * 60) * 15)), "); // 3 + 1 here
            sql.append(" ?, NULL, SYSTIMESTAMP, NULL, NULL, NULL, 'A' ) "); // 4 + 6 till here

            statement = conn.prepareStatement(sql.toString());

            logger.log(Level.INFO, "OTPTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            statement.setLong(1, userBeanObj.getUserId());
            statement.setString(2, userBeanObj.getUserName());
            statement.setString(3, userBeanObj.getNewPass());
            statement.setString(4, userBeanObj.getUserName());

            count = statement.executeUpdate();
            
            conn.commit();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OTPTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
        return userBeanObj;
    }

    @Override
    public void updateObject(Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteObject(Connection conn) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        int count = 0;
        try {
            logger.log(Level.INFO, "OTPTxnHelper ::: createObject() :: method called ::");
            
            sql.append(" UPDATE XXMIS_EMP_LOGIN_OTP ELO ");
            sql.append(" SET ELO.EXPIRE_TIME = SYSDATE, ELO.STATUS = 'E' ");
            sql.append(" WHERE ELO.USER_ID = ? ");
            sql.append(" AND ELO.STATUS = 'A' ");

            statement = conn.prepareStatement(sql.toString());

            logger.log(Level.INFO, "OTPTxnHelper ::: createObject() :: SQL :: " + sql.toString());

            statement.setString(1, userBeanObj.getUserName());

            count = statement.executeUpdate();
            
            conn.commit();

        } catch (Exception ex) {
            logger.log(Level.ERROR, "OTPTxnHelper ::: createObject() :: Exception :: " + ex);
            throw ex;
        }
    }

    @Override
    public void updateObjectAttribute(Connection conn, HashMap attributeMap) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
