/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.notifications;

import in.emp.home.notifications.bean.NotificationsPrezData;

/**
 *
 * @author Prajakta
 */
public interface NotificationsDelegate {
    
    public NotificationsPrezData getNotificationsList(NotificationsPrezData notificationsPrezDataObj) throws Exception;
    
    public NotificationsPrezData getAllNotificationsList(NotificationsPrezData notificationsPrezDataObj) throws Exception;
}
