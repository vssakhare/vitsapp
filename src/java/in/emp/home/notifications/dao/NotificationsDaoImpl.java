/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.notifications.dao;

import in.emp.dao.BaseDao;
import in.emp.home.notifications.bean.NotificationsBean;
import in.emp.home.notifications.bean.NotificationsPrezData;
import in.emp.home.notifications.dao.helper.GetAllNotificationsListQueryHelper;
import in.emp.home.notifications.dao.helper.GetNotificationsListQueryHelper;
import java.util.LinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class NotificationsDaoImpl extends BaseDao implements NotificationsDao {
    
    private static Logger logger = Logger.getLogger(NotificationsDaoImpl.class);

    @Override
    public NotificationsPrezData getNotificationsList(NotificationsPrezData notificationsPrezDataObj) throws Exception {
        LinkedList<NotificationsBean> notificationsList = new LinkedList<NotificationsBean>();
        try {
            logger.log(Level.INFO, " NotificationsDaoImpl :: getNotificationsList() :: method called");

            notificationsList = (LinkedList<NotificationsBean>) getObjectList(new GetNotificationsListQueryHelper((notificationsPrezDataObj)));
            notificationsPrezDataObj.setNotificationsList(notificationsList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " NotificationsDaoImpl :: getNotificationsList() :: Exception :: " + ex);
        }
        return notificationsPrezDataObj;
    }
    
    @Override
    public NotificationsPrezData getAllNotificationsList(NotificationsPrezData notificationsPrezDataObj) throws Exception {
        LinkedList<NotificationsBean> notificationsList = new LinkedList<NotificationsBean>();
        try {
            logger.log(Level.INFO, " NotificationsDaoImpl :: getAllNotificationsList() :: method called");

            notificationsList = (LinkedList<NotificationsBean>) getObjectList(new GetAllNotificationsListQueryHelper((notificationsPrezDataObj)));
            notificationsPrezDataObj.setNotificationsList(notificationsList);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " NotificationsDaoImpl :: getAllNotificationsList() :: Exception :: " + ex);
        }
        return notificationsPrezDataObj;
    }
}
