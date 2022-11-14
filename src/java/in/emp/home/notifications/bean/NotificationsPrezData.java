/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.notifications.bean;

import java.util.LinkedList;

/**
 *
 * @author Prajakta
 */
public class NotificationsPrezData implements java.io.Serializable {

    private NotificationsBean notificationBeanObj = new NotificationsBean();
    private LinkedList<NotificationsBean> NotificationsList = new LinkedList<NotificationsBean>();

    public NotificationsBean getNotificationBeanObj() {
        return notificationBeanObj;
    }

    public void setNotificationBeanObj(NotificationsBean notificationBeanObj) {
        this.notificationBeanObj = notificationBeanObj;
    }

    public LinkedList<NotificationsBean> getNotificationsList() {
        return NotificationsList;
    }

    public void setNotificationsList(LinkedList<NotificationsBean> NotificationsList) {
        this.NotificationsList = NotificationsList;
    }
}
