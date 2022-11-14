/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.security.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.user.bean.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Sachin
 */
public class GetOTPQueryHelper implements QueryHelper {

    private static Logger logger = Logger.getLogger(GetOTPQueryHelper.class);
    private UserBean userBeanObj = new UserBean();

    public GetOTPQueryHelper(UserBean userBeanObj) {
        this.userBeanObj = userBeanObj;
    }

    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        UserBean userBean = new UserBean();
        try {
            logger.log(Level.INFO, "GetOTPQueryHelper ::: getDataObject() :: method called ::");

            userBean.setUserName(results.getString("EMP_NUMBER"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetOTPQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return userBean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;

        try {
            logger.log(Level.INFO, "GetOTPQueryHelper :: getQueryResults() :: method called");

            sql.append(" SELECT ELO.EMP_NUMBER ");
            sql.append(" FROM XXMIS_EMP_LOGIN_OTP ELO ");
            sql.append(" WHERE ELO.USER_ID = ? ");
            sql.append(" AND ELO.OTP = ? ");
            sql.append(" AND ELO.EXPIRE_TIME >= SYSDATE ");
            sql.append(" AND ELO.STATUS = 'A' ");

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, userBeanObj.getUserName());
            statement.setString(2, userBeanObj.getNewPass());

            logger.log(Level.INFO, "GetOTPQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());
            
            rs = statement.executeQuery();
            
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetOTPQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }

        return rs;
    }

}
