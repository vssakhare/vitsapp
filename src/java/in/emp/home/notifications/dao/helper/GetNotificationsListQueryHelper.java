/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.notifications.dao.helper;

import in.emp.dao.QueryHelper;
import in.emp.home.notifications.bean.NotificationsBean;
import in.emp.home.notifications.bean.NotificationsPrezData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class GetNotificationsListQueryHelper implements QueryHelper {
    
    private static Logger logger = Logger.getLogger(GetNotificationsListQueryHelper.class);
    private NotificationsBean notificationsBeanObj = new NotificationsBean();
    private NotificationsPrezData notificationsPrezDataObj = new NotificationsPrezData();

    public GetNotificationsListQueryHelper(NotificationsBean notificationsBeanObj) {
        this.notificationsBeanObj = notificationsBeanObj;
    }

    public GetNotificationsListQueryHelper(NotificationsPrezData notificationsPrezDataObj) {
        this.notificationsPrezDataObj = notificationsPrezDataObj;
        this.notificationsBeanObj = this.notificationsPrezDataObj.getNotificationBeanObj();
    }    
    
    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        NotificationsBean notificationsBean = new NotificationsBean();
        try {
            logger.log(Level.INFO, "GetNotificationsListQueryHelper ::: getDataObject() :: method called ::    ");

            notificationsBean.setEmpNumber(results.getString("EMP_NUMBER"));
            notificationsBean.setNotificationType(results.getString("CTYPE"));
            notificationsBean.setNotificationCount(results.getString("PENDING_COUNT"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetNotificationsListQueryHelper :: getDataObject() :: Exception :: " + ex);
            throw ex;
        }
        return notificationsBean;
    }

    @Override
    public ResultSet getQueryResults(Connection connection) throws Exception {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        ResultSet rs = null;
        try {
            logger.log(Level.INFO, "GetNotificationsListQueryHelper ::: getQueryResults() :: method called ::    ");

            sql.append(" SELECT EHN.PENDING_COUNT, EHN.EMP_NUMBER, EHN.CTYPE ");
            sql.append(" FROM XXMIS_EMP_HOME_NOTIFICATIONS EHN ");
            sql.append(" WHERE EHN.EMP_NUMBER = ? ");

            logger.log(Level.INFO, "GetNotificationsListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, notificationsBeanObj.getEmpNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetNotificationsListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
    
}
