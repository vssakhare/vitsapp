/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.home.notifications.bean;

/**
 *
 * @author Prajakta
 */
public class NotificationsBean implements java.io.Serializable {

    private String EmpNumber = "";
    private String NotificationType = "";
    private String NotificationCount = "";

    public String getEmpNumber() {
        return EmpNumber;
    }

    public void setEmpNumber(String EmpNumber) {
        this.EmpNumber = EmpNumber;
    }

    public String getNotificationType() {
        return NotificationType;
    }

    public void setNotificationType(String NotificationType) {
        this.NotificationType = NotificationType;
    }

    public String getNotificationCount() {
        return NotificationCount;
    }

    public void setNotificationCount(String NotificationCount) {
        this.NotificationCount = NotificationCount;
    }
}
