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
public class GetAllNotificationsListQueryHelper implements QueryHelper {
    
    private static Logger logger = Logger.getLogger(GetAllNotificationsListQueryHelper.class);
    private NotificationsBean notificationsBeanObj = new NotificationsBean();
    private NotificationsPrezData notificationsPrezDataObj = new NotificationsPrezData();

    public GetAllNotificationsListQueryHelper(NotificationsBean notificationsBeanObj) {
        this.notificationsBeanObj = notificationsBeanObj;
    }

    public GetAllNotificationsListQueryHelper(NotificationsPrezData notificationsPrezDataObj) {
        this.notificationsPrezDataObj = notificationsPrezDataObj;
        this.notificationsBeanObj = this.notificationsPrezDataObj.getNotificationBeanObj();
    }    
    
    @Override
    public Object getDataObject(ResultSet results) throws Exception {
        NotificationsBean notificationsBean = new NotificationsBean();
        try {
            logger.log(Level.INFO, "GetAllNotificationsListQueryHelper ::: getDataObject() :: method called ::    ");

            notificationsBean.setEmpNumber(results.getString("EMP_NUMBER"));
            notificationsBean.setNotificationType(results.getString("CTYPE"));
            notificationsBean.setNotificationCount(results.getString("PENDING_COUNT"));

        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetAllNotificationsListQueryHelper :: getDataObject() :: Exception :: " + ex);
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
            logger.log(Level.INFO, "GetAllNotificationsListQueryHelper ::: getQueryResults() :: method called ::    ");

            sql.append(" SELECT EAN.PENDING_COUNT, EAN.EMP_NUMBER, EAN.CTYPE ");
            sql.append(" FROM XXMIS_EMP_ALL_NOTIFICATIONS EAN ");
            sql.append(" WHERE EAN.EMP_NUMBER = ? ");

            logger.log(Level.INFO, "GetAllNotificationsListQueryHelper :: getQueryResults() :: SQL :: " + sql.toString());

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, notificationsBeanObj.getEmpNumber());
            rs = statement.executeQuery();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "GetAllNotificationsListQueryHelper :: getQueryResults() :: Exception :: " + ex);
            throw ex;
        }
        return rs;
    }
    
}
