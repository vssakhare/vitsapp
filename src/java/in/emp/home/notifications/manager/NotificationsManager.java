/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.notifications.manager;

import in.emp.home.notifications.NotificationsDelegate;
import in.emp.home.notifications.bean.NotificationsPrezData;
import in.emp.home.notifications.dao.NotificationsDao;
import in.emp.home.notifications.dao.NotificationsDaoImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Prajakta
 */
public class NotificationsManager implements NotificationsDelegate {
    
    private static String CLASS_NAME = NotificationsManager.class.getName();
    private static Logger logger = Logger.getLogger(NotificationsManager.class);

    @Override
    public NotificationsPrezData getNotificationsList(NotificationsPrezData notificationsPrezDataObj) throws Exception {
        NotificationsDao notificationsDaoObj = new NotificationsDaoImpl();
        try {
            logger.log(Level.INFO, " NotificationsManager :: getNotificationsList() :: method called");

            notificationsPrezDataObj = (NotificationsPrezData) notificationsDaoObj.getNotificationsList(notificationsPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " NotificationsManager :: getNotificationsList() :: Exception :: " + ex);
        }
        return notificationsPrezDataObj;
    }
    
    @Override
    public NotificationsPrezData getAllNotificationsList(NotificationsPrezData notificationsPrezDataObj) throws Exception {
        NotificationsDao notificationsDaoObj = new NotificationsDaoImpl();
        try {
            logger.log(Level.INFO, " NotificationsManager :: getAllNotificationsList() :: method called");

            notificationsPrezDataObj = (NotificationsPrezData) notificationsDaoObj.getAllNotificationsList(notificationsPrezDataObj);

        } catch (Exception ex) {
            logger.log(Level.ERROR, " NotificationsManager :: getAllNotificationsList() :: Exception :: " + ex);
        }
        return notificationsPrezDataObj;
    }
}
